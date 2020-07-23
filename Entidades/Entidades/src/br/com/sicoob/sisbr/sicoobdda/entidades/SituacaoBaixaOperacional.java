package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SituacaoBaixaOperacional
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "SITUACAOBAIXAOPERACIONAL", schema = "DDA")
public class SituacaoBaixaOperacional extends SicoobDDAEntidade {

    private static final long serialVersionUID = 0L;

    public static final String ATIVA = "A";
    public static final String BAIXADA_POR_NAO_CONFIRMACAO_EFETIVA = "B";
    public static final String CANCELADA_PELO_PARTICIPANTE = "C";

    @Id
    @Column(name = "CODSITUACAOBAIXAOPERACIONAL", unique = true, nullable = false)
    private String codSituacaoBaixaOperacional;

    @Column(name = "DESCSITUACAOBAIXAOPERACIONAL", nullable = false)
    private String descSituacaoBaixaOperacional;

    /**
     * @return the codSituacaoBaixaOperacional
     */
    public String getCodSituacaoBaixaOperacional() {
        return codSituacaoBaixaOperacional;
    }

    /**
     * @param codSituacaoBaixaOperacional the codSituacaoBaixaOperacional to set
     */
    public void setCodSituacaoBaixaOperacional(String codSituacaoBaixaOperacional) {
        this.codSituacaoBaixaOperacional = codSituacaoBaixaOperacional;
    }

    /**
     * @return the descSituacaoBaixaOperacional
     */
    public String getDescSituacaoBaixaOperacional() {
        return descSituacaoBaixaOperacional;
    }

    /**
     * @param descSituacaoBaixaOperacional the descSituacaoBaixaOperacional to set
     */
    public void setDescSituacaoBaixaOperacional(String descSituacaoBaixaOperacional) {
        this.descSituacaoBaixaOperacional = descSituacaoBaixaOperacional;
    }

}
