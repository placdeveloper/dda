/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         TipoTermoPagador.java
 * Data Criação:    Mar 31, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoTermoPagador
 * 
 * @author samuell.ramos
 */
@Entity
@Table(name = "TIPOTERMOPAGADOR", schema = "DDA")
public class TipoTermoPagador extends SicoobDDAEntidade {

    private static final long serialVersionUID = -5418478231066375496L;

    @Id
    private Short codTipoTermoPagador;

    private String descTipoTermoPagador;

    @OneToMany(mappedBy = "tipoTermoPagador")
    private List<HistoricoTermoPagador> listaHistTermoPagador;

    @OneToMany(mappedBy = "tipoTermoPagador")
    private List<TermoPagador> listaTermoPagador;

    /**
     * Construtor
     */
    public TipoTermoPagador() {
        super();
    }

    /**
     * @param codTipoTermoPagador
     */
    public TipoTermoPagador(Short codTipoTermoPagador) {
        super();
        this.codTipoTermoPagador = codTipoTermoPagador;
    }

    /**
     * @return o atributo codTipoTermoPagador
     */
    public Short getCodTipoTermoPagador() {
        return codTipoTermoPagador;
    }

    /**
     * Define o atributo codTipoTermoPagador
     */
    public void setCodTipoTermoPagador(Short codTipoTermoPagador) {
        this.codTipoTermoPagador = codTipoTermoPagador;
    }

    /**
     * @return o atributo descTipoTermoPagador
     */
    public String getDescTipoTermoPagador() {
        return descTipoTermoPagador;
    }

    /**
     * Define o atributo descTipoTermoPagador
     */
    public void setDescTipoTermoPagador(String descTipoTermoPagador) {
        this.descTipoTermoPagador = descTipoTermoPagador;
    }

    /**
     * @return o atributo listaHistTermoPagador
     */
    public List<HistoricoTermoPagador> getListaHistTermoPagador() {
        return listaHistTermoPagador;
    }

    /**
     * Define o atributo listaHistTermoPagador
     */
    public void setListaHistTermoPagador(List<HistoricoTermoPagador> listaHistTermoPagador) {
        this.listaHistTermoPagador = listaHistTermoPagador;
    }

    /**
     * @return o atributo listaTermoPagador
     */
    public List<TermoPagador> getListaTermoPagador() {
        return listaTermoPagador;
    }

    /**
     * Define o atributo listaTermoPagador
     */
    public void setListaTermoPagador(List<TermoPagador> listaTermoPagador) {
        this.listaTermoPagador = listaTermoPagador;
    }

}