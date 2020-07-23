package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoParametro
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "TIPOPARAMETRO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoParametroVO")
public class TipoParametroDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = 4990210398862186865L;

    @Id
    @Column(name = "IDTIPOPARAMETRO", insertable = false, unique = true, nullable = false)
    private Short id;

    @Column(name = "DESCTIPOPARAMETRO", nullable = false, length = 500)
    private String descTipoParametro;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade#getId()
     */
    public Short getId() {
        return id;
    }

    /**
     * @return the descTipoParametro
     */
    public String getDescTipoParametro() {
        return descTipoParametro;
    }

    /**
     * @param descTipoParametro the descTipoParametro to set
     */
    public void setDescTipoParametro(String descTipoParametro) {
        this.descTipoParametro = descTipoParametro;
    }

    /**
     * @param id the id to set
     */
    public void setId(Short id) {
        this.id = id;
    }

}