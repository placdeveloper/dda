//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 09:22:39 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0214;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Grupo_DDA0214R1_MsgComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_DDA0214R1_MsgComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodMsgProcd" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}CodMsg"/>
 *         &lt;element name="QtdMsgActo" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}Qtd"/>
 *         &lt;element name="QtdMsgRejd" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}Qtd" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_DDA0214R1_MsgComplexType", propOrder = { "codMsgProcd", "qtdMsgActo", "qtdMsgRejd" })
public class GrupoDDA0214R1MsgComplexType {

    @XmlElement(name = "CodMsgProcd", required = true)
    private String codMsgProcd;
    @XmlElement(name = "QtdMsgActo", required = true)
    private BigInteger qtdMsgActo;
    @XmlElement(name = "QtdMsgRejd")
    private BigInteger qtdMsgRejd;

    /**
     * Gets the value of the codMsgProcd property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodMsgProcd() {
        return codMsgProcd;
    }

    /**
     * Sets the value of the codMsgProcd property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodMsgProcd(String value) {
        this.codMsgProcd = value;
    }

    /**
     * Gets the value of the qtdMsgActo property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getQtdMsgActo() {
        return qtdMsgActo;
    }

    /**
     * Sets the value of the qtdMsgActo property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setQtdMsgActo(BigInteger value) {
        this.qtdMsgActo = value;
    }

    /**
     * Gets the value of the qtdMsgRejd property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getQtdMsgRejd() {
        return qtdMsgRejd;
    }

    /**
     * Sets the value of the qtdMsgRejd property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setQtdMsgRejd(BigInteger value) {
        this.qtdMsgRejd = value;
    }

}
