//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 09:22:34 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * <p>
 * Java class for DDA0121R2ComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDA0121R2ComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodMsg" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}CodMsg"/>
 *         &lt;element name="ISPBPartRecbdrPrincipal" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}ISPB"/>
 *         &lt;element name="ISPBPartRecbdrAdmtd" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}ISPB"/>
 *         &lt;element name="NumCtrlDDA" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}ControleIF"/>
 *         &lt;element name="NumIdentcTit" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}NumIdentcTit"/>
 *         &lt;element name="NumIdentcTerc" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}NumIdentcTit"/>
 *         &lt;element name="NumRefAtlCadTerc" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}NumRefCad"/>
 *         &lt;element name="TpPessoaPagdrAutzdr" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}TpPessoa"/>
 *         &lt;element name="CNPJ_CPFPagdrAutzdr" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}CNPJ_CPF"/>
 *         &lt;element name="TpPessoaTerc" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}TpPessoa"/>
 *         &lt;element name="CNPJ_CPFTerc" type="{http://www.bcb.gov.br/SPB/DDA0121.xsd}CNPJ_CPF"/>
 *         &lt;element name="DtHrDDA" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DtMovto" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DDA0121R2ComplexType", propOrder = { "codMsg", "ispbPartRecbdrPrincipal", "ispbPartRecbdrAdmtd", "numCtrlDDA", "numIdentcTit", "numIdentcTerc",
        "numRefAtlCadTerc", "tpPessoaPagdrAutzdr", "cnpjcpfPagdrAutzdr", "tpPessoaTerc", "cnpjcpfTerc", "dtHrDDA", "dtMovto" })
public class DDA0121R2ComplexType implements ConteudoMsgRecebida {

    @XmlElement(name = "CodMsg", required = true)
    private String codMsg;
    @XmlElement(name = "ISPBPartRecbdrPrincipal", required = true)
    private String ispbPartRecbdrPrincipal;
    @XmlElement(name = "ISPBPartRecbdrAdmtd", required = true)
    private String ispbPartRecbdrAdmtd;
    @XmlElement(name = "NumCtrlDDA", required = true)
    private String numCtrlDDA;
    @XmlElement(name = "NumIdentcTit", required = true)
    private BigInteger numIdentcTit;
    @XmlElement(name = "NumIdentcTerc", required = true)
    private BigInteger numIdentcTerc;
    @XmlElement(name = "NumRefAtlCadTerc", required = true)
    private BigInteger numRefAtlCadTerc;
    @XmlElement(name = "TpPessoaPagdrAutzdr", required = true)
    private String tpPessoaPagdrAutzdr;
    @XmlElement(name = "CNPJ_CPFPagdrAutzdr", required = true)
    private BigInteger cnpjcpfPagdrAutzdr;
    @XmlElement(name = "TpPessoaTerc", required = true)
    private String tpPessoaTerc;
    @XmlElement(name = "CNPJ_CPFTerc", required = true)
    private BigInteger cnpjcpfTerc;
    @XmlElement(name = "DtHrDDA", required = true)
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar dtHrDDA;
    @XmlElement(name = "DtMovto", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtMovto;

    /**
     * Gets the value of the codMsg property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodMsg() {
        return codMsg;
    }

    /**
     * Sets the value of the codMsg property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodMsg(String value) {
        this.codMsg = value;
    }

    /**
     * Gets the value of the ispbPartRecbdrPrincipal property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartRecbdrPrincipal() {
        return ispbPartRecbdrPrincipal;
    }

    /**
     * Sets the value of the ispbPartRecbdrPrincipal property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartRecbdrPrincipal(String value) {
        this.ispbPartRecbdrPrincipal = value;
    }

    /**
     * Gets the value of the ispbPartRecbdrAdmtd property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartRecbdrAdmtd() {
        return ispbPartRecbdrAdmtd;
    }

    /**
     * Sets the value of the ispbPartRecbdrAdmtd property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartRecbdrAdmtd(String value) {
        this.ispbPartRecbdrAdmtd = value;
    }

    /**
     * Gets the value of the numCtrlDDA property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNumCtrlDDA() {
        return numCtrlDDA;
    }

    /**
     * Sets the value of the numCtrlDDA property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNumCtrlDDA(String value) {
        this.numCtrlDDA = value;
    }

    /**
     * Gets the value of the numIdentcTit property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumIdentcTit() {
        return numIdentcTit;
    }

    /**
     * Sets the value of the numIdentcTit property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumIdentcTit(BigInteger value) {
        this.numIdentcTit = value;
    }

    /**
     * Gets the value of the numIdentcTerc property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumIdentcTerc() {
        return numIdentcTerc;
    }

    /**
     * Sets the value of the numIdentcTerc property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumIdentcTerc(BigInteger value) {
        this.numIdentcTerc = value;
    }

    /**
     * Gets the value of the numRefAtlCadTerc property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumRefAtlCadTerc() {
        return numRefAtlCadTerc;
    }

    /**
     * Sets the value of the numRefAtlCadTerc property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumRefAtlCadTerc(BigInteger value) {
        this.numRefAtlCadTerc = value;
    }

    /**
     * Gets the value of the tpPessoaPagdrAutzdr property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpPessoaPagdrAutzdr() {
        return tpPessoaPagdrAutzdr;
    }

    /**
     * Sets the value of the tpPessoaPagdrAutzdr property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpPessoaPagdrAutzdr(String value) {
        this.tpPessoaPagdrAutzdr = value;
    }

    /**
     * Gets the value of the cnpjcpfPagdrAutzdr property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getCNPJCPFPagdrAutzdr() {
        return cnpjcpfPagdrAutzdr;
    }

    /**
     * Sets the value of the cnpjcpfPagdrAutzdr property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setCNPJCPFPagdrAutzdr(BigInteger value) {
        this.cnpjcpfPagdrAutzdr = value;
    }

    /**
     * Gets the value of the tpPessoaTerc property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpPessoaTerc() {
        return tpPessoaTerc;
    }

    /**
     * Sets the value of the tpPessoaTerc property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpPessoaTerc(String value) {
        this.tpPessoaTerc = value;
    }

    /**
     * Gets the value of the cnpjcpfTerc property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getCNPJCPFTerc() {
        return cnpjcpfTerc;
    }

    /**
     * Sets the value of the cnpjcpfTerc property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setCNPJCPFTerc(BigInteger value) {
        this.cnpjcpfTerc = value;
    }

    /**
     * Gets the value of the dtHrDDA property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrDDA() {
        return dtHrDDA;
    }

    /**
     * Sets the value of the dtHrDDA property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrDDA(XMLGregorianCalendar value) {
        this.dtHrDDA = value;
    }

    /**
     * Gets the value of the dtMovto property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtMovto() {
        return dtMovto;
    }

    /**
     * Sets the value of the dtMovto property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtMovto(XMLGregorianCalendar value) {
        this.dtMovto = value;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getIdMensagemOrigem()
     */
    public Long getIdMensagemOrigem() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getNumIdent()
     */
    public Long getNumIdent() {
        if (!ObjectUtil.isEmpty(getNumIdentcTerc())) {
            return getNumIdentcTerc().longValue();
        }
        return null;
    }
}
