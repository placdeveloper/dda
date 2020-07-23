/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         ArquivoAlteraBoletoDaoImpl.java
 * Data Criação:    Jul 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.sql.Connection;

import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;

/**
 * ArquivoIncluirBoletoDaoImpl
 * 
 * @author Danilo.Barros
 */
public class ArquivoIncluirBoletoDaoImpl extends IntegracaoCipCrudDaoDB2<SicoobDDAEntidade> implements ArquivoIncluirBoletoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_arquivo.incluirboleto.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-arquivo-incluirboleto";

    private static final String LOG_INCLUIR_BOLETO_ADDA101RET = "Incluir Boleto ADDA101RET";
    private static final String LOG_INCLUIR_BOLETO_ADDA101RR2 = "Incluir Boleto ADDA101RR2";
    private static final String LOG_INCLUIR_BOLETO_ADDA106 = "Incluir Boleto ADDA106";
    private static final String LOG_INCLUIR_BOLETO_ADDA127 = "Incluir Boleto Pagamento Aberto ADDA127";

    /**
     * 
     */
    public ArquivoIncluirBoletoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao#incluirADDA101RET(long, long, long)
     */
    public void incluirADDA101RET(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) {
        executarScriptEmXML("PROCESSAR_INCLUSAO_BOLETO_ADDA101RET", idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_BOLETO_ADDA101RET);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao#incluirADDA101RR2(long, long, long)
     */
    public void incluirADDA101RR2(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
        executarScriptEmXML("PROCESSAR_INCLUSAO_BOLETO_ADDA101RR2", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_BOLETO_ADDA101RR2);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao#inluirADDA106(long, long, long)
     */
    public void inluirADDA106(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_GRUPO_CALCULO_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_BAIXA_OPERACIONAL_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_BAIXA_EFETIVA_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_BOLETO_ADDA106);
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETODDA_TERCEIRO_AUTORIZADO_XML_ADDA106", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_BOLETO_ADDA106);
        } finally {
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoIncluirBoletoDao#incluirADDA127(long, long, long)
     */
    public void incluirADDA127(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
        executarScriptEmXML("PROCESSAR_INCLUSAO_BOLETO_ADDA127", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_BOLETO_ADDA127);
    }

}
