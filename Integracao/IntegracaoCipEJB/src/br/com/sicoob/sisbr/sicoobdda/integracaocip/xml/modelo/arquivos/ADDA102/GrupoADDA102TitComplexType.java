//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.26 at 05:37:00 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Grupo_ADDA102_TitComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ADDA102_TitComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumCtrlReqPart" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}ControleIF"/>
 *         &lt;element name="ISPBPartDestinatarioPrincipal" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}ISPB"/>
 *         &lt;element name="ISPBPartDestinatarioAdmtd" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}ISPB"/>
 *         &lt;element name="NumIdentcTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}NumIdentcTit"/>
 *         &lt;element name="NumRefAtlCadTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}NumRefCad" minOccurs="0"/>
 *         &lt;element name="IndrManutBenfcrioOr" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_BenfcrioOr" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_BenfcrioOrComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutBenfcrioFinl" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMEA"/>
 *         &lt;element name="Grupo_ADDA102_BenfcrioFinl" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_BenfcrioFinlComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutPagdrTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_Pagdr" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_PagdrComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutSacdrAvalst" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_SacdrAvalst" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_SacdrAvalstComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutDocTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_DocTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_DocTitComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutInstcPgtoTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_InstcPgtoTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_InstcPgtoTitComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutInstcVlrRecbt" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_InstcVlrRecbt" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_InstcVlrRecbtComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutJurosTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_JurosTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_JurosTitComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutMultaTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_MultaTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_MultaTitComplexType" minOccurs="0"/>
 *         &lt;element name="IndrManutDesctTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMA"/>
 *         &lt;element name="Grupo_ADDA102_DesctTit" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_DesctTitComplexType" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="Grupo_ADDA102_Calc" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_CalcComplexType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IndrManutHistNotaFis" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMEA"/>
 *         &lt;element name="Grupo_ADDA102_NotaFis" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Grupo_ADDA102_NotaFisComplexType" maxOccurs="30" minOccurs="0"/>
 *         &lt;element name="IndrManutHistTxtInf" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}IndrMEA"/>
 *         &lt;element name="TxtInfBenfcrio" type="{http://www.bcb.gov.br/ARQ/ADDA102.xsd}Txt100" maxOccurs="100" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ADDA102_TitComplexType", propOrder = { "numCtrlReqPart", "ispbPartDestinatarioPrincipal", "ispbPartDestinatarioAdmtd", "numIdentcTit", "numRefAtlCadTit",
        "indrManutBenfcrioOr", "grupoADDA102BenfcrioOr", "indrManutBenfcrioFinl", "grupoADDA102BenfcrioFinl", "indrManutPagdrTit", "grupoADDA102Pagdr", "indrManutSacdrAvalst",
        "grupoADDA102SacdrAvalst", "indrManutDocTit", "grupoADDA102DocTit", "indrManutInstcPgtoTit", "grupoADDA102InstcPgtoTit", "indrManutInstcVlrRecbt",
        "grupoADDA102InstcVlrRecbt", "indrManutJurosTit", "grupoADDA102JurosTit", "indrManutMultaTit", "grupoADDA102MultaTit", "indrManutDesctTit", "grupoADDA102DesctTit",
        "grupoADDA102Calc", "indrManutHistNotaFis", "grupoADDA102NotaFis", "indrManutHistTxtInf", "txtInfBenfcrio" })
public class GrupoADDA102TitComplexType {

