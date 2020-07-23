/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         IntegracaoCipLegadoDaoImpl.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.PersistenceException;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoRetDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoLegadoDao;

/**
 * RateioDDALancamentoLegadoDaoImpl é responsável por
 * 
 * @author George.Santos
 */
public class RateioDDALancamentoLegadoDaoImpl extends OperacionalCrudDao<SicoobDDAEntidade> implements RateioDDALancamentoLegadoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.rateioddalancamento.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-rateioddalancamento";
    public static final String COMANDO_PESQUISAR = "";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public RateioDDALancamentoLegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, COMANDO_PESQUISAR);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao#gravarLancamentoIntegracaoSP(br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto)
     */
    public LancamentoIntegracaoRetDto gravarLancamentoIntegracaoSP(Integer numCooperativa, LancamentoIntegracaoDto lancamentoIntegracaoDto) {
        debug("iniciando a gravação do lancamento Integração pela SP_GravarLancamentoIntegracao");
        Connection conn = null;
        ResultSet rs = null;

        CallableStatement cs = null;

        LancamentoIntegracaoRetDto lancamentoIntegracaoRetDto = new LancamentoIntegracaoRetDto();
        final String sql = "SPU_CHAMADA_CCO_LANCAMENTO ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ? ";

        debugParametros(lancamentoIntegracaoDto);

        try {
            conn = estabelecerConexao(numCooperativa);

            cs = conn.prepareCall("exec " + sql);
            cs.registerOutParameter(1, java.sql.Types.DATE);
            cs.setDate(1, new Date(lancamentoIntegracaoDto.getDataLote().getTime()));
            cs.setInt(2, lancamentoIntegracaoDto.getNumLoteLanc());
            cs.setString(3, lancamentoIntegracaoDto.getDescNumDocumento());
            cs.setLong(4, lancamentoIntegracaoDto.getNumContaCorrente());
            cs.setBigDecimal(5, lancamentoIntegracaoDto.getValorLanc());
            cs.setInt(6, lancamentoIntegracaoDto.getIdProduto());
            cs.setInt(7, lancamentoIntegracaoDto.getIdTipoHistoricoLanc());
            if (!ObjectUtil.isNull(lancamentoIntegracaoDto.getIdProdutoEstorno())) {
                cs.setInt(8, lancamentoIntegracaoDto.getIdProdutoEstorno());
            } else {
                cs.setNull(8, java.sql.Types.NULL);
            }

            if (!ObjectUtil.isNull(lancamentoIntegracaoDto.getIdTipoHistoricoEstorno())) {
                cs.setInt(9, lancamentoIntegracaoDto.getIdTipoHistoricoEstorno());
            } else {
                cs.setNull(9, java.sql.Types.NULL);
            }

            cs.setString(10, lancamentoIntegracaoDto.getIdUsuarioResp());
            cs.setBoolean(11, lancamentoIntegracaoDto.getBolVerificaSaldo());

            if (!ObjectUtil.isNull(lancamentoIntegracaoDto.getCodOrigemLote())) {
                cs.setInt(12, lancamentoIntegracaoDto.getCodOrigemLote());
            } else {
                cs.setInt(12, 0);
            }

            cs.setInt(13, lancamentoIntegracaoDto.getNumSeqLanc());

            cs.setBoolean(14, lancamentoIntegracaoDto.getBolVerificaContaAnt());
            cs.setBoolean(15, lancamentoIntegracaoDto.getBolConsideraLimite());

            // CodRetorno
            cs.registerOutParameter(16, java.sql.Types.TINYINT);
            // Mensagem
            cs.registerOutParameter(17, java.sql.Types.VARCHAR);
            // CampoErro
            cs.registerOutParameter(18, java.sql.Types.VARCHAR);
            // CodErroRetorno
            cs.registerOutParameter(19, java.sql.Types.VARCHAR);
            // NumSeqLancOUt
            cs.registerOutParameter(20, java.sql.Types.INTEGER);

            cs.setString(21, lancamentoIntegracaoDto.getDescInfComplementar());

            // @BolConsSaldoResgAuto BIT = 0
            cs.setNull(22, java.sql.Types.INTEGER);
            // @IdTipoHistIOF smallint = 0
            cs.setNull(23, java.sql.Types.INTEGER);
            // @ValorIOFTrans money = 0.0
            cs.setNull(24, java.sql.Types.DOUBLE);
            // @NumSeqLancIOFOUT
            cs.registerOutParameter(25, java.sql.Types.INTEGER);

            debug("Iniciando a execução da SP de lancamento do Conta Corrente - SP_GravarLancamentoIntegracao ");

            cs.execute();

            debug("SP lancamento do Conta Corrente - SP_GravarLancamentoIntegracao Finalizado com Sucesso");
            lancamentoIntegracaoRetDto.setCodRetorno(cs.getInt(16));
            lancamentoIntegracaoRetDto.setMensagem(cs.getString(17));
            lancamentoIntegracaoRetDto.setCampoErro(cs.getString(18));
            lancamentoIntegracaoRetDto.setCodErroRetorno(cs.getString(19));
            lancamentoIntegracaoRetDto.setNumSeqLanc(cs.getInt(20));

        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharStatement(cs);
            fecharConexao(conn);
        }
        return lancamentoIntegracaoRetDto;
    }

    /**
     * Método responsável por
     * 
     * @param lancamentoIntegracaoDto void
     */
    private void debugParametros(LancamentoIntegracaoDto lancamentoIntegracaoDto) {
        debug("Parametros que serao utilizadas na SP: ");

        debug("Data Lote = " + lancamentoIntegracaoDto.getDataLote().getTime());
        debug("Num Lote Lancamento = " + lancamentoIntegracaoDto.getNumLoteLanc());
        debug("Desc Num Documento = " + lancamentoIntegracaoDto.getDescNumDocumento());
        debug("Num Conta Corrente = " + lancamentoIntegracaoDto.getNumContaCorrente());
        debug("Valor lancamento =" + lancamentoIntegracaoDto.getValorLanc());
        debug("id Produto" + lancamentoIntegracaoDto.getIdProduto());
        debug("id Tipo Historico lancamento = " + lancamentoIntegracaoDto.getIdTipoHistoricoLanc());
        debug("Id produto estorno = " + lancamentoIntegracaoDto.getIdProdutoEstorno());
        debug("id Tipo Historico Estorno = " + lancamentoIntegracaoDto.getIdTipoHistoricoEstorno());
        debug("Id Usuario Responsavel =" + lancamentoIntegracaoDto.getIdUsuarioResp());
        debug("Bol Verifica Saldo = " + lancamentoIntegracaoDto.getBolVerificaSaldo());
        debug("Cod Origem Lote = " + lancamentoIntegracaoDto.getCodOrigemLote());
        debug("Num Seq lancamento = " + lancamentoIntegracaoDto.getNumSeqLanc());

        debug("Bol Verifica Conta Anterior = " + lancamentoIntegracaoDto.getBolVerificaContaAnt());
        debug("Bol Considera Limite = " + lancamentoIntegracaoDto.getBolConsideraLimite());

        debug("Desc informaçao Complementar = " + lancamentoIntegracaoDto.getDescInfComplementar());
    }
}
