package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoServicoTarifavel
 * 
 * @author Samuell.Ramos
 */
@Entity
@Table(name = "TIPOSERVICOTARIFAVEL", schema = "DDA")
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoServicoTarifavelVO")
public class TipoServicoTarifavel extends SicoobDDAEntidade {

    private static final long serialVersionUID = -1127459008801085675L;

    @Id
    @Column(nullable = false)
    private short codTipoServicoTarifavel;

    @Column(length = 30)
    private String descServicoTarifavel;

    public TipoServicoTarifavel() {
    }

    /**
     * @return o atributo codTipoServicoTarifavel
     */
    public short getCodTipoServicoTarifavel() {
        return codTipoServicoTarifavel;
    }

    /**
     * Define o atributo codTipoServicoTarifavel
     */
    public void setCodTipoServicoTarifavel(short codTipoServicoTarifavel) {
        this.codTipoServicoTarifavel = codTipoServicoTarifavel;
    }

    /**
     * @return o atributo descServicoTarifavel
     */
    public String getDescServicoTarifavel() {
        return descServicoTarifavel;
    }

    /**
     * Define o atributo descServicoTarifavel
     */
    public void setDescServicoTarifavel(String descServicoTarifavel) {
        this.descServicoTarifavel = descServicoTarifavel;
    }

}