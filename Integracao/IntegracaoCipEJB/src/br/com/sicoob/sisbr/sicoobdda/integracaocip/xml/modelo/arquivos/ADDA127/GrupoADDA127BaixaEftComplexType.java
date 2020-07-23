//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.12 at 02:22:08 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for Grupo_ADDA127_BaixaEftComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ADDA127_BaixaEftComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumIdentcBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}NumIdentcTit" minOccurs="0"/>
 *         &lt;element name="NumRefAtlBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}NumRefCad" minOccurs="0"/>
 *         &lt;element name="NumSeqAtlzBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}NumSeqAtlzCadDDA"/>
 *         &lt;element name="NumIdentcBaixaOperacBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}NumIdentcTit" minOccurs="0"/>
 *         &lt;element name="TpBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}TpBaixaEft"/>
 *         &lt;element name="ISPBPartRecbdrBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}ISPB" minOccurs="0"/>
 *         &lt;element name="CodPartRecbdrBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}CodIF" minOccurs="0"/>
 *         &lt;element name="DtHrProcBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}DataHora"/>
 *         &lt;element name="DtProcBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}Data"/>
 *         &lt;element name="VlrBaixaEftTit" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}Valor" minOccurs="0"/>
 *         &lt;element name="NumCodBarrasBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}NumCodBarras" minOccurs="0"/>
 *         &lt;element name="CanPgtoBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}CanPgto" minOccurs="0"/>
 *         &lt;element name="MeioPgtoBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}MeioPgto" minOccurs="0"/>
 *         &lt;element name="DtHrSitBaixaEft" type="{http://www.bcb.gov.br/ARQ/ADDA127.xsd}DataHora"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ADDA127_BaixaEftComplexType", propOrder = { "numIdentcBaixaEft", "numRefAtlBaixaEft", "numSeqAtlzBaixaEft", "numIdentcBaixaOperacBaixaEft", "tpBaixaEft",
        "ispbPartRecbdrBaixaEft", "codPartRecbdrBaixaEft", "dtHrProcBaixaEft", "dtProcBaixaEft", "vlrBaixaEftTit", "numCodBarrasBaixaEft", "canPgtoBaixaEft", "meioPgtoBaixaEft",
        "dtHrSitBaixaEft" })
public class GrupoADDA127BaixaEftComplexType {

    @XmlElement(name = "NumIdentcBaixaEft")
    private BigInteger numIdentcBaixaEft;
    @XmlElement(name = "NumRefAtlBaixaEft")
    private BigInteger numRefAtlBaixaEft;
    @XmlElement(name = "NumSeqAtlzBaixaEft", required = true)
    private BigInteger numSeqAtlzBaixaEft;
    @XmlElement(name = "NumIdentcBaixaOperacBaixaEft")
    private BigInteger numIdentcBaixaOperacBaixaEft;
    @XmlElement(name = "TpBaixaEft", required = true)
    private String tpBaixaEft;
    @XmlElement(name = "ISPBPartRecbdrBaixaEft")
    private String ispbPartRecbdrBaixaEft;
    @XmlElement(name = "CodPartRecbdrBaixaEft")
    private String codPartRecbdrBaixaEft;
    @XmlElement(name = "DtHrProcBaixaEft", required = true)
    private XMLGregorianCalendar dtHrProcBaixaEft;
    @XmlElement(name = "DtProcBaixaEft", required = true)
    private XMLGregorianCalendar dtProcBaixaEft;
    @XmlElement(name = "VlrBaixaEftTit")
    private BigDecimal vlrBaixaEftTit;
    @XmlElement(name = "NumCodBarrasBaixaEft")
    private String numCodBarrasBaixaEft;
    @XmlElement(name = "CanPgtoBaixaEft")
    private BigInteger canPgtoBaixaEft;
    @XmlElement(name = "MeioPgtoBaixaEft")
    private BigInteger meioPgtoBaixaEft;
    @XmlElement(name = "DtHrSitBaixaEft", required = true)
    private XMLGregorianCalendar dtHrSitBaixaEft;

