package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsavel por representar a entidade.
 * 
 * @author Wesley.Costa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.ValorParametroDDA")
public class ValorParametroVO extends BancoobVO {

    private Long id;
    private Integer idInstituicao;
    private DateTime dataCriacao;
    private String valorParametro;
    private DateTime dataHoraUltimaAtualizacao;
    private String idUsuarioResponsavel;
    private String valorParametroCriacao;
    private ParametroVO parametro;
    private Boolean ativo;
    private String descInstituicao;

    public ValorParametroVO() {
    }

    /**
     * @param idInstituicao
     * @param descInstituicao
     */
    public ValorParametroVO(Integer idInstituicao, String descInstituicao) {
        super();
        this.idInstituicao = idInstituicao;
        this.descInstituicao = descInstituicao;
    }

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
     * @return o atributo dataCriacao
     */
    public DateTime getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Define o atributo dataCriacao
     */
    public void setDataCriacao(DateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /**
     * @return o atributo valorParametro
     */
    public String getValorParametro() {
        return valorParametro;
    }

    /**
     * Define o atributo valorParametro
     */
    public void setValorParametro(String valorParametro) {
        this.valorParametro = valorParametro;
    }

    /**
     * @return o atributo dataHoraUltimaAtualizacao
     */
    public DateTime getDataHoraUltimaAtualizacao() {
        return dataHoraUltimaAtualizacao;
    }

    /**
     * Define o atributo dataHoraUltimaAtualizacao
     */
    public void setDataHoraUltimaAtualizacao(DateTime dataHoraUltimaAtualizacao) {
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    /**
     * @return o atributo idUsuarioResponsavel
     */
    public String getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }

    /**
     * Define o atributo idUsuarioResponsavel
     */
    public void setIdUsuarioResponsavel(String idUsuarioResponsavel) {
        this.idUsuarioResponsavel = idUsuarioResponsavel;
    }

    /**
     * @return o atributo valorParametroCriacao
     */
    public String getValorParametroCriacao() {
        return valorParametroCriacao;
    }

    /**
     * Define o atributo valorParametroCriacao
     */
    public void setValorParametroCriacao(String valorParametroCriacao) {
        this.valorParametroCriacao = valorParametroCriacao;
    }

    /**
     * @return o atributo parametro
     */
    public ParametroVO getParametro() {
        return parametro;
    }

    /**
     * Define o atributo parametro
     */
    public void setParametro(ParametroVO parametro) {
        this.parametro = parametro;
    }

    /**
     * @return o atributo ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }

    /**
     * Define o atributo ativo
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    /**
     * @return o atributo descInstituicao
     */
    public String getDescInstituicao() {
        return descInstituicao;
    }

    /**
     * Define o atributo descInstituicao
     */
    public void setDescInstituicao(String descInstituicao) {
        this.descInstituicao = descInstituicao;
    }

}
