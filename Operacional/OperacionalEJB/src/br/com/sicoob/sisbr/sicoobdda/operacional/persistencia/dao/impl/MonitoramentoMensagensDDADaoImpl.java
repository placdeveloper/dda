/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         MonitoramentoMensagensDaoImpl.java
 * Data Criação:    Ago 13, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.util.List;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroCargaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TipoErroCipDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoramentoMensagensDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * MonitoramentoMensagensDaoImpl é responsável por
 * 
 * @author Samuell.Ramos
 */
public class MonitoramentoMensagensDDADaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements MonitoramentoMensagensDDADao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional";
    public static final String COMANDOS = "comandos-sicoobdda-operacional";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public MonitoramentoMensagensDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, null, COMANDOS, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.MonitoramentoMensagensDDADao#listarTipoMensagensDDA()
     */
    public List<TipoMensagemDDA> listarTipoMensagensDDA(String origem) throws ComumException {
        return listar("LISTAR_TIPO_MENSAGENS_DDA", getMapaParametro(origem, "origem"));

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.MonitoramentoMensagensDDADao#obterTiposErroCIP()
     */
    public List<TipoErroCipDto> listarTiposErroCIP() throws ComumException {
        return listar("LISTAR_TIPO_ERRO_CIP");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.MonitoramentoMensagensDDADao#obterLogErroCargaDTO(java.lang.Long)
     */
    public List<LogErroCargaDto> obterLogErroCargaDTO(Long idLogErro) throws ComumException {
        return listar("OBTER_TIPO_ERRO_LOG_BENEFICIARIO_INSTITUICAO", getMapaParametro(idLogErro, "idLogErro"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.MonitoramentoMensagensDDADao#countRegistroBeneficiarioErro(java.lang.Long,
     *      java.lang.String)
     */
    public Long obterQtdTotalErrosBeneficiariosPorArquivo(Long idArquivo) throws ComumException {
        // TODO - felipe.rosa - Validar se ainda é utilizado;
        return obterDados("OBTER_COUNT_ERRO_BENEFICIARIO_CIP", getMapaParametro(idArquivo, "idArquivo"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.MonitoramentoMensagensDDADao#countRegistroBeneficiarioErro(java.lang.Long,
     *      java.lang.String)
     */
    public void atualizarTipoTratamentoMensagemRetornoCip(Long idMensagemDDA, int codTipoTratamentoErroCip) throws ComumException {
        Map<String, Object> parametros = getMapaParametro(idMensagemDDA, "idMensagemDDA");
        executarUpdate("ATUALIZAR_TIPO_TRATAMENTO_MENSAGEM_RETORNO_CIP", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoramentoMensagensDDADao#recuperaMensagemOrigemDDA(java.lang.Long)
     */
    public MensagemDDA recuperaMensagemOrigemDDA(Long idMensagem) throws ComumException {
        return obterDados("OBTER_MENSAGEM_ORIGEM_DDA", getMapaParametro(idMensagem, "idMensagem"));
    }

}
