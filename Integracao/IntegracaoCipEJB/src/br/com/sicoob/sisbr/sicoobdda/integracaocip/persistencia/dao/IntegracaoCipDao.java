/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         IntegracaoCipDao.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;

/**
 * IntegracaoCipDao
 * 
 * @author Rafael.Silva
 */
public interface IntegracaoCipDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por listar as mensagens para envio a CIP, de acordo com a prioridade (BOLMSGPRIORITARIA) e o numAgrupamento da tabela temporaria
     * 
     * @param bolMensagemPrioritaria
     * @param numAgrupamentoSteps
     * @return
     * @throws ComumException Map<String,Integer>
     */
    Map<Long, String> mapMensagensEnvioCip(boolean bolMensagemPrioritaria, int numAgrupamentoSteps) throws ComumException;

    /**
     * Método responsável por atualizar as informações da mensagem após postagem na fila da CIP com a dataHoraMensagem atual do banco.
     * 
     * @param idMsg
     * @param xmlMsg
     * @throws ComumException void
     * 
     */
    void atualizarMensagemEnviada(Long idMsg, String xmlMsg) throws ComumException;

    /**
     * Método responsável por atualizar o tipo Mensagem dda
     * 
     * @param idMsg
     * @param codTipoMensagemDDA
     * @throws ComumException void
     * 
     */
    void atualizarTipoMensagemDDA(Long idMsg, String codTipoMensagemDDA) throws ComumException;

    /**
     * Método responsável por obter a mensagem do DDA
     * 
     * @param idMensagemDDA
     * @return MensagemDDA
     * 
     */
    MensagemDDA obterMensagemDDA(Long idMensagemDDA);

    /**
     * Método responsável por Atualizar a dataHoraArquivo da tabela DDA.LogEnvioArquivoDDA
     * 
     * @param idLogEnvioArquivoDDA
     * @throws ComumException void
     * 
     */
    void atualizarDataHoraArquivo(Long idLogEnvioArquivoDDA) throws ComumException;

    /**
     * Método responsável por Obter O LogEnvioArquivoDDA
     * 
     * @param idLogEnvioArquivoDDA
     * @return LogEnvioArquivoDDA
     * 
     */
    LogEnvioArquivoDDA obterLogEnvioArquivoDDA(Long idLogEnvioArquivoDDA) throws ComumException;

    /**
     * Método responsável por Atualizar a dataHoraEnvio da tabela DDA.LogEnvioArquivoDDA
     * 
     * @param idLogEnvioArquivoDDA
     * @throws ComumException void
     * 
     */
    void atualizarDataHoraEnvio(Long idLogEnvioArquivoDDA) throws ComumException;

    /**
     * Método responsável por Atualizar a Data Hora Mensagem
     * 
     * @param codTipoMensagemDDA
     * 
     * @param id void
     * 
     */
    void atualizarDataHoraMensagem(Long idLogEnvioArquivoDDA, String codTipoMensagemDDA) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idMensagem
     * @return
     * @throws ComumException String
     * 
     */
    String obterTipoMensagemLock(Long idMensagem) throws ComumException;

    /**
     * Método responsável por atualizar o XML da MensagemDDA
     * 
     * @param idMensagemDDA
     * @param xml
     * @throws ComumException void
     * 
     */
    void atualizarXMLMensagemDDA(Long idMensagemDDA, String xml) throws ComumException;

    /**
     * @param tipoGradeHoraria
     * @throws ComumException void
     * 
     */
    void atualizarTipoGradeHoraria(TipoGradeHoraria tipoGradeHoraria) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param codTipoMensagemDDA
     * @return Set<MensagemDDA>
     * @throws ComumException
     * 
     */
    List<Long> listarIdMensagensSLAExcedido(String codTipoMensagemDDA) throws ComumException;

    /**
     * Metodo Responsavel por incluir o LogEnvioarquivoDDA junto com os seus Detalhes
     * 
     * @param qtdMaximaArquivosProcessadosPorInteracao
     * @param
     * @param qtdMaxRegistrosArquivos
     * 
     * @param idMensagemDDAInicial
     * @param idMensagemDDAFinal
     * @param descNomeArquivoEnviado
     * @param dataMovimento
     * @param codTipoMensagemDDA
     */
    void incluirLogEnvioArquivoDDA(Long qtdMaxRegistrosArquivos, Long qtdMaximaTotalRegistros, Long qtdMaximaArquivosProcessadosPorInteracao) throws ComumException;

    /**
     * Método responsável por atualizar o logEnvioArquivoDDa
     * 
     * @param logEnvioArquivoDDA
     * @throws ComumException void
     * 
     */
    void atualizarLogEnvioArquivoDDA(LogEnvioArquivoDDA logEnvioArquivoDDA) throws ComumException;

    /**
     * Método responsável por obter o ultimo Sequencial do Log Envio de arquivo
     * 
     * @param codTipoMensagem
     * @param dataMovimento
     * @return Integer
     * 
     */
    Integer obterUltimoSequencialArquivo(String codTipoMensagem, Date dataMovimento) throws ComumException;

    /**
     * Método responsável por excluir todos os registros de mensagens da tabela temporaria utilizado no SWS de Envio de MGS
     * 
     * @throws ComumException void
     */
    void excluirMensagensTabelaTmp() throws ComumException;

    /**
     * Método responsável por inserir todas as mensagens que serao enviadas pelo SWS do motor de envio de msgs
     * 
     * @param qtdParametroMensagensEnviadasPorRajada
     * @param qtdMaxRegistrosPorStep
     * @param qtdParametroNumPrioridadeMsg void
     */
    void registrarMensagensTabelaTmp(Integer qtdParametroMensagensEnviadasPorRajada, Integer qtdMaxRegistrosPorStep, Integer qtdParametroNumPrioridadeMsg) throws ComumException;

    /**
     * Método responsável por verificar se o motor de envio de arquivo enviara mensagens.
     * 
     * Ex: parametro 118 - definido como 30 e parametro 119 - definido como grade horaria dda006 Dessa maneira o motor de envio de arquivo na rodara 30 minutos
     * antes e nem 30 minutos depois da hora de abertura da grade horaria.
     * 
     * @param gradeHoraria
     * 
     * @param tempo
     * 
     * @return Boolean
     */
    Boolean isVerificarGradeHorariaEnviarMotorArquivo(Integer tempoInativoMotorEnvioArquivo, String listaGradeHoraria) throws ComumException;

}
