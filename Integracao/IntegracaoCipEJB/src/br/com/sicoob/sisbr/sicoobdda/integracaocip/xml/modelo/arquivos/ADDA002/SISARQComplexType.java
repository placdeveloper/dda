//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.26 at 05:36:55 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for SISARQComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SISARQComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ADDA002" type="{http://www.bcb.gov.br/ARQ/ADDA002.xsd}ADDA002ComplexType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "SISARQ")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SISARQComplexType", propOrder = { "adda002" })
public class SISARQComplexType {

    @XmlElement(name = "ADDA002")
    private ADDA002ComplexType adda002;

    /**
     * Gets the value of the adda002 property.
     * 
     * @return possible object is {@link ADDA002ComplexType }
     * 
     */
    public ADDA002ComplexType getADDA002() {
        return adda002;
    }

    /**
     * Sets the value of the adda002 property.
     * 
     * @param value allowed object is {@link ADDA002ComplexType }
     * 
     */
    public void setADDA002(ADDA002ComplexType value) {
        this.adda002 = value;
    }

}
