package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.Cedente")
public class CedenteVO extends BancoobVO {

    private ClienteVO cliente;
    private Long codLeiauteCliente;
    private Long codPerfilImpressaoViaSite;
    private Long codSituacaoModCedente;
    private DateTime dataCancelamento;
    private DateTime dataConvenio;
    private DateTime dataInicioUtilizacao;
    private DateTime dataMovimento;
    private DateTime dataUltimaAtualizacaoAplicacao;
    private DateTime dataUltimaAtualizacaoEstrutura;
    private String descChaveAcessoCliente;
    private Boolean emiteBoletoSiteProprio;
    private Long idModalidadeCustodiaCheque;
    private Long idPerfil;
    private Long idProduto;
    private Long numCliente;
    private Long numCooperativa;
    private Long numMaxTituloLiberar;
    private Long numPac;
    private Long qtdDiaLimiteImpressaoTitVencido;
    private Long numBanco;
    private Long codLocalConvenio;
    private ListaItemLegadoVO situacao;
    private Boolean utilizaBancoCorrespondente;
    private Long codLayoutArquivo240;
    private Long codLayoutLote240;
    private Long numSeqArquivoCnab240;
    private Long numSeqArquivoCnab400;

    /**
     * @return o atributo cliente
     */
    public ClienteVO getCliente() {
        return cliente;
    }

    /**
     * Define o atributo cliente
     */
    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return o atributo codLeiauteCliente
     */
    public Long getCodLeiauteCliente() {
        return codLeiauteCliente;
    }

    /**
     * Define o atributo codLeiauteCliente
     */
    public void setCodLeiauteCliente(Long codLeiauteCliente) {
        this.codLeiauteCliente = codLeiauteCliente;
    }

    /**
     * @return o atributo codPerfilImpressaoViaSite
     */
    public Long getCodPerfilImpressaoViaSite() {
        return codPerfilImpressaoViaSite;
    }

    /**
     * Define o atributo codPerfilImpressaoViaSite
     */
    public void setCodPerfilImpressaoViaSite(Long codPerfilImpressaoViaSite) {
        this.codPerfilImpressaoViaSite = codPerfilImpressaoViaSite;
    }

    /**
     * @return o atributo codSituacaoModCedente
     */
    public Long getCodSituacaoModCedente() {
        return codSituacaoModCedente;
    }

