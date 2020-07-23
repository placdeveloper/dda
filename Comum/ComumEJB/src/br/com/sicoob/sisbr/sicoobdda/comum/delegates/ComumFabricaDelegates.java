/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         ComumFabricaDelegates.java
 * Data Criação:    27/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * ComumFabricaDelegates é responsável por
 * 
 * @author George.Santos
 */
public class ComumFabricaDelegates extends BancoobFabricaDelegates {

    private static ComumFabricaDelegates fabrica = null;

    private AtualizacaoCacheDelegate atualizacaoCacheDelegate;
    private AtualizaParametroDelegate atualizaParametroDelegate;
    private CacheDelegate cacheDelegate;
    private CacheNotificaDelegate cacheNotificaDelegate;
    private CapesDelegate capesDelegate;
    private DominioDDADelegate dominioDDADelegate;
    private EmailDelegate emailDelegate;
    private InstituicaoDelegate instituicaoDelegate;
    private ParametroDelegate parametroDelegate;
    private RequisicaoCacheDelegate requisicaoCacheDelegate;
    private ServidorDDADelegate servidorDDADelegate;

    /**
     * Método responsável por getInstance
     * 
     * @return ComumFabricaDelegates
     * 
     */
    public static ComumFabricaDelegates getInstance() {
        if (fabrica == null) {
            fabrica = new ComumFabricaDelegates();
        }
        return fabrica;
    }

    protected ComumFabricaDelegates() {
    }

    /**
     * Método responsável por responsavel por criar a InstituicaoDelegate
     * 
     * @return
     */
    public InstituicaoDelegate getInstituicaoDelegate() {
        if (instituicaoDelegate == null) {
            instituicaoDelegate = new InstituicaoDelegate();
        }
        return instituicaoDelegate;
    }

    /**
     * Método responsável por responsavel por criar AtualizaParametroDelegate
     * 
     * @return
     */
    public AtualizaParametroDelegate getAtualizaParametroDelegate() {
        if (atualizaParametroDelegate == null) {
            atualizaParametroDelegate = new AtualizaParametroDelegate();
        }
        return atualizaParametroDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return CacheDelegate
     */
    public CacheDelegate getCacheDelegate() {
        if (cacheDelegate == null) {
            cacheDelegate = new CacheDelegate();
        }
        return cacheDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return capesDelegate
     * 
     */
    public CapesDelegate getCapesDelegate() {
        if (capesDelegate == null) {
            capesDelegate = new CapesDelegate();
        }
        return capesDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ParametroDelegate
     */
    public ParametroDelegate getParametroDelegate() {
        if (parametroDelegate == null) {
            parametroDelegate = new ParametroDelegate();
        }
        return parametroDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return CacheNotificaServico
     * 
     */
    public CacheNotificaDelegate getCacheNotificaDelegate() {
        if (cacheNotificaDelegate == null) {
            cacheNotificaDelegate = new CacheNotificaDelegate();
        }
        return cacheNotificaDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return AtualizacaoCacheDelegate
     * 
     */
    public AtualizacaoCacheDelegate getAtualizacaoCacheDelegate() {
        if (atualizacaoCacheDelegate == null) {
            atualizacaoCacheDelegate = new AtualizacaoCacheDelegate();
        }
        return atualizacaoCacheDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return RequisicaoCacheDelegate
     * 
     */
    public RequisicaoCacheDelegate getRequisicaoCacheDelegate() {
        if (requisicaoCacheDelegate == null) {
            requisicaoCacheDelegate = new RequisicaoCacheDelegate();
        }
        return requisicaoCacheDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ServidorDDADelegate
     * 
     */
    public ServidorDDADelegate getServidorDDADelegate() {
        if (servidorDDADelegate == null) {
            servidorDDADelegate = new ServidorDDADelegate();
        }
        return servidorDDADelegate;
    }

    /**
     * Método responsável por
     * 
     * @return DominioDDADelegate
     * 
     */
    public DominioDDADelegate getDominioDDADelegate() {
        if (dominioDDADelegate == null) {
            dominioDDADelegate = new DominioDDADelegate();
        }
        return dominioDDADelegate;
    }

    /**
     * Método responsável por
     * 
     * @return EmailDelegate
     * 
     */
    public EmailDelegate getEmailDelegate() {
        if (emailDelegate == null) {
            emailDelegate = new EmailDelegate();
        }
        return emailDelegate;
    }

}
