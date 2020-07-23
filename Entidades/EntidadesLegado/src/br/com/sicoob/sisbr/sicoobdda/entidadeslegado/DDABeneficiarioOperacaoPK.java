/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades-legado
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.dda
 * Arquivo:         DDABeneficiarioOperacaoPK.java
 * Data Criação:    Aug 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * DDABeneficiarioOperacaoPK é responsável por 
 * 
 * @author felipe.rosa
 */
@Embeddable
public class DDABeneficiarioOperacaoPK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4599120716116795624L;

    private Long idBeneficiario;

    private Short codOperacao;

    /**
     * 
     */
    public DDABeneficiarioOperacaoPK() {
        super();
    }

    /**
     * @param idBeneficiario
     * @param codOperacao
     */
    public DDABeneficiarioOperacaoPK(Long idBeneficiario, Short codOperacao) {
        super();
        this.idBeneficiario = idBeneficiario;
        this.codOperacao = codOperacao;
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
     * @return the codOperacao
     */
    public Short getCodOperacao() {
        return codOperacao;
    }

    /**
     * @param codOperacao the codOperacao to set
     */
    public void setCodOperacao(Short codOperacao) {
        this.codOperacao = codOperacao;
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
        result = prime * result + ((codOperacao == null) ? 0 : codOperacao.hashCode());
        result = prime * result + ((idBeneficiario == null) ? 0 : idBeneficiario.hashCode());
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
        DDABeneficiarioOperacaoPK other = (DDABeneficiarioOperacaoPK) obj;
        if (codOperacao == null) {
            if (other.codOperacao != null) {
                return false;
            }
        } else if (!codOperacao.equals(other.codOperacao)) {
            return false;
        }
        if (idBeneficiario == null) {
            if (other.idBeneficiario != null) {
                return false;
            }
        } else if (!idBeneficiario.equals(other.idBeneficiario)) {
            return false;
        }
        return true;
    }

}
