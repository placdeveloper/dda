/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor
 * Arquivo:         ConversorMensagemBoletoDDA.java
 * Data Criação:    Sep 30, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCarteiraCobrancaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDAGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101RR2CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102RR2CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110.GrupoADDA110BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110.GrupoADDA110BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110.GrupoADDA110CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110.GrupoADDA110RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.GrupoDDA0101R2CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102R2CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.GrupoDDA0106R1TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.DDA0110R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.GrupoDDA0110R1BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.GrupoDDA0110R1BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.GrupoDDA0110R1CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.GrupoDDA0127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * ConversorBoletoDDA
 * 
 * @author George.santos
 */
public final class ConversorBoletoDDA {

    /** * */
    private ConversorBoletoDDA() {

    }

    /**
     * Método responsável por fazer a conversao do Boleto
     * 
     * @param conteudo
     * @return BoletoDDA
     * 
     */
    public static BoletoDDA converter(ConteudoMsgRecebida conteudo) {
        return converter(conteudo, null);
    }

    /**
     * Método responsável por fazer a conversao do Boleto
     * 
     * @param conteudo
     * @param mensagemBoleto
     * @return BoletoDDA
     * 
     */
    public static BoletoDDA converter(ConteudoMsgRecebida conteudo, MensagemDDABoleto mensagemBoleto) {
        BoletoDDA boletoDDA = null;
        if (conteudo instanceof DDA0101R1ComplexType) {
            boletoDDA = converter((DDA0101R1ComplexType) conteudo, mensagemBoleto);
        } else if (conteudo instanceof DDA0101R2ComplexType) {
            boletoDDA = converter((DDA0101R2ComplexType) conteudo);
        } else if (conteudo instanceof GrupoADDA101RETTitActoComplexType) {
            boletoDDA = converter((GrupoADDA101RETTitActoComplexType) conteudo, mensagemBoleto);
        } else if (conteudo instanceof GrupoADDA101RR2TitComplexType) {
            boletoDDA = converter((GrupoADDA101RR2TitComplexType) conteudo);
        } else if (conteudo instanceof GrupoDDA0106R1TitComplexType) {
            boletoDDA = converter((GrupoDDA0106R1TitComplexType) conteudo);
        } else if (conteudo instanceof GrupoADDA106TitComplexType) {
            boletoDDA = converter((GrupoADDA106TitComplexType) conteudo);
        } else if (conteudo instanceof GrupoDDA0127TitComplexType) {
            boletoDDA = converter((GrupoDDA0127TitComplexType) conteudo);
        } else if (conteudo instanceof GrupoADDA127TitComplexType) {
            boletoDDA = converter((GrupoADDA127TitComplexType) conteudo);
        } else if (conteudo instanceof DDA0110R1ComplexType) {
            boletoDDA = converterDDA0110((DDA0110R1ComplexType) conteudo);
        } else if (conteudo instanceof GrupoADDA110RETTitActoComplexType) {
            boletoDDA = converterADDA110((GrupoADDA110RETTitActoComplexType) conteudo);
        }

        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    public static BoletoDDA merge(ConteudoMsgRecebida conteudo, BoletoDDA boletoDDA) {
        return merge(conteudo, null, boletoDDA);
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param mensagemDDABoleto
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    public static BoletoDDA merge(ConteudoMsgRecebida conteudo, MensagemDDABoleto mensagemDDABoleto, BoletoDDA boletoDDA) {
        BoletoDDA boletoDDARetorno = null;
        if (conteudo instanceof DDA0110R1ComplexType) {
            boletoDDARetorno = merge((DDA0110R1ComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof DDA0102R2ComplexType) {
            boletoDDARetorno = merge((DDA0102R2ComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof GrupoADDA102RR2TitComplexType) {
            boletoDDARetorno = merge((GrupoADDA102RR2TitComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof DDA0102R1ComplexType) {
            boletoDDARetorno = merge((DDA0102R1ComplexType) conteudo, mensagemDDABoleto, boletoDDA);
        } else if (conteudo instanceof GrupoADDA102RETTitActoComplexType) {
            boletoDDARetorno = merge((GrupoADDA102RETTitActoComplexType) conteudo, mensagemDDABoleto, boletoDDA);
        } else if (conteudo instanceof GrupoDDA0106R1TitComplexType) {
            boletoDDARetorno = merge((GrupoDDA0106R1TitComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof GrupoADDA106TitComplexType) {
            boletoDDARetorno = merge((GrupoADDA106TitComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof GrupoDDA0127TitComplexType) {
            boletoDDARetorno = merge((GrupoDDA0127TitComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof GrupoADDA127TitComplexType) {
            boletoDDARetorno = merge((GrupoADDA127TitComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof GrupoADDA110RETTitActoComplexType) {
            boletoDDARetorno = merge((GrupoADDA110RETTitActoComplexType) conteudo, boletoDDA);
        }

        validaProcessamento(boletoDDA, boletoDDARetorno);
        return boletoDDARetorno;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param mensagemBoleto
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converter(DDA0101R1ComplexType boletoRetorno, MensagemDDABoleto mensagemBoleto) {
        return converter(mensagemBoleto, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatarioPrincipal());
    }

    /**
     * Método responsável por criar a entidade BoletoDDA, utilizado no SWS motor de carga para que no envio da 101 para CIP ja grave na DDA.BoletoDDA
     * 
     * 
     * @param boletoRetorno
     * @param mensagemBoleto
     * @return BoletoDDA
     * 
     */
    public static BoletoDDA converterBoletoDDA(MensagemDDABoleto mensagemBoleto) {
        return converter(mensagemBoleto, null, null, null, null);
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param mensagemBoleto
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converter(GrupoADDA101RETTitActoComplexType boletoRetorno, MensagemDDABoleto mensagemBoleto) {
        return converter(mensagemBoleto, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatarioPrincipal());
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param mensagemBoleto
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(DDA0102R1ComplexType boletoRetorno, MensagemDDABoleto mensagemBoleto, BoletoDDA boletoDDA) {
        return merge(boletoDDA, mensagemBoleto, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatarioPrincipal());
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param mensagemBoleto
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(GrupoADDA102RETTitActoComplexType boletoRetorno, MensagemDDABoleto mensagemBoleto, BoletoDDA boletoDDA) {
        return merge(boletoDDA, mensagemBoleto, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatarioPrincipal());
    }

    /**
     * Método responsável por fazer a conversao do boleto quando recebido uma DDA0101R1ComplexType (mensagem) ou GrupoADDA101RETTitActoComplexType (Arquivo) -
     * INCLUSÃO
     * 
     * @param msgPagador
     * @param numIdentcPagdr
     * @param numRefAtlCadPagdr
     * @param numSeqAtlzCadPagdr
     * @param qtdAdesPagdrDDA
     * @return PagadorDDA
     * 
     */
    private static BoletoDDA converter(MensagemDDABoleto mensagemDDABoleto, BigInteger numIdentificacaoBoleto, BigInteger numReferenciaBoleto, BigInteger numSeqBoleto,
            String codISPBPartDestinatario) {
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());
        setBoletoDDAIdentificacao(boletoDDA, numIdentificacaoBoleto, numReferenciaBoleto, numSeqBoleto, codISPBPartDestinatario, Constantes.BANCOOB);

        setBoletoDDABeneficiario(boletoDDA, mensagemDDABoleto.getCodTipoPessoaBeneficiario(), mensagemDDABoleto.getNumCpfCnpjBeneficiario(),
                mensagemDDABoleto.getNomeBeneficiario(), mensagemDDABoleto.getNomeFantasiaBeneficiario(), mensagemDDABoleto.getDescLogradouroBeneficiario(),
                mensagemDDABoleto.getDescCidadeBeneficiario(), mensagemDDABoleto.getSiglaUfBeneficiario(), mensagemDDABoleto.getNumCepBeneficiario());

        setBoletoDDABeneficiarioFinal(boletoDDA, mensagemDDABoleto.getCodTipoPessoaBeneficiarioFinal(), mensagemDDABoleto.getNumCpfCnpjBeneficiarioFinal(),
                mensagemDDABoleto.getNomeBeneficiarioFinal(), mensagemDDABoleto.getNomeFantasiaBeneficiarioFinal());

        setBoletoDDAPagador(boletoDDA, mensagemDDABoleto.getCodTipoPessoaPagador(), mensagemDDABoleto.getNumCpfCnpjPagador(), mensagemDDABoleto.getNomePagador(),
                mensagemDDABoleto.getNomeFantasiaPagador(), mensagemDDABoleto.getDescLogradouroPagador(), mensagemDDABoleto.getDescCidadePagador(),
                mensagemDDABoleto.getSiglaUfPagador(), mensagemDDABoleto.getNumCepPagador());

        setBoletoDDASacadorAvalista(boletoDDA, mensagemDDABoleto.getCodTipoPessoaDDAAvalista(), mensagemDDABoleto.getNumCpfCnpjAvalista(), mensagemDDABoleto.getNomeAvalista());

        setBoletoDDADocumentacao(boletoDDA, mensagemDDABoleto.getIdCarteira(), mensagemDDABoleto.getIdEspecieDocumento(), mensagemDDABoleto.getNumDocumento(),
                mensagemDDABoleto.getCodTipoPagamento(), mensagemDDABoleto.getNumParcela(), mensagemDDABoleto.getQtdTotalParcela(), mensagemDDABoleto.getBolTituloNegociado());

        setBoletoDDAPagamento(boletoDDA, mensagemDDABoleto.getDataVencimento(), mensagemDDABoleto.getValorBoleto(), mensagemDDABoleto.getNumDiasProtesto(),
                mensagemDDABoleto.getDataLimitePagamento(), mensagemDDABoleto.getBolBloqueioPagamento(), mensagemDDABoleto.getValorAbatimento(),
                mensagemDDABoleto.getQtdPagamentoParcial());

        setBoletoDDADadosTitulo(boletoDDA, mensagemDDABoleto.getCodMoeda(), mensagemDDABoleto.getNumNossoNumero(), mensagemDDABoleto.getNumCodigoBarra(),
                mensagemDDABoleto.getNumLinhaDigitavel(), mensagemDDABoleto.getDataEmissao(), mensagemDDABoleto.isBolPagamentoParcial());

        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoJuros())) {
            TipoJuros tipoJuros = new TipoJuros();
            tipoJuros.setId(mensagemDDABoleto.getCodTipoJuros().shortValue());
            boletoDDA.setTipoJuros(tipoJuros);
            
            boletoDDA.setDataJuros(ObjectUtil.isNull(mensagemDDABoleto.getDataJuros()) ? null : mensagemDDABoleto.getDataJuros());
            boletoDDA.setValorPercentualJuros(mensagemDDABoleto.getValorPercentualJuros());
        } else {
            jurosIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoMulta())) {
            TipoMulta tipoMulta = new TipoMulta();
            tipoMulta.setId(mensagemDDABoleto.getCodTipoMulta().shortValue());
            boletoDDA.setTipoMulta(tipoMulta);
            
            boletoDDA.setDataMulta(ObjectUtil.isNull(mensagemDDABoleto.getDataMulta()) ? null : mensagemDDABoleto.getDataMulta());
            boletoDDA.setValorPercentualMulta(mensagemDDABoleto.getValorPercentualMulta());
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto1())) {
            setBoletoDDADesconto1(boletoDDA, ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto1()) ? null : mensagemDDABoleto.getDataDesconto1(),
                    mensagemDDABoleto.getCodTipoDesconto1(), mensagemDDABoleto.getValorPercentualDesconto1());

            if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto2()) && !ObjectUtil.isEmpty(mensagemDDABoleto.getValorPercentualDesconto2())) {
                setBoletoDDADesconto2(boletoDDA, ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto2()) ? null : mensagemDDABoleto.getDataDesconto2(),
                        mensagemDDABoleto.getCodTipoDesconto2(), mensagemDDABoleto.getValorPercentualDesconto2());
            }

            if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto3()) && !ObjectUtil.isEmpty(mensagemDDABoleto.getValorPercentualDesconto3())) {
                setBoletoDDADesconto3(boletoDDA, ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto3()) ? null : mensagemDDABoleto.getDataDesconto3(),
                        mensagemDDABoleto.getCodTipoDesconto3(), mensagemDDABoleto.getValorPercentualDesconto3());
            }
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(mensagemDDABoleto.getCodIndicadorValorMinimo(), mensagemDDABoleto.getValorMinimo(), mensagemDDABoleto.getCodIndicadorValorMaximo(),
                mensagemDDABoleto.getValorMaximo(), mensagemDDABoleto.getCodTipoModeloCalculo(), mensagemDDABoleto.getCodAutorizacaoValorDivergente(), boletoDDA);

        setBoletoDDAGrupoCalculo(mensagemDDABoleto, boletoDDA);

        setBoletoDDAAceite(boletoDDA, 0);

        boletoDDA.setCodSituacaoBoleto(SituacaoBoleto.ABERTO);

        return boletoDDA;
    }

    /**
     * Método responsável por fazer a conversao do boleto quando recebido uma DDA0101R1ComplexType (mensagem) ou GrupoADDA101RETTitActoComplexType (Arquivo) -
     * INCLUSÃO
     * 
     * @param msgPagador
     * @param numIdentcPagdr
     * @param numRefAtlCadPagdr
     * @param numSeqAtlzCadPagdr
     * @param qtdAdesPagdrDDA
     * @return PagadorDDA
     * 
     */
    private static BoletoDDA merge(BoletoDDA boletoDDA, MensagemDDABoleto mensagemDDABoleto, BigInteger numIdentificacaoBoleto, BigInteger numReferenciaBoleto,
            BigInteger numSeqBoleto, String codISPBPartDestinatario) {
        setBoletoDDAIdentificacao(boletoDDA, numIdentificacaoBoleto, numReferenciaBoleto, numSeqBoleto, codISPBPartDestinatario);

        setBoletoDDABeneficiario(boletoDDA, mensagemDDABoleto.getCodTipoPessoaBeneficiario(), mensagemDDABoleto.getNumCpfCnpjBeneficiario(),
                mensagemDDABoleto.getNomeBeneficiario(), mensagemDDABoleto.getNomeFantasiaBeneficiario(), mensagemDDABoleto.getDescLogradouroBeneficiario(),
                mensagemDDABoleto.getDescCidadeBeneficiario(), mensagemDDABoleto.getSiglaUfBeneficiario(), mensagemDDABoleto.getNumCepBeneficiario());

        setBoletoDDABeneficiarioFinal(boletoDDA, mensagemDDABoleto.getCodTipoPessoaBeneficiarioFinal(), mensagemDDABoleto.getNumCpfCnpjBeneficiarioFinal(),
                mensagemDDABoleto.getNomeBeneficiarioFinal(), mensagemDDABoleto.getNomeFantasiaBeneficiarioFinal());

        setBoletoDDAPagador(boletoDDA, mensagemDDABoleto.getCodTipoPessoaPagador(), mensagemDDABoleto.getNumCpfCnpjPagador(), mensagemDDABoleto.getNomePagador(),
                mensagemDDABoleto.getNomeFantasiaPagador(), mensagemDDABoleto.getDescLogradouroPagador(), mensagemDDABoleto.getDescCidadePagador(),
                mensagemDDABoleto.getSiglaUfPagador(), mensagemDDABoleto.getNumCepPagador());

        setBoletoDDASacadorAvalista(boletoDDA, mensagemDDABoleto.getCodTipoPessoaDDAAvalista(), mensagemDDABoleto.getNumCpfCnpjAvalista(), mensagemDDABoleto.getNomeAvalista());

        setBoletoDDADocumentacao(boletoDDA, mensagemDDABoleto.getIdCarteira(), mensagemDDABoleto.getIdEspecieDocumento(), mensagemDDABoleto.getNumDocumento(),
                mensagemDDABoleto.getCodTipoPagamento(), mensagemDDABoleto.getNumParcela(), mensagemDDABoleto.getQtdTotalParcela(), mensagemDDABoleto.getBolTituloNegociado());

        validaAlteracoesBoletoR1(mensagemDDABoleto.getDataVencimento(), mensagemDDABoleto.getDataLimitePagamento(), mensagemDDABoleto.getValorBoleto(), boletoDDA);

        setBoletoDDAPagamento(boletoDDA, mensagemDDABoleto.getDataVencimento(), mensagemDDABoleto.getValorBoleto(), mensagemDDABoleto.getNumDiasProtesto(),
                mensagemDDABoleto.getDataLimitePagamento(), mensagemDDABoleto.getBolBloqueioPagamento(), mensagemDDABoleto.getValorAbatimento(),
                mensagemDDABoleto.getQtdPagamentoParcial());

        setBoletoDDAValorRecebido(mensagemDDABoleto.getCodIndicadorValorMinimo(), mensagemDDABoleto.getValorMinimo(), mensagemDDABoleto.getCodIndicadorValorMaximo(),
                mensagemDDABoleto.getValorMaximo(), mensagemDDABoleto.getCodTipoModeloCalculo(), mensagemDDABoleto.getCodAutorizacaoValorDivergente(), boletoDDA);

        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoJuros())) {
            TipoJuros tipoJuros = new TipoJuros();
            tipoJuros.setId(mensagemDDABoleto.getCodTipoJuros().shortValue());
            boletoDDA.setTipoJuros(tipoJuros);
            
            boletoDDA.setDataJuros(ObjectUtil.isNull(mensagemDDABoleto.getDataJuros()) ? null : mensagemDDABoleto.getDataJuros());
            boletoDDA.setValorPercentualJuros(mensagemDDABoleto.getValorPercentualJuros());
        } else {
            jurosIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoMulta())) {
            TipoMulta tipoMulta = new TipoMulta();
            tipoMulta.setId(mensagemDDABoleto.getCodTipoMulta().shortValue());
            boletoDDA.setTipoMulta(tipoMulta);
            
            boletoDDA.setDataMulta(ObjectUtil.isNull(mensagemDDABoleto.getDataMulta()) ? null : mensagemDDABoleto.getDataMulta());
            boletoDDA.setValorPercentualMulta(mensagemDDABoleto.getValorPercentualMulta());
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto1())) {
            setBoletoDDADesconto1(boletoDDA, ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto1()) ? null : mensagemDDABoleto.getDataDesconto1(),
                    mensagemDDABoleto.getCodTipoDesconto1(), mensagemDDABoleto.getValorPercentualDesconto1());

            if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto2()) && !ObjectUtil.isEmpty(mensagemDDABoleto.getValorPercentualDesconto2())) {
                setBoletoDDADesconto2(boletoDDA, ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto2()) ? null : mensagemDDABoleto.getDataDesconto2(),
                        mensagemDDABoleto.getCodTipoDesconto2(), mensagemDDABoleto.getValorPercentualDesconto2());
            }

            if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto2()) && !ObjectUtil.isEmpty(mensagemDDABoleto.getValorPercentualDesconto2())) {
                setBoletoDDADesconto3(boletoDDA, ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto3()) ? null : mensagemDDABoleto.getDataDesconto3(),
                        mensagemDDABoleto.getCodTipoDesconto3(), mensagemDDABoleto.getValorPercentualDesconto3());
            }
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAGrupoCalculo(mensagemDDABoleto, boletoDDA);

        boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());

        return boletoDDA;
    }

    /**
     * Método responsável por fazer a alteraçao de acordo com a mensagem recebida pela cip R2
     * 
     * @param boletoRetorno
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converter(DDA0101R2ComplexType boletoRetorno) {
        BoletoDDA boletoDDA = new BoletoDDA();

        setBoletoDDAIdentificacao(boletoDDA, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatario(), boletoRetorno.getCodPartDestinatario());

        setBoletoDDABeneficiario(boletoDDA, boletoRetorno.getTpPessoaBenfcrioOr(), boletoRetorno.getCNPJCPFBenfcrioOr(), boletoRetorno.getNomRzSocBenfcrioOr(),
                boletoRetorno.getNomFantsBenfcrioOr(), boletoRetorno.getLogradBenfcrioOr(), boletoRetorno.getCidBenfcrioOr(), boletoRetorno.getUFBenfcrioOr(),
                boletoRetorno.getCEPBenfcrioOr());

        setBoletoDDABeneficiarioFinal(boletoDDA, boletoRetorno.getTpPessoaBenfcrioFinl(), boletoRetorno.getCNPJCPFBenfcrioFinl(), boletoRetorno.getNomRzSocBenfcrioFinl(),
                boletoRetorno.getNomFantsBenfcrioFinl());

        setBoletoDDAPagador(boletoDDA, boletoRetorno.getTpPessoaPagdr(), boletoRetorno.getCNPJCPFPagdr(), boletoRetorno.getNomRzSocPagdr(), boletoRetorno.getNomFantsPagdr(),
                boletoRetorno.getLogradPagdr(), boletoRetorno.getCidPagdr(), boletoRetorno.getUFPagdr(), boletoRetorno.getCEPPagdr());

        setBoletoDDASacadorAvalista(boletoDDA, boletoRetorno.getTpIdentcSacdrAvalst(), boletoRetorno.getIdentcSacdrAvalst(), boletoRetorno.getNomRzSocSacdrAvalst());

        setBoletoDDADocumentacao(boletoDDA, boletoRetorno.getCodCartTit(), boletoRetorno.getCodEspTit(), boletoRetorno.getNumDocTit(), boletoRetorno.getTpPgtoTit(),
                boletoRetorno.getNumParcl(), boletoRetorno.getQtdTotParcl(), boletoRetorno.getIndrTitNegcd());

        setBoletoDDAPagamento(boletoDDA, boletoRetorno.getDtVencTit(), boletoRetorno.getVlrTit(), boletoRetorno.getQtdDiaPrott(), boletoRetorno.getDtLimPgtoTit(),
                boletoRetorno.getIndrBloqPgto(), boletoRetorno.getVlrAbattTit(), boletoRetorno.getQtdPgtoParcl());

        setBoletoDDADadosTitulo(boletoDDA, boletoRetorno.getCodMoedaCNAB(), boletoRetorno.getIdentdNossoNum(), boletoRetorno.getNumCodBarras(), boletoRetorno.getNumLinhaDigtvl(),
                boletoRetorno.getDtEmsTit(), boletoRetorno.getIndrPgtoParcl());

        // Desnormalizada entidade BoletoDDAJuros agora atributo na BoletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0101R2JurosTit())) {
            setBoletoDDAJuros(boletoRetorno.getGrupoDDA0101R2JurosTit().getDtJurosTit(), boletoRetorno.getGrupoDDA0101R2JurosTit().getCodJurosTit(),
                    boletoRetorno.getGrupoDDA0101R2JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada entidade BoletoDDAMulta agora atributo na BoletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0101R2MultaTit())) {
            setBoletoDDAMulta(boletoRetorno.getGrupoDDA0101R2MultaTit().getDtMultaTit(), boletoRetorno.getGrupoDDA0101R2MultaTit().getCodMultaTit(),
                    boletoRetorno.getGrupoDDA0101R2MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0101R2DesctTit())) {
            converterBoletoDDADesconto(boletoRetorno, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(boletoRetorno.getTpVlrPercMinTit(), boletoRetorno.getVlrPercMinTit(), boletoRetorno.getTpVlrPercMaxTit(), boletoRetorno.getVlrPercMaxTit(),
                boletoRetorno.getTpModlCalc(), boletoRetorno.getTpAutcRecbtVlrDivgte(), boletoDDA);

        converterBoletoDDAGrupoCalculo(boletoRetorno, boletoDDA);

        boletoDDA.setValorSaldoPagamento(boletoRetorno.getVlrSldTotAtlPgtoTit());

        boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getDtHrSit()));

        boletoDDA.setCodSituacaoBoletoPagamento(boletoRetorno.getSitTitPgto());

        setBoletoDDAAceite(boletoDDA, 0);

        boletoDDA.setCodSituacaoBoleto(SituacaoBoleto.ABERTO);

        return boletoDDA;
    }

    /**
     * Método responsável por fazer a alteraçao de acordo com a mensagem recebida pela cip R2
     * 
     * @param boletoRetorno
     * @return BoletoDDA
     * 
     */

    private static BoletoDDA converter(GrupoADDA101RR2TitComplexType boletoRetorno) {
        BoletoDDA boletoDDA = new BoletoDDA();

        setBoletoDDAIdentificacao(boletoDDA, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatario(), boletoRetorno.getCodPartDestinatario());

        setBoletoDDABeneficiario(boletoDDA, boletoRetorno.getTpPessoaBenfcrioOr(), boletoRetorno.getCNPJCPFBenfcrioOr(), boletoRetorno.getNomRzSocBenfcrioOr(),
                boletoRetorno.getNomFantsBenfcrioOr(), boletoRetorno.getLogradBenfcrioOr(), boletoRetorno.getCidBenfcrioOr(), boletoRetorno.getUFBenfcrioOr(),
                boletoRetorno.getCEPBenfcrioOr());

        setBoletoDDABeneficiarioFinal(boletoDDA, boletoRetorno.getTpPessoaBenfcrioFinl(), boletoRetorno.getCNPJCPFBenfcrioFinl(), boletoRetorno.getNomRzSocBenfcrioFinl(),
                boletoRetorno.getNomFantsBenfcrioFinl());

        setBoletoDDAPagador(boletoDDA, boletoRetorno.getTpPessoaPagdr(), boletoRetorno.getCNPJCPFPagdr(), boletoRetorno.getNomRzSocPagdr(), boletoRetorno.getNomFantsPagdr(),
                boletoRetorno.getLogradPagdr(), boletoRetorno.getCidPagdr(), boletoRetorno.getUFPagdr(), boletoRetorno.getCEPPagdr());

        setBoletoDDASacadorAvalista(boletoDDA, boletoRetorno.getTpIdentcSacdrAvalst(), boletoRetorno.getIdentcSacdrAvalst(), boletoRetorno.getNomRzSocSacdrAvalst());

        setBoletoDDADocumentacao(boletoDDA, boletoRetorno.getCodCartTit(), boletoRetorno.getCodEspTit(), boletoRetorno.getNumDocTit(), boletoRetorno.getTpPgtoTit(),
                boletoRetorno.getNumParcl(), boletoRetorno.getQtdTotParcl(), boletoRetorno.getIndrTitNegcd());

        setBoletoDDAPagamento(boletoDDA, boletoRetorno.getDtVencTit(), boletoRetorno.getVlrTit(), boletoRetorno.getQtdDiaPrott(), boletoRetorno.getDtLimPgtoTit(),
                boletoRetorno.getIndrBloqPgto(), boletoRetorno.getVlrAbattTit(), boletoRetorno.getQtdPgtoParcl());

        setBoletoDDADadosTitulo(boletoDDA, boletoRetorno.getCodMoedaCNAB(), boletoRetorno.getIdentdNossoNum(), boletoRetorno.getNumCodBarras(), boletoRetorno.getNumLinhaDigtvl(),
                boletoRetorno.getDtEmsTit(), boletoRetorno.getIndrPgtoParcl(), boletoRetorno.getTpPgtoTit());

        // Desnormalizada BoleteoDDAJuros, agora atributo na BoletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA101RR2JurosTit())) {
            setBoletoDDAJuros(boletoRetorno.getGrupoADDA101RR2JurosTit().getDtJurosTit(), boletoRetorno.getGrupoADDA101RR2JurosTit().getCodJurosTit(),
                    boletoRetorno.getGrupoADDA101RR2JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada BoleteoDDAMulta, agora atributo na BoletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA101RR2MultaTit())) {
            setBoletoDDAMulta(boletoRetorno.getGrupoADDA101RR2MultaTit().getDtMultaTit(), boletoRetorno.getGrupoADDA101RR2MultaTit().getCodMultaTit(),
                    boletoRetorno.getGrupoADDA101RR2MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA101RR2DesctTit())) {
            converterBoletoDDADesconto(boletoRetorno, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }


        setBoletoDDAValorRecebido(boletoRetorno.getTpVlrPercMinTit(), boletoRetorno.getVlrPercMinTit(), boletoRetorno.getTpVlrPercMaxTit(), boletoRetorno.getVlrPercMaxTit(),
                boletoRetorno.getTpModlCalc(), boletoRetorno.getTpAutcRecbtVlrDivgte(), boletoDDA);

        converterBoletoDDAGrupoCalculo(boletoRetorno, boletoDDA);

        boletoDDA.setValorSaldoPagamento(boletoRetorno.getVlrSldTotAtlPgtoTit());

        boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getDtHrSit()));

        boletoDDA.setCodSituacaoBoletoPagamento(boletoRetorno.getSitTitPgto());

        boletoDDA.setCodSituacaoBoleto(SituacaoBoleto.ABERTO);

        setBoletoDDAAceite(boletoDDA, 0);

        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converter(GrupoDDA0106R1TitComplexType boletoRetorno) {
        BoletoDDA boletoDDA = new BoletoDDA();

        setBoletoDDAIdentificacao(boletoDDA, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatario(), boletoRetorno.getCodPartDestinatario());

        setBoletoDDABeneficiario(boletoDDA, boletoRetorno.getTpPessoaBenfcrioOr(), boletoRetorno.getCNPJCPFBenfcrioOr(), boletoRetorno.getNomRzSocBenfcrioOr(),
                boletoRetorno.getNomFantsBenfcrioOr(), boletoRetorno.getLogradBenfcrioOr(), boletoRetorno.getCidBenfcrioOr(), boletoRetorno.getUFBenfcrioOr(),
                boletoRetorno.getCEPBenfcrioOr());

        setBoletoDDABeneficiarioFinal(boletoDDA, boletoRetorno.getTpPessoaBenfcrioFinl(), boletoRetorno.getCNPJCPFBenfcrioFinl(), boletoRetorno.getNomRzSocBenfcrioFinl(),
                boletoRetorno.getNomFantsBenfcrioFinl());

        setBoletoDDAPagador(boletoDDA, boletoRetorno.getTpPessoaPagdr(), boletoRetorno.getCNPJCPFPagdr(), boletoRetorno.getNomRzSocPagdr(), boletoRetorno.getNomFantsPagdr(),
                boletoRetorno.getLogradPagdr(), boletoRetorno.getCidPagdr(), boletoRetorno.getUFPagdr(), boletoRetorno.getCEPPagdr());

        setBoletoDDASacadorAvalista(boletoDDA, boletoRetorno.getTpIdentcSacdrAvalst(), boletoRetorno.getIdentcSacdrAvalst(), boletoRetorno.getNomRzSocSacdrAvalst());

        setBoletoDDADocumentacao(boletoDDA, boletoRetorno.getCodCartTit(), boletoRetorno.getCodEspTit(), boletoRetorno.getNumDocTit(), boletoRetorno.getTpPgtoTit(),
                boletoRetorno.getNumParcl(), boletoRetorno.getQtdTotParcl(), boletoRetorno.getIndrTitNegcd());

        setBoletoDDAPagamento(boletoDDA, boletoRetorno.getDtVencTit(), boletoRetorno.getVlrTit(), boletoRetorno.getQtdDiaPrott(), boletoRetorno.getDtLimPgtoTit(),
                boletoRetorno.getIndrBloqPgto(), boletoRetorno.getVlrAbattTit(), boletoRetorno.getQtdPgtoParcl());

        setBoletoDDADadosTitulo(boletoDDA, boletoRetorno.getCodMoedaCNAB(), boletoRetorno.getIdentdNossoNum(), boletoRetorno.getNumCodBarras(), boletoRetorno.getNumLinhaDigtvl(),
                boletoRetorno.getDtEmsTit(), boletoRetorno.getIndrPgtoParcl(), boletoRetorno.getTpPgtoTit());

        // Desnormalizada BoletoDDAJuros, agora atributo na BoletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0106R1JurosTit())) {
            setBoletoDDAJuros(boletoRetorno.getGrupoDDA0106R1JurosTit().getDtJurosTit(), boletoRetorno.getGrupoDDA0106R1JurosTit().getCodJurosTit(),
                    boletoRetorno.getGrupoDDA0106R1JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada BoletoDDAMulta, agora atributo na BoletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0106R1MultaTit())) {
            setBoletoDDAMulta(boletoRetorno.getGrupoDDA0106R1MultaTit().getDtMultaTit(), boletoRetorno.getGrupoDDA0106R1MultaTit().getCodMultaTit(),
                    boletoRetorno.getGrupoDDA0106R1MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0106R1DesctTit())) {
            converterBoletoDDADesconto(boletoRetorno, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(boletoRetorno.getTpVlrPercMinTit(), boletoRetorno.getVlrPercMinTit(), boletoRetorno.getTpVlrPercMaxTit(), boletoRetorno.getVlrPercMaxTit(),
                boletoRetorno.getTpModlCalc(), boletoRetorno.getTpAutcRecbtVlrDivgte(), boletoDDA);

        converterBoletoDDAGrupoCalculo(boletoRetorno, boletoDDA);

        setBoletoDDASaldoPagamento(boletoDDA, boletoRetorno.getDtHrSit(), boletoRetorno.getQtdPgtoParclRegtd(), boletoRetorno.getVlrSldTotAtlPgtoTit(),
                boletoRetorno.getSitTitPgto(), boletoRetorno.getSitTit());

        setBoletoDDAIndicadorAceite(boletoDDA, boletoRetorno.getIndrActe(), boletoRetorno.getNumRefAtlActe(), boletoRetorno.getNumSeqAtlzActe());

        setBoletoDDATerceiroAutorizado(boletoDDA, boletoRetorno);

        setBoletoDDABaixaEfet(boletoDDA, boletoRetorno);

        setBoletoDDABaixaOper(boletoDDA, boletoRetorno);

        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converter(GrupoADDA106TitComplexType boletoRetorno) {
        BoletoDDA boletoDDA = new BoletoDDA();

        setBoletoDDAIdentificacao(boletoDDA, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatario(), boletoRetorno.getCodPartDestinatario());

        setBoletoDDABeneficiario(boletoDDA, boletoRetorno.getTpPessoaBenfcrioOr(), boletoRetorno.getCNPJCPFBenfcrioOr(), boletoRetorno.getNomRzSocBenfcrioOr(),
                boletoRetorno.getNomFantsBenfcrioOr(), boletoRetorno.getLogradBenfcrioOr(), boletoRetorno.getCidBenfcrioOr(), boletoRetorno.getUFBenfcrioOr(),
                boletoRetorno.getCEPBenfcrioOr());

        setBoletoDDABeneficiarioFinal(boletoDDA, boletoRetorno.getTpPessoaBenfcrioFinl(), boletoRetorno.getCNPJCPFBenfcrioFinl(), boletoRetorno.getNomRzSocBenfcrioFinl(),
                boletoRetorno.getNomFantsBenfcrioFinl());

        setBoletoDDAPagador(boletoDDA, boletoRetorno.getTpPessoaPagdr(), boletoRetorno.getCNPJCPFPagdr(), boletoRetorno.getNomRzSocPagdr(), boletoRetorno.getNomFantsPagdr(),
                boletoRetorno.getLogradPagdr(), boletoRetorno.getCidPagdr(), boletoRetorno.getUFPagdr(), boletoRetorno.getCEPPagdr());

        setBoletoDDASacadorAvalista(boletoDDA, boletoRetorno.getTpIdentcSacdrAvalst(), boletoRetorno.getIdentcSacdrAvalst(), boletoRetorno.getNomRzSocSacdrAvalst());

        setBoletoDDADocumentacao(boletoDDA, boletoRetorno.getCodCartTit(), boletoRetorno.getCodEspTit(), boletoRetorno.getNumDocTit(), boletoRetorno.getTpPgtoTit(),
                boletoRetorno.getNumParcl(), boletoRetorno.getQtdTotParcl(), boletoRetorno.getIndrTitNegcd());

        setBoletoDDAPagamento(boletoDDA, boletoRetorno.getDtVencTit(), boletoRetorno.getVlrTit(), boletoRetorno.getQtdDiaPrott(), boletoRetorno.getDtLimPgtoTit(),
                boletoRetorno.getIndrBloqPgto(), boletoRetorno.getVlrAbattTit(), boletoRetorno.getQtdPgtoParcl());

        setBoletoDDADadosTitulo(boletoDDA, boletoRetorno.getCodMoedaCNAB(), boletoRetorno.getIdentdNossoNum(), boletoRetorno.getNumCodBarras(), boletoRetorno.getNumLinhaDigtvl(),
                boletoRetorno.getDtEmsTit(), boletoRetorno.getIndrPgtoParcl(), boletoRetorno.getTpPgtoTit());

        // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA106JurosTit())) {
            setBoletoDDAJuros(boletoRetorno.getGrupoADDA106JurosTit().getDtJurosTit(), boletoRetorno.getGrupoADDA106JurosTit().getCodJurosTit(),
                    boletoRetorno.getGrupoADDA106JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA106MultaTit())) {
            setBoletoDDAMulta(boletoRetorno.getGrupoADDA106MultaTit().getDtMultaTit(), boletoRetorno.getGrupoADDA106MultaTit().getCodMultaTit(),
                    boletoRetorno.getGrupoADDA106MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA106DesctTit())) {
            converterBoletoDDADesconto(boletoRetorno, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(boletoRetorno.getTpVlrPercMinTit(), boletoRetorno.getVlrPercMinTit(), boletoRetorno.getTpVlrPercMaxTit(), boletoRetorno.getVlrPercMaxTit(),
                boletoRetorno.getTpModlCalc(), boletoRetorno.getTpAutcRecbtVlrDivgte(), boletoDDA);

        converterBoletoDDAGrupoCalculo(boletoRetorno, boletoDDA);

        setBoletoDDASaldoPagamento(boletoDDA, boletoRetorno.getDtHrSit(), boletoRetorno.getQtdPgtoParclRegtd(), boletoRetorno.getVlrSldTotAtlPgtoTit(),
                boletoRetorno.getSitTitPgto(), boletoRetorno.getSitTit());

        setBoletoDDAIndicadorAceite(boletoDDA, boletoRetorno.getIndrActe(), boletoRetorno.getNumRefAtlActe(), boletoRetorno.getNumSeqAtlzActe());

        setBoletoDDATerceiroAutorizado(boletoDDA, boletoRetorno);

        setBoletoDDABaixaEfet(boletoDDA, boletoRetorno);

        setBoletoDDABaixaOper(boletoDDA, boletoRetorno);

        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converter(GrupoDDA0127TitComplexType boletoRetorno) {
        BoletoDDA boletoDDA = new BoletoDDA();

        setBoletoDDAIdentificacao(boletoDDA, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatario(), boletoRetorno.getCodPartDestinatario());

        setBoletoDDABeneficiario(boletoDDA, boletoRetorno.getTpPessoaBenfcrioOr(), boletoRetorno.getCNPJCPFBenfcrioOr(), boletoRetorno.getNomRzSocBenfcrioOr(),
                boletoRetorno.getNomFantsBenfcrioOr(), boletoRetorno.getLogradBenfcrioOr(), boletoRetorno.getCidBenfcrioOr(), boletoRetorno.getUFBenfcrioOr(),
                boletoRetorno.getCEPBenfcrioOr());

        setBoletoDDABeneficiarioFinal(boletoDDA, boletoRetorno.getTpPessoaBenfcrioFinl(), boletoRetorno.getCNPJCPFBenfcrioFinl(), boletoRetorno.getNomRzSocBenfcrioFinl(),
                boletoRetorno.getNomFantsBenfcrioFinl());

        setBoletoDDAPagador(boletoDDA, boletoRetorno.getTpPessoaPagdr(), boletoRetorno.getCNPJCPFPagdr(), boletoRetorno.getNomRzSocPagdr(), boletoRetorno.getNomFantsPagdr(),
                boletoRetorno.getLogradPagdr(), boletoRetorno.getCidPagdr(), boletoRetorno.getUFPagdr(), boletoRetorno.getCEPPagdr());

        setBoletoDDASacadorAvalista(boletoDDA, boletoRetorno.getTpIdentcSacdrAvalst(), boletoRetorno.getIdentcSacdrAvalst(), boletoRetorno.getNomRzSocSacdrAvalst());

        setBoletoDDADocumentacao(boletoDDA, boletoRetorno.getCodCartTit(), boletoRetorno.getCodEspTit(), boletoRetorno.getNumDocTit(), boletoRetorno.getTpPgtoTit(),
                boletoRetorno.getNumParcl(), boletoRetorno.getQtdTotParcl(), boletoRetorno.getIndrTitNegcd());

        setBoletoDDAPagamento(boletoDDA, boletoRetorno.getDtVencTit(), boletoRetorno.getVlrTit(), boletoRetorno.getQtdDiaPrott(), boletoRetorno.getDtLimPgtoTit(),
                boletoRetorno.getIndrBloqPgto(), boletoRetorno.getVlrAbattTit(), boletoRetorno.getQtdPgtoParcl());

        setBoletoDDADadosTitulo(boletoDDA, boletoRetorno.getCodMoedaCNAB(), boletoRetorno.getIdentdNossoNum(), boletoRetorno.getNumCodBarras(), boletoRetorno.getNumLinhaDigtvl(),
                boletoRetorno.getDtEmsTit(), boletoRetorno.getIndrPgtoParcl(), boletoRetorno.getTpPgtoTit());

        // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0127JurosTit())) {
            setBoletoDDAJuros(boletoRetorno.getGrupoDDA0127JurosTit().getDtJurosTit(), boletoRetorno.getGrupoDDA0127JurosTit().getCodJurosTit(),
                    boletoRetorno.getGrupoDDA0127JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0127MultaTit())) {
            setBoletoDDAMulta(boletoRetorno.getGrupoDDA0127MultaTit().getDtMultaTit(), boletoRetorno.getGrupoDDA0127MultaTit().getCodMultaTit(),
                    boletoRetorno.getGrupoDDA0127MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0127DesctTit())) {
            converterBoletoDDADesconto(boletoRetorno, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(boletoRetorno.getTpVlrPercMinTit(), boletoRetorno.getVlrPercMinTit(), boletoRetorno.getTpVlrPercMaxTit(), boletoRetorno.getVlrPercMaxTit(),
                boletoRetorno.getTpModlCalc(), boletoRetorno.getTpAutcRecbtVlrDivgte(), boletoDDA);

        converterBoletoDDAGrupoCalculo(boletoRetorno, boletoDDA);

        setBoletoDDASaldoPagamento(boletoDDA, boletoRetorno.getDtHrSit(), boletoRetorno.getQtdPgtoParclRegtd(), boletoRetorno.getVlrSldTotAtlPgtoTit(),
                boletoRetorno.getSitTitPgto(), boletoRetorno.getSitTit());

        setBoletoDDAIndicadorAceite(boletoDDA, boletoRetorno.getIndrActe(), boletoRetorno.getNumRefAtlActe(), boletoRetorno.getNumSeqAtlzActe());

        setBoletoDDATerceiroAutorizado(boletoDDA, boletoRetorno);

        setBoletoDDABaixaEfet(boletoDDA, boletoRetorno);

        setBoletoDDABaixaOper(boletoDDA, boletoRetorno);

        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converter(GrupoADDA127TitComplexType boletoRetorno) {
        BoletoDDA boletoDDA = new BoletoDDA();

        setBoletoDDAIdentificacao(boletoDDA, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                boletoRetorno.getISPBPartDestinatario(), boletoRetorno.getCodPartDestinatario());

        setBoletoDDABeneficiario(boletoDDA, boletoRetorno.getTpPessoaBenfcrioOr(), boletoRetorno.getCNPJCPFBenfcrioOr(), boletoRetorno.getNomRzSocBenfcrioOr(),
                boletoRetorno.getNomFantsBenfcrioOr(), boletoRetorno.getLogradBenfcrioOr(), boletoRetorno.getCidBenfcrioOr(), boletoRetorno.getUFBenfcrioOr(),
                boletoRetorno.getCEPBenfcrioOr());

        setBoletoDDABeneficiarioFinal(boletoDDA, boletoRetorno.getTpPessoaBenfcrioFinl(), boletoRetorno.getCNPJCPFBenfcrioFinl(), boletoRetorno.getNomRzSocBenfcrioFinl(),
                boletoRetorno.getNomFantsBenfcrioFinl());

        setBoletoDDAPagador(boletoDDA, boletoRetorno.getTpPessoaPagdr(), boletoRetorno.getCNPJCPFPagdr(), boletoRetorno.getNomRzSocPagdr(), boletoRetorno.getNomFantsPagdr(),
                boletoRetorno.getLogradPagdr(), boletoRetorno.getCidPagdr(), boletoRetorno.getUFPagdr(), boletoRetorno.getCEPPagdr());

        setBoletoDDASacadorAvalista(boletoDDA, boletoRetorno.getTpIdentcSacdrAvalst(), boletoRetorno.getIdentcSacdrAvalst(), boletoRetorno.getNomRzSocSacdrAvalst());

        setBoletoDDADocumentacao(boletoDDA, boletoRetorno.getCodCartTit(), boletoRetorno.getCodEspTit(), boletoRetorno.getNumDocTit(), boletoRetorno.getTpPgtoTit(),
                boletoRetorno.getNumParcl(), boletoRetorno.getQtdTotParcl(), boletoRetorno.getIndrTitNegcd());

        setBoletoDDAPagamento(boletoDDA, boletoRetorno.getDtVencTit(), boletoRetorno.getVlrTit(), boletoRetorno.getQtdDiaPrott(), boletoRetorno.getDtLimPgtoTit(),
                boletoRetorno.getIndrBloqPgto(), boletoRetorno.getVlrAbattTit(), boletoRetorno.getQtdPgtoParcl());

        setBoletoDDADadosTitulo(boletoDDA, boletoRetorno.getCodMoedaCNAB(), boletoRetorno.getIdentdNossoNum(), boletoRetorno.getNumCodBarras(), boletoRetorno.getNumLinhaDigtvl(),
                boletoRetorno.getDtEmsTit(), boletoRetorno.getIndrPgtoParcl(), boletoRetorno.getTpPgtoTit());

        // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA127JurosTit())) {
            setBoletoDDAJuros(boletoRetorno.getGrupoADDA127JurosTit().getDtJurosTit(), boletoRetorno.getGrupoADDA127JurosTit().getCodJurosTit(),
                    boletoRetorno.getGrupoADDA127JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA127MultaTit())) {
            setBoletoDDAMulta(boletoRetorno.getGrupoADDA127MultaTit().getDtMultaTit(), boletoRetorno.getGrupoADDA127MultaTit().getCodMultaTit(),
                    boletoRetorno.getGrupoADDA127MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA127DesctTit())) {
            converterBoletoDDADesconto(boletoRetorno, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        // TODO daniel.moraes: verificar campos IndrVlrPercMinTit e IndrVlrPercMaxTit
        setBoletoDDAValorRecebido(boletoRetorno.getTpVlrPercMinTit(), boletoRetorno.getVlrPercMinTit(), boletoRetorno.getTpVlrPercMaxTit(), boletoRetorno.getVlrPercMaxTit(),
                boletoRetorno.getTpModlCalc(), boletoRetorno.getTpAutcRecbtVlrDivgte(), boletoDDA);

        converterBoletoDDAGrupoCalculo(boletoRetorno, boletoDDA);

        setBoletoDDASaldoPagamento(boletoDDA, boletoRetorno.getDtHrSit(), boletoRetorno.getQtdPgtoParclRegtd(), boletoRetorno.getVlrSldTotAtlPgtoTit(),
                boletoRetorno.getSitTitPgto(), boletoRetorno.getSitTit());

        setBoletoDDAIndicadorAceite(boletoDDA, boletoRetorno.getIndrActe(), boletoRetorno.getNumRefAtlActe(), boletoRetorno.getNumSeqAtlzActe());

        setBoletoDDATerceiroAutorizado(boletoDDA, boletoRetorno);

        setBoletoDDABaixaEfet(boletoDDA, boletoRetorno);

        setBoletoDDABaixaOper(boletoDDA, boletoRetorno);

        return boletoDDA;
    }

    /**
     * Método responsável por fazer a alteraçao de acordo com a mensagem recebida pela cip R2
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0106 e NÃO processar a mensagem recebida.
     * 
     * Se o sequencial da mensagem recebida for Maior, PROCESSAR.
     * 
     * Se o sequencial da mensagem recebida for igual, NÃO processar.
     * 
     * @param boletoRetorno
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(DDA0102R2ComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (isMensagemConcorrente(boletoDDA, boletoRetorno.getNumSeqAtlzCadTit())) {
            return boletoDDA;
        } else {
            setBoletoDDAIdentificacao(boletoDDA, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                    boletoRetorno.getISPBPartDestinatario());

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2BenfcrioOr())
                    && boletoRetorno.getIndrManutBenfcrioOr().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDABeneficiario(boletoDDA, boletoRetorno.getGrupoDDA0102R2BenfcrioOr().getNomFantsBenfcrioOr(),
                        boletoRetorno.getGrupoDDA0102R2BenfcrioOr().getLogradBenfcrioOr(), boletoRetorno.getGrupoDDA0102R2BenfcrioOr().getCidBenfcrioOr(),
                        boletoRetorno.getGrupoDDA0102R2BenfcrioOr().getUFBenfcrioOr(), boletoRetorno.getGrupoDDA0102R2BenfcrioOr().getCEPBenfcrioOr());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2BenfcrioFinl())) {
                setBoletoDDABeneficiarioFinal(boletoDDA, boletoRetorno.getGrupoDDA0102R2BenfcrioFinl().getTpPessoaBenfcrioFinl(),
                        boletoRetorno.getGrupoDDA0102R2BenfcrioFinl().getCNPJCPFBenfcrioFinl(), boletoRetorno.getGrupoDDA0102R2BenfcrioFinl().getNomRzSocBenfcrioFinl(),
                        boletoRetorno.getGrupoDDA0102R2BenfcrioFinl().getNomFantsBenfcrioFinl());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2Pagdr())
                    && boletoRetorno.getIndrManutPagdrTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDAPagador(boletoDDA, boletoRetorno.getGrupoDDA0102R2Pagdr().getNomRzSocPagdr(), boletoRetorno.getGrupoDDA0102R2Pagdr().getNomFantsPagdr(),
                        boletoRetorno.getGrupoDDA0102R2Pagdr().getLogradPagdr(), boletoRetorno.getGrupoDDA0102R2Pagdr().getCidPagdr(),
                        boletoRetorno.getGrupoDDA0102R2Pagdr().getUFPagdr(), boletoRetorno.getGrupoDDA0102R2Pagdr().getCEPPagdr());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2DocTit()) && boletoRetorno.getIndrManutDocTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDADocumentacao(boletoDDA, boletoRetorno.getGrupoDDA0102R2DocTit().getCodCartTit(), boletoRetorno.getGrupoDDA0102R2DocTit().getCodEspTit(),
                        boletoRetorno.getGrupoDDA0102R2DocTit().getNumDocTit(), boletoRetorno.getGrupoDDA0102R2DocTit().getTpPgtoTit(),
                        boletoRetorno.getGrupoDDA0102R2DocTit().getNumParcl(), boletoRetorno.getGrupoDDA0102R2DocTit().getQtdTotParcl(),
                        boletoRetorno.getGrupoDDA0102R2DocTit().getIndrTitNegcd());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2SacdrAvalst())
                    && boletoRetorno.getIndrManutSacdrAvalst().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDASacadorAvalista(boletoDDA, boletoRetorno.getGrupoDDA0102R2SacdrAvalst().getTpIdentcSacdrAvalst(),
                        boletoRetorno.getGrupoDDA0102R2SacdrAvalst().getIdentcSacdrAvalst(), boletoRetorno.getGrupoDDA0102R2SacdrAvalst().getNomRzSocSacdrAvalst());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2InstcPgtoTit())
                    && boletoRetorno.getIndrManutInstcPgtoTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                validaAlteracoesBoletoR2(boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getDtVencTit(), boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getDtLimPgtoTit(),
                        boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getVlrTit(), boletoDDA);

                setBoletoDDAPagamento(boletoDDA, boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getDtVencTit(), boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getVlrTit(),
                        boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getQtdDiaPrott(), boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getDtLimPgtoTit(),
                        boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getIndrBloqPgto(), boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getVlrAbattTit(),
                        boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getQtdPgtoParcl(), boletoRetorno.getGrupoDDA0102R2InstcPgtoTit().getTpModlCalc());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2InstcVlrRecbt())
                    && boletoRetorno.getIndrManutInstcVlrRecbt().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDAValorRecebido(boletoDDA, boletoRetorno.getGrupoDDA0102R2InstcVlrRecbt().getTpAutcRecbtVlrDivgte(),
                        boletoRetorno.getGrupoDDA0102R2InstcVlrRecbt().getTpVlrPercMinTit(), boletoRetorno.getGrupoDDA0102R2InstcVlrRecbt().getVlrPercMinTit(),
                        boletoRetorno.getGrupoDDA0102R2InstcVlrRecbt().getTpVlrPercMaxTit(), boletoRetorno.getGrupoDDA0102R2InstcVlrRecbt().getVlrPercMaxTit());
            }

            // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2JurosTit())
                    && boletoRetorno.getIndrManutJurosTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {

                setBoletoDDAJuros(boletoRetorno.getGrupoDDA0102R2JurosTit().getDtJurosTit(), boletoRetorno.getGrupoDDA0102R2JurosTit().getCodJurosTit(),
                        boletoRetorno.getGrupoDDA0102R2JurosTit().getVlrPercJurosTit(), boletoDDA);
            }
            // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
            if (!ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2MultaTit())
                    && boletoRetorno.getIndrManutMultaTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {

                setBoletoDDAMulta(boletoRetorno.getGrupoDDA0102R2MultaTit().getDtMultaTit(), boletoRetorno.getGrupoDDA0102R2MultaTit().getCodMultaTit(),
                        boletoRetorno.getGrupoDDA0102R2MultaTit().getVlrPercMultaTit(), boletoDDA);
            }

            setBoletoDDADescontoR2(boletoRetorno, boletoDDA);

            setBoletoDDAGrupoCalculoR2(boletoRetorno, boletoDDA);

            boletoDDA.setQtdPagamentoParcialReg(boletoRetorno.getQtdPgtoParclRegtd());

            boletoDDA.setValorSaldoPagamento(boletoRetorno.getVlrSldTotAtlPgtoTit());

            boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getDtHrSitTit()));

            boletoDDA.setCodSituacaoBoletoPagamento(boletoRetorno.getSitTitPgto());

            boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());

            boletoDDA.setBolProcessarMensagemRecebida(Boolean.TRUE);
            boletoDDA.setBolProcessarMensagem106(Boolean.FALSE);

            return boletoDDA;
        }

    }

    /**
     * Ao receber o retorno de alteração de boletos verificar se houve alteração na Data de Vencimento ou Data Limite para Pagamento ou Valor do Boleto, se sim,
     * excluir todas as baixas operacionais e efetivas do boleto.
     * 
     * @param dataVencimento
     * @param datalimitePagamento
     * @param valorBoleto
     * @param boletoDDA void
     * 
     */
    private static void validaAlteracoesBoletoR1(DateTimeDB dataVencimento, DateTimeDB datalimitePagamento, BigDecimal valorBoleto, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(boletoDDA.getDataVencimento()) && !boletoDDA.getDataVencimento().equals(dataVencimento)
                || !ObjectUtil.isNull(boletoDDA.getDataLimitePagamento()) && !boletoDDA.getDataLimitePagamento().equals(datalimitePagamento)
                || !ObjectUtil.isNull(boletoDDA.getValorBoleto()) && !boletoDDA.getValorBoleto().equals(valorBoleto)) {
            boletoDDA.getListaBoletoDDABaixaEfet().clear();
            boletoDDA.getListaBoletoDDABaixaOper().clear();
        }
    }

    /**
     * Ao receber o retorno de alteração de boletos verificar se houve alteração na Data de Vencimento ou Data Limite para Pagamento ou Valor do Boleto, se sim,
     * excluir todas as baixas operacionais e efetivas do boleto.
     * 
     * @param dataVencimento
     * @param datalimitePagamento
     * @param valorBoleto
     * @param boletoDDA void
     * 
     */
    private static void validaAlteracoesBoletoR2(XMLGregorianCalendar dataVencimento, XMLGregorianCalendar datalimitePagamento, BigDecimal valorBoleto, BoletoDDA boletoDDA) {
        if ((!ObjectUtil.isNull(boletoDDA.getDataVencimento()) && !boletoDDA.getDataVencimento().equals(DataCipUtil.xMLGregorianCalendarParaDateTime(dataVencimento)))
                || (!ObjectUtil.isNull(boletoDDA.getDataLimitePagamento())
                        && !boletoDDA.getDataLimitePagamento().equals(DataCipUtil.xMLGregorianCalendarParaDateTime(datalimitePagamento)))
                || (!ObjectUtil.isNull(boletoDDA.getValorBoleto()) && !boletoDDA.getValorBoleto().equals(valorBoleto))) {
            boletoDDA.getListaBoletoDDABaixaEfet().clear();
            boletoDDA.getListaBoletoDDABaixaOper().clear();
        }
    }

    /**
     * Método responsável por fazer a alteraçao de acordo com a mensagem recebida pela cip R2
     * 
     * Se o sequencial de atualização recebido na mensagem for menor que o do registro na base, gerar uma mensagem DDA0106 e NÃO processar a mensagem recebida.
     * 
     * Se o sequencial da mensagem recebida for Maior, PROCESSAR.
     * 
     * Se o sequencial da mensagem recebida for igual, NÃO processar.
     * 
     * 
     * @param boletoRetorno
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(GrupoADDA102RR2TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (isMensagemConcorrente(boletoDDA, boletoRetorno.getNumSeqAtlzCadTit())) {
            return boletoDDA;
        } else {
            setBoletoDDAIdentificacao(boletoDDA, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit(),
                    boletoRetorno.getISPBPartDestinatario());

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2BenfcrioOr())
                    && boletoRetorno.getIndrManutBenfcrioOr().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDABeneficiario(boletoDDA, boletoRetorno.getGrupoADDA102RR2BenfcrioOr().getNomFantsBenfcrioOr(),
                        boletoRetorno.getGrupoADDA102RR2BenfcrioOr().getLogradBenfcrioOr(), boletoRetorno.getGrupoADDA102RR2BenfcrioOr().getCidBenfcrioOr(),
                        boletoRetorno.getGrupoADDA102RR2BenfcrioOr().getUFBenfcrioOr(), boletoRetorno.getGrupoADDA102RR2BenfcrioOr().getCEPBenfcrioOr());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2BenfcrioFinl())) {
                setBoletoDDABeneficiarioFinal(boletoDDA, boletoRetorno.getGrupoADDA102RR2BenfcrioFinl().getTpPessoaBenfcrioFinl(),
                        boletoRetorno.getGrupoADDA102RR2BenfcrioFinl().getCNPJCPFBenfcrioFinl(), boletoRetorno.getGrupoADDA102RR2BenfcrioFinl().getNomRzSocBenfcrioFinl(),
                        boletoRetorno.getGrupoADDA102RR2BenfcrioFinl().getNomFantsBenfcrioFinl());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2Pagdr())
                    && boletoRetorno.getIndrManutPagdrTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDAPagador(boletoDDA, boletoRetorno.getGrupoADDA102RR2Pagdr().getNomRzSocPagdr(), boletoRetorno.getGrupoADDA102RR2Pagdr().getNomFantsPagdr(),
                        boletoRetorno.getGrupoADDA102RR2Pagdr().getLogradPagdr(), boletoRetorno.getGrupoADDA102RR2Pagdr().getCidPagdr(),
                        boletoRetorno.getGrupoADDA102RR2Pagdr().getUFPagdr(), boletoRetorno.getGrupoADDA102RR2Pagdr().getCEPPagdr());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2DocTit())
                    && boletoRetorno.getIndrManutDocTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDADocumentacao(boletoDDA, boletoRetorno.getGrupoADDA102RR2DocTit().getCodCartTit(), boletoRetorno.getGrupoADDA102RR2DocTit().getCodEspTit(),
                        boletoRetorno.getGrupoADDA102RR2DocTit().getNumDocTit(), boletoRetorno.getGrupoADDA102RR2DocTit().getTpPgtoTit(),
                        boletoRetorno.getGrupoADDA102RR2DocTit().getNumParcl(), boletoRetorno.getGrupoADDA102RR2DocTit().getQtdTotParcl(),
                        boletoRetorno.getGrupoADDA102RR2DocTit().getIndrTitNegcd());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2SacdrAvalst())
                    && boletoRetorno.getIndrManutSacdrAvalst().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDASacadorAvalista(boletoDDA, boletoRetorno.getGrupoADDA102RR2SacdrAvalst().getTpIdentcSacdrAvalst(),
                        boletoRetorno.getGrupoADDA102RR2SacdrAvalst().getIdentcSacdrAvalst(), boletoRetorno.getGrupoADDA102RR2SacdrAvalst().getNomRzSocSacdrAvalst());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2InstcPgtoTit())
                    && boletoRetorno.getIndrManutInstcPgtoTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                validaAlteracoesBoletoR2(boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getDtVencTit(), boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getDtLimPgtoTit(),
                        boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getVlrTit(), boletoDDA);
                setBoletoDDAPagamento(boletoDDA, boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getDtVencTit(), boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getVlrTit(),
                        boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getQtdDiaPrott(), boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getDtLimPgtoTit(),
                        boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getIndrBloqPgto(), boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getVlrAbattTit(),
                        boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getQtdPgtoParcl(), boletoRetorno.getGrupoADDA102RR2InstcPgtoTit().getTpModlCalc());
            }

            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2InstcVlrRecbt())
                    && boletoRetorno.getIndrManutInstcVlrRecbt().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {
                setBoletoDDAValorRecebido(boletoDDA, boletoRetorno.getGrupoADDA102RR2InstcVlrRecbt().getTpAutcRecbtVlrDivgte(),
                        boletoRetorno.getGrupoADDA102RR2InstcVlrRecbt().getTpVlrPercMinTit(), boletoRetorno.getGrupoADDA102RR2InstcVlrRecbt().getVlrPercMinTit(),
                        boletoRetorno.getGrupoADDA102RR2InstcVlrRecbt().getTpVlrPercMaxTit(), boletoRetorno.getGrupoADDA102RR2InstcVlrRecbt().getVlrPercMaxTit());
            }

            // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2JurosTit())
                    && boletoRetorno.getIndrManutJurosTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {

                setBoletoDDAJuros(boletoRetorno.getGrupoADDA102RR2JurosTit().getDtJurosTit(), boletoRetorno.getGrupoADDA102RR2JurosTit().getCodJurosTit(),
                        boletoRetorno.getGrupoADDA102RR2JurosTit().getVlrPercJurosTit(), boletoDDA);
            }

            // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
            if (!ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2MultaTit())
                    && boletoRetorno.getIndrManutMultaTit().equals(TipoOperacaoSicoobDDAEnum.ALTERACAO.getCodigoOperacao())) {

                setBoletoDDAMulta(boletoRetorno.getGrupoADDA102RR2MultaTit().getDtMultaTit(), boletoRetorno.getGrupoADDA102RR2MultaTit().getCodMultaTit(),
                        boletoRetorno.getGrupoADDA102RR2MultaTit().getVlrPercMultaTit(), boletoDDA);
            }

            mergeBoletoDDADesconto(boletoRetorno, boletoDDA);

            mergeBoletoDDAGrupoCalculo(boletoRetorno, boletoDDA);

            boletoDDA.setQtdPagamentoParcialReg(boletoRetorno.getQtdPgtoParclRegtd());

            boletoDDA.setValorSaldoPagamento(boletoRetorno.getVlrSldTotAtlPgtoTit());

            boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getDtHrSitTit()));

            boletoDDA.setCodSituacaoBoletoPagamento(boletoRetorno.getSitTitPgto());

            boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());

            boletoDDA.setBolProcessarMensagemRecebida(Boolean.TRUE);
            boletoDDA.setBolProcessarMensagem106(Boolean.FALSE);

            return boletoDDA;
        }
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converterDDA0110(DDA0110R1ComplexType conteudo) {
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());
        setBoletoDDAIdentificacao(boletoDDA, conteudo.getNumIdentcTit(), conteudo.getNumRefAtlCadTit(), conteudo.getNumSeqAtlzCadTit(), conteudo.getISPBPartDestinatario(),
                conteudo.getCodPartDestinatario());

        boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getDtHrSitTit()));

        setBoletoDDABeneficiario(boletoDDA, conteudo.getTpPessoaBenfcrioOr(), conteudo.getCNPJCPFBenfcrioOr(), conteudo.getNomRzSocBenfcrioOr(), conteudo.getNomFantsBenfcrioOr(),
                conteudo.getLogradBenfcrioOr(), conteudo.getCidBenfcrioOr(), conteudo.getUFBenfcrioOr(), conteudo.getCEPBenfcrioOr());

        String cpfCnpjBeneficiarioFinal = null;
        if (!ObjectUtil.isNull(conteudo.getCNPJCPFBenfcrioFinl()) && !ObjectUtil.isNull(conteudo.getTpPessoaBenfcrioFinl())) {
            cpfCnpjBeneficiarioFinal = DataCipUtil.obterCPFCNPJ(conteudo.getCNPJCPFBenfcrioFinl(), conteudo.getTpPessoaBenfcrioFinl());
        }
        setBoletoDDABeneficiarioFinal(boletoDDA, conteudo.getTpPessoaBenfcrioFinl(), cpfCnpjBeneficiarioFinal, conteudo.getNomRzSocBenfcrioFinl(),
                conteudo.getNomFantsBenfcrioFinl());

        boletoDDA.setCodTipoPessoaPagador(conteudo.getTpPessoaPagdr());
        boletoDDA.setNumCpfCnpjPagador(DataCipUtil.obterCPFCNPJ(conteudo.getCNPJCPFPagdr(), conteudo.getTpPessoaPagdr()));
        boletoDDA.setNomePagador(conteudo.getNomRzSocPagdr());
        boletoDDA.setNomeFantasiaPagador(conteudo.getNomFantsPagdr());

        setPagamentoDDA110(conteudo, boletoDDA);

        // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(conteudo.getGrupoDDA0110R1JurosTit())) {
            setBoletoDDAJuros(conteudo.getGrupoDDA0110R1JurosTit().getDtJurosTit(), conteudo.getGrupoDDA0110R1JurosTit().getCodJurosTit(),
                    conteudo.getGrupoDDA0110R1JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(conteudo.getGrupoDDA0110R1MultaTit())) {
            setBoletoDDAMulta(conteudo.getGrupoDDA0110R1MultaTit().getDtMultaTit(), conteudo.getGrupoDDA0110R1MultaTit().getCodMultaTit(),
                    conteudo.getGrupoDDA0110R1MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(conteudo.getGrupoDDA0110R1DesctTit())) {
            converterBoletoDDADesconto(conteudo, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(boletoDDA, conteudo.getTpAutcRecbtVlrDivgte(), conteudo.getTpVlrPercMinTit(), conteudo.getVlrPercMinTit(), conteudo.getTpVlrPercMaxTit(),
                conteudo.getVlrPercMaxTit());

        boletoDDA.setCodTipoModeloCalculo(conteudo.getTpModlCalc());

        if (!ObjectUtil.isEmpty(conteudo.getGrupoDDA0110R1Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoDDA0110R1CalcComplexType grupoCalculo : conteudo.getGrupoDDA0110R1Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoCalculo.getVlrCalcdJuros(), grupoCalculo.getVlrCalcdMulta(), grupoCalculo.getVlrCalcdDesct(),
                        grupoCalculo.getVlrTotCobrar(),
                        ObjectUtil.isNull(grupoCalculo.getDtValiddCalc()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(grupoCalculo.getDtValiddCalc())));
            }

            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }

        boletoDDA.setValorSaldoPagamento(conteudo.getVlrSldTotAtlPgtoTit());

        setBoletoDDABaixaOper(boletoDDA, conteudo);

        setBoletoDDABaixaEfet(boletoDDA, conteudo);

        boletoDDA.setCodSituacaoBoletoPagamento(conteudo.getSitTitPgto());
        boletoDDA.setCodSituacaoBoleto(SituacaoBoleto.ABERTO);

        setBoletoDDAAceite(boletoDDA, ObjectUtil.isNull(conteudo.getQtdPgtoParclRegtd()) ? 0 : conteudo.getQtdPgtoParclRegtd().intValue());

        // FIXME rodrigo.neri testar CIP
        setBoletoDDASacadorAvalista(boletoDDA, conteudo.getTpIdentcSacdrAvalst(), conteudo.getIdentcSacdrAvalst(), conteudo.getNomRzSocSacdrAvalst());

        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converterADDA110(GrupoADDA110RETTitActoComplexType conteudo) {
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());
        setBoletoDDAIdentificacao(boletoDDA, conteudo.getNumIdentcTit(), conteudo.getNumRefAtlCadTit(), conteudo.getNumSeqAtlzCadTit(), conteudo.getISPBPartDestinatario(),
                conteudo.getCodPartDestinatario());

        boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getDtHrSitTit()));

        setBoletoDDABeneficiario(boletoDDA, conteudo.getTpPessoaBenfcrioOr(), conteudo.getCNPJCPFBenfcrioOr(), conteudo.getNomRzSocBenfcrioOr(), conteudo.getNomFantsBenfcrioOr(),
                conteudo.getLogradBenfcrioOr(), conteudo.getCidBenfcrioOr(), conteudo.getUFBenfcrioOr(), conteudo.getCEPBenfcrioOr());

        String cpfCnpjBeneficiarioFinal = null;
        if (!ObjectUtil.isNull(conteudo.getCNPJCPFBenfcrioFinl()) && !ObjectUtil.isNull(conteudo.getTpPessoaBenfcrioFinl())) {
            cpfCnpjBeneficiarioFinal = DataCipUtil.obterCPFCNPJ(conteudo.getCNPJCPFBenfcrioFinl(), conteudo.getTpPessoaBenfcrioFinl());
        }
        setBoletoDDABeneficiarioFinal(boletoDDA, conteudo.getTpPessoaBenfcrioFinl(), cpfCnpjBeneficiarioFinal, conteudo.getNomRzSocBenfcrioFinl(),
                conteudo.getNomFantsBenfcrioFinl());

        boletoDDA.setCodTipoPessoaPagador(conteudo.getTpPessoaPagdr());
        boletoDDA.setNumCpfCnpjPagador(DataCipUtil.obterCPFCNPJ(conteudo.getCNPJCPFPagdr(), conteudo.getTpPessoaPagdr()));
        boletoDDA.setNomePagador(conteudo.getNomRzSocPagdr());
        boletoDDA.setNomeFantasiaPagador(conteudo.getNomFantsPagdr());

        setPagamentoADDA110(conteudo, boletoDDA);

        // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(conteudo.getGrupoADDA110JurosTit())) {
            setBoletoDDAJuros(conteudo.getGrupoADDA110JurosTit().getDtJurosTit(), conteudo.getGrupoADDA110JurosTit().getCodJurosTit(),
                    conteudo.getGrupoADDA110JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }


        // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(conteudo.getGrupoADDA110MultaTit())) {
            setBoletoDDAMulta(conteudo.getGrupoADDA110MultaTit().getDtMultaTit(), conteudo.getGrupoADDA110MultaTit().getCodMultaTit(),
                    conteudo.getGrupoADDA110MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(conteudo.getGrupoADDA110DesctTit())) {
            converterBoletoDDADesconto(conteudo, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(boletoDDA, conteudo.getTpAutcRecbtVlrDivgte(), conteudo.getTpVlrPercMinTit(), conteudo.getVlrPercMinTit(), conteudo.getTpVlrPercMaxTit(),
                conteudo.getVlrPercMaxTit());

        boletoDDA.setCodTipoModeloCalculo(conteudo.getTpModlCalc());

        if (!ObjectUtil.isEmpty(conteudo.getGrupoADDA110Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoADDA110CalcComplexType grupoCalculo : conteudo.getGrupoADDA110Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoCalculo.getVlrCalcdJuros(), grupoCalculo.getVlrCalcdMulta(), grupoCalculo.getVlrCalcdDesct(),
                        grupoCalculo.getVlrTotCobrar(),
                        ObjectUtil.isNull(grupoCalculo.getDtValiddCalc()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(grupoCalculo.getDtValiddCalc())));
            }

            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }

        boletoDDA.setValorSaldoPagamento(conteudo.getVlrSldTotAtlPgtoTit());

        setBoletoDDABaixaOper(boletoDDA, conteudo);

        setBoletoDDABaixaEfet(boletoDDA, conteudo);

        boletoDDA.setCodSituacaoBoletoPagamento(conteudo.getSitTitPgto());
        boletoDDA.setCodSituacaoBoleto(SituacaoBoleto.ABERTO);

        setBoletoDDAAceite(boletoDDA, ObjectUtil.isNull(conteudo.getQtdPgtoParclRegtd()) ? 0 : conteudo.getQtdPgtoParclRegtd().intValue());
        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(DDA0110R1ComplexType conteudo, BoletoDDA boletoDDA) {
        boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());
        setBoletoDDAIdentificacao(boletoDDA, conteudo.getNumIdentcTit(), conteudo.getNumRefAtlCadTit(), conteudo.getNumSeqAtlzCadTit(), conteudo.getISPBPartDestinatario(),
                conteudo.getCodPartDestinatario());

        boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getDtHrSitTit()));

        setBoletoDDABeneficiario(boletoDDA, conteudo.getTpPessoaBenfcrioOr(), conteudo.getCNPJCPFBenfcrioOr(), conteudo.getNomRzSocBenfcrioOr(), conteudo.getNomFantsBenfcrioOr(),
                conteudo.getLogradBenfcrioOr(), conteudo.getCidBenfcrioOr(), conteudo.getUFBenfcrioOr(), conteudo.getCEPBenfcrioOr());

        String cpfCnpjBeneficiarioFinal = null;
        if (ObjectUtil.isNull(conteudo.getCNPJCPFBenfcrioFinl()) && ObjectUtil.isNull(conteudo.getTpPessoaBenfcrioFinl())) {
            cpfCnpjBeneficiarioFinal = DataCipUtil.obterCPFCNPJ(conteudo.getCNPJCPFBenfcrioFinl(), conteudo.getTpPessoaBenfcrioFinl());
        }
        setBoletoDDABeneficiarioFinal(boletoDDA, conteudo.getTpPessoaBenfcrioFinl(), cpfCnpjBeneficiarioFinal, conteudo.getNomRzSocBenfcrioFinl(),
                conteudo.getNomFantsBenfcrioFinl());

        boletoDDA.setCodTipoPessoaPagador(conteudo.getTpPessoaPagdr());
        boletoDDA.setNumCpfCnpjPagador(DataCipUtil.obterCPFCNPJ(conteudo.getCNPJCPFPagdr(), conteudo.getTpPessoaPagdr()));
        boletoDDA.setNomePagador(conteudo.getNomRzSocPagdr());
        boletoDDA.setNomeFantasiaPagador(conteudo.getNomFantsPagdr());

        setPagamentoDDA110(conteudo, boletoDDA);

        // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(conteudo.getGrupoDDA0110R1JurosTit())) {
            setBoletoDDAJuros(conteudo.getGrupoDDA0110R1JurosTit().getDtJurosTit(), conteudo.getGrupoDDA0110R1JurosTit().getCodJurosTit(),
                    conteudo.getGrupoDDA0110R1JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(conteudo.getGrupoDDA0110R1MultaTit())) {
            setBoletoDDAMulta(conteudo.getGrupoDDA0110R1MultaTit().getDtMultaTit(), conteudo.getGrupoDDA0110R1MultaTit().getCodMultaTit(),
                    conteudo.getGrupoDDA0110R1MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }


        if (!ObjectUtil.isEmpty(conteudo.getGrupoDDA0110R1DesctTit())) {
            converterBoletoDDADesconto(conteudo, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(boletoDDA, conteudo.getTpAutcRecbtVlrDivgte(), conteudo.getTpVlrPercMinTit(), conteudo.getVlrPercMinTit(), conteudo.getTpVlrPercMaxTit(),
                conteudo.getVlrPercMaxTit());

        boletoDDA.setCodTipoModeloCalculo(conteudo.getTpModlCalc());

        if (!ObjectUtil.isEmpty(conteudo.getGrupoDDA0110R1Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoDDA0110R1CalcComplexType grupoCalculo : conteudo.getGrupoDDA0110R1Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoCalculo.getVlrCalcdJuros(), grupoCalculo.getVlrCalcdMulta(), grupoCalculo.getVlrCalcdDesct(),
                        grupoCalculo.getVlrTotCobrar(),
                        ObjectUtil.isNull(grupoCalculo.getDtValiddCalc()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(grupoCalculo.getDtValiddCalc())));
            }

            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }

        boletoDDA.setValorSaldoPagamento(conteudo.getVlrSldTotAtlPgtoTit());

        setBoletoDDABaixaOper(boletoDDA, conteudo);

        setBoletoDDABaixaEfet(boletoDDA, conteudo);

        boletoDDA.setCodSituacaoBoletoPagamento(conteudo.getSitTitPgto());
        setBoletoDDAAceite(boletoDDA, ObjectUtil.isNull(conteudo.getQtdPgtoParclRegtd()) ? 0 : conteudo.getQtdPgtoParclRegtd().intValue());

        // FIXME rodrigo.neri testar CIP
        setBoletoDDASacadorAvalista(boletoDDA, conteudo.getTpIdentcSacdrAvalst(), conteudo.getIdentcSacdrAvalst(), conteudo.getNomRzSocSacdrAvalst());

        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(GrupoADDA110RETTitActoComplexType conteudo, BoletoDDA boletoDDA) {
        boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());
        setBoletoDDAIdentificacao(boletoDDA, conteudo.getNumIdentcTit(), conteudo.getNumRefAtlCadTit(), conteudo.getNumSeqAtlzCadTit(), conteudo.getISPBPartDestinatario(),
                conteudo.getCodPartDestinatario());

        boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getDtHrSitTit()));

        setBoletoDDABeneficiario(boletoDDA, conteudo.getTpPessoaBenfcrioOr(), conteudo.getCNPJCPFBenfcrioOr(), conteudo.getNomRzSocBenfcrioOr(), conteudo.getNomFantsBenfcrioOr(),
                conteudo.getLogradBenfcrioOr(), conteudo.getCidBenfcrioOr(), conteudo.getUFBenfcrioOr(), conteudo.getCEPBenfcrioOr());

        String cpfCnpjBeneficiarioFinal = null;
        if (ObjectUtil.isNull(conteudo.getCNPJCPFBenfcrioFinl()) && ObjectUtil.isNull(conteudo.getTpPessoaBenfcrioFinl())) {
            cpfCnpjBeneficiarioFinal = DataCipUtil.obterCPFCNPJ(conteudo.getCNPJCPFBenfcrioFinl(), conteudo.getTpPessoaBenfcrioFinl());
        }
        setBoletoDDABeneficiarioFinal(boletoDDA, conteudo.getTpPessoaBenfcrioFinl(), cpfCnpjBeneficiarioFinal, conteudo.getNomRzSocBenfcrioFinl(),
                conteudo.getNomFantsBenfcrioFinl());

        boletoDDA.setCodTipoPessoaPagador(conteudo.getTpPessoaPagdr());
        boletoDDA.setNumCpfCnpjPagador(DataCipUtil.obterCPFCNPJ(conteudo.getCNPJCPFPagdr(), conteudo.getTpPessoaPagdr()));
        boletoDDA.setNomePagador(conteudo.getNomRzSocPagdr());
        boletoDDA.setNomeFantasiaPagador(conteudo.getNomFantsPagdr());

        setPagamentoADDA110(conteudo, boletoDDA);

        // Desnormalizada BoletoDDAJuros, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(conteudo.getGrupoADDA110JurosTit())) {
            setBoletoDDAJuros(conteudo.getGrupoADDA110JurosTit().getDtJurosTit(), conteudo.getGrupoADDA110JurosTit().getCodJurosTit(),
                    conteudo.getGrupoADDA110JurosTit().getVlrPercJurosTit(), boletoDDA);
        } else {
            jurosIsento(boletoDDA);
        }

        // Desnormalizada BoletoDDAMulta, agora atributo na boletoDDA
        if (!ObjectUtil.isNull(conteudo.getGrupoADDA110MultaTit())) {
            setBoletoDDAMulta(conteudo.getGrupoADDA110MultaTit().getDtMultaTit(), conteudo.getGrupoADDA110MultaTit().getCodMultaTit(),
                    conteudo.getGrupoADDA110MultaTit().getVlrPercMultaTit(), boletoDDA);
        } else {
            multaIsento(boletoDDA);
        }

        if (!ObjectUtil.isEmpty(conteudo.getGrupoADDA110DesctTit())) {
            converterBoletoDDADesconto(conteudo, boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        setBoletoDDAValorRecebido(boletoDDA, conteudo.getTpAutcRecbtVlrDivgte(), conteudo.getTpVlrPercMinTit(), conteudo.getVlrPercMinTit(), conteudo.getTpVlrPercMaxTit(),
                conteudo.getVlrPercMaxTit());

        boletoDDA.setCodTipoModeloCalculo(conteudo.getTpModlCalc());

        if (!ObjectUtil.isEmpty(conteudo.getGrupoADDA110Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoADDA110CalcComplexType grupoCalculo : conteudo.getGrupoADDA110Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoCalculo.getVlrCalcdJuros(), grupoCalculo.getVlrCalcdMulta(), grupoCalculo.getVlrCalcdDesct(),
                        grupoCalculo.getVlrTotCobrar(),
                        ObjectUtil.isNull(grupoCalculo.getDtValiddCalc()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(grupoCalculo.getDtValiddCalc())));
            }

            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }

        boletoDDA.setValorSaldoPagamento(conteudo.getVlrSldTotAtlPgtoTit());

        setBoletoDDABaixaOper(boletoDDA, conteudo);

        setBoletoDDABaixaEfet(boletoDDA, conteudo);

        boletoDDA.setCodSituacaoBoletoPagamento(conteudo.getSitTitPgto());

        setBoletoDDAAceite(boletoDDA, ObjectUtil.isNull(conteudo.getQtdPgtoParclRegtd()) ? 0 : conteudo.getQtdPgtoParclRegtd().intValue());
        return boletoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(GrupoDDA0106R1TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        BoletoDDA boletoDDAMerge = converter(boletoRetorno);
        mergeIdBoletoDDA(boletoDDA, boletoDDAMerge);
        return boletoDDAMerge;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(GrupoADDA106TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        BoletoDDA boletoDDAMerge = converter(boletoRetorno);
        mergeIdBoletoDDA(boletoDDA, boletoDDAMerge);
        return boletoDDAMerge;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(GrupoDDA0127TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        BoletoDDA boletoDDAMerge = converter(boletoRetorno);
        boletoDDAMerge.setId(boletoDDA.getId());
        boletoDDAMerge.setDataHoraInclusao(!ObjectUtil.isNull(boletoDDA.getDataHoraInclusao()) ? boletoDDA.getDataHoraInclusao() : new DateTimeDB());
        boletoDDAMerge.setDataHoraUltimaAtualizacao(!ObjectUtil.isNull(boletoDDA.getDataHoraUltimaAtualizacao()) ? boletoDDA.getDataHoraUltimaAtualizacao() : new DateTimeDB());
        return boletoDDAMerge;
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA merge(GrupoADDA127TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        BoletoDDA boletoDDAMerge = converter(boletoRetorno);
        boletoDDAMerge.setId(boletoDDA.getId());
        boletoDDAMerge.setDataHoraInclusao(!ObjectUtil.isNull(boletoDDA.getDataHoraInclusao()) ? boletoDDA.getDataHoraInclusao() : new DateTimeDB());
        boletoDDAMerge.setDataHoraUltimaAtualizacao(!ObjectUtil.isNull(boletoDDA.getDataHoraUltimaAtualizacao()) ? boletoDDA.getDataHoraUltimaAtualizacao() : new DateTimeDB());
        return boletoDDAMerge;
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param idBoletoDDAJuros
     * @param idBoletoDDAMulta
     * @param boletoDDAMerge void
     * 
     */
    private static void mergeIdBoletoDDA(BoletoDDA boletoDDA, BoletoDDA boletoDDAMerge) {
        boletoDDAMerge.setId(boletoDDA.getId());
        boletoDDAMerge.setDataHoraInclusao(!ObjectUtil.isNull(boletoDDA.getDataHoraInclusao()) ? boletoDDA.getDataHoraInclusao() : new DateTimeDB());
        boletoDDAMerge.setDataHoraUltimaAtualizacao(!ObjectUtil.isNull(boletoDDA.getDataHoraUltimaAtualizacao()) ? boletoDDA.getDataHoraUltimaAtualizacao() : new DateTimeDB());
    }

    /**
     * Método responsável por
     * 
     * @param mensagemDDABoleto
     * @param boletoDDA void
     * 
     */
    private static void setBoletoDDAGrupoCalculo(MensagemDDABoleto mensagemDDABoleto, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getListaMensagemDDABoletoGrupoCalculo())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (MensagemDDABoletoGrupoCalculo mensagemDDABoletoGrupoCalculo : mensagemDDABoleto.getListaMensagemDDABoletoGrupoCalculo()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, mensagemDDABoletoGrupoCalculo.getValorCalculadoJuros(), mensagemDDABoletoGrupoCalculo.getValorCalculadoMulta(),
                        mensagemDDABoletoGrupoCalculo.getValorCalculadoDesconto(), mensagemDDABoletoGrupoCalculo.getValorTotalCobrado(),
                        mensagemDDABoletoGrupoCalculo.getDataValidadeCalculo()));
            }

            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDADesconto(DDA0101R2ComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0101R2DesctTit())) {
            int cont = boletoRetorno.getGrupoDDA0101R2DesctTit().size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0101R2DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0101R2DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0101R2DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoDDA0101R2DesctTit().get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0101R2DesctTit().get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0101R2DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0101R2DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0101R2DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoDDA0101R2DesctTit().get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0101R2DesctTit().get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0101R2DesctTit().get(2).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0101R2DesctTit().get(2).getCodDesctTit(), boletoRetorno.getGrupoDDA0101R2DesctTit().get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDADesconto(GrupoADDA101RR2TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA101RR2DesctTit())) {
            int cont = boletoRetorno.getGrupoADDA101RR2DesctTit().size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA101RR2DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA101RR2DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA101RR2DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoADDA101RR2DesctTit().get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA101RR2DesctTit().get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA101RR2DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA101RR2DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA101RR2DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoADDA101RR2DesctTit().get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA101RR2DesctTit().get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA101RR2DesctTit().get(2).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA101RR2DesctTit().get(2).getCodDesctTit(), boletoRetorno.getGrupoADDA101RR2DesctTit().get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDADesconto(GrupoDDA0106R1TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0106R1DesctTit())) {
            int cont = boletoRetorno.getGrupoDDA0106R1DesctTit().size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0106R1DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0106R1DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0106R1DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoDDA0106R1DesctTit().get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0106R1DesctTit().get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0106R1DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0106R1DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0106R1DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoDDA0106R1DesctTit().get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0106R1DesctTit().get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0106R1DesctTit().get(2).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0106R1DesctTit().get(2).getCodDesctTit(), boletoRetorno.getGrupoDDA0106R1DesctTit().get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDADesconto(GrupoADDA106TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA106DesctTit())) {
            int cont = boletoRetorno.getGrupoADDA106DesctTit().size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA106DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA106DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA106DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA106DesctTit().get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA106DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA106DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA106DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA106DesctTit().get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA106DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA106DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA106DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoADDA106DesctTit().get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA106DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA106DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA106DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA106DesctTit().get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA106DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA106DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA106DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoADDA106DesctTit().get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA106DesctTit().get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA106DesctTit().get(2).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA106DesctTit().get(2).getCodDesctTit(), boletoRetorno.getGrupoADDA106DesctTit().get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDADesconto(GrupoDDA0127TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0127DesctTit())) {
            int cont = boletoRetorno.getGrupoDDA0127DesctTit().size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0127DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0127DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0127DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0127DesctTit().get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0127DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0127DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0127DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0127DesctTit().get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0127DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0127DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0127DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoDDA0127DesctTit().get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0127DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0127DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0127DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0127DesctTit().get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0127DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0127DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0127DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoDDA0127DesctTit().get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0127DesctTit().get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0127DesctTit().get(2).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0127DesctTit().get(2).getCodDesctTit(), boletoRetorno.getGrupoDDA0127DesctTit().get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDADesconto(GrupoADDA127TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA127DesctTit())) {
            int cont = boletoRetorno.getGrupoADDA127DesctTit().size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA127DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA127DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA127DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA127DesctTit().get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA127DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA127DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA127DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA127DesctTit().get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA127DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA127DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA127DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoADDA127DesctTit().get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA127DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA127DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA127DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA127DesctTit().get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA127DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA127DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA127DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoADDA127DesctTit().get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA127DesctTit().get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA127DesctTit().get(2).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA127DesctTit().get(2).getCodDesctTit(), boletoRetorno.getGrupoADDA127DesctTit().get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param boletoDDA void
     */
    private static void converterBoletoDDADesconto(DDA0110R1ComplexType conteudo, BoletoDDA boletoDDA) {
        int cont = conteudo.getGrupoDDA0110R1DesctTit().size();

        if (cont == 1) {
            setBoletoDDADesconto1(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoDDA0110R1DesctTit().get(0).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoDDA0110R1DesctTit().get(0).getDtDesctTit()),
                    conteudo.getGrupoDDA0110R1DesctTit().get(0).getCodDesctTit(), conteudo.getGrupoDDA0110R1DesctTit().get(0).getVlrPercDesctTit());
        } else if (cont == 2) {
            setBoletoDDADesconto1(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoDDA0110R1DesctTit().get(0).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoDDA0110R1DesctTit().get(0).getDtDesctTit()),
                    conteudo.getGrupoDDA0110R1DesctTit().get(0).getCodDesctTit(), conteudo.getGrupoDDA0110R1DesctTit().get(0).getVlrPercDesctTit());

            setBoletoDDADesconto2(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoDDA0110R1DesctTit().get(1).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoDDA0110R1DesctTit().get(1).getDtDesctTit()),
                    conteudo.getGrupoDDA0110R1DesctTit().get(1).getCodDesctTit(), conteudo.getGrupoDDA0110R1DesctTit().get(1).getVlrPercDesctTit());
        } else if (cont == 3) {
            setBoletoDDADesconto1(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoDDA0110R1DesctTit().get(0).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoDDA0110R1DesctTit().get(0).getDtDesctTit()),
                    conteudo.getGrupoDDA0110R1DesctTit().get(0).getCodDesctTit(), conteudo.getGrupoDDA0110R1DesctTit().get(0).getVlrPercDesctTit());
            setBoletoDDADesconto2(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoDDA0110R1DesctTit().get(1).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoDDA0110R1DesctTit().get(1).getDtDesctTit()),
                    conteudo.getGrupoDDA0110R1DesctTit().get(1).getCodDesctTit(), conteudo.getGrupoDDA0110R1DesctTit().get(1).getVlrPercDesctTit());

            setBoletoDDADesconto3(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoDDA0110R1DesctTit().get(2).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoDDA0110R1DesctTit().get(2).getDtDesctTit()),
                    conteudo.getGrupoDDA0110R1DesctTit().get(2).getCodDesctTit(), conteudo.getGrupoDDA0110R1DesctTit().get(2).getVlrPercDesctTit());
        }
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param boletoDDA void
     */
    private static void converterBoletoDDADesconto(GrupoADDA110RETTitActoComplexType conteudo, BoletoDDA boletoDDA) {
        int cont = conteudo.getGrupoADDA110DesctTit().size();

        if (cont == 1) {
            setBoletoDDADesconto1(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoADDA110DesctTit().get(0).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoADDA110DesctTit().get(0).getDtDesctTit()),
                    conteudo.getGrupoADDA110DesctTit().get(0).getCodDesctTit(), conteudo.getGrupoADDA110DesctTit().get(0).getVlrPercDesctTit());
        } else if (cont == 2) {
            setBoletoDDADesconto1(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoADDA110DesctTit().get(0).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoADDA110DesctTit().get(0).getDtDesctTit()),
                    conteudo.getGrupoADDA110DesctTit().get(0).getCodDesctTit(), conteudo.getGrupoADDA110DesctTit().get(0).getVlrPercDesctTit());

            setBoletoDDADesconto2(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoADDA110DesctTit().get(1).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoADDA110DesctTit().get(1).getDtDesctTit()),
                    conteudo.getGrupoADDA110DesctTit().get(1).getCodDesctTit(), conteudo.getGrupoADDA110DesctTit().get(1).getVlrPercDesctTit());
        } else if (cont == 3) {
            setBoletoDDADesconto1(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoADDA110DesctTit().get(0).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoADDA110DesctTit().get(0).getDtDesctTit()),
                    conteudo.getGrupoADDA110DesctTit().get(0).getCodDesctTit(), conteudo.getGrupoADDA110DesctTit().get(0).getVlrPercDesctTit());
            setBoletoDDADesconto2(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoADDA110DesctTit().get(1).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoADDA110DesctTit().get(1).getDtDesctTit()),
                    conteudo.getGrupoADDA110DesctTit().get(1).getCodDesctTit(), conteudo.getGrupoADDA110DesctTit().get(1).getVlrPercDesctTit());

            setBoletoDDADesconto3(boletoDDA,
                    ObjectUtil.isNull(conteudo.getGrupoADDA110DesctTit().get(2).getDtDesctTit()) ? null
                            : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getGrupoADDA110DesctTit().get(2).getDtDesctTit()),
                    conteudo.getGrupoADDA110DesctTit().get(2).getCodDesctTit(), conteudo.getGrupoADDA110DesctTit().get(2).getVlrPercDesctTit());
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDAGrupoCalculo(DDA0101R2ComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0101R2Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoDDA0101R2CalcComplexType grupoDDA : boletoRetorno.getGrupoDDA0101R2Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoDDA.getVlrCalcdJuros(), grupoDDA.getVlrCalcdMulta(), grupoDDA.getVlrCalcdDesct(), grupoDDA.getVlrTotCobrar(),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA.getDtValiddCalc())));
            }
            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDAGrupoCalculo(GrupoDDA0106R1TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0106R1Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoDDA0106R1CalcComplexType grupoDDA : boletoRetorno.getGrupoDDA0106R1Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoDDA.getVlrCalcdJuros(), grupoDDA.getVlrCalcdMulta(), grupoDDA.getVlrCalcdDesct(), grupoDDA.getVlrTotCobrar(),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA.getDtValiddCalc())));
            }
            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDAGrupoCalculo(GrupoDDA0127TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0127Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoDDA0127CalcComplexType grupoDDA : boletoRetorno.getGrupoDDA0127Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoDDA.getVlrCalcdJuros(), grupoDDA.getVlrCalcdMulta(), grupoDDA.getVlrCalcdDesct(), grupoDDA.getVlrTotCobrar(),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA.getDtValiddCalc())));
            }
            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDAGrupoCalculo(GrupoADDA106TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA106Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoADDA106CalcComplexType grupoDDA : boletoRetorno.getGrupoADDA106Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoDDA.getVlrCalcdJuros(), grupoDDA.getVlrCalcdMulta(), grupoDDA.getVlrCalcdDesct(), grupoDDA.getVlrTotCobrar(),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA.getDtValiddCalc())));
            }
            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDAGrupoCalculo(GrupoADDA127TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA127Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoADDA127CalcComplexType grupoDDA : boletoRetorno.getGrupoADDA127Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoDDA.getVlrCalcdJuros(), grupoDDA.getVlrCalcdMulta(), grupoDDA.getVlrCalcdDesct(), grupoDDA.getVlrTotCobrar(),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA.getDtValiddCalc())));
            }
            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void mergeBoletoDDADesconto(GrupoADDA102RR2TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA102RR2DesctTit())) {
            int cont = boletoRetorno.getGrupoADDA102RR2DesctTit().size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA102RR2DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA102RR2DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoADDA102RR2DesctTit().get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoADDA102RR2DesctTit().get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA102RR2DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA102RR2DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoADDA102RR2DesctTit().get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoADDA102RR2DesctTit().get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoADDA102RR2DesctTit().get(2).getDtDesctTit()),
                        boletoRetorno.getGrupoADDA102RR2DesctTit().get(2).getCodDesctTit(), boletoRetorno.getGrupoADDA102RR2DesctTit().get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void mergeBoletoDDAGrupoCalculo(GrupoADDA102RR2TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA102RR2Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoADDA102RR2CalcComplexType grupoDDA0101R2Calc : boletoRetorno.getGrupoADDA102RR2Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoDDA0101R2Calc.getVlrCalcdJuros(), grupoDDA0101R2Calc.getVlrCalcdMulta(), grupoDDA0101R2Calc.getVlrCalcdDesct(),
                        grupoDDA0101R2Calc.getVlrTotCobrar(), DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0101R2Calc.getDtValiddCalc())));
            }
            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void converterBoletoDDAGrupoCalculo(GrupoADDA101RR2TitComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoADDA101RR2Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoADDA101RR2CalcComplexType grupoDDA0101R2Calc : boletoRetorno.getGrupoADDA101RR2Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoDDA0101R2Calc.getVlrCalcdJuros(), grupoDDA0101R2Calc.getVlrCalcdMulta(), grupoDDA0101R2Calc.getVlrCalcdDesct(),
                        grupoDDA0101R2Calc.getVlrTotCobrar(), DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0101R2Calc.getDtValiddCalc())));
            }
            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param idCarteira
     * @param codEspecie
     * @param numDocumento
     * @param codTipoPagamento
     * @param numParcela
     * @param qtdTotalParcela
     * @param indrTituloNegociado void
     * 
     */
    private static void setBoletoDDADocumentacao(BoletoDDA boletoDDA, String idCarteira, String codEspecie, String numDocumento, BigInteger codTipoPagamento, BigInteger numParcela,
            BigInteger qtdTotalParcela, String indrTituloNegociado) {
        setBoletoDDADocumentacao(boletoDDA, Integer.valueOf(idCarteira), Integer.valueOf(codEspecie), numDocumento, codTipoPagamento.intValue(),
                ObjectUtil.isEmpty(numParcela) ? null : numParcela.intValue(), ObjectUtil.isEmpty(qtdTotalParcela) ? null : qtdTotalParcela.intValue(), indrTituloNegociado);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param idCarteira
     * @param codEspecie
     * @param numDocumento
     * @param codTipoPagamento
     * @param numParcela
     * @param qtdTotalParcela
     * @param indrTituloNegociado void
     * 
     */
    private static void setBoletoDDADocumentacao(BoletoDDA boletoDDA, Integer idCarteira, Integer codEspecie, String numDocumento, Integer codTipoPagamento, Integer numParcela,
            Integer qtdTotalParcela, String indrTituloNegociado) {
        boletoDDA.setIdCarteira(idCarteira);
        boletoDDA.setIdEspecieDocumento(codEspecie);
        boletoDDA.setNumDocumento(numDocumento);
        boletoDDA.setCodTipoPagamento(codTipoPagamento);
        boletoDDA.setNumParcela(numParcela);
        boletoDDA.setQtdTotalParcela(qtdTotalParcela);
        boletoDDA.setBolTituloNegociado(indrTituloNegociado.equals("S"));
    }

    /**
     * Método responsável por
     * 
     * @param identcSacdrAvalst
     * @param tpIdentcSacdrAvalst
     * @param nomRzSocSacdrAvalst
     * @param boletoDDA void
     * 
     */
    private static void setBoletoDDASacadorAvalista(BoletoDDA boletoDDA, BigInteger codTipoPessoaDDAAvalista, String numCpfCnpjAvalista, String nomeAvalista) {
        setBoletoDDASacadorAvalista(boletoDDA, (ObjectUtil.isNull(codTipoPessoaDDAAvalista) ? null : codTipoPessoaDDAAvalista.toString()), numCpfCnpjAvalista, nomeAvalista);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param codTipoPessoaDDAAvalista
     * @param numCpfCnpjAvalista
     * @param nomeAvalista void
     * 
     */
    private static void setBoletoDDASacadorAvalista(BoletoDDA boletoDDA, String codTipoPessoaDDAAvalista, String numCpfCnpjAvalista, String nomeAvalista) {
        boletoDDA.setNumCpfCnpjCodTipoPessoaAvalista(numCpfCnpjAvalista, codTipoPessoaDDAAvalista);
        boletoDDA.setNomeAvalista(nomeAvalista);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param dataVencimento
     * @param valorBoleto
     * @param numDiasProtesto
     * @param dataLimitePagamento
     * @param indrBloqueioPagamento
     * @param valorAbatimento
     * @param qtdPagamentoParcial
     * @param codTipoModeloCalculo void
     * 
     */
    private static void setBoletoDDAPagamento(BoletoDDA boletoDDA, XMLGregorianCalendar dataVencimento, BigDecimal valorBoleto, BigInteger numDiasProtesto,
            XMLGregorianCalendar dataLimitePagamento, String indrBloqueioPagamento, BigDecimal valorAbatimento, BigInteger qtdPagamentoParcial, String codTipoModeloCalculo) {
        setBoletoDDAPagamento(boletoDDA, dataVencimento, valorBoleto, numDiasProtesto, dataLimitePagamento, indrBloqueioPagamento, valorAbatimento, qtdPagamentoParcial);
        boletoDDA.setCodTipoModeloCalculo(codTipoModeloCalculo);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param dtVencTit
     * @param vlrTit
     * @param qtdDiaPrott
     * @param dtLimPgtoTit
     * @param indrBloqPgto
     * @param vlrAbattTit
     * @param qtdPgtoParcl void
     * 
     */
    private static void setBoletoDDAPagamento(BoletoDDA boletoDDA, XMLGregorianCalendar dataVencimento, BigDecimal valorBoleto, BigInteger numDiasProtesto,
            XMLGregorianCalendar dataLimitePagamento, String indrBloqueioPagamento, BigDecimal valorAbatimento, BigInteger qtdPagamentoParcial) {
        setBoletoDDAPagamento(boletoDDA, ObjectUtil.isNull(dataVencimento) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(dataVencimento), valorBoleto,
                ObjectUtil.isNull(numDiasProtesto) ? null : numDiasProtesto.intValue(),
                ObjectUtil.isNull(dataLimitePagamento) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(dataLimitePagamento), indrBloqueioPagamento, valorAbatimento,
                ObjectUtil.isNull(qtdPagamentoParcial) ? null : qtdPagamentoParcial.intValue());
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param dataVencimento
     * @param valorBoleto
     * @param numDiasProtesto
     * @param dataLimitePagamento
     * @param indrBloqueioPagamento
     * @param valorAbatimento
     * @param qtdPagamentoParcial void
     * 
     */
    private static void setBoletoDDAPagamento(BoletoDDA boletoDDA, DateTimeDB dataVencimento, BigDecimal valorBoleto, Integer numDiasProtesto, DateTimeDB dataLimitePagamento,
            String indrBloqueioPagamento, BigDecimal valorAbatimento, Integer qtdPagamentoParcial) {
        setBoletoDDAPagamento(boletoDDA, dataVencimento, valorBoleto, numDiasProtesto, dataLimitePagamento, indrBloqueioPagamento, valorAbatimento);
        boletoDDA.setQtdPagamentoParcial(qtdPagamentoParcial);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param dataVencimento
     * @param valorBoleto
     * @param numDiasProtesto
     * @param dataLimitePagamento
     * @param indrBloqueioPagamento
     * @param valorAbatimento void
     * 
     */
    private static void setBoletoDDAPagamento(BoletoDDA boletoDDA, DateTimeDB dataVencimento, BigDecimal valorBoleto, Integer numDiasProtesto, DateTimeDB dataLimitePagamento,
            String indrBloqueioPagamento, BigDecimal valorAbatimento) {
        boletoDDA.setDataVencimento(dataVencimento);
        boletoDDA.setValorBoleto(valorBoleto);
        boletoDDA.setNumDiasProtesto(numDiasProtesto);
        boletoDDA.setDataLimitePagamento(dataLimitePagamento);
        boletoDDA.setBolBloqueioPagamento(indrBloqueioPagamento.equals("S"));
        boletoDDA.setValorAbatimento(valorAbatimento);
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void setBoletoDDADescontoR2(DDA0102R2ComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0102R2DesctTit())) {
            int cont = boletoRetorno.getGrupoDDA0102R2DesctTit().size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0102R2DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0102R2DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoDDA0102R2DesctTit().get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getCodDesctTit(), boletoRetorno.getGrupoDDA0102R2DesctTit().get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2DesctTit().get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0102R2DesctTit().get(1).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0102R2DesctTit().get(1).getCodDesctTit(), boletoRetorno.getGrupoDDA0102R2DesctTit().get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(boletoRetorno.getGrupoDDA0102R2DesctTit().get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(boletoRetorno.getGrupoDDA0102R2DesctTit().get(2).getDtDesctTit()),
                        boletoRetorno.getGrupoDDA0102R2DesctTit().get(2).getCodDesctTit(), boletoRetorno.getGrupoDDA0102R2DesctTit().get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoRetorno
     * @param boletoDDA void
     * 
     */
    private static void setBoletoDDAGrupoCalculoR2(DDA0102R2ComplexType boletoRetorno, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(boletoRetorno.getGrupoDDA0102R2Calc())) {
            List<BoletoDDAGrupoCalculo> lista = new ArrayList<>();

            for (GrupoDDA0102R2CalcComplexType grupoDDA0102R2Calc : boletoRetorno.getGrupoDDA0102R2Calc()) {
                lista.add(new BoletoDDAGrupoCalculo(boletoDDA, grupoDDA0102R2Calc.getVlrCalcdJuros(), grupoDDA0102R2Calc.getVlrCalcdMulta(), grupoDDA0102R2Calc.getVlrCalcdDesct(),
                        grupoDDA0102R2Calc.getVlrTotCobrar(), DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0102R2Calc.getDtValiddCalc())));
            }

            boletoDDA.setListaBoletoDDAGrupoCalculo(lista);
        }
    }

    /**
     * Método responsável por setar a identificação do boleto.
     * 
     * @param boletoDDA
     * @param numIdentificacaoBoleto
     * @param numReferenciaBoleto
     * @param numSeqBoleto
     * @param codISPBPartDestinatario
     * @param codPartDestinatario void
     * 
     */
    private static void setBoletoDDAIdentificacao(BoletoDDA boletoDDA, BigInteger numIdentificacaoBoleto, BigInteger numReferenciaBoleto, BigInteger numSeqBoleto,
            String codISPBPartDestinatario, String codPartDestinatario) {
        boletoDDA.setCodPartDestinatario(codPartDestinatario);
        setBoletoDDAIdentificacao(boletoDDA, numIdentificacaoBoleto, numReferenciaBoleto, numSeqBoleto, codISPBPartDestinatario);
    }

    /**
     * Método responsável por setar a identificação do boleto.
     * 
     * @param numIdentificacaoBoleto
     * @param numReferenciaBoleto
     * @param numSeqBoleto
     * @param codISPBPartDestinatario
     * @param boletoDDA void
     * 
     */
    private static void setBoletoDDAIdentificacao(BoletoDDA boletoDDA, BigInteger numIdentificacaoBoleto, BigInteger numReferenciaBoleto, BigInteger numSeqBoleto,
            String codISPBPartDestinatario) {
        boletoDDA.setNumIdentificadorBoletoCip(numIdentificacaoBoleto.longValue());
        boletoDDA.setNumRefAtualCadBoleto(ObjectUtil.isEmpty(numReferenciaBoleto) ? null : numReferenciaBoleto.longValue());
        boletoDDA.setNumSeqAtualCadBoleto(numSeqBoleto.longValue());
        boletoDDA.setCodIspbPartDestinatario(codISPBPartDestinatario);
        boletoDDA.setDataHoraSituacaoBoleto(new DateTimeDB());
    }

    /**
     * Método responsável por
     * 
     * @param codIndicadorValorMinimo
     * @param valorMinimo
     * @param codIndicadorValorMaximo
     * @param valorMaximo
     * @param codTipoModeloCalculo
     * @param codAutorizacaoValorDivergente
     * @param boletoDDA void
     * 
     */
    private static void setBoletoDDAValorRecebido(String codIndicadorValorMinimo, BigDecimal valorMinimo, String codIndicadorValorMaximo, BigDecimal valorMaximo,
            String codTipoModeloCalculo, String codAutorizacaoValorDivergente, BoletoDDA boletoDDA) {
        setBoletoDDAValorRecebido(boletoDDA, codAutorizacaoValorDivergente, codIndicadorValorMinimo, valorMinimo, codIndicadorValorMaximo, valorMaximo);
        boletoDDA.setCodTipoModeloCalculo(codTipoModeloCalculo);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param codAutorizacaoValorDivergente
     * @param codIndicadorValorMinimo
     * @param valorMinimo
     * @param codIndicadorValorMaximo
     * @param valorMaximo void
     * 
     */
    private static void setBoletoDDAValorRecebido(BoletoDDA boletoDDA, String codAutorizacaoValorDivergente, String codIndicadorValorMinimo, BigDecimal valorMinimo,
            String codIndicadorValorMaximo, BigDecimal valorMaximo) {
        boletoDDA.setCodAutorizacaoValorDivergente(codAutorizacaoValorDivergente);
        boletoDDA.setCodIndicadorValorMinimo(codIndicadorValorMinimo);
        boletoDDA.setValorMinimo(valorMinimo);
        boletoDDA.setCodIndicadorValorMaximo(codIndicadorValorMaximo);
        boletoDDA.setValorMaximo(valorMaximo);
    }

    /**
     * Método responsável por setar as informações no BoletoDDABeneficiario
     * 
     * @param boletoDDA
     * @param tpPessoaBeneficiario
     * @param cnpjCpfBeneficiario
     * @param nomeBeneficiario
     * @param nomeFantasiaBeneficiario
     * @param logradouroBeneficiario
     * @param cidadeBeneficiario
     * @param UFBeneficiario
     * @param cepBeneficiario void
     * 
     */
    private static void setBoletoDDABeneficiario(BoletoDDA boletoDDA, String tpPessoaBeneficiario, BigInteger cnpjCpfBeneficiario, String nomeBeneficiario,
            String nomeFantasiaBeneficiario, String logradouroBeneficiario, String cidadeBeneficiario, String ufBeneficiario, BigInteger cepBeneficiario) {
        boletoDDA.setNumCpfCnpjCodTipoPessoaBeneficiario(ObjectUtil.isNull(cnpjCpfBeneficiario) ? null : cnpjCpfBeneficiario.toString(), tpPessoaBeneficiario);
        boletoDDA.setNomeBeneficiario(nomeBeneficiario);
        setBoletoDDABeneficiario(boletoDDA, nomeFantasiaBeneficiario, logradouroBeneficiario, cidadeBeneficiario, ufBeneficiario, cepBeneficiario);
    }

    /**
     * Método responsável por setar as informações no BoletoDDABeneficiario
     * 
     * @param boletoDDA
     * @param tpPessoaBeneficiario
     * @param cnpjCpfBeneficiario
     * @param nomeBeneficiario
     * @param nomeFantasiaBeneficiario
     * @param logradouroBeneficiario
     * @param cidadeBeneficiario
     * @param UFBeneficiario
     * @param cepBeneficiario void
     * 
     */
    private static void setBoletoDDABeneficiario(BoletoDDA boletoDDA, String tpPessoaBeneficiario, String cnpjCpfBeneficiario, String nomeBeneficiario,
            String nomeFantasiaBeneficiario, String logradouroBeneficiario, String cidadeBeneficiario, String ufBeneficiario, String cepBeneficiario) {
        boletoDDA.setNumCpfCnpjCodTipoPessoaBeneficiario(cnpjCpfBeneficiario, tpPessoaBeneficiario);
        boletoDDA.setNomeBeneficiario(nomeBeneficiario);
        setBoletoDDABeneficiario(boletoDDA, nomeFantasiaBeneficiario, logradouroBeneficiario, cidadeBeneficiario, ufBeneficiario, cepBeneficiario);
    }

    /**
     * Método responsável por setar o Beneficiario do Boleto.
     * 
     * @param boletoDDA
     * @param nomeFantasia
     * @param logradouro
     * @param cidade
     * @param ufBeneficiario
     * @param numCepBeneficiario void
     * 
     */
    private static void setBoletoDDABeneficiario(BoletoDDA boletoDDA, String nomeFantasia, String logradouro, String cidade, String ufBeneficiario, String numCepBeneficiario) {
        boletoDDA.setNomeFantasiaBeneficiario(nomeFantasia);
        boletoDDA.setDescLogradouroBeneficiario(logradouro);
        boletoDDA.setDescCidadeBeneficiario(cidade);
        boletoDDA.setSiglaUfBeneficiario(ufBeneficiario);
        boletoDDA.setNumCepBeneficiario(numCepBeneficiario);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param nomeFantasia
     * @param logradouro
     * @param cidade
     * @param ufBeneficiario
     * @param numCepBeneficiario void
     * 
     */
    private static void setBoletoDDABeneficiario(BoletoDDA boletoDDA, String nomeFantasia, String logradouro, String cidade, String ufBeneficiario, BigInteger numCepBeneficiario) {
        setBoletoDDABeneficiario(boletoDDA, nomeFantasia, logradouro, cidade, ufBeneficiario, ObjectUtil.isEmpty(numCepBeneficiario) ? null : numCepBeneficiario.toString());
    }

    /**
     * 
     * Método responsável por setar as informações do pagador no bojeto boletoDDAPagador
     * 
     * @param boletoDDA
     * @param tpPessoaPagador
     * @param cnpjCpfPagador
     * @param nomePagador
     * @param nomeFantasiaPagador
     * @param logradouroPagador
     * @param cidadePagador
     * @param ufPagador
     * @param cepPagador void
     * 
     */
    private static void setBoletoDDAPagador(BoletoDDA boletoDDA, String tpPessoaPagador, BigInteger cnpjCpfPagador, String nomePagador, String nomeFantasiaPagador,
            String logradouroPagador, String cidadePagador, String ufPagador, BigInteger cepPagador) {
        boletoDDA.setNumCpfCnpjCodTipoPessoaPagador(ObjectUtil.isNull(cnpjCpfPagador) ? null : cnpjCpfPagador.toString(), tpPessoaPagador);
        setBoletoDDAPagador(boletoDDA, nomePagador, nomeFantasiaPagador, logradouroPagador, cidadePagador, ufPagador, cepPagador);
    }

    /**
     * Método responsável por setar as informações do pagador no bojeto boletoDDAPagador
     * 
     * @param boletoDDA
     * @param tpPessoaPagador
     * @param cnpjCpfPagador
     * @param nomePagador
     * @param nomeFantasiaPagador
     * @param logradouroPagador
     * @param cidadePagador
     * @param ufPagador
     * @param cepPagador void
     * 
     */
    private static void setBoletoDDAPagador(BoletoDDA boletoDDA, String tpPessoaPagador, String cnpjCpfPagador, String nomePagador, String nomeFantasiaPagador,
            String logradouroPagador, String cidadePagador, String ufPagador, String cepPagador) {
        boletoDDA.setNumCpfCnpjCodTipoPessoaPagador(cnpjCpfPagador, tpPessoaPagador);
        setBoletoDDAPagador(boletoDDA, nomePagador, nomeFantasiaPagador, logradouroPagador, cidadePagador, ufPagador, cepPagador);
    }

    /**
     * Método responsável por setar as informações do pagador no bojeto boletoDDAPagador
     * 
     * @param boletoDDA
     * @param nomeRazaoSocialPagador
     * @param nomeFantasiaPagador
     * @param logradouroPagador
     * @param cidadePagador
     * @param ufPagador
     * @param cepPagador void
     * 
     */
    private static void setBoletoDDAPagador(BoletoDDA boletoDDA, String nomeRazaoSocialPagador, String nomeFantasiaPagador, String logradouroPagador, String cidadePagador,
            String ufPagador, String cepPagador) {
        boletoDDA.setNomePagador(nomeRazaoSocialPagador);
        boletoDDA.setNomeFantasiaPagador(nomeFantasiaPagador);
        boletoDDA.setDescLogradouroPagador(logradouroPagador);
        boletoDDA.setDescCidadePagador(cidadePagador);
        boletoDDA.setSiglaUfPagador(ufPagador);
        boletoDDA.setNumCepPagador(cepPagador);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param nomeRazaoSocialPagador
     * @param nomeFantasiaPagador
     * @param logradouroPagador
     * @param cidadePagador
     * @param ufPagador
     * @param cepPagador void
     * 
     */
    private static void setBoletoDDAPagador(BoletoDDA boletoDDA, String nomeRazaoSocialPagador, String nomeFantasiaPagador, String logradouroPagador, String cidadePagador,
            String ufPagador, BigInteger cepPagador) {
        setBoletoDDAPagador(boletoDDA, nomeRazaoSocialPagador, nomeFantasiaPagador, logradouroPagador, cidadePagador, ufPagador,
                ObjectUtil.isEmpty(cepPagador) ? null : cepPagador.toString());
    }

    /**
     * Método responsável setar o beneficiario final do boleto
     * 
     * @param boletoDDA
     * @param tpPessoaBeneficiarioFinal
     * @param cnpjCpfBeneficiarioFinal
     * @param nomeBeneficiarioFinal
     * @param nomeFantasiaBeneficiarioFinal void
     * 
     */
    private static void setBoletoDDABeneficiarioFinal(BoletoDDA boletoDDA, String tpPessoaBeneficiarioFinal, String cnpjCpfBeneficiarioFinal, String nomeBeneficiarioFinal,
            String nomeFantasiaBeneficiarioFinal) {
        boletoDDA.setCodTipoPessoaBeneficiarioFinal(tpPessoaBeneficiarioFinal);
        if (!ObjectUtil.isEmpty(cnpjCpfBeneficiarioFinal)) {
            boletoDDA.setNumCpfCnpjBeneficiarioFinal(cnpjCpfBeneficiarioFinal, TipoPessoaEnum.getBy(tpPessoaBeneficiarioFinal));
        }
        boletoDDA.setNomeBeneficiarioFinal(nomeBeneficiarioFinal);
        boletoDDA.setNomeFantasiaBeneficiarioFinal(nomeFantasiaBeneficiarioFinal);
    }

    /**
     * Método responsável setar o beneficiario final do boleto
     * 
     * @param boletoDDA
     * @param tpPessoaBeneficiarioFinal
     * @param cnpjCpfBeneficiarioFinal
     * @param nomeBeneficiarioFinal
     * @param nomeFantasiaBeneficiarioFinal void
     * 
     */
    private static void setBoletoDDABeneficiarioFinal(BoletoDDA boletoDDA, String tpPessoaBeneficiarioFinal, BigInteger cnpjCpfBeneficiarioFinal, String nomeBeneficiarioFinal,
            String nomeFantasiaBeneficiarioFinal) {
        setBoletoDDABeneficiarioFinal(boletoDDA, tpPessoaBeneficiarioFinal, ObjectUtil.isEmpty(cnpjCpfBeneficiarioFinal) ? null : cnpjCpfBeneficiarioFinal.toString(),
                nomeBeneficiarioFinal, nomeFantasiaBeneficiarioFinal);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param codMoeda
     * @param numNossoNumero
     * @param numCodigoBarra
     * @param numLinhaDigitavel
     * @param dataEmissao
     * @param indicadorPagamentoParcial
     * @param codTipoPagamento void
     * 
     */
    private static void setBoletoDDADadosTitulo(BoletoDDA boletoDDA, String codMoeda, String numNossoNumero, String numCodigoBarra, String numLinhaDigitavel,
            XMLGregorianCalendar dataEmissao, String indicadorPagamentoParcial, BigInteger codTipoPagamento) {
        setBoletoDDADadosTitulo(boletoDDA, codMoeda, numNossoNumero, numCodigoBarra, numLinhaDigitavel, dataEmissao, indicadorPagamentoParcial);
        boletoDDA.setCodTipoPagamento(codTipoPagamento.intValue());
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param codMoedaCNAB
     * @param identdNossoNum
     * @param numCodBarras
     * @param numLinhaDigtvl
     * @param dtEmsTit
     * @param indrPgtoParcl void
     * 
     */
    private static void setBoletoDDADadosTitulo(BoletoDDA boletoDDA, String codMoeda, String numNossoNumero, String numCodigoBarra, String numLinhaDigitavel,
            XMLGregorianCalendar dataEmissao, String indicadorPagamentoParcial) {
        setBoletoDDADadosTitulo(boletoDDA, codMoeda, numNossoNumero, numCodigoBarra, numLinhaDigitavel, DataCipUtil.xMLGregorianCalendarParaDateTime(dataEmissao),
                indicadorPagamentoParcial.equals("S"));
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param codMoeda
     * @param numNossoNumero
     * @param numCodigoBarra
     * @param numLinhaDigitavel
     * @param dataEmissao
     * @param bolPagamentoParcial void
     * 
     */
    private static void setBoletoDDADadosTitulo(BoletoDDA boletoDDA, String codMoeda, String numNossoNumero, String numCodigoBarra, String numLinhaDigitavel,
            DateTimeDB dataEmissao, Boolean bolPagamentoParcial) {
        boletoDDA.setCodMoeda(codMoeda);
        boletoDDA.setNumNossoNumero(numNossoNumero);
        boletoDDA.setNumCodigoBarra(numCodigoBarra);
        boletoDDA.setNumCodBarrasCampoLivre(numCodigoBarra != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(numCodigoBarra) : null);
        boletoDDA.setNumLinhaDigitavel(numLinhaDigitavel);
        boletoDDA.setDataEmissao(dataEmissao);
        boletoDDA.setBolPagamentoParcial(bolPagamentoParcial);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA void
     * 
     */
    private static void setBoletoDDAAceite(BoletoDDA boletoDDA, Integer qtdPagamentoParcialReg) {
        boletoDDA.setBolAceite(Boolean.FALSE);
        boletoDDA.setQtdPagamentoParcialReg(qtdPagamentoParcialReg);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param indrActe
     * @param numRefAtlActe
     * @param numSeqAtualAceite void
     * 
     */
    private static void setBoletoDDAIndicadorAceite(BoletoDDA boletoDDA, String indrActe, BigInteger numRefAtlActe, BigInteger numSeqAtualAceite) {
        if (!ObjectUtil.isNull(indrActe) && indrActe.equals("S")) {
            boletoDDA.setBolAceite(true);
        } else {
            boletoDDA.setBolAceite(false);
        }
        boletoDDA.setNumRefAtualCadAceite(ObjectUtil.isNull(numRefAtlActe) ? null : numRefAtlActe.longValue());
        boletoDDA.setNumSeqAtualAceite(ObjectUtil.isNull(numSeqAtualAceite) ? null : numSeqAtualAceite.longValue());
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param dataHoraSitBoleto
     * @param qtdPagamentoParcialReg
     * @param valorSaldoPagamento
     * @param codSituacaoBoletoPagamento void
     * 
     */
    private static void setBoletoDDASaldoPagamento(BoletoDDA boletoDDA, XMLGregorianCalendar dataHoraSitBoleto, BigInteger qtdPagamentoParcialReg, BigDecimal valorSaldoPagamento,
            String codSituacaoBoletoPagamento, String codSituacaoBoleto) {
        boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(dataHoraSitBoleto));
        boletoDDA.setQtdPagamentoParcialReg(ObjectUtil.isNull(qtdPagamentoParcialReg) ? BigInteger.ZERO.intValue() : qtdPagamentoParcialReg.intValue());
        boletoDDA.setValorSaldoPagamento(valorSaldoPagamento);
        boletoDDA.setCodSituacaoBoletoPagamento(codSituacaoBoletoPagamento);
        boletoDDA.setCodSituacaoBoleto(ObjectUtil.isNull(codSituacaoBoleto) ? SituacaoBoleto.ABERTO : Integer.valueOf(codSituacaoBoleto));

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDATerceiroAutorizado(BoletoDDA boletoDDA, GrupoDDA0106R1TitComplexType boletoRetorno) {
        List<BoletoDDATerceiroAut> lista = new ArrayList<>();
        for (GrupoDDA0106R1TercComplexType grupoTerceiro : boletoRetorno.getGrupoDDA0106R1Terc()) {
            lista.add(new BoletoDDATerceiroAut(boletoDDA, grupoTerceiro.getTpPessoaPagdrAutzdr(), grupoTerceiro.getCNPJCPFPagdrAutzdr(), grupoTerceiro.getTpPessoaTerc(),
                    grupoTerceiro.getCNPJCPFTerc(), grupoTerceiro.getNumIdentcTerc(), grupoTerceiro.getNumRefAtlCadTerc(), grupoTerceiro.getSitTerc()));
        }
        boletoDDA.setListaBoletoDDATerceiroAutorizado(lista);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDATerceiroAutorizado(BoletoDDA boletoDDA, GrupoADDA106TitComplexType boletoRetorno) {
        List<BoletoDDATerceiroAut> lista = new ArrayList<>();
        for (GrupoADDA106TercComplexType grupoTerceiro : boletoRetorno.getGrupoADDA106Terc()) {
            lista.add(new BoletoDDATerceiroAut(boletoDDA, grupoTerceiro.getTpPessoaPagdrAutzdr(), grupoTerceiro.getCNPJCPFPagdrAutzdr(), grupoTerceiro.getTpPessoaTerc(),
                    grupoTerceiro.getCNPJCPFTerc(), grupoTerceiro.getNumIdentcTerc(), grupoTerceiro.getNumRefAtlCadTerc(), grupoTerceiro.getSitTerc()));
        }
        boletoDDA.setListaBoletoDDATerceiroAutorizado(lista);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDATerceiroAutorizado(BoletoDDA boletoDDA, GrupoADDA127TitComplexType boletoRetorno) {
        List<BoletoDDATerceiroAut> lista = new ArrayList<>();
        for (GrupoADDA127TercComplexType grupoTerceiro : boletoRetorno.getGrupoADDA127Terc()) {
            lista.add(new BoletoDDATerceiroAut(boletoDDA, grupoTerceiro.getTpPessoaPagdrAutdr(), grupoTerceiro.getCNPJCPFPagdrAutdr(), grupoTerceiro.getTpPessoaTerc(),
                    grupoTerceiro.getCNPJCPFTerc(), grupoTerceiro.getNumIdentcTerc(), grupoTerceiro.getNumRefAtlCadTerc(), grupoTerceiro.getSitTerc()));
        }
        boletoDDA.setListaBoletoDDATerceiroAutorizado(lista);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDATerceiroAutorizado(BoletoDDA boletoDDA, GrupoDDA0127TitComplexType boletoRetorno) {
        List<BoletoDDATerceiroAut> lista = new ArrayList<>();
        for (GrupoDDA0127TercComplexType grupoTerceiro : boletoRetorno.getGrupoDDA0127Terc()) {
            lista.add(new BoletoDDATerceiroAut(boletoDDA, grupoTerceiro.getTpPessoaPagdrAutzdr(), grupoTerceiro.getCNPJCPFPagdrAutzdr(), grupoTerceiro.getTpPessoaTerc(),
                    grupoTerceiro.getCNPJCPFTerc(), grupoTerceiro.getNumIdentcTerc(), grupoTerceiro.getNumRefAtlCadTerc(), grupoTerceiro.getSitTerc()));
        }
        boletoDDA.setListaBoletoDDATerceiroAutorizado(lista);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDABaixaEfet(BoletoDDA boletoDDA, GrupoDDA0106R1TitComplexType boletoRetorno) {
        List<BoletoDDABaixaEfet> lista = new ArrayList<>();
        for (GrupoDDA0106R1BaixaEftComplexType grupoBaixaEfet : boletoRetorno.getGrupoDDA0106R1BaixaEft()) {
            lista.add(new BoletoDDABaixaEfet(boletoDDA, grupoBaixaEfet.getNumIdentcBaixaEft(), grupoBaixaEfet.getNumRefAtlBaixaEft(), grupoBaixaEfet.getNumSeqAtlzBaixaEft(),
                    DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtProcBaixaEft()),
                    DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtHrProcBaixaEft()), grupoBaixaEfet.getVlrBaixaEftTit(),
                    grupoBaixaEfet.getNumCodBarrasBaixaEft(), grupoBaixaEfet.getCanPgtoBaixaEft(), grupoBaixaEfet.getMeioPgtoBaixaEft(),
                    grupoBaixaEfet.getNumIdentcBaixaOperacBaixaEft(), grupoBaixaEfet.getTpBaixaEft(), grupoBaixaEfet.getISPBPartRecbdrBaixaEft(),
                    grupoBaixaEfet.getCodPartRecbdrBaixaEft()));
        }
        boletoDDA.setListaBoletoDDABaixaEfet(lista);

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDABaixaEfet(BoletoDDA boletoDDA, GrupoADDA127TitComplexType boletoRetorno) {
        List<BoletoDDABaixaEfet> lista = new ArrayList<>();
        for (GrupoADDA127BaixaEftComplexType grupoBaixaEfet : boletoRetorno.getGrupoADDA127BaixaEft()) {
            lista.add(new BoletoDDABaixaEfet(boletoDDA, grupoBaixaEfet.getNumIdentcBaixaEft(), grupoBaixaEfet.getNumRefAtlBaixaEft(), grupoBaixaEfet.getNumSeqAtlzBaixaEft(),
                    DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtProcBaixaEft()),
                    DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtHrProcBaixaEft()), grupoBaixaEfet.getVlrBaixaEftTit(),
                    grupoBaixaEfet.getNumCodBarrasBaixaEft(), grupoBaixaEfet.getCanPgtoBaixaEft(), grupoBaixaEfet.getMeioPgtoBaixaEft(),
                    grupoBaixaEfet.getNumIdentcBaixaOperacBaixaEft(), grupoBaixaEfet.getTpBaixaEft(), grupoBaixaEfet.getISPBPartRecbdrBaixaEft(),
                    grupoBaixaEfet.getCodPartRecbdrBaixaEft()));
        }
        boletoDDA.setListaBoletoDDABaixaEfet(lista);

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDABaixaEfet(BoletoDDA boletoDDA, GrupoDDA0127TitComplexType boletoRetorno) {
        List<BoletoDDABaixaEfet> lista = new ArrayList<>();
        for (GrupoDDA0127BaixaEftComplexType grupoBaixaEfet : boletoRetorno.getGrupoDDA0127BaixaEft()) {
            lista.add(new BoletoDDABaixaEfet(boletoDDA, grupoBaixaEfet.getNumIdentcBaixaEft(), grupoBaixaEfet.getNumRefAtlBaixaEft(), grupoBaixaEfet.getNumSeqAtlzBaixaEft(),
                    DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtProcBaixaEft()),
                    DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtHrProcBaixaEft()), grupoBaixaEfet.getVlrBaixaEftTit(),
                    grupoBaixaEfet.getNumCodBarrasBaixaEft(), grupoBaixaEfet.getCanPgtoBaixaEft(), grupoBaixaEfet.getMeioPgtoBaixaEft(),
                    grupoBaixaEfet.getNumIdentcBaixaOperacBaixaEft(), grupoBaixaEfet.getTpBaixaEft(), grupoBaixaEfet.getISPBPartRecbdrBaixaEft(),
                    grupoBaixaEfet.getCodPartRecbdrBaixaEft()));
        }
        boletoDDA.setListaBoletoDDABaixaEfet(lista);

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDABaixaEfet(BoletoDDA boletoDDA, GrupoADDA106TitComplexType boletoRetorno) {
        List<BoletoDDABaixaEfet> lista = new ArrayList<>();
        for (GrupoADDA106BaixaEftComplexType grupoBaixaEfet : boletoRetorno.getGrupoADDA106BaixaEft()) {
            lista.add(new BoletoDDABaixaEfet(boletoDDA, grupoBaixaEfet.getNumIdentcBaixaEft(), grupoBaixaEfet.getNumRefAtlBaixaEft(), grupoBaixaEfet.getNumSeqAtlzBaixaEft(),
                    DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtProcBaixaEft()),
                    DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtHrProcBaixaEft()), grupoBaixaEfet.getVlrBaixaEftTit(),
                    grupoBaixaEfet.getNumCodBarrasBaixaEft(), grupoBaixaEfet.getCanPgtoBaixaEft(), grupoBaixaEfet.getMeioPgtoBaixaEft(),
                    grupoBaixaEfet.getNumIdentcBaixaOperacBaixaEft(), grupoBaixaEfet.getTpBaixaEft(), grupoBaixaEfet.getISPBPartRecbdrBaixaEft(),
                    grupoBaixaEfet.getCodPartRecbdrBaixaEft()));
        }
        boletoDDA.setListaBoletoDDABaixaEfet(lista);

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param conteudo void
     * 
     */
    private static void setBoletoDDABaixaEfet(BoletoDDA boletoDDA, DDA0110R1ComplexType conteudo) {
        if (!ObjectUtil.isEmpty(conteudo.getGrupoDDA0110R1BaixaEft())) {
            List<BoletoDDABaixaEfet> lista = new ArrayList<>();

            for (GrupoDDA0110R1BaixaEftComplexType grupoBaixaEfet : conteudo.getGrupoDDA0110R1BaixaEft()) {
                lista.add(new BoletoDDABaixaEfet(boletoDDA, grupoBaixaEfet.getNumIdentcBaixaEft(), grupoBaixaEfet.getNumRefAtlBaixaEft(), grupoBaixaEfet.getNumSeqAtlzBaixaEft(),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtProcBaixaEft()),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtHrProcBaixaEft()), grupoBaixaEfet.getVlrBaixaEftTit(),
                        grupoBaixaEfet.getNumCodBarrasBaixaEft(), grupoBaixaEfet.getCanPgto(), grupoBaixaEfet.getMeioPgto()));
            }

            boletoDDA.setListaBoletoDDABaixaEfet(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param conteudo void
     * 
     */
    private static void setBoletoDDABaixaEfet(BoletoDDA boletoDDA, GrupoADDA110RETTitActoComplexType conteudo) {
        if (!ObjectUtil.isEmpty(conteudo.getGrupoADDA110BaixaEft())) {
            List<BoletoDDABaixaEfet> lista = new ArrayList<>();
            for (GrupoADDA110BaixaEftComplexType grupoBaixaEfet : conteudo.getGrupoADDA110BaixaEft()) {
                lista.add(new BoletoDDABaixaEfet(boletoDDA, grupoBaixaEfet.getNumIdentcBaixaEft(), grupoBaixaEfet.getNumRefAtlBaixaEft(), grupoBaixaEfet.getNumSeqAtlzBaixaEft(),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtProcBaixaEft()),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaEfet.getDtHrProcBaixaEft()), grupoBaixaEfet.getVlrBaixaEftTit(),
                        grupoBaixaEfet.getNumCodBarrasBaixaEft(), grupoBaixaEfet.getCanPgto(), grupoBaixaEfet.getMeioPgto()));
            }
            boletoDDA.setListaBoletoDDABaixaEfet(lista);
        }
    }

    /**
     * Método responsável por setar a baixa operacional, vinculado a mensagem 106
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDABaixaOper(BoletoDDA boletoDDA, GrupoDDA0106R1TitComplexType boletoRetorno) {
        List<BoletoDDABaixaOper> lista = new ArrayList<>();
        BoletoDDABaixaOper boletoDDABaixaOper;
        for (GrupoDDA0106R1BaixaOperacComplexType grupoBaixaOper : boletoRetorno.getGrupoDDA0106R1BaixaOperac()) {
            boletoDDABaixaOper = new BoletoDDABaixaOper();
            boletoDDABaixaOper.setBoletoDDA(boletoDDA);
            boletoDDABaixaOper.setNumIdentificadorBaixaOper(grupoBaixaOper.getNumIdentcBaixaOperac());
            boletoDDABaixaOper.setNumRefAtualBaixaOper(grupoBaixaOper.getNumRefAtlBaixaOperac());
            boletoDDABaixaOper.setNumSeqAtualBaixaOper(grupoBaixaOper.getNumSeqAtlzBaixaOperac());
            boletoDDABaixaOper.setCodIspbPartRecebedorBaixaOperacional(grupoBaixaOper.getISPBPartRecbdrBaixaOperac());
            boletoDDABaixaOper.setCodPartRecebedorBaixaOperacional(grupoBaixaOper.getCodPartRecbdrBaixaOperac());
            boletoDDABaixaOper.setCodTipoBaixaOperacional(grupoBaixaOper.getTpBaixaOperac());
            boletoDDABaixaOper.setCodSituacaoBaixaOperacional(grupoBaixaOper.getSitBaixaOperac());
            boletoDDABaixaOper.setNumRefAtualCadBoleto(grupoBaixaOper.getNumRefCadTitBaixaOperac());
            if (!ObjectUtil.isNull(grupoBaixaOper.getGrupoDDA0106R1CanceltBaixaOperac())) {
                boletoDDABaixaOper
                        .setDataHoraCancelBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getGrupoDDA0106R1CanceltBaixaOperac().getDtHrCanceltBaixaOperac()));
                boletoDDABaixaOper
                        .setDataCancelamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getGrupoDDA0106R1CanceltBaixaOperac().getDtCanceltBaixaOperac()));
            }

            boletoDDABaixaOper.setBolOperacaoContingencia(Boolean.FALSE);
            setBoletoDDADetBaixaOper(boletoDDABaixaOper, grupoBaixaOper.getGrupoDDA0106R1DettBaixaOperac());
            lista.add(boletoDDABaixaOper);
        }

        boletoDDA.setListaBoletoDDABaixaOper(lista);
    }

    /**
     * Método responsável por setar a baixa operacional, vinculado ao arquivo ADDA106
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDABaixaOper(BoletoDDA boletoDDA, GrupoADDA106TitComplexType boletoRetorno) {
        List<BoletoDDABaixaOper> lista = new ArrayList<>();
        BoletoDDABaixaOper boletoDDABaixaOper;
        for (GrupoADDA106BaixaOperacComplexType grupoBaixaOper : boletoRetorno.getGrupoADDA106BaixaOperac()) {
            boletoDDABaixaOper = new BoletoDDABaixaOper();
            boletoDDABaixaOper.setBoletoDDA(boletoDDA);
            boletoDDABaixaOper.setNumIdentificadorBaixaOper(grupoBaixaOper.getNumIdentcBaixaOperac());
            boletoDDABaixaOper.setNumRefAtualBaixaOper(grupoBaixaOper.getNumRefAtlBaixaOperac());
            boletoDDABaixaOper.setNumSeqAtualBaixaOper(grupoBaixaOper.getNumSeqAtlzBaixaOperac());
            boletoDDABaixaOper.setCodIspbPartRecebedorBaixaOperacional(grupoBaixaOper.getISPBPartRecbdrBaixaOperac());
            boletoDDABaixaOper.setCodPartRecebedorBaixaOperacional(grupoBaixaOper.getCodPartRecbdrBaixaOperac());
            boletoDDABaixaOper.setCodTipoBaixaOperacional(grupoBaixaOper.getTpBaixaOperac());
            boletoDDABaixaOper.setCodSituacaoBaixaOperacional(grupoBaixaOper.getSitBaixaOperac());
            boletoDDABaixaOper.setNumRefAtualCadBoleto(grupoBaixaOper.getNumRefCadTitBaixaOperac());
            boletoDDABaixaOper.setBolOperacaoContingencia(Boolean.FALSE);
            if (!ObjectUtil.isNull(grupoBaixaOper.getGrupoADDA106CanceltBaixaOperac())) {
                boletoDDABaixaOper
                        .setDataHoraCancelBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getGrupoADDA106CanceltBaixaOperac().getDtHrCanceltBaixaOperac()));
                boletoDDABaixaOper
                        .setDataCancelamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getGrupoADDA106CanceltBaixaOperac().getDtCanceltBaixaOperac()));
            }

            setBoletoDDADetBaixaOper(boletoDDABaixaOper, grupoBaixaOper.getGrupoADDA106DettBaixaOperac());
            lista.add(boletoDDABaixaOper);
        }
        boletoDDA.setListaBoletoDDABaixaOper(lista);

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDABaixaOper(BoletoDDA boletoDDA, GrupoDDA0127TitComplexType boletoRetorno) {
        List<BoletoDDABaixaOper> lista = new ArrayList<>();
        BoletoDDABaixaOper boletoDDABaixaOper;
        for (GrupoDDA0127BaixaOperacComplexType grupoBaixaOper : boletoRetorno.getGrupoDDA0127BaixaOperac()) {
            boletoDDABaixaOper = new BoletoDDABaixaOper();
            boletoDDABaixaOper.setBoletoDDA(boletoDDA);
            boletoDDABaixaOper.setNumIdentificadorBaixaOper(grupoBaixaOper.getNumIdentcBaixaOperac());
            boletoDDABaixaOper.setNumRefAtualBaixaOper(grupoBaixaOper.getNumRefAtlBaixaOperac());
            boletoDDABaixaOper.setNumSeqAtualBaixaOper(grupoBaixaOper.getNumSeqAtlzBaixaOperac());
            boletoDDABaixaOper.setCodIspbPartRecebedorBaixaOperacional(grupoBaixaOper.getISPBPartRecbdrBaixaOperac());
            boletoDDABaixaOper.setCodPartRecebedorBaixaOperacional(grupoBaixaOper.getCodPartRecbdrBaixaOperac());
            boletoDDABaixaOper.setCodTipoBaixaOperacional(grupoBaixaOper.getTpBaixaOperac());
            boletoDDABaixaOper.setCodSituacaoBaixaOperacional(grupoBaixaOper.getSitBaixaOperac());
            boletoDDABaixaOper.setNumRefAtualCadBoleto(grupoBaixaOper.getNumRefCadTitBaixaOperac());
            boletoDDABaixaOper.setBolOperacaoContingencia(Boolean.FALSE);

            if (!ObjectUtil.isNull(grupoBaixaOper.getGrupoDDA0127CanceltBaixaOperac())) {
                boletoDDABaixaOper
                        .setDataHoraCancelBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getGrupoDDA0127CanceltBaixaOperac().getDtHrCanceltBaixaOperac()));
                boletoDDABaixaOper
                        .setDataCancelamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getGrupoDDA0127CanceltBaixaOperac().getDtCanceltBaixaOperac()));
            }

            setBoletoDDADetBaixaOper(boletoDDABaixaOper, grupoBaixaOper.getGrupoDDA0127DettBaixaOperac());
            lista.add(boletoDDABaixaOper);
        }
        boletoDDA.setListaBoletoDDABaixaOper(lista);

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param boletoRetorno void
     * 
     */
    private static void setBoletoDDABaixaOper(BoletoDDA boletoDDA, GrupoADDA127TitComplexType boletoRetorno) {
        List<BoletoDDABaixaOper> lista = new ArrayList<>();
        BoletoDDABaixaOper boletoDDABaixaOper;
        for (GrupoADDA127BaixaOperacComplexType grupoBaixaOper : boletoRetorno.getGrupoADDA127BaixaOperac()) {
            boletoDDABaixaOper = new BoletoDDABaixaOper();
            boletoDDABaixaOper.setBoletoDDA(boletoDDA);
            boletoDDABaixaOper.setNumIdentificadorBaixaOper(grupoBaixaOper.getNumIdentcBaixaOperac());
            boletoDDABaixaOper.setNumRefAtualBaixaOper(grupoBaixaOper.getNumRefAtlBaixaOperac());
            boletoDDABaixaOper.setNumSeqAtualBaixaOper(grupoBaixaOper.getNumSeqAtlzBaixaOperac());
            boletoDDABaixaOper.setCodIspbPartRecebedorBaixaOperacional(grupoBaixaOper.getISPBPartRecbdrBaixaOperac());
            boletoDDABaixaOper.setCodPartRecebedorBaixaOperacional(grupoBaixaOper.getCodPartRecbdrBaixaOperac());
            boletoDDABaixaOper.setCodTipoBaixaOperacional(grupoBaixaOper.getTpBaixaOperac());
            boletoDDABaixaOper.setCodSituacaoBaixaOperacional(grupoBaixaOper.getSitBaixaOperac());
            boletoDDABaixaOper.setBolOperacaoContingencia(Boolean.FALSE);
            if (!ObjectUtil.isNull(grupoBaixaOper.getGrupoADDA127CanceltBaixaOperac())) {
                boletoDDABaixaOper
                        .setDataHoraCancelBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getGrupoADDA127CanceltBaixaOperac().getDtHrCanceltBaixaOperac()));
                boletoDDABaixaOper
                        .setDataCancelamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getGrupoADDA127CanceltBaixaOperac().getDtCanceltBaixaOperac()));
            }
            setBoletoDDADetBaixaOper(boletoDDABaixaOper, grupoBaixaOper.getGrupoADDA127DettBaixaOperac());
            lista.add(boletoDDABaixaOper);
        }
        boletoDDA.setListaBoletoDDABaixaOper(lista);

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDABaixaOper
     * @param grupoDetBaixaOper void
     * 
     */
    private static void setBoletoDDADetBaixaOper(BoletoDDABaixaOper boletoDDABaixaOper, GrupoDDA0106R1DettBaixaOperacComplexType grupoDetBaixaOper) {
        if (!ObjectUtil.isNull(grupoDetBaixaOper)) {
            boletoDDABaixaOper.setNumCnpjCpfCodTipoPessoaPortador(grupoDetBaixaOper.getCNPJCPFPort(), grupoDetBaixaOper.getTpPessoaPort());
            boletoDDABaixaOper.setDataHoraProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOper.getDtHrProcBaixaOperac()));
            boletoDDABaixaOper.setDataProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOper.getDtProcBaixaOperac()));
            boletoDDABaixaOper.setValorBaixaOper(grupoDetBaixaOper.getVlrBaixaOperacTit());
            boletoDDABaixaOper.setNumCodBarrasBaixaOper(grupoDetBaixaOper.getNumCodBarrasBaixaOperac());
            boletoDDABaixaOper.setNumCodBarrasCampoLivre(
                    grupoDetBaixaOper.getNumCodBarrasBaixaOperac() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(grupoDetBaixaOper.getNumCodBarrasBaixaOperac()) : null);
            boletoDDABaixaOper.setCodCanalPagamento(grupoDetBaixaOper.getCanPgtoBaixaOperac());
            boletoDDABaixaOper.setCodMeioPagamento(grupoDetBaixaOper.getMeioPgtoBaixaOperac());
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDABaixaOper
     * @param grupoDetBaixaOper void
     * 
     */
    private static void setBoletoDDADetBaixaOper(BoletoDDABaixaOper boletoDDABaixaOper, GrupoADDA106DettBaixaOperacComplexType grupoDetBaixaOper) {
        if (!ObjectUtil.isNull(grupoDetBaixaOper)) {
            boletoDDABaixaOper.setNumCnpjCpfCodTipoPessoaPortador(grupoDetBaixaOper.getCNPJCPFPort(), grupoDetBaixaOper.getTpPessoaPort());
            boletoDDABaixaOper.setDataHoraProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOper.getDtHrProcBaixaOperac()));
            boletoDDABaixaOper.setDataProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOper.getDtProcBaixaOperac()));
            boletoDDABaixaOper.setValorBaixaOper(grupoDetBaixaOper.getVlrBaixaOperacTit());
            boletoDDABaixaOper.setNumCodBarrasBaixaOper(grupoDetBaixaOper.getNumCodBarrasBaixaOperac());
            boletoDDABaixaOper.setNumCodBarrasCampoLivre(
                    grupoDetBaixaOper.getNumCodBarrasBaixaOperac() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(grupoDetBaixaOper.getNumCodBarrasBaixaOperac()) : null);
            boletoDDABaixaOper.setCodCanalPagamento(grupoDetBaixaOper.getCanPgtoBaixaOperac());
            boletoDDABaixaOper.setCodMeioPagamento(grupoDetBaixaOper.getMeioPgtoBaixaOperac());
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDABaixaOper
     * @param grupoDetBaixaOper void
     * 
     */
    private static void setBoletoDDADetBaixaOper(BoletoDDABaixaOper boletoDDABaixaOper, GrupoDDA0127DettBaixaOperacComplexType grupoDetBaixaOper) {
        if (!ObjectUtil.isNull(grupoDetBaixaOper)) {
            boletoDDABaixaOper.setNumCnpjCpfCodTipoPessoaPortador(grupoDetBaixaOper.getCNPJCPFPort(), grupoDetBaixaOper.getTpPessoaPort());
            boletoDDABaixaOper.setDataHoraProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOper.getDtHrProcBaixaOperac()));
            boletoDDABaixaOper.setDataProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOper.getDtProcBaixaOperac()));
            boletoDDABaixaOper.setValorBaixaOper(grupoDetBaixaOper.getVlrBaixaOperacTit());
            boletoDDABaixaOper.setNumCodBarrasBaixaOper(grupoDetBaixaOper.getNumCodBarrasBaixaOperac());
            boletoDDABaixaOper.setNumCodBarrasCampoLivre(
                    grupoDetBaixaOper.getNumCodBarrasBaixaOperac() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(grupoDetBaixaOper.getNumCodBarrasBaixaOperac()) : null);
            boletoDDABaixaOper.setCodCanalPagamento(grupoDetBaixaOper.getCanPgtoBaixaOperac());
            boletoDDABaixaOper.setCodMeioPagamento(grupoDetBaixaOper.getMeioPgtoBaixaOperac());
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDABaixaOper
     * @param grupoDetBaixaOper void
     * 
     */
    private static void setBoletoDDADetBaixaOper(BoletoDDABaixaOper boletoDDABaixaOper, GrupoADDA127DettBaixaOperacComplexType grupoDetBaixaOper) {
        if (!ObjectUtil.isNull(grupoDetBaixaOper)) {
            boletoDDABaixaOper.setNumCnpjCpfCodTipoPessoaPortador(grupoDetBaixaOper.getCNPJCPFPort(), grupoDetBaixaOper.getTpPessoaPort());
            boletoDDABaixaOper.setDataHoraProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOper.getDtHrProcBaixaOperac()));
            boletoDDABaixaOper.setDataProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOper.getDtProcBaixaOperac()));
            boletoDDABaixaOper.setValorBaixaOper(grupoDetBaixaOper.getVlrBaixaOperacTit());
            boletoDDABaixaOper.setNumCodBarrasBaixaOper(grupoDetBaixaOper.getNumCodBarrasBaixaOperac());
            boletoDDABaixaOper.setNumCodBarrasCampoLivre(
                    grupoDetBaixaOper.getNumCodBarrasBaixaOperac() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(grupoDetBaixaOper.getNumCodBarrasBaixaOperac()) : null);
            boletoDDABaixaOper.setCodCanalPagamento(grupoDetBaixaOper.getCanPgtoBaixaOperac());
            boletoDDABaixaOper.setCodMeioPagamento(grupoDetBaixaOper.getMeioPgtoBaixaOperac());
        }
    }

    /**
     * @param boletoDDA
     * @param conteudo void
     * 
     */
    private static void setBoletoDDABaixaOper(BoletoDDA boletoDDA, GrupoADDA110RETTitActoComplexType conteudo) {
        if (!ObjectUtil.isEmpty(conteudo.getGrupoADDA110BaixaOperac())) {
            List<BoletoDDABaixaOper> lista = new ArrayList<>();
            for (GrupoADDA110BaixaOperacComplexType grupoBaixaOper : conteudo.getGrupoADDA110BaixaOperac()) {
                lista.add(new BoletoDDABaixaOper(boletoDDA, grupoBaixaOper.getNumIdentcBaixaOperac(), grupoBaixaOper.getNumRefAtlBaixaOperac(),
                        grupoBaixaOper.getNumSeqAtlzBaixaOperac(), DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getDtProcBaixaOperac()),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getDtHrProcBaixaOperac()), grupoBaixaOper.getVlrBaixaOperacTit(),
                        grupoBaixaOper.getNumCodBarrasBaixaOperac(), SituacaoBaixaOperacional.ATIVA));
            }
            boletoDDA.setListaBoletoDDABaixaOper(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param conteudo void
     * 
     */
    private static void setBoletoDDABaixaOper(BoletoDDA boletoDDA, DDA0110R1ComplexType conteudo) {
        if (!ObjectUtil.isEmpty(conteudo.getGrupoDDA0110R1BaixaOperac())) {
            List<BoletoDDABaixaOper> lista = new ArrayList<BoletoDDABaixaOper>();

            for (GrupoDDA0110R1BaixaOperacComplexType grupoBaixaOper : conteudo.getGrupoDDA0110R1BaixaOperac()) {
                lista.add(new BoletoDDABaixaOper(boletoDDA, grupoBaixaOper.getNumIdentcBaixaOperac(), grupoBaixaOper.getNumRefAtlBaixaOperac(),
                        grupoBaixaOper.getNumSeqAtlzBaixaOperac(), DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getDtProcBaixaOperac()),
                        DataCipUtil.xMLGregorianCalendarParaDateTime(grupoBaixaOper.getDtHrProcBaixaOperac()), grupoBaixaOper.getVlrBaixaOperacTit(),
                        grupoBaixaOper.getNumCodBarrasBaixaOperac(), SituacaoBaixaOperacional.ATIVA));
            }

            boletoDDA.setListaBoletoDDABaixaOper(lista);
        }
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param boletoDDA void
     * 
     */
    private static void setPagamentoDDA110(DDA0110R1ComplexType conteudo, BoletoDDA boletoDDA) {
        // TODO george.santos verificar qual carteira utilizar junto com o Jorge eduardo e Rafael Gomes
        boletoDDA.setIdCarteira(Integer.valueOf(TipoCarteiraCobrancaEnum.AMBAS.getCodDominio()));
        boletoDDA.setCodMoeda(conteudo.getCodMoedaCNAB());
        boletoDDA.setNumCodigoBarra(conteudo.getNumCodBarras());
        boletoDDA.setNumCodBarrasCampoLivre(conteudo.getNumCodBarras() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(conteudo.getNumCodBarras()) : null);
        boletoDDA.setNumLinhaDigitavel(conteudo.getNumLinhaDigtvl());
        boletoDDA.setDataVencimento(ObjectUtil.isNull(conteudo.getDtVencTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getDtVencTit()));
        boletoDDA.setValorBoleto(conteudo.getVlrTit());
        boletoDDA.setIdEspecieDocumento(Integer.valueOf(conteudo.getCodEspTit()));

        boletoDDA.setNumDiasProtesto(ObjectUtil.isNull(conteudo.getQtdDiaPrott()) ? null : conteudo.getQtdDiaPrott().intValue());
        boletoDDA.setDataLimitePagamento(ObjectUtil.isNull(conteudo.getDtLimPgtoTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getDtLimPgtoTit()));
        boletoDDA.setBolBloqueioPagamento(conteudo.getIndrBloqPgto().equals("S") ? Boolean.TRUE : Boolean.FALSE);
        boletoDDA.setBolPagamentoParcial(conteudo.getIndrPgtoParcl().equals("S") ? Boolean.TRUE : Boolean.FALSE);
        boletoDDA.setBolTituloNegociado(Boolean.FALSE);
        boletoDDA.setQtdPagamentoParcial(ObjectUtil.isNull(conteudo.getQtdPgtoParcl()) ? null : conteudo.getQtdPgtoParcl().intValue());
        boletoDDA.setValorAbatimento(conteudo.getVlrAbattTit());
    }

    /**
     * @param conteudo
     * @param boletoDDA void
     * 
     */
    private static void setPagamentoADDA110(GrupoADDA110RETTitActoComplexType conteudo, BoletoDDA boletoDDA) {
        // TODO george.santos verificar qual carteira utilizar junto com o Jorge eduardo e Rafael Gomes
        boletoDDA.setIdCarteira(Integer.valueOf(TipoCarteiraCobrancaEnum.AMBAS.getCodDominio()));
        boletoDDA.setCodMoeda(conteudo.getCodMoedaCNAB());
        boletoDDA.setNumCodigoBarra(conteudo.getNumCodBarras());
        boletoDDA.setNumCodBarrasCampoLivre(conteudo.getNumCodBarras() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(conteudo.getNumCodBarras()) : null);
        boletoDDA.setNumLinhaDigitavel(conteudo.getNumLinhaDigtvl());
        boletoDDA.setDataVencimento(ObjectUtil.isNull(conteudo.getDtVencTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getDtVencTit()));
        boletoDDA.setValorBoleto(conteudo.getVlrTit());
        boletoDDA.setIdEspecieDocumento(Integer.valueOf(conteudo.getCodEspTit()));

        boletoDDA.setNumDiasProtesto(ObjectUtil.isNull(conteudo.getQtdDiaPrott()) ? null : conteudo.getQtdDiaPrott().intValue());
        boletoDDA.setDataLimitePagamento(ObjectUtil.isNull(conteudo.getDtLimPgtoTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(conteudo.getDtLimPgtoTit()));
        boletoDDA.setBolBloqueioPagamento(conteudo.getIndrBloqPgto().equals("S") ? Boolean.TRUE : Boolean.FALSE);
        boletoDDA.setBolPagamentoParcial(conteudo.getIndrPgtoParcl().equals("S") ? Boolean.TRUE : Boolean.FALSE);
        boletoDDA.setBolTituloNegociado(Boolean.FALSE);
        boletoDDA.setQtdPagamentoParcial(ObjectUtil.isNull(conteudo.getQtdPgtoParcl()) ? null : conteudo.getQtdPgtoParcl().intValue());
        boletoDDA.setValorAbatimento(conteudo.getVlrAbattTit());
    }

    /**
     * @param boletoDDA
     * @param boletoDDARetorno void
     * 
     */
    private static void validaProcessamento(BoletoDDA boletoDDA, BoletoDDA boletoDDARetorno) {
        if (!ObjectUtil.isNull(boletoDDARetorno) && ObjectUtil.isNull(boletoDDARetorno.getBolProcessarMensagemRecebida())) {
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.TRUE);
        }
    }

    /**
     * Método responsável por verificar se uma mensagem é concorrente e se deve ser processada.
     * 
     * @param boletoDDA
     * @param numSeqAtlzCadTit
     * @return Boolean
     * 
     */
    private static Boolean isMensagemConcorrente(BoletoDDA boletoDDA, BigInteger numSeqAtlzCadTit) {
        Boolean msgConcorrente = Boolean.FALSE;
        if (isNumSeqRecebidoMenorQueAtual(boletoDDA, numSeqAtlzCadTit)) {
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.FALSE);
            boletoDDA.setBolProcessarMensagem106(Boolean.TRUE);
            msgConcorrente = Boolean.TRUE;
        } else if (isNumSeqRecebidoIgualAtual(boletoDDA, numSeqAtlzCadTit)) {
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.FALSE);
            boletoDDA.setBolProcessarMensagem106(Boolean.FALSE);
            msgConcorrente = Boolean.TRUE;
        }
        return msgConcorrente;
    }

    /**
     * Método responsável por verificar se o numSeqAtualCadBoleto do BoletoDDA recebido na mensagem é menor que o numSeqAtualCadBoleto do BoletoDDA gravado na
     * base de dados. Caso o @param boletoDDAAtual seja null retorna falso.
     * 
     * @param boletoDDAAtual
     * @param numSeqAtualCadBoletoRecebido
     * @return Boolean
     * 
     */
    public static Boolean isNumSeqRecebidoMenorQueAtual(BoletoDDA boletoDDAAtual, Number numSeqAtualCadBoletoRecebido) {
        return !isNumSeqBoletoNulo(boletoDDAAtual) && numSeqAtualCadBoletoRecebido.longValue() < boletoDDAAtual.getNumSeqAtualCadBoleto();
    }

    /**
     * Método responsável por verificar se o numSeqAtualCadBoleto do BoletoDDA recebido na mensagem é menor ou igual ao numSeqAtualCadBoleto do BoletoDDA
     * gravado na base de dados. Caso o @param boletoDDAAtual seja null retorna falso.
     * 
     * @param boletoDDAAtual
     * @param numSeqAtualCadBoletoRecebido
     * @return Boolean
     * 
     */
    public static Boolean isNumSeqRecebidoMenorOuIgualQueAtual(BoletoDDA boletoDDAAtual, Number numSeqAtualCadBoletoRecebido) {
        return !isNumSeqBoletoNulo(boletoDDAAtual) && numSeqAtualCadBoletoRecebido.longValue() <= boletoDDAAtual.getNumSeqAtualCadBoleto();
    }

    /**
     * 
     * @param boletoDDAAtual
     * @param numSeqAtualAceiteRecebido
     * @return Boolean
     * 
     */
    public static Boolean isNumSeqAceiteRecebidoMenorOuIgualQueAtual(BoletoDDA boletoDDAAtual, Number numSeqAtualAceiteRecebido) {
        return !isNumSeqAceiteBoletoNulo(boletoDDAAtual) && numSeqAtualAceiteRecebido.longValue() <= boletoDDAAtual.getNumSeqAtualAceite();
    }

    /**
     * Método responsável por verificar se o numSeqAtualCadBoleto do BoletoDDA recebido na mensagem é maior que o numSeqAtualCadBoleto do BoletoDDA gravado na
     * base de dados. Caso o @param boletoDDAAtual seja null retorna true.
     * 
     * @param boletoDDAAtual
     * @param numSeqAtualCadBoletoRecebido
     * @return Boolean
     * 
     */
    public static Boolean isNumSeqRecebidoMaiorQueAtual(BoletoDDA boletoDDAAtual, Number numSeqAtualCadBoletoRecebido) {
        return isNumSeqBoletoNulo(boletoDDAAtual) || numSeqAtualCadBoletoRecebido.longValue() > boletoDDAAtual.getNumSeqAtualCadBoleto();
    }

    /**
     * Método responsável por verificar se o numSeqAtualCadBoleto do BoletoDDA recebido na mensagem é igual ao numSeqAtualCadBoleto do BoletoDDA gravado na base
     * de dados. Caso o @param boletoDDAAtual seja null retorna falso.
     * 
     * @param boletoDDAAtual
     * @param numSeqAtualCadBoletoRecebido
     * @return Boolean
     * 
     */
    public static Boolean isNumSeqRecebidoIgualAtual(BoletoDDA boletoDDAAtual, BigInteger numSeqAtualCadBoletoRecebido) {
        return !isNumSeqBoletoNulo(boletoDDAAtual) && numSeqAtualCadBoletoRecebido.longValue() == boletoDDAAtual.getNumSeqAtualCadBoleto();
    }

    /**
     * @param boleto
     * @return boolean
     * 
     */
    public static boolean isNumSeqBoletoNulo(BoletoDDA boleto) {
        return ObjectUtil.isNull(boleto) || ObjectUtil.isNull(boleto.getNumSeqAtualCadBoleto());
    }

    /**
     * @param boleto
     * @return boolean
     * 
     */
    public static boolean isNumSeqAceiteBoletoNulo(BoletoDDA boleto) {
        return ObjectUtil.isNull(boleto) || ObjectUtil.isNull(boleto.getNumSeqAtualAceite());
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA void
     */
    private static void setBoletoDDADesconto1(BoletoDDA boletoDDA, DateTimeDB dataDesconto, String codTipoDesconto, BigDecimal valorPercentualDesconto) {
        boletoDDA.setDataDesconto1(dataDesconto);
        TipoDesconto tipoDesconto1 =new TipoDesconto();
        tipoDesconto1.setCodTipoDesconto(codTipoDesconto);
        boletoDDA.setTipoDesconto1(tipoDesconto1);        
        boletoDDA.setValorPercentualDesconto1(valorPercentualDesconto);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA void
     */
    private static void setBoletoDDADesconto2(BoletoDDA boletoDDA, DateTimeDB dataDesconto, String codTipoDesconto, BigDecimal valorPercentualDesconto) {
        boletoDDA.setDataDesconto2(dataDesconto);
        TipoDesconto tipoDesconto2 =new TipoDesconto();
        tipoDesconto2.setCodTipoDesconto(codTipoDesconto);
        boletoDDA.setTipoDesconto2(tipoDesconto2);
        boletoDDA.setValorPercentualDesconto2(valorPercentualDesconto);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA void
     */
    private static void setBoletoDDADesconto3(BoletoDDA boletoDDA, DateTimeDB dataDesconto, String codTipoDesconto, BigDecimal valorPercentualDesconto) {
        boletoDDA.setDataDesconto3(dataDesconto);
        TipoDesconto tipoDesconto3 =new TipoDesconto();
        tipoDesconto3.setCodTipoDesconto(codTipoDesconto);
        boletoDDA.setTipoDesconto3(tipoDesconto3);
        boletoDDA.setValorPercentualDesconto3(valorPercentualDesconto);
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param boletoDDA void
     */
    private static void setBoletoDDAMulta(XMLGregorianCalendar dataMulta, String codTipoMulta, BigDecimal valorPercentualMulta, BoletoDDA boletoDDA) {
        TipoMulta tipoMulta = new TipoMulta();
        tipoMulta.setId(ObjectUtil.isEmpty(codTipoMulta) ? null : Short.valueOf(codTipoMulta));
        boletoDDA.setTipoMulta(tipoMulta);
        boletoDDA.setDataMulta(ObjectUtil.isNull(dataMulta) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(dataMulta));
        boletoDDA.setValorPercentualMulta(valorPercentualMulta);
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param boletoDDA void
     */
    private static void setBoletoDDAJuros(XMLGregorianCalendar dataJuros, String codTipoJuros, BigDecimal valorPercentualJuros, BoletoDDA boletoDDA) {
        TipoJuros tipoJuros = new TipoJuros();
        tipoJuros.setId(ObjectUtil.isEmpty(codTipoJuros) ? null : Short.valueOf(codTipoJuros));
        boletoDDA.setTipoJuros(tipoJuros);
        boletoDDA.setDataJuros(ObjectUtil.isNull(dataJuros) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(dataJuros));
        boletoDDA.setValorPercentualJuros(valorPercentualJuros);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA void
     */
    private static void desconto1Isento(BoletoDDA boletoDDA) {
        TipoDesconto tipoDesconto = new TipoDesconto();
        tipoDesconto.setCodTipoDesconto(TipoDesconto.ISENTO);
        boletoDDA.setTipoDesconto1(tipoDesconto);
        boletoDDA.setDataDesconto1(null);
        boletoDDA.setValorPercentualDesconto1(BigDecimal.ZERO);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA void
     */
    private static void multaIsento(BoletoDDA boletoDDA) {
        TipoMulta tipoMulta = new TipoMulta();
        tipoMulta.setId(TipoMulta.ISENTO);
        boletoDDA.setTipoMulta(tipoMulta);
        boletoDDA.setDataMulta(null);
        boletoDDA.setValorPercentualMulta(BigDecimal.ZERO);
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA void
     */
    private static void jurosIsento(BoletoDDA boletoDDA) {
        TipoJuros tipoJuros = new TipoJuros();
        tipoJuros.setId(TipoJuros.ISENTO);
        boletoDDA.setTipoJuros(tipoJuros);
        boletoDDA.setDataJuros(null);
        boletoDDA.setValorPercentualJuros(BigDecimal.ZERO);
    }
}
