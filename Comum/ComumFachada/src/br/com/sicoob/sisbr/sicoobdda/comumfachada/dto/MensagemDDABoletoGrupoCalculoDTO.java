package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoGrupoCalculoDto")
public class MensagemDDABoletoGrupoCalculoDTO extends BancoobDTO {

    private Long idMensagemDDABoletoGrupoCalculo;
    private BigDecimal juros;
    private BigDecimal multa;
    private BigDecimal desconto;
    private BigDecimal valorTotalCobrado;
    private DateTime dataValidadeCalculo;

    /**
     * 
     */
    public MensagemDDABoletoGrupoCalculoDTO() {
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
    public MensagemDDABoletoGrupoCalculoDTO(Long idMensagemDDABoletoGrupoCalculo, BigDecimal juros, BigDecimal multa, BigDecimal desconto, BigDecimal valorTotalCobrado,
            DateTime dataValidadeCalculo) {
        this.idMensagemDDABoletoGrupoCalculo = idMensagemDDABoletoGrupoCalculo;
        this.juros = juros;
        this.multa = multa;
        this.desconto = desconto;
        this.valorTotalCobrado = valorTotalCobrado;
        this.dataValidadeCalculo = dataValidadeCalculo;
    }

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
    public DateTime getDataValidadeCalculo() {
        return dataValidadeCalculo;
    }

    /**
     * Define o atributo dataValidadeCalculo
     */
    public void setDataValidadeCalculo(DateTime dataValidadeCalculo) {
        this.dataValidadeCalculo = dataValidadeCalculo;
    }

}
