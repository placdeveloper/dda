/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl
 * Arquivo:         InstituicaoDaoImpl.java
 * Data Criação:    30/09/2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.EnderecoInstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto.InstituicaoCooperativaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.CentralSingularVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.InstituicaoVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.UnidadeVo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * {@link InstituicaoDaoImpl} é responsável por incluir e recuperar o histórico de determinada entidade.
 * 
 * @author samuell.ramos
 */
public class InstituicaoDaoImpl extends ComumCrudDaoDB2<SicoobDDAEntidade> implements InstituicaoDao {

    private static final String ARQUIVO_QUERIES = "sicoobdda_comum.instituicao.queries.xml";
    private static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-instituicao";

    /**
     * Construtor
     */
    public InstituicaoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws SQLException
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#listaCentrais(java.lang.Long)
     */
    public List<CentralSingularVo> listaCentrais(Integer idInstituicao) throws ComumException {
        getLogger().debug("*******OBTER_LISTA_CENTRAIS*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rset = null;
        List<CentralSingularVo> lista = new ArrayList<>();

        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_CENTRAIS");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rset = comando.executarConsulta(conn);
            while (rset.next()) {
                CentralSingularVo vo = new CentralSingularVo();
                vo.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
                vo.setNumeroCooperativa(rset.getInt("NUMCOOPERATIVA"));
                lista.add(vo);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rset);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws SQLException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#listaSingulares(java.lang.Long)
     */
    public List<CentralSingularVo> listaSingulares(Integer idInstituicao, Integer numeroCooperativa) throws ComumException {
        getLogger().debug("*******OBTER_LISTA_SINGULARES*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rset = null;
        List<CentralSingularVo> lista = new ArrayList<>();
        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_SINGULARES");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.adicionarVariavel("numeroCooperativa", numeroCooperativa);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rset = comando.executarConsulta(conn);
            while (rset.next()) {
                CentralSingularVo vo = new CentralSingularVo();
                vo.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
                vo.setNumeroCooperativa(rset.getInt("NUMCOOPERATIVA"));
                lista.add(vo);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rset);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws SQLException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#listaUnidades(java.lang.Long)
     */
    public List<UnidadeVo> listaUnidades(Integer numeroCooperativa, Integer idInstituicao) throws ComumException {
        getLogger().debug("*******OBTER_LISTA_UNIDADE*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rset = null;
        List<UnidadeVo> lista = new ArrayList<>();
        if ((numeroCooperativa != null && idInstituicao == null) || (numeroCooperativa == null && idInstituicao != null)) {
            try {
                conn = estabelecerConexao();
                comando = getComando("OBTER_PACS");
                comando.adicionarVariavel("numeroCooperativa", numeroCooperativa);
                comando.adicionarVariavel("idInstituicao", idInstituicao);
                comando.configurar();
                getLogger().debug(comando.getSqlEmUso());
                rset = comando.executarConsulta(conn);
                while (rset.next()) {
                    UnidadeVo vo = new UnidadeVo();
                    vo.setCodigo(rset.getInt("IDUNIDADEINST"));
                    vo.setNomeInstituicao(rset.getString("DESCNOMECOOPPAI"));
                    vo.setIdUnidadeInst(rset.getInt("IDUNIDADEINST"));
                    vo.setDescricao(rset.getString("DESCNOMEUNIDADE"));
                    lista.add(vo);
                }
            } catch (SQLException e) {
                throw new ComumException(e);
            } finally {
                fecharResultSet(rset);
                fecharComando(comando);
                fecharConexao(conn);
            }
        } else {
            throw new ComumException("Somente um parâmetro deve ser passado para consulta");
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#obterIdInstituicaoPai(java.lang.Integer)
     */
    public InstituicaoDto obterIdInstituicaoPai(Integer idInstituicao) throws ComumException {
        getLogger().debug("*******OBTER_ID_INSTITUICAO_PAI*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        InstituicaoDto instituicao = new InstituicaoDto();
        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_INSTITUICAO_PAI");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                instituicao.setIdInstituicao(rs.getInt("IDINSTITUICAO"));
                instituicao.setNumCooperativa(rs.getInt("NUMCOOPERATIVA"));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return instituicao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#obterCooperativaPorInstituicao(java.lang.Integer)
     */
    public Integer obterCooperativaPorInstituicao(Integer idInstituicao) throws ComumException {
        getLogger().debug("*******OBTER_COOPERATIVA_POR_INSTITUICAO*******");
        Comando comando = null;
        Connection conn = null;
        Integer idNumeoroCoperativa = null;
        try {
            comando = getComando("OBTER_COOPERATIVA_POR_INSTITUICAO");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            Query query = comando.criarNativeQuery(getEntityManager());
            idNumeoroCoperativa = (Integer) query.getSingleResult();
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
            fecharConexao(conn);
        }
        return idNumeoroCoperativa;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws SQLException
     * @throws NumberFormatException
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#obterCooperativaPorInstituicao(java.lang.Integer)
     */
    public InstituicaoVo obterTipoGrauCooperativo(Integer idInstituicao) throws ComumException {
        getLogger().debug("*******OBTER_TIPO_GRAU_COOPERATIVA*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rset = null;
        InstituicaoVo instituicao = new InstituicaoVo();
        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_TIPO_GRAU_COOPERATIVA");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rset = comando.executarConsulta(conn);
            while (rset.next()) {
                instituicao.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
                instituicao.setCodTipoGrauCoop(rset.getInt("CODTIPOGRAUCOOP"));
                instituicao.setDescTipoGrauCoop("DESCTIPOGRAUCOOP");
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rset);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return instituicao;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws SQLException
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#listaCentrais(java.lang.Long)
     */
    public List<InstituicaoCooperativaDto> listaSingulares(Integer idInstituicao) throws ComumException {
        getLogger().debug("*******LISTA_SINGULARES_RETORNO_DTO*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rset = null;
        List<InstituicaoCooperativaDto> lista = new ArrayList<>();

        try {
            conn = estabelecerConexao();
            comando = getComando("LISTAR_SINGULARES");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rset = comando.executarConsulta(conn);
            while (rset.next()) {
                InstituicaoCooperativaDto dto = new InstituicaoCooperativaDto();
                dto.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
                dto.setNumCooperativa(rset.getInt("NUMCOOPERATIVA"));
                dto.setNomeCooperativa(rset.getString("DESCNOMECOOP"));
                dto.setSiglaUfCooperativa(rset.getString("DESCSIGLACOOP"));
                dto.setIdLocalidade(rset.getInt("IDLOCALIDADE"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rset);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#listaCentrais()
     */
    public List<InstituicaoCooperativaDto> listaCentrais() throws ComumException {
        getLogger().debug("*******LISTA_CENTRAIS_RETORNO_DTO*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rset = null;
        List<InstituicaoCooperativaDto> lista = new ArrayList<>();

        try {
            conn = estabelecerConexao();
            comando = getComando("LISTAR_CENTRAIS");
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            rset = comando.executarConsulta(conn);
            while (rset.next()) {
                InstituicaoCooperativaDto dto = new InstituicaoCooperativaDto();
                dto.setIdInstituicao(rset.getInt("IDINSTITUICAO"));
                dto.setNumCooperativa(rset.getInt("NUMCOOPERATIVA"));
                dto.setNomeCooperativa(rset.getString("DESCNOMECOOP"));
                dto.setSiglaUfCooperativa(rset.getString("DESCSIGLACOOP"));
                dto.setIdLocalidade(rset.getInt("IDLOCALIDADE"));
                lista.add(dto);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rset);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#listarInstituicao()
     */
    public List<InstituicaoDto> listarInstituicao() throws ComumException {
        return listarInstituicao(null, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#listarInstituicao(java.util.List)
     */
    public List<InstituicaoDto> listarInstituicao(List<Integer> listaIdInstituicao) throws ComumException {
        return listarInstituicao(null, null, listaIdInstituicao);
    }

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @param numCooperativa
     * @param listaIdInstituicao
     * @return
     * @throws ComumException List<InstituicaoDto>
     */
    private List<InstituicaoDto> listarInstituicao(Integer idInstituicao, Integer numCooperativa, List<Integer> listaIdInstituicao) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<InstituicaoDto> listaInstituicao = new ArrayList<>();
        try {
            conn = estabelecerConexao();

            comando = getComando("LISTAR_INSTITUICAO_VIW_INSTITUICAO");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.adicionarVariavel("numeroCooperativa", numCooperativa);
            comando.adicionarVariavel("listaIdInstituicao", listaIdInstituicao);
            comando.configurar();
            getLogger().debug(comando.getSql());

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaInstituicao.add(getInstituicaoDto(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaInstituicao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#obterInstituicao(java.lang.Integer)
     */
    public InstituicaoDto obterInstituicao(Integer idInstituicao) throws ComumException {
        return obterInstituicao(idInstituicao, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#obterInstituicaoPorCooperativa(java.lang.Integer)
     */
    public InstituicaoDto obterInstituicaoPorCooperativa(Integer numeroCooperativa) throws ComumException {
        return obterInstituicao(null, numeroCooperativa);
    }

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @param numCooperativa
     * @return
     * @throws ComumException InstituicaoDto
     */
    private InstituicaoDto obterInstituicao(Integer idInstituicao, Integer numCooperativa) throws ComumException {
        List<InstituicaoDto> listaInstituicao = listarInstituicao(idInstituicao, numCooperativa, null);

        if (ObjectUtil.isEmpty(listaInstituicao)) {
            return null;
        }

        return listaInstituicao.get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao#obterEnderecoUnidadeInstituicao(java.lang.Integer, java.lang.Integer)
     */
    public EnderecoInstituicaoDto obterEnderecoUnidadeInstituicao(Integer idInstituicao, Integer idUnidadeInstituicao) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        EnderecoInstituicaoDto retorno = null;
        try {
            conn = estabelecerConexao();

            comando = getComando("OBTER_ENDERECO_INSTITUICAO_VIW_INSTITUICAO");
            comando.adicionarVariavel("idInstituicao", idInstituicao);
            comando.adicionarVariavel("idUnidadeInstituicao", idUnidadeInstituicao);
            comando.configurar();
            getLogger().debug(comando.getSql());

            rs = comando.executarConsulta(conn, 1);
            if (rs.next()) {
                retorno = getEnderecoInstituicaoDto(rs);
            }
        } catch (NoResultException e) {
            getLogger().alerta(e, e.getMessage());
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return retorno;
    }

    /**
     * Método responsável por criar um InstituicaoDto
     * 
     * @param rs
     * @return
     * @throws SQLException InstituicaoDto
     * 
     */
    private InstituicaoDto getInstituicaoDto(ResultSet rs) throws SQLException {
        return new InstituicaoDto(rs.getInt("IDINSTITUICAO"), rs.getInt("NUMCOOPERATIVA"), rs.getInt("IDINSTITUICAOPAI"), rs.getInt("NUMCOOPPAI"), rs.getString("DESCNOMECOOP"),
                rs.getString("DESCSIGLACOOP"), rs.getInt("CODTIPOGRAUCOOP"), rs.getString("DESCTIPOGRAUCOOP"), rs.getString("NUMCNPJ"), rs.getLong("NUMCONTACONVENIO"));
    }

    /**
     * Método responsável por criar um EnderecoInstituicaoDto
     * 
     * @param rs
     * @return EnderecoInstituicaoDto
     * @throws SQLException
     * 
     */
    private EnderecoInstituicaoDto getEnderecoInstituicaoDto(ResultSet rs) throws SQLException {
        return new EnderecoInstituicaoDto(rs.getInt("NUMPAC"), rs.getInt("IDINSTITUICAO"), rs.getString("DESCENDERECOCOOP"), rs.getString("DESCNUMEROCOOP"),
                rs.getString("DESCCOMPLEMENTOCOOP"), rs.getString("DESCBAIRROCOOP"), rs.getString("DESCCIDADECOOP"), rs.getString("SIGLAUF"), rs.getString("NUMCEPCOOP"),
                rs.getInt("IDLOCALIDADE"));
    }

}
