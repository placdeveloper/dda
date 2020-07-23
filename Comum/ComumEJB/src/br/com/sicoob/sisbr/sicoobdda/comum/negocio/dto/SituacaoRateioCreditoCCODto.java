/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         SituacaoRateioCreditoCCODto.java
 * Data Criação:    Jan 29, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * SituacaoRateioCreditoCCODto
 * 
 * @author Danilo.Barros
 */
public class SituacaoRateioCreditoCCODto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idRateioDDA;
    private Integer qtdAguardandoEfetivacao;
    private Integer qtdEfetivado;
    private Integer qtdErroEfetivacao;
    private Integer qtdEfetivadoManualmente;
    private Integer qtdEfetivadoComAjuste;

    /**
     * 
     */
    public SituacaoRateioCreditoCCODto() {
        super();
    }

    /**
     * @param idRateioDDA
     * @param qtdAguardandoEfetivacao
     * @param qtdEfetivado
     * @param qtdErroEfetivacao
     * @param qtdEfetivadoManualmente
     * @param qtdEfetivadoComAjuste
     */
    public SituacaoRateioCreditoCCODto(Long idRateioDDA, Integer qtdAguardandoEfetivacao, Integer qtdEfetivado, Integer qtdErroEfetivacao, Integer qtdEfetivadoManualmente,
            Integer qtdEfetivadoComAjuste) {
        super();
        this.idRateioDDA = idRateioDDA;
        this.qtdAguardandoEfetivacao = qtdAguardandoEfetivacao;
        this.qtdEfetivado = qtdEfetivado;
        this.qtdErroEfetivacao = qtdErroEfetivacao;
        this.qtdEfetivadoManualmente = qtdEfetivadoManualmente;
        this.qtdEfetivadoComAjuste = qtdEfetivadoComAjuste;
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
     * @return o atributo qtdAguardandoEfetivacao
     */
    public Integer getQtdAguardandoEfetivacao() {
        return qtdAguardandoEfetivacao;
    }

    /**
     * Define o atributo qtdAguardandoEfetivacao
     */
    public void setQtdAguardandoEfetivacao(Integer qtdAguardandoEfetivacao) {
        this.qtdAguardandoEfetivacao = qtdAguardandoEfetivacao;
    }

    /**
     * @return o atributo qtdEfetivado
     */
    public Integer getQtdEfetivado() {
        return qtdEfetivado;
    }

    /**
     * Define o atributo qtdEfetivado
     */
    public void setQtdEfetivado(Integer qtdEfetivado) {
        this.qtdEfetivado = qtdEfetivado;
    }

    /**
     * @return o atributo qtdEfetivadoManualmente
     */
    public Integer getQtdEfetivadoManualmente() {
        return qtdEfetivadoManualmente;
    }

    /**
     * Define o atributo qtdEfetivadoManualmente
     */
    public void setQtdEfetivadoManualmente(Integer qtdEfetivadoManualmente) {
        this.qtdEfetivadoManualmente = qtdEfetivadoManualmente;
    }

    /**
     * @return o atributo qtdEfetivadoComAjuste
     */
    public Integer getQtdEfetivadoComAjuste() {
        return qtdEfetivadoComAjuste;
    }

    /**
     * Define o atributo qtdEfetivadoComAjuste
     */
    public void setQtdEfetivadoComAjuste(Integer qtdEfetivadoComAjuste) {
        this.qtdEfetivadoComAjuste = qtdEfetivadoComAjuste;
    }

    /**
     * @return o atributo qtdErroEfetivacao
     */
    public Integer getQtdErroEfetivacao() {
        return qtdErroEfetivacao;
    }

    /**
     * Define o atributo qtdErroEfetivacao
     */
    public void setQtdErroEfetivacao(Integer qtdErroEfetivacao) {
        this.qtdErroEfetivacao = qtdErroEfetivacao;
    }

}
