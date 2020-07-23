package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA")
public class ParametroVO extends BancoobVO {

    private Long id;
    private String nomeParametro;
    private TipoParametroVO tipoParametro;
    private Boolean bolVisivelFuncionalidadeAlteracao;
    private Boolean bolPermiteAlteracaoPeloUsuario;
    private Boolean bolAtivo;
    private DateTime dataCriacao;
    private DateTime dataHoraUltimaAtualizacao;
    private String descParametro;
    private String valorParametro;
    private String valorParametroTexto;
    private String nomeTabelaDominio;
    private Boolean bolParametroGlobal;
    private Integer idParametroLegado;
    private List<ValorParametroVO> listaValorParametro;

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
     * @return o atributo nomeParametro
     */
    public String getNomeParametro() {
        return nomeParametro;
    }

    /**
     * Define o atributo nomeParametro
     */
    public void setNomeParametro(String nomeParametro) {
        this.nomeParametro = nomeParametro;
    }

    /**
     * @return o atributo tipoParametro
     */
    public TipoParametroVO getTipoParametro() {
        return tipoParametro;
    }

    /**
     * Define o atributo tipoParametro
     */
    public void setTipoParametro(TipoParametroVO tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    /**
     * @return o atributo bolVisivelFuncionalidadeAlteracao
     */
    public Boolean getBolVisivelFuncionalidadeAlteracao() {
        return bolVisivelFuncionalidadeAlteracao;
    }

    /**
     * Define o atributo bolVisivelFuncionalidadeAlteracao
     */
    public void setBolVisivelFuncionalidadeAlteracao(Boolean bolVisivelFuncionalidadeAlteracao) {
        this.bolVisivelFuncionalidadeAlteracao = bolVisivelFuncionalidadeAlteracao;
    }

    /**
     * @return o atributo bolPermiteAlteracaoPeloUsuario
     */
    public Boolean getBolPermiteAlteracaoPeloUsuario() {
        return bolPermiteAlteracaoPeloUsuario;
    }

    /**
     * Define o atributo bolPermiteAlteracaoPeloUsuario
     */
    public void setBolPermiteAlteracaoPeloUsuario(Boolean bolPermiteAlteracaoPeloUsuario) {
        this.bolPermiteAlteracaoPeloUsuario = bolPermiteAlteracaoPeloUsuario;
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
     * @return o atributo descParametro
     */
    public String getDescParametro() {
        return descParametro;
    }

    /**
     * Define o atributo descParametro
     */
    public void setDescParametro(String descParametro) {
        this.descParametro = descParametro;
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
     * @return o atributo valorParametroTexto
     */
    public String getValorParametroTexto() {
        return valorParametroTexto;
    }

    /**
     * Define o atributo valorParametroTexto
     */
    public void setValorParametroTexto(String valorParametroTexto) {
        this.valorParametroTexto = valorParametroTexto;
    }

    /**
     * @return o atributo nomeTabelaDominio
     */
    public String getNomeTabelaDominio() {
        return nomeTabelaDominio;
    }

    /**
     * Define o atributo nomeTabelaDominio
     */
    public void setNomeTabelaDominio(String nomeTabelaDominio) {
        this.nomeTabelaDominio = nomeTabelaDominio;
    }

    /**
     * @return o atributo bolParametroGlobal
     */
    public Boolean getBolParametroGlobal() {
        return bolParametroGlobal;
    }

    /**
     * Define o atributo bolParametroGlobal
     */
    public void setBolParametroGlobal(Boolean bolParametroGlobal) {
        this.bolParametroGlobal = bolParametroGlobal;
    }

    /**
     * @return o atributo idParametroLegado
     */
    public Integer getIdParametroLegado() {
        return idParametroLegado;
    }

    /**
     * Define o atributo idParametroLegado
     */
    public void setIdParametroLegado(Integer idParametroLegado) {
        this.idParametroLegado = idParametroLegado;
    }

    /**
     * @return o atributo listaValorParametro
     */
    public List<ValorParametroVO> getListaValorParametro() {
        return listaValorParametro;
    }

    /**
     * Define o atributo listaValorParametro
     */
    public void setListaValorParametro(List<ValorParametroVO> listaValorParametro) {
        this.listaValorParametro = listaValorParametro;
    }

}
