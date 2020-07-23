package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.SEPARADOR_DE_DADOS;

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
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;

/**
 * ArquivoCipDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ArquivoCipDaoImpl extends IntegracaoCipCrudDaoDB2<SicoobDDAEntidade> implements ArquivoCipDao {

    private static final String ID_ARQUIVO_RECEBIDO = "idArquivoRecebido";

    public static final String ARQUIVO_QUERIES = "sicoobdda_integracaocip.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-integracaocip";

    /**
     * 
     */
    public ArquivoCipDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, "", "");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#obterQtdArquivosGeradosDataAtual()
     */
    public Long obterQtdArquivosGeradosDataAtual() throws ComumException {
        return obterDados("CONTAR_QTD_ARQUIVOS_GERADOS_NO_DIA", getMapaParametro(new DateTimeDB(), "dataAtual"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#alterarSituacaoProcessamento(long, long)
     */
    public void alterarSituacaoProcessamento(long idArquivo, long codSituacaoProcessamentoArquivo) throws ComumException {
        Map<String, Object> parametros = getMapaParametro(idArquivo, "idArquivo");
        parametros.put("idSituacaoProcessamento", codSituacaoProcessamentoArquivo);
        executarUpdate("ATUALIZAR_STATUS_SITUACAO_PROCESSAMENTO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#obterLogEnvioArquivoPorNome(java.lang.String)
     */
    public LogEnvioArquivoDDA obterLogEnvioArquivoPorNome(String nomeArquivo) throws ComumException {
        return obterDados("OBTER_LOG_ENVIO_ARQUIVO_POR_NOME", getMapaParametro(nomeArquivo, "descNomeArquivoEnviado"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#excluirRegistrosDetalheRecebidos(long, int, int)
     */
    public void excluirRegistrosDetalheRecebidos(long prArquivoRecebido, int prOrdemInicial, int prOrdemFinal) throws ComumException {
        Map<String, Object> parametros = getMapaParametro(prArquivoRecebido, "idArquivo");
        parametros.put("ordemInicial", prOrdemInicial);
        parametros.put("ordemFinal", prOrdemFinal);
        executarUpdate("EXLUIR_RANGE_DETALHE_ARQUIVO_RECEBIDO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#listarDetalhesArquivoRecebido(long, long, long)
     */
    public List<String> listarDetalhesArquivoRecebido(long idArquivo, long idLogDetArqInicial, long idLogDetArqFinal) {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        try {

            comando = getComando("RECUPERA_DETALHES_PARA_PROCESSAMENTO_DO_STEP_ARQUIVO");

            comando.adicionarVariavel("idArquivo", idArquivo);
            comando.adicionarVariavel("inicio", idLogDetArqInicial);
            comando.adicionarVariavel("fim", idLogDetArqFinal);
            comando.configurar();

            getLogger().debug(comando.getSqlEmUso());

            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            List<String> dados = new ArrayList<String>();
            while (rs.next()) {
                dados.add(rs.getLong(1) + SEPARADOR_DE_DADOS + rs.getString(2));
            }

            return dados;

        } catch (SQLException e) {
            getLogger().erro(e, "Falha ao obter os registros para processamento pelo STEP ");
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);

        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#atualizarStatusDetalheArquivoRecebido(java.util.List, boolean)
     */
    public void atualizarStatusDetalheArquivoRecebido(Long idLogDetalheArquivo) throws ComumException {
        executarUpdate("ATUALIZAR_STATUS_DO_DETALHE_RECEBIDO", getMapaParametro(idLogDetalheArquivo, "idLogDetalheArquivo"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#gravaDetalheEmLote()
     */
    public void gravaDetalheEmLote(List<Object[]> parametros) throws ComumException {
        executarUpdateEmLote("GRAVAR_DETALHE_EM_LOTE", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#atualizarMensagensComProtocolo(java.util.List)
     */
    public void atualizarMensagensComProtocolo(List<Object[]> prParametros) throws ComumException {
        executarUpdateEmLote("ATUALIZAR_MENAGENS_COM_PROTOCOLO", prParametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#atualizarMensagensComErroProtocolo(java.util.List)
     */
    public void atualizarMensagensComErroProtocolo(List<Object[]> prParametros) throws ComumException {
        executarUpdateEmLote("ATUALIZAR_MENAGENS_COM_ERRO_PROTOCOLO", prParametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#atualizarSituacaoDetalhesArquivoProcessados(long, java.lang.String, java.lang.String)
     */
    public void atualizarSituacaoDetalhesArquivoProcessados(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            executarAtualizacaoArquivoDDA(conn, "ATUALIZAR_DETALHES_ARQUIVO_PROCESSADOS", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal,
                    "Atualizar LogDetRecebimentoArquivo para PROCESSADO");
        } finally {
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#obterQtdRegistroArquivo(long)
     */
    public Integer obterQtdRegistroArquivo(long idArquivoRecebido) throws ComumException {
        return obterDados("OBTER_QTD_REGISTROS_ARQUIVO", getMapaParametro(idArquivoRecebido, ID_ARQUIVO_RECEBIDO));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#obterCountDetGravados(long)
     */
    public Long obterCountDetGravados(long idArquivoRecebido) throws ComumException {
        return obterCount("CONTAR_DET_LOG_RECEBIDO", getMapaParametro(idArquivoRecebido, ID_ARQUIVO_RECEBIDO));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao#atualizarDataHoraProcLogRecebimentoArquivo(long)
     */
    public void atualizarDataHoraProcLogRecebimentoArquivo(long idLongRecebimento) throws ComumException {
        executarUpdate("ATUALIZAR_DATA_HORA_PROCESSAMENTO_LOG_RECEB_ARQ", getMapaParametro(idLongRecebimento, ID_ARQUIVO_RECEBIDO));
    }
}
