package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoTarifavelDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDATarifa;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * EventoTarifavelDDADaoImpl é responsável por
 * 
 * @author samuell.ramos
 */
public class EventoTarifavelDDADaoImpl extends OperacionalCrudDaoDB2<EventoTarifavelDDATarifa> implements EventoTarifavelDDADao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.evento.tarifavel.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-evento-tarifavel";
    public static final int FIRST_INDEX_EVENTO_TARIFAVEL = 0;

    /**
     * 
     */
    public EventoTarifavelDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, EventoTarifavelDDATarifa.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao#listarEventoTarifavelDDA()
     */
    public List<EventoTarifavelDDA> listarEventoTarifavelDDA() throws ComumException {
        return listar("LISTA_EVENTO_TARIFAVEL");
    }
    
    /* (non-Javadoc)
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao#obterEventoTarifavelDDA()
     */
    public EventoTarifavelDDA obterEventoTarifavelDDA(Integer codEventoTarifavel) throws ComumException {
        return obterDados("LISTA_EVENTO_TARIFAVEL", parametroCodEventoTarifavel(codEventoTarifavel));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao#listarEventoTarifavelDDAParcial()
     */
    public List<EventoTarifavelDDA> listarEventoTarifavelDDAParcial() throws ComumException {
        return listar("LISTA_EVENTO_TARIFAVEL_PARCIAL");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao#obterEventoTarifavelDDATarifa(java.lang.Long)
     */
    public EventoTarifavelDto obterEventoTarifavelDDATarifa(Long idEventoTarifavelDDATarifa) throws ComumException {
        return executaObterEventoTarifavelDDA("PESQUISA_EVENTO_TARIFAVEL", null, idEventoTarifavelDDATarifa, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao#obterUltimoStatusEventoTarifavel(java.lang.Integer,
     *      java.lang.Integer)
     */
    public EventoTarifavelDto obterUltimoStatusEventoTarifavel(Integer codEventoTarifavel, Integer codStatus) throws ComumException {
        return executaObterEventoTarifavelDDA("OBTER_ULTIMO_STATUS_EVENTO_TARIFAVEL", codEventoTarifavel, null, codStatus);
    }

    /**
     * Método responsável por
     * 
     * @param codEventoTarifavel
     * @param codStatus
     * @return
     * @throws ComumException List<EventoTarifavelDto>
     * 
     */
    public List<EventoTarifavelDto> pesquisaEventoTarifavelDDA(Integer codEventoTarifavel, Integer codStatus) throws ComumException {
        return executaListarEventoTarifavelDDA(codEventoTarifavel, null, codStatus);
    }

    /**
     * Método responsável por
     * 
     * @param codEventoTarifavel
     * @return
     * @throws ComumException Boolean
     * 
     */
    public Boolean existeProgramada(Integer codEventoTarifavel) throws ComumException {
        debug("### Verificando se já existe uma tarifa programada");
        debug("Parâmetro - EventoTarifavelDto: " + codEventoTarifavel);
        return existeRegistro("EXISTE_TARIFA_PROGRAMADA", parametroCodEventoTarifavel(codEventoTarifavel));
    }

    /**
     * Método responsável por
     * 
     * @param codEventoTarifavel
     * @return
     * @throws ComumException Boolean
     * 
     */
    public Boolean existeVigente(Integer codEventoTarifavel) throws ComumException {
        debug("### Verificando se já existe uma tarifa vigente");
        debug("Parâmetro - EventoTarifavelDto: " + codEventoTarifavel);
        return existeRegistro("EXISTE_TARIFA_VIGENTE", parametroCodEventoTarifavel(codEventoTarifavel));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao#existeRateio(Long, Integer)
     */
    public Boolean existeRateio(Long idEventoTarifavelDDATarifa, Integer codEventoTarifavel) throws ComumException {
        debug("### Verifica se a vigencia está com rateio.");
        debug("Parâmetro - idEventoTarifavelDDATarifa: " + idEventoTarifavelDDATarifa);
        debug("Parâmetro - codEventoTarifavel: " + codEventoTarifavel);
        Map<String, Object> parametro = new HashMap<String, Object>();
        parametro.put("idEventoTarifavelDDATarifa", idEventoTarifavelDDATarifa);
        parametro.put("codEventoTarifavel", codEventoTarifavel);
        return existeRegistro("EXISTE_VIGENCIA_EM_RATEIO", parametro);
    }

    /**
     * @param codEventoTarifavel void
     * @return
     * 
     */
    private Map<String, Object> parametroCodEventoTarifavel(Integer codEventoTarifavel) {
        Map<String, Object> parametro = new HashMap<String, Object>();
        parametro.put("codEventoTarifavel", codEventoTarifavel);
        return parametro;
    }

    /**
     * Método responsável por
     * 
     * @param codEventoTarifavel
     * @param idEventoTarifavelDDATarifa
     * @param codStatus
     * @return
     * @throws ComumException List<EventoTarifavelDto>
     * 
     */
    private List<EventoTarifavelDto> executaListarEventoTarifavelDDA(Integer codEventoTarifavel, Long idEventoTarifavelDDATarifa, Integer codStatus) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<EventoTarifavelDto> listaEventoTarifavel = new ArrayList<EventoTarifavelDto>();
        try {
            comando = getComando("PESQUISA_EVENTO_TARIFAVEL");
            comando.adicionarVariavel("codEventoTarifavel", codEventoTarifavel);
            comando.adicionarVariavel("idEventoTarifavelDDATarifa", idEventoTarifavelDDATarifa);
            comando.adicionarVariavel("codStatus", codStatus);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaEventoTarifavel.add(montarEventoTarifavelDto(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "Erro ao listar evento tarifavel");
            throw new OperacionalException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaEventoTarifavel;
    }

    /**
     * Método responsável por
     * 
     * @param nomeComando
     * @param codEventoTarifavel
     * @param idEventoTarifavelDDATarifa
     * @param codStatus
     * @return
     * @throws ComumException EventoTarifavelDto
     * 
     */
    private EventoTarifavelDto executaObterEventoTarifavelDDA(String nomeComando, Integer codEventoTarifavel, Long idEventoTarifavelDDATarifa, Integer codStatus)
            throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        EventoTarifavelDto eventoTarifavel = null;
        try {
            comando = getComando(nomeComando);
            comando.adicionarVariavel("codEventoTarifavel", codEventoTarifavel);
            comando.adicionarVariavel("idEventoTarifavelDDATarifa", idEventoTarifavelDDATarifa);
            comando.adicionarVariavel("codStatus", codStatus);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                eventoTarifavel = montarEventoTarifavelDto(rs);
            }
        } catch (SQLException e) {
            getLogger().erro(e, "Erro ao listar evento tarifavel");
            throw new OperacionalException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return eventoTarifavel;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException EventoTarifavelDto
     * 
     */
    private EventoTarifavelDto montarEventoTarifavelDto(ResultSet rs) throws SQLException {
        return new EventoTarifavelDto(rs.getInt("CODEVENTOTARIFAVEL"), rs.getString("DESCEVENTOTARIFAVELEXTRATO"), rs.getDate("DATAINICIOVIGENCIA"), rs.getDate("DATAFIMVIGENCIA"),
                rs.getBoolean("BOLTARIFAVEL"),
                rs.getBigDecimal("VALORCIP"), rs.getBigDecimal("VALORBANCOOB"), rs.getString("DESCTIPOEVENTOTARIFAVEL"), rs.getString("STATUS"),
                rs.getLong("IDEVENTOTARIFAVELDDATARIFA"));
    }

}
