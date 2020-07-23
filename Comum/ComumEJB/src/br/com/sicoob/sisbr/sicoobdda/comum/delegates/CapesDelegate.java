/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         CapesDelegate.java
 * Data Criação:    Oct 1, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CapesServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;

// TODO: Auto-generated Javadoc
/**
 * CapesDelegate é responsável por.
 *
 * @author george.santos
 */
public class CapesDelegate extends ComumDelegate<CapesServico> implements CapesServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected CapesServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarCapesServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CapesServico#obterPessoa(java.lang.String, java.lang.Integer)
     */
    public PessoaDto obterPessoa(String cpfCnpj, Integer idInstituicao) throws ComumException {
        return localizarServico().obterPessoa(cpfCnpj, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CapesServico#obterPessoa(java.lang.Long, java.lang.Integer)
     */
    @Override
    public PessoaDto obterPessoa(Long idPessoa, Integer idInstituicao) throws ComumException {
        return localizarServico().obterPessoa(idPessoa, idInstituicao);
    }

}
