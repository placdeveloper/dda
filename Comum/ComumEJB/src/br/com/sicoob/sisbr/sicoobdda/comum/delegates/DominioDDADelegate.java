/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         DominioDDADelegate.java
 * Data Criação:    22 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;

/**
 * DominioDDADelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class DominioDDADelegate extends ComumCrudDelegate<SicoobDDAEntidade, DominioDDAServico> implements DominioDDAServico {

    /**
     * Método utilizado pelo CacheEnum
     * 
     * @return DominioDDADelegate
     * 
     */
    public static DominioDDADelegate getInstance() {
        return ComumFabricaDelegates.getInstance().getDominioDDADelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected DominioDDAServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarDominioDDAServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    @Override
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#listarAutorizacaoDivergente()
     */
    @Override
    public List<AutorizacaoValorDivergente> listarAutorizacaoDivergente() throws ComumException {
        return localizarServico().listarAutorizacaoDivergente();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#listarAutorizacaoDivergenteBanco()
     */
    @Override
    public List<AutorizacaoValorDivergente> listarAutorizacaoDivergenteBanco() throws ComumException {
        return localizarServico().listarAutorizacaoDivergenteBanco();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#listarSituacaoBoletoPagamento()
     */
    @Override
    public List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamento() throws ComumException {
        return localizarServico().listarSituacaoBoletoPagamento();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#listarSituacaoBoletoPagamentoBanco()
     */
    @Override
    public List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamentoBanco() throws ComumException {
        return localizarServico().listarSituacaoBoletoPagamentoBanco();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#obterGradeHorariaReferenciaDDA0110()
     */
    @Override
    public GradeHoraria obterGradeHorariaReferenciaDDA0110() throws ComumException {
        return localizarServico().obterGradeHorariaReferenciaDDA0110();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico#obterGradeHorariaReferenciaDDA0110Banco()
     */
    @Override
    public GradeHoraria obterGradeHorariaReferenciaDDA0110Banco() throws ComumException {
        return localizarServico().obterGradeHorariaReferenciaDDA0110Banco();
    }

}
