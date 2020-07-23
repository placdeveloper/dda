package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTarifasDDAPagasDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDALancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateioLancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao;

/**
 * RateioDDALancamentoDaoImpl é responsável por
 * 
 * @author rodrigo.neri
 */
public class RateioDDALancamentoDaoImpl extends OperacionalCrudDaoDB2<RateioDDALancamento> implements RateioDDALancamentoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.rateioddalancamento.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-rateioddalancamento";

    public static final String PESQUISAR = "PESQUISAR_RATEIODDALANCAMENTO";
    public static final String OBTER_NUMERO_REGISTROS = "OBTER_NUMERO_REGISTROS_RATEIODDALANCAMENTO";

    public RateioDDALancamentoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, RateioDDALancamento.class, PESQUISAR, OBTER_NUMERO_REGISTROS);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#listarSituacaoRateioLancamento()
     */
    public List<SituacaoRateioLancamento> listarSituacaoRateioLancamento() throws ComumException {
        return super.listar("LISTA_SITUACAO_RATEIO_LANCAMENTO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#obterSituacaoRateioLancamento(java.lang.Long)
     */
    public SituacaoRateioLancamento obterSituacaoRateioLancamento(Long codSituacaoRateioLancamento) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("codSituacaoRateioLancamento", codSituacaoRateioLancamento);
        return super.obterDados("OBTER_SITUACAO_RATEIO_LANCAMENTO", parametros);
    }


    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#listarLancamentosTarifas(java.lang.Long)
     */
    public List<LancamentosTarifasDDADto> listarLancamentosTarifas(Long idRateioDDALancamento) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>(1);
        parametros.put("idRateioDDALancamento", idRateioDDALancamento);
        return super.listar("LISTAR_LANCAMENTOS_TARIFAS", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#obterLancamento(java.lang.Long)
     */
    public RateioDDALancamento obterLancamento(Long idLancamento) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>(1);
        parametros.put("idLancamento", idLancamento);
        return super.obterDados("OBTER_RATEIO_DDA_LANCAMENTO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#listarTarifasDDAPagas(br.com.bancoob.negocio.dto.ConsultaDto)
     */
    public List<ConsultaTarifasDDAPagasDto> listarTarifasDDAPagas(ConsultaDto<ConsultaTarifasDDAPagasDto> dto) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>(1);
        parametros.put("criterios", dto);

        return super.listar(PESQUISAR, parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#pesquisarMovimentoPaginado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto)
     */
    public List<ConsultaMovimentoSicoobDDADto> pesquisarMovimentoPaginado(ConsultaMovimentoSicoobDDADto consultaMovimentoSicoobDDADto) throws ComumException {
        return pesquisarMovimentoPaginado(consultaMovimentoSicoobDDADto, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#pesquisarMovimentoPaginado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto,
     *      java.lang.Integer, java.lang.Integer)
     */
    public List<ConsultaMovimentoSicoobDDADto> pesquisarMovimentoPaginado(ConsultaMovimentoSicoobDDADto consultaMovimentoSicoobDDADto, Integer pagina, Integer tamanhoPagina)
            throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("consultaDto", consultaMovimentoSicoobDDADto);
        parametros.put("pagina", pagina);
        parametros.put("tamanhoPagina", tamanhoPagina);

        Comando comando = null;
        ResultSet rs = null;
        Connection conn = null;

        List<ConsultaMovimentoSicoobDDADto> lista = null;

        try {
            conn = estabelecerConexao();
            comando = getComando("PESQUISAR_MOVIMENTO_SICOOBDDA", parametros);
            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                lista = new ArrayList<ConsultaMovimentoSicoobDDADto>();

                do {
                    ConsultaMovimentoSicoobDDADto dto = new ConsultaMovimentoSicoobDDADto();
                    dto.setNumCodBarras(rs.getString("NUMCODIGOBARRA"));
                    dto.setCpfCnpjBeneficiario(rs.getString("NUMCPFCNPJBENEFICIARIO"));
                    dto.setNomeBeneficiario(rs.getString("NOMEBENEFICIARIO"));
                    dto.setValor(rs.getBigDecimal("VALORBOLETO"));

                    Date dataVencimento = rs.getDate("DATAVENCIMENTO");

                    if (!ObjectUtil.isNull(dataVencimento)) {
                        dto.setDataVencimento(new DateTimeDB(dataVencimento.getTime()));
                    }

                    Date data = rs.getDate("DATAHORAMENSAGEM");

                    if (!ObjectUtil.isNull(data)) {
                        dto.setDataEntrada(new DateTimeDB(data.getTime()));
                    }

                    lista.add(dto);
                } while (rs.next());
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#countMovimento(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto)
     */
    public Integer countMovimento(ConsultaMovimentoSicoobDDADto consultaMovimentoSicoobDDADto) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("consultaDto", consultaMovimentoSicoobDDADto);

        Comando comando = null;
        ResultSet rs = null;
        Connection conn = null;

        Integer total = 0;

        try {
            conn = estabelecerConexao();
            comando = getComando("COUNT_MOVIMENTO_SICOOBDDA", parametros);

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return total;
    }
    
	/* (non-Javadoc)
	 * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#obterSituacaoLancamento(java.lang.Long)
	 */
    public Long obterSituacaoLancamento(Long idLancamento) throws ComumException {
    Comando comando = null;
    Connection conn = null;
    ResultSet rs = null;
    Long codSituacaoLancamento = Constantes.LONG_ZERO;
    try {
        comando = getComando("OBTER_STATUS_DDA_LANCAMENTO");
        comando.adicionarVariavel("idLancamento", idLancamento);
        comando.configurar();
        getLogger().debug(comando.getSqlEmUso());
        conn = estabelecerConexao();
        rs = comando.executarConsulta(conn);
        while (rs.next()) {
        	codSituacaoLancamento = rs.getLong(1);
        }
    } catch (SQLException e) {
        getLogger().erro(e, "Erro ao obter Rateio Lancamento");
            throw new ComumException(e);
    } finally {
        fecharResultSet(rs);
        fecharComando(comando);
        fecharConexao(conn);
    }
    return codSituacaoLancamento;
	}
    
}