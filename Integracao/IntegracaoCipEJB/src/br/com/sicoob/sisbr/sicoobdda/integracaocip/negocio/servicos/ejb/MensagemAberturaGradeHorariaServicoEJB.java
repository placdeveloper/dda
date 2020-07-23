package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemAberturaGradeHorariaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0403.DDA0403ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAberturaGradeHorariaServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemAberturaGradeHorariaServicoLocal.class })
public class MensagemAberturaGradeHorariaServicoEJB extends IntegracaoCipServicoEJB implements MensagemAberturaGradeHorariaServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) para DDA0403");

        DDA0403ComplexType gradeHorarioRetorno = (DDA0403ComplexType) retornoDDA;

        GradeHoraria gradeHoraria = new GradeHoraria();
        gradeHoraria.setDataHoraAbertura(DataCipUtil.xMLGregorianCalendarParaDateTime(gradeHorarioRetorno.getDtHrAbert()));
        gradeHoraria.setTipoGradeHoraria(new TipoGradeHoraria(gradeHorarioRetorno.getCodGrd()));
        gradeHoraria.setDataReferencia(DataCipUtil.xMLGregorianCalendarParaDateTime(gradeHorarioRetorno.getDtMovto()));

        mensagemDao.atualizarGradeHoraria(gradeHoraria.getDataReferencia(), gradeHoraria.getTipoGradeHoraria().getCodTipoGradeHoraria(), gradeHoraria.getDataHoraAbertura(), null);

        if (gradeHoraria.getTipoGradeHoraria().getCodTipoGradeHoraria().equals(TipoGradeHoraria.DDA12)) {
            registrarRequisicaoAtualizacaoCache();
        }

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) para DDA0403");

    }

    /**
     * Método responsável por 
     * @throws ComumException void
     * 
     */
    private void registrarRequisicaoAtualizacaoCache() throws ComumException {
        try {
            IntegracaoCipFabricaDelegates.getInstance().getRequisicaoCacheDelegate().incluir(CacheEnum.DOMINIO_GRADE_HORARIA_DDA0110);
        } catch (ComumNegocioException e) {
            // Essa exceção só é lançada no backOffice.
            getLogger().erro(e, e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }
}
