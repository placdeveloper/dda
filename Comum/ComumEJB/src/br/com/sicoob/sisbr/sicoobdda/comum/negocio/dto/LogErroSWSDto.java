/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         LogErroSWSDto.java
 * Data Criação:    Sep 23, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * LogErroSWSDto é responsável por encapsular a logica de logs de erro para os motores de msg, carga
 * 
 * @author Rafael.Silva
 */
public class LogErroSWSDto {
    private StringBuilder logs = new StringBuilder();
    private int qtdErros;
    private int qtdMaxErros;
    private String chaveExcecao = null;

    private static final int QTD_MAX_DEFAULT = 10;

    /**
     * Construtor para inicializar o log com qtdMax de erros e chave para compor a mensagem.
     * 
     * @param qtdMaxErros
     * @param chaveExcecao
     */
    public LogErroSWSDto(int qtdMaxErros, String chaveExcecao) {
        this.qtdMaxErros = qtdMaxErros;
        this.chaveExcecao = chaveExcecao;
    }

    /**
     * Construtor para inicializar o log com qtdMax com default "10" de erros e chave para compor a mensagem.
     * 
     * @param chaveExcecao
     */
    public LogErroSWSDto(String chaveExcecao) {
        this.qtdMaxErros = QTD_MAX_DEFAULT;
        this.chaveExcecao = chaveExcecao;
    }

    /**
     * Método responsável por registrar o erro ocorrido.
     * 
     * @param e
     * @param erro
     * @param informacoes
     * @throws MigrarCanaisException
     */
    public void incluirErro(Exception e, String informacoes) throws ComumException {
        qtdErros++;
        logs.append(getLogErro(e.getMessage(), informacoes));
        if (qtdErros >= qtdMaxErros) {
            validarPossuiErro();
        }
    }

    /**
     * Método responsável por formar o erro com a chave.
     * 
     * @param msgErro
     * @param informacoes
     * @return String
     * 
     */
    public String getLogErroComChave(String msgErro, String informacoes) {
        return MensagemUtil.getString(this.chaveExcecao, getLogErro(msgErro, informacoes));

    }

    /**
     * Método responsável por formatar o erro.
     * 
     * @param msgErro
     * @param informacoes
     * @return String
     * 
     */
    public String getLogErro(String msgErro, String informacoes) {
        StringBuilder log = new StringBuilder();
        log.append(informacoes);
        log.append(" Exc.: ");
        log.append(msgErro);
        log.append(Constantes.STRING_PONTO_E_VIRGULA);
        log.append(Constantes.STRING_ESPACO_EM_BRANCO);
        return log.toString();

    }

    /**
     * Método responsável por limpar os registros de log de erro.
     */
    public void iniciarLogs(String chaveExcecao) {
        logs.setLength(0);
        qtdErros = 0;
        this.chaveExcecao = chaveExcecao;
    }

    /**
     * Método responsável por lançar a exception caso tenha ocorrido erro.
     * 
     * @throws MigrarCanaisException
     */
    public void validarPossuiErro() throws ComumException {
        if (qtdErros != 0) {
            String msg = logs.toString();
            throw new ComumException(chaveExcecao, msg);
        }
    }

    /**
     * @return the logs
     */
    public StringBuilder getLogs() {
        return logs;
    }

    /**
     * @param logs the logs to set
     */
    public void setLogs(StringBuilder logs) {
        this.logs = logs;
    }

    /**
     * @return the qtdErros
     */
    public int getQtdErros() {
        return qtdErros;
    }

    /**
     * @param qtdErros the qtdErros to set
     */
    public void setQtdErros(int qtdErros) {
        this.qtdErros = qtdErros;
    }

    /**
     * @return the chaveExcecao
     */
    public String getChaveExcecao() {
        return chaveExcecao;
    }

    /**
     * @param chaveExcecao the chaveExcecao to set
     */
    public void setChaveExcecao(String chaveExcecao) {
        this.chaveExcecao = chaveExcecao;
    }

    /**
     * @return the qtdMaxErros
     */
    public int getQtdMaxErros() {
        return qtdMaxErros;
    }

    /**
     * @param qtdMaxErros the qtdMaxErros to set
     */
    public void setQtdMaxErros(int qtdMaxErros) {
        this.qtdMaxErros = qtdMaxErros;
    }

}
