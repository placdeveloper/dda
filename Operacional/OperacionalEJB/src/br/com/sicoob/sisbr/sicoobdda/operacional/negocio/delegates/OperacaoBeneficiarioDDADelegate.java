/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         OperacaoBeneficiarioDDADelegate.java
 * Data Criação:    Aug 6, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacaoBeneficiarioDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * OperacaoBeneficiarioDDADelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class OperacaoBeneficiarioDDADelegate extends OperacionalDelegate<OperacionalServico> implements OperacaoBeneficiarioDDAServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected OperacaoBeneficiarioDDAServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarOperacaoBeneficiarioDDASevico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacaoBeneficiarioDDAServico#validaSituacaoBeneficiarioCIP(java.lang.String)
     */
    public void validaSituacaoBeneficiarioCIP(String numCpfCnpj) throws ComumException, ComumNegocioException {
        localizarServico().validaSituacaoBeneficiarioCIP(numCpfCnpj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.OperacaoBeneficiarioDDAServico#validaOperacaoCadastroCedente(java.lang.Long,
     *      java.lang.Long)
     */
    public boolean permiteRealizarOperacoesCobranca(String numCPFCNPJ) throws ComumException {
        return localizarServico().permiteRealizarOperacoesCobranca(numCPFCNPJ);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacaoBeneficiarioDDAServico#beneficiarioEstaNaCip(java.lang.String)
     */
    public boolean beneficiarioEstaNaCip(String numCPFCNPJ) throws ComumException {
        return localizarServico().beneficiarioEstaNaCip(numCPFCNPJ);
    }

}
