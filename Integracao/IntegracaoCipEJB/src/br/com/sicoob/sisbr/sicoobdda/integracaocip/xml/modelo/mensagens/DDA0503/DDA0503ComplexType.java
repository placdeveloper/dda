//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.23 at 04:29:23 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0503;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * <p>
 * Java class for DDA0503ComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDA0503ComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodMsg" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}CodMsg"/>
 *         &lt;element name="NumCtrlPart" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}ControleIF"/>
 *         &lt;element name="ISPBPartDestinatarioPrincipal" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}ISPB"/>
 *         &lt;element name="ISPBPartDestinatarioAdmtd" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}ISPB"/>
 *         &lt;element name="NumIdentcBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}NumIdentcBenfcrio"/>
 *         &lt;element name="NumRefAtlCadBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}NumRefCad" minOccurs="0"/>
 *         &lt;element name="TpPessoaBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}TpPessoa"/>
 *         &lt;element name="CNPJ_CPFBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}CNPJ_CPF"/>
 *         &lt;element name="SitRelctPart" type="{http://www.bcb.gov.br/SPB/DDA0503.xsd}SitRelctPart"/>
 *         &lt;element name="DtFimRelctPart" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
@XmlType(name = "DDA0503ComplexTypeNPC", propOrder = { "codMsg", "numCtrlPart", "ispbPartDestinatarioPrincipal", "ispbPartDestinatarioAdmtd", "numIdentcBenfcrio",
        "numRefAtlCadBenfcrio", "tpPessoaBenfcrio", "cnpjcpfBenfcrio", "sitRelctPart", "dtFimRelctPart", "dtMovto" })
public class DDA0503ComplexType {

    @XmlElement(name = "CodMsg", required = true)
    private String codMsg;
    @XmlElement(name = "NumCtrlPart", required = true)
    private String numCtrlPart;
    @XmlElement(name = "ISPBPartDestinatarioPrincipal", required = true)
    private String ispbPartDestinatarioPrincipal;
    @XmlElement(name = "ISPBPartDestinatarioAdmtd", required = true)
    private String ispbPartDestinatarioAdmtd;
    @XmlElement(name = "NumIdentcBenfcrio", required = true)
    private BigInteger numIdentcBenfcrio;
    @XmlElement(name = "NumRefAtlCadBenfcrio")
    private BigInteger numRefAtlCadBenfcrio;
    @XmlElement(name = "TpPessoaBenfcrio", required = true)
    private String tpPessoaBenfcrio;
    @XmlElement(name = "CNPJ_CPFBenfcrio", required = true)
    private BigInteger cnpjcpfBenfcrio;
    @XmlElement(name = "SitRelctPart", required = true)
    private String sitRelctPart;
    @XmlElement(name = "DtFimRelctPart", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtFimRelctPart;
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
     * Gets the value of the ispbPartDestinatarioPrincipal property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartDestinatarioPrincipal() {
        return ispbPartDestinatarioPrincipal;
    }

    /**
     * Sets the value of the ispbPartDestinatarioPrincipal property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartDestinatarioPrincipal(String value) {
        this.ispbPartDestinatarioPrincipal = value;
    }

    /**
     * Gets the value of the ispbPartDestinatarioAdmtd property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartDestinatarioAdmtd() {
        return ispbPartDestinatarioAdmtd;
    }

    /**
     * Sets the value of the ispbPartDestinatarioAdmtd property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartDestinatarioAdmtd(String value) {
        this.ispbPartDestinatarioAdmtd = value;
    }

    /**
     * Gets the value of the numIdentcBenfcrio property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumIdentcBenfcrio() {
        return numIdentcBenfcrio;
    }

    /**
     * Sets the value of the numIdentcBenfcrio property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumIdentcBenfcrio(BigInteger value) {
        this.numIdentcBenfcrio = value;
    }

    public void setNumIdentcBenfcrio(Long value) {
        if (!ObjectUtil.isNull(value)) {
            this.numIdentcBenfcrio = BigInteger.valueOf(value);
        }
    }

    /**
     * Gets the value of the numRefAtlCadBenfcrio property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumRefAtlCadBenfcrio() {
        return numRefAtlCadBenfcrio;
    }

    /**
     * Sets the value of the numRefAtlCadBenfcrio property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumRefAtlCadBenfcrio(BigInteger value) {
        this.numRefAtlCadBenfcrio = value;
    }

    public void setNumRefAtlCadBenfcrio(Long value) {
        if (!ObjectUtil.isNull(value)) {
            this.numRefAtlCadBenfcrio = BigInteger.valueOf(value);
        }
    }

    /**
     * Gets the value of the tpPessoaBenfcrio property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpPessoaBenfcrio() {
        return tpPessoaBenfcrio;
    }

    /**
     * Sets the value of the tpPessoaBenfcrio property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpPessoaBenfcrio(String value) {
        this.tpPessoaBenfcrio = value;
    }

    /**
     * Gets the value of the cnpjcpfBenfcrio property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getCNPJCPFBenfcrio() {
        return cnpjcpfBenfcrio;
    }

    /**
     * Sets the value of the cnpjcpfBenfcrio property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setCNPJCPFBenfcrio(BigInteger value) {
        this.cnpjcpfBenfcrio = value;
    }

    /**
     * Gets the value of the sitRelctPart property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSitRelctPart() {
        return sitRelctPart;
    }

    /**
     * Sets the value of the sitRelctPart property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setSitRelctPart(String value) {
        this.sitRelctPart = value;
    }

    /**
     * Gets the value of the dtFimRelctPart property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtFimRelctPart() {
        return dtFimRelctPart;
    }

    /**
     * Sets the value of the dtFimRelctPart property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtFimRelctPart(XMLGregorianCalendar value) {
        this.dtFimRelctPart = value;
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

}
