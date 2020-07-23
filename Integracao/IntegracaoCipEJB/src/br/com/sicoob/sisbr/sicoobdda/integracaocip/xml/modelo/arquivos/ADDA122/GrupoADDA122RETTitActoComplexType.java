//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.09 at 09:41:54 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Grupo_ADDA122RET_TitActoComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ADDA122RET_TitActoComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumCtrlReqPart" type="{http://www.bcb.gov.br/ARQ/ADDA122.xsd}ControleIF"/>
 *         &lt;element name="ISPBPartRecbdrPrincipal" type="{http://www.bcb.gov.br/ARQ/ADDA122.xsd}ISPB"/>
 *         &lt;element name="ISPBPartRecbdrAdmtd" type="{http://www.bcb.gov.br/ARQ/ADDA122.xsd}ISPB"/>
 *         &lt;element name="NumCtrlDDA" type="{http://www.bcb.gov.br/ARQ/ADDA122.xsd}ControleIF"/>
 *         &lt;element name="NumIdentcTit" type="{http://www.bcb.gov.br/ARQ/ADDA122.xsd}NumIdentcTit"/>
 *         &lt;element name="NumIdentcTerc" type="{http://www.bcb.gov.br/ARQ/ADDA122.xsd}NumIdentcTit"/>
 *         &lt;element name="NumRefAtlCadTerc" type="{http://www.bcb.gov.br/ARQ/ADDA122.xsd}NumRefCad"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ADDA122RET_TitActoComplexType", propOrder = { "numCtrlReqPart", "ispbPartRecbdrPrincipal", "ispbPartRecbdrAdmtd", "numCtrlDDA", "numIdentcTit",
        "numIdentcTerc", "numRefAtlCadTerc" })
public class GrupoADDA122RETTitActoComplexType {

    @XmlElement(name = "NumCtrlReqPart", required = true)
    private String numCtrlReqPart;
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

    /**
     * Gets the value of the numCtrlReqPart property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNumCtrlReqPart() {
        return numCtrlReqPart;
    }

    /**
     * Sets the value of the numCtrlReqPart property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNumCtrlReqPart(String value) {
        this.numCtrlReqPart = value;
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

}
