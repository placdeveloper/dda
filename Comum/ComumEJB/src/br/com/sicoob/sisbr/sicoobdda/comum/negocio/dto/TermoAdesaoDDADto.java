package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * TermoAdesaoDDADto
 * 
 * @author George.Silva
 */
public class TermoAdesaoDDADto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String descTermo;
    private String descOuvidoria;
    private String descTerminal;
    private DateTimeDB dataEvento;
    private String descTerminalCriacao;

    /**
     * @return o atributo descTermo
     */
    public String getDescTermo() {
        return descTermo;
    }

    /**
     * Define o atributo descTermo
     */
    public void setDescTermo(String descTermo) {
        this.descTermo = descTermo;
    }

    /**
     * @return o atributo descOuvidoria
     */
    public String getDescOuvidoria() {
        return descOuvidoria;
    }

    /**
     * Define o atributo descOuvidoria
     */
    public void setDescOuvidoria(String descOuvidoria) {
        this.descOuvidoria = descOuvidoria;
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
     * @return o atributo descTerminalCriacao
     */
    public String getDescTerminalCriacao() {
        return descTerminalCriacao;
    }

    /**
     * Define o atributo descTerminalCriacao
     */
    public void setDescTerminalCriacao(String descTerminalCriacao) {
        this.descTerminalCriacao = descTerminalCriacao;
    }
}
