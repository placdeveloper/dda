package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TipoBaixaEfetiva
 * 
 * @author george.santos
 */
@Entity
@Table(name = "TIPOBAIXAEFETIVA", schema = "DDA")
public class TipoBaixaEfetiva extends SicoobDDAEntidade {

    private static final long serialVersionUID = -7451206216292302599L;

    public static final String BAIXA_EFETIVA_INTEGRAL_INTERBANCARIA = "0";
    public static final String BAIXA_EFETIVA_INTEGRAL_INTRABANCARIA = "1";
    public static final String BAIXA_EFETIVA_INTEGRAL_SOLICITACAO_CEDENTE = "2";
    public static final String BAIXA_EFETIVA_INTEGRAL_ENVIO_PROTESTO = "3";
    public static final String BAIXA_EFETIVA_INTEGRAL_DECURSO_PRAZO = "4";
    public static final String BAIXA_EFETIVA_PARCIAL_INTRABANCARIA = "5";
    public static final String BAIXA_EFETIVA_PARCIAL_INTERBANCARIA = "6";
    public static final String BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA = "7";

    @Id
    @Column(name = "CODTIPOBAIXAEFETIVA", unique = true, nullable = false)
    private String codTipoBaixaEfetiva;

    private String descTipoBaixaEfetiva;

    /**
     * @return o atributo codTipoBaixaEfetiva
     */
    public String getCodTipoBaixaEfetiva() {
        return codTipoBaixaEfetiva;
    }

    /**
     * Define o atributo codTipoBaixaEfetiva
     */
    public void setCodTipoBaixaEfetiva(String codTipoBaixaEfetiva) {
        this.codTipoBaixaEfetiva = codTipoBaixaEfetiva;
    }

    /**
     * @return o atributo descTipoBaixaEfetiva
     */
    public String getDescTipoBaixaEfetiva() {
        return descTipoBaixaEfetiva;
    }

    /**
     * Define o atributo descTipoBaixaEfetiva
     */
    public void setDescTipoBaixaEfetiva(String descTipoBaixaEfetiva) {
        this.descTipoBaixaEfetiva = descTipoBaixaEfetiva;
    }

}
