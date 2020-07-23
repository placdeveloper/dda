//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.01.12 at 02:22:09 PM BRST 
//

package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA200;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * <p>
 * Java class for Grupo_ADDA200_DmstrComplexType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Grupo_ADDA200_DmstrComplexType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumIdentcLanc" type="{http://www.bcb.gov.br/ARQ/ADDA200.xsd}NumIdentcTit" minOccurs="0"/>
 *         &lt;element name="DtHrTransc" type="{http://www.bcb.gov.br/ARQ/ADDA200.xsd}DataHora" minOccurs="0"/>
 *         &lt;element name="TpTransc" type="{http://www.bcb.gov.br/ARQ/ADDA200.xsd}TpTransc"/>
 *         &lt;element name="QtdFranq" type="{http://www.bcb.gov.br/ARQ/ADDA200.xsd}Qtd"/>
 *         &lt;element name="QtdLancCobrd" type="{http://www.bcb.gov.br/ARQ/ADDA200.xsd}Qtd"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_ADDA200_DmstrComplexType", propOrder = { "numIdentcLanc", "dtHrTransc", "tpTransc", "qtdFranq", "qtdLancCobrd" })
public class GrupoADDA200DmstrComplexType implements ConteudoMsgRecebida {

    @XmlElement(name = "NumIdentcLanc")
    private BigInteger numIdentcLanc;
    @XmlElement(name = "DtHrTransc")
    private XMLGregorianCalendar dtHrTransc;
    @XmlElement(name = "TpTransc", required = true)
    private String tpTransc;
    @XmlElement(name = "QtdFranq", required = true)
    private BigInteger qtdFranq;
    @XmlElement(name = "QtdLancCobrd", required = true)
    private BigInteger qtdLancCobrd;

    /**
     * Gets the value of the numIdentcLanc property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumIdentcLanc() {
        return numIdentcLanc;
    }

    /**
     * Sets the value of the numIdentcLanc property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumIdentcLanc(BigInteger value) {
        this.numIdentcLanc = value;
    }

    /**
     * Gets the value of the dtHrTransc property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDtHrTransc() {
        return dtHrTransc;
    }

    /**
     * Sets the value of the dtHrTransc property.
     * 
     * @param value allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDtHrTransc(XMLGregorianCalendar value) {
        this.dtHrTransc = value;
    }

    /**
     * Gets the value of the tpTransc property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTpTransc() {
        return tpTransc;
    }

    /**
     * Sets the value of the tpTransc property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setTpTransc(String value) {
        this.tpTransc = value;
    }

    /**
     * Gets the value of the qtdFranq property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getQtdFranq() {
        return qtdFranq;
    }

    /**
     * Sets the value of the qtdFranq property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setQtdFranq(BigInteger value) {
        this.qtdFranq = value;
    }

    /**
     * Gets the value of the qtdLancCobrd property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getQtdLancCobrd() {
        return qtdLancCobrd;
    }

    /**
     * Sets the value of the qtdLancCobrd property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setQtdLancCobrd(BigInteger value) {
        this.qtdLancCobrd = value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getIdMensagemOrigem()
     */
    public Long getIdMensagemOrigem() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getCodMsg()
     */
    public String getCodMsg() {
        return TipoMensagemDDA.ADDA200;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getNumIdent()
     */
    public Long getNumIdent() {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida#getNumCtrlDDA()
     */
    public String getNumCtrlDDA() {
        return null;
    }

}
