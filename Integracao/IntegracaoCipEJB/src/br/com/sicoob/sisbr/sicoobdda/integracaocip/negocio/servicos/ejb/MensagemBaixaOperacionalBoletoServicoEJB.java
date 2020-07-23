package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemBaixaOperacionalBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBaixaOperacionalDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemBaixaOperacionalBoletoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemBaixaOperacionalBoletoServicoLocal.class })
public class MensagemBaixaOperacionalBoletoServicoEJB extends IntegracaoCipServicoEJB implements MensagemBaixaOperacionalBoletoServicoLocal {

    /**
     * 
     */
    private static final int CINCO_MINUTOS = 5;

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao boletoDao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemBaixaOperacionalDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemBaixaOperacionalBoletoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) {
        dao.gravarBaixaADDA108RR2(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0108 - Participante informa baixa operacional boleto");
        MensagemDDABaixaOperacional mensagem = dao.obterMensagemDDABaixaOperacionalAtualizaReferencias(idMensagem);

        DDA0108ComplexType dda0108 = getDDA0108ComplexType(mensagem);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0108, mensagem.getId());
        getLogger().debug("xmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0108 - Participante informa baixa operacional boletoo");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0108R1, DDA0108R2, ADDA108RR2, ADDA114R1, ADDA114RR2 ");

        MensagemDDABaixaOperacional mensagem = getMensagemDDABoleto(retornoDDA);
        BoletoDDA boletoDDA = boletoDao.obterBoletoDDA(retornoDDA.getNumIdent());
        BoletoDDABaixaOper boletoDDABaixaOper = ConversorBaixaOperacionalDDA.converter(retornoDDA, mensagem, boletoDDA);

        BoletoDDABaixaOper boletoDDABaixaOperacionalExistenaBase = boletoDao.obterBoletoDDABaixaOperacional(boletoDDABaixaOper.getBoletoDDA().getNumIdentificadorBoletoCip(),
                boletoDDABaixaOper.getNumIdentificadorBaixaOper());

        if (ObjectUtil.isNull(boletoDDABaixaOperacionalExistenaBase)) {
            em.persist(boletoDDABaixaOper);
        }

        getLogger().debug("########### Fim processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0108R1, DDA0108R2, ADDA108RR2, ADDA114R1, ADDA114RR2 ");
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @return MensagemDDABaixaOperacional
     * 
     */
    private MensagemDDABaixaOperacional getMensagemDDABoleto(ConteudoMsgRecebida retornoDDA) {
        MensagemDDABaixaOperacional mensagem = null;
        if (!ObjectUtil.isEmpty(retornoDDA.getIdMensagemOrigem())) {
            mensagem = em.find(MensagemDDABaixaOperacional.class, retornoDDA.getIdMensagemOrigem());
        }
        return mensagem;
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0106ComplexType
     * 
     */
    private DDA0108ComplexType getDDA0108ComplexType(MensagemDDABaixaOperacional mensagem) throws ComumException {
        DDA0108ComplexType dda = new DDA0108ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0108);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        dda.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcTit(ObjectUtil.isEmpty(mensagem.getNumIdentificadorBoleto()) ? null : BigInteger.valueOf(mensagem.getNumIdentificadorBoleto()));

        dda.setNumRefCadTitBaixaOperac(ObjectUtil.isEmpty(mensagem.getNumRefAtualCadBoleto()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualCadBoleto()));
        dda.setNumRefAtlBaixaOperac(ObjectUtil.isEmpty(mensagem.getNumRefAtualBaixaOper()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualBaixaOper()));

        dda.setTpBaixaOperac(mensagem.getCodTipoBaixaOperacional().toString());
        dda.setISPBPartRecbdrBaixaOperac(Constantes.ISPB_BANCOOB);
        dda.setCodPartRecbdrBaixaOperac(Constantes.BANCOOB);

        if (!ObjectUtil.isEmpty(mensagem.getNumCpfCnpjPortador())) {
            dda.setTpPessoaPort(mensagem.getCodTipoPessoaPortador());
            dda.setCNPJCPFPort(new BigInteger(mensagem.getNumCpfCnpjPortador()));
        }

        dda.setDtHrProcBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(ajustarDataHoraCadastro(mensagem.getMensagemDDA().getDataHoraCadastro())));
        dda.setDtProcBaixaOperac(DataCipUtil.dateTimeParaXMLGregorianCalendar(ajustarDataHoraCadastro(mensagem.getMensagemDDA().getDataHoraCadastro())));

        dda.setVlrBaixaOperacTit(mensagem.getValorBaixa());
        dda.setNumCodBarrasBaixaOperac(mensagem.getNumCodigoBarra());
        dda.setCanPgto(ObjectUtil.isEmpty(mensagem.getCodCanalPagamento()) ? null : BigInteger.valueOf(mensagem.getCodCanalPagamento()));
        dda.setMeioPgto(ObjectUtil.isEmpty(mensagem.getCodMeioPagamento()) ? null : BigInteger.valueOf(mensagem.getCodMeioPagamento()));
        dda.setIndrOpContg(mensagem.getBolOperacaoContingencia());
        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        return dda;
    }

    /**
     * @param dataHoraCadastro
     * @return
     * @throws ComumException Date
     * 
     */
    private Date ajustarDataHoraCadastro(DateTimeDB dataHoraCadastro) throws ComumException {
        return DateUtil.decrementarData(dataHoraCadastro, Calendar.MINUTE, CINCO_MINUTOS);
    }

}
