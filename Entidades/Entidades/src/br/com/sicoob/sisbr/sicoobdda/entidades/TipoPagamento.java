package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TipoPagamento é responsável por
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "TIPOPAGAMENTO", schema = "DDA")
public class TipoPagamento extends SicoobDDAEntidade {

    private static final long serialVersionUID = -4590967887684087137L;

    public static final short A_VISTA = 1;
    public static final short CONTRA_APRESENTACAO = 2;
    public static final short VENCIMENTO_DETERMINADO = 3;
    public static final short CARNE = 4;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODTIPOPAGAMENTO", unique = true, nullable = false)
    private Short id;

    @Column(nullable = false)
    private String descTipoPagamento;

    /**
     * @return o atributo id
     */
    public Short getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * @return o atributo descTipoPagamento
     */
    public String getDescTipoPagamento() {
        return descTipoPagamento;
    }

    /**
     * Define o atributo descTipoPagamento
     */
    public void setDescTipoPagamento(String descTipoPagamento) {
        this.descTipoPagamento = descTipoPagamento;
    }
}
