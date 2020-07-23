/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         ArquivoRecebidoDaoImpl.java
 * Data Criação:    Jan 27, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * ArquivoRecebidoDaoImpl
 * 
 * @author Samuell.Ramos
 */
public class ArquivoRecebidoDaoImpl extends OperacionalCrudDaoDB2<LogRecebimentoArquivoDDA> implements ArquivoRecebidoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.arquivorecebido.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-arquivorecebido";
    public static final String COMANDOS = "PESQUISAR_ARQUIVO_RECEBIDO";
    public static final String COMANDOS_QTD = "OBTER_QTD_ARQUIVO_RECEBIDO";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public ArquivoRecebidoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, null, COMANDOS, COMANDOS_QTD);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao#listarSitucaoArquivo()
     */
    public List<SituacaoProcessamentoArquivo> listarSitucaoArquivo() throws ComumException {
        return listar("LISTAR_SITUACAO_ARQUIVO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao#listarTipoArquivo()
     */
    public List<TipoArquivo> listarTipoArquivo() throws ComumException {
        return listar("LISTAR_TIPO_ARQUIVO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao#listarTipoMensagemAgenAdda()
     */
    public List<TipoMensagemDDA> listarTipoMensagemAgenAdda() throws OperacionalException {
        Comando comando = null;
        Connection conn = null;
        List<TipoMensagemDDA> listaTipoMensagem = new ArrayList<TipoMensagemDDA>();
        ResultSet rs = null;
        try {
            comando = getComando("LISTAR_TIPO_MENSAGEM_AGEN_ADDA");
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaTipoMensagem.add(this.montarTipoMensagemAgenAdda(rs));
            }
        } catch (PersistenceException e) {
            getLogger().erro(e, "Erro ao listar tipo mensagem");
            throw new OperacionalException(e);
        } catch (SQLException e) {
            throw new OperacionalException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaTipoMensagem;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao#obterArquivoRecebido(java.lang.Long)
     */
    public ArquivoRecebidoDto obterArquivoRecebido(Long idLogRecebimentoArquivoDDA) throws ComumException {
        return obterDados("OBTER_ARQUIVO_RECEBIDO", getMapaIdLogParametro(idLogRecebimentoArquivoDDA));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao#alterarSituacaoArquivo(long, short)
     */
    public void alterarSituacaoArquivo(long idLogRecebimentoArquivoDDA, short codSituacao) throws ComumException {
        Map<String, Object> parametros = getMapaIdLogParametro(idLogRecebimentoArquivoDDA);
        parametros.put("codSituacao", codSituacao);
        executarUpdate("ALTERAR_SITUACAO_ARQUIVO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao#alterarSituacaoRegistro(br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA)
     */
    public void alterarSituacaoRegistro(LogDetRecebimentoArquivoDDA logDetRecebimentoArquivoDDA) throws ComumException {
        Map<String, Object> parametros = getMapaIdLogDetParametro(logDetRecebimentoArquivoDDA.getId());
        parametros.put("bolProcessado", logDetRecebimentoArquivoDDA.getBolProcessado());
        executarUpdate("ALTERAR_SITUACAO_REGISTRO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao#atualizarQtdeTotalRegistrosArquivoRecebido(long, int)
     */
    public void atualizarQtdeTotalRegistrosArquivoRecebido(long idLogRecebimentoArquivoDDA, int qtdTotalRegistro) throws ComumException {
        Map<String, Object> parametros = getMapaIdLogParametro(idLogRecebimentoArquivoDDA);
        parametros.put("qtdTotalRegistro", qtdTotalRegistro);
        executarUpdate("ALTERAR_QTD_REGISTROS_ARQUIVO_RECEBIDO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao#obterDadosProcessamentoArquivo(java.lang.Long)
     */
    public ArquivoProcessamentoVO obterDadosProcessamentoArquivo(Long idLogRecebimentoArquivoDDA) throws ComumException {
        return obterDados("OBTER_DADOS_PROCESSAMENTO_ARQ", getMapaIdLogParametro(idLogRecebimentoArquivoDDA));
    }

    /**
     * Método responsável por
     * 
     * @param idLogRecebimentoArquivoDDA
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getMapaIdLogParametro(Long idLogRecebimentoArquivoDDA) {
        return getMapaParametro(idLogRecebimentoArquivoDDA, "idLogRecebimentoArquivoDDA");
    }

    /**
     * Método responsável por
     * 
     * @param idLogDetRecebimentoArquivoDDA
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getMapaIdLogDetParametro(Long idLogDetRecebimentoArquivoDDA) {
        return getMapaParametro(idLogDetRecebimentoArquivoDDA, "idLogDetRecebimentoArquivoDDA");
    }

    /**
     * @param rs
     * @return TipoMensagemDDA
     * @throws SQLException
     * 
     */
    private TipoMensagemDDA montarTipoMensagemAgenAdda(ResultSet rs) throws SQLException {
        TipoMensagemDDA tipoMensagemDDA = new TipoMensagemDDA();
        tipoMensagemDDA.setCodTipoMensagem(rs.getString("CODTIPOMENSAGEMDDA"));
        tipoMensagemDDA.setDescTipoMensagem(rs.getString("DESCTIPOMENSAGEMDDA"));
        return tipoMensagemDDA;
    }
}
