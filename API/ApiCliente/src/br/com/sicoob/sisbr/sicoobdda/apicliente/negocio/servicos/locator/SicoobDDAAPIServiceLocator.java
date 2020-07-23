/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator
 * Arquivo:         SicoobDDAServiceLocator.java
 * Data Criação:    May 9, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.PagadorEletronicoDDAAPIServico;

/**
 * SicoobDDAServiceLocator é responsável por
 * 
 * @author Daniel.Basso
 */
public class SicoobDDAAPIServiceLocator extends BancoobServiceLocator {

    private static SicoobDDAAPIServiceLocator locator;

    public static SicoobDDAAPIServiceLocator getInstance() {
        if (locator == null) {
            locator = new SicoobDDAAPIServiceLocator();
        }
        return locator;
    }

    protected SicoobDDAAPIServiceLocator() {
        super("sicoobdda_apicliente");
    }

    /**
     * Método responsável por recuperar os serviços referente ao Pagador na integração com a CIP.
     */
    public BeneficiarioAPIServico getBeneficiarioAPIServico() {
        return (BeneficiarioAPIServico) localizar("servico.sicoobdda.apicliente.BeneficiarioAPIServico");
    }

    /**
     * Método responsável por recuperar o serviço.
     */
    public PagadorEletronicoDDAAPIServico getPagadorEletronicoDDAAPIServico() {
        return (PagadorEletronicoDDAAPIServico) localizar("servico.sicoobdda.apicliente.PagadorEletronicoDDAAPIServico");
    }

    /**
     * Método responsável por recuperar o serviço.
     */
    public BoletoDDAAPIServico getBoletoDDAAPIServico() {
        return (BoletoDDAAPIServico) localizar("servico.sicoobdda.apicliente.BoletoDDAAPIServico");
    }

}
