//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.27 at 09:22:28 AM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for DDA0108ComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DDA0108ComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodMsg" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}CodMsg"/>
 *         &lt;element name="NumCtrlPart" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}ControleIF"/>
 *         &lt;element name="ISPBPartRecbdrPrincipal" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}ISPB"/>
 *         &lt;element name="ISPBPartRecbdrAdmtd" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}ISPB"/>
 *         &lt;element name="NumIdentcTit" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}NumIdentcTit"/>
 *         &lt;element name="NumRefCadTitBaixaOperac" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}NumRefCad" minOccurs="0"/>
 *         &lt;element name="NumRefAtlBaixaOperac" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}NumRefCad" minOccurs="0"/>
 *         &lt;element name="TpBaixaOperac" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}TpBaixaOperac"/>
 *         &lt;element name="ISPBPartRecbdrBaixaOperac" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}ISPB"/>
 *         &lt;element name="CodPartRecbdrBaixaOperac" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}CodIF" minOccurs="0"/>
 *         &lt;element name="TpPessoaPort" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}TpPessoa" minOccurs="0"/>
 *         &lt;element name="CNPJ_CPFPort" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}CNPJ_CPF" minOccurs="0"/>
 *         &lt;element name="DtHrProcBaixaOperac" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DtProcBaixaOperac" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="VlrBaixaOperacTit" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}Valor"/>
 *         &lt;element name="NumCodBarrasBaixaOperac" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}NumCodBarras"/>
 *         &lt;element name="CanPgto" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}CanPgto"/>
 *         &lt;element name="MeioPgto" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}MeioPgto"/>
 *         &lt;element name="IndrOpContg" type="{http://www.bcb.gov.br/SPB/DDA0108.xsd}Indr"/>
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
@XmlType(name = "DDA0108ComplexType", propOrder = { "codMsg", "numCtrlPart", "ispbPartRecbdrPrincipal", "ispbPartRecbdrAdmtd", "numIdentcTit", "numRefCadTitBaixaOperac",
        "numRefAtlBaixaOperac", "tpBaixaOperac", "ispbPartRecbdrBaixaOperac", "codPartRecbdrBaixaOperac", "tpPessoaPort", "cnpjcpfPort", "dtHrProcBaixaOperac",
        "dtProcBaixaOperac", "vlrBaixaOperacTit", "numCodBarrasBaixaOperac", "canPgto", "meioPgto", "indrOpContg", "dtMovto" })
public class DDA0108ComplexType {

    @XmlElement(name = "CodMsg", required = true)
    private String codMsg;
    @XmlElement(name = "NumCtrlPart", required = true)
    private String numCtrlPart;
    @XmlElement(name = "ISPBPartRecbdrPrincipal", required = true)
    private String ispbPartRecbdrPrincipal;
    @XmlElement(name = "ISPBPartRecbdrAdmtd", required = true)
    private String ispbPartRecbdrAdmtd;
    @XmlElement(name = "NumIdentcTit", required = true)
    private BigInteger numIdentcTit;
    @XmlElement(name = "NumRefCadTitBaixaOperac")
    private BigInteger numRefCadTitBaixaOperac;
    @XmlElement(name = "NumRefAtlBaixaOperac")
    private BigInteger numRefAtlBaixaOperac;
    @XmlElement(name = "TpBaixaOperac", required = true)
    private String tpBaixaOperac;
    @XmlElement(name = "ISPBPartRecbdrBaixaOperac", required = true)
    private String ispbPartRecbdrBaixaOperac;
    @XmlElement(name = "CodPartRecbdrBaixaOperac")
    private String codPartRecbdrBaixaOperac;
    @XmlElement(name = "TpPessoaPort")
    private String tpPessoaPort;
    @XmlElement(name = "CNPJ_CPFPort")
    private BigInteger cnpjcpfPort;
    @XmlElement(name = "DtHrProcBaixaOperac", required = true)
    @XmlSchemaType(name = "dateTime")
    private XMLGregorianCalendar dtHrProcBaixaOperac;
    @XmlElement(name = "DtProcBaixaOperac", required = true)
    @XmlSchemaType(name = "date")
    private XMLGregorianCalendar dtProcBaixaOperac;
    @XmlElement(name = "VlrBaixaOperacTit", required = true)
    private BigDecimal vlrBaixaOperacTit;
    @XmlElement(name = "NumCodBarrasBaixaOperac", required = true)
    private String numCodBarrasBaixaOperac;
    @XmlElement(name = "CanPgto", required = true)
    private BigInteger canPgto;
    @XmlElement(name = "MeioPgto", required = true)
    private BigInteger meioPgto;
    @XmlElement(name = "IndrOpContg", required = true)
    private String indrOpContg;
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
     * Gets the value of the numRefCadTitBaixaOperac property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumRefCadTitBaixaOperac() {
        return numRefCadTitBaixaOperac;
    }

