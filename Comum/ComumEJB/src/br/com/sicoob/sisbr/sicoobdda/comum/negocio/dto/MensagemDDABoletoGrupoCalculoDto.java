/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MensagemDDABoletoGrupoCalculoDto.java
 * Data Criação:    Jul 21, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemDDABoletoGrupoCalculoDto é responsável por
 * 
 * @author tayrone.oliveira
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDABoletoGrupoCalculoDTO")
public class MensagemDDABoletoGrupoCalculoDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idMensagemDDABoletoGrupoCalculo;
    private BigDecimal juros;
    private BigDecimal multa;
    private BigDecimal desconto;
    private BigDecimal valorTotalCobrado;
    private DateTimeDB dataValidadeCalculo;

    // CONSTRUCTORS ======================================
    /**
     * 
     */
    public MensagemDDABoletoGrupoCalculoDto() {
        super();
    }

    /**
     * @param idMensagemDDABoletoGrupoCalculo
     * @param juros
     * @param multa
     * @param desconto
     * @param valorTotalCobrado
     * @param dataValidadeCalculo
     */
    public MensagemDDABoletoGrupoCalculoDto(Long idMensagemDDABoletoGrupoCalculo, BigDecimal juros, BigDecimal multa, BigDecimal desconto, BigDecimal valorTotalCobrado,
            DateTimeDB dataValidadeCalculo) {
        this.idMensagemDDABoletoGrupoCalculo = idMensagemDDABoletoGrupoCalculo;
        this.juros = juros;
        this.multa = multa;
        this.desconto = desconto;
        this.valorTotalCobrado = valorTotalCobrado;
        this.dataValidadeCalculo = dataValidadeCalculo;
    }

    // SETTERS AND GETTERS ===============================
    /**
     * @return o atributo idMensagemDDABoletoGrupoCalculo
     */
    public Long getIdMensagemDDABoletoGrupoCalculo() {
        return idMensagemDDABoletoGrupoCalculo;
    }

    /**
     * Define o atributo idMensagemDDABoletoGrupoCalculo
     */
    public void setIdMensagemDDABoletoGrupoCalculo(Long idMensagemDDABoletoGrupoCalculo) {
        this.idMensagemDDABoletoGrupoCalculo = idMensagemDDABoletoGrupoCalculo;
    }

    /**
     * @return o atributo juros
     */
    public BigDecimal getJuros() {
        return juros;
    }

    /**
     * Define o atributo juros
     */
    public void setJuros(BigDecimal juros) {
        this.juros = juros;
    }

    /**
     * @return o atributo multa
     */
    public BigDecimal getMulta() {
        return multa;
    }

    /**
     * Define o atributo multa
     */
    public void setMulta(BigDecimal multa) {
        this.multa = multa;
    }

    /**
     * @return o atributo desconto
     */
    public BigDecimal getDesconto() {
        return desconto;
    }

    /**
     * Define o atributo desconto
     */
    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    /**
     * @return o atributo valorTotalCobrado
     */
    public BigDecimal getValorTotalCobrado() {
        return valorTotalCobrado;
    }

    /**
     * Define o atributo valorTotalCobrado
     */
    public void setValorTotalCobrado(BigDecimal valorTotalCobrado) {
        this.valorTotalCobrado = valorTotalCobrado;
    }

    /**
     * @return o atributo dataValidadeCalculo
     */
    public DateTimeDB getDataValidadeCalculo() {
        return dataValidadeCalculo;
    }

    /**
     * Define o atributo dataValidadeCalculo
     */
    public void setDataValidadeCalculo(DateTimeDB dataValidadeCalculo) {
        this.dataValidadeCalculo = dataValidadeCalculo;
    }

}
