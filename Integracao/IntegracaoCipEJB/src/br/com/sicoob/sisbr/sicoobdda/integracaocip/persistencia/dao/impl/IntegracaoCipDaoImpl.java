/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         IntegracaoCipDaoImpl.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EnvioArquivoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;

/**
 * IntegracaoCipDaoImpl
 * 
 * @author Rafael.Silva
 */
public class IntegracaoCipDaoImpl extends IntegracaoCipCrudDaoDB2<SicoobDDAEntidade> implements IntegracaoCipDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_integracaocip.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-integracaocip";

    /**
     * IntegracaoCipDaoImpl
     */
    public IntegracaoCipDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, "", "");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#listarIdMensagensPrioritariasEnvioCip(int)
     */
    public Map<Long, String> mapMensagensEnvioCip(boolean bolMensagemPrioritaria, int numAgrupamentoSteps) throws ComumException {
        getLogger().debug("******* listarIdMensagensSLAExcedido *******");
        Comando comando = null;
        Connection connection = null;
        ResultSet resultSet = null;
        Map<Long, String> hashMapMensagensPrioritarias = new HashMap<Long, String>();
        try {
            comando = getComando("LISTAR_MENSAGENS_PARA_ENVIO_CIP");
            comando.adicionarVariavel("bolMensagemPrioritaria", bolMensagemPrioritaria ? 1 : 0);
            comando.adicionarVariavel("numAgrupamentoSteps", numAgrupamentoSteps);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            connection = estabelecerConexao();
            resultSet = comando.executarConsulta(connection);
            while (resultSet.next()) {
                hashMapMensagensPrioritarias.put(resultSet.getLong(2), resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(resultSet);
            fecharComando(comando);
            fecharConexao(connection);
        }
        return hashMapMensagensPrioritarias;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#atualizarMensagemEnviada(java.lang.Long, java.lang.String)
     */
    public void atualizarMensagemEnviada(Long idMsg, String xmlMsg) throws ComumException {
        atualizarXMLMensagem(idMsg, xmlMsg, "ATUALIZAR_MENSAGEMDDA_POSTADA");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#atualizarXMLMensagemDDA(java.lang.Long, java.lang.String)
     */
    public void atualizarXMLMensagemDDA(Long idMensagemDDA, String xml) throws ComumException {
        atualizarXMLMensagem(idMensagemDDA, xml, "ATUALIZAR_XML_MENSAGEMDDA");
    }

    /**
     * @param idMensagemDDA
     * @param xmlMsg
     * @param nomeComando
     * @throws ComumException void
     * 
     */
    private void atualizarXMLMensagem(Long idMensagemDDA, String xmlMsg, String nomeComando) throws ComumException {
        debug("### Atualizando o xml da mensagem DDA...");
        debug("Parâmetro - idMensagemDDA: " + idMensagemDDA);
        debug("Parâmetro - xml: " + xmlMsg);

        Map<String, Object> parametros = getMapaParametro(idMensagemDDA, "idMensagem");
        parametros.put("xml", xmlMsg);
        executarUpdate(nomeComando, parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#atualizarTipoMensagemDDA(java.lang.Long, java.lang.String)
     */
    public void atualizarTipoMensagemDDA(Long idMsg, String codTipoMensagemDDA) throws ComumException {
        getLogger().debug("*******atualizarMensagemEnviada*******");
        Map<String, Object> parametros = getMapaParametro(idMsg, "idMensagem");
        parametros.put("codTipoMensagemDDA", codTipoMensagemDDA);
        executarUpdate("ATUALIZAR_TIPO_MENSAGEM_DDA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#obterMensagemDDA(java.lang.Long)
     */
    public MensagemDDA obterMensagemDDA(Long idMensagemDDA) {
        return super.obterMensagemDDA(idMensagemDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#atualizarDataHoraArquivo(java.lang.Long)
     */
    public void atualizarDataHoraArquivo(Long idLogEnvioArquivoDDA) throws ComumException {
        getLogger().debug("*******atualizarDataHoraArquivo*******");
        executarUpdate("ATUALIZAR_DATA_HORA_ARQUIVO", getMapaParametro(idLogEnvioArquivoDDA, "idLogEnvioArquivoDDA"));

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#atualizarDataHoraEnvio(java.lang.Long)
     */
    public void atualizarDataHoraEnvio(Long idLogEnvioArquivoDDA) throws ComumException {
        getLogger().debug("*******atualizarDataHoraEnvio*******");
        executarUpdate("ATUALIZAR_DATA_HORA_ENVIO", getMapaParametro(idLogEnvioArquivoDDA, "idLogEnvioArquivoDDA"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#obterLogEnvioArquivoDDA(java.lang.Long)
     */
    public LogEnvioArquivoDDA obterLogEnvioArquivoDDA(Long idLogEnvioArquivoDDA) throws ComumException {
        getLogger().debug("*******obterLogEnvioArquivoDDA*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        LogEnvioArquivoDDA logEnvioArquivoDDA = new LogEnvioArquivoDDA();
        try {
            comando = getComando("OBTER_LOG_ENVIO_ARQUIVO_DDA");
            comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                logEnvioArquivoDDA.setId(rs.getLong(1));
                logEnvioArquivoDDA.setDescNomeArquivoEnviado(rs.getString(2));
                logEnvioArquivoDDA.setDataHoraArquivo(ObjectUtil.isNull(rs.getDate(3)) ? null : new DateTimeDB(rs.getDate(3).getTime()));
                logEnvioArquivoDDA.setDataMovimento(new DateTimeDB(rs.getDate(4).getTime()));
                logEnvioArquivoDDA.setTipoMensagem(new TipoMensagemDDA(rs.getString(5)));
                logEnvioArquivoDDA.setDataHoraEnvio(ObjectUtil.isNull(rs.getDate(6)) ? null : new DateTimeDB(rs.getDate(6).getTime()));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return logEnvioArquivoDDA;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#atualizarDataHoraMensagem(java.lang.Long)
     */
    public void atualizarDataHoraMensagem(Long idLogEnvioArquivoDDA, String codTipoMensagemDDA) throws ComumException {
        getLogger().debug("*******atualizarDataHoraMensagem*******");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
        parametros.put("codTipoMensagemDDA", codTipoMensagemDDA);
        executarUpdate("ATUALIZAR_DATA_HORA_MENSAGEM", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#obterTipoMensagemLock(java.lang.Long)
     */
    public String obterTipoMensagemLock(Long idMensagem) throws ComumException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        try {
            comando = getComando("OBTER_TIPO_MENSAGEM_LOCK_REGISTRO");
            comando.adicionarVariavel("idMensagem", idMensagem);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                return rs.getString("CODTIPOMENSAGEMDDA");
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharConexao(conn);
            fecharComando(comando);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#atualizarTipoGradeHoraria(br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria)
     */
    public void atualizarTipoGradeHoraria(TipoGradeHoraria tipoGradeHoraria) throws ComumException {
        getLogger().debug("*******atualizarDataHoraMensagem*******");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("codTipoGradeHoraria", tipoGradeHoraria.getCodTipoGradeHoraria());
        parametros.put("dataHoraAberturaPadrao", tipoGradeHoraria.getDataHoraAberturaPadrao());
        parametros.put("dataHoraFechamentoPadrao", tipoGradeHoraria.getDataHoraFechamentoPadrao());

        executarUpdate("ATUALIZAR_TIPO_GRADE_HORARIA", parametros);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#listarIdMensagensSLAExcedido(java.lang.String)
     */
    public List<Long> listarIdMensagensSLAExcedido(String codTipoMensagemDDA) throws ComumException {
        getLogger().debug("******* listarIdMensagensSLAExcedido *******");
        Comando comando = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Long> listaIdMensagensSLAExcedido = new ArrayList<Long>();
        try {
            comando = getComando("LISTAR_ID_MENSAGENS_DDA_SLA_EXCEDIDO");
            comando.adicionarVariavel("codTipoMensagemDDA", codTipoMensagemDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            connection = estabelecerConexao();
            resultSet = comando.executarConsulta(connection);
            while (resultSet.next()) {
                listaIdMensagensSLAExcedido.add(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(resultSet);
            fecharComando(comando);
            fecharConexao(connection);
        }
        return listaIdMensagensSLAExcedido;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#incluirLogEnvioArquivoDDA(java.lang.String,
     *      java.lang.Date,java.lang.String)
     */
    public void incluirLogEnvioArquivoDDA(Long qtdMaxRegistrosArquivos, Long qtdMaximaTotalRegistros, Long qtdMaximaArquivosProcessadosPorInteracao) throws ComumException {
        debug("Estabelecendo a conexão...");
        Connection conn = estabelecerConexao();
        try {
            conn.setAutoCommit(Boolean.FALSE);

            getLogger().info("Inicio da criação da tabela temporaria do envio de arquivo");
            criarTmpEnvioArquivo(conn);

            getLogger().info("Inserção na tabela temporaria do envio de arquivo");
            inserirTmpEnvioArquivo(conn, qtdMaxRegistrosArquivos, qtdMaximaTotalRegistros, qtdMaximaArquivosProcessadosPorInteracao);

            getLogger().info("Criação do indice  da tabela temporaria do envio de arquivo");
            executarIndiceTabelaTemporaria(conn);

            getLogger().info("Criação do Runstats  da tabela temporaria do envio de arquivo");
            executarRunstatsTabelaTemporaria(conn);

            getLogger().info("Inicio do processo de incluir na tabela LogEnvioArquivoDDA");
            incluirLogEnvioArquivoDDA(conn);

            
        } catch (SQLException e) {
            rollback(conn);
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
        }
    }

    /**
     * Método responsável por criar a tabela temporaria de arquivamento
     * 
     * @param conn
     * @throws ComumException, SQLException void
     * 
     */
    private void criarTmpEnvioArquivo(Connection conn) throws ComumException, SQLException {
        Comando comando = null;

        try {
            comando = getComando("CRIA_TABELA_TEMPORARIA_ENVIO_ARQUIVO");
            debug("Comando:  " + comando.getSqlEmUso());
            comando.executarAtualizacao(conn);

        } catch (PersistenceException e) {
            throw new ComumException("registrar.erro.criar.tmp.envio.arquivo", e);
        } finally {
            fecharComando(comando);
        }
    }

    /**
     * Método responsável por inserir todos os registros na tabela temporaria
     * 
     * @param conn
     * @throws ComumException
     * @throws SQLException void
     */
    private void inserirTmpEnvioArquivo(Connection conn, Long qtdMaximaRegistrosArquivo, Long qtdMaximaTotalRegistros, Long qtdMaximaArquivoIteracao)
            throws ComumException, SQLException {
        PreparedStatement ps = null;

        StringBuffer sql = new StringBuffer();
        sql.append(
                "INSERT INTO SESSION.TMP_MENSAGENS_ENVIO_ARQUIVO_DDA                                                                                                                                             ");
        sql.append(
                "            SELECT X.IDMENSAGEMDDA,                                                                                                                                                             ");
        sql.append(
                "                   /*Nesse ponto sera chamado um parametro para definir a quantidade de registros que cada arquivo terá*/                                                                       ");
        sql.append(
                "                   INT(X.NUMERO_LINHA/?) as NUMAGRUPAMENTO_ARQ,                                                                                                                                ");
        sql.append(
                "                   /*Nesse ponto sera chamado um parametro para definir a quantidade de arquivos que cada interação do loop gravará*/                                                           ");
        sql.append(
                "                   INT(INT(X.NUMERO_LINHA/?)/?) as NUMAGRUPAMENTO_LOOP,                                                                                                                       ");
        sql.append(
                "                   X.DATAMOVIMENTO,                                                                                                                                                             ");
        sql.append(
                "                   X.CODTIPOMENSAGEMDDA                                                                                                                                                         ");
        sql.append(
                "            FROM ( SELECT MSG.IDMENSAGEMDDA,                                                                                                                                                    ");
        sql.append(
                "                          /*Ordenando as mensagens por código de mensagem e depois idmensagemdda*/                                                                                              ");
        sql.append(
                "                          ROWNUMBER() OVER (PARTITION BY MSG.DATAMOVIMENTO, MSG.CODTIPOMENSAGEMDDA ORDER BY MSG.CODTIPOMENSAGEMDDA, MSG.IDMENSAGEMDDA) - 1 AS NUMERO_LINHA,                     ");
        sql.append(
                "                          MSG.DATAMOVIMENTO,                                                                                                                                                    ");
        sql.append(
                "                          COALESCE(TM1.CODTIPOARQUIVOCORRESPONDENTE,MSG.CODTIPOMENSAGEMDDA) AS CODTIPOMENSAGEMDDA                                                                               ");
        sql.append(
                "                   FROM DDA.MENSAGEMDDA MSG                                                                                                                                                     ");
        sql.append(
                "                   LEFT JOIN DDA.MENSAGEMDDABOLETO MBOL ON MBOL.IDMENSAGEMDDA = MSG.IDMENSAGEMDDA                                                                                               ");
        sql.append(
                "                   LEFT JOIN DDA.MENSAGEMDDABAIXAOPERACIONAL MBXO ON MBXO.IDMENSAGEMDDA = MSG.IDMENSAGEMDDA                                                                                     ");
        sql.append(
                "                   LEFT JOIN DDA.MENSAGEMDDABAIXAEFETIVA MBXE ON MBXE.IDMENSAGEMDDA = MSG.IDMENSAGEMDDA                                                                                         ");
        sql.append(
                "                   LEFT JOIN DDA.MENSAGEMDDABENEFICIARIO MBEN ON MBEN.IDMENSAGEMDDA = MSG.IDMENSAGEMDDA                                                                                         ");
        sql.append(
                "                   LEFT JOIN DDA.MENSAGEMDDAPAGADOR MPAG ON MPAG.IDMENSAGEMDDA = MSG.IDMENSAGEMDDA                                                                                              ");
        sql.append(
                "                   JOIN DDA.TIPOMENSAGEMDDA TM1 ON TM1.CODTIPOMENSAGEMDDA = MSG.CODTIPOMENSAGEMDDA AND                                                                                          ");
        sql.append(
                "                                                   TM1.CODCATEGORIAMENSAGEMDDA = 'A'                                                                                                            ");
        sql.append(
                "                   LEFT JOIN DDA.TIPOMENSAGEMDDA TM2 ON TM2.CODTIPOMENSAGEMDDA = TM1.CODTIPOARQUIVOCORRESPONDENTE AND                                                                           ");
        sql.append(
                "                                                        TM2.CODCATEGORIAMENSAGEMDDA = 'A'                                                                                                       ");
        sql.append(
                "                   JOIN DDA.GRADEHORARIA GH ON GH.CODTIPOGRADEHORARIA = COALESCE(TM2.CODTIPOGRADEHORARIA, TM1.CODTIPOGRADEHORARIA) AND                                                          ");
        sql.append(
                "                                               CURRENT_TIMESTAMP BETWEEN GH.DATAHORAABERTURA AND GH.DATAHORAFECHAMENTO AND                                                                      ");
        sql.append(
                "                                               GH.DATAREFERENCIA = MSG.DATAMOVIMENTO                                                                                                            ");
        sql.append(
                "                   WHERE MSG.BOLMENSAGEMPENDENTE = 1                                                                                                                                            ");
        sql.append(
                "                   AND MSG.CODSITUACAOMENSAGEMDDA = 1                                                                                                                                           ");
        sql.append(
                "                   /*Verificacão do parametro de contingência*/                                                                                                                                 ");
        sql.append(
                "                   AND EXISTS (SELECT 1 FROM DDA.PARAMETRO PA WHERE PA.IDPARAMETRO = 26 AND PA.VALORBASEPARAMETRO = 0)                                                                          ");
        sql.append(
                "                   /*Verifica se a mensagem já está associada a um arquivo*/                                                                                                                    ");
        sql.append(
                "                   AND NOT EXISTS (SELECT 1                                                                                                                                                     ");
        sql.append(
                "                                   FROM DDA.LOGDETALHEENVIOARQUIVODDA LOGDET                                                                                                                    ");
        sql.append(
                "                                   WHERE LOGDET.IDMENSAGEMDDA = MSG.IDMENSAGEMDDA)                                                                                                              ");
        sql.append(
                "                   /*Somente retornará se a mensagens for OUTROS ou se não houver nenhuma mensagem pendente que a mensagem avaliada seja dependente*/                                           ");
        sql.append(
                "                   AND (CASE                                                                                                                                                                    ");
        sql.append(
                "                        WHEN MBEN.IDMENSAGEMDDA IS NOT NULL AND TM1.BOLEXIGEMENSAGEMRETORNO = 1 THEN 'BENEFICIARIO'                                                                             ");
        sql.append(
                "                        WHEN MPAG.IDMENSAGEMDDA IS NOT NULL AND TM1.BOLEXIGEMENSAGEMRETORNO = 1 THEN 'PAGADOR'                                                                                  ");
        sql.append(
                "                        WHEN MBOL.IDMENSAGEMDDA IS NOT NULL AND TM1.BOLEXIGEMENSAGEMRETORNO = 1 THEN 'BOLETO'                                                                                   ");
        sql.append(
                "                        WHEN MBXO.IDMENSAGEMDDA IS NOT NULL AND TM1.BOLEXIGEMENSAGEMRETORNO = 1 THEN 'BAIXA OPERACIONAL'                                                                        ");
        sql.append(
                "                        WHEN MBXE.IDMENSAGEMDDA IS NOT NULL AND TM1.BOLEXIGEMENSAGEMRETORNO = 1 THEN 'BAIXA EFETIVA'                                                                            ");
        sql.append(
                "                        ELSE 'OUTROS'                                                                                                                                                           ");
        sql.append(
                "                        END = 'OUTROS'                                                                                                                                                          ");
        sql.append(
                "                        OR                                                                                                                                                                      ");
        sql.append(
                "                        MSG.IDMENSAGEMDDA = CASE                                                                                                                                                ");
        sql.append(
                "                                            WHEN TM1.BOLEXIGEMENSAGEMRETORNO = 1 THEN                                                                                                           ");
        sql.append(
                "                                               /* Usando o LEAST para verifcar entre os vários valores qual é o menor                                                                           ");
        sql.append(
                "                                                * Buscando o menor IDMENSAGEMDDA existente para cada possibilidade de envio de MENSAGEMDDA                                                      ");
        sql.append(
                "                                                * para verificar a prioridade entre as mensagens                                                                                                ");
        sql.append(
                "                                                */                                                                                                                                              ");
        sql.append(
                "                                               LEAST (CASE                                                                                                                                      ");
        sql.append(
                "                                                      /*Nesse caso somente busca o MIN na tabela beneficiário se a mensagem avaliada for de beneficiário*/                                      ");
        sql.append(
                "                                                      WHEN MBEN.IDMENSAGEMDDA IS NOT NULL THEN                                                                                                  ");
        sql.append(
                "                                                         (SELECT COALESCE(MIN(MBEN2.IDMENSAGEMDDA),MSG.IDMENSAGEMDDA + 1)                                                                       ");
        sql.append(
                "                                                          FROM DDA.MENSAGEMDDABENEFICIARIO MBEN2                                                                                                ");
        sql.append(
                "                                                          JOIN DDA.MENSAGEMDDA MSG2 ON MSG2.IDMENSAGEMDDA = MBEN2.IDMENSAGEMDDA AND                                                             ");
        sql.append(
                "                                                                                       MSG2.BOLMENSAGEMPENDENTE = 1                                                                             ");
        sql.append(
                "                                                          WHERE MBEN2.NUMCPFCNPJBENEFICIARIO = MBEN.NUMCPFCNPJBENEFICIARIO )                                                                    ");
        sql.append(
                "                                                      /*Esse ELSE serve para retornar o mesmo ID somados a 1 para que o NULL não seja retornado no LEAST*/                                      ");
        sql.append(
                "                                                      ELSE MSG.IDMENSAGEMDDA + 1                                                                                                                ");
        sql.append(
                "                                                      END,                                                                                                                                      ");
        sql.append(
                "                                                      CASE                                                                                                                                      ");
        sql.append(
                "                                                      /*Nesse caso somente busca o MIN na tabela pagadores se a mensagem avaliada for de pagador*/                                              ");
        sql.append(
                "                                                      WHEN MPAG.IDMENSAGEMDDA IS NOT NULL THEN                                                                                                  ");
        sql.append(
                "                                                         (SELECT COALESCE(MIN(MPAG2.IDMENSAGEMDDA),MSG.IDMENSAGEMDDA + 1)                                                                       ");
        sql.append(
                "                                                          FROM DDA.MENSAGEMDDAPAGADOR MPAG2                                                                                                     ");
        sql.append(
                "                                                          JOIN DDA.MENSAGEMDDA MSG2 ON MSG2.IDMENSAGEMDDA = MPAG2.IDMENSAGEMDDA AND                                                             ");
        sql.append(
                "                                                                                       MSG2.BOLMENSAGEMPENDENTE = 1                                                                             ");
        sql.append(
                "                                                          WHERE MPAG2.NUMCPFCNPJPAGADOR = MPAG.NUMCPFCNPJPAGADOR)                                                                               ");
        sql.append(
                "                                                      ELSE MSG.IDMENSAGEMDDA + 1                                                                                                                ");
        sql.append(
                "                                                      END,                                                                                                                                      ");
        sql.append(
                "                                                      CASE                                                                                                                                      ");
        sql.append(
                "                                                      /*Nesse caso somente busca o MIN na tabela de boleto se a mensagem avaliada for de Boleto, Baixa Operacional ou Baixa Efetiva*/           ");
        sql.append(
                "                                                      WHEN COALESCE(MBOL.NUMCODIGOBARRA,MBXO.NUMCODIGOBARRA,MBXE.NUMCODIGOBARRA) IS NOT NULL THEN                                               ");
        sql.append(
                "                                                         (SELECT COALESCE(MIN(MBOL2.IDMENSAGEMDDA),MSG.IDMENSAGEMDDA + 1)                                                                       ");
        sql.append(
                "                                                          FROM DDA.MENSAGEMDDABOLETO MBOL2                                                                                                      ");
        sql.append(
                "                                                          JOIN DDA.MENSAGEMDDA MSG2 ON MSG2.IDMENSAGEMDDA = MBOL2.IDMENSAGEMDDA AND                                                             ");
        sql.append(
                "                                                                                       MSG2.BOLMENSAGEMPENDENTE = 1                                                                             ");
        sql.append(
                "                                                          WHERE MBOL2.NUMCODIGOBARRA = COALESCE(MBOL.NUMCODIGOBARRA,MBXO.NUMCODIGOBARRA,MBXE.NUMCODIGOBARRA))                                   ");
        sql.append(
                "                                                      ELSE MSG.IDMENSAGEMDDA + 1                                                                                                                ");
        sql.append(
                "                                                      END,                                                                                                                                      ");
        sql.append(
                "                                                      CASE                                                                                                                                      ");
        sql.append(
                "                                                      /*Nesse caso somente busca o MIN na tabela de baixa operacional se a mensagem avaliada for de Boleto, Baixa Operacional ou Baixa Efetiva*/");
        sql.append(
                "                                                      WHEN COALESCE(MBOL.NUMCODIGOBARRA,MBXO.NUMCODIGOBARRA,MBXE.NUMCODIGOBARRA) IS NOT NULL THEN                                               ");
        sql.append(
                "                                                         (SELECT COALESCE(MIN(MBXO2.IDMENSAGEMDDA),MSG.IDMENSAGEMDDA + 1)                                                                        ");
        sql.append(
                "                                                          FROM DDA.MENSAGEMDDABAIXAOPERACIONAL MBXO2                                                                                             ");
        sql.append(
                "                                                          JOIN DDA.MENSAGEMDDA MSG2 ON MSG2.IDMENSAGEMDDA = MBXO2.IDMENSAGEMDDA AND                                                              ");
        sql.append(
                "                                                                                       MSG2.BOLMENSAGEMPENDENTE = 1                                                                              ");
        sql.append(
                "                                                          WHERE MBXO2.NUMCODIGOBARRA = COALESCE(MBOL.NUMCODIGOBARRA,MBXO.NUMCODIGOBARRA,MBXE.NUMCODIGOBARRA))                                    ");
        sql.append(
                "                                                      ELSE MSG.IDMENSAGEMDDA + 1                                                                                                                 ");
        sql.append(
                "                                                      END,                                                                                                                                       ");
        sql.append(
                "                                                      CASE                                                                                                                                       ");
        sql.append(
                "                                                      /*Nesse caso somente busca o MIN na tabela de baixa efetiva se a mensagem avaliada for de Boleto, Baixa Operacional ou Baixa Efetiva*/     ");
        sql.append(
                "                                                      WHEN COALESCE(MBOL.NUMCODIGOBARRA,MBXO.NUMCODIGOBARRA,MBXE.NUMCODIGOBARRA) IS NOT NULL THEN                                                ");
        sql.append(
                "                                                         (SELECT COALESCE(MIN(MBXE2.IDMENSAGEMDDA),MSG.IDMENSAGEMDDA + 1)                                                                        ");
        sql.append(
                "                                                          FROM DDA.MENSAGEMDDABAIXAEFETIVA MBXE2                                                                                                 ");
        sql.append(
                "                                                          JOIN DDA.MENSAGEMDDA MSG2 ON MSG2.IDMENSAGEMDDA = MBXE2.IDMENSAGEMDDA AND                                                              ");
        sql.append(
                "                                                                                       MSG2.BOLMENSAGEMPENDENTE = 1                                                                              ");
        sql.append(
                "                                                          WHERE MBXE2.NUMCODIGOBARRA = COALESCE(MBOL.NUMCODIGOBARRA,MBXO.NUMCODIGOBARRA,MBXE.NUMCODIGOBARRA))                                    ");
        sql.append(
                "                                                      ELSE MSG.IDMENSAGEMDDA + 1                                                                                                                 ");
        sql.append(
                "                                                      END                                                                                                                                        ");
        sql.append(
                "                                                     )                                                                                                                                           ");
        sql.append(
                "                                            ELSE MSG.IDMENSAGEMDDA                                                                                                                               ");
        sql.append(
                "                                            END)                                                                                                                                                 ");
        sql.append(
                "                 ) X                                                                                                                                                                             ");
        sql.append(
                "            ORDER BY X.DATAMOVIMENTO, X.CODTIPOMENSAGEMDDA, X.NUMERO_LINHA                                                                                                                       ");
         sql.append(" FETCH FIRST "+qtdMaximaTotalRegistros+" ROWS ONLY ");

        debug("SQL Inserir na tabela temporaria do envio de arquivo: " + sql.toString().replaceAll("  ", "").trim());

        try {
            ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, qtdMaximaRegistrosArquivo);
            ps.setLong(2, qtdMaximaRegistrosArquivo);
            ps.setLong(3, qtdMaximaArquivoIteracao);
            // ps.setInt(4, qtdMaximaTotalRegistros.intValue());

            debug("Inserindo na tabela temporaria do envio de arquivo...");
            ps.execute();

        } finally {
            fecharStatement(ps);
        }
    }

    /**
     * Método responsável por criar o indice na tabela temporaria do envio de arquivo
     * 
     * @param conn void
     * @throws SQLException
     * 
     */
    private void executarIndiceTabelaTemporaria(Connection conn) throws SQLException {
        Comando comando = getComando("CRIA_INDICE_TABELA_TEMPORARIA_ENVIO_ARQUIVO");

        comando.executarAtualizacao(conn);

    }

    /**
     * Método responsável por fazer o runStats da tabela temporaria do envio de arquivo
     * 
     * @param conn void
     * @throws SQLException
     * 
     */
    private void executarRunstatsTabelaTemporaria(Connection conn) throws SQLException {
        Comando comando = getComando("RUNSTATS_TABELA_TEMPORARIA_ENVIO_ARQUIVO");

        comando.executarAtualizacao(conn);
    }

    /**
     * Método responsável por fazer toda a logica para incluir na tabela DDA.LOGENVIOARQUIVODDA e DDA.LOGDETENVIOARQUIVODDA
     * 
     * @param conn
     * @throws ComumException, SQLException void
     * @throws SQLException
     * 
     */
    private void incluirLogEnvioArquivoDDA(Connection conn) throws ComumException, SQLException, SQLException {
        ResultSet rs = null;
        Comando comando = null;

        try {
            comando = getComando("CURSOR_ENVIO_ARQUIVO");
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);
            List<EnvioArquivoDto> listaEnvioArquivoDto = new ArrayList<EnvioArquivoDto>();

            while (rs.next()) {

                getLogger().info(" ### Inicio do Bloco de EXCLUSÃO das mensagens ####");
                EnvioArquivoDto envioArquivoDto = new EnvioArquivoDto();
                envioArquivoDto.setNumAgrupamento(rs.getInt("NUMAGRUPAMENTO_LOOP"));
                envioArquivoDto.setDataMovimento(rs.getDate("DATAMOVIMENTO") != null ? new DateTimeDB(rs.getDate("DATAMOVIMENTO").getTime()) : null);
                envioArquivoDto.setCodTipoMensagemDDA(rs.getString("CODTIPOMENSAGEMDDA"));

                listaEnvioArquivoDto.add(envioArquivoDto);
            }

            if (!ObjectUtil.isEmpty(listaEnvioArquivoDto)) {
                conn.commit();

                for (EnvioArquivoDto envioArquivoDto : listaEnvioArquivoDto) {
                    inserirRegistrosLogEnvioArquivoDDA(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getDataMovimento(), envioArquivoDto.getCodTipoMensagemDDA());

                    conn.commit();
                }
            } else {
                conn.setAutoCommit(Boolean.TRUE);
            }


        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
        }
    }

    /**
     * Método responsável por inserir na tabela DDA.LOGENVIOARQUIVODDA de acordo com o loop
     * 
     * @param conn
     * @throws ComumException, SQLException void
     * 
     */
    private void inserirRegistrosLogEnvioArquivoDDA(Connection conn, Integer numAgrupamentoLoop, DateTimeDB dataMovimento, String codTipoMensagemDDA)
            throws ComumException, SQLException {
        final String sql = " WITH                                                                                                                                                                "
                + "                SEL_TMP_ARQ AS ( SELECT ( TMP_MSG.CODTIPOMENSAGEMDDA                                            || '_' ||                                           "
                + "                                          '02038232'                                                            || '_' ||                                           "
                + "                                          TO_CHAR(TMP_MSG.DATAMOVIMENTO,'YYYYMMDD')                             || '_' ||                                           "
                + "                                          /*Próximo sequencial de arquivo para o CodTipoMensagem informado*/                                                        "
                + "                                          (SELECT LPAD(COALESCE(MAX(RIGHT(LOG.DESCNOMEARQUIVOENVIADO,5)),0) + 1 + TMP_MSG.NUMAGRUPAMENTO_ARQ ,5,'0')                "
                + "                                           FROM DDA.LOGENVIOARQUIVODDA LOG                                                                                          "
                + "                                           WHERE LOG.DATAMOVIMENTO   = TMP_MSG.DATAMOVIMENTO                                                                        "
                + "                                           AND   LOG.CODTIPOMENSAGEM = TMP_MSG.CODTIPOMENSAGEMDDA ) ) AS NOVO_NOME_ARQUIVO,                                         "
                + "                                        TMP_MSG.DATAMOVIMENTO                                         AS DATAMOVIMENTO,                                             "
                + "                                        TMP_MSG.CODTIPOMENSAGEMDDA                                    AS CODTIPOMENSAGEMDDA,                                        "
                + "                                        TMP_MSG.NUMAGRUPAMENTO_ARQ                                    AS NUMAGRUPAMENTO_ARQ                                         "
                + "                                 FROM TABLE ( SELECT DISTINCT T.CODTIPOMENSAGEMDDA,                                                                                 "
                + "                                                              T.DATAMOVIMENTO,                                                                                      "
                + "                                                              T.NUMAGRUPAMENTO_ARQ                                                                                  "
                + "                                              FROM SESSION.TMP_MENSAGENS_ENVIO_ARQUIVO_DDA T                                                                        "
                + "                                              WHERE T.NUMAGRUPAMENTO_LOOP = ?                                          "
                + "                                              AND   T.DATAMOVIMENTO       = ?                                   "
                + "                                              AND   T.CODTIPOMENSAGEMDDA  = ? ) AS TMP_MSG ),                          "
                + "                INSERT_LOGENVIO AS (SELECT IDLOGENVIOARQUIVODDA,                                                                                                    "
                + "                                           DESCNOMEARQUIVOENVIADO                                                                                                   "
                + "                                    FROM FINAL TABLE (INSERT                                                                                                        "
                + "                                                      INTO DDA.LOGENVIOARQUIVODDA (DESCNOMEARQUIVOENVIADO,                                                          "
                + "                                                                                   DATAHORAARQUIVO,                                                                 "
                + "                                                                                   DATAMOVIMENTO,                                                                   "
                + "                                                                                   CODTIPOMENSAGEM,                                                                 "
                + "                                                                                   DATAHORAENVIO)                                                                   "
                + "                                                      SELECT TMP_ARQ.NOVO_NOME_ARQUIVO        AS DESCNOMEARQUIVOENVIADO,                                            "
                + "                                                             NULL                             AS DATAHORAARQUIVO,                                                   "
                + "                                                             TMP_ARQ.DATAMOVIMENTO            AS DATAMOVIMENTO,                                                     "
                + "                                                             TMP_ARQ.CODTIPOMENSAGEMDDA       AS CODTIPOMENSAGEMDDA,                                                "
                + "                                                             NULL                             AS DATAHORAENVIO                                                      "
                + "                                                      FROM SEL_TMP_ARQ TMP_ARQ ) ),                                                                                 "
                + "                INSERT_LOGDET AS (SELECT                                                                                                                            "
                + "                                  COUNT(*) QTD_DET                                                                                                                  "
                + "                                  FROM FINAL TABLE (INSERT                                                                                                          "
                + "                                                    INTO DDA.LOGDETALHEENVIOARQUIVODDA (IDLOGENVIOARQUIVODDA,                                                       "
                + "                                                                                        IDMENSAGEMDDA)                                                              "
                + "                                                    SELECT ENV.IDLOGENVIOARQUIVODDA,                                                                                "
                + "                                                           TMP_MSG.IDMENSAGEMDDA                                                                                    "
                + "                                                    FROM SESSION.TMP_MENSAGENS_ENVIO_ARQUIVO_DDA TMP_MSG                                                            "
                + "                                                    JOIN SEL_TMP_ARQ TMP_ARQ ON TMP_ARQ.CODTIPOMENSAGEMDDA = TMP_MSG.CODTIPOMENSAGEMDDA AND                         "
                + "                                                                                TMP_ARQ.DATAMOVIMENTO      = TMP_MSG.DATAMOVIMENTO AND                              "
                + "                                                                                TMP_ARQ.NUMAGRUPAMENTO_ARQ = TMP_MSG.NUMAGRUPAMENTO_ARQ                             "
                + "                                                    JOIN INSERT_LOGENVIO ENV    ON ENV.DESCNOMEARQUIVOENVIADO = TMP_ARQ.NOVO_NOME_ARQUIVO                           "
                + "                                                    WHERE TMP_MSG.NUMAGRUPAMENTO_LOOP = ?                              "
                + "                                                    AND   TMP_MSG.DATAMOVIMENTO       = ?                       "
                + "                                                    AND   TMP_MSG.CODTIPOMENSAGEMDDA  = ? ) )                          "
                + "                                                 SELECT 1 FROM SYSIBM.SYSDUMMY1";
        debug("SQL -  Inserir na tabela Historico MensagemDDA : " + sql.replaceAll("  ", "").trim());

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numAgrupamentoLoop);
            ps.setDate(2, new java.sql.Date(dataMovimento.getTime()));
            ps.setString(3, codTipoMensagemDDA);

            ps.setInt(4, numAgrupamentoLoop);
            ps.setDate(5, new java.sql.Date(dataMovimento.getTime()));
            ps.setString(6, codTipoMensagemDDA);

            ps.execute();
        } catch (Exception e) {
            System.out.println("teste");
        } finally {
            fecharStatement(ps);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#atualizarLogEnvioArquivoDDA(br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA)
     */
    public void atualizarLogEnvioArquivoDDA(LogEnvioArquivoDDA logEnvioArquivoDDA) throws ComumException {
        getLogger().debug("*******atualizarLogEnvioArquivoDDA*******");
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("dataMovimento", logEnvioArquivoDDA.getDataMovimento());
        parametros.put("descNomeArquivoEnviado", logEnvioArquivoDDA.getDescNomeArquivoEnviado());
        parametros.put("idLogEnvioArquivoDDA", logEnvioArquivoDDA.getId());

        executarUpdate("ATUALIZAR_LOG_ENVIO_ARQUIVO_DDA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#obterUltimoSequencialArquivo(java.lang.String, java.util.Date)
     */
    public Integer obterUltimoSequencialArquivo(String codTipoMensagem, Date dataMovimento) throws ComumException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        try {
            comando = getComando("OBTER_ULTIMO_SEQUENCIAL_ARQUIVO");
            comando.adicionarVariavel("codTipoMensagem", codTipoMensagem);
            comando.adicionarVariavel("dataMovimento", dataMovimento);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                return rs.getInt("ULTIMO");
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharConexao(conn);
            fecharComando(comando);
        }
        return null;
    }
    
    private void rollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                getLogger().erro(e1, e1.getMessage());
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#excluirMensagensTabelaTmp()
     */
    public void excluirMensagensTabelaTmp() throws ComumException {
        getLogger().debug("*******atualizarDataHoraArquivo*******");
        executarUpdate("EXCLUIR_MENSAGENS_TABELA_TEMPORARIA");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#registrarMensagensTabelaTmp(java.lang.Integer, java.lang.Integer,
     *      java.lang.Integer)
     */
    public void registrarMensagensTabelaTmp(Integer qtdParametroMensagensEnviadasPorRajada, Integer qtdMaxRegistrosPorStep, Integer qtdParametroNumPrioridadeMsg)
            throws ComumException {
        getLogger().info("iniciando a inserção dos registros na tabela temporaria do motor de envio de msgs");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("qtdParametroMensagensEnviadasPorRajada", qtdParametroMensagensEnviadasPorRajada);
        parametros.put("qtdMaxRegistrosPorStep", qtdMaxRegistrosPorStep);
        parametros.put("numPrioridadeConsideradaMais1", qtdParametroNumPrioridadeMsg + 1);
        parametros.put("numPrioridadeConsiderada", qtdParametroNumPrioridadeMsg);


        executarUpdate("INSERIR_TABELA_TEMPORARIA_MENSAGEM", parametros);

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#isVerificarGradeHorariaEnviarMotorArquivo()
     */
    public Boolean isVerificarGradeHorariaEnviarMotorArquivo(Integer tempoInativoMotorEnvioArquivo, String listaGradeHoraria) throws ComumException {
        getLogger().debug("*******isVerificarGradeHorariaEnviarMotorArquivo*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        try {
            comando = getComando("VERIFICAR_INATIVIDADE_MOTOR_ENVIO_ARQUIVO");
            comando.adicionarVariavel("tempoInativoMotorEnvioArquivo", tempoInativoMotorEnvioArquivo);
            comando.adicionarVariavel("listaGradeHoraria", listaGradeHoraria);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                return rs.getLong(1) > 0;
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return false;
    }
}