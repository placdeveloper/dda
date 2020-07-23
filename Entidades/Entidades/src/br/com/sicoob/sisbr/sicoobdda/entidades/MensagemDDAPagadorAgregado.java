/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDAPagadorAgregado.java
 * Data Criação:    Jun 1, 2016
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

/**
 * MensagemDDAPagadorAgregado
 * 
 * @author george.santos
 */
@Entity
@Table(name = "MensagemDDAPagadorAgregado", schema = "DDA")
public class MensagemDDAPagadorAgregado extends SicoobDDAEntidade {
    private static final long serialVersionUID = -1463531203355301738L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMensagemDDAPagadorAgregado", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idMensagemDDA", nullable = false)
    private MensagemDDAPagador mensagemDDAPagador;

    @Column(nullable = false)
    private String numCpfCnpjAgregado;

    @Column(nullable = false)
    private String codTipoPessoaAgregado;

    @Column(nullable = false)
    private String codTipoOperacao;

    /**
     * 
     */
    public MensagemDDAPagadorAgregado() {
    }

    /**
     * @param mensagemDDAPagador
     * @param numCpfCnpjAgregado
     * @param codTipoPessoaAgregado
     * @param codTipoOperacao
     */
    public MensagemDDAPagadorAgregado(MensagemDDAPagador mensagemDDAPagador, String numCpfCnpjAgregado, String codTipoPessoaAgregado, String codTipoOperacao) {
        super();
        this.mensagemDDAPagador = mensagemDDAPagador;
        this.numCpfCnpjAgregado = numCpfCnpjAgregado;
        this.codTipoPessoaAgregado = codTipoPessoaAgregado;
        this.codTipoOperacao = codTipoOperacao;
    }

    /**
     * @param id
     * @param idMensagemDDAPagador
     * @param numCpfCnpjAgregado
     * @param codTipoPessoaAgregado
     * @param codTipoOperacao
     */
    public MensagemDDAPagadorAgregado(Long id, Long idMensagemDDAPagador, String numCpfCnpjAgregado, String codTipoPessoaAgregado, String codTipoOperacao) {
        super();
        this.id = id;
        this.mensagemDDAPagador = new MensagemDDAPagador(idMensagemDDAPagador);
        this.numCpfCnpjAgregado = numCpfCnpjAgregado;
        this.codTipoPessoaAgregado = codTipoPessoaAgregado;
        this.codTipoOperacao = codTipoOperacao;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo mensagemDDAPagador
     */
    public MensagemDDAPagador getMensagemDDAPagador() {
        return mensagemDDAPagador;
    }

    /**
     * Define o atributo mensagemDDAPagador
     */
    public void setMensagemDDAPagador(MensagemDDAPagador mensagemDDAPagador) {
        this.mensagemDDAPagador = mensagemDDAPagador;
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
     * @return o atributo codTipoPessoaAgregado
     */
    public String getCodTipoPessoaAgregado() {
        return codTipoPessoaAgregado;
    }

    /**
     * Define o atributo codTipoPessoaAgregado
     */
    public void setCodTipoPessoaAgregado(String codTipoPessoaAgregado) {
        this.codTipoPessoaAgregado = codTipoPessoaAgregado;
    }

    /**
     * @return o atributo codTipoOperacao
     */
    public String getCodTipoOperacao() {
        return codTipoOperacao;
    }

    /**
     * Define o atributo codTipoOperacao
     */
    public void setCodTipoOperacao(String codTipoOperacao) {
        this.codTipoOperacao = codTipoOperacao;
    }
}