    @XmlElement(name = "NumCtrlReqPart", required = true)
    private String numCtrlReqPart;
    @XmlElement(name = "ISPBPartDestinatarioPrincipal", required = true)
    private String ispbPartDestinatarioPrincipal;
    @XmlElement(name = "ISPBPartDestinatarioAdmtd", required = true)
    private String ispbPartDestinatarioAdmtd;
    @XmlElement(name = "NumIdentcTit", required = true)
    private BigInteger numIdentcTit;
    @XmlElement(name = "NumRefAtlCadTit")
    private BigInteger numRefAtlCadTit;
    @XmlElement(name = "IndrManutBenfcrioOr", required = true)
    private String indrManutBenfcrioOr;
    @XmlElement(name = "Grupo_ADDA102_BenfcrioOr")
    private GrupoADDA102BenfcrioOrComplexType grupoADDA102BenfcrioOr;
    @XmlElement(name = "IndrManutBenfcrioFinl", required = true)
    private String indrManutBenfcrioFinl;
    @XmlElement(name = "Grupo_ADDA102_BenfcrioFinl")
    private GrupoADDA102BenfcrioFinlComplexType grupoADDA102BenfcrioFinl;
    @XmlElement(name = "IndrManutPagdrTit", required = true)
    private String indrManutPagdrTit;
    @XmlElement(name = "Grupo_ADDA102_Pagdr")
    private GrupoADDA102PagdrComplexType grupoADDA102Pagdr;
    @XmlElement(name = "IndrManutSacdrAvalst", required = true)
    private String indrManutSacdrAvalst;
    @XmlElement(name = "Grupo_ADDA102_SacdrAvalst")
    private GrupoADDA102SacdrAvalstComplexType grupoADDA102SacdrAvalst;
    @XmlElement(name = "IndrManutDocTit", required = true)
    private String indrManutDocTit;
    @XmlElement(name = "Grupo_ADDA102_DocTit")
    private GrupoADDA102DocTitComplexType grupoADDA102DocTit;
    @XmlElement(name = "IndrManutInstcPgtoTit", required = true)
    private String indrManutInstcPgtoTit;
    @XmlElement(name = "Grupo_ADDA102_InstcPgtoTit")
    private GrupoADDA102InstcPgtoTitComplexType grupoADDA102InstcPgtoTit;
    @XmlElement(name = "IndrManutInstcVlrRecbt", required = true)
    private String indrManutInstcVlrRecbt;
    @XmlElement(name = "Grupo_ADDA102_InstcVlrRecbt")
    private GrupoADDA102InstcVlrRecbtComplexType grupoADDA102InstcVlrRecbt;
    @XmlElement(name = "IndrManutJurosTit", required = true)
    private String indrManutJurosTit;
    @XmlElement(name = "Grupo_ADDA102_JurosTit")
    private GrupoADDA102JurosTitComplexType grupoADDA102JurosTit;
    @XmlElement(name = "IndrManutMultaTit", required = true)
    private String indrManutMultaTit;
    @XmlElement(name = "Grupo_ADDA102_MultaTit")
    private GrupoADDA102MultaTitComplexType grupoADDA102MultaTit;
    @XmlElement(name = "IndrManutDesctTit", required = true)
    private String indrManutDesctTit;
    @XmlElement(name = "Grupo_ADDA102_DesctTit")
    private List<GrupoADDA102DesctTitComplexType> grupoADDA102DesctTit;
    @XmlElement(name = "Grupo_ADDA102_Calc")
    private List<GrupoADDA102CalcComplexType> grupoADDA102Calc;
    @XmlElement(name = "IndrManutHistNotaFis", required = true)
    private String indrManutHistNotaFis;
    @XmlElement(name = "Grupo_ADDA102_NotaFis")
    private List<GrupoADDA102NotaFisComplexType> grupoADDA102NotaFis;
    @XmlElement(name = "IndrManutHistTxtInf", required = true)
    private String indrManutHistTxtInf;
    @XmlElement(name = "TxtInfBenfcrio")
    private List<String> txtInfBenfcrio;

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
     * Gets the value of the numRefAtlCadTit property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumRefAtlCadTit() {
        return numRefAtlCadTit;
    }

