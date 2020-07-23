package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemCancelamentoBaixaOperacionalServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0115.DDA0115ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0115.DDA0115R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemCancelamentoBaixaOperacionalServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemCancelamentoBaixaOperacionalServicoLocal.class })
public class MensagemCancelamentoBaixaOperacionalServicoEJB extends IntegracaoCipServicoEJB implements MensagemCancelamentoBaixaOperacionalServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemBaixaOperacionalDao mensagemDao;

    private static final int MENOS_CINCO_MINUTOS = -5;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0115 - Participante solicita cancelamento da baixa operacional");
        MensagemDDABaixaOperacional mensagem = mensagemDao.obterMensagemDDABaixaOperacionalAtualizaReferencias(idMensagem);

        DDA0115ComplexType dda0115 = getDDA0115ComplexType(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0115, mensagem.getId());
        getLogger().debug("xmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0115 - Participante solicita cancelamento da baixa operacional");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Object retornoDDA) - DDA0115R1, DDA115R2, ADDA115RR2");

        BoletoDDABaixaOper boletoDDABaixaOper = getDao().obterBoletoDDABaixaOperacional(retornoDDA.getNumIdent());

        if (!ObjectUtil.isNull(boletoDDABaixaOper) && !ObjectUtil.isNull(boletoDDABaixaOper.getId())) {
            getDao().atualizarSituacaoBoletoDDABaixaOper(retornoDDA.getNumIdent(), SituacaoBaixaOperacional.CANCELADA_PELO_PARTICIPANTE);
            if (retornoDDA instanceof DDA0115R2ComplexType) {
                DDA0115R2ComplexType retornoR2DDA = (DDA0115R2ComplexType) retornoDDA;
                getDao().atualizarSituacaoBoletoPagamentoDDA(retornoR2DDA.getSitTitPgto().toString(), retornoR2DDA.getNumRefAtlCadTit(), retornoR2DDA.getNumIdentcTit().toString());
            }
        } else {
            throw new ComumException("integracaocip.erro.integracao.cancelamento.baixa.operacional");
        }

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Object retornoDDA) - DDA0115R1, DDA115R2, ADDA115RR2");
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0106ComplexType
     * 
     */
    private DDA0115ComplexType getDDA0115ComplexType(MensagemDDABaixaOperacional mensagem) throws ComumException {
        DDA0115ComplexType dda = new DDA0115ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0115);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        if (!ObjectUtil.isNull(mensagem.getNumIdentificadorBaixaOper())) {
            dda.setNumIdentcBaixaOperac(BigInteger.valueOf(mensagem.getNumIdentificadorBaixaOper()));
        } else {
            List<BoletoDDABaixaOper> listaBaixaOperacional = getDao().listarBoletoDDABaixaOperacional(mensagem.getNumCodigoBarra(), mensagem.getValorBaixa(),
                    mensagem.getMensagemDDA().getDataMovimento());

            if (!ObjectUtil.isEmpty(listaBaixaOperacional)) {
                dda.setNumIdentcBaixaOperac(BigInteger.valueOf(listaBaixaOperacional.get(0).getNumIdentificadorBaixaOper()));
            }
        }

        dda.setDtHrCanceltBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(ajustarDataHoraCadastro(mensagem.getMensagemDDA().getDataHoraCadastro())));
        dda.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        return dda;
    }

    /**
     * @param dataHoraCadastro
     * @return
     * @throws ComumException Date
     * 
     */
    private Date ajustarDataHoraCadastro(DateTimeDB dataHoraCadastro) throws ComumException {
        return DateUtil.incrementarData(dataHoraCadastro, Calendar.MINUTE, MENOS_CINCO_MINUTOS);
    }

    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return the integracaoCipDao
     */
    public BoletoCipDao getDao() {
        return dao;
    }
}
