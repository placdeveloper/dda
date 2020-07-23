package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoDescontoDto")
public class MensagemDDABoletoDescontoDTO extends BancoobDTO {

    private Long idMensagemDDABoletoDesconto;
    private Long idMensagemDDA;
    private String codTipoDesconto;
    private Date dataDesconto;
    private BigDecimal valorDesconto;

    /**
     * 
     */
    public MensagemDDABoletoDescontoDTO() {
        super();
    }

    /**
     * @param idMensagemDDABoletoDesconto
     * @param idMensagemDDA
     * @param codTipoDesconto
     * @param dataDesconto
     * @param valorDesconto
     */
    public MensagemDDABoletoDescontoDTO(Long idMensagemDDABoletoDesconto, Long idMensagemDDA, String codTipoDesconto, Date dataDesconto, BigDecimal valorDesconto) {
        this.idMensagemDDABoletoDesconto = idMensagemDDABoletoDesconto;
        this.idMensagemDDA = idMensagemDDA;
        this.codTipoDesconto = codTipoDesconto;
        this.dataDesconto = dataDesconto;
        this.valorDesconto = valorDesconto;
    }

    /**
     * @return o atributo idMensagemDDABoletoDesconto
     */
    public Long getIdMensagemDDABoletoDesconto() {
        return idMensagemDDABoletoDesconto;
    }

    /**
     * Define o atributo idMensagemDDABoletoDesconto
     */
    public void setIdMensagemDDABoletoDesconto(Long idMensagemDDABoletoDesconto) {
        this.idMensagemDDABoletoDesconto = idMensagemDDABoletoDesconto;
    }

    /**
     * @return o atributo idMensagemDDA
     */
    public Long getIdMensagemDDA() {
        return idMensagemDDA;
    }

    /**
     * Define o atributo idMensagemDDA
     */
    public void setIdMensagemDDA(Long idMensagemDDA) {
        this.idMensagemDDA = idMensagemDDA;
    }

    /**
     * @return o atributo codTipoDesconto
     */
    public String getCodTipoDesconto() {
        return codTipoDesconto;
    }

    /**
     * Define o atributo codTipoDesconto
     */
    public void setCodTipoDesconto(String codTipoDesconto) {
        this.codTipoDesconto = codTipoDesconto;
    }

    /**
     * @return o atributo dataDesconto
     */
    public Date getDataDesconto() {
        return dataDesconto;
    }

    /**
     * Define o atributo dataDesconto
     */
    public void setDataDesconto(Date dataDesconto) {
        this.dataDesconto = dataDesconto;
    }

    /**
     * @return o atributo valorDesconto
     */
    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    /**
     * Define o atributo valorDesconto
     */
    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

}
