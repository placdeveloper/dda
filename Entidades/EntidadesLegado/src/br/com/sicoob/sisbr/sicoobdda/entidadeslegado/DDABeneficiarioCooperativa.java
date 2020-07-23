/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades-legado
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.dda
 * Arquivo:         DDABeneficiarioCooperativa.java
 * Data Criação:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DDABeneficiarioCooperativa é responsável por
 * 
 * @author felipe.rosa
 */
@Entity
@Table(schema = "dbo", name = "DDABENEFICIARIOCOOPERATIVA")
public class DDABeneficiarioCooperativa extends SicoobDDAEntidadeLegado<DDABeneficiarioCooperativaPK> {

    /**
     * 
     */
    private static final long serialVersionUID = 7553157195570037318L;

    @EmbeddedId
    private DDABeneficiarioCooperativaPK pk;

    @ManyToOne
    @JoinColumn(name = "IDBENEFICIARIO", nullable = false, updatable = false, insertable = false)
    private DDABeneficiario beneficiario;

    @Column(name = "NUMCLIENTE", nullable = false, updatable = false, insertable = false)
    private Integer numCliente;

    @Column(name = "NUMCOOP", nullable = false, updatable = false, insertable = false)
    private Integer numCoop;

    /**
     * 
     */
    public DDABeneficiarioCooperativa() {

    }

    /**
     * @param beneficiario
     * @param numCliente
     * @param numCoop
     */
    public DDABeneficiarioCooperativa(DDABeneficiario beneficiario, Integer numCliente, Integer numCoop) {
        super();
        this.beneficiario = beneficiario;
        this.numCliente = numCliente;
        this.numCoop = numCoop;
        this.pk = new DDABeneficiarioCooperativaPK((beneficiario == null ? null : beneficiario.getId()), numCliente, numCoop);
    }

    /**
     * @return the beneficiario
     */
    public DDABeneficiario getBeneficiario() {
        return beneficiario;
    }

    /**
     * @param beneficiario the beneficiario to set
     */
    public void setBeneficiario(DDABeneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    /**
     * @return the numCliente
     */
    public Integer getNumCliente() {
        return numCliente;
    }

    /**
     * @param numCliente the numCliente to set
     */
    public void setNumCliente(Integer numCliente) {
        this.numCliente = numCliente;
    }

    /**
     * @return the numCoop
     */
    public Integer getNumCoop() {
        return numCoop;
    }

    /**
     * @param numCoop the numCoop to set
     */
    public void setNumCoop(Integer numCoop) {
        this.numCoop = numCoop;
    }

    /**
     * @return the pk
     */
    public DDABeneficiarioCooperativaPK getPk() {
        return pk;
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(DDABeneficiarioCooperativaPK pk) {
        this.pk = pk;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.SicoobDDAEntidadeLegado#getIdSQL()
     */
    @Override
    public DDABeneficiarioCooperativaPK getIdSQL() {
        return pk;
    }

    /**
     * Método responsável por
     * 
     * @param pk void
     * 
     */
    public void setIdSQL(DDABeneficiarioCooperativaPK pk) {
        this.pk = pk;
    }
}
