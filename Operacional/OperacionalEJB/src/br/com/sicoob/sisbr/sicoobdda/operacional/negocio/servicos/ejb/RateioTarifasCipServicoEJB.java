package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.SituacaoRateioCreditoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDALancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateio;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateioLancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ContaCorrenteDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoCCODto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoRetDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.RateioTarifasCIPDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.RateioTarifasCipServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao;

/**
 * RateioTarifasCipServicoEJB
 * 
 * @author Danilo.Barros
 */
@Stateless
@Local({ RateioTarifasCipServicoLocal.class })
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RateioTarifasCipServicoEJB extends OperacionalCrudServicoEJB<RateioDDA> implements RateioTarifasCipServicoLocal {

	@PersistenceContext(unitName = "emSicoobDDA_db2")
	private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private RateioTarifasCipDao dao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private InstituicaoDao instituicaoDao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private RateioDDALancamentoDao lancamentoDao;

	@Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
	private EventoTarifavelDDADao eventoTarifavelDDADao;

    @Dao(entityManager = "emSQL", fabrica = OperacionalDaoFactory.class)
    private RateioDDALancamentoLegadoDao rateioDDALancamentoLegadoDao;

	private ADMDelegate admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();

	private static final int GRAVAR_RETORNO_ERRO_DB2 = 0;
	private static final int GRAVAR_RETORNO_EFETIVADO_DB2 = 1;
	private static final int GRAVAR_CONTA_CONVEIO_CC = 2;
    private static final String CONSULTA_DTO = "consultaDto";
    private static final String DESC_SITUACAO_LANCAMENTO = "descSituacaoLancamento";
    private static final String PARAM_COOPERATIVA_CENTRAL = "cooperativaCentral";
    private static final String PARAM_COOPERATIVA_SINGULAR = "cooperativaSingular";
    private static final String TOTAL_DTO = "totalDto";
    private static final String DESC_EVENTO_TARIFAVEL = "descEventoTarifavel";
    private static final String CONTROLE_RATEIO = "controleRateioDto";
    private static final String VISUALIZAR_RATEIO = "dtoVisualRateio";

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
	 */
	@Override
	protected RateioTarifasCipDao getDAO() {
		return dao;
	}

	/**
	 * 
	 * @return ContaCorrenteDelegate
	 */
	private ContaCorrenteDelegate getContaCorrenteDelegate() {
		return IntegracaoInternaFabricaDelegates.getInstance().getContaCorrenteDelegate();
	}

	/**
	 * @return RateioTarifasCIPDelegate
	 */
	private RateioTarifasCIPDelegate getAtualizarSituacaoRateioCCODelegate() {
		return OperacionalFabricaDelegates.getInstance().getRateioTarifasCIPDelegate();
	}

	/**
	 * Método responsável por retornar o EntityManager
	 * 
	 * @return
	 */
	protected EntityManager getEm() {
		return em;
	}
	
    /**
     * {@inheritDoc}
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#configurarRelatorioEventosDisponiveis(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto, br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto, br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioEventosDisponiveis(ControleRateioDto controleRateioDto, ConsultaEventoRateioDto consultaDto, UsuarioBancoobDTO usuario)
            throws BancoobException {
        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA516, usuario, getParametrosEventosDisponiveis(controleRateioDto, consultaDto));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#configurarRelatorioEventosRateio(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioEventosRateio(ControleRateioDto controleRateioDto, UsuarioBancoobDTO usuario) throws BancoobException {
        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA517, usuario, getParametrosDetalhesRateio(controleRateioDto));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#configurarRelatorioRateioLancamento(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioRateioLancamento(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException {
        List<VisualizaRateioTarifaDto> listaDadosLancamentoDto = dao.listarDadosLancamento(visualizaRateioTarifaDto);
        if (ObjectUtil.isEmpty(listaDadosLancamentoDto)) {
            throw new ComumNegocioException("Não há dados para exibir o relatório");
        }
        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA518, listaDadosLancamentoDto, usuario, getParametrosVisualizarRateioTarifa(visualizaRateioTarifaDto));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#configurarRelatorioDadosRateio(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioDadosRateio(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException {
        visualizaRateioTarifaDto.setListaDadosRateio(dao.listarDadosRateio(visualizaRateioTarifaDto));
        List<VisualizaRateioTarifaDto> lista = dao.obterTotalDadosRateio(visualizaRateioTarifaDto);
        visualizaRateioTarifaDto.setTotalDadosRateio(calculaDadosRateio(lista));

        if (ObjectUtil.isEmpty(lista)) {
            throw new ComumNegocioException("Não há dados para exibir o relatório");
        }
        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA519, visualizaRateioTarifaDto.getListaDadosRateio(), usuario,
                getParametrosRelatorioDadosRateio(visualizaRateioTarifaDto));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#configurarRelatorioRateioTarifa(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioRateioTarifa(VisualizaRateioTarifaDto visualizaRateioTarifaDto, UsuarioBancoobDTO usuario) throws BancoobException {
        List<VisualizaRateioTarifaDto> lista = dao.pesquisarRateioTarifaDDAPaginado(visualizaRateioTarifaDto);        
        Map<String, Object> parametros =  new HashMap<>();
        parametros.put(VISUALIZAR_RATEIO, visualizaRateioTarifaDto);
        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA520, lista, usuario, parametros);
    }

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#pesquisarDadosRateio(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)
	 */
	public VisualizaRateioTarifaDto pesquisarDadosRateio(VisualizaRateioTarifaDto dto) throws ComumException {
		VisualizaRateioTarifaDto visualRateioTarifaDto = new VisualizaRateioTarifaDto();
		visualRateioTarifaDto.setListaDadosRateio(dao.listarDadosRateio(dto));
		List<VisualizaRateioTarifaDto> lista = dao.obterTotalDadosRateio(dto);
		visualRateioTarifaDto.setTotalDadosRateio(calculaDadosRateio(lista));
		return visualRateioTarifaDto;
	}

	/**
	 * Método responsável por calcular o total da lista de rateio.
	 * 
	 * @param lista
	 *            void
	 * 
	 */
	private VisualizaRateioTarifaDto calculaDadosRateio(List<VisualizaRateioTarifaDto> lista) {
		VisualizaRateioTarifaDto visualRateioTarifaDto = new VisualizaRateioTarifaDto();
		Integer qtdApuradoBancoob = Constantes.INTEGER_ZERO;
		Integer qtdApuradoCIP = Constantes.INTEGER_ZERO;

		Double valorApuradoBancoob = Constantes.DOUBLE_VALUE_ZERO;
		Double valorApuradoCIP = Constantes.DOUBLE_VALUE_ZERO;
		Double valorRateioCoop = Constantes.DOUBLE_VALUE_ZERO;

		for (VisualizaRateioTarifaDto dto : lista) {
			qtdApuradoBancoob += dto.getQtdApuradoBancoob();
			qtdApuradoCIP += dto.getQtdApuradoCIP();

			valorApuradoBancoob += apuracaoValores(dto.getQtdApuradoBancoob(), dto.getValorTarifaCIP()).doubleValue();
			valorApuradoCIP += apuracaoValores(dto.getQtdApuradoCIP(), dto.getValorTarifaCIP()).doubleValue();
			valorRateioCoop += apuracaoValores(dto.getQtdApuradoBancoob(), dto.getValorTarifaBancoob()).doubleValue();

		}
		visualRateioTarifaDto.setValorApuradoBancoob(new BigDecimal(valorApuradoBancoob));
		visualRateioTarifaDto.setValorApuradoCIP(new BigDecimal(valorApuradoCIP));
		visualRateioTarifaDto.setValorRateioCoop(new BigDecimal(valorRateioCoop));
		visualRateioTarifaDto.setDifQuantidade((qtdApuradoBancoob - qtdApuradoCIP));
		visualRateioTarifaDto.setDifValor(subtrairValores(valorApuradoBancoob, valorApuradoCIP));
		visualRateioTarifaDto.setQtdApuradoBancoob(qtdApuradoBancoob);
		visualRateioTarifaDto.setQtdApuradoCIP(qtdApuradoCIP);
		return visualRateioTarifaDto;
	}

	/**
	 * Método responsável por calcular apuracao de valores
	 * 
	 * @param count
	 * @param qtdApurado
	 * @param valorTarifa
	 * @return BigDecimal
	 * 
	 */
	private BigDecimal apuracaoValores(Integer qtdApurado, BigDecimal valorTarifa) {
		return new BigDecimal(valorTarifa.multiply(new BigDecimal(qtdApurado.doubleValue())).doubleValue());
	}

	/**
	 * @param valorTarifa1
	 * @param valorTarifa2
	 * @return
	 */
	private BigDecimal subtrairValores(Double valorTarifa1, Double valorTarifa2) {
		return new BigDecimal(new BigDecimal(valorTarifa1).subtract(new BigDecimal(valorTarifa2)).doubleValue());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#pesquisarLancamento(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)
	 */
	public List<VisualizaRateioTarifaDto> pesquisarLancamento(VisualizaRateioTarifaDto dto) throws ComumException {
		return dao.listarDadosLancamento(dto);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#obterDadosLancamentoRateioDDA(java.lang.Long)
	 */
	public VisualizaRateioTarifaDto obterDadosLancamentoRateioDDA(Long idRateioLancamento) throws ComumException {
		VisualizaRateioTarifaDto visualRateioTarifaDto = dao.obterDadosLancamentoRateioDDA(idRateioLancamento);
		InstituicaoDto inst = instituicaoDao.obterIdInstituicaoPai(defineInstituicaoCmbCentral(visualRateioTarifaDto));
		visualRateioTarifaDto.setIdInstituicaoCentral(inst.getNumCooperativa());
		return visualRateioTarifaDto;
	}

	/**
	 * Método responsável por
	 * 
	 * @param visualRateioTarifaDto
	 * @return Integer
	 * 
	 */
	private Integer defineInstituicaoCmbCentral(VisualizaRateioTarifaDto visualRateioTarifaDto) {
		Integer idComboCentral = Constantes.INTEGER_UM;
		if (!ObjectUtil.isNull(visualRateioTarifaDto.getIdInstituicaoTransfDebito())) {
			idComboCentral = visualRateioTarifaDto.getIdInstituicaoTransfDebito();
		} else {
			idComboCentral = visualRateioTarifaDto.getIdInstituicaoRateio();
		}
		return idComboCentral;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#detalharRateioTarifa(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)
	 */
	public VisualizaRateioTarifaDto detalharRateioTarifa(VisualizaRateioTarifaDto dto) throws BancoobException {
		VisualizaRateioTarifaDto visualRateioTarifaDto = new VisualizaRateioTarifaDto();
		visualRateioTarifaDto.setListaDadosRateio(dao.listarDadosRateio(dto));
		List<VisualizaRateioTarifaDto> lista = dao.obterTotalDadosRateio(dto);
		visualRateioTarifaDto.setTotalDadosRateio(calculaDadosRateio(lista));
		visualRateioTarifaDto.setListaLancamentoRateios(dao.listarDadosLancamento(dto));
		visualRateioTarifaDto.setListaSituacaoLancamento(lancamentoDao.listarSituacaoRateioLancamento());
		return visualRateioTarifaDto;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#pesquisarRateioTarifaDDAPaginado(br.com.bancoob.negocio.dto.ConsultaDto)
	 */
	public ConsultaDto<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(
			ConsultaDto<VisualizaRateioTarifaDto> consultaDto) throws BancoobException {
		if (ObjectUtil.isNull(consultaDto)) {
			throw new ComumNegocioException("integracaocip.parametro.nao.informado", "consultaDto");
		} else if (ObjectUtil.isEmpty(consultaDto.getTamanhoPagina())) {
			throw new ComumNegocioException("integracaocip.campo.obrigatorio", "tamanho da página");
		} else if (ObjectUtil.isNull(consultaDto.getPagina())) {
			throw new ComumNegocioException("integracaocip.campo.obrigatorio", "página");
		} else if (ObjectUtil.isNull(consultaDto.getFiltro())) {
			throw new ComumNegocioException("integracaocip.campo.obrigatorio", "filtro");
		}

		VisualizaRateioTarifaDto visualizaRateioTarifaDto = (VisualizaRateioTarifaDto) consultaDto.getFiltro();

		Integer total = dao.countRateioTarifaDDA(visualizaRateioTarifaDto);

		consultaDto.setTotalRegistros(total);

		if (total > 0) {
			consultaDto.setResultado(dao.pesquisarRateioTarifaDDAPaginado(visualizaRateioTarifaDto,
					consultaDto.getPagina(), consultaDto.getTamanhoPagina()));
		}

		return consultaDto;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#listaSituacaoRateio()
	 */
	public List<SituacaoRateio> listaSituacaoRateio() throws ComumException {
		return getDAO().listaSituacaoRateio();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#consolidarEventosTarifaveis()
	 */
	public void consolidarEventosTarifaveis() throws ComumException {
		getLogger().debug(Constantes.STR_SEPARACAO_2 + "Consolidando os eventos Cip DDA e PCR tarifávies...");
		getDAO().consolidarEventosTarifaveis();
		getLogger().debug(Constantes.STR_SEPARACAO_2 + "Eventos tarifáveis Cip consolidados.");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#obterDadosControleRateio()
	 */
	public ControleRateioDto obterDadosControleRateio() throws BancoobException {
		ControleRateioDto dto = new ControleRateioDto();

		RateioDDA rateioDDA = dao.obterRateioAtual();

		if (!ObjectUtil.isNull(rateioDDA)) {
			dto.setIdRateio(rateioDDA.getId());
			dto.setDataInclusao(rateioDDA.getDataHoraInclusao());
			dto.setDescSituacao(rateioDDA.getSituacaoRateio().getDescSituacaoRateio());
			dto.setCodSituacaoRateio(rateioDDA.getSituacaoRateio().getCodSituacaoRateio());

			dto.setListaEventoRateio(dao.listarEventosRateio(rateioDDA.getId()));
		}

		dto.setListaEventoTarifavelDDA(eventoTarifavelDDADao.listarEventoTarifavelDDAParcial());
		dto.setDesvioPadrao(new BigDecimal(
				parametroDAO.obterValorDouble(ParametroDDA.DESVIO_PADRAO_RATEIO_TARIFAS, Constantes.ID_SICOOB)));

		return dto;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#pesquisarEventosDisponiveis(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto)
	 */
	public List<EventoRateioDto> pesquisarEventosDisponiveis(ConsultaEventoRateioDto dto) throws BancoobException {
		if (ObjectUtil.isNull(dto)) {
			throw new ComumNegocioException("integracaocip.parametro.nao.informado", "dto");
		}

		return dao.pesquisarEventosDisponiveis(dto);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#consolidarLancamentosCCO()
	 */
	public void consolidarLancamentosCCO() throws ComumException {
		getLogger().debug(Constantes.STR_SEPARACAO_2 + "Consolidando os lançamentos a serem efetivados no CCO...");
		getDAO().consolidarLancamentosCCO();
		getLogger().debug(Constantes.STR_SEPARACAO_2 + "Lançamentos consolidados.");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#incluirListaEventoConsolidadoRateio(java.util.List)
	 */
	public RateioDDA incluirListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws BancoobException {
		if (ObjectUtil.isEmpty(lista)) {
			throw new ComumNegocioException("integracaocip.parametro.nao.informado", "lista");
		}

		RateioDDA rateioDDA = dao.obterRateioAtual();

		// Se não existe rateio será criado um novo
		if (ObjectUtil.isNull(rateioDDA)) {
			rateioDDA = criarRateio();
		}

		// O rateio deve estar na situação aguardando aprovação
		if (!rateioDDA.getSituacaoRateio().getCodSituacaoRateio().equals(SituacaoRateio.AGUARDANDO_APROVACAO)) {
			throw new ComumNegocioException("operacional.rateio.nao.possivel.incluir.evento");
		}

		UsuarioBancoob usuario = getUsuario();

		dao.incluirListaEventoConsolidadoRateio(lista, rateioDDA.getId(), Integer.parseInt(usuario.getIdInstituicao()),
				usuario.getLogin());

		return rateioDDA;
	}

	/**
	 * Método responsável por criar um novo rateio
	 * 
	 * @return
	 * @throws BancoobException
	 */
	private RateioDDA criarRateio() throws BancoobException {
		RateioDDA rateioDDA = new RateioDDA();
		rateioDDA.setDataHoraInclusao(new DateTimeDB());

		SituacaoRateio situacao = em.find(SituacaoRateio.class, SituacaoRateio.AGUARDANDO_APROVACAO);

		rateioDDA.setSituacaoRateio(situacao);

		dao.incluir(rateioDDA);

		return rateioDDA;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#removerListaEventoConsolidadoRateio(java.util.List)
	 */
	public void removerListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws BancoobException {
		if (ObjectUtil.isEmpty(lista)) {
			throw new ComumNegocioException("integracaocip.parametro.nao.informado", "lista");
		}

		dao.removerListaEventoConsolidadoRateio(lista);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#atualizarEventoConsolidado(java.math.BigDecimal,
	 *      java.util.List)
	 */
	public void atualizarEventoConsolidado(BigDecimal desvioPadrao, List<EventoRateioDto> lista)
			throws BancoobException {
		if (ObjectUtil.isEmpty(desvioPadrao)) {
			parametroDAO.atualizarValorParametro(ParametroDDA.DESVIO_PADRAO_RATEIO_TARIFAS, Constantes.ID_SICOOB,
					Constantes.STRING_NUMERO_0);
		} else {
			parametroDAO.atualizarValorParametro(ParametroDDA.DESVIO_PADRAO_RATEIO_TARIFAS, Constantes.ID_SICOOB,
					desvioPadrao.toString());
		}

		if (!ObjectUtil.isEmpty(lista)) {
			dao.atualizarEventoConsolidado(lista);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#aprovarRateio(java.lang.String,
	 *      java.lang.Integer)
	 */
	public RateioDDA aprovarRateio(String idUsuarioAprovacao, Integer idInstituicaoUsuarioAprovacao)
			throws BancoobException {
		RateioDDA rateioDDA = dao.obterRateioAtual();

		// Se não trouxer um rateio ou se a situação for diferente de "aguardando
		// aprovação"
		if (ObjectUtil.isNull(rateioDDA)
				|| !rateioDDA.getSituacaoRateio().getCodSituacaoRateio().equals(SituacaoRateio.AGUARDANDO_APROVACAO)) {
			throw new ComumNegocioException("operacional.nao.existe.rateio.aguardando.aprovacao");
		}
		// Verifica se existe algum rateio em processamento
		else if (dao.existeRateioProcessando()) {
			throw new ComumNegocioException("operacional.rateio.em.processamento");
		}
		// Se não possuir eventos adicionados ao rateio
		else if (!dao.possuiListaEventoConsolidado(rateioDDA.getId())) {
			throw new ComumNegocioException("operacional.rateio.nao.possui.evento.consolidado");
		}

		rateioDDA.setDataHoraAprovacao(new DateTimeDB());
		rateioDDA.setIdInstituicaoUsuarioAprovacao(idInstituicaoUsuarioAprovacao);
		rateioDDA.setIdUsuarioAprovacao(idUsuarioAprovacao);

		SituacaoRateio situacao = em.find(SituacaoRateio.class, SituacaoRateio.APROVADO_PARA_EFETIVACAO);

		rateioDDA.setSituacaoRateio(situacao);

		dao.alterar(rateioDDA);

		return rateioDDA;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#cancelarAprovacaoRateio()
	 */
	public RateioDDA cancelarAprovacaoRateio() throws BancoobException {
		RateioDDA rateioDDA = dao.obterRateioAtual();

		// Se não trouxer um rateio ou se a situação for diferente de "aprovado para
		// efetivação"
		if (ObjectUtil.isNull(rateioDDA) || !rateioDDA.getSituacaoRateio().getCodSituacaoRateio()
				.equals(SituacaoRateio.APROVADO_PARA_EFETIVACAO)) {
			throw new ComumNegocioException("operacional.nao.existe.rateio.aprovado.efetivacao");
		}

		rateioDDA.setDataHoraAprovacao(null);
		rateioDDA.setIdInstituicaoUsuarioAprovacao(null);
		rateioDDA.setIdUsuarioAprovacao(null);

		SituacaoRateio situacao = em.find(SituacaoRateio.class, SituacaoRateio.AGUARDANDO_APROVACAO);

		rateioDDA.setSituacaoRateio(situacao);

		dao.alterar(rateioDDA);

		return rateioDDA;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#efetivarLancamentosRateioCCO(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto)
	 */
	public void efetivarLancamentosRateioCCO(RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto)
			throws ComumException, ComumNegocioException {
		getLogger().debug(Constantes.STR_SEPARACAO_2 + "Efetivando os lançamentos do rateio no CCO...");

		validarCamposObrigatorios(rateioCreditoLancamentoCCODto);

		List<LancamentoIntegracaoDto> listaLancamentosIntegracaoDto = getDAO().listarLancamentosRateioCCO(
				rateioCreditoLancamentoCCODto.getIdRateioDDA(), rateioCreditoLancamentoCCODto.getIdInstituicao(),
				rateioCreditoLancamentoCCODto.getIdRateioDDALancamento());

		if (!ObjectUtil.isEmpty(listaLancamentosIntegracaoDto)) {
			List<LancamentoIntegracaoCCODto> listaLancamentosIntegracaoCCODto = new ArrayList<LancamentoIntegracaoCCODto>();
            Date dataMovimentoBancoob = admDelegate.obterDataMovimentoBancoob();
			LancamentoIntegracaoRetDto lancamentoIntegracaoRetDto = null;

			for (LancamentoIntegracaoDto lancamentoIntegracaoDto : listaLancamentosIntegracaoDto) {
				lancamentoIntegracaoDto.setIdInstituicao(Constantes.NUM_COOP_0001);
				lancamentoIntegracaoDto
						.setDataLote(ObjectUtil.isNull(rateioCreditoLancamentoCCODto.getDataMovimentoBancoob())
								? dataMovimentoBancoob
								: rateioCreditoLancamentoCCODto.getDataMovimentoBancoob());
				lancamentoIntegracaoDto.setIdProduto(Constantes.ID_PRODUTO_CONTA_CORRENTE);
				lancamentoIntegracaoDto.setBolVerificaSaldo(Boolean.FALSE);
				lancamentoIntegracaoDto.setBolVerificaContaAnt(Boolean.FALSE);
				lancamentoIntegracaoDto.setBolConsideraLimite(Boolean.FALSE);

				LancamentoIntegracaoCCODto lancamentoIntegracaoCCODto = new LancamentoIntegracaoCCODto();

				if (!Arrays.asList(Constantes.ID_BANCOOB, Constantes.ID_SICOOB)
						.contains(rateioCreditoLancamentoCCODto.getIdInstituicao())) {
					defineInstituicaoTransferenciaDebito(lancamentoIntegracaoDto, GRAVAR_CONTA_CONVEIO_CC,
							rateioCreditoLancamentoCCODto);
                    Integer numCooperativa = obterNumCooperativa(lancamentoIntegracaoDto.getIdInstituicao());
                    lancamentoIntegracaoRetDto = gravarLancamentoIntegracaoSP(numCooperativa, lancamentoIntegracaoDto);
                    defineInstituicaoTransferenciaDebito(lancamentoIntegracaoDto,
							lancamentoIntegracaoRetDto.getCodRetorno(), rateioCreditoLancamentoCCODto);
				}

				lancamentoIntegracaoCCODto.setLancamentoIntegracaoDto(lancamentoIntegracaoDto);
				lancamentoIntegracaoCCODto.setLancamentoIntegracaoRetDto(lancamentoIntegracaoRetDto);

				listaLancamentosIntegracaoCCODto.add(lancamentoIntegracaoCCODto);
			}

			getDAO().atualizarLancamentosRateioCCO(rateioCreditoLancamentoCCODto, listaLancamentosIntegracaoCCODto);
		}
		getLogger().debug(Constantes.STR_SEPARACAO_2 + "Lançamentos efetivados");
		atualizarRateio(rateioCreditoLancamentoCCODto);
	}

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @throws ComumException void
     */
    private Integer obterNumCooperativa(Integer idInstituicao) throws ComumException {
        return instituicaoDao.obterCooperativaPorInstituicao(idInstituicao);
    }

	/**
	 * @param rateioCreditoLancamentoCCODto
	 * @throws ComumNegocioException
	 * @throws ComumException
	 */
	public void atualizarRateio(RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto)
			throws ComumNegocioException, ComumException {
		if (rateioCreditoLancamentoCCODto
				.getCodSituacaoRateioLancamento() == SituacaoRateioLancamento.EFETIVADO_COM_AJUSTE
				|| rateioCreditoLancamentoCCODto
						.getCodSituacaoRateioLancamento() == SituacaoRateioLancamento.EFETIVADO_MANUALMENTE) {
			SituacaoRateioCreditoCCODto situacaoRateioCreditoCCODto = dao
					.listarRateiosCreditoAtualizacaoCCO(rateioCreditoLancamentoCCODto.getIdRateioDDA());
			if (situacaoRateioCreditoCCODto.getQtdErroEfetivacao() == Constantes.INTEGER_ZERO) {
				getAtualizarSituacaoRateioCCODelegate().atualizarSituacaoRateioCCO(
						situacaoRateioCreditoCCODto.getIdRateioDDA(), SituacaoRateio.EFETIVADO);
				getLogger().debug(Constantes.STR_SEPARACAO_2 + "Rateio efetivado");
			}
		}
	}

	/**
	 * @param lancamentoIntegracaoDto
	 * @param codRetorno
	 * @param rateioCreditoLancamentoCCODto
	 * @throws ComumException
	 */
	private void defineInstituicaoTransferenciaDebito(LancamentoIntegracaoDto lancamentoIntegracaoDto,
			Integer codRetorno, RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto) throws ComumException {
		if (rateioCreditoLancamentoCCODto
				.getCodSituacaoRateioLancamento() == SituacaoRateioLancamento.EFETIVADO_COM_AJUSTE) {
			if (codRetorno == GRAVAR_RETORNO_ERRO_DB2) {
				InstituicaoDto instituicaoDto = instituicaoDao
						.obterInstituicao(rateioCreditoLancamentoCCODto.getIdInstituicao());
				lancamentoIntegracaoDto.setIdInstituicaoTransferenciaDebito(null);
				lancamentoIntegracaoDto.setNumContaCorrente(instituicaoDto.getNumContaConvenio());
			} else if (codRetorno == GRAVAR_RETORNO_EFETIVADO_DB2) {
				lancamentoIntegracaoDto.setIdInstituicaoTransferenciaDebito(
						rateioCreditoLancamentoCCODto.getIdInstituicaoTransferenciaDebito());
				lancamentoIntegracaoDto.setNumContaCorrente(rateioCreditoLancamentoCCODto.getNumContaLancamentoCCO());
			} else if (codRetorno == GRAVAR_CONTA_CONVEIO_CC) {
				lancamentoIntegracaoDto.setIdInstituicaoTransferenciaDebito(
						rateioCreditoLancamentoCCODto.getIdInstituicaoTransferenciaDebito());
				lancamentoIntegracaoDto.setNumContaCorrente(rateioCreditoLancamentoCCODto.getNumContaLancamentoCCO());
			}
		}
	}

	/**
	 * Método responsável por verificar a ocorrência dos campos obrigatórios
	 * 
	 * @param rateioCreditoLancamentoCCODto
	 * @return
	 * @throws ComumNegocioException
	 */
	private void validarCamposObrigatorios(RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto)
			throws ComumNegocioException {
		if (ObjectUtil.isNull(rateioCreditoLancamentoCCODto)
				|| ObjectUtil.isEmpty(rateioCreditoLancamentoCCODto.getIdRateioDDA())
				|| ObjectUtil.isEmpty(rateioCreditoLancamentoCCODto.getIdInstituicao())) {
			throw new ComumNegocioException("operacional.rateiotarifascip.efetivacao.parametro.obrigatorio");
		}
	}

    /**
     * Método responsável por realizar a chamado do CCO para efetivar o lançamento do rateio na conta de convênio da cooperativa
     * 
     * @param numCooperativa
     * 
     * @param LancamentoIntegracaoDto
     * @return LancamentoIntegracaoRetDto
     * @throws ComumException
     * @throws ComumNegocioException
     */
    public LancamentoIntegracaoRetDto gravarLancamentoIntegracaoSP(Integer numCooperativa, LancamentoIntegracaoDto lancamentoIntegracaoDto)
            throws ComumException {
        return rateioDDALancamentoLegadoDao.gravarLancamentoIntegracaoSP(numCooperativa, lancamentoIntegracaoDto);
    }

	/**
	 * Método responsável por realizar a chamado do CCO para efetivar o lançamento
	 * do rateio na conta de convênio da cooperativa
	 * 
	 * @param LancamentoIntegracaoDto
	 * @return LancamentoIntegracaoRetDto
	 * @throws ComumException
	 */
	public LancamentoIntegracaoRetDto gravarLancamentoIntegracao(LancamentoIntegracaoDto lancamentoIntegracaoDto)
			throws ComumException {
		return getContaCorrenteDelegate().gravarLancamentoIntegracao(lancamentoIntegracaoDto);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#atualizarSituacaoRateioCreditoCCO(java.lang.Long,
	 *      java.lang.Long)
	 */
	public void atualizarSituacaoRateioCreditoCCO(Long idRateioDDA, Long codSituacaoRateio)
			throws ComumException, ComumNegocioException {
		getLogger().debug(Constantes.STR_SEPARACAO_2 + "Atualizando a situação do rateio de crédito...");

		if (ObjectUtil.isEmpty(idRateioDDA) || ObjectUtil.isEmpty(codSituacaoRateio)) {
			throw new ComumNegocioException("operacional.rateiotarifascip.situacao.parametro.obrigatorio");
		}

		getDAO().atualizarSituacaoRateioCreditoCCO(idRateioDDA, codSituacaoRateio);

		getLogger().debug(Constantes.STR_SEPARACAO_2 + "Situação do rateio de crédito atualizada");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#transferenciaDebito(java.lang.Long,
	 *      java.lang.Integer, long)
	 */
	public Long transferenciaDebito(Long idRateioDDALanc, Integer numeroCooperativa, long codSituacaoLancamento)
			throws BancoobException {
		validaTransferenciaDebito(idRateioDDALanc, numeroCooperativa);
		efetivacaoManualAjuste(idRateioDDALanc, numeroCooperativa);
		return verificaSucessoEfetivacao(idRateioDDALanc);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioTarifasCipServico#efetivacaoManualLancamento(java.lang.Long)
	 */
	public Long efetivacaoManualLancamento(Long idRateioDDALanc) throws BancoobException {
		efetivacaoManual(idRateioDDALanc);
		return verificaSucessoEfetivacao(idRateioDDALanc);
	}
    /**
     * @param idRateioDDALanc
     * @param numeroCooperativa
     * @throws BancoobException
     */
    private void validaTransferenciaDebito(Long idRateioDDALanc, Integer numeroCooperativa) throws BancoobException {
    	RateioDDALancamento lancamento = lancamentoDao.obterLancamento(idRateioDDALanc);
    	validaSeErroEfetivacao(lancamento);
        	InstituicaoDto instituicaoDto = instituicaoDao.obterInstituicaoPorCooperativa(numeroCooperativa);
    	if(instituicaoDto.getIdInstituicao().equals(lancamento.getIdInstituicao())){
    		throw new ComumNegocioException("operacional.rateio.lancamento.transferenciadebito.erro.mesmacoop");	
    	}else if(!ObjectUtil.isEmpty(lancamento.getNumContaLancamentoCCO())){
    		if(instituicaoDto.getIdInstituicao().equals(lancamento.getIdInstituicaoTransferenciaDebito())){
    			throw new ComumNegocioException("operacional.rateio.lancamento.transferenciadebito.erro.mesmacoop");
    		}
    	}
    }
    

    /**
     * Método responsável por
     * 
     * @param visualizaRateioTarifaDto
     * @return
     * @throws BancoobException Map<String,Object>
     * 
     */
    @SuppressWarnings("static-access")
    private Map<String, Object> getParametrosVisualizarRateioTarifa(VisualizaRateioTarifaDto visualizaRateioTarifaDto) throws BancoobException {
        Map<String, Object> parametros = null;
        parametros = new HashMap<>();
        // Validacao vinda do negocial antigo
        if (!ObjectUtil.isNull(visualizaRateioTarifaDto.getIdInstituicaoSingular())) {

            Integer cooperativaCentral = ObjectUtil.isNull(visualizaRateioTarifaDto.getIdInstituicaoCentral()) ? null
                    : instituicaoDao.obterCooperativaPorInstituicao(visualizaRateioTarifaDto.getIdInstituicaoCentral());
            Integer cooperativaSingular = ObjectUtil.isNull(visualizaRateioTarifaDto.getIdInstituicaoSingular()) ? null
                    : instituicaoDao.obterCooperativaPorInstituicao(visualizaRateioTarifaDto.getIdInstituicaoSingular());

            parametros.put(PARAM_COOPERATIVA_CENTRAL, cooperativaCentral);
            parametros.put(PARAM_COOPERATIVA_SINGULAR, cooperativaSingular);

        }

        if (!ObjectUtil.isNull(visualizaRateioTarifaDto.getCodSituacaoRateioLancamento())) {
            String descSituacaoLancamento = lancamentoDao.obterSituacaoRateioLancamento(visualizaRateioTarifaDto.getCodSituacaoRateioLancamento())
                    .getDescSituacaoRateioLancamento();
            if (!ObjectUtil.isEmpty(descSituacaoLancamento)) {
                parametros.put(DESC_SITUACAO_LANCAMENTO, descSituacaoLancamento);
            }
        }

        return parametros;
    }

    /**
     * Método responsável por
     * 
     * @param visualizaRateioTarifaDto
     * @return
     * @throws BancoobException Map<String,Object>
     * 
     */
    @SuppressWarnings("static-access")
    private Map<String, Object> getParametrosRelatorioDadosRateio(VisualizaRateioTarifaDto visualizaRateioTarifaDto) throws BancoobException {
        Map<String, Object> parametros = new HashMap<>();

        parametros.put(TOTAL_DTO, visualizaRateioTarifaDto.getTotalDadosRateio());

        if (!ObjectUtil.isEmpty(visualizaRateioTarifaDto.getCodEventoTarifavel())) {
            EventoTarifavelDDA eventoTarifavelDDA = eventoTarifavelDDADao.obterEventoTarifavelDDA(visualizaRateioTarifaDto.getCodEventoTarifavel());
            parametros.put(DESC_EVENTO_TARIFAVEL, eventoTarifavelDDA.getDescEventoTarifavelExtrato());
        }

        return parametros;
    }
    
    /**
     * Método responsável por
     * 
     * @param controleRateioDto
     * @return
     * @throws BancoobException Map<String,Object>
     * 
     */
    @SuppressWarnings("static-access")
    private Map<String, Object> getParametrosDetalhesRateio(ControleRateioDto controleRateioDto) throws BancoobException {
        Map<String, Object> parametros = null;
        parametros = new HashMap<>();

        parametros.put(CONTROLE_RATEIO, controleRateioDto);

        return parametros;
    }
    
    /**
     * Método responsável por
     * 
     * @param controleRateioDto
     * @param consultaEventoRateioDto
     * @return
     * @throws BancoobException Map<String,Object>
     * 
     */
    @SuppressWarnings("static-access")
    private Map<String, Object> getParametrosEventosDisponiveis(ControleRateioDto controleRateioDto, ConsultaEventoRateioDto consultaEventoRateioDto) throws BancoobException {
        Map<String, Object> parametros = null;
        parametros = new HashMap<>();

        parametros.put(CONTROLE_RATEIO, controleRateioDto);
        parametros.put(CONSULTA_DTO, consultaEventoRateioDto);

        return parametros;
    }

	
    /**
     * Método responsável por
     * 
     * @param codSituacaoRateioLancamento
     * @throws ComumNegocioException void
     * @throws ComumException
     * 
     */
    private void validaSeErroEfetivacao(RateioDDALancamento lancamento) throws ComumNegocioException {
        if (lancamento.getSituacaoRateioLancamento().getCodSituacaoRateioLancamento() != SituacaoRateioLancamento.ERRO_EFETIVACAO) {
        	throw new ComumNegocioException("operacional.rateio.lancamento.erro.erroefetivacao.lancamento");
        }else if(lancamento.getRateioDDA().getSituacaoRateio().getCodSituacaoRateio() != SituacaoRateio.ERRO_EFETIVACAO){
        	throw new ComumNegocioException("operacional.rateio.lancamento.erro.erroefetivacao.rateio");
        }
    }
 
	/**
	 * @param idRateioDDALanc
	 * @return
	 * @throws BancoobException
	 */
	private Long verificaSucessoEfetivacao(Long idRateioDDALanc) throws BancoobException {
		Long codSituacaoLancamento = lancamentoDao.obterSituacaoLancamento(idRateioDDALanc);
		if (codSituacaoLancamento == SituacaoRateioLancamento.ERRO_EFETIVACAO) {
			return SituacaoRateioLancamento.ERRO_EFETIVACAO;
		} else if (codSituacaoLancamento == SituacaoRateioLancamento.EFETIVADO_MANUALMENTE) {
			return SituacaoRateioLancamento.EFETIVADO_MANUALMENTE;
		} else if (codSituacaoLancamento == SituacaoRateioLancamento.EFETIVADO_COM_AJUSTE) {
			return SituacaoRateioLancamento.EFETIVADO_COM_AJUSTE;
		}
		return Constantes.LONG_ZERO;
	}

	/**
	 * Método responsável por
	 * 
	 * @param idRateioDDALanc
	 * @param lancamento
	 * @throws BancoobException
	 * 
	 */
	private void efetivacaoManualAjuste(Long idRateioDDALanc, Integer numeroCooperativa) throws BancoobException {
		InstituicaoDto instituicaoDto = instituicaoDao.obterInstituicaoPorCooperativa(numeroCooperativa);
		RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto = preparaCamposRateioCreditoLancamentoCCCO(
				idRateioDDALanc);
		rateioCreditoLancamentoCCODto.setCodSituacaoRateioLancamento(SituacaoRateioLancamento.EFETIVADO_COM_AJUSTE);
		rateioCreditoLancamentoCCODto.setNumContaLancamentoCCO(instituicaoDto.getNumContaConvenio());
		rateioCreditoLancamentoCCODto.setIdInstituicaoTransferenciaDebito(instituicaoDto.getIdInstituicao());
		efetivarLancamentosRateioCCO(rateioCreditoLancamentoCCODto);
	}

	/**
	 * @param idRateioDDALanc
	 * @return
	 * @throws BancoobException
	 */
	private RateioCreditoLancamentoCCODto preparaCamposRateioCreditoLancamentoCCCO(Long idRateioDDALanc)
			throws BancoobException {
		RateioDDALancamento lancamento = lancamentoDao.obterLancamento(idRateioDDALanc);
		validaSeErroEfetivacao(lancamento);

		RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto = new RateioCreditoLancamentoCCODto();
		rateioCreditoLancamentoCCODto.setIdRateioDDALancamento(lancamento.getId());
		rateioCreditoLancamentoCCODto.setIdInstituicao(lancamento.getIdInstituicao());
		rateioCreditoLancamentoCCODto.setIdRateioDDA(lancamento.getRateioDDA().getId());
		return rateioCreditoLancamentoCCODto;
	}

	/**
	 * @param idRateioDDALanc
	 * @throws BancoobException
	 */
	private void efetivacaoManual(Long idRateioDDALanc) throws BancoobException {
		RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto = preparaCamposRateioCreditoLancamentoCCCO(
				idRateioDDALanc);
		rateioCreditoLancamentoCCODto.setCodSituacaoRateioLancamento(SituacaoRateioLancamento.EFETIVADO_MANUALMENTE);
		efetivarLancamentosRateioCCO(rateioCreditoLancamentoCCODto);
	}
}
