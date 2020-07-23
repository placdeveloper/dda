//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 09:22:38 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0200;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * <p>
 * Java class for DDA0200R1ComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDA0200R1ComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodMsg" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}CodMsg"/>
 *         &lt;element name="NumCtrlPart" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}ControleIF"/>
 *         &lt;element name="ISPBPartPrincipal" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}ISPB"/>
 *         &lt;element name="ISPBPartAdmtd" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}ISPB"/>
 *         &lt;element name="NumCtrlDDA" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}ControleIF"/>
 *         &lt;element name="DtIniApurc" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DtFimApurc" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Grupo_DDA0200R1_Dmstr" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}Grupo_DDA0200R1_DmstrComplexType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IndrInfAdcl" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}Indr" minOccurs="0"/>
 *         &lt;element name="SitReqDDA" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}SitCons"/>
 *         &lt;element name="DtHrDDA" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="TamArq" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}TamArq" minOccurs="0"/>
 *         &lt;element name="IdentdArq" type="{http://www.bcb.gov.br/SPB/DDA0200.xsd}IdentdArq" minOccurs="0"/>
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
@XmlType(name = "DDA0200R1ComplexType", propOrder = { "codMsg", "numCtrlPart", "ispbPartPrincipal", "ispbPartAdmtd", "numCtrlDDA", "dtIniApurc", "dtFimApurc",
        "grupoDDA0200R1Dmstr", "indrInfAdcl", "sitReqDDA", "dtHrDDA", "tamArq", "identdArq", "dtMovto" })
public class DDA0200R1ComplexType implements ConteudoMsgRecebida {

    @XmlElement(name = "CodMsg", required = true)
    private String codMsg;
    @XmlElement(name = "NumCtrlPart", required = true)
    private String numCtrlPart;
    @XmlElement(name = "ISPBPartPrincipal", required = true)
    private String ispbPartPrincipal;
    @XmlElement(name = "ISPBPartAdmtd", required = true)
    private String ispbPartAdmtd;
    @XmlElement(name = "NumCtrlDDA", required = true)
    private String numCtrlDDA;
    @XmlElement(name = "DtIniApurc", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtIniApurc;
    @XmlElement(name = "DtFimApurc", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtFimApurc;
    @XmlElement(name = "Grupo_DDA0200R1_Dmstr")
    private List<GrupoDDA0200R1DmstrComplexType> grupoDDA0200R1Dmstr;
    @XmlElement(name = "IndrInfAdcl")
    private String indrInfAdcl;
    @XmlElement(name = "SitReqDDA", required = true)
    private BigInteger sitReqDDA;
    @XmlElement(name = "DtHrDDA", required = true)
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar dtHrDDA;
    @XmlElement(name = "TamArq")
    private BigInteger tamArq;
    @XmlElement(name = "IdentdArq")
    private String identdArq;
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
     * Gets the value of the ispbPartPrincipal property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartPrincipal() {
        return ispbPartPrincipal;
    }

    /**
     * Sets the value of the ispbPartPrincipal property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartPrincipal(String value) {
        this.ispbPartPrincipal = value;
    }

    /**
     * Gets the value of the ispbPartAdmtd property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartAdmtd() {
        return ispbPartAdmtd;
    }

    /**
     * Sets the value of the ispbPartAdmtd property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartAdmtd(String value) {
        this.ispbPartAdmtd = value;
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
     * Gets the value of the dtIniApurc property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtIniApurc() {
        return dtIniApurc;
    }

    /**
     * Sets the value of the dtIniApurc property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtIniApurc(XMLGregorianCalendar value) {
        this.dtIniApurc = value;
    }

    /**
     * Gets the value of the dtFimApurc property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtFimApurc() {
        return dtFimApurc;
    }

    /**
     * Sets the value of the dtFimApurc property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtFimApurc(XMLGregorianCalendar value) {
        this.dtFimApurc = value;
    }

    /**
     * Gets the value of the grupoDDA0200R1Dmstr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the grupoDDA0200R1Dmstr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getGrupoDDA0200R1Dmstr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link GrupoDDA0200R1DmstrComplexType }
     * 
     * 
     */
    public List<GrupoDDA0200R1DmstrComplexType> getGrupoDDA0200R1Dmstr() {
        if (grupoDDA0200R1Dmstr == null) {
            grupoDDA0200R1Dmstr = new ArrayList<GrupoDDA0200R1DmstrComplexType>();
        }
        return this.grupoDDA0200R1Dmstr;
    }

    /**
     * Gets the value of the indrInfAdcl property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrInfAdcl() {
        return indrInfAdcl;
    }

    /**
     * Sets the value of the indrInfAdcl property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrInfAdcl(String value) {
        this.indrInfAdcl = value;
    }

    /**
     * Gets the value of the sitReqDDA property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getSitReqDDA() {
        return sitReqDDA;
    }

    /**
     * Sets the value of the sitReqDDA property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setSitReqDDA(BigInteger value) {
        this.sitReqDDA = value;
    }

    /**
     * Gets the value of the dtHrDDA property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrDDA() {
        return dtHrDDA;
    }

    /**
     * Sets the value of the dtHrDDA property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrDDA(XMLGregorianCalendar value) {
        this.dtHrDDA = value;
    }

    /**
     * Gets the value of the tamArq property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getTamArq() {
        return tamArq;
    }

    /**
     * Sets the value of the tamArq property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setTamArq(BigInteger value) {
        this.tamArq = value;
    }

    /**
     * Gets the value of the identdArq property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIdentdArq() {
        return identdArq;
    }

    /**
     * Sets the value of the identdArq property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIdentdArq(String value) {
        this.identdArq = value;
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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getIdMensagemOrigem()
     */
    public Long getIdMensagemOrigem() {
        if (!ObjectUtil.isEmpty(getNumCtrlPart())) {
            return Long.parseLong(getNumCtrlPart());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getNumIdent()
     */
    public Long getNumIdent() {
        return null;
    }
}
