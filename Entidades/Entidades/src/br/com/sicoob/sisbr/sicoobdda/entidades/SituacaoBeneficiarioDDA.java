/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         DDASituacaoBeneficiario.java
 * Data Criação:    Jul 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SituacaoBeneficiarioDDA
 * 
 * @author rafael.silva
 */

@Entity
@Table(name = "SITUACAOBENEFICIARIO", schema = "DDA")
public class SituacaoBeneficiarioDDA extends SicoobDDAEntidade {

    public static final String APTO = "A";
    public static final String EM_ANALISE = "E";
    public static final String INAPTO = "I";
    public static final String SEM_CADASTRO = "S";

    private static final long serialVersionUID = 6736197850009349259L;

    @Id
    @Column(name = "CODSITUACAOBENEFICIARIO", unique = true, nullable = false)
    private String codSituacaoBeneficiario;

    @Column(name = "DESCSITUACAOBENEFICIARIO", nullable = false)
    private String descSituacaoBeneficiario;

    public SituacaoBeneficiarioDDA() {
    }

    public SituacaoBeneficiarioDDA(String codSituacaoBeneficiario) {
        super();
        this.codSituacaoBeneficiario = codSituacaoBeneficiario;
    }

    /**
     * @return o atributo codSituacaoBeneficiario
     */
    public String getCodSituacaoBeneficiario() {
        return codSituacaoBeneficiario;
    }

    /**
     * Define o atributo codSituacaoBeneficiario
     */
    public void setCodSituacaoBeneficiario(String codSituacaoBeneficiario) {
        this.codSituacaoBeneficiario = codSituacaoBeneficiario;
    }

    /**
     * @return o atributo descSituacaoBeneficiario
     */
    public String getDescSituacaoBeneficiario() {
        return descSituacaoBeneficiario;
    }

    /**
     * Define o atributo descSituacaoBeneficiario
     */
    public void setDescSituacaoBeneficiario(String descSituacaoBeneficiario) {
        this.descSituacaoBeneficiario = descSituacaoBeneficiario;
    }

}
