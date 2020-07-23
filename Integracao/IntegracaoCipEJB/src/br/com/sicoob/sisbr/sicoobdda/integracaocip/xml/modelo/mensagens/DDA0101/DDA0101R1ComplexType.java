//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 09:22:23 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101;

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
 * Java class for DDA0101R1ComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDA0101R1ComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodMsg" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}CodMsg"/>
 *         &lt;element name="NumCtrlPart" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}ControleIF"/>
 *         &lt;element name="ISPBPartDestinatarioPrincipal" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}ISPB"/>
 *         &lt;element name="ISPBPartDestinatarioAdmtd" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}ISPB"/>
 *         &lt;element name="NumIdentcTit" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}NumIdentcTit"/>
 *         &lt;element name="NumRefAtlCadTit" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}NumRefCad"/>
 *         &lt;element name="NumSeqAtlzCadTit" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}NumSeqAtlzCadDDA"/>
 *         &lt;element name="NumCtrlDDA" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}ControleIF"/>
 *         &lt;element name="NumCodBarras" type="{http://www.bcb.gov.br/SPB/DDA0101.xsd}NumCodBarras"/>
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
@XmlType(name = "DDA0101R1ComplexType", propOrder = { "codMsg", "numCtrlPart", "ispbPartDestinatarioPrincipal", "ispbPartDestinatarioAdmtd", "numIdentcTit", "numRefAtlCadTit",
        "numSeqAtlzCadTit", "numCtrlDDA", "numCodBarras", "dtHrDDA", "dtMovto" })
public class DDA0101R1ComplexType implements ConteudoMsgRecebida {

    @XmlElement(name = "CodMsg", required = true)
    private String codMsg;
    @XmlElement(name = "NumCtrlPart", required = true)
    private String numCtrlPart;
    @XmlElement(name = "ISPBPartDestinatarioPrincipal", required = true)
    private String ispbPartDestinatarioPrincipal;
    @XmlElement(name = "ISPBPartDestinatarioAdmtd", required = true)
    private String ispbPartDestinatarioAdmtd;
    @XmlElement(name = "NumIdentcTit", required = true)
    private BigInteger numIdentcTit;
    @XmlElement(name = "NumRefAtlCadTit", required = true)
    private BigInteger numRefAtlCadTit;
    @XmlElement(name = "NumSeqAtlzCadTit", required = true)
    private BigInteger numSeqAtlzCadTit;
    @XmlElement(name = "NumCtrlDDA", required = true)
    private String numCtrlDDA;
    @XmlElement(name = "NumCodBarras", required = true)
    private String numCodBarras;
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
     * Gets the value of the numCtrlPart property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNumCtrlPart() {
        return numCtrlPart;
    }

    /**
     * Sets the value of the numCtrlPart property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNumCtrlPart(String value) {
        this.numCtrlPart = value;
    }

    /**
     * Gets the value of the ispbPartDestinatarioPrincipal property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartDestinatarioPrincipal() {
        return ispbPartDestinatarioPrincipal;
    }

    /**
     * Sets the value of the ispbPartDestinatarioPrincipal property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartDestinatarioPrincipal(String value) {
        this.ispbPartDestinatarioPrincipal = value;
    }

    /**
     * Gets the value of the ispbPartDestinatarioAdmtd property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartDestinatarioAdmtd() {
        return ispbPartDestinatarioAdmtd;
    }

    /**
     * Sets the value of the ispbPartDestinatarioAdmtd property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartDestinatarioAdmtd(String value) {
        this.ispbPartDestinatarioAdmtd = value;
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
     * Gets the value of the numRefAtlCadTit property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumRefAtlCadTit() {
        return numRefAtlCadTit;
    }

    /**
     * Sets the value of the numRefAtlCadTit property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumRefAtlCadTit(BigInteger value) {
        this.numRefAtlCadTit = value;
    }

    /**
     * Gets the value of the numSeqAtlzCadTit property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumSeqAtlzCadTit() {
        return numSeqAtlzCadTit;
    }

    /**
     * Sets the value of the numSeqAtlzCadTit property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumSeqAtlzCadTit(BigInteger value) {
        this.numSeqAtlzCadTit = value;
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
     * Gets the value of the numCodBarras property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNumCodBarras() {
        return numCodBarras;
    }

    /**
     * Sets the value of the numCodBarras property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNumCodBarras(String value) {
        this.numCodBarras = value;
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
        if (!ObjectUtil.isEmpty(getNumCtrlPart())) {
            return Long.parseLong(getNumCtrlPart());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getNumIdent()
     */
    public Long getNumIdent() {
        if (!ObjectUtil.isEmpty(getNumIdentcTit())) {
            return getNumIdentcTit().longValue();
        }
        return null;
    }
}
