//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.23 at 04:25:16 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for Grupo_DDA0501_ConvComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_DDA0501_ConvComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ISPBPartIncorpd" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}ISPB" minOccurs="0"/>
 *         &lt;element name="SitConvBenfcrioPart" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}SitConvBenfcrioPart" minOccurs="0"/>
 *         &lt;element name="DtIniRelctConv" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="TpAgDest" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}TpAg"/>
 *         &lt;element name="AgDest" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}Agencia"/>
 *         &lt;element name="TpCtDest" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}TpCt" minOccurs="0"/>
 *         &lt;element name="CtDest" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}CtBanc_Pgto" minOccurs="0"/>
 *         &lt;element name="TpProdtConv" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}TpProdtConv"/>
 *         &lt;element name="TpCartConvCobr" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}TpCartConvCobr" minOccurs="0"/>
 *         &lt;element name="CodCli_Conv" type="{http://www.bcb.gov.br/SPB/DDA0501.xsd}CodCli" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_DDA0501_ConvComplexTypeNPC", propOrder = { "ispbPartIncorpd", "sitConvBenfcrioPart", "dtIniRelctConv", "tpAgDest", "agDest", "tpCtDest", "ctDest",
        "tpProdtConv", "tpCartConvCobr", "codCliConv" })
public class GrupoDDA0501ConvComplexType {

    @XmlElement(name = "ISPBPartIncorpd")
    private String ispbPartIncorpd;
    @XmlElement(name = "SitConvBenfcrioPart")
    private String sitConvBenfcrioPart;
    @XmlElement(name = "DtIniRelctConv", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtIniRelctConv;
    @XmlElement(name = "TpAgDest", required = true)
    private String tpAgDest;
    @XmlElement(name = "AgDest", required = true)
    private BigInteger agDest;
    @XmlElement(name = "TpCtDest")
    private String tpCtDest;
    @XmlElement(name = "CtDest")
    private BigInteger ctDest;
    @XmlElement(name = "TpProdtConv", required = true)
    private String tpProdtConv;
    @XmlElement(name = "TpCartConvCobr")
    private String tpCartConvCobr;
    @XmlElement(name = "CodCli_Conv")
    private String codCliConv;

    /**
     * Gets the value of the ispbPartIncorpd property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartIncorpd() {
        return ispbPartIncorpd;
    }

    /**
     * Sets the value of the ispbPartIncorpd property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartIncorpd(String value) {
        this.ispbPartIncorpd = value;
    }

    /**
     * Gets the value of the sitConvBenfcrioPart property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSitConvBenfcrioPart() {
        return sitConvBenfcrioPart;
    }

    /**
     * Sets the value of the sitConvBenfcrioPart property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setSitConvBenfcrioPart(String value) {
        this.sitConvBenfcrioPart = value;
    }

    /**
     * Gets the value of the dtIniRelctConv property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtIniRelctConv() {
        return dtIniRelctConv;
    }

    /**
     * Sets the value of the dtIniRelctConv property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtIniRelctConv(XMLGregorianCalendar value) {
        this.dtIniRelctConv = value;
    }

    /**
     * Gets the value of the tpAgDest property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpAgDest() {
        return tpAgDest;
    }

    /**
     * Sets the value of the tpAgDest property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpAgDest(String value) {
        this.tpAgDest = value;
    }

    /**
     * Gets the value of the agDest property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getAgDest() {
        return agDest;
    }

    /**
     * Sets the value of the agDest property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setAgDest(BigInteger value) {
        this.agDest = value;
    }

    /**
     * Gets the value of the tpCtDest property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpCtDest() {
        return tpCtDest;
    }

    /**
     * Sets the value of the tpCtDest property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpCtDest(String value) {
        this.tpCtDest = value;
    }

    /**
     * Gets the value of the ctDest property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getCtDest() {
        return ctDest;
    }

    /**
     * Sets the value of the ctDest property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setCtDest(BigInteger value) {
        this.ctDest = value;
    }

    /**
     * Gets the value of the tpProdtConv property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpProdtConv() {
        return tpProdtConv;
    }

    /**
     * Sets the value of the tpProdtConv property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpProdtConv(String value) {
        this.tpProdtConv = value;
    }

    /**
     * Gets the value of the tpCartConvCobr property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpCartConvCobr() {
        return tpCartConvCobr;
    }

    /**
     * Sets the value of the tpCartConvCobr property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpCartConvCobr(String value) {
        this.tpCartConvCobr = value;
    }

    /**
     * Gets the value of the codCliConv property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodCliConv() {
        return codCliConv;
    }

    /**
     * Sets the value of the codCliConv property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodCliConv(String value) {
        this.codCliConv = value;
    }

}
