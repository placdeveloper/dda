/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor
 * Arquivo:         ConversorBeneficiario.java
 * Data Criação:    Nov 3, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoIfStatusBeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoStatusBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.IFBeneficiarioAlerta;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA504.GrupoADDA504BenfcrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA504.GrupoADDA504ConvComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.GrupoDDA0504R1BenfcrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.GrupoDDA0504R1ConvComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;

/**
 * ConversorBeneficiario é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class ConversorBeneficiarioDDA {

    /**
     * 
     */
    private ConversorBeneficiarioDDA() {

    }

    /**
     * Método responsável por fazer a conversão do Pagador
     * 
     * @param conteudo
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA converter(ConteudoMsgRecebida conteudo) throws ComumException {
        return converter(conteudo, null);
    }

    /**
     * Método responsável por fazer a conversão do Pagador
     * 
     * @param conteudo
     * @param mensagemBeneficiario
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA converter(ConteudoMsgRecebida conteudo, MensagemDDABeneficiario mensagemBeneficiario) throws ComumException {
        BeneficiarioDDA beneficiarioDDA = null;
        if (conteudo instanceof GrupoADDA504BenfcrioComplexType) {
            beneficiarioDDA = converter((GrupoADDA504BenfcrioComplexType) conteudo);
        } else if (conteudo instanceof GrupoDDA0504R1BenfcrioComplexType) {
            beneficiarioDDA = converter((GrupoDDA0504R1BenfcrioComplexType) conteudo);
        }
        return beneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param beneficiarioMerge
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA merge(ConteudoMsgRecebida conteudo, BeneficiarioDDA beneficiarioMerge) throws ComumException {
        return merge(conteudo, beneficiarioMerge, null);
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param beneficiarioMerge
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    public static BeneficiarioDDA merge(ConteudoMsgRecebida conteudo, BeneficiarioDDA beneficiarioMerge, MensagemDDAPagador mensagemBeneficiario) throws ComumException {
        BeneficiarioDDA beneficiarioDDA = null;
        if (conteudo instanceof GrupoADDA504BenfcrioComplexType) {
            beneficiarioDDA = merge((GrupoADDA504BenfcrioComplexType) conteudo, beneficiarioMerge);
        } else if (conteudo instanceof GrupoDDA0504R1BenfcrioComplexType) {
            beneficiarioDDA = merge((GrupoDDA0504R1BenfcrioComplexType) conteudo, beneficiarioMerge);
        }
        return beneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @param grupoBeneficiario
     * @return BeneficiarioDDA
     * @throws ComumException
     * 
     */
    private static BeneficiarioDDA converter(GrupoADDA504BenfcrioComplexType grupoBeneficiario) throws ComumException {
        BeneficiarioDDA beneficiario = new BeneficiarioDDA();

        setBeneficiarioDDAInfo(beneficiario, grupoBeneficiario.getCNPJCPFBenfcrio(), grupoBeneficiario.getTpPessoaBenfcrio(), grupoBeneficiario.getSitBenfcrio(),
                grupoBeneficiario.getDtIniRelctPart(), grupoBeneficiario.getNumRefAtlCadBenfcrio(), grupoBeneficiario.getNumSeqAtlzCadBenfcrio(),
                grupoBeneficiario.getNumIdentcBenfcrio(), grupoBeneficiario.getNumCtrlDDA());
        setListaIFBeneficiarioAlerta(beneficiario, grupoBeneficiario.getISPBPartDestinatarioOrigdrAlert());
        setBeneficiarioDDAOrigem(beneficiario, grupoBeneficiario.getGrupoADDA504Conv().isEmpty() ? Boolean.FALSE : Boolean.TRUE);
        setListaBeneficiarioInstituicaoArquivo(beneficiario, grupoBeneficiario.getGrupoADDA504Conv());
        return beneficiario;
    }

    /**
     * @param grupoBeneficiario
     * @return
     * @throws ComumException
     */
    private static BeneficiarioDDA converter(GrupoDDA0504R1BenfcrioComplexType grupoBeneficiario) throws ComumException {
        BeneficiarioDDA beneficiario = new BeneficiarioDDA();

        grupoBeneficiario.getGrupoDDA0504R1Conv();

        setBeneficiarioDDAInfo(beneficiario, grupoBeneficiario.getCNPJCPFBenfcrio(), grupoBeneficiario.getTpPessoaBenfcrio(), grupoBeneficiario.getSitBenfcrio(),
                grupoBeneficiario.getDtIniRelctPart(), grupoBeneficiario.getNumRefAtlCadBenfcrio(), grupoBeneficiario.getNumSeqAtlzCadBenfcrio(),
                grupoBeneficiario.getNumIdentcBenfcrio(), grupoBeneficiario.getNumCtrlDDA());
        setListaIFBeneficiarioAlerta(beneficiario, grupoBeneficiario.getISPBPartDestinatarioOrigdrAlert());
        setBeneficiarioDDAOrigem(beneficiario, grupoBeneficiario.getGrupoDDA0504R1Conv().isEmpty() ? Boolean.FALSE : Boolean.TRUE);
        setListaBeneficiarioInstituicaoR1(beneficiario, grupoBeneficiario.getGrupoDDA0504R1Conv());
        return beneficiario;
    }

    /**
     * @param grupoBeneficiario
     * @param beneficiarioMerge
     * @return
     * @throws ComumException
     */
    private static BeneficiarioDDA merge(GrupoDDA0504R1BenfcrioComplexType grupoBeneficiario, BeneficiarioDDA beneficiarioMerge) throws ComumException {
        BeneficiarioDDA beneficiario = converter(grupoBeneficiario);

        beneficiario.setId(beneficiarioMerge.getId());
        setBeneficiarioDDAOrigem(beneficiario, beneficiarioMerge.getBolOrigemSicoob());

        if (validacaoNulaSituacaoBeneficiario(grupoBeneficiario)) {
            validacaoSituacaoRelacionamentoBeneficiario(grupoBeneficiario.getSitRelctPart(), grupoBeneficiario.getSitBenfcrio(), beneficiario);
        }

        mergeListaHistoricoStatusBeneficiarioDDA(beneficiario, beneficiarioMerge);
        return beneficiario;
    }

    /**
     * Método responsável por
     * 
     * @param grupoBeneficiario
     * @param beneficiarioMerge
     * @return BeneficiarioDDA
     * @throws ComumException
     * 
     */
    private static BeneficiarioDDA merge(GrupoADDA504BenfcrioComplexType grupoBeneficiario, BeneficiarioDDA beneficiarioMerge) throws ComumException {
        BeneficiarioDDA beneficiario = converter(grupoBeneficiario);

        beneficiario.setId(beneficiarioMerge.getId());
        beneficiario.setDataInicioRelacionamento(beneficiarioMerge.getDataInicioRelacionamento());
        setBeneficiarioDDAOrigem(beneficiario, beneficiarioMerge.getBolOrigemSicoob());

        if (validacaoNulaSituacaoBeneficiario(grupoBeneficiario)) {
            validacaoSituacaoRelacionamentoBeneficiario(grupoBeneficiario.getSitRelctPart(), grupoBeneficiario.getSitBenfcrio(), beneficiario);
        }

        mergeListaHistoricoStatusBeneficiarioDDA(beneficiario, beneficiarioMerge);
        return beneficiario;
    }

    /**
     * Se SitRelctPart = 'E' e SitBenfcrio = 'A', atualizar a tabela BeneficiarioDDA, HistoricoStatusBeneficiario e HistoricoIFStatusBeneficiario com todas as
     * informações e com a situação igual a Sem Cadastro. Além disso, fazer uma exclusão dos registros da tabela BeneficiarioInstituicao visando atualizar o
     * registro do beneficiário.
     * 
     * @param GrupoADDA504BenfcrioComplexType
     * @param beneficiario void
     * 
     */
    private static void validacaoSituacaoRelacionamentoBeneficiario(String situacaoRelacionamentoParticipante, String situacaoBeneficiario, BeneficiarioDDA beneficiario) {
        if (situacaoRelacionamentoParticipante.equals(SituacaoRelacionamentoParticipanteEnum.EXCLUIDO.getCodDominio()) && situacaoBeneficiario.equals(SituacaoBeneficiarioDDA.APTO)) {
            beneficiario.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA(SituacaoBeneficiarioDDA.SEM_CADASTRO));
            beneficiario.setBolOrigemSicoob(Boolean.FALSE);
            beneficiario.getListaBeneficiarioInstituicao().clear();
        }
    }

    private static boolean validacaoNulaSituacaoBeneficiario(GrupoDDA0504R1BenfcrioComplexType grupoBeneficiario) {
        return !ObjectUtil.isNull(grupoBeneficiario) && !ObjectUtil.isNull(grupoBeneficiario.getSitRelctPart()) && !ObjectUtil.isNull(grupoBeneficiario.getSitBenfcrio());
    }

    private static boolean validacaoNulaSituacaoBeneficiario(GrupoADDA504BenfcrioComplexType grupoBeneficiario) {
        return !ObjectUtil.isNull(grupoBeneficiario) && !ObjectUtil.isNull(grupoBeneficiario.getSitRelctPart()) && !ObjectUtil.isNull(grupoBeneficiario.getSitBenfcrio());
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param beneficiarioMerge void
     * 
     */
    private static void mergeListaHistoricoStatusBeneficiarioDDA(BeneficiarioDDA beneficiario, BeneficiarioDDA beneficiarioMerge) {
        beneficiario.getListaHistoricoStatusBeneficiarioDDA().addAll(beneficiarioMerge.getListaHistoricoStatusBeneficiarioDDA());
        beneficiario.getListaHistoricoStatusBeneficiarioDDA().add(setHistoricoStatusBeneficiarioDDA(beneficiario, beneficiarioMerge));
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param beneficiarioMerge
     * @return HistoricoStatusBeneficiarioDDA
     * 
     */
    private static HistoricoStatusBeneficiarioDDA setHistoricoStatusBeneficiarioDDA(BeneficiarioDDA beneficiario, BeneficiarioDDA beneficiarioMerge) {
        HistoricoStatusBeneficiarioDDA historicoStatusBeneficiarioDDA = new HistoricoStatusBeneficiarioDDA(beneficiario, beneficiarioMerge.getSituacaoBeneficiarioDDA(),
                beneficiarioMerge.getDataHoraUltimaAtualizacao(), new ArrayList<HistoricoIfStatusBeneficiario>());
        for (IFBeneficiarioAlerta ifAlerta : beneficiarioMerge.getListaIFBeneficiarioAlerta()) {
            historicoStatusBeneficiarioDDA.getListaHistoricoIfStatusBeneficiario().add(
                    new HistoricoIfStatusBeneficiario(historicoStatusBeneficiarioDDA, ifAlerta.getCodIspbDestinatarioOriginalAlerta(), beneficiarioMerge
                            .getDataHoraUltimaAtualizacao()));
        }
        return historicoStatusBeneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param ispbPartDestinatarioOrigdrAlert void
     * 
     */
    private static void setListaIFBeneficiarioAlerta(BeneficiarioDDA beneficiario, List<String> ispbPartDestinatarioOrigdrAlert) {
        for (String ispbParticipante : ispbPartDestinatarioOrigdrAlert) {
            beneficiario.getListaIFBeneficiarioAlerta().add(new IFBeneficiarioAlerta(beneficiario, ispbParticipante.trim(), new DateTimeDB()));
        }
    }

    private static void setListaBeneficiarioInstituicaoR1(BeneficiarioDDA beneficiario, List<GrupoDDA0504R1ConvComplexType> grupoBeneficiarioConv) throws ComumException {
        for (GrupoDDA0504R1ConvComplexType beneficiarioConv : grupoBeneficiarioConv) {
            beneficiario.getListaBeneficiarioInstituicao().add(
                    new BeneficiarioInstituicao(beneficiario, obterIdInstituicao(beneficiarioConv.getAgDest()), DataCipUtil.xMLGregorianCalendarParaDateTime(beneficiarioConv
                            .getDtIniRelctConv())));
        }
    }

    private static void setListaBeneficiarioInstituicaoArquivo(BeneficiarioDDA beneficiario, List<GrupoADDA504ConvComplexType> listaGrupoADDA504ConvComplexType)
            throws ComumException {
        for (GrupoADDA504ConvComplexType grupoADDA504ConvComplexType : listaGrupoADDA504ConvComplexType) {
            beneficiario.getListaBeneficiarioInstituicao().add(
                    new BeneficiarioInstituicao(beneficiario, obterIdInstituicao(grupoADDA504ConvComplexType.getAgDest()), DataCipUtil
                            .xMLGregorianCalendarParaDateTime(grupoADDA504ConvComplexType.getDtIniRelctConv())));
        }
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param numCpfCnpjCodTipoPessoa
     * @param codTipoPessoa
     * @param codSituacaoBeneficiarioDDA
     * @param dataInicioRelacionamento
     * @param dataHoraUltimaAtualizacao void
     * @param numIdentcBenfcrio
     * @param numIdentcBenfcrio
     * 
     */
    private static void setBeneficiarioDDAInfo(BeneficiarioDDA beneficiario, BigInteger numCpfCnpjCodTipoPessoa, String codTipoPessoa, String codSituacaoBeneficiarioDDA,
            XMLGregorianCalendar dataInicioRelacionamento, BigInteger numRefAtualCadBeneficiario, BigInteger numSeqAtualCadBeneficiario, BigInteger numIdentcBenfcrio,
            String numCtrlDDA) {
        beneficiario.setNumCpfCnpjCodTipoPessoa(numCpfCnpjCodTipoPessoa.toString(), codTipoPessoa);
        beneficiario.setNumIdentBeneficiario(ObjectUtil.isNull(numIdentcBenfcrio) ? null : numIdentcBenfcrio.longValue());

        beneficiario.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA(codSituacaoBeneficiarioDDA));

        beneficiario.setDataInicioRelacionamento(ObjectUtil.isNull(dataInicioRelacionamento) ? new DateTimeDB() : DataCipUtil
                .xMLGregorianCalendarParaDateTime(dataInicioRelacionamento));
        beneficiario.setDataHoraUltimaAtualizacao(new DateTimeDB());
        beneficiario.setNumRefAtualCadBeneficiario(ObjectUtil.isNull(numRefAtualCadBeneficiario) ? null : numRefAtualCadBeneficiario.longValue());
        beneficiario.setNumSeqAtualCadBeneficiario(ObjectUtil.isNull(numSeqAtualCadBeneficiario) ? null : numSeqAtualCadBeneficiario.longValue());

        beneficiario.setNumCtrlDDA(numCtrlDDA);
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @param bolOrigemSicoob void
     * 
     */
    private static void setBeneficiarioDDAOrigem(BeneficiarioDDA beneficiario, Boolean bolOrigemSicoob) {
        beneficiario.setBolOrigemSicoob(bolOrigemSicoob);
        beneficiario.setIdProduto(Constantes.ID_PRODUTO_COBRANCA);
    }

    /**
     * Método responsável por
     * 
     * @param agDest
     * @return Integer
     * @throws ComumException
     * 
     */
    private static Integer obterIdInstituicao(BigInteger numCooperativa) throws ComumException {
        InstituicaoDto instituicaoDto = getSCIDelegate().obterInstituicaoPorCooperativaCache(numCooperativa.intValue());
        if (ObjectUtil.isNull(instituicaoDto)) {
            throw new ComumException("integracaocip.erro.instituicao.sci.invalida");
        }
        return instituicaoDto.getIdInstituicao();
    }

    /**
     * Método responsável por
     * 
     * @return SCIDelegate
     * 
     */
    private static SCIDelegate getSCIDelegate() {
        return IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();
    }
}
