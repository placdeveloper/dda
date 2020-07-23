/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator
 * Arquivo:         SicoobDDAServiceLocator.java
 * Data Cria��o:    May 9, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.api.implementacao.negocio.servicos.locator;

import br.com.bancoob.negocio.servicos.locator.BancoobServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BoletoDDAAPIServico;

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
     * @param nomeAplicacao
     */
    protected SicoobDDAAPIServiceLocator(String nomeAplicacao) {
        super(nomeAplicacao);
    }

    /**
     * Metodo responsavel por recuperar os servi�os referente ao Pagador na integra��o com a CIP.
     */
    public BeneficiarioAPIServico getBeneficiarioAPIServico() {
        return (BeneficiarioAPIServico) localizar("servico.sicoobdda.apicliente.BeneficiarioAPIServico");
    }

    /**
     * Metodo responsavel por recuperar o servi�o.
     */
    public BoletoDDAAPIServico getBoletoDDAAPIServico() {
        return (BoletoDDAAPIServico) localizar("servico.sicoobdda.apicliente.BoletoDDAAPIServico");
    }

}
