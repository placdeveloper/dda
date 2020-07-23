/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ConsultarBeneficiarioDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ConsultarBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * ConsultarBeneficiarioDelegate
 * 
 * @author rafael.silva
 */
public class ConsultarBeneficiarioDelegate extends OperacionalDelegate<OperacionalServico> implements ConsultarBeneficiarioServico {

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected ConsultarBeneficiarioServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarConsultarBeneficiarioServico();
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultarBeneficiarioServico#consultarBeneficiario(br.com.sicoob.sisbr.sicoobdda
     * .integracaocip.dto.ConsultaBeneficiarioDto)
     */
    public void processarConsultaBeneficiarioPorCnpjCpf(TipoPessoaEnum tipoPessoaBeneficiarioEnum, String cnpjCpfBeneficiario) throws ComumNegocioException, ComumException {
        localizarServico().processarConsultaBeneficiarioPorCnpjCpf(tipoPessoaBeneficiarioEnum, cnpjCpfBeneficiario);

    }

}
