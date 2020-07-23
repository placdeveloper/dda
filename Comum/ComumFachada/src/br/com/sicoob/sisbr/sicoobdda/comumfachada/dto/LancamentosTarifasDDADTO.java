package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LancamentosTarifasDDADto")
public class LancamentosTarifasDDADTO extends BancoobDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idEventoConsolidadoDDA;
    private Long idEventoConsolidadoDDADetalhe;
    private DateTime dataEvento;
    private Integer quantidadeApurada;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;

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
    public DateTime getDataEvento() {
        return dataEvento;
    }

    /**
     * Define o atributo dataEvento
     */
    public void setDataEvento(DateTime dataEvento) {
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
