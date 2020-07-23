/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         RequisitarArquivoServicoEJB.java
 * Data Criação:    May 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoArquivoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.RequisitarArquivoServicoLocal;

/**
 * RequisitarArquivoServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ RequisitarArquivoServicoLocal.class })
public class RequisitarArquivoServicoEJB extends OperacionalServicoEJB implements RequisitarArquivoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RequisitarArquivoServico#processarRequisicaoArquivoCargaInicial()
     */
    public void processarRequisicaoArquivoCargaInicial() throws BancoobException {
        processarRequisicaoArquivo(TipoSolicitacaoArquivoBeneficiarioEnum.CARGA_INICIAL);
    }

    /**
     * Método responsável por
     * 
     * @param situacaoBeneficiarioEnum
     * @throws BancoobException void
     * 
     */
    public void processarRequisicaoArquivoInventario(SituacaoBeneficiarioEnum situacaoBeneficiarioEnum) throws BancoobException {
        processarRequisicaoArquivo(TipoSolicitacaoArquivoBeneficiarioEnum.INVENTARIO, situacaoBeneficiarioEnum);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RequisitarArquivoServico#processarRequisicaoArquivoInventario()
     */
    public void processarRequisicaoArquivoInventario() throws BancoobException {
        processarRequisicaoArquivo(TipoSolicitacaoArquivoBeneficiarioEnum.INVENTARIO);
    }

    /**
     * Método responsável por processar a requisicao do arquivo a ser solicitado junto a CIP
     * 
     * @param tipoSolicitacaoArquivoBeneficiario
     * @throws BancoobException void
     * 
     */
    private void processarRequisicaoArquivo(TipoSolicitacaoArquivoBeneficiarioEnum tipoSolicitacaoArquivoBeneficiario) throws BancoobException {
        processarRequisicaoArquivo(tipoSolicitacaoArquivoBeneficiario, null);
    }

    /**
     * Método responsável por a requisicao do arquivo a ser solicitado junto a CIP
     * 
     * @param tipoSolicitacaoArquivoBeneficiario
     * @param situacaoBeneficiarioEnum
     * @throws BancoobException void
     * 
     */
    private void processarRequisicaoArquivo(TipoSolicitacaoArquivoBeneficiarioEnum tipoSolicitacaoArquivoBeneficiario, SituacaoBeneficiarioEnum situacaoBeneficiarioEnum)
            throws BancoobException {
        // TODO: rafael.silva - Alterar implementação para Motor de Envio....
        IntegracaoCipFabricaDelegates.getInstance().getMensagemGEN0014Delegate().processarMensagem(tipoSolicitacaoArquivoBeneficiario, situacaoBeneficiarioEnum);
    }
}
