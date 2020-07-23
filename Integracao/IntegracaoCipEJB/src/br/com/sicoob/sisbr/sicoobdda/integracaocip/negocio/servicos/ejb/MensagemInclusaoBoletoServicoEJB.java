package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoNotaFiscal;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoTextoInfo;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaSacadorAvalistaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemInclusaoBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.GrupoDDA0101CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.GrupoDDA0101DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.GrupoDDA0101JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.GrupoDDA0101MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.GrupoDDA0101NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

@Stateless
@Local({ MensagemInclusaoBoletoServicoLocal.class })
public class MensagemInclusaoBoletoServicoEJB extends IntegracaoCipServicoEJB implements MensagemInclusaoBoletoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoIncluirBoletoDao arquivoDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemInclusaoBoletoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) {
        arquivoDao.incluirADDA101RR2(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0101 - Participante informa inclusão de Boleto");
        MensagemDDABoleto mensagemDDABoleto = getDao().obterMensagemDDABoleto(idMensagem);

        DDA0101ComplexType dda0101 = getDDA0101EnvioCIP(mensagemDDABoleto);
        getLogger().debug("*******INICIO obterXmlEnvio*******");
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(dda0101, mensagemDDABoleto.getId());
        getLogger().debug("xmlEnvio gerado >>> " + xmlEnvio);

        getLogger().debug("########### Fim processarMensagem(Long idMensagem) para DDA0101 - Participante informa inclusão de Boleto");
        return xmlEnvio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
        getLogger().debug("########### Inicio processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0101R1 ");

        getLogger().info("Inicio da conversão do Objeto do Boleto");
        MensagemDDABoleto mensagemBoleto = getMensagemDDABoleto(retornoDDA);
        if (!ObjectUtil.isNull(mensagemBoleto) && !ObjectUtil.isEmpty(mensagemBoleto.getId())) {
            BoletoDDA boletoDDAEncontradoBase = getDao().obterBoletoDDA(mensagemBoleto.getNumCodigoBarra(), (int) SituacaoBoleto.ABERTO);
            if (!ObjectUtil.isNull(boletoDDAEncontradoBase)) {
                getLogger().debug("Atualização do boletoDDA");
                updateBoletoDDA(retornoDDA, boletoDDAEncontradoBase);

            } else {
                BoletoDDA boletoDDA = ConversorBoletoDDA.converter(retornoDDA, mensagemBoleto);
                getLogger().info("Objeto Convertido com Sucesso");

                BoletoDDA boletoddaBase = getDao().obterBoletoDDA(boletoDDA.getNumCodigoBarra(), (int) SituacaoBoleto.ABERTO);

                if (ObjectUtil.isNull(boletoddaBase)) {
                    em.persist(boletoDDA);
                }
            }
        }
        getLogger().debug("########### Fim processarRetornoMensagemDDA(Long idMensagem, Object retornoDDA) - DDA0101R1 ");
    }

    /**
     * Método responsável por Fazer o update da boleto dda e atualizar somente o Numidentificador, NumReferencia, numSeq e o codIspbPartDestinatario.
     * 
     * @param retornoDDA
     * @param boletoDDAEncontradoBase void
     */
    private void updateBoletoDDA(ConteudoMsgRecebida retornoDDA, BoletoDDA boletoDDAEncontradoBase) {
        if (retornoDDA instanceof DDA0101R1ComplexType) {
            DDA0101R1ComplexType boletoRetorno = (DDA0101R1ComplexType) retornoDDA;
            setBoletoDDARetornoCIP(boletoDDAEncontradoBase, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit());
        } else if (retornoDDA instanceof GrupoADDA101RETTitActoComplexType) {
            GrupoADDA101RETTitActoComplexType boletoRetorno = (GrupoADDA101RETTitActoComplexType) retornoDDA;
            setBoletoDDARetornoCIP(boletoDDAEncontradoBase, boletoRetorno.getNumIdentcTit(), boletoRetorno.getNumRefAtlCadTit(), boletoRetorno.getNumSeqAtlzCadTit());
        }

        em.merge(boletoDDAEncontradoBase);
    }

    /**
     * Método responsável por 
     * @param boletoDDAEncontradoBase
     * @param boletoRetorno void
     */
    private void setBoletoDDARetornoCIP(BoletoDDA boletoDDAEncontradoBase, BigInteger numIdentificadorCIP, BigInteger numRefAtualCadBoleto, BigInteger numSeqBoleto) {
        getLogger().debug("NumIdentificadorBoletoCIP - " + numIdentificadorCIP);
        getLogger().debug("NumRefAtualCadBoleto - " + numRefAtualCadBoleto);
        getLogger().debug("NumSeqAtualCadBoleto - " + numSeqBoleto);

        boletoDDAEncontradoBase.setNumIdentificadorBoletoCip(numIdentificadorCIP.longValue());
        boletoDDAEncontradoBase.setNumRefAtualCadBoleto(ObjectUtil.isEmpty(numRefAtualCadBoleto) ? null : numRefAtualCadBoleto.longValue());
        boletoDDAEncontradoBase.setNumSeqAtualCadBoleto(numSeqBoleto.longValue());
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @return MensagemDDABoleto
     * 
     */
    private MensagemDDABoleto getMensagemDDABoleto(ConteudoMsgRecebida retornoDDA) {
        MensagemDDABoleto mensagemBoleto = null;
        if (!ObjectUtil.isEmpty(retornoDDA.getIdMensagemOrigem())) {
            mensagemBoleto = getDao().obterMensagemDDABoleto(retornoDDA.getIdMensagemOrigem());
        }
        return mensagemBoleto;
    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0101ComplexType
     * 
     */
    private DDA0101ComplexType getDDA0101EnvioCIP(MensagemDDABoleto mensagem) throws ComumException {
        DDA0101ComplexType dda = new DDA0101ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0101);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);
        dda.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);

        dda.setCodPartDestinatario(Constantes.BANCOOB);

        // Beneficiario
        dda.setTpPessoaBenfcrioOr(mensagem.getCodTipoPessoaBeneficiario());
        dda.setCNPJCPFBenfcrioOr(new BigInteger(mensagem.getNumCpfCnpjBeneficiario()));
        dda.setNomRzSocBenfcrioOr(mensagem.getNomeBeneficiario());
        dda.setNomFantsBenfcrioOr(mensagem.getNomeFantasiaBeneficiario());
        dda.setLogradBenfcrioOr(mensagem.getDescLogradouroBeneficiario());
        dda.setCidBenfcrioOr(mensagem.getDescCidadeBeneficiario());
        dda.setUFBenfcrioOr(mensagem.getSiglaUfBeneficiario());

        // Definimos que quando nao tiver CEP pagador iremos colocar inserir um CEP padrao.
        dda.setCEPBenfcrioOr(ObjectUtil.isEmpty(mensagem.getNumCepBeneficiario()) || mensagem.getNumCepBeneficiario().trim().length() < Constantes.NUM_CEP ? null : new BigInteger(
                mensagem.getNumCepBeneficiario()));

        // BeneficiarioFinal
        dda.setTpPessoaBenfcrioFinl(mensagem.getCodTipoPessoaBeneficiarioFinal());
        dda.setCNPJCPFBenfcrioFinl(ObjectUtil.isEmpty(mensagem.getNumCpfCnpjBeneficiarioFinal()) ? null : new BigInteger(mensagem.getNumCpfCnpjBeneficiarioFinal().trim()));
        dda.setNomRzSocBenfcrioFinl(mensagem.getNomeBeneficiarioFinal());
        dda.setNomFantsBenfcrioFinl(mensagem.getNomeFantasiaBeneficiarioFinal());

        // Pagador
        dda.setTpPessoaPagdr(mensagem.getCodTipoPessoaPagador());
        dda.setCNPJCPFPagdr(new BigInteger(mensagem.getNumCpfCnpjPagador().trim()));
        dda.setNomRzSocPagdr(mensagem.getNomePagador());
        dda.setNomFantsPagdr(mensagem.getNomeFantasiaPagador());
        dda.setLogradPagdr(mensagem.getDescLogradouroPagador());
        dda.setCidPagdr(mensagem.getDescCidadePagador());
        dda.setUFPagdr(mensagem.getSiglaUfPagador());

        // Definimos que quando nao tiver CEP pagador iremos colocar inserir um CEP padrao.
        dda.setCEPPagdr(ObjectUtil.isEmpty(mensagem.getNumCepPagador()) || mensagem.getNumCepPagador().trim().length() < Constantes.NUM_CEP ? null : new BigInteger(mensagem
                .getNumCepPagador()));

        // Sacador Avalista
        dda.setTpIdentcSacdrAvalst(ObjectUtil.isNull(mensagem.getCodTipoPessoaDDAAvalista()) ? new BigInteger(TipoPessoaSacadorAvalistaEnum.ISENTO_NAO_INFORMADO.getCodDominio())
                : new BigInteger(mensagem.getCodTipoPessoaDDAAvalista()));
        dda.setIdentcSacdrAvalst(mensagem.getNumCpfCnpjAvalista());
        dda.setNomRzSocSacdrAvalst(mensagem.getNomeAvalista());

        dda.setCodCartTit(mensagem.getIdCarteira().toString());
        dda.setCodMoedaCNAB(mensagem.getCodMoeda());
        dda.setIdentdNossoNum(mensagem.getNumNossoNumero());
        dda.setNumCodBarras(mensagem.getNumCodigoBarra());
        dda.setNumLinhaDigtvl(mensagem.getNumLinhaDigitavel());

        dda.setDtVencTit(ObjectUtil.isNull(mensagem.getDataVencimento()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataVencimento()));
        dda.setVlrTit(mensagem.getValorBoleto());
        dda.setNumDocTit(ObjectUtil.isEmpty(mensagem.getNumDocumento()) ? null : mensagem.getNumDocumento().trim());

        dda.setCodEspTit(mensagem.getIdEspecieDocumento().toString());

        dda.setDtEmsTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataEmissao()));
        dda.setQtdDiaPrott(ObjectUtil.isEmpty(mensagem.getNumDiasProtesto()) ? null : BigInteger.valueOf(mensagem.getNumDiasProtesto().longValue()));
        dda.setDtLimPgtoTit(ObjectUtil.isNull(mensagem.getDataLimitePagamento()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataLimitePagamento()));
        dda.setTpPgtoTit(BigInteger.valueOf(mensagem.getCodTipoPagamento().longValue()));
        dda.setNumParcl(ObjectUtil.isEmpty(mensagem.getNumParcela()) ? null : BigInteger.valueOf(mensagem.getNumParcela().longValue()));
        dda.setQtdTotParcl(ObjectUtil.isEmpty(mensagem.getQtdTotalParcela()) ? null : BigInteger.valueOf(mensagem.getQtdTotalParcela().longValue()));

        dda.setIndrTitNegcd(mensagem.getBolTituloNegociado());
        dda.setIndrBloqPgto(mensagem.getBolBloqueioPagamento());
        dda.setIndrPgtoParcl(mensagem.getBolPagamentoParcial());

        if (mensagem.isBolPagamentoParcial()) {
            dda.setQtdPgtoParcl(ObjectUtil.isEmpty(mensagem.getQtdPagamentoParcial()) ? null : BigInteger.valueOf(mensagem.getQtdPagamentoParcial().longValue()));
        }
        dda.setVlrAbattTit(mensagem.getValorAbatimento());

        dda.setTpVlrPercMinTit(mensagem.getCodIndicadorValorMinimo());
        dda.setVlrPercMinTit(mensagem.getValorMinimo());

        dda.setTpVlrPercMaxTit(mensagem.getCodIndicadorValorMaximo());
        dda.setVlrPercMaxTit(mensagem.getValorMaximo());

        dda.setTpModlCalc(mensagem.getCodTipoModeloCalculo().toString());
        dda.setTpAutcRecbtVlrDivgte(mensagem.getCodAutorizacaoValorDivergente().toString());

        if (!ObjectUtil.isEmpty(mensagem.getCodTipoJuros())) {
            setMensagemDDABoletoJuros(mensagem, dda);
        }

        if (!ObjectUtil.isEmpty(mensagem.getCodTipoMulta())) {
            setMensagemDDABoletoMulta(mensagem, dda);
        }

        if (!ObjectUtil.isEmpty(mensagem.getCodTipoDesconto1())) {
            setMensagemDDABoletoDesconto(mensagem, dda);
        }

        if (!ObjectUtil.isEmpty(mensagem.getListaMensagemDDABoletoNotaFiscal())) {
            setMensagemDDABoletoNotaFiscal(mensagem, dda);
        }

        if (!ObjectUtil.isEmpty(mensagem.getListaMensagemDDABoletoGrupoCalculo())) {
            setMensagemDDABoletoGrupoCalculo(mensagem, dda);
        }

        if (!ObjectUtil.isEmpty(mensagem.getListaMensagemDDABoletoTextoInfo())) {
            setMensagemDDABoletoTextoInfo(mensagem, dda);
        }

        dda.setDtMovto(DataCipUtil.dateTimeZeroHoraParaXMLGregorianCalendar(mensagem.getMensagemDDA().getDataMovimento()));

        return dda;
    }

    /**
     * Método responsável por popular o objeto mensagemDDABoletoJuros
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoJuros(MensagemDDABoleto mensagem, DDA0101ComplexType dda) throws ComumException {
        dda.setGrupoDDA0101JurosTit(new GrupoDDA0101JurosTitComplexType(ObjectUtil.isNull(mensagem.getDataJuros()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem
                .getDataJuros()), mensagem.getCodTipoJuros().toString(), mensagem.getValorPercentualJuros()));
    }

    /**
     * Método responsável por popular o objeto mensagemDDABoletoMulta
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoMulta(MensagemDDABoleto mensagem, DDA0101ComplexType dda) throws ComumException {
        dda.setGrupoDDA0101MultaTit(new GrupoDDA0101MultaTitComplexType(ObjectUtil.isNull(mensagem.getDataMulta()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem
                .getDataMulta()), mensagem.getCodTipoMulta().toString(), mensagem.getValorPercentualMulta()));
    }

    /**
     * Metodo responsavel por popular o objeto desconto 1 antiga entidade mensagemDDABoletoDesconto
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoDesconto(MensagemDDABoleto mensagem, DDA0101ComplexType dda) throws ComumException {
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
            case 1:
                setMensagemDDABoletoDesconto1(mensagem, dda);
                break;
            default:
                getLogger().debug("###### mensagemDDABoleto -> " + mensagem.getMensagemDDA());
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
     * Metodo responsavel por popular o objeto desconto 1 antiga entidade mensagemDDABoletoDesconto
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoDesconto1(MensagemDDABoleto mensagem, DDA0101ComplexType dda) throws ComumException {
        dda.getGrupoDDA0101DesctTit()
                .add(new GrupoDDA0101DesctTitComplexType(
                        ObjectUtil.isNull(mensagem.getDataDesconto1()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataDesconto1()),
                        mensagem.getCodTipoDesconto1(), mensagem.getValorPercentualDesconto1()));
    }

    /**
     * Metodo responsavel por popular o objeto desconto 2 antiga entidade mensagemDDABoletoDesconto
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoDesconto2(MensagemDDABoleto mensagem, DDA0101ComplexType dda) throws ComumException {
        if (!mensagem.getCodTipoDesconto2().equals(TipoDesconto.ISENTO)) {
            dda.getGrupoDDA0101DesctTit()
                    .add(new GrupoDDA0101DesctTitComplexType(
                            ObjectUtil.isNull(mensagem.getDataDesconto2()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataDesconto2()),
                            mensagem.getCodTipoDesconto2(), mensagem.getValorPercentualDesconto2()));
        }
    }

    /**
     * Metodo responsavel por popular o objeto desconto 3 antiga entidade mensagemDDABoletoDesconto
     * 
     * @param mensagem
     * @param dda
     * @throws ComumException void
     * 
     */
    private void setMensagemDDABoletoDesconto3(MensagemDDABoleto mensagem, DDA0101ComplexType dda) throws ComumException {
        if (!mensagem.getCodTipoDesconto3().equals(TipoDesconto.ISENTO)) {
            dda.getGrupoDDA0101DesctTit().add(
                new GrupoDDA0101DesctTitComplexType(
                        ObjectUtil.isNull(mensagem.getDataDesconto3()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataDesconto3()),
                        mensagem.getCodTipoDesconto3(), mensagem.getValorPercentualDesconto3()));
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
    private void setMensagemDDABoletoNotaFiscal(MensagemDDABoleto mensagem, DDA0101ComplexType dda) throws ComumException {
        for (MensagemDDABoletoNotaFiscal mensagemDDANF : mensagem.getListaMensagemDDABoletoNotaFiscal()) {
            dda.getGrupoDDA0101NotaFis().add(
                    new GrupoDDA0101NotaFisComplexType(mensagemDDANF.getNumNotaFiscal(), DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDANF.getDataEmissaoNotaFiscal()),
                            mensagemDDANF.getValorNotaFiscal()));
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
    private void setMensagemDDABoletoGrupoCalculo(MensagemDDABoleto mensagem, DDA0101ComplexType dda) throws ComumException {
        for (MensagemDDABoletoGrupoCalculo mensagemDDAGC : mensagem.getListaMensagemDDABoletoGrupoCalculo()) {
            dda.getGrupoDDA0101Calc().add(
                    new GrupoDDA0101CalcComplexType(mensagemDDAGC.getValorCalculadoJuros(), mensagemDDAGC.getValorCalculadoMulta(), mensagemDDAGC.getValorCalculadoDesconto(),
                            mensagemDDAGC.getValorTotalCobrado(), DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDAGC.getDataValidadeCalculo())));
        }
    }

    /**
     * Método responsável por popular o campo TextoInfo
     * 
     * @param mensagem
     * @param dda void
     * 
     */
    private void setMensagemDDABoletoTextoInfo(MensagemDDABoleto mensagem, DDA0101ComplexType dda) {
        for (MensagemDDABoletoTextoInfo mensagemDDABoletoTextoInfo : mensagem.getListaMensagemDDABoletoTextoInfo()) {
            dda.getTxtInfBenfcrio().add(mensagemDDABoletoTextoInfo.getDescTextoInformativo());
        }
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
     * @return the BoletoCipDao
     */
    public BoletoCipDao getDao() {
        return dao;
    }
}
