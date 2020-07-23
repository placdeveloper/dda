package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ConsultaTarifasDDAPagasDto é responsável por
 * 
 * @author rodrigo.neri
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaTarifasDDAPagasDTO")
public class ConsultaTarifasDDAPagasDto extends BancoobDto {

    private static final long serialVersionUID = 3569462441418984344L;

    // Resultado da consulta

    private Long idRateioDDALancamento;
    private Long idRateioDDA;
    private DateTimeDB dataConciliacao;
    private String numCooperativa;
    private String descEventoTarifavel;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private Long numContaCorrenteDebitada;
    private Integer numLote;

    // Dados para consulta

    private Integer idInstituicaoCentral;
    private String numCooperativaCentral;

    private Integer idInstituicaoSingular;
    private String numCooperativaSingular;

    private DateTimeDB dataConciliacaoInicial;
    private DateTimeDB dataConciliacaoFinal;

    public ConsultaTarifasDDAPagasDto() {
    }

    public ConsultaTarifasDDAPagasDto(Long idRateioDDALancamento, Long idRateioDDA, Date dataConciliacao, String numCooperativa, String descEventoTarifavel, Integer quantidade,
            BigDecimal valorUnitario, BigDecimal valorTotal, Long numContaCorrenteDebitada, Integer numLote) {
        this.idRateioDDALancamento = idRateioDDALancamento;
        this.idRateioDDA = idRateioDDA;
        this.dataConciliacao = dataConciliacao != null ? new DateTimeDB(dataConciliacao.getTime()) : null;
        this.numCooperativa = numCooperativa != null ? numCooperativa : "0001";
        this.descEventoTarifavel = descEventoTarifavel;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
        this.numContaCorrenteDebitada = numContaCorrenteDebitada;
        this.numLote = numLote;
    }

    /**
     * @return o atributo idRateioDDALancamento
     */
    public Long getIdRateioDDALancamento() {
        return idRateioDDALancamento;
    }

    /**
     * Define o atributo idRateioDDALancamento
     */
    public void setIdRateioDDALancamento(Long idRateioDDALancamento) {
        this.idRateioDDALancamento = idRateioDDALancamento;
    }

    /**
     * @return o atributo idRateioDDA
     */
    public Long getIdRateioDDA() {
        return idRateioDDA;
    }

    /**
     * Define o atributo idRateioDDA
     */
    public void setIdRateioDDA(Long idRateioDDA) {
        this.idRateioDDA = idRateioDDA;
    }

    /**
     * @return o atributo dataConciliacao
     */
    public DateTimeDB getDataConciliacao() {
        return dataConciliacao;
    }

    /**
     * Define o atributo dataConciliacao
     */
    public void setDataConciliacao(DateTimeDB dataConciliacao) {
        this.dataConciliacao = dataConciliacao;
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

    /**
     * @return o atributo descEventoTarifavel
     */
    public String getDescEventoTarifavel() {
        return descEventoTarifavel;
    }

    /**
     * Define o atributo descEventoTarifavel
     */
    public void setDescEventoTarifavel(String descEventoTarifavel) {
        this.descEventoTarifavel = descEventoTarifavel;
    }

    /**
     * @return o atributo quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define o atributo quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
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

    /**
     * @return o atributo numContaCorrenteDebitada
     */
    public Long getNumContaCorrenteDebitada() {
        return numContaCorrenteDebitada;
    }

    /**
     * Define o atributo numContaCorrenteDebitada
     */
    public void setNumContaCorrenteDebitada(Long numContaCorrenteDebitada) {
        this.numContaCorrenteDebitada = numContaCorrenteDebitada;
    }

    /**
     * @return o atributo numLote
     */
    public Integer getNumLote() {
        return numLote;
    }

    /**
     * Define o atributo numLote
     */
    public void setNumLote(Integer numLote) {
        this.numLote = numLote;
    }

    /**
     * @return o atributo idInstituicaoCentral
     */
    public Integer getIdInstituicaoCentral() {
        return idInstituicaoCentral;
    }

    /**
     * Define o atributo idInstituicaoCentral
     */
    public void setIdInstituicaoCentral(Integer idInstituicaoCentral) {
        this.idInstituicaoCentral = idInstituicaoCentral;
    }

    /**
     * @return o atributo numCooperativaCentral
     */
    public String getNumCooperativaCentral() {
        return numCooperativaCentral;
    }

    /**
     * Define o atributo numCooperativaCentral
     */
    public void setNumCooperativaCentral(String numCooperativaCentral) {
        this.numCooperativaCentral = numCooperativaCentral;
    }

    /**
     * @return o atributo idInstituicaoSingular
     */
    public Integer getIdInstituicaoSingular() {
        return idInstituicaoSingular;
    }

    /**
     * Define o atributo idInstituicaoSingular
     */
    public void setIdInstituicaoSingular(Integer idInstituicaoSingular) {
        this.idInstituicaoSingular = idInstituicaoSingular;
    }

    /**
     * @return o atributo numCooperativaSingular
     */
    public String getNumCooperativaSingular() {
        return numCooperativaSingular;
    }

    /**
     * Define o atributo numCooperativaSingular
     */
    public void setNumCooperativaSingular(String numCooperativaSingular) {
        this.numCooperativaSingular = numCooperativaSingular;
    }

    /**
     * @return o atributo dataConciliacaoInicial
     */
    public DateTimeDB getDataConciliacaoInicial() {
        return dataConciliacaoInicial;
    }

    /**
     * Define o atributo dataConciliacaoInicial
     */
    public void setDataConciliacaoInicial(DateTimeDB dataConciliacaoInicial) {
        this.dataConciliacaoInicial = dataConciliacaoInicial;
    }

    /**
     * @return o atributo dataConciliacaoFinal
     */
    public DateTimeDB getDataConciliacaoFinal() {
        return dataConciliacaoFinal;
    }

    /**
     * Define o atributo dataConciliacaoFinal
     */
    public void setDataConciliacaoFinal(DateTimeDB dataConciliacaoFinal) {
        this.dataConciliacaoFinal = dataConciliacaoFinal;
    }

}