    /**
     * Gets the value of the numIdentcBaixaEft property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumIdentcBaixaEft() {
        return numIdentcBaixaEft;
    }

    /**
     * Sets the value of the numIdentcBaixaEft property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumIdentcBaixaEft(BigInteger value) {
        this.numIdentcBaixaEft = value;
    }

    /**
     * Gets the value of the numRefAtlBaixaEft property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumRefAtlBaixaEft() {
        return numRefAtlBaixaEft;
    }

    /**
     * Sets the value of the numRefAtlBaixaEft property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumRefAtlBaixaEft(BigInteger value) {
        this.numRefAtlBaixaEft = value;
    }

    /**
     * Gets the value of the numSeqAtlzBaixaEft property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumSeqAtlzBaixaEft() {
        return numSeqAtlzBaixaEft;
    }

    /**
     * Sets the value of the numSeqAtlzBaixaEft property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumSeqAtlzBaixaEft(BigInteger value) {
        this.numSeqAtlzBaixaEft = value;
    }

    /**
     * Gets the value of the numIdentcBaixaOperacBaixaEft property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumIdentcBaixaOperacBaixaEft() {
        return numIdentcBaixaOperacBaixaEft;
    }

    /**
     * Sets the value of the numIdentcBaixaOperacBaixaEft property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumIdentcBaixaOperacBaixaEft(BigInteger value) {
        this.numIdentcBaixaOperacBaixaEft = value;
    }

    /**
     * Gets the value of the tpBaixaEft property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpBaixaEft() {
        return tpBaixaEft;
    }

    /**
     * Sets the value of the tpBaixaEft property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpBaixaEft(String value) {
        this.tpBaixaEft = value;
    }

    /**
     * Gets the value of the ispbPartRecbdrBaixaEft property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartRecbdrBaixaEft() {
        return ispbPartRecbdrBaixaEft;
    }

    /**
     * Sets the value of the ispbPartRecbdrBaixaEft property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartRecbdrBaixaEft(String value) {
        this.ispbPartRecbdrBaixaEft = value;
    }

    /**
     * Gets the value of the codPartRecbdrBaixaEft property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodPartRecbdrBaixaEft() {
        return codPartRecbdrBaixaEft;
    }

    /**
     * Sets the value of the codPartRecbdrBaixaEft property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodPartRecbdrBaixaEft(String value) {
        this.codPartRecbdrBaixaEft = value;
    }

    /**
     * Gets the value of the dtHrProcBaixaEft property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrProcBaixaEft() {
        return dtHrProcBaixaEft;
    }

    /**
     * Sets the value of the dtHrProcBaixaEft property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrProcBaixaEft(XMLGregorianCalendar value) {
        this.dtHrProcBaixaEft = value;
    }

    /**
     * Gets the value of the dtProcBaixaEft property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtProcBaixaEft() {
        return dtProcBaixaEft;
    }

    /**
     * Sets the value of the dtProcBaixaEft property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtProcBaixaEft(XMLGregorianCalendar value) {
        this.dtProcBaixaEft = value;
    }

    /**
     * Gets the value of the vlrBaixaEftTit property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getVlrBaixaEftTit() {
        return vlrBaixaEftTit;
    }

    /**
     * Sets the value of the vlrBaixaEftTit property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setVlrBaixaEftTit(BigDecimal value) {
        this.vlrBaixaEftTit = value;
    }

    /**
     * Gets the value of the numCodBarrasBaixaEft property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNumCodBarrasBaixaEft() {
        return numCodBarrasBaixaEft;
    }

    /**
     * Sets the value of the numCodBarrasBaixaEft property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNumCodBarrasBaixaEft(String value) {
        this.numCodBarrasBaixaEft = value;
    }

    /**
     * Gets the value of the canPgtoBaixaEft property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getCanPgtoBaixaEft() {
        return canPgtoBaixaEft;
    }

    /**
     * Sets the value of the canPgtoBaixaEft property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setCanPgtoBaixaEft(BigInteger value) {
        this.canPgtoBaixaEft = value;
    }

    /**
     * Gets the value of the meioPgtoBaixaEft property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getMeioPgtoBaixaEft() {
        return meioPgtoBaixaEft;
    }

    /**
     * Sets the value of the meioPgtoBaixaEft property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setMeioPgtoBaixaEft(BigInteger value) {
        this.meioPgtoBaixaEft = value;
    }

    /**
     * Gets the value of the dtHrSitBaixaEft property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrSitBaixaEft() {
        return dtHrSitBaixaEft;
    }

    /**
     * Sets the value of the dtHrSitBaixaEft property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrSitBaixaEft(XMLGregorianCalendar value) {
        this.dtHrSitBaixaEft = value;
    }

}
