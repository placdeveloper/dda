/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         BancoCafDTO.java
 * Data Cria��o:    Feb 21, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.io.Serializable;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * BancoCafDTO � respons�vel por
 * 
 * @author Danilo.Barros
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.BancoCafDto")
public class BancoCafDTO extends BancoobDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Short numBanco;
    private String descBanco;
    private String numCodISPB;
    private Boolean bolAtividade;

    /**
     * 
     */
    public BancoCafDTO() {
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
