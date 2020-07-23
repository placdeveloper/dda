package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao;

/**
 * @author samuell.ramos
 */
public class TipoGradeHorariaDaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements TipoGradeHorariaDao {

    private static final String COD_TIPO_GRADE_HORARIA = "codTipoGradeHoraria";
    private static final String COD_SUB_TIPO_GRADE = "codSubTipoGrade";
    private static final String COD_TIPO_GRADE_ORIGEM = "codTipoGradeOrigem";
    private static final String DESC_SUB_TIPO_GRADE = "descSubTipoGrade";
    private static final String DESC_TIPO_GRADE_HORARIA = "descTipoGradeHoraria";
    private static final String DATA_ULTIMA_GRADE = "dataUltimaGrade";

    public static final String ERRO_LISTAR_TIPO_CARGA_HORARIA = "Erro ao listar carga horária";
    public static final String ERRO_OBTER_TIPO_CARGA_HORARIA = "Erro ao obter carga horária";

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.tipogradehoraria.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-tipogradehoraria";
    public static final String COMANDOS = "comandos-sicoobdda-operacional";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public TipoGradeHorariaDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, null, COMANDOS, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#listarTipoGradeHorariaPorCodigoSubtipo(java.lang.Short)
     */
    public List<TipoGradeHorariaDto> listarTipoGradeHorariaPorCodigoSubtipo(Short codSubTipoGrade) throws OperacionalException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoGradeHorariaDto> listaTipoGradeHoraria = new ArrayList<TipoGradeHorariaDto>();
        try {
            comando = getComando("LISTAR_TIPO_GRADE_HORARIA");
            comando.adicionarVariavel(COD_SUB_TIPO_GRADE, codSubTipoGrade);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaTipoGradeHoraria.add(montarTipoGradeHorariaDto(rs));
            }
        } catch (PersistenceException e) {
            getLogger().erro(e, ERRO_LISTAR_TIPO_CARGA_HORARIA);
            throw new OperacionalException(e);
        } catch (SQLException e) {
            throw new OperacionalException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaTipoGradeHoraria;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#listarTipoGradeHorariaPersonalizadaSemGradeHoraria(br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public List<TipoGradeHorariaDto> listarTipoGradeHorariaPersonalizadaSemGradeHoraria(DateTimeDB dataReferencia) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoGradeHorariaDto> listaTipoGradeHoraria = new ArrayList<TipoGradeHorariaDto>();
        try {
            conn = estabelecerConexao();
            comando = getComando("LISTAR_TIPO_GRADE_HORARIA_SEM_GRADE_HORARIA_PERSONALIZADA");
            comando.adicionarVariavel("dataReferencia", dataReferencia);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaTipoGradeHoraria.add(montarTipoGradeHorariaCompleta(rs));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaTipoGradeHoraria;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#listarTipoGradeHorariaDDA()
     */
    public List<TipoGradeHoraria> listarTipoGradeHorariaDDA() throws ComumException {
        return listar("LISTAR_TIPO_GRADE_HORARIA_DDA", getMapaParametro(SubTipoGrade.GRADE_DDA, COD_SUB_TIPO_GRADE));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#listarCodigosTipoGradeHoraria()
     */
    public List<TipoGradeHoraria> listarCodigosTipoGradeHoraria() throws ComumException {
        return listar("LISTAR_CODIGO_TIPO_GRADE_HORARIA");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#obterTipoGradeHoraria(java.lang.String)
     */
    public TipoGradeHoraria obterTipoGradeHoraria(String codTipoGradeHoraria) throws ComumException {
        return obterDados("OBTER_TIPO_GRADE_HORARIA", getMapaParametro(codTipoGradeHoraria, COD_TIPO_GRADE_HORARIA));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#obterCountTipoGradeHoraria(java.lang.String)
     */
    public Long obterCountTipoGradeHoraria(String codTipoGradeHoraria) throws ComumException {
        return obterDados("OBTER_COUNT_TIPO_GRADE_HORARIA", getMapaParametro(codTipoGradeHoraria, COD_TIPO_GRADE_HORARIA));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#listarSubTipoGrade()
     */
    public List<SubTipoGrade> listarSubTipoGrade() throws ComumException {
        return listar("LISTAR_SUBTIPO_GRADE");
    }

    /**
     * @param codTipoGradeHoraria
     * @return
     * @throws ComumException
     */
    public Boolean isExisteEmTipoMensagem(String codTipoGradeHoraria) throws ComumException {
        return existeRegistro("TIPO_GRADE_EXISTE_EM_TIPO_MENSAGME", getParametroCodTipoGrade(codTipoGradeHoraria));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#isExisteEmGradeHoraria(java.lang.String)
     */
    public Boolean isExisteEmGradeHoraria(String codTipoGradeHoraria) throws ComumException {
        return existeRegistro("TIPO_GRADE_EXISTE_EM_GRADE_HORARIA", getParametroCodTipoGrade(codTipoGradeHoraria));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao#isExisteEmGradeHoraria(java.lang.String)
     */
    public Boolean isExisteEmGradeOrigem(String codTipoGradeHoraria) throws ComumException {
        return existeRegistro("TIPO_GRADE_HORARIA_EXISTE_EM_GRADE_ORIGEM", getParametroCodTipoGrade(codTipoGradeHoraria));
    }

    /**
     * Método responsável por
     * 
     * @param codTipoGradeHoraria void
     * 
     */
    private Map<String, Object> getParametroCodTipoGrade(String codTipoGradeHoraria) {
        return getMapaParametro(codTipoGradeHoraria, COD_TIPO_GRADE_HORARIA);
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException TipoGradeHorariaDto
     * 
     */
    private TipoGradeHorariaDto montarTipoGradeHorariaCompleta(ResultSet rs) throws SQLException {
        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        tipoGradeHorariaDto.setCodTipoGradeHoraria(rs.getString(COD_TIPO_GRADE_HORARIA));
        tipoGradeHorariaDto.setDescTipoGradeHoraria(rs.getString(DESC_TIPO_GRADE_HORARIA));
        tipoGradeHorariaDto.setCodSubTipoGrade(rs.getShort(COD_SUB_TIPO_GRADE));
        tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(rs.getString(COD_TIPO_GRADE_ORIGEM));
        tipoGradeHorariaDto.setDataUltimaGrade(rs.getDate(DATA_ULTIMA_GRADE));
        return tipoGradeHorariaDto;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws OperacionalException
     * @throws SQLException ConvenioAlteracaoDDADto
     * 
     */
    private TipoGradeHorariaDto montarTipoGradeHorariaDto(ResultSet rs) throws SQLException {
        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        tipoGradeHorariaDto.setCodTipoGradeHoraria(rs.getString(COD_TIPO_GRADE_HORARIA));
        tipoGradeHorariaDto.setDescTipoGradeHoraria(rs.getString(DESC_TIPO_GRADE_HORARIA));
        tipoGradeHorariaDto.setDescSubTipoGrade(rs.getString(DESC_SUB_TIPO_GRADE));
        tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(rs.getString(COD_TIPO_GRADE_ORIGEM));

        return tipoGradeHorariaDto;
    }

}
