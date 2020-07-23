package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

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
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaLancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDALancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.RateioDDALancamentoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao;

/**
 * RateioDDALancamentoServicoEJB é responsável por
 * 
 * @author rodrigo.neri
 */
@Stateless
@Local(RateioDDALancamentoServicoLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RateioDDALancamentoServicoEJB extends OperacionalCrudServicoEJB<RateioDDALancamento> implements RateioDDALancamentoServicoLocal {

    private static final String CONSULTA = "consulta";
    private static final String INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO = "integracaocip.parametro.nao.informado";

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private RateioDDALancamentoDao dao;

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

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
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected RateioDDALancamentoDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioDDALancamentoServico#listarLancamentosTarifas(java.lang.Long)
     */
    public List<LancamentosTarifasDDADto> listarLancamentosTarifas(Long idRateioDDALancamento) throws BancoobException {
        if (ObjectUtil.isEmpty(idRateioDDALancamento)) {
            throw new ComumNegocioException(INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "idRateioDDALancamento");
        }

        return dao.listarLancamentosTarifas(idRateioDDALancamento);
    }

    /**
     * @param param
     * @param relatorio
     * @return
     * @throws BancoobException
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioTarifasDDAPagas(ConsultaTarifasDDAPagasDto consulta, UsuarioBancoobDTO usuario) throws BancoobException {
        if (ObjectUtil.isNull(consulta)) {
            throw new NegocioException(INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, CONSULTA);
        }

        ConsultaDto<ConsultaTarifasDDAPagasDto> dto = new ConsultaDto<>();
        dto.setFiltro(consulta);

        List<ConsultaTarifasDDAPagasDto> lista = dao.listarTarifasDDAPagas(dto);

        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA513, lista, usuario, getParametrosRelatorio(CONSULTA, consulta));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioDDALancamentoServico#configurarRelatorioLancamentosTarifas(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto,
     *      br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ListaLancamentosTarifasDDADto, br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioLancamentosTarifas(ConsultaTarifasDDAPagasDto consulta, ListaLancamentosTarifasDDADto listaLancamentosTarifasDDADto,
            UsuarioBancoobDTO usuario) throws BancoobException {

        if (ObjectUtil.isNull(consulta)) {
            throw new NegocioException(INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "consultaTarifasDDAPagasDto");
        } else if (ObjectUtil.isEmpty(listaLancamentosTarifasDDADto)) {
            throw new NegocioException(INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "listaLancamentosTarifasDDADto");
        }

        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA514, listaLancamentosTarifasDDADto, usuario, getParametrosRelatorio(CONSULTA, consulta));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioDDALancamentoServico#configurarRelatorioMovimentoSicoobDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto,
     *      br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto,
     *      br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto, br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioMovimentoSicoobDDA(ConsultaMovimentoSicoobDDADto consultaDto, ConsultaTarifasDDAPagasDto consultaTarifasDDAPagasDto,
            LancamentosTarifasDDADto lancamentosTarifasDDADto, UsuarioBancoobDTO usuario) throws BancoobException {

        if (ObjectUtil.isNull(consultaDto)) {
            throw new NegocioException(INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "consultaDto");
        } else if (ObjectUtil.isNull(consultaTarifasDDAPagasDto)) {
            throw new NegocioException(INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "consultaTarifasDDAPagasDto");
        } else if (ObjectUtil.isNull(lancamentosTarifasDDADto)) {
            throw new NegocioException(INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "lancamentosTarifasDDADto");
        }

        String numCodBarras = consultaDto.getNumCodBarras();

        if (!ObjectUtil.isEmpty(numCodBarras) && numCodBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            consultaDto.setNumLinhaDigitavel(numCodBarras);
            consultaDto.setNumCodBarras(LinhaDigitavelCodigoBarrasUtil.obterCodigoBarrasPorLinhaDigitavel(numCodBarras));
        }

        List<ConsultaMovimentoSicoobDDADto> lista = dao.pesquisarMovimentoPaginado(consultaDto);

        Map<String, Object> map = new HashMap<>();
        map.put("consultaDto", consultaDto);
        map.put("consultaTarifasDto", consultaTarifasDDAPagasDto);
        map.put("lancamento", lancamentosTarifasDDADto);

        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA515, lista, usuario, map);

    }

    /**
     * @param key
     * @param object
     * @return
     */
    private Map<String, Object> getParametrosRelatorio(String key, Object object) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, object);
        return map;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.RateioDDALancamentoServico#pesquisarMovimentoPaginado(br.com.bancoob.negocio.dto.ConsultaDto)
     */
    public ConsultaDto<ConsultaMovimentoSicoobDDADto> pesquisarMovimentoPaginado(ConsultaDto<ConsultaMovimentoSicoobDDADto> consultaDto) throws BancoobException {
        if (ObjectUtil.isNull(consultaDto)) {
            throw new ComumNegocioException(INTEGRACAOCIP_PARAMETRO_NAO_INFORMADO, "consultaDto");
        } else if (ObjectUtil.isEmpty(consultaDto.getTamanhoPagina())) {
            throw new ComumNegocioException("integracaocip.campo.obrigatorio", "tamanho da página");
        } else if (ObjectUtil.isNull(consultaDto.getPagina())) {
            throw new ComumNegocioException("integracaocip.campo.obrigatorio", "página");
        } else if (ObjectUtil.isNull(consultaDto.getFiltro())) {
            throw new ComumNegocioException("integracaocip.campo.obrigatorio", "filtro");
        } else if (!(consultaDto.getFiltro() instanceof ConsultaMovimentoSicoobDDADto)) {
            throw new ComumNegocioException("integracaocip.filtro.tipo.invalido");
        }

        ConsultaMovimentoSicoobDDADto consultaMovimentoSicoobDDADto = (ConsultaMovimentoSicoobDDADto) consultaDto.getFiltro();

        String numCodBarras = consultaMovimentoSicoobDDADto.getNumCodBarras();

        if (!ObjectUtil.isEmpty(numCodBarras)) {
            if (numCodBarras.trim().length() != Constantes.TAMANHO_CODIGO_BARRAS && numCodBarras.trim().length() != Constantes.TAMANHO_LINHA_DIGITAVEL) {
                throw new ComumNegocioException("integracaocip.linha.digitavel.codigo.barras.tamanho.invalido");
            } else if (!numCodBarras.matches("\\d+")) {
                throw new ComumNegocioException("integracaocip.linha.digitavel.codigo.barras.invalido");
            }
        }

        if (!ObjectUtil.isEmpty(numCodBarras) && numCodBarras.length() == Constantes.TAMANHO_LINHA_DIGITAVEL) {
            consultaMovimentoSicoobDDADto.setNumCodBarras(LinhaDigitavelCodigoBarrasUtil.obterCodigoBarrasPorLinhaDigitavel(numCodBarras));
        }

        Integer total = dao.countMovimento(consultaMovimentoSicoobDDADto);

        consultaDto.setTotalRegistros(total);

        if (total > 0) {
            consultaDto.setResultado(dao.pesquisarMovimentoPaginado(consultaMovimentoSicoobDDADto, consultaDto.getPagina(), consultaDto.getTamanhoPagina()));
        }
        return consultaDto;
    }

}
