/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         EfetivarLancamentosCCODto.java
 * Data Criação:    Jan 22, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * RateioCreditoLancamentoCCODto
 * 
 * @author Danilo.Barros
 */
public class RateioCreditoLancamentoCCODto extends BancoobDto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idRateioDDA;
    private Integer idInstituicao;
    private String numCooperativa;
    private Date dataMovimentoBancoob;
    private Long idRateioDDALancamento;
    private Long codSituacaoRateioLancamento;
    private Long numContaLancamentoCCO;
    private Integer idInstituicaoTransferenciaDebito;
    
    

    /**
     * 
     */
    public RateioCreditoLancamentoCCODto() {
        super();
    }

    /**
     * @param idRateioDDA
     * @param idInstituicao
     * @param numCooperativa
     */
    public RateioCreditoLancamentoCCODto(Long idRateioDDA, Integer idInstituicao, String numCooperativa) {
        super();
        this.idRateioDDA = idRateioDDA;
        this.idInstituicao = idInstituicao;
        this.numCooperativa = numCooperativa;
    }

    /**
     * @param idRateioDDA
     * @param idInstituicao
     * @param numCooperativa
     * @param dataMovimentoBancoob
     */
    public RateioCreditoLancamentoCCODto(Long idRateioDDA, Integer idInstituicao, String numCooperativa, Date dataMovimentoBancoob, Long codSituacaoRateioLancamento) {
        super();
        this.idRateioDDA = idRateioDDA;
        this.idInstituicao = idInstituicao;
        this.numCooperativa = numCooperativa;
        this.dataMovimentoBancoob = dataMovimentoBancoob;
        this.codSituacaoRateioLancamento = codSituacaoRateioLancamento;
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
     * @return o atributo idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
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
     * @return o atributo dataMovimentoBancoob
     */
    public Date getDataMovimentoBancoob() {
        return dataMovimentoBancoob;
    }

    /**
     * Define o atributo dataMovimentoBancoob
     */
    public void setDataMovimentoBancoob(Date dataMovimentoBancoob) {
        this.dataMovimentoBancoob = dataMovimentoBancoob;
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
     * @return o atributo codSituacaoRateioLancamento
     */
    public Long getCodSituacaoRateioLancamento() {
        return codSituacaoRateioLancamento;
    }

    /**
     * Define o atributo codSituacaoRateioLancamento
     */
    public void setCodSituacaoRateioLancamento(Long codSituacaoRateioLancamento) {
        this.codSituacaoRateioLancamento = codSituacaoRateioLancamento;
    }

	/**
	 * @return the numContaLancamentoCCO
	 */
	public Long getNumContaLancamentoCCO() {
		return numContaLancamentoCCO;
	}

	/**
	 * @param numContaLancamentoCCO the numContaLancamentoCCO to set
	 */
	public void setNumContaLancamentoCCO(Long numContaLancamentoCCO) {
		this.numContaLancamentoCCO = numContaLancamentoCCO;
	}

	/**
	 * @return the idInstituicaoTransferenciaDebito
	 */
	public Integer getIdInstituicaoTransferenciaDebito() {
		return idInstituicaoTransferenciaDebito;
	}

	/**
	 * @param idInstituicaoTransferenciaDebito the idInstituicaoTransferenciaDebito to set
	 */
	public void setIdInstituicaoTransferenciaDebito(
			Integer idInstituicaoTransferenciaDebito) {
		this.idInstituicaoTransferenciaDebito = idInstituicaoTransferenciaDebito;
	}

}
