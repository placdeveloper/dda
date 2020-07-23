package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Grupo_GEN0014_Benfcrio")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_GEN0014_Benfcrio", propOrder = { "ispbPartDestinatarioAdmtd", "tpSolctcBenfcrio", "sitBenfcrio" })
public class GrupoGen14Beneficiario {

    @XmlElement(name = "ISPBPartDestinatarioAdmtd", required = true)
    private String ispbPartDestinatarioAdmtd;

    @XmlElement(name = "TpSolctcBenfcrio", required = true)
    private String tpSolctcBenfcrio;

    @XmlElement(name = "SitBenfcrio", required = false)
    private String sitBenfcrio;

    /**
     * @return the ispbPartDestinatarioAdmtd
     */
    public String getIspbPartDestinatarioAdmtd() {
        return ispbPartDestinatarioAdmtd;
    }

    /**
     * @param ispbPartDestinatarioAdmtd the ispbPartDestinatarioAdmtd to set
     */
    public void setIspbPartDestinatarioAdmtd(String ispbPartDestinatarioAdmtd) {
        this.ispbPartDestinatarioAdmtd = ispbPartDestinatarioAdmtd;
    }

    /**
     * @return the tpSolctcBenfcrio
     */
    public String getTpSolctcBenfcrio() {
        return tpSolctcBenfcrio;
    }

    /**
     * @param tpSolctcBenfcrio the tpSolctcBenfcrio to set
     */
    public void setTpSolctcBenfcrio(String tpSolctcBenfcrio) {
        this.tpSolctcBenfcrio = tpSolctcBenfcrio;
    }

    /**
     * @return the sitBenfcrio
     */
    public String getSitBenfcrio() {
        return sitBenfcrio;
    }

    /**
     * @param sitBenfcrio the sitBenfcrio to set
     */
    public void setSitBenfcrio(String sitBenfcrio) {
        this.sitBenfcrio = sitBenfcrio;
    }

}
