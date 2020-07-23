/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl
 * Arquivo:         DominioDDADaoImpl.java
 * Data Criação:    22 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.DominioDDADao;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoletoPagamento;

/**
 * DominioDDADaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class DominioDDADaoImpl extends ComumCrudDaoDB2<SicoobDDAEntidade> implements DominioDDADao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_comum.dominiodda.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-comum-dominiodda";

    /**
     * 
     */
    public DominioDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.DominioDDADao#listarAutorizacaoValorDivergente()
     */
    @Override
    public List<AutorizacaoValorDivergente> listarAutorizacaoValorDivergente() throws ComumException {
        return listar("LISTAR_AUTORIZACAO_VALOR_DIVERGENTE");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.DominioDDADao#listarSituacaoBoletoPagamento()
     */
    @Override
    public List<SituacaoBoletoPagamento> listarSituacaoBoletoPagamento() throws ComumException {
        return listar("LISTAR_SITUACAO_BOLETO_PAGAMENTO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.DominioDDADao#obterGradeHorariaReferencia(java.lang.String)
     */
    @Override
    public GradeHoraria obterGradeHorariaReferencia(String codTipoMensagem) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        getLogger().debug("******obterGradeHorariaReferencia******");
        try {
            conn = estabelecerConexao();

            comando = getComando("OBTER_GRADE_HORARIA_REFERENCIA");
            comando.adicionarVariavel("codTipoMensagem", codTipoMensagem);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                return montarGradeHoraria(rs);
            }
        } catch (SQLException e) {
            throw new ComumException("dominiodda.erro.obter.grade.horaria.referencia");
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return null;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return GradeHoraria
     * 
     */
    private GradeHoraria montarGradeHoraria(ResultSet rs) throws SQLException {
        return new GradeHoraria(rs.getLong("IDGRADEHORARIA"), rs.getDate("DATAREFERENCIA"), rs.getDate("DATAHORAABERTURA"), rs.getDate("DATAHORAFECHAMENTO"),
                rs.getString("CODTIPOGRADEHORARIA"));
    }

}
