/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         MensagemBaixaOperacionalDaoImpl.java
 * Data Criação:    Jul 24, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao;

/**
 * MensagemBaixaOperacionalDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemBaixaOperacionalDaoImpl extends IntegracaoCipCrudDaoDB2<MensagemDDABaixaOperacional> implements MensagemBaixaOperacionalDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_mensagem.baixaoperacional.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-mensagem-baixaoperacional";

    private static final String LOG_BAIXA_OPERACIONAL_BOLETO_ADDA108RR2 = "Gravar Baixa Operacional Boleto ADDA108RR2";
    private static final String LOG_BAIXA_OPERACIONAL_BOLETO_ADDA108RET = "Gravar Baixa Operacional Boleto ADDA108RET";
    private static final String LOG_BAIXA_OPERACIONAL_BOLETO_ADDA114RET = "Gravar Baixa Operacional em contingencia Boleto ADDA114RET";

    /**
     * 
     */
    public MensagemBaixaOperacionalDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, MensagemDDABaixaOperacional.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao#gravarBaixaADDA108RR2(long, long, long)
     */
    public void gravarBaixaADDA108RR2(long idLogRecebArq, long idLogDetArqInicial, long idLogDetFinal) {
        executarScriptEmXML("GRAVAR_BOLETODDA_BAIXA_OPERACIONAL_XML_ADDA108RR2", idLogRecebArq, idLogDetArqInicial, idLogDetFinal, LOG_BAIXA_OPERACIONAL_BOLETO_ADDA108RR2);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao#gravarBaixaADDA108RET(long, long, long)
     */
    public void gravarBaixaADDA108RET(long idLogRecebArq, long idLogDetArqInicial, long idLogDetFinal) {
        executarScriptEmXML("PROCESSAR_INCLUSAO_BAIXAOPERACIONAL_ADDA108RET", idLogRecebArq, idLogDetArqInicial, idLogDetFinal, LOG_BAIXA_OPERACIONAL_BOLETO_ADDA108RET);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao#obterMensagemDDABaixaOperacionalAtualizaReferencias(java.lang.Long)
     */
    public MensagemDDABaixaOperacional obterMensagemDDABaixaOperacionalAtualizaReferencias(Long idMensagem) throws ComumException {
        getLogger().debug("Inicio Classe: BoletoCipDaoImpl Metodo: obterMensagemDDABaixaOperacionalAtualizaReferencias IdMensagemDDA = " + idMensagem);
        MensagemDDABaixaOperacional mensagem = obterMensagemDDABaixaOperacional(idMensagem);
        getLogger().debug("Busca feita com Sucesso - obterMensagemDDABaixaOperacionalAtualizaReferencias");
        if (!ObjectUtil.isNull(mensagem) && !ObjectUtil.isEmpty(mensagem.getNumRefAtualBaixaOper()) && !ObjectUtil.isEmpty(mensagem.getNumSeqAtualBaixaOper())) {
            atualizaMensagemDDABaixaOperacional(mensagem.getId(), mensagem.getNumRefAtualBaixaOper(), mensagem.getNumSeqAtualBaixaOper());
        }
        getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: obterMensagemDDABaixaOperacionalAtualizaReferencias");
        return mensagem;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao#listarMensagemDDABaixaOperacional(java.lang.Long)
     */
    public List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacional(Long idLogEnvioArquivoDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacional = new ArrayList<MensagemDDABaixaOperacional>();

        try {
            comando = getComando("LISTAR_MENSAGEMDDA_BAIXA_OPERACIONAL");
            comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
            comando.configurar();
            getLogger().debug("idLogEnvioArquivoDDA = " + idLogEnvioArquivoDDA);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listarMensagemDDABaixaOperacional.add(setMensagemDDABaixaOperacional(rs));

            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listarMensagemDDABaixaOperacional;
    }
    
    /**
     * {@inheritDoc}
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao#listarMensagemDDABaixaOperacionalContingencia(java.lang.Long)
     */
    @Override
    public List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacionalContingencia(Long idLogEnvioArquivoDDA) {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemDDABaixaOperacional> listarMensagemDDABaixaOperacional = new ArrayList<MensagemDDABaixaOperacional>();

        try {
            comando = getComando("LISTAR_MENSAGEMDDA_BAIXA_OPERACIONAL_CONTINGENCIA");
            comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
            comando.configurar();
            getLogger().debug("idLogEnvioArquivoDDA = " + idLogEnvioArquivoDDA);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listarMensagemDDABaixaOperacional.add(setMensagemDDABaixaOperacionalContingencia(rs));

            }

        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listarMensagemDDABaixaOperacional;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagem
     * @return MensagemDDABaixaOperacional
     * 
     */
    private MensagemDDABaixaOperacional obterMensagemDDABaixaOperacional(Long idMensagem) {
        getLogger().debug("*******INICIO obterMensagemDDABaixaOperacional()*******");
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        MensagemDDABaixaOperacional mensagemDDABaixaOperacional = null;

        try {
            comando = getComando("OBTER_MENSAGEMDDA_BAIXA_OPERACIONAL_REFERENCIAS");
            comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
            comando.configurar();
            getLogger().debug("idMensagem = " + idMensagem);
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                mensagemDDABaixaOperacional = setMensagemDDABaixaOperacional(rs);
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return mensagemDDABaixaOperacional;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagemDDABaixaOperacional
     * @param numRefAtualBaixaOper
     * @param numSeqAtualBaixaOper
     * @throws ComumException void
     * 
     */
    private void atualizaMensagemDDABaixaOperacional(Long idMensagemDDABaixaOperacional, Long numRefAtualBaixaOper, Long numSeqAtualBaixaOper) throws ComumException {
        getLogger().debug(
                "Inicio Classe: MensagemBaixaOperacionalDaoImpl Metodo: atualizaMensagemDDABaixaOperacional Parametros - idMensagemDDABaixaOperacional = "
                        + idMensagemDDABaixaOperacional);
        Map<String, Object> parametros = getMapaParametrosAtualizarMensagem(idMensagemDDABaixaOperacional, numRefAtualBaixaOper, numSeqAtualBaixaOper);
        executarUpdate("ATUALIZAR_MENSAGEMDDA_BAIXA_OPERACIONAL", parametros);
        getLogger().debug("FIM Classe: MensagemBaixaOperacionalDaoImpl Metodo: atualizaMensagemDDABaixaOperacional");

    }

    /**
     * Método responsável por
     * 
     * @param idMensagemDDABaixaOperacional
     * @param numRefAtualBaixaOper
     * @param numSeqAtualBaixaOper
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getMapaParametrosAtualizarMensagem(Long idMensagemDDABaixaOperacional, Long numRefAtualBaixaOper, Long numSeqAtualBaixaOper) {
        Map<String, Object> parametros = getMapaParametro(idMensagemDDABaixaOperacional, "idMensagemDDABaixaOperacional");
        parametros.put("numRefAtualBaixaOper", numRefAtualBaixaOper);
        parametros.put("numSeqAtualBaixaOper", numSeqAtualBaixaOper);
        return parametros;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDABaixaOperacional
     * 
     */
    private MensagemDDABaixaOperacional setMensagemDDABaixaOperacional(ResultSet rs) throws SQLException {
        MensagemDDABaixaOperacional mensagemDDABaixaOperacional = new MensagemDDABaixaOperacional();
        mensagemDDABaixaOperacional.setId(rs.getLong("idMensagemDDA"));

        mensagemDDABaixaOperacional.setNumCodigoBarra(rs.getString("numCodigoBarra"));
        mensagemDDABaixaOperacional
                .setNumCodBarrasCampoLivre(rs.getString("numCodigoBarra") != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(rs.getString("numCodigoBarra")) : null);
        mensagemDDABaixaOperacional.setNumRefAtualCadBoleto(rs.getLong("numRefAtualCadBoleto"));
        mensagemDDABaixaOperacional.setCodTipoBaixaOperacional(rs.getShort("codTipoBaixaOperacional"));
        mensagemDDABaixaOperacional.setCodTipoPessoaPortador(rs.getString("codTipoPessoaPortador"));
        mensagemDDABaixaOperacional.setNumCpfCnpjPortador(rs.getString("numCpfCnpjPortador"));
        mensagemDDABaixaOperacional.setValorBaixa(rs.getBigDecimal("valorBaixa"));
        mensagemDDABaixaOperacional.setCodCanalPagamento(rs.getShort("codCanalPagamento"));
        mensagemDDABaixaOperacional.setCodMeioPagamento(rs.getShort("codMeioPagamento"));
        mensagemDDABaixaOperacional.setBolOperacaoContingencia(rs.getBoolean("bolOperacaoContingencia"));

        mensagemDDABaixaOperacional.setNumRefAtualBaixaOper(getNullableLong("numRefAtualBaixaOper", rs));
        mensagemDDABaixaOperacional.setNumSeqAtualBaixaOper(getNullableLong("numSeqAtualBaixaOper", rs));

        mensagemDDABaixaOperacional.setNumIdentificadorBoleto(getNullableLong("numIdentificadorBoletoCip", rs));

        mensagemDDABaixaOperacional.setNumIdentificadorBaixaOper(getNullableLong("numIdentificadorBaixaOper", rs));

        mensagemDDABaixaOperacional.setMensagemDDA(obterMensagemDDA(mensagemDDABaixaOperacional.getId()));

        return mensagemDDABaixaOperacional;
    }

    private MensagemDDABaixaOperacional setMensagemDDABaixaOperacionalContingencia(ResultSet rs) throws SQLException {
        MensagemDDABaixaOperacional mensagemDDABaixaOperacional = new MensagemDDABaixaOperacional();
        mensagemDDABaixaOperacional.setId(rs.getLong("idMensagemDDA"));

        mensagemDDABaixaOperacional.setNumCodigoBarra(rs.getString("numCodigoBarra"));
        mensagemDDABaixaOperacional
                .setNumCodBarrasCampoLivre(rs.getString("numCodigoBarra") != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(rs.getString("numCodigoBarra")) : null);
        mensagemDDABaixaOperacional.setNumRefAtualCadBoleto(rs.getLong("numRefAtualCadBoleto"));
        mensagemDDABaixaOperacional.setCodTipoBaixaOperacional(rs.getShort("codTipoBaixaOperacional"));
        mensagemDDABaixaOperacional.setCodTipoPessoaPortador(rs.getString("codTipoPessoaPortador"));
        mensagemDDABaixaOperacional.setNumCpfCnpjPortador(rs.getString("numCpfCnpjPortador"));
        mensagemDDABaixaOperacional.setValorBaixa(rs.getBigDecimal("valorBaixa"));
        mensagemDDABaixaOperacional.setCodCanalPagamento(rs.getShort("codCanalPagamento"));
        mensagemDDABaixaOperacional.setCodMeioPagamento(rs.getShort("codMeioPagamento"));
        mensagemDDABaixaOperacional.setBolOperacaoContingencia(rs.getBoolean("bolOperacaoContingencia"));

        mensagemDDABaixaOperacional.setMensagemDDA(obterMensagemDDA(mensagemDDABaixaOperacional.getId()));

        return mensagemDDABaixaOperacional;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaOperacionalDao#gravarBaixaADDA114RET(long, long, long)
     */
    @Override
    public void gravarBaixaADDA114RET(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        executarScriptEmXML("PROCESSAR_INCLUSAO_BAIXAOPERACIONAL_ADDA114RET", idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal,
                LOG_BAIXA_OPERACIONAL_BOLETO_ADDA114RET);
    }
}
