/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util
 * Arquivo:         RetornarObjetosUtil.java
 * Data Criação:    Jan 22, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.IFBeneficiarioAlerta;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAAceite;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioRepresentante;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoNotaFiscal;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoTextoInfo;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiarioCooperativa;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAOperacao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA003.GrupoADDA003PagdrDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA104.GrupoADDA104RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.DDA0001R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.DDA0110R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0115.DDA0115R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0118.DDA0118R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.DDA0127ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0402.DDA0402ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0402.GrupoDDA0402GrdHrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0403.DDA0403ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0404.DDA0404ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501.DDA0501R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.DDA0502R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0503.DDA0503R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0506.DDA0506ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0015.GEN0015ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * RetornarObjetosUtil é responsável por
 * 
 * @author Tayrone.Oliveira
 */
public class RetornarObjetosUtil extends Mockito {

    @Mock
    private ConteudoMsgRecebida conteudoMsgRecebida;

    /**
     * Método responsável por
     * 
     * @return DDA0403ComplexType
     * 
     */
    protected DDA0403ComplexType retornarDDA0403ComplexType() {
        DDA0403ComplexType ret = new DDA0403ComplexType();
        ret.setDtHrAbert(retornarXMLGregorianCalendar());
        ret.setCodGrd(TipoGradeHoraria.DDA12);
        ret.setDtMovto(retornarXMLGregorianCalendar());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return LogEnvioArquivoDDA
     */
    protected LogEnvioArquivoDDA retornarLogEnvioArquivoDDA() {
        LogEnvioArquivoDDA ret = new LogEnvioArquivoDDA();
        ret.setTipoMensagem(retornarTipoMensagemDDA());
        ret.setDataMovimento(Constantes.DATE_TIME_DB_AUX);
        ret.setId(Constantes.LONG_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return TipoMensagemDDA
     */
    protected TipoMensagemDDA retornarTipoMensagemDDA() {
        TipoMensagemDDA ret = new TipoMensagemDDA();
        ret.setCodTipoMensagem(TipoMensagemDDA.ADDA001);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA127TitComplexType
     */
    protected GrupoADDA127TitComplexType retornarGrupoADDA127TitComplexType() {
        GrupoADDA127TitComplexType ret = new GrupoADDA127TitComplexType();
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setISPBPartDestinatario(Constantes.STRING_NUMERO_1);
        ret.setCodPartDestinatario(Constantes.STRING_NUMERO_1);
        ret.setTpPessoaBenfcrioFinl(Constantes.STRING_NUMERO_1);
        ret.setCNPJCPFBenfcrioOr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomRzSocBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setNomFantsBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setLogradBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setCidBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setCEPBenfcrioOr(Constantes.BIG_INTEGER_1_AUX);
        ret.setCNPJCPFBenfcrioOr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomRzSocBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setUFBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setTpPessoaBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setTpPessoaPagdr(Constantes.STRING_NUMERO_1);
        ret.setCNPJCPFPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setLogradPagdr(Constantes.STRING_NUMERO_1);
        ret.setCidPagdr(Constantes.STRING_NUMERO_1);
        ret.setUFPagdr(Constantes.STRING_NUMERO_1);
        ret.setNomRzSocPagdr(Constantes.STRING_NUMERO_1);
        ret.setCEPPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomFantsPagdr(Constantes.STRING_NUMERO_1);
        ret.setCodCartTit(Constantes.STRING_NUMERO_1);
        ret.setCodEspTit(Constantes.STRING_NUMERO_1);
        ret.setNumParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setQtdTotParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setIndrTitNegcd(Constantes.STRING_NUMERO_1);
        ret.setNumDocTit(Constantes.STRING_NUMERO_1);
        ret.setTpPgtoTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setDtVencTit(Constantes.XML_GREGORIAN_CALENDAR_AUX);
        ret.setIndrBloqPgto(Constantes.STRING_NUMERO_1);
        ret.setVlrAbattTit(Constantes.CEM);
        ret.setQtdPgtoParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setVlrTit(Constantes.CEM);
        ret.setQtdDiaPrott(Constantes.BIG_INTEGER_1_AUX);
        ret.setDtLimPgtoTit(Constantes.XML_GREGORIAN_CALENDAR_AUX);
        ret.setCodMoedaCNAB(Constantes.STRING_NUMERO_1);
        ret.setDtEmsTit(Constantes.XML_GREGORIAN_CALENDAR_AUX);
        ret.setIndrPgtoParcl(Constantes.STRING_NUMERO_1);
        ret.setIdentdNossoNum(Constantes.STRING_NUMERO_1);
        ret.setTpPgtoTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumCodBarras(Constantes.COD_BARRAS_39_VALIDO);
        ret.setNumLinhaDigtvl(Constantes.STRING_NUMERO_1);
        ret.setGrupoADDA127JurosTit(retornarGrupoADDA127JurosTit());
        ret.setGrupoADDA127MultaTit(retornarGrupoADDA127MultaTit());
        ret.getGrupoADDA127DesctTit().add(retornarGrupoADDA127DesctTitComplexType());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA127DesctTitComplexType
     */
    protected GrupoADDA127DesctTitComplexType retornarGrupoADDA127DesctTitComplexType() {
        GrupoADDA127DesctTitComplexType ret = new GrupoADDA127DesctTitComplexType();
        ret.setCodDesctTit(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA127JurosTitComplexType
     */
    protected GrupoADDA127JurosTitComplexType retornarGrupoADDA127JurosTit() {
        GrupoADDA127JurosTitComplexType ret = new GrupoADDA127JurosTitComplexType();
        ret.setCodJurosTit(Constantes.STRING_NUMERO_1);
        ret.setDtJurosTit(Constantes.XML_GREGORIAN_CALENDAR_AUX);
        ret.setVlrPercJurosTit(Constantes.CEM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA127MultaTitComplexType
     */
    protected GrupoADDA127MultaTitComplexType retornarGrupoADDA127MultaTit() {
        GrupoADDA127MultaTitComplexType ret = new GrupoADDA127MultaTitComplexType();
        ret.setCodMultaTit(Constantes.STRING_NUMERO_1);
        ret.setDtMultaTit(Constantes.XML_GREGORIAN_CALENDAR_AUX);
        ret.setVlrPercMultaTit(Constantes.CEM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0127ComplexType
     */
    protected DDA0127ComplexType retornarDDA0127ComplexType() {
        DDA0127ComplexType ret = new DDA0127ComplexType();
        ret.setCodMsg(TipoMensagemDDA.ADDA001);
        ret.getGrupoDDA0127Tit().add(retornarGrupoDDA0127TitComplexType());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0127TitComplexType
     */
    protected GrupoDDA0127TitComplexType retornarGrupoDDA0127TitComplexType() {
        GrupoDDA0127TitComplexType ret = new GrupoDDA0127TitComplexType();
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setISPBPartDestinatario(Constantes.STRING_NUMERO_1);
        ret.setCodPartDestinatario(Constantes.STRING_NUMERO_1);
        ret.setTpPessoaBenfcrioFinl(Constantes.STRING_NUMERO_1);
        ret.setCNPJCPFBenfcrioOr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomRzSocBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setNomFantsBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setLogradBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setCidBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setCEPBenfcrioOr(Constantes.BIG_INTEGER_1_AUX);
        ret.setCNPJCPFBenfcrioOr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomRzSocBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setUFBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setTpPessoaBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setTpPessoaPagdr(Constantes.STRING_NUMERO_1);
        ret.setCNPJCPFPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setLogradPagdr(Constantes.STRING_NUMERO_1);
        ret.setCidPagdr(Constantes.STRING_NUMERO_1);
        ret.setUFPagdr(Constantes.STRING_NUMERO_1);
        ret.setNomRzSocPagdr(Constantes.STRING_NUMERO_1);
        ret.setCEPPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomFantsPagdr(Constantes.STRING_NUMERO_1);
        ret.setCodCartTit(Constantes.STRING_NUMERO_1);
        ret.setCodEspTit(Constantes.STRING_NUMERO_1);
        ret.setNumParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setQtdTotParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setIndrTitNegcd(Constantes.STRING_NUMERO_1);
        ret.setNumDocTit(Constantes.STRING_NUMERO_1);
        ret.setTpPgtoTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setDtVencTit(Constantes.XML_GREGORIAN_CALENDAR_AUX);
        ret.setIndrBloqPgto(Constantes.STRING_NUMERO_1);
        ret.setVlrAbattTit(Constantes.CEM);
        ret.setQtdPgtoParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setVlrTit(Constantes.CEM);
        ret.setQtdDiaPrott(Constantes.BIG_INTEGER_1_AUX);
        ret.setDtLimPgtoTit(Constantes.XML_GREGORIAN_CALENDAR_AUX);
        ret.setCodMoedaCNAB(Constantes.STRING_NUMERO_1);
        ret.setDtEmsTit(Constantes.XML_GREGORIAN_CALENDAR_AUX);
        ret.setIndrPgtoParcl(Constantes.STRING_NUMERO_1);
        ret.setIdentdNossoNum(Constantes.STRING_NUMERO_1);
        ret.setTpPgtoTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumCodBarras(Constantes.COD_BARRAS_39_VALIDO);
        ret.setNumLinhaDigtvl(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAAceite
     * 
     */
    protected MensagemDDAAceite retornarMensagemDDAAceite() {
        MensagemDDAAceite ret = new MensagemDDAAceite();
        ret.setId(Constantes.LONG_UM);
        ret.setNumIdentificadorBoletoCip(Constantes.LONG_UM);
        ret.setNumRefAtualAceite(Constantes.LONG_UM);
        ret.setBolAceite(Boolean.TRUE);
        ret.setMensagemDDA(retornarMensagemDDA());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0108R1ComplexType
     */
    protected DDA0108R1ComplexType retornarDDA0108R1ComplexType() {
        DDA0108R1ComplexType ret = new DDA0108R1ComplexType();
        ret.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoleto
     * 
     */
    protected MensagemDDABoleto retornarMensagemDDABoleto() {
        MensagemDDABoleto ret = new MensagemDDABoleto();
        ret.setId(Constantes.LONG_UM);
        ret.setCodTipoPessoaBeneficiario(Constantes.STRING_NUMERO_1);
        ret.setNumCpfCnpjBeneficiario(Constantes.STRING_NUMERO_1);
        ret.setCodTipoPessoaPagador(Constantes.STRING_NUMERO_1);
        ret.setNumCpfCnpjPagador(Constantes.CPF_AUX);
        ret.setNumIdentificadorBoletoCip(Constantes.LONG_UM);
        ret.setNumRefAtualCadBoleto(Constantes.LONG_UM);
        ret.setMensagemDDA(retornarMensagemDDA());
        ret.setNumCepBeneficiario(Constantes.STRING_NUMERO_1);
        ret.setCodTipoPessoaBeneficiarioFinal(Constantes.STRING_NUMERO_1);
        ret.setNumCpfCnpjBeneficiarioFinal(Constantes.STRING_NUMERO_1);
        ret.setNomePagador(Constantes.NOME_BANCO);
        ret.setCodTipoPessoaDDAAvalista(Constantes.STRING_NUMERO_1);
        ret.setIdCarteira(Constantes.INTEGER_UM);
        ret.setBolTituloNegociado(Boolean.TRUE);
        ret.setIdEspecieDocumento(Constantes.INTEGER_UM);
        ret.setValorBoleto(new BigDecimal(Constantes.LONG_UM));
        ret.setNumCepPagador("72016190");
        ret.setBolBloqueioPagamento(Boolean.TRUE);
        ret.setCodTipoModeloCalculo(Constantes.STRING_NUMERO_1);
        ret.setCodAutorizacaoValorDivergente(Constantes.STRING_NUMERO_1);
        ret.setCodTipoPagamento(Constantes.INTEGER_UM);
        ret.setDataEmissao(Constantes.DATE_TIME_DB_AUX);
        ret.setBolPagamentoParcial(Boolean.TRUE);
        ret.setValorAbatimento(new BigDecimal(Constantes.INTEGER_UM));
        ret.setListaMensagemDDABoletoGrupoCalculo(listarMensagemDDABoletoGrupoCalculo());
        ret.setListaMensagemDDABoletoNotaFiscal(listarMensagemDDABoletoNotaFiscal());
        ret.setListaMensagemDDABoletoTextoInfo(listarMensagemDDABoletoTextoInfo());
        ret.setDataVencimento(Constantes.DATE_TIME_DB_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return BoletoDDABaixaOper
     * 
     */
    protected BoletoDDABaixaOper retornarBoletoDDABaixaOper() {
        BoletoDDABaixaOper ret = new BoletoDDABaixaOper();
        ret.setCodIspbPartRecebedorBaixaOperacional(Constantes.STRING_NUMERO_1);
        ret.setCodPartRecebedorBaixaOperacional(Constantes.STRING_NUMERO_1);
        ret.setNumIdentificadorBaixaOper(Constantes.LONG_UM);
        ret.setId(Constantes.LONG_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0101R1ComplexType
     * 
     */
    protected DDA0101R1ComplexType retornarDDA0101R1ComplexType() {
        DDA0101R1ComplexType ret = new DDA0101R1ComplexType();
        ret.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setISPBPartDestinatarioPrincipal(Constantes.STRING_NUMERO_1);
        ret.setNumSeqAtlzCadTit(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0101R2ComplexType
     * 
     */
    protected DDA0101R2ComplexType retornarDDA0101R2ComplexType() {
        DDA0101R2ComplexType ret = new DDA0101R2ComplexType();
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setISPBPartPrincipal(Constantes.STRING_NUMERO_1);
        ret.setTpPessoaBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setCNPJCPFBenfcrioOr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomRzSocBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setNomFantsBenfcrioOr(Constantes.NOME_BANCO);
        ret.setTpPessoaPagdr(Constantes.STRING_NUMERO_1);
        ret.setCNPJCPFPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomRzSocPagdr(Constantes.NOME_BANCO);
        ret.setNomFantsPagdr(Constantes.NOME_BANCO);
        ret.setLogradPagdr(Constantes.NOME_BANCO);
        ret.setCidPagdr(Constantes.NOME_BANCO);
        ret.setUFPagdr(Constantes.NOME_BANCO);
        ret.setCEPPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setCodCartTit(Constantes.STRING_NUMERO_1);
        ret.setCodEspTit(Constantes.STRING_NUMERO_1);
        ret.setNumDocTit(Constantes.STRING_NUMERO_1);
        ret.setTpPgtoTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setQtdTotParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setIndrTitNegcd(Constantes.STRING_NUMERO_1);
        ret.setDtVencTit(retornarXMLGregorianCalendar());
        ret.setVlrTit(new BigDecimal(Constantes.LONG_UM));
        ret.setQtdDiaPrott(Constantes.BIG_INTEGER_1_AUX);
        ret.setDtLimPgtoTit(retornarXMLGregorianCalendar());
        ret.setIndrBloqPgto(Constantes.STRING_NUMERO_1);
        ret.setVlrAbattTit(Constantes.CEM);
        ret.setQtdPgtoParcl(Constantes.BIG_INTEGER_1_AUX);
        ret.setCodMoedaCNAB(Constantes.STRING_NUMERO_1);
        ret.setDtEmsTit(retornarXMLGregorianCalendar());
        ret.setIndrPgtoParcl(Constantes.STRING_NUMERO_1);
        ret.setIdentdNossoNum(Constantes.STRING_NUMERO_1);
        ret.setNumCodBarras(Constantes.COD_BARRAS_39_VALIDO);
        ret.setNumLinhaDigtvl(Constantes.NUM_LINHA_DIGITAVEL);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0503R1ComplexType
     * 
     */
    protected DDA0503R1ComplexType retornarDDA0503R1ComplexType() {
        DDA0503R1ComplexType ret = new DDA0503R1ComplexType();
        ret.setNumIdentcBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0404ComplexType
     * 
     */
    protected DDA0404ComplexType retornarDDA0404ComplexType() {
        DDA0404ComplexType ret = new DDA0404ComplexType();
        ret.setDtHrFcht(retornarXMLGregorianCalendar());
        ret.setCodGrd(Constantes.STRING_NUMERO_1);
        ret.setDtMovto(retornarXMLGregorianCalendar());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABeneficiario
     * 
     */
    protected MensagemDDABeneficiario retornarMensagemDDABeneficiario() {
        MensagemDDABeneficiario ret = new MensagemDDABeneficiario();
        ret.setId(Constantes.LONG_UM);
        ret.setMensagemDDA(retornarMensagemDDA());
        ret.setCodSituacaoBeneficiario(Constantes.STRING_NUMERO_1);
        ret.setCodTipoPessoaBeneficiario(Constantes.STRING_NUMERO_1);
        ret.setDataHoraSituacao(Constantes.DATE_TIME_DB_AUX);
        ret.setNumCpfCnpjBeneficiario(Constantes.CPF_AUX);
        ret.setDataInicioRelacionamento(Constantes.DATE_TIME_DB_AUX);
        ret.setListaMensagemDDABeneficiarioConvenio(listarMensagemDDABeneficiarioConvenio());
        ret.setListaMensagemDDABeneficiarioRepresentante(listarMensagemDDABeneficiarioRepresentante());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return SituacaoBeneficiarioDDA
     * 
     */
    protected SituacaoBeneficiarioDDA retornarSituacaoBeneficiarioDDA() {
        SituacaoBeneficiarioDDA ret = new SituacaoBeneficiarioDDA();
        ret.setCodSituacaoBeneficiario(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0505R1ComplexType
     * 
     */
    protected DDA0505R1ComplexType retornarDDA0505R1ComplexType() {
        DDA0505R1ComplexType ret = new DDA0505R1ComplexType();
        ret.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        ret.setNumIdentcBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioDDA
     * 
     */
    protected BeneficiarioDDA retornarBeneficiarioDDA() {
        BeneficiarioDDA ret = new BeneficiarioDDA();
        ret.setListaBeneficiarioInstituicao(null);
        ret.setSituacaoBeneficiarioDDA(retornarSituacaoBeneficiarioDDA());
        ret.setDataInicioRelacionamento(DateUtil.getDateTimeDB(DataUtil.incrementarData(Constantes.DATE_AUX, Calendar.DAY_OF_MONTH, 3)));
        ret.setListaBeneficiarioInstituicao(listarBeneficiarioInstituicao());
        ret.getListaIFBeneficiarioAlerta().add(retornarIFBeneficiarioAlerta());
        ret.setBolOrigemSicoob(Boolean.TRUE);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return IFBeneficiarioAlerta
     * 
     */
    protected IFBeneficiarioAlerta retornarIFBeneficiarioAlerta() {
        IFBeneficiarioAlerta ret = new IFBeneficiarioAlerta();
        ret.setCodIspbDestinatarioOriginalAlerta(Constantes.STRING_NUMERO_1);
        return ret;

    }

    /**
     * Método responsável por
     * 
     * @return ParametroDDA
     * 
     */
    protected ParametroDDA retornarParametroDDA() {
        ParametroDDA ret = new ParametroDDA();
        ret.setValorParametro(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABaixaOperacional
     * 
     */
    protected MensagemDDABaixaOperacional retornarMensagemDDABaixaOperacional() {
        MensagemDDABaixaOperacional ret = new MensagemDDABaixaOperacional();
        ret.setId(Constantes.LONG_UM);
        ret.setNumIdentificadorBaixaOper(Constantes.LONG_UM);
        ret.setMensagemDDA(retornarMensagemDDA());
        ret.setCodTipoBaixaOperacional(Constantes.SHORT_UM);
        ret.setNumCpfCnpjPortador(Constantes.CPF_AUX);
        ret.setCodCanalPagamento(Constantes.SHORT_UM);
        ret.setCodMeioPagamento(Constantes.SHORT_UM);
        ret.setBolOperacaoContingencia(Boolean.TRUE);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAConsultaBoleto
     */
    protected MensagemDDAConsultaBoleto retornarMensagemDDAConsultaBoleto() {
        MensagemDDAConsultaBoleto ret = new MensagemDDAConsultaBoleto();
        ret.setId(Constantes.LONG_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0506ComplexType
     * 
     */
    protected DDA0506ComplexType retornarDDA0506ComplexType() {
        DDA0506ComplexType ret = new DDA0506ComplexType();
        ret.setCNPJCPFBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setTpPessoaBenfcrio(Constantes.STRING_NUMERO_1);
        ret.setNumCtrlDDA(Constantes.STRING_NUMERO_1);
        ret.getISPBPartDestinatarioOrigdrAlert().add(Constantes.STRING_NUMERO_1);
        ret.setSitBenfcrio(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioInstituicao
     * 
     */
    protected BeneficiarioInstituicao retornarBeneficiarioInstituicao() {
        BeneficiarioInstituicao ret = new BeneficiarioInstituicao();
        ret.setIdInstituicao(Constantes.INTEGER_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return InstituicaoDto
     * 
     */
    protected InstituicaoDto retornarInstituicaoDto() {
        InstituicaoDto ret = new InstituicaoDto();
        ret.setIdInstituicao(Constantes.INTEGER_UM);
        ret.setNumero(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagador
     */
    protected MensagemDDAPagador retornarMensagemDDAPagador() {
        MensagemDDAPagador ret = new MensagemDDAPagador();
        ret.setId(Constantes.LONG_UM);
        ret.setNumCpfCnpjPagador(Constantes.CPF_AUX);
        ret.setListaMensagemDDAPagadorConta(listarMensagemDDAPagadorConta());
        ret.setListaMensagemDDAPagadorAgregado(listarMensagemDDAPagadorAgregado());
        ret.setBolPagadorEletronico(Boolean.TRUE);
        ret.setNumIdentificaPagadorCip(Constantes.LONG_UM);
        ret.setNumRefAtualCadPagador(Constantes.LONG_UM);
        ret.setMensagemDDA(retornarMensagemDDA());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA003PagdrDDAComplexType
     */
    protected GrupoADDA003PagdrDDAComplexType retornarGrupoADDA003PagdrDDAComplexType() {
        GrupoADDA003PagdrDDAComplexType ret = new GrupoADDA003PagdrDDAComplexType();
        ret.setIndrManutPagdr(TipoManutencaoEnum.INCLUSAO.getCodIndicador());
        ret.setCNPJCPFPagdr(new BigInteger(Constantes.CPF_AUX));
        ret.setTpPessoaPagdr(Constantes.STRING_NUMERO_1);
        ret.setQtdAdesCliPagdrDDA(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagadorConta
     */
    protected MensagemDDAPagadorConta retornarMensagemDDAPagadorConta() {
        MensagemDDAPagadorConta ret = new MensagemDDAPagadorConta();
        ret.setId(Constantes.LONG_UM);
        ret.setNumAgencia(Constantes.INTEGER_UM);
        ret.setNumContaCorrente(Constantes.CEM);
        ret.setDataHoraAdesao(Constantes.DATE_TIME_DB_AUX);
        ret.setCodTipoOperacao(TipoOperacaoSicoobDDAEnum.INCLUSAO.getCodigoOperacao());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GEN0015ComplexType
     */
    protected GEN0015ComplexType retornarGEN0015ComplexType() {
        GEN0015ComplexType ret = new GEN0015ComplexType();
        ret.setNomArq("12345678911234567" + new Date().getTime() + "" + TipoArquivo.PROTOCOLO);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagadorAgregado
     */
    protected MensagemDDAPagadorAgregado retornarMensagemDDAPagadorAgregado() {
        MensagemDDAPagadorAgregado ret = new MensagemDDAPagadorAgregado();
        ret.setId(Constantes.LONG_UM);
        ret.setNumCpfCnpjAgregado(Constantes.CPF_AUX);
        ret.setCodTipoOperacao(TipoOperacaoSicoobDDAEnum.INCLUSAO.getCodigoOperacao());
        ret.setCodTipoPessoaAgregado(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDABeneficiarioCooperativa
     * 
     */
    protected DDABeneficiarioCooperativa retornarDDABeneficiarioCooperativa() {
        DDABeneficiarioCooperativa ret = new DDABeneficiarioCooperativa();
        ret.setNumCoop(Constantes.INTEGER_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDABeneficiario
     * 
     */
    protected DDABeneficiario retornarDDABeneficiario() {
        DDABeneficiario ret = new DDABeneficiario();
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorDDA
     */
    protected PagadorDDA retornarPagadorDDA() {
        PagadorDDA ret = new PagadorDDA();
        ret.setId(Constantes.LONG_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDAOperacao
     * 
     */
    protected DDAOperacao retornarDDAOperacao() {
        DDAOperacao ret = new DDAOperacao();
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return PessoaDto
     * 
     */
    protected PessoaDto retornarPessoaDto() {
        PessoaDto ret = new PessoaDto();
        ret.setIdPessoaLegado(Constantes.INTEGER_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABaixaEfetiva
     * 
     */
    protected MensagemDDABaixaEfetiva retornarMensagemDDABaixaEfetiva() {
        MensagemDDABaixaEfetiva ret = new MensagemDDABaixaEfetiva();
        ret.setId(Constantes.LONG_UM);
        ret.setNumIdentificadorBoletoCip(Constantes.LONG_UM);
        ret.setNumRefAtualCadBoleto(Constantes.LONG_UM);
        ret.setNumRefAtualBaixaEfetiva(Constantes.LONG_UM);
        ret.setCodTipoBaixaEfetiva(Constantes.INTEGER_UM);
        ret.setNumIdentificadorBaixaOper(Constantes.LONG_UM);
        ret.setValorBaixa(new BigDecimal(Constantes.LONG_UM));
        ret.setNumCodigoBarra(Constantes.COD_BARRAS_39_VALIDO);
        ret.setNumCodBarrasCampoLivre(Constantes.COD_BARRAS_39_VALIDO);
        ret.setCodCanalPagamento(Constantes.INTEGER_UM);
        ret.setCodMeioPagamento(Constantes.INTEGER_UM);
        ret.setMensagemDDA(retornarMensagemDDA());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return BoletoDDABaixaEfet
     */
    protected BoletoDDABaixaEfet retornarBoletoDDABaixaEfet() {
        BoletoDDABaixaEfet ret = new BoletoDDABaixaEfet();
        ret.setBoletoDDA(retornarBoletoDDA());
        ret.setDataHoraProcessamentoBaixaEfet(Constantes.DATE_TIME_DB_AUX);
        ret.setDataHoraProcessamentoBaixaEfet(Constantes.DATE_TIME_DB_AUX);
        ret.setNumIdentificadorBaixaOperacional(Constantes.LONG_UM);
        ret.setNumIdentificadorBaixaEfet(Constantes.LONG_UM);
        ret.setNumRefAtualBaixaEfet(Constantes.LONG_UM);
        ret.setNumSeqAtualBaixaEfet(Constantes.LONG_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502NPC.DDA0502R1ComplexType
     * 
     */
    protected DDA0502R1ComplexType retornarDDA0502R1ComplexType() {
        DDA0502R1ComplexType ret = new DDA0502R1ComplexType();
        ret.setNumRefAtlCadBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumIdentcBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0402ComplexType
     * 
     */
    protected DDA0402ComplexType retornarDDA0402ComplexType() {
        DDA0402ComplexType ret = new DDA0402ComplexType();
        ret.getGrupoDDA0402GrdHrio().add(retornarGrupoDDA0402GrdHrioComplexType());
        ret.setDtRef(retornarXMLGregorianCalendar());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0402GrdHrioComplexType
     * 
     */
    protected GrupoDDA0402GrdHrioComplexType retornarGrupoDDA0402GrdHrioComplexType() {
        GrupoDDA0402GrdHrioComplexType ret = new GrupoDDA0402GrdHrioComplexType();
        ret.setCodGrd(Constantes.STRING_NUMERO_1);
        ret.setDtHrAbert(retornarXMLGregorianCalendar());
        ret.setDtHrFcht(retornarXMLGregorianCalendar());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABeneficiarioRepresentante
     * 
     */
    protected MensagemDDABeneficiarioRepresentante retornarMensagemDDABeneficiarioRepresentante() {
        MensagemDDABeneficiarioRepresentante ret = new MensagemDDABeneficiarioRepresentante();
        ret.setNumCpfCnpjRepresentante(Constantes.CPF_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABeneficiarioConvenio
     * 
     */
    protected MensagemDDABeneficiarioConvenio retornarMensagemDDABeneficiarioConvenio() {
        MensagemDDABeneficiarioConvenio ret = new MensagemDDABeneficiarioConvenio();
        ret.setDataInicioRelacionamento(Constantes.DATE_TIME_DB_AUX);
        ret.setDataFimRelacionamento(Constantes.DATE_TIME_DB_AUX);
        ret.setNumAgencia(Constantes.INTEGER_UM);
        ret.setCodTipoOperacao(TipoManutencaoEnum.INCLUSAO.getCodIndicador());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoletoTextoInfo
     * 
     */
    protected MensagemDDABoletoTextoInfo retornarMensagemDDABoletoTextoInfo() {
        MensagemDDABoletoTextoInfo ret = new MensagemDDABoletoTextoInfo();
        ret.setDescTextoInformativo(Constantes.NOME_BANCO);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoletoNotaFiscal
     * 
     */
    protected MensagemDDABoletoNotaFiscal retornarMensagemDDABoletoNotaFiscal() {
        MensagemDDABoletoNotaFiscal ret = new MensagemDDABoletoNotaFiscal();
        ret.setNumNotaFiscal(Constantes.STRING_NUMERO_1);
        ret.setValorNotaFiscal(new BigDecimal(Constantes.INTEGER_UM));
        ret.setDataEmissaoNotaFiscal(Constantes.DATE_TIME_DB_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoletoGrupoCalculo
     * 
     */
    protected MensagemDDABoletoGrupoCalculo retornarMensagemDDABoletoGrupoCalculo() {
        MensagemDDABoletoGrupoCalculo ret = new MensagemDDABoletoGrupoCalculo();
        ret.setDataValidadeCalculo(Constantes.DATE_TIME_DB_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501NPC.DDA0501R1ComplexType
     * 
     */
    protected DDA0501R1ComplexType retornarDDA0501R1ComplexType() {
        DDA0501R1ComplexType ret = new DDA0501R1ComplexType();
        ret.setNumIdentcBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadBenfcrio(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return BoletoDDA
     * 
     */
    protected BoletoDDA retornarBoletoDDA() {
        BoletoDDA ret = new BoletoDDA();
        ret.setId(Constantes.LONG_UM);
        ret.setBolProcessarMensagemRecebida(Boolean.TRUE);
        ret.setBolProcessarMensagem106(Boolean.TRUE);
        ret.setNumIdentificadorBoletoCip(Constantes.LONG_UM);
        ret.setNumRefAtualCadBoleto(Constantes.LONG_UM);
        ret.setDataHoraSituacaoBoleto(Constantes.DATE_TIME_DB_AUX);
        ret.setCodPartDestinatario(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return LogDetRecebimentoArquivoDDA
     */
    protected LogDetRecebimentoArquivoDDA retornarLogDetRecebimentoArquivoDDA() {
        LogDetRecebimentoArquivoDDA ret = new LogDetRecebimentoArquivoDDA();
        ret.setDescXMLMensagemRecebida(Constantes.STRING_PALAVRA_FORAM);
        ret.setBolProcessado(Boolean.TRUE);
        ret.setNumOrdemProcessamento(Constantes.LONG_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return ArquivoProcessamentoVO
     */
    protected ArquivoProcessamentoVO retornarArquivoProcessamentoVO() {
        return new ArquivoProcessamentoVO(Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @return byte[]
     */
    protected byte[] retornarByte() {
        byte[] by = { 1, 2, 3 };
        return by;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0104R1ComplexType
     * 
     */
    protected DDA0104R1ComplexType retornarDDA0104R1ComplexType() {
        DDA0104R1ComplexType ret = new DDA0104R1ComplexType();
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        ret.setNumSeqAtlzActe(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlActe(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return retornarDDA0001R1ComplexType
     */
    protected DDA0001R1ComplexType retornarDDA0001R1ComplexType() {
        DDA0001R1ComplexType ret = new DDA0001R1ComplexType();
        ret.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        ret.setNumIdentcPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        ret.setQtdAdesCliPagdrDDA(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0110R1ComplexType
     */
    protected DDA0110R1ComplexType retornarDDA0110R1ComplexType() {
        DDA0110R1ComplexType ret = new DDA0110R1ComplexType();
        ret.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setISPBPartDestinatario(Constantes.STRING_NUMERO_1);
        ret.setCodPartDestinatario(Constantes.STRING_NUMERO_1);
        ret.setTpPessoaBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setCNPJCPFBenfcrioOr(Constantes.BIG_INTEGER_1_AUX);
        ret.setNomRzSocBenfcrioOr(Constantes.STRING_NUMERO_1);
        ret.setNomFantsBenfcrioOr(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0102R2ComplexType
     * 
     */
    protected DDA0102R2ComplexType retornarDDA0102R2ComplexType() {
        DDA0102R2ComplexType ret = new DDA0102R2ComplexType();
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setISPBPartDestinatario(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDATerceiroAut
     */
    protected MensagemDDATerceiroAut retornarMensagemDDATerceiroAut() {
        MensagemDDATerceiroAut ret = new MensagemDDATerceiroAut();
        ret.setId(Constantes.LONG_UM);
        ret.setNumIdentificadorBoletoCip(Constantes.LONG_UM);
        ret.setNumIdentificadorTerceiro(Constantes.LONG_UM);
        ret.setNumRefAtualTerceiro(Constantes.LONG_UM);
        ret.setNumCpfCnpjAutorizador(Constantes.CPF_AUX);
        ret.setNumCpfCnpjTerceiro(Constantes.CPF_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0104R2ComplexType
     * 
     */
    protected DDA0104R2ComplexType retornarDDA0104R2ComplexType() {
        DDA0104R2ComplexType ret = new DDA0104R2ComplexType();
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzActe(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlActe(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0102R1ComplexType
     * 
     */
    protected DDA0102R1ComplexType retornarDDA0102R1ComplexType() {
        DDA0102R1ComplexType ret = new DDA0102R1ComplexType();
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        ret.setNumRefAtlCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzCadTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setISPBPartDestinatarioPrincipal(Constantes.STRING_NUMERO_1);
        return ret;

    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA104RR2TitComplexType
     * 
     */
    protected GrupoADDA104RR2TitComplexType retornarGrupoADDA104RR2TitComplexType() {
        GrupoADDA104RR2TitComplexType ret = new GrupoADDA104RR2TitComplexType();
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzActe(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlActe(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDA
     * 
     */
    protected MensagemDDA retornarMensagemDDA() {
        MensagemDDA ret = new MensagemDDA();
        ret.setId(Constantes.LONG_UM);
        ret.setDataMovimento(Constantes.DATE_TIME_DB_AUX);
        ret.setDataHoraCadastro(Constantes.DATE_TIME_DB_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0118R1ComplexType
     * 
     */
    protected DDA0118R1ComplexType retornarDDA0118R1ComplexType() {
        DDA0118R1ComplexType ret = new DDA0118R1ComplexType();
        ret.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        ret.setDtHrDDA(retornarXMLGregorianCalendar());
        ret.setNumIdentcBaixaEft(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumRefAtlBaixaEft(Constantes.BIG_INTEGER_1_AUX);
        ret.setNumSeqAtlzBaixaEft(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0115R2ComplexType
     * 
     */
    protected DDA0115R2ComplexType retornarDDA0115R2ComplexType() {
        DDA0115R2ComplexType ret = new DDA0115R2ComplexType();
        ret.setSitTitPgto(Constantes.STRING_NUMERO_1);
        ret.setNumIdentcTit(Constantes.BIG_INTEGER_1_AUX);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return XMLGregorianCalendar
     * 
     */
    private XMLGregorianCalendar retornarXMLGregorianCalendar() {
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(Constantes.DATE_AUX);
        XMLGregorianCalendar date2 = null;
        try {
            date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return date2;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABoletoGrupoCalculo>
     * 
     */
    protected List<MensagemDDABoletoGrupoCalculo> listarMensagemDDABoletoGrupoCalculo() {
        List<MensagemDDABoletoGrupoCalculo> ret = new ArrayList<MensagemDDABoletoGrupoCalculo>();
        ret.add(retornarMensagemDDABoletoGrupoCalculo());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABoletoNotaFiscal>
     * 
     */
    protected List<MensagemDDABoletoNotaFiscal> listarMensagemDDABoletoNotaFiscal() {
        List<MensagemDDABoletoNotaFiscal> ret = new ArrayList<MensagemDDABoletoNotaFiscal>();
        ret.add(retornarMensagemDDABoletoNotaFiscal());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABaixaOperacional>
     */
    protected List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacional() {
        List<MensagemDDABaixaOperacional> ret = new ArrayList<MensagemDDABaixaOperacional>();
        ret.add(retornarMensagemDDABaixaOperacional());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDAPagador>
     */
    protected List<MensagemDDAPagador> listarMensagemDDAPagador() {
        List<MensagemDDAPagador> ret = new ArrayList<MensagemDDAPagador>();
        ret.add(retornarMensagemDDAPagador());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<String>
     */
    protected List<String> listarStrings() {
        List<String> ret = new ArrayList<String>();
        ret.add(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<Object[]>
     */
    protected List<Object[]> listarObjectArray() {
        List<Object[]> ret = new ArrayList<Object[]>();
        Object[] array = { 1, 2, 3 };
        ret.add(array);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDATerceiroAut>
     */
    protected List<MensagemDDATerceiroAut> listarMensagemDDATerceiroAut() {
        List<MensagemDDATerceiroAut> ret = new ArrayList<MensagemDDATerceiroAut>();
        ret.add(retornarMensagemDDATerceiroAut());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABoletoTextoInfo>
     * 
     */
    protected List<MensagemDDABoletoTextoInfo> listarMensagemDDABoletoTextoInfo() {
        List<MensagemDDABoletoTextoInfo> ret = new ArrayList<MensagemDDABoletoTextoInfo>();
        ret.add(retornarMensagemDDABoletoTextoInfo());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABeneficiarioConvenio>
     * 
     */
    protected List<MensagemDDABeneficiarioConvenio> listarMensagemDDABeneficiarioConvenio() {
        List<MensagemDDABeneficiarioConvenio> ret = new ArrayList<MensagemDDABeneficiarioConvenio>();
        ret.add(retornarMensagemDDABeneficiarioConvenio());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABaixaEfetiva>
     */
    protected List<MensagemDDABaixaEfetiva> listarMensagemDDABaixaEfetiva() {
        List<MensagemDDABaixaEfetiva> ret = new ArrayList<MensagemDDABaixaEfetiva>();
        ret.add(retornarMensagemDDABaixaEfetiva());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABoleto>
     */
    protected List<MensagemDDABoleto> listarMensagemDDABoleto() {
        List<MensagemDDABoleto> ret = new ArrayList<MensagemDDABoleto>();
        ret.add(retornarMensagemDDABoleto());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDAConsultaBoleto>
     */
    protected List<MensagemDDAConsultaBoleto> listarMensagemDDAConsultaBoleto() {
        List<MensagemDDAConsultaBoleto> ret = new ArrayList<MensagemDDAConsultaBoleto>();
        ret.add(retornarMensagemDDAConsultaBoleto());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<LogDetRecebimentoArquivoDDA>
     */
    protected List<LogDetRecebimentoArquivoDDA> listarLogDetRecebimentoArquivoDDA() {
        List<LogDetRecebimentoArquivoDDA> ret = new ArrayList<LogDetRecebimentoArquivoDDA>();
        ret.add(retornarLogDetRecebimentoArquivoDDA());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<Long>
     */
    protected List<Long> listarLong() {
        List<Long> ret = new ArrayList<Long>();
        ret.add(Constantes.LONG_UM);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDAPagadorAgregado>
     */
    protected List<MensagemDDAPagadorAgregado> listarMensagemDDAPagadorAgregado() {
        List<MensagemDDAPagadorAgregado> ret = new ArrayList<MensagemDDAPagadorAgregado>();
        ret.add(retornarMensagemDDAPagadorAgregado());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDAPagadorConta>
     */
    protected List<MensagemDDAPagadorConta> listarMensagemDDAPagadorConta() {
        List<MensagemDDAPagadorConta> ret = new ArrayList<MensagemDDAPagadorConta>();
        ret.add(retornarMensagemDDAPagadorConta());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABeneficiarioRepresentante>
     * 
     */
    protected List<MensagemDDABeneficiarioRepresentante> listarMensagemDDABeneficiarioRepresentante() {
        List<MensagemDDABeneficiarioRepresentante> ret = new ArrayList<MensagemDDABeneficiarioRepresentante>();
        ret.add(retornarMensagemDDABeneficiarioRepresentante());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<BeneficiarioInstituicao>
     * 
     */
    protected List<BeneficiarioInstituicao> listarBeneficiarioInstituicao() {
        List<BeneficiarioInstituicao> ret = new ArrayList<BeneficiarioInstituicao>();
        ret.add(retornarBeneficiarioInstituicao());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<BoletoDDABaixaOper>
     * 
     */
    protected List<BoletoDDABaixaOper> listarBoletoDDABaixaOper() {
        List<BoletoDDABaixaOper> ret = new ArrayList<BoletoDDABaixaOper>();
        ret.add(retornarBoletoDDABaixaOper());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<DDABeneficiarioCooperativa>
     * 
     */
    protected List<DDABeneficiarioCooperativa> listarDDABeneficiarioCooperativa() {
        List<DDABeneficiarioCooperativa> ret = new ArrayList<DDABeneficiarioCooperativa>();
        ret.add(retornarDDABeneficiarioCooperativa());
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return List<DDAOperacao>
     * 
     */
    protected List<DDAOperacao> listarDDAOperacao() {
        List<DDAOperacao> ret = new ArrayList<DDAOperacao>();
        ret.add(retornarDDAOperacao());
        return ret;
    }
}
