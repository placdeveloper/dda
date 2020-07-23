package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemBoletoPagamentoAbertoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.BoletoDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.DDA0127ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

@RunWith(MockitoJUnitRunner.class)
public class MensagemBoletoPagamentoAbertoServicoTest extends Mockito {

    /**
     * 
     */
    private static final String _VALOR_PADRAO = "1";

    @InjectMocks
    private MensagemBoletoPagamentoAbertoServicoEJB ejb;

    @Mock
    private EntityManager em;

    @Mock
    private BoletoCipDao boletoCipDao;

    @Test
    public void processarRetornoMensagemDDAcomSucesso() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA(getDDA0127ComplexType()));
        // verifyObterBoletoDDA();
        // verifyEmPersistBoletoDDA();
    }

    public void processarRetornoMensagemADDAcomSucesso() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA(getGrupoDDA0127Tit()));
    }

    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem());
        // verify(boletoCipDao, times(1)).obterMensagemDDABoleto(Constantes.LONG_UM);
    }

    private String processarRetornoMensagemDDA(ConteudoMsgRecebida type) {
        try {
            // if (type instanceof DDA0127ComplexType) {
            // ((DDA0127ComplexType) type).getGrupoDDA0127Tit().add(getGrupoDDA0127Tit());
            // }
            MensagemDDABoleto msgDDABoleto = new MensagemDDABoleto();
            msgDDABoleto.setNumCodigoBarra(_VALOR_PADRAO);
            msgDDABoleto.setNumCodBarrasCampoLivre(_VALOR_PADRAO);
            msgDDABoleto.setBolPagamentoParcial(Boolean.FALSE);
            when(em.find(MensagemDDABoleto.class, 1L)).thenReturn(msgDDABoleto);
            when(boletoCipDao.obterBoletoDDA(Constantes.LONG_UM)).thenReturn(BoletoDDALoader.geraBoletoDDA());
            ejb.processarRetornoMensagemDDA(type);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;

    }

    private static DDA0127ComplexType getDDA0127ComplexType() throws ComumException {
        DDA0127ComplexType type = new DDA0127ComplexType();
        type.setCodMsg(TipoMensagemDDA.DDA0101);
        type.setISPBPartRecbdrAdmtd(_VALOR_PADRAO);
        type.setISPBPartRecbdrPrincipal(_VALOR_PADRAO);
        type.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type.setDtHrDDA(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));

        return type;
    }

    private static GrupoADDA127TitComplexType getGrupoDDA0127Tit() throws ComumException {
        GrupoADDA127TitComplexType type = new GrupoADDA127TitComplexType();
        type.setNumRefAtlCadTit(BigInteger.ONE);
        type.setISPBPartDestinatario(Constantes.NOME_TESTE);
        type.setISPBPartRecbdrAdmtd(_VALOR_PADRAO);
        type.setISPBPartRecbdrPrincipal(_VALOR_PADRAO);
        type.setNumIdentcTit(new BigInteger(_VALOR_PADRAO));
        type.setNumRefAtlActe(new BigInteger(_VALOR_PADRAO));
        type.setNumSeqAtlzActe(new BigInteger(_VALOR_PADRAO));
        type.setNumSeqAtlzCadTit(new BigInteger(_VALOR_PADRAO));
        type.setDtHrSit(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type.setDtEmsTit(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type.setDtLimPgtoTit(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type.setDtVencTit(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(new Date()));
        type.setQtdPgtoParclRegtd(new BigInteger(_VALOR_PADRAO));
        type.setVlrSldTotAtlPgtoTit(new BigDecimal(_VALOR_PADRAO));
        type.setSitTitPgto(_VALOR_PADRAO);
        type.setNumCodBarras(_VALOR_PADRAO);
        type.setVlrAbattTit(new BigDecimal(_VALOR_PADRAO));
        type.setVlrPercMaxTit(new BigDecimal(_VALOR_PADRAO));
        type.setVlrPercMinTit(new BigDecimal(_VALOR_PADRAO));
        type.setCodCartTit(_VALOR_PADRAO);
        type.setCodEspTit(_VALOR_PADRAO);
        type.setCodMoedaCNAB(_VALOR_PADRAO);
        type.setCodPartDestinatario(_VALOR_PADRAO);
        type.setTpAutcRecbtVlrDivgte(_VALOR_PADRAO);
        type.setTpIdentcSacdrAvalst((new BigInteger(_VALOR_PADRAO)));
        type.setTpModlCalc(_VALOR_PADRAO);
        type.setTpPessoaBenfcrioFinl(_VALOR_PADRAO);
        type.setTpPessoaPagdr(_VALOR_PADRAO);
        type.setTpPgtoTit(new BigInteger(_VALOR_PADRAO));
        type.setCNPJCPFBenfcrioFinl(new BigInteger(_VALOR_PADRAO));
        type.setCNPJCPFBenfcrioOr(new BigInteger(_VALOR_PADRAO));
        type.setCNPJCPFPagdr(new BigInteger(_VALOR_PADRAO));
        type.setIndrActe(_VALOR_PADRAO);
        type.setIndrBloqPgto(_VALOR_PADRAO);
        type.setIndrPgtoParcl(_VALOR_PADRAO);
        type.setIndrTitNegcd(_VALOR_PADRAO);
        type.setTpVlrPercMaxTit(_VALOR_PADRAO);
        type.setTpVlrPercMinTit(_VALOR_PADRAO);
        type.setCEPBenfcrioOr(new BigInteger(_VALOR_PADRAO));
        type.setCEPPagdr(new BigInteger(_VALOR_PADRAO));
        type.setCidPagdr(_VALOR_PADRAO);
        type.setTpPessoaBenfcrioOr(TipoPessoaEnum.PJ.getCodDominioCip());
        type.setNomRzSocBenfcrioOr(Constantes.NOME_TESTE);
        type.setNomFantsBenfcrioOr(Constantes.NOME_TESTE);
        type.setLogradBenfcrioOr(Constantes.NOME_TESTE);
        type.setCidBenfcrioOr(Constantes.NOME_TESTE);
        type.setUFBenfcrioOr(Constantes.NOME_TESTE);
        type.setNomRzSocBenfcrioFinl(Constantes.NOME_TESTE);
        type.setNomRzSocPagdr(Constantes.NOME_TESTE);
        type.setNomFantsPagdr(Constantes.NOME_TESTE);
        type.setLogradPagdr(Constantes.NOME_TESTE);
        type.setUFPagdr(Constantes.NOME_TESTE);
        type.setCEPPagdr(BigInteger.ONE);
        type.setIdentcSacdrAvalst(Constantes.CNPJ_AUX);
        type.setNomRzSocSacdrAvalst(Constantes.NOME_TESTE);
        type.setIdentdNossoNum(Constantes.NOME_TESTE);
        type.setNumLinhaDigtvl(Constantes.NOME_TESTE);
        type.setVlrTit(BigDecimal.ONE);
        type.setNumDocTit(Constantes.NOME_TESTE);
        type.setCodEspTit(Constantes.NOME_TESTE);
        type.setQtdDiaPrott(BigInteger.ONE);
        type.setNumParcl(BigInteger.ONE);
        type.setQtdTotParcl(BigInteger.ONE);

        type.setGrupoADDA127JurosTit(geraGrupoJurosTitADDA0127());

        type.setGrupoADDA127MultaTit(geraGrupoMultaADDA0127());

        type.getGrupoADDA127DesctTit().add(geraGrupoDescontoADDA0127());

        type.getGrupoADDA127NotaFis().add(geraGrupoNotaFiscalADDA127());

        type.getGrupoADDA127Calc().add(geraGrupoCalculoADDA127());

        type.getGrupoADDA127Terc().add(geraGrupoTerceiroAutorizadoADDA127());

        type.getGrupoADDA127BaixaEft().add(geraGrupoBaixaEfetADDA127());

        type.getGrupoADDA127BaixaOperac().add(geraGrupoBaixaOperADDA127());

        type.getTxtInfCliBenfcrio().add(Constantes.NOME_TESTE);

        return type;
    }

    private String processarMensagem() throws ComumException {
        when(boletoCipDao.obterMensagemDDABoleto(Constantes.LONG_UM)).thenReturn(getMensagemDDABoleto());
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    private MensagemDDABoleto getMensagemDDABoleto() {
        MensagemDDABoleto mensagemDDABoleto = new MensagemDDABoleto();
        mensagemDDABoleto.setId(Constantes.LONG_UM);
        mensagemDDABoleto.setNumCodigoBarra(Constantes.STRING_NUMERO_1);
        mensagemDDABoleto.setNumCodBarrasCampoLivre(Constantes.STRING_NUMERO_1);
        mensagemDDABoleto.setMensagemDDA(new MensagemDDA());
        mensagemDDABoleto.getMensagemDDA().setDataMovimento(Constantes.DATE_TIME_DB_AUX);
        return mensagemDDABoleto;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0127DesctTitComplexType
     * @throws ComumException
     * 
     */
    private static GrupoADDA127DesctTitComplexType geraGrupoDescontoADDA0127() throws ComumException {
        GrupoADDA127DesctTitComplexType grupoDesconto = new GrupoADDA127DesctTitComplexType();
        grupoDesconto.setCodDesctTit(Constantes.NOME_TESTE);
        grupoDesconto.setDtDesctTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDesconto.setVlrPercDesctTit(BigDecimal.ONE);
        return grupoDesconto;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0127MultaTitComplexType
     * @throws ComumException
     * 
     */
    private static GrupoADDA127MultaTitComplexType geraGrupoMultaADDA0127() throws ComumException {
        GrupoADDA127MultaTitComplexType grupoMulta = new GrupoADDA127MultaTitComplexType();
        grupoMulta.setCodMultaTit(Constantes.STRING_NUMERO_1);
        grupoMulta.setDtMultaTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoMulta.setVlrPercMultaTit(BigDecimal.ONE);
        return grupoMulta;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0127JurosTitComplexType
     * @throws ComumException
     * 
     */
    private static GrupoADDA127JurosTitComplexType geraGrupoJurosTitADDA0127() throws ComumException {
        GrupoADDA127JurosTitComplexType grupoJuros = new GrupoADDA127JurosTitComplexType();
        grupoJuros.setCodJurosTit(Constantes.STRING_NUMERO_1);
        grupoJuros.setDtJurosTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoJuros.setVlrPercJurosTit(BigDecimal.ONE);
        return grupoJuros;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106BaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private static GrupoADDA127BaixaOperacComplexType geraGrupoBaixaOperADDA127() throws ComumException {
        GrupoADDA127BaixaOperacComplexType grupoBaixaOper = new GrupoADDA127BaixaOperacComplexType();
        grupoBaixaOper.setNumIdentcBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumRefAtlBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumSeqAtlzBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setISPBPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setCodPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setTpBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setSitBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setDtHrSitBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBaixaOper.setGrupoADDA127DettBaixaOperac(geraGrupoDetBaixaOperADDA127());
        return grupoBaixaOper;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106DettBaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private static GrupoADDA127DettBaixaOperacComplexType geraGrupoDetBaixaOperADDA127() throws ComumException {
        GrupoADDA127DettBaixaOperacComplexType grupoDetBaixaOper = new GrupoADDA127DettBaixaOperacComplexType();
        grupoDetBaixaOper.setCNPJCPFPort(Constantes.CNPJ_AUX_BIGINT);
        grupoDetBaixaOper.setTpPessoaPort(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoDetBaixaOper.setDtHrProcBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDetBaixaOper.setDtProcBaixaOperac(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDetBaixaOper.setVlrBaixaOperacTit(BigDecimal.ONE);
        grupoDetBaixaOper.setNumCodBarrasBaixaOperac(Constantes.NOME_TESTE);
        return grupoDetBaixaOper;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106BaixaEftComplexType
     * @throws ComumException
     * 
     */
    private static GrupoADDA127BaixaEftComplexType geraGrupoBaixaEfetADDA127() throws ComumException {
        GrupoADDA127BaixaEftComplexType grupoBaixaEfet = new GrupoADDA127BaixaEftComplexType();
        grupoBaixaEfet.setNumIdentcBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumRefAtlBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumSeqAtlzBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumIdentcBaixaOperacBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setTpBaixaEft(Constantes.NOME_TESTE);
        grupoBaixaEfet.setISPBPartRecbdrBaixaEft(Constantes.NOME_TESTE);
        grupoBaixaEfet.setCodPartRecbdrBaixaEft(Constantes.NOME_TESTE);
        grupoBaixaEfet.setDtHrProcBaixaEft(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBaixaEfet.setVlrBaixaEftTit(BigDecimal.ONE);
        grupoBaixaEfet.setNumCodBarrasBaixaEft(Constantes.NOME_TESTE);
        grupoBaixaEfet.setCanPgtoBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setMeioPgtoBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setDtHrSitBaixaEft(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        return grupoBaixaEfet;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106TercComplexType
     * 
     */
    private static GrupoADDA127TercComplexType geraGrupoTerceiroAutorizadoADDA127() {
        GrupoADDA127TercComplexType grupoTerceiro = new GrupoADDA127TercComplexType();
        grupoTerceiro.setTpPessoaPagdrAutdr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoTerceiro.setCNPJCPFPagdrAutdr(Constantes.CNPJ_AUX_BIGINT);
        grupoTerceiro.setTpPessoaTerc(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoTerceiro.setCNPJCPFTerc(Constantes.CNPJ_AUX_BIGINT);
        grupoTerceiro.setNumIdentcTerc(BigInteger.ONE);
        grupoTerceiro.setNumRefAtlCadTerc(BigInteger.ONE);
        return grupoTerceiro;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106CalcComplexType
     * @throws ComumException
     * 
     */
    private static GrupoADDA127CalcComplexType geraGrupoCalculoADDA127() throws ComumException {
        GrupoADDA127CalcComplexType grupoCalculo = new GrupoADDA127CalcComplexType();
        grupoCalculo.setDtValiddCalc(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoCalculo.setVlrCalcdDesct(BigDecimal.ONE);
        grupoCalculo.setVlrCalcdJuros(BigDecimal.ONE);
        grupoCalculo.setVlrCalcdMulta(BigDecimal.ONE);
        grupoCalculo.setVlrTotCobrar(BigDecimal.ONE);
        return grupoCalculo;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106NotaFisComplexType
     * @throws ComumException
     * 
     */
    private static GrupoADDA127NotaFisComplexType geraGrupoNotaFiscalADDA127() throws ComumException {
        GrupoADDA127NotaFisComplexType grupoNotaFiscal = new GrupoADDA127NotaFisComplexType();
        grupoNotaFiscal.setDtEmsNotaFis(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoNotaFiscal.setNumNotaFis(Constantes.NOME_TESTE);
        grupoNotaFiscal.setVlrNotaFis(BigDecimal.ONE);
        return grupoNotaFiscal;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106DesctTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0127DesctTitComplexType geraGrupoDescontoDDA0127() throws ComumException {
        GrupoDDA0127DesctTitComplexType grupoDesconto = new GrupoDDA0127DesctTitComplexType();
        grupoDesconto.setCodDesctTit(Constantes.NOME_TESTE);
        grupoDesconto.setDtDesctTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDesconto.setVlrPercDesctTit(BigDecimal.ONE);
        return grupoDesconto;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106MultaTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA106MultaTitComplexType geraGrupoMultaADDA106() throws ComumException {
        GrupoADDA106MultaTitComplexType grupoMulta = new GrupoADDA106MultaTitComplexType();
        grupoMulta.setCodMultaTit(Constantes.STRING_NUMERO_1);
        grupoMulta.setDtMultaTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoMulta.setVlrPercMultaTit(BigDecimal.ONE);
        return grupoMulta;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106JurosTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA106JurosTitComplexType geraGrupoJurosTitADDA106() throws ComumException {
        GrupoADDA106JurosTitComplexType grupoJuros = new GrupoADDA106JurosTitComplexType();
        grupoJuros.setCodJurosTit(Constantes.STRING_NUMERO_1);
        grupoJuros.setDtJurosTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoJuros.setVlrPercJurosTit(BigDecimal.ONE);
        return grupoJuros;
    }

    private GrupoDDA0127TitComplexType geraGrupoDDA127() throws ComumException {
        GrupoDDA0127TitComplexType grupoBoleto = new GrupoDDA0127TitComplexType();
        grupoBoleto.setNumIdentcTit(BigInteger.ONE);
        grupoBoleto.setNumRefAtlCadTit(BigInteger.ONE);
        grupoBoleto.setNumSeqAtlzCadTit(BigInteger.ONE);
        grupoBoleto.setISPBPartDestinatario(Constantes.NOME_TESTE);
        grupoBoleto.setCodPartDestinatario(Constantes.NOME_TESTE);

        grupoBoleto.setTpPessoaBenfcrioOr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoBoleto.setCNPJCPFBenfcrioOr(Constantes.CNPJ_AUX_BIGINT);
        grupoBoleto.setNomRzSocBenfcrioOr(Constantes.NOME_TESTE);
        grupoBoleto.setNomFantsBenfcrioOr(Constantes.NOME_TESTE);
        grupoBoleto.setLogradBenfcrioOr(Constantes.NOME_TESTE);
        grupoBoleto.setCidBenfcrioOr(Constantes.NOME_TESTE);
        grupoBoleto.setUFBenfcrioOr(Constantes.NOME_TESTE);
        grupoBoleto.setCEPBenfcrioOr(BigInteger.ONE);

        // BeneficiarioFinal
        grupoBoleto.setTpPessoaBenfcrioFinl(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoBoleto.setCNPJCPFBenfcrioFinl(Constantes.CNPJ_AUX_BIGINT);
        grupoBoleto.setNomRzSocBenfcrioFinl(Constantes.NOME_TESTE);
        grupoBoleto.setNomFantsBenfcrioFinl(Constantes.NOME_TESTE);

        // Pagador
        grupoBoleto.setTpPessoaPagdr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoBoleto.setCNPJCPFPagdr(Constantes.CNPJ_AUX_BIGINT);
        grupoBoleto.setNomRzSocPagdr(Constantes.NOME_TESTE);
        grupoBoleto.setNomFantsPagdr(Constantes.NOME_TESTE);
        grupoBoleto.setLogradPagdr(Constantes.NOME_TESTE);
        grupoBoleto.setCidPagdr(Constantes.NOME_TESTE);
        grupoBoleto.setUFPagdr(Constantes.NOME_TESTE);
        grupoBoleto.setCEPPagdr(BigInteger.ONE);

        // Sacador Avalista
        grupoBoleto.setTpIdentcSacdrAvalst(Constantes.BIG_INTEGER_2_AUX);
        grupoBoleto.setIdentcSacdrAvalst(Constantes.CNPJ_AUX);
        grupoBoleto.setNomRzSocSacdrAvalst(Constantes.NOME_TESTE);

        grupoBoleto.setCodCartTit(Constantes.NOME_TESTE);
        grupoBoleto.setCodMoedaCNAB(Constantes.NOME_TESTE);
        grupoBoleto.setIdentdNossoNum(Constantes.NOME_TESTE);
        grupoBoleto.setNumCodBarras(Constantes.NOME_TESTE);
        grupoBoleto.setNumLinhaDigtvl(Constantes.NOME_TESTE);

        grupoBoleto.setDtVencTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBoleto.setVlrTit(BigDecimal.ONE);
        grupoBoleto.setNumDocTit(Constantes.NOME_TESTE);

        grupoBoleto.setCodEspTit(Constantes.NOME_TESTE);

        grupoBoleto.setDtEmsTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBoleto.setQtdDiaPrott(BigInteger.ONE);
        grupoBoleto.setDtLimPgtoTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBoleto.setTpPgtoTit(BigInteger.ONE);
        grupoBoleto.setNumParcl(BigInteger.ONE);
        grupoBoleto.setQtdTotParcl(BigInteger.ONE);

        grupoBoleto.setIndrTitNegcd(Constantes.NOME_TESTE);
        grupoBoleto.setIndrBloqPgto(Constantes.NOME_TESTE);
        grupoBoleto.setIndrPgtoParcl(Constantes.NOME_TESTE);

        grupoBoleto.setQtdPgtoParcl(BigInteger.ONE);

        grupoBoleto.setVlrAbattTit(BigDecimal.ONE);

        grupoBoleto.setTpVlrPercMinTit(Constantes.NOME_TESTE);
        grupoBoleto.setVlrPercMinTit(BigDecimal.ONE);

        grupoBoleto.setTpVlrPercMaxTit(Constantes.NOME_TESTE);
        grupoBoleto.setVlrPercMaxTit(BigDecimal.ONE);

        grupoBoleto.setTpModlCalc(Constantes.NOME_TESTE);
        grupoBoleto.setTpAutcRecbtVlrDivgte(Constantes.NOME_TESTE);

        grupoBoleto.setDtHrSit(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBoleto.setQtdPgtoParclRegtd(BigInteger.ONE);
        grupoBoleto.setVlrSldTotAtlPgtoTit(BigDecimal.ONE);
        grupoBoleto.setSitTitPgto(Constantes.NOME_TESTE);

        grupoBoleto.setIndrActe(Constantes.NOME_TESTE);
        grupoBoleto.setNumRefAtlActe(BigInteger.ONE);
        grupoBoleto.setNumSeqAtlzActe(BigInteger.ONE);

        grupoBoleto.setGrupoDDA0127JurosTit(geraGrupoJurosTitDDA0127());

        grupoBoleto.setGrupoDDA0127MultaTit(geraGrupoMultaDDA0127());

        grupoBoleto.getGrupoDDA0127DesctTit().add(geraGrupoDescontoDDA0127());

        grupoBoleto.getGrupoDDA0127NotaFis().add(geraGrupoNotaFiscalDDA0127());

        grupoBoleto.getGrupoDDA0127Calc().add(geraGrupoCalculoDDA0127());

        grupoBoleto.getGrupoDDA0127Terc().add(geraGrupoTerceiroAutorizadoDDA0127());

        grupoBoleto.getGrupoDDA0127BaixaEft().add(geraGrupoBaixaEfetDDA0127());

        grupoBoleto.getGrupoDDA0127BaixaOperac().add(geraGrupoBaixaOperDDA0127());

        grupoBoleto.getTxtInfBenfcrio().add(Constantes.NOME_TESTE);

        return grupoBoleto;
    }

    private GrupoDDA0127JurosTitComplexType geraGrupoJurosTitDDA0127() throws ComumException {
        GrupoDDA0127JurosTitComplexType grupoJuros = new GrupoDDA0127JurosTitComplexType();
        grupoJuros.setCodJurosTit(Constantes.STRING_NUMERO_1);
        grupoJuros.setDtJurosTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoJuros.setVlrPercJurosTit(BigDecimal.ONE);
        return grupoJuros;
    }

    private GrupoDDA0127MultaTitComplexType geraGrupoMultaDDA0127() throws ComumException {
        GrupoDDA0127MultaTitComplexType grupoMulta = new GrupoDDA0127MultaTitComplexType();
        grupoMulta.setCodMultaTit(Constantes.STRING_NUMERO_1);
        grupoMulta.setDtMultaTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoMulta.setVlrPercMultaTit(BigDecimal.ONE);
        return grupoMulta;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1NotaFisComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0127NotaFisComplexType geraGrupoNotaFiscalDDA0127() throws ComumException {
        GrupoDDA0127NotaFisComplexType grupoNotaFiscal = new GrupoDDA0127NotaFisComplexType();
        grupoNotaFiscal.setDtEmsNotaFis(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoNotaFiscal.setNumNotaFis(Constantes.NOME_TESTE);
        grupoNotaFiscal.setVlrNotaFis(BigDecimal.ONE);
        return grupoNotaFiscal;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1CalcComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0127CalcComplexType geraGrupoCalculoDDA0127() throws ComumException {
        GrupoDDA0127CalcComplexType grupoCalculo = new GrupoDDA0127CalcComplexType();
        grupoCalculo.setDtValiddCalc(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoCalculo.setVlrCalcdDesct(BigDecimal.ONE);
        grupoCalculo.setVlrCalcdJuros(BigDecimal.ONE);
        grupoCalculo.setVlrCalcdMulta(BigDecimal.ONE);
        grupoCalculo.setVlrTotCobrar(BigDecimal.ONE);
        return grupoCalculo;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1TercComplexType
     * 
     */
    private GrupoDDA0127TercComplexType geraGrupoTerceiroAutorizadoDDA0127() {
        GrupoDDA0127TercComplexType grupoTerceiro = new GrupoDDA0127TercComplexType();
        grupoTerceiro.setTpPessoaPagdrAutzdr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoTerceiro.setCNPJCPFPagdrAutzdr(Constantes.CNPJ_AUX_BIGINT);
        grupoTerceiro.setTpPessoaTerc(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoTerceiro.setCNPJCPFTerc(Constantes.CNPJ_AUX_BIGINT);
        grupoTerceiro.setNumIdentcTerc(BigInteger.ONE);
        grupoTerceiro.setNumRefAtlCadTerc(BigInteger.ONE);
        return grupoTerceiro;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1BaixaEftComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0127BaixaEftComplexType geraGrupoBaixaEfetDDA0127() throws ComumException {
        GrupoDDA0127BaixaEftComplexType grupoBaixaEfet = new GrupoDDA0127BaixaEftComplexType();
        grupoBaixaEfet.setNumIdentcBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumRefAtlBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumSeqAtlzBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumIdentcBaixaOperacBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setTpBaixaEft(Constantes.NOME_TESTE);
        grupoBaixaEfet.setISPBPartRecbdrBaixaEft(Constantes.NOME_TESTE);
        grupoBaixaEfet.setCodPartRecbdrBaixaEft(Constantes.NOME_TESTE);
        grupoBaixaEfet.setDtHrProcBaixaEft(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBaixaEfet.setVlrBaixaEftTit(BigDecimal.ONE);
        grupoBaixaEfet.setNumCodBarrasBaixaEft(Constantes.NOME_TESTE);
        grupoBaixaEfet.setCanPgtoBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setMeioPgtoBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setDtHrSitBaixaEft(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        return grupoBaixaEfet;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1BaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0127BaixaOperacComplexType geraGrupoBaixaOperDDA0127() throws ComumException {
        GrupoDDA0127BaixaOperacComplexType grupoBaixaOper = new GrupoDDA0127BaixaOperacComplexType();
        grupoBaixaOper.setNumIdentcBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumRefAtlBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumSeqAtlzBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setISPBPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setCodPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setTpBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setSitBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setDtHrSitBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBaixaOper.setGrupoDDA0127DettBaixaOperac(geraGrupoDetBaixaOperDDA0127());
        return grupoBaixaOper;
    }

    private GrupoDDA0127DettBaixaOperacComplexType geraGrupoDetBaixaOperDDA0127() throws ComumException {
        GrupoDDA0127DettBaixaOperacComplexType grupoDetBaixaOper = new GrupoDDA0127DettBaixaOperacComplexType();
        grupoDetBaixaOper.setCNPJCPFPort(Constantes.CNPJ_AUX_BIGINT);
        grupoDetBaixaOper.setTpPessoaPort(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoDetBaixaOper.setDtHrProcBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDetBaixaOper.setDtProcBaixaOperac(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDetBaixaOper.setVlrBaixaOperacTit(BigDecimal.ONE);
        grupoDetBaixaOper.setNumCodBarrasBaixaOperac(Constantes.NOME_TESTE);
        return grupoDetBaixaOper;
    }

}
