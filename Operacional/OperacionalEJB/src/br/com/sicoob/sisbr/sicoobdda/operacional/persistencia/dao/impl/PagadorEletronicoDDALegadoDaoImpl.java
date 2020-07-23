package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDALegadoDao;

/**
 * PagadorEletronicoDaoImpl é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class PagadorEletronicoDDALegadoDaoImpl extends OperacionalCrudDao<SicoobDDAEntidade> implements PagadorEletronicoDDALegadoDao {

    public static final String PESQUISAR = "PESQUISAR_PAGADOR_ELETRONICO";
    public static final String OBTER_NUMERO_REGISTROS = "OBTER_NUMERO_REGISTROS_PAGADOR_ELETRONICO";

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.pagador.eletronico.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-pagador-eletronico";

    public static final int COD_RETORNO = 0;
    public static final String MENSAGEM = "";
    public static final String CAMPO_ERRO = "";

    public static final int NUMERO_COOPERATIVA_BD_INTEGRACAO = 0;

    public static final int COD_TRANSACAO_TITULOS = 23;
    public static final int DETALHE_TITULOS = 1;
    public static final int VALOR_LANCAMENTO = 0;

    public PagadorEletronicoDDALegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDALegadoDao#atualizarSacadoEletronico(java.lang.Integer)
     */
    public void atualizarSacadoEletronico(Integer numCooperativa) throws ComumException {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = estabelecerConexao(numCooperativa);
            cs = conn.prepareCall("{call SPU_DDA_ATUALIZAR_SACADOELETRONICO}");
            cs.execute();
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharStatement(cs);
            fecharConexao(conn);
        }
    }

}
