/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-interna-privada-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.negocio.delegates
 * Arquivo:         SCIDelegate.java
 * Data Criação:    Oct 22, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.IntegracaoInternaServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.SCIServico;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.locator.IntegracaoInternaServiceLocator;

/**
 * SCIDelegate
 * 
 * @author Samuell.Ramos
 */
@Deprecated
public class SCIDelegate extends IntegracaoInternaDelegate<IntegracaoInternaServico> implements SCIServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected SCIServico localizarServico() {
        return IntegracaoInternaServiceLocator.getInstance().localizarSCIServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.sicoobdda.integracao.interna.privada.negocio.servicos.SCIServico#obterInstituicaoCache(java.lang.Integer)
     */
    @Deprecated
    public InstituicaoDto obterInstituicaoCache(Integer idInstituicao) throws ComumException {
        return localizarServico().obterInstituicaoCache(idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.sicoobdda.integracao.interna.privada.negocio.servicos.SCIServico#obterInstituicaoPorCooperativaCache(java.lang.Integer)
     */
    @Deprecated
    public InstituicaoDto obterInstituicaoPorCooperativaCache(Integer numeroCooperativa) throws ComumException {
        return localizarServico().obterInstituicaoPorCooperativaCache(numeroCooperativa);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

}
