package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaOrigemDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * GradeHorariaDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class GradeHorariaDaoImpl extends OperacionalCrudDaoDB2<GradeHoraria> implements GradeHorariaDao {

    private static final String COD_TIPO_GRADE_HORARIA = "codTipoGradeHoraria";
    private static final String DATA_REFERENCIA = "dataReferencia";

    private static final String ARQUIVO_QUERIES = "sicoobdda_operacional.gradehoraria.queries.xml";
    private static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-gradehoraria";

    private static final String COMANDOS = "PESQUISAR_GRADE_HORARIA";
    private static final String COMANDOS_QTD = "OBTER_QTD_GRADE_HORARIA";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public GradeHorariaDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, null, COMANDOS, COMANDOS_QTD);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao#listarGrades(java.lang.String, java.util.Date)
     */
    public List<GradeHorariaDto> listarGrades(String codTipoGradeHoraria, Date dataReferencia) throws ComumException {
        return listar("LISTA_GRADE_HORARIA_POR_CODTIPOGRADEHORARIA", getParametrosTipoGradeDataRef(codTipoGradeHoraria, dataReferencia));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao#removerGrades(java.lang.String, java.util.Date)
     */
    public void removerGrades(String codTipoGradeHoraria, Date dataReferencia) throws ComumException {
        executarUpdate("REMOVER_GRADE_HORARIA", getParametrosTipoGradeDataRef(codTipoGradeHoraria, dataReferencia));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao#obterGradeHoraria(java.lang.Long)
     */
    public GradeHorariaDto obterGradeHoraria(Long idGradeHoraria) throws ComumException {
        return obterDados("OBTER_GRADE_HORARIA", getMapaParametro(idGradeHoraria, "idGradeHoraria"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao#incluirGradeHorariaCIP(java.util.Date)
     */
    public void incluirGradeHorariaCIP(Date dataReferencia) throws ComumException {
        executarUpdate("INCLUIR_GRADE_HORARIA_CIP", getParametroDataReferencia(dataReferencia));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao#incluirGradeHorariaPersonalizada(java.util.Date, java.util.Date, java.lang.String)
     */
    public void incluirGradeHorariaPersonalizada(Date dataReferencia, Date dataUltimaGrade, String codTipoGradeHoraria) throws ComumException {
        Map<String, Object> parametros = getParametroDataReferencia(dataReferencia);
        parametros.put(COD_TIPO_GRADE_HORARIA, codTipoGradeHoraria);
        parametros.put("dataUltimaGrade", dataUltimaGrade);
        executarUpdate("INCLUIR_GRADE_HORARIA_ULTIMA_GRADE_VALIDA", parametros);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao#listarGradeHorariaPersonalizadaExtrapolada(java.util.Date)
     */
    public List<GradeHorariaOrigemDto> listarGradeHorariaPersonalizadaExtrapolada(Date dataReferencia) throws OperacionalException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<GradeHorariaOrigemDto> listaGradeHoraria = new ArrayList<GradeHorariaOrigemDto>();
        try {
            comando = getComando("LISTAR_GRADE_EXTRAPOLANDO_GRANDE_ORIGEM");
            comando.adicionarVariavel(DATA_REFERENCIA, dataReferencia);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaGradeHoraria.add(montarGradeHorariaDto(rs));
            }
        } catch (SQLException e) {
            throw new OperacionalException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaGradeHoraria;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao#excluirGradesHorariasAntigas()
     */
    public void excluirGradesHorariasAntigas() throws ComumException {
        executarUpdate("EXCLUIR_GRADES_ANTIGAS");
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException GradeHorariaOrigemDto
     * 
     */
    private GradeHorariaOrigemDto montarGradeHorariaDto(ResultSet rs) throws SQLException {
        return new GradeHorariaOrigemDto(rs.getDate("DATAULTIMAGRADE"), rs.getDate("DATAREFERENCIA"), rs.getString("CODTIPOGRADEHORARIA"), rs.getDate("DATAHORAABERTURA"),
                rs.getDate("DATAHORAFECHAMENTO"), rs.getString("CODTIPOGRADEHORARIAORIGEM"), rs.getDate("DATAHORAABERTURAORIGEM"), rs.getDate("DATAHORAFECHAMENTOORIGEM"));
    }

    /**
     * Método responsável por
     * 
     * @param codTipoGradeHoraria
     * @param dataReferencia
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getParametrosTipoGradeDataRef(String codTipoGradeHoraria, Date dataReferencia) {
        Map<String, Object> parametros = getMapaParametro(dataReferencia, DATA_REFERENCIA);
        parametros.put(COD_TIPO_GRADE_HORARIA, codTipoGradeHoraria);
        return parametros;
    }

    /**
     * Método responsável por
     * 
     * @param dataReferencia
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getParametroDataReferencia(Date dataReferencia) {
        return getMapaParametro(DateUtil.formatarDataSQL(dataReferencia), DATA_REFERENCIA);
    }
}
