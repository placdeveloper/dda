package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoBaixaDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA118.GrupoADDA118RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA118.GrupoADDA118RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA120.GrupoADDA120TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0118.DDA0118R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0118.DDA0118R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * ConversorBaixaEfetivaDDA
 * 
 * @author George.santos
 */
public final class ConversorBaixaEfetivaDDA {

    /**
     * 
     */
    private ConversorBaixaEfetivaDDA() {
    }

    /**
     * @param conteudo
     * @param boletoDDA
     * @param mensagem
     * @return
     * @throws ComumException BoletoDDABaixaEfet
     * 
     */
    public static BoletoDDABaixaEfet converter(ConteudoMsgRecebida conteudo, BoletoDDA boletoDDA, MensagemDDABaixaEfetiva mensagem) throws ComumException {
        BoletoDDABaixaEfet boletoDDABaixaEfet = null;
        if (conteudo instanceof DDA0118R1ComplexType) {
            DDA0118R1ComplexType baixaEfetivaRetorno = (DDA0118R1ComplexType) conteudo;
            boletoDDABaixaEfet = converterDDA0118R1(boletoDDA, mensagem, baixaEfetivaRetorno.getNumIdentcBaixaEft(), baixaEfetivaRetorno.getNumRefAtlBaixaEft(),
                    baixaEfetivaRetorno.getNumSeqAtlzBaixaEft(), DataCipUtil.xMLGregorianCalendarParaDateTime(baixaEfetivaRetorno.getDtHrDDA()),
                    baixaEfetivaRetorno.getNumRefAtlCadTit());
        } else if (conteudo instanceof GrupoADDA118RETTitActoComplexType) {
            GrupoADDA118RETTitActoComplexType baixaEfetivaRetorno = (GrupoADDA118RETTitActoComplexType) conteudo;
            boletoDDABaixaEfet = converterDDA0118R1(boletoDDA, mensagem, baixaEfetivaRetorno.getNumIdentcBaixaEft(), baixaEfetivaRetorno.getNumRefAtlBaixaEft(),
                    baixaEfetivaRetorno.getNumSeqAtlzBaixaEft(), mensagem.getMensagemDDA().getDataHoraCadastro(), baixaEfetivaRetorno.getNumRefAtlCadTit());
        } else if (conteudo instanceof DDA0118R2ComplexType) {
            DDA0118R2ComplexType baixaEfetivaRetorno = (DDA0118R2ComplexType) conteudo;
            boletoDDABaixaEfet = converterDDA0118R2(boletoDDA, baixaEfetivaRetorno);
        } else if (conteudo instanceof GrupoADDA118RR2TitComplexType) {
            GrupoADDA118RR2TitComplexType baixaEfetivaRetorno = (GrupoADDA118RR2TitComplexType) conteudo;
            boletoDDABaixaEfet = converterADDA0118R2(boletoDDA, baixaEfetivaRetorno);
        } else if (conteudo instanceof GrupoADDA120TitComplexType) {
            boletoDDABaixaEfet = converterADDA0120(boletoDDA, (GrupoADDA120TitComplexType) conteudo);
        }

        return boletoDDABaixaEfet;
    }

