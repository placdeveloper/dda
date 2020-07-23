/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         MensagemSerie0200Delegate.java
 * Data Criação:    Jan 16, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemSerie0200Servico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * MensagemSerie0200Delegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemSerie0200Delegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements MensagemSerie0200Servico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected MensagemSerie0200Servico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarMensagemSerie0200Servico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemSerie0200Servico#incluir(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto,
     *      java.lang.String, java.lang.Integer)
     */
    public void incluir(CadastroSerie0200Dto cadastroDto, String codTipoMensagem, Integer numPrioridadeEnvio) throws BancoobException {
        localizarServico().incluir(cadastroDto, codTipoMensagem, numPrioridadeEnvio);
    }

}
