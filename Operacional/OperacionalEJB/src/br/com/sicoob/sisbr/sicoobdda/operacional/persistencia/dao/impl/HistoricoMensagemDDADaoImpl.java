package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EnvioArquivoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.HistoricoMensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * 
 * @author George.santos
 * 
 */
public class HistoricoMensagemDDADaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements HistoricoMensagemDDADao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.historicomensagem.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-historicomensagem";

    /**
     * @param datasource
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public HistoricoMensagemDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.HistoricoMensagemDDADao#arquivarMensagensDDA(java.lang.Long, java.lang.Long)
     */
    public void arquivarMensagensDDA(Long qtdRegistroBlocoExpurgo, Long qtdDiasLimiteExpurgo) throws ComumException {
        debug("Estabelecendo a conexão...");
        Connection conn = estabelecerConexao();
        try {
            Date dataParticao = new Date();
            conn.setAutoCommit(Boolean.FALSE);

            getLogger().info("Inicio da criação da tabela temporaria de arquivamento");
            criarTmpArquivamento(conn);

            getLogger().info("Inserção na tabela temporaria de arquivamento");
            inserirTmpMensagemDDAArquivamento(conn, qtdRegistroBlocoExpurgo, qtdDiasLimiteExpurgo);

            getLogger().info("Criação do indice  da tabela temporaria de arquivamento");
            indiceTmpMovimentoConsolidado(conn);

            getLogger().info("Criação do Runstats  da tabela temporaria de arquivamento");
            runStatsTmpMovimentoConsolidado(conn);

            getLogger().info("Inicio do processo de arquivamento - Inclusão na tabela de historico e depois expurgo das mensagens na tabela DDA.MensagemDDA");
            arquivamentoMensagens(conn, dataParticao);



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
    private void criarTmpArquivamento(Connection conn) throws ComumException, SQLException {
        Comando comando = null;

        try {
            comando = getComando("CRIA_TABELA_TEMPORARIA_ARQUIVAMENTO");
            debug("Comando:  " + comando.getSqlEmUso());
            comando.executarAtualizacao(conn);

        } catch (PersistenceException e) {
            throw new ComumException("historico.erro.criar.tmp.arquivamento", e);
        } finally {
            fecharComando(comando);
        }
    }

    /**
     * Método responsável por inserir na tabela temporaria de arquivamento definindo a quatindade de registros e o tempo de expurgo
     * 
     * @param conn
     * @param qtdRegistroBlocoExpurgo
     * @param qtdDiasLimiteExpurgo
     * @throws ComumException, SQLException void
     * 
     */
    private void inserirTmpMensagemDDAArquivamento(Connection conn, Long qtdRegistroBlocoExpurgo, Long qtdDiasLimiteExpurgo) throws ComumException, SQLException {
        PreparedStatement ps = null;

        final String sql = "INSERT INTO SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO                                                                                                                      "
                + "                             SELECT X.IDMENSAGEMDDA,                                                                                                                  "
                + "                                    X.NUMERO_LINHA/? AS NUMAGRUPAMENTO,                                                                                               "
                + "                                    X.BOLORIGEM                                                                                                                       "
                + "                             FROM ( SELECT MSG.IDMENSAGEMDDA,                                                                                                         "
                + "                                           ROWNUMBER() OVER (ORDER BY CASE WHEN MSG.IDMENSAGEMDDAORIGEM IS NULL THEN 1 ELSE 0 END, MSG.IDMENSAGEMDDA) AS NUMERO_LINHA,"
                + "                                           CASE WHEN MSG.IDMENSAGEMDDAORIGEM IS NULL THEN 1 ELSE 0 END AS BOLORIGEM                                                   "
                + "                                    FROM DDA.MENSAGEMDDA MSG                                                                                                          "
                + "                                    WHERE MSG.DATAMOVIMENTO < (CURRENT_DATE - ? DAYS)                                                                                 "
                + "                                    AND MSG.CODTIPOMENSAGEMDDA NOT IN ('ERRO001')                                                                                     "
                + "                                    AND MSG.BOLMENSAGEMPENDENTE = 0                                                                                                   "
                + "                                    AND NOT EXISTS ( SELECT 1                                                                                                         "
                + "                                                     FROM DDA.MENSAGEMDDA MSGORI                                                                                      "
                + "                                                     WHERE MSGORI.IDMENSAGEMDDA = MSG.IDMENSAGEMDDAORIGEM                                                             "
                + "                                                     AND MSGORI.BOLMENSAGEMPENDENTE = 1 )                                                                             "
                + "                                  AND NOT EXISTS (   SELECT 1                                                                                                           "
                + "                                                     FROM DDA.MENSAGEMDDA MSGRET                                                                                      "
                + "                                                     WHERE MSGRET.IDMENSAGEMDDA = MSG.IDMENSAGEMDDAORIGEM                                                             "
                + "                                                     AND MSGRET.DATAMOVIMENTO >= (CURRENT_DATE - ? DAYS)                                                                "
                + "                                                     AND MSGRET.BOLMENSAGEMPENDENTE = 0 )                                                                             "
                + "                                  ) X                                                                                                                                 "
                + "                             ORDER BY X.NUMERO_LINHA                                                                                                                  ";

        debug("SQL Inserir na tabela temporaria do arquivamento: " + sql.replaceAll("  ", "").trim());

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, qtdRegistroBlocoExpurgo);
            ps.setLong(2, qtdDiasLimiteExpurgo);
            ps.setLong(3, qtdDiasLimiteExpurgo);

            debug("Inserindo na tabela temporaria do arquivamento...");
            ps.execute();
        } finally {
            fecharStatement(ps);
        }
    }

    /**
     * Método responsável por criar o indice na tabela temporaria
     * 
     * @param conn void
     * @throws SQLException
     * 
     */
    private void indiceTmpMovimentoConsolidado(Connection conn) throws SQLException {
        Comando comando = getComando("CRIA_INDICE_TABELA_TEMPORARIA_ARQUIVAMENTO");

        comando.executarAtualizacao(conn);
    }

    /**
     * Método responsável por fazer o runStats da tabela de arquivamento
     * 
     * @param conn void
     * @throws SQLException
     * 
     */
    private void runStatsTmpMovimentoConsolidado(Connection conn) throws SQLException {
        Comando comando = getComando("RUNSTATS_TABELA_TEMPORARIA_ARQUIVAMENTO");

        comando.executarAtualizacao(conn);
    }

    /**
     * Método responsável por fazer toda a logica das inclusões na historico e exclusao da mensagemDDa
     * 
     * @param conn
     * @throws ComumException, SQLException void
     * @throws SQLException
     * 
     */
    private void arquivamentoMensagens(Connection conn, Date dataParticao) throws ComumException, SQLException, SQLException {
        ResultSet rs = null;
        Comando comando = null;

        try {
            comando = getComando("CURSOR_ARQUIVAMENTO_MENSAGENS");
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);

            List<EnvioArquivoDto> listaEnvioArquivoDto = new ArrayList<EnvioArquivoDto>();
            while (rs.next()) {
                getLogger().info(" ####  Inicio do Bloco de INCLUSÃO dos Historicos ###");
                EnvioArquivoDto envioArquivoDto = new EnvioArquivoDto();
                envioArquivoDto.setNumAgrupamento(rs.getInt("NUMAGRUPAMENTO"));
                envioArquivoDto.setBolOrigem(rs.getBoolean("BOLORIGEM"));

                listaEnvioArquivoDto.add(envioArquivoDto);
            }

            if (!ObjectUtil.isEmpty(listaEnvioArquivoDto)) {

                conn.commit();

                for (EnvioArquivoDto envioArquivoDto : listaEnvioArquivoDto) {
                    incluirHistoricoMensagemDDA(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABoleto(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDARetornoCIP(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoLogDetalheMensagemDDA(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDAAceite(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABaixaEfetiva(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABaixaOperacional(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABeneficiario(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABeneficiarioConvenio(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABeneficiarioRepresentante(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABoletoGrupoCalculo(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABoletoNotaFiscal(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDABoletoTextoInfo(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDAConsultaBoleto(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDAPagadorConta(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDAPagadorAgregado(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDAPagador(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);
                    incluirHistoricoMensagemDDATerceiroAutorizado(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem(), dataParticao);

                    getLogger().info(" ### Inicio do Bloco de EXCLUSÃO das mensagens ####");
                    excluirMensagemDDABaixaEfetiva(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDABaixaOperacional(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDABeneficiarioRepresentante(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDABeneficiarioConvenio(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDABeneficiario(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDATerceiroAutorizado(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDAAceite(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDAConsultaBoleto(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirLogDetalheMensagemDDA(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDARetornoCip(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDABoletoGrupoCalculo(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDABoletoNotaFiscal(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDABoletoTextoInfo(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDABoleto(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDAPagadorConta(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDAPagadorAgregado(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDAPagador(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());
                    excluirMensagemDDA(conn, envioArquivoDto.getNumAgrupamento(), envioArquivoDto.getBolOrigem());

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
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDA(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDA (IDMENSAGEMDDA,                                    "
                + "                               IDMENSAGEMDDAORIGEM,                                "
                + "                               CODTIPOMENSAGEMDDA,                                 "
                + "                               DATAMOVIMENTO,                                      "
                + "                               DATAHORAMENSAGEM,                                   "
                + "                               QTDTOTREG,                                          "
                + "                               DATAHORAPROTOCOLO,                                  "
                + "                               NUMOPERACAO,                                        "
                + "                               DESCERROPROTOCOLO,                                  "
                + "                               XMLMENSAGEM,                                        "
                + "                               BOLORIGEMSICOOB,                                    "
                + "                               DATAHORACADASTRO,                                   "
                + "                               NUMPRIORIDADEENVIO,                                 "
                + "                               NUMCONTROLEDDA,                                     "
                + "                               IDINSTITUICAOSOLICITANTE,                           "
                + "                               IDUSUARIOSOLICITANTE,                               "
                + "                               IDCANAL,                                            "
                + "                               BOLEXCEDEUSLA,                                      "
                + "                               BOLMENSAGEMPENDENTE,                                "
                + "                               CODSITUACAOMENSAGEMDDA,                             "
                + "                               DATAPARTICAO)                                       "
                + "              SELECT M.IDMENSAGEMDDA,                                              "
                + "                     M.IDMENSAGEMDDAORIGEM,                                        "
                + "                     M.CODTIPOMENSAGEMDDA,                                         "
                + "                     M.DATAMOVIMENTO,                                              "
                + "                     M.DATAHORAMENSAGEM,                                           "
                + "                     M.QTDTOTREG,                                                  "
                + "                     M.DATAHORAPROTOCOLO,                                          "
                + "                     M.NUMOPERACAO,                                                "
                + "                     M.DESCERROPROTOCOLO,                                          "
                + "                     M.XMLMENSAGEM,                                                "
                + "                     M.BOLORIGEMSICOOB,                                            "
                + "                     M.DATAHORACADASTRO,                                           "
                + "                     M.NUMPRIORIDADEENVIO,                                         "
                + "                     M.NUMCONTROLEDDA,                                             "
                + "                     M.IDINSTITUICAOSOLICITANTE,                                   "
                + "                     M.IDUSUARIOSOLICITANTE,                                       "
                + "                     M.IDCANAL,                                                    "
                + "                     M.BOLEXCEDEUSLA,                                              "
                + "                     M.BOLMENSAGEMPENDENTE,                                        "
                + "                     M.CODSITUACAOMENSAGEMDDA,                                     "
                + "                     ?                                                  "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "              JOIN DDA.MENSAGEMDDA M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA       WHERE TMP.NUMAGRUPAMENTO = ? "
                + "              AND TMP.BOLORIGEM = ?            ";
        debug("SQL -  Inserir na tabela Historico MensagemDDA : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);

    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABoleto(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABOLETO (IDMENSAGEMDDA,                              "
                + "                                     CODTIPOPESSOABENEFICIARIO,                    "
                + "                                     NUMCPFCNPJBENEFICIARIO,                       "
                + "                                     NOMEBENEFICIARIO,                             "
                + "                                     NOMEFANTASIABENEFICIARIO,                     "
                + "                                     DESCLOGRADOUROBENEFICIARIO,                   "
                + "                                     DESCCIDADEBENEFICIARIO,                       "
                + "                                     SIGLAUFBENEFICIARIO,                          "
                + "                                     NUMCEPBENEFICIARIO,                           "
                + "                                     CODTIPOPESSOABENEFICIARIOFINAL,               "
                + "                                     NUMCPFCNPJBENEFICIARIOFINAL,                  "
                + "                                     NOMEBENEFICIARIOFINAL,                        "
                + "                                     NOMEFANTASIABENEFICIARIOFINAL,                "
                + "                                     CODTIPOPESSOAPAGADOR,                         "
                + "                                     NUMCPFCNPJPAGADOR,                            "
                + "                                     NOMEPAGADOR,                                  "
                + "                                     NOMEFANTASIAPAGADOR,                          "
                + "                                     DESCLOGRADOUROPAGADOR,                        "
                + "                                     DESCCIDADEPAGADOR,                            "
                + "                                     SIGLAUFPAGADOR,                               "
                + "                                     NUMCEPPAGADOR,                                "
                + "                                     CODTIPOPESSOADDAAVALISTA,                     "
                + "                                     NUMCPFCNPJAVALISTA,                           "
                + "                                     NOMEAVALISTA,                                 "
                + "                                     IDCARTEIRA,                                   "
                + "                                     CODMOEDA,                                     "
                + "                                     IDORGAOMOEDA,                                 "
                + "                                     NUMNOSSONUMERO,                               "
                + "                                     NUMCODIGOBARRA,                               "
                + "                                     NUMLINHADIGITAVEL,                            "
                + "                                     DATAVENCIMENTO,                               "
                + "                                     VALORBOLETO,                                  "
                + "                                     NUMDOCUMENTO,                                 "
                + "                                     DATAEMISSAO,                                  "
                + "                                     NUMDIASPROTESTO,                              "
                + "                                     DATALIMITEPAGAMENTO,                          "
                + "                                     NUMPARCELA,                                   "
                + "                                     QTDTOTALPARCELA,                              "
                + "                                     BOLTITULONEGOCIADO,                           "
                + "                                     BOLBLOQUEIOPAGAMENTO,                         "
                + "                                     BOLPAGAMENTOPARCIAL,                          "
                + "                                     QTDPAGAMENTOPARCIAL,                          "
                + "                                     VALORABATIMENTO,                              "
                + "                                     CODINDICADORVALORMINIMO,                      "
                + "                                     VALORMINIMO,                                  "
                + "                                     CODINDICADORVALORMAXIMO,                      "
                + "                                     VALORMAXIMO,                                  "
                + "                                     CODTIPOMODELOCALCULO,                         "
                + "                                     CODAUTORIZACAOVALORDIVERGENTE,                "
                + "                                     IDESPECIEDOCUMENTO,                           "
                + "                                     NUMREFATUALCADBOLETO,                         "
                + "                                     NUMSEQATUALCADBOLETO,                         "
                + "                                     CODTIPOPAGAMENTO,                             "
                + "                                     BOLDDA,                                       "
                + "                                     DATAPARTICAO,                                 "
                + "                                     NUMCODBARRASCAMPOLIVRE,                       "
                + "                                     DATAJUROS,                                    "
                + "                                     CODTIPOJUROS,                                 "
                + "                                     VALORPERCENTUALJUROS,                         "
                + "                                     DATAMULTA,                                    "
                + "                                     CODTIPOMULTA,                                 "
                + "                                     VALORPERCENTUALMULTA,                         "
                + "                                     DATADESCONTO1,                                "
                + "                                     CODTIPODESCONTO1,                             "
                + "                                     VALORPERCENTUALDESCONTO1,                     "
                + "                                     DATADESCONTO2,                                "
                + "                                     CODTIPODESCONTO2,                             "
                + "                                     VALORPERCENTUALDESCONTO2,                     "
                + "                                     DATADESCONTO3,                                "
                + "                                     CODTIPODESCONTO3,                             "
                + "                                     VALORPERCENTUALDESCONTO3)                     "
                + "              SELECT M.IDMENSAGEMDDA,                                              "
                + "                     M.CODTIPOPESSOABENEFICIARIO,                                  "
                + "                     M.NUMCPFCNPJBENEFICIARIO,                                     "
                + "                     M.NOMEBENEFICIARIO,                                           "
                + "                     M.NOMEFANTASIABENEFICIARIO,                                   "
                + "                     M.DESCLOGRADOUROBENEFICIARIO,                                 "
                + "                     M.DESCCIDADEBENEFICIARIO,                                     "
                + "                     M.SIGLAUFBENEFICIARIO,                                        "
                + "                     M.NUMCEPBENEFICIARIO,                                         "
                + "                     M.CODTIPOPESSOABENEFICIARIOFINAL,                             "
                + "                     M.NUMCPFCNPJBENEFICIARIOFINAL,                                "
                + "                     M.NOMEBENEFICIARIOFINAL,                                      "
                + "                     M.NOMEFANTASIABENEFICIARIOFINAL,                              "
                + "                     M.CODTIPOPESSOAPAGADOR,                                       "
                + "                     M.NUMCPFCNPJPAGADOR,                                          "
                + "                     M.NOMEPAGADOR,                                                "
                + "                     M.NOMEFANTASIAPAGADOR,                                        "
                + "                     M.DESCLOGRADOUROPAGADOR,                                      "
                + "                     M.DESCCIDADEPAGADOR,                                          "
                + "                     M.SIGLAUFPAGADOR,                                             "
                + "                     M.NUMCEPPAGADOR,                                              "
                + "                     M.CODTIPOPESSOADDAAVALISTA,                                   "
                + "                     M.NUMCPFCNPJAVALISTA,                                         "
                + "                     M.NOMEAVALISTA,                                               "
                + "                     M.IDCARTEIRA,                                                 "
                + "                     M.CODMOEDA,                                                   "
                + "                     M.IDORGAOMOEDA,                                               "
                + "                     M.NUMNOSSONUMERO,                                             "
                + "                     M.NUMCODIGOBARRA,                                             "
                + "                     M.NUMLINHADIGITAVEL,                                          "
                + "                     M.DATAVENCIMENTO,                                             "
                + "                     M.VALORBOLETO,                                                "
                + "                     M.NUMDOCUMENTO,                                               "
                + "                     M.DATAEMISSAO,                                                "
                + "                     M.NUMDIASPROTESTO,                                            "
                + "                     M.DATALIMITEPAGAMENTO,                                        "
                + "                     M.NUMPARCELA,                                                 "
                + "                     M.QTDTOTALPARCELA,                                            "
                + "                     M.BOLTITULONEGOCIADO,                                         "
                + "                     M.BOLBLOQUEIOPAGAMENTO,                                       "
                + "                     M.BOLPAGAMENTOPARCIAL,                                        "
                + "                     M.QTDPAGAMENTOPARCIAL,                                        "
                + "                     M.VALORABATIMENTO,                                            "
                + "                     M.CODINDICADORVALORMINIMO,                                    "
                + "                     M.VALORMINIMO,                                                "
                + "                     M.CODINDICADORVALORMAXIMO,                                    "
                + "                     M.VALORMAXIMO,                                                "
                + "                     M.CODTIPOMODELOCALCULO,                                       "
                + "                     M.CODAUTORIZACAOVALORDIVERGENTE,                              "
                + "                     M.IDESPECIEDOCUMENTO,                                         "
                + "                     M.NUMREFATUALCADBOLETO,                                       "
                + "                     M.NUMSEQATUALCADBOLETO,                                       "
                + "                     M.CODTIPOPAGAMENTO,                                           "
                + "                     M.BOLDDA,                                                     "
                + "                     ?,                                                 "
                + "                     M.NUMCODBARRASCAMPOLIVRE,                                     "
                + "                     M.DATAJUROS,                                                  "
                + "                     M.CODTIPOJUROS,                                               "
                + "                     M.VALORPERCENTUALJUROS,                                       "
                + "                     M.DATAMULTA,                                                  "
                + "                     M.CODTIPOMULTA,                                               "
                + "                     M.VALORPERCENTUALMULTA,                                       "
                + "                     M.DATADESCONTO1,                                              "
                + "                     M.CODTIPODESCONTO1,                                           "
                + "                     M.VALORPERCENTUALDESCONTO1,                                   "
                + "                     M.DATADESCONTO2,                                              "
                + "                     M.CODTIPODESCONTO2,                                           "
                + "                     M.VALORPERCENTUALDESCONTO2,                                   "
                + "                     M.DATADESCONTO3,                                              "
                + "                     M.CODTIPODESCONTO3,                                           "
                + "                     M.VALORPERCENTUALDESCONTO3                                    "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "              JOIN DDA.MENSAGEMDDABOLETO M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA  " + "              WHERE TMP.NUMAGRUPAMENTO = ?"
                + "              AND TMP.BOLORIGEM = ?           ";
        debug("SQL -  Inserir na tabela Historico MensagemDDA Boleto : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDARetornoCIP(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTERROMENSAGEMRETORNOCIP (IDERROMENSAGEMRETORNOCIP,        "
                + "                                                          IDMENSAGEMDDA,              "
                + "                                                          CODTIPOERROCIP,             "
                + "                                                          DATAHORAATUALIZACAO,        "
                + "                                                          CODTIPOTRATAMENTOERROCIP,   "
                + "                                                          DATAPARTICAO)               "
                + "              SELECT M.IDERROMENSAGEMRETORNOCIP,                                      "
                + "                     M.IDMENSAGEMDDA,                                                 "
                + "                     M.CODTIPOERROCIP,                                                "
                + "                     M.DATAHORAATUALIZACAO,                                           "
                + "                     M.CODTIPOTRATAMENTOERROCIP,                                      "
                + "                     ?                                                   "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                           "
                + "              JOIN DDA.ERROMENSAGEMRETORNOCIP M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                            "
                + "              AND TMP.BOLORIGEM = ?                                                   ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Retorno CIP : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoLogDetalheMensagemDDA(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTLOGDETALHEENVIOARQUIVODDA (IDLOGDETALHEENVIOARQUIVODDA,    "
                + "                                                             IDLOGENVIOARQUIVODDA,       "
                + "                                                             IDMENSAGEMDDA,              "
                + "                                                             DATAPARTICAO)               "
                + "              SELECT M.IDLOGDETALHEENVIOARQUIVODDA,                                      "
                + "                     M.IDLOGENVIOARQUIVODDA,                                             "
                + "                     M.IDMENSAGEMDDA,                                                    "
                + "                     ?                                                      "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                              "
                + "              JOIN DDA.LOGDETALHEENVIOARQUIVODDA M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                               "
                + "              AND TMP.BOLORIGEM = ?                                                      ";

        debug("SQL -  Inserir na tabela Historico Log Detalhe Mensagem DDA : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDAAceite(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDAACEITE (IDMENSAGEMDDA,                     "
                + "                                                     BOLACEITE,                    "
                + "                                                     NUMREFATUALACEITE,            "
                + "                                                     NUMSEQATUALACEITE,            "
                + "                                                     NUMIDENTIFICADORBOLETOCIP,    "
                + "                                                     DATAPARTICAO)                 "
                + "              SELECT M.IDMENSAGEMDDA,                                              "
                + "                     M.BOLACEITE,                                                  "
                + "                     M.NUMREFATUALACEITE,                                          "
                + "                     M.NUMSEQATUALACEITE,                                          "
                + "                     M.NUMIDENTIFICADORBOLETOCIP,                                  "
                + "                     ?                                                "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "              JOIN DDA.MENSAGEMDDAACEITE M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA  "
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                         "
                + "              AND TMP.BOLORIGEM = ?                                                ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Aceite : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABaixaEfetiva(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABAIXAEFETIVA (IDMENSAGEMDDA,                             "
                + "                                                           NUMREFATUALBAIXAEFETIVA,    "
                + "                                                           NUMSEQATUALBAIXAEFETIVA,    "
                + "                                                           NUMIDENTIFICADORBAIXAOPER,  "
                + "                                                           NUMCODIGOBARRA,             "
                + "                                                           NUMREFATUALCADBOLETO,       "
                + "                                                           CODTIPOBAIXAEFETIVA,        "
                + "                                                           VALORBAIXA,                 "
                + "                                                           CODCANALPAGAMENTO,          "
                + "                                                           CODMEIOPAGAMENTO,           "
                + "                                                           DATAPARTICAO,               "
                + "                                                           NUMCODBARRASCAMPOLIVRE)     "
                + "              SELECT M.IDMENSAGEMDDA,                                                  "
                + "                     M.NUMREFATUALBAIXAEFETIVA,                                        "
                + "                     M.NUMSEQATUALBAIXAEFETIVA,                                        "
                + "                     M.NUMIDENTIFICADORBAIXAOPER,                                      "
                + "                     M.NUMCODIGOBARRA,                                                 "
                + "                     M.NUMREFATUALCADBOLETO,                                           "
                + "                     M.CODTIPOBAIXAEFETIVA,                                            "
                + "                     M.VALORBAIXA,                                                     "
                + "                     M.CODCANALPAGAMENTO,                                              "
                + "                     M.CODMEIOPAGAMENTO,                                               "
                + "                     ?,                                                     "
                + "                     M.NUMCODBARRASCAMPOLIVRE                                          "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                            "
                + "              JOIN DDA.MENSAGEMDDABAIXAEFETIVA M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                             "
                + "              AND TMP.BOLORIGEM = ?                                                    ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Baixa Efetiva : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABaixaOperacional(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABAIXAOPERACIONAL (IDMENSAGEMDDA,                   "
                + "                                                               NUMREFATUALBAIXAOPER,       "
                + "                                                               NUMSEQATUALBAIXAOPER,       "
                + "                                                               NUMCODIGOBARRA,             "
                + "                                                               NUMREFATUALCADBOLETO,       "
                + "                                                               CODTIPOBAIXAOPERACIONAL,    "
                + "                                                               NUMCPFCNPJPORTADOR,         "
                + "                                                               VALORBAIXA,                 "
                + "                                                               CODMEIOPAGAMENTO,           "
                + "                                                               CODCANALPAGAMENTO,          "
                + "                                                               CODTIPOPESSOAPORTADOR,      "
                + "                                                               BOLOPERACAOCONTINGENCIA,    "
                + "                                                               DATAPARTICAO,               "
                + "                                                               NUMCODBARRASCAMPOLIVRE)     "
                + "              SELECT M.IDMENSAGEMDDA,                                                      "
                + "                     M.NUMREFATUALBAIXAOPER,                                               "
                + "                     M.NUMSEQATUALBAIXAOPER,                                               "
                + "                     M.NUMCODIGOBARRA,                                                     "
                + "                     M.NUMREFATUALCADBOLETO,                                               "
                + "                     M.CODTIPOBAIXAOPERACIONAL,                                            "
                + "                     M.NUMCPFCNPJPORTADOR,                                                 "
                + "                     M.VALORBAIXA,                                                         "
                + "                     M.CODMEIOPAGAMENTO,                                                   "
                + "                     M.CODCANALPAGAMENTO,                                                  "
                + "                     M.CODTIPOPESSOAPORTADOR,                                              "
                + "                     M.BOLOPERACAOCONTINGENCIA,                                            "
                + "                     ?,                                                         "
                + "                     M.NUMCODBARRASCAMPOLIVRE                                              "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                                "
                + "              JOIN DDA.MENSAGEMDDABAIXAOPERACIONAL M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                                 "
                + "              AND TMP.BOLORIGEM = ?                                                        ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Baixa Operacional : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABeneficiario(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABENEFICIARIO (IDMENSAGEMDDA,                            "
                + "                                             NUMCPFCNPJBENEFICIARIO,                   "
                + "                                             CODTIPOPESSOABENEFICIARIO,                "
                + "                                             NOMEBENEFICIARIO,                         "
                + "                                             NOMEFANTASIABENEFICIARIO,                 "
                + "                                             CODSITUACAOBENEFICIARIO,                  "
                + "                                             DATAHORASITUACAO,                         "
                + "                                             CODSITUACAORELACIONAMENTOBENEFICIARIO,    "
                + "                                             DATAINICIORELACIONAMENTO,                 "
                + "                                             DATAFIMRELACIONAMENTO,                    "
                + "                                             NUMREFATUALCADBENEFICIARIO,               "
                + "                                             NUMSEQATUALCADBENEFICIARIO,               "
                + "                                             DATAPARTICAO)                             "
                + "              SELECT M.IDMENSAGEMDDA,                                                  "
                + "                     M.NUMCPFCNPJBENEFICIARIO,                                         "
                + "                     M.CODTIPOPESSOABENEFICIARIO,                                      "
                + "                     M.NOMEBENEFICIARIO,                                               "
                + "                     M.NOMEFANTASIABENEFICIARIO,                                       "
                + "                     M.CODSITUACAOBENEFICIARIO,                                        "
                + "                     M.DATAHORASITUACAO,                                               "
                + "                     M.CODSITUACAORELACIONAMENTOBENEFICIARIO,                          "
                + "                     M.DATAINICIORELACIONAMENTO,                                       "
                + "                     M.DATAFIMRELACIONAMENTO,                                          "
                + "                     M.NUMREFATUALCADBENEFICIARIO,                                     "
                + "                     M.NUMSEQATUALCADBENEFICIARIO,                                     "
                + "                     ?                                                    "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                            "
                + "              JOIN DDA.MENSAGEMDDABENEFICIARIO M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                             "
                + "              AND TMP.BOLORIGEM = ?                                                    ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Beneficiario : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABeneficiarioConvenio(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABENEFICIARIOCONVENIO (IDMENSAGEMDDABENEFICIARIOCONVENIO,        "
                + "                                                           IDMENSAGEMDDA,                      "
                + "                                                           NUMAGENCIA,                         "
                + "                                                           NUMCONTA,                           "
                + "                                                           NUMCLIENTEOUCONVENIO,               "
                + "                                                           CODTIPOPRODUTODDA,                  "
                + "                                                           CODSITUACAOCONVENIODDA,             "
                + "                                                           DATAINICIORELACIONAMENTO,           "
                + "                                                           DATAFIMRELACIONAMENTO,              "
                + "                                                           CODISPBPARTICIPANTEINCORPORADO,     "
                + "                                                           CODTIPOOPERACAO,                    "
                + "                                                           DATAPARTICAO)                       "
                + "              SELECT M.IDMENSAGEMDDABENEFICIARIOCONVENIO,                                      "
                + "                     M.IDMENSAGEMDDA,                                                          "
                + "                     M.NUMAGENCIA,                                                             "
                + "                     M.NUMCONTA,                                                               "
                + "                     M.NUMCLIENTEOUCONVENIO,                                                   "
                + "                     M.CODTIPOPRODUTODDA,                                                      "
                + "                     M.CODSITUACAOCONVENIODDA,                                                 "
                + "                     M.DATAINICIORELACIONAMENTO,                                               "
                + "                     M.DATAFIMRELACIONAMENTO,                                                  "
                + "                     M.CODISPBPARTICIPANTEINCORPORADO,                                         "
                + "                     M.CODTIPOOPERACAO,                                                        "
                + "                     ?                                                            "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                                    "
                + "              JOIN DDA.MENSAGEMDDABENEFICIARIOCONVENIO M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                                     "
                + "              AND TMP.BOLORIGEM = ?                                                            ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Beneficiario Convenio : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABeneficiarioRepresentante(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao)
            throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABENEFICIARIOREPRESENTANTE (IDMENSAGEMDDABENEFICIARIOREPRESENTANTE,   "
                + "                                                                        IDMENSAGEMDDA,              "
                + "                                                                        CODTIPOPESSOADDA,           "
                + "                                                                        NUMCPFCNPJREPRESENTANTE,    "
                + "                                                                        CODTIPOPESSOAREPRESENTANTE, "
                + "                                                                        DATAPARTICAO)               "
                + "              SELECT M.IDMENSAGEMDDABENEFICIARIOREPRESENTANTE,                                      "
                + "                     M.IDMENSAGEMDDA,                                                               "
                + "                     M.CODTIPOPESSOADDA,                                                            "
                + "                     M.NUMCPFCNPJREPRESENTANTE,                                                     "
                + "                     M.CODTIPOPESSOAREPRESENTANTE,                                                  "
                + "                     ?                                                                 "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                                         "
                + "              JOIN DDA.MENSAGEMDDABENEFICIARIOREPRESENTANTE M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                                          "
                + "              AND TMP.BOLORIGEM = ?                                                                 ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Beneficiario Representante : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABoletoGrupoCalculo(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABOLETOGRUPOCALCULO (IDMENSAGEMDDABOLETOGRUPOCALCULO,          "
                + "                                                                 IDMENSAGEMDDA,              "
                + "                                                                 VALORCALCULADOJUROS,        "
                + "                                                                 VALORCALCULADOMULTA,        "
                + "                                                                 VALORCALCULADODESCONTO,     "
                + "                                                                 VALORTOTALCOBRADO,          "
                + "                                                                 DATAVALIDADECALCULO,        "
                + "                                                                 DATAPARTICAO)               "
                + "              SELECT M.IDMENSAGEMDDABOLETOGRUPOCALCULO,                                      "
                + "                     M.IDMENSAGEMDDA,                                                        "
                + "                     M.VALORCALCULADOJUROS,                                                  "
                + "                     M.VALORCALCULADOMULTA,                                                  "
                + "                     M.VALORCALCULADODESCONTO,                                               "
                + "                     M.VALORTOTALCOBRADO,                                                    "
                + "                     M.DATAVALIDADECALCULO,                                                  "
                + "                     ?                                                          "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                                  "
                + "              JOIN DDA.MENSAGEMDDABOLETOGRUPOCALCULO M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                                   "
                + "              AND TMP.BOLORIGEM = ?                                                          ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Boleto Grupo Calculo : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABoletoNotaFiscal(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABOLETONOTAFISCAL (IDMENSAGEMDDABOLETONOTAFISCAL,            "
                + "                                                               IDMENSAGEMDDA,              "
                + "                                                               NUMNOTAFISCAL,              "
                + "                                                               DATAEMISSAONOTAFISCAL,      "
                + "                                                               VALORNOTAFISCAL,            "
                + "                                                               DATAPARTICAO)               "
                + "              SELECT M.IDMENSAGEMDDABOLETONOTAFISCAL,                                      "
                + "                     M.IDMENSAGEMDDA,                                                      "
                + "                     M.NUMNOTAFISCAL,                                                      "
                + "                     M.DATAEMISSAONOTAFISCAL,                                              "
                + "                     M.VALORNOTAFISCAL,                                                    "
                + "                    ?                                                         "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                                "
                + "              JOIN DDA.MENSAGEMDDABOLETONOTAFISCAL M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                                 "
                + "              AND TMP.BOLORIGEM = ?                                                        ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Boleto Nota Fiscal : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDABoletoTextoInfo(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDABOLETOTEXTOINFO (IDMENSAGEMDDABOLETOTEXTOINFO,    "
                + "                                                              IDMENSAGEMDDA,              "
                + "                                                              DESCTEXTOINFORMATIVO,       "
                + "                                                              DATAPARTICAO)               "
                + "              SELECT M.IDMENSAGEMDDABOLETOTEXTOINFO,                                      "
                + "                     M.IDMENSAGEMDDA,                                                     "
                + "                     M.DESCTEXTOINFORMATIVO,                                              "
                + "                     ?                                                       "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                               "
                + "              JOIN DDA.MENSAGEMDDABOLETOTEXTOINFO M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                                "
                + "              AND TMP.BOLORIGEM = ?                                                       ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Boleto Texto Info : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDAConsultaBoleto(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDACONSULTABOLETO (IDMENSAGEMDDA,                  "
                + "                                                             NUMCODIGOBARRA,             "
                + "                                                             NUMCPFCNPJPAGADOR,          "
                + "                                                             CODTIPOBOLETOCONSULTADO,    "
                + "                                                             DATAPARTICAO,               "
                + "                                                             NUMCODBARRASCAMPOLIVRE)     "
                + "              SELECT M.IDMENSAGEMDDA,                                                    "
                + "                     M.NUMCODIGOBARRA,                                                   "
                + "                     M.NUMCPFCNPJPAGADOR,                                                "
                + "                     M.CODTIPOBOLETOCONSULTADO,                                          "
                + "                     ?,                                                       "
                + "                     M.NUMCODBARRASCAMPOLIVRE                                              "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                              "
                + "              JOIN DDA.MENSAGEMDDACONSULTABOLETO M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                               "
                + "              AND TMP.BOLORIGEM = ?                                                      ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Boleto Consulta Boleto  : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDAPagadorConta(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDAPAGADORCONTA (IDMENSAGEMDDAPAGADORCONTA,                "
                + "                                                           IDMENSAGEMDDA,              "
                + "                                                           NUMAGENCIA,                 "
                + "                                                           NUMCONTACORRENTE,           "
                + "                                                           CODTIPOOPERACAO,            "
                + "                                                           DATAHORAADESAO,             "
                + "                                                           DATAPARTICAO)               "
                + "              SELECT M.IDMENSAGEMDDAPAGADORCONTA,                                      "
                + "                     M.IDMENSAGEMDDA,                                                  "
                + "                     M.NUMAGENCIA,                                                     "
                + "                     M.NUMCONTACORRENTE,                                               "
                + "                     M.CODTIPOOPERACAO,                                                "
                + "                     M.DATAHORAADESAO,                                                 "
                + "                     ?                                                    "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                            "
                + "              JOIN DDA.MENSAGEMDDAPAGADORCONTA M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                             "
                + "              AND TMP.BOLORIGEM = ?                                                    ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Pagador Conta  : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDAPagadorAgregado(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDAPAGADORAGREGADO (IDMENSAGEMDDAPAGADORAGREGADO,             "
                + "                                                              IDMENSAGEMDDA,              "
                + "                                                              NUMCPFCNPJAGREGADO,         "
                + "                                                              CODTIPOPESSOAAGREGADO,      "
                + "                                                              CODTIPOOPERACAO,            "
                + "                                                              DATAPARTICAO)               "
                + "              SELECT M.IDMENSAGEMDDAPAGADORAGREGADO,                                      "
                + "                     M.IDMENSAGEMDDA,                                                     "
                + "                     M.NUMCPFCNPJAGREGADO,                                                "
                + "                     M.CODTIPOPESSOAAGREGADO,                                             "
                + "                     M.CODTIPOOPERACAO,                                                   "
                + "                     ?                                                       "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                               "
                + "              JOIN DDA.MENSAGEMDDAPAGADORAGREGADO M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                                "
                + "              AND TMP.BOLORIGEM = ?                                                       ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Pagador Agregado  : " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDAPagador(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDAPAGADOR (IDMENSAGEMDDA,                              "
                + "                                                      NUMCPFCNPJPAGADOR,           "
                + "                                                      CODTIPOPESSOAPAGADOR,        "
                + "                                                      BOLPAGADORELETRONICO,        "
                + "                                                      NUMREFATUALCADPAGADOR,       "
                + "                                                      NUMSEQATUALCADPAGADOR,       "
                + "                                                      DATAPARTICAO)                "
                + "              SELECT M.IDMENSAGEMDDA,                                              "
                + "                     M.NUMCPFCNPJPAGADOR,                                          "
                + "                     M.CODTIPOPESSOAPAGADOR,                                       "
                + "                     M.BOLPAGADORELETRONICO,                                       "
                + "                     M.NUMREFATUALCADPAGADOR,                                      "
                + "                     M.NUMSEQATUALCADPAGADOR,                                      "
                + "                     ?                                                "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "              JOIN DDA.MENSAGEMDDAPAGADOR M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA "
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                         "
                + "              AND TMP.BOLORIGEM = ?                                                ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Pagador: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @param dataParticao
     * @throws ComumException, SQLException void
     * 
     */
    private void incluirHistoricoMensagemDDATerceiroAutorizado(Connection conn, int numAgrupamento, boolean bolOrigem, Date dataParticao) throws ComumException, SQLException {
        final String sql = "INSERT INTO DDA.HISTMENSAGEMDDATERCEIROAUT (IDMENSAGEMDDA,                   "
                + "                                                          CODTIPOPESSOAAUTORIZADOR,   "
                + "                                                          NUMCPFCNPJAUTORIZADOR,      "
                + "                                                          CODTIPOPESSOATERCEIRO,      "
                + "                                                          NUMCPFCNPJTERCEIRO,         "
                + "                                                          NUMIDENTIFICADORBOLETOCIP,  "
                + "                                                          NUMREFATUALTERCEIRO,        "
                + "                                                          DATAPARTICAO)               "
                + "              SELECT M.IDMENSAGEMDDA,                                                 "
                + "                     M.CODTIPOPESSOAAUTORIZADOR,                                      "
                + "                     M.NUMCPFCNPJAUTORIZADOR,                                         "
                + "                     M.CODTIPOPESSOATERCEIRO,                                         "
                + "                     M.NUMCPFCNPJTERCEIRO,                                            "
                + "                     M.NUMIDENTIFICADORBOLETOCIP,                                     "
                + "                     M.NUMREFATUALTERCEIRO,                                           "
                + "                     ?                                                   "
                + "              FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                           "
                + "              JOIN DDA.MENSAGEMDDATERCEIROAUT M ON M.IDMENSAGEMDDA = TMP.IDMENSAGEMDDA"
                + "              WHERE TMP.NUMAGRUPAMENTO = ?                                            "
                + "              AND TMP.BOLORIGEM = ?                                                   ";

        debug("SQL -  Inserir na tabela Historico MensagemDDA Terceiro Autorizado: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, dataParticao, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABaixaEfetiva(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABAIXAEFETIVA M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Baixa Efetiva: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABaixaOperacional(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABAIXAOPERACIONAL M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Baixa Operacional: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABeneficiarioRepresentante(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABENEFICIARIOREPRESENTANTE M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Beneficiario Representante: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABeneficiarioConvenio(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABENEFICIARIOCONVENIO M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Beneficiario Convenio: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABeneficiario(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABENEFICIARIO M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Beneficiario: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDATerceiroAutorizado(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDATERCEIROAUT M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Terceiro Autorizado: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDAAceite(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDAACEITE M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Aceite: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDAConsultaBoleto(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDACONSULTABOLETO M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Consulta Boleto: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirLogDetalheMensagemDDA(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.LOGDETALHEENVIOARQUIVODDA M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela Log Detalhe Mensagem DDA: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDARetornoCip(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.ERROMENSAGEMRETORNOCIP M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela Erro Mensagem Retorno CIP: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABoletoGrupoCalculo(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABOLETOGRUPOCALCULO M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Boleto Grupo Calculo: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABoletoNotaFiscal(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABOLETONOTAFISCAL M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Boleto Multa: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABoletoTextoInfo(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABOLETOTEXTOINFO M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Boleto Texto Info: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDABoleto(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDABOLETO M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Boleto: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDAPagadorConta(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDAPAGADORCONTA M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Pagador Conta: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDAPagadorAgregado(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDAPAGADORAGREGADO M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Pagador Agregado: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDAPagador(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDAPAGADOR M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA Pagador: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param numAgrupamento
     * @param bolOrigem
     * @throws ComumException, SQLException void
     * 
     */
    private void excluirMensagemDDA(Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        final String sql = "DELETE FROM DDA.MENSAGEMDDA M                                                         "
                + "              WHERE EXISTS (SELECT 1                                                             "
                + "                            FROM SESSION.TMP_MENSAGEMDDA_ARQUIVAMENTO TMP                        "
                + "                            WHERE TMP.IDMENSAGEMDDA  = M.IDMENSAGEMDDA                           "
                + "                            AND   TMP.NUMAGRUPAMENTO = ?                                         "
                + "                            AND TMP.BOLORIGEM = ?)                                               ";

        debug("SQL -  Exclusão na tabela MensagemDDA: " + sql.replaceAll("  ", "").trim());

        executarBatch(sql, conn, numAgrupamento, bolOrigem);
    }

    private void executarBatch(String sql, Connection conn, Date dataParticao, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(dataParticao.getTime()));
            ps.setInt(2, numAgrupamento);
            ps.setBoolean(3, bolOrigem);

            ps.execute();
        } finally {
            fecharStatement(ps);
        }
    }

    private void executarBatch(String sql, Connection conn, int numAgrupamento, boolean bolOrigem) throws ComumException, SQLException {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, numAgrupamento);
            ps.setBoolean(2, bolOrigem);

            ps.execute();
        } finally {
            fecharStatement(ps);
        }
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

}
