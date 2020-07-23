package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemAlterarHorariosDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0402.DDA0402ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0402.GrupoDDA0402GrdHrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAlterarHorariosDDAServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemAlterarHorariosDDAServicoLocal.class })
public class MensagemAlterarHorariosDDAServicoEJB extends IntegracaoCipServicoEJB implements MensagemAlterarHorariosDDAServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) para DDA0402");

        DDA0402ComplexType gradeHorarioRetorno = (DDA0402ComplexType) retornoDDA;

        for (GrupoDDA0402GrdHrioComplexType grupoDDA0402GrdHrio : gradeHorarioRetorno.getGrupoDDA0402GrdHrio()) {
            GradeHoraria gradeHoraria = new GradeHoraria();
            gradeHoraria.setDataReferencia(DataCipUtil.xMLGregorianCalendarParaDateTime(gradeHorarioRetorno.getDtRef()));
            gradeHoraria.setTipoGradeHoraria(new TipoGradeHoraria(grupoDDA0402GrdHrio.getCodGrd()));

            mensagemDao.excluirGradeHoraria(gradeHoraria.getDataReferencia(), gradeHoraria.getTipoGradeHoraria().getCodTipoGradeHoraria());

            gradeHoraria.setDataHoraAbertura(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0402GrdHrio.getDtHrAbert()));
            gradeHoraria.setDataHoraFechamento(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0402GrdHrio.getDtHrFcht()));
            em.persist(gradeHoraria);
        }

        getLogger().debug("########### Fim processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) para DDA0401");

    }

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }
}
