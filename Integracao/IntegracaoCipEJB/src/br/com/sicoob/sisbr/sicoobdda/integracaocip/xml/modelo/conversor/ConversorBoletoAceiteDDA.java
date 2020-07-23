package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAAceite;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA104.GrupoADDA104RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * ConversorBoletoAceiteDDA
 * 
 * @author George.santos
 */
public final class ConversorBoletoAceiteDDA {

    /** * */
    private ConversorBoletoAceiteDDA() {
    }

    public static void converter(ConteudoMsgRecebida conteudo, BoletoDDA boletoDDA, MensagemDDAAceite mensagem) throws ComumException {
        if (conteudo instanceof DDA0104R1ComplexType) {
            conveterDDA0104R1((DDA0104R1ComplexType) conteudo, boletoDDA, mensagem);
        } else if (conteudo instanceof DDA0104R2ComplexType) {
            conveterDDA0104R2((DDA0104R2ComplexType) conteudo, boletoDDA);
        } else if (conteudo instanceof GrupoADDA104RR2TitComplexType) {
            conveterADDA0104R2((GrupoADDA104RR2TitComplexType) conteudo, boletoDDA);
        }

    }

    /**
     * Método responsável por
     * 
     * -Se o sequencial de atualização recebido na mensagem for menor ou igual ao do registro, NÃO processar a mensagem recebida
     * 
     * @param retornoDDA
     * @param boletoDDA
     * @param mensagem
     * @throws ComumException void
     * 
     */
    private static void conveterDDA0104R1(DDA0104R1ComplexType retornoDDA, BoletoDDA boletoDDA, MensagemDDAAceite mensagem) throws ComumException {
        if (ConversorBoletoDDA.isNumSeqAceiteRecebidoMenorOuIgualQueAtual(boletoDDA, retornoDDA.getNumSeqAtlzActe())) {
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.FALSE);
        } else {
            boletoDDA.setNumSeqAtualAceite(retornoDDA.getNumSeqAtlzActe().longValue());
            boletoDDA.setNumRefAtualCadAceite(retornoDDA.getNumRefAtlActe().longValue());
            boletoDDA.setBolAceite(mensagem.isBolAceite());
            boletoDDA.setDataHoraSituacaoAceite(new DateTimeDB());
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.TRUE);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor ou igual ao do registro, NÃO processar a mensagem recebida
     * 
     * @param retornoDDA
     * @param boletoDDA
     * @throws ComumException void
     * 
     */
    private static void conveterDDA0104R2(DDA0104R2ComplexType retornoDDA, BoletoDDA boletoDDA) throws ComumException {
        if (ConversorBoletoDDA.isNumSeqAceiteRecebidoMenorOuIgualQueAtual(boletoDDA, retornoDDA.getNumSeqAtlzActe())) {
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.FALSE);
        } else {
            boletoDDA.setNumSeqAtualAceite(retornoDDA.getNumSeqAtlzActe().longValue());
            boletoDDA.setNumRefAtualCadAceite(retornoDDA.getNumRefAtlActe().longValue());
            boletoDDA.setBolAceite(retornoDDA.getIndrActe().equals("S"));
            boletoDDA.setDataHoraSituacaoAceite(DataCipUtil.xMLGregorianCalendarParaDateTime(retornoDDA.getDtHrSitActe()));
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.TRUE);
        }
    }

    /**
     * Método responsável por
     * 
     * Se o sequencial de atualização recebido na mensagem for menor ou igual ao do registro, NÃO processar a mensagem recebida
     * 
     * @param retornoDDA
     * @param boletoDDA
     * @throws ComumException void
     * 
     */
    private static void conveterADDA0104R2(GrupoADDA104RR2TitComplexType retornoDDA, BoletoDDA boletoDDA) throws ComumException {
        if (ConversorBoletoDDA.isNumSeqAceiteRecebidoMenorOuIgualQueAtual(boletoDDA, retornoDDA.getNumSeqAtlzActe())) {
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.FALSE);
        } else {
            boletoDDA.setNumSeqAtualAceite(retornoDDA.getNumSeqAtlzActe().longValue());
            boletoDDA.setNumRefAtualCadAceite(retornoDDA.getNumRefAtlActe().longValue());
            boletoDDA.setBolAceite(retornoDDA.getIndrActe().equals("S"));
            boletoDDA.setDataHoraSituacaoAceite(DataCipUtil.xMLGregorianCalendarParaDateTime(retornoDDA.getDtHrSitActe()));
            boletoDDA.setBolProcessarMensagemRecebida(Boolean.TRUE);
        }
    }

}
