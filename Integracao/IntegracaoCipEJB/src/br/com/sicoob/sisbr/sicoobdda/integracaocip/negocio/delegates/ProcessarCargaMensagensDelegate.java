/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ProcessarCargaMensagensDelegate.java
 * Data Criação:    Ago 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ProcessarCargaMensagensDelegate
 * 
 * @author Rafael.Silva
 */
public class ProcessarCargaMensagensDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ProcessarCargaMensagensServico {

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ProcessarCargaMensagensServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarProcessarCargaMensagensServico();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#gerarCargaDadosLegado(java.lang.Integer)
     */
    public void gerarCargaDadosLegadoInclusao(Integer numCooperativa) throws ComumException {
        localizarServico().gerarCargaDadosLegadoInclusao(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#gerarCargaDadosLegadoAlteracao(java.lang.Integer)
     */
    public void gerarCargaDadosLegadoAlteracao(Integer numCooperativa) throws ComumException {
        localizarServico().gerarCargaDadosLegadoAlteracao(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#gerarCargaDadosLegadoBaixa(java.lang.Integer)
     */
    public void gerarCargaDadosLegadoBaixa(Integer numCooperativa) throws ComumException {
        localizarServico().gerarCargaDadosLegadoBaixa(numCooperativa);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#processarCadastroMensagensPagador(java.lang.Integer)
     */
    public void processarCadastroMensagensPagador(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException {
        localizarServico().processarCadastroMensagensPagador(numCooperativa, qtdAgrupamentoCooperativa);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#processarCadastroMensagensBoleto(java.lang.Integer)
     */
    public void processarCadastroMensagensBoleto(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException {
        localizarServico().processarCadastroMensagensBoleto(numCooperativa, qtdAgrupamentoCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#processarCadastroMensagensBaixaEfetiva(java.lang.Integer)
     */
    public void processarCadastroMensagensBaixaEfetiva(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException {
        localizarServico().processarCadastroMensagensBaixaEfetiva(numCooperativa, qtdAgrupamentoCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#obterQtdAgrupamentoCooperativa(java.lang.Integer)
     */
    public Integer obterQtdAgrupamentoCooperativa(Integer numCooperativa) throws ComumException {
        return localizarServico().obterQtdAgrupamentoCooperativa(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#listaAgrupaCooperativaEspecificaBoleto(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaBoleto(Integer numCooperativa) {
        return localizarServico().listaAgrupaCooperativaEspecificaBoleto(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#listaAgrupaCooperativaEspecificaBaixa(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaBaixa(Integer numCooperativa) {
        return localizarServico().listaAgrupaCooperativaEspecificaBaixa(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#listaAgrupaCooperativaEspecificaPagador(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaPagador(Integer numCooperativa) {
        return localizarServico().listaAgrupaCooperativaEspecificaPagador(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#processarCadastroMensagens(java.lang.Integer,
     *      java.lang.Integer, java.lang.String)
     */
    public void processarCadastroMensagens(Integer numCooperativa, Integer nrAgrupamento, String codTipoOperacao) throws ComumException {
        localizarServico().processarCadastroMensagens(numCooperativa, nrAgrupamento, codTipoOperacao);
    }

}
