package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * LancamentosTarifasDDADto é responsável por
 * 
 * @author rodrigo.neri
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LancamentosTarifasDDADTO")
public class LancamentosTarifasDDADto extends BancoobDto {

    private static final long serialVersionUID = 3569462441418984344L;

    private Long idEventoConsolidadoDDA;
    private Long idEventoConsolidadoDDADetalhe;
    private DateTimeDB dataEvento;
    private Integer quantidadeApurada;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

    /**
     * 
     */
    public LancamentosTarifasDDADto() {
    }

    /**
     * @param idEventoConsolidadoDDA
     * @param idEventoConsolidadoDDADetalhe
     * @param dataEvento
     * @param quantidadeApurada
     * @param valorUnitario
     * @param valorTotal
     */
    public LancamentosTarifasDDADto(Long idEventoConsolidadoDDA, Long idEventoConsolidadoDDADetalhe, Date dataEvento, Integer quantidadeApurada, BigDecimal valorUnitario,
            BigDecimal valorTotal) {
        this.idEventoConsolidadoDDA = idEventoConsolidadoDDA;
        this.idEventoConsolidadoDDADetalhe = idEventoConsolidadoDDADetalhe;
        this.dataEvento = dataEvento != null ? new DateTimeDB(dataEvento.getTime()) : null;
        this.quantidadeApurada = quantidadeApurada;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    /**
     * @return o atributo idEventoConsolidadoDDA
     */
    public Long getIdEventoConsolidadoDDA() {
        return idEventoConsolidadoDDA;
    }

    /**
     * Define o atributo idEventoConsolidadoDDA
     */
    public void setIdEventoConsolidadoDDA(Long idEventoConsolidadoDDA) {
        this.idEventoConsolidadoDDA = idEventoConsolidadoDDA;
    }

    /**
     * @return o atributo idEventoConsolidadoDDADetalhe
     */
    public Long getIdEventoConsolidadoDDADetalhe() {
        return idEventoConsolidadoDDADetalhe;
    }

    /**
     * Define o atributo idEventoConsolidadoDDADetalhe
     */
    public void setIdEventoConsolidadoDDADetalhe(Long idEventoConsolidadoDDADetalhe) {
        this.idEventoConsolidadoDDADetalhe = idEventoConsolidadoDDADetalhe;
    }

    /**
     * @return o atributo dataEvento
     */
    public DateTimeDB getDataEvento() {
        return dataEvento;
    }

    /**
     * Define o atributo dataEvento
     */
    public void setDataEvento(DateTimeDB dataEvento) {
        this.dataEvento = dataEvento;
    }

    /**
     * @return o atributo quantidadeApurada
     */
    public Integer getQuantidadeApurada() {
        return quantidadeApurada;
    }

    /**
     * Define o atributo quantidadeApurada
     */
    public void setQuantidadeApurada(Integer quantidadeApurada) {
        this.quantidadeApurada = quantidadeApurada;
    }

    /**
     * @return o atributo valorUnitario
     */
    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Define o atributo valorUnitario
     */
    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return o atributo valorTotal
     */
    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    /**
     * Define o atributo valorTotal
     */
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

}
