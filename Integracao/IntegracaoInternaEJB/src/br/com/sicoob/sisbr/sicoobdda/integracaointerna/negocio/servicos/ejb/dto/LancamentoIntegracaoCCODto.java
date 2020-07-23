/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-integracao-interna-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto
 * Arquivo:         LancamentoRateioCCODto.java
 * Data Criação:    Jan 24, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto;

/**
 * LancamentoRateioCCODto
 * 
 * @author Danilo.Barros
 */
public class LancamentoIntegracaoCCODto {
    /**
     * 
     */
    public LancamentoIntegracaoCCODto() {
        super();
    }

    private LancamentoIntegracaoDto lancamentoIntegracaoDto;
    private LancamentoIntegracaoRetDto lancamentoIntegracaoRetDto;

    /**
     * @return o atributo lancamentoIntegracaoDto
     */
    public LancamentoIntegracaoDto getLancamentoIntegracaoDto() {
        return lancamentoIntegracaoDto;
    }

    /**
     * Define o atributo lancamentoIntegracaoDto
     */
    public void setLancamentoIntegracaoDto(LancamentoIntegracaoDto lancamentoIntegracaoDto) {
        this.lancamentoIntegracaoDto = lancamentoIntegracaoDto;
    }

    /**
     * @return o atributo lancamentoIntegracaoRetDto
     */
    public LancamentoIntegracaoRetDto getLancamentoIntegracaoRetDto() {
        return lancamentoIntegracaoRetDto;
    }

    /**
     * Define o atributo lancamentoIntegracaoRetDto
     */
    public void setLancamentoIntegracaoRetDto(LancamentoIntegracaoRetDto lancamentoIntegracaoRetDto) {
        this.lancamentoIntegracaoRetDto = lancamentoIntegracaoRetDto;
    }

}