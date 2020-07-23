/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
 * Arquivo:         LogArquivoCargaDto.java
 * Data Criação:    Sep 11, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.tipos.DateTime;

/**
 * LogArquivoCargaDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LogArquivoCargaDTO")
public class LogArquivoCargaDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -8153427149542870113L;

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

    private DateTime movimentoDataInicial;
    private DateTime movimentoDataFinal;

    /**
     * @param idArquivo
     * @param descNomeArquivoEnviado
     * @param bolOrigemSicoob
     * @param dataHoraDDA
     * @param dataHoraMovimento
     * @param descSituacaoArquivo
     * @param descTipoErro
     * @param qtdErroBeneficiarioRetornoCip
     */
    public LogArquivoCargaDto(Long idArquivo, String descNomeArquivoEnviado, Boolean bolOrigemSicoob, Date dataHoraDDA, Date dataHoraMovimento, String descSituacaoArquivo,
            String descTipoErro, Long qtdErroBeneficiarioRetornoCip, String descNomeArquivoRecebido, Date dataHoraArquivoRecebido) {
        super();
        this.idArquivo = idArquivo;
        this.descNomeArquivoEnviado = descNomeArquivoEnviado;
        this.bolOrigemSicoob = bolOrigemSicoob;
        this.dataHoraDDA = dataHoraDDA;
        this.dataHoraMovimento = dataHoraMovimento;
        this.descSituacaoArquivo = descSituacaoArquivo;
        this.statusArquivo = (!ObjectUtil.isEmpty(descTipoErro) ? Constantes.SHORT_ZERO : (!ObjectUtil.isEmpty(qtdErroBeneficiarioRetornoCip) ? Constantes.SHORT_ZERO
                : Constantes.SHORT_UM));
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
        this.dataHoraArquivoRecebido = dataHoraArquivoRecebido;
    }

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
     * @return the bolOrigemSicoob
     */
    public Boolean getBolOrigemSicoob() {
        return bolOrigemSicoob;
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
    public DateTime getMovimentoDataInicial() {
        return movimentoDataInicial;
    }

    /**
     * @param movimentoDataInicial the movimentoDataInicial to set
     */
    public void setMovimentoDataInicial(DateTime movimentoDataInicial) {
        this.movimentoDataInicial = movimentoDataInicial;
    }

    /**
     * @return the movimentoDataFinal
     */
    public DateTime getMovimentoDataFinal() {
        return movimentoDataFinal;
    }

    /**
     * @param movimentoDataFinal the movimentoDataFinal to set
     */
    public void setMovimentoDataFinal(DateTime movimentoDataFinal) {
        this.movimentoDataFinal = movimentoDataFinal;
    }

}
