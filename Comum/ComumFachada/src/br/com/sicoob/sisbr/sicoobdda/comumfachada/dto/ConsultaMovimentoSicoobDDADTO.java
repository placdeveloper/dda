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
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaMovimentoSicoobDDADto")
public class ConsultaMovimentoSicoobDDADTO extends BancoobDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numCodBarras;
    private String nomeBeneficiario;
    private String cpfCnpjBeneficiario;
    private DateTime dataEntrada;
    private DateTime dataVencimento;
    private BigDecimal valor;
    private DateTime dataEvento;
    private Long idEventoConsolidadoDDA;
    private Long idEventoConsolidadoDDADetalhe;
    private String numCooperativa;

    /**
     * @return o atributo numCodBarras
     */
    public String getNumCodBarras() {
        return numCodBarras;
    }

    /**
     * Define o atributo numCodBarras
     */
    public void setNumCodBarras(String numCodBarras) {
        this.numCodBarras = numCodBarras;
    }

    /**
     * @return o atributo nomeBeneficiario
     */
    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    /**
     * Define o atributo nomeBeneficiario
     */
    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    /**
     * @return o atributo cpfCnpjBeneficiario
     */
    public String getCpfCnpjBeneficiario() {
        return cpfCnpjBeneficiario;
    }

    /**
     * Define o atributo cpfCnpjBeneficiario
     */
    public void setCpfCnpjBeneficiario(String cpfCnpjBeneficiario) {
        this.cpfCnpjBeneficiario = cpfCnpjBeneficiario;
    }

    /**
     * @return o atributo dataEntrada
     */
    public DateTime getDataEntrada() {
        return dataEntrada;
    }

    /**
     * Define o atributo dataEntrada
     */
    public void setDataEntrada(DateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * @return o atributo dataVencimento
     */
    public DateTime getDataVencimento() {
        return dataVencimento;
    }

    /**
     * Define o atributo dataVencimento
     */
    public void setDataVencimento(DateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return o atributo valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * Define o atributo valor
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
     * @return o atributo numCooperativa
     */
    public String getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(String numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

}
