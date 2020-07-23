/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         MensagemFilaDto.java
 * Data Criação:    Aug 2, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author samuell.ramos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemFilaDTO")
public class MensagemFilaDto extends BancoobDto {

    private static final long serialVersionUID = -6930013488350664209L;

    private static final long ALERTA_VERDE = 1;
    private static final long ALERTA_AMARELO = 2;
    private static final long ALERTA_VERMELHO = 3;
    private static final long ALERTA_PRETO = 4;

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
     * 
     */
    public MensagemFilaDto() {
        super();

        this.qtdAguardandoEnvio = 0L;
        this.qtdSemRetornoSSPB = 0L;
        this.qtdSemRetornoCIP = 0L;
        this.qtdRetornoComErro = 0L;

        this.alertaAguardandoEnvio = ALERTA_VERDE;
        this.alertaRetornoComErro = ALERTA_VERDE;
        this.alertaSemRetornoCIP = ALERTA_VERDE;
        this.alertaSemRetornoSSPB = ALERTA_VERDE;
    }

    /**
     * @param total
     * @param qtdAguardandoEnvio
     * @param valorParametroAguardandoEnvio
     * @param qtdSemRetornoSSPB
     * @param valorParametroSemRetornoSSPB
     * @param qtdSemRetornoCIPInteger
     * @param valorParametroSemRetornoCIP
     * @param qtdRetornoComErro
     * @param valorParametroRetornoComErro
     */
    public MensagemFilaDto(Long total, Long qtdAguardandoEnvio, Integer amareloAguardandoEnvio, Integer valorParametroAguardandoEnvio,

    Long qtdSemRetornoSSPB, Integer amareloSemRetornoSSPB, Integer valorParametroSemRetornoSSPB,

    Long qtdSemRetornoCIPInteger, Integer amareloSemRetornoCIP, Integer valorParametroSemRetornoCIP,

    Long qtdRetornoComErro, Integer amareloRetornoComErro, Integer valorParametroRetornoComErro, Long totalForaGrade) {

        this.total = total;
        this.qtdAguardandoEnvio = qtdAguardandoEnvio;
        this.qtdSemRetornoSSPB = qtdSemRetornoSSPB;
        this.qtdSemRetornoCIP = qtdSemRetornoCIPInteger;
        this.qtdRetornoComErro = qtdRetornoComErro;

        this.alertaAguardandoEnvio = nivelAlerta(qtdAguardandoEnvio, valorParametroAguardandoEnvio, amareloAguardandoEnvio, totalForaGrade);
        this.alertaRetornoComErro = nivelAlerta(qtdRetornoComErro, valorParametroRetornoComErro, amareloRetornoComErro, totalForaGrade);
        this.alertaSemRetornoCIP = nivelAlerta(qtdSemRetornoCIPInteger, valorParametroSemRetornoCIP, amareloSemRetornoCIP, totalForaGrade);
        this.alertaSemRetornoSSPB = nivelAlerta(qtdSemRetornoSSPB, valorParametroSemRetornoSSPB, amareloSemRetornoSSPB, totalForaGrade);
    }

    /**
     * @param dataMovimento
     * @param total
     * @param qtdAguardandoEnvio
     * @param qtdSemRetornoSSPB
     * @param qtdSemRetornoCIPInteger
     * @param qtdRetornoComErro
     */
    public MensagemFilaDto(Date dataMovimento, String codTipoMensagemDDA, Long total, Long qtdAguardandoEnvio, Long qtdSemRetornoSSPB, Long qtdSemRetornoCIPInteger,
            Long qtdRetornoComErro) {
        this.total = total;
        this.codTipoMensagemDDA = codTipoMensagemDDA;
        this.dataMovimento = DateUtil.getDateTimeDB(dataMovimento);
        this.qtdAguardandoEnvio = qtdAguardandoEnvio;
        this.qtdSemRetornoSSPB = qtdSemRetornoSSPB;
        this.qtdSemRetornoCIP = qtdSemRetornoCIPInteger;
        this.qtdRetornoComErro = qtdRetornoComErro;
    }

    /**
     * @param codTipoErroCip
     * @param descTipoErroCip
     * @param total
     */
    public MensagemFilaDto(String codTipoErroCip, String descTipoErroCip, Long total) {
        this.codTipoErroCip = codTipoErroCip;
        this.descTipoErroCip = descTipoErroCip;
        this.total = total;
    }

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
     * @return codTipoMensagemDDA da Mensagem
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

    /**
     * Método responsável por ober Nivel de alerta.
     * 
     * @param total
     * @param valorParametroVermelho
     * @param valorParametroAmarelo
     * @param totalForaGrade
     * @return Long
     * 
     */
    private Long nivelAlerta(Long total, Integer valorParametroVermelho, Integer valorParametroAmarelo, Long totalForaGrade) {
        if (totalForaGrade > 0) {
            return ALERTA_PRETO;
        } else if (total > valorParametroVermelho) {
            return ALERTA_VERMELHO;
        } else if ((total > valorParametroAmarelo) && (total <= valorParametroVermelho)) {
            return ALERTA_AMARELO;
        }
        return ALERTA_VERDE;
    }

}
