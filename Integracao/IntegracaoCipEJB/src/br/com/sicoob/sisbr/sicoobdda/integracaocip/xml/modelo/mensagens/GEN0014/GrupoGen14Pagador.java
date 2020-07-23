package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Grupo_GEN0014_Pagdr")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Grupo_GEN0014_Pagdr", propOrder = { "ispbPartAdmtd", "tpSolctcPagdr" })
public class GrupoGen14Pagador {

    @XmlElement(name = "ISPBPartAdmtd", required = true)
    private String ispbPartAdmtd;

    @XmlElement(name = "TpSolctcPagdr", required = true)
    private String tpSolctcPagdr;

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
     * @return the tpSolctcPagdr
     */
    public String getTpSolctcPagdr() {
        return tpSolctcPagdr;
    }

    /**
     * @param tpSolctcPagdr the tpSolctcPagdr to set
     */
    public void setTpSolctcPagdr(String tpSolctcPagdr) {
        this.tpSolctcPagdr = tpSolctcPagdr;
    }

}
