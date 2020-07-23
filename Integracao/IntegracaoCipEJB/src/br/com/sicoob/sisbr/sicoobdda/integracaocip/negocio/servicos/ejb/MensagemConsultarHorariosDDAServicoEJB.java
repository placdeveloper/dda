package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultarHorariosDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0401.DDA0401R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0401.GrupoDDA0401R1GrdHrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemConsultarHorariosDDAServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Local({ MensagemConsultarHorariosDDAServicoLocal.class })
public class MensagemConsultarHorariosDDAServicoEJB extends IntegracaoCipServicoEJB implements MensagemConsultarHorariosDDAServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        debug("########### Inicio processarMensagem(Long idMensagem) para DDA0401");
        MensagemDDA mensagemDDA = getDao().obterMensagemDDA(idMensagem);
        debug("MensagemDDA: " + mensagemDDA);

        /*
         * Esse processo já teve a mensagem XML preenchida quando veio da tela, por isso recupera o XML que já foi salvo.
         */
        String xml = mensagemDDA.getXmlMensagem();
        debug("Xml MensagemDDA: " + xml);

        // Se não houver xml
        if (ObjectUtil.isEmpty(xml)) {
            throw new ComumException("integracaocip.erro.xml.mensagem.invalido");
        }

        // Substituição de "&lt" e "&gt" por "<" e ">" gerados no marshal do campo CDATA "critSelec".
        xml = xml.replace("&lt;", "<").replace("&gt;", ">");
        debug("Xml MensagemDDA - após replace: " + xml);

        return xml;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) para DDA0401");

        DDA0401R1ComplexType gradeHorarioRetorno = (DDA0401R1ComplexType) retornoDDA;

        for (GrupoDDA0401R1GrdHrioComplexType grupoDDA0401R1GrdHrio : gradeHorarioRetorno.getGrupoDDA0401R1GrdHrio()) {
            if (grupoDDA0401R1GrdHrio.getTpHrio().equals(Constantes.TIPO_HORARIO_PADRAO)) {
                alterarTipoGradeHoraria(gradeHorarioRetorno, grupoDDA0401R1GrdHrio);
            } else if (grupoDDA0401R1GrdHrio.getTpHrio().equals(Constantes.TIPO_HORARIO_EVENTUAL)) {
                incluirGradeHoraria(gradeHorarioRetorno, grupoDDA0401R1GrdHrio);
            }
        }

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Object retornoDDA) para DDA0401");

    }

    /**
     * Método responsável por
     * 
     * @param gradeHorarioRetorno
     * @param grupoDDA0401R1GrdHrio
     * @throws ComumException void
     * 
     */
    private void alterarTipoGradeHoraria(DDA0401R1ComplexType gradeHorarioRetorno, GrupoDDA0401R1GrdHrioComplexType grupoDDA0401R1GrdHrio) throws ComumException {
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria(grupoDDA0401R1GrdHrio.getCodGrd());

        tipoGradeHoraria.setDataHoraAberturaPadrao(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0401R1GrdHrio.getDtHrAbert()));
        tipoGradeHoraria.setDataHoraFechamentoPadrao(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0401R1GrdHrio.getDtHrFcht()));

        dao.atualizarTipoGradeHoraria(tipoGradeHoraria);
    }

    /**
     * Método responsável por
     * 
     * @param gradeHorarioRetorno
     * @param grupoDDA0401R1GrdHrio
     * @throws ComumException void
     * 
     */
    private void incluirGradeHoraria(DDA0401R1ComplexType gradeHorarioRetorno, GrupoDDA0401R1GrdHrioComplexType grupoDDA0401R1GrdHrio) throws ComumException {
        GradeHoraria gradeHoraria = new GradeHoraria();

        gradeHoraria.setDataReferencia(DataCipUtil.xMLGregorianCalendarParaDateTime(gradeHorarioRetorno.getDtRef()));
        gradeHoraria.setTipoGradeHoraria(new TipoGradeHoraria(grupoDDA0401R1GrdHrio.getCodGrd()));

        mensagemDao.excluirGradeHoraria(gradeHoraria.getDataReferencia(), gradeHoraria.getTipoGradeHoraria().getCodTipoGradeHoraria());

        gradeHoraria.setDataHoraAbertura(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0401R1GrdHrio.getDtHrAbert()));
        gradeHoraria.setDataHoraFechamento(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0401R1GrdHrio.getDtHrFcht()));
        em.persist(gradeHoraria);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return o atributo dao
     */
    public IntegracaoCipDao getDao() {
        return dao;
    }

    /**
     * Define o atributo dao
     */
    public void setDao(IntegracaoCipDao dao) {
        this.dao = dao;
    }

    /**
     * @return o atributo mensagemDao
     */
    public MensagemDDADao getMensagemDao() {
        return mensagemDao;
    }

    /**
     * Define o atributo mensagemDao
     */
    public void setMensagemDao(MensagemDDADao mensagemDao) {
        this.mensagemDao = mensagemDao;
    }

    /**
     * Define o atributo em
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

}
