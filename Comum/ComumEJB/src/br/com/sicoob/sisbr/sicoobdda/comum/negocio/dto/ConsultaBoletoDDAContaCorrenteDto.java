package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * ConsultaBoletoDDAContaCorrenteDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
@SuppressWarnings("serial")
public class ConsultaBoletoDDAContaCorrenteDto extends BancoobDto {

    private Integer numeroCooperativa;

    private Integer numeroCooperativaCartao;

    private BigDecimal contaCorrente;

    private Integer codSituacao;

    private DateTimeDB dataInicial;

    private DateTimeDB dataFinal;

    /**
     * @return o atributo numeroCooperativa
     */
    public Integer getNumeroCooperativa() {
        return numeroCooperativa;
    }

    /**
     * Define o atributo numeroCooperativa
     */
    public void setNumeroCooperativa(Integer numeroCooperativa) {
        this.numeroCooperativa = numeroCooperativa;
    }

    /**
     * @return numeroCooperativaCartao
     */
    public Integer getNumeroCooperativaCartao() {
        return numeroCooperativaCartao;
    }

    /**
     * @param numeroCooperativaCartao
     */
    public void setNumeroCooperativaCartao(Integer numeroCooperativaCartao) {
        this.numeroCooperativaCartao = numeroCooperativaCartao;
    }

    /**
     * @return o atributo contaCorrente
     */
    public BigDecimal getContaCorrente() {
        return contaCorrente;
    }

    /**
     * Define o atributo contaCorrente
     */
    public void setContaCorrente(BigDecimal contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    /**
     * @return o atributo codSituacao
     */
    public Integer getCodSituacao() {
        return codSituacao;
    }

    /**
     * Define o atributo codSituacao
     */
    public void setCodSituacao(Integer codSituacao) {
        this.codSituacao = codSituacao;
    }

    /**
     * @return o atributo dataInicial
     */
    public DateTimeDB getDataInicial() {
        return dataInicial;
    }

    /**
     * Define o atributo dataInicial
     */
    public void setDataInicial(DateTimeDB dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return o atributo dataFinal
     */
    public DateTimeDB getDataFinal() {
        return dataFinal;
    }

    /**
     * Define o atributo dataFinal
     */
    public void setDataFinal(DateTimeDB dataFinal) {
        this.dataFinal = dataFinal;
    }

}
