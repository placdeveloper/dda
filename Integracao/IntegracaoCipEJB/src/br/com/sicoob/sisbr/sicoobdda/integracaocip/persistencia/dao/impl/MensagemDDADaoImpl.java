/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         MensagemDDADaoImpl.java
 * Data Criação:    Setembro 27, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroSWSDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBoletoConsultado;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoDDA;

/**
 * MensagemDDADaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDADaoImpl extends IntegracaoCipCrudDaoDB2<MensagemDDA> implements MensagemDDADao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_integracaocip.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "sicoobdda_integracaocip.mensagemdda.queries";

    /**
     * IntegracaoCipDaoImpl
     */
    public MensagemDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, MensagemDDA.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#obterMensagemPorNumOperacao(java.lang.String)
     */
    public MensagemDDA obterMensagemPorNumOperacao(String numOperacao) throws ComumException {
        MensagemDDA mensagem = null;
        Comando comando = null;
        try {
            comando = getComando("OBTER_MENSAGEMDDA_POR_NUMOPERACAO");
            comando.adicionarVariavel("numOperacao", numOperacao);
            comando.configurar();

            Query query = comando.criarQuery(getEntityManager());
            query.setMaxResults(1);
            mensagem = (MensagemDDA) query.getSingleResult();
        } finally {
            fecharComando(comando);
        }
        return mensagem;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#listarIdsMensagensEnviadas(long)
     */
    public List<Long> listarIdsMensagensEnviadas(long idArquivoEnvio) throws ComumException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<Long> mensagensDDaIDs = new ArrayList<Long>();
        try {
            comando = getComando("RECUPERA_MENSAGENS_DE_ENVIO");
            comando.adicionarVariavel("idArquivo", idArquivoEnvio);
            comando.configurar();

            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                mensagensDDaIDs.add(rs.getLong("IDMENSAGEMDDA"));
            }
        } catch (SQLException e) {
            throw new ComumException("integracao.erro.recuperar.ids.mensagem.envio", new Object[] { idArquivoEnvio, e.getMessage() }, e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mensagensDDaIDs;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#obterMensagemErroLock(java.lang.Long)
     */
    public MensagemDDA obterMensagemErroLock(Long idMensagem) throws ComumException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        try {
            comando = getComando("OBTER_MENSAGEM_ERRO_LOCK_REGISTRO");
            comando.adicionarVariavel("idMensagem", idMensagem);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                return new MensagemDDA(rs.getLong("IDMENSAGEMDDA"), rs.getString("XMLMENSAGEM"));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#excluirGradeHoraria(java.util.Date, java.lang.String)
     */
    public void excluirGradeHoraria(Date dataReferencia, String codTipoGradeHoraria) throws ComumException {
        Map<String, Object> parametros = getMapaParametroCodTipoGrade(dataReferencia, codTipoGradeHoraria);
        executarUpdate("EXCLUIR_GRADE_HORARIA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#atualizarGradeHoraria(java.util.Date, java.lang.String, java.util.Date,
     *      java.util.Date)
     */
    public void atualizarGradeHoraria(Date dataReferencia, String codTipoGradeHoraria, Date dataAbertura, Date dataFechamento) throws ComumException {
        Map<String, Object> parametros = getMapaParametroCodTipoGrade(dataReferencia, codTipoGradeHoraria);
        parametros.put("dataAbertura", dataAbertura);
        parametros.put("dataFechamento", dataFechamento);
        executarUpdate("ATUALIZAR_GRADE_HORARIA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#possuiMensagemDDAParaEnvio(java.lang.Long)
     */
    public boolean possuiMensagemDDAParaEnvio(Long idMensagemDDA) throws ComumException {
        return existeRegistroNativo("POSSUI_MENSAGEM_DDA_PARA_ENVIO", getMapaParametro(idMensagemDDA, "idMensagemDDA"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#obterQuantidadeMensagemPendente(java.lang.String)
     */
    public long obterQuantidadeMensagemPendente(String prCodicoDaMensagem) throws ComumException {
        return obterCount("OBTER_QUANTIDADE_MENSAGENS_PENDENTES", getMapaParametro(prCodicoDaMensagem, "codTipoMensagem"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#obterMensagemDeTesteDeConectividadeAGEN001(java.lang.Long)
     */
    public MensagemDDA obterMensagemDeTesteDeConectividadeAGEN001(Long idLogEnvioArquivoDDA) throws ComumException {

        Connection conn = null;
        Comando comando = null;
        ResultSet rs = null;

        MensagemDDA msgdda = null;

        try {
            comando = getComando("LISTAR_MESAGEM_TESTE_CONECTIVIDADE");
            comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
            comando.configurar();

            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                msgdda = new MensagemDDA(rs.getLong(1), rs.getString(10));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return msgdda;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#listarTipoSituacaoBoleto()
     */
    public List<SituacaoBoleto> listarTipoSituacaoBoleto() throws ComumException {
        return listar("LISTAR_TIPO_SIT_BOLETO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#listarTipoBoletoConsultado()
     */
    public List<TipoBoletoConsultado> listarTipoBoletoConsultado() throws ComumException {
        return listar("LISTAR_TIPO_BOLETO_CONSULTADO");
    }

    /**
     * Método responsável por
     * 
     * @param dataReferencia
     * @param codTipoGradeHoraria
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getMapaParametroCodTipoGrade(Date dataReferencia, String codTipoGradeHoraria) {
        Map<String, Object> parametros = getMapaParametro(codTipoGradeHoraria, "codTipoGradeHoraria");
        parametros.put("dataReferencia", dataReferencia);
        return parametros;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#obterDataReferencia(java.lang.String)
     */
    public Date obterDataReferencia(String codTipoMensagemDDA) throws ComumException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        Date dataReferencia = null;
        try {
            comando = getComando("OBTER_DATA_REFERENCIA");
            comando.adicionarVariavel("codTipoMensagemDDA", codTipoMensagemDDA);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                dataReferencia = rs.getDate("DATAREFERENCIA");
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return dataReferencia;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#incluirListaMsgPagadorBatch(java.util.List, java.lang.Integer,
     *      java.lang.String, br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroSWSDto)
     */
    public void incluirListaMsgPagadorBatch(List<MensagemDDAPagador> listaMsgPagador, Integer idInstituicao, String nomeUsuarioPagador, LogErroSWSDto log) throws ComumException {
        debug("SWS carga de mensagem - inicio da Inclusão Pagador em Batch");
        debug("SWS carga de mensagem - quantidade de Mensagens Pagador - " + listaMsgPagador.size());
        debug("SWS carga de mensagem - idInstituicao - " + idInstituicao);

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            debug("Estabelecendo a conexão...");
            conn = estabelecerConexao();

            final String sql = "UPDATE dda.EventoConsolidadoDDA SET idRateioDDA = ?, dataHoraInclusaoRateio = ?, idInstituicaoUsuarioInclusaoRateio = ?, "
                    + "idUsuarioInclusaoRateio = ?, valorTarifaCIP = ?, valorTarifaBancoob = ? WHERE idEventoConsolidadoDDA = ?";
            debug("SQL da Mensagem DDA: " + sql);

            ps = conn.prepareStatement(sql);

            for (MensagemDDAPagador mensagemDDAPagador : listaMsgPagador) {
                // ps.setLong(1, idRateio);
                // ps.setDate(2, dataHoraInclusao);
                // ps.setInt(3, idInstituicaoUsuarioInclusaoRateio);
                // ps.setString(4, idUsuarioInclusaoRateio);
                // s.setBigDecimal(5, dto.getValorUnitarioCIP());
                // ps.setBigDecimal(6, dto.getValorUnitarioBancoob());

                // ps.setLong(7, dto.getIdEventoConsolidado());

                ps.addBatch();
            }

            // TODO george.santos - No antigo servico quando era incluido um por vez e desse erro, dava rollback e depois
            // atualiza o tituloDDA a data de movimento... verificar se faremos isso tambem.

            // TODO george.santos - Pagador lembrar de colocar o Historico termo pagador
            /*
             * String numCpfCnpjAgregado = null; if (!ObjectUtil.isEmpty(msg.getListaMensagemDDAPagadorAgregado())) { numCpfCnpjAgregado =
             * msg.getListaMensagemDDAPagadorAgregado().get(0).getNumCpfCnpjAgregado(); } HistoricoTermoPagador histTermoPagador = new HistoricoTermoPagador(new
             * DateTimeDB(), CanalEnum.RETAGUARDA.getId(), numCpfCnpjAgregado, msg.getNumCpfCnpjPagador()); histTermoPagador.setTipoTermoPagador(new
             * TipoTermoPagador(msg.getCodTipoTermoPagador())); getEm().persist(histTermoPagador);
             */

            debug("Atualizando os eventos consolidados...");
            ps.executeBatch();
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharStatement(ps);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#incluirListaMsgBoletoBatch(java.util.List, java.lang.Integer,
     *      java.lang.String, br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroSWSDto)
     */
    public void incluirListaMsgBoletoBatch(List<MensagemDDABoleto> listaMensagemDDABoleto, Integer idInstituicao, String nomeUsuarioBoleto, LogErroSWSDto log) {
        debug("SWS carga de mensagem - inicio da Inclusão/Alteração boleto em Batch");
        debug("SWS carga de mensagem - quantidade de Mensagens Boleto - " + listaMensagemDDABoleto.size());
        debug("SWS carga de mensagem - idInstituicao - " + idInstituicao);
        
        for (MensagemDDABoleto mensagemDDABoleto : listaMensagemDDABoleto) {
            if (mensagemDDABoleto.getMensagemDDA().getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.ADDA101)
                    || mensagemDDABoleto.getMensagemDDA().getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0101)) {
                BoletoDDA boletoDDA = ConversorBoletoDDA.converterBoletoDDA(mensagemDDABoleto);
                boletoDDA.setDataHoraSituacaoBoleto(new DateTimeDB());
                boletoDDA.setCodPartDestinatario(Constantes.BANCOOB);
                // incluir a BoletoDDa
            }
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#incluirListaMsgBaixaEfetivaBatch(java.util.List, java.lang.Integer,
     *      java.lang.String, br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroSWSDto)
     */
    public void incluirListaMsgBaixaEfetivaBatch(List<MensagemDDABaixaEfetiva> listaMsgBaixas, Integer idInstituicao, String nomeUsuarioBaixaEfetiva, LogErroSWSDto log) {
        debug("SWS carga de mensagem - inicio da Inclusão Baixa Efeitva em Batch");
        debug("SWS carga de mensagem - quantidade de Mensagens Baixa Efetiva - " + listaMsgBaixas.size());
        debug("SWS carga de mensagem - idInstituicao - " + idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao#obterBoletoBaixaOperacional(java.math.BigDecimal, java.lang.String)
     */
    public BoletoDDABaixaOper obterBoletoBaixaOperacional(BigDecimal valorBaixa, String numCodigoBarra) throws ComumException {
        // TODO Auto-generated method stub
        return null;
    }
}
