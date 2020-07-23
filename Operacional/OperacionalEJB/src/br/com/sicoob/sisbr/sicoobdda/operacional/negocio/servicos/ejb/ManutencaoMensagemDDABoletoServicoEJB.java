package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.StringUtil;
import br.com.bancoob.validacao.ValidacaoCnpj;
import br.com.bancoob.validacao.ValidacaoCpf;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CarteiraDeParaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EspecieDocumentoDeParaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDescontoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoGrupoCalculoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CarteiraDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.EspecieDocumentoDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoPagamentoDeParaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.CpfCnpjUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoletoGrupoCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoPessoaDDAAvalista;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaSacadorAvalistaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.ManutencaoMensagemDDABoletoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ManutencaoMensagemDDABoletoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.tipos.DateTime;

/**
 * AlterarMensagemBoletoServicoEJB é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@Stateless
@Local(ManutencaoMensagemDDABoletoServicoLocal.class)
public class ManutencaoMensagemDDABoletoServicoEJB extends OperacionalCrudServicoEJB<MensagemDDABoleto> implements ManutencaoMensagemDDABoletoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @SuppressWarnings("unused")
    @PersistenceContext(unitName = "emSicoobDDA_sqlServer")
    private EntityManager emSQL;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDDADao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    @Dao(entityManager = "emSQL", fabrica = ComumDaoFactory.class)
    private ParametroLegadoDao parametroLegadoDao;

    @Dao(entityManager = "emSQL", fabrica = OperacionalDaoFactory.class)
    private BoletoLegadoDao boletoLegadoDao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private ManutencaoMensagemDDABoletoDao manutencaoMensagemDDABoletoDao;

    private MensagemDDADelegate mensagemDDADelegate;

    private SCIDelegate sciDelegate;
    private ADMDelegate admDelegate;

    private static final Integer VALOR_MAXIMO_SMALLINT = 32767;
    private static final Integer VALOR_MINIMO_SMALLINT = -32768;

    private static final int TAMANHO_MAX_NOME_FANTASIA = 75;
    private static final int TAMANHO_MAX_NOME = 50;
    private static final int TAMANHO_MAX_SEU_NUMERO = 10;

    private String caracteresRemover;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected ManutencaoMensagemDDABoletoDao getDAO() {
        return manutencaoMensagemDDABoletoDao;
    }

    /**
     * Método responsável por
     * 
     * @return BoletoCipDao
     * 
     */
    private BoletoCipDao getBoletoCipDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico#listarMensagemDDABoletoDtoPaginado(br.com.bancoob.negocio.dto.ConsultaDto)
     */
    public List<MensagemDDABoletoDto> listarMensagemDDABoletoDtoPaginado(MensagemDDABoletoFiltroDto filtro) throws ComumException {
        return getDAO().listarMensagemDDABoletoDtoPaginado(filtro);
    }

    /**
     * Método responsável por
     * 
     * @param id
     * @return MensagemDDABoleto
     * 
     */
    public MensagemDDABoleto obterMensagemDDABoletoPorId(Long id) {
        return getBoletoCipDao().obterMensagemDDABoleto(id);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico#listarCombos()
     */
    public MensagemDDABoletoFiltroDto listarCombos() {
        MensagemDDABoletoFiltroDto dto = new MensagemDDABoletoFiltroDto();
        for (CarteiraDeParaEnum enun : Arrays.asList(CarteiraDeParaEnum.values())) {
            CarteiraDeParaDto dtoCarteira = new CarteiraDeParaDto(enun.getDescricaoCarteira(), enun.getIdCarteiraCip());
            dto.getListaIdCarteira().add(dtoCarteira);
        }
        for (EspecieDocumentoDeParaEnum enun : Arrays.asList(EspecieDocumentoDeParaEnum.values())) {
            EspecieDocumentoDeParaDto dtoEspecieDocumento = new EspecieDocumentoDeParaDto(enun.getSiglaEspecieDocumentoCob(), enun.getIdEspecieDocumentoCip(),
                    enun.getSiglaEspecieDocumentoCob() + " - " + enun.getDescEspecieDocumentoCob());
            dto.getListaEspecieDocumento().add(dtoEspecieDocumento);
        }
        dto.getListaTipoModeloCalculo().addAll(getDAO().listarTipoModeloCalculo());
        dto.getListaTipoJuros().addAll(getDAO().listarTipoJuros());
        dto.getListaTipoDesconto().addAll(getDAO().listarTipoDesconto());
        dto.getListaTipoMulta().addAll(getDAO().listarTipoMulta());
        dto.getListaTipoModeloPagamento().addAll(getDAO().listarMeioPagamento());
        dto.getListaAutorizacaoValorDivergente().addAll(getDAO().listarAutorizacaoValorDivergente());
        dto.getListaTipoPessoaDDAAvalista().addAll(getDAO().listarTipoPessoaDDAAvalista());

        return dto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico#obterMensagemDtoPorId(java.lang.Long)
     */
    public MensagemDDABoletoDto obterMensagemDtoPorId(Long id) throws ComumNegocioException {
        MensagemDDABoletoDto mensagemddaBoletoRetorno = new MensagemDDABoletoDto();

        MensagemDDABoleto msg = obterMensagemDDABoletoPorId(id);
        if (!ObjectUtil.isNull(msg)) {
            mensagemddaBoletoRetorno.setIdMensagem(msg.getId());
            // DADOS BENEFICIÁRIO
            mensagemddaBoletoRetorno.setCodTipoPessoaBeneficiario(msg.getCodTipoPessoaBeneficiario());
            mensagemddaBoletoRetorno.setNumCnpjCpfBeneficiario(msg.getNumCpfCnpjBeneficiario());
            mensagemddaBoletoRetorno.setNomeBeneficiario(msg.getNomeBeneficiario());
            mensagemddaBoletoRetorno.setNomeFantasiaBeneficiario(msg.getNomeFantasiaBeneficiario());
            mensagemddaBoletoRetorno.setDescLogradouroBeneficiario(msg.getDescLogradouroBeneficiario());
            mensagemddaBoletoRetorno.setDescCidadeBeneficiario(msg.getDescCidadeBeneficiario());
            mensagemddaBoletoRetorno.setUfBeneficiario(msg.getSiglaUfBeneficiario());
            mensagemddaBoletoRetorno.setNumCepBeneficiario(msg.getNumCepBeneficiario());
            // DADOS BENEFICIÁRIO FINAL
            mensagemddaBoletoRetorno.setCodTipoPessoaBeneficiarioFinal(msg.getCodTipoPessoaBeneficiarioFinal());
            mensagemddaBoletoRetorno.setNumCnpjCpfBeneficiarioFinal(msg.getNumCpfCnpjBeneficiarioFinal());
            mensagemddaBoletoRetorno.setNomeBeneficiarioFinal(msg.getNomeBeneficiarioFinal());
            mensagemddaBoletoRetorno.setNomeFantasiaBeneficiarioFinal(msg.getNomeFantasiaBeneficiarioFinal());
            // DADOS PAGADOR
            mensagemddaBoletoRetorno.setCodTipoPessoaPagador(msg.getCodTipoPessoaPagador());
            mensagemddaBoletoRetorno.setNumCnpjCpfPagador(msg.getNumCpfCnpjPagador());
            mensagemddaBoletoRetorno.setNomePagador(msg.getNomePagador());
            mensagemddaBoletoRetorno.setNomeFantasiaPagador(msg.getNomeFantasiaPagador());
            mensagemddaBoletoRetorno.setDescLogradouroPagador(msg.getDescLogradouroPagador());
            mensagemddaBoletoRetorno.setDesCidadePagador(msg.getDescCidadePagador());
            mensagemddaBoletoRetorno.setUfPagador(msg.getSiglaUfPagador());
            mensagemddaBoletoRetorno.setNumCepPagador(msg.getNumCepPagador());
            // DADOS AVALISTA
            mensagemddaBoletoRetorno.setCodTipoPessoaAvalista(msg.getCodTipoPessoaDDAAvalista());
            mensagemddaBoletoRetorno.setNumCnpjCpfAvalista(msg.getNumCpfCnpjAvalista());
            mensagemddaBoletoRetorno.setNomeAvalista(msg.getNomeAvalista());
            // DADOS DO BOLETO
            mensagemddaBoletoRetorno.setIdCarteira(msg.getIdCarteira());
            mensagemddaBoletoRetorno.setCodMoeda(msg.getCodMoeda());
            mensagemddaBoletoRetorno.setIdOrgaoMoeda(msg.getIdOrgaoMoeda());
            mensagemddaBoletoRetorno.setNumNossoNumero(msg.getNumNossoNumero());
            mensagemddaBoletoRetorno.setNumCodigoDeBarras(msg.getNumCodigoBarra());
            mensagemddaBoletoRetorno.setNumLinhaDigitavel(msg.getNumLinhaDigitavel());
            mensagemddaBoletoRetorno.setDataVencimento(msg.getDataVencimento());
            mensagemddaBoletoRetorno.setValorDoBoleto(msg.getValorBoleto());
            mensagemddaBoletoRetorno.setDataEmissao(msg.getDataEmissao());
            mensagemddaBoletoRetorno.setNumDocumento(msg.getNumDocumento());
            mensagemddaBoletoRetorno.setDataLimitePgto(msg.getDataLimitePagamento());
            mensagemddaBoletoRetorno.setDiasDeProtesto(msg.getNumDiasProtesto());
            mensagemddaBoletoRetorno.setValorAbatimento(msg.getValorAbatimento());
            mensagemddaBoletoRetorno.setIndValorMinimo(msg.getCodIndicadorValorMinimo());
            mensagemddaBoletoRetorno.setValorMinimo(msg.getValorMinimo());
            mensagemddaBoletoRetorno.setIndValorMaximo(msg.getCodIndicadorValorMaximo());
            mensagemddaBoletoRetorno.setValorMaximo(msg.getValorMaximo());
            mensagemddaBoletoRetorno.setNumeroParcela(msg.getNumParcela());
            mensagemddaBoletoRetorno.setQtdTotalParcelas(msg.getQtdTotalParcela());
            mensagemddaBoletoRetorno.setBolTituloNegociado(msg.getBolTituloNegociado());
            mensagemddaBoletoRetorno.setBolBloqueioPagamento(msg.getBolBloqueioPagamento());
            mensagemddaBoletoRetorno.setBolPagamentoParcial(msg.getBolPagamentoParcial());
            mensagemddaBoletoRetorno.setQtdPagamentoParcial(msg.getQtdPagamentoParcial());
            mensagemddaBoletoRetorno.setTipoModeloDeCalculo(msg.getCodTipoModeloCalculo());
            mensagemddaBoletoRetorno.setCodAutorizacaoValorDivergente(msg.getCodAutorizacaoValorDivergente());
            mensagemddaBoletoRetorno.setIdEspecieDocumento(msg.getIdEspecieDocumento());
            mensagemddaBoletoRetorno.setCodTipoPagamento(msg.getCodTipoPagamento());
            mensagemddaBoletoRetorno.setNumRefAtualCadBoleto(msg.getNumRefAtualCadBoleto());
            mensagemddaBoletoRetorno.setNumSeqAtualCadBoleto(msg.getNumSeqAtualCadBoleto());

            if (ObjectUtil.isNull(mensagemddaBoletoRetorno.getListDadosDesconto())) {
                mensagemddaBoletoRetorno.setListDadosDesconto(new ArrayList<MensagemDDABoletoDescontoDto>());
            }
            // DADOS DESCONTO 1
            if (!ObjectUtil.isEmpty(msg.getValorPercentualDesconto1()) && !ObjectUtil.isEmpty(msg.getCodTipoDesconto1())) {
                MensagemDDABoletoDescontoDto dto1 = new MensagemDDABoletoDescontoDto(msg.getCodTipoDesconto1(), msg.getDataDesconto1(), msg.getValorPercentualDesconto1());
                mensagemddaBoletoRetorno.getListDadosDesconto().add(dto1);
            }

            // DADOS DESCONTO 2
            if (!ObjectUtil.isEmpty(msg.getValorPercentualDesconto2()) && !ObjectUtil.isEmpty(msg.getCodTipoDesconto2())) {
                MensagemDDABoletoDescontoDto dto2 = new MensagemDDABoletoDescontoDto(msg.getCodTipoDesconto2(), msg.getDataDesconto2(), msg.getValorPercentualDesconto2());
                mensagemddaBoletoRetorno.getListDadosDesconto().add(dto2);
            }

            // DADOS DESCONTO 3
            if (!ObjectUtil.isEmpty(msg.getValorPercentualDesconto3()) && !ObjectUtil.isEmpty(msg.getCodTipoDesconto3())) {
                MensagemDDABoletoDescontoDto dto3 = new MensagemDDABoletoDescontoDto(msg.getCodTipoDesconto3(), msg.getDataDesconto3(), msg.getValorPercentualDesconto3());
                mensagemddaBoletoRetorno.getListDadosDesconto().add(dto3);
            }

            // DADOS DE JUROS DE MORA
            if (!ObjectUtil.isNull(msg.getCodTipoJuros()) && !ObjectUtil.isNull(msg.getDataJuros()) && !ObjectUtil.isNull(msg.getValorPercentualJuros())) {
                mensagemddaBoletoRetorno.setCodTipoJuros(msg.getCodTipoJuros());
                mensagemddaBoletoRetorno.setDataJuros(msg.getDataJuros());
                mensagemddaBoletoRetorno.setValorJuros(msg.getValorPercentualJuros());
            }
            // DADOS DE MULTA
            if (!ObjectUtil.isNull(msg.getCodTipoMulta()) && !ObjectUtil.isNull(msg.getDataMulta()) && !ObjectUtil.isNull(msg.getValorPercentualMulta())) {
                mensagemddaBoletoRetorno.setCodTipoMulta(msg.getCodTipoMulta());
                mensagemddaBoletoRetorno.setDataMulta(msg.getDataMulta());
                mensagemddaBoletoRetorno.setValorMulta(msg.getValorPercentualMulta());
            }

            // DADOS GRUPO DE CÁLCULO
            if (!ObjectUtil.isEmpty(msg.getListaMensagemDDABoletoGrupoCalculo())) {
                for (MensagemDDABoletoGrupoCalculo grp : msg.getListaMensagemDDABoletoGrupoCalculo()) {
                    MensagemDDABoletoGrupoCalculoDto dto = new MensagemDDABoletoGrupoCalculoDto(grp.getId(), grp.getValorCalculadoJuros(), grp.getValorCalculadoMulta(),
                            grp.getValorCalculadoDesconto(), grp.getValorTotalCobrado(), grp.getDataValidadeCalculo());
                    if (ObjectUtil.isNull(mensagemddaBoletoRetorno.getListDadosGrupoCalculo())) {
                        mensagemddaBoletoRetorno.setListDadosGrupoCalculo(new ArrayList<MensagemDDABoletoGrupoCalculoDto>());
                    }
                    mensagemddaBoletoRetorno.getListDadosGrupoCalculo().add(dto);
                }
            }
        }
        return mensagemddaBoletoRetorno;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico#inserirMensagemDDABoleto(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto)
     */
    public void inserirMensagemDDABoletoGeraBoleto(BoletoLegadoDto boletoLegadoDto) throws ComumException, ComumNegocioException {
        Boolean bolEnviaOnlineMensagemBoleto = parametroDAO.obterValorBoolean(ParametroDDA.HABILITAR_ENVIO_MENSAGEM_GERA_BOLETO_ONLINE, Constantes.ID_SICOOB);
        inserirMensagemDDABoleto(boletoLegadoDto, bolEnviaOnlineMensagemBoleto, Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico#inserirMensagemDDABoletoSicoobNetEmpresarial(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto)
     */
    public void inserirMensagemDDABoletoSicoobNetEmpresarial(BoletoLegadoDto boletoLegadoDto) throws ComumException, ComumNegocioException {
        Boolean bolEnviaOnlineMensagemBoleto = parametroDAO.obterValorBoolean(ParametroDDA.HABILITAR_ENVIO_MENSAGEM_SICOOB_NET_EMPRESARIAL_ONLINE, Constantes.ID_SICOOB);
        inserirMensagemDDABoleto(boletoLegadoDto, bolEnviaOnlineMensagemBoleto, Boolean.FALSE);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico#inserirMensagemDDABoleto(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto)
     */
    private void inserirMensagemDDABoleto(BoletoLegadoDto boletoLegadoDto, Boolean bolEnviaOnlineMensagemBoleto, Boolean bolEnviaPeloGeraBoleto)
            throws ComumException, ComumNegocioException {
        debug("### Inserindo MensagemDDABoleto...");
        debug("Parâmetro - bolEnviaOnlineMensagemBoleto: " + bolEnviaOnlineMensagemBoleto);
        debug("Parâmetro - bolEnviaPeloGeraBoleto: " + bolEnviaPeloGeraBoleto);

        boolean enviarMensagem = boletoLegadoDao.incluirTituloDDA(boletoLegadoDto);
        debug("Enviar a mensagem: " + enviarMensagem);

        if (!enviarMensagem) {
            debug("Mensagem não será enviada");
            return;
        }

        Date dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0101);
        debug("Data de referência: " + dataReferencia);

        InstituicaoDto instituicaoDto = getSCIDelegate().obterInstituicaoPorCooperativaCache(boletoLegadoDto.getNumCooperativa());

        MensagemDDABoleto msgBoleto = popularMensagemDDABoleto(boletoLegadoDto, instituicaoDto.getIdInstituicao(), bolEnviaPeloGeraBoleto);

        int prioridade = bolEnviaOnlineMensagemBoleto ? 0 : 10;
        String tipoMensagem = TipoMensagemDDA.DDA0101;

        String tipoArquivo = TipoMensagemDDA.ADDA101;

        debug("Tipo de mensagem: " + tipoMensagem + ", prioridade: " + prioridade);

        if (!ObjectUtil.isNull(dataReferencia)) {
            debug("Parâmetro - dataReferencia: " + dataReferencia);
            debug("Parâmetro - data Atual (new Date): " + DateUtil.obterDataSemHora(new Date()));
            if (DateUtil.igualA(dataReferencia, DateUtil.obterDataSemHora(new Date()))) {
                getMensagemDDADelegate().incluir(msgBoleto, tipoMensagem, new DateTimeDB(dataReferencia.getTime()),
                        prioridade, getUsuarioLogado(), instituicaoDto.getIdInstituicao(), boletoLegadoDto.getNumCodigoCanal().shortValue());
            } else {
                getMensagemDDADelegate().incluir(msgBoleto, tipoArquivo, new DateTimeDB(dataReferencia.getTime()), 10, getUsuarioLogado(), instituicaoDto.getIdInstituicao(),
                        boletoLegadoDto.getNumCodigoCanal().shortValue());
            }
        } else {
            // nao tem data de referencia, sera enviado por arquivos (ADDA101) com prioridade 10 e new Date da data de movimento
            getMensagemDDADelegate().incluir(msgBoleto, tipoArquivo, new DateTimeDB(), 10, getUsuarioLogado(), instituicaoDto.getIdInstituicao(),
                    boletoLegadoDto.getNumCodigoCanal().shortValue());
        }
    }

    /**
     * Método responsável por popular o objeto
     * 
     * @param boletoLegadoDto
     * @param idInstituicao
     * @param bolEnviaPeloGeraBoleto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private MensagemDDABoleto popularMensagemDDABoleto(BoletoLegadoDto boletoLegadoDto, Integer idInstituicao, Boolean bolEnviaPeloGeraBoleto)
            throws ComumException, ComumNegocioException {
        debug("### Populando MensagemDDABoleto...");
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        debug("Parâmetro - bolEnviaPeloGeraBoleto: " + bolEnviaPeloGeraBoleto);

        preencherParametroCaracteresRemover();

        MensagemDDABoleto msgBoleto = new MensagemDDABoleto();

        // beneficiario
        setBeneficiarioBoleto(msgBoleto, boletoLegadoDto);
        // pagador
        setPagadorBoleto(msgBoleto, boletoLegadoDto);

        // SacadorAvalista
        if (!ObjectUtil.isEmpty(boletoLegadoDto.getNumCpfCnpjAvalista()) && !ObjectUtil.isEmpty(boletoLegadoDto.getDescAvalista())) {
            setSacadorAvalistaBoleto(msgBoleto, boletoLegadoDto);
        } else {
            msgBoleto.setCodTipoPessoaDDAAvalista(TipoPessoaDDAAvalista.ISENTO_NAO_INFORMADO);
        }
        // Boleto
        msgBoleto.setNumCodigoBarra(boletoLegadoDto.getNumCodigoBarras());
        msgBoleto.setNumCodBarrasCampoLivre(
                boletoLegadoDto.getNumCodigoBarras() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(boletoLegadoDto.getNumCodigoBarras()) : null);
        msgBoleto.setNumLinhaDigitavel(boletoLegadoDto.getNumLinhaDigitavel());

        if (!ObjectUtil.isEmpty(boletoLegadoDto.getNossoNumeroString())) {
            msgBoleto.setNumNossoNumero(boletoLegadoDto.getNossoNumeroString());
        }
        msgBoleto.setNumDocumento(truncar(removerCaracteresEspeciais(boletoLegadoDto.getDescSeuNumero()), TAMANHO_MAX_SEU_NUMERO));
        msgBoleto.setDataEmissao(boletoLegadoDto.getDataEmissaoDocumento());
        msgBoleto.setDataVencimento(boletoLegadoDto.getDataVencimentoTitulo());

        definirDataLimitePagamento(boletoLegadoDto, idInstituicao, msgBoleto);

        msgBoleto.setIdEspecieDocumento(EspecieDocumentoDeParaEnum.getIdEspecieDocumentoCip(boletoLegadoDto.getCodEspDocumento()));
        msgBoleto.setIdOrgaoMoeda(1);
        msgBoleto.setCodMoeda("09");

        boolean isEspecieDocumentoCCouBDP = isEspecieDocumentoCCouBDP(boletoLegadoDto);
        debug("Espécie de documento CC ou BDP: " + isEspecieDocumentoCCouBDP);

        if (!ObjectUtil.isNull(boletoLegadoDto.getCodEspDocumento())) {
            if (boletoLegadoDto.getCodEspDocumento().equals("BDP")) {
                msgBoleto.setQtdPagamentoParcial(1);
                msgBoleto.setQtdTotalParcela(1);
            } else if (boletoLegadoDto.getCodEspDocumento().equals("CC")) {
                msgBoleto.setQtdPagamentoParcial(99);
            } else {
                msgBoleto.setQtdPagamentoParcial(null);
            }
        }

        msgBoleto.setValorAbatimento(boletoLegadoDto.getValorAbatimento());
        msgBoleto.setValorBoleto(boletoLegadoDto.getValor());

        msgBoleto.setBolBloqueioPagamento(!ObjectUtil.isNull(boletoLegadoDto.getCodSituacao()) && boletoLegadoDto.getCodSituacao() == 27 ? Boolean.TRUE : Boolean.FALSE);

        msgBoleto.setBolPagamentoParcial(isEspecieDocumentoCCouBDP ? Boolean.TRUE : Boolean.FALSE);

        Integer codCarteira = boletoLegadoDao.obterCodCarteiraPelaModalidade(boletoLegadoDto.getNumCooperativa(), boletoLegadoDto.getIdProduto(),
                boletoLegadoDto.getIdModalidade());
        msgBoleto.setBolTituloNegociado(!ObjectUtil.isNull(codCarteira) && (codCarteira == 2 || codCarteira == 3 || codCarteira == 4) ? Boolean.TRUE : Boolean.FALSE);
        msgBoleto.setIdCarteira(CarteiraDeParaEnum.getIdCarteiraCip(codCarteira));

        msgBoleto.setCodAutorizacaoValorDivergente(
                isEspecieDocumentoCCouBDP ? AutorizacaoValorDivergente.QUALQUER_VALOR : AutorizacaoValorDivergente.NAO_ACEITAR_PAGAMENTO_COM_VALOR_DIVERGENTE);

        Boolean bolHabilitarModeloCalculo01 = parametroDAO.obterValorBoolean(ParametroDDA.HABILITAR_MODELO_CALCULO_01, Constantes.ID_SICOOB);
        debug("Habilitar modelo de cálculo 01: " + bolHabilitarModeloCalculo01);

        msgBoleto.setCodTipoModeloCalculo(bolHabilitarModeloCalculo01 ? TipoModeloCalculo.INSTITUICAO_RECEBEDORA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS
                : TipoModeloCalculo.CIP_INSTITUICAO_RECEBEDORA_CALCULA_SALDO_PARCIAL_E_TITULOS_A_VENCER_E_VENCIDOS_NA_CONSULTA);

        // Se for nulo ou igual a ZERO então define null
        msgBoleto.setNumDiasProtesto(ObjectUtil.isEmpty(boletoLegadoDto.getQtdDiasProtesto()) ? null : boletoLegadoDto.getQtdDiasProtesto());
        msgBoleto.setCodTipoPagamento(TipoPagamentoDeParaEnum.getIdTipoPagamentoCip(boletoLegadoDto.getCodTipoVencimento()));

        definirJuros(msgBoleto, boletoLegadoDto, bolEnviaPeloGeraBoleto);
        definirMulta(msgBoleto, boletoLegadoDto, bolEnviaPeloGeraBoleto);
        definirDesconto(boletoLegadoDto, msgBoleto);

        msgBoleto.setCodIndicadorValorMaximo(isEspecieDocumentoCCouBDP ? "V" : null);
        msgBoleto.setValorMaximo(isEspecieDocumentoCCouBDP ? new BigDecimal("99999999.99") : null);

        msgBoleto.setCodIndicadorValorMinimo(isEspecieDocumentoCCouBDP ? "V" : null);
        msgBoleto.setValorMinimo(isEspecieDocumentoCCouBDP ? new BigDecimal("0.01") : null);

        return msgBoleto;
    }

    /**
     * Método responsável por obter o parâmetro
     * 
     * @throws ComumException
     */
    private void preencherParametroCaracteresRemover() throws ComumException {
        debug("Obtendo parâmetro dos caracteres a remover...");
        caracteresRemover = parametroDAO.obterValor(ParametroDDA.CARACTERES_REMOVER_INCLUSAO_BOLETO, Constantes.ID_SICOOB);
        debug("caracteresRemover: " + caracteresRemover);
    }

    /**
     * Condicoes para Data Limite de Pagamento
     * 
     * 1º Especie = BD ou Modalidade = 13 -> Proximo dia Util depois da data de vencimento
     * 
     * 2º Modalidade = 14 -> FIXO data 2030-12-31
     * 
     * 3º DataLimitePagamento != null -> Proximo dia util depois da data limite de pagamento
     * 
     * 4º DataVencimento + Parametro do Legado = 20 menos 1 dia
     * 
     * @param boletoLegadoDto
     * @param idInstituicao
     * @param msgBoleto
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    private void definirDataLimitePagamento(BoletoLegadoDto boletoLegadoDto, Integer idInstituicao, MensagemDDABoleto msgBoleto) throws ComumException, ComumNegocioException {
        DateTimeDB dataLimite = null;

        if (boletoLegadoDto.getCodEspDocumento().equals("BDP") || boletoLegadoDto.getIdModalidade() == 13) {
            if (getADMDelegate().verificarDiaUtil(msgBoleto.getDataVencimento())) {
                dataLimite = msgBoleto.getDataVencimento();
            } else {
                dataLimite = new DateTimeDB(getADMDelegate().obterProximoDiaUtil(idInstituicao, msgBoleto.getDataVencimento()).getTime());
            }
        } else if (boletoLegadoDto.getIdModalidade() == 14) {
            dataLimite = DateUtil.converterStringToDateTimeDB("2030-12-31", Constantes.FORMATO_DE_DATA_YYYY_MM_DD);
        } else if (!ObjectUtil.isNull(boletoLegadoDto.getDataLimitePagamento())) {
            // Se preencheu a data limite utiliza ela
            if (getADMDelegate().verificarDiaUtil(boletoLegadoDto.getDataLimitePagamento())) {
                dataLimite = boletoLegadoDto.getDataLimitePagamento();
            } else {
                dataLimite = new DateTimeDB(getADMDelegate().obterProximoDiaUtil(idInstituicao, boletoLegadoDto.getDataLimitePagamento()).getTime());
            }

        } else {
            dataLimite = new DateTimeDB(DateUtil.somarDias(boletoLegadoDto.getDataVencimentoTitulo(), obterNumeroParametro20Legado(boletoLegadoDto)).getTime());
        }

        debug("Data limite: " + dataLimite);
        msgBoleto.setDataLimitePagamento(dataLimite);
    }

    /**
     * Método responsável por buscar no Legado o parametro 20 - Menos 1 dia
     * 
     * @param boletoLegadoDto
     * @return
     * @throws ComumException
     * @throws ComumNegocioException int
     * 
     */
    private int obterNumeroParametro20Legado(BoletoLegadoDto boletoLegadoDto) throws ComumException, ComumNegocioException {
        return Integer.valueOf(parametroLegadoDao.obterValor(Constantes.PARAMETRO_LEGADO_PRAZO_DIAS_BAIXA_AUTOMATICA_TITULO_VENCIDO, boletoLegadoDto.getNumCooperativa())) - 1;
    }

    /**
     * Método responsável por verificar se a especie documento e CC - Cartao de Credito ou BDP - Boleto Proposta
     * 
     * @param boletoLegadoDto
     * @return boolean
     * 
     */
    private boolean isEspecieDocumentoCCouBDP(BoletoLegadoDto boletoLegadoDto) {
        return !ObjectUtil.isNull(boletoLegadoDto.getCodEspDocumento())
                && (boletoLegadoDto.getCodEspDocumento().equals("CC") || boletoLegadoDto.getCodEspDocumento().equals("BDP"));
    }

    /**
     * Método responsável por definir os juros
     * 
     * @param mensagemDDABoleto
     * @param boletoLegadoDto
     * @param bolEnviaPeloGeraBoleto
     */
    private void definirJuros(MensagemDDABoleto mensagemDDABoleto, BoletoLegadoDto boletoLegadoDto, Boolean bolEnviaPeloGeraBoleto) {
        if (!ObjectUtil.isNull(boletoLegadoDto.getCodEspDocumento()) && !ObjectUtil.isNull(boletoLegadoDto.getPercTaxaMora())) {
            boolean isEspecieDocumentoCCouBDP = isEspecieDocumentoCCouBDP(boletoLegadoDto);

            mensagemDDABoleto.setCodTipoJuros(isEspecieDocumentoCCouBDP || boletoLegadoDto.getPercTaxaMora().compareTo(BigDecimal.ZERO) == 0 ? Integer.valueOf(TipoJuros.ISENTO)
                    : Integer.valueOf(TipoJuros.PERCENTUAL_AO_MES_DIAS_CORRIDOS));

            mensagemDDABoleto.setDataJuros(isEspecieDocumentoCCouBDP || boletoLegadoDto.getPercTaxaMora().compareTo(BigDecimal.ZERO) == 0 ? null
                    : new DateTimeDB(DateUtil.somarDias(boletoLegadoDto.getDataVencimentoTitulo(), 1).getTime()));

            BigDecimal valorPercentualJuros;

            if (bolEnviaPeloGeraBoleto) {
                valorPercentualJuros = isEspecieDocumentoCCouBDP ? BigDecimal.ZERO : boletoLegadoDto.getPercTaxaMora();
            } else {
                valorPercentualJuros = getValorJurosSicoobNetEmpresarial(boletoLegadoDto);
            }

            mensagemDDABoleto.setValorPercentualJuros(valorPercentualJuros);
        } else {
            mensagemDDABoleto.setCodTipoJuros(Integer.valueOf(TipoJuros.ISENTO));
            mensagemDDABoleto.setValorPercentualJuros(BigDecimal.ZERO);
        }
    }

    /**
     * Método responsável por calcular o valor da multa/ Juros
     * 
     * valorMultaJuros dividido pelo valor do boleto, multiplicado por 100.
     * 
     * 
     * @param valorMultaJuros
     * @param valorBoleto
     * @param codTipoMultJuros
     * @return BigDecimal
     * 
     */
    private BigDecimal getValorJurosSicoobNetEmpresarial(BoletoLegadoDto boletoLegadoDto) {
        if (isEspecieDocumentoCCouBDP(boletoLegadoDto)) {
            return BigDecimal.ZERO;
        } else if (boletoLegadoDto.getCodTipoMora() == TipoJuros.VALOR_DIAS_CORRIDOS) {
            return (boletoLegadoDto.getPercTaxaMora().divide(boletoLegadoDto.getValor(), Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN)).multiply(Constantes.CEM);
        } else if (boletoLegadoDto.getCodTipoMora() == TipoJuros.PERCENTUAL_AO_DIA_DIAS_CORRIDOS) {
            return boletoLegadoDto.getPercTaxaMora();
        }

        return BigDecimal.ZERO;
    }

    /**
     * Método responsável por definir a multa
     * 
     * @param mensagemDDABoleto
     * @param boletoLegadoDto
     * @param bolEnviaPeloGeraBoleto
     */
    private void definirMulta(MensagemDDABoleto mensagemDDABoleto, BoletoLegadoDto boletoLegadoDto, Boolean bolEnviaPeloGeraBoleto) {
        if (!ObjectUtil.isNull(boletoLegadoDto.getCodEspDocumento()) && !ObjectUtil.isNull(boletoLegadoDto.getPercTaxaMulta())) {
            boolean isEspecieDocumentoCCouBDP = isEspecieDocumentoCCouBDP(boletoLegadoDto);

            mensagemDDABoleto.setCodTipoMulta(isEspecieDocumentoCCouBDP || boletoLegadoDto.getPercTaxaMulta().compareTo(BigDecimal.ZERO) == 0 ? Integer.valueOf(TipoMulta.ISENTO)
                    : Integer.valueOf(TipoMulta.PERCENTUAL));

            mensagemDDABoleto.setDataMulta(isEspecieDocumentoCCouBDP || boletoLegadoDto.getPercTaxaMulta().compareTo(BigDecimal.ZERO) == 0 ? null
                    : new DateTimeDB(DateUtil.somarDias(boletoLegadoDto.getDataVencimentoTitulo(), 1).getTime()));

            BigDecimal valorPercentualMulta;

            if (bolEnviaPeloGeraBoleto) {
                valorPercentualMulta = isEspecieDocumentoCCouBDP ? BigDecimal.ZERO : boletoLegadoDto.getPercTaxaMulta();
            } else {
                valorPercentualMulta = getValorMultaSicoobNetEmpresarial(boletoLegadoDto);
            }

            mensagemDDABoleto.setValorPercentualMulta(valorPercentualMulta);
        } else {
            mensagemDDABoleto.setCodTipoMulta(Integer.valueOf(TipoMulta.ISENTO));
            mensagemDDABoleto.setValorPercentualMulta(BigDecimal.ZERO);
        }
    }

    /**
     * Método responsável por calcular o valor da multa/ Juros
     * 
     * valorMultaJuros dividido pelo valor do boleto, multiplicado por 100.
     * 
     * @param boletoLegadoDto
     * @return BigDecimal
     */
    private BigDecimal getValorMultaSicoobNetEmpresarial(BoletoLegadoDto boletoLegadoDto) {
        if (isEspecieDocumentoCCouBDP(boletoLegadoDto)) {
            return BigDecimal.ZERO;
        } else if (boletoLegadoDto.getCodTipoMulta() == TipoMulta.VALOR_FIXO) {
            return (boletoLegadoDto.getPercTaxaMulta().divide(boletoLegadoDto.getValor(), Constantes.SCALE, BigDecimal.ROUND_HALF_EVEN)).multiply(Constantes.CEM);
        } else if (boletoLegadoDto.getCodTipoMulta() == TipoMulta.PERCENTUAL) {
            return boletoLegadoDto.getPercTaxaMulta();
        }

        return BigDecimal.ZERO;
    }

    /**
     * Método responsável por definir os descontos
     * 
     * @param boletoLegadoDto
     * @param msgBoleto
     */
    private void definirDesconto(BoletoLegadoDto boletoLegadoDto, MensagemDDABoleto msgBoleto) {
        Date dataVencimento = boletoLegadoDto.getDataVencimentoTitulo();

        DateTimeDB dataDesconto1 = boletoLegadoDto.getDataPrimDesconto();
        BigDecimal valorDesconto1 = boletoLegadoDto.getValorPrimDesconto();

        if (ObjectUtil.isNull(dataDesconto1) || valorDesconto1.compareTo(BigDecimal.ZERO) == 0) {
            msgBoleto.setCodTipoDesconto1(TipoDesconto.ISENTO);
            msgBoleto.setDataDesconto1(null);
            msgBoleto.setValorPercentualDesconto1(BigDecimal.ZERO);
        } else {
            msgBoleto.setCodTipoDesconto1(TipoDesconto.VALOR_FIXO_ATE_A_DATA_INFORMADA);
            // Se a data de desconto for igual a data de vencimento então a data deve ser NULL, caso contrário define a data de desconto
            msgBoleto.setDataDesconto1(dataDesconto1.equals(dataVencimento) ? null : dataDesconto1);
            // No gera boleto é sempre valor fixo e se vier do empresarial o valor já vem calculado como valor
            msgBoleto.setValorPercentualDesconto1(valorDesconto1);

            if (!ObjectUtil.isNull(boletoLegadoDto.getDataSegDesconto()) && !ObjectUtil.isNull(boletoLegadoDto.getValorSegDesconto())
                    && DateUtil.maiorQue(boletoLegadoDto.getDataSegDesconto(), boletoLegadoDto.getDataPrimDesconto())) {
                DateTimeDB dataDesconto2 = boletoLegadoDto.getDataSegDesconto();
                BigDecimal valorDesconto2 = boletoLegadoDto.getValorSegDesconto();

                msgBoleto.setCodTipoDesconto2(TipoDesconto.VALOR_FIXO_ATE_A_DATA_INFORMADA);
                // Se a data de desconto for igual a data de vencimento então a data deve ser NULL, caso contrário define a data de desconto
                msgBoleto.setDataDesconto2(dataDesconto2.equals(dataVencimento) ? null : dataDesconto2);
                // No gera boleto é sempre valor fixo e se vier do empresarial o valor já vem calculado como valor
                msgBoleto.setValorPercentualDesconto2(valorDesconto2);

                if (!ObjectUtil.isNull(boletoLegadoDto.getDataTerDesconto()) && !ObjectUtil.isNull(boletoLegadoDto.getValorTerDesconto())
                        && DateUtil.maiorQue(boletoLegadoDto.getDataTerDesconto(), boletoLegadoDto.getDataSegDesconto())) {
                    DateTimeDB dataDesconto3 = boletoLegadoDto.getDataTerDesconto();
                    BigDecimal valorDesconto3 = boletoLegadoDto.getValorTerDesconto();

                    msgBoleto.setCodTipoDesconto3(TipoDesconto.VALOR_FIXO_ATE_A_DATA_INFORMADA);
                    // Se a data de desconto for igual a data de vencimento então a data deve ser NULL, caso contrário define a data de desconto
                    msgBoleto.setDataDesconto3(dataDesconto3.equals(dataVencimento) ? null : dataDesconto3);
                    // No gera boleto é sempre valor fixo e se vier do empresarial o valor já vem calculado como valor
                    msgBoleto.setValorPercentualDesconto3(valorDesconto3);
                }
            }
        }
    }

    /**
     * Método responsável por definir o sacador avalista
     * 
     * @param msgBoleto
     * @param boletoLegadoDto
     * @throws ComumException
     */
    private void setSacadorAvalistaBoleto(MensagemDDABoleto msgBoleto, BoletoLegadoDto boletoLegadoDto) throws ComumException {
        debug("### Definindo sacador avalista...");

        final String cpfCnpj = boletoLegadoDto.getNumCpfCnpjAvalista().trim();
        debug("CPF/CNPJ: " + cpfCnpj);

        final String codTipoPessoaDDAAvalista = cpfCnpj.length() == Constantes.TAMANHO_CPF ? TipoPessoaSacadorAvalistaEnum.CPF.getCodDominio()
                : TipoPessoaSacadorAvalistaEnum.CNPJ.getCodDominio();
        debug("Tipo sacador avalista: " + codTipoPessoaDDAAvalista);

        msgBoleto.setCodTipoPessoaDDAAvalista(codTipoPessoaDDAAvalista);
        msgBoleto.setNumCpfCnpjAvalista(cpfCnpj);
        msgBoleto.setNomeAvalista(truncar(removerCaracteresEspeciais(boletoLegadoDto.getDescAvalista()), TAMANHO_MAX_NOME));
    }

    /**
     * Método responsável por definir o beneficiário
     * 
     * @param msgBoleto
     * @param boletoLegadoDto
     * @throws ComumException
     */
    private void setBeneficiarioBoleto(MensagemDDABoleto msgBoleto, BoletoLegadoDto boletoLegadoDto) throws ComumException {
        debug("### Definindo beneficiário...");

        final String cpfCnpj = boletoLegadoDto.getNumCpfCnpjCedente().trim();
        debug("CPF/CNPJ: " + cpfCnpj);

        final String codTipoPessoaBeneficiario = cpfCnpj.length() == Constantes.TAMANHO_CPF ? TipoPessoaEnum.PF.getCodDominioCip() : TipoPessoaEnum.PJ.getCodDominioCip();
        debug("Tipo beneficiário: " + codTipoPessoaBeneficiario);

        msgBoleto.setCodTipoPessoaBeneficiario(codTipoPessoaBeneficiario);
        msgBoleto.setNumCpfCnpjBeneficiario(cpfCnpj);
        msgBoleto.setNomeBeneficiario(truncar(removerCaracteresEspeciais(boletoLegadoDto.getDescNomePessoa()), TAMANHO_MAX_NOME));

        if (msgBoleto.getCodTipoPessoaBeneficiario().equals(TipoPessoaEnum.PJ.getCodDominioCip())) {
            debug("Definindo nome fantasia...");
            msgBoleto.setNomeFantasiaBeneficiario(truncar(removerCaracteresEspeciais(boletoLegadoDto.getDescNomePessoa()), TAMANHO_MAX_NOME_FANTASIA));
        }
    }

    /**
     * Método responsável por remover os caracteres especiais do texto
     * 
     * @param txt
     * @return
     * @throws ComumException
     */
    private String removerCaracteresEspeciais(String txt) throws ComumException {
        if (ObjectUtil.isEmpty(txt)) {
            return txt;
        }

        String texto = ObjectUtil.removerCaracteresInvalidos(StringUtil.substituirCaracteresEspeciais(txt));

        // Se há caracteres para tratamento
        if (!ObjectUtil.isEmpty(caracteresRemover)) {
            texto = texto.replaceAll(caracteresRemover, "");
        }

        return texto;
    }

    /**
     * Método responsável por definir o pagador
     * 
     * @param msgBoleto
     * @param boletoLegadoDto
     * @throws ComumException
     */
    private void setPagadorBoleto(MensagemDDABoleto msgBoleto, BoletoLegadoDto boletoLegadoDto) throws ComumException {
        debug("### Definindo pagador...");

        final String cpfCnpj = boletoLegadoDto.getNumCpfCnpjPagador().trim();
        debug("CPF/CNPJ: " + cpfCnpj);

        String codTipoPessoaPagador = cpfCnpj.length() == Constantes.TAMANHO_CPF ? TipoPessoaEnum.PF.getCodDominioCip() : TipoPessoaEnum.PJ.getCodDominioCip();
        debug("Tipo pagador: " + codTipoPessoaPagador);

        msgBoleto.setCodTipoPessoaPagador(codTipoPessoaPagador);
        msgBoleto.setNumCpfCnpjPagador(cpfCnpj);
        msgBoleto.setNomePagador(truncar(removerCaracteresEspeciais(boletoLegadoDto.getDescNomeSacado()), TAMANHO_MAX_NOME));

        if (msgBoleto.getCodTipoPessoaPagador().equals(TipoPessoaEnum.PJ.getCodDominioCip())) {
            debug("Definindo nome fantasia...");
            msgBoleto.setNomeFantasiaPagador(truncar(removerCaracteresEspeciais(boletoLegadoDto.getDescNomeSacado()), TAMANHO_MAX_NOME_FANTASIA));
        }
    }

    /**
     * Método responsável por truncar o texto
     * 
     * @param txt
     * @param tamanhoMax
     * @return
     */
    private String truncar(String txt, int tamanhoMax) {
        if (ObjectUtil.isEmpty(txt)) {
            return txt;
        }

        int tamanho = txt.length();
        int limite = tamanho > tamanhoMax ? tamanhoMax : tamanho;

        return txt.substring(0, limite).trim();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ManutencaoMensagemDDABoletoServico#alterarMensagemDDABoleto(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDto)
     */
    public void alterarMensagemDDABoleto(MensagemDDABoletoDto dto) throws ComumNegocioException {
        validarDadosBeneficiario(dto);
        validarDadosBeneficiarioFinal(dto);
        validarDadosPagador(dto);
        validarDadosMultaMoraDesconto(dto.getCodTipoMulta(), dto.getValorMulta(), "Multa");
        validarDadosMultaMoraDesconto(dto.getCodTipoJuros(), dto.getValorJuros(), "Juros");
        validarDadosDesconto(dto);
        validarDadosBoleto(dto);
        validarDadosGrupoCalculo(dto);
        validarDigitoSmallInt(dto);

        em.merge(construirObjetoMensagemDDABoleto(dto));
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @throws ComumNegocioException
     * @throws NumberFormatException void
     * 
     */
    private void validarDadosDesconto(MensagemDDABoletoDto dto) throws ComumNegocioException, NumberFormatException {
        if (!ObjectUtil.isEmpty(dto.getListDadosDesconto())) {
            for (MensagemDDABoletoDescontoDto msgBoletoDescontoDto : dto.getListDadosDesconto()) {
                validarDadosMultaMoraDesconto(ObjectUtil.isNull(msgBoletoDescontoDto.getCodTipoDesconto()) ? null : Integer.valueOf(msgBoletoDescontoDto.getCodTipoDesconto()),
                        msgBoletoDescontoDto.getValorDesconto(), "Desconto");
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param dto void
     * @throws ComumNegocioException
     * 
     */
    private void validarDigitoSmallInt(MensagemDDABoletoDto dto) throws ComumNegocioException {
        if ((!ObjectUtil.isEmpty(dto.getIdOrgaoMoeda())) && (dto.getIdOrgaoMoeda() > VALOR_MAXIMO_SMALLINT || dto.getIdOrgaoMoeda() < VALOR_MINIMO_SMALLINT)) {
            throw new ComumNegocioException("integracaocip.erro.tamanho.numero.invalido.db2", "Id Orgão Moeda");
        }
        if ((!ObjectUtil.isEmpty(dto.getDiasDeProtesto())) && (dto.getDiasDeProtesto() > VALOR_MAXIMO_SMALLINT || dto.getDiasDeProtesto() < VALOR_MINIMO_SMALLINT)) {
            throw new ComumNegocioException("integracaocip.erro.tamanho.numero.invalido.db2", "Dias de Protesto");
        }
    }

    /**
     * Método responsável por
     * 
     * @throws ComumNegocioException void
     * 
     */
    private void validarDadosGrupoCalculo(MensagemDDABoletoDto dtoMerge) throws ComumNegocioException {
        if (!ObjectUtil.isEmpty(dtoMerge.getListDadosGrupoCalculo())) {
            for (MensagemDDABoletoGrupoCalculoDto dto : dtoMerge.getListDadosGrupoCalculo()) {
                if (!isGrupoVazio(dto)) {
                    if (ObjectUtil.isNull(dto.getDataValidadeCalculo())) {
                        throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Dt. Validade Cálc.");
                    } else if (ObjectUtil.isNull(dto.getJuros())) {
                        throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Juros");
                    } else if (ObjectUtil.isNull(dto.getMulta())) {
                        throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Multa");
                    } else if (ObjectUtil.isNull(dto.getDesconto())) {
                        throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Desconto");
                    } else if (ObjectUtil.isNull(dto.getValorTotalCobrado())) {
                        throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Vlr. Total Cobr.");
                    }
                }
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @throws ComumNegocioException void
     * 
     */
    private void validarDadosBoleto(MensagemDDABoletoDto dtoMerge) throws ComumNegocioException {
        if (ObjectUtil.isEmpty(dtoMerge.getNumCodigoDeBarras())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Num. Código de Barras");
        } else if (dtoMerge.getNumCodigoDeBarras().trim().length() != Constantes.TAMANHO_CODIGO_BARRAS) {
            throw new ComumNegocioException("integracaocip.linha.digitavel.codigo.barras.tamanho.invalido");
        } else if (ObjectUtil.isEmpty(dtoMerge.getNumLinhaDigitavel())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Linha Digitável");
        } else if (dtoMerge.getNumLinhaDigitavel().trim().length() != Constantes.TAMANHO_LINHA_DIGITAVEL) {
            throw new ComumNegocioException("integracaocip.linha.digitavel.codigo.barras.tamanho.invalido");
        } else if (ObjectUtil.isEmpty(dtoMerge.getIdCarteira())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Id Carteira");
        } else if (ObjectUtil.isEmpty(dtoMerge.getCodMoeda())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Cod. Moeda");
        } else if (ObjectUtil.isEmpty(dtoMerge.getIdOrgaoMoeda())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Id Orgão Moeda");
        } else if (ObjectUtil.isNull(dtoMerge.getDataVencimento())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Data Vencimento");
        } else if (ObjectUtil.isEmpty(dtoMerge.getValorDoBoleto())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Valor Boleto");
        } else if (ObjectUtil.isNull(dtoMerge.getDataEmissao())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Data Emissão");
        } else if (ObjectUtil.isNull(dtoMerge.getDataLimitePgto())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Data Limite Pgto");
        } else if (ObjectUtil.isEmpty(dtoMerge.getBolTituloNegociado())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Bol. Tít. Negociado");
        } else if (ObjectUtil.isEmpty(dtoMerge.getBolBloqueioPagamento())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Bol Bloqueio Pgto");
        } else if (ObjectUtil.isEmpty(dtoMerge.getBolPagamentoParcial())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Bol Pagamento Parcial");
        } else if (ObjectUtil.isNull(dtoMerge.getValorAbatimento())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Valor Abatimento");
        } else if (ObjectUtil.isEmpty(dtoMerge.getTipoModeloDeCalculo())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Tipo Modelo de Cálc.");
        } else if (ObjectUtil.isEmpty(dtoMerge.getCodAutorizacaoValorDivergente())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Aut. Valor Divergente");
        } else if (ObjectUtil.isEmpty(dtoMerge.getIdEspecieDocumento())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Id. Espécie Documento");
        } else if (ObjectUtil.isEmpty(dtoMerge.getCodTipoPagamento())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Cod. Tipo Pagamento");
        }
    }

    /**
     * Método responsável por
     * 
     * @return boolean
     * @throws ComumNegocioException
     * 
     */
    private void validarDadosBeneficiario(MensagemDDABoletoDto dtoMerge) throws ComumNegocioException {
        // RN12
        if (ObjectUtil.isEmpty(dtoMerge.getCodTipoPessoaBeneficiario())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Cod. Tipo Beneficiário");
        } else if (ObjectUtil.isEmpty(dtoMerge.getNumCnpjCpfBeneficiario())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "CPF/CNPJ Beneficiário");
        } else if (!CpfCnpjUtil.isTipoIgualDocumento(dtoMerge.getCodTipoPessoaBeneficiario(), dtoMerge.getNumCnpjCpfBeneficiario())) {
            throw new ComumNegocioException("operacional.mensagemddaboleto.erro.tipopessoa.documento", "Benefiário");
        } else if (ObjectUtil.isEmpty(dtoMerge.getNomeBeneficiario())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Beneficiário");
        }
    }

    /**
     * Método responsável por
     * 
     * @param dtoMerge
     * @throws ComumNegocioException void
     * 
     */
    private void validarDadosMultaMoraDesconto(Integer codTipo, BigDecimal valor, String nomeCampo) throws ComumNegocioException {
        // RN52 e RN51
        if (nomeCampo.equals("Juros") || nomeCampo.equals("Multa")) {
            if (codTipo == Constantes.INTEGER_ZERO && (!ObjectUtil.isNull(valor))) {
                throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Cod. Tipo " + nomeCampo);
            }
            if (nomeCampo.equals("Juros")) {
                if (codTipo == 5 && (ObjectUtil.isNull(valor))) {
                    throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Valor " + nomeCampo);
                }
            } else if (nomeCampo.equals("Multa")) {
                if (codTipo == 3 && (ObjectUtil.isNull(valor))) {
                    throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Valor " + nomeCampo);
                }
            }
        } else {
            if (ObjectUtil.isNull(codTipo) && (!ObjectUtil.isNull(valor))) {
                throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Cod. Tipo " + nomeCampo);
            } else if (!ObjectUtil.isNull(codTipo) && ObjectUtil.isNull(valor)) {
                throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Valor " + nomeCampo);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @throws ComumNegocioException void
     * 
     */
    private void validarDadosBeneficiarioFinal(MensagemDDABoletoDto dtoMerge) throws ComumNegocioException {
        if ((!ObjectUtil.isEmpty(dtoMerge.getCodTipoPessoaBeneficiarioFinal())) && (!ObjectUtil.isEmpty(dtoMerge.getNumCnpjCpfBeneficiarioFinal()))) {
            if (!CpfCnpjUtil.isTipoIgualDocumento(dtoMerge.getCodTipoPessoaBeneficiarioFinal(), dtoMerge.getNumCnpjCpfBeneficiarioFinal())) {
                throw new ComumNegocioException("operacional.mensagemddaboleto.erro.tipopessoa.documento", "Beneficiário Final");
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @throws ComumNegocioException void
     * 
     */
    private void validarDadosPagador(MensagemDDABoletoDto dtoMerge) throws ComumNegocioException {
        if (ObjectUtil.isEmpty(dtoMerge.getCodTipoPessoaPagador())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Cod. Tipo Pessoa Pagador");
        } else if (ObjectUtil.isEmpty(dtoMerge.getNumCnpjCpfPagador())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "CPF/CNPJ Pagador");
        } else if (!CpfCnpjUtil.isTipoIgualDocumento(dtoMerge.getCodTipoPessoaPagador(), dtoMerge.getNumCnpjCpfPagador())) {
            throw new ComumNegocioException("operacional.mensagemddaboleto.erro.tipopessoa.documento", "Pagador");
        } else if (ObjectUtil.isEmpty(dtoMerge.getNomePagador())) {
            throw new ComumNegocioException("integracaocip.preenchimento.obrigatorio", "Nome/Razão Social Pagador");
        }
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDABoleto
     * @throws ComumNegocioException
     * 
     */
    private MensagemDDABoleto construirObjetoMensagemDDABoleto(MensagemDDABoletoDto dtoMerge) throws ComumNegocioException {
        MensagemDDABoleto msg = new MensagemDDABoleto();
        msg.setId(dtoMerge.getIdMensagem());
        // DADOS BENEFICIÁRIO
        isTipoIgualDocumento(dtoMerge.getCodTipoPessoaBeneficiario(), dtoMerge.getNumCnpjCpfBeneficiario(), "Beneficiário");
        msg.setCodTipoPessoaBeneficiario(dtoMerge.getCodTipoPessoaBeneficiario());
        msg.setNumCpfCnpjBeneficiario(dtoMerge.getNumCnpjCpfBeneficiario());
        msg.setNomeBeneficiario(dtoMerge.getNomeBeneficiario());
        msg.setNomeFantasiaBeneficiario(retornarStringNull(dtoMerge.getNomeFantasiaBeneficiario()));
        msg.setDescLogradouroBeneficiario(retornarStringNull(dtoMerge.getDescLogradouroBeneficiario()));
        msg.setDescCidadeBeneficiario(retornarStringNull(dtoMerge.getDescCidadeBeneficiario()));
        msg.setSiglaUfBeneficiario(retornarStringNull(dtoMerge.getUfBeneficiario()));
        msg.setNumCepBeneficiario(retornarStringNull(dtoMerge.getNumCepBeneficiario()));
        // DADOS BENEFICIÁRIO FINAL
        isTipoIgualDocumento(dtoMerge.getCodTipoPessoaBeneficiarioFinal(), dtoMerge.getNumCnpjCpfBeneficiarioFinal(), "Beneficiário Final");
        msg.setCodTipoPessoaBeneficiarioFinal(retornarStringNull(dtoMerge.getCodTipoPessoaBeneficiarioFinal()));
        msg.setNumCpfCnpjBeneficiarioFinal(retornarStringNull(dtoMerge.getNumCnpjCpfBeneficiarioFinal()));
        msg.setNomeBeneficiarioFinal(retornarStringNull(dtoMerge.getNomeBeneficiarioFinal()));
        msg.setNomeFantasiaBeneficiarioFinal(retornarStringNull(dtoMerge.getNomeFantasiaBeneficiarioFinal()));
        // DADOS PAGADOR
        isTipoIgualDocumento(dtoMerge.getCodTipoPessoaPagador(), dtoMerge.getNumCnpjCpfPagador(), "Pagador");
        msg.setCodTipoPessoaPagador(dtoMerge.getCodTipoPessoaPagador());
        msg.setNumCpfCnpjPagador(dtoMerge.getNumCnpjCpfPagador());
        msg.setNomePagador(dtoMerge.getNomePagador());
        msg.setNomeFantasiaPagador(retornarStringNull(dtoMerge.getNomeFantasiaPagador()));
        msg.setDescLogradouroPagador(retornarStringNull(dtoMerge.getDescLogradouroPagador()));
        msg.setDescCidadePagador(retornarStringNull(dtoMerge.getDesCidadePagador()));
        msg.setSiglaUfPagador(retornarStringNull(dtoMerge.getUfPagador()));
        msg.setNumCepPagador(retornarStringNull(dtoMerge.getNumCepPagador()));
        // DADOS AVALISTA
        isTipoIgualDocumento(dtoMerge.getCodTipoPessoaAvalista(), dtoMerge.getNumCnpjCpfAvalista(), "Avalista");
        msg.setCodTipoPessoaDDAAvalista(retornarStringNull(dtoMerge.getCodTipoPessoaAvalista()));
        msg.setNumCpfCnpjAvalista(retornarStringNull(dtoMerge.getNumCnpjCpfAvalista()));
        msg.setNomeAvalista(retornarStringNull(dtoMerge.getNomeAvalista()));
        // DADOS BOLETO
        msg.setIdCarteira(dtoMerge.getIdCarteira());
        msg.setCodMoeda(dtoMerge.getCodMoeda());
        msg.setIdOrgaoMoeda(dtoMerge.getIdOrgaoMoeda());
        msg.setNumNossoNumero(retornarStringNull(dtoMerge.getNumNossoNumero()));
        msg.setNumCodigoBarra(dtoMerge.getNumCodigoDeBarras());
        msg.setNumCodBarrasCampoLivre(dtoMerge.getNumCodigoDeBarras() != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(dtoMerge.getNumCodigoDeBarras()) : null);
        msg.setNumLinhaDigitavel(retornarStringNull(dtoMerge.getNumLinhaDigitavel()));
        msg.setDataVencimento(DateUtil.getDateTimeDB(dtoMerge.getDataVencimento()));
        msg.setValorBoleto(dtoMerge.getValorDoBoleto());
        msg.setDataEmissao(DateUtil.getDateTimeDB(dtoMerge.getDataEmissao()));
        msg.setNumDocumento(retornarStringNull(dtoMerge.getNumDocumento()));
        msg.setDataLimitePagamento(DateUtil.getDateTimeDB(dtoMerge.getDataLimitePgto()));
        msg.setNumDiasProtesto(retornarIntegerNull(dtoMerge.getDiasDeProtesto()));
        msg.setValorAbatimento(dtoMerge.getValorAbatimento());
        msg.setCodIndicadorValorMinimo(retornarStringNull(dtoMerge.getIndValorMinimo()));
        msg.setValorMinimo(retornarBigDecimalNull(dtoMerge.getValorMinimo()));
        msg.setCodIndicadorValorMaximo(retornarStringNull(dtoMerge.getIndValorMaximo()));
        msg.setValorMaximo(retornarBigDecimalNull(dtoMerge.getValorMaximo()));
        msg.setNumParcela(retornarIntegerNull(dtoMerge.getNumeroParcela()));
        msg.setQtdTotalParcela(retornarIntegerNull(dtoMerge.getQtdTotalParcelas()));
        msg.setBolTituloNegociado(dtoMerge.getBolTituloNegociado().equals("S"));
        msg.setBolBloqueioPagamento(dtoMerge.getBolBloqueioPagamento().equals("S"));
        msg.setBolPagamentoParcial(dtoMerge.getBolPagamentoParcial().equals("S"));
        msg.setQtdPagamentoParcial(retornarIntegerNull(dtoMerge.getQtdPagamentoParcial()));
        msg.setCodTipoModeloCalculo(dtoMerge.getTipoModeloDeCalculo());
        msg.setCodAutorizacaoValorDivergente(dtoMerge.getCodAutorizacaoValorDivergente());
        msg.setIdEspecieDocumento(dtoMerge.getIdEspecieDocumento());
        msg.setCodTipoPagamento(dtoMerge.getCodTipoPagamento());
        msg.setNumRefAtualCadBoleto(retornarLongNull(dtoMerge.getNumRefAtualCadBoleto()));
        msg.setNumSeqAtualCadBoleto(retornarLongNull(dtoMerge.getNumSeqAtualCadBoleto()));
        // DADOS DESCONTO
        setarMensagemDDABoleto(dtoMerge, msg);

        // DADOS JURO
        if (!isJuroVazio(dtoMerge)) {
            msg.setCodTipoJuros(dtoMerge.getCodTipoJuros());
            msg.setDataJuros(retornarDateTimeDBNull(dtoMerge.getDataJuros()));
            msg.setValorPercentualJuros(dtoMerge.getValorJuros());
        } else {
            msg.setCodTipoJuros(null);
            msg.setDataJuros(null);
            msg.setValorPercentualJuros(null);
        }

        // DADOS MULTA
        if (!isMultaVazia(dtoMerge)) {
            msg.setCodTipoMulta(dtoMerge.getCodTipoMulta());
            msg.setDataMulta(retornarDateTimeDBNull(dtoMerge.getDataMulta()));
            msg.setValorPercentualMulta(dtoMerge.getValorMulta());
        } else {
            msg.setCodTipoMulta(null);
            msg.setDataMulta(null);
            msg.setValorPercentualMulta(null);
        }
        // DADOS GRUPO CÁLCULO
        if (!ObjectUtil.isEmpty(dtoMerge.getListDadosGrupoCalculo())) {
            for (MensagemDDABoletoGrupoCalculoDto dto : dtoMerge.getListDadosGrupoCalculo()) {
                if (!isGrupoVazio(dto)) {
                    MensagemDDABoletoGrupoCalculo grupo = new MensagemDDABoletoGrupoCalculo();
                    grupo.setId(dto.getIdMensagemDDABoletoGrupoCalculo());
                    grupo.setMensagemDDABoleto(msg);
                    grupo.setDataValidadeCalculo(DateUtil.getDateTimeDB(dto.getDataValidadeCalculo()));
                    grupo.setValorCalculadoJuros(dto.getJuros());
                    grupo.setValorCalculadoMulta(dto.getMulta());
                    grupo.setValorCalculadoDesconto(dto.getDesconto());
                    grupo.setValorTotalCobrado(dto.getValorTotalCobrado());
                    if (ObjectUtil.isEmpty(msg.getListaMensagemDDABoletoGrupoCalculo())) {
                        msg.setListaMensagemDDABoletoGrupoCalculo(new ArrayList<MensagemDDABoletoGrupoCalculo>());
                    }
                    msg.getListaMensagemDDABoletoGrupoCalculo().add(grupo);
                } else {
                    // Correção Mantis 17357
                    MensagemDDABoletoGrupoCalculo grupoCalculoDelete = em.find(MensagemDDABoletoGrupoCalculo.class, dto.getIdMensagemDDABoletoGrupoCalculo());
                    if (!ObjectUtil.isNull(grupoCalculoDelete)) {
                        em.remove(grupoCalculoDelete);
                    }
                }
            }
        }
        return msg;
    }

    /**
     * Método responsável por
     * 
     * @param dtoMerge
     * @param msg void
     */
    private void setarMensagemDDABoleto(MensagemDDABoletoDto dtoMerge, MensagemDDABoleto msg) {
        if (!ObjectUtil.isEmpty(dtoMerge.getListDadosDesconto())) {
            int cont = dtoMerge.getListDadosDesconto().size();

            if (cont == 1) {
                msg.setCodTipoDesconto1(dtoMerge.getListDadosDesconto().get(0).getCodTipoDesconto());
                msg.setDataDesconto1(retornarDateTimeDBNull(dtoMerge.getListDadosDesconto().get(0).getDataDesconto()));
                msg.setValorPercentualDesconto1(dtoMerge.getListDadosDesconto().get(0).getValorDesconto());
            } else if (cont == 2) {
                msg.setCodTipoDesconto1(dtoMerge.getListDadosDesconto().get(0).getCodTipoDesconto());
                msg.setDataDesconto1(retornarDateTimeDBNull(dtoMerge.getListDadosDesconto().get(0).getDataDesconto()));
                msg.setValorPercentualDesconto1(dtoMerge.getListDadosDesconto().get(0).getValorDesconto());

                msg.setCodTipoDesconto2(dtoMerge.getListDadosDesconto().get(1).getCodTipoDesconto());
                msg.setDataDesconto2(retornarDateTimeDBNull(dtoMerge.getListDadosDesconto().get(1).getDataDesconto()));
                msg.setValorPercentualDesconto2(dtoMerge.getListDadosDesconto().get(1).getValorDesconto());
            } else if (cont == 3) {
                msg.setCodTipoDesconto1(dtoMerge.getListDadosDesconto().get(0).getCodTipoDesconto());
                msg.setDataDesconto1(retornarDateTimeDBNull(dtoMerge.getListDadosDesconto().get(0).getDataDesconto()));
                msg.setValorPercentualDesconto1(dtoMerge.getListDadosDesconto().get(0).getValorDesconto());

                msg.setCodTipoDesconto2(dtoMerge.getListDadosDesconto().get(1).getCodTipoDesconto());
                msg.setDataDesconto2(retornarDateTimeDBNull(dtoMerge.getListDadosDesconto().get(1).getDataDesconto()));
                msg.setValorPercentualDesconto2(dtoMerge.getListDadosDesconto().get(1).getValorDesconto());

                msg.setCodTipoDesconto3(dtoMerge.getListDadosDesconto().get(2).getCodTipoDesconto());
                msg.setDataDesconto3(retornarDateTimeDBNull(dtoMerge.getListDadosDesconto().get(2).getDataDesconto()));
                msg.setValorPercentualDesconto3(dtoMerge.getListDadosDesconto().get(2).getValorDesconto());
            } else {
                msg.setCodTipoDesconto1(null);
                msg.setDataDesconto1(null);
                msg.setValorPercentualDesconto1(null);

                msg.setCodTipoDesconto2(null);
                msg.setDataDesconto2(null);
                msg.setValorPercentualDesconto2(null);

                msg.setCodTipoDesconto3(null);
                msg.setDataDesconto3(null);
                msg.setValorPercentualDesconto3(null);
            }

        }
    }

    /**
     * Método responsável por retornar NULO caso o parâmetro seja VAZIO (Mantis 17352 pós homologação)
     * 
     * @param param
     * @return String
     * 
     */
    private String retornarStringNull(String param) {
        return ObjectUtil.isEmpty(param) ? null : param;
    }

    /**
     * Método responsável por retornar NULO caso o parâmetro seja VAZIO (Mantis 17352 pós homologação)
     * 
     * @param param
     * @return Integer
     * 
     */
    private Integer retornarIntegerNull(Integer param) {
        return ObjectUtil.isEmpty(param) ? null : param;
    }

    /**
     * Método responsável por retornar NULO caso o parâmetro seja VAZIO (Mantis 17352 pós homologação)
     * 
     * @param param
     * @return BigDecimal
     * 
     */
    private BigDecimal retornarBigDecimalNull(BigDecimal param) {
        return ObjectUtil.isEmpty(param) ? null : param;
    }

    /**
     * Método responsável por retornar NULO caso o parâmetro seja VAZIO (Mantis 17352 pós homologação)
     * 
     * @param param
     * @return Long
     * 
     */
    private Long retornarLongNull(Long param) {
        return ObjectUtil.isEmpty(param) ? null : param;
    }

    /**
     * Método responsável por retornar NULO caso o parâmetro seja VAZIO (Mantis 17352 pós homologação)
     * 
     * @param param
     * @return DateTimeDB
     * 
     */
    private DateTimeDB retornarDateTimeDBNull(DateTime param) {
        return ObjectUtil.isNull(param) ? null : DateUtil.getDateTimeDB(param);
    }

    /**
     * Método responsável por retornar NULO caso o parâmetro seja VAZIO (Mantis 17352 pós homologação)
     * 
     * @param param
     * @return DateTimeDB
     * 
     */
    private DateTimeDB retornarDateTimeDBNull(Date param) {
        return ObjectUtil.isNull(param) ? null : DateUtil.getDateTimeDB(param);
    }

    /**
     * Método responsável por
     * 
     * @return boolean
     * 
     */
    private boolean isJuroVazio(MensagemDDABoletoDto dtoMerge) {
        return ((ObjectUtil.isEmpty(dtoMerge.getCodTipoJuros())) && (ObjectUtil.isEmpty(dtoMerge.getValorJuros())));
    }

    /**
     * Método responsável por
     * 
     * @return boolean
     * 
     */
    private boolean isMultaVazia(MensagemDDABoletoDto dtoMerge) {
        return ((ObjectUtil.isEmpty(dtoMerge.getCodTipoMulta())) && (ObjectUtil.isEmpty(dtoMerge.getValorMulta())));
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return boolean
     * 
     */
    private boolean isGrupoVazio(MensagemDDABoletoGrupoCalculoDto dto) {
        return ((ObjectUtil.isNull(dto.getDataValidadeCalculo())) && (ObjectUtil.isEmpty(dto.getJuros())) && (ObjectUtil.isEmpty(dto.getMulta()))
                && (ObjectUtil.isEmpty(dto.getDesconto())) && (ObjectUtil.isEmpty(dto.getValorTotalCobrado())));
    }

    /**
     * Método responsável por validar TipoPessoa com Numero de Inscrição (Mantis 17351 pós homologação)
     * 
     * @param codTipoPessoa
     * @param numCpfCnpj
     * @param pessoa
     * @throws ComumNegocioException void
     * 
     */
    private void isTipoIgualDocumento(String codTipoPessoa, String numCpfCnpj, String pessoa) throws ComumNegocioException {
        if (!ObjectUtil.isEmpty(codTipoPessoa)) {
            // Como nas constantes não existe um valor de PF como 1 (no caso de AVALISTA) inseri o INTEGER_UM
            if (codTipoPessoa.charAt(0) == Constantes.TIPO_PESSOA_FISICA || Constantes.STRING_NUMERO_1.equals(codTipoPessoa)) {
                if (numCpfCnpj.length() != Constantes.TAMANHO_CPF) {
                    throw new ComumNegocioException("operacional.mensagemddaboleto.erro.tipopessoa.documento", pessoa);
                } else if (!new ValidacaoCpf(numCpfCnpj, null).validar()) {
                    throw new ComumNegocioException("operacional.erro.cpf.invalido", pessoa);
                }
            } else if (codTipoPessoa.charAt(0) == Constantes.TIPO_PESSOA_JURIDICA || codTipoPessoa.equals("2")) {
                if (numCpfCnpj.length() != Constantes.TAMANHO_CNPJ) {
                    throw new ComumNegocioException("operacional.mensagemddaboleto.erro.tipopessoa.documento");
                } else if (!new ValidacaoCnpj(numCpfCnpj, null).validar()) {
                    throw new ComumNegocioException("operacional.erro.cnpj.invalido", pessoa);
                }
            }
        } else {
            if (!ObjectUtil.isEmpty(numCpfCnpj)) {
                if (numCpfCnpj.length() == Constantes.TAMANHO_CPF && (!new ValidacaoCpf(numCpfCnpj, null).validar())) {
                    throw new ComumNegocioException("operacional.erro.cpf.invalido", pessoa);
                } else if (numCpfCnpj.length() == Constantes.TAMANHO_CNPJ && (!new ValidacaoCnpj(numCpfCnpj, null).validar())) {
                    throw new ComumNegocioException("operacional.erro.cnpj.invalido", pessoa);
                } else {
                    if (!pessoa.equals("Avalista")) {
                        throw new ComumNegocioException("operacional.erro.cpfcnpj.invalido", pessoa);
                    }
                }
            }
        }
    }

    /**
     * @return o atributo mensagemDDADao
     */
    public MensagemDDADao getMensagemDDADao() {
        return mensagemDDADao;
    }

    /**
     * @return o atributo mensagemDDADelegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (mensagemDDADelegate == null) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }

    /**
     * @return o atributo sciDelegate
     */
    public SCIDelegate getSCIDelegate() {
        if (ObjectUtil.isNull(sciDelegate)) {
            sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();
        }

        return sciDelegate;
    }

    /**
     * @return o atributo sciDelegate
     */
    public ADMDelegate getADMDelegate() {
        if (ObjectUtil.isNull(admDelegate)) {
            admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
        }

        return admDelegate;
    }
}
