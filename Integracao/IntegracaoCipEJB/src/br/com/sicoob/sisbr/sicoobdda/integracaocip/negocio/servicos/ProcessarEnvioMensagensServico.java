/**
7 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ProcessarEnvioMensagensServico.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ProcessarEnvioMensagensServico é responsável por centralizar o envio de mensagens para CIP
 * 
 * @author Rafael.Silva
 */
public interface ProcessarEnvioMensagensServico extends IntegracaoCipServico {

    /**
     * Método responsável por listar as mensagens pendentes de envio para a CIP de acordo com os agrupamentos. Chamar o serviço de conversão em seguida postar
     * na fila de envio.
     * 
     * @param numPrioridade
     * 
     * @param idMsgInicio
     * @param idMsgFim
     * @throws ComumException void
     * 
     */
    void processarEnvioMensagensNaoPrioritarias(int numAgrupamentoSteps) throws ComumException;

    /**
     * Método responsável por listar as mensagens pendentes de envio para a CIP de acordo com os agrupamentos. Chamar o serviço de conversão em seguida postar
     * na fila de envio.
     * 
     * @param numPrioridade
     * 
     * @param idMsgInicio
     * @param idMsgFim
     * @throws ComumException void
     * 
     */
    void processarEnvioMensagensPrioritarias(int numAgrupamentoSteps) throws ComumException;

    /**
     * Método responsável por excluir todos os Registros da tabela Temporaria criada para o Motor de envio de Mensagens CIP
     */
    void excluirMensagensTabelaTmp() throws ComumException;

    /**
     * Método responsável por inserir todas as mensagens na tabela temporaria que serão enviadas pelo SWS do motor de envio de msgs
     * 
     * @param qtdParametroMensagensEnviadasPorRajada
     * @param qtdMaxRegistrosPorStep
     * @param qtdParametroNumPrioridadeMsg void
     */
    void registrarMensagensTabelaTmp(Integer qtdParametroMensagensEnviadasPorRajada, Integer qtdMaxRegistrosPorStep, Integer qtdParametroNumPrioridadeMsg) throws ComumException;

}
