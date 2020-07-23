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

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA001.GrupoADDA001RETPagdrActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002AgrgdDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002CtCliPagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002PagdrProprioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA003.GrupoADDA003PagdrDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA005.GrupoADDA005RETPagdrActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA006.GrupoADDA006RETPagdrActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.DDA0001R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1AgrgdDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1CtCliPagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1PagdrProprioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0005.DDA0005R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0006.DDA0006R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * ConversorPagadorDDA
 * 
 * @author Rafael.Silva
 */
public final class ConversorPagadorDDA {

    /**
     * 
     */
    private ConversorPagadorDDA() {

    }

    /**
     * Método responsável por fazer a conversão do Pagador
     * 
     * @param conteudo
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    public static PagadorDDA converter(ConteudoMsgRecebida conteudo) throws ComumException {
        return converter(conteudo, null);
    }

    /**
     * Método responsável por fazer a conversão do Pagador
     * 
     * @param conteudo
     * @param mensagemPagador
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    public static PagadorDDA converter(ConteudoMsgRecebida conteudo, MensagemDDAPagador mensagemPagador) throws ComumException {
        PagadorDDA pagadorDDA = null;
        if (conteudo instanceof DDA0001R1ComplexType) {
            pagadorDDA = converter((DDA0001R1ComplexType) conteudo, mensagemPagador);
        } else if (conteudo instanceof GrupoADDA001RETPagdrActoComplexType) {
            pagadorDDA = converter((GrupoADDA001RETPagdrActoComplexType) conteudo, mensagemPagador);
        } else if (conteudo instanceof GrupoADDA003PagdrDDAComplexType) {
            pagadorDDA = converter((GrupoADDA003PagdrDDAComplexType) conteudo);
        } else if (conteudo instanceof GrupoDDA0002R1PagdrComplexType) {
            pagadorDDA = converter((GrupoDDA0002R1PagdrComplexType) conteudo);
        } else if (conteudo instanceof GrupoADDA002PagdrComplexType) {
            pagadorDDA = converter((GrupoADDA002PagdrComplexType) conteudo);
        }

        return pagadorDDA;
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param pagadorMerge
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    public static PagadorDDA merge(ConteudoMsgRecebida conteudo, PagadorDDA pagadorMerge) throws ComumException {
        return merge(conteudo, pagadorMerge, null);
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param pagadorMerge
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    public static PagadorDDA merge(ConteudoMsgRecebida conteudo, PagadorDDA pagadorMerge, MensagemDDAPagador mensagemPagador) throws ComumException {
        PagadorDDA pagadorDDA = null;
        if (conteudo instanceof GrupoDDA0002R1PagdrComplexType) {
            pagadorDDA = merge((GrupoDDA0002R1PagdrComplexType) conteudo, pagadorMerge);
        } else if (conteudo instanceof GrupoADDA002PagdrComplexType) {
            pagadorDDA = merge((GrupoADDA002PagdrComplexType) conteudo, pagadorMerge);
        } else if (conteudo instanceof DDA0001R1ComplexType) {
            pagadorDDA = mergeInclusaoPagador((DDA0001R1ComplexType) conteudo, mensagemPagador, pagadorMerge);
        } else if (conteudo instanceof DDA0005R1ComplexType) {
            pagadorDDA = merge((DDA0005R1ComplexType) conteudo, mensagemPagador, pagadorMerge);
        } else if (conteudo instanceof GrupoADDA003PagdrDDAComplexType) {
            pagadorDDA = merge((GrupoADDA003PagdrDDAComplexType) conteudo, pagadorMerge);
        } else if (conteudo instanceof GrupoADDA005RETPagdrActoComplexType) {
            pagadorDDA = merge((GrupoADDA005RETPagdrActoComplexType) conteudo, pagadorMerge);
        } else if (conteudo instanceof GrupoADDA001RETPagdrActoComplexType) {
            pagadorDDA = mergeInclusaoArquivoPagador((GrupoADDA001RETPagdrActoComplexType) conteudo, mensagemPagador, pagadorMerge);
        }
        return pagadorDDA;
    }

    /**
     * Método responsável por fazer o merge quando na inclusao do pagador existir na base.
     * 
     * @param conteudo
     * @param mensagemPagador
     * @param pagadorMerge
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA mergeInclusaoPagador(DDA0001R1ComplexType conteudo, MensagemDDAPagador mensagemPagador, PagadorDDA pagadorMerge) throws ComumException {
        pagadorMerge.setNumIdentificaPagadorCip(conteudo.getNumIdentcPagdr().longValue());
        pagadorMerge.setNumRefAtualCadPagador(conteudo.getNumRefAtlCadCliPagdr().longValue());
        pagadorMerge.setNumSeqAtualCadPagador(conteudo.getNumSeqAtlzCadCliPagdr().longValue());
        pagadorMerge.setBolSacadoEletronico(mensagemPagador.getBolPagadorEletronico().equals("S"));
        pagadorMerge.setQtdAdesaoDDA(conteudo.getQtdAdesCliPagdrDDA());

        mergeListaPagadorConta(mensagemPagador, pagadorMerge);

        mergePagadorAgregado(mensagemPagador, pagadorMerge);
        return pagadorMerge;
    }

    /**
     * Método responsável por fazer o merge quando na inclusao do pagador existir na base.
     * 
     * @param conteudo
     * @param mensagemPagador
     * @param pagadorMerge
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA mergeInclusaoArquivoPagador(GrupoADDA001RETPagdrActoComplexType conteudo, MensagemDDAPagador mensagemPagador, PagadorDDA pagadorMerge)
            throws ComumException {
        pagadorMerge.setNumIdentificaPagadorCip(conteudo.getNumIdentcPagdr().longValue());
        pagadorMerge.setNumRefAtualCadPagador(conteudo.getNumRefAtlCadCliPagdr().longValue());
        pagadorMerge.setNumSeqAtualCadPagador(conteudo.getNumSeqAtlzCadCliPagdr().longValue());
        pagadorMerge.setBolSacadoEletronico(mensagemPagador.getBolPagadorEletronico().equals("S"));
        pagadorMerge.setQtdAdesaoDDA(conteudo.getQtdAdesCliPagdrDDA());

        mergeListaPagadorConta(mensagemPagador, pagadorMerge);

        mergePagadorAgregado(mensagemPagador, pagadorMerge);
        return pagadorMerge;
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @param pagadorRemove
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    public static PagadorDDA remove(ConteudoMsgRecebida conteudo, PagadorDDA pagadorRemove) throws ComumException {
        if (conteudo instanceof DDA0006R1ComplexType) {
            pagadorRemove = remove((DDA0006R1ComplexType) conteudo, pagadorRemove);
        } else if (conteudo instanceof GrupoADDA003PagdrDDAComplexType) {
            pagadorRemove = remove((GrupoADDA003PagdrDDAComplexType) conteudo, pagadorRemove);
        } else if (conteudo instanceof GrupoADDA006RETPagdrActoComplexType) {
            pagadorRemove = remove((GrupoADDA006RETPagdrActoComplexType) conteudo, pagadorRemove);
        }
        return pagadorRemove;
    }

    /**
     * Método responsável por
     * 
     * @param pagadorRetorno
     * @param msgPagador
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA converter(GrupoADDA001RETPagdrActoComplexType pagadorRetorno, MensagemDDAPagador msgPagador) {
        return converter(msgPagador, pagadorRetorno.getNumIdentcPagdr(), pagadorRetorno.getNumRefAtlCadCliPagdr(), pagadorRetorno.getNumSeqAtlzCadCliPagdr(),
                pagadorRetorno.getQtdAdesCliPagdrDDA());
    }

    /**
     * Método responsável por
     * 
     * @param pagadorRetorno
     * @param msgPagador
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA converter(DDA0001R1ComplexType pagadorRetorno, MensagemDDAPagador msgPagador) {
        return converter(msgPagador, pagadorRetorno.getNumIdentcPagdr(), pagadorRetorno.getNumRefAtlCadCliPagdr(), pagadorRetorno.getNumSeqAtlzCadCliPagdr(),
                pagadorRetorno.getQtdAdesCliPagdrDDA());
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private static PagadorDDA converter(GrupoADDA003PagdrDDAComplexType grupoPagador) throws ComumException {
        return setPagador(grupoPagador);
    }

    /**
     * Método responsável por converter GrupoDDA0002R1PagdrComplexType em PagadorDDA
     * 
     * @param conteudo
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA converter(GrupoDDA0002R1PagdrComplexType grupoPagador) {
        return setPagador(grupoPagador);
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA converter(GrupoADDA002PagdrComplexType grupoPagador) {
        return setPagador(grupoPagador);
    }

    /**
     * Método responsável por fazer a conversao do pagador quando recebido uma DDA0001R1ComplexType (mensagem) ou GrupoADDA001RETPagdrActoComplexType (Arquivo)
     * - INCLUSÃO
     * 
     * @param msgPagador
     * @param numIdentcPagdr
     * @param numRefAtlCadPagdr
     * @param numSeqAtlzCadPagdr
     * @param qtdAdesPagdrDDA
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA converter(MensagemDDAPagador msgPagador, BigInteger numIdentcPagdr, BigInteger numRefAtlCadPagdr, BigInteger numSeqAtlzCadPagdr,
            BigInteger qtdAdesPagdrDDA) {
        PagadorDDA pagadorDDA = new PagadorDDA(msgPagador.getNumCpfCnpjPagador(), msgPagador.getCodTipoPessoaPagador(), numIdentcPagdr, numRefAtlCadPagdr, qtdAdesPagdrDDA,
                numSeqAtlzCadPagdr);

        setListaPagadorConta(msgPagador, pagadorDDA);
        setListaPagadorAgregado(msgPagador, pagadorDDA);

        return pagadorDDA;

    }

    /**
     * Método responsável por
     * 
     * @param pagadorRetorno
     * @param msgPagador
     * @param pagadorMerge
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private static PagadorDDA merge(DDA0005R1ComplexType pagadorRetorno, MensagemDDAPagador msgPagador, PagadorDDA pagadorMerge) throws ComumException {
        return merge(msgPagador, pagadorMerge, pagadorRetorno.getNumIdentcPagdr(), pagadorRetorno.getNumRefAtlCadCliPagdr(), pagadorRetorno.getNumSeqAtlzCadCliPagdr());
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @param pagadorMerge
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA merge(GrupoADDA005RETPagdrActoComplexType grupoPagador, PagadorDDA pagadorMerge) throws ComumException {
        pagadorMerge.setNumIdentificaPagadorCip(grupoPagador.getNumIdentcPagdr().longValue());
        pagadorMerge.setNumRefAtualCadPagador(grupoPagador.getNumRefAtlCadCliPagdr().longValue());
        pagadorMerge.setNumSeqAtualCadPagador(grupoPagador.getNumSeqAtlzCadCliPagdr().longValue());

        return pagadorMerge;
    }

    /**
     * Método responsável por
     * 
     * @param pagadorRetorno
     * @param pagadorRemove
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private static PagadorDDA remove(DDA0006R1ComplexType pagadorRetorno, PagadorDDA pagadorRemove) throws ComumException {
        return remove(pagadorRemove, pagadorRetorno.getNumIdentcPagdr(), pagadorRetorno.getNumRefAtlCadCliPagdr(), pagadorRetorno.getNumSeqAtlzCadCliPagdr());
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @param pagadorMerge
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA merge(GrupoDDA0002R1PagdrComplexType grupoPagador, PagadorDDA pagadorMerge) {
        PagadorDDA pagadorDDA = setPagador(grupoPagador);
        pagadorDDA.setId(pagadorMerge.getId());

        if (!ObjectUtil.isNull(grupoPagador.getGrupoDDA0002R1PagdrProprio())) {
            mergeListaPagadorConta(grupoPagador.getGrupoDDA0002R1PagdrProprio(), pagadorDDA);
            mergeListaPagadorAgregado(grupoPagador.getGrupoDDA0002R1PagdrProprio(), pagadorDDA);
        }

        return pagadorDDA;
    }

    /**
     * Método responsável por
     * 
     * @param conteudo
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA merge(GrupoADDA002PagdrComplexType grupoPagador, PagadorDDA pagadorMerge) {
        PagadorDDA pagadorDDA = setPagador(grupoPagador);
        pagadorDDA.setId(pagadorMerge.getId());

        if (!ObjectUtil.isNull(grupoPagador.getGrupoADDA002PagdrProprio())) {
            mergeListaPagadorConta(grupoPagador.getGrupoADDA002PagdrProprio(), pagadorDDA);
            mergeListaPagadorAgregado(grupoPagador.getGrupoADDA002PagdrProprio(), pagadorDDA);
        }

        return pagadorDDA;
    }

    /**
     * Método responsável por fazer a conversao do pagador quando recebido uma DDA0005R1ComplexType (mensagem) - ALTERAÇÃO
     * 
     * @param msgPagador
     * @param numIdentcPagdr
     * @param numRefAtlCadPagdr
     * @param numSeqAtlzCadPagdr
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private static PagadorDDA merge(MensagemDDAPagador msgPagador, PagadorDDA pagadorMerge, BigInteger numIdentcPagdr, BigInteger numRefAtlCadPagdr, BigInteger numSeqAtlzCadPagdr)
            throws ComumException {
        pagadorMerge.setNumIdentificaPagadorCip(numIdentcPagdr.longValue());
        pagadorMerge.setNumRefAtualCadPagador(numRefAtlCadPagdr.longValue());
        pagadorMerge.setNumSeqAtualCadPagador(numSeqAtlzCadPagdr.longValue());

        mergeListaPagadorConta(msgPagador, pagadorMerge);

        mergePagadorAgregado(msgPagador, pagadorMerge);

        return pagadorMerge;
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @param pagadorMerge
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA merge(GrupoADDA003PagdrDDAComplexType grupoPagador, PagadorDDA pagadorMerge) {
        pagadorMerge.setQtdAdesaoDDA(grupoPagador.getQtdAdesCliPagdrDDA());
        return pagadorMerge;
    }

    /**
     * Método responsável por fazer a conversao do pagador quando recebido uma DDA0006R1ComplexType (mensagem) - EXCLUSÃO
     * 
     * @param pagadorRemove
     * @param numIdentcPagdr
     * @param numRefAtlCadPagdr
     * @param numSeqAtlzCadPagdr
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private static PagadorDDA remove(PagadorDDA pagadorRemove, BigInteger numIdentcPagdr, BigInteger numRefAtlCadPagdr, BigInteger numSeqAtlzCadPagdr) throws ComumException {
        pagadorRemove.setNumIdentificaPagadorCip(numIdentcPagdr.longValue());
        pagadorRemove.setNumRefAtualCadPagador(numRefAtlCadPagdr.longValue());
        pagadorRemove.setNumSeqAtualCadPagador(numSeqAtlzCadPagdr.longValue());
        pagadorRemove.setQtdAdesaoDDA(pagadorRemove.getQtdAdesaoDDA() == 0 ? 0 : (pagadorRemove.getQtdAdesaoDDA() - 1));
        if (pagadorRemove.getQtdAdesaoDDA() == 0) {
            pagadorRemove.setBolSacadoEletronico(Boolean.FALSE);
        }
        return pagadorRemove;
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorDDA
     * @param pagadorRemove
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private static PagadorDDA remove(GrupoADDA003PagdrDDAComplexType grupoPagadorDDA, PagadorDDA pagadorRemove) throws ComumException {
        pagadorRemove.setQtdAdesaoDDA(grupoPagadorDDA.getQtdAdesCliPagdrDDA());
        if (pagadorRemove.getQtdAdesaoDDA() == 0) {
            pagadorRemove.setBolSacadoEletronico(Boolean.FALSE);
        }
        return pagadorRemove;
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorDDA
     * @param pagadorRemove
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    private static PagadorDDA remove(GrupoADDA006RETPagdrActoComplexType grupoPagadorDDA, PagadorDDA pagadorRemove) throws ComumException {
        pagadorRemove.setNumIdentificaPagadorCip(grupoPagadorDDA.getNumIdentcPagdr().longValue());
        pagadorRemove.setNumRefAtualCadPagador(grupoPagadorDDA.getNumRefAtlCadCliPagdr().longValue());
        pagadorRemove.setNumSeqAtualCadPagador(grupoPagadorDDA.getNumSeqAtlzCadCliPagdr().longValue());
        pagadorRemove.setQtdAdesaoDDA(pagadorRemove.getQtdAdesaoDDA() == 0 ? 0 : (pagadorRemove.getQtdAdesaoDDA() - 1));
        if (pagadorRemove.getQtdAdesaoDDA() == 0) {
            pagadorRemove.setBolSacadoEletronico(Boolean.FALSE);
        }
        return pagadorRemove;
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorProprio
     * @param pagadorDDA void
     * 
     */
    private static void mergeListaPagadorAgregado(GrupoDDA0002R1PagdrProprioComplexType grupoPagadorProprio, PagadorDDA pagadorDDA) {
        for (GrupoDDA0002R1AgrgdDDAComplexType grupoPagadorAgregado : grupoPagadorProprio.getGrupoDDA0002R1AgrgdDDA()) {
            mergePagadorAgregado(grupoPagadorAgregado, pagadorDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorAgregado
     * @param pagadorDDA void
     * 
     */
    private static void mergePagadorAgregado(GrupoDDA0002R1AgrgdDDAComplexType grupoPagadorAgregado, PagadorDDA pagadorDDA) {
        if (isNaoExistePagadorDDAAgregadoNaBase(pagadorDDA, DataCipUtil.obterCPFCNPJ(grupoPagadorAgregado.getCNPJCPFAgrgd(), grupoPagadorAgregado.getTpPessoaAgrgd()))) {
            setPagadorAgregado(pagadorDDA, grupoPagadorAgregado);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorProprio
     * @param pagadorDDA void
     * 
     */
    private static void mergeListaPagadorConta(GrupoDDA0002R1PagdrProprioComplexType grupoPagadorProprio, PagadorDDA pagadorDDA) {
        for (GrupoDDA0002R1CtCliPagdrComplexType grupoPagadorConta : grupoPagadorProprio.getGrupoDDA0002R1CtCliPagdr()) {
            mergePagadorConta(grupoPagadorConta, pagadorDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorConta
     * @param pagadorDDA void
     * 
     */
    private static void mergePagadorConta(GrupoDDA0002R1CtCliPagdrComplexType grupoPagadorConta, PagadorDDA pagadorDDA) {
        if (isNaoExistePagadorDDAContaNaBase(pagadorDDA, grupoPagadorConta.getAgCliPagdr(), grupoPagadorConta.getCtCliPagdr())) {
            setPagadorConta(pagadorDDA, grupoPagadorConta);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoADDA002PagdrProprio
     * @param pagadorDDA void
     * 
     */
    private static void mergeListaPagadorConta(GrupoADDA002PagdrProprioComplexType grupoPagadorProprio, PagadorDDA pagadorDDA) {
        for (GrupoADDA002CtCliPagdrComplexType grupoPagadorConta : grupoPagadorProprio.getGrupoADDA002CtCliPagdr()) {
            mergePagadorConta(grupoPagadorConta, pagadorDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorConta
     * @param pagadorDDA void
     * 
     */
    private static void mergePagadorConta(GrupoADDA002CtCliPagdrComplexType grupoPagadorConta, PagadorDDA pagadorDDA) {
        if (isNaoExistePagadorDDAContaNaBase(pagadorDDA, grupoPagadorConta.getAgCliPagdr(), grupoPagadorConta.getCtCliPagdr())) {
            setPagadorConta(pagadorDDA, grupoPagadorConta);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorProprio
     * @param pagadorDDA void
     * 
     */
    private static void mergeListaPagadorAgregado(GrupoADDA002PagdrProprioComplexType grupoPagadorProprio, PagadorDDA pagadorDDA) {
        for (GrupoADDA002AgrgdDDAComplexType grupoPagadorAgregado : grupoPagadorProprio.getGrupoADDA002AgrgdDDA()) {
            mergePagadorAgregado(grupoPagadorAgregado, pagadorDDA);
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorAgregado
     * @param pagadorDDA void
     * 
     */
    private static void mergePagadorAgregado(GrupoADDA002AgrgdDDAComplexType grupoPagadorAgregado, PagadorDDA pagadorDDA) {
        if (isNaoExistePagadorDDAAgregadoNaBase(pagadorDDA, DataCipUtil.obterCPFCNPJ(grupoPagadorAgregado.getCNPJCPFAgrgd(), grupoPagadorAgregado.getTpPessoaAgrgd()))) {
            setPagadorAgregado(pagadorDDA, grupoPagadorAgregado);
        }
    }

    /**
     * Método responsável por
     * 
     * @param msgPagador
     * @param pagadorDDA void
     * 
     */
    private static void mergeListaPagadorConta(MensagemDDAPagador msgPagador, PagadorDDA pagadorDDA) {
        for (MensagemDDAPagadorConta mensagemDDAPagadorConta : msgPagador.getListaMensagemDDAPagadorConta()) {
            mergePagadorConta(pagadorDDA, mensagemDDAPagadorConta);
        }
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDDA
     * @param mensagemDDAPagadorConta void
     * 
     */
    private static void mergePagadorConta(PagadorDDA pagadorDDA, MensagemDDAPagadorConta mensagemDDAPagadorConta) {
        if (mensagemDDAPagadorConta.getCodTipoOperacao().equals(TipoOperacaoSicoobDDAEnum.INCLUSAO.getCodigoOperacao())
                && isNaoExistePagadorDDAContaNaBase(pagadorDDA, mensagemDDAPagadorConta.getNumAgencia(), mensagemDDAPagadorConta.getNumContaCorrente())) {
            incluirPagadorConta(pagadorDDA, mensagemDDAPagadorConta);
        } else if (mensagemDDAPagadorConta.getCodTipoOperacao().equals(TipoOperacaoSicoobDDAEnum.EXCLUSAO.getCodigoOperacao())) {
            excluirPagadorConta(pagadorDDA, mensagemDDAPagadorConta);
        }
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDDA
     * @param mensagemDDAPagadorConta void
     * 
     */
    private static void incluirPagadorConta(PagadorDDA pagadorDDA, MensagemDDAPagadorConta mensagemDDAPagadorConta) {
        pagadorDDA.getListaPagadorDDAConta().add(setPagadorConta(pagadorDDA, mensagemDDAPagadorConta));
    }

    /**
     * Método responsável por fazer a validação caso a conta do pagador na mensagem ja tenha na base de dados, para nao duplicar.
     * 
     * @param pagadorDDA
     * @param pagadorConta
     * @return boolean
     * 
     */
    private static boolean isNaoExistePagadorDDAContaNaBase(PagadorDDA pagadorDDA, BigInteger numAgencia, BigInteger numContaCorrente) {
        return isNaoExistePagadorDDAContaNaBase(pagadorDDA, numAgencia.intValue(), new BigDecimal(numContaCorrente));
    }

    /**
     * Método responsável por fazer a validação caso a conta do pagador na mensagem ja tenha na base de dados, para nao duplicar.
     * 
     * @param pagadorDDA
     * @param pagadorConta
     * @return boolean
     * 
     */
    private static boolean isNaoExistePagadorDDAContaNaBase(PagadorDDA pagadorDDA, Integer numAgencia, BigDecimal numContaCorrente) {
        for (PagadorDDAConta pagadorDDAConta : pagadorDDA.getListaPagadorDDAConta()) {
            if (pagadorDDAConta.getNumAgencia().equals(numAgencia) && pagadorDDAConta.getNumContaCorrente().equals(numContaCorrente)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDDA
     * @param mensagemDDAPagadorConta void
     * 
     */
    private static void excluirPagadorConta(PagadorDDA pagadorDDA, MensagemDDAPagadorConta mensagemDDAPagadorConta) {
        List<PagadorDDAConta> listaExclusao = new ArrayList<PagadorDDAConta>();
        if (pagadorDDA.getListaPagadorDDAConta().size() != 1) {
            for (PagadorDDAConta pagadorDDAConta : pagadorDDA.getListaPagadorDDAConta()) {
                if (pagadorDDAConta.getNumAgencia().equals(mensagemDDAPagadorConta.getNumAgencia())
                        && pagadorDDAConta.getNumContaCorrente().equals(mensagemDDAPagadorConta.getNumContaCorrente())) {
                    listaExclusao.add(pagadorDDAConta);
                }
            }
            pagadorDDA.getListaPagadorDDAConta().removeAll(listaExclusao);
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param pagadorDDA
     * @throws ComumException void
     * 
     */
    private static void mergePagadorAgregado(MensagemDDAPagador mensagem, PagadorDDA pagadorDDA) throws ComumException {
        for (MensagemDDAPagadorAgregado mensagemDDAPagadorAgregado : mensagem.getListaMensagemDDAPagadorAgregado()) {
            if (mensagemDDAPagadorAgregado.getCodTipoOperacao().equals(TipoOperacaoSicoobDDAEnum.INCLUSAO.getCodigoOperacao())
                    && isNaoExistePagadorDDAAgregadoNaBase(pagadorDDA, mensagemDDAPagadorAgregado.getNumCpfCnpjAgregado())) {
                incluirPagadorAgregado(pagadorDDA, mensagemDDAPagadorAgregado.getNumCpfCnpjAgregado(), mensagemDDAPagadorAgregado.getCodTipoPessoaAgregado());
            } else if (mensagemDDAPagadorAgregado.getCodTipoOperacao().equals(TipoOperacaoSicoobDDAEnum.EXCLUSAO.getCodigoOperacao())) {
                removerPagadorAgregado(pagadorDDA, mensagemDDAPagadorAgregado.getNumCpfCnpjAgregado());
            }
        }
    }

    /**
     * Método responsável por fazer a validação caso o pagador agregado na mensagem ja tenha na base de dados, para nao duplicar.
     * 
     * @param pagadorDDA
     * @param pagadorConta
     * @return boolean
     * 
     */
    private static boolean isNaoExistePagadorDDAAgregadoNaBase(PagadorDDA pagadorDDA, String cpfCnpj) {
        for (PagadorDDAAgregado pagadorDDAAgregado : pagadorDDA.getListaPagadorDDAAgregado()) {
            if (pagadorDDAAgregado.getNumCpfCnpjAgregado().equals(cpfCnpj)) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDDA
     * @param mensagemDDAPagadorAgregado void
     * @throws ComumException
     * 
     */
    private static void incluirPagadorAgregado(PagadorDDA pagadorDDA, String numCpfCnpjAgregado, String codTipoPessoaAgregado) throws ComumException {
        pagadorDDA.getListaPagadorDDAAgregado().add(setPagadorAgregado(pagadorDDA, numCpfCnpjAgregado, codTipoPessoaAgregado));
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDDA
     * @param mensagemDDAPagadorAgregado void
     * 
     */
    private static void removerPagadorAgregado(PagadorDDA pagadorDDA, String numCpfCnpjAgregado) {
        List<PagadorDDAAgregado> listaExclusao = new ArrayList<PagadorDDAAgregado>();
        for (PagadorDDAAgregado pagadorDDAAgregado : pagadorDDA.getListaPagadorDDAAgregado()) {
            if (pagadorDDAAgregado.getNumCpfCnpjAgregado().equals(numCpfCnpjAgregado)) {
                listaExclusao.add(pagadorDDAAgregado);
            }
        }
        pagadorDDA.getListaPagadorDDAAgregado().removeAll(listaExclusao);
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA setPagador(GrupoADDA003PagdrDDAComplexType grupoPagador) {
        return new PagadorDDA(grupoPagador.getCNPJCPFPagdr().toString(), grupoPagador.getTpPessoaPagdr(), grupoPagador.getQtdAdesCliPagdrDDA());
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA setPagador(GrupoDDA0002R1PagdrComplexType grupoPagador) {
        PagadorDDA pagadorDDA;
        if (ObjectUtil.isNull(grupoPagador.getGrupoDDA0002R1PagdrProprio())) {
            pagadorDDA = new PagadorDDA(grupoPagador.getCNPJCPFPagdr().toString(), grupoPagador.getTpPessoaPagdr(), grupoPagador.getQtdAdesCliPagdrDDA());
        } else {
            GrupoDDA0002R1PagdrProprioComplexType grupoPagadorProprio = grupoPagador.getGrupoDDA0002R1PagdrProprio();

            pagadorDDA = new PagadorDDA(grupoPagador.getCNPJCPFPagdr().toString(), grupoPagador.getTpPessoaPagdr(), grupoPagadorProprio.getNumIdentcPagdr(),
                    grupoPagadorProprio.getNumRefAtlCadCliPagdr(), grupoPagador.getQtdAdesCliPagdrDDA(),

                    grupoPagadorProprio.getNumSeqAtlzCadCliPagdr());

            if (!grupoPagadorProprio.getSitCliPagdrPart().equals(Constantes.SITUACAO_PAGADOR_DDA_ADESAO_EXCLUIDO)) {
                setListaPagadorConta(grupoPagadorProprio, pagadorDDA);
                setListaPagadorAgregado(grupoPagadorProprio, pagadorDDA);
            }
        }
        return pagadorDDA;
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagador
     * @return PagadorDDA
     * 
     */
    private static PagadorDDA setPagador(GrupoADDA002PagdrComplexType grupoPagador) {
        PagadorDDA pagadorDDA;
        if (ObjectUtil.isNull(grupoPagador.getGrupoADDA002PagdrProprio())) {
            pagadorDDA = new PagadorDDA(grupoPagador.getCNPJCPFPagdr().toString(), grupoPagador.getTpPessoaPagdr(), grupoPagador.getQtdAdesCliPagdrDDA());
        } else {
            GrupoADDA002PagdrProprioComplexType grupoPagadorProprio = grupoPagador.getGrupoADDA002PagdrProprio();

            pagadorDDA = new PagadorDDA(grupoPagador.getCNPJCPFPagdr().toString(), grupoPagador.getTpPessoaPagdr(), grupoPagadorProprio.getNumIdentcPagdr(),
                    grupoPagadorProprio.getNumRefAtlCadCliPagdr(), grupoPagador.getQtdAdesCliPagdrDDA(), grupoPagadorProprio.getNumSeqAtlzCadCliPagdr());

            if (!grupoPagadorProprio.getSitCliPagdrPart().equals(Constantes.SITUACAO_PAGADOR_DDA_ADESAO_EXCLUIDO)) {
                setListaPagadorConta(grupoPagadorProprio, pagadorDDA);
                setListaPagadorAgregado(grupoPagadorProprio, pagadorDDA);
            }

        }
        return pagadorDDA;
    }

    /**
     * Método responsável por obter ListaPagadorConta pelo GrupoDDA0002R1PagdrProprioComplexType
     * 
     * @param grupoPagadorProprio
     * @param pagadorDDA void
     * 
     */
    private static void setListaPagadorConta(GrupoDDA0002R1PagdrProprioComplexType grupoPagadorProprio, PagadorDDA pagadorDDA) {
        for (GrupoDDA0002R1CtCliPagdrComplexType grupoPagadorConta : grupoPagadorProprio.getGrupoDDA0002R1CtCliPagdr()) {
            pagadorDDA.getListaPagadorDDAConta().add(setPagadorConta(pagadorDDA, grupoPagadorConta));
        }
    }

    /**
     * Método responsável por obter ListaPagadorConta pelo GrupoDDA0002R1CtPagdrComplexType
     * 
     * @param pagadorDDA
     * @param grupoPagadorConta
     * @return PagadorDDAConta
     * 
     */
    private static PagadorDDAConta setPagadorConta(PagadorDDA pagadorDDA, GrupoDDA0002R1CtCliPagdrComplexType grupoPagadorConta) {
        return new PagadorDDAConta(pagadorDDA, grupoPagadorConta.getAgCliPagdr(), grupoPagadorConta.getCtCliPagdr(), DataCipUtil.xMLGregorianCalendarParaDateTime(grupoPagadorConta
                .getDtAdesCliPagdrDDA()));
    }

    /**
     * Método responsável por obter ListaPagadorAgregado pelo GrupoDDA0002R1PagdrProprioComplexType
     * 
     * @param grupoPagadorProprio
     * @param pagadorDDA void
     * 
     */
    private static void setListaPagadorAgregado(GrupoDDA0002R1PagdrProprioComplexType grupoPagadorProprio, PagadorDDA pagadorDDA) {
        for (GrupoDDA0002R1AgrgdDDAComplexType grupoPagadorAgregado : grupoPagadorProprio.getGrupoDDA0002R1AgrgdDDA()) {
            pagadorDDA.getListaPagadorDDAAgregado().add(setPagadorAgregado(pagadorDDA, grupoPagadorAgregado));
        }
    }

    /**
     * Método responsável por obter o PagadorDDAAgregado pelo GrupoDDA0002R1AgrgdDDAComplexType
     * 
     * @param pagadorDDA
     * @param grupoPagadorAgregado
     * @return PagadorDDAAgregado
     * 
     */
    private static PagadorDDAAgregado setPagadorAgregado(PagadorDDA pagadorDDA, GrupoDDA0002R1AgrgdDDAComplexType grupoPagadorAgregado) {
        return new PagadorDDAAgregado(pagadorDDA, grupoPagadorAgregado.getCNPJCPFAgrgd().toString(), grupoPagadorAgregado.getTpPessoaAgrgd());
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorProprio
     * @param pagadorDDA void
     * 
     */
    private static void setListaPagadorConta(GrupoADDA002PagdrProprioComplexType grupoPagadorProprio, PagadorDDA pagadorDDA) {
        for (GrupoADDA002CtCliPagdrComplexType grupoPagadorConta : grupoPagadorProprio.getGrupoADDA002CtCliPagdr()) {
            pagadorDDA.getListaPagadorDDAConta().add(setPagadorConta(pagadorDDA, grupoPagadorConta));
        }
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDDA
     * @param grupoPagadorConta
     * @return PagadorDDAConta
     * 
     */
    private static PagadorDDAConta setPagadorConta(PagadorDDA pagadorDDA, GrupoADDA002CtCliPagdrComplexType grupoPagadorConta) {
        return new PagadorDDAConta(pagadorDDA, grupoPagadorConta.getAgCliPagdr(), grupoPagadorConta.getCtCliPagdr(), DataCipUtil.xMLGregorianCalendarParaDateTime(grupoPagadorConta
                .getDtAdesCliPagdrDDA()));
    }

    /**
     * Método responsável por
     * 
     * @param grupoPagadorProprio
     * @param pagadorDDA void
     * 
     */
    private static void setListaPagadorAgregado(GrupoADDA002PagdrProprioComplexType grupoPagadorProprio, PagadorDDA pagadorDDA) {
        for (GrupoADDA002AgrgdDDAComplexType grupoPagadorAgregado : grupoPagadorProprio.getGrupoADDA002AgrgdDDA()) {
            pagadorDDA.getListaPagadorDDAAgregado().add(setPagadorAgregado(pagadorDDA, grupoPagadorAgregado));
        }
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDDA
     * @param grupoPagadorAgregado
     * @return PagadorDDAAgregado
     * 
     */
    private static PagadorDDAAgregado setPagadorAgregado(PagadorDDA pagadorDDA, GrupoADDA002AgrgdDDAComplexType grupoPagadorAgregado) {
        return new PagadorDDAAgregado(pagadorDDA, grupoPagadorAgregado.getCNPJCPFAgrgd().toString(), grupoPagadorAgregado.getTpPessoaAgrgd());
    }

    /**
     * Método responsável por obter ListaPagadorConta pelo MensagemDDAPagador
     * 
     * @param msgPagador
     * @param pagadorDDA void
     * 
     */
    private static void setListaPagadorConta(MensagemDDAPagador msgPagador, PagadorDDA pagadorDDA) {
        for (MensagemDDAPagadorConta mensagemDDAPagadorConta : msgPagador.getListaMensagemDDAPagadorConta()) {
            pagadorDDA.getListaPagadorDDAConta().add(setPagadorConta(pagadorDDA, mensagemDDAPagadorConta));
        }
    }

    /**
     * Método responsável por obter o PagadorDDAConta pelo MensagemDDAPagadorConta
     * 
     * @param pagadorDDA
     * @param mensagemDDAPagadorConta
     * @return PagadorDDAConta
     * 
     */
    private static PagadorDDAConta setPagadorConta(PagadorDDA pagadorDDA, MensagemDDAPagadorConta mensagemDDAPagadorConta) {
        return new PagadorDDAConta(pagadorDDA, mensagemDDAPagadorConta.getNumAgencia(), mensagemDDAPagadorConta.getNumContaCorrente(), mensagemDDAPagadorConta.getDataHoraAdesao());
    }

    /**
     * Método responsável por obter ListaPagadorAgregado pelo MensagemDDAPagador
     * 
     * @param msgPagador
     * @param pagadorDDA void
     * 
     */
    private static void setListaPagadorAgregado(MensagemDDAPagador msgPagador, PagadorDDA pagadorDDA) {
        for (MensagemDDAPagadorAgregado mensagemDDAPagadorAgregado : msgPagador.getListaMensagemDDAPagadorAgregado()) {
            pagadorDDA.getListaPagadorDDAAgregado().add(
                    setPagadorAgregado(pagadorDDA, mensagemDDAPagadorAgregado.getNumCpfCnpjAgregado(), mensagemDDAPagadorAgregado.getCodTipoPessoaAgregado()));
        }
    }

    /**
     * Método responsável por obter o PagadorDDAAgregado pelo MensagemDDAPagadorAgregado
     * 
     * @param pagadorDDA
     * @param mensagemDDAPagadorAgregado
     * @return PagadorDDAAgregado
     * 
     */
    private static PagadorDDAAgregado setPagadorAgregado(PagadorDDA pagadorDDA, String numCpfCnpjAgregado, String codTipoPessoaAgregado) {
        return new PagadorDDAAgregado(pagadorDDA, numCpfCnpjAgregado, codTipoPessoaAgregado);
    }

}
