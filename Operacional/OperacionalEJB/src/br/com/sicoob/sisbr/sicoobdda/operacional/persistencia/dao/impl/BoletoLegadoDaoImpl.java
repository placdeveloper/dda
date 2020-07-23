package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ParametroNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDao;

/**
 * PagadorEletronicoDaoImpl é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class BoletoLegadoDaoImpl extends OperacionalCrudDao<SicoobDDAEntidade> implements BoletoLegadoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.boleto.legado.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-boleto-legado";

    public BoletoLegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoLegadoDao#obterCodCarteiraPelaModalidade(java.lang.Integer, java.lang.Integer,
     *      java.lang.Integer)
     */
    public Integer obterCodCarteiraPelaModalidade(Integer numCooperativa, Integer idProduto, Integer idModalidade) throws ComumException, ComumNegocioException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        final String sql = "select mo.CodCarteira from MOdalidadeopcredito mo where mo.IDProduto = ? and mo.IDModalidadeProduto = ?";

        try {
            conn = estabelecerConexao(numCooperativa);

            ps = conn.prepareStatement(sql);
            ps.setInt(1, idProduto);
            ps.setInt(2, idModalidade);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("CodCarteira");
            } else {
                // A exceção faz rollback da transação
                throw new ParametroNaoEncontradoException(idModalidade, numCooperativa);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharStatement(ps);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoLegadoDao#incluirTituloDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto)
     */
    public boolean incluirTituloDDA(BoletoLegadoDto boletoLegadoDto) throws ComumException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comando comando = null;

        try {
            conn = estabelecerConexao(boletoLegadoDto.getNumCooperativa());

            comando = getComando("INCLUIR_TITULO_DDA");

            ps = conn.prepareStatement(comando.getSqlEmUso());
            debug(comando.getSqlEmUso());

            int index = 1;

            ps.setInt(index++, Constantes.ID_PRODUTO_COBRANCA);
            ps.setInt(index++, boletoLegadoDto.getNumCooperativa());
            ps.setInt(index++, Constantes.ID_PRODUTO_COBRANCA);
            ps.setInt(index++, boletoLegadoDto.getIdModalidade());
            ps.setInt(index++, boletoLegadoDto.getNumeroTitulo());
            ps.setInt(index++, boletoLegadoDto.getNumeroCliente());

            rs = ps.executeQuery();

            if (rs.next()) {
                long qtdRegistros = rs.getLong(1);
                debug("Registros incluídos na TituloDDA: " + qtdRegistros);

                return qtdRegistros > 0;
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
            fecharResultSet(rs);
            fecharStatement(ps);
            fecharConexao(conn);
        }

        return false;
    }

}
