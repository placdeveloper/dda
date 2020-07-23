package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * PagadorEletronicoDDADto
 * 
 * @author George.Silva
 */
/**
 * PagadorEletronicoDDADto é responsável por
 * 
 * @author George.Santos
 */
public class PagadorEletronicoDDADto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Integer numCliente;
    private Integer idPessoaCapes;
    private BigDecimal numConta;
    private Integer codCanal;
    private String descTerminal;
    private Integer numCoopCartao;
    private Integer numCooperativa;
    private Integer numPac;
    private Boolean bolOuvidoria;
    private BigDecimal numIdentDDA;
    private String indAceite;
    private Integer codSitTittulo;
    private Boolean bolControlaTransacao;

    private Integer tipoTermo;
    private Integer tipoRetorno;

    private Integer idEventoDDA;
    private DateTimeDB dataEvento;
    private String descOperacao;
    
    private Integer idRespTitular;
    private String numCpfCnpj;

    /**
     * @param integerUm
     * @param longUm
     * @param integerUm2
     * @param shortUm
     */
    public PagadorEletronicoDDADto(int numCooperativa, long numConta, int tipoRetorno, int codCanal) {
        this.numCooperativa = numCooperativa;
        this.numConta = new BigDecimal(numConta);
        this.tipoRetorno = tipoRetorno;
        this.codCanal = codCanal;
    }

    /**
     * 
     */
    public PagadorEletronicoDDADto() {
    }

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
     * @return o atributo idPessoaCapes
     */
    public Integer getIdPessoaCapes() {
        return idPessoaCapes;
    }

    /**
     * Define o atributo idPessoaCapes
     */
    public void setIdPessoaCapes(Integer idPessoaCapes) {
        this.idPessoaCapes = idPessoaCapes;
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
    public BigDecimal getNumIdentDDA() {
        return numIdentDDA;
    }

    /**
     * Define o atributo numIdentDDA
     */
    public void setNumIdentDDA(BigDecimal numIdentDDA) {
        this.numIdentDDA = numIdentDDA;
    }

    /**
     * @return o atributo indAceite
     */
    public String getIndAceite() {
        return indAceite;
    }

    /**
     * Define o atributo indAceite
     */
    public void setIndAceite(String indAceite) {
        this.indAceite = indAceite;
    }

    /**
     * @return o atributo codSitTittulo
     */
    public Integer getCodSitTittulo() {
        return codSitTittulo;
    }

    /**
     * Define o atributo codSitTittulo
     */
    public void setCodSitTittulo(Integer codSitTittulo) {
        this.codSitTittulo = codSitTittulo;
    }

    /**
     * @return o atributo bolControlaTransacao
     */
    public Boolean getBolControlaTransacao() {
        return bolControlaTransacao;
    }

    /**
     * Define o atributo bolControlaTransacao
     */
    public void setBolControlaTransacao(Boolean bolControlaTransacao) {
        this.bolControlaTransacao = bolControlaTransacao;
    }

    /**
     * @return o atributo tipoTermo
     */
    public Integer getTipoTermo() {
        return tipoTermo;
    }

    /**
     * Define o atributo tipoTermo
     */
    public void setTipoTermo(Integer tipoTermo) {
        this.tipoTermo = tipoTermo;
    }

    /**
     * @return o atributo tipoRetorno
     */
    public Integer getTipoRetorno() {
        return tipoRetorno;
    }

    /**
     * Define o atributo tipoRetorno
     */
    public void setTipoRetorno(Integer tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    /**
     * @return o atributo idEventoDDA
     */
    public Integer getIdEventoDDA() {
        return idEventoDDA;
    }

    /**
     * Define o atributo idEventoDDA
     */
    public void setIdEventoDDA(Integer idEventoDDA) {
        this.idEventoDDA = idEventoDDA;
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
     * @return o atributo descOperacao
     */
    public String getDescOperacao() {
        return descOperacao;
    }

    /**
     * Define o atributo descOperacao
     */
    public void setDescOperacao(String descOperacao) {
        this.descOperacao = descOperacao;
    }

    /**
     * @return idRespTitular
     */
    public Integer getIdRespTitular() {
        return idRespTitular;
    }

    /**
     * @param idRespTitular
     */
    public void setIdRespTitular(Integer idRespTitular) {
        this.idRespTitular = idRespTitular;
    }

    /**
     * @return numCpfCnpj
     */
    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    /**
     * @param numCpfCnpj
     */
    public void setNumCpfCnpj(String numCpfCnpj) {
        this.numCpfCnpj = numCpfCnpj;
    }
}
