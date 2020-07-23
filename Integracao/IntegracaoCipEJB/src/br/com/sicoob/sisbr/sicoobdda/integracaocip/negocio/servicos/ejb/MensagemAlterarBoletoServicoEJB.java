package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoNotaFiscal;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoTextoInfo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemAlterarBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102BenfcrioFinlComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102BenfcrioOrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102DocTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102InstcPgtoTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102InstcVlrRecbtComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.GrupoDDA0102SacdrAvalstComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAlterarBoletoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemAlterarBoletoServicoLocal.class })
public class MensagemAlterarBoletoServicoEJB extends IntegracaoCipServicoEJB implements MensagemAlterarBoletoServicoLocal {
    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0102 - Participante informa inclusão de Boleto");
        MensagemDDABoleto mensagem = getDao().obterMensagemDDABoletoAtualizaReferencias(idMensagem);

        getLogger().debug("*******INICIO obterXmlEnvio*******");
        DDA0102ComplexType dda0102 = getDDA0102ComplexType(mensagem);
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0102, mensagem.getId());
        getLogger().debug("xmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0102 - Participante informa inclusão de Boleto");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(java.lang.Long,
     *      java.lang.Object)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0102R1 ");
        MensagemDDABoleto mensagemDDABoleto = null;
        if (!ObjectUtil.isEmpty(retornoDDA.getIdMensagemOrigem())) {
            mensagemDDABoleto = getDao().obterMensagemDDABoleto(retornoDDA.getIdMensagemOrigem());
        }

        BoletoDDA boletoDDABanco = getDao().obterBoletoDDALock(retornoDDA.getNumIdent());

        if (!ObjectUtil.isNull(boletoDDABanco) && !ObjectUtil.isEmpty(boletoDDABanco.getId())) {
            getLogger().info("Inicio da conversão do Objeto do Boleto");
            BoletoDDA boletoDDA = ConversorBoletoDDA.merge(retornoDDA, mensagemDDABoleto, boletoDDABanco);
            getLogger().info("Objeto Convertido com Sucesso");

            if (boletoDDA.getBolProcessarMensagemRecebida()) {
                getLogger().info("Numero sequencial MAIOR que o numero sequencial da base");
                removendoObjetosBoleto(boletoDDABanco);
                em.merge(boletoDDA);
            } else if (boletoDDA.getBolProcessarMensagem106()) {
                incluirMensagemDDA0106(boletoDDABanco);
            }
        } else {
            throw new ComumException("Alteracao de BoletoDDA com erro - numero de identificacao do boleto " + retornoDDA.getNumIdent() + " não encontrado");
        }
        getLogger().debug("########### Fim processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0102R1 ");
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0101ComplexType
     * 
     */
    private DDA0102ComplexType getDDA0102ComplexType(MensagemDDABoleto mensagem) throws ComumException {
        DDA0102ComplexType dda = new DDA0102ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0102);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);
        dda.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);

        dda.setNumIdentcTit(ObjectUtil.isNull(mensagem.getNumIdentificadorBoletoCip()) ? BigInteger.ZERO : BigInteger.valueOf(mensagem.getNumIdentificadorBoletoCip()));
        dda.setNumRefAtlCadTit(ObjectUtil.isNull(mensagem.getNumRefAtualCadBoleto()) ? null : BigInteger.valueOf(mensagem.getNumRefAtualCadBoleto()));

        // Grupo Beneficiario Original
        setGrupoBeneficiarioOriginal(mensagem, dda);

        // Grupo Beneficiario Final
        setGrupoBeneficiarioFinal(mensagem, dda);

        // Grupo Pagador
        setGrupoPagador(mensagem, dda);

        // Grupo Sacador Avalista
        setGrupoSacadorAvalista(mensagem, dda);

        // Grupo Documento Titulo
        setGrupoDocumentoBoleto(mensagem, dda);

        // Grupo Pagamento Titulo
        setGrupoPagamentoBoleto(mensagem, dda);

        // Grupo Valor Recebimento
        setGrupoValorRecebimento(mensagem, dda);

        // Grupo Valor Juros
        setMensagemDDABoletoJuros(mensagem, dda);

        // Grupo Multa
        setMensagemDDABoletoMulta(mensagem, dda);

        // Grupo Boleto Desconto
        setMensagemDDABoletoDesconto(mensagem, dda);

        // Grupo Boleto Grupo calculo
        setMensagemDDABoletoGrupoCalculo(mensagem, dda);

        // Grupo Boleto Nota Fiscal
        setMensagemDDABoletoNotaFiscal(mensagem, dda);

        // Grupo Boleto Texto Info
        setMensagemDDABoletoTextoInfo(mensagem, dda);

        dda.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));
        return dda;
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void setGrupoBeneficiarioOriginal(MensagemDDABoleto mensagem, DDA0102ComplexType dda) {
        if (!ObjectUtil.isEmpty(mensagem.getNomeFantasiaBeneficiario()) || !ObjectUtil.isEmpty(mensagem.getDescLogradouroBeneficiario())
                || !ObjectUtil.isEmpty(mensagem.getDescCidadeBeneficiario()) || !ObjectUtil.isEmpty(mensagem.getSiglaUfBeneficiario())
                || !ObjectUtil.isEmpty(mensagem.getNumCepBeneficiario())) {
            dda.setGrupoDDA0102BenfcrioOr(new GrupoDDA0102BenfcrioOrComplexType());
            dda.getGrupoDDA0102BenfcrioOr().setNomFantsBenfcrioOr(mensagem.getNomeFantasiaBeneficiario());
            dda.getGrupoDDA0102BenfcrioOr().setLogradBenfcrioOr(mensagem.getDescLogradouroBeneficiario());
            dda.getGrupoDDA0102BenfcrioOr().setCidBenfcrioOr(mensagem.getDescCidadeBeneficiario());
            dda.getGrupoDDA0102BenfcrioOr().setUFBenfcrioOr(mensagem.getSiglaUfBeneficiario());
            dda.getGrupoDDA0102BenfcrioOr().setCEPBenfcrioOr(ObjectUtil.isEmpty(mensagem.getNumCepBeneficiario()) ? null : new BigInteger(mensagem.getNumCepBeneficiario()));
            dda.setIndrManutBenfcrioOr(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutBenfcrioOr(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void setGrupoBeneficiarioFinal(MensagemDDABoleto mensagem, DDA0102ComplexType dda) {
        if (!ObjectUtil.isEmpty(mensagem.getCodTipoPessoaBeneficiarioFinal()) && !ObjectUtil.isEmpty(mensagem.getNumCpfCnpjBeneficiarioFinal())) {

            GrupoDDA0102BenfcrioFinlComplexType grupoBeneficiarioFinal = new GrupoDDA0102BenfcrioFinlComplexType();
            dda.setGrupoDDA0102BenfcrioFinl(grupoBeneficiarioFinal);
            grupoBeneficiarioFinal.setTpPessoaBenfcrioFinl(mensagem.getCodTipoPessoaBeneficiarioFinal());
            grupoBeneficiarioFinal.setCNPJCPFBenfcrioFinl(new BigInteger(mensagem.getNumCpfCnpjBeneficiarioFinal()));
            grupoBeneficiarioFinal.setNomRzSocBenfcrioFinl(mensagem.getNomeBeneficiarioFinal());
            grupoBeneficiarioFinal.setNomFantsBenfcrioFinl(mensagem.getNomeFantasiaBeneficiarioFinal());
            dda.setIndrManutBenfcrioFinl(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutBenfcrioFinl(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void setGrupoPagador(MensagemDDABoleto mensagem, DDA0102ComplexType dda) {
        if (!ObjectUtil.isEmpty(mensagem.getNomePagador())) {
            dda.setGrupoDDA0102Pagdr(new GrupoDDA0102PagdrComplexType());
            dda.getGrupoDDA0102Pagdr().setNomRzSocPagdr(mensagem.getNomePagador());
            dda.getGrupoDDA0102Pagdr().setNomFantsPagdr(mensagem.getNomeFantasiaPagador());
            dda.getGrupoDDA0102Pagdr().setLogradPagdr(mensagem.getDescLogradouroPagador());
            dda.getGrupoDDA0102Pagdr().setCidPagdr(mensagem.getDescCidadePagador());
            dda.getGrupoDDA0102Pagdr().setUFPagdr(mensagem.getSiglaUfPagador());
            dda.getGrupoDDA0102Pagdr().setCEPPagdr(ObjectUtil.isEmpty(mensagem.getNumCepPagador()) ? null : new BigInteger(mensagem.getNumCepPagador()));
            dda.setIndrManutPagdrTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutPagdrTit(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void setGrupoSacadorAvalista(MensagemDDABoleto mensagem, DDA0102ComplexType dda) {
        if (!ObjectUtil.isEmpty(mensagem.getCodTipoPessoaDDAAvalista())) {
            dda.setGrupoDDA0102SacdrAvalst(new GrupoDDA0102SacdrAvalstComplexType());
            dda.getGrupoDDA0102SacdrAvalst().setTpIdentcSacdrAvalst(new BigInteger(mensagem.getCodTipoPessoaDDAAvalista()));
            dda.getGrupoDDA0102SacdrAvalst().setIdentcSacdrAvalst(mensagem.getNumCpfCnpjAvalista());
            dda.getGrupoDDA0102SacdrAvalst().setNomRzSocSacdrAvalst(mensagem.getNomeAvalista());
            dda.setIndrManutSacdrAvalst(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutSacdrAvalst(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void setGrupoDocumentoBoleto(MensagemDDABoleto mensagem, DDA0102ComplexType dda) {
        if (!ObjectUtil.isEmpty(mensagem.getIdCarteira()) && !ObjectUtil.isEmpty(mensagem.getIdEspecieDocumento()) && !ObjectUtil.isEmpty(mensagem.getCodTipoPagamento())
                && !ObjectUtil.isNull(mensagem.getBolTituloNegociado())) {
            dda.setGrupoDDA0102DocTit(new GrupoDDA0102DocTitComplexType());
            dda.getGrupoDDA0102DocTit().setCodCartTit(mensagem.getIdCarteira().toString());
            dda.getGrupoDDA0102DocTit().setCodEspTit(mensagem.getIdEspecieDocumento().toString());
            dda.getGrupoDDA0102DocTit().setNumDocTit(mensagem.getNumDocumento());
            dda.getGrupoDDA0102DocTit().setTpPgtoTit(BigInteger.valueOf(mensagem.getCodTipoPagamento()));
            dda.getGrupoDDA0102DocTit().setNumParcl(ObjectUtil.isEmpty(mensagem.getNumParcela()) ? null : BigInteger.valueOf(mensagem.getNumParcela()));
            dda.getGrupoDDA0102DocTit().setQtdTotParcl(ObjectUtil.isEmpty(mensagem.getQtdTotalParcela()) ? null : BigInteger.valueOf(mensagem.getQtdTotalParcela()));
            dda.getGrupoDDA0102DocTit().setIndrTitNegcd(mensagem.getBolTituloNegociado());
            dda.setIndrManutDocTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutDocTit(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setGrupoPagamentoBoleto(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        if (!ObjectUtil.isNull(mensagem.getValorBoleto()) && !ObjectUtil.isNull(mensagem.getBolBloqueioPagamento()) && !ObjectUtil.isNull(mensagem.getValorAbatimento())
                && !ObjectUtil.isNull(mensagem.getCodTipoModeloCalculo())) {

            dda.setGrupoDDA0102InstcPgtoTit(new GrupoDDA0102InstcPgtoTitComplexType());
            dda.getGrupoDDA0102InstcPgtoTit()
                    .setDtVencTit(ObjectUtil.isNull(mensagem.getDataVencimento()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataVencimento()));
            dda.getGrupoDDA0102InstcPgtoTit().setVlrTit(mensagem.getValorBoleto());
            dda.getGrupoDDA0102InstcPgtoTit().setQtdDiaPrott(ObjectUtil.isEmpty(mensagem.getNumDiasProtesto()) ? null : BigInteger.valueOf(mensagem.getNumDiasProtesto()));
            dda.getGrupoDDA0102InstcPgtoTit()
                    .setDtLimPgtoTit(ObjectUtil.isNull(mensagem.getDataLimitePagamento()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataLimitePagamento()));
            dda.getGrupoDDA0102InstcPgtoTit().setIndrBloqPgto(mensagem.getBolBloqueioPagamento());
            dda.getGrupoDDA0102InstcPgtoTit().setVlrAbattTit(mensagem.getValorAbatimento());
            dda.getGrupoDDA0102InstcPgtoTit().setQtdPgtoParcl(ObjectUtil.isEmpty(mensagem.getQtdPagamentoParcial()) ? null : BigInteger.valueOf(mensagem.getQtdPagamentoParcial()));
            dda.getGrupoDDA0102InstcPgtoTit().setTpModlCalc(mensagem.getCodTipoModeloCalculo());
            dda.setIndrManutInstcPgtoTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutInstcPgtoTit(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void setGrupoValorRecebimento(MensagemDDABoleto mensagem, DDA0102ComplexType dda) {
        if (!ObjectUtil.isEmpty(mensagem.getCodAutorizacaoValorDivergente())) {
            dda.setGrupoDDA0102InstcVlrRecbt(new GrupoDDA0102InstcVlrRecbtComplexType());
            dda.getGrupoDDA0102InstcVlrRecbt().setTpAutcRecbtVlrDivgte(mensagem.getCodAutorizacaoValorDivergente());
            dda.getGrupoDDA0102InstcVlrRecbt().setTpVlrPercMinTit(mensagem.getCodIndicadorValorMinimo());
            dda.getGrupoDDA0102InstcVlrRecbt().setVlrPercMinTit(mensagem.getValorMinimo());
            dda.getGrupoDDA0102InstcVlrRecbt().setTpVlrPercMaxTit(mensagem.getCodIndicadorValorMaximo());
            dda.getGrupoDDA0102InstcVlrRecbt().setVlrPercMaxTit(mensagem.getValorMaximo());
            dda.setIndrManutInstcVlrRecbt(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutInstcVlrRecbt(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por popular o objeto mensagemDDABoletoJuros
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoJuros(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagem.getCodTipoJuros())) {
            dda.setGrupoDDA0102JurosTit(
                    new GrupoDDA0102JurosTitComplexType(ObjectUtil.isNull(mensagem.getDataJuros()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataJuros()),
                            mensagem.getCodTipoJuros().toString(), mensagem.getValorPercentualJuros()));
            dda.setIndrManutJurosTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutJurosTit(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por popular o objeto mensagemDDABoletoMulta
     * 
     * @param mensagemDDABoletoMulta
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoMulta(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagem.getCodTipoMulta())) {
            dda.setGrupoDDA0102MultaTit(
                    new GrupoDDA0102MultaTitComplexType(ObjectUtil.isNull(mensagem.getDataMulta()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataMulta()),
                            mensagem.getCodTipoMulta().toString(), mensagem.getValorPercentualMulta()));
            dda.setIndrManutMultaTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutMultaTit(TipoManutencaoEnum.MANTER.getCodIndicador());
        }

    }

    /**
     * Método responsável por popular o objeto mensagemDDABoletoDesconto
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoDesconto(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        if (!ObjectUtil.isNull(mensagem)) {

            int quantidade = obterQuantidadeDescontosBoletoDDA(mensagem);

            // De acordo com a quantidade realiza as operacoes necessarias
            switch (quantidade) {
            case 3:
                setMensagemDDABoletoDesconto1(mensagem, dda);
                setMensagemDDABoletoDesconto2(mensagem, dda);
                setMensagemDDABoletoDesconto3(mensagem, dda);
                break;
            case 2:
                setMensagemDDABoletoDesconto1(mensagem, dda);
                setMensagemDDABoletoDesconto2(mensagem, dda);
                break;
            default:
                getLogger().debug("###### mensagemDDABoleto -> " + mensagem.getMensagemDDA());
                setMensagemDDABoletoDesconto1(mensagem, dda);
                break;
            }
        }
    }

    /**
     * Responsavel por obter numero de atributos de descontos validos na mensagemDDABoleto
     * 
     * @param boletoDDA
     * @return Integer
     */
    private Integer obterQuantidadeDescontosBoletoDDA(MensagemDDABoleto mensagemDDABoleto) {

        int valor = 1;

        if (!ObjectUtil.isNull(mensagemDDABoleto.getCodTipoDesconto3())) {
            valor = 3;
        } else if (!ObjectUtil.isNull(mensagemDDABoleto.getCodTipoDesconto2())) {
            valor = 2;
        }

        return valor;
    }

    /**
     * Método responsável por popular o objeto primeiro desconto antiga mensagemDDABoletoDesconto
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoDesconto1(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagem.getCodTipoDesconto1())) {
            dda.getGrupoDDA0102DesctTit()
                    .add(new GrupoDDA0102DesctTitComplexType(
                            ObjectUtil.isNull(mensagem.getDataDesconto1()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataDesconto1()),
                            mensagem.getCodTipoDesconto1(), mensagem.getValorPercentualDesconto1()));
            dda.setIndrManutDesctTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutDesctTit(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por popular o objeto segundo desconto antiga mensagemDDABoletoDesconto
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoDesconto2(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagem.getCodTipoDesconto2()) && !mensagem.getCodTipoDesconto2().equals(TipoDesconto.ISENTO)) {
            dda.getGrupoDDA0102DesctTit()
                    .add(new GrupoDDA0102DesctTitComplexType(
                            ObjectUtil.isNull(mensagem.getDataDesconto2()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataDesconto2()),
                            mensagem.getCodTipoDesconto2(), mensagem.getValorPercentualDesconto2()));
            dda.setIndrManutDesctTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutDesctTit(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por popular o objeto terceiro desconto antiga mensagemDDABoletoDesconto
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoDesconto3(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagem.getCodTipoDesconto3()) && !mensagem.getCodTipoDesconto3().equals(TipoDesconto.ISENTO)) {
            dda.getGrupoDDA0102DesctTit()
                    .add(new GrupoDDA0102DesctTitComplexType(
                            ObjectUtil.isNull(mensagem.getDataDesconto3()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataDesconto3()),
                            mensagem.getCodTipoDesconto3(), mensagem.getValorPercentualDesconto3()));
            dda.setIndrManutDesctTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutDesctTit(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * Método responsável por popular o objeto mensagemDDABoletoCalculo
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoGrupoCalculo(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        for (MensagemDDABoletoGrupoCalculo mensagemDDAGrupoCalculo : mensagem.getListaMensagemDDABoletoGrupoCalculo()) {
            dda.getGrupoDDA0102Calc()
                    .add(new GrupoDDA0102CalcComplexType(mensagemDDAGrupoCalculo.getValorCalculadoJuros(), mensagemDDAGrupoCalculo.getValorCalculadoMulta(),
                            mensagemDDAGrupoCalculo.getValorCalculadoDesconto(), mensagemDDAGrupoCalculo.getValorTotalCobrado(),
                            DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDAGrupoCalculo.getDataValidadeCalculo())));
        }
    }

    /**
     * Método responsável por popular o objeto mensagemDDABoletoNotaFiscal
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoNotaFiscal(MensagemDDABoleto mensagem, DDA0102ComplexType dda) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagem.getListaMensagemDDABoletoNotaFiscal())) {
            for (MensagemDDABoletoNotaFiscal mensagemDDANotaFiscal : mensagem.getListaMensagemDDABoletoNotaFiscal()) {
                dda.getGrupoDDA0102NotaFis().add(new GrupoDDA0102NotaFisComplexType(mensagemDDANotaFiscal.getNumNotaFiscal(),
                        DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDANotaFiscal.getDataEmissaoNotaFiscal()), mensagemDDANotaFiscal.getValorNotaFiscal()));
            }
            dda.setIndrManutHistNotaFis(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutHistNotaFis(TipoManutencaoEnum.MANTER.getCodIndicador());
        }

    }

    /**
     * Método responsável por popular o campo TextoInfo
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void setMensagemDDABoletoTextoInfo(MensagemDDABoleto mensagem, DDA0102ComplexType dda) {
        if (!ObjectUtil.isEmpty(mensagem.getListaMensagemDDABoletoTextoInfo())) {
            for (MensagemDDABoletoTextoInfo mensagemDDABoletoTextoInfo : mensagem.getListaMensagemDDABoletoTextoInfo()) {
                dda.getTxtInfBenfcrio().add(mensagemDDABoletoTextoInfo.getDescTextoInformativo());
            }
            dda.setIndrManutHistTxtInf(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            dda.setIndrManutHistTxtInf(TipoManutencaoEnum.MANTER.getCodIndicador());
        }

    }

    /**
     * @param boletoDDABanco
     * @throws ComumException void
     * 
     */
    private void removendoObjetosBoleto(BoletoDDA boletoDDABanco) throws ComumException {
        getDao().removerBaixaEfetiva(boletoDDABanco.getId());
        getDao().removerBaixaOperacional(boletoDDABanco.getId());
        getDao().removerGrupoCalculo(boletoDDABanco.getId());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * Método responsável por
     * 
     * @return BoletoCipDao
     * 
     */
    public BoletoCipDao getDao() {
        return dao;
    }
}
