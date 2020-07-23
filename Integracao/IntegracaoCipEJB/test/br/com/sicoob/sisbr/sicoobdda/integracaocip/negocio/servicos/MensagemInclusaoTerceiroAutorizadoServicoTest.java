/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemInclusaoTerceiroAutorizadoServicoTest.java
 * Data Criação:    May 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemInclusaoTerceiroAutorizadoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.BoletoDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDATerceiroAutLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R3ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemInclusaoTerceiroAutorizadoServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class })
public class MensagemInclusaoTerceiroAutorizadoServicoTest extends Mockito {

    @InjectMocks
    private MensagemInclusaoTerceiroAutorizadoServicoEJB ejb;

    @Mock
    private BoletoCipDao dao;

    @Mock
    private EntityManager em;

    @Mock
    private MensagemDDADelegate mensagemDDADelegate;

    private final InformacoesUsuario infoUsuario = new InformacoesUsuario(geraUsuarioBancoob());

    /**
     * Método responsável por void
     * 
     * @throws MensagemDesconhecidaException
     * 
     */
    @Before
    public void setUp() throws MensagemDesconhecidaException {
        PowerMockito.mockStatic(InformacoesUsuario.class);
        when(InformacoesUsuario.getInstance()).thenReturn(infoUsuario);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem());
        verify(dao, times(1)).obterMensagemDDATerceiroAutorizadoAtualizaReferencias(Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoDDA0121R1Incluir() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetorno(geraDDA0121R1(), null));
        verify(dao, times(1)).obterMensagemDDATerceiroAutorizado(Constantes.LONG_UM);
        verify(em, times(1)).persist(any(BoletoDDATerceiroAut.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoDDA0121R1Alterar() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetorno(geraDDA0121R1(), new BoletoDDATerceiroAut()));
        verify(dao, times(1)).obterMensagemDDATerceiroAutorizado(Constantes.LONG_UM);
        verify(em, times(1)).merge(any(BoletoDDATerceiroAut.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoDDA0121R2() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetorno(geraDDA0121R2(), null));
        verify(dao, times(1)).obterBoletoDDATerceiroAutorizado(Constantes.LONG_UM, Constantes.LONG_UM);
        verify(em, times(1)).persist(any(BoletoDDATerceiroAut.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoADDA121RR2Incluir() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetorno(geraADDA0121RR2(), null));
        verify(dao, times(1)).obterBoletoDDATerceiroAutorizado(Constantes.LONG_UM, Constantes.LONG_UM);
        verify(em, times(1)).persist(any(BoletoDDATerceiroAut.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoADDA121RR2Alterar() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetorno(geraADDA0121RR2(), new BoletoDDATerceiroAut()));
        verify(dao, times(1)).obterBoletoDDATerceiroAutorizado(Constantes.LONG_UM, Constantes.LONG_UM);
        verify(em, times(1)).merge(any(BoletoDDATerceiroAut.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    @Ignore
    public void processarRetornoDDA0121R3IncluirDDA0106() throws ComumException {
        BoletoDDA boleto = BoletoDDALoader.geraBoletoDDA();
        boleto.setNumSeqAtualCadBoleto(Constantes.LONG_DOIS);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoR3(geraDDA0121R3(), boleto));
        verify(mensagemDDADelegate, times(1)).incluir(any(MensagemDDAConsultaBoleto.class), any(String.class), any(String.class), any(Integer.class), any(Short.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test
    public void processarRetornoDDA0121R3Alterar() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoR3(geraDDA0121R3(), BoletoDDALoader.geraBoletoDDA()));
        verify(dao, times(1)).obterBoletoDDALock(Constantes.LONG_UM);
        verify(em, times(1)).merge(any(BoletoDDA.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test
    public void processarRetornoADDA121RR3Incluir() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoR3(geraGrupoADDA0121R3(), null));
        verify(dao, times(1)).obterBoletoDDALock(Constantes.LONG_UM);
        verify(em, times(1)).persist(any(BoletoDDA.class));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Ignore
    @Test
    public void processarRetornoADDA121RR3Alterar() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoR3(geraGrupoADDA0121R3(), BoletoDDALoader.geraBoletoDDA()));
        verify(dao, times(1)).obterBoletoDDALock(Constantes.LONG_UM);
        verify(em, times(1)).merge(any(BoletoDDA.class));
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String processarMensagem() throws ComumException {
        when(dao.obterMensagemDDATerceiroAutorizadoAtualizaReferencias(Constantes.LONG_UM)).thenReturn(MensagemDDATerceiroAutLoader.gerar());
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetorno(ConteudoMsgRecebida retornoDDA, BoletoDDATerceiroAut boletoDDATerceiro) throws ComumException {
        when(dao.obterBoletoDDATerceiroAutorizado(Constantes.LONG_UM, Constantes.LONG_UM)).thenReturn(boletoDDATerceiro);
        when(dao.obterMensagemDDATerceiroAutorizado(Constantes.LONG_UM)).thenReturn(MensagemDDATerceiroAutLoader.gerar());
        return processarRetorno(retornoDDA);
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @param boletoDDA
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetornoR3(ConteudoMsgRecebida retornoDDA, BoletoDDA boletoDDA) throws ComumException {
        when(dao.obterBoletoDDALock(Constantes.LONG_UM)).thenReturn(boletoDDA);
        return processarRetorno(retornoDDA);
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetorno(ConteudoMsgRecebida retornoDDA) throws ComumException {
        ejb.processarRetornoMensagemDDA(retornoDDA);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0121R1ComplexType
     * 
     */
    private DDA0121R1ComplexType geraDDA0121R1() {
        DDA0121R1ComplexType msg = new DDA0121R1ComplexType();
        msg.setCodMsg(TipoMensagemDDA.DDA0121R1);
        msg.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        msg.setNumIdentcTerc(BigInteger.ONE);
        msg.setNumRefAtlCadTerc(BigInteger.ONE);
        return msg;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0121R2ComplexType
     * 
     */
    private DDA0121R2ComplexType geraDDA0121R2() {
        DDA0121R2ComplexType msg = new DDA0121R2ComplexType();
        msg.setCodMsg(TipoMensagemDDA.DDA0121R2);
        msg.setNumIdentcTerc(BigInteger.ONE);
        msg.setNumIdentcTit(BigInteger.ONE);
        msg.setNumRefAtlCadTerc(BigInteger.ONE);
        msg.setTpPessoaPagdrAutzdr(TipoPessoaEnum.PF.getCodDominioCip());
        msg.setCNPJCPFPagdrAutzdr(BigInteger.ONE);
        msg.setTpPessoaTerc(TipoPessoaEnum.PF.getCodDominioCip());
        msg.setCNPJCPFTerc(BigInteger.ONE);
        return msg;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA121RR2TitComplexType
     * 
     */
    private GrupoADDA121RR2TitComplexType geraADDA0121RR2() {
        GrupoADDA121RR2TitComplexType msg = new GrupoADDA121RR2TitComplexType();
        msg.setNumIdentcTerc(BigInteger.ONE);
        msg.setNumIdentcTit(BigInteger.ONE);
        msg.setNumRefAtlCadTerc(BigInteger.ONE);
        msg.setTpPessoaPagdrAutzdr(TipoPessoaEnum.PF.getCodDominioCip());
        msg.setCNPJCPFPagdrAutzdr(BigInteger.ONE);
        msg.setTpPessoaTerc(TipoPessoaEnum.PF.getCodDominioCip());
        msg.setCNPJCPFTerc(BigInteger.ONE);
        return msg;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0121R3ComplexType
     * @throws ComumException
     * 
     */
    private DDA0121R3ComplexType geraDDA0121R3() throws ComumException {
        DDA0121R3ComplexType msg = new DDA0121R3ComplexType();
        msg.setCodMsg(TipoMensagemDDA.DDA0121R3);
        msg.setGrupoDDA0121R3Tit(geraGrupoDDA0121R3());
        return msg;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0121R3TitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3TitComplexType geraGrupoDDA0121R3() throws ComumException {
        GrupoDDA0121R3TitComplexType grupo = new GrupoDDA0121R3TitComplexType();
        grupo.setNumIdentcTit(BigInteger.ONE);
        grupo.setNumRefAtlCadTit(BigInteger.ONE);
        grupo.setNumSeqAtlzCadTit(BigInteger.ONE);
        grupo.setISPBPartDestinatario(Constantes.NOME_TESTE);
        grupo.setCodPartDestinatario(Constantes.NOME_TESTE);

        grupo.setTpPessoaBenfcrioOr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupo.setCNPJCPFBenfcrioOr(Constantes.CNPJ_AUX_BIGINT);
        grupo.setNomRzSocBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setNomFantsBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setLogradBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setCidBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setUFBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setCEPBenfcrioOr(BigInteger.ONE);

        // BeneficiarioFinal
        grupo.setTpPessoaBenfcrioFinl(TipoPessoaEnum.PJ.getCodDominioCip());
        grupo.setCNPJCPFBenfcrioFinl(Constantes.CNPJ_AUX_BIGINT);
        grupo.setNomRzSocBenfcrioFinl(Constantes.NOME_TESTE);
        grupo.setNomFantsBenfcrioFinl(Constantes.NOME_TESTE);

        // Pagador
        grupo.setTpPessoaPagdr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupo.setCNPJCPFPagdr(Constantes.CNPJ_AUX_BIGINT);
        grupo.setNomRzSocPagdr(Constantes.NOME_TESTE);
        grupo.setNomFantsPagdr(Constantes.NOME_TESTE);
        grupo.setLogradPagdr(Constantes.NOME_TESTE);
        grupo.setCidPagdr(Constantes.NOME_TESTE);
        grupo.setUFPagdr(Constantes.NOME_TESTE);
        grupo.setCEPPagdr(BigInteger.ONE);

        // Sacador Avalista
        grupo.setTpIdentcSacdrAvalst(Constantes.BIG_INTEGER_2_AUX);
        grupo.setIdentcSacdrAvalst(Constantes.CNPJ_AUX);
        grupo.setNomRzSocSacdrAvalst(Constantes.NOME_TESTE);

        grupo.setCodCartTit(Constantes.STRING_NUMERO_1);
        grupo.setCodEspTit(Constantes.STRING_NUMERO_1);
        grupo.setCodMoedaCNAB(Constantes.NOME_TESTE);
        grupo.setIdentdNossoNum(Constantes.NOME_TESTE);
        grupo.setNumCodBarras(Constantes.NOME_TESTE);
        grupo.setNumLinhaDigtvl(Constantes.NOME_TESTE);

        grupo.setDtVencTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupo.setVlrTit(BigDecimal.ONE);
        grupo.setNumDocTit(Constantes.NOME_TESTE);

        grupo.setDtEmsTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupo.setQtdDiaPrott(BigInteger.ONE);
        grupo.setDtLimPgtoTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupo.setTpPgtoTit(BigInteger.ONE);
        grupo.setNumParcl(BigInteger.ONE);
        grupo.setQtdTotParcl(BigInteger.ONE);

        grupo.setIndrTitNegcd(Constantes.NOME_TESTE);
        grupo.setIndrBloqPgto(Constantes.NOME_TESTE);
        grupo.setIndrPgtoParcl(Constantes.NOME_TESTE);

        grupo.setQtdPgtoParcl(BigInteger.ONE);

        grupo.setVlrAbattTit(BigDecimal.ONE);

        grupo.setTpVlrPercMinTit(Constantes.NOME_TESTE);
        grupo.setVlrPercMinTit(BigDecimal.ONE);

        grupo.setTpVlrPercMaxTit(Constantes.NOME_TESTE);
        grupo.setVlrPercMaxTit(BigDecimal.ONE);

        grupo.setTpModlCalc(Constantes.NOME_TESTE);
        grupo.setTpAutcRecbtVlrDivgte(Constantes.NOME_TESTE);

        grupo.setDtHrSit(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupo.setQtdPgtoParclRegtd(BigInteger.ONE);
        grupo.setVlrSldTotAtlPgtoTit(BigDecimal.ONE);
        grupo.setSitTitPgto(Constantes.NOME_TESTE);

        grupo.setIndrActe(Constantes.NOME_TESTE);
        grupo.setNumRefAtlActe(BigInteger.ONE);
        grupo.setNumSeqAtlzActe(BigInteger.ONE);

        grupo.setGrupoDDA0121R3JurosTit(geraGrupoJuros());

        grupo.setGrupoDDA0121R3MultaTit(geraGrupoMulta());

        grupo.getGrupoDDA0121R3DesctTit().add(geraGrupoDesconto());

        grupo.getGrupoDDA0121R3NotaFis().add(geraGrupoNotaFiscal());

        grupo.getGrupoDDA0121R3Calc().add(geraGrupoCalculo());

        grupo.getGrupoDDA0121R3Terc().add(geraGrupoTerceiroAutorizado());

        grupo.getGrupoDDA0121R3BaixaEft().add(geraGrupoBaixaEfet());

        grupo.getGrupoDDA0121R3BaixaOperac().add(geraGrupoBaixaOper());

        grupo.getTxtInfBenfcrio().add(Constantes.NOME_TESTE);
        return grupo;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA106BaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3BaixaOperacComplexType geraGrupoBaixaOper() throws ComumException {
        GrupoDDA0121R3BaixaOperacComplexType grupoBaixaOper = new GrupoDDA0121R3BaixaOperacComplexType();
        grupoBaixaOper.setNumIdentcBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumRefAtlBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumSeqAtlzBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setISPBPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setCodPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setTpBaixaOperac(Constantes.STRING_NUMERO_1);
        grupoBaixaOper.setSitBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setDtHrSitBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBaixaOper.setGrupoDDA0121R3DettBaixaOperac(geraGrupoDetBaixaOper());
        return grupoBaixaOper;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0121R3DettBaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3DettBaixaOperacComplexType geraGrupoDetBaixaOper() throws ComumException {
        GrupoDDA0121R3DettBaixaOperacComplexType grupoDetBaixaOper = new GrupoDDA0121R3DettBaixaOperacComplexType();
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
     * @return GrupoDDA0121R3BaixaEftComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3BaixaEftComplexType geraGrupoBaixaEfet() throws ComumException {
        GrupoDDA0121R3BaixaEftComplexType grupoBaixaEfet = new GrupoDDA0121R3BaixaEftComplexType();
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
     * @return GrupoDDA0121R3TercComplexType
     * 
     */
    private GrupoDDA0121R3TercComplexType geraGrupoTerceiroAutorizado() {
        GrupoDDA0121R3TercComplexType grupoTerceiro = new GrupoDDA0121R3TercComplexType();
        grupoTerceiro.setTpPessoaPagdrAutzdr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoTerceiro.setCNPJCPFPagdrAutzdr(Constantes.CNPJ_AUX_BIGINT);
        grupoTerceiro.setTpPessoaTerc(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoTerceiro.setCNPJCPFTerc(Constantes.CNPJ_AUX_BIGINT);
        grupoTerceiro.setNumIdentcTerc(BigInteger.ONE);
        grupoTerceiro.setNumRefAtlCadTerc(BigInteger.ONE);
        grupoTerceiro.setSitTerc("S");
        return grupoTerceiro;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0121R3CalcComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3CalcComplexType geraGrupoCalculo() throws ComumException {
        GrupoDDA0121R3CalcComplexType grupoCalculo = new GrupoDDA0121R3CalcComplexType();
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
     * @return GrupoDDA0121R3NotaFisComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3NotaFisComplexType geraGrupoNotaFiscal() throws ComumException {
        GrupoDDA0121R3NotaFisComplexType grupoNotaFiscal = new GrupoDDA0121R3NotaFisComplexType();
        grupoNotaFiscal.setDtEmsNotaFis(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoNotaFiscal.setNumNotaFis(Constantes.NOME_TESTE);
        grupoNotaFiscal.setVlrNotaFis(BigDecimal.ONE);
        return grupoNotaFiscal;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0121R3DesctTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3DesctTitComplexType geraGrupoDesconto() throws ComumException {
        GrupoDDA0121R3DesctTitComplexType grupoDesconto = new GrupoDDA0121R3DesctTitComplexType();
        grupoDesconto.setCodDesctTit(Constantes.NOME_TESTE);
        grupoDesconto.setDtDesctTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDesconto.setVlrPercDesctTit(BigDecimal.ONE);
        return grupoDesconto;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0121R3MultaTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3MultaTitComplexType geraGrupoMulta() throws ComumException {
        GrupoDDA0121R3MultaTitComplexType grupoMulta = new GrupoDDA0121R3MultaTitComplexType();
        grupoMulta.setCodMultaTit(Constantes.STRING_NUMERO_1);
        grupoMulta.setDtMultaTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoMulta.setVlrPercMultaTit(BigDecimal.ONE);
        return grupoMulta;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0121R3JurosTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0121R3JurosTitComplexType geraGrupoJuros() throws ComumException {
        GrupoDDA0121R3JurosTitComplexType grupoJuros = new GrupoDDA0121R3JurosTitComplexType();
        grupoJuros.setCodJurosTit(Constantes.STRING_NUMERO_1);
        grupoJuros.setDtJurosTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoJuros.setVlrPercJurosTit(BigDecimal.ONE);
        return grupoJuros;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException GrupoADDA121RR3TitComplexType
     * 
     */
    private GrupoADDA121RR3TitComplexType geraGrupoADDA0121R3() throws ComumException {
        GrupoADDA121RR3TitComplexType grupo = new GrupoADDA121RR3TitComplexType();
        grupo.setNumIdentcTit(BigInteger.ONE);
        grupo.setNumRefAtlCadTit(BigInteger.ONE);
        grupo.setNumSeqAtlzCadTit(BigInteger.ONE);
        grupo.setISPBPartDestinatario(Constantes.NOME_TESTE);
        grupo.setCodPartDestinatario(Constantes.NOME_TESTE);

        grupo.setTpPessoaBenfcrioOr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupo.setCNPJCPFBenfcrioOr(Constantes.CNPJ_AUX_BIGINT);
        grupo.setNomRzSocBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setNomFantsBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setLogradBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setCidBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setUFBenfcrioOr(Constantes.NOME_TESTE);
        grupo.setCEPBenfcrioOr(BigInteger.ONE);

        // BeneficiarioFinal
        grupo.setTpPessoaBenfcrioFinl(TipoPessoaEnum.PJ.getCodDominioCip());
        grupo.setCNPJCPFBenfcrioFinl(Constantes.CNPJ_AUX_BIGINT);
        grupo.setNomRzSocBenfcrioFinl(Constantes.NOME_TESTE);
        grupo.setNomFantsBenfcrioFinl(Constantes.NOME_TESTE);

        // Pagador
        grupo.setTpPessoaPagdr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupo.setCNPJCPFPagdr(Constantes.CNPJ_AUX_BIGINT);
        grupo.setNomRzSocPagdr(Constantes.NOME_TESTE);
        grupo.setNomFantsPagdr(Constantes.NOME_TESTE);
        grupo.setLogradPagdr(Constantes.NOME_TESTE);
        grupo.setCidPagdr(Constantes.NOME_TESTE);
        grupo.setUFPagdr(Constantes.NOME_TESTE);
        grupo.setCEPPagdr(BigInteger.ONE);

        // Sacador Avalista
        grupo.setTpIdentcSacdrAvalst(Constantes.BIG_INTEGER_2_AUX);
        grupo.setIdentcSacdrAvalst(Constantes.CNPJ_AUX);
        grupo.setNomRzSocSacdrAvalst(Constantes.NOME_TESTE);

        grupo.setCodCartTit(Constantes.STRING_NUMERO_1);
        grupo.setCodEspTit(Constantes.STRING_NUMERO_1);
        grupo.setCodMoedaCNAB(Constantes.NOME_TESTE);
        grupo.setIdentdNossoNum(Constantes.NOME_TESTE);
        grupo.setNumCodBarras(Constantes.NOME_TESTE);
        grupo.setNumLinhaDigtvl(Constantes.NOME_TESTE);

        grupo.setDtVencTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupo.setVlrTit(BigDecimal.ONE);
        grupo.setNumDocTit(Constantes.NOME_TESTE);

        grupo.setDtEmsTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupo.setQtdDiaPrott(BigInteger.ONE);
        grupo.setDtLimPgtoTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupo.setTpPgtoTit(BigInteger.ONE);
        grupo.setNumParcl(BigInteger.ONE);
        grupo.setQtdTotParcl(BigInteger.ONE);

        grupo.setIndrTitNegcd(Constantes.NOME_TESTE);
        grupo.setIndrBloqPgto(Constantes.NOME_TESTE);
        grupo.setIndrPgtoParcl(Constantes.NOME_TESTE);

        grupo.setQtdPgtoParcl(BigInteger.ONE);

        grupo.setVlrAbattTit(BigDecimal.ONE);

        grupo.setTpVlrPercMinTit(Constantes.NOME_TESTE);
        grupo.setVlrPercMinTit(BigDecimal.ONE);

        grupo.setTpVlrPercMaxTit(Constantes.NOME_TESTE);
        grupo.setVlrPercMaxTit(BigDecimal.ONE);

        grupo.setTpModlCalc(Constantes.NOME_TESTE);
        grupo.setTpAutcRecbtVlrDivgte(Constantes.NOME_TESTE);

        grupo.setDtHrSit(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupo.setQtdPgtoParclRegtd(BigInteger.ONE);
        grupo.setVlrSldTotAtlPgtoTit(BigDecimal.ONE);
        grupo.setSitTitPgto(Constantes.NOME_TESTE);

        grupo.setIndrActe(Constantes.NOME_TESTE);
        grupo.setNumRefAtlActe(BigInteger.ONE);
        grupo.setNumSeqAtlzActe(BigInteger.ONE);

        grupo.setGrupoADDA121RR3JurosTit(geraGrupoJurosADDA());

        grupo.setGrupoADDA121RR3MultaTit(geraGrupoMultaADDA());

        grupo.getGrupoADDA121RR3DesctTit().add(geraGrupoDescontoADDA());

        grupo.getGrupoADDA121RR3NotaFis().add(geraGrupoNotaFiscalADDA());

        grupo.getGrupoADDA121RR3Calc().add(geraGrupoCalculoADDA());

        grupo.getGrupoADDA121RR3Terc().add(geraGrupoTerceiroAutorizadoADDA());

        grupo.getGrupoADDA121RR3BaixaEft().add(geraGrupoBaixaEfetADDA());

        grupo.getGrupoADDA121RR3BaixaOperac().add(geraGrupoBaixaOperADDA());

        grupo.getTxtInfBenfcrio().add(Constantes.NOME_TESTE);
        return grupo;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA121RR3BaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA121RR3BaixaOperacComplexType geraGrupoBaixaOperADDA() throws ComumException {
        GrupoADDA121RR3BaixaOperacComplexType grupoBaixaOper = new GrupoADDA121RR3BaixaOperacComplexType();
        grupoBaixaOper.setNumIdentcBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumRefAtlBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setNumSeqAtlzBaixaOperac(BigInteger.ONE);
        grupoBaixaOper.setISPBPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setCodPartRecbdrBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setTpBaixaOperac(Constantes.STRING_NUMERO_1);
        grupoBaixaOper.setSitBaixaOperac(Constantes.NOME_TESTE);
        grupoBaixaOper.setDtHrSitBaixaOperac(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBaixaOper.getGrupoADDA121RR3DettBaixaOperac().add(geraGrupoDetBaixaOperADDA());
        return grupoBaixaOper;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA121RR3DettBaixaOperacComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA121RR3DettBaixaOperacComplexType geraGrupoDetBaixaOperADDA() throws ComumException {
        GrupoADDA121RR3DettBaixaOperacComplexType grupoDetBaixaOper = new GrupoADDA121RR3DettBaixaOperacComplexType();
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
     * @return GrupoADDA121RR3BaixaEftComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA121RR3BaixaEftComplexType geraGrupoBaixaEfetADDA() throws ComumException {
        GrupoADDA121RR3BaixaEftComplexType grupoBaixaEfet = new GrupoADDA121RR3BaixaEftComplexType();
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
     * @return GrupoADDA121RR3TercComplexType
     * 
     */
    private GrupoADDA121RR3TercComplexType geraGrupoTerceiroAutorizadoADDA() {
        GrupoADDA121RR3TercComplexType grupoTerceiro = new GrupoADDA121RR3TercComplexType();
        grupoTerceiro.setTpPessoaPagdrAutzdr(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoTerceiro.setCNPJCPFPagdrAutzdr(Constantes.CNPJ_AUX_BIGINT);
        grupoTerceiro.setTpPessoaTerc(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoTerceiro.setCNPJCPFTerc(Constantes.CNPJ_AUX_BIGINT);
        grupoTerceiro.setNumIdentcTerc(BigInteger.ONE);
        grupoTerceiro.setNumRefAtlCadTerc(BigInteger.ONE);
        grupoTerceiro.setSitTerc("S");
        return grupoTerceiro;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA121RR3CalcComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA121RR3CalcComplexType geraGrupoCalculoADDA() throws ComumException {
        GrupoADDA121RR3CalcComplexType grupoCalculo = new GrupoADDA121RR3CalcComplexType();
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
     * @return GrupoADDA121RR3NotaFisComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA121RR3NotaFisComplexType geraGrupoNotaFiscalADDA() throws ComumException {
        GrupoADDA121RR3NotaFisComplexType grupoNotaFiscal = new GrupoADDA121RR3NotaFisComplexType();
        grupoNotaFiscal.setDtEmsNotaFis(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoNotaFiscal.setNumNotaFis(Constantes.NOME_TESTE);
        grupoNotaFiscal.setVlrNotaFis(BigDecimal.ONE);
        return grupoNotaFiscal;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA121RR3DesctTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA121RR3DesctTitComplexType geraGrupoDescontoADDA() throws ComumException {
        GrupoADDA121RR3DesctTitComplexType grupoDesconto = new GrupoADDA121RR3DesctTitComplexType();
        grupoDesconto.setCodDesctTit(Constantes.NOME_TESTE);
        grupoDesconto.setDtDesctTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoDesconto.setVlrPercDesctTit(BigDecimal.ONE);
        return grupoDesconto;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA121RR3MultaTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA121RR3MultaTitComplexType geraGrupoMultaADDA() throws ComumException {
        GrupoADDA121RR3MultaTitComplexType grupoMulta = new GrupoADDA121RR3MultaTitComplexType();
        grupoMulta.setCodMultaTit(Constantes.STRING_NUMERO_1);
        grupoMulta.setDtMultaTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoMulta.setVlrPercMultaTit(BigDecimal.ONE);
        return grupoMulta;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA121RR3JurosTitComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA121RR3JurosTitComplexType geraGrupoJurosADDA() throws ComumException {
        GrupoADDA121RR3JurosTitComplexType grupoJuros = new GrupoADDA121RR3JurosTitComplexType();
        grupoJuros.setCodJurosTit(Constantes.STRING_NUMERO_1);
        grupoJuros.setDtJurosTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoJuros.setVlrPercJurosTit(BigDecimal.ONE);
        return grupoJuros;
    }

    /**
     * Método responsável por
     * 
     * @return UsuarioBancoob
     * 
     */
    private UsuarioBancoob geraUsuarioBancoob() {
        UsuarioBancoob usuario = new UsuarioBancoob();
        usuario.setCooperativa(Constantes.STRING_NUMERO_0);
        usuario.setDominio(Constantes.STRING_NUMERO_0);
        usuario.setIdInstituicao(Constantes.STRING_NUMERO_0);
        usuario.setIdUnidadeInstituicao(Constantes.STRING_NUMERO_0);
        usuario.setLogin(Constantes.STRING_NUMERO_0);
        usuario.setPac(Constantes.STRING_NUMERO_0);
        return usuario;
    }
}