    /**
     * Sets the value of the numRefAtlCadTit property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumRefAtlCadTit(BigInteger value) {
        this.numRefAtlCadTit = value;
    }

    /**
     * Gets the value of the indrManutBenfcrioOr property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutBenfcrioOr() {
        return indrManutBenfcrioOr;
    }

    /**
     * Sets the value of the indrManutBenfcrioOr property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutBenfcrioOr(String value) {
        this.indrManutBenfcrioOr = value;
    }

    /**
     * Gets the value of the grupoADDA102BenfcrioOr property.
     * 
     * @return possible object is {@link GrupoADDA102BenfcrioOrComplexType }
     * 
     */
    public GrupoADDA102BenfcrioOrComplexType getGrupoADDA102BenfcrioOr() {
        return grupoADDA102BenfcrioOr;
    }

    /**
     * Sets the value of the grupoADDA102BenfcrioOr property.
     * 
     * @param value allowed object is {@link GrupoADDA102BenfcrioOrComplexType }
     * 
     */
    public void setGrupoADDA102BenfcrioOr(GrupoADDA102BenfcrioOrComplexType value) {
        this.grupoADDA102BenfcrioOr = value;
    }

    /**
     * Gets the value of the indrManutBenfcrioFinl property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutBenfcrioFinl() {
        return indrManutBenfcrioFinl;
    }

    /**
     * Sets the value of the indrManutBenfcrioFinl property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutBenfcrioFinl(String value) {
        this.indrManutBenfcrioFinl = value;
    }

    /**
     * Gets the value of the grupoADDA102BenfcrioFinl property.
     * 
     * @return possible object is {@link GrupoADDA102BenfcrioFinlComplexType }
     * 
     */
    public GrupoADDA102BenfcrioFinlComplexType getGrupoADDA102BenfcrioFinl() {
        return grupoADDA102BenfcrioFinl;
    }

    /**
     * Sets the value of the grupoADDA102BenfcrioFinl property.
     * 
     * @param value allowed object is {@link GrupoADDA102BenfcrioFinlComplexType }
     * 
     */
    public void setGrupoADDA102BenfcrioFinl(GrupoADDA102BenfcrioFinlComplexType value) {
        this.grupoADDA102BenfcrioFinl = value;
    }

    /**
     * Gets the value of the indrManutPagdrTit property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutPagdrTit() {
        return indrManutPagdrTit;
    }

    /**
     * Sets the value of the indrManutPagdrTit property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutPagdrTit(String value) {
        this.indrManutPagdrTit = value;
    }

    /**
     * Gets the value of the grupoADDA102Pagdr property.
     * 
     * @return possible object is {@link GrupoADDA102PagdrComplexType }
     * 
     */
    public GrupoADDA102PagdrComplexType getGrupoADDA102Pagdr() {
        return grupoADDA102Pagdr;
    }

    /**
     * Sets the value of the grupoADDA102Pagdr property.
     * 
     * @param value allowed object is {@link GrupoADDA102PagdrComplexType }
     * 
     */
    public void setGrupoADDA102Pagdr(GrupoADDA102PagdrComplexType value) {
        this.grupoADDA102Pagdr = value;
    }

    /**
     * Gets the value of the indrManutSacdrAvalst property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutSacdrAvalst() {
        return indrManutSacdrAvalst;
    }

    /**
     * Sets the value of the indrManutSacdrAvalst property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutSacdrAvalst(String value) {
        this.indrManutSacdrAvalst = value;
    }

    /**
     * Gets the value of the grupoADDA102SacdrAvalst property.
     * 
     * @return possible object is {@link GrupoADDA102SacdrAvalstComplexType }
     * 
     */
    public GrupoADDA102SacdrAvalstComplexType getGrupoADDA102SacdrAvalst() {
        return grupoADDA102SacdrAvalst;
    }

    /**
     * Sets the value of the grupoADDA102SacdrAvalst property.
     * 
     * @param value allowed object is {@link GrupoADDA102SacdrAvalstComplexType }
     * 
     */
    public void setGrupoADDA102SacdrAvalst(GrupoADDA102SacdrAvalstComplexType value) {
        this.grupoADDA102SacdrAvalst = value;
    }

