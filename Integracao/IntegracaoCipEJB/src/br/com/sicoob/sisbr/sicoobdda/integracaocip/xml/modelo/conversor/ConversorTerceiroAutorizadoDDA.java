package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDAGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122.GrupoADDA122RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3BaixaEftComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3BaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3DettBaixaOperacComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3TercComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.GrupoDDA0121R3TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * ConversorBaixaOperacionalDDA
 * 
 * @author Francisco.Marcio
 */
public class ConversorTerceiroAutorizadoDDA {

    /**
     * @param conteudo
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    public static BoletoDDATerceiroAut converter(ConteudoMsgRecebida conteudo) throws ComumException {
        return converter(conteudo, null);
    }

    /**
     * @param conteudo
     * @param mensagem
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    public static BoletoDDATerceiroAut converter(ConteudoMsgRecebida conteudo, MensagemDDATerceiroAut mensagem) throws ComumException {
        BoletoDDATerceiroAut terceiroAutorizado = null;
        if (conteudo instanceof DDA0121R1ComplexType) {
            terceiroAutorizado = conveterDDA0121R1((DDA0121R1ComplexType) conteudo, mensagem);
        } else if (conteudo instanceof DDA0121R2ComplexType) {
            terceiroAutorizado = converterDDA0121R2((DDA0121R2ComplexType) conteudo);
        } else if (conteudo instanceof GrupoADDA121RR2TitComplexType) {
            terceiroAutorizado = converterADDA0121RR2((GrupoADDA121RR2TitComplexType) conteudo);
        }
        return terceiroAutorizado;
    }

    /**
     * Método responsável por converter mensagens ou arquivos 121R3 em boletoDDA;
     * 
     * @param conteudo
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    public static BoletoDDA converterBoletoDDAR3(ConteudoMsgRecebida conteudo) throws ComumException {
        BoletoDDA boleto = null;
        if (conteudo instanceof GrupoDDA0121R3TitComplexType) {
            boleto = converterBoletoDDA((GrupoDDA0121R3TitComplexType) conteudo);
        } else if (conteudo instanceof GrupoADDA121RR3TitComplexType) {
            boleto = converterBoletoDDA((GrupoADDA121RR3TitComplexType) conteudo);
        }
        return boleto;
    }

    /**
     * @param boletoDDATerceiroAut
     * @param conteudo
     * @param mensagem
     * @return BoletoDDATerceiroAut
     * 
     */
    public static BoletoDDATerceiroAut merge(BoletoDDATerceiroAut boletoDDATerceiroAut, Long numIdentificadorTerceiro, Long numRefAtualTerceiro) {
        boletoDDATerceiroAut.setNumIdentificadorTerceiro(numIdentificadorTerceiro);
        boletoDDATerceiroAut.setNumRefAtualTerceiro(numRefAtualTerceiro);
        boletoDDATerceiroAut.setBolAtivo(Boolean.TRUE);
        return boletoDDATerceiroAut;

    }

    /**
     * Método responsável por realizar merge no boleto para as mensagens/arquivos 121R3
     * 
     * @param conteudo
     * @param boleto
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    public static BoletoDDA mergeBoletoDDAR3(ConteudoMsgRecebida conteudo, BoletoDDA boleto) throws ComumException {
        BoletoDDA boletoRetorno = null;
        if (conteudo instanceof GrupoDDA0121R3TitComplexType) {
            boletoRetorno = mergeBoletoDDA((GrupoDDA0121R3TitComplexType) conteudo, boleto.getId());
        } else if (conteudo instanceof GrupoADDA121RR3TitComplexType) {
            boletoRetorno = mergeBoletoDDA((GrupoADDA121RR3TitComplexType) conteudo, boleto.getId());
        }
        return boletoRetorno;
    }

    /**
     * 
     * @param conteudo
     * @param id
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA mergeBoletoDDA(GrupoDDA0121R3TitComplexType conteudo, Long idBoleto) {
        BoletoDDA boleto = converterBoletoDDA(conteudo);
        boleto.setId(idBoleto);
        return boleto;
    }

    /**
     * @param conteudo
     * @param idBoleto
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA mergeBoletoDDA(GrupoADDA121RR3TitComplexType conteudo, Long idBoleto) {
        BoletoDDA boleto = converterBoletoDDA(conteudo);
        boleto.setId(idBoleto);
        return boleto;
    }

    /**
     * @param boletoTerceiroAut
     * @param conteudo
     * @return BoletoDDATerceiroAut
     * 
     */
    public static BoletoDDATerceiroAut converterMensagem122Exclusao(BoletoDDATerceiroAut boletoTerceiroAut, ConteudoMsgRecebida conteudo) {
        if (conteudo instanceof DDA0122R1ComplexType) {
            DDA0122R1ComplexType dda0122R1 = (DDA0122R1ComplexType) conteudo;
            boletoTerceiroAut.setNumRefAtualTerceiro(dda0122R1.getNumRefAtlCadTerc().longValue());
        } else if (conteudo instanceof DDA0122R2ComplexType) {
            DDA0122R2ComplexType dda0122R2 = (DDA0122R2ComplexType) conteudo;
            boletoTerceiroAut.setNumRefAtualTerceiro(dda0122R2.getNumRefAtlCadTerc().longValue());
        } else if (conteudo instanceof GrupoADDA122RR2TitComplexType) {
            GrupoADDA122RR2TitComplexType grupoAdda122R2 = (GrupoADDA122RR2TitComplexType) conteudo;
            boletoTerceiroAut.setNumRefAtualTerceiro(grupoAdda122R2.getNumRefAtlCadTerc().longValue());
        }
        boletoTerceiroAut.setBolAtivo(Boolean.FALSE);
        return boletoTerceiroAut;
    }

