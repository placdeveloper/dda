/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator
 * Arquivo:         SicoobDDAServiceLocator.java
 * Data Cria��o:    May 9, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.PagadorEletronicoDDAAPIServico;

/**
 * SicoobDDAServiceLocator � respons�vel por
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
     * M�todo respons�vel por recuperar os servi�os referente ao Pagador na integra��o com a CIP.
     */
    public BeneficiarioAPIServico getBeneficiarioAPIServico() {
        return (BeneficiarioAPIServico) localizar("servico.sicoobdda.apicliente.BeneficiarioAPIServico");
    }

    /**
     * M�todo respons�vel por recuperar o servi�o.
     */
    public PagadorEletronicoDDAAPIServico getPagadorEletronicoDDAAPIServico() {
        return (PagadorEletronicoDDAAPIServico) localizar("servico.sicoobdda.apicliente.PagadorEletronicoDDAAPIServico");
    }

    /**
     * M�todo respons�vel por recuperar o servi�o.
     */
    public BoletoDDAAPIServico getBoletoDDAAPIServico() {
        return (BoletoDDAAPIServico) localizar("servico.sicoobdda.apicliente.BoletoDDAAPIServico");
    }

}
