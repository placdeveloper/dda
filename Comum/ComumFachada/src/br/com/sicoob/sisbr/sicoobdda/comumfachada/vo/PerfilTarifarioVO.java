package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.PerfilTarifario")
public class PerfilTarifarioVO extends BancoobVO {

    private Long id;
    private Boolean bolAtivo;
    private Boolean bolVinculado;
    private DateTime dataCadastroPerfilTarifario;
    private Integer idInstituicao;
    private PerfilTarifarioVO perfilTarifarioPai;
    private Short idTipoPessoa;
    private String nomePerfilTarifario;
    private String siglaPerfil;
    private DateTime dataVinculacao;
    private DateTime dataDesvinculacao;
    private TipoPerfilTarifarioVO tipoPerfilTarifario;
    private List<ValorTarifaVO> listaValorTarifa;
    private String idUsuarioLogado;

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
     * @return o atributo bolVinculado
     */
    public Boolean getBolVinculado() {
        return bolVinculado;
    }

    /**
     * Define o atributo bolVinculado
     */
    public void setBolVinculado(Boolean bolVinculado) {
        this.bolVinculado = bolVinculado;
    }

    /**
     * @return o atributo dataCadastroPerfilTarifario
     */
    public DateTime getDataCadastroPerfilTarifario() {
        return dataCadastroPerfilTarifario;
    }

    /**
     * Define o atributo dataCadastroPerfilTarifario
     */
    public void setDataCadastroPerfilTarifario(DateTime dataCadastroPerfilTarifario) {
        this.dataCadastroPerfilTarifario = dataCadastroPerfilTarifario;
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
     * @return o atributo perfilTarifarioPai
     */
    public PerfilTarifarioVO getPerfilTarifarioPai() {
        return perfilTarifarioPai;
    }

    /**
     * Define o atributo perfilTarifarioPai
     */
    public void setPerfilTarifarioPai(PerfilTarifarioVO perfilTarifarioPai) {
        this.perfilTarifarioPai = perfilTarifarioPai;
    }

    /**
     * @return o atributo idTipoPessoa
     */
    public Short getIdTipoPessoa() {
        return idTipoPessoa;
    }

    /**
     * Define o atributo idTipoPessoa
     */
    public void setIdTipoPessoa(Short idTipoPessoa) {
        this.idTipoPessoa = idTipoPessoa;
    }

    /**
     * @return o atributo nomePerfilTarifario
     */
    public String getNomePerfilTarifario() {
        return nomePerfilTarifario;
    }

    /**
     * Define o atributo nomePerfilTarifario
     */
    public void setNomePerfilTarifario(String nomePerfilTarifario) {
        this.nomePerfilTarifario = nomePerfilTarifario;
    }

    /**
     * @return o atributo siglaPerfil
     */
    public String getSiglaPerfil() {
        return siglaPerfil;
    }

    /**
     * Define o atributo siglaPerfil
     */
    public void setSiglaPerfil(String siglaPerfil) {
        this.siglaPerfil = siglaPerfil;
    }

    /**
     * @return o atributo dataVinculacao
     */
    public DateTime getDataVinculacao() {
        return dataVinculacao;
    }

    /**
     * Define o atributo dataVinculacao
     */
    public void setDataVinculacao(DateTime dataVinculacao) {
        this.dataVinculacao = dataVinculacao;
    }

    /**
     * @return o atributo dataDesvinculacao
     */
    public DateTime getDataDesvinculacao() {
        return dataDesvinculacao;
    }

    /**
     * Define o atributo dataDesvinculacao
     */
    public void setDataDesvinculacao(DateTime dataDesvinculacao) {
        this.dataDesvinculacao = dataDesvinculacao;
    }

    /**
     * @return o atributo tipoPerfilTarifario
     */
    public TipoPerfilTarifarioVO getTipoPerfilTarifario() {
        return tipoPerfilTarifario;
    }

    /**
     * Define o atributo tipoPerfilTarifario
     */
    public void setTipoPerfilTarifario(TipoPerfilTarifarioVO tipoPerfilTarifario) {
        this.tipoPerfilTarifario = tipoPerfilTarifario;
    }

    /**
     * @return o atributo listaValorTarifa
     */
    public List<ValorTarifaVO> getListaValorTarifa() {
        return listaValorTarifa;
    }

    /**
     * Define o atributo listaValorTarifa
     */
    public void setListaValorTarifa(List<ValorTarifaVO> listaValorTarifa) {
        this.listaValorTarifa = listaValorTarifa;
    }

    /**
     * @return o atributo idUsuarioLogado
     */
    public String getIdUsuarioLogado() {
        return idUsuarioLogado;
    }

    /**
     * Define o atributo idUsuarioLogado
     */
    public void setIdUsuarioLogado(String idUsuarioLogado) {
        this.idUsuarioLogado = idUsuarioLogado;
    }

}
