package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemBaixaEfetivaBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBaixaEfetivaDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0118.DDA0118ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemBaixaEfetivaBoletoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemBaixaEfetivaBoletoServicoLocal.class })
public class MensagemBaixaEfetivaBoletoServicoEJB extends IntegracaoCipServicoEJB implements MensagemBaixaEfetivaBoletoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao boletoDao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemBaixaEfetivaDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemBaixaEfetivaBoletoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) {
        dao.gravarBaixaADDA118RR2(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0118 - Participante requisita baixa efetiva");
        MensagemDDABaixaEfetiva mensagem = dao.obterMensagemDDABaixaEfetivaAtualizaReferencias(idMensagem);

        DDA0118ComplexType dda0118 = getDDA0118ComplexType(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0118, mensagem.getId());
        getLogger().debug("xmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0118 - Participante requisita baixa efetiva");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0118 ");

        MensagemDDABaixaEfetiva mensagem = getMensagemDDABaixaEfetiva(retornoDDA);

        BoletoDDA boletoDDA = boletoDao.obterBoletoDDA(retornoDDA.getNumIdent());

        BoletoDDABaixaEfet boletoDDABaixaEfet = ConversorBaixaEfetivaDDA.converter(retornoDDA, boletoDDA, mensagem);

        BoletoDDABaixaEfet boletoDDABaixaEfetIgual = boletoDao.obterBoletoDDABaixaEfetiva(boletoDDABaixaEfet.getBoletoDDA().getNumIdentificadorBoletoCip(),
                boletoDDABaixaEfet.getNumIdentificadorBaixaEfet());

        if (ObjectUtil.isNull(boletoDDABaixaEfetIgual)) {
            em.persist(boletoDDABaixaEfet);
        }
        getLogger().debug("########### Fim processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA)- DDA0118  ");
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @return MensagemDDABaixaEfetiva
     * 
     */
    private MensagemDDABaixaEfetiva getMensagemDDABaixaEfetiva(ConteudoMsgRecebida retornoDDA) {
        MensagemDDABaixaEfetiva mensagem = null;
        if (!ObjectUtil.isEmpty(retornoDDA.getIdMensagemOrigem())) {
            mensagem = em.find(MensagemDDABaixaEfetiva.class, retornoDDA.getIdMensagemOrigem());
        }
        return mensagem;
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0118ComplexType
     * 
     */
    private DDA0118ComplexType getDDA0118ComplexType(MensagemDDABaixaEfetiva msgBaixaEfetiva) throws ComumException {
        DDA0118ComplexType boletoBaixaEfetiva = new DDA0118ComplexType();
        boletoBaixaEfetiva.setCodMsg(TipoMensagemDDA.DDA0118);
        boletoBaixaEfetiva.setNumCtrlPart(msgBaixaEfetiva.getId().toString());
        boletoBaixaEfetiva.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);
        boletoBaixaEfetiva.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);

        boletoBaixaEfetiva.setNumIdentcTit(BigInteger.valueOf(msgBaixaEfetiva.getNumIdentificadorBoletoCip()));
        boletoBaixaEfetiva.setNumRefAtlCadTit(ObjectUtil.isEmpty(msgBaixaEfetiva.getNumRefAtualCadBoleto()) ? null : msgBaixaEfetiva.getNumRefAtualCadBoleto());
        boletoBaixaEfetiva.setNumRefAtlBaixaEft(ObjectUtil.isEmpty(msgBaixaEfetiva.getNumRefAtualBaixaEfetiva()) ? null : msgBaixaEfetiva.getNumRefAtualBaixaEfetiva());

        if (ObjectUtil.isNull(msgBaixaEfetiva.getNumIdentificadorBaixaOper())) {
            if (msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTERBANCARIA)
                    || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTRABANCARIA)) {
                setValoresBaixaEfetiva(boletoBaixaEfetiva, null, null, null, Integer.valueOf(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA));
            } else if (msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_SOLICITACAO_CEDENTE)
                    || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_ENVIO_PROTESTO)
                    || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_DECURSO_PRAZO)
                    || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA)) {
                setValoresBaixaEfetiva(boletoBaixaEfetiva, null, null, null, msgBaixaEfetiva.getCodTipoBaixaEfetiva());
            } else {
                setValoresBaixaEfetiva(boletoBaixaEfetiva, msgBaixaEfetiva.getCodCanalPagamento(), msgBaixaEfetiva.getCodMeioPagamento(), msgBaixaEfetiva.getValorBaixa(),
                        msgBaixaEfetiva.getCodTipoBaixaEfetiva());
            }
        } else {
            boletoBaixaEfetiva.setNumIdentcBaixaOperac(ObjectUtil.isEmpty(msgBaixaEfetiva.getNumIdentificadorBaixaOper()) ? null : msgBaixaEfetiva.getNumIdentificadorBaixaOper());

            setValoresBaixaEfetiva(boletoBaixaEfetiva, msgBaixaEfetiva.getCodCanalPagamento(), msgBaixaEfetiva.getCodMeioPagamento(), msgBaixaEfetiva.getValorBaixa(),
                    msgBaixaEfetiva.getCodTipoBaixaEfetiva());

            setISPBPartRecebedorBaixaEfetiva(msgBaixaEfetiva, boletoBaixaEfetiva);
        }

        // Necessario essa validação pois se o legado enviar uma baixa efetiva por solicitacao do destinatario e o boleto em questao tiver baixa, alguns dados
        // nao podem ser enviados
        validaBaixaSolicitacaoDestinataria(msgBaixaEfetiva, boletoBaixaEfetiva);

        boletoBaixaEfetiva.setDtHrProcBaixaEft(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(
                getDataHoraCadastro(msgBaixaEfetiva.getMensagemDDA().getDataHoraCadastro(), msgBaixaEfetiva.getMensagemDDA().getDataMovimento()).getTime()));
        boletoBaixaEfetiva.setDtProcBaixaEft(DataCipUtil.dateTimeParaXMLGregorianCalendar(msgBaixaEfetiva.getMensagemDDA().getDataMovimento()));

        boletoBaixaEfetiva.setNumCodBarrasBaixaEft(msgBaixaEfetiva.getNumCodigoBarra());

        boletoBaixaEfetiva.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(msgBaixaEfetiva.getMensagemDDA().getDataMovimento()));

        return boletoBaixaEfetiva;
    }

    /**
     * Método responsável por
     * 
     * @param msgBaixaEfetiva
     * @param boletoBaixaEfetiva void
     */
    private void validaBaixaSolicitacaoDestinataria(MensagemDDABaixaEfetiva msgBaixaEfetiva, DDA0118ComplexType boletoBaixaEfetiva) {
        if (msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA)) {
            boletoBaixaEfetiva.setCanPgto(null);
            boletoBaixaEfetiva.setMeioPgto(null);
            boletoBaixaEfetiva.setVlrBaixaEftTit(null);
        }
    }

    /**
     * Método responsável por
     * 
     * @param msgBaixaEfetiva
     * @param boletoBaixaEfetiva void
     */
    private void setISPBPartRecebedorBaixaEfetiva(MensagemDDABaixaEfetiva msgBaixaEfetiva, DDA0118ComplexType boletoBaixaEfetiva) {
        if (msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_PARCIAL_INTERBANCARIA)
                || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_PARCIAL_INTRABANCARIA)
                || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTERBANCARIA)
                || msgBaixaEfetiva.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTRABANCARIA)) {
            boletoBaixaEfetiva
                    .setISPBPartRecbdrBaixaEft(!ObjectUtil.isEmpty(msgBaixaEfetiva.getCodISPBPartRecbdrBaixaOper()) ? msgBaixaEfetiva.getCodISPBPartRecbdrBaixaOper() : null);
            boletoBaixaEfetiva.setCodPartRecbdrBaixaEft(!ObjectUtil.isEmpty(msgBaixaEfetiva.getCodPartRecbdrBaixaOper()) ? msgBaixaEfetiva.getCodPartRecbdrBaixaOper() : null);
        }
    }

    /**
     * Necessario para obter a hora que foi cadastrado na mensagemDDA junto com a data de movimento
     * 
     * @param dtHoraCadastro
     * @param dataMovimento
     * @return Calendar
     */
    private Calendar getDataHoraCadastro(DateTimeDB dtHoraCadastro, DateTimeDB dataMovimento) {

        final Calendar dataHoraCadastro = Calendar.getInstance();
        dataHoraCadastro.setTime(dtHoraCadastro);
        int hour = dataHoraCadastro.get(Calendar.HOUR_OF_DAY);
        int minute = dataHoraCadastro.get(Calendar.MINUTE);
        int second = dataHoraCadastro.get(Calendar.SECOND);

        dataHoraCadastro.setTime(dataMovimento);
        dataHoraCadastro.set(Calendar.HOUR_OF_DAY, hour);
        dataHoraCadastro.set(Calendar.MINUTE, minute);
        dataHoraCadastro.set(Calendar.SECOND, second);

        return dataHoraCadastro;
    }

    /**
     * Método responsável por setar os valores do canal de pagamento, meio de pagamento, valor da Baixa e Tipo baixa Efetiva
     * 
     * @param boletoBaixaEfetiva
     * @param codCanalPagamento
     * @param codMeioPagamento
     * @param valorBaixa
     * @param codTipoBaixaEfetiva void
     * 
     */
    private void setValoresBaixaEfetiva(DDA0118ComplexType boletoBaixaEfetiva, Integer codCanalPagamento, Integer codMeioPagamento, BigDecimal valorBaixa,
            Integer codTipoBaixaEfetiva) {
        boletoBaixaEfetiva.setCanPgto(ObjectUtil.isEmpty(codCanalPagamento) ? null : BigInteger.valueOf(codCanalPagamento));
        boletoBaixaEfetiva.setMeioPgto(ObjectUtil.isEmpty(codMeioPagamento) ? null : BigInteger.valueOf(codMeioPagamento));
        boletoBaixaEfetiva.setVlrBaixaEftTit(ObjectUtil.isEmpty(valorBaixa) ? null : valorBaixa);
        boletoBaixaEfetiva.setTpBaixaEft(codTipoBaixaEfetiva.toString());
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
