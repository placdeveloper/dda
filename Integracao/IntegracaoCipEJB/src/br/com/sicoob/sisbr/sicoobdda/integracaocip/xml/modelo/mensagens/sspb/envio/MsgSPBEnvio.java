package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.envio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001.AGEN001ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.DDA0001ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.DDA0002ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0005.DDA0005ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0006.DDA0006ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.DDA0106ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.DDA0110ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0115.DDA0115ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0118.DDA0118ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0200.DDA0200ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0214.DDA0214ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.DDA0215ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0401.DDA0401ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501.DDA0501ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.DDA0502ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0503.DDA0503ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.DDA0504ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014.GEN0014ComplexType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MsgSPB", propOrder = { "dda0501", "dda0502", "dda0503", "dda0504", "dda0505", "gen0014", "dda0200", "dda0001", "dda0002", "dda0005", "dda0006", "dda0101",
        "dda0102", "dda0104", "dda0106", "dda0108", "dda0110", "dda0115", "dda0118", "dda0121", "dda0122", "dda0401", "dda0215", "dda0214", "agen001" })
public class MsgSPBEnvio {

    @XmlElement(name = "DDA0501")
    private DDA0501ComplexType dda0501;

    @XmlElement(name = "DDA0502")
    private DDA0502ComplexType dda0502;

    @XmlElement(name = "DDA0503")
    private DDA0503ComplexType dda0503;

    @XmlElement(name = "DDA0504")
    private DDA0504ComplexType dda0504;

    @XmlElement(name = "DDA0505")
    private DDA0505ComplexType dda0505;

    @XmlElement(name = "GEN0014")
    private GEN0014ComplexType gen0014;

    @XmlElement(name = "DDA0200")
    private DDA0200ComplexType dda0200;

    @XmlElement(name = "DDA0001")
    private DDA0001ComplexType dda0001;

    @XmlElement(name = "DDA0002")
    private DDA0002ComplexType dda0002;

    @XmlElement(name = "DDA0005")
    private DDA0005ComplexType dda0005;

    @XmlElement(name = "DDA0006")
    private DDA0006ComplexType dda0006;

    @XmlElement(name = "DDA0101")
    private DDA0101ComplexType dda0101;

    @XmlElement(name = "DDA0102")
    private DDA0102ComplexType dda0102;

    @XmlElement(name = "DDA0104")
    private DDA0104ComplexType dda0104;

    @XmlElement(name = "DDA0106")
    private DDA0106ComplexType dda0106;

    @XmlElement(name = "DDA0108")
    private DDA0108ComplexType dda0108;

    @XmlElement(name = "DDA0110")
    private DDA0110ComplexType dda0110;

    @XmlElement(name = "DDA0115")
    private DDA0115ComplexType dda0115;

    @XmlElement(name = "DDA0118")
    private DDA0118ComplexType dda0118;

    @XmlElement(name = "DDA0121")
    private DDA0121ComplexType dda0121;

    @XmlElement(name = "DDA0122")
    private DDA0122ComplexType dda0122;

    @XmlElement(name = "DDA0401")
    private DDA0401ComplexType dda0401;

    @XmlElement(name = "DDA0215")
    private DDA0215ComplexType dda0215;

    @XmlElement(name = "DDA0214")
    private DDA0214ComplexType dda0214;

    @XmlElement(name = "AGEN001")
    private AGEN001ComplexType agen001;

    /**
     * @return the dda0501
     */
    public DDA0501ComplexType getDDA0501() {
        return dda0501;
    }

    /**
     * @param dda0501 the dda0501 to set
     */
    public void setDDA0501(DDA0501ComplexType dda0501) {
        this.dda0501 = dda0501;
    }

    /**
     * @return the dda0502
     */
    public DDA0502ComplexType getDDA0502() {
        return dda0502;
    }

    /**
     * @param dda0502 the dda0502 to set
     */
    public void setDDA0502(DDA0502ComplexType dda0502) {
        this.dda0502 = dda0502;
    }

    /**
     * @return the dda0503
     */
    public DDA0503ComplexType getDDA0503() {
        return dda0503;
    }

    /**
     * @param dda0503 the dda0503 to set
     */
    public void setDDA0503(DDA0503ComplexType dda0503) {
        this.dda0503 = dda0503;
    }

    /**
     * @return the dda0504
     */
    public DDA0504ComplexType getDDA0504() {
        return dda0504;
    }

    /**
     * @param dda0504 the dda0504 to set
     */
    public void setDDA0504(DDA0504ComplexType dda0504) {
        this.dda0504 = dda0504;
    }

    /**
     * @return the dda0505
     */
    public DDA0505ComplexType getDDA0505() {
        return dda0505;
    }

    /**
     * @param dda0505 the dda0505 to set
     */
    public void setDDA0505(DDA0505ComplexType dda0505) {
        this.dda0505 = dda0505;
    }

    /**
     * @return the gen0014
     */
    public GEN0014ComplexType getGen0014() {
        return gen0014;
    }

    /**
     * @param gen0014 the gen0014 to set
     */
    public void setGen0014(GEN0014ComplexType gen0014) {
        this.gen0014 = gen0014;
    }

    /**
     * @return the dda0200
     */
    public DDA0200ComplexType getDDA0200() {
        return dda0200;
    }

    /**
     * @param dda0200 the dda0200 to set
     */
    public void setDDA0200(DDA0200ComplexType dda0200) {
        this.dda0200 = dda0200;
    }

