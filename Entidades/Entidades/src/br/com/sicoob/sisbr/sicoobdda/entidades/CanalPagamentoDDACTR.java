package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CanalPagamentoDDACTR é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "CanalPagamentoDDACTR", schema = "DDA")
public class CanalPagamentoDDACTR extends SicoobDDAEntidade {

    private static final long serialVersionUID = -4409384617380385567L;

    @Id
    @Column(name = "IDCANALCTR", unique = true, nullable = false)
    private Short id;

    private Short codCanalPagamento;

    private Short idCanal;

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
     * @return o atributo codCanalPagamento
     */
    public Short getCodCanalPagamento() {
        return codCanalPagamento;
    }

    /**
     * Define o atributo codCanalPagamento
     */
    public void setCodCanalPagamento(Short codCanalPagamento) {
        this.codCanalPagamento = codCanalPagamento;
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

}
