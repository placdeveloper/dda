/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         ArquivoEnviadoDaoImpl.java
 * Data Criação:    Jul 05, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoEnviadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * ArquivoEnviadoDaoImpl
 * 
 * @author Samuell.Ramos
 */
public class ArquivoEnviadoDaoImpl extends OperacionalCrudDaoDB2<LogEnvioArquivoDDA> implements ArquivoEnviadoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.arquivoenviado.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-arquivoenviado";
    public static final String COMANDOS = "PESQUISAR_ARQUIVO_ENVIADO";
    public static final String COMANDOS_QTD = "OBTER_QTD_ARQUIVO_ENVIADO";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public ArquivoEnviadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, LogEnvioArquivoDDA.class , COMANDOS, COMANDOS_QTD);
    }

    /**
     * {@inheritDoc}
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoEnviadoDao#obterEnvioArquivoDDA(java.lang.Long)
     */
    public LogEnvioArquivoDDA obterEnvioArquivoDDA(Long idLogEnvioArquivodda) throws ComumException {
        return obterDados("OBTER_ENVIO_ARQUIVO", getMapaParametro(idLogEnvioArquivodda, "idLogEnvioArquivodda"));
    }

    /**
     * {@inheritDoc}
     * @throws ComumException 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoEnviadoDao#listarTipoMensagemAgenAdda()
     */
    public List<TipoMensagemDDA> listarTipoMensagemAgenAdda() throws ComumException {
        return listar("LISTAR_TIPO_MENSAGEM_AGEN_ADDA");
    }

}
