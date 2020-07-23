/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         MensagemDDAConsultaBoleto.java
 * Data Criação:    Nov 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDAConsultaBoleto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "MensagemDDAConsultaBoleto", schema = "DDA")
public class MensagemDDAConsultaBoleto extends SicoobDDAEntidade implements IMensagemDDA {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "primarykey")
    @Column(name = "idMensagemDDA", unique = true, nullable = false)
    @GenericGenerator(name = "primarykey", strategy = "foreign", parameters = { @Parameter(name = "property", value = "mensagemDDA") })
    private Long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private MensagemDDA mensagemDDA;

    private String numCodigoBarra;

    private String numCodBarrasCampoLivre;

    private String numCpfCnpjPagador;

    private Short codSituacaoBoleto;

    @Column(name = "NUMIDENTIFICADORBOLETOCIPINICIAL")
    private Long numIdentBoletoInicial;

    @Column(name = "NUMIDENTIFICADORBOLETOCIPFINAL")
    private Long numIdentBoletoFinal;

    private String codTipoBoletoConsultado;

    // Problemas com a conversão de String para Long no Flex.
    @Transient
    private String numIdentBoletoInicialStr;
    @Transient
    private String numIdentBoletoFinalStr;

    /**
     * 
     */
    public MensagemDDAConsultaBoleto() {
        super();
    }

    /**
     * @param mensagemDDA
     * @param numCodigoBarra
     */
    public MensagemDDAConsultaBoleto(String numCodigoBarra) {
        super();
        this.numCodigoBarra = numCodigoBarra;
        this.numCodBarrasCampoLivre = numCodigoBarra != null ? numCodigoBarra.substring(19, 44) : null;
    }

    /**
     * @param mensagemDDA
     * @param numCodigoBarra
     */
    public MensagemDDAConsultaBoleto(MensagemDDA mensagemDDA, String numCodigoBarra) {
        this(numCodigoBarra);
        this.mensagemDDA = mensagemDDA;
        this.numCodBarrasCampoLivre = numCodigoBarra != null ? numCodigoBarra.substring(19, 44) : null;
    }

    /**
     * @param numCodigoBarra
     * @param codSituacaoBoleto
     */
    public MensagemDDAConsultaBoleto(String numCodigoBarra, Short codSituacaoBoleto, String codTipoBoletoConsultado) {
        this(numCodigoBarra);
        this.codSituacaoBoleto = codSituacaoBoleto;
        this.codTipoBoletoConsultado = codTipoBoletoConsultado;
        this.numCodBarrasCampoLivre = numCodigoBarra != null ? numCodigoBarra.substring(19, 44) : null;
    }

    /**
     * @param numIdentBoletoInicial
     * @param numIdentBoletoFinal
     */
    public MensagemDDAConsultaBoleto(Long numIdentBoletoInicial, Long numIdentBoletoFinal, String codTipoBoletoConsultado) {
        super();
        this.numIdentBoletoInicial = numIdentBoletoInicial;
        this.numIdentBoletoFinal = numIdentBoletoFinal;
        this.codTipoBoletoConsultado = codTipoBoletoConsultado;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the mensagemDDA
     */
    public MensagemDDA getMensagemDDA() {
        return mensagemDDA;
    }

    /**
     * @param mensagemDDA the mensagemDDA to set
     */
    public void setMensagemDDA(MensagemDDA mensagemDDA) {
        this.mensagemDDA = mensagemDDA;
    }

    /**
     * @return the numCodigoBarra
     */
    public String getNumCodigoBarra() {
        return numCodigoBarra;
    }

    /**
     * @param numCodigoBarra the numCodigoBarra to set
     */
    public void setNumCodigoBarra(String numCodigoBarra) {
        this.numCodigoBarra = numCodigoBarra;
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
     * @return the numCpfCnpjPagador
     */
    public String getNumCpfCnpjPagador() {
        return numCpfCnpjPagador;
    }

    /**
     * @param numCpfCnpjPagador the numCpfCnpjPagador to set
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA#getIdEventoCadastro()
     */
    public Long getIdEventoCadastro() {
        return null;
    }

    /**
     * @return o atributo codSituacaoBoleto
     */
    public Short getCodSituacaoBoleto() {
        return codSituacaoBoleto;
    }

    /**
     * Define o atributo codSituacaoBoleto
     */
    public void setCodSituacaoBoleto(Short codSituacaoBoleto) {
        this.codSituacaoBoleto = codSituacaoBoleto;
    }

    /**
     * @return o atributo numIdentBoletoInicial
     */
    public Long getNumIdentBoletoInicial() {
        return numIdentBoletoInicial;
    }

    /**
     * Define o atributo numIdentBoletoInicial
     */
    public void setNumIdentBoletoInicial(Long numIdentBoletoInicial) {
        this.numIdentBoletoInicial = numIdentBoletoInicial;
    }

    /**
     * @return o atributo numIdentBoletoFinal
     */
    public Long getNumIdentBoletoFinal() {
        return numIdentBoletoFinal;
    }

    /**
     * Define o atributo numIdentBoletoFinal
     */
    public void setNumIdentBoletoFinal(Long numIdentBoletoFinal) {
        this.numIdentBoletoFinal = numIdentBoletoFinal;
    }

    /**
     * @return o atributo codTipoBoletoConsultado
     */
    public String getCodTipoBoletoConsultado() {
        return codTipoBoletoConsultado;
    }

    /**
     * Define o atributo codTipoBoletoConsultado
     */
    public void setCodTipoBoletoConsultado(String codTipoBoletoConsultado) {
        this.codTipoBoletoConsultado = codTipoBoletoConsultado;
    }

    /**
     * @return o atributo numIdentBoletoInicialStr
     */
    public String getNumIdentBoletoInicialStr() {
        return numIdentBoletoInicialStr;
    }

    /**
     * Define o atributo numIdentBoletoInicialStr
     */
    public void setNumIdentBoletoInicialStr(String numIdentBoletoInicialStr) {
        this.numIdentBoletoInicialStr = numIdentBoletoInicialStr;
    }

    /**
     * @return o atributo numIdentBoletoFinalStr
     */
    public String getNumIdentBoletoFinalStr() {
        return numIdentBoletoFinalStr;
    }

    /**
     * Define o atributo numIdentBoletoFinalStr
     */
    public void setNumIdentBoletoFinalStr(String numIdentBoletoFinalStr) {
        this.numIdentBoletoFinalStr = numIdentBoletoFinalStr;
    }

    /**
     * Método responsável por converter String para Long por causa do erro no Flex.
     * 
     */
    public void converteNumIdentBoletoInicial() {
        if (numIdentBoletoInicialStr != null) {
            numIdentBoletoInicial = Long.parseLong(numIdentBoletoInicialStr);
        }
    }

    /**
     * Método responsável por converter String para Long por causa do erro no Flex.
     * 
     */
    public void converteNumIdentBoletoFinal() {
        if (numIdentBoletoFinalStr != null) {
            numIdentBoletoFinal = Long.parseLong(numIdentBoletoFinalStr);
        }
    }
}
