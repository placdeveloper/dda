/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
 * Arquivo:         MensagemFilaDto.java
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
public class SubTipoGradeDTO extends BancoobDTO {

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
    
	public SubTipoGradeDTO() {
		super();
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
