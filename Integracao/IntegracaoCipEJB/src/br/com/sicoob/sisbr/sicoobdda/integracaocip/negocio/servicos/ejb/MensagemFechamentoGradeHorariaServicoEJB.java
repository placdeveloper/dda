package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemFechamentoGradeHorariaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0404.DDA0404ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

@Stateless
@Local({ MensagemFechamentoGradeHorariaServicoLocal.class })
public class MensagemFechamentoGradeHorariaServicoEJB extends IntegracaoCipServicoEJB implements MensagemFechamentoGradeHorariaServicoLocal {

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
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) para DDA0404");

        DDA0404ComplexType gradeHorarioRetorno = (DDA0404ComplexType) retornoDDA;

        GradeHoraria gradeHoraria = new GradeHoraria();
        gradeHoraria.setDataHoraFechamento(DataCipUtil.xMLGregorianCalendarParaDateTime(gradeHorarioRetorno.getDtHrFcht()));
        gradeHoraria.setTipoGradeHoraria(new TipoGradeHoraria(gradeHorarioRetorno.getCodGrd()));
        gradeHoraria.setDataReferencia(DataCipUtil.xMLGregorianCalendarParaDateTime(gradeHorarioRetorno.getDtMovto()));

        mensagemDao
                .atualizarGradeHoraria(gradeHoraria.getDataReferencia(), gradeHoraria.getTipoGradeHoraria().getCodTipoGradeHoraria(), null, gradeHoraria.getDataHoraFechamento());

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Object retornoDDA) para DDA0404");
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
