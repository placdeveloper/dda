//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 09:22:24 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Grupo_DDA0102R2_SacdrAvalstComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_DDA0102R2_SacdrAvalstComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TpIdentcSacdrAvalst" type="{http://www.bcb.gov.br/SPB/DDA0102.xsd}TpIdentcSacdrAvalst"/>
 *         &lt;element name="IdentcSacdrAvalst" type="{http://www.bcb.gov.br/SPB/DDA0102.xsd}IdentcSacdrAvalst" minOccurs="0"/>
 *         &lt;element name="Nom_RzSocSacdrAvalst" type="{http://www.bcb.gov.br/SPB/DDA0102.xsd}Nom_RzSoc" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_DDA0102R2_SacdrAvalstComplexType", propOrder = { "tpIdentcSacdrAvalst", "identcSacdrAvalst", "nomRzSocSacdrAvalst" })
public class GrupoDDA0102R2SacdrAvalstComplexType {

    @XmlElement(name = "TpIdentcSacdrAvalst", required = true)
    private BigInteger tpIdentcSacdrAvalst;
    @XmlElement(name = "IdentcSacdrAvalst")
    private String identcSacdrAvalst;
    @XmlElement(name = "Nom_RzSocSacdrAvalst")
    private String nomRzSocSacdrAvalst;

    /**
     * Gets the value of the tpIdentcSacdrAvalst property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getTpIdentcSacdrAvalst() {
        return tpIdentcSacdrAvalst;
    }

    /**
     * Sets the value of the tpIdentcSacdrAvalst property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setTpIdentcSacdrAvalst(BigInteger value) {
        this.tpIdentcSacdrAvalst = value;
    }

    /**
     * Gets the value of the identcSacdrAvalst property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIdentcSacdrAvalst() {
        return identcSacdrAvalst;
    }

    /**
     * Sets the value of the identcSacdrAvalst property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIdentcSacdrAvalst(String value) {
        this.identcSacdrAvalst = value;
    }

    /**
     * Gets the value of the nomRzSocSacdrAvalst property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNomRzSocSacdrAvalst() {
        return nomRzSocSacdrAvalst;
    }

    /**
     * Sets the value of the nomRzSocSacdrAvalst property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNomRzSocSacdrAvalst(String value) {
        this.nomRzSocSacdrAvalst = value;
    }

}
