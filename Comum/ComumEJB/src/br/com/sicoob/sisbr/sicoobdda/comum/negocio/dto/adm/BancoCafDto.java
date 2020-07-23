/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm
 * Arquivo:         BancoCafDto.java
 * Data Criação:    Feb 21, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * BancoCafDto é responsável por
 * 
 * @author Danilo.Barros
 */
@Conversor(DTO="br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BancoCafDTO")
public class BancoCafDto extends BancoobDto {
    private static final long serialVersionUID = 1L;
    private Short numBanco;
    private String descBanco;
    private String numCodISPB;
    private Boolean bolAtividade;

    /**
     * @param numBanco
     * @param descBanco
     * @param numCodISPB
     * @param bolAtividade
     */
    public BancoCafDto(Short numBanco, String descBanco, String numCodISPB, Boolean bolAtividade) {
        this.numBanco = numBanco;
        this.descBanco = descBanco;
        this.numCodISPB = numCodISPB;
        this.bolAtividade = bolAtividade;
    }

    /**
     * 
     */
    public BancoCafDto() {
    }

    /**
     * @return o atributo numBanco
     */
    public Short getNumBanco() {
        return numBanco;
    }

    /**
     * @return o atributo descBanco
     */
    public String getDescBanco() {
        return descBanco;
    }

    /**
     * @return o atributo numCodISPB
     */
    public String getNumCodISPB() {
        return numCodISPB;
    }

    /**
     * @return o atributo bolAtividade
     */
    public Boolean getBolAtividade() {
        return bolAtividade;
    }

    /**
     * Define o atributo numBanco
     */
    public void setNumBanco(Short numBanco) {
        this.numBanco = numBanco;
    }

    /**
     * Define o atributo descBanco
     */
    public void setDescBanco(String descBanco) {
        this.descBanco = descBanco;
    }

    /**
     * Define o atributo numCodISPB
     */
    public void setNumCodISPB(String numCodISPB) {
        this.numCodISPB = numCodISPB;
    }

    /**
     * Define o atributo bolAtividade
     */
    public void setBolAtividade(Boolean bolAtividade) {
        this.bolAtividade = bolAtividade;
    }

}
