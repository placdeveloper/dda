/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         LogArquivoCargaDTO.java
 * Data Criação:    Sep 11, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.Date;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * LogArquivoCargaDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.LogArquivoCargaDto")
public class LogArquivoCargaDTO extends BancoobDTO {

    private Long idArquivo;
    private String descNomeArquivoEnviado;
    private Boolean bolOrigemSicoob;
    private Date dataHoraDDA;
    private Date dataHoraMovimento;
    private String descSituacaoArquivo;
    private Short statusArquivo;
    private Boolean selecionado;

    private String descNomeArquivoRecebido;
    private Date dataHoraArquivoRecebido;

    private Date movimentoDataInicial;
    private Date movimentoDataFinal;

    /**
     * @return the idArquivo
     */
    public Long getIdArquivo() {
        return idArquivo;
    }

    /**
     * @param idArquivo the idArquivo to set
     */
    public void setIdArquivo(Long idArquivo) {
        this.idArquivo = idArquivo;
    }

    /**
     * @return the bolOrigemSicoob
     */
    public Boolean getBolOrigemSicoob() {
        return bolOrigemSicoob;
    }

    /**
     * @return the descNomeArquivoEnviado
     */
    public String getDescNomeArquivoEnviado() {
        return descNomeArquivoEnviado;
    }

    /**
     * @param descNomeArquivoEnviado the descNomeArquivoEnviado to set
     */
    public void setDescNomeArquivoEnviado(String descNomeArquivoEnviado) {
        this.descNomeArquivoEnviado = descNomeArquivoEnviado;
    }

    /**
     * @param bolOrigemSicoob the bolOrigemSicoob to set
     */
    public void setBolOrigemSicoob(Boolean bolOrigemSicoob) {
        this.bolOrigemSicoob = bolOrigemSicoob;
    }

    /**
     * @return the dataHoraDDA
     */
    public Date getDataHoraDDA() {
        return dataHoraDDA;
    }

    /**
     * @param dataHoraDDA the dataHoraDDA to set
     */
    public void setDataHoraDDA(Date dataHoraDDA) {
        this.dataHoraDDA = dataHoraDDA;
    }

    /**
     * @return the dataHoraMovimento
     */
    public Date getDataHoraMovimento() {
        return dataHoraMovimento;
    }

    /**
     * @param dataHoraMovimento the dataHoraMovimento to set
     */
    public void setDataHoraMovimento(Date dataHoraMovimento) {
        this.dataHoraMovimento = dataHoraMovimento;
    }

    /**
     * @return the descSituacaoArquivo
     */
    public String getDescSituacaoArquivo() {
        return descSituacaoArquivo;
    }

    /**
     * @param descSituacaoArquivo the descSituacaoArquivo to set
     */
    public void setDescSituacaoArquivo(String descSituacaoArquivo) {
        this.descSituacaoArquivo = descSituacaoArquivo;
    }

    /**
     * @return the statusArquivo
     */
    public Short getStatusArquivo() {
        return statusArquivo;
    }

    /**
     * @param statusArquivo the statusArquivo to set
     */
    public void setStatusArquivo(Short statusArquivo) {
        this.statusArquivo = statusArquivo;
    }

    /**
     * @return the selecionado
     */
    public Boolean getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the descNomeArquivoRecebido
     */
    public String getDescNomeArquivoRecebido() {
        return descNomeArquivoRecebido;
    }

    /**
     * @param descNomeArquivoRecebido the descNomeArquivoRecebido to set
     */
    public void setDescNomeArquivoRecebido(String descNomeArquivoRecebido) {
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
    }

    /**
     * @return the dataHoraArquivoRecebido
     */
    public Date getDataHoraArquivoRecebido() {
        return dataHoraArquivoRecebido;
    }

    /**
     * @param dataHoraArquivoRecebido the dataHoraArquivoRecebido to set
     */
    public void setDataHoraArquivoRecebido(Date dataHoraArquivoRecebido) {
        this.dataHoraArquivoRecebido = dataHoraArquivoRecebido;
    }

    /**
     * @return the movimentoDataInicial
     */
    public Date getMovimentoDataInicial() {
        return movimentoDataInicial;
    }

    /**
     * @param movimentoDataInicial the movimentoDataInicial to set
     */
    public void setMovimentoDataInicial(Date movimentoDataInicial) {
        this.movimentoDataInicial = movimentoDataInicial;
    }

    /**
     * @return the movimentoDataFinal
     */
    public Date getMovimentoDataFinal() {
        return movimentoDataFinal;
    }

    /**
     * @param movimentoDataFinal the movimentoDataFinal to set
     */
    public void setMovimentoDataFinal(Date movimentoDataFinal) {
        this.movimentoDataFinal = movimentoDataFinal;
    }

}
