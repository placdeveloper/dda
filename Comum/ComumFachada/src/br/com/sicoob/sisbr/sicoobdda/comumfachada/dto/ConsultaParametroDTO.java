package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoParametroVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author daniel.moraes
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaParametroDto")
public class ConsultaParametroDTO extends BancoobDTO {

    private Long idParametro;
    private String nomeParametro;
    private String descricaoParametro;
    private Long idInstituicao;
    private TipoParametroVO tipoParametro;
    private Boolean bolPermiteAlteracaoPeloUsuario;
    private Boolean bolVisivelFuncionalidadeAlteracao;
    private Boolean bolAtivo;
    private Boolean bolParametroGlobal;
    private String valorBaseParametro;
    private String valorBaseParametroTexto;
    private DateTime dataCriacao;
    private DateTime dataUltimaAlteracao;
    private String numeroCooperativa;
    private String descricaoSiglaCooperativa;
    private Boolean somenteVisiveis;
    private Long idParametroLegado;

    /**
     * @return o atributo idParametro
     */
    public Long getIdParametro() {
        return idParametro;
    }

    /**
     * Define o atributo idParametro
     */
    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
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
     * @return o atributo descricaoParametro
     */
    public String getDescricaoParametro() {
        return descricaoParametro;
    }

    /**
     * Define o atributo descricaoParametro
     */
    public void setDescricaoParametro(String descricaoParametro) {
        this.descricaoParametro = descricaoParametro;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Long getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Long idInstituicao) {
        this.idInstituicao = idInstituicao;
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
     * @return o atributo valorBaseParametro
     */
    public String getValorBaseParametro() {
        return valorBaseParametro;
    }

    /**
     * Define o atributo valorBaseParametro
     */
    public void setValorBaseParametro(String valorBaseParametro) {
        this.valorBaseParametro = valorBaseParametro;
    }

    /**
     * @return o atributo valorBaseParametroTexto
     */
    public String getValorBaseParametroTexto() {
        return valorBaseParametroTexto;
    }

    /**
     * Define o atributo valorBaseParametroTexto
     */
    public void setValorBaseParametroTexto(String valorBaseParametroTexto) {
        this.valorBaseParametroTexto = valorBaseParametroTexto;
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
     * @return o atributo dataUltimaAlteracao
     */
    public DateTime getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    /**
     * Define o atributo dataUltimaAlteracao
     */
    public void setDataUltimaAlteracao(DateTime dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    /**
     * @return o atributo numeroCooperativa
     */
    public String getNumeroCooperativa() {
        return numeroCooperativa;
    }

    /**
     * Define o atributo numeroCooperativa
     */
    public void setNumeroCooperativa(String numeroCooperativa) {
        this.numeroCooperativa = numeroCooperativa;
    }

    /**
     * @return o atributo descricaoSiglaCooperativa
     */
    public String getDescricaoSiglaCooperativa() {
        return descricaoSiglaCooperativa;
    }

    /**
     * Define o atributo descricaoSiglaCooperativa
     */
    public void setDescricaoSiglaCooperativa(String descricaoSiglaCooperativa) {
        this.descricaoSiglaCooperativa = descricaoSiglaCooperativa;
    }

    /**
     * @return o atributo somenteVisiveis
     */
    public Boolean getSomenteVisiveis() {
        return somenteVisiveis;
    }

    /**
     * Define o atributo somenteVisiveis
     */
    public void setSomenteVisiveis(Boolean somenteVisiveis) {
        this.somenteVisiveis = somenteVisiveis;
    }

    /**
     * @return o atributo idParametroLegado
     */
    public Long getIdParametroLegado() {
        return idParametroLegado;
    }

    /**
     * Define o atributo idParametroLegado
     */
    public void setIdParametroLegado(Long idParametroLegado) {
        this.idParametroLegado = idParametroLegado;
    }

}
