/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         ServidorDDA.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ServidorDDA é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "SERVIDORDDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ServidorDDAVO")
public class ServidorDDA extends SicoobDDAEntidade {

    /**
     * 
     */
    private static final long serialVersionUID = 4322708838027197507L;

    @Id
    @Column(name = "CODSERVIDORDDA", unique = true, nullable = false)
    private String codServidorDDA;

    private String descPerfil;

    private DateTimeDB dataHoraCadastro;

    private Boolean bolAtivo;

    /**
     * 
     */
    public ServidorDDA() {
        super();
    }

    /**
     * @param codServidorDDA
     */
    public ServidorDDA(String codServidorDDA) {
        super();
        this.codServidorDDA = codServidorDDA;
        this.dataHoraCadastro = new DateTimeDB();
        this.bolAtivo = Boolean.TRUE;
    }

    /**
     * @param codServidorDDA
     * @param perfil
     * @param dataHoraCadastro
     */
    public ServidorDDA(String codServidorDDA, String perfil, DateTimeDB dataHoraCadastro, Boolean bolAtivo) {
        this.codServidorDDA = codServidorDDA;
        this.descPerfil = perfil;
        this.dataHoraCadastro = dataHoraCadastro;
        this.bolAtivo = bolAtivo;
    }

    /**
     * @return o atributo codServidorDDA
     */
    public String getCodServidorDDA() {
        return codServidorDDA;
    }

    /**
     * Define o atributo codServidorDDA
     */
    public void setCodServidorDDA(String codServidorDDA) {
        this.codServidorDDA = codServidorDDA;
    }

    /**
     * @return o atributo descPerfil
     */
    public String getDescPerfil() {
        return descPerfil;
    }

    /**
     * Define o atributo descPerfil
     */
    public void setDescPerfil(String descPerfil) {
        this.descPerfil = descPerfil;
    }

    /**
     * @return o atributo dataHoraCadastro
     */
    public DateTimeDB getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    /**
     * Define o atributo dataHoraCadastro
     */
    public void setDataHoraCadastro(DateTimeDB dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    /**
     * @return o atributo bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
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
        result = prime * result + ((codServidorDDA == null) ? 0 : codServidorDDA.hashCode());
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
            return Boolean.TRUE;
        }
        if (obj == null) {
            return Boolean.FALSE;
        }
        if (getClass() != obj.getClass()) {
            return Boolean.FALSE;
        }
        ServidorDDA other = (ServidorDDA) obj;
        if (codServidorDDA == null) {
            if (other.codServidorDDA != null) {
                return Boolean.FALSE;
            }
        } else if (!codServidorDDA.equals(other.codServidorDDA)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
