package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * BoletoDDABaixaOperVO é responsável por
 * 
 * @author george.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper")
public class BoletoDDABaixaOperVO extends BancoobVO {

    private Long id;

    private BoletoDDAVO boletoDDA;

    private Long numIdentificadorBaixaOper;

    private Long numRefAtualBaixaOper;

    private Long numSeqAtualBaixaOper;

    private DateTime dataProcessamentoBaixaOper;

    private DateTime dataHoraProcessamentoBaixaOper;

    private BigDecimal valorBaixaOper;

    private String numCodBarrasBaixaOper;

    private Short codCanalPagamento;

    private Short codMeioPagamento;

    private Boolean bolOperacaoContingencia;

    private String codSituacaoBaixaOperacional;

    private Integer codTipoBaixaOperacional;

    private String codPartRecebedorBaixaOperacional;

    private String codIspbPartRecebedorBaixaOperacional;

    private String codTipoPessoaPortador;

    private String numCnpjCpfPortador;

    private Long numRefAtualCadBoleto;

    private DateTime dataCancelamentoBaixaOper;

    private DateTime dataHoraCancelBaixaOper;

    private String numCodBarrasCampoLivre;

    private String descTpBaixaOperacional;

    private String descCanalPagamento;

    private String descMeioPagamento;

    private String descSituacaoBxOperacional;

    private String descBanco;

    private String descBancoOpCompleta;

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
     * @return o atributo boletoDDA
     */
    public BoletoDDAVO getBoletoDDA() {
        return boletoDDA;
    }

    /**
     * Define o atributo boletoDDA
     */
    public void setBoletoDDA(BoletoDDAVO boletoDDA) {
        this.boletoDDA = boletoDDA;
    }

    /**
     * @return o atributo numIdentificadorBaixaOper
     */
    public Long getNumIdentificadorBaixaOper() {
        return numIdentificadorBaixaOper;
    }

    /**
     * Define o atributo numIdentificadorBaixaOper
     */
    public void setNumIdentificadorBaixaOper(Long numIdentificadorBaixaOper) {
        this.numIdentificadorBaixaOper = numIdentificadorBaixaOper;
    }

    /**
     * @return o atributo numRefAtualBaixaOper
     */
    public Long getNumRefAtualBaixaOper() {
        return numRefAtualBaixaOper;
    }

    /**
     * Define o atributo numRefAtualBaixaOper
     */
    public void setNumRefAtualBaixaOper(Long numRefAtualBaixaOper) {
        this.numRefAtualBaixaOper = numRefAtualBaixaOper;
    }

    /**
     * @return o atributo numSeqAtualBaixaOper
     */
    public Long getNumSeqAtualBaixaOper() {
        return numSeqAtualBaixaOper;
    }

    /**
     * Define o atributo numSeqAtualBaixaOper
     */
    public void setNumSeqAtualBaixaOper(Long numSeqAtualBaixaOper) {
        this.numSeqAtualBaixaOper = numSeqAtualBaixaOper;
    }

    /**
     * @return o atributo dataProcessamentoBaixaOper
     */
    public DateTime getDataProcessamentoBaixaOper() {
        return dataProcessamentoBaixaOper;
    }

    /**
     * Define o atributo dataProcessamentoBaixaOper
     */
    public void setDataProcessamentoBaixaOper(DateTime dataProcessamentoBaixaOper) {
        this.dataProcessamentoBaixaOper = dataProcessamentoBaixaOper;
    }

    /**
     * @return dataCancelamentoBaixaOper
     */
    public DateTime getDataCancelamentoBaixaOper() {
        return dataCancelamentoBaixaOper;
    }

    /**
     * @param dataCancelamentoBaixaOper
     */
    public void setDataCancelamentoBaixaOper(DateTime dataCancelamentoBaixaOper) {
        this.dataCancelamentoBaixaOper = dataCancelamentoBaixaOper;
    }

    /**
     * @return dataHoraCancelBaixaOper
     */
    public DateTime getDataHoraCancelBaixaOper() {
        return dataHoraCancelBaixaOper;
    }

