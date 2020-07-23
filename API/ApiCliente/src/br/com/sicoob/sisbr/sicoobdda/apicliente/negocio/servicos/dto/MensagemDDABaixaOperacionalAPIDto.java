package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * MensagemDDABaixaOperacionalAPIDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class MensagemDDABaixaOperacionalAPIDto extends BancoobDto {

    private static final long serialVersionUID = 1315476263667586558L;

    private String numCodigoBarra;
    private String codTipoPessoaPortador;
    private String numCpfCnpjPortador;
    private BigDecimal valorBaixa;
    private Long numRefAtualCadBoleto;

    /**
     * @return o atributo numCodigoBarra
     */
    public String getNumCodigoBarra() {
        return numCodigoBarra;
    }

    /**
     * Define o atributo numCodigoBarra
     */
    public void setNumCodigoBarra(String numCodigoBarra) {
        this.numCodigoBarra = numCodigoBarra;
    }

    /**
     * @return o atributo codTipoPessoaPortador
     */
    public String getCodTipoPessoaPortador() {
        return codTipoPessoaPortador;
    }

    /**
     * Define o atributo codTipoPessoaPortador
     */
    public void setCodTipoPessoaPortador(String codTipoPessoaPortador) {
        this.codTipoPessoaPortador = codTipoPessoaPortador;
    }

    /**
     * @return o atributo numCpfCnpjPortador
     */
    public String getNumCpfCnpjPortador() {
        return numCpfCnpjPortador;
    }

    /**
     * Define o atributo numCpfCnpjPortador
     */
    public void setNumCpfCnpjPortador(String numCpfCnpjPortador) {
        this.numCpfCnpjPortador = numCpfCnpjPortador;
    }

    /**
     * @return o atributo valorBaixa
     */
    public BigDecimal getValorBaixa() {
        return valorBaixa;
    }

    /**
     * Define o atributo valorBaixa
     */
    public void setValorBaixa(BigDecimal valorBaixa) {
        this.valorBaixa = valorBaixa;
    }

    /**
     * @return o atributo numRefAtualCadBoleto
     */
    public Long getNumRefAtualCadBoleto() {
        return numRefAtualCadBoleto;
    }

    /**
     * Define o atributo numRefAtualCadBoleto
     */
    public void setNumRefAtualCadBoleto(Long numRefAtualCadBoleto) {
        this.numRefAtualCadBoleto = numRefAtualCadBoleto;
    }

}
