package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * DDA.PAGADORDDACONTA
 * 
 * @author George.santos
 */
@Entity
@Table(name = "PAGADORDDACONTA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.PagadorDDAContaVO")
public class PagadorDDAConta extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPAGADORDDACONTA", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDPAGADORDDA", nullable = false)
    private PagadorDDA pagadorDDA;

    @Column(nullable = false)
    private Integer numAgencia;

    @Column(nullable = false)
    private BigDecimal numContaCorrente;

    private DateTimeDB dataHoraAdesao;

    /**
     * 
     */
    public PagadorDDAConta() {
        super();
    }

    /**
     * @param pagadorDDA
     * @param numAgencia
     * @param numContaCorrente
     * @param dataHoraAdesao
     */
    public PagadorDDAConta(PagadorDDA pagadorDDA, Integer numAgencia, BigDecimal numContaCorrente, DateTimeDB dataHoraAdesao) {
        super();
        this.pagadorDDA = pagadorDDA;
        this.numAgencia = numAgencia;
        this.numContaCorrente = numContaCorrente;
        this.dataHoraAdesao = dataHoraAdesao;
    }

    /**
     * @param pagadorDDA
     * @param numAgencia
     * @param numContaCorrente
     * @param dataHoraAdesao
     */
    public PagadorDDAConta(PagadorDDA pagadorDDA, BigInteger numAgencia, BigInteger numContaCorrente, DateTimeDB dataHoraAdesao) {
        super();
        this.pagadorDDA = pagadorDDA;
        if (numAgencia != null) {
            this.numAgencia = numAgencia.intValue();
        }
        if (numContaCorrente != null) {
            this.numContaCorrente = new BigDecimal(numContaCorrente);
        }
        this.dataHoraAdesao = dataHoraAdesao;
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
     * @return o atributo pagadorDDA
     */
    public PagadorDDA getPagadorDDA() {
        return pagadorDDA;
    }

    /**
     * Define o atributo pagadorDDA
     */
    public void setPagadorDDA(PagadorDDA pagadorDDA) {
        this.pagadorDDA = pagadorDDA;
    }

    /**
     * @return o atributo numAgencia
     */
    public Integer getNumAgencia() {
        return numAgencia;
    }

    /**
     * Define o atributo numAgencia
     */
    public void setNumAgencia(Integer numAgencia) {
        this.numAgencia = numAgencia;
    }

    /**
     * @return o atributo numContaCorrente
     */
    public BigDecimal getNumContaCorrente() {
        return numContaCorrente;
    }

    /**
     * Define o atributo numContaCorrente
     */
    public void setNumContaCorrente(BigDecimal numContaCorrente) {
        this.numContaCorrente = numContaCorrente;
    }

    /**
     * @return o atributo dataHoraAdesao
     */
    public DateTimeDB getDataHoraAdesao() {
        return dataHoraAdesao;
    }

    /**
     * Define o atributo dataHoraAdesao
     */
    public void setDataHoraAdesao(DateTimeDB dataHoraAdesao) {
        this.dataHoraAdesao = dataHoraAdesao;
    }
}
