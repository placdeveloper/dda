/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         MensagemFilaDTO.java
 * Data Criação:    Aug 2, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author samuell.ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.MensagemFilaDto")
public class MensagemFilaDTO extends BancoobDTO {

    private Long qtdAguardandoEnvio;
    private Long qtdRetornoComErro;
    private Long qtdSemRetornoCIP;
    private Long qtdSemRetornoSSPB;

    private Long alertaAguardandoEnvio;
    private Long alertaRetornoComErro;
    private Long alertaSemRetornoCIP;
    private Long alertaSemRetornoSSPB;

    private DateTimeDB dataMovimento;

    private String codTipoErroCip;
    private String descTipoErroCip;
    private Long total;
    private String codTipoMensagemDDA;

    /**
     * @return the qtdAguardandoEnvio
     */
    public Long getQtdAguardandoEnvio() {
        return qtdAguardandoEnvio;
    }

    /**
     * @param qtdAguardandoEnvio the qtdAguardandoEnvio to set
     */
    public void setQtdAguardandoEnvio(Long qtdAguardandoEnvio) {
        this.qtdAguardandoEnvio = qtdAguardandoEnvio;
    }

    /**
     * @return the qtdRetornoComErro
     */
    public Long getQtdRetornoComErro() {
        return qtdRetornoComErro;
    }

    /**
     * @param qtdRetornoComErro the qtdRetornoComErro to set
     */
    public void setQtdRetornoComErro(Long qtdRetornoComErro) {
        this.qtdRetornoComErro = qtdRetornoComErro;
    }

    /**
     * @return the qtdSemRetornoCIP
     */
    public Long getQtdSemRetornoCIP() {
        return qtdSemRetornoCIP;
    }

    /**
     * @param qtdSemRetornoCIP the qtdSemRetornoCIP to set
     */
    public void setQtdSemRetornoCIP(Long qtdSemRetornoCIP) {
        this.qtdSemRetornoCIP = qtdSemRetornoCIP;
    }

    /**
     * @return the qtdSemRetornoSSPB
     */
    public Long getQtdSemRetornoSSPB() {
        return qtdSemRetornoSSPB;
    }

    /**
     * @param qtdSemRetornoSSPB the qtdSemRetornoSSPB to set
     */
    public void setQtdSemRetornoSSPB(Long qtdSemRetornoSSPB) {
        this.qtdSemRetornoSSPB = qtdSemRetornoSSPB;
    }

    /**
     * @return the alertaAguardandoEnvio
     */
    public Long getAlertaAguardandoEnvio() {
        return alertaAguardandoEnvio;
    }

    /**
     * @param alertaAguardandoEnvio the alertaAguardandoEnvio to set
     */
    public void setAlertaAguardandoEnvio(Long alertaAguardandoEnvio) {
        this.alertaAguardandoEnvio = alertaAguardandoEnvio;
    }

    /**
     * @return the alertaRetornoComErro
     */
    public Long getAlertaRetornoComErro() {
        return alertaRetornoComErro;
    }

    /**
     * @param alertaRetornoComErro the alertaRetornoComErro to set
     */
    public void setAlertaRetornoComErro(Long alertaRetornoComErro) {
        this.alertaRetornoComErro = alertaRetornoComErro;
    }

    /**
     * @return the alertaSemRetornoCIP
     */
    public Long getAlertaSemRetornoCIP() {
        return alertaSemRetornoCIP;
    }

    /**
     * @param alertaSemRetornoCIP the alertaSemRetornoCIP to set
     */
    public void setAlertaSemRetornoCIP(Long alertaSemRetornoCIP) {
        this.alertaSemRetornoCIP = alertaSemRetornoCIP;
    }

    /**
     * @return the alertaSemRetornoSSPB
     */
    public Long getAlertaSemRetornoSSPB() {
        return alertaSemRetornoSSPB;
    }

    /**
     * @param alertaSemRetornoSSPB the alertaSemRetornoSSPB to set
     */
    public void setAlertaSemRetornoSSPB(Long alertaSemRetornoSSPB) {
        this.alertaSemRetornoSSPB = alertaSemRetornoSSPB;
    }

    /**
     * @return the dataMovimento
     */
    public DateTimeDB getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(DateTimeDB dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the codTipoErroCip
     */
    public String getCodTipoErroCip() {
        return codTipoErroCip;
    }

    /**
     * @param codTipoErroCip the codTipoErroCip to set
     */
    public void setCodTipoErroCip(String codTipoErroCip) {
        this.codTipoErroCip = codTipoErroCip;
    }

    /**
     * @return the descTipoErroCip
     */
    public String getDescTipoErroCip() {
        return descTipoErroCip;
    }

    /**
     * @param descTipoErroCip the descTipoErroCip to set
     */
    public void setDescTipoErroCip(String descTipoErroCip) {
        this.descTipoErroCip = descTipoErroCip;
    }

    /**
     * @return the total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * @return codTipoMensagemDDA
     */
    public String getCodTipoMensagemDDA() {
        return codTipoMensagemDDA;
    }

    /**
     * @param codTipoMensagemDDA
     */
    public void setCodTipoMensagemDDA(String codTipoMensagemDDA) {
        this.codTipoMensagemDDA = codTipoMensagemDDA;
    }

}
