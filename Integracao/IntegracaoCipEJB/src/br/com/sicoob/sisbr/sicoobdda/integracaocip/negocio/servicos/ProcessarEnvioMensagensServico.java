/**
7 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ProcessarEnvioMensagensServico.java
 * Data Cria��o:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * ProcessarEnvioMensagensServico � respons�vel por centralizar o envio de mensagens para CIP
 * 
 * @author Rafael.Silva
 */
public interface ProcessarEnvioMensagensServico extends IntegracaoCipServico {

    /**
     * M�todo respons�vel por listar as mensagens pendentes de envio para a CIP de acordo com os agrupamentos. Chamar o servi�o de convers�o em seguida postar
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
     * M�todo respons�vel por listar as mensagens pendentes de envio para a CIP de acordo com os agrupamentos. Chamar o servi�o de convers�o em seguida postar
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
     * M�todo respons�vel por excluir todos os Registros da tabela Temporaria criada para o Motor de envio de Mensagens CIP
     */
    void excluirMensagensTabelaTmp() throws ComumException;

    /**
     * M�todo respons�vel por inserir todas as mensagens na tabela temporaria que ser�o enviadas pelo SWS do motor de envio de msgs
     * 
     * @param qtdParametroMensagensEnviadasPorRajada
     * @param qtdMaxRegistrosPorStep
     * @param qtdParametroNumPrioridadeMsg void
     */
    void registrarMensagensTabelaTmp(Integer qtdParametroMensagensEnviadasPorRajada, Integer qtdMaxRegistrosPorStep, Integer qtdParametroNumPrioridadeMsg) throws ComumException;

}
