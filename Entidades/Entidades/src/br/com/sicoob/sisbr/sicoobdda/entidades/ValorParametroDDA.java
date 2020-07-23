/**
 * Projeto: Cobrança Bancária
 * Camada Projeto:  atendimento-backoffice-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.atendimentobackoffice.negocio.entidades
 * Arquivo:         ValorParametro.java
 * Data Criação:    27/06/2012
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

@Entity
@Table(name = "VALORPARAMETRO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ValorParametroVO")
public class ValorParametroDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = 6225969366569707057L;

    @Id
    @Column(name = "IDVALORPARAMETRO", unique = true, nullable = false)
    private Long id;

    @Column(name = "IDINSTITUICAO", nullable = false)
    private Long idInstituicao;

    @Column(name = "DATACRIACAO", nullable = false)
    private DateTimeDB dataCriacao;

    @Column(name = "VALORPARAMETRO", nullable = true)
    private String valorParametro;

    @Column(name = "DATAHORAULTIMAATUALIZACAO", nullable = false)
    private DateTimeDB dataHoraUltimaAtualizacao;

    @Column(name = "IDUSUARIORESPONSAVEL", nullable = true)
    private String idUsuarioResponsavel;

    @Column(name = "VALORPARAMETROCRIACAO", nullable = false)
    private String valorParametroCriacao;

    @ManyToOne
    @JoinColumn(name = "IDPARAMETRO", nullable = false)
    private ParametroDDA parametro;

    @Transient
    private Boolean ativo;

    @Transient
    private String descInstituicao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the idInstituicao
     */
    public Long getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * @param idInstituicao the idInstituicao to set
     */
    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return the valorParametro
     */
    public String getValorParametro() {
        return valorParametro;
    }

    /**
     * @param valorParametro the valorParametro to set
     */
    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    /**
     * @return the dataCriacao
     */
    public DateTimeDB getDataCriacao() {
        return dataCriacao;
    }

    /**
     * @param dataCriacao the dataCriacao to set
     */
    public void setDataCriacao(DateTimeDB dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * @return the dataHoraUltimaAtualizacao
     */
    public DateTimeDB getDataHoraUltimaAtualizacao() {
        return dataHoraUltimaAtualizacao;
    }

    /**
     * @param dataHoraUltimaAtualizacao the dataHoraUltimaAtualizacao to set
     */
    public void setDataHoraUltimaAtualizacao(DateTimeDB dataHoraUltimaAtualizacao) {
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    /**
     * @return the idUsuarioResponsavel
     */
    public String getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }

    /**
     * @param idUsuarioResponsavel the idUsuarioResponsavel to set
     */
    public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getValorParametroCriacao() {
        return valorParametroCriacao;
    }

    public void setValorParametroCriacao(String valorParametroCriacao) {
        this.valorParametroCriacao = valorParametroCriacao;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getDescInstituicao() {
        return descInstituicao;
    }

    public void setDescInstituicao(String descInstituicao) {
        this.descInstituicao = descInstituicao;
    }

    /**
     * @return o atributo parametro
     */
    public ParametroDDA getParametro() {
        return parametro;
    }

    /**
     * Define o atributo parametro
     */
    public void setParametro(ParametroDDA parametro) {
        this.parametro = parametro;
    }

}
