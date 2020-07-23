/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         RequisitarArquivoDelegate.java
 * Data Criação:    Jun 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RequisitarArquivoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * RequisitarArquivoDelegate
 * 
 * @author rafael.silva
 */
public class RequisitarArquivoDelegate extends OperacionalDelegate<OperacionalServico> implements RequisitarArquivoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipDelegate#localizarServico()
     */
    @Override
    protected RequisitarArquivoServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarRequisitarArquivoServico();
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
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemGEN0014Servico#processarRequisicaoArquivo(br.com.sicoob.sisbr.sicoobdda
     *      .integracaocip.enums.TipoSolicitacaoArquivoBeneficiarioEnum)
     */
    public void processarRequisicaoArquivoCargaInicial() throws BancoobException {
        localizarServico().processarRequisicaoArquivoCargaInicial();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemGEN0014Servico#processarRequisicaoArquivoInventario()
     */
    public void processarRequisicaoArquivoInventario() throws BancoobException {
        localizarServico().processarRequisicaoArquivoInventario();
    }

}
