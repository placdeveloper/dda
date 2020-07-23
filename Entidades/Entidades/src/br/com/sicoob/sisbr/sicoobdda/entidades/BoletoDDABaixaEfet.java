/**
 * Projeto:         SicoobDDa
 * Camada Projeto:  swdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         BeneficiarioInstituicao.java
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

/**
 * DDA.BOLETODDABAIXAEFET
 * 
 * @author George.santos
 */
/**
 * BoletoDDABaixaEfet é responsável por 
 * 
 * @author George.Santos
 */
/**
 * BoletoDDABaixaEfet é responsável por
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "BOLETODDABAIXAEFET", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDABaixaEfetVO")
public class BoletoDDABaixaEfet extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBOLETODDABAIXAEFET", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBOLETODDA", nullable = false)
    private BoletoDDA boletoDDA;

    private Long numIdentificadorBaixaEfet;

    private Long numRefAtualBaixaEfet;

    private Long numSeqAtualBaixaEfet;

    private DateTimeDB dataProcessamentoBaixaEfet;

    private DateTimeDB dataHoraProcessamentoBaixaEfet;

    private BigDecimal valorBaixaEfet;

    private String numCodBarrasBaixaEfet;

    private Integer codCanalPagamento;

    private Integer codMeioPagamento;

    private Long numIdentificadorBaixaOperacional;

    private Integer codTipoBaixaEfetiva;

    private String codIspbPartRecebedorBaixaEfetiva;

    private String codPartRecebedorBaixaEfetiva;

    private String numCodBarrasCampoLivre;

    @Transient
    private String descTpBaixaEfetiva;

    @Transient
    private String descCanalPagamento;

    @Transient
    private String descMeioPagamento;

    @Transient
    private String descBanco;

    @Transient
    private String descBancoEfCompleta;

    public BoletoDDABaixaEfet() {
    }

    /**
     * 
     * Utilizado no converter do Boleto das mensagens GrupoADDA110RETTitActoComplexType e DDA0110R1ComplexType
     * 
     * @param boletoDDA
     * @param numIdentcBaixaEft
     * @param numRefAtlBaixaEft
     * @param numSeqAtlzBaixaEft
     * @param dataProcessamentoBaixaEfet
     * @param dataHoraProcessamentoBaixaEfet
     * @param vlrBaixaEftTit
     * @param numCodBarrasBaixaEft
     * @param canPgto
     * @param meioPgto
     */
    public BoletoDDABaixaEfet(BoletoDDA boletoDDA, BigInteger numIdentcBaixaEft, BigInteger numRefAtlBaixaEft, BigInteger numSeqAtlzBaixaEft,
            DateTimeDB dataProcessamentoBaixaEfet, DateTimeDB dataHoraProcessamentoBaixaEfet, BigDecimal vlrBaixaEftTit, String numCodBarrasBaixaEft, BigInteger canPgto,
            BigInteger meioPgto) {
        this.boletoDDA = boletoDDA;
        setNumIdentificadorBaixaEfet(numIdentcBaixaEft);
        setNumRefAtualBaixaEfet(numRefAtlBaixaEft);
        setNumSeqAtualBaixaEfet(numSeqAtlzBaixaEft);
        this.dataProcessamentoBaixaEfet = dataProcessamentoBaixaEfet;
        this.dataHoraProcessamentoBaixaEfet = dataHoraProcessamentoBaixaEfet;
        this.valorBaixaEfet = vlrBaixaEftTit;
        this.numCodBarrasBaixaEfet = numCodBarrasBaixaEft;
        setCodCanalPagamento(canPgto);
        setCodMeioPagamento(meioPgto);
    }

    /**
     * 
     * Utilizado no converter do Boleto das mensagens GrupoDDA0127TitComplexType, GrupoADDA127TitComplexType, GrupoDDA0106R1TitComplexType
     * 
     * @param boletoDDA
     * @param numIdentcBaixaEft
     * @param numRefAtlBaixaEft
     * @param numSeqAtlzBaixaEft
     * @param dataProcessamentoBaixaEfet
     * @param dataHoraProcessamentoBaixaEfet
     * @param vlrBaixaEftTit
     * @param numCodBarrasBaixaEft
     * @param canPgto
     * @param meioPgto
     * @param numIdentificadorBaixaOperacional
     * @param codTipoBaixaEfetiva
     * @param codIspbPartRecebedorBaixaEfetiva
     * @param codPartRecebedorBaixaEfetiva
     */
    public BoletoDDABaixaEfet(BoletoDDA boletoDDA, BigInteger numIdentcBaixaEft, BigInteger numRefAtlBaixaEft, BigInteger numSeqAtlzBaixaEft,
            DateTimeDB dataProcessamentoBaixaEfet, DateTimeDB dataHoraProcessamentoBaixaEfet, BigDecimal vlrBaixaEftTit, String numCodBarrasBaixaEft, BigInteger canPgto,
            BigInteger meioPgto, BigInteger numIdentificadorBaixaOperacional, String codTipoBaixaEfetiva, String codIspbPartRecebedorBaixaEfetiva,
            String codPartRecebedorBaixaEfetiva) {
        this.boletoDDA = boletoDDA;
        setNumIdentificadorBaixaEfet(numIdentcBaixaEft);
        setNumRefAtualBaixaEfet(numRefAtlBaixaEft);
        setNumSeqAtualBaixaEfet(numSeqAtlzBaixaEft);
        this.dataProcessamentoBaixaEfet = dataProcessamentoBaixaEfet;
        this.dataHoraProcessamentoBaixaEfet = dataHoraProcessamentoBaixaEfet;
        this.valorBaixaEfet = vlrBaixaEftTit;
        this.numCodBarrasBaixaEfet = numCodBarrasBaixaEft;
        this.numCodBarrasCampoLivre = numCodBarrasBaixaEft.substring(19, 44);
        setCodCanalPagamento(canPgto);
        setCodMeioPagamento(meioPgto);
        setNumIdentificadorBaixaOperacional(numIdentificadorBaixaOperacional);
        setCodTipoBaixaEfetiva(codTipoBaixaEfetiva);
        this.codIspbPartRecebedorBaixaEfetiva = codIspbPartRecebedorBaixaEfetiva;
        this.codPartRecebedorBaixaEfetiva = codPartRecebedorBaixaEfetiva;
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
    public DateTimeDB getDataProcessamentoBaixaEfet() {
        return dataProcessamentoBaixaEfet;
    }

    /**
     * Define o atributo dataProcessamentoBaixaEfet
     */
    public void setDataProcessamentoBaixaEfet(DateTimeDB dataProcessamentoBaixaEfet) {
        this.dataProcessamentoBaixaEfet = dataProcessamentoBaixaEfet;
    }

    /**
     * @return o atributo dataHoraProcessamentoBaixaEfet
     */
    public DateTimeDB getDataHoraProcessamentoBaixaEfet() {
        return dataHoraProcessamentoBaixaEfet;
    }

    /**
     * Define o atributo dataHoraProcessamentoBaixaEfet
     */
    public void setDataHoraProcessamentoBaixaEfet(DateTimeDB dataHoraProcessamentoBaixaEfet) {
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
     * Método responsável por
     * 
     * @param numIdentificadorBaixaEfet void
     * 
     */
    public void setNumIdentificadorBaixaEfet(BigInteger numIdentificadorBaixaEfet) {
        if (numIdentificadorBaixaEfet != null) {
            this.numIdentificadorBaixaEfet = numIdentificadorBaixaEfet.longValue();
        }
    }

    /**
     * Método responsável por
     * 
     * @param numRefAtualBaixaEfet void
     * 
     */
    public void setNumRefAtualBaixaEfet(BigInteger numRefAtualBaixaEfet) {
        if (numRefAtualBaixaEfet != null) {
            this.numRefAtualBaixaEfet = numRefAtualBaixaEfet.longValue();
        }
    }

    /**
     * Método responsável por
     * 
     * @param numSeqAtualBaixaEfet void
     * 
     */
    public void setNumSeqAtualBaixaEfet(BigInteger numSeqAtualBaixaEfet) {
        if (numSeqAtualBaixaEfet != null) {
            this.numSeqAtualBaixaEfet = numSeqAtualBaixaEfet.longValue();
        }
    }

    /**
     * Método responsável por
     * 
     * @param numIdentificadorBaixaOperacional void
     * 
     */
    public void setNumIdentificadorBaixaOperacional(BigInteger numIdentificadorBaixaOperacional) {
        if (numIdentificadorBaixaOperacional != null) {
            this.numIdentificadorBaixaOperacional = numIdentificadorBaixaOperacional.longValue();
        }
    }

    /**
     * Método responsável por
     * 
     * @param codTipoBaixaEfetiva void
     * 
     */
    public void setCodTipoBaixaEfetiva(String codTipoBaixaEfetiva) {
        if (codTipoBaixaEfetiva != null) {
            this.codTipoBaixaEfetiva = Integer.valueOf(codTipoBaixaEfetiva);
        }
    }

    /**
     * Método responsável por
     * 
     * @param codCanalPagamento void
     * 
     */
    public void setCodCanalPagamento(BigInteger codCanalPagamento) {
        if (codCanalPagamento != null) {
            this.codCanalPagamento = codCanalPagamento.intValue();
        }
    }

    /**
     * Método responsável por
     * 
     * @param codMeioPagamento void
     * 
     */
    public void setCodMeioPagamento(BigInteger codMeioPagamento) {
        if (codMeioPagamento != null) {
            this.codMeioPagamento = codMeioPagamento.intValue();
        }
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