    /**
     * @param dataHoraCancelBaixaOper
     */
    public void setDataHoraCancelBaixaOper(DateTime dataHoraCancelBaixaOper) {
        this.dataHoraCancelBaixaOper = dataHoraCancelBaixaOper;
    }

    /**
     * @return numCodBarrasCampoLivre
     */
    public String getNumCodBarrasCampoLivre() {
        return numCodBarrasCampoLivre;
    }

    /**
     * @param numCodBarrasCampoLivre
     */
    public void setNumCodBarrasCampoLivre(String numCodBarrasCampoLivre) {
        this.numCodBarrasCampoLivre = numCodBarrasCampoLivre;
    }

    /**
     * @return o atributo dataHoraProcessamentoBaixaOper
     */
    public DateTime getDataHoraProcessamentoBaixaOper() {
        return dataHoraProcessamentoBaixaOper;
    }

    /**
     * Define o atributo dataHoraProcessamentoBaixaOper
     */
    public void setDataHoraProcessamentoBaixaOper(DateTime dataHoraProcessamentoBaixaOper) {
        this.dataHoraProcessamentoBaixaOper = dataHoraProcessamentoBaixaOper;
    }

    /**
     * @return o atributo valorBaixaOper
     */
    public BigDecimal getValorBaixaOper() {
        return valorBaixaOper;
    }

    /**
     * Define o atributo valorBaixaOper
     */
    public void setValorBaixaOper(BigDecimal valorBaixaOper) {
        this.valorBaixaOper = valorBaixaOper;
    }

    /**
     * @return o atributo numCodBarrasBaixaOper
     */
    public String getNumCodBarrasBaixaOper() {
        return numCodBarrasBaixaOper;
    }

    /**
     * Define o atributo numCodBarrasBaixaOper
     */
    public void setNumCodBarrasBaixaOper(String numCodBarrasBaixaOper) {
        this.numCodBarrasBaixaOper = numCodBarrasBaixaOper;
    }

    /**
     * @return o atributo codCanalPagamento
     */
    public Short getCodCanalPagamento() {
        return codCanalPagamento;
    }

    /**
     * Define o atributo codCanalPagamento
     */
    public void setCodCanalPagamento(Short codCanalPagamento) {
        this.codCanalPagamento = codCanalPagamento;
    }

    /**
     * @return o atributo codMeioPagamento
     */
    public Short getCodMeioPagamento() {
        return codMeioPagamento;
    }

    /**
     * Define o atributo codMeioPagamento
     */
    public void setCodMeioPagamento(Short codMeioPagamento) {
        this.codMeioPagamento = codMeioPagamento;
    }

    /**
     * @return o atributo bolOperacaoContingencia
     */
    public Boolean getBolOperacaoContingencia() {
        return bolOperacaoContingencia;
    }

    /**
     * Define o atributo bolOperacaoContingencia
     */
    public void setBolOperacaoContingencia(Boolean bolOperacaoContingencia) {
        this.bolOperacaoContingencia = bolOperacaoContingencia;
    }

    /**
     * @return o atributo codSituacaoBaixaOperacional
     */
    public String getCodSituacaoBaixaOperacional() {
        return codSituacaoBaixaOperacional;
    }

    /**
     * Define o atributo codSituacaoBaixaOperacional
     */
    public void setCodSituacaoBaixaOperacional(String codSituacaoBaixaOperacional) {
        this.codSituacaoBaixaOperacional = codSituacaoBaixaOperacional;
    }

    /**
     * @return o atributo codTipoBaixaOperacional
     */
    public Integer getCodTipoBaixaOperacional() {
        return codTipoBaixaOperacional;
    }

    /**
     * Define o atributo codTipoBaixaOperacional
     */
    public void setCodTipoBaixaOperacional(Integer codTipoBaixaOperacional) {
        this.codTipoBaixaOperacional = codTipoBaixaOperacional;
    }

