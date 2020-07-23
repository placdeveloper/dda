/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto
 * Arquivo:         LancamentoIntegracaoRetDto.java
 * Data Criação:    Nov 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * LancamentoIntegracaoRetDto é responsável por
 * 
 * @author samuell.ramos
 */
public class LancamentoIntegracaoRetDto extends BancoobDto {

    private static final long serialVersionUID = 1L;

    public static final String[] CAMPOS = new String[] { "numSeqLanc", "codRetorno", "mensagem", "campoErro", "codErroRetorno", "numSeqTransacaoLog" };

    private Integer numSeqLanc;

    private Integer codRetorno;
    private String mensagem;
    private String campoErro;
    private String codErroRetorno;
    private Long numSeqTransacaoLog;

    public Integer getNumSeqLanc() {
        return numSeqLanc;
    }

    public void setNumSeqLanc(Integer numSeqLanc) {
        this.numSeqLanc = numSeqLanc;
    }

    public Integer getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(Integer codRetorno) {
        this.codRetorno = codRetorno;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCampoErro() {
        return campoErro;
    }

    public void setCampoErro(String campoErro) {
        this.campoErro = campoErro;
    }

    public String getCodErroRetorno() {
        return codErroRetorno;
    }

    public void setCodErroRetorno(String codErroRetorno) {
        this.codErroRetorno = codErroRetorno;
    }

    public Long getNumSeqTransacaoLog() {
        return numSeqTransacaoLog;
    }

    public void setNumSeqTransacaoLog(Long numSeqTransacaoLog) {
        this.numSeqTransacaoLog = numSeqTransacaoLog;
    }

}
