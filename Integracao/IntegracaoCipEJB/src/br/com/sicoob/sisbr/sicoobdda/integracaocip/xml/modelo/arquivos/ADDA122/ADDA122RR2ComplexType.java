//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.09 at 09:41:54 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ADDA122RR2ComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ADDA122RR2ComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Grupo_ADDA122RR2_Tit" type="{http://www.bcb.gov.br/ARQ/ADDA122.xsd}Grupo_ADDA122RR2_TitComplexType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ADDA122RR2ComplexType", propOrder = { "grupoADDA122RR2Tit" })
public class ADDA122RR2ComplexType {

    @XmlElement(name = "Grupo_ADDA122RR2_Tit", required = true)
    private List<GrupoADDA122RR2TitComplexType> grupoADDA122RR2Tit;

    /**
     * Gets the value of the grupoADDA122RR2Tit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the grupoADDA122RR2Tit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getGrupoADDA122RR2Tit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link GrupoADDA122RR2TitComplexType }
     * 
     * 
     */
    public List<GrupoADDA122RR2TitComplexType> getGrupoADDA122RR2Tit() {
        if (grupoADDA122RR2Tit == null) {
            grupoADDA122RR2Tit = new ArrayList<GrupoADDA122RR2TitComplexType>();
        }
        return this.grupoADDA122RR2Tit;
    }

}
