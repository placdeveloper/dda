/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.servicos.locator
 * Arquivo:         ComumServiceLocator.java
 * Data Cria��o:    30/09/2015
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
     * M�todo respons�vel por getInstance
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
     * M�todo respons�vel por recuperar servi�o do InstituicaoServico
     * 
     * @return
     */
    public InstituicaoServico localizarInstituicaoServico() {
        return (InstituicaoServico) localizar("servico.sicoobdda.comum.InstituicaoServico");
    }

    /**
     * M�todo respons�vel pela recupera��o do servico atualiza parametro
     */
    public AtualizaParametroServico localizarAtualizaParametroServico() {
        return (AtualizaParametroServico) localizar("servico.sicoobdda.comum.AtualizaParametroServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return CacheServico
     * 
     */
    public CacheServico localizarCacheServico() {
        return (CacheServico) localizar("servico.sicoobdda.comum.CacheServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return CacheNotificaServico
     * 
     */
    public CacheNotificaServico localizarCacheNotificaServico() {
        return (CacheNotificaServico) localizar("servico.sicoobdda.comum.CacheNotificaServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return CapesServico
     */
    public CapesServico localizarCapesServico() {
        return (CapesServico) localizar("servico.sicoobdda.comum.CapesServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return ParametroServico
     */
    public ParametroServico localizarParametroServico() {
        return (ParametroServico) localizar("servico.sicoobdda.comum.ParametroServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return RequisicaoCacheServico
     * 
     */
    public RequisicaoCacheServico localizarRequisicaoCacheServico() {
        return (RequisicaoCacheServico) localizar("servico.sicoobdda.comum.RequisicaoCacheServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return AtualizacaoCacheServico
     * 
     */
    public AtualizacaoCacheServico localizarAtualizacaoCacheServico() {
        return (AtualizacaoCacheServico) localizar("servico.sicoobdda.comum.AtualizacaoCacheServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return ServidorDDAServico
     * 
     */
    public ServidorDDAServico localizarServidorDDAServico() {
        return (ServidorDDAServico) localizar("servico.sicoobdda.comum.ServidorDDAServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return DominioDDAServico
     * 
     */
    public DominioDDAServico localizarDominioDDAServico() {
        return (DominioDDAServico) localizar("servico.sicoobdda.comum.DominioDDAServico");
    }

    /**
     * M�todo respons�vel por
     * 
     * @return EmailServico
     * 
     */
    public EmailServico localizarEmailServico() {
        return (EmailServico) localizar("servico.sicoobdda.comum.EmailServico");
    }
}