    /**
     * Gets the value of the indrManutDocTit property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutDocTit() {
        return indrManutDocTit;
    }

    /**
     * Sets the value of the indrManutDocTit property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutDocTit(String value) {
        this.indrManutDocTit = value;
    }

    /**
     * Gets the value of the grupoADDA102DocTit property.
     * 
     * @return possible object is {@link GrupoADDA102DocTitComplexType }
     * 
     */
    public GrupoADDA102DocTitComplexType getGrupoADDA102DocTit() {
        return grupoADDA102DocTit;
    }

    /**
     * Sets the value of the grupoADDA102DocTit property.
     * 
     * @param value allowed object is {@link GrupoADDA102DocTitComplexType }
     * 
     */
    public void setGrupoADDA102DocTit(GrupoADDA102DocTitComplexType value) {
        this.grupoADDA102DocTit = value;
    }

    /**
     * Gets the value of the indrManutInstcPgtoTit property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutInstcPgtoTit() {
        return indrManutInstcPgtoTit;
    }

    /**
     * Sets the value of the indrManutInstcPgtoTit property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutInstcPgtoTit(String value) {
        this.indrManutInstcPgtoTit = value;
    }

    /**
     * Gets the value of the grupoADDA102InstcPgtoTit property.
     * 
     * @return possible object is {@link GrupoADDA102InstcPgtoTitComplexType }
     * 
     */
    public GrupoADDA102InstcPgtoTitComplexType getGrupoADDA102InstcPgtoTit() {
        return grupoADDA102InstcPgtoTit;
    }

    /**
     * Sets the value of the grupoADDA102InstcPgtoTit property.
     * 
     * @param value allowed object is {@link GrupoADDA102InstcPgtoTitComplexType }
     * 
     */
    public void setGrupoADDA102InstcPgtoTit(GrupoADDA102InstcPgtoTitComplexType value) {
        this.grupoADDA102InstcPgtoTit = value;
    }

    /**
     * Gets the value of the indrManutInstcVlrRecbt property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutInstcVlrRecbt() {
        return indrManutInstcVlrRecbt;
    }

    /**
     * Sets the value of the indrManutInstcVlrRecbt property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutInstcVlrRecbt(String value) {
        this.indrManutInstcVlrRecbt = value;
    }

    /**
     * Gets the value of the grupoADDA102InstcVlrRecbt property.
     * 
     * @return possible object is {@link GrupoADDA102InstcVlrRecbtComplexType }
     * 
     */
    public GrupoADDA102InstcVlrRecbtComplexType getGrupoADDA102InstcVlrRecbt() {
        return grupoADDA102InstcVlrRecbt;
    }

    /**
     * Sets the value of the grupoADDA102InstcVlrRecbt property.
     * 
     * @param value allowed object is {@link GrupoADDA102InstcVlrRecbtComplexType }
     * 
     */
    public void setGrupoADDA102InstcVlrRecbt(GrupoADDA102InstcVlrRecbtComplexType value) {
        this.grupoADDA102InstcVlrRecbt = value;
    }

    /**
     * Gets the value of the indrManutJurosTit property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutJurosTit() {
        return indrManutJurosTit;
    }

    /**
     * Sets the value of the indrManutJurosTit property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutJurosTit(String value) {
        this.indrManutJurosTit = value;
    }

    /**
     * Gets the value of the grupoADDA102JurosTit property.
     * 
     * @return possible object is {@link GrupoADDA102JurosTitComplexType }
     * 
     */
    public GrupoADDA102JurosTitComplexType getGrupoADDA102JurosTit() {
        return grupoADDA102JurosTit;
    }

    /**
     * Sets the value of the grupoADDA102JurosTit property.
     * 
     * @param value allowed object is {@link GrupoADDA102JurosTitComplexType }
     * 
     */
    public void setGrupoADDA102JurosTit(GrupoADDA102JurosTitComplexType value) {
        this.grupoADDA102JurosTit = value;
    }