    /**
     * Método responsável por setar o BoletoDDABaixaEfet da mensagem DDA0118R1
     * 
     * @param boletoDDA
     * @param mensagem
     * @param numIdentificacaoBaixaEfetiva
     * @param numRefBaixaEfetiva
     * @param numSeqBaixaEfetiva
     * @param dataHoraBaixaEfetiva
     * @param numRefAtualCadTit
     * @return BoletoDDABaixaEfet
     * 
     */
    private static BoletoDDABaixaEfet converterDDA0118R1(BoletoDDA boletoDDA, MensagemDDABaixaEfetiva mensagem, BigInteger numIdentificacaoBaixaEfetiva,
            BigInteger numRefBaixaEfetiva, BigInteger numSeqBaixaEfetiva, DateTimeDB dataHoraBaixaEfetiva, BigInteger numRefAtlCadTit) {
        BoletoDDABaixaEfet boletoDDABaixaEfet = new BoletoDDABaixaEfet();

        boletoDDABaixaEfet.setBoletoDDA(boletoDDA);
        if (!ObjectUtil.isNull(boletoDDA) && !ObjectUtil.isEmpty(boletoDDA.getId())) {
            boletoDDA.setCodSituacaoBoleto(TipoBaixaDeParaEnum.getSituacaoBoleto(mensagem.getCodTipoBaixaEfetiva().toString()));
            if (!ObjectUtil.isNull(numRefAtlCadTit)) {
                boletoDDA.setNumRefAtualCadBoleto(numRefAtlCadTit.longValue());
            }
        }

        boletoDDABaixaEfet.setNumIdentificadorBaixaEfet(numIdentificacaoBaixaEfetiva.longValue());
        boletoDDABaixaEfet.setNumRefAtualBaixaEfet(numRefBaixaEfetiva.longValue());
        boletoDDABaixaEfet.setNumSeqAtualBaixaEfet(numSeqBaixaEfetiva.longValue());

        boletoDDABaixaEfet.setDataHoraProcessamentoBaixaEfet(dataHoraBaixaEfetiva);
        boletoDDABaixaEfet.setDataProcessamentoBaixaEfet(dataHoraBaixaEfetiva);

        boletoDDABaixaEfet.setValorBaixaEfet(mensagem.getValorBaixa());
        boletoDDABaixaEfet.setNumCodBarrasBaixaEfet(mensagem.getNumCodigoBarra());
        boletoDDABaixaEfet
                .setNumCodBarrasCampoLivre(ObjectUtil.isEmpty(mensagem.getNumCodigoBarra()) ? null : LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(mensagem.getNumCodigoBarra()));

        boletoDDABaixaEfet.setCodCanalPagamento(mensagem.getCodCanalPagamento());
        boletoDDABaixaEfet.setCodMeioPagamento(mensagem.getCodMeioPagamento());
        boletoDDABaixaEfet.setNumIdentificadorBaixaOperacional(mensagem.getNumIdentificadorBaixaOper());

        boletoDDABaixaEfet.setCodTipoBaixaEfetiva(mensagem.getCodTipoBaixaEfetiva());

        boletoDDABaixaEfet.setCodIspbPartRecebedorBaixaEfetiva(Constantes.ISPB_BANCOOB);
        boletoDDABaixaEfet.setCodPartRecebedorBaixaEfetiva(Constantes.BANCOOB);

        return boletoDDABaixaEfet;
    }

    /**
     * Método responsável por chamar o converter do R2 de Mensagem - DDA0118
     * 
     * @param boletoDDA
     * @param baixaEfetivaRetorno
     * @return BoletoDDABaixaEfet
     * 
     */
    private static BoletoDDABaixaEfet converterDDA0118R2(BoletoDDA boletoDDA, DDA0118R2ComplexType baixaEfetivaRetorno) {
        return converter118R2(boletoDDA, baixaEfetivaRetorno.getNumIdentcTit(), baixaEfetivaRetorno.getNumRefAtlCadTit(), baixaEfetivaRetorno.getNumIdentcBaixaEft(),
                baixaEfetivaRetorno.getNumSeqAtlzBaixaEft(), baixaEfetivaRetorno.getTpBaixaEft(), baixaEfetivaRetorno.getISPBPartRecbdrBaixaEft(),
                baixaEfetivaRetorno.getCodPartRecbdrBaixaEft(), baixaEfetivaRetorno.getDtHrProcBaixaEft(), baixaEfetivaRetorno.getDtProcBaixaEft(),
                baixaEfetivaRetorno.getVlrBaixaEftTit(), baixaEfetivaRetorno.getNumCodBarrasBaixaEft(), baixaEfetivaRetorno.getCanPgto(), baixaEfetivaRetorno.getMeioPgto(),
                baixaEfetivaRetorno.getNumIdentcBaixaOperac(), baixaEfetivaRetorno.getQtdPgtoParclRegtd(), baixaEfetivaRetorno.getVlrSldTotAtlPgtoTit(),
                baixaEfetivaRetorno.getSitTitPgto(), baixaEfetivaRetorno.getSitTit());
    }

