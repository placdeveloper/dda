/**
 * Projeto:         SicoobDDa
 * Camada Projeto:  swdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         BOLETODDABAIXAOPER.java
 * Data Criação:    Jul 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.SDDAStringUtil;

/**
 * DDA.BOLETODDABAIXAOPER
 * 
 * @author George.santos
 */
@Entity
@Table(name = "BOLETODDABAIXAOPER", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDABaixaOperVO")
public class BoletoDDABaixaOper extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBOLETODDABAIXAOPER", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBOLETODDA", nullable = false)
    private BoletoDDA boletoDDA;

    private Long numIdentificadorBaixaOper;

    private Long numRefAtualBaixaOper;

    private Long numSeqAtualBaixaOper;

    private DateTimeDB dataProcessamentoBaixaOper;

    private DateTimeDB dataHoraProcessamentoBaixaOper;

    private BigDecimal valorBaixaOper;

    private String numCodBarrasBaixaOper;

    private Short codCanalPagamento;

    private Short codMeioPagamento;

    @Column(nullable = false)
    private Boolean bolOperacaoContingencia;

    // DDA.SITUACAOBAIXAOPERACIONAL
    private String codSituacaoBaixaOperacional;

    // TODO: rafael.silva - Revisar tipo após recebimento da última versao do manual.
    private Integer codTipoBaixaOperacional;

    private String codPartRecebedorBaixaOperacional;

    private String codIspbPartRecebedorBaixaOperacional;

    private String codTipoPessoaPortador;

    private String numCnpjCpfPortador;

    private Long numRefAtualCadBoleto;

    private DateTimeDB dataCancelamentoBaixaOper;

    private DateTimeDB dataHoraCancelBaixaOper;

    private String numCodBarrasCampoLivre;

    @Transient
    private String descTpBaixaOperacional;

    @Transient
    private String descCanalPagamento;

    @Transient
    private String descMeioPagamento;

    @Transient
    private String descSituacaoBxOperacional;

    @Transient
    private String descBanco;

    @Transient
    private String descBancoOpCompleta;

    /** * */
    public BoletoDDABaixaOper() {
    }

    /**
     * @param boleto
     */
    public BoletoDDABaixaOper(BoletoDDA boleto) {
        this.boletoDDA = boleto;
    }

    public BoletoDDABaixaOper(BoletoDDA boletoDDA, BigInteger numIdentcBaixaOperac, BigInteger numRefAtlBaixaOperac, BigInteger numSeqAtlzBaixaOperac,
            DateTimeDB dataProcessamentoBaixaOper, DateTimeDB dataHoraProcessamentoBaixaOper, BigDecimal vlrBaixaOperacTit, String numCodBarrasBaixaOperac,
            String codSituacaoBaixaOperacional) {
        this.boletoDDA = boletoDDA;
        setNumIdentificadorBaixaOper(numIdentcBaixaOperac);
        setNumRefAtualBaixaOper(numRefAtlBaixaOperac);
        setNumSeqAtualBaixaOper(numSeqAtlzBaixaOperac);
        this.dataProcessamentoBaixaOper = dataProcessamentoBaixaOper;
        this.dataHoraProcessamentoBaixaOper = dataHoraProcessamentoBaixaOper;
        this.valorBaixaOper = vlrBaixaOperacTit;
        this.numCodBarrasBaixaOper = numCodBarrasBaixaOperac;
        this.numCodBarrasCampoLivre = numCodBarrasBaixaOperac.substring(19, 44);
        this.bolOperacaoContingencia = Boolean.FALSE;
        this.codSituacaoBaixaOperacional = codSituacaoBaixaOperacional;
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
     * @return o atributo boletoDDA
     */
    public BoletoDDA getBoletoDDA() {
        return boletoDDA;
    }

    /**
     * Define o atributo boletoDDA
     */
    public void setBoletoDDA(BoletoDDA boletoDDA) {
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
    public DateTimeDB getDataProcessamentoBaixaOper() {
        return dataProcessamentoBaixaOper;
    }

    /**
     * Define o atributo dataProcessamentoBaixaOper
     */
    public void setDataProcessamentoBaixaOper(DateTimeDB dataProcessamentoBaixaOper) {
        this.dataProcessamentoBaixaOper = dataProcessamentoBaixaOper;
    }

    /**
     * @return o atributo dataHoraProcessamentoBaixaOper
     */
    public DateTimeDB getDataHoraProcessamentoBaixaOper() {
        return dataHoraProcessamentoBaixaOper;
    }

    /**
     * Define o atributo dataHoraProcessamentoBaixaOper
     */
    public void setDataHoraProcessamentoBaixaOper(DateTimeDB dataHoraProcessamentoBaixaOper) {
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
     * Define o atributo codCanalPagamento
     */
    public void setCodCanalPagamento(BigInteger codCanalPagamento) {
        if (codCanalPagamento != null) {
            this.codCanalPagamento = codCanalPagamento.shortValue();
        }
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
     * Define o atributo codMeioPagamento
     */
    public void setCodMeioPagamento(BigInteger codMeioPagamento) {
        if (codMeioPagamento != null) {
            this.codMeioPagamento = codMeioPagamento.shortValue();
        }
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
     * Define o atributo bolOperacaoContingencia
     */
    public void setBolOperacaoContingencia(String indContingencia) {
        this.bolOperacaoContingencia = (indContingencia == null ? Boolean.FALSE : indContingencia.equals("S"));
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
     * @param numCnpjCpfPortador
     * @param tipoPessoaEnum void
     * 
     */
    public void setNumCnpjCpfPortador(String numCnpjCpfPortador, TipoPessoaEnum tipoPessoaEnum) {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            this.numCnpjCpfPortador = SDDAStringUtil.incluirZerosCPF(numCnpjCpfPortador);
        } else {
            this.numCnpjCpfPortador = SDDAStringUtil.incluirZerosCNPJ(numCnpjCpfPortador);
        }
    }

    /**
     * @param numCnpjCpfPortador
     * @param codTipoPessoaPortador void
     * 
     */
    public void setNumCnpjCpfCodTipoPessoaPortador(String numCnpjCpfPortador, String codTipoPessoaPortador) {
        if (codTipoPessoaPortador != null) {
            setCodTipoPessoaPortador(codTipoPessoaPortador);
            setNumCnpjCpfPortador(numCnpjCpfPortador, TipoPessoaEnum.getBy(codTipoPessoaPortador));
        }
    }

    public void setNumCnpjCpfCodTipoPessoaPortador(BigInteger numCnpjCpfPortador, String codTipoPessoaPortador) {
        if (codTipoPessoaPortador != null) {
            setCodTipoPessoaPortador(codTipoPessoaPortador);
            setNumCnpjCpfPortador(numCnpjCpfPortador != null ? numCnpjCpfPortador.toString() : null, TipoPessoaEnum.getBy(codTipoPessoaPortador));
        }
    }

    /**
     * @param numIdentificadorBaixaOper void
     * 
     */
    public void setNumIdentificadorBaixaOper(BigInteger numIdentificadorBaixaOper) {
        if (numIdentificadorBaixaOper != null) {
            this.numIdentificadorBaixaOper = numIdentificadorBaixaOper.longValue();
        }

    }

    /**
     * Método responsável por
     * 
     * @param numRefAtualBaixaOper void
     * 
     */
    public void setNumRefAtualBaixaOper(BigInteger numRefAtualBaixaOper) {
        if (numRefAtualBaixaOper != null) {
            this.numRefAtualBaixaOper = numRefAtualBaixaOper.longValue();
        }
    }

    /**
     * @param numSeqAtualBaixaOper void
     * 
     */
    public void setNumSeqAtualBaixaOper(BigInteger numSeqAtualBaixaOper) {
        if (numSeqAtualBaixaOper != null) {
            this.numSeqAtualBaixaOper = numSeqAtualBaixaOper.longValue();
        }
    }

    /**
     * @param tpBaixaOperac void
     * 
     */
    public void setCodTipoBaixaOperacional(String codTipoBaixaOperacional) {
        this.codTipoBaixaOperacional = Integer.parseInt(codTipoBaixaOperacional);
    }

    /**
     * @return the numRefAtualCadBoleto
     */
    public Long getNumRefAtualCadBoleto() {
        return numRefAtualCadBoleto;
    }

    /**
     * @param numRefAtualCadBoleto the numRefAtualCadBoleto to set
     */
    public void setNumRefAtualCadBoleto(Long numRefAtualCadBoleto) {
        this.numRefAtualCadBoleto = numRefAtualCadBoleto;
    }

    public void setNumRefAtualCadBoleto(BigInteger numRefAtualCadBoleto) {
        if (numRefAtualCadBoleto != null) {
            this.numRefAtualCadBoleto = numRefAtualCadBoleto.longValue();
        }
    }

    /**
     * @return o atributo dataCancelamentoBaixaOper
     */
    public DateTimeDB getDataCancelamentoBaixaOper() {
        return dataCancelamentoBaixaOper;
    }

    /**
     * Define o atributo dataCancelamentoBaixaOper
     */
    public void setDataCancelamentoBaixaOper(DateTimeDB dataCancelamentoBaixaOper) {
        this.dataCancelamentoBaixaOper = dataCancelamentoBaixaOper;
    }

    /**
     * @return o atributo dataHoraCancelBaixaOper
     */
    public DateTimeDB getDataHoraCancelBaixaOper() {
        return dataHoraCancelBaixaOper;
    }

    /**
     * Define o atributo dataHoraCancelBaixaOper
     */
    public void setDataHoraCancelBaixaOper(DateTimeDB dataHoraCancelBaixaOper) {
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
     * Define o atributo numCnpjCpfPortador
     */
    public void setNumCnpjCpfPortador(String numCnpjCpfPortador) {
        this.numCnpjCpfPortador = numCnpjCpfPortador;
    }

    /**
     * Define o atributo descBancoOpCompleta
     */
    public void setDescBancoOpCompleta(String descBancoOpCompleta) {
        this.descBancoOpCompleta = descBancoOpCompleta;
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
