//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.26 at 05:37:01 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106;

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
 *         &lt;element name="ADDA106" type="{http://www.bcb.gov.br/ARQ/ADDA106.xsd}ADDA106ComplexType"/>
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
@XmlType(name = "SISARQComplexType", propOrder = { "adda106" })
public class SISARQComplexType {

    @XmlElement(name = "ADDA106")
    private ADDA106ComplexType adda106;

    /**
     * Gets the value of the adda106 property.
     * 
     * @return possible object is {@link ADDA106ComplexType }
     * 
     */
    public ADDA106ComplexType getADDA106() {
        return adda106;
    }

    /**
     * Sets the value of the adda106 property.
     * 
     * @param value allowed object is {@link ADDA106ComplexType }
     * 
     */
    public void setADDA106(ADDA106ComplexType value) {
        this.adda106 = value;
    }

}
