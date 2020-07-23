package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DDATerceiroDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class DDATerceiroDto extends BancoobDto {

    private static final long serialVersionUID = 8880434939909422774L;

    private Integer numCliente;

    private BigDecimal numConta;

    private Integer codCanal;

    private String descTerminal;

    private Integer numCoopCartao;

    private Integer numCooperativa;

    private Integer numPac;

    private Boolean bolOuvidoria;

    private Long numIdentDDA;

    /**
     * Tipo de pessoa: F ou J
     */
    private Character tipoPessoaTerc;

    private String numCpfCnpj;

    /**
     * Tipo de operação: I: Incluir | E: Excluir | A: Alterar
     */
    private Character indTerceiro;

    /**
     * @return o atributo numCliente
     */
    public Integer getNumCliente() {
        return numCliente;
    }

    /**
     * Define o atributo numCliente
     */
    public void setNumCliente(Integer numCliente) {
        this.numCliente = numCliente;
    }

    /**
     * @return o atributo numConta
     */
    public BigDecimal getNumConta() {
        return numConta;
    }

    /**
     * Define o atributo numConta
     */
    public void setNumConta(BigDecimal numConta) {
        this.numConta = numConta;
    }

    /**
     * @return o atributo codCanal
     */
    public Integer getCodCanal() {
        return codCanal;
    }

    /**
     * Define o atributo codCanal
     */
    public void setCodCanal(Integer codCanal) {
        this.codCanal = codCanal;
    }

    /**
     * @return o atributo descTerminal
     */
    public String getDescTerminal() {
        return descTerminal;
    }

    /**
     * Define o atributo descTerminal
     */
    public void setDescTerminal(String descTerminal) {
        this.descTerminal = descTerminal;
    }

    /**
     * @return o atributo numCoopCartao
     */
    public Integer getNumCoopCartao() {
        return numCoopCartao;
    }

    /**
     * Define o atributo numCoopCartao
     */
    public void setNumCoopCartao(Integer numCoopCartao) {
        this.numCoopCartao = numCoopCartao;
    }

    /**
     * @return o atributo numCooperativa
     */
    public Integer getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(Integer numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return o atributo numPac
     */
    public Integer getNumPac() {
        return numPac;
    }

    /**
     * Define o atributo numPac
     */
    public void setNumPac(Integer numPac) {
        this.numPac = numPac;
    }

    /**
     * @return o atributo bolOuvidoria
     */
    public Boolean getBolOuvidoria() {
        return bolOuvidoria;
    }

    /**
     * Define o atributo bolOuvidoria
     */
    public void setBolOuvidoria(Boolean bolOuvidoria) {
        this.bolOuvidoria = bolOuvidoria;
    }

    /**
     * @return o atributo numIdentDDA
     */
    public Long getNumIdentDDA() {
        return numIdentDDA;
    }

    /**
     * Define o atributo numIdentDDA
     */
    public void setNumIdentDDA(Long numIdentDDA) {
        this.numIdentDDA = numIdentDDA;
    }

    /**
     * @return o atributo tipoPessoaTerc
     */
    public Character getTipoPessoaTerc() {
        return tipoPessoaTerc;
    }

    /**
     * Define o atributo tipoPessoaTerc
     */
    public void setTipoPessoaTerc(Character tipoPessoaTerc) {
        this.tipoPessoaTerc = tipoPessoaTerc;
    }

    /**
     * @return o atributo numCpfCnpj
     */
    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    /**
     * Define o atributo numCpfCnpj
     */
    public void setNumCpfCnpj(String numCpfCnpj) {
        this.numCpfCnpj = numCpfCnpj;
    }

    /**
     * @return o atributo indTerceiro
     */
    public Character getIndTerceiro() {
        return indTerceiro;
    }

    /**
     * Define o atributo indTerceiro
     */
    public void setIndTerceiro(Character indTerceiro) {
        this.indTerceiro = indTerceiro;
    }
}
