package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoEventoTarifavel
 * 
 * @author Samuell.Ramos
 */
@Entity
@Table(name = "TIPOEVENTOTARIFAVEL", schema = "DDA")
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoEventoTarifavelVO")
public class TipoEventoTarifavel extends SicoobDDAEntidade {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(unique = true, nullable = false)
    private short codTipoEventoTarifavel;

    @Column(nullable = false, length = 30)
    private String descTipoEventoTarifavel;

    public TipoEventoTarifavel() {
    }

    /**
     * @return o atributo codTipoEventoTarifavel
     */
    public short getCodTipoEventoTarifavel() {
        return codTipoEventoTarifavel;
    }

    /**
     * Define o atributo codTipoEventoTarifavel
     */
    public void setCodTipoEventoTarifavel(short codTipoEventoTarifavel) {
        this.codTipoEventoTarifavel = codTipoEventoTarifavel;
    }

    /**
     * @return o atributo descTipoEventoTarifavel
     */
    public String getDescTipoEventoTarifavel() {
        return descTipoEventoTarifavel;
    }

    /**
     * Define o atributo descTipoEventoTarifavel
     */
    public void setDescTipoEventoTarifavel(String descTipoEventoTarifavel) {
        this.descTipoEventoTarifavel = descTipoEventoTarifavel;
    }

}