    /**
     * @param retornoDDA
     * @param mensagem
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    private static BoletoDDATerceiroAut conveterDDA0121R1(DDA0121R1ComplexType retornoDDA, MensagemDDATerceiroAut mensagem) throws ComumException {
        BoletoDDATerceiroAut boletoTerceiroAut = new BoletoDDATerceiroAut();

        boletoTerceiroAut.setCodTipoPessoaAutorizador(mensagem.getCodTipoPessoaAutorizador());
        boletoTerceiroAut.setNumCpfCnpjAutorizador(mensagem.getNumCpfCnpjAutorizador(),
                mensagem.getCodTipoPessoaAutorizador().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoTerceiroAut.setCodTipoPessoaTerceiro(mensagem.getCodTipoPessoaTerceiro());
        boletoTerceiroAut.setNumCpfCnpjTerceiro(mensagem.getNumCpfCnpjTerceiro(),
                mensagem.getCodTipoPessoaTerceiro().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoTerceiroAut.setNumIdentificadorTerceiro(retornoDDA.getNumIdentcTerc().longValue());
        boletoTerceiroAut.setNumRefAtualTerceiro(retornoDDA.getNumRefAtlCadTerc().longValue());

        boletoTerceiroAut.setBolAtivo(Boolean.TRUE);

        return boletoTerceiroAut;

    }

    /**
     * @param retornoCIP
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    private static BoletoDDATerceiroAut converterDDA0121R2(DDA0121R2ComplexType retornoCIP) throws ComumException {
        BoletoDDATerceiroAut boletoTerceiroAut = new BoletoDDATerceiroAut();

        boletoTerceiroAut.setCodTipoPessoaAutorizador(retornoCIP.getTpPessoaPagdrAutzdr());
        boletoTerceiroAut.setNumCpfCnpjAutorizador(retornoCIP.getCNPJCPFPagdrAutzdr().toString(),
                retornoCIP.getTpPessoaPagdrAutzdr().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoTerceiroAut.setCodTipoPessoaTerceiro(retornoCIP.getTpPessoaTerc());
        boletoTerceiroAut.setNumCpfCnpjTerceiro(retornoCIP.getCNPJCPFTerc().toString(),
                retornoCIP.getTpPessoaTerc().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoTerceiroAut.setNumIdentificadorTerceiro(retornoCIP.getNumIdentcTerc().longValue());
        boletoTerceiroAut.setNumRefAtualTerceiro(retornoCIP.getNumRefAtlCadTerc().longValue());
        boletoTerceiroAut.setBolAtivo(Boolean.TRUE);

        return boletoTerceiroAut;
    }

    /**
     * @param retornoCIP
     * @return
     * @throws ComumException BoletoDDATerceiroAut
     * 
     */
    private static BoletoDDATerceiroAut converterADDA0121RR2(GrupoADDA121RR2TitComplexType retornoCIP) throws ComumException {
        BoletoDDATerceiroAut boletoTerceiroAut = new BoletoDDATerceiroAut();

        boletoTerceiroAut.setCodTipoPessoaAutorizador(retornoCIP.getTpPessoaPagdrAutzdr());
        boletoTerceiroAut.setNumCpfCnpjAutorizador(retornoCIP.getCNPJCPFPagdrAutzdr().toString(),
                retornoCIP.getTpPessoaPagdrAutzdr().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoTerceiroAut.setCodTipoPessoaTerceiro(retornoCIP.getTpPessoaTerc());
        boletoTerceiroAut.setNumCpfCnpjTerceiro(retornoCIP.getCNPJCPFTerc().toString(),
                retornoCIP.getTpPessoaTerc().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoTerceiroAut.setNumIdentificadorTerceiro(retornoCIP.getNumIdentcTerc().longValue());
        boletoTerceiroAut.setNumRefAtualTerceiro(retornoCIP.getNumRefAtlCadTerc().longValue());

        boletoTerceiroAut.setBolAtivo(Boolean.TRUE);

        return boletoTerceiroAut;
    }

    /**
     * @param retornoCIP
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converterBoletoDDA(GrupoADDA121RR3TitComplexType retornoCIP) {
        BoletoDDA boletoDDA = new BoletoDDA();

        boletoDDA.setNumIdentificadorBoletoCip(retornoCIP.getNumIdentcTit().longValue());
        boletoDDA.setNumRefAtualCadBoleto(ObjectUtil.isEmpty(retornoCIP.getNumRefAtlCadTit()) ? null : retornoCIP.getNumRefAtlCadTit().longValue());
        boletoDDA.setNumSeqAtualCadBoleto(retornoCIP.getNumSeqAtlzCadTit().longValue());

        boletoDDA.setCodIspbPartDestinatario(retornoCIP.getISPBPartDestinatario());
        boletoDDA.setCodPartDestinatario(ObjectUtil.isEmpty(retornoCIP.getCodPartDestinatario()) ? null : retornoCIP.getCodPartDestinatario());

        boletoDDA.setCodTipoPessoaBeneficiario(retornoCIP.getTpPessoaBenfcrioOr());
        boletoDDA.setNumCpfCnpjBeneficiario(retornoCIP.getCNPJCPFBenfcrioOr().toString(),
                retornoCIP.getTpPessoaBenfcrioOr().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoDDA.setNomeBeneficiario(retornoCIP.getNomRzSocBenfcrioOr());
        boletoDDA.setNomeFantasiaBeneficiario(ObjectUtil.isEmpty(retornoCIP.getNomFantsBenfcrioOr()) ? null : retornoCIP.getNomFantsBenfcrioOr());
        boletoDDA.setDescLogradouroBeneficiario(ObjectUtil.isEmpty(retornoCIP.getLogradBenfcrioOr()) ? null : retornoCIP.getLogradBenfcrioOr());
        boletoDDA.setDescCidadeBeneficiario(ObjectUtil.isEmpty(retornoCIP.getCidBenfcrioOr()) ? null : retornoCIP.getCidBenfcrioOr());
        boletoDDA.setSiglaUfBeneficiario(ObjectUtil.isEmpty(retornoCIP.getUFBenfcrioOr()) ? null : retornoCIP.getUFBenfcrioOr());
        boletoDDA.setNumCepBeneficiario(ObjectUtil.isEmpty(retornoCIP.getCEPBenfcrioOr()) ? null : retornoCIP.getCEPBenfcrioOr().toString());

        boletoDDA.setCodTipoPessoaBeneficiarioFinal(ObjectUtil.isEmpty(retornoCIP.getTpPessoaBenfcrioFinl()) ? null : retornoCIP.getTpPessoaBenfcrioFinl());

        if (!ObjectUtil.isEmpty(retornoCIP.getCNPJCPFBenfcrioFinl())) {
            boletoDDA.setNumCpfCnpjBeneficiarioFinal(retornoCIP.getCNPJCPFBenfcrioFinl().toString(),
                    retornoCIP.getTpPessoaBenfcrioFinl().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        }

        boletoDDA.setNomeBeneficiarioFinal(ObjectUtil.isEmpty(retornoCIP.getNomRzSocBenfcrioFinl()) ? null : retornoCIP.getNomRzSocBenfcrioFinl());
        boletoDDA.setNomeFantasiaBeneficiarioFinal(ObjectUtil.isEmpty(retornoCIP.getNomFantsBenfcrioFinl()) ? null : retornoCIP.getNomFantsBenfcrioFinl());

        boletoDDA.setCodTipoPessoaPagador(retornoCIP.getTpPessoaPagdr());
        boletoDDA.setNumCpfCnpjPagador(retornoCIP.getCNPJCPFPagdr().toString(),
                retornoCIP.getTpPessoaPagdr().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoDDA.setNomePagador(retornoCIP.getNomRzSocPagdr());
        boletoDDA.setNomeFantasiaPagador(ObjectUtil.isEmpty(retornoCIP.getNomFantsPagdr()) ? null : retornoCIP.getNomFantsPagdr());
        boletoDDA.setDescLogradouroPagador(ObjectUtil.isEmpty(retornoCIP.getLogradPagdr()) ? null : retornoCIP.getLogradPagdr());
        boletoDDA.setDescCidadePagador(ObjectUtil.isEmpty(retornoCIP.getCidPagdr()) ? null : retornoCIP.getCidPagdr());
        boletoDDA.setSiglaUfPagador(ObjectUtil.isEmpty(retornoCIP.getUFPagdr()) ? null : retornoCIP.getUFPagdr());
        boletoDDA.setNumCepPagador(ObjectUtil.isEmpty(retornoCIP.getCEPPagdr()) ? null : retornoCIP.getCEPPagdr().toString());

        boletoDDA.setNumCpfCnpjCodTipoPessoaAvalista(retornoCIP.getIdentcSacdrAvalst(),
                ObjectUtil.isNull(retornoCIP.getTpIdentcSacdrAvalst()) ? null : retornoCIP.getTpIdentcSacdrAvalst().toString());

        boletoDDA.setNomeAvalista(ObjectUtil.isEmpty(retornoCIP.getNomRzSocSacdrAvalst()) ? null : retornoCIP.getNomRzSocSacdrAvalst());

        boletoDDA.setIdCarteira(Integer.valueOf(retornoCIP.getCodCartTit()));
        boletoDDA.setCodMoeda(retornoCIP.getCodMoedaCNAB());

        boletoDDA.setNumNossoNumero(retornoCIP.getIdentdNossoNum());
        boletoDDA.setNumCodigoBarra(retornoCIP.getNumCodBarras());
        boletoDDA.setNumCodBarrasCampoLivre(retornoCIP.getNumCodBarras() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(retornoCIP.getNumCodBarras()) : null);
        boletoDDA.setNumLinhaDigitavel(ObjectUtil.isEmpty(retornoCIP.getNumLinhaDigtvl()) ? null : retornoCIP.getNumLinhaDigtvl());

        boletoDDA.setDataVencimento(ObjectUtil.isNull(retornoCIP.getDtVencTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(retornoCIP.getDtVencTit()));
        boletoDDA.setValorBoleto(retornoCIP.getVlrTit());
        boletoDDA.setNumDocumento(ObjectUtil.isEmpty(retornoCIP.getNumDocTit()) ? null : retornoCIP.getNumDocTit());
        boletoDDA.setIdEspecieDocumento(Integer.valueOf(retornoCIP.getCodEspTit()));
        boletoDDA.setDataEmissao(DataCipUtil.xMLGregorianCalendarParaDateTime(retornoCIP.getDtEmsTit()));

        boletoDDA.setNumDiasProtesto(ObjectUtil.isEmpty(retornoCIP.getQtdDiaPrott()) ? null : retornoCIP.getQtdDiaPrott().intValue());
        boletoDDA.setDataLimitePagamento(ObjectUtil.isNull(retornoCIP.getDtLimPgtoTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(retornoCIP.getDtLimPgtoTit()));
        boletoDDA.setCodTipoPagamento(retornoCIP.getTpPgtoTit().intValue());
        boletoDDA.setNumParcela(ObjectUtil.isEmpty(retornoCIP.getNumParcl()) ? null : retornoCIP.getNumParcl().intValue());
        boletoDDA.setQtdTotalParcela(ObjectUtil.isEmpty(retornoCIP.getQtdTotParcl()) ? null : retornoCIP.getQtdTotParcl().intValue());

        boletoDDA.setBolTituloNegociado(retornoCIP.getIndrTitNegcd().equals("S"));
        boletoDDA.setBolBloqueioPagamento(retornoCIP.getIndrBloqPgto().equals("S"));
        boletoDDA.setBolPagamentoParcial(retornoCIP.getIndrPgtoParcl().equals("S"));

        boletoDDA.setQtdPagamentoParcial(ObjectUtil.isEmpty(retornoCIP.getQtdPgtoParcl()) ? null : retornoCIP.getQtdPgtoParcl().intValue());
        boletoDDA.setValorAbatimento(retornoCIP.getVlrAbattTit());

        validarR3Juros(retornoCIP.getGrupoADDA121RR3JurosTit(), boletoDDA);

        validarR3Multa(retornoCIP.getGrupoADDA121RR3MultaTit(), boletoDDA);

        if (!ObjectUtil.isEmpty(retornoCIP.getGrupoADDA121RR3DesctTit())) {
            validarR3DescontoADDA(retornoCIP.getGrupoADDA121RR3DesctTit(), boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        boletoDDA.setCodIndicadorValorMinimo(ObjectUtil.isEmpty(retornoCIP.getTpVlrPercMinTit()) ? null : retornoCIP.getTpVlrPercMinTit());
        boletoDDA.setValorMinimo(ObjectUtil.isEmpty(retornoCIP.getVlrPercMinTit()) ? null : retornoCIP.getVlrPercMinTit());
        boletoDDA.setCodIndicadorValorMaximo(ObjectUtil.isEmpty(retornoCIP.getTpVlrPercMaxTit()) ? null : retornoCIP.getTpVlrPercMaxTit());
        boletoDDA.setValorMaximo(ObjectUtil.isEmpty(retornoCIP.getVlrPercMaxTit()) ? null : retornoCIP.getVlrPercMaxTit());

        boletoDDA.setCodTipoModeloCalculo(retornoCIP.getTpModlCalc());
        boletoDDA.setCodAutorizacaoValorDivergente(retornoCIP.getTpAutcRecbtVlrDivgte());

        validarR3GrupoCalculo(retornoCIP, boletoDDA);

        boletoDDA.setNumRefAtualCadAceite(ObjectUtil.isEmpty(retornoCIP.getNumRefAtlActe()) ? null : retornoCIP.getNumRefAtlActe().longValue());
        boletoDDA.setNumSeqAtualAceite(ObjectUtil.isEmpty(retornoCIP.getNumSeqAtlzActe()) ? null : retornoCIP.getNumSeqAtlzActe().longValue());

        boletoDDA.setBolAceite(ObjectUtil.isEmpty(retornoCIP.getIndrActe()) ? Boolean.FALSE : retornoCIP.getIndrActe().equals("S"));

        validarR3TerceiroAutorizadoADDA(retornoCIP.getGrupoADDA121RR3Terc(), boletoDDA);

        boletoDDA.setQtdPagamentoParcialReg(ObjectUtil.isEmpty(retornoCIP.getQtdPgtoParclRegtd()) ? 0 : retornoCIP.getQtdPgtoParclRegtd().intValue());
        boletoDDA.setValorSaldoPagamento(ObjectUtil.isEmpty(retornoCIP.getVlrSldTotAtlPgtoTit()) ? null : retornoCIP.getVlrSldTotAtlPgtoTit());
        boletoDDA.setCodSituacaoBoletoPagamento(retornoCIP.getSitTitPgto());
        boletoDDA.setCodSituacaoBoleto(ObjectUtil.isNull(retornoCIP.getSitTit()) ? null : Integer.valueOf(retornoCIP.getSitTit()));

        validarR3BaixaOperacionalADDA(retornoCIP.getGrupoADDA121RR3BaixaOperac(), boletoDDA);

        validaR3BaixaEfetivaADDA(retornoCIP.getGrupoADDA121RR3BaixaEft(), boletoDDA);

        return boletoDDA;

    }

    /**
     * @param retornoCIP
     * @return BoletoDDA
     * 
     */
    private static BoletoDDA converterBoletoDDA(GrupoDDA0121R3TitComplexType retornoCIP) {
        BoletoDDA boletoDDA = new BoletoDDA();

        boletoDDA.setNumIdentificadorBoletoCip(retornoCIP.getNumIdentcTit().longValue());
        boletoDDA.setNumRefAtualCadBoleto(ObjectUtil.isEmpty(retornoCIP.getNumRefAtlCadTit()) ? null : retornoCIP.getNumRefAtlCadTit().longValue());
        boletoDDA.setNumSeqAtualCadBoleto(retornoCIP.getNumSeqAtlzCadTit().longValue());

        boletoDDA.setCodIspbPartDestinatario(retornoCIP.getISPBPartDestinatario());
        boletoDDA.setCodPartDestinatario(ObjectUtil.isEmpty(retornoCIP.getCodPartDestinatario()) ? null : retornoCIP.getCodPartDestinatario());

        boletoDDA.setCodTipoPessoaBeneficiario(retornoCIP.getTpPessoaBenfcrioOr());
        boletoDDA.setNumCpfCnpjBeneficiario(retornoCIP.getCNPJCPFBenfcrioOr().toString(),
                retornoCIP.getTpPessoaBenfcrioOr().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoDDA.setNomeBeneficiario(retornoCIP.getNomRzSocBenfcrioOr());
        boletoDDA.setNomeFantasiaBeneficiario(ObjectUtil.isEmpty(retornoCIP.getNomFantsBenfcrioOr()) ? null : retornoCIP.getNomFantsBenfcrioOr());
        boletoDDA.setDescLogradouroBeneficiario(ObjectUtil.isEmpty(retornoCIP.getLogradBenfcrioOr()) ? null : retornoCIP.getLogradBenfcrioOr());
        boletoDDA.setDescCidadeBeneficiario(ObjectUtil.isEmpty(retornoCIP.getCidBenfcrioOr()) ? null : retornoCIP.getCidBenfcrioOr());
        boletoDDA.setSiglaUfBeneficiario(ObjectUtil.isEmpty(retornoCIP.getUFBenfcrioOr()) ? null : retornoCIP.getUFBenfcrioOr());
        boletoDDA.setNumCepBeneficiario(ObjectUtil.isEmpty(retornoCIP.getCEPBenfcrioOr()) ? null : retornoCIP.getCEPBenfcrioOr().toString());

        boletoDDA.setCodTipoPessoaBeneficiarioFinal(ObjectUtil.isEmpty(retornoCIP.getTpPessoaBenfcrioFinl()) ? null : retornoCIP.getTpPessoaBenfcrioFinl());

        if (!ObjectUtil.isEmpty(retornoCIP.getCNPJCPFBenfcrioFinl())) {
            boletoDDA.setNumCpfCnpjBeneficiarioFinal(retornoCIP.getCNPJCPFBenfcrioFinl().toString(),
                    retornoCIP.getTpPessoaBenfcrioFinl().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        }

        boletoDDA.setNomeBeneficiarioFinal(ObjectUtil.isEmpty(retornoCIP.getNomRzSocBenfcrioFinl()) ? null : retornoCIP.getNomRzSocBenfcrioFinl());
        boletoDDA.setNomeFantasiaBeneficiarioFinal(ObjectUtil.isEmpty(retornoCIP.getNomFantsBenfcrioFinl()) ? null : retornoCIP.getNomFantsBenfcrioFinl());

        boletoDDA.setCodTipoPessoaPagador(retornoCIP.getTpPessoaPagdr());
        boletoDDA.setNumCpfCnpjPagador(retornoCIP.getCNPJCPFPagdr().toString(),
                retornoCIP.getTpPessoaPagdr().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

        boletoDDA.setNomePagador(retornoCIP.getNomRzSocPagdr());
        boletoDDA.setNomeFantasiaPagador(ObjectUtil.isEmpty(retornoCIP.getNomFantsPagdr()) ? null : retornoCIP.getNomFantsPagdr());
        boletoDDA.setDescLogradouroPagador(ObjectUtil.isEmpty(retornoCIP.getLogradPagdr()) ? null : retornoCIP.getLogradPagdr());
        boletoDDA.setDescCidadePagador(ObjectUtil.isEmpty(retornoCIP.getCidPagdr()) ? null : retornoCIP.getCidPagdr());
        boletoDDA.setSiglaUfPagador(ObjectUtil.isEmpty(retornoCIP.getUFPagdr()) ? null : retornoCIP.getUFPagdr());
        boletoDDA.setNumCepPagador(ObjectUtil.isEmpty(retornoCIP.getCEPPagdr()) ? null : retornoCIP.getCEPPagdr().toString());

        boletoDDA.setNumCpfCnpjCodTipoPessoaAvalista(retornoCIP.getIdentcSacdrAvalst(),
                ObjectUtil.isNull(retornoCIP.getTpIdentcSacdrAvalst()) ? null : retornoCIP.getTpIdentcSacdrAvalst().toString());

        boletoDDA.setNomeAvalista(ObjectUtil.isEmpty(retornoCIP.getNomRzSocSacdrAvalst()) ? null : retornoCIP.getNomRzSocSacdrAvalst());

        boletoDDA.setIdCarteira(Integer.valueOf(retornoCIP.getCodCartTit()));
        boletoDDA.setCodMoeda(retornoCIP.getCodMoedaCNAB());

        boletoDDA.setNumNossoNumero(retornoCIP.getIdentdNossoNum());
        boletoDDA.setNumCodigoBarra(retornoCIP.getNumCodBarras());
        boletoDDA.setNumCodBarrasCampoLivre(retornoCIP.getNumCodBarras() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(retornoCIP.getNumCodBarras()) : null);
        boletoDDA.setNumLinhaDigitavel(ObjectUtil.isEmpty(retornoCIP.getNumLinhaDigtvl()) ? null : retornoCIP.getNumLinhaDigtvl());

        boletoDDA.setDataVencimento(ObjectUtil.isNull(retornoCIP.getDtVencTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(retornoCIP.getDtVencTit()));
        boletoDDA.setValorBoleto(retornoCIP.getVlrTit());
        boletoDDA.setNumDocumento(ObjectUtil.isEmpty(retornoCIP.getNumDocTit()) ? null : retornoCIP.getNumDocTit());
        boletoDDA.setIdEspecieDocumento(Integer.valueOf(retornoCIP.getCodEspTit()));
        boletoDDA.setDataEmissao(DataCipUtil.xMLGregorianCalendarParaDateTime(retornoCIP.getDtEmsTit()));

        boletoDDA.setNumDiasProtesto(ObjectUtil.isEmpty(retornoCIP.getQtdDiaPrott()) ? null : retornoCIP.getQtdDiaPrott().intValue());
        boletoDDA.setDataLimitePagamento(ObjectUtil.isNull(retornoCIP.getDtLimPgtoTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(retornoCIP.getDtLimPgtoTit()));
        boletoDDA.setCodTipoPagamento(retornoCIP.getTpPgtoTit().intValue());
        boletoDDA.setNumParcela(ObjectUtil.isEmpty(retornoCIP.getNumParcl()) ? null : retornoCIP.getNumParcl().intValue());
        boletoDDA.setQtdTotalParcela(ObjectUtil.isEmpty(retornoCIP.getQtdTotParcl()) ? null : retornoCIP.getQtdTotParcl().intValue());

        boletoDDA.setBolTituloNegociado(retornoCIP.getIndrTitNegcd().equals("S"));
        boletoDDA.setBolBloqueioPagamento(retornoCIP.getIndrBloqPgto().equals("S"));
        boletoDDA.setBolPagamentoParcial(retornoCIP.getIndrPgtoParcl().equals("S"));

        boletoDDA.setQtdPagamentoParcial(ObjectUtil.isEmpty(retornoCIP.getQtdPgtoParcl()) ? null : retornoCIP.getQtdPgtoParcl().intValue());
        boletoDDA.setValorAbatimento(retornoCIP.getVlrAbattTit());

        validarR3Juros(retornoCIP.getGrupoDDA0121R3JurosTit(), boletoDDA);

        validarR3Multa(retornoCIP.getGrupoDDA0121R3MultaTit(), boletoDDA);

        if (!ObjectUtil.isEmpty(retornoCIP.getGrupoDDA0121R3DesctTit())) {
            validarR3Desconto(retornoCIP.getGrupoDDA0121R3DesctTit(), boletoDDA);
        } else {
            desconto1Isento(boletoDDA);
        }

        boletoDDA.setCodIndicadorValorMinimo(ObjectUtil.isEmpty(retornoCIP.getTpVlrPercMinTit()) ? null : retornoCIP.getTpVlrPercMinTit());
        boletoDDA.setValorMinimo(ObjectUtil.isEmpty(retornoCIP.getVlrPercMinTit()) ? null : retornoCIP.getVlrPercMinTit());
        boletoDDA.setCodIndicadorValorMaximo(ObjectUtil.isEmpty(retornoCIP.getTpVlrPercMaxTit()) ? null : retornoCIP.getTpVlrPercMaxTit());
        boletoDDA.setValorMaximo(ObjectUtil.isEmpty(retornoCIP.getVlrPercMaxTit()) ? null : retornoCIP.getVlrPercMaxTit());

        boletoDDA.setCodTipoModeloCalculo(retornoCIP.getTpModlCalc());
        boletoDDA.setCodAutorizacaoValorDivergente(retornoCIP.getTpAutcRecbtVlrDivgte());

        validarR3GrupoCalculo(retornoCIP.getGrupoDDA0121R3Calc(), boletoDDA);

        boletoDDA.setNumRefAtualCadAceite(ObjectUtil.isEmpty(retornoCIP.getNumRefAtlActe()) ? null : retornoCIP.getNumRefAtlActe().longValue());
        boletoDDA.setNumSeqAtualAceite(ObjectUtil.isEmpty(retornoCIP.getNumSeqAtlzActe()) ? null : retornoCIP.getNumSeqAtlzActe().longValue());

        boletoDDA.setBolAceite(ObjectUtil.isEmpty(retornoCIP.getIndrActe()) ? Boolean.FALSE : retornoCIP.getIndrActe().equals("S"));

        validarR3TerceiroAutorizado(retornoCIP.getGrupoDDA0121R3Terc(), boletoDDA);
        boletoDDA.setQtdPagamentoParcialReg(ObjectUtil.isEmpty(retornoCIP.getQtdPgtoParclRegtd()) ? 0 : retornoCIP.getQtdPgtoParclRegtd().intValue());
        boletoDDA.setValorSaldoPagamento(ObjectUtil.isEmpty(retornoCIP.getVlrSldTotAtlPgtoTit()) ? null : retornoCIP.getVlrSldTotAtlPgtoTit());
        boletoDDA.setCodSituacaoBoletoPagamento(retornoCIP.getSitTitPgto());
        boletoDDA.setCodSituacaoBoleto(ObjectUtil.isNull(retornoCIP.getSitTit()) ? null : Integer.valueOf(retornoCIP.getSitTit()));

        validarR3BaixaOperacional(retornoCIP.getGrupoDDA0121R3BaixaOperac(), boletoDDA);

        validaR3BaixaEfetiva(retornoCIP.getGrupoDDA0121R3BaixaEft(), boletoDDA);

        return boletoDDA;

    }

    /**
     * @param grupoTitulo
     * @param boletoDDA void
     * 
     */
    private static void validarR3Juros(GrupoDDA0121R3JurosTitComplexType grupoTitulo, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(grupoTitulo)) {
            boletoDDA.setDataJuros(ObjectUtil.isNull(grupoTitulo.getDtJurosTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(grupoTitulo.getDtJurosTit()));
            TipoJuros tipoJuros = new TipoJuros();
            tipoJuros.setId(Short.valueOf(grupoTitulo.getCodJurosTit()));
            boletoDDA.setTipoJuros(tipoJuros);
            boletoDDA.setValorPercentualJuros(grupoTitulo.getVlrPercJurosTit());
        } else {
            jurosIsento(boletoDDA);
        }
    }

    /**
     * @param grupoJuros
     * @param boletoDDA void
     * 
     */
    private static void validarR3Juros(GrupoADDA121RR3JurosTitComplexType grupoJuros, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(grupoJuros)) {
            boletoDDA.setDataJuros(ObjectUtil.isNull(grupoJuros.getDtJurosTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(grupoJuros.getDtJurosTit()));
            TipoJuros tipoJuros = new TipoJuros();
            tipoJuros.setId(Short.valueOf(grupoJuros.getCodJurosTit()));
            boletoDDA.setTipoJuros(tipoJuros);
            boletoDDA.setValorPercentualJuros(grupoJuros.getVlrPercJurosTit());
        } else {
            jurosIsento(boletoDDA);
        }
    }

    /**
     * @param grupoMulta
     * @param boletoDDA void
     * 
     */
    private static void validarR3Multa(GrupoDDA0121R3MultaTitComplexType grupoMulta, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(grupoMulta)) {
            boletoDDA.setDataMulta(ObjectUtil.isNull(grupoMulta.getDtMultaTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(grupoMulta.getDtMultaTit()));
            TipoMulta tipoMulta = new TipoMulta();
            tipoMulta.setId(Short.valueOf(grupoMulta.getCodMultaTit()));
            boletoDDA.setTipoMulta(tipoMulta);
            boletoDDA.setValorPercentualMulta(grupoMulta.getVlrPercMultaTit());
        } else {
            multaIsento(boletoDDA);
        }
    }

    /**
     * @param grupoMulta
     * @param boletoDDA void
     * 
     */
    private static void validarR3Multa(GrupoADDA121RR3MultaTitComplexType grupoMulta, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(grupoMulta)) {
            boletoDDA.setDataMulta(ObjectUtil.isNull(grupoMulta.getDtMultaTit()) ? null : DataCipUtil.xMLGregorianCalendarParaDateTime(grupoMulta.getDtMultaTit()));
            TipoMulta tipoMulta = new TipoMulta();
            tipoMulta.setId(Short.valueOf(grupoMulta.getCodMultaTit()));
            boletoDDA.setTipoMulta(tipoMulta);
            boletoDDA.setValorPercentualMulta(grupoMulta.getVlrPercMultaTit());
        } else {
            multaIsento(boletoDDA);
        }
    }

    /**
     * @param listaGrupoDesconto
     * @param boletoDDA void
     * 
     */
    private static void validarR3Desconto(List<GrupoDDA0121R3DesctTitComplexType> listaGrupoDesconto, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(listaGrupoDesconto)) {
            int cont = listaGrupoDesconto.size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(0).getDtDesctTit()),
                        listaGrupoDesconto.get(0).getCodDesctTit(), listaGrupoDesconto.get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(0).getDtDesctTit()),
                        listaGrupoDesconto.get(0).getCodDesctTit(), listaGrupoDesconto.get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(1).getDtDesctTit()),
                        listaGrupoDesconto.get(1).getCodDesctTit(), listaGrupoDesconto.get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(0).getDtDesctTit()),
                        listaGrupoDesconto.get(0).getCodDesctTit(), listaGrupoDesconto.get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(1).getDtDesctTit()),
                        listaGrupoDesconto.get(1).getCodDesctTit(), listaGrupoDesconto.get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(2).getDtDesctTit()),
                        listaGrupoDesconto.get(2).getCodDesctTit(), listaGrupoDesconto.get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * @param listaGrupoDesconto
     * @param boletoDDA void
     * 
     */
    private static void validarR3DescontoADDA(List<GrupoADDA121RR3DesctTitComplexType> listaGrupoDesconto, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(listaGrupoDesconto)) {
            int cont = listaGrupoDesconto.size();

            if (cont == 1) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(0).getDtDesctTit()),
                        listaGrupoDesconto.get(0).getCodDesctTit(), listaGrupoDesconto.get(0).getVlrPercDesctTit());
            } else if (cont == 2) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(0).getDtDesctTit()),
                        listaGrupoDesconto.get(0).getCodDesctTit(), listaGrupoDesconto.get(0).getVlrPercDesctTit());

                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(1).getDtDesctTit()),
                        listaGrupoDesconto.get(1).getCodDesctTit(), listaGrupoDesconto.get(1).getVlrPercDesctTit());
            } else if (cont == 3) {
                setBoletoDDADesconto1(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(0).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(0).getDtDesctTit()),
                        listaGrupoDesconto.get(0).getCodDesctTit(), listaGrupoDesconto.get(0).getVlrPercDesctTit());
                setBoletoDDADesconto2(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(1).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(1).getDtDesctTit()),
                        listaGrupoDesconto.get(1).getCodDesctTit(), listaGrupoDesconto.get(1).getVlrPercDesctTit());

                setBoletoDDADesconto3(boletoDDA,
                        ObjectUtil.isNull(listaGrupoDesconto.get(2).getDtDesctTit()) ? null
                                : DataCipUtil.xMLGregorianCalendarParaDateTime(listaGrupoDesconto.get(2).getDtDesctTit()),
                        listaGrupoDesconto.get(2).getCodDesctTit(), listaGrupoDesconto.get(2).getVlrPercDesctTit());
            }
        }
    }

    /**
     * @param listaGrupoCalculo
     * @param boletoDDA void
     * 
     */
    private static void validarR3GrupoCalculo(List<GrupoDDA0121R3CalcComplexType> listaGrupoCalculo, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(listaGrupoCalculo)) {
            boletoDDA.setListaBoletoDDAGrupoCalculo(new ArrayList<BoletoDDAGrupoCalculo>());
            BoletoDDAGrupoCalculo boletoDDAGrupoCalculo;
            for (GrupoDDA0121R3CalcComplexType grupo : listaGrupoCalculo) {
                boletoDDAGrupoCalculo = new BoletoDDAGrupoCalculo();
                boletoDDAGrupoCalculo.setValorCalculadoJuros(grupo.getVlrCalcdJuros());
                boletoDDAGrupoCalculo.setValorCalculadoMulta(grupo.getVlrCalcdMulta());
                boletoDDAGrupoCalculo.setValorCalculadoDesconto(grupo.getVlrCalcdDesct());
                boletoDDAGrupoCalculo.setValorTotalCobrado(grupo.getVlrTotCobrar());
                boletoDDAGrupoCalculo.setDataValidadeCalculo(DataCipUtil.xMLGregorianCalendarParaDateTime(grupo.getDtValiddCalc()));
                boletoDDAGrupoCalculo.setBoletoDDA(boletoDDA);

                boletoDDA.getListaBoletoDDAGrupoCalculo().add(boletoDDAGrupoCalculo);
            }
        }
    }

    /**
     * @param retornoCIP
     * @param boletoDDA void
     * 
     */
    private static void validarR3GrupoCalculo(GrupoADDA121RR3TitComplexType retornoCIP, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(retornoCIP.getGrupoADDA121RR3Calc())) {
            boletoDDA.setListaBoletoDDAGrupoCalculo(new ArrayList<BoletoDDAGrupoCalculo>());
            BoletoDDAGrupoCalculo boletoDDAGrupoCalculo;
            for (GrupoADDA121RR3CalcComplexType grupoDDA0121R3Calc : retornoCIP.getGrupoADDA121RR3Calc()) {
                boletoDDAGrupoCalculo = new BoletoDDAGrupoCalculo();
                boletoDDAGrupoCalculo.setValorCalculadoJuros(grupoDDA0121R3Calc.getVlrCalcdJuros());
                boletoDDAGrupoCalculo.setValorCalculadoMulta(grupoDDA0121R3Calc.getVlrCalcdMulta());
                boletoDDAGrupoCalculo.setValorCalculadoDesconto(grupoDDA0121R3Calc.getVlrCalcdDesct());
                boletoDDAGrupoCalculo.setValorTotalCobrado(grupoDDA0121R3Calc.getVlrTotCobrar());
                boletoDDAGrupoCalculo.setDataValidadeCalculo(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDDA0121R3Calc.getDtValiddCalc()));
                boletoDDAGrupoCalculo.setBoletoDDA(boletoDDA);

                boletoDDA.getListaBoletoDDAGrupoCalculo().add(boletoDDAGrupoCalculo);
            }
        }
    }

    /**
     * @param listaGrupoTercAuto
     * @param boletoDDA void
     * 
     */
    private static void validarR3TerceiroAutorizado(List<GrupoDDA0121R3TercComplexType> listaGrupoTercAuto, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(listaGrupoTercAuto)) {
            boletoDDA.setListaBoletoDDATerceiroAutorizado(new ArrayList<BoletoDDATerceiroAut>());
            BoletoDDATerceiroAut boletoDDATerceiroAut;
            for (GrupoDDA0121R3TercComplexType grupo : listaGrupoTercAuto) {
                boletoDDATerceiroAut = new BoletoDDATerceiroAut();
                boletoDDATerceiroAut.setNumIdentificadorTerceiro(ObjectUtil.isEmpty(grupo.getNumIdentcTerc()) ? null : grupo.getNumIdentcTerc().longValue());
                boletoDDATerceiroAut.setNumRefAtualTerceiro(ObjectUtil.isEmpty(grupo.getNumRefAtlCadTerc()) ? null : grupo.getNumRefAtlCadTerc().longValue());
                boletoDDATerceiroAut.setCodTipoPessoaAutorizador(ObjectUtil.isEmpty(grupo.getTpPessoaPagdrAutzdr()) ? null : grupo.getTpPessoaPagdrAutzdr());
                boletoDDATerceiroAut.setNumCpfCnpjAutorizador(ObjectUtil.isEmpty(grupo.getCNPJCPFPagdrAutzdr()) ? null : grupo.getCNPJCPFPagdrAutzdr().toString(),
                        grupo.getTpPessoaPagdrAutzdr().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);
                boletoDDATerceiroAut.setCodTipoPessoaTerceiro(grupo.getTpPessoaTerc());
                boletoDDATerceiroAut.setNumCpfCnpjTerceiro(grupo.getCNPJCPFTerc().toString(),
                        grupo.getTpPessoaTerc().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);
                boletoDDATerceiroAut.setBolAtivo(grupo.getSitTerc());
                boletoDDATerceiroAut.setBoletoDDA(boletoDDA);

                boletoDDA.getListaBoletoDDATerceiroAutorizado().add(boletoDDATerceiroAut);
            }
        }
    }

    /**
     * @param retornoCIP
     * @param boletoDDA void
     * 
     */
    private static void validarR3TerceiroAutorizadoADDA(List<GrupoADDA121RR3TercComplexType> listaGrupoTercAutorizado, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(listaGrupoTercAutorizado)) {
            boletoDDA.setListaBoletoDDATerceiroAutorizado(new ArrayList<BoletoDDATerceiroAut>());
            for (GrupoADDA121RR3TercComplexType grupo : listaGrupoTercAutorizado) {
                BoletoDDATerceiroAut boletoDDATerceiroAut = new BoletoDDATerceiroAut();
                boletoDDATerceiroAut.setNumIdentificadorTerceiro(ObjectUtil.isEmpty(grupo.getNumIdentcTerc()) ? null : grupo.getNumIdentcTerc().longValue());
                boletoDDATerceiroAut.setNumRefAtualTerceiro(ObjectUtil.isEmpty(grupo.getNumRefAtlCadTerc()) ? null : grupo.getNumRefAtlCadTerc().longValue());
                boletoDDATerceiroAut.setCodTipoPessoaAutorizador(ObjectUtil.isEmpty(grupo.getTpPessoaPagdrAutzdr()) ? null : grupo.getTpPessoaPagdrAutzdr());
                boletoDDATerceiroAut.setNumCpfCnpjAutorizador(ObjectUtil.isEmpty(grupo.getCNPJCPFPagdrAutzdr()) ? null : grupo.getCNPJCPFPagdrAutzdr().toString(),
                        grupo.getTpPessoaPagdrAutzdr().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);
                boletoDDATerceiroAut.setCodTipoPessoaTerceiro(grupo.getTpPessoaTerc());
                boletoDDATerceiroAut.setNumCpfCnpjTerceiro(grupo.getCNPJCPFTerc().toString(),
                        grupo.getTpPessoaTerc().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);
                boletoDDATerceiroAut.setBolAtivo(grupo.getSitTerc());

                boletoDDATerceiroAut.setBoletoDDA(boletoDDA);

                boletoDDA.getListaBoletoDDATerceiroAutorizado().add(boletoDDATerceiroAut);
            }
        }
    }

    /**
     * @param listaGrupoBaixaOperac
     * @param boletoDDA void
     * 
     */
    private static void validarR3BaixaOperacionalADDA(List<GrupoADDA121RR3BaixaOperacComplexType> listaGrupoBaixaOperac, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(listaGrupoBaixaOperac)) {
            boletoDDA.setListaBoletoDDABaixaOper(new ArrayList<BoletoDDABaixaOper>());
            for (GrupoADDA121RR3BaixaOperacComplexType grupoBaixaOperac : listaGrupoBaixaOperac) {
                BoletoDDABaixaOper boletoDDABaixaOper = new BoletoDDABaixaOper(boletoDDA);
                boletoDDABaixaOper.setNumIdentificadorBaixaOper(
                        ObjectUtil.isEmpty(grupoBaixaOperac.getNumIdentcBaixaOperac()) ? null : grupoBaixaOperac.getNumIdentcBaixaOperac().longValue());
                boletoDDABaixaOper
                        .setNumRefAtualBaixaOper(ObjectUtil.isEmpty(grupoBaixaOperac.getNumRefAtlBaixaOperac()) ? null : grupoBaixaOperac.getNumRefAtlBaixaOperac().longValue());
                boletoDDABaixaOper.setNumSeqAtualBaixaOper(grupoBaixaOperac.getNumSeqAtlzBaixaOperac().longValue());
                // Referencia Cadastro Titulo Baixa Operacional
                // Referencia Atual cadstro titulo baixa operacional
                boletoDDABaixaOper.setCodIspbPartRecebedorBaixaOperacional(grupoBaixaOperac.getISPBPartRecbdrBaixaOperac());
                boletoDDABaixaOper.setCodPartRecebedorBaixaOperacional(
                        ObjectUtil.isEmpty(grupoBaixaOperac.getCodPartRecbdrBaixaOperac()) ? null : grupoBaixaOperac.getCodPartRecbdrBaixaOperac());
                boletoDDABaixaOper.setCodTipoBaixaOperacional(Integer.valueOf(grupoBaixaOperac.getTpBaixaOperac()));
                boletoDDABaixaOper.setCodSituacaoBaixaOperacional(grupoBaixaOperac.getSitBaixaOperac());

                if (!ObjectUtil.isEmpty(grupoBaixaOperac.getGrupoADDA121RR3DettBaixaOperac())) {
                    validarR3DetBaixaOper(grupoBaixaOperac.getGrupoADDA121RR3DettBaixaOperac().get(0), boletoDDABaixaOper);
                }

                boletoDDA.getListaBoletoDDABaixaOper().add(boletoDDABaixaOper);
            }
        }
    }

    /**
     * @param grupoDet
     * @param boletoDDABaixaOper void
     * 
     */
    private static void validarR3DetBaixaOper(GrupoADDA121RR3DettBaixaOperacComplexType grupoDet, BoletoDDABaixaOper boletoDDABaixaOper) {
        if (!ObjectUtil.isEmpty(grupoDet.getTpPessoaPort()) && !ObjectUtil.isEmpty(grupoDet.getCNPJCPFPort())) {
            boletoDDABaixaOper.setCodTipoPessoaPortador(grupoDet.getTpPessoaPort());
            boletoDDABaixaOper.setNumCnpjCpfPortador(grupoDet.getCNPJCPFPort().toString(),
                    grupoDet.getTpPessoaPort().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);
        }

        boletoDDABaixaOper.setDataHoraProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDet.getDtHrProcBaixaOperac()));
        boletoDDABaixaOper.setDataProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDet.getDtProcBaixaOperac()));
        boletoDDABaixaOper.setValorBaixaOper(grupoDet.getVlrBaixaOperacTit());
        boletoDDABaixaOper.setNumCodBarrasBaixaOper(grupoDet.getNumCodBarrasBaixaOperac());
        boletoDDABaixaOper.setNumCodBarrasCampoLivre(
                grupoDet.getNumCodBarrasBaixaOperac() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(grupoDet.getNumCodBarrasBaixaOperac()) : null);
        boletoDDABaixaOper.setCodCanalPagamento(ObjectUtil.isEmpty(grupoDet.getCanPgtoBaixaOperac()) ? null : grupoDet.getCanPgtoBaixaOperac().shortValue());
        boletoDDABaixaOper.setCodMeioPagamento(ObjectUtil.isEmpty(grupoDet.getMeioPgtoBaixaOperac()) ? null : grupoDet.getMeioPgtoBaixaOperac().shortValue());
        boletoDDABaixaOper.setBolOperacaoContingencia(ObjectUtil.isEmpty(grupoDet.getMeioPgtoBaixaOperac()) ? null : grupoDet.getIndrOpContg().equals("S"));
    }

    /**
     * @param listaGrupoBaixaOperac
     * @param boletoDDA void
     * 
     */
    private static void validarR3BaixaOperacional(List<GrupoDDA0121R3BaixaOperacComplexType> listaGrupoBaixaOperac, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(listaGrupoBaixaOperac)) {
            boletoDDA.setListaBoletoDDABaixaOper(new ArrayList<BoletoDDABaixaOper>());
            for (GrupoDDA0121R3BaixaOperacComplexType grupoBaixaOperac : listaGrupoBaixaOperac) {
                BoletoDDABaixaOper boletoDDABaixaOper = new BoletoDDABaixaOper(boletoDDA);
                boletoDDABaixaOper.setNumIdentificadorBaixaOper(
                        ObjectUtil.isEmpty(grupoBaixaOperac.getNumIdentcBaixaOperac()) ? null : grupoBaixaOperac.getNumIdentcBaixaOperac().longValue());
                boletoDDABaixaOper
                        .setNumRefAtualBaixaOper(ObjectUtil.isEmpty(grupoBaixaOperac.getNumRefAtlBaixaOperac()) ? null : grupoBaixaOperac.getNumRefAtlBaixaOperac().longValue());
                boletoDDABaixaOper.setNumSeqAtualBaixaOper(grupoBaixaOperac.getNumSeqAtlzBaixaOperac().longValue());
                // Referencia Cadastro Titulo Baixa Operacional
                // Referencia Atual cadstro titulo baixa operacional
                boletoDDABaixaOper.setCodIspbPartRecebedorBaixaOperacional(grupoBaixaOperac.getISPBPartRecbdrBaixaOperac());
                boletoDDABaixaOper.setCodPartRecebedorBaixaOperacional(
                        ObjectUtil.isEmpty(grupoBaixaOperac.getCodPartRecbdrBaixaOperac()) ? null : grupoBaixaOperac.getCodPartRecbdrBaixaOperac());
                boletoDDABaixaOper.setCodTipoBaixaOperacional(Integer.valueOf(grupoBaixaOperac.getTpBaixaOperac()));
                boletoDDABaixaOper.setCodSituacaoBaixaOperacional(grupoBaixaOperac.getSitBaixaOperac());

                validarR3DetBaixaOper(grupoBaixaOperac.getGrupoDDA0121R3DettBaixaOperac(), boletoDDABaixaOper);

                boletoDDA.getListaBoletoDDABaixaOper().add(boletoDDABaixaOper);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param grupoDetBaixaOperac
     * @param boletoDDABaixaOper void
     * 
     */
    private static void validarR3DetBaixaOper(GrupoDDA0121R3DettBaixaOperacComplexType grupoDetBaixaOperac, BoletoDDABaixaOper boletoDDABaixaOper) {
        if (!ObjectUtil.isNull(grupoDetBaixaOperac)) {
            boletoDDABaixaOper.setCodTipoPessoaPortador(ObjectUtil.isEmpty(grupoDetBaixaOperac.getTpPessoaPort()) ? null : grupoDetBaixaOperac.getTpPessoaPort());
            boletoDDABaixaOper.setNumCnpjCpfPortador(ObjectUtil.isEmpty(grupoDetBaixaOperac.getCNPJCPFPort()) ? null : grupoDetBaixaOperac.getCNPJCPFPort().toString(),
                    grupoDetBaixaOperac.getTpPessoaPort().equals(TipoPessoaEnum.PF.getCodDominioCip()) ? TipoPessoaEnum.PF : TipoPessoaEnum.PJ);

            boletoDDABaixaOper.setDataHoraProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOperac.getDtHrProcBaixaOperac()));
            boletoDDABaixaOper.setDataProcessamentoBaixaOper(DataCipUtil.xMLGregorianCalendarParaDateTime(grupoDetBaixaOperac.getDtProcBaixaOperac()));
            boletoDDABaixaOper.setValorBaixaOper(grupoDetBaixaOperac.getVlrBaixaOperacTit());
            boletoDDABaixaOper.setNumCodBarrasBaixaOper(grupoDetBaixaOperac.getNumCodBarrasBaixaOperac());
            boletoDDABaixaOper.setNumCodBarrasCampoLivre(
                    grupoDetBaixaOperac.getNumCodBarrasBaixaOperac() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(grupoDetBaixaOperac.getNumCodBarrasBaixaOperac())
                            : null);
            boletoDDABaixaOper
                    .setCodCanalPagamento(ObjectUtil.isEmpty(grupoDetBaixaOperac.getCanPgtoBaixaOperac()) ? null : grupoDetBaixaOperac.getCanPgtoBaixaOperac().shortValue());
            boletoDDABaixaOper
                    .setCodMeioPagamento(ObjectUtil.isEmpty(grupoDetBaixaOperac.getMeioPgtoBaixaOperac()) ? null : grupoDetBaixaOperac.getMeioPgtoBaixaOperac().shortValue());
            boletoDDABaixaOper
                    .setBolOperacaoContingencia(ObjectUtil.isEmpty(grupoDetBaixaOperac.getMeioPgtoBaixaOperac()) ? null : grupoDetBaixaOperac.getIndrOpContg().equals("S"));
        }
    }

    /**
     * @param listaGrupoBaixaEfet
     * @param boletoDDA void
     * 
     */
    private static void validaR3BaixaEfetiva(List<GrupoDDA0121R3BaixaEftComplexType> listaGrupoBaixaEfet, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isEmpty(listaGrupoBaixaEfet)) {
            boletoDDA.setListaBoletoDDABaixaEfet(new ArrayList<BoletoDDABaixaEfet>());
            BoletoDDABaixaEfet boletoDDABaixaEfet;
            for (GrupoDDA0121R3BaixaEftComplexType grupo : listaGrupoBaixaEfet) {
                boletoDDABaixaEfet = new BoletoDDABaixaEfet();
                boletoDDABaixaEfet.setNumIdentificadorBaixaEfet(ObjectUtil.isEmpty(grupo.getNumIdentcBaixaEft()) ? null : grupo.getNumIdentcBaixaEft().longValue());
                boletoDDABaixaEfet.setNumRefAtualBaixaEfet(ObjectUtil.isEmpty(grupo.getNumRefAtlBaixaEft()) ? null : grupo.getNumRefAtlBaixaEft().longValue());
                boletoDDABaixaEfet.setNumSeqAtualBaixaEfet(grupo.getNumSeqAtlzBaixaEft().longValue());

                boletoDDABaixaEfet.setNumIdentificadorBaixaOperacional(
                        ObjectUtil.isNull(grupo.getNumIdentcBaixaOperacBaixaEft()) ? null : grupo.getNumIdentcBaixaOperacBaixaEft().longValue());
                boletoDDABaixaEfet.setCodTipoBaixaEfetiva(Integer.valueOf(grupo.getTpBaixaEft()));
                boletoDDABaixaEfet.setCodIspbPartRecebedorBaixaEfetiva(ObjectUtil.isNull(grupo.getISPBPartRecbdrBaixaEft()) ? null : grupo.getISPBPartRecbdrBaixaEft());
                boletoDDABaixaEfet.setCodPartRecebedorBaixaEfetiva(ObjectUtil.isNull(grupo.getCodPartRecbdrBaixaEft()) ? null : grupo.getCodPartRecbdrBaixaEft());

                boletoDDABaixaEfet.setDataHoraProcessamentoBaixaEfet(DataCipUtil.xMLGregorianCalendarParaDateTime(grupo.getDtHrProcBaixaEft()));
                boletoDDABaixaEfet.setDataProcessamentoBaixaEfet(DataCipUtil.xMLGregorianCalendarParaDateTime(grupo.getDtProcBaixaEft()));
                boletoDDABaixaEfet.setValorBaixaEfet(ObjectUtil.isEmpty(grupo.getVlrBaixaEftTit()) ? null : grupo.getVlrBaixaEftTit());
                boletoDDABaixaEfet.setNumCodBarrasBaixaEfet(ObjectUtil.isEmpty(grupo.getNumCodBarrasBaixaEft()) ? null : grupo.getNumCodBarrasBaixaEft());
                boletoDDABaixaEfet.setNumCodBarrasCampoLivre(
                        ObjectUtil.isEmpty(grupo.getNumCodBarrasBaixaEft()) ? null : LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(grupo.getNumCodBarrasBaixaEft()));
                boletoDDABaixaEfet.setCodCanalPagamento(ObjectUtil.isEmpty(grupo.getCanPgtoBaixaEft()) ? null : grupo.getCanPgtoBaixaEft().intValue());
                boletoDDABaixaEfet.setCodMeioPagamento(ObjectUtil.isEmpty(grupo.getMeioPgtoBaixaEft()) ? null : grupo.getMeioPgtoBaixaEft().intValue());

                boletoDDABaixaEfet.setBoletoDDA(boletoDDA);

                boletoDDA.getListaBoletoDDABaixaEfet().add(boletoDDABaixaEfet);
            }
        }
    }

    /**
     * 
     * @param listaGrupoBaixaEfet
     * @param boletoDDA void
     * 
     */
    private static void validaR3BaixaEfetivaADDA(List<GrupoADDA121RR3BaixaEftComplexType> listaGrupoBaixaEfet, BoletoDDA boletoDDA) {
        if (!ObjectUtil.isNull(listaGrupoBaixaEfet)) {
            boletoDDA.setListaBoletoDDABaixaEfet(new ArrayList<BoletoDDABaixaEfet>());
            BoletoDDABaixaEfet boletoDDABaixaEfet;
            for (GrupoADDA121RR3BaixaEftComplexType grupo : listaGrupoBaixaEfet) {
                boletoDDABaixaEfet = new BoletoDDABaixaEfet();
                boletoDDABaixaEfet.setNumIdentificadorBaixaEfet(ObjectUtil.isEmpty(grupo.getNumIdentcBaixaEft()) ? null : grupo.getNumIdentcBaixaEft().longValue());
                boletoDDABaixaEfet.setNumRefAtualBaixaEfet(ObjectUtil.isEmpty(grupo.getNumRefAtlBaixaEft()) ? null : grupo.getNumRefAtlBaixaEft().longValue());
                boletoDDABaixaEfet.setNumSeqAtualBaixaEfet(grupo.getNumSeqAtlzBaixaEft().longValue());

                boletoDDABaixaEfet.setNumIdentificadorBaixaOperacional(
                        ObjectUtil.isNull(grupo.getNumIdentcBaixaOperacBaixaEft()) ? null : grupo.getNumIdentcBaixaOperacBaixaEft().longValue());
                boletoDDABaixaEfet.setCodTipoBaixaEfetiva(Integer.valueOf(grupo.getTpBaixaEft()));
                boletoDDABaixaEfet.setCodIspbPartRecebedorBaixaEfetiva(ObjectUtil.isNull(grupo.getISPBPartRecbdrBaixaEft()) ? null : grupo.getISPBPartRecbdrBaixaEft());
                boletoDDABaixaEfet.setCodPartRecebedorBaixaEfetiva(ObjectUtil.isNull(grupo.getCodPartRecbdrBaixaEft()) ? null : grupo.getCodPartRecbdrBaixaEft());

                boletoDDABaixaEfet.setDataHoraProcessamentoBaixaEfet(DataCipUtil.xMLGregorianCalendarParaDateTime(grupo.getDtHrProcBaixaEft()));
                boletoDDABaixaEfet.setDataProcessamentoBaixaEfet(DataCipUtil.xMLGregorianCalendarParaDateTime(grupo.getDtProcBaixaEft()));
                boletoDDABaixaEfet.setValorBaixaEfet(ObjectUtil.isEmpty(grupo.getVlrBaixaEftTit()) ? null : grupo.getVlrBaixaEftTit());
                boletoDDABaixaEfet.setNumCodBarrasBaixaEfet(ObjectUtil.isEmpty(grupo.getNumCodBarrasBaixaEft()) ? null : grupo.getNumCodBarrasBaixaEft());
                boletoDDABaixaEfet.setNumCodBarrasCampoLivre(
                        ObjectUtil.isEmpty(grupo.getNumCodBarrasBaixaEft()) ? null : LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(grupo.getNumCodBarrasBaixaEft()));
                boletoDDABaixaEfet.setCodCanalPagamento(ObjectUtil.isEmpty(grupo.getCanPgtoBaixaEft()) ? null : grupo.getCanPgtoBaixaEft().intValue());
                boletoDDABaixaEfet.setCodMeioPagamento(ObjectUtil.isEmpty(grupo.getMeioPgtoBaixaEft()) ? null : grupo.getMeioPgtoBaixaEft().intValue());

                boletoDDABaixaEfet.setBoletoDDA(boletoDDA);

                boletoDDA.getListaBoletoDDABaixaEfet().add(boletoDDABaixaEfet);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param boletoDDA void
     */
    private static void setBoletoDDADesconto1(BoletoDDA boletoDDA, DateTimeDB dataDesconto, String codTipoDesconto, BigDecimal valorPercentualDesconto) {
        boletoDDA.setDataDesconto1(dataDesconto);
        TipoDesconto tipoDesconto1 = new TipoDesconto();
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
        TipoDesconto tipoDesconto2 = new TipoDesconto();
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
        TipoDesconto tipoDesconto3 = new TipoDesconto();
        tipoDesconto3.setCodTipoDesconto(codTipoDesconto);
        boletoDDA.setTipoDesconto3(tipoDesconto3);
        boletoDDA.setValorPercentualDesconto3(valorPercentualDesconto);
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

}
