/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         ArquivoAlteraBoletoDaoImpl.java
 * Data Criação:    Jul 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.sql.Connection;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoAlteraBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;

/**
 * ArquivoAlteraBoletoDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ArquivoAlteraBoletoDaoImpl extends IntegracaoCipCrudDaoDB2<SicoobDDAEntidade> implements ArquivoAlteraBoletoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_arquivo.alterarboleto.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-arquivo-alterarboleto";

    private static final String LOG_ALTERAR_BOLETO_ADDA102RR2 = "Alterar Boleto ADDA102RR2";

    private static final String LOG_ALTERAR_BOLETO_ADDA102RET = "Alterar Boleto ADDA102RET";

    /**
     * 
     */
    public ArquivoAlteraBoletoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoAlteraBoletoDao#alterarADDA102RR2(long, long, long)
     */
    public void alterarADDA102RR2(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {

        Connection conn = null;
        try {
            conn = estabelecerConexao();
            executarScriptEmXML("PROCESSAR_ATUALIZACAO_BOLETO_ADDA102RR2", idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal, LOG_ALTERAR_BOLETO_ADDA102RR2);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_CAMPOS_BOLETO_ADDA102RR2", idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal, "GRAVANDO CAMPOS");
            executarAtualizacaoArquivoDDA(conn, "ALTERAR_BOLPROCESSADO_ADDA102RR2", idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal, "GRAVANDO CAMPOS");

        } finally {
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoAlteraBoletoDao#alterarADDA102RET(long, long, long)
     */
    public void alterarADDA102RET(long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal) {
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            executarScriptEmXML("PROCESSAR_ATUALIZACAO_BOLETO_ADDA102RET", idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal, LOG_ALTERAR_BOLETO_ADDA102RET);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_CAMPOS_BOLETO_ADDA102RET", idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal, "GRAVANDO CAMPOS");
            executarAtualizacaoArquivoDDA(conn, "ALTERAR_BOLPROCESSADO_ADDA102RET", idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal, "GRAVANDO CAMPOS");
        } finally {
            fecharConexao(conn);
        }

    }

}
