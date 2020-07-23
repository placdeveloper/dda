/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         ArquivoAlteraBoletoDaoImpl.java
 * Data Criação:    Jul 18, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.sql.Connection;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoDecursoPrazoBaixaOperacionalDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;

/**
 * ArquivoDecursoPrazoBaixaOperacionalDaoImpl
 * 
 * @author Danilo.Barros
 */
public class ArquivoDecursoPrazoBaixaOperacionalDaoImpl extends IntegracaoCipCrudDaoDB2<SicoobDDAEntidade> implements ArquivoDecursoPrazoBaixaOperacionalDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_arquivo.decursoprazo.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-arquivo-decursoprazo";

    private static final String LOG_INCLUIR_DECURSO_ADDA117 = "Incluir Decurso Prazo Baixa Operacional ADDA117";

    /**
     * 
     */
    public ArquivoDecursoPrazoBaixaOperacionalDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoDecursoPrazoBaixaOperacionalDao#incluirADDA117(long, long, long)
     */
    public void incluirADDA117(long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            executarAtualizacaoArquivoDDA(conn, "GRAVAR_BOLETO_DECURSO_PRAZO_ADDA117", idLogRecebArq, idLogDetArqInicial, idLogDetArqInicial, LOG_INCLUIR_DECURSO_ADDA117);
            executarScriptEmXML("PROCESSAR_ALTERACAO_DECURSO_PRAZO_ADDA117", idLogRecebArq, idLogDetArqInicial, idLogDetArqFinal, LOG_INCLUIR_DECURSO_ADDA117);
        } finally {
            fecharConexao(conn);
        }
    }

}
