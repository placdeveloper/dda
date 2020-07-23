/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         HistoricoTermoPagador.java
 * Data Criação:    Mar 31, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * HistoricoTermoPagador
 * 
 * @author samuell.ramos
 */
@Entity
@Table(name = "HISTORICOTERMOPAGADOR", schema = "DDA")
public class HistoricoTermoPagador extends SicoobDDAEntidade {

    private static final long serialVersionUID = 5791507526809355267L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHISTORICOTERMOPAGADOR", unique = true, nullable = false)
    private Long id;

    private DateTimeDB dataHoraTermoDDA;

    private Short idCanal;

    private String numCpfCnpjAgregado;

    private String numCpfCnpjPagador;

    @ManyToOne
    @JoinColumn(name = "CODTIPOTERMOPAGADOR")
    private TipoTermoPagador tipoTermoPagador;

    /**
     * 
     */
    public HistoricoTermoPagador() {
    }

    /**
     * @param dataHoraTermoDDA
     * @param idCanal
     * @param numCpfCnpjAgregado
     * @param numCpfCnpjPagador
     */
    public HistoricoTermoPagador(DateTimeDB dataHoraTermoDDA, Short idCanal, String numCpfCnpjAgregado, String numCpfCnpjPagador) {
        super();
        this.dataHoraTermoDDA = dataHoraTermoDDA;
        this.idCanal = idCanal;
        this.numCpfCnpjAgregado = numCpfCnpjAgregado;
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * @param dataHoraTermoDDA
     * @param idCanal
     * @param numCpfCnpjPagador
     */
    public HistoricoTermoPagador(DateTimeDB dataHoraTermoDDA, Short idCanal, String numCpfCnpjPagador) {
        super();
        this.dataHoraTermoDDA = dataHoraTermoDDA;
        this.idCanal = idCanal;
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    public HistoricoTermoPagador(DateTimeDB dataHoraTermoDDA, TipoTermoPagador tipoTermoPagador) {
        super();
        this.dataHoraTermoDDA = dataHoraTermoDDA;
        this.tipoTermoPagador = tipoTermoPagador;
    }

    /**
     * @return o atributo id
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo dataHoraTermoDDA
     */
    public DateTimeDB getDataHoraTermoDDA() {
        return dataHoraTermoDDA;
    }

    /**
     * Define o atributo dataHoraTermoDDA
     */
    public void setDataHoraTermoDDA(DateTimeDB dataHoraTermoDDA) {
        this.dataHoraTermoDDA = dataHoraTermoDDA;
    }

    /**
     * @return o atributo idCanal
     */
    public Short getIdCanal() {
        return idCanal;
    }

    /**
     * Define o atributo idCanal
     */
    public void setIdCanal(Short idCanal) {
        this.idCanal = idCanal;
    }

    /**
     * @return o atributo numCpfCnpjAgregado
     */
    public String getNumCpfCnpjAgregado() {
        return numCpfCnpjAgregado;
    }

    /**
     * Define o atributo numCpfCnpjAgregado
     */
    public void setNumCpfCnpjAgregado(String numCpfCnpjAgregado) {
        this.numCpfCnpjAgregado = numCpfCnpjAgregado;
    }

    /**
     * @return o atributo numCpfCnpjPagador
     */
    public String getNumCpfCnpjPagador() {
        return numCpfCnpjPagador;
    }

    /**
     * Define o atributo numCpfCnpjPagador
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * @return o atributo tipoTermoPagador
     */
    public TipoTermoPagador getTipoTermoPagador() {
        return tipoTermoPagador;
    }

    /**
     * Define o atributo tipoTermoPagador
     */
    public void setTipoTermoPagador(TipoTermoPagador tipoTermoPagador) {
        this.tipoTermoPagador = tipoTermoPagador;
    }

    /**
     * @return o atributo serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}