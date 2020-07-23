/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
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
public class SubTipoGradeDto extends BancoobDto {

    private static final long serialVersionUID = -6930013488350664209L;
    
    private static final long ALERTA_AMARELO = 2;
    private static final long ALERTA_VERMELHO = 3;
    private static final long ALERTA_VERDE = 1;
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
    
	public SubTipoGradeDto() {
		super();
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
	public SubTipoGradeDto(
			Long total, 
			Long qtdAguardandoEnvio,
			Integer amareloAguardandoEnvio,
			Integer valorParametroAguardandoEnvio,
			
			Long qtdSemRetornoSSPB,
			Integer amareloSemRetornoSSPB,
			Integer valorParametroSemRetornoSSPB,
			
			Long qtdSemRetornoCIPInteger,
			Integer amareloSemRetornoCIP,
			Integer valorParametroSemRetornoCIP,
			
			Long qtdRetornoComErro,
			Integer amareloRetornoComErro,
			Integer valorParametroRetornoComErro,
			Long totalForaGrade) {
		
		this.total = total;
		this.qtdAguardandoEnvio = qtdAguardandoEnvio;
		this.qtdSemRetornoSSPB = qtdSemRetornoSSPB;
		this.qtdSemRetornoCIP = qtdSemRetornoCIPInteger;
		this.qtdRetornoComErro = qtdRetornoComErro;
		
		this.alertaAguardandoEnvio = nivelAlerta(qtdAguardandoEnvio, valorParametroAguardandoEnvio, amareloAguardandoEnvio, totalForaGrade);
		this.alertaRetornoComErro = nivelAlerta(qtdSemRetornoSSPB, valorParametroSemRetornoSSPB, amareloRetornoComErro, totalForaGrade);
		this.alertaSemRetornoCIP = nivelAlerta(qtdSemRetornoCIPInteger, valorParametroSemRetornoCIP, amareloSemRetornoCIP, totalForaGrade);
		this.alertaSemRetornoSSPB = nivelAlerta(qtdRetornoComErro, valorParametroRetornoComErro, amareloSemRetornoSSPB, totalForaGrade);
	}
	
	
	/*
	 * Nivel de alerta.
	 */
	public Long nivelAlerta(Long total, Integer valorParametroVermelho, Integer valorParametroAmarelo, Long totalForaGrade){

		if(totalForaGrade > 0){
			return ALERTA_PRETO;
		}else{
			if (total > valorParametroVermelho) {
				return ALERTA_VERMELHO;
			} else if ((total > valorParametroAmarelo) && (total <= valorParametroVermelho)){
				return ALERTA_AMARELO;
			}  
			return ALERTA_VERDE;
		}
	}


	/**
	 * @param dataMovimento
	 * @param total
	 * @param qtdAguardandoEnvio
	 * @param qtdSemRetornoSSPB
	 * @param qtdSemRetornoCIPInteger
	 * @param qtdRetornoComErro
	 */
	public SubTipoGradeDto(Date dataMovimento, Long total, Long qtdAguardandoEnvio, Long qtdSemRetornoSSPB, Long qtdSemRetornoCIPInteger, Long qtdRetornoComErro) {
		this.total = total;
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
	public SubTipoGradeDto(String codTipoErroCip, String descTipoErroCip, Long total) {
		this.codTipoErroCip = codTipoErroCip;
		this.descTipoErroCip = descTipoErroCip;
		this.total = total;
	}

	public Long getQtdAguardandoEnvio() {
		return qtdAguardandoEnvio;
	}

	public void setQtdAguardandoEnvio(Long qtdAguardandoEnvio) {
		this.qtdAguardandoEnvio = qtdAguardandoEnvio;
	}

	public Long getQtdRetornoComErro() {
		return qtdRetornoComErro;
	}

	public void setQtdRetornoComErro(Long qtdRetornoComErro) {
		this.qtdRetornoComErro = qtdRetornoComErro;
	}

	public Long getQtdSemRetornoCIP() {
		return qtdSemRetornoCIP;
	}

	public void setQtdSemRetornoCIP(Long qtdSemRetornoCIP) {
		this.qtdSemRetornoCIP = qtdSemRetornoCIP;
	}

	public Long getQtdSemRetornoSSPB() {
		return qtdSemRetornoSSPB;
	}

	public void setQtdSemRetornoSSPB(Long qtdSemRetornoSSPB) {
		this.qtdSemRetornoSSPB = qtdSemRetornoSSPB;
	}

	public DateTimeDB getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(DateTimeDB dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public String getCodTipoErroCip() {
		return codTipoErroCip;
	}

	public void setCodTipoErroCip(String codTipoErroCip) {
		this.codTipoErroCip = codTipoErroCip;
	}

	public String getDescTipoErroCip() {
		return descTipoErroCip;
	}

	public void setDescTipoErroCip(String descTipoErroCip) {
		this.descTipoErroCip = descTipoErroCip;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Long getAlertaAguardandoEnvio() {
		return alertaAguardandoEnvio;
	}

	public Long getAlertaRetornoComErro() {
		return alertaRetornoComErro;
	}

	public void setAlertaRetornoComErro(Long alertaRetornoComErro) {
		this.alertaRetornoComErro = alertaRetornoComErro;
	}

	public Long getAlertaSemRetornoCIP() {
		return alertaSemRetornoCIP;
	}

	public void setAlertaSemRetornoCIP(Long alertaSemRetornoCIP) {
		this.alertaSemRetornoCIP = alertaSemRetornoCIP;
	}

	public Long getAlertaSemRetornoSSPB() {
		return alertaSemRetornoSSPB;
	}

	public void setAlertaSemRetornoSSPB(Long alertaSemRetornoSSPB) {
		this.alertaSemRetornoSSPB = alertaSemRetornoSSPB;
	}

	public void setAlertaAguardandoEnvio(Long alertaAguardandoEnvio) {
		this.alertaAguardandoEnvio = alertaAguardandoEnvio;
	}


}
