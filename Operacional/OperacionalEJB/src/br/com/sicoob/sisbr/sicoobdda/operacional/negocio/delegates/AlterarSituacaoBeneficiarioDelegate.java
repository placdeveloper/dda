/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         AlterarSituacaoBeneficiarioDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlteraSituacaoBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RetornoAlterarSituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarSituacaoBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * AlterarSituacaoBeneficiarioDelegate
 * 
 * @author rafael.silva
 */
public class AlterarSituacaoBeneficiarioDelegate extends OperacionalDelegate<OperacionalServico> implements AlterarSituacaoBeneficiarioServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected AlterarSituacaoBeneficiarioServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarAlterarSituacaoBeneficiarioServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarSituacaoBeneficiarioServico#alterarSituacaoBeneficiario(br.com.sicoob.sisbr
     *      .sicoobdda.integracaocip.dto.AlteraSituacaoBeneficiarioDto)
     */
    public RetornoAlterarSituacaoBeneficiarioEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioDto alteraSitBeneficiarioDto, Short idCanal)
            throws ComumException, ComumNegocioException {
        return localizarServico().processarAlterarSituacaoBeneficiario(alteraSitBeneficiarioDto, idCanal);

    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarSituacaoBeneficiarioServico#processarAlterarSituacaoBeneficiario(java.lang.String)
     */
    public void processarAlterarSituacaoBeneficiario(String numCpfCnpj, Short idCanal) throws ComumException, ComumNegocioException {
        localizarServico().processarAlterarSituacaoBeneficiario(numCpfCnpj, idCanal);
    }

}
