/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-interna-privada-ejb
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.integracao.interna.privada.negocio.delegates
 * Arquivo:         ContaCorrenteDelegate.java
 * Data Criação:    Nov 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParticipanteContaDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ContaCorrenteServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.IntegracaoInternaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoRetDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.locator.IntegracaoInternaServiceLocator;

/**
 * ContaCorrenteDelegate
 * 
 * @author George.santos
 */
public class ContaCorrenteDelegate extends IntegracaoInternaDelegate<IntegracaoInternaServico> implements ContaCorrenteServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ContaCorrenteServico localizarServico() {
        return IntegracaoInternaServiceLocator.getInstance().localizarContaCorrenteServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ContaCorrenteServico#listarParticipanteConta(java.lang.Long, java.lang.Long)
     */
    public List<ParticipanteContaDto> listarParticipanteConta(Integer idInstituicao, Long numContaCorrente) throws ComumException {
        return localizarServico().listarParticipanteConta(idInstituicao, numContaCorrente);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ContaCorrenteServico#listarNumContaCorrenteAtiva(java.lang.Integer,
     *      java.lang.String)
     */
    public List<String> listarNumContaCorrenteAtiva(Integer idInstituicao, String numCpfCnpj) throws ComumException {
        return localizarServico().listarNumContaCorrenteAtiva(idInstituicao, numCpfCnpj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ContaCorrenteServico#gravarLancamentoIntegracao(br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto)
     */
    public LancamentoIntegracaoRetDto gravarLancamentoIntegracao(LancamentoIntegracaoDto lancamentoIntegracaoDto) throws ComumException {
        return localizarServico().gravarLancamentoIntegracao(lancamentoIntegracaoDto);
    }

}