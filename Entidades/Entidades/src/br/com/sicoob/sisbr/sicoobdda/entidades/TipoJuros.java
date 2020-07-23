package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoJuros é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "TIPOJUROS", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoJurosVO")
public class TipoJuros extends SicoobDDAEntidade {

    private static final long serialVersionUID = -3892263549110602208L;

    public static final short VALOR_DIAS_CORRIDOS = 1;
    public static final short PERCENTUAL_AO_DIA_DIAS_CORRIDOS = 2;
    public static final short PERCENTUAL_AO_MES_DIAS_CORRIDOS = 3;
    public static final short PERCENTUAL_AO_ANO_DIAS_CORRIDOS = 4;
    public static final short ISENTO = 5;
    public static final short VALOR_DIA_UTIL = 6;
    public static final short PERCENTUAL_AO_DIA_DIAS_UTEIS = 7;
    public static final short PERCENTUAL_AO_MES_DIAS_UTEIS = 8;
    public static final short PERCENTUAL_AO_ANO_DIAS_UTEIS = 9;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODTIPOJUROS", unique = true, nullable = false)
    private Short id;

    @Column(nullable = false)
    private String descTipoJuros;

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
     * @return o atributo descTipoJuros
     */
    public String getDescTipoJuros() {
        return descTipoJuros;
    }

    /**
     * Define o atributo descTipoJuros
     */
    public void setDescTipoJuros(String descTipoJuros) {
        this.descTipoJuros = descTipoJuros;
    }

}
