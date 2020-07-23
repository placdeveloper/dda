package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Grupo_GEN0014_Tit")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_GEN0014_Tit", propOrder = { "ispbPartAdmtd", "tpSolctcTit", "tpTitConsd" })
public class GrupoGen14Boleto {

    @XmlElement(name = "ISPBPartAdmtd", required = true)
    private String ispbPartAdmtd;

    @XmlElement(name = "TpSolctcTit", required = true)
    private String tpSolctcTit;

    @XmlElement(name = "TpTitConsd", required = true)
    private String tpTitConsd;

    /**
     * @return the ispbPartAdmtd
     */
    public String getIspbPartAdmtd() {
        return ispbPartAdmtd;
    }

    /**
     * @param ispbPartAdmtd the ispbPartAdmtd to set
     */
    public void setIspbPartAdmtd(String ispbPartAdmtd) {
        this.ispbPartAdmtd = ispbPartAdmtd;
    }

    /**
     * @return the tpSolctcTit
     */
    public String getTpSolctcTit() {
        return tpSolctcTit;
    }

    /**
     * @param tpSolctcTit the tpSolctcTit to set
     */
    public void setTpSolctcTit(String tpSolctcTit) {
        this.tpSolctcTit = tpSolctcTit;
    }

    /**
     * @return the tpTitConsd
     */
    public String getTpTitConsd() {
        return tpTitConsd;
    }

    /**
     * @param tpTitConsd the tpTitConsd to set
     */
    public void setTpTitConsd(String tpTitConsd) {
        this.tpTitConsd = tpTitConsd;
    }
}
