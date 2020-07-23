/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates
 * Arquivo:         SicoobDDAAPIFabricaDelegates.java
 * Data Criação:    May 9, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobFabricaDelegates;

/**
 * SicoobDDAAPIFabricaDelegates é responsável por
 * 
 * @author Daniel.Basso
 */
public class SicoobDDAAPIFabricaDelegates extends BancoobFabricaDelegates {
    private static SicoobDDAAPIFabricaDelegates fabrica;

    private BeneficiarioAPIDelegate beneficiarioApiDelegate;
    private PagadorEletronicoDDAAPIDelegate pagadorEletronicoDDAAPIDelegate;
    private BoletoDDAAPIDelegate boletoDDAAPIDelegate;

    /**
     * Método responsável por recuperar a intância da Fábrida de Delegates da API do SicoobDDA
     * 
     * @return SicoobDDAAPIFabricaDelegates
     * 
     */
    public static SicoobDDAAPIFabricaDelegates getInstance() {
        if (fabrica == null) {
            fabrica = new SicoobDDAAPIFabricaDelegates();
        }
        return fabrica;
    }

    public SicoobDDAAPIFabricaDelegates() {
    }

    /**
     * Método responsável por criar o delegate
     * 
     * @return
     */
    public BeneficiarioAPIDelegate getBeneficiarioAPIDelegate() {
        if (beneficiarioApiDelegate == null) {
            beneficiarioApiDelegate = new BeneficiarioAPIDelegate();
        }
        return beneficiarioApiDelegate;
    }

    /**
     * Método responsável por criar o delegate
     * 
     * @return
     */
    public PagadorEletronicoDDAAPIDelegate getPagadorEletronicoDDAAPIDelegate() {
        if (pagadorEletronicoDDAAPIDelegate == null) {
            pagadorEletronicoDDAAPIDelegate = new PagadorEletronicoDDAAPIDelegate();
        }
        return pagadorEletronicoDDAAPIDelegate;
    }

    /**
     * Método responsável por criar o delegate
     * 
     * @return
     */
    public BoletoDDAAPIDelegate getBoletoDDAAPIDelegate() {
        if (boletoDDAAPIDelegate == null) {
            boletoDDAAPIDelegate = new BoletoDDAAPIDelegate();
        }

        return boletoDDAAPIDelegate;
    }

}
