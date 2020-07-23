/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         DDACatalogoErrosCip.java
 * Data Criação:    Jul 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * DDACatalogoErrosCip
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "TIPOERROCIP", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoErroCipVO")
public class TipoErroCip extends SicoobDDAEntidade {

    private static final long serialVersionUID = 5167502239245186020L;

    @Id
    @Column(name = "CODTIPOERROCIP", unique = true, nullable = false)
    private String codTipoErro;

    @Column(name = "DESCTIPOERROCIP", nullable = false)
    private String descTipoErro;

    @ManyToOne
    @JoinColumn(name = "CODTIPOTRATAMENTOAUTOMATIZADO")
    private TipoTratamentoErroCip tipoTratamentoErroCip;

    /**
     * 
     */
    public TipoErroCip() {
        super();
    }

    /**
     * @param codTipoErro
     * @param descTipoErro
     * @param tipoTratamentoErroCip
     */
    public TipoErroCip(String codTipoErro, String descTipoErro, TipoTratamentoErroCip tipoTratamentoErroCip) {
        super();
        this.codTipoErro = codTipoErro;
        this.descTipoErro = descTipoErro;
        this.tipoTratamentoErroCip = tipoTratamentoErroCip;
    }

    /**
     * @param erroGEN
     */
    public TipoErroCip(String codTipoErro) {
        this.codTipoErro = codTipoErro;
    }

    /**
     * @return o atributo codTipoErro
     */
    public String getCodTipoErro() {
        return codTipoErro;
    }

    /**
     * Define o atributo codTipoErro
     */
    public void setCodTipoErro(String codTipoErro) {
        this.codTipoErro = codTipoErro;
    }

    /**
     * @return o atributo descTipoErro
     */
    public String getDescTipoErro() {
        return descTipoErro;
    }

    /**
     * Define o atributo descTipoErro
     */
    public void setDescTipoErro(String descTipoErro) {
        this.descTipoErro = descTipoErro;
    }

    /**
     * @return the tipoTratamentoErroCip
     */
    public TipoTratamentoErroCip getTipoTratamentoErroCip() {
        return tipoTratamentoErroCip;
    }

    /**
     * @param tipoTratamentoErroCip the tipoTratamentoErroCip to set
     */
    public void setTipoTratamentoErroCip(TipoTratamentoErroCip tipoTratamentoErroCip) {
        this.tipoTratamentoErroCip = tipoTratamentoErroCip;
    }

}
