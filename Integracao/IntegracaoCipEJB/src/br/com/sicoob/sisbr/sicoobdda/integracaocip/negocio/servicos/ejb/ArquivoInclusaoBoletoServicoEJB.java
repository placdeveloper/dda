package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoNotaFiscal;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoTextoInfo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaSacadorAvalistaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoInclusaoBoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.ADDA101ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101CalcComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101DesctTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101JurosTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101MultaTitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101NotaFisComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.SISARQComplexType;

/**
 * ArquivoInclusaoBoletoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoInclusaoBoletoServicoLocal.class })
public class ArquivoInclusaoBoletoServicoEJB extends IntegracaoCipServicoEJB implements ArquivoInclusaoBoletoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoIncluirBoletoDao incluirBoletoDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        List<MensagemDDABoleto> lista = dao.listarMensagemDDABoletoLogEnvioArquivo(idLogEnvioArquivoDDA);
        if (lista.size() > Constantes.QTD_ARQUIVOS_INSERIDOS_BOLETO_POR_VEZ) {
            return getListaADDA101(lista);
        }
        return new SISARQComplexType(getADDA101(lista));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoInclusaoBoletoServico#processarRetornoMensagemDDA(long, long, long)
     */
    public void processarRetornoMensagemDDA(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
        getLogger().debug("###### Registrando as mensagens ADDA101RET...");
        incluirBoletoDao.incluirADDA101RET(idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal);
        getLogger().debug("###### Mensagens ADDA101RET registradas.");
    }

    /**
     * Método responsável por montar o ADDA001
     * 
     * @param listaMensagemDDABoleto
     * @return
     * @throws ComumException
     * 
     */
    private ADDA101ComplexType getADDA101(List<MensagemDDABoleto> listaMensagemDDABoleto) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDABoleto.size());

        ADDA101ComplexType adda101 = new ADDA101ComplexType();
        for (MensagemDDABoleto mensagemDDABoleto : listaMensagemDDABoleto) {
            adda101.getGrupoADDA101Tit().add(getGrupoADDA101(mensagemDDABoleto));
        }

        return adda101;
    }

    /**
     * Método responsável por montar o ADDA001
     * 
     * @param listaMensagemDDABoleto
     * @return
     * @throws ComumException
     * 
     */
    private List<ADDA101ComplexType> getListaADDA101(List<MensagemDDABoleto> listaMensagemDDABoleto) throws ComumException {
        getLogger().debug("###### Qtde de Arquivos Enviados -> " + listaMensagemDDABoleto.size());

        List<ADDA101ComplexType> listaADDA101ComplexType = new ArrayList<ADDA101ComplexType>();
        ADDA101ComplexType adda101 = new ADDA101ComplexType();
        int cont = 0;
        int qtd_arquivos = Constantes.QTD_ARQUIVOS_INSERIDOS_BOLETO_POR_VEZ;
        for (MensagemDDABoleto mensagemDDABoleto : listaMensagemDDABoleto) {
            cont += 1;
            adda101.getGrupoADDA101Tit().add(getGrupoADDA101(mensagemDDABoleto));
            if ((cont) == qtd_arquivos) {
                qtd_arquivos += Constantes.QTD_ARQUIVOS_INSERIDOS_BOLETO_POR_VEZ;
                listaADDA101ComplexType.add(adda101);
                adda101 = new ADDA101ComplexType();
            } else if ((cont) == listaMensagemDDABoleto.size()) {
                listaADDA101ComplexType.add(adda101);
            }

        }
        return listaADDA101ComplexType;
    }

    /**
     * Método responsável por popular o GrupoADDA101
     * 
     * @param mensagemDDABoleto
     * @return
     * @throws ComumException GrupoADDA001PagdrComplexType
     * 
     */
    private GrupoADDA101TitComplexType getGrupoADDA101(MensagemDDABoleto mensagemDDABoleto) throws ComumException {
        GrupoADDA101TitComplexType boleto = new GrupoADDA101TitComplexType();
        boleto.setNumCtrlReqPart(mensagemDDABoleto.getId().toString());
        boleto.setISPBPartDestinatarioAdmtd(Constantes.ISPB_BANCOOB);
        boleto.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);

        boleto.setCodPartDestinatario(Constantes.BANCOOB);

        boleto.setTpPessoaBenfcrioOr(mensagemDDABoleto.getCodTipoPessoaBeneficiario());
        boleto.setCNPJCPFBenfcrioOr(new BigInteger(mensagemDDABoleto.getNumCpfCnpjBeneficiario()));
        boleto.setNomRzSocBenfcrioOr(mensagemDDABoleto.getNomeBeneficiario());
        boleto.setNomFantsBenfcrioOr(mensagemDDABoleto.getNomeFantasiaBeneficiario());
        boleto.setLogradBenfcrioOr(mensagemDDABoleto.getDescLogradouroBeneficiario());
        boleto.setCidBenfcrioOr(mensagemDDABoleto.getDescCidadeBeneficiario());
        boleto.setUFBenfcrioOr(mensagemDDABoleto.getSiglaUfBeneficiario());
        // Definimos que quando nao tiver CEP pagador iremos colocar inserir um CEP padrao.
        boleto.setCEPBenfcrioOr(ObjectUtil.isEmpty(mensagemDDABoleto.getNumCepBeneficiario()) || mensagemDDABoleto.getNumCepBeneficiario().trim().length() < Constantes.NUM_CEP ? null
                : new BigInteger(mensagemDDABoleto.getNumCepBeneficiario()));

        boleto.setTpPessoaBenfcrioFinl(mensagemDDABoleto.getCodTipoPessoaBeneficiarioFinal());
        boleto.setCNPJCPFBenfcrioFinl(ObjectUtil.isNull(mensagemDDABoleto.getNumCpfCnpjBeneficiarioFinal()) ? null : new BigInteger(mensagemDDABoleto
                .getNumCpfCnpjBeneficiarioFinal().trim()));
        boleto.setNomRzSocBenfcrioFinl(mensagemDDABoleto.getNomeBeneficiarioFinal());
        boleto.setNomFantsBenfcrioFinl(mensagemDDABoleto.getNomeFantasiaBeneficiarioFinal());

        boleto.setTpPessoaPagdr(mensagemDDABoleto.getCodTipoPessoaPagador());
        boleto.setCNPJCPFPagdr(new BigInteger(mensagemDDABoleto.getNumCpfCnpjPagador().trim()));
        boleto.setNomRzSocPagdr(mensagemDDABoleto.getNomePagador());
        boleto.setNomFantsPagdr(mensagemDDABoleto.getNomeFantasiaPagador());
        boleto.setLogradPagdr(mensagemDDABoleto.getDescLogradouroPagador());
        boleto.setCidPagdr(mensagemDDABoleto.getDescCidadePagador());
        boleto.setUFPagdr(mensagemDDABoleto.getSiglaUfPagador());
        // Definimos que quando nao tiver CEP pagador iremos colocar inserir um CEP padrao.
        boleto.setCEPPagdr(ObjectUtil.isEmpty(mensagemDDABoleto.getNumCepPagador()) || mensagemDDABoleto.getNumCepPagador().trim().length() < Constantes.NUM_CEP ? null
                : new BigInteger(mensagemDDABoleto.getNumCepPagador()));

        boleto.setTpIdentcSacdrAvalst(ObjectUtil.isNull(mensagemDDABoleto.getCodTipoPessoaDDAAvalista()) ? new BigInteger(TipoPessoaSacadorAvalistaEnum.ISENTO_NAO_INFORMADO
                .getCodDominio()) : new BigInteger(mensagemDDABoleto.getCodTipoPessoaDDAAvalista()));
        boleto.setIdentcSacdrAvalst(mensagemDDABoleto.getNumCpfCnpjAvalista());
        boleto.setNomRzSocSacdrAvalst(mensagemDDABoleto.getNomeAvalista());

        boleto.setCodCartTit(mensagemDDABoleto.getIdCarteira().toString());
        boleto.setCodMoedaCNAB(mensagemDDABoleto.getCodMoeda());
        boleto.setIdentdNossoNum(mensagemDDABoleto.getNumNossoNumero());
        boleto.setNumCodBarras(mensagemDDABoleto.getNumCodigoBarra());
        boleto.setNumLinhaDigtvl(mensagemDDABoleto.getNumLinhaDigitavel());
        boleto.setDtVencTit(ObjectUtil.isNull(mensagemDDABoleto.getDataVencimento()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataVencimento()));
        boleto.setVlrTit(mensagemDDABoleto.getValorBoleto());

        boleto.setNumDocTit(ObjectUtil.isEmpty(mensagemDDABoleto.getNumDocumento()) ? null : mensagemDDABoleto.getNumDocumento().trim());
        boleto.setCodEspTit(mensagemDDABoleto.getIdEspecieDocumento().toString());
        boleto.setDtEmsTit(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataEmissao()));
        boleto.setQtdDiaPrott(ObjectUtil.isEmpty(mensagemDDABoleto.getNumDiasProtesto()) ? null : new BigInteger(mensagemDDABoleto.getNumDiasProtesto().toString()));
        boleto.setDtLimPgtoTit(ObjectUtil.isNull(mensagemDDABoleto.getDataLimitePagamento()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto
                .getDataLimitePagamento()));
        boleto.setTpPgtoTit(new BigInteger(mensagemDDABoleto.getCodTipoPagamento().toString()));
        boleto.setNumParcl(ObjectUtil.isNull(mensagemDDABoleto.getNumParcela()) ? null : new BigInteger(mensagemDDABoleto.getNumParcela().toString()));
        boleto.setQtdTotParcl(ObjectUtil.isNull(mensagemDDABoleto.getQtdTotalParcela()) ? null : new BigInteger(mensagemDDABoleto.getQtdTotalParcela().toString()));

        boleto.setIndrTitNegcd(mensagemDDABoleto.getBolTituloNegociado());
        boleto.setIndrBloqPgto(mensagemDDABoleto.getBolBloqueioPagamento());
        boleto.setIndrPgtoParcl(mensagemDDABoleto.getBolPagamentoParcial());

        if (mensagemDDABoleto.isBolPagamentoParcial()) {
            boleto.setQtdPgtoParcl(ObjectUtil.isNull(mensagemDDABoleto.getQtdPagamentoParcial()) ? null : new BigInteger(mensagemDDABoleto.getQtdPagamentoParcial().toString()));
        }
        boleto.setVlrAbattTit(mensagemDDABoleto.getValorAbatimento());

        getMensagemDDABoletoJuros(mensagemDDABoleto, boleto);

        getMensagagemDDABoletoMulta(mensagemDDABoleto, boleto);

        getMensagemDDABoletoDesconto(mensagemDDABoleto, boleto);

        getMensagemDDABoletoNotaFiscal(mensagemDDABoleto, boleto);

        boleto.setTpVlrPercMinTit(mensagemDDABoleto.getCodIndicadorValorMinimo());
        boleto.setVlrPercMinTit(mensagemDDABoleto.getValorMinimo());
        boleto.setTpVlrPercMaxTit(mensagemDDABoleto.getCodIndicadorValorMaximo());
        boleto.setVlrPercMaxTit(mensagemDDABoleto.getValorMaximo());
        boleto.setTpModlCalc(mensagemDDABoleto.getCodTipoModeloCalculo());
        boleto.setTpAutcRecbtVlrDivgte(mensagemDDABoleto.getCodAutorizacaoValorDivergente());

        getMensagemDDABoletoGrupoCalculo(mensagemDDABoleto, boleto);

        getMensagemDDABoletoTextoInfo(mensagemDDABoleto, boleto);

        return boleto;
    }

    /**
     * Método responsável por popular a MensagemDDABoletoJuros agora atributos na MensagemDDABoleto
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void getMensagemDDABoletoJuros(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoJuros())) {
                GrupoADDA101JurosTitComplexType grupoADDA101Juros = new GrupoADDA101JurosTitComplexType();
            grupoADDA101Juros
                    .setDtJurosTit(ObjectUtil.isNull(mensagemDDABoleto.getDataJuros()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataJuros()));
            grupoADDA101Juros.setVlrPercJurosTit(mensagemDDABoleto.getValorPercentualJuros());
                // TODO george.santos jorge ira mudar no banco para String
            grupoADDA101Juros.setCodJurosTit(mensagemDDABoleto.getCodTipoJuros().toString());
                boleto.setGrupoADDA101JurosTit(grupoADDA101Juros);
        }
    }

    /**
     * Método responsável por popular a MensagemDDABoletoMulta agora atributos na MensagemDDABoleto
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void getMensagagemDDABoletoMulta(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoMulta())) {
                GrupoADDA101MultaTitComplexType grupoADDA101Multa = new GrupoADDA101MultaTitComplexType();
                grupoADDA101Multa.setDtMultaTit(ObjectUtil.isNull(mensagemDDABoleto.getDataMulta()) ? null : DataCipUtil
                        .dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataMulta()));
                // TODO george.santos jorge ira mudar no banco para String
                grupoADDA101Multa.setCodMultaTit(mensagemDDABoleto.getCodTipoMulta().toString());
                grupoADDA101Multa.setVlrPercMultaTit(mensagemDDABoleto.getValorPercentualMulta());
                boleto.setGrupoADDA101MultaTit(grupoADDA101Multa);
        }
    }

    /**
     * Metodo responsavel pelo Desconto
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void getMensagemDDABoletoDesconto(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isNull(mensagemDDABoleto)) {

            int quantidade = obterQuantidadeDescontosBoletoDDA(mensagemDDABoleto);

            // De acordo com a quantidade realiza as operacoes necessarias
            switch (quantidade) {
            case 3:
                getMensagemDDABoletoDesconto1(mensagemDDABoleto, boleto);
                getMensagemDDABoletoDesconto2(mensagemDDABoleto, boleto);
                getMensagemDDABoletoDesconto3(mensagemDDABoleto, boleto);
                break;
            case 2:
                getMensagemDDABoletoDesconto1(mensagemDDABoleto, boleto);
                getMensagemDDABoletoDesconto2(mensagemDDABoleto, boleto);
                break;
            case 1:
                getMensagemDDABoletoDesconto1(mensagemDDABoleto, boleto);
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
     * Metodo responsavel pelo Desconto 1
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void getMensagemDDABoletoDesconto1(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto1())) {
                GrupoADDA101DesctTitComplexType grupoADDA101Desconto = new GrupoADDA101DesctTitComplexType();
            grupoADDA101Desconto.setDtDesctTit(
                    ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto1()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataDesconto1()));
            grupoADDA101Desconto.setCodDesctTit(mensagemDDABoleto.getCodTipoDesconto1());
            grupoADDA101Desconto.setVlrPercDesctTit(mensagemDDABoleto.getValorPercentualDesconto1());
                boleto.getGrupoADDA101DesctTit().add(grupoADDA101Desconto);
        }
    }

    /**
     * Metodo responsavel pelo Desconto 2
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void getMensagemDDABoletoDesconto2(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto2()) && !mensagemDDABoleto.getCodTipoDesconto2().equals(TipoDesconto.ISENTO)) {
                GrupoADDA101DesctTitComplexType grupoADDA101Desconto = new GrupoADDA101DesctTitComplexType();
            grupoADDA101Desconto.setDtDesctTit(
                    ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto2()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataDesconto2()));
            grupoADDA101Desconto.setCodDesctTit(mensagemDDABoleto.getCodTipoDesconto2());
            grupoADDA101Desconto.setVlrPercDesctTit(mensagemDDABoleto.getValorPercentualDesconto2());
                boleto.getGrupoADDA101DesctTit().add(grupoADDA101Desconto);
        }
    }

    /**
     * Metodo responsavel pelo Desconto 3
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void getMensagemDDABoletoDesconto3(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getCodTipoDesconto3()) && !mensagemDDABoleto.getCodTipoDesconto3().equals(TipoDesconto.ISENTO)) {
                GrupoADDA101DesctTitComplexType grupoADDA101Desconto = new GrupoADDA101DesctTitComplexType();
            grupoADDA101Desconto.setDtDesctTit(
                    ObjectUtil.isNull(mensagemDDABoleto.getDataDesconto3()) ? null : DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoleto.getDataDesconto3()));
            grupoADDA101Desconto.setCodDesctTit(mensagemDDABoleto.getCodTipoDesconto3());
            grupoADDA101Desconto.setVlrPercDesctTit(mensagemDDABoleto.getValorPercentualDesconto3());
                boleto.getGrupoADDA101DesctTit().add(grupoADDA101Desconto);
        }
    }

    /**
     * Método responsável por popular a MensagemDDABoletoNotaFiscal
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void getMensagemDDABoletoNotaFiscal(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getListaMensagemDDABoletoNotaFiscal())) {
            for (MensagemDDABoletoNotaFiscal mensagemDDABoletoNotaFiscal : mensagemDDABoleto.getListaMensagemDDABoletoNotaFiscal()) {
                GrupoADDA101NotaFisComplexType grupoADDA101NotaFiscal = new GrupoADDA101NotaFisComplexType();
                grupoADDA101NotaFiscal.setNumNotaFis(mensagemDDABoletoNotaFiscal.getNumNotaFiscal());
                grupoADDA101NotaFiscal.setDtEmsNotaFis(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoletoNotaFiscal.getDataEmissaoNotaFiscal()));
                grupoADDA101NotaFiscal.setVlrNotaFis(mensagemDDABoletoNotaFiscal.getValorNotaFiscal());
                boleto.getGrupoADDA101NotaFis().add(grupoADDA101NotaFiscal);
            }
        }
    }

    /**
     * Método responsável por popular a MensagemDDABoletoGrupoCalculo
     * 
     * @param mensagemDDABoleto
     * @param boleto
     * @throws ComumException void
     * 
     */
    private void getMensagemDDABoletoGrupoCalculo(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) throws ComumException {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getListaMensagemDDABoletoGrupoCalculo())) {
            for (MensagemDDABoletoGrupoCalculo mensagemDDABoletoGrupoCalculo : mensagemDDABoleto.getListaMensagemDDABoletoGrupoCalculo()) {
                GrupoADDA101CalcComplexType grupoADDA101Cal = new GrupoADDA101CalcComplexType();
                grupoADDA101Cal.setVlrCalcdJuros(mensagemDDABoletoGrupoCalculo.getValorCalculadoJuros());
                grupoADDA101Cal.setVlrCalcdMulta(mensagemDDABoletoGrupoCalculo.getValorCalculadoMulta());
                grupoADDA101Cal.setVlrCalcdDesct(mensagemDDABoletoGrupoCalculo.getValorCalculadoDesconto());
                grupoADDA101Cal.setVlrTotCobrar(mensagemDDABoletoGrupoCalculo.getValorTotalCobrado());
                grupoADDA101Cal.setDtValiddCalc(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagemDDABoletoGrupoCalculo.getDataValidadeCalculo()));
                boleto.getGrupoADDA101Calc().add(grupoADDA101Cal);
            }
        }
    }

    /**
     * Método responsável por popular a MensagemDDABoletoTextoInfo
     * 
     * @param mensagemDDABoleto
     * @param boleto void
     * 
     */
    private void getMensagemDDABoletoTextoInfo(MensagemDDABoleto mensagemDDABoleto, GrupoADDA101TitComplexType boleto) {
        if (!ObjectUtil.isEmpty(mensagemDDABoleto.getListaMensagemDDABoletoTextoInfo())) {
            for (MensagemDDABoletoTextoInfo mensagemDDABoletoTextoInfo : mensagemDDABoleto.getListaMensagemDDABoletoTextoInfo()) {
                boleto.getTxtInfBenfcrio().add(mensagemDDABoletoTextoInfo.getDescTextoInformativo());
            }
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
}
