//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.12 at 02:22:04 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110NPC;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for Grupo_ADDA110_BaixaOperacComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ADDA110_BaixaOperacComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumIdentcBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA110.xsd}NumIdentcTit" minOccurs="0"/>
 *         &lt;element name="NumRefAtlBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA110.xsd}NumRefCad" minOccurs="0"/>
 *         &lt;element name="NumSeqAtlzBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA110.xsd}NumSeqAtlzCadDDA"/>
 *         &lt;element name="DtProcBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA110.xsd}Data"/>
 *         &lt;element name="DtHrProcBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA110.xsd}DataHora"/>
 *         &lt;element name="DtHrSitBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA110.xsd}DataHora"/>
 *         &lt;element name="VlrBaixaOperacTit" type="{http://www.bcb.gov.br/ARQ/ADDA110.xsd}Valor"/>
 *         &lt;element name="NumCodBarrasBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA110.xsd}NumCodBarras"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ADDA110_BaixaOperacComplexType", propOrder = { "numIdentcBaixaOperac", "numRefAtlBaixaOperac", "numSeqAtlzBaixaOperac", "dtProcBaixaOperac",
        "dtHrProcBaixaOperac", "dtHrSitBaixaOperac", "vlrBaixaOperacTit", "numCodBarrasBaixaOperac" })
public class GrupoADDA110BaixaOperacComplexType {

    @XmlElement(name = "NumIdentcBaixaOperac")
    private BigInteger numIdentcBaixaOperac;
    @XmlElement(name = "NumRefAtlBaixaOperac")
    private BigInteger numRefAtlBaixaOperac;
    @XmlElement(name = "NumSeqAtlzBaixaOperac", required = true)
    private BigInteger numSeqAtlzBaixaOperac;
    @XmlElement(name = "DtProcBaixaOperac", required = true)
    private XMLGregorianCalendar dtProcBaixaOperac;
    @XmlElement(name = "DtHrProcBaixaOperac", required = true)
    private XMLGregorianCalendar dtHrProcBaixaOperac;
    @XmlElement(name = "DtHrSitBaixaOperac", required = true)
    private XMLGregorianCalendar dtHrSitBaixaOperac;
    @XmlElement(name = "VlrBaixaOperacTit", required = true)
    private BigDecimal vlrBaixaOperacTit;
    @XmlElement(name = "NumCodBarrasBaixaOperac", required = true)
    private String numCodBarrasBaixaOperac;

    /**
     * Gets the value of the numIdentcBaixaOperac property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumIdentcBaixaOperac() {
        return numIdentcBaixaOperac;
    }

    /**
     * Sets the value of the numIdentcBaixaOperac property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumIdentcBaixaOperac(BigInteger value) {
        this.numIdentcBaixaOperac = value;
    }

    /**
     * Gets the value of the numRefAtlBaixaOperac property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumRefAtlBaixaOperac() {
        return numRefAtlBaixaOperac;
    }

    /**
     * Sets the value of the numRefAtlBaixaOperac property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumRefAtlBaixaOperac(BigInteger value) {
        this.numRefAtlBaixaOperac = value;
    }

    /**
     * Gets the value of the numSeqAtlzBaixaOperac property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumSeqAtlzBaixaOperac() {
        return numSeqAtlzBaixaOperac;
    }

    /**
     * Sets the value of the numSeqAtlzBaixaOperac property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumSeqAtlzBaixaOperac(BigInteger value) {
        this.numSeqAtlzBaixaOperac = value;
    }

    /**
     * Gets the value of the dtProcBaixaOperac property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtProcBaixaOperac() {
        return dtProcBaixaOperac;
    }

    /**
     * Sets the value of the dtProcBaixaOperac property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtProcBaixaOperac(XMLGregorianCalendar value) {
        this.dtProcBaixaOperac = value;
    }

    /**
     * Gets the value of the dtHrProcBaixaOperac property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrProcBaixaOperac() {
        return dtHrProcBaixaOperac;
    }

    /**
     * Sets the value of the dtHrProcBaixaOperac property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrProcBaixaOperac(XMLGregorianCalendar value) {
        this.dtHrProcBaixaOperac = value;
    }

    /**
     * Gets the value of the dtHrSitBaixaOperac property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrSitBaixaOperac() {
        return dtHrSitBaixaOperac;
    }

    /**
     * Sets the value of the dtHrSitBaixaOperac property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrSitBaixaOperac(XMLGregorianCalendar value) {
        this.dtHrSitBaixaOperac = value;
    }

    /**
     * Gets the value of the vlrBaixaOperacTit property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getVlrBaixaOperacTit() {
        return vlrBaixaOperacTit;
    }

    /**
     * Sets the value of the vlrBaixaOperacTit property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setVlrBaixaOperacTit(BigDecimal value) {
        this.vlrBaixaOperacTit = value;
    }

    /**
     * Gets the value of the numCodBarrasBaixaOperac property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNumCodBarrasBaixaOperac() {
        return numCodBarrasBaixaOperac;
    }

    /**
     * Sets the value of the numCodBarrasBaixaOperac property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNumCodBarrasBaixaOperac(String value) {
        this.numCodBarrasBaixaOperac = value;
    }

}