    /**
     * Método responsável por chamar o converter do R2 de Arquivo - ADDA0118
     * 
     * @param boletoDDA
     * @param baixaEfetivaRetorno
     * @return BoletoDDABaixaEfet
     * 
     */
    private static BoletoDDABaixaEfet converterADDA0118R2(BoletoDDA boletoDDA, GrupoADDA118RR2TitComplexType baixaEfetivaRetorno) {
        return converter118R2(boletoDDA, baixaEfetivaRetorno.getNumIdentcTit(), baixaEfetivaRetorno.getNumRefAtlCadTit(), baixaEfetivaRetorno.getNumIdentcBaixaEft(),
                baixaEfetivaRetorno.getNumSeqAtlzBaixaEft(), baixaEfetivaRetorno.getTpBaixaEft(), baixaEfetivaRetorno.getISPBPartRecbdrBaixaEft(),
                baixaEfetivaRetorno.getCodPartRecbdrBaixaEft(), baixaEfetivaRetorno.getDtHrProcBaixaEft(), baixaEfetivaRetorno.getDtProcBaixaEft(),
                baixaEfetivaRetorno.getVlrBaixaEftTit(), baixaEfetivaRetorno.getNumCodBarrasBaixaEft(), baixaEfetivaRetorno.getCanPgto(), baixaEfetivaRetorno.getMeioPgto(),
                baixaEfetivaRetorno.getNumIdentcBaixaOperac(), baixaEfetivaRetorno.getQtdPgtoParclRegtd(), baixaEfetivaRetorno.getVlrSldTotAtlPgtoTit(),
                baixaEfetivaRetorno.getSitTitPgto(), baixaEfetivaRetorno.getSitTit());
    }

