/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
 * Arquivo:         ValidacaoCargaBeneficiarioDto.java
 * Data Criação:    Jul 3, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoErroEnum;

/**
 * ValidacaoCargaDto é responsável por
 * 
 * @author Jesliel.Rocha
 */
public class ValidacaoCargaBeneficiarioDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8363695231984917690L;
    private String agencia; // cooperativa
    private List<TipoErroEnum> erros;

    public ValidacaoCargaBeneficiarioDto(String agencia) {
        this.agencia = agencia;
        this.erros = new ArrayList<TipoErroEnum>();
    }

    /**
     * @return o atributo agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * Define o atributo agencia
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * @return o atributo erros
     */
    public List<TipoErroEnum> getErros() {
        return erros;
    }

    /**
     * Define o atributo erros
     */
    public void setErros(List<TipoErroEnum> erros) {
        this.erros = erros;
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
        result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null){
            return false;
        }
        if (getClass() != obj.getClass()){
            return false;
        }
        ValidacaoCargaBeneficiarioDto other = (ValidacaoCargaBeneficiarioDto) obj;
        if (agencia == null) {
            if (other.agencia != null){
                return false;
            }
        } else if (!agencia.equals(other.agencia)){
            return false;
        }
        return true;
    }

}
