/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades-legado
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.dda
 * Arquivo:         DDABeneficiarioCooperativaPK.java
 * Data Criação:    Aug 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * DDABeneficiarioCooperativaPK é responsável por 
 * 
 * @author felipe.rosa
 */
@Embeddable
public class DDABeneficiarioCooperativaPK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8742706566741505737L;

    private Long idBeneficiario;

    private Integer numCliente;

    private Integer numCoop;

    /**
     * 
     */
    public DDABeneficiarioCooperativaPK() {
        super();
    }

    /**
     * @param idBeneficiario
     * @param numCliente
     * @param numCoop
     */
    public DDABeneficiarioCooperativaPK(Long idBeneficiario, Integer numCliente, Integer numCoop) {
        super();
        this.idBeneficiario = idBeneficiario;
        this.numCliente = numCliente;
        this.numCoop = numCoop;
    }

    /**
     * @return the idBeneficiario
     */
    public Long getIdBeneficiario() {
        return idBeneficiario;
    }

    /**
     * @param idBeneficiario the idBeneficiario to set
     */
    public void setIdBeneficiario(Long idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
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
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idBeneficiario == null) ? 0 : idBeneficiario.hashCode());
        result = prime * result + ((numCliente == null) ? 0 : numCliente.hashCode());
        result = prime * result + ((numCoop == null) ? 0 : numCoop.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DDABeneficiarioCooperativaPK other = (DDABeneficiarioCooperativaPK) obj;
        if (idBeneficiario == null) {
            if (other.idBeneficiario != null) {
                return false;
            }
        } else if (!idBeneficiario.equals(other.idBeneficiario)) {
            return false;
        }
        if (numCliente == null) {
            if (other.numCliente != null) {
                return false;
            }
        } else if (!numCliente.equals(other.numCliente)) {
            return false;
        }
        if (numCoop == null) {
            if (other.numCoop != null) {
                return false;
            }
        } else if (!numCoop.equals(other.numCoop)) {
            return false;
        }
        return true;
    }

}
