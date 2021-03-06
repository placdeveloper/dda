//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 09:22:39 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0214;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;

/**
 * <p>
 * Java class for DDA0214ComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDA0214ComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodMsg" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}CodMsg"/>
 *         &lt;element name="NumCtrlPart" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}ControleIF"/>
 *         &lt;element name="ISPBPart" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}ISPB"/>
 *         &lt;element name="DtRef" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DtHrIni" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DtHrFim" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TpMsg_Arq" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}TpMsg_Arq" minOccurs="0"/>
 *         &lt;element name="CodMsg_Arq" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}CodMsg_Arq" minOccurs="0"/>
 *         &lt;element name="TpRet" type="{http://www.bcb.gov.br/SPB/DDA0214.xsd}TpRet"/>
 *         &lt;element name="DtMovto" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DDA0214ComplexType", propOrder = { "codMsg", "numCtrlPart", "ispbPart", "dtRef", "dtHrIni", "dtHrFim", "tpMsgArq", "codMsgArq", "tpRet", "dtMovto" })
public class DDA0214ComplexType implements ComplexType {

    @XmlElement(name = "CodMsg", required = true)
    private String codMsg;
    @XmlElement(name = "NumCtrlPart", required = true)
    private String numCtrlPart;
    @XmlElement(name = "ISPBPart", required = true)
    private String ispbPart;
    @XmlElement(name = "DtRef", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtRef;
    @XmlElement(name = "DtHrIni")
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar dtHrIni;
    @XmlElement(name = "DtHrFim")
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar dtHrFim;
    @XmlElement(name = "TpMsg_Arq")
    private String tpMsgArq;
    @XmlElement(name = "CodMsg_Arq")
    private String codMsgArq;
    @XmlElement(name = "TpRet", required = true)
    private String tpRet;
    @XmlElement(name = "DtMovto", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtMovto;

    /**
     * Gets the value of the codMsg property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodMsg() {
        return codMsg;
    }

    /**
     * Sets the value of the codMsg property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodMsg(String value) {
        this.codMsg = value;
    }

    /**
     * Gets the value of the numCtrlPart property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNumCtrlPart() {
        return numCtrlPart;
    }

    /**
     * Sets the value of the numCtrlPart property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNumCtrlPart(String value) {
        this.numCtrlPart = value;
    }

    /**
     * Gets the value of the ispbPart property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPart() {
        return ispbPart;
    }

    /**
     * Sets the value of the ispbPart property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPart(String value) {
        this.ispbPart = value;
    }

    /**
     * Gets the value of the dtRef property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtRef() {
        return dtRef;
    }

    /**
     * Sets the value of the dtRef property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtRef(XMLGregorianCalendar value) {
        this.dtRef = value;
    }

    /**
     * Gets the value of the dtHrIni property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrIni() {
        return dtHrIni;
    }

    /**
     * Sets the value of the dtHrIni property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrIni(XMLGregorianCalendar value) {
        this.dtHrIni = value;
    }

    /**
     * Gets the value of the dtHrFim property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrFim() {
        return dtHrFim;
    }

    /**
     * Sets the value of the dtHrFim property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrFim(XMLGregorianCalendar value) {
        this.dtHrFim = value;
    }

    /**
     * Gets the value of the tpMsgArq property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpMsgArq() {
        return tpMsgArq;
    }

    /**
     * Sets the value of the tpMsgArq property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpMsgArq(String value) {
        this.tpMsgArq = value;
    }

    /**
     * Gets the value of the codMsgArq property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodMsgArq() {
        return codMsgArq;
    }

    /**
     * Sets the value of the codMsgArq property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodMsgArq(String value) {
        this.codMsgArq = value;
    }

    /**
     * Gets the value of the tpRet property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpRet() {
        return tpRet;
    }

    /**
     * Sets the value of the tpRet property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpRet(String value) {
        this.tpRet = value;
    }

    /**
     * Gets the value of the dtMovto property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtMovto() {
        return dtMovto;
    }

    /**
     * Sets the value of the dtMovto property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtMovto(XMLGregorianCalendar value) {
        this.dtMovto = value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType#setIdMensagemDDA(java.lang.String)
     */
    public void setIdMensagemDDA(String id) {
        setNumCtrlPart(id);
    }

}
