/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.servicos.locator
 * Arquivo:         ComumServiceLocator.java
 * Data Criação:    30/09/2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizaParametroServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizacaoCacheServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CacheNotificaServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CacheServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CapesServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.DominioDDAServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.EmailServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.InstituicaoServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ParametroServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.RequisicaoCacheServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico;

/**
 * ComumServiceLocator
 * 
 * @author Fabricio.Brandao
 */
public class ComumServiceLocator extends BancoobServiceLocator {

    private static ComumServiceLocator instance;

    /**
     * Construtor
     */
    protected ComumServiceLocator() {
        super("sicoobdda_comum");
    }

    /**
     * Método responsável por getInstance
     * 
     * @return ComumServiceLocator
     * 
     */
    public static ComumServiceLocator getInstance() {
        if (instance == null) {
            instance = new ComumServiceLocator();
        }
        return instance;
    }

    /**
     * @param nomeAplicacao
     */
    protected ComumServiceLocator(String nomeAplicacao) {
        super(nomeAplicacao);
    }

    /**
     * Método responsável por recuperar serviço do InstituicaoServico
     * 
     * @return
     */
    public InstituicaoServico localizarInstituicaoServico() {
        return (InstituicaoServico) localizar("servico.sicoobdda.comum.InstituicaoServico");
    }

    /**
     * Método responsável pela recuperação do servico atualiza parametro
     */
    public AtualizaParametroServico localizarAtualizaParametroServico() {
        return (AtualizaParametroServico) localizar("servico.sicoobdda.comum.AtualizaParametroServico");
    }

    /**
     * Método responsável por
     * 
     * @return CacheServico
     * 
     */
    public CacheServico localizarCacheServico() {
        return (CacheServico) localizar("servico.sicoobdda.comum.CacheServico");
    }

    /**
     * Método responsável por
     * 
     * @return CacheNotificaServico
     * 
     */
    public CacheNotificaServico localizarCacheNotificaServico() {
        return (CacheNotificaServico) localizar("servico.sicoobdda.comum.CacheNotificaServico");
    }

    /**
     * Método responsável por
     * 
     * @return CapesServico
     */
    public CapesServico localizarCapesServico() {
        return (CapesServico) localizar("servico.sicoobdda.comum.CapesServico");
    }

    /**
     * Método responsável por
     * 
     * @return ParametroServico
     */
    public ParametroServico localizarParametroServico() {
        return (ParametroServico) localizar("servico.sicoobdda.comum.ParametroServico");
    }

    /**
     * Método responsável por
     * 
     * @return RequisicaoCacheServico
     * 
     */
    public RequisicaoCacheServico localizarRequisicaoCacheServico() {
        return (RequisicaoCacheServico) localizar("servico.sicoobdda.comum.RequisicaoCacheServico");
    }

    /**
     * Método responsável por
     * 
     * @return AtualizacaoCacheServico
     * 
     */
    public AtualizacaoCacheServico localizarAtualizacaoCacheServico() {
        return (AtualizacaoCacheServico) localizar("servico.sicoobdda.comum.AtualizacaoCacheServico");
    }

    /**
     * Método responsável por
     * 
     * @return ServidorDDAServico
     * 
     */
    public ServidorDDAServico localizarServidorDDAServico() {
        return (ServidorDDAServico) localizar("servico.sicoobdda.comum.ServidorDDAServico");
    }

    /**
     * Método responsável por
     * 
     * @return DominioDDAServico
     * 
     */
    public DominioDDAServico localizarDominioDDAServico() {
        return (DominioDDAServico) localizar("servico.sicoobdda.comum.DominioDDAServico");
    }

    /**
     * Método responsável por
     * 
     * @return EmailServico
     * 
     */
    public EmailServico localizarEmailServico() {
        return (EmailServico) localizar("servico.sicoobdda.comum.EmailServico");
    }
}