    /**
     * Define o atributo codSituacaoModCedente
     */
    public void setCodSituacaoModCedente(Long codSituacaoModCedente) {
        this.codSituacaoModCedente = codSituacaoModCedente;
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
     * @return o atributo dataInicioUtilizacao
     */
    public DateTime getDataInicioUtilizacao() {
        return dataInicioUtilizacao;
    }

    /**
     * Define o atributo dataInicioUtilizacao
     */
    public void setDataInicioUtilizacao(DateTime dataInicioUtilizacao) {
        this.dataInicioUtilizacao = dataInicioUtilizacao;
    }

    /**
     * @return o atributo dataMovimento
     */
    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Define o atributo dataMovimento
     */
    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo dataUltimaAtualizacaoAplicacao
     */
    public DateTime getDataUltimaAtualizacaoAplicacao() {
        return dataUltimaAtualizacaoAplicacao;
    }

    /**
     * Define o atributo dataUltimaAtualizacaoAplicacao
     */
    public void setDataUltimaAtualizacaoAplicacao(DateTime dataUltimaAtualizacaoAplicacao) {
        this.dataUltimaAtualizacaoAplicacao = dataUltimaAtualizacaoAplicacao;
    }

    /**
     * @return o atributo dataUltimaAtualizacaoEstrutura
     */
    public DateTime getDataUltimaAtualizacaoEstrutura() {
        return dataUltimaAtualizacaoEstrutura;
    }

    /**
     * Define o atributo dataUltimaAtualizacaoEstrutura
     */
    public void setDataUltimaAtualizacaoEstrutura(DateTime dataUltimaAtualizacaoEstrutura) {
        this.dataUltimaAtualizacaoEstrutura = dataUltimaAtualizacaoEstrutura;
    }

    /**
     * @return o atributo descChaveAcessoCliente
     */
    public String getDescChaveAcessoCliente() {
        return descChaveAcessoCliente;
    }

    /**
     * Define o atributo descChaveAcessoCliente
     */
    public void setDescChaveAcessoCliente(String descChaveAcessoCliente) {
        this.descChaveAcessoCliente = descChaveAcessoCliente;
    }

    /**
     * @return o atributo emiteBoletoSiteProprio
     */
    public Boolean getEmiteBoletoSiteProprio() {
        return emiteBoletoSiteProprio;
    }

    /**
     * Define o atributo emiteBoletoSiteProprio
     */
    public void setEmiteBoletoSiteProprio(Boolean emiteBoletoSiteProprio) {
        this.emiteBoletoSiteProprio = emiteBoletoSiteProprio;
    }

    /**
     * @return o atributo idModalidadeCustodiaCheque
     */
    public Long getIdModalidadeCustodiaCheque() {
        return idModalidadeCustodiaCheque;
    }

    /**
     * Define o atributo idModalidadeCustodiaCheque
     */
    public void setIdModalidadeCustodiaCheque(Long idModalidadeCustodiaCheque) {
        this.idModalidadeCustodiaCheque = idModalidadeCustodiaCheque;
    }

    /**
     * @return o atributo idPerfil
     */
    public Long getIdPerfil() {
        return idPerfil;
    }

    /**
     * Define o atributo idPerfil
     */
    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

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
     * @return o atributo numMaxTituloLiberar
     */
    public Long getNumMaxTituloLiberar() {
        return numMaxTituloLiberar;
    }

    /**
     * Define o atributo numMaxTituloLiberar
     */
    public void setNumMaxTituloLiberar(Long numMaxTituloLiberar) {
        this.numMaxTituloLiberar = numMaxTituloLiberar;
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
     * @return o atributo qtdDiaLimiteImpressaoTitVencido
     */
    public Long getQtdDiaLimiteImpressaoTitVencido() {
        return qtdDiaLimiteImpressaoTitVencido;
    }

    /**
     * Define o atributo qtdDiaLimiteImpressaoTitVencido
     */
    public void setQtdDiaLimiteImpressaoTitVencido(Long qtdDiaLimiteImpressaoTitVencido) {
        this.qtdDiaLimiteImpressaoTitVencido = qtdDiaLimiteImpressaoTitVencido;
    }

    /**
     * @return o atributo numBanco
     */
    public Long getNumBanco() {
        return numBanco;
    }

    /**
     * Define o atributo numBanco
     */
    public void setNumBanco(Long numBanco) {
        this.numBanco = numBanco;
    }

    /**
     * @return o atributo codLocalConvenio
     */
    public Long getCodLocalConvenio() {
        return codLocalConvenio;
    }

    /**
     * Define o atributo codLocalConvenio
     */
    public void setCodLocalConvenio(Long codLocalConvenio) {
        this.codLocalConvenio = codLocalConvenio;
    }

    /**
     * @return o atributo situacao
     */
    public ListaItemLegadoVO getSituacao() {
        return situacao;
    }

    /**
     * Define o atributo situacao
     */
    public void setSituacao(ListaItemLegadoVO situacao) {
        this.situacao = situacao;
    }

    /**
     * @return o atributo utilizaBancoCorrespondente
     */
    public Boolean getUtilizaBancoCorrespondente() {
        return utilizaBancoCorrespondente;
    }

    /**
     * Define o atributo utilizaBancoCorrespondente
     */
    public void setUtilizaBancoCorrespondente(Boolean utilizaBancoCorrespondente) {
        this.utilizaBancoCorrespondente = utilizaBancoCorrespondente;
    }

    public Long getNumSeqArquivoCnab240() {
        return numSeqArquivoCnab240;
    }

    public void setNumSeqArquivoCnab240(Long numSeqArquivoCnab240) {
        this.numSeqArquivoCnab240 = numSeqArquivoCnab240;
    }

    public Long getNumSeqArquivoCnab400() {
        return numSeqArquivoCnab400;
    }

    public void setNumSeqArquivoCnab400(Long numSeqArquivoCnab400) {
        this.numSeqArquivoCnab400 = numSeqArquivoCnab400;
    }

    public Long getCodLayoutArquivo240() {
        return codLayoutArquivo240;
    }

    public void setCodLayoutArquivo240(Long codLayoutArquivo240) {
        this.codLayoutArquivo240 = codLayoutArquivo240;
    }

    public Long getCodLayoutLote240() {
        return codLayoutLote240;
    }

    public void setCodLayoutLote240(Long codLayoutLote240) {
        this.codLayoutLote240 = codLayoutLote240;
    }

}