    /**
     * @return o atributo codPartRecebedorBaixaOperacional
     */
    public String getCodPartRecebedorBaixaOperacional() {
        return codPartRecebedorBaixaOperacional;
    }

    /**
     * Define o atributo codPartRecebedorBaixaOperacional
     */
    public void setCodPartRecebedorBaixaOperacional(String codPartRecebedorBaixaOperacional) {
        this.codPartRecebedorBaixaOperacional = codPartRecebedorBaixaOperacional;
    }

    /**
     * @return o atributo codIspbPartRecebedorBaixaOperacional
     */
    public String getCodIspbPartRecebedorBaixaOperacional() {
        return codIspbPartRecebedorBaixaOperacional;
    }

    /**
     * Define o atributo codIspbPartRecebedorBaixaOperacional
     */
    public void setCodIspbPartRecebedorBaixaOperacional(String codIspbPartRecebedorBaixaOperacional) {
        this.codIspbPartRecebedorBaixaOperacional = codIspbPartRecebedorBaixaOperacional;
    }

    /**
     * @return o atributo codTipoPessoaPortador
     */
    public String getCodTipoPessoaPortador() {
        return codTipoPessoaPortador;
    }

    /**
     * Define o atributo codTipoPessoaPortador
     */
    public void setCodTipoPessoaPortador(String codTipoPessoaPortador) {
        this.codTipoPessoaPortador = codTipoPessoaPortador;
    }

    /**
     * @return o atributo numCnpjCpfPortador
     */
    public String getNumCnpjCpfPortador() {
        return numCnpjCpfPortador;
    }

    /**
     * Define o atributo numCnpjCpfPortador
     */
    public void setNumCnpjCpfPortador(String numCnpjCpfPortador) {
        this.numCnpjCpfPortador = numCnpjCpfPortador;
    }

    /**
     * @return o atributo numRefAtualCadBoleto
     */
    public Long getNumRefAtualCadBoleto() {
        return numRefAtualCadBoleto;
    }

    /**
     * Define o atributo numRefAtualCadBoleto
     */
    public void setNumRefAtualCadBoleto(Long numRefAtualCadBoleto) {
        this.numRefAtualCadBoleto = numRefAtualCadBoleto;
    }

    /**
     * @return o atributo descTpBaixaOperacional
     */
    public String getDescTpBaixaOperacional() {
        return descTpBaixaOperacional;
    }

    /**
     * Define o atributo descTpBaixaOperacional
     */
    public void setDescTpBaixaOperacional(String descTpBaixaOperacional) {
        this.descTpBaixaOperacional = descTpBaixaOperacional;
    }

    /**
     * @return o atributo descCanalPagamento
     */
    public String getDescCanalPagamento() {
        return descCanalPagamento;
    }

    /**
     * Define o atributo descCanalPagamento
     */
    public void setDescCanalPagamento(String descCanalPagamento) {
        this.descCanalPagamento = descCanalPagamento;
    }

    /**
     * @return o atributo descMeioPagamento
     */
    public String getDescMeioPagamento() {
        return descMeioPagamento;
    }

    /**
     * Define o atributo descMeioPagamento
     */
    public void setDescMeioPagamento(String descMeioPagamento) {
        this.descMeioPagamento = descMeioPagamento;
    }

    /**
     * @return o atributo descSituacaoBxOperacional
     */
    public String getDescSituacaoBxOperacional() {
        return descSituacaoBxOperacional;
    }

    /**
     * Define o atributo descSituacaoBxOperacional
     */
    public void setDescSituacaoBxOperacional(String descSituacaoBxOperacional) {
        this.descSituacaoBxOperacional = descSituacaoBxOperacional;
    }

    /**
     * @return o atributo descBanco
     */
    public String getDescBanco() {
        return descBanco;
    }

    /**
     * Define o atributo descBanco
     */
    public void setDescBanco(String descBanco) {
        this.descBanco = descBanco;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String getDescBancoOpCompleta() {
        return this.descBancoOpCompleta = this.codPartRecebedorBaixaOperacional + " - " + this.descBanco;
    }
}
