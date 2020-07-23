/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         DDALogErroBeneficiarioCoop.java
 * Data Criação:    Jul 27, 2015
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

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * DDALogErroBeneficiarioCoop
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "LOGERROBENEFICARIOINSTITUICAO", schema = "DDA")
public class LogErroBeneficiarioInstituicao extends SicoobDDAEntidade {

    private static final long serialVersionUID = -2482988932242853010L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLOGERROBENEFICARIOINSTITUICAO", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CODTIPOERROCIP", nullable = false)
    private TipoErroCip tipoErroCip;

    @Column(nullable = false)
    private Integer idInstituicao;

    @Column(nullable = false)
    private DateTimeDB dataHoraCadastro;

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
     * @return o atributo tipoErroCip
     */
    public TipoErroCip getTipoErroCip() {
        return tipoErroCip;
    }

    /**
     * Define o atributo tipoErroCip
     */
    public void setTipoErroCip(TipoErroCip tipoErroCip) {
        this.tipoErroCip = tipoErroCip;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
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

}
