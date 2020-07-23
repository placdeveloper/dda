/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         CategoriaMensagemDDA.java
 * Data Criação:    Ago 09, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CategoriaMensagemDDA
 * 
 * @author samuell.ramos
 */
@Entity
@Table(name = "CATEGORIAMENSAGEMDDA", schema = "DDA")
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.CategoriaMensagemDDAVO")
public class CategoriaMensagemDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = 1050792762615948953L;

    @Id
    @Column(name = "CODCATEGORIAMENSAGEMDDA", unique = true, nullable = true)
    private String codCategoriaMensagemDda;

    private String descCategoriaMensagemDda;

    /**
     * @return o atributo codCategoriaMensagemDda
     */
    public String getCodCategoriaMensagemDda() {
        return codCategoriaMensagemDda;
    }

    /**
     * Define o atributo codCategoriaMensagemDda
     */
    public void setCodCategoriaMensagemDda(String codCategoriaMensagemDda) {
        this.codCategoriaMensagemDda = codCategoriaMensagemDda;
    }

    /**
     * @return o atributo descCategoriaMensagemDda
     */
    public String getDescCategoriaMensagemDda() {
        return descCategoriaMensagemDda;
    }

    /**
     * Define o atributo descCategoriaMensagemDda
     */
    public void setDescCategoriaMensagemDda(String descCategoriaMensagemDda) {
        this.descCategoriaMensagemDda = descCategoriaMensagemDda;
    }

}
