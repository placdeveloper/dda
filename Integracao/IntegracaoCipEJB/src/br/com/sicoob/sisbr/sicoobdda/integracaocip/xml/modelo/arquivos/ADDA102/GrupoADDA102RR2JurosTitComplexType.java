//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.26 at 05:37:00 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for Grupo_ADDA102RR2_JurosTitComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ADDA102RR2_JurosTitComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DtJurosTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Data" minOccurs="0"/>
 *         &lt;element name="CodJurosTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}CodJurosTit"/>
 *         &lt;element name="Vlr_PercJurosTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Vlr_PercDDA"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ADDA102RR2_JurosTitComplexType", propOrder = { "dtJurosTit", "codJurosTit", "vlrPercJurosTit" })
public class GrupoADDA102RR2JurosTitComplexType {

    @XmlElement(name = "DtJurosTit")
    private XMLGregorianCalendar dtJurosTit;
    @XmlElement(name = "CodJurosTit", required = true)
    private String codJurosTit;
    @XmlElement(name = "Vlr_PercJurosTit", required = true)
    private BigDecimal vlrPercJurosTit;

    /**
     * Gets the value of the dtJurosTit property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtJurosTit() {
        return dtJurosTit;
    }

    /**
     * Sets the value of the dtJurosTit property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtJurosTit(XMLGregorianCalendar value) {
        this.dtJurosTit = value;
    }

    /**
     * Gets the value of the codJurosTit property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodJurosTit() {
        return codJurosTit;
    }

    /**
     * Sets the value of the codJurosTit property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodJurosTit(String value) {
        this.codJurosTit = value;
    }

    /**
     * Gets the value of the vlrPercJurosTit property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getVlrPercJurosTit() {
        return vlrPercJurosTit;
    }

    /**
     * Sets the value of the vlrPercJurosTit property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setVlrPercJurosTit(BigDecimal value) {
        this.vlrPercJurosTit = value;
    }

}
