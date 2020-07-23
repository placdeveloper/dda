//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.12 at 02:22:06 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA114;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * <p>
 * Java class for Grupo_ADDA114RET_TitActoComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ADDA114RET_TitActoComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumCtrlReqPart" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}ControleIF"/>
 *         &lt;element name="ISPBPartRecbdrPrincipal" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}ISPB"/>
 *         &lt;element name="ISPBPartRecbdrAdmtd" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}ISPB"/>
 *         &lt;element name="NumIdentcTit" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}NumIdentcTit"/>
 *         &lt;element name="NumRefAtlCadTit" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}NumRefCad"/>
 *         &lt;element name="NumIdentcBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}NumIdentcTit"/>
 *         &lt;element name="NumRefAtlBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}NumRefCad"/>
 *         &lt;element name="NumSeqAtlzBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}NumSeqAtlzCadDDA"/>
 *         &lt;element name="NumCodBarrasBaixaOperac" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}NumCodBarras"/>
 *         &lt;element name="NumCtrlDDA" type="{http://www.bcb.gov.br/ARQ/ADDA114.xsd}ControleIF"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ADDA114RET_TitActoComplexType", propOrder = { "numCtrlReqPart", "ispbPartRecbdrPrincipal", "ispbPartRecbdrAdmtd", "numIdentcTit", "numRefAtlCadTit",
        "numIdentcBaixaOperac", "numRefAtlBaixaOperac", "numSeqAtlzBaixaOperac", "numCodBarrasBaixaOperac", "numCtrlDDA" })
public class GrupoADDA114RETTitActoComplexType implements ConteudoMsgRecebida {

    @XmlElement(name = "NumCtrlReqPart", required = true)
    private String numCtrlReqPart;
    @XmlElement(name = "ISPBPartRecbdrPrincipal", required = true)
    private String ispbPartRecbdrPrincipal;
    @XmlElement(name = "ISPBPartRecbdrAdmtd", required = true)
    private String ispbPartRecbdrAdmtd;
    @XmlElement(name = "NumIdentcTit", required = true)
    private BigInteger numIdentcTit;
    @XmlElement(name = "NumRefAtlCadTit", required = true)
    private BigInteger numRefAtlCadTit;
    @XmlElement(name = "NumIdentcBaixaOperac", required = true)
    private BigInteger numIdentcBaixaOperac;
    @XmlElement(name = "NumRefAtlBaixaOperac", required = true)
    private BigInteger numRefAtlBaixaOperac;
    @XmlElement(name = "NumSeqAtlzBaixaOperac", required = true)
    private BigInteger numSeqAtlzBaixaOperac;
    @XmlElement(name = "NumCodBarrasBaixaOperac", required = true)
    private String numCodBarrasBaixaOperac;
    @XmlElement(name = "NumCtrlDDA", required = true)
    private String numCtrlDDA;

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
     * Gets the value of the numIdentcBaixaOperac property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumIdentcBaixaOperac() {
        return numIdentcBaixaOperac;
    }

    /**
     * Sets the value of the numIdentcBaixaOperac property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumIdentcBaixaOperac(BigInteger value) {
        this.numIdentcBaixaOperac = value;
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
     * Gets the value of the numSeqAtlzBaixaOperac property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumSeqAtlzBaixaOperac() {
        return numSeqAtlzBaixaOperac;
    }

    /**
     * Sets the value of the numSeqAtlzBaixaOperac property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumSeqAtlzBaixaOperac(BigInteger value) {
        this.numSeqAtlzBaixaOperac = value;
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

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getIdMensagemOrigem()
     */
    public Long getIdMensagemOrigem() {
        if (!ObjectUtil.isEmpty(getNumCtrlReqPart())) {
            return Long.parseLong(getNumCtrlReqPart());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getCodMsg()
     */
    public String getCodMsg() {
        return TipoMensagemDDA.ADDA114RET;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getNumIdent()
     */
    public Long getNumIdent() {
        if (!ObjectUtil.isEmpty(getNumIdentcTit())) {
            return getNumIdentcTit().longValue();
        }
        return null;
    }

}
