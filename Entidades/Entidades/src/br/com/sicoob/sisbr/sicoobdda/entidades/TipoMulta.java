package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoMulta é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "TIPOMULTA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMultaVO")
public class TipoMulta extends SicoobDDAEntidade {

    private static final long serialVersionUID = -4590967887684087137L;

    public static final short VALOR_FIXO = 1;
    public static final short PERCENTUAL = 2;
    public static final short ISENTO = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODTIPOMULTA", unique = true, nullable = false)
    private Short id;

    @Column(nullable = false)
    private String descTipoMulta;

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
     * @return o atributo descTipoMulta
     */
    public String getDescTipoMulta() {
        return descTipoMulta;
    }

    /**
     * Define o atributo descTipoMulta
     */
    public void setDescTipoMulta(String descTipoMulta) {
        this.descTipoMulta = descTipoMulta;
    }

}
