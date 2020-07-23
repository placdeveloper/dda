/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         AlterarCadastroBeneficiarioDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioAlteracaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarCadastroBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * AlterarCadastroBeneficiarioDelegate
 * 
 * @author rafael.silva
 */
public class AlterarCadastroBeneficiarioDelegate extends OperacionalDelegate<OperacionalServico> implements AlterarCadastroBeneficiarioServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected AlterarCadastroBeneficiarioServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarAlterarCadastroBeneficiarioServico();
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
     * @throws ComumException
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarCadastroBeneficiarioServico#alterarCadastroBeneficiario(java.lang.Long,
     *      java.lang.Integer, java.lang.Long, java.util.Date)
     */
    public void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTimeDB dataAtualMovimento, Short idCanal)
            throws ComumNegocioException, ComumException {
        localizarServico().alterarCadastroBeneficiario(numCliente, idInstituicao, codSituacaoCedente, dataCancelamento, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarCadastroBeneficiarioServico#alterarCadastroBeneficiario(java.lang.Long,
     *      java.lang.Integer, java.lang.Long)
     */
    public void alterarCadastroBeneficiario(Long numCliente, Integer numCooperativa, Long codSituacaoCedente, BeneficiarioDDA beneficiario, DateTimeDB dataAtualMovimento,
            Short idCanal) throws ComumException, ComumNegocioException {
        localizarServico().alterarCadastroBeneficiario(numCliente, numCooperativa, codSituacaoCedente, beneficiario, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarCadastroBeneficiarioServico#excluirConvenioBeneficiario(java.lang.Long,
     *      java.lang.Integer)
     */
    public void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumNegocioException, ComumException {
        localizarServico().excluirConvenioBeneficiario(numCliente, numCooperativa, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemAlterarCadastroBeneficiarioServico#alterarCadastroBeneficiario(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA,
     *      java.util.List)
     */
    public void processarReenvioAlterarCadastroBeneficiario(BeneficiarioDDA beneficiario, List<ConvenioAlteracaoDDADto> listaConvenio, Short idCanal) throws ComumNegocioException,
            ComumException {
        localizarServico().processarReenvioAlterarCadastroBeneficiario(beneficiario, listaConvenio, idCanal);
    }
}