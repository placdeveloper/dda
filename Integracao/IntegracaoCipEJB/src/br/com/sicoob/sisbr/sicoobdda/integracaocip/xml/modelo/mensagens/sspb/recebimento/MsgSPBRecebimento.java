package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.DDA0001R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.DDA0002R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0005.DDA0005R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0006.DDA0006R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.DDA0106R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.DDA0110R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0115.DDA0115R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0115.DDA0115R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0118.DDA0118R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0118.DDA0118R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121R3ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0127.DDA0127ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0200.DDA0200R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0214.DDA0214R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.DDA0215R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0400.DDA0400ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0401.DDA0401R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0402.DDA0402ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0403.DDA0403ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0404.DDA0404ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501.DDA0501R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.DDA0502R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0503.DDA0503R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.DDA0504R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0506.DDA0506ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0004.GEN0004ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014.GEN0014R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0015.GEN0015ComplexType;

/**
 * 
 * @author Felipe.Rosa
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MsgSPBRecebimento {

    /** Mensagens 0500 */
    @XmlElement(name = "DDA0501R1")
    private DDA0501R1ComplexType dda0501R1;

    @XmlElement(name = "DDA0502R1")
    private DDA0502R1ComplexType dda0502R1;

    @XmlElement(name = "DDA0503R1")
    private DDA0503R1ComplexType dda0503R1;

    @XmlElement(name = "DDA0504R1")
    private DDA0504R1ComplexType dda0504R1;

    @XmlElement(name = "DDA0505R1")
    private DDA0505R1ComplexType dda0505R1;

    @XmlElement(name = "DDA0506")
    private DDA0506ComplexType dda0506;

    /** Mensagens 0200 */

    @XmlElement(name = "DDA0200R1")
    private DDA0200R1ComplexType dda0200R1;

    @XmlElement(name = "DDA0214R1")
    private DDA0214R1ComplexType dda0214R1;

    @XmlElement(name = "DDA0215R1")
    private DDA0215R1ComplexType dda0215R1;

    /** Mensagens 0400 */

    @XmlElement(name = "DDA0400")
    private DDA0400ComplexType dda0400;

    @XmlElement(name = "DDA0401R1")
    private DDA0401R1ComplexType dda0401R1;

    @XmlElement(name = "DDA0402")
    private DDA0402ComplexType dda0402;

    @XmlElement(name = "DDA0403")
    private DDA0403ComplexType dda0403;

    @XmlElement(name = "DDA0404")
    private DDA0404ComplexType dda0404;

    /** Mensagens Inclusao Pagador */

    @XmlElement(name = "DDA0001R1")
    private DDA0001R1ComplexType dda0001R1;

    /** Mensagens Consulta Pagador */

    @XmlElement(name = "DDA0002R1")
    private DDA0002R1ComplexType dda0002R1;

    /** Mensagens Alteracao Pagador */

    @XmlElement(name = "DDA0005R1")
    private DDA0005R1ComplexType dda0005R1;

    /** Mensagens Exclusao Pagador */
    @XmlElement(name = "DDA0006R1")
    private DDA0006R1ComplexType dda0006R1;

    /** Mensagens Inclusao Boleto */

    @XmlElement(name = "DDA0101R1")
    private DDA0101R1ComplexType dda0101R1;

    @XmlElement(name = "DDA0101R2")
    private DDA0101R2ComplexType dda0101R2;

    /** Mensagens Alteracao Boleto */

    @XmlElement(name = "DDA0102R1")
    private DDA0102R1ComplexType dda0102R1;

    @XmlElement(name = "DDA0102R2")
    private DDA0102R2ComplexType dda0102R2;

    /** Mensagens Aceite */

    @XmlElement(name = "DDA0104R1")
    private DDA0104R1ComplexType dda0104R1;

    @XmlElement(name = "DDA0104R2")
    private DDA0104R2ComplexType dda0104R2;

    /** Mensagens Consulta Boleto Pagamento */

    @XmlElement(name = "DDA0106R1")
    private DDA0106R1ComplexType dda0106R1;

    /** Mensagens Baixa Operacional */

    @XmlElement(name = "DDA0108R1")
    private DDA0108R1ComplexType dda0108R1;

    @XmlElement(name = "DDA0108R2")
    private DDA0108R2ComplexType dda0108R2;

    /** Mensagens Cancelamento Baixa Operacional */

    @XmlElement(name = "DDA0115R1")
    private DDA0115R1ComplexType dda0115R1;

    @XmlElement(name = "DDA0115R2")
    private DDA0115R2ComplexType dda0115R2;

    /** Mensagens Baixa Efetiva */

    @XmlElement(name = "DDA0118R1")
    private DDA0118R1ComplexType dda0118R1;

    @XmlElement(name = "DDA0118R2")
    private DDA0118R2ComplexType dda0118R2;

    /** Mensagens inclusao Terceiro Autorizado */

    @XmlElement(name = "DDA0121R1")
    private DDA0121R1ComplexType dda0121R1;

    @XmlElement(name = "DDA0121R2")
    private DDA0121R2ComplexType dda0121R2;

    @XmlElement(name = "DDA0121R3")
    private DDA0121R3ComplexType dda0121R3;

    /** Mensagens Exclusao Terceiro Autorizado */

    @XmlElement(name = "DDA0122R1")
    private DDA0122R1ComplexType dda0122R1;

    @XmlElement(name = "DDA0122R2")
    private DDA0122R2ComplexType dda0122R2;

    /** Mensagens Boletos pagamento em Aberto */
    @XmlElement(name = "DDA0127")
    private DDA0127ComplexType dda0127;

    /** Mensagens Consulta Boleto pagamento para pagamento */
    @XmlElement(name = "DDA0110R1")
    private DDA0110R1ComplexType dda0110R1;

    /** Distribuição */
    @XmlElement(name = "GEN0004")
    private GEN0004ComplexType gen0004;

    @XmlElement(name = "GEN0014R1")
    private GEN0014R1ComplexType gen0014R1;

    @XmlElement(name = "GEN0015")
    private GEN0015ComplexType gen0015;

    /**
     * @return o atributo dda0501R1NPC
     */
    public DDA0501R1ComplexType getDda0501R1() {
        return dda0501R1;
    }

    /**
     * Define o atributo dda0501R1NPC
     */
    public void setDda0501R1(DDA0501R1ComplexType dda0501r1) {
        dda0501R1 = dda0501r1;
    }

    /**
     * @return the dda0502R1
     */
    public DDA0502R1ComplexType getDDA0502R1() {
        return dda0502R1;
    }

    /**
     * @param dda0502r1 the dda0502R1 to set
     */
    public void setDDA0502R1(DDA0502R1ComplexType dda0502r1) {
        dda0502R1 = dda0502r1;
    }

    /**
     * @return the dda0503R1
     */
    public DDA0503R1ComplexType getDDA0503R1() {
        return dda0503R1;
    }

    /**
     * @param dda0503r1 the dda0503R1 to set
     */
    public void setDDA0503R1(DDA0503R1ComplexType dda0503r1) {
        dda0503R1 = dda0503r1;
    }

    /**
     * @return the dda0504R1
     */
    public DDA0504R1ComplexType getDDA0504R1() {
        return dda0504R1;
    }

    /**
     * @param dda0504r1 the dda0504R1 to set
     */
    public void setDDA0504R1(DDA0504R1ComplexType dda0504r1) {
        dda0504R1 = dda0504r1;
    }

    /**
     * @return the dda0505R1
     */
    public DDA0505R1ComplexType getDDA0505R1() {
        return dda0505R1;
    }

    /**
     * @param dda0505r1 the dda0505R1 to set
     */
    public void setDDA0505R1(DDA0505R1ComplexType dda0505r1) {
        dda0505R1 = dda0505r1;
    }

    /**
     * @return the dda0506
     */
    public DDA0506ComplexType getDDA0506() {
        return dda0506;
    }

    /**
     * @param dda0506 the dda0506 to set
     */
    public void setDDA0506(DDA0506ComplexType dda0506) {
        this.dda0506 = dda0506;
    }

    /**
     * @return the gen0014R1
     */
    public GEN0014R1ComplexType getGEN0014R1() {
        return gen0014R1;
    }

    /**
     * @param gen0014r1 the gen0014R1 to set
     */
    public void setGEN0014R1(GEN0014R1ComplexType gen0014r1) {
        gen0014R1 = gen0014r1;
    }

    /**
     * @return the gen0015
     */
    public GEN0015ComplexType getGEN0015() {
        return gen0015;
    }

    /**
     * @param gen0015 the gen0015 to set
     */
    public void setGEN0015(GEN0015ComplexType gen0015) {
        this.gen0015 = gen0015;
    }

    /**
     * @return the dda0200R1
     */
    public DDA0200R1ComplexType getDDA0200R1() {
        return dda0200R1;
    }

    /**
     * @param dda0200r1 the dda0200R1 to set
     */
    public void setDDA0200R1(DDA0200R1ComplexType dda0200r1) {
        dda0200R1 = dda0200r1;
    }

    /**
     * @return the dda0214R1
     */
    public DDA0214R1ComplexType getDDA0214R1() {
        return dda0214R1;
    }

    /**
     * @param dda0214r1 the dda0214R1 to set
     */
    public void setDDA0214R1(DDA0214R1ComplexType dda0214r1) {
        dda0214R1 = dda0214r1;
    }

    /**
     * @return the dda0215R1
     */
    public DDA0215R1ComplexType getDDA0215R1() {
        return dda0215R1;
    }

    /**
     * @param dda0215r1 the dda0215R1 to set
     */
    public void setDDA0215R1(DDA0215R1ComplexType dda0215r1) {
        dda0215R1 = dda0215r1;
    }

    /**
     * @return the dda0001R1
     */
    public DDA0001R1ComplexType getDDA0001R1() {
        return dda0001R1;
    }

    /**
     * @param dda0001r1 the dda0001R1 to set
     */
    public void setDDA0001R1(DDA0001R1ComplexType dda0001r1) {
        dda0001R1 = dda0001r1;
    }

    /**
     * @return the dda0002R1
     */
    public DDA0002R1ComplexType getDDA0002R1() {
        return dda0002R1;
    }

    /**
     * @param dda0002r1 the dda0002R1 to set
     */
    public void setDDA0002R1(DDA0002R1ComplexType dda0002r1) {
        dda0002R1 = dda0002r1;
    }

    /**
     * @return the dda0005R1
     */
    public DDA0005R1ComplexType getDDA0005R1() {
        return dda0005R1;
    }

    /**
     * @param dda0005r1 the dda0005R1 to set
     */
    public void setDDA0005R1(DDA0005R1ComplexType dda0005r1) {
        dda0005R1 = dda0005r1;
    }

    /**
     * @return the dda0006R1
     */
    public DDA0006R1ComplexType getDDA0006R1() {
        return dda0006R1;
    }

    /**
     * @param dda0006r1 the dda0006R1 to set
     */
    public void setDDA0006R1(DDA0006R1ComplexType dda0006r1) {
        dda0006R1 = dda0006r1;
    }

    /**
     * @return the dda0106R1
     */
    public DDA0106R1ComplexType getDda0106R1() {
        return dda0106R1;
    }

    /**
     * @param dda0106r1 the dda0106R1 to set
     */
    public void setDda0106R1(DDA0106R1ComplexType dda0106r1) {
        dda0106R1 = dda0106r1;
    }

    /**
     * @return the dda0401R1
     */
    public DDA0401R1ComplexType getDda0401R1() {
        return dda0401R1;
    }

    /**
     * @param dda0401r1 the dda0401R1 to set
     */
    public void setDda0401R1(DDA0401R1ComplexType dda0401r1) {
        dda0401R1 = dda0401r1;
    }

    /**
     * @return the dda0402
     */
    public DDA0402ComplexType getDda0402() {
        return dda0402;
    }

    /**
     * @param dda0402 the dda0402 to set
     */
    public void setDda0402(DDA0402ComplexType dda0402) {
        this.dda0402 = dda0402;
    }

    /**
     * @return the dda0403
     */
    public DDA0403ComplexType getDda0403() {
        return dda0403;
    }

    /**
     * @param dda0403 the dda0403 to set
     */
    public void setDda0403(DDA0403ComplexType dda0403) {
        this.dda0403 = dda0403;
    }

    /**
     * @return the dda0404
     */
    public DDA0404ComplexType getDda0404() {
        return dda0404;
    }

    /**
     * @param dda0404 the dda0404 to set
     */
    public void setDda0404(DDA0404ComplexType dda0404) {
        this.dda0404 = dda0404;
    }

    /**
     * @return the gen0004
     */
    public GEN0004ComplexType getGen0004() {
        return gen0004;
    }

    /**
     * @param gen0004 the gen0004 to set
     */
    public void setGen0004(GEN0004ComplexType gen0004) {
        this.gen0004 = gen0004;
    }

    /**
     * @return o atributo dda0101R1
     */
    public DDA0101R1ComplexType getDda0101R1() {
        return dda0101R1;
    }

    /**
     * Define o atributo dda0101R1
     */
    public void setDda0101R1(DDA0101R1ComplexType dda0101r1) {
        dda0101R1 = dda0101r1;
    }

    /**
     * @return o atributo dda0102R1
     */
    public DDA0102R1ComplexType getDda0102R1() {
        return dda0102R1;
    }

    /**
     * Define o atributo dda0102R1
     */
    public void setDda0102R1(DDA0102R1ComplexType dda0102r1) {
        dda0102R1 = dda0102r1;
    }

    /**
     * @return o atributo dda0108R1
     */
    public DDA0108R1ComplexType getDda0108R1() {
        return dda0108R1;
    }

    /**
     * Define o atributo dda0108R1
     */
    public void setDda0108R1(DDA0108R1ComplexType dda0108r1) {
        dda0108R1 = dda0108r1;
    }

    /**
     * @return o atributo dda0108R2
     */
    public DDA0108R2ComplexType getDda0108R2() {
        return dda0108R2;
    }

    /**
     * Define o atributo dda0108R2
     */
    public void setDda0108R2(DDA0108R2ComplexType dda0108r2) {
        dda0108R2 = dda0108r2;
    }

    /**
     * @return o atributo dda0115R1
     */
    public DDA0115R1ComplexType getDda0115R1() {
        return dda0115R1;
    }

    /**
     * Define o atributo dda0115R1
     */
    public void setDda0115R1(DDA0115R1ComplexType dda0115r1) {
        dda0115R1 = dda0115r1;
    }

    /**
     * @return o atributo dda0121R1
     */
    public DDA0121R1ComplexType getDda0121R1() {
        return dda0121R1;
    }

    /**
     * Define o atributo dda0121R1
     */
    public void setDda0121R1(DDA0121R1ComplexType dda0121r1) {
        dda0121R1 = dda0121r1;
    }

    /**
     * @return o atributo dda0101R2
     */
    public DDA0101R2ComplexType getDda0101R2() {
        return dda0101R2;
    }

    /**
     * Define o atributo dda0101R2
     */
    public void setDda0101R2(DDA0101R2ComplexType dda0101r2) {
        dda0101R2 = dda0101r2;
    }

    /**
     * @return o atributo dda0102R2
     */
    public DDA0102R2ComplexType getDda0102R2() {
        return dda0102R2;
    }

    /**
     * Define o atributo dda0102R2
     */
    public void setDda0102R2(DDA0102R2ComplexType dda0102r2) {
        dda0102R2 = dda0102r2;
    }

    /**
     * @return o atributo dda0104R1
     */
    public DDA0104R1ComplexType getDda0104R1() {
        return dda0104R1;
    }

    /**
     * Define o atributo dda0104R1
     */
    public void setDda0104R1(DDA0104R1ComplexType dda0104r1) {
        dda0104R1 = dda0104r1;
    }

    /**
     * @return o atributo dda0104R2
     */
    public DDA0104R2ComplexType getDda0104R2() {
        return dda0104R2;
    }

    /**
     * Define o atributo dda0104R2
     */
    public void setDda0104R2(DDA0104R2ComplexType dda0104r2) {
        dda0104R2 = dda0104r2;
    }

    public DDA0127ComplexType getDda0127() {
        return dda0127;
    }

    public void setDda0127(DDA0127ComplexType dda0127) {
        this.dda0127 = dda0127;
    }

    /**
     * @return o atributo dda0122R1
     */
    public DDA0122R1ComplexType getDda0122R1() {
        return dda0122R1;
    }

    /**
     * Define o atributo dda0122R1
     */
    public void setDda0122R1(DDA0122R1ComplexType dda0122r1) {
        dda0122R1 = dda0122r1;
    }

    /**
     * @return o atributo dda0122R2
     */
    public DDA0122R2ComplexType getDda0122R2() {
        return dda0122R2;
    }

    /**
     * Define o atributo dda0122R2
     */
    public void setDda0122R2(DDA0122R2ComplexType dda0122r2) {
        dda0122R2 = dda0122r2;
    }

    /**
     * @return o atributo dda0121R2
     */
    public DDA0121R2ComplexType getDda0121R2() {
        return dda0121R2;
    }

    /**
     * Define o atributo dda0121R2
     */
    public void setDda0121R2(DDA0121R2ComplexType dda0121r2) {
        dda0121R2 = dda0121r2;
    }

    public DDA0400ComplexType getDda0400() {
        return dda0400;
    }

    public void setDda0400(DDA0400ComplexType dda0400) {
        this.dda0400 = dda0400;
    }

    /**
     * @return o atributo dda0118R1
     */
    public DDA0118R1ComplexType getDda0118R1() {
        return dda0118R1;
    }

    /**
     * Define o atributo dda0118R1
     */
    public void setDda0118R1(DDA0118R1ComplexType dda0118r1) {
        dda0118R1 = dda0118r1;
    }

    /**
     * @return o atributo dda0118R2
     */
    public DDA0118R2ComplexType getDda0118R2() {
        return dda0118R2;
    }

    /**
     * Define o atributo dda0118R2
     */
    public void setDda0118R2(DDA0118R2ComplexType dda0118r2) {
        dda0118R2 = dda0118r2;
    }

    /**
     * @return o atributo dda0121R3
     */
    public DDA0121R3ComplexType getDda0121R3() {
        return dda0121R3;
    }

    /**
     * Define o atributo dda0121R3
     */
    public void setDda0121R3(DDA0121R3ComplexType dda0121r3) {
        dda0121R3 = dda0121r3;
    }

    /**
     * @return o atributo dda0110R1
     */
    public DDA0110R1ComplexType getDda0110R1() {
        return dda0110R1;
    }

    /**
     * Define o atributo dda0110R1
     */
    public void setDda0110R1(DDA0110R1ComplexType dda0110r1) {
        dda0110R1 = dda0110r1;
    }

    /**
     * @return o atributo dda0115R2
     */
    public DDA0115R2ComplexType getDda0115R2() {
        return dda0115R2;
    }

    /**
     * Define o atributo dda0115R2
     */
    public void setDda0115R2(DDA0115R2ComplexType dda0115r2) {
        dda0115R2 = dda0115r2;
    }

}
