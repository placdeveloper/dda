package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TipoPercentualValor é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "TipoPercentualValor", schema = "DDA")
public class TipoPercentualValor extends SicoobDDAEntidade {

    private static final long serialVersionUID = -8427374591512749662L;

    // Pode representar um valor ou percentual
    public static final char LEGADO = 'L';
    public static final char PERCENTUAL = 'P';
    public static final char VALOR = 'V';

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODTIPOPERCENTUALVALOR", unique = true, nullable = false, length = 1)
    private Character codTipoPercentualValor;

    @Column(nullable = false)
    private String descTipoPercentualValor;

    /**
     * @return o atributo codTipoPercentualValor
     */
    public Character getCodTipoPercentualValor() {
        return codTipoPercentualValor;
    }

    /**
     * Define o atributo codTipoPercentualValor
     */
    public void setCodTipoPercentualValor(Character codTipoPercentualValor) {
        this.codTipoPercentualValor = codTipoPercentualValor;
    }

    /**
     * @return o atributo descTipoPercentualValor
     */
    public String getDescTipoPercentualValor() {
        return descTipoPercentualValor;
    }

    /**
     * Define o atributo descTipoPercentualValor
     */
    public void setDescTipoPercentualValor(String descTipoPercentualValor) {
        this.descTipoPercentualValor = descTipoPercentualValor;
    }

}
