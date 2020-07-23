/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao
 * Arquivo:         ProcessamentoSWSDao.java
 * Data Criação:    Jun 27, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.processamento.persistencia.dao;

import java.util.List;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EnvioArquivoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.SituacaoRateioCreditoCCODto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;

/**
 * ProcessamentoSWSDao
 * 
 * @author rafael.silva
 */
public interface ProcessamentoSWSDao extends ProcessamentoCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por obter a quantidade de registros postados na fila de envio CIP que estejam sem protocolo.
     * 
     * @return
     * @throws ComumException Long
     * 
     */
    Long verificarQtdRegistroNaFilaEnvioMsgSemProtocolo() throws ComumException;


    /**
     * Método responsável por listar os agrupamentos de acordo com o tipo da prioridade da mensagem
     * 
     * @param numPrioridadeEnvio
     * @return List<Integer>
     * 
     */
    List<Integer> listarNumAgrupamentoEnvioMsg(boolean bolMsgPrioritaria);


    /**
     * Método responsável por Listar a Tabela DDA.LOGENVIOARQUIVODDA pela datahoraArquivo NULL e DataHoraEnvio NULL
     * 
     * @return
     * @throws ComumException List<Long>
     * 
     */
    List<Long> listarIdLogEnvioArquivoGeraArquivo() throws ComumException;

    /**
     * Método responsável por Listar a Tabela DDA.LOGENVIOARQUIVODDA pela datahoraArquivo NOT NULL e DataHoraEnvio NULL
     * 
     * @return
     * @throws ComumException List<Long>
     * 
     */
    List<Long> listarIdLogEnvioArquivoDDAPostarArquivo() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException List<Long>
     * 
     */
    List<Long> listarIdLogEnvioArquivoTratamento() throws ComumException;

    /**
     * Método responsável por retornar se existe algum arquivo para ser processado
     * 
     * @param codSituacaoProcessamentoArquivo situacao desejada do arquivo na tabela logrecebimentoarquivo
     * @param tiposDeArquivos um array contendo os tipos de arquivos desejados
     * @param tiposDeMensagens um array com os tipos de mensagens as quais os arquivos se referem
     * @return boolean retorno da consulta, true = existe arquivo, false = não existe arquivo
     * @throws ComumException boolean
     * 
     */
    boolean existeArquivoDisponivel(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens) throws ComumException;

    /**
     * Método responsável por retornar todos os arquivos disponíveis para processamento que atendam aos critérios passados nos parâmetros
     * 
     * @param codSituacaoProcessamentoArquivo inteiro indicando o código referente à situação de processamento do arquivo
     * @param tiposDeArquivos : Array de String com os tipos de arquivos que se deseja recuperar da base de dados
     * @param tiposDeMensagens : Array de String com os tipos de mensagens que se deseja recuperar da base de dados
     * @param qtdMaximaRegistros
     * @return List &lt ArquivoProcessamentoVO &lg : lista com os arquivos recuperados da base de dados que satisfazem os critérios de informados nos parâmetros
     * 
     */
    List<ArquivoProcessamentoVO> listarArquivoDaSequenciaParaProcessamento(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens,
            int qtdMaximaRegistros);

    /**
     * Método responsável por retornar todos os IDs dos arquivos disponíveis para processamento que atendam aos critérios passados nos parâmetros
     * 
     * @param codSituacaoProcessamentoArquivo inteiro indicando o código referente à situação de processamento do arquivo
     * @param tiposDeArquivos : Array de String com os tipos de arquivos que se deseja recuperar da base de dados
     * @param tiposDeMensagens : Array de String com os tipos de mensagens que se deseja recuperar da base de dados
     * @param qtdMaximaRegistros
     * @return List &lt Long &lg : lista com os arquivos recuperados da base de dados que satisfazem os critérios de informados nos parâmetros
     * 
     */
    List<Long> listarIdArquivoDaSequenciaParaProcessamento(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens, int qtdMaximaRegistros);

    /**
     * Método responsável por retornar os aquivos que deverão ter sua situação de processamento alterada.
     * 
     * @param codSituacaoProcessamentoArquivo
     * @param tiposDeArquivos
     * @param tiposDeMensagens
     * @param qtdMaximaRegistros
     * @return
     * @throws ComumException List<Long>
     * 
     */
    List<Long> listarIdArquivosEmSituacao(short codSituacaoProcessamentoArquivo, String[] tiposDeArquivos, String[] tiposDeMensagens, int qtdMaximaRegistros) throws ComumException;

    /**
     * Método responsável por recuperar os parametros para os steps que processarão os arquivos de BENEFICIARIOS
     * 
     * @param prIdArquivoRecebido identificador dos arquivo de BENEFICIARIO
     * @return List<String> lista com string contendo os parametros para cada step os parÂmetros são separados por ":"
     * 
     */
    List<String> listarParametrosParaStepPorInstancia(TipoArquivoRetornoEnum prTipoArq, long prIdArquivoRecebido, String prGrupoTag, String prTag);

    /**
     * Método responsável por listar as mensagens para irem para o expurgo
     * 
     * @param qtdDiasLimiteExpurgo
     * @return Map<Long,Long>
     * 
     */
    List<EnvioArquivoDto> listarMensagensCopiadasParaExpurgo(Long qtdDiasLimiteExpurgo);

    /**
     * Método responsável por listar as mensagens antigas para serem expurgadas
     * 
     * @return Map<Long,Long>
     * 
     */
    List<EnvioArquivoDto> listarMensagensAntigasParaExpurgar();

    /**
     * Método responsável por setar a quantidade de registros recebidos no arquivo.
     * 
     * @param idArquivoRecebido
     * @param qtdTotalRegistro
     * @throws ComumException void
     * 
     */
    void atualizarQtdeTotalRegistrosArquivoRecebido(long idArquivoRecebido, int qtdTotalRegistro) throws ComumException;

    /**
     * @param idArquivoRecebido
     * @param qtdRegistrosStep
     * @return Map<Long,List<ArquivoProcessamentoVO>>
     * 
     */
    Map<Long, List<ArquivoProcessamentoVO>> listarParametrosStepsProcessamentoArq(List<Long> idArquivoRecebido, long qtdRegistrosStep);

    /**
     * Método responsável por obter a quantidade de steps para o AtualizarSituacaoBoletoJobServico
     * 
     * @return
     * @throws ComumException long
     * 
     */
    long obterQuantidadeStepsAtualizarSituacaoBoleto() throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException List<MensagemContingenciaDto>
     * 
     */
    List<MensagemContingenciaDto> listarParametroStepContingenciaMensagem() throws ComumException;

    /**
     * Método responsável por listar as cooperativas que contêm lançamentos de rateio a serem efetivados no CCO
     * 
     * @param
     * @return List<LancamentosRateioCCODto>
     * @throws ComumException
     */
    List<RateioCreditoLancamentoCCODto> listarCooperativasLancamentosRateioCCO() throws ComumException;

    /**
     * Método responsável por listar os rateios de crétido em processamento para atualização no CCO
     * 
     * @param
     * @return List<SituacaoRateioCreditoCCODto>
     * @throws ComumException
     */
    List<SituacaoRateioCreditoCCODto> listarRateiosCreditoAtualizacaoCCO() throws ComumException;
}
