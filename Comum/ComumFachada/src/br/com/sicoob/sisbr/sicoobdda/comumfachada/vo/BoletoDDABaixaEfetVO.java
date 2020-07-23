package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * BoletoDDABaixaEfetVO é responsável por
 * 
 * @author george.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaEfet")
public class BoletoDDABaixaEfetVO extends BancoobVO {

    private Long id;

    private BoletoDDAVO boletoDDA;

    private Long numIdentificadorBaixaEfet;

    private Long numRefAtualBaixaEfet;

    private Long numSeqAtualBaixaEfet;

    private DateTime dataProcessamentoBaixaEfet;

    private DateTime dataHoraProcessamentoBaixaEfet;

    private BigDecimal valorBaixaEfet;

    private String numCodBarrasBaixaEfet;

    private Integer codCanalPagamento;

    private Integer codMeioPagamento;

    private Long numIdentificadorBaixaOperacional;

    private Integer codTipoBaixaEfetiva;

    private String codIspbPartRecebedorBaixaEfetiva;

    private String codPartRecebedorBaixaEfetiva;

    private String descTpBaixaEfetiva;

    private String descCanalPagamento;

    private String descMeioPagamento;

    private String descBanco;

    private String descBancoEfCompleta;

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
     * @return o atributo numIdentificadorBaixaEfet
     */
    public Long getNumIdentificadorBaixaEfet() {
        return numIdentificadorBaixaEfet;
    }

    /**
     * Define o atributo numIdentificadorBaixaEfet
     */
    public void setNumIdentificadorBaixaEfet(Long numIdentificadorBaixaEfet) {
        this.numIdentificadorBaixaEfet = numIdentificadorBaixaEfet;
    }

    /**
     * @return o atributo numRefAtualBaixaEfet
     */
    public Long getNumRefAtualBaixaEfet() {
        return numRefAtualBaixaEfet;
    }

    /**
     * Define o atributo numRefAtualBaixaEfet
     */
    public void setNumRefAtualBaixaEfet(Long numRefAtualBaixaEfet) {
        this.numRefAtualBaixaEfet = numRefAtualBaixaEfet;
    }

    /**
     * @return o atributo numSeqAtualBaixaEfet
     */
    public Long getNumSeqAtualBaixaEfet() {
        return numSeqAtualBaixaEfet;
    }

    /**
     * Define o atributo numSeqAtualBaixaEfet
     */
    public void setNumSeqAtualBaixaEfet(Long numSeqAtualBaixaEfet) {
        this.numSeqAtualBaixaEfet = numSeqAtualBaixaEfet;
    }

    /**
     * @return o atributo dataProcessamentoBaixaEfet
     */
    public DateTime getDataProcessamentoBaixaEfet() {
        return dataProcessamentoBaixaEfet;
    }

    /**
     * Define o atributo dataProcessamentoBaixaEfet
     */
    public void setDataProcessamentoBaixaEfet(DateTime dataProcessamentoBaixaEfet) {
        this.dataProcessamentoBaixaEfet = dataProcessamentoBaixaEfet;
    }

    /**
     * @return o atributo dataHoraProcessamentoBaixaEfet
     */
    public DateTime getDataHoraProcessamentoBaixaEfet() {
        return dataHoraProcessamentoBaixaEfet;
    }

    /**
     * Define o atributo dataHoraProcessamentoBaixaEfet
     */
    public void setDataHoraProcessamentoBaixaEfet(DateTime dataHoraProcessamentoBaixaEfet) {
        this.dataHoraProcessamentoBaixaEfet = dataHoraProcessamentoBaixaEfet;
    }

    /**
     * @return o atributo valorBaixaEfet
     */
    public BigDecimal getValorBaixaEfet() {
        return valorBaixaEfet;
    }

    /**
     * Define o atributo valorBaixaEfet
     */
    public void setValorBaixaEfet(BigDecimal valorBaixaEfet) {
        this.valorBaixaEfet = valorBaixaEfet;
    }

    /**
     * @return o atributo numCodBarrasBaixaEfet
     */
    public String getNumCodBarrasBaixaEfet() {
        return numCodBarrasBaixaEfet;
    }

    /**
     * Define o atributo numCodBarrasBaixaEfet
     */
    public void setNumCodBarrasBaixaEfet(String numCodBarrasBaixaEfet) {
        this.numCodBarrasBaixaEfet = numCodBarrasBaixaEfet;
    }

    /**
     * @return o atributo codCanalPagamento
     */
    public Integer getCodCanalPagamento() {
        return codCanalPagamento;
    }

    /**
     * Define o atributo codCanalPagamento
     */
    public void setCodCanalPagamento(Integer codCanalPagamento) {
        this.codCanalPagamento = codCanalPagamento;
    }

    /**
     * @return o atributo codMeioPagamento
     */
    public Integer getCodMeioPagamento() {
        return codMeioPagamento;
    }

    /**
     * Define o atributo codMeioPagamento
     */
    public void setCodMeioPagamento(Integer codMeioPagamento) {
        this.codMeioPagamento = codMeioPagamento;
    }

    /**
     * @return o atributo numIdentificadorBaixaOperacional
     */
    public Long getNumIdentificadorBaixaOperacional() {
        return numIdentificadorBaixaOperacional;
    }

    /**
     * Define o atributo numIdentificadorBaixaOperacional
     */
    public void setNumIdentificadorBaixaOperacional(Long numIdentificadorBaixaOperacional) {
        this.numIdentificadorBaixaOperacional = numIdentificadorBaixaOperacional;
    }

    /**
     * @return o atributo codTipoBaixaEfetiva
     */
    public Integer getCodTipoBaixaEfetiva() {
        return codTipoBaixaEfetiva;
    }

    /**
     * Define o atributo codTipoBaixaEfetiva
     */
    public void setCodTipoBaixaEfetiva(Integer codTipoBaixaEfetiva) {
        this.codTipoBaixaEfetiva = codTipoBaixaEfetiva;
    }

    /**
     * @return o atributo codIspbPartRecebedorBaixaEfetiva
     */
    public String getCodIspbPartRecebedorBaixaEfetiva() {
        return codIspbPartRecebedorBaixaEfetiva;
    }

    /**
     * Define o atributo codIspbPartRecebedorBaixaEfetiva
     */
    public void setCodIspbPartRecebedorBaixaEfetiva(String codIspbPartRecebedorBaixaEfetiva) {
        this.codIspbPartRecebedorBaixaEfetiva = codIspbPartRecebedorBaixaEfetiva;
    }

    /**
     * @return o atributo codPartRecebedorBaixaEfetiva
     */
    public String getCodPartRecebedorBaixaEfetiva() {
        return codPartRecebedorBaixaEfetiva;
    }

    /**
     * Define o atributo codPartRecebedorBaixaEfetiva
     */
    public void setCodPartRecebedorBaixaEfetiva(String codPartRecebedorBaixaEfetiva) {
        this.codPartRecebedorBaixaEfetiva = codPartRecebedorBaixaEfetiva;
    }

    /**
     * @return o atributo descTpBaixaEfetiva
     */
    public String getDescTpBaixaEfetiva() {
        return descTpBaixaEfetiva;
    }

    /**
     * Define o atributo descTpBaixaEfetiva
     */
    public void setDescTpBaixaEfetiva(String descTpBaixaEfetiva) {
        this.descTpBaixaEfetiva = descTpBaixaEfetiva;
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
    public String getDescBancoEfCompleta() {
        return this.descBancoEfCompleta = this.codPartRecebedorBaixaEfetiva + " - " + this.descBanco;
    }
}
