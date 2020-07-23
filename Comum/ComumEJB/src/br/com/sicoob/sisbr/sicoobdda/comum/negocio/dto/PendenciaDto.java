/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         PendenciaDto.java
 * Data Criação:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.sql.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * PendenciaDto é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PendenciaDTO")
public class PendenciaDto extends BancoobDto {

    private static final long serialVersionUID = 997512472056651920L;

    private DateTime dataMovimento;
    private Integer qtdEnviar;
    private Integer qtdSemRetornoSSPB;
    private Integer qtdSemRetornoCIP;
    private Integer qtdRetornoErro;
    private String codTipoMensagemDDA;

    /**
     * 
     */
    public PendenciaDto() {
        super();
    }

    /**
     * @param dataMovimento
     * @param qtdEnviar
     * @param qtdSemRetornoSSPB
     * @param qtdSemRetornoCIP
     * @param qtdRetornoErro
     */
    public PendenciaDto(Date dataMovimento, String codTipoMensagemDDA, Integer qtdEnviar, Integer qtdSemRetornoSSPB, Integer qtdSemRetornoCIP, Integer qtdRetornoErro) {
        super();
        this.dataMovimento = new DateTimeDB(dataMovimento.getTime());
        this.codTipoMensagemDDA = codTipoMensagemDDA;
        this.qtdEnviar = qtdEnviar;
        this.qtdSemRetornoSSPB = qtdSemRetornoSSPB;
        this.qtdSemRetornoCIP = qtdSemRetornoCIP;
        this.qtdRetornoErro = qtdRetornoErro;
    }

    /**
     * @return the dataMovimento
     */
    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the qtdEnviar
     */
    public Integer getQtdEnviar() {
        return qtdEnviar;
    }

    /**
     * @param qtdEnviar the qtdEnviar to set
     */
    public void setQtdEnviar(Integer qtdEnviar) {
        this.qtdEnviar = qtdEnviar;
    }

    /**
     * @return the qtdSemRetornoSSPB
     */
    public Integer getQtdSemRetornoSSPB() {
        return qtdSemRetornoSSPB;
    }

    /**
     * @param qtdSemRetornoSSPB the qtdSemRetornoSSPB to set
     */
    public void setQtdSemRetornoSSPB(Integer qtdSemRetornoSSPB) {
        this.qtdSemRetornoSSPB = qtdSemRetornoSSPB;
    }

    /**
     * @return the qtdSemRetornoCIP
     */
    public Integer getQtdSemRetornoCIP() {
        return qtdSemRetornoCIP;
    }

    /**
     * @param qtdSemRetornoCIP the qtdSemRetornoCIP to set
     */
    public void setQtdSemRetornoCIP(Integer qtdSemRetornoCIP) {
        this.qtdSemRetornoCIP = qtdSemRetornoCIP;
    }

    /**
     * @return the qtdRetornoErro
     */
    public Integer getQtdRetornoErro() {
        return qtdRetornoErro;
    }

    /**
     * @param qtdRetornoErro the qtdRetornoErro to set
     */
    public void setQtdRetornoErro(Integer qtdRetornoErro) {
        this.qtdRetornoErro = qtdRetornoErro;
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
