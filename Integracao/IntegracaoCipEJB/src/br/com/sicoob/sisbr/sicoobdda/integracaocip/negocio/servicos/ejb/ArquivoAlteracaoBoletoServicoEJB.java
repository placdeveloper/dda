package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoNotaFiscal;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoTextoInfo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaSacadorAvalistaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoAlteracaoBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoAlteraBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.ADDA102ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102BenfcrioFinlComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102BenfcrioOrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102DocTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102InstcPgtoTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102InstcVlrRecbtComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102SacdrAvalstComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.SISARQComplexType;

/**
 * ArquivoAlteracaoBoletoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoAlteracaoBoletoServicoLocal.class })
public class ArquivoAlteracaoBoletoServicoEJB extends IntegracaoCipServicoEJB implements ArquivoAlteracaoBoletoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoAlteraBoletoDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao boletoDao;

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
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoAlteracaoBoletoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
        dao.alterarADDA102RR2(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoAlteracaoBoletoServico#processarRetornoAlteracaoMensagemDDA(long, long, long)
     */
    public void processarRetornoAlteracaoMensagemDDA(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        dao.alterarADDA102RET(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        return new SISARQComplexType(getADDA102(boletoDao.listarMensagemDDABoletoLogEnvioArquivo(idLogEnvioArquivoDDA)));
    }

    /**
     * Método responsável por montar o ADDA001
     * 
     * @param listaMensagemDDABoleto
     * @return
     * @throws ComumException ADDA102ComplexType
     * 
     */
    private ADDA102ComplexType getADDA102(List<MensagemDDABoleto> listaMensagemDDABoleto) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDABoleto.size());

        ADDA102ComplexType adda102 = new ADDA102ComplexType();

        for (MensagemDDABoleto mensagemDDABoleto : listaMensagemDDABoleto) {
            adda102.getGrupoADDA102Tit().add(getGrupoADDA102(mensagemDDABoleto));
        }

        return adda102;
    }

    /**
     * Método responsável por popular o GrupoADDA101
     * 
     * @param mensagemDDABoleto
     * @return
     * @throws ComumException GrupoADDA001PagdrComplexType
     * 
     */
    private GrupoADDA102TitComplexType getGrupoADDA102(MensagemDDABoleto mensagemDDABoleto) throws ComumException {
        GrupoADDA102TitComplexType boleto = new GrupoADDA102TitComplexType();
        boleto.setNumCtrlReqPart(mensagemDDABoleto.getId().toString());
        boleto.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);
        boleto.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);

        boleto.setNumIdentcTit(ObjectUtil.isNull(mensagemDDABoleto.getNumIdentificadorBoletoCip()) ? BigInteger.ZERO : BigInteger.valueOf(mensagemDDABoleto
                .getNumIdentificadorBoletoCip()));
        boleto.setNumRefAtlCadTit(ObjectUtil.isNull(mensagemDDABoleto.getNumRefAtualCadBoleto()) ? null : BigInteger.valueOf(mensagemDDABoleto.getNumRefAtualCadBoleto()));

        setBeneficiarioOriginal(mensagemDDABoleto, boleto);

        setBeneficiarioFinal(mensagemDDABoleto, boleto);

        setPagador(mensagemDDABoleto, boleto);

        setSacadorAvalista(mensagemDDABoleto, boleto);

        setDocumentoTitulo(mensagemDDABoleto, boleto);

        setInstrucaoPagamentoTitulo(mensagemDDABoleto, boleto);

        setInstrucaoValorRecebimento(mensagemDDABoleto, boleto);

        setBoletoJuros(mensagemDDABoleto, boleto);

        setBoletoMulta(mensagemDDABoleto, boleto);

        setBoletoDesconto(mensagemDDABoleto, boleto);

        setGrupoCalculo(mensagemDDABoleto, boleto);

        setBoletoNotaFiscal(mensagemDDABoleto, boleto);

        setBoletoTextoInfo(mensagemDDABoleto, boleto);
        return boleto;
    }

    /**
     * @param mensagemDDABoleto
     * @param boleto void
     * 
     */
    private void setBeneficiarioOriginal(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getNomeFantasiaBeneficiario()) || !ObjectUtil.isEmpty(mensagemDDABoleto.getDescLogradouroBeneficiario())
                || !ObjectUtil.isEmpty(mensagemDDABoleto.getDescCidadeBeneficiario()) || !ObjectUtil.isEmpty(mensagemDDABoleto.getSiglaUfBeneficiario())
                || !ObjectUtil.isEmpty(mensagemDDABoleto.getNumCepBeneficiario())) {
            GrupoADDA102BenfcrioOrComplexType grupoBeneficiarioOriginal = new GrupoADDA102BenfcrioOrComplexType();
            grupoBeneficiarioOriginal.setNomFantsBenfcrioOr(mensagemDDABoleto.getNomeFantasiaBeneficiario());
            grupoBeneficiarioOriginal.setLogradBenfcrioOr(mensagemDDABoleto.getDescLogradouroBeneficiario());
            grupoBeneficiarioOriginal.setCidBenfcrioOr(mensagemDDABoleto.getDescCidadeBeneficiario());
            grupoBeneficiarioOriginal.setUFBenfcrioOr(mensagemDDABoleto.getSiglaUfBeneficiario());
            grupoBeneficiarioOriginal.setCEPBenfcrioOr(ObjectUtil.isEmpty(mensagemDDABoleto.getNumCepBeneficiario()) ? null : new BigInteger(mensagemDDABoleto
                    .getNumCepBeneficiario()));
            boleto.setGrupoADDA102BenfcrioOr(grupoBeneficiarioOriginal);
            boleto.setIndrManutBenfcrioOr(TipoManutencaoEnum.ALTERAR.getCodIndicador());
        } else {
            boleto.setIndrManutBenfcrioOr(TipoManutencaoEnum.MANTER.getCodIndicador());
        }
    }

    /**
     * @param mensagemDDABoleto
     * @param boleto void
     * 
     */
    private void setBeneficiarioFinal(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoPessoaBeneficiarioFinal()) && !ObjectUtil.isEmpty(mensagemDDABoleto.getNumCpfCnpjBeneficiarioFinal())) {
            GrupoADDA102BenfcrioFinlComplexType grupoBeneficiarioFinal = new GrupoADDA102BenfcrioFinlComplexType();
            grupoBeneficiarioFinal.setTpPessoaBenfcrioFinl(mensagemDDABoleto.getCodTipoPessoaBeneficiarioFinal());
            grupoBeneficiarioFinal.setCNPJCPFBenfcrioFinl(new BigInteger(mensagemDDABoleto.getNumCpfCnpjBeneficiarioFinal()));
            grupoBeneficiarioFinal.setNomRzSocBenfcrioFinl(mensagemDDABoleto.getNomeBeneficiarioFinal());
            grupoBeneficiarioFinal.setNomFantsBenfcrioFinl(mensagemDDABoleto.getNomeFantasiaBeneficiarioFinal());
            boleto.setGrupoADDA102BenfcrioFinl(grupoBeneficiarioFinal);
            boleto.setIndrManutBenfcrioFinl(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao - Exclusao -Manter
        } else {
            boleto.setIndrManutBenfcrioFinl(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao - Exclusao -Manter
        }

    }

    /**
     * @param mensagemDDABoleto
     * @param boleto void
     * 
     */
    private void setPagador(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getNomePagador())) {
            GrupoADDA102PagdrComplexType grupoPagador = new GrupoADDA102PagdrComplexType();
            grupoPagador.setNomRzSocPagdr(mensagemDDABoleto.getNomePagador());
            grupoPagador.setNomFantsPagdr(mensagemDDABoleto.getNomeFantasiaPagador());
            grupoPagador.setLogradPagdr(mensagemDDABoleto.getDescLogradouroPagador());
            grupoPagador.setCidPagdr(mensagemDDABoleto.getDescCidadePagador());
            grupoPagador.setUFPagdr(mensagemDDABoleto.getSiglaUfPagador());
            grupoPagador.setCEPPagdr(ObjectUtil.isEmpty(mensagemDDABoleto.getNumCepPagador()) ? null : new BigInteger(mensagemDDABoleto.getNumCepPagador()));
            boleto.setGrupoADDA102Pagdr(grupoPagador);
            boleto.setIndrManutPagdrTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutPagdrTit(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }
    }

    /**
     * @param mensagemDDABoleto
     * @param boleto void
     * 
     */
    private void setSacadorAvalista(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoPessoaDDAAvalista())) {
            GrupoADDA102SacdrAvalstComplexType grupoSacadorAvalista = new GrupoADDA102SacdrAvalstComplexType();
            grupoSacadorAvalista.setTpIdentcSacdrAvalst(ObjectUtil.isNull(mensagemDDABoleto.getCodTipoPessoaDDAAvalista()) ? new BigInteger(
                    TipoPessoaSacadorAvalistaEnum.ISENTO_NAO_INFORMADO.getCodDominio()) : new BigInteger(mensagemDDABoleto.getCodTipoPessoaDDAAvalista()));
            grupoSacadorAvalista.setIdentcSacdrAvalst(mensagemDDABoleto.getNumCpfCnpjAvalista());
            grupoSacadorAvalista.setNomRzSocSacdrAvalst(mensagemDDABoleto.getNomeAvalista());
            boleto.setGrupoADDA102SacdrAvalst(grupoSacadorAvalista);
            boleto.setIndrManutSacdrAvalst(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutSacdrAvalst(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }
    }

    /**
     * @param mensagemDDABoleto
     * @param boleto void
     * 
     */
    private void setDocumentoTitulo(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getIdCarteira()) && !ObjectUtil.isEmpty(mensagemDDABoleto.getIdEspecieDocumento())
                && !ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoPagamento()) && !ObjectUtil.isNull(mensagemDDABoleto.getBolTituloNegociado())) {
            GrupoADDA102DocTitComplexType grupoDocumentoTitulo = new GrupoADDA102DocTitComplexType();
            grupoDocumentoTitulo.setCodCartTit(mensagemDDABoleto.getIdCarteira().toString());
            grupoDocumentoTitulo.setCodEspTit(mensagemDDABoleto.getIdEspecieDocumento().toString());
            grupoDocumentoTitulo.setNumDocTit(mensagemDDABoleto.getNumDocumento());
            grupoDocumentoTitulo.setTpPgtoTit(BigInteger.valueOf(mensagemDDABoleto.getCodTipoPagamento()));
            grupoDocumentoTitulo.setNumParcl(ObjectUtil.isEmpty(mensagemDDABoleto.getNumParcela()) ? null : BigInteger.valueOf(mensagemDDABoleto.getNumParcela()));
            grupoDocumentoTitulo.setQtdTotParcl(ObjectUtil.isEmpty(mensagemDDABoleto.getQtdTotalParcela()) ? null : BigInteger.valueOf(mensagemDDABoleto.getQtdTotalParcela()
                    .longValue()));
            grupoDocumentoTitulo.setIndrTitNegcd(mensagemDDABoleto.getBolTituloNegociado());
            boleto.setGrupoADDA102DocTit(grupoDocumentoTitulo);
            boleto.setIndrManutDocTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutDocTit(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }

    }

    /**
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setInstrucaoPagamentoTitulo(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isNull(mensagemDDABoleto.getValorBoleto()) && !ObjectUtil.isNull(mensagemDDABoleto.getBolBloqueioPagamento())
                && !ObjectUtil.isNull(mensagemDDABoleto.getValorAbatimento()) && !ObjectUtil.isNull(mensagemDDABoleto.getCodTipoModeloCalculo())) {
            GrupoADDA102InstcPgtoTitComplexType grupoInstrucaoPagamentoTitulo = new GrupoADDA102InstcPgtoTitComplexType();
            grupoInstrucaoPagamentoTitulo.setDtVencTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataVencimento()));
            grupoInstrucaoPagamentoTitulo.setVlrTit(mensagemDDABoleto.getValorBoleto());
            grupoInstrucaoPagamentoTitulo.setQtdDiaPrott(ObjectUtil.isEmpty(mensagemDDABoleto.getNumDiasProtesto()) ? null : BigInteger.valueOf(mensagemDDABoleto
                    .getNumDiasProtesto()));
            grupoInstrucaoPagamentoTitulo.setDtLimPgtoTit(ObjectUtil.isNull(mensagemDDABoleto.getDataLimitePagamento()) ? null : DataCipUtil
                    .dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataLimitePagamento()));
            grupoInstrucaoPagamentoTitulo.setIndrBloqPgto(mensagemDDABoleto.getBolBloqueioPagamento());
            grupoInstrucaoPagamentoTitulo.setVlrAbattTit(mensagemDDABoleto.getValorAbatimento());
            grupoInstrucaoPagamentoTitulo.setQtdPgtoParcl(ObjectUtil.isEmpty(mensagemDDABoleto.getQtdPagamentoParcial()) ? null : BigInteger.valueOf(mensagemDDABoleto
                    .getQtdPagamentoParcial()));
            grupoInstrucaoPagamentoTitulo.setTpModlCalc(mensagemDDABoleto.getCodTipoModeloCalculo());
            boleto.setGrupoADDA102InstcPgtoTit(grupoInstrucaoPagamentoTitulo);
            boleto.setIndrManutInstcPgtoTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutInstcPgtoTit(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }

    }

    /**
     * @param mensagemDDABoleto
     * @param boleto void
     * 
     */
    private void setInstrucaoValorRecebimento(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodAutorizacaoValorDivergente())) {
            GrupoADDA102InstcVlrRecbtComplexType grupoInstrucaoValorRecebimento = new GrupoADDA102InstcVlrRecbtComplexType();
            grupoInstrucaoValorRecebimento.setTpAutcRecbtVlrDivgte(mensagemDDABoleto.getCodAutorizacaoValorDivergente());
            grupoInstrucaoValorRecebimento.setTpVlrPercMinTit(mensagemDDABoleto.getCodIndicadorValorMinimo());
            grupoInstrucaoValorRecebimento.setVlrPercMinTit(mensagemDDABoleto.getValorMinimo());
            grupoInstrucaoValorRecebimento.setTpVlrPercMaxTit(mensagemDDABoleto.getCodIndicadorValorMaximo());
            grupoInstrucaoValorRecebimento.setVlrPercMaxTit(mensagemDDABoleto.getValorMaximo());
            boleto.setGrupoADDA102InstcVlrRecbt(grupoInstrucaoValorRecebimento);
            boleto.setIndrManutInstcVlrRecbt(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutInstcVlrRecbt(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }
    }

    /**
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setBoletoJuros(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoJuros())) {
            // Fluxo unico para nosso negocial mas de acordo com modelo da CIP pode receber mais em caso de evolucao tratar como desconto
                GrupoADDA102JurosTitComplexType grupoJuros = new GrupoADDA102JurosTitComplexType();
            grupoJuros.setDtJurosTit(ObjectUtil.isNull(mensagemDDABoleto.getDataJuros()) ? null
                    : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto
                        .getDataJuros()));
            grupoJuros.setCodJurosTit(mensagemDDABoleto.getCodTipoJuros().toString());
            grupoJuros.setVlrPercJurosTit(mensagemDDABoleto.getValorPercentualJuros());
                boleto.setGrupoADDA102JurosTit(grupoJuros);
            boleto.setIndrManutJurosTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutJurosTit(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }

    }

    /**
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setBoletoMulta(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoMulta())) {
            // Fluxo unico para nosso negocial mas de acordo com modelo da CIP pode receber mais em caso de evolucao tratar como desconto
                GrupoADDA102MultaTitComplexType grupoMulta = new GrupoADDA102MultaTitComplexType();
            grupoMulta.setDtMultaTit(ObjectUtil.isNull(mensagemDDABoleto.getDataMulta()) ? null
                    : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto
                        .getDataMulta()));
            grupoMulta.setCodMultaTit(mensagemDDABoleto.getCodTipoMulta().toString());
            grupoMulta.setVlrPercMultaTit(mensagemDDABoleto.getValorPercentualMulta());
                boleto.setGrupoADDA102MultaTit(grupoMulta);
            boleto.setIndrManutMultaTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutMultaTit(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }

    }

    /**
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setBoletoDesconto(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isNull(mensagemDDABoleto)) {

            int quantidade = obterQuantidadeDescontosBoletoDDA(mensagemDDABoleto);

            // De acordo com a quantidade realiza as operacoes necessarias
            switch (quantidade) {
            case 3:
                setBoletoDesconto1(mensagemDDABoleto, boleto);
                setBoletoDesconto2(mensagemDDABoleto, boleto);
                setBoletoDesconto3(mensagemDDABoleto, boleto);
                break;
            case 2:
                setBoletoDesconto1(mensagemDDABoleto, boleto);
                setBoletoDesconto2(mensagemDDABoleto, boleto);
                break;
            case 1:
                setBoletoDesconto1(mensagemDDABoleto, boleto);
                break;
            default:
                getLogger().debug("###### mensagemDDABoleto -> " + mensagemDDABoleto.getMensagemDDA());
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
     * Processamento do primeiro desconto
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setBoletoDesconto1(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto1())) {
            GrupoADDA102DesctTitComplexType grupoDesconto = new GrupoADDA102DesctTitComplexType();
            grupoDesconto.setDtDesctTit(
                    ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto1()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataDesconto1()));
            grupoDesconto.setCodDesctTit(mensagemDDABoleto.getCodTipoDesconto1());
            grupoDesconto.setVlrPercDesctTit(mensagemDDABoleto.getValorPercentualDesconto1());
            boleto.getGrupoADDA102DesctTit().add(grupoDesconto);
            boleto.setIndrManutDesctTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutDesctTit(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }
    }

    /**
     * Processamento do segundo desconto
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setBoletoDesconto2(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto2()) && !mensagemDDABoleto.getCodTipoDesconto2().equals(TipoDesconto.ISENTO)) {
                GrupoADDA102DesctTitComplexType grupoDesconto = new GrupoADDA102DesctTitComplexType();
            grupoDesconto.setDtDesctTit(
                    ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto2()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataDesconto2()));
            grupoDesconto.setCodDesctTit(mensagemDDABoleto.getCodTipoDesconto2());
            grupoDesconto.setVlrPercDesctTit(mensagemDDABoleto.getValorPercentualDesconto2());
                boleto.getGrupoADDA102DesctTit().add(grupoDesconto);
            boleto.setIndrManutDesctTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutDesctTit(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }
    }

    /**
     * Processamento do terceiro desconto
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setBoletoDesconto3(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto3()) && !mensagemDDABoleto.getCodTipoDesconto3().equals(TipoDesconto.ISENTO)) {
                GrupoADDA102DesctTitComplexType grupoDesconto = new GrupoADDA102DesctTitComplexType();
            grupoDesconto.setDtDesctTit(
                    ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto3()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataDesconto3()));
            grupoDesconto.setCodDesctTit(mensagemDDABoleto.getCodTipoDesconto3());
            grupoDesconto.setVlrPercDesctTit(mensagemDDABoleto.getValorPercentualDesconto3());
                boleto.getGrupoADDA102DesctTit().add(grupoDesconto);
            boleto.setIndrManutDesctTit(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Manter
        } else {
            boleto.setIndrManutDesctTit(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Manter
        }
    }

    /**
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setGrupoCalculo(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        for (MensagemDDABoletoGrupoCalculo mensagemDDABoletoGrupoCalculo : mensagemDDABoleto.getListaMensagemDDABoletoGrupoCalculo()) {
            GrupoADDA102CalcComplexType grupoCalculo = new GrupoADDA102CalcComplexType();
            grupoCalculo.setVlrCalcdJuros(mensagemDDABoletoGrupoCalculo.getValorCalculadoJuros());
            grupoCalculo.setVlrCalcdMulta(mensagemDDABoletoGrupoCalculo.getValorCalculadoMulta());
            grupoCalculo.setVlrCalcdDesct(mensagemDDABoletoGrupoCalculo.getValorCalculadoDesconto());
            grupoCalculo.setVlrTotCobrar(mensagemDDABoletoGrupoCalculo.getValorTotalCobrado());
            grupoCalculo.setDtValiddCalc(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoletoGrupoCalculo.getDataValidadeCalculo()));

            boleto.getGrupoADDA102Calc().add(grupoCalculo);
        }
    }

    /**
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void setBoletoNotaFiscal(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getListaMensagemDDABoletoNotaFiscal())) {
            for (MensagemDDABoletoNotaFiscal mensagemDDABoletoNotaFiscal : mensagemDDABoleto.getListaMensagemDDABoletoNotaFiscal()) {
                GrupoADDA102NotaFisComplexType grupoNotaFiscal = new GrupoADDA102NotaFisComplexType();
                grupoNotaFiscal.setNumNotaFis(mensagemDDABoletoNotaFiscal.getNumNotaFiscal());
                grupoNotaFiscal.setDtEmsNotaFis(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoletoNotaFiscal.getDataEmissaoNotaFiscal()));
                grupoNotaFiscal.setVlrNotaFis(mensagemDDABoletoNotaFiscal.getValorNotaFiscal());
                boleto.getGrupoADDA102NotaFis().add(grupoNotaFiscal);
            }
            boleto.setIndrManutHistNotaFis(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Excluir - Manter
        } else {
            boleto.setIndrManutHistNotaFis(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Excluir - Manter
        }
    }

    /**
     * @param mensagemDDABoleto
     * @param boleto void
     * 
     */
    private void setBoletoTextoInfo(MensagemDDABoleto mensagemDDABoleto, GrupoADDA102TitComplexType boleto) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getListaMensagemDDABoletoTextoInfo())) {
            for (MensagemDDABoletoTextoInfo mensagemDDABoletoTextoInfo : mensagemDDABoleto.getListaMensagemDDABoletoTextoInfo()) {
                boleto.getTxtInfBenfcrio().add(mensagemDDABoletoTextoInfo.getDescTextoInformativo());
            }
            boleto.setIndrManutHistTxtInf(TipoManutencaoEnum.ALTERAR.getCodIndicador());// Alteracao- Excluir - Manter
        } else {
            boleto.setIndrManutHistTxtInf(TipoManutencaoEnum.MANTER.getCodIndicador());// Alteracao- Excluir - Manter
        }
    }
}