    /**
     * Gets the value of the indrManutMultaTit property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutMultaTit() {
        return indrManutMultaTit;
    }

    /**
     * Sets the value of the indrManutMultaTit property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutMultaTit(String value) {
        this.indrManutMultaTit = value;
    }

    /**
     * Gets the value of the grupoADDA102MultaTit property.
     * 
     * @return possible object is {@link GrupoADDA102MultaTitComplexType }
     * 
     */
    public GrupoADDA102MultaTitComplexType getGrupoADDA102MultaTit() {
        return grupoADDA102MultaTit;
    }

    /**
     * Sets the value of the grupoADDA102MultaTit property.
     * 
     * @param value allowed object is {@link GrupoADDA102MultaTitComplexType }
     * 
     */
    public void setGrupoADDA102MultaTit(GrupoADDA102MultaTitComplexType value) {
        this.grupoADDA102MultaTit = value;
    }

    /**
     * Gets the value of the indrManutDesctTit property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutDesctTit() {
        return indrManutDesctTit;
    }

    /**
     * Sets the value of the indrManutDesctTit property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutDesctTit(String value) {
        this.indrManutDesctTit = value;
    }

    /**
     * Gets the value of the grupoADDA102DesctTit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the grupoADDA102DesctTit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getGrupoADDA102DesctTit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link GrupoADDA102DesctTitComplexType }
     * 
     * 
     */
    public List<GrupoADDA102DesctTitComplexType> getGrupoADDA102DesctTit() {
        if (grupoADDA102DesctTit == null) {
            grupoADDA102DesctTit = new ArrayList<GrupoADDA102DesctTitComplexType>();
        }
        return this.grupoADDA102DesctTit;
    }

    /**
     * Gets the value of the grupoADDA102Calc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the grupoADDA102Calc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getGrupoADDA102Calc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link GrupoADDA102CalcComplexType }
     * 
     * 
     */
    public List<GrupoADDA102CalcComplexType> getGrupoADDA102Calc() {
        if (grupoADDA102Calc == null) {
            grupoADDA102Calc = new ArrayList<GrupoADDA102CalcComplexType>();
        }
        return this.grupoADDA102Calc;
    }

    /**
     * Gets the value of the indrManutHistNotaFis property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutHistNotaFis() {
        return indrManutHistNotaFis;
    }

    /**
     * Sets the value of the indrManutHistNotaFis property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutHistNotaFis(String value) {
        this.indrManutHistNotaFis = value;
    }

    /**
     * Gets the value of the grupoADDA102NotaFis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the grupoADDA102NotaFis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getGrupoADDA102NotaFis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link GrupoADDA102NotaFisComplexType }
     * 
     * 
     */
    public List<GrupoADDA102NotaFisComplexType> getGrupoADDA102NotaFis() {
        if (grupoADDA102NotaFis == null) {
            grupoADDA102NotaFis = new ArrayList<GrupoADDA102NotaFisComplexType>();
        }
        return this.grupoADDA102NotaFis;
    }

    /**
     * Gets the value of the indrManutHistTxtInf property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getIndrManutHistTxtInf() {
        return indrManutHistTxtInf;
    }

    /**
     * Sets the value of the indrManutHistTxtInf property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setIndrManutHistTxtInf(String value) {
        this.indrManutHistTxtInf = value;
    }

    /**
     * Gets the value of the txtInfBenfcrio property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you make to the returned list will be present
     * inside the JAXB object. This is why there is not a <CODE>set</CODE> method for the txtInfBenfcrio property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getTxtInfBenfcrio().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     * 
     * 
     */
    public List<String> getTxtInfBenfcrio() {
        if (txtInfBenfcrio == null) {
            txtInfBenfcrio = new ArrayList<String>();
        }
        return this.txtInfBenfcrio;
    }

}
