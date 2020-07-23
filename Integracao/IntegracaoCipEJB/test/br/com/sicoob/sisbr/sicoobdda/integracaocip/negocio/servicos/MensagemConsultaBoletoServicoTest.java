/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemConsultaBoletoServicoTest.java
 * Data Criação:    Oct 31, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.EntityManager;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemConsultaBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.BoletoDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.DDA0106R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;
import junit.framework.Assert;

/**
 * MensagemConsultaBoletoServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class MensagemConsultaBoletoServicoTest extends Mockito {

    @InjectMocks
    private MensagemConsultaBoletoServicoEJB ejb;

    @Mock
    private BoletoCipDao dao;

    @Mock
    private EntityManager em;

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem());
        verify(dao, times(1)).obterMensagemDDAConsultaBoleto(Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test
    public void processarRetornoMensagemDDA0106Incluir() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDAIncluir(geraDDA0106()));
        verifyObterBoletoDDA();
        verifyEmPersistBoletoDDA();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test
    public void processarRetornoMensagemDDA0106Alterar() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDAAlterar(geraDDA0106()));
        verifyObterBoletoDDA();
        verifyEmMergeBoletoDDA();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test
    public void processarRetornoMensagemADDA106Incluir() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDAIncluir(geraGrupoADDA106()));
        verifyObterBoletoDDA();
        verifyEmPersistBoletoDDA();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test
    public void processarRetornoMensagemADDA106Alterar() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDAAlterar(geraGrupoADDA106()));
        verifyObterBoletoDDA();
        verifyEmMergeBoletoDDA();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    private String processarMensagem() throws ComumException {
        when(dao.obterMensagemDDAConsultaBoleto(Constantes.LONG_UM)).thenReturn(getMensagemDDAConsultaBoleto());
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAConsultaBoleto
     * 
     */
    private MensagemDDAConsultaBoleto getMensagemDDAConsultaBoleto() {
        MensagemDDAConsultaBoleto mensagemDDAConsultaBoleto = new MensagemDDAConsultaBoleto();
        mensagemDDAConsultaBoleto.setId(Constantes.LONG_UM);
        mensagemDDAConsultaBoleto.setNumCodigoBarra("756");
        mensagemDDAConsultaBoleto.setNumCodBarrasCampoLivre("756");
        mensagemDDAConsultaBoleto.setCodSituacaoBoleto(Constantes.SHORT_UM);
        mensagemDDAConsultaBoleto.setMensagemDDA(MensagemDDALoader.geraMensagemDDA());
        return mensagemDDAConsultaBoleto;
    }

    /**
     * Método responsável por
     * 
     * @param conteudoMsg
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetornoMensagemDDAIncluir(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        return processarRetornoMensagemDDA(conteudoMsg, null);
    }

    /**
     * Método responsável por
     * 
     * @param conteudoMsg
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetornoMensagemDDAAlterar(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        return processarRetornoMensagemDDA(conteudoMsg, BoletoDDALoader.geraBoletoDDA());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(java.lang.Long,
     *      br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    private String processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg, BoletoDDA boletoDDA) throws ComumException {
        when(dao.obterBoletoDDALock(Constantes.LONG_UM)).thenReturn(boletoDDA);
        ejb.processarRetornoMensagemDDA(conteudoMsg);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return ConteudoMsgRecebida
     * @throws ComumException
     * 
     */
    private DDA0106R1ComplexType geraDDA0106() throws ComumException {
        DDA0106R1ComplexType dda = new DDA0106R1ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0101);
        dda.setNumCtrlPart(Constantes.NOME_TESTE);
        dda.getGrupoDDA0106R1Tit().add(geraGrupoDDA106R1());
        return dda;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1TitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1TitComplexType geraGrupoDDA106R1() throws ComumException {
        GrupoDDA0106R1TitComplexType grupoBoleto = new GrupoDDA0106R1TitComplexType();
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

        grupoBoleto.setCodCartTit(Constantes.STRING_NUMERO_1);
        grupoBoleto.setCodEspTit(Constantes.STRING_NUMERO_1);
        grupoBoleto.setCodMoedaCNAB(Constantes.NOME_TESTE);
        grupoBoleto.setIdentdNossoNum(Constantes.NOME_TESTE);
        grupoBoleto.setNumCodBarras(Constantes.NOME_TESTE);
        grupoBoleto.setNumLinhaDigtvl(Constantes.NOME_TESTE);

        grupoBoleto.setDtVencTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBoleto.setVlrTit(BigDecimal.ONE);
        grupoBoleto.setNumDocTit(Constantes.NOME_TESTE);

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

        grupoBoleto.setGrupoDDA0106R1JurosTit(geraGrupoJurosTitDDA0106R1());

        grupoBoleto.setGrupoDDA0106R1MultaTit(geraGrupoMultaDDA0106R1());

        grupoBoleto.getGrupoDDA0106R1DesctTit().add(geraGrupoDescontoDDA0106R1());

        grupoBoleto.getGrupoDDA0106R1NotaFis().add(geraGrupoNotaFiscalDDA0106R1());

        grupoBoleto.getGrupoDDA0106R1Calc().add(geraGrupoCalculoDDA0106R1());

        grupoBoleto.getGrupoDDA0106R1Terc().add(geraGrupoTerceiroAutorizadoDDA0106R1());

        grupoBoleto.getGrupoDDA0106R1BaixaEft().add(geraGrupoBaixaEfetDDA0106R1());

        grupoBoleto.getGrupoDDA0106R1BaixaOperac().add(geraGrupoBaixaOperDDA0106R1());

        grupoBoleto.getTxtInfBenfcrio().add(Constantes.NOME_TESTE);
        return grupoBoleto;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1BaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1BaixaOperacComplexType geraGrupoBaixaOperDDA0106R1() throws ComumException {
        GrupoDDA0106R1BaixaOperacComplexType grupoBaixaOper = new GrupoDDA0106R1BaixaOperacComplexType();
        grupoBaixaOper.setNumIdentcBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumRefAtlBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumSeqAtlzBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setISPBPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setCodPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setTpBaixaOperac(Constantes.STRING_NUMERO_1);
        grupoBaixaOper.setSitBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setDtHrSitBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBaixaOper.setGrupoDDA0106R1DettBaixaOperac(geraGrupoDetBaixaOperDDA0106R1());
        return grupoBaixaOper;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1DettBaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1DettBaixaOperacComplexType geraGrupoDetBaixaOperDDA0106R1() throws ComumException {
        GrupoDDA0106R1DettBaixaOperacComplexType grupoDetBaixaOper = new GrupoDDA0106R1DettBaixaOperacComplexType();
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
     * @return GrupoDDA0106R1BaixaEftComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1BaixaEftComplexType geraGrupoBaixaEfetDDA0106R1() throws ComumException {
        GrupoDDA0106R1BaixaEftComplexType grupoBaixaEfet = new GrupoDDA0106R1BaixaEftComplexType();
        grupoBaixaEfet.setNumIdentcBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumRefAtlBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumSeqAtlzBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumIdentcBaixaOperacBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setTpBaixaEft(Constantes.STRING_NUMERO_1);
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
     * @return GrupoDDA0106R1TercComplexType
     * 
     */
    private GrupoDDA0106R1TercComplexType geraGrupoTerceiroAutorizadoDDA0106R1() {
        GrupoDDA0106R1TercComplexType grupoTerceiro = new GrupoDDA0106R1TercComplexType();
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
     * @return GrupoDDA0106R1CalcComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1CalcComplexType geraGrupoCalculoDDA0106R1() throws ComumException {
        GrupoDDA0106R1CalcComplexType grupoCalculo = new GrupoDDA0106R1CalcComplexType();
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
     * @return GrupoDDA0106R1NotaFisComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1NotaFisComplexType geraGrupoNotaFiscalDDA0106R1() throws ComumException {
        GrupoDDA0106R1NotaFisComplexType grupoNotaFiscal = new GrupoDDA0106R1NotaFisComplexType();
        grupoNotaFiscal.setDtEmsNotaFis(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoNotaFiscal.setNumNotaFis(Constantes.NOME_TESTE);
        grupoNotaFiscal.setVlrNotaFis(BigDecimal.ONE);
        return grupoNotaFiscal;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1DesctTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1DesctTitComplexType geraGrupoDescontoDDA0106R1() throws ComumException {
        GrupoDDA0106R1DesctTitComplexType grupoDesconto = new GrupoDDA0106R1DesctTitComplexType();
        grupoDesconto.setCodDesctTit(Constantes.NOME_TESTE);
        grupoDesconto.setDtDesctTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDesconto.setVlrPercDesctTit(BigDecimal.ONE);
        return grupoDesconto;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1MultaTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1MultaTitComplexType geraGrupoMultaDDA0106R1() throws ComumException {
        GrupoDDA0106R1MultaTitComplexType grupoMulta = new GrupoDDA0106R1MultaTitComplexType();
        grupoMulta.setCodMultaTit(Constantes.STRING_NUMERO_1);
        grupoMulta.setDtMultaTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoMulta.setVlrPercMultaTit(BigDecimal.ONE);
        return grupoMulta;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1JurosTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0106R1JurosTitComplexType geraGrupoJurosTitDDA0106R1() throws ComumException {
        GrupoDDA0106R1JurosTitComplexType grupoJuros = new GrupoDDA0106R1JurosTitComplexType();
        grupoJuros.setCodJurosTit(Constantes.STRING_NUMERO_1);
        grupoJuros.setDtJurosTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoJuros.setVlrPercJurosTit(BigDecimal.ONE);
        return grupoJuros;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0106R1TitComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA106TitComplexType geraGrupoADDA106() throws ComumException {
        GrupoADDA106TitComplexType grupoBoleto = new GrupoADDA106TitComplexType();
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

        grupoBoleto.setCodCartTit(Constantes.STRING_NUMERO_1);
        grupoBoleto.setCodEspTit(Constantes.STRING_NUMERO_1);
        grupoBoleto.setCodMoedaCNAB(Constantes.NOME_TESTE);
        grupoBoleto.setIdentdNossoNum(Constantes.NOME_TESTE);
        grupoBoleto.setNumCodBarras(Constantes.NOME_TESTE);
        grupoBoleto.setNumLinhaDigtvl(Constantes.NOME_TESTE);

        grupoBoleto.setDtVencTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBoleto.setVlrTit(BigDecimal.ONE);
        grupoBoleto.setNumDocTit(Constantes.NOME_TESTE);

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

        grupoBoleto.setGrupoADDA106JurosTit(geraGrupoJurosTitADDA106());

        grupoBoleto.setGrupoADDA106MultaTit(geraGrupoMultaADDA106());

        grupoBoleto.getGrupoADDA106DesctTit().add(geraGrupoDescontoADDA106());

        grupoBoleto.getGrupoADDA106NotaFis().add(geraGrupoNotaFiscalADDA106());

        grupoBoleto.getGrupoADDA106Calc().add(geraGrupoCalculoADDA106());

        grupoBoleto.getGrupoADDA106Terc().add(geraGrupoTerceiroAutorizadoADDA106());

        grupoBoleto.getGrupoADDA106BaixaEft().add(geraGrupoBaixaEfetADDA106());

        grupoBoleto.getGrupoADDA106BaixaOperac().add(geraGrupoBaixaOperADDA106());

        grupoBoleto.getTxtInfBenfcrio().add(Constantes.NOME_TESTE);
        return grupoBoleto;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106BaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA106BaixaOperacComplexType geraGrupoBaixaOperADDA106() throws ComumException {
        GrupoADDA106BaixaOperacComplexType grupoBaixaOper = new GrupoADDA106BaixaOperacComplexType();
        grupoBaixaOper.setNumIdentcBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumRefAtlBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumSeqAtlzBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setISPBPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setCodPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setTpBaixaOperac(Constantes.STRING_NUMERO_1);
        grupoBaixaOper.setSitBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setDtHrSitBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBaixaOper.setGrupoADDA106DettBaixaOperac(geraGrupoDetBaixaOperADDA106());
        return grupoBaixaOper;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106DettBaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA106DettBaixaOperacComplexType geraGrupoDetBaixaOperADDA106() throws ComumException {
        GrupoADDA106DettBaixaOperacComplexType grupoDetBaixaOper = new GrupoADDA106DettBaixaOperacComplexType();
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
    private GrupoADDA106BaixaEftComplexType geraGrupoBaixaEfetADDA106() throws ComumException {
        GrupoADDA106BaixaEftComplexType grupoBaixaEfet = new GrupoADDA106BaixaEftComplexType();
        grupoBaixaEfet.setNumIdentcBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumRefAtlBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumSeqAtlzBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setNumIdentcBaixaOperacBaixaEft(BigInteger.ONE);
        grupoBaixaEfet.setTpBaixaEft(Constantes.STRING_NUMERO_1);
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
    private GrupoADDA106TercComplexType geraGrupoTerceiroAutorizadoADDA106() {
        GrupoADDA106TercComplexType grupoTerceiro = new GrupoADDA106TercComplexType();
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
     * @return GrupoADDA106CalcComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA106CalcComplexType geraGrupoCalculoADDA106() throws ComumException {
        GrupoADDA106CalcComplexType grupoCalculo = new GrupoADDA106CalcComplexType();
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
    private GrupoADDA106NotaFisComplexType geraGrupoNotaFiscalADDA106() throws ComumException {
        GrupoADDA106NotaFisComplexType grupoNotaFiscal = new GrupoADDA106NotaFisComplexType();
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
    private GrupoADDA106DesctTitComplexType geraGrupoDescontoADDA106() throws ComumException {
        GrupoADDA106DesctTitComplexType grupoDesconto = new GrupoADDA106DesctTitComplexType();
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

    /**
     * Método responsável por void
     * 
     */
    private void verifyEmPersistBoletoDDA() {
        verify(em, times(1)).persist(any(BoletoDDA.class));
    }

    /**
     * Método responsável por void
     * 
     */
    private void verifyEmMergeBoletoDDA() {
        verify(em, times(1)).merge(any(BoletoDDA.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    private void verifyObterBoletoDDA() throws ComumException {
        verify(dao, times(1)).obterBoletoDDALock(Constantes.LONG_UM);
    }

}
