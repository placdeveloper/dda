package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * ConsultaBoletoDDAContaCorrenteAPIDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class ConsultaBoletoDDAContaCorrenteAPIDto extends BancoobDto {

    private static final long serialVersionUID = 7756052104125360460L;

    private Integer numeroCooperativa;

    private Integer numeroCooperativaCartao;

    private BigDecimal contaCorrente;

    private Integer codSituacao;

    private DateTime dataInicial;

    private DateTime dataFinal;

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
    public DateTime getDataInicial() {
        return dataInicial;
    }

    /**
     * Define o atributo dataInicial
     */
    public void setDataInicial(DateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return o atributo dataFinal
     */
    public DateTime getDataFinal() {
        return dataFinal;
    }

    /**
     * Define o atributo dataFinal
     */
    public void setDataFinal(DateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

}
