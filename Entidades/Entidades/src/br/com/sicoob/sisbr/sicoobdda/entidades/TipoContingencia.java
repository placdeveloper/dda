/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         TipoContingencia.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TipoContingencia
 * 
 * @author Danilo.Barros
 */
@Entity
@Table(name = "TIPOCONTINGENCIA", schema = "DDA")
public class TipoContingencia extends SicoobDDAEntidade {

    private static final long serialVersionUID = 1L;

    public static final long CIP = 1;
    public static final long BANCOOB = 2;

    @Id
    @Column(name = "CODTIPOCONTINGENCIA", unique = true, nullable = false)
    private Long codTipoContingencia;

    @Column(name = "DESCTIPOCONTIGENCIA", nullable = false, length = 50)
    private String descTipoContingencia;

    /**
     * @param
     */
    public TipoContingencia() {
        super();
    }

    /**
     * @param codTipoContingencia
     * @param descTipoContingencia
     */
    public TipoContingencia(Long codTipoContingencia) {
        super();
        this.codTipoContingencia = codTipoContingencia;
    }

    /**
     * @return o atributo codTipoContingencia
     */
    public Long getCodTipoContingencia() {
        return codTipoContingencia;
    }

    /**
     * Define o atributo codTipoContingencia
     */
    public void setCodTipoContingencia(Long codTipoContingencia) {
        this.codTipoContingencia = codTipoContingencia;
    }

    /**
     * @return o atributo descTipoContingencia
     */
    public String getDescTipoContingencia() {
        return descTipoContingencia;
    }

    /**
     * Define o atributo descTipoContingencia
     */
    public void setDescTipoContingencia(String descTipoContingencia) {
        this.descTipoContingencia = descTipoContingencia;
    }

}