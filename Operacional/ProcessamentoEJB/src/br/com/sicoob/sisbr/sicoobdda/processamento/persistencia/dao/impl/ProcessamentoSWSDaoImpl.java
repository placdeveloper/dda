/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.impl
 * Arquivo:         ProcessamentoSWSDaoImpl.java
 * Data Criação:    Jun 27, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.impl;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.SEPARADOR_DE_DADOS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EnvioArquivoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.SituacaoRateioCreditoCCODto;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao;

/**
 * ProcessamentoSWSDaoImpl
 * 
 * @author rafael.silva
 */
public class ProcessamentoSWSDaoImpl extends ProcessamentoCrudDaoDB2<SicoobDDAEntidade> implements ProcessamentoSWSDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_processamento.sws.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-processamento-sws";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public ProcessamentoSWSDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, "", "");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#obterQtdRegistroNaFilaEnvio()
     */
    public Long verificarQtdRegistroNaFilaEnvioMsgSemProtocolo() throws ComumException {
        return obterCount("CONTAR_QTD_REGISTROS_FILA_ENVIO_CIP_SQL");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarNumMensagensPrioritariasEnvioCIP(int)
     */
    public List<Integer> listarNumAgrupamentoEnvioMsg(boolean bolMsgPrioritaria) {
        getLogger().debug("### Listando número de mensagens prioritárias para envio cip");
        debug("Parâmetro - bolMsgPrioritaria: " + bolMsgPrioritaria);

        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Integer> listaNumPrioridades = new ArrayList<Integer>();

        try {
            comando = getComando("LISTAR_NUM_AGRUPAMENTO_ENVIO_MSG");
            comando.adicionarVariavel("msgPrioritaria", bolMsgPrioritaria ? 1 : 0);
            comando.configurar();

            debug(comando.getSqlEmUso());

            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                listaNumPrioridades.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return listaNumPrioridades;
    }


    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarIdLogEnvioArquivoGeraArquivo()
     */
    public List<Long> listarIdLogEnvioArquivoGeraArquivo() throws ComumException {
        getLogger().debug("*******listarIdLogEnvioArquivoGeraArquivo*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Long> listaId = new ArrayList<Long>();
        try {
            comando = getComando("LISTAR_ID_LOG_ENVIO_ARQUIVO_GERAR_ARQUIVO");

            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaId.add(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaId;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarIdLogEnvioArquivoDDAPostarArquivo()
     */
    public List<Long> listarIdLogEnvioArquivoDDAPostarArquivo() throws ComumException {
        return listarIdNativo("LISTAR_ID_LOG_ENVIO_ARQUIVO_POSTAR_ARQUIVO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarIdLogEnvioArquivoTratamento()
     */
    public List<Long> listarIdLogEnvioArquivoTratamento() throws ComumException {
        return listarIdNativo("LISTAR_ID_LOG_ENVIO_ARQUIVO_TRATAMENTO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#existeArquivoDisponivel(short, java.lang.String[],
     *      java.lang.String[])
     */
    public boolean existeArquivoDisponivel(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens) throws ComumException {
        getLogger().debug("*******verificando a existencia de arquivo  disponível para processamento*******");
        Map<String, Object> parametros = criarMapaArquivosProcessamento(tiposDeArquivos, tiposDeMensagens, 1, codSituacaoProcessamentoArquivo);
        return existeRegistroNativo("EXISTE_ARQUIVO_PARA_PROCESSAMENTO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarIdArquivosProcessados(java.lang.String[], java.lang.String[],
     *      int)
     */
    public List<Long> listarIdArquivosEmSituacao(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens, int qtdMaximaRegistros)
            throws ComumException {
        getLogger().debug("*******listarIdArquivosProcessados*******");
        Map<String, Object> parametros = criarMapaArquivosProcessamento(tiposDeArquivos, tiposDeMensagens, qtdMaximaRegistros, codSituacaoProcessamentoArquivo);
        return listarIdNativo("LISTAR_ID_ARQUIVO_PROCESSAMENTO", parametros);
    }

    /**
     * Método responsável por
     * 
     * @param tiposDeArquivos
     * @param tiposDeMensagens
     * @param qtdMaximaRegistros
     * @param codSituacaoProcessamentoArquivo
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> criarMapaArquivosProcessamento(String[] tiposDeArquivos, String[] tiposDeMensagens, int qtdMaximaRegistros, short codSituacaoProcessamentoArquivo) {
        Map<String, Object> parametros = getMapaParametro(codSituacaoProcessamentoArquivo, "codSituacaoProcessamentoArquivo");
        parametros.put("listaTipoArquivo", Arrays.asList(tiposDeArquivos));
        parametros.put("listaTipoMensagem", Arrays.asList(tiposDeMensagens));
        parametros.put("qtdMaximaRegistros", qtdMaximaRegistros);
        return parametros;
    }

    /**
     * {@inheritDoc}
     * 
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#obterPrimeiroArquivoDaSequenciaParaProcessamento(short,
     *      java.lang.String[], java.lang.String[])
     */
    public List<ArquivoProcessamentoVO> listarArquivoDaSequenciaParaProcessamento(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens,
            int qtdMaximaRegistros) {
        getLogger().debug("******* Recuperando arquivos disponívis para processamento *******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<ArquivoProcessamentoVO> listaArquivo = new ArrayList<ArquivoProcessamentoVO>();
        try {
            comando = getComandoListarArqSeqProcessamento(codSituacaoProcessamentoArquivo, tiposDeArquivos, tiposDeMensagens, qtdMaximaRegistros);
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                listaArquivo.add(new ArquivoProcessamentoVO(rs.getLong(1), TipoArquivoRetornoEnum.valueOf(rs.getString(2)), rs.getString(3), rs.getLong(4), rs.getString(5), rs
                        .getShort(6)));
            }
        } catch (SQLException e) {
            erroRecuperarArquivo(codSituacaoProcessamentoArquivo, e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaArquivo;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarIdArquivoDaSequenciaParaProcessamento(short,
     *      java.lang.String[], java.lang.String[], int)
     */
    public List<Long> listarIdArquivoDaSequenciaParaProcessamento(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens, int qtdMaximaRegistros) {
        getLogger().debug("******* Recuperando arquivos disponívis para processamento *******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<Long> listaIdArquivo = new ArrayList<Long>();
        try {
            comando = getComandoListarArqSeqProcessamento(codSituacaoProcessamentoArquivo, tiposDeArquivos, tiposDeMensagens, qtdMaximaRegistros);
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                listaIdArquivo.add(rs.getLong(1));
            }
        } catch (SQLException e) {
            erroRecuperarArquivo(codSituacaoProcessamentoArquivo, e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaIdArquivo;
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoProcessamentoArquivo
     * @param tiposDeArquivos
     * @param tiposDeMensagens
     * @param qtdMaximaRegistros
     * @return Comando
     * 
     */
    private Comando getComandoListarArqSeqProcessamento(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens, int qtdMaximaRegistros) {
        Comando comando;
        getLogger().debug("****** Situacao: " + codSituacaoProcessamentoArquivo);
        getLogger().debug("****** Tipo Arquivo: " + Arrays.toString(tiposDeArquivos));
        getLogger().debug("****** Tipos de mensagens: " + Arrays.toString(tiposDeMensagens));
        getLogger().debug("****** Qtd. Máxima de Registros:" + qtdMaximaRegistros);

        comando = getComando("LISTAR_ARQUIVO_SEQUENCIA_PARA_PROCESSAMENTO");

        comando.adicionarVariavel("listaTipoArquivo", Arrays.asList(tiposDeArquivos));
        comando.adicionarVariavel("listaTipoMensagem", Arrays.asList(tiposDeMensagens));
        comando.adicionarVariavel("codSituacaoProcessamentoArquivo", codSituacaoProcessamentoArquivo);
        comando.adicionarVariavel("qtdMaximaRegistros", qtdMaximaRegistros);

        getLogger().debug("****** " + comando.getSqlEmUso());

        comando.configurar();
        return comando;
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoProcessamentoArquivo
     * @param e void
     * 
     */
    private void erroRecuperarArquivo(short codSituacaoProcessamentoArquivo, Exception e) {
        getLogger().erro(e, "Falha na recuperação do arquivo com status[" + codSituacaoProcessamentoArquivo + "] para processamento! MOTIVO [" + e.getMessage() + "]");
        throw new PersistenceException(e);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#obterParametrosParaStepPorInstancia(br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum,
     *      long, java.lang.String, java.lang.String)
     */

    public List<String> listarParametrosParaStepPorInstancia(TipoArquivoRetornoEnum prTipoArq, long prIdArquivoRecebido, String prGrupoTag, String prTag) {
        getLogger().debug("*******Recuperando parâmetros para execução dos steps *******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<String> listaParametros = null;
        try {

            if (prTipoArq == TipoArquivoRetornoEnum.RET) {
                comando = getComando("MONTA_STEPS_PARA_PROCESSAMENTO_ARQUIVOS_RET");
            } else if (prTipoArq == TipoArquivoRetornoEnum.DIS) {
                comando = getComando("MONTA_STEPS_PARA_PROCESSAMENTO_ARQUIVOS_DIS");

                comando.setSql(comando.getSql().replace("#GRUPO#", prGrupoTag));
                comando.setSql(comando.getSql().replace("#TAG#", prTag));
            } else {
                return null;
            }

            comando.adicionarVariavel("idArquivo", prIdArquivoRecebido);
            comando.configurar();

            getLogger().debug(comando.getSqlEmUso());

            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            listaParametros = new ArrayList<String>();
            while (rs.next()) {
                listaParametros.add(String.format("%1$s" + SEPARADOR_DE_DADOS + "%2$s" + SEPARADOR_DE_DADOS + "%3$s", rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            return listaParametros;
        } catch (SQLException e) {
            getLogger().debug("Falha na recuperação dos parametros para os STEPS de processamento [" + e.getMessage() + "]");
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
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarMensagensCopiadasParaExpurgo(java.lang.Long)
     */
    public List<EnvioArquivoDto> listarMensagensCopiadasParaExpurgo(Long qtdDiasLimiteExpurgo) {
        return listarMensagensParaExpurgo("LISTAR_MENSAGENS_COPIADAS_PARA_EXPURGO", qtdDiasLimiteExpurgo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarMensagensAntigasParaExpurgar()
     */
    public List<EnvioArquivoDto> listarMensagensAntigasParaExpurgar() {
        return listarMensagensParaExpurgo("LISTAR_MENSAGENS_ANTIGAS_PARA_EXPURGO", null);
    }

    /**
     * Método responsável por
     * 
     * @param nomeComando
     * @param qtdDiasLimiteExpurgo
     * @return List<EnvioArquivoDto>
     * 
     */
    private List<EnvioArquivoDto> listarMensagensParaExpurgo(String nomeComando, Long qtdDiasLimiteExpurgo) {
        getLogger().debug("*******listarMensagensParaExpurgo*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<EnvioArquivoDto> listaEnvioArquivoDto = new ArrayList<EnvioArquivoDto>();
        try {
            comando = getComando(nomeComando);
            comando.adicionarVariavel("qtdDiasLimiteExpurgo", qtdDiasLimiteExpurgo);
            comando.adicionarVariavel("idParametro", ParametroDDA.QTD_REGISTROS_BLOCO_EXPURGO);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaEnvioArquivoDto.add(new EnvioArquivoDto(rs.getLong(1), rs.getLong(2)));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaEnvioArquivoDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#atualizarQtdeTotalRegistrosArquivoRecebido(long, int)
     */
    public void atualizarQtdeTotalRegistrosArquivoRecebido(long idArquivoRecebido, int qtdTotalRegistro) throws ComumException {
        getLogger().debug("*******atualizarQtdeTotalRegistrosArquivoRecebido*******");
        Map<String, Object> parametros = getMapaParametro(idArquivoRecebido, "idArquivo");
        parametros.put("qtdTotalRegistro", qtdTotalRegistro);
        executarUpdate("ATUALIZAR_TOTAL_DE_REGISTROS_DO_ARQUIVO_RECEBIDO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarParametrosStepsProcessamentoArq(java.util.List, long)
     */
    public Map<Long, List<ArquivoProcessamentoVO>> listarParametrosStepsProcessamentoArq(List<Long> listaIdLogArquivo, long qtdRegistrosStep) {
        getLogger().debug("*******listarParametrosStepsInventario*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        Map<Long, List<ArquivoProcessamentoVO>> mapArquivo = new HashMap<Long, List<ArquivoProcessamentoVO>>();
        try {
            comando = getComando("LISTAR_PARAMETROS_STEPS_PROCESSAMENTO_ARQ");
            comando.adicionarVariavel("listaIdLogArquivo", listaIdLogArquivo);
            comando.adicionarVariavel("qtdRegistrosStep", qtdRegistrosStep);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                montarMapaArquivoProcessamento(rs, mapArquivo);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mapArquivo;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @param mapArquivo
     * @throws SQLException void
     * 
     */
    private void montarMapaArquivoProcessamento(ResultSet rs, Map<Long, List<ArquivoProcessamentoVO>> mapArquivo) throws SQLException {
        ArquivoProcessamentoVO arquivo = new ArquivoProcessamentoVO(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getLong(4));
        if (mapArquivo.containsKey(arquivo.getIdArquivoRecebido())) {
            mapArquivo.get(arquivo.getIdArquivoRecebido()).add(arquivo);
        } else {
            List<ArquivoProcessamentoVO> listaArquivo = new ArrayList<ArquivoProcessamentoVO>();
            listaArquivo.add(arquivo);
            mapArquivo.put(arquivo.getIdArquivoRecebido(), listaArquivo);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#obterQuantidadeStepsAtualizarSituacaoBoleto()
     */
    public long obterQuantidadeStepsAtualizarSituacaoBoleto() throws ComumException {
        return obterCount("COUNT_STEPS_ATUALIZAR_SIT_BOLETO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarParametroStepContingenciaMensagem()
     */
    public List<MensagemContingenciaDto> listarParametroStepContingenciaMensagem() throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemContingenciaDto> listaMensagemContingencia = new ArrayList<MensagemContingenciaDto>();
        try {
            comando = getComando("LISTAR_PARAMETROS_STEP_CONTINGENCIA_MSG");
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaMensagemContingencia.add(new MensagemContingenciaDto(rs.getDate(1), rs.getString(2), rs.getLong(3), rs.getLong(4), rs.getInt(5)));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaMensagemContingencia;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarCooperativasLancamentosRateioCCO()
     */
    public List<RateioCreditoLancamentoCCODto> listarCooperativasLancamentosRateioCCO() throws ComumException {
        getLogger().debug(Constantes.STR_SEPARACAO_2 + ProcessamentoSWSDaoImpl.class.getName());
        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Obtendo as cooperativas que contêm lançamentos de rateio a serem efetivados no CCO");

        Comando comando = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<RateioCreditoLancamentoCCODto> listaCooperativasRateioCCO = new ArrayList<RateioCreditoLancamentoCCODto>();

        try {
            comando = getComando("LISTAR_COOPERATIVAS_LANCAMENTO_RATEIO_CCO");
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            connection = estabelecerConexao();
            resultSet = comando.executarConsulta(connection);
            while (resultSet.next()) {
                listaCooperativasRateioCCO.add(new RateioCreditoLancamentoCCODto(resultSet.getLong(1), resultSet.getInt(2), resultSet.getString(3)));
            }
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(resultSet);
            fecharComando(comando);
            fecharConexao(connection);
        }

        return listaCooperativasRateioCCO;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao.ProcessamentoSWSDao#listarRateiosCreditoAtualizacaoCCO()
     */
    public List<SituacaoRateioCreditoCCODto> listarRateiosCreditoAtualizacaoCCO() throws ComumException {
        getLogger().debug(Constantes.STR_SEPARACAO_2 + ProcessamentoSWSDaoImpl.class.getName());
        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Obtendo os rateios de crétido em processamento para atualização");

        Comando comando = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<SituacaoRateioCreditoCCODto> listaRateiosCreditoCCO = new ArrayList<SituacaoRateioCreditoCCODto>();

        try {
            comando = getComando("LISTAR_RATEIOS_CREDITO_ATUALIZACAO_CCO");
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            connection = estabelecerConexao();
            resultSet = comando.executarConsulta(connection);
            while (resultSet.next()) {
                listaRateiosCreditoCCO.add(new SituacaoRateioCreditoCCODto(resultSet.getLong(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4),
                        resultSet.getInt(5), resultSet.getInt(6)));
            }
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(resultSet);
            fecharComando(comando);
            fecharConexao(connection);
        }

        return listaRateiosCreditoCCO;
    }

}
