/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MensagemDDABoletoDescontoDto.java
 * Data Criação:    Jul 21, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemDDABoletoDescontoDto é responsável por
 * 
 * @author tayrone.oliveira
 */
/**
 * MensagemDDABoletoDescontoDto é responsável por
 * 
 * @author Sadi.Santos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDABoletoDescontoDTO")
public class MensagemDDABoletoDescontoDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String codTipoDesconto;
    private Date dataDesconto;
    private BigDecimal valorDesconto;

    // CONSTRUCTOR
    /**
     * 
     */
    public MensagemDDABoletoDescontoDto() {
        super();
    }

    /**
     * @param idMensagemDDABoletoDesconto
     * @param idMensagemDDA
     * @param codTipoDesconto
     * @param dataDesconto
     * @param valorDesconto
     */
    public MensagemDDABoletoDescontoDto(String codTipoDesconto, Date dataDesconto, BigDecimal valorDesconto) {
        this.codTipoDesconto = codTipoDesconto;
        this.dataDesconto = dataDesconto;
        this.valorDesconto = valorDesconto;
    }

    /**
     * @return codTipoDesconto
     */
    public String getCodTipoDesconto() {
        return codTipoDesconto;
    }

    /**
     * @param codTipoDesconto
     */
    public void setCodTipoDesconto(String codTipoDesconto) {
        this.codTipoDesconto = codTipoDesconto;
    }

    /**
     * @return dataDesconto
     */
    public Date getDataDesconto() {
        return dataDesconto;
    }

    /**
     * @param dataDesconto
     */
    public void setDataDesconto(Date dataDesconto) {
        this.dataDesconto = dataDesconto;
    }

    /**
     * @return valorDesconto
     */
    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    /**
     * @param valorDesconto
     */
    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }
}