    /**
     * Sets the value of the numRefCadTitBaixaOperac property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumRefCadTitBaixaOperac(BigInteger value) {
        this.numRefCadTitBaixaOperac = value;
    }

    /**
     * Gets the value of the numRefAtlBaixaOperac property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumRefAtlBaixaOperac() {
        return numRefAtlBaixaOperac;
    }

    /**
     * Sets the value of the numRefAtlBaixaOperac property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumRefAtlBaixaOperac(BigInteger value) {
        this.numRefAtlBaixaOperac = value;
    }

    /**
     * Gets the value of the tpBaixaOperac property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpBaixaOperac() {
        return tpBaixaOperac;
    }

    /**
     * Sets the value of the tpBaixaOperac property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpBaixaOperac(String value) {
        this.tpBaixaOperac = value;
    }

    /**
     * Gets the value of the ispbPartRecbdrBaixaOperac property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getISPBPartRecbdrBaixaOperac() {
        return ispbPartRecbdrBaixaOperac;
    }

    /**
     * Sets the value of the ispbPartRecbdrBaixaOperac property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setISPBPartRecbdrBaixaOperac(String value) {
        this.ispbPartRecbdrBaixaOperac = value;
    }

    /**
     * Gets the value of the codPartRecbdrBaixaOperac property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getCodPartRecbdrBaixaOperac() {
        return codPartRecbdrBaixaOperac;
    }

    /**
     * Sets the value of the codPartRecbdrBaixaOperac property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCodPartRecbdrBaixaOperac(String value) {
        this.codPartRecbdrBaixaOperac = value;
    }

    /**
     * Gets the value of the tpPessoaPort property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpPessoaPort() {
        return tpPessoaPort;
    }

    /**
     * Sets the value of the tpPessoaPort property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpPessoaPort(String value) {
        this.tpPessoaPort = value;
    }

    /**
     * Gets the value of the cnpjcpfPort property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getCNPJCPFPort() {
        return cnpjcpfPort;
    }

    /**
     * Sets the value of the cnpjcpfPort property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setCNPJCPFPort(BigInteger value) {
        this.cnpjcpfPort = value;
    }

    /**
     * Gets the value of the dtHrProcBaixaOperac property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrProcBaixaOperac() {
        return dtHrProcBaixaOperac;
    }

    /**
     * Sets the value of the dtHrProcBaixaOperac property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrProcBaixaOperac(XMLGregorianCalendar value) {
        this.dtHrProcBaixaOperac = value;
    }

    /**
     * Gets the value of the dtProcBaixaOperac property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtProcBaixaOperac() {
        return dtProcBaixaOperac;
    }

    /**
     * Sets the value of the dtProcBaixaOperac property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtProcBaixaOperac(XMLGregorianCalendar value) {
        this.dtProcBaixaOperac = value;
    }

    /**
     * Gets the value of the vlrBaixaOperacTit property.
     * 
     * @return possible object is {@link BigDecimal }
     * 
     */
    public BigDecimal getVlrBaixaOperacTit() {
        return vlrBaixaOperacTit;
    }

    /**
     * Sets the value of the vlrBaixaOperacTit property.
     * 
     * @param value allowed object is {@link BigDecimal }
     * 
     */
    public void setVlrBaixaOperacTit(BigDecimal value) {
        this.vlrBaixaOperacTit = value;
    }

    /**
     * Gets the value of the numCodBarrasBaixaOperac property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNumCodBarrasBaixaOperac() {
        return numCodBarrasBaixaOperac;
    }

    /**
     * Sets the value of the numCodBarrasBaixaOperac property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setNumCodBarrasBaixaOperac(String value) {
        this.numCodBarrasBaixaOperac = value;
    }

    /**
     * Gets the value of the canPgto property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getCanPgto() {
        return canPgto;
    }

    /**
     * Sets the value of the canPgto property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setCanPgto(BigInteger value) {
        this.canPgto = value;
    }

    /**
     * Gets the value of the meioPgto property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getMeioPgto() {
        return meioPgto;
    }

    /**
     * Sets the value of the meioPgto property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setMeioPgto(BigInteger value) {
        this.meioPgto = value;
    }

    /**
     * Gets the value of the indrOpContg property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrOpContg() {
        return indrOpContg;
    }

    /**
     * Sets the value of the indrOpContg property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrOpContg(String value) {
        this.indrOpContg = value;
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
