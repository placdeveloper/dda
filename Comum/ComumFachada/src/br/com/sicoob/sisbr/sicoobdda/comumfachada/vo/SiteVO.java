package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.Site")
public class SiteVO extends BancoobVO {

    private Long idProduto;
    private Long numCooperativa;
    private Long numPac;
    private Long numCliente;
    private Long idCedSiteWebBoleto;
    private String descSiteWeb;
    private DateTime dataConvenio;
    private DateTime dataPrimeiroAcesso;
    private DateTime dataUltimoAcesso;
    private DateTime dataCancelamento;
    private Long codSituacaoConvenio;
    private String descChaveAcesso;

    private transient String descSituacao;
    private transient Boolean selecionado;

    /**
     * @return o atributo idProduto
     */
    public Long getIdProduto() {
        return idProduto;
    }

    /**
     * Define o atributo idProduto
     */
    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return o atributo numCooperativa
     */
    public Long getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(Long numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return o atributo numPac
     */
    public Long getNumPac() {
        return numPac;
    }

    /**
     * Define o atributo numPac
     */
    public void setNumPac(Long numPac) {
        this.numPac = numPac;
    }

    /**
     * @return o atributo numCliente
     */
    public Long getNumCliente() {
        return numCliente;
    }

    /**
     * Define o atributo numCliente
     */
    public void setNumCliente(Long numCliente) {
        this.numCliente = numCliente;
    }

    /**
     * @return o atributo idCedSiteWebBoleto
     */
    public Long getIdCedSiteWebBoleto() {
        return idCedSiteWebBoleto;
    }

    /**
     * Define o atributo idCedSiteWebBoleto
     */
    public void setIdCedSiteWebBoleto(Long idCedSiteWebBoleto) {
        this.idCedSiteWebBoleto = idCedSiteWebBoleto;
    }

    /**
     * @return o atributo descSiteWeb
     */
    public String getDescSiteWeb() {
        return descSiteWeb;
    }

    /**
     * Define o atributo descSiteWeb
     */
    public void setDescSiteWeb(String descSiteWeb) {
        this.descSiteWeb = descSiteWeb;
    }

    /**
     * @return o atributo dataConvenio
     */
    public DateTime getDataConvenio() {
        return dataConvenio;
    }

    /**
     * Define o atributo dataConvenio
     */
    public void setDataConvenio(DateTime dataConvenio) {
        this.dataConvenio = dataConvenio;
    }

    /**
     * @return o atributo dataPrimeiroAcesso
     */
    public DateTime getDataPrimeiroAcesso() {
        return dataPrimeiroAcesso;
    }

    /**
     * Define o atributo dataPrimeiroAcesso
     */
    public void setDataPrimeiroAcesso(DateTime dataPrimeiroAcesso) {
        this.dataPrimeiroAcesso = dataPrimeiroAcesso;
    }

    /**
     * @return o atributo dataUltimoAcesso
     */
    public DateTime getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    /**
     * Define o atributo dataUltimoAcesso
     */
    public void setDataUltimoAcesso(DateTime dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    /**
     * @return o atributo dataCancelamento
     */
    public DateTime getDataCancelamento() {
        return dataCancelamento;
    }

    /**
     * Define o atributo dataCancelamento
     */
    public void setDataCancelamento(DateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    /**
     * @return o atributo codSituacaoConvenio
     */
    public Long getCodSituacaoConvenio() {
        return codSituacaoConvenio;
    }

    /**
     * Define o atributo codSituacaoConvenio
     */
    public void setCodSituacaoConvenio(Long codSituacaoConvenio) {
        this.codSituacaoConvenio = codSituacaoConvenio;
    }

    /**
     * @return o atributo descChaveAcesso
     */
    public String getDescChaveAcesso() {
        return descChaveAcesso;
    }

    /**
     * Define o atributo descChaveAcesso
     */
    public void setDescChaveAcesso(String descChaveAcesso) {
        this.descChaveAcesso = descChaveAcesso;
    }

    /**
     * @return o atributo descSituacao
     */
    public String getDescSituacao() {
        return descSituacao;
    }

    /**
     * Define o atributo descSituacao
     */
    public void setDescSituacao(String descSituacao) {
        this.descSituacao = descSituacao;
    }

    /**
     * @return o atributo selecionado
     */
    public Boolean getSelecionado() {
        return selecionado;
    }

    /**
     * Define o atributo selecionado
     */
    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

}
