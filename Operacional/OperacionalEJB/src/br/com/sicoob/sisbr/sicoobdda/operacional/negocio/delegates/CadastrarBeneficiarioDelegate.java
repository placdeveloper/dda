/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates
 * Arquivo:         CadastrarBeneficiarioDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CadastrarBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * CadastrarBeneficiarioDelegate é responsável por
 * 
 * @author rafael.silva
 */
public class CadastrarBeneficiarioDelegate extends OperacionalDelegate<OperacionalServico> implements CadastrarBeneficiarioServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected CadastrarBeneficiarioServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarCadastrarBeneficiarioServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.MensagemCadastrarBeneficiarioServico.negocio.servicos.sicoobdda.integracaocip.negocio.servicos.CadastrarBeneficiarioServico#processarCadastroBeneficiario(java.lang.String,
     *      java.util.List)
     */
    public void processarReenvioMensagemCadastroBeneficiario(String numCpfCnpj, List<Integer> listaNumCooperativa, Short idCanal) throws ComumException, ComumNegocioException {
        localizarServico().processarReenvioMensagemCadastroBeneficiario(numCpfCnpj, listaNumCooperativa, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.MensagemCadastrarBeneficiarioServico.negocio.servicos.sicoobdda.integracaocip.negocio.servicos.CadastrarBeneficiarioServico#processarCadastroBeneficiario(br.com.sicoob.sisbr.sicoobdda.entidades.sicoobdda.entidades.negocio.entidades.dda.BeneficiarioDDA,
     *      java.lang.Integer)
     */
    public void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException,
            ComumNegocioException {
        localizarServico().processarCadastroBeneficiario(numCliente, numCpfCnpj, numCooperativa, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.MensagemCadastrarBeneficiarioServico.negocio.servicos.sicoobdda.integracaocip.negocio.servicos.CadastrarBeneficiarioServico#processarCadastroBeneficiario(java.lang.Long)
     */
    public void processarCadastroBeneficiario(Long idBeneficiario, Short idCanal) throws ComumException, ComumNegocioException {
        localizarServico().processarCadastroBeneficiario(idBeneficiario, idCanal);
    }

}
