package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA108.GrupoADDA108RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA108.GrupoADDA108RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA114.GrupoADDA114RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA114.GrupoADDA114RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * ConversorBaixaOperacionalDDA é responsável por
 * 
 * @author felipe.rosa
 */
public final class ConversorBaixaOperacionalDDA {

    private static final String IND_CONTINGENCIA_PADRAO = "N";

    /**
     * 
     */
    private ConversorBaixaOperacionalDDA() {
    }

    /**
     * @param conteudo
     * @param mensagem
     * @param boletoDDA
     * @return
     * @throws ComumException BoletoDDABaixaOper
     * 
     */
    public static BoletoDDABaixaOper converter(ConteudoMsgRecebida conteudo, MensagemDDABaixaOperacional mensagem, BoletoDDA boletoDDA) throws ComumException {
        BoletoDDABaixaOper boletoDDABaixaOper = null;
        if (conteudo instanceof DDA0108R1ComplexType) {
            boletoDDABaixaOper = ConversorBaixaOperacionalDDA.converter((DDA0108R1ComplexType) conteudo, mensagem, boletoDDA);
        } else if (conteudo instanceof DDA0108R2ComplexType) {
            boletoDDABaixaOper = ConversorBaixaOperacionalDDA.converter((DDA0108R2ComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof GrupoADDA108RETTitActoComplexType) {
            boletoDDABaixaOper = ConversorBaixaOperacionalDDA.converter((GrupoADDA108RETTitActoComplexType) conteudo, mensagem, boletoDDA);
        } else if (conteudo instanceof GrupoADDA108RR2TitComplexType) {
            boletoDDABaixaOper = ConversorBaixaOperacionalDDA.converter((GrupoADDA108RR2TitComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof GrupoADDA114RETTitActoComplexType) {
            boletoDDABaixaOper = ConversorBaixaOperacionalDDA.converter((GrupoADDA114RETTitActoComplexType) conteudo, mensagem, boletoDDA);
        } else if (conteudo instanceof GrupoADDA114RR2TitComplexType) {
            boletoDDABaixaOper = ConversorBaixaOperacionalDDA.converter((GrupoADDA114RR2TitComplexType) conteudo, boletoDDA);
        }

        return boletoDDABaixaOper;
    }

    /**
     * Método responsável por converter o retorno da CIP DDA0108R1ComplexType em BoletoDDABaixaOper
     * 
     * @param retornoDDA
     * @param mensagem
     * @param boletoDDA
     * @return BoletoDDABaixaOper
     * 
     */
    private static BoletoDDABaixaOper converter(DDA0108R1ComplexType retornoDDA, MensagemDDABaixaOperacional mensagem, BoletoDDA boletoDDA) {
        BoletoDDABaixaOper boletoDDABxOp = new BoletoDDABaixaOper(boletoDDA);
        setNumReferencia(boletoDDABxOp, retornoDDA.getNumIdentcBaixaOperac(), retornoDDA.getNumRefAtlBaixaOperac(), retornoDDA.getNumSeqAtlzBaixaOperac(),
                mensagem.getNumRefAtualCadBoleto());

        setDataHoraProcessamento(boletoDDABxOp, mensagem.getMensagemDDA().getDataHoraCadastro(), mensagem.getMensagemDDA().getDataHoraCadastro());

        setInfPagamento(boletoDDABxOp, mensagem.getNumCodigoBarra(), mensagem.getValorBaixa(), mensagem.getCodTipoPessoaPortador(), mensagem.getNumCpfCnpjPortador(),
                mensagem.getCodCanalPagamento(), mensagem.getCodMeioPagamento());
        setIfRecebedora(boletoDDABxOp, Constantes.ISPB_BANCOOB, Constantes.BANCOOB);
        setInfGerais(boletoDDABxOp, IND_CONTINGENCIA_PADRAO, mensagem.getCodTipoBaixaOperacional().toString());

        return boletoDDABxOp;
    }

    /**
     * Método responsável por converter o retorno da CIP DDA0108R2ComplexType em BoletoDDABaixaOper
     * 
     * @param retornoDDA
     * @param boletoDDA
     * @return BoletoDDABaixaOper
     * 
     */
    private static BoletoDDABaixaOper converter(DDA0108R2ComplexType retornoDDA, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(boletoDDA) && !ObjectUtil.isNull(boletoDDA.getId())) {
            boletoDDA.setQtdPagamentoParcialReg(retornoDDA.getQtdPgtoParclRegtd());
            boletoDDA.setCodSituacaoBoletoPagamento(retornoDDA.getSitTitPgto());

            if (!ObjectUtil.isNull(retornoDDA.getNumRefAtlCadTit())) {
                boletoDDA.setNumRefAtualCadBoleto(retornoDDA.getNumRefAtlCadTit().longValue());
            }
        }
        BoletoDDABaixaOper boletoDDABaixaOper = new BoletoDDABaixaOper(boletoDDA);
        setNumReferencia(boletoDDABaixaOper, retornoDDA.getNumIdentcBaixaOperac(), retornoDDA.getNumRefAtlBaixaOperac(), retornoDDA.getNumSeqAtlzBaixaOperac(), retornoDDA
                .getNumRefCadTitBaixaOperac().longValue());
        setDataHoraProcessamento(boletoDDABaixaOper, retornoDDA.getDtHrProcBaixaOperac(), retornoDDA.getDtProcBaixaOperac());

        setInfPagamento(boletoDDABaixaOper, retornoDDA.getNumCodBarrasBaixaOperac(), retornoDDA.getVlrBaixaOperacTit(), ObjectUtil.isNull(retornoDDA.getTpPessoaPort()) ? null
                : retornoDDA.getTpPessoaPort(), ObjectUtil.isNull(retornoDDA.getCNPJCPFPort()) ? null : retornoDDA.getCNPJCPFPort().toString(), ObjectUtil.isNull(retornoDDA
                .getCanPgto()) ? null : retornoDDA.getCanPgto().shortValue(), ObjectUtil.isNull(retornoDDA.getMeioPgto()) ? null : retornoDDA.getMeioPgto().shortValue());

        setIfRecebedora(boletoDDABaixaOper, retornoDDA.getISPBPartRecbdrBaixaOperac(), retornoDDA.getCodPartRecbdrBaixaOperac());
        setInfGerais(boletoDDABaixaOper, retornoDDA.getIndrOpContg(), retornoDDA.getTpBaixaOperac());

        return boletoDDABaixaOper;
    }

    /**
     * Método responsável por converter o retorno da CIP GrupoADDA108RR2TitComplexType em BoletoDDABaixaOper
     * 
     * @param retornoDDA
     * @param boletoDDA
     * @return BoletoDDABaixaOper
     * 
     */
    private static BoletoDDABaixaOper converter(GrupoADDA108RR2TitComplexType retornoDDA, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(boletoDDA) && !ObjectUtil.isNull(boletoDDA.getId())) {
            boletoDDA.setQtdPagamentoParcialReg(retornoDDA.getQtdPgtoParclRegtd());
            boletoDDA.setCodSituacaoBoletoPagamento(retornoDDA.getSitTitPgto());

            if (!ObjectUtil.isNull(retornoDDA.getNumRefAtlCadTit())) {
                boletoDDA.setNumRefAtualCadBoleto(retornoDDA.getNumRefAtlCadTit().longValue());
            }
        }

        BoletoDDABaixaOper boletoDDABaixaOper = new BoletoDDABaixaOper(boletoDDA);

        setNumReferencia(boletoDDABaixaOper, retornoDDA.getNumIdentcBaixaOperac(), retornoDDA.getNumRefAtlBaixaOperac(), retornoDDA.getNumSeqAtlzBaixaOperac(), retornoDDA
                .getNumRefCadTitBaixaOperac().longValue());
        setDataHoraProcessamento(boletoDDABaixaOper, retornoDDA.getDtHrProcBaixaOperac(), retornoDDA.getDtProcBaixaOperac());
        setInfPagamento(boletoDDABaixaOper, retornoDDA.getNumCodBarrasBaixaOperac(), retornoDDA.getVlrBaixaOperacTit(), ObjectUtil.isNull(retornoDDA.getTpPessoaPort()) ? null
                : retornoDDA.getTpPessoaPort(), ObjectUtil.isNull(retornoDDA.getCNPJCPFPort()) ? null : retornoDDA.getCNPJCPFPort().toString(), ObjectUtil.isNull(retornoDDA
                .getCanPgto()) ? null : retornoDDA.getCanPgto().shortValue(), ObjectUtil.isNull(retornoDDA.getMeioPgto()) ? null : retornoDDA.getMeioPgto().shortValue());

        setIfRecebedora(boletoDDABaixaOper, retornoDDA.getISPBPartRecbdrBaixaOperac(), retornoDDA.getCodPartRecbdrBaixaOperac());
        setInfGerais(boletoDDABaixaOper, retornoDDA.getIndrOpContg(), retornoDDA.getTpBaixaOperac());

        return boletoDDABaixaOper;
    }

    /**
     * Método responsável por converter o retorno da CIP GrupoADDA108RETTitActoComplexType em BoletoDDABaixaOper
     * 
     * @param retornoDDA
     * @param boletoDDA
     * @return BoletoDDABaixaOper
     * 
     */
    private static BoletoDDABaixaOper converter(GrupoADDA108RETTitActoComplexType retornoDDA, MensagemDDABaixaOperacional mensagem, BoletoDDA boletoDDA) {
        BoletoDDABaixaOper boletoDDABxOp = new BoletoDDABaixaOper(boletoDDA);

        setNumReferencia(boletoDDABxOp, retornoDDA.getNumIdentcBaixaOperac(), retornoDDA.getNumRefAtlBaixaOperac(), retornoDDA.getNumSeqAtlzBaixaOperac(), retornoDDA
                .getNumRefAtlCadTit().longValue());

        setDataHoraProcessamento(boletoDDABxOp, mensagem.getMensagemDDA().getDataHoraCadastro(), mensagem.getMensagemDDA().getDataHoraCadastro());

        setInfPagamento(boletoDDABxOp, mensagem.getNumCodigoBarra(), mensagem.getValorBaixa(), mensagem.getCodTipoPessoaPortador(), mensagem.getNumCpfCnpjPortador(),
                mensagem.getCodCanalPagamento(), mensagem.getCodMeioPagamento());
        setIfRecebedora(boletoDDABxOp, Constantes.ISPB_BANCOOB, Constantes.BANCOOB);
        setInfGerais(boletoDDABxOp, IND_CONTINGENCIA_PADRAO, mensagem.getCodTipoBaixaOperacional().toString());

        return boletoDDABxOp;
    }

    /**
     * Método responsável por converter o retorno da CIP GrupoADDA114RETTitActoComplexType em BoletoDDABaixaOper
     * 
     * @param retornoDDA
     * @param mensagem
     * @param boletoDDA
     * @return BoletoDDABaixaOper
     * 
     */
    private static BoletoDDABaixaOper converter(GrupoADDA114RETTitActoComplexType retornoDDA, MensagemDDABaixaOperacional mensagem, BoletoDDA boletoDDA) {
        BoletoDDABaixaOper boletoDDABxOp = new BoletoDDABaixaOper(boletoDDA);
        setNumReferencia(boletoDDABxOp, retornoDDA.getNumIdentcBaixaOperac(), retornoDDA.getNumRefAtlBaixaOperac(), retornoDDA.getNumSeqAtlzBaixaOperac(),
                mensagem.getNumRefAtualCadBoleto());

        setDataHoraProcessamento(boletoDDABxOp, mensagem.getMensagemDDA().getDataHoraCadastro(), mensagem.getMensagemDDA().getDataHoraCadastro());
        setInfPagamento(boletoDDABxOp, mensagem.getNumCodigoBarra(), mensagem.getValorBaixa(), mensagem.getCodTipoPessoaPortador(), mensagem.getNumCpfCnpjPortador(),
                mensagem.getCodCanalPagamento(), mensagem.getCodMeioPagamento());
        setIfRecebedora(boletoDDABxOp, Constantes.ISPB_BANCOOB, Constantes.BANCOOB);
        setInfGerais(boletoDDABxOp, IND_CONTINGENCIA_PADRAO, mensagem.getCodTipoBaixaOperacional().toString());

        return boletoDDABxOp;
    }

    /**
     * Método responsável por converter o retorno da CIP GrupoADDA114RR2TitComplexType em BoletoDDABaixaOper
     * 
     * @param retornoDDA
     * @param boletoDDA
     * @return BoletoDDABaixaOper
     * 
     */
    private static BoletoDDABaixaOper converter(GrupoADDA114RR2TitComplexType retornoDDA, BoletoDDA boletoDDA) {
        BoletoDDABaixaOper boletoDDABaixaOper = new BoletoDDABaixaOper(boletoDDA);
        // Para aviso de baixa em contingencia não é distribuído a referência do boleto na hora da baixa, apenas a referencia atual.
        setNumReferencia(boletoDDABaixaOper, retornoDDA.getNumIdentcBaixaOperac(), retornoDDA.getNumRefAtlBaixaOperac(), retornoDDA.getNumSeqAtlzBaixaOperac(), retornoDDA
                .getNumRefAtlCadTit().longValue());
        setDataHoraProcessamento(boletoDDABaixaOper, retornoDDA.getDtHrProcBaixaOperac(), retornoDDA.getDtProcBaixaOperac());

        setInfPagamento(boletoDDABaixaOper, retornoDDA.getNumCodBarrasBaixaOperac(), retornoDDA.getVlrBaixaOperacTit(), ObjectUtil.isNull(retornoDDA.getTpPessoaPort()) ? null
                : retornoDDA.getTpPessoaPort(), ObjectUtil.isNull(retornoDDA.getCNPJCPFPort()) ? null : retornoDDA.getCNPJCPFPort().toString(), ObjectUtil.isNull(retornoDDA
                .getCanPgto()) ? null : retornoDDA.getCanPgto().shortValue(), ObjectUtil.isNull(retornoDDA.getMeioPgto()) ? null : retornoDDA.getMeioPgto().shortValue());

        setIfRecebedora(boletoDDABaixaOper, retornoDDA.getISPBPartRecbdrBaixaOperac(), retornoDDA.getCodPartRecbdrBaixaOperac());
        setInfGerais(boletoDDABaixaOper, retornoDDA.getIndrOpContg(), retornoDDA.getTpBaixaOperac());

        return boletoDDABaixaOper;
    }

    /**
     * @param boletoDDABxOp
     * @param numIdentBxOp
     * @param numRefBxOp
     * @param numSeqBxOp
     * @param numRefCadTitBxOp void
     * 
     */
    private static void setNumReferencia(BoletoDDABaixaOper boletoDDABxOp, BigInteger numIdentBxOp, BigInteger numRefBxOp, BigInteger numSeqBxOp, Long numRefCadTitBxOp) {
        boletoDDABxOp.setNumIdentificadorBaixaOper(numIdentBxOp);
        boletoDDABxOp.setNumRefAtualBaixaOper(numRefBxOp);
        boletoDDABxOp.setNumSeqAtualBaixaOper(numSeqBxOp);
        boletoDDABxOp.setNumRefAtualCadBoleto(numRefCadTitBxOp);
    }

    /**
     * @param boletoDDABxOp
     * @param dataHoraProcessamento
     * @param dataProcessamento void
     * 
     */
    private static void setDataHoraProcessamento(BoletoDDABaixaOper boletoDDABxOp, XMLGregorianCalendar dataHoraProcessamento, XMLGregorianCalendar dataProcessamento) {
        boletoDDABxOp.setDataHoraProcessamentoBaixaOper(dataHoraProcessamento == null ? new DateTimeDB() : DataCipUtil.xMLGregorianCalendarParaDateTime(dataHoraProcessamento));
        boletoDDABxOp.setDataProcessamentoBaixaOper(dataProcessamento == null ? new DateTimeDB() : DataCipUtil.xMLGregorianCalendarParaDateTime(dataProcessamento));
    }

    /**
     * @param boletoDDABxOp
     * @param dataHoraProcessamento
     * @param dataProcessamento void
     * 
     */
    private static void setDataHoraProcessamento(BoletoDDABaixaOper boletoDDABxOp, DateTimeDB dataHoraProcessamento, DateTimeDB dataProcessamento) {
        boletoDDABxOp.setDataHoraProcessamentoBaixaOper(dataHoraProcessamento == null ? new DateTimeDB() : dataHoraProcessamento);
        boletoDDABxOp.setDataProcessamentoBaixaOper(dataProcessamento == null ? new DateTimeDB() : dataProcessamento);
    }

    /**
     * @param boletoDDABxOp
     * @param codIspbIfRecebedor
     * @param codIfRecebedor void
     * 
     */
    private static void setIfRecebedora(BoletoDDABaixaOper boletoDDABxOp, String codIspbIfRecebedor, String codIfRecebedor) {
        boletoDDABxOp.setCodIspbPartRecebedorBaixaOperacional(codIspbIfRecebedor);
        boletoDDABxOp.setCodPartRecebedorBaixaOperacional(codIfRecebedor);
    }

    /**
     * @param boletoDDABxOp
     * @param indContingencia
     * @param codTipoBxOp void
     * 
     */
    private static void setInfGerais(BoletoDDABaixaOper boletoDDABxOp, String indContingencia, String codTipoBxOp) {
        boletoDDABxOp.setCodSituacaoBaixaOperacional(SituacaoBaixaOperacional.ATIVA);
        boletoDDABxOp.setBolOperacaoContingencia(indContingencia);
        boletoDDABxOp.setCodTipoBaixaOperacional(Integer.valueOf(codTipoBxOp));
    }

    /**
     * @param boletoDDABxOp
     * @param numCodBarras
     * @param valor
     * @param codTipoPessoaPort
     * @param numCpfCnpjPort
     * @param codCanalPgto
     * @param codMeioPgto void
     * 
     */
    private static void setInfPagamento(BoletoDDABaixaOper boletoDDABxOp, String numCodBarras, BigDecimal valor, String codTipoPessoaPort, String numCpfCnpjPort,
            Short codCanalPgto, Short codMeioPgto) {
        boletoDDABxOp.setNumCodBarrasBaixaOper(numCodBarras);
        boletoDDABxOp.setNumCodBarrasCampoLivre(numCodBarras != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(numCodBarras) : null);
        boletoDDABxOp.setValorBaixaOper(valor);
        boletoDDABxOp.setNumCnpjCpfCodTipoPessoaPortador(numCpfCnpjPort, codTipoPessoaPort);
        boletoDDABxOp.setCodCanalPagamento(codCanalPgto);
        boletoDDABxOp.setCodMeioPagamento(codMeioPgto);
    }

}
