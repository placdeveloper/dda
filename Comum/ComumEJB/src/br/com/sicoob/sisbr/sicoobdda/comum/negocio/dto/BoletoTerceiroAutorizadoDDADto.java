package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * BoletoTerceiroAutorizadoDDADto é responsável por
 * 
 * @author George.santos
 */
public class BoletoTerceiroAutorizadoDDADto extends BancoobDto {

    private static final long serialVersionUID = -7233918615348979028L;

    private Integer bolComCheck;

    private String numIdentificadorBoletoCip;

    private Character codTipoPessoaPagador;

    private String numCpfCnpjPagador;

    private Character codTipoPessoaTerceiro;

    private String numCpfCnpjTerceiro;

    private String operacao;

    private String situacao;

    /**
     * @return o atributo bolComCheck
     */
    public Integer getBolComCheck() {
        return bolComCheck;
    }

    /**
     * Define o atributo bolComCheck
     */
    public void setBolComCheck(Integer bolComCheck) {
        this.bolComCheck = bolComCheck;
    }

    /**
     * @return o atributo numIdentificadorBoletoCip
     */
    public String getNumIdentificadorBoletoCip() {
        return numIdentificadorBoletoCip;
    }

    /**
     * Define o atributo numIdentificadorBoletoCip
     */
    public void setNumIdentificadorBoletoCip(String numIdentificadorBoletoCip) {
        this.numIdentificadorBoletoCip = numIdentificadorBoletoCip;
    }

    /**
     * @return o atributo codTipoPessoaPagador
     */
    public Character getCodTipoPessoaPagador() {
        return codTipoPessoaPagador;
    }

    /**
     * Define o atributo codTipoPessoaPagador
     */
    public void setCodTipoPessoaPagador(Character codTipoPessoaPagador) {
        this.codTipoPessoaPagador = codTipoPessoaPagador;
    }

    /**
     * @return o atributo numCpfCnpjPagador
     */
    public String getNumCpfCnpjPagador() {
        return numCpfCnpjPagador;
    }

    /**
     * Define o atributo numCpfCnpjPagador
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * @return o atributo codTipoPessoaTerceiro
     */
    public Character getCodTipoPessoaTerceiro() {
        return codTipoPessoaTerceiro;
    }

    /**
     * Define o atributo codTipoPessoaTerceiro
     */
    public void setCodTipoPessoaTerceiro(Character codTipoPessoaTerceiro) {
        this.codTipoPessoaTerceiro = codTipoPessoaTerceiro;
    }

    /**
     * @return o atributo numCpfCnpjTerceiro
     */
    public String getNumCpfCnpjTerceiro() {
        return numCpfCnpjTerceiro;
    }

    /**
     * Define o atributo numCpfCnpjTerceiro
     */
    public void setNumCpfCnpjTerceiro(String numCpfCnpjTerceiro) {
        this.numCpfCnpjTerceiro = numCpfCnpjTerceiro;
    }

    /**
     * @return o atributo operacao
     */
    public String getOperacao() {
        return operacao;
    }

    /**
     * Define o atributo operacao
     */
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    /**
     * @return o atributo situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * Define o atributo situacao
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
