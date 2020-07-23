/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades-legado
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.dda
 * Arquivo:         DDABeneficiario.java
 * Data Criação:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * DDAPagadorEletronico é responsável por
 * 
 * @author George.Santos
 */
@SuppressWarnings("serial")
@Entity
@Table(schema = "dbo", name = "DDAPAGADORELETRONICO")
public class DDAPagadorEletronico extends SicoobDDAEntidade implements Serializable {

    @Id
    @Column(name = "NUMCPFCNPJ", unique = true, nullable = false, length = 14)
    private String numCPFCNPJ;

    @Column(name = "NUMCOOPERATIVA", nullable = true)
    private Integer numCooperativa;

    @Column(name = "BOLSACADOELETRONICO", nullable = false)
    private Boolean bolSacadoEletronico;

    public DDAPagadorEletronico() {
    }

    public DDAPagadorEletronico(String numCPFCNPJ, Boolean bolSacadoEletronico, Integer numCooperativa) {
        super();
        this.numCPFCNPJ = numCPFCNPJ;
        this.numCooperativa = numCooperativa;
        this.bolSacadoEletronico = bolSacadoEletronico;
    }

    /**
     * @return o atributo numCPFCNPJ
     */
    public String getNumCPFCNPJ() {
        return numCPFCNPJ;
    }

    /**
     * Define o atributo numCPFCNPJ
     */
    public void setNumCPFCNPJ(String numCPFCNPJ) {
        this.numCPFCNPJ = numCPFCNPJ;
    }

    /**
     * @return o atributo numCooperativa
     */
    public Integer getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(Integer numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return o atributo bolSacadoEletronico
     */
    public Boolean getBolSacadoEletronico() {
        return bolSacadoEletronico;
    }

    /**
     * Define o atributo bolSacadoEletronico
     */
    public void setBolSacadoEletronico(Boolean bolSacadoEletronico) {
        this.bolSacadoEletronico = bolSacadoEletronico;
    }
}