    /**
     * Método responsável por setar o BOletoDDABaixaEfet da mensagem/arquivo DDA0118R2
     * 
     * @param boletoBaixaEfetivaRetorno
     * @param boletoDDA
     * @return BoletoDDABaixaEfet
     * 
     */
    private static BoletoDDABaixaEfet converter118R2(BoletoDDA boletoDDA, BigInteger numIdentificadorBoleto, BigInteger numReferenciaCadastrotitulo,
            BigInteger numIdentificacaoBaixaEfetiva, BigInteger numSeqBaixaEfetiva, String tipoBaixaEfetiva, String ispbPartRecebedorBaixaEfetiva,
            String partRecebedorBaixaEfetiva, XMLGregorianCalendar dataHoraProcessamento, XMLGregorianCalendar dataProcessamento, BigDecimal valorBaixaEfetiva,
            String numCodigoBarras, BigInteger canalPagamento, BigInteger meioPagamento, BigInteger numIdentificadorBaixaOperacional, BigInteger qtdPgtoParcialRegistrado,
            BigDecimal valorSaldoPagamento, String situacaoTituloPag, String situacaoTitulo) {
        BoletoDDABaixaEfet boletoDDABaixaEfet = new BoletoDDABaixaEfet();

        boletoDDABaixaEfet.setNumIdentificadorBaixaEfet(numIdentificacaoBaixaEfetiva.longValue());
        boletoDDABaixaEfet.setNumSeqAtualBaixaEfet(numSeqBaixaEfetiva.longValue());
        boletoDDABaixaEfet.setCodTipoBaixaEfetiva(Integer.valueOf(tipoBaixaEfetiva));
        boletoDDABaixaEfet.setCodIspbPartRecebedorBaixaEfetiva(ObjectUtil.isNull(ispbPartRecebedorBaixaEfetiva) ? null : ispbPartRecebedorBaixaEfetiva);
        boletoDDABaixaEfet.setCodPartRecebedorBaixaEfetiva(ObjectUtil.isNull(partRecebedorBaixaEfetiva) ? null : partRecebedorBaixaEfetiva);
        boletoDDABaixaEfet.setDataHoraProcessamentoBaixaEfet(DataCipUtil.xMLGregorianCalendarParaDateTime(dataHoraProcessamento));
        boletoDDABaixaEfet.setDataProcessamentoBaixaEfet(DataCipUtil.xMLGregorianCalendarParaDateTime(dataProcessamento));
        boletoDDABaixaEfet.setValorBaixaEfet(valorBaixaEfetiva);
        boletoDDABaixaEfet.setNumCodBarrasBaixaEfet(numCodigoBarras);
        boletoDDABaixaEfet.setNumCodBarrasCampoLivre(ObjectUtil.isEmpty(numCodigoBarras) ? null : LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(numCodigoBarras));

        boletoDDABaixaEfet.setCodCanalPagamento(ObjectUtil.isEmpty(canalPagamento) ? null : canalPagamento.intValue());
        boletoDDABaixaEfet.setCodMeioPagamento(ObjectUtil.isEmpty(meioPagamento) ? null : meioPagamento.intValue());
        boletoDDABaixaEfet.setNumIdentificadorBaixaOperacional(ObjectUtil.isNull(numIdentificadorBaixaOperacional) ? null : numIdentificadorBaixaOperacional.longValue());

        if (!ObjectUtil.isNull(boletoDDA) && !ObjectUtil.isEmpty(boletoDDA.getId())) {
            boletoDDA.setNumIdentificadorBoletoCip(numIdentificadorBoleto.longValue());
            boletoDDA.setNumRefAtualCadBoleto(ObjectUtil.isNull(numReferenciaCadastrotitulo) ? null : numReferenciaCadastrotitulo.longValue());

            boletoDDA.setQtdPagamentoParcialReg(qtdPgtoParcialRegistrado);

            boletoDDA.setValorSaldoPagamento(ObjectUtil.isEmpty(valorSaldoPagamento) ? null : valorSaldoPagamento);
            boletoDDA.setCodSituacaoBoletoPagamento(situacaoTituloPag);
            boletoDDA.setCodSituacaoBoleto(ObjectUtil.isNull(situacaoTitulo) ? null : Integer.valueOf(situacaoTitulo));
        }

        boletoDDABaixaEfet.setBoletoDDA(boletoDDA);

        return boletoDDABaixaEfet;

    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA
     * @param retorno
     * @return BoletoDDABaixaEfet
     * 
     */
    private static BoletoDDABaixaEfet converterADDA0120(BoletoDDA boletoDDA, GrupoADDA120TitComplexType retorno) {
        BoletoDDABaixaEfet boletoDDABaixaEfet = new BoletoDDABaixaEfet();
        boletoDDABaixaEfet.setBoletoDDA(boletoDDA);
        if (!ObjectUtil.isNull(boletoDDA) && !ObjectUtil.isNull(boletoDDA.getId())) {
            boletoDDA.setNumIdentificadorBoletoCip(retorno.getNumIdentcTit().longValue());
            boletoDDA.setNumRefAtualCadBoleto(ObjectUtil.isNull(retorno.getNumRefAtlCadTit()) ? null : retorno.getNumRefAtlCadTit().longValue());
            boletoDDA.setDataHoraSituacaoBoleto(DataCipUtil.xMLGregorianCalendarParaDateTime(retorno.getDtHrSitTit()));

            boletoDDA.setQtdPagamentoParcialReg(retorno.getQtdPgtoParclRegtd());
            boletoDDA.setValorSaldoPagamento(retorno.getVlrSldTotAtlPgtoTit());
            boletoDDA.setCodSituacaoBoletoPagamento(retorno.getSitTitPgto());
            boletoDDA.setCodSituacaoBoleto(SituacaoBoleto.BAIXA_EFETIVA_POR_DECURSO_DE_PRAZO);
        }

        boletoDDABaixaEfet.setNumIdentificadorBaixaOperacional(retorno.getNumUltIdentcBaixaOperac());
        boletoDDABaixaEfet.setNumRefAtualBaixaEfet(retorno.getNumUltRefAtlBaixaEft());
        boletoDDABaixaEfet.setNumSeqAtualBaixaEfet(retorno.getNumUltSeqAtlzBaixaEft());
        boletoDDABaixaEfet.setNumIdentificadorBaixaEfet(retorno.getNumIdentcBaixaEft());
        boletoDDABaixaEfet.setCodTipoBaixaEfetiva(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_DECURSO_PRAZO);
        boletoDDABaixaEfet.setDataProcessamentoBaixaEfet(DataCipUtil.xMLGregorianCalendarParaDateTime(retorno.getDtHrProcBaixaEft()));
        boletoDDABaixaEfet.setDataHoraProcessamentoBaixaEfet(DataCipUtil.xMLGregorianCalendarParaDateTime(retorno.getDtHrProcBaixaEft()));

        return boletoDDABaixaEfet;
    }

}
