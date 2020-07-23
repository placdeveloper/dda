//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.23 at 04:27:45 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502;

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

/**
 * <p>
 * Java class for DDA0502ComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDA0502ComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodMsg" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}CodMsg"/>
 *         &lt;element name="NumCtrlPart" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}ControleIF"/>
 *         &lt;element name="ISPBPartDestinatarioPrincipal" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}ISPB"/>
 *         &lt;element name="ISPBPartDestinatarioAdmtd" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}ISPB"/>
 *         &lt;element name="NumIdentcBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}NumIdentcBenfcrio"/>
 *         &lt;element name="NumRefAtlCadBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}NumRefCad" minOccurs="0"/>
 *         &lt;element name="TpPessoaBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}TpPessoa"/>
 *         &lt;element name="CNPJ_CPFBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}CNPJ_CPF"/>
 *         &lt;element name="Nom_RzSocBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}Nom_RzSoc"/>
 *         &lt;element name="NomFantsBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}Nom_RzSoc" minOccurs="0"/>
 *         &lt;element name="DtIniRelctPart" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Grupo_DDA0502_Conv" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}Grupo_DDA0502_ConvComplexType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Grupo_DDA0502_ReprtteCliBenfcrio" type="{http://www.bcb.gov.br/SPB/DDA0502.xsd}Grupo_DDA0502_ReprtteCliBenfcrioComplexType" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "DDA0502ComplexTypeNPC", propOrder = { "codMsg", "numCtrlPart", "ispbPartDestinatarioPrincipal", "ispbPartDestinatarioAdmtd", "numIdentcBenfcrio",
        "numRefAtlCadBenfcrio", "tpPessoaBenfcrio", "cnpjcpfBenfcrio", "nomRzSocBenfcrio", "nomFantsBenfcrio", "dtIniRelctPart", "grupoDDA0502Conv",
        "grupoDDA0502ReprtteCliBenfcrio", "dtMovto" })
public class DDA0502ComplexType {

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
    @XmlElement(name = "Nom_RzSocBenfcrio", required = true)
    private String nomRzSocBenfcrio;
    @XmlElement(name = "NomFantsBenfcrio")
    private String nomFantsBenfcrio;
    @XmlElement(name = "DtIniRelctPart", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtIniRelctPart;
    @XmlElement(name = "Grupo_DDA0502_Conv")
    private List<GrupoDDA0502ConvComplexType> grupoDDA0502Conv;
    @XmlElement(name = "Grupo_DDA0502_ReprtteCliBenfcrio")
    private List<GrupoDDA0502ReprtteCliBenfcrioComplexType> grupoDDA0502ReprtteCliBenfcrio;
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
     * Gets the value of the nomRzSocBenfcrio property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNomRzSocBenfcrio() {
        return nomRzSocBenfcrio;
    }

    /**
     * Sets the value of the nomRzSocBenfcrio property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNomRzSocBenfcrio(String value) {
        this.nomRzSocBenfcrio = value;
    }

    /**
     * Gets the value of the nomFantsBenfcrio property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNomFantsBenfcrio() {
        return nomFantsBenfcrio;
    }

    /**
     * Sets the value of the nomFantsBenfcrio property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNomFantsBenfcrio(String value) {
        this.nomFantsBenfcrio = value;
    }

    /**
     * Gets the value of the dtIniRelctPart property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtIniRelctPart() {
        return dtIniRelctPart;
    }

    /**
     * Sets the value of the dtIniRelctPart property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtIniRelctPart(XMLGregorianCalendar value) {
        this.dtIniRelctPart = value;
    }

    /**
     * Gets the value of the grupoDDA0502Conv property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the grupoDDA0502Conv property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getGrupoDDA0502Conv().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link GrupoDDA0502ConvComplexType }
     * 
     * 
     */
    public List<GrupoDDA0502ConvComplexType> getGrupoDDA0502Conv() {
        if (grupoDDA0502Conv == null) {
            grupoDDA0502Conv = new ArrayList<GrupoDDA0502ConvComplexType>();
        }
        return this.grupoDDA0502Conv;
    }

    /**
     * Gets the value of the grupoDDA0502ReprtteCliBenfcrio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the grupoDDA0502ReprtteCliBenfcrio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getGrupoDDA0502ReprtteCliBenfcrio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link GrupoDDA0502ReprtteCliBenfcrioComplexType }
     * 
     * 
     */
    public List<GrupoDDA0502ReprtteCliBenfcrioComplexType> getGrupoDDA0502ReprtteCliBenfcrio() {
        if (grupoDDA0502ReprtteCliBenfcrio == null) {
            grupoDDA0502ReprtteCliBenfcrio = new ArrayList<GrupoDDA0502ReprtteCliBenfcrioComplexType>();
        }
        return this.grupoDDA0502ReprtteCliBenfcrio;
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