    /**
     * @return o atributo dda0001
     */
    public DDA0001ComplexType getDDA0001() {
        return dda0001;
    }

    /**
     * Define o atributo dda0001
     */
    public void setDDA0001(DDA0001ComplexType dda0001) {
        this.dda0001 = dda0001;
    }

    /**
     * @return o atributo dda0002
     */
    public DDA0002ComplexType getDDA0002() {
        return dda0002;
    }

    /**
     * Define o atributo dda0002
     */
    public void setDDA0002(DDA0002ComplexType dda0002) {
        this.dda0002 = dda0002;
    }

    /**
     * @return o atributo dda0005
     */
    public DDA0005ComplexType getDDA0005() {
        return dda0005;
    }

    /**
     * Define o atributo dda0005
     */
    public void setDDA0005(DDA0005ComplexType dda0005) {
        this.dda0005 = dda0005;
    }

    /**
     * @return o atributo dda0006
     */
    public DDA0006ComplexType getDDA0006() {
        return dda0006;
    }

    /**
     * Define o atributo dda0006
     */
    public void setDDA0006(DDA0006ComplexType dda0006) {
        this.dda0006 = dda0006;
    }

    /**
     * @return o atributo dda0101
     */
    public DDA0101ComplexType getDDA0101() {
        return dda0101;
    }

    /**
     * Define o atributo dda0101
     */
    public void setDDA0101(DDA0101ComplexType dda0101) {
        this.dda0101 = dda0101;
    }

    /**
     * @return o atributo dda0102
     */
    public DDA0102ComplexType getDDA0102() {
        return dda0102;
    }

    /**
     * Define o atributo dda0102
     */
    public void setDDA0102(DDA0102ComplexType dda0102) {
        this.dda0102 = dda0102;
    }

    /**
     * @return o atributo dda0104
     */
    public DDA0104ComplexType getDDA0104() {
        return dda0104;
    }

    /**
     * Define o atributo dda0104
     */
    public void setDDA0104(DDA0104ComplexType dda0104) {
        this.dda0104 = dda0104;
    }

    /**
     * @return o atributo dda0106
     */
    public DDA0106ComplexType getDDA0106() {
        return dda0106;
    }

    /**
     * Define o atributo dda0106
     */
    public void setDDA0106(DDA0106ComplexType dda0106) {
        this.dda0106 = dda0106;
    }

    /**
     * @return o atributo dda0108
     */
    public DDA0108ComplexType getDDA0108() {
        return dda0108;
    }

    /**
     * Define o atributo dda0108
     */
    public void setDDA0108(DDA0108ComplexType dda0108) {
        this.dda0108 = dda0108;
    }

    /**
     * @return o atributo dda0110
     */
    public DDA0110ComplexType getDDA0110() {
        return dda0110;
    }

    /**
     * Define o atributo dda0110
     */
    public void setDDA0110(DDA0110ComplexType dda0110) {
        this.dda0110 = dda0110;
    }

    /**
     * @return o atributo dda0115
     */
    public DDA0115ComplexType getDDA0115() {
        return dda0115;
    }

    /**
     * Define o atributo dda0115
     */
    public void setDDA0115(DDA0115ComplexType dda0115) {
        this.dda0115 = dda0115;
    }

    /**
     * @return o atributo dda0118
     */
    public DDA0118ComplexType getDDA0118() {
        return dda0118;
    }

    /**
     * Define o atributo dda0118
     */
    public void setDDA0118(DDA0118ComplexType dda0118) {
        this.dda0118 = dda0118;
    }

    /**
     * @return o atributo dda0121
     */
    public DDA0121ComplexType getDDA0121() {
        return dda0121;
    }

    /**
     * Define o atributo dda0121
     */
    public void setDDA0121(DDA0121ComplexType dda0121) {
        this.dda0121 = dda0121;
    }

    /**
     * @return o atributo dda0122
     */
    public DDA0122ComplexType getDDA0122() {
        return dda0122;
    }

    /**
     * Define o atributo dda0122
     */
    public void setDDA0122(DDA0122ComplexType dda0122) {
        this.dda0122 = dda0122;
    }

    /**
     * @return o atributo dda0401
     */
    public DDA0401ComplexType getDDA0401() {
        return dda0401;
    }

    /**
     * Define o atributo dda0401
     */
    public void setDDA0401(DDA0401ComplexType dda0401) {
        this.dda0401 = dda0401;
    }

    /**
     * @return o atributo dda0215
     */
    public DDA0215ComplexType getDDA0215() {
        return dda0215;
    }

    /**
     * Define o atributo dda0215
     */
    public void setDDA0215(DDA0215ComplexType dda0215) {
        this.dda0215 = dda0215;
    }

    /**
     * @return o atributo dda0214
     */
    public DDA0214ComplexType getDDA0214() {
        return dda0214;
    }

    /**
     * @return the agen001
     */
    public AGEN001ComplexType getAgen001() {
        return agen001;
    }

    /**
     * @param agen001 the agen001 to set
     */
    public void setAgen001(AGEN001ComplexType agen001) {
        this.agen001 = agen001;
    }

    /**
     * Define o atributo AGEN001
     */
    public void setDDA0214(DDA0214ComplexType dda0214) {
        this.dda0214 = dda0214;
    }

}
