package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TipoBaixaOperacional
 * 
 * @author george.santos
 */
@Entity
@Table(name = "TIPOBAIXAOPERACIONAL", schema = "DDA")
public class TipoBaixaOperacional extends SicoobDDAEntidade {

    private static final long serialVersionUID = -7451206216292302599L;

    public static final short BAIXA_OPERACIONAL_INTEGRAL_INTERBANCARIA = 0;
    public static final short BAIXA_OPERACIONAL_INTEGRAL_INTRABANCARIA = 1;
    public static final short BAIXA_OPERACIONAL_PARCIAL_INTRABANCARIA = 2;
    public static final short BAIXA_OPERACIONAL_PARCIAL_INTERBANCARIA = 3;

    @Id
    @Column(name = "CODTIPOBAIXAOPERACIONAL", unique = true, nullable = false)
    private Short codTipoBaixaOperacional;

    private String descTipoBaixaOperacional;

    /**
     * @return o atributo codTipoBaixaOperacional
     */
    public Short getCodTipoBaixaOperacional() {
        return codTipoBaixaOperacional;
    }

    /**
     * Define o atributo codTipoBaixaOperacional
     */
    public void setCodTipoBaixaOperacional(Short codTipoBaixaOperacional) {
        this.codTipoBaixaOperacional = codTipoBaixaOperacional;
    }

    /**
     * @return o atributo descTipoBaixaOperacional
     */
    public String getDescTipoBaixaOperacional() {
        return descTipoBaixaOperacional;
    }

    /**
     * Define o atributo descTipoBaixaOperacional
     */
    public void setDescTipoBaixaOperacional(String descTipoBaixaOperacional) {
        this.descTipoBaixaOperacional = descTipoBaixaOperacional;
    }

}
