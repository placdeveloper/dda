package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.TAGRECEBIMENTOARQ;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA001.GrupoADDA001RETPagdrActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA003.GrupoADDA003PagdrDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA005.GrupoADDA005RETPagdrActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA006.GrupoADDA006RETPagdrActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA102.GrupoADDA102RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA104.GrupoADDA104RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA106.GrupoADDA106TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA108.GrupoADDA108RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA108.GrupoADDA108RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA110.GrupoADDA110RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA114.GrupoADDA114RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA114.GrupoADDA114RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA115.GrupoADDA115RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA117.GrupoADDA117TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA118.GrupoADDA118RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA118.GrupoADDA118RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA120.GrupoADDA120TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA121.GrupoADDA121RR3TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122.GrupoADDA122RETTitActoComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122.GrupoADDA122RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA127.GrupoADDA127TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA200.GrupoADDA200DmstrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA504.GrupoADDA504BenfcrioComplexType;

@XmlRootElement(name = TAGRECEBIMENTOARQ)
@XmlAccessorType(XmlAccessType.FIELD)
public class ArqRecebimento {

    @XmlElement(name = "nomeArquivoCip")
    private String nomeArquivoCip;

    @XmlElement(name = "Grupo_ADDA001RET_PagdrActo")
    private GrupoADDA001RETPagdrActoComplexType grupoADDA001RETPagdrActo;

    @XmlElement(name = "Grupo_ADDA002_Pagdr")
    private GrupoADDA002PagdrComplexType grupoADDA002Pagdr;

    @XmlElement(name = "Grupo_ADDA005RET_PagdrActo")
    private GrupoADDA005RETPagdrActoComplexType grupoADDA005RETPagdrActo;

    @XmlElement(name = "Grupo_ADDA006RET_PagdrActo")
    private GrupoADDA006RETPagdrActoComplexType grupoADDA006RETPagdrActo;

    @XmlElement(name = "Grupo_ADDA003_PagdrDDA")
    private GrupoADDA003PagdrDDAComplexType grupoADDA003PagdrDDA;

    @XmlElement(name = "Grupo_ADDA101RET_TitActo")
    private GrupoADDA101RETTitActoComplexType grupoADDA101RETTitActo;

    @XmlElement(name = "Grupo_ADDA102RET_TitActo")
    private GrupoADDA102RETTitActoComplexType grupoADDA102RETTitActo;

    @XmlElement(name = "Grupo_ADDA104RR2_Tit")
    private GrupoADDA104RR2TitComplexType GrupoADDA104RR2Tit;

    @XmlElement(name = "Grupo_ADDA110RET_TitActo")
    private GrupoADDA110RETTitActoComplexType grupoADDA110RETTitActo;

    @XmlElement(name = "Grupo_ADDA101RR2_Tit")
    private GrupoADDA101RR2TitComplexType grupoADDA101RR2Tit;

    @XmlElement(name = "Grupo_ADDA102RR2_Tit")
    private GrupoADDA102RR2TitComplexType grupoADDA102RR2Tit;

    @XmlElement(name = "Grupo_ADDA108RET_TitActo")
    private GrupoADDA108RETTitActoComplexType grupoADDA108RETTitActo;

    @XmlElement(name = "Grupo_ADDA106_Tit")
    private GrupoADDA106TitComplexType grupoADDA106Tit;

    @XmlElement(name = "Grupo_ADDA108RR2_Tit")
    private GrupoADDA108RR2TitComplexType grupoADDA108RR2Tit;

    @XmlElement(name = "Grupo_ADDA114RET_TitActo")
    private GrupoADDA114RETTitActoComplexType grupoADDA114RETTitActo;

    @XmlElement(name = "Grupo_ADDA114RR2_Tit")
    private GrupoADDA114RR2TitComplexType grupoADDA114RR2Tit;

    @XmlElement(name = "Grupo_ADDA118RET_TitActo")
    private GrupoADDA118RETTitActoComplexType grupoADDA118RETTitActo;

    @XmlElement(name = "Grupo_ADDA118RR2_Tit")
    private GrupoADDA118RR2TitComplexType grupoADDA118RR2Tit;

    @XmlElement(name = "Grupo_ADDA120_Tit")
    private GrupoADDA120TitComplexType grupoADDA120Tit;

    @XmlElement(name = "Grupo_ADDA504_Benfcrio")
    private GrupoADDA504BenfcrioComplexType grupoADDA504Benfcrio;

    @XmlElement(name = "Grupo_ADDA115RR2_Tit")
    private GrupoADDA115RR2TitComplexType grupoADDA115RR2Tit;

    @XmlElement(name = "Grupo_ADDA121RET_TitActo")
    private GrupoADDA121RETTitActoComplexType grupoADDA121RETTitActo;

    @XmlElement(name = "Grupo_ADDA121RR2_Tit")
    private GrupoADDA121RR2TitComplexType grupoADDA121RR2Tit;

    @XmlElement(name = "Grupo_ADDA121RR3_Tit")
    private GrupoADDA121RR3TitComplexType grupoADDA121RR3Tit;

    @XmlElement(name = "Grupo_ADDA122RET_TitActo")
    private GrupoADDA122RETTitActoComplexType grupoADDA122RETTitActo;

    @XmlElement(name = "Grupo_ADDA122RR2_Tit")
    private GrupoADDA122RR2TitComplexType grupoADDA122RR2Tit;

    @XmlElement(name = "Grupo_ADDA117_Tit")
    private GrupoADDA117TitComplexType grupoADDA117Tit;

    @XmlElement(name = "Grupo_ADDA127_Tit")
    private GrupoADDA127TitComplexType GrupoADDA127Tit;

    @XmlElement(name = "Grupo_ADDA200_Dmstr")
    private GrupoADDA200DmstrComplexType grupoADDA200Dmstr;

    /**
     * @return the nomeArquivoCip
     */
    public String getNomeArquivoCip() {
        return nomeArquivoCip;
    }

    /**
     * @param nomeArquivoCip the nomeArquivoCip to set
     */
    public void setNomeArquivoCip(String nomeArquivoCip) {
        this.nomeArquivoCip = nomeArquivoCip;
    }

    /**
     * @return the grupoADDA002Pagdr
     */
    public GrupoADDA002PagdrComplexType getGrupoADDA002Pagdr() {
        return grupoADDA002Pagdr;
    }

    /**
     * @param grupoADDA002Pagdr the grupoADDA002Pagdr to set
     */
    public void setGrupoADDA002Pagdr(GrupoADDA002PagdrComplexType grupoADDA002Pagdr) {
        this.grupoADDA002Pagdr = grupoADDA002Pagdr;
    }

    /**
     * @return the grupoADDA003Pagdr
     */
    public GrupoADDA003PagdrDDAComplexType getGrupoADDA003PagdrDDA() {
        return grupoADDA003PagdrDDA;
    }

    /**
     * @param grupoADDA003Pagdr the grupoADDA003Pagdr to set
     */
    public void setGrupoADDA003PagdrDDA(GrupoADDA003PagdrDDAComplexType grupoADDA003Pagdr) {
        this.grupoADDA003PagdrDDA = grupoADDA003Pagdr;
    }

    /**
     * @return o atributo grupoADDA101RETTitActo
     */
    public GrupoADDA101RETTitActoComplexType getGrupoADDA101RETTitActo() {
        return grupoADDA101RETTitActo;
    }

    /**
     * Define o atributo grupoADDA101RETTitActo
     */
    public void setGrupoADDA101RETTitActo(GrupoADDA101RETTitActoComplexType grupoADDA101RETTitActo) {
        this.grupoADDA101RETTitActo = grupoADDA101RETTitActo;
    }

    /**
     * @return o atributo grupoADDA102RETTitActo
     */
    public GrupoADDA102RETTitActoComplexType getGrupoADDA102RETTitActo() {
        return grupoADDA102RETTitActo;
    }

    /**
     * Define o atributo grupoADDA102RETTitActo
     */
    public void setGrupoADDA102RETTitActo(GrupoADDA102RETTitActoComplexType grupoADDA102RETTitActo) {
        this.grupoADDA102RETTitActo = grupoADDA102RETTitActo;
    }

    /**
     * @return o atributo grupoADDA104RR2Tit
     */
    public GrupoADDA104RR2TitComplexType getGrupoADDA104RR2Tit() {
        return GrupoADDA104RR2Tit;
    }

    /**
     * Define o atributo grupoADDA104RR2Tit
     */
    public void setGrupoADDA104RR2Tit(GrupoADDA104RR2TitComplexType grupoADDA104RR2Tit) {
        GrupoADDA104RR2Tit = grupoADDA104RR2Tit;
    }

    /**
     * @return the grupoADDA001RETPagdrActo
     */
    public GrupoADDA001RETPagdrActoComplexType getGrupoADDA001RETPagdrActo() {
        return grupoADDA001RETPagdrActo;
    }

    /**
     * @param grupoADDA001RETPagdrActo the grupoADDA001RETPagdrActo to set
     */
    public void setGrupoADDA001RETPagdrActo(GrupoADDA001RETPagdrActoComplexType grupoADDA001RETPagdrActo) {
        this.grupoADDA001RETPagdrActo = grupoADDA001RETPagdrActo;
    }

    /**
     * @return the grupoADDA005RETPagdrActo
     */
    public GrupoADDA005RETPagdrActoComplexType getGrupoADDA005RETPagdrActo() {
        return grupoADDA005RETPagdrActo;
    }

    /**
     * @param grupoADDA005RETPagdrActo the grupoADDA005RETPagdrActo to set
     */
    public void setGrupoADDA005RETPagdrActo(GrupoADDA005RETPagdrActoComplexType grupoADDA005RETPagdrActo) {
        this.grupoADDA005RETPagdrActo = grupoADDA005RETPagdrActo;
    }

    /**
     * @return o atributo grupoADDA006RETPagdrActo
     */
    public GrupoADDA006RETPagdrActoComplexType getGrupoADDA006RETPagdrActo() {
        return grupoADDA006RETPagdrActo;
    }

    /**
     * Define o atributo grupoADDA006RETPagdrActo
     */
    public void setGrupoADDA006RETPagdrActo(GrupoADDA006RETPagdrActoComplexType grupoADDA006RETPagdrActo) {
        this.grupoADDA006RETPagdrActo = grupoADDA006RETPagdrActo;
    }

    /**
     * @return o atributo grupoADDA101R2TitActo
     */
    public GrupoADDA101RR2TitComplexType getGrupoADDA101RR2Tit() {
        return grupoADDA101RR2Tit;
    }

    /**
     * Define o atributo grupoADDA101R2TitActo
     */
    public void setGrupoADDA101RR2Tit(GrupoADDA101RR2TitComplexType grupoADDA101R2Tit) {
        this.grupoADDA101RR2Tit = grupoADDA101R2Tit;
    }

    /**
     * @return o atributo grupoADDA102R2TitActo
     */
    public GrupoADDA102RR2TitComplexType getGrupoADDA102RR2Tit() {
        return grupoADDA102RR2Tit;
    }

    /**
     * Define o atributo grupoADDA102R2TitActo
     */
    public void setGrupoADDA102RR2Tit(GrupoADDA102RR2TitComplexType grupoADDA102R2TitActo) {
        this.grupoADDA102RR2Tit = grupoADDA102R2TitActo;
    }

    /**
     * @return the grupoADDA106Tit
     */
    public GrupoADDA106TitComplexType getGrupoADDA106Tit() {
        return grupoADDA106Tit;
    }

    /**
     * @param grupoADDA106Tit the grupoADDA106Tit to set
     */
    public void setGrupoADDA106Tit(GrupoADDA106TitComplexType grupoADDA106Tit) {
        this.grupoADDA106Tit = grupoADDA106Tit;
    }

    /**
     * @return o atributo grupoADDA108RETTitActo
     */
    public GrupoADDA108RETTitActoComplexType getGrupoADDA108RETTitActo() {
        return grupoADDA108RETTitActo;
    }

    /**
     * Define o atributo grupoADDA108RETTitActo
     */
    public void setGrupoADDA108RETTitActo(GrupoADDA108RETTitActoComplexType grupoADDA108RETTitActo) {
        this.grupoADDA108RETTitActo = grupoADDA108RETTitActo;
    }

    /**
     * @return o atributo grupoADDA108R2TitActo
     */
    public GrupoADDA108RR2TitComplexType getGrupoADDA108RR2Tit() {
        return grupoADDA108RR2Tit;
    }

    /**
     * Define o atributo grupoADDA108R2TitActo
     */
    public void setGrupoADDA108RR2Tit(GrupoADDA108RR2TitComplexType grupoADDA108R2TitActo) {
        this.grupoADDA108RR2Tit = grupoADDA108R2TitActo;
    }

    /**
     * @return o atributo grupoADDA114RETTitActo
     */
    public GrupoADDA114RETTitActoComplexType getGrupoADDA114RETTitActo() {
        return grupoADDA114RETTitActo;
    }

    /**
     * Define o atributo grupoADDA114RETTitActo
     */
    public void setGrupoADDA114RETTitActo(GrupoADDA114RETTitActoComplexType grupoADDA114RETTitActo) {
        this.grupoADDA114RETTitActo = grupoADDA114RETTitActo;
    }

    /**
     * @return o atributo grupoADDA114R2TitActo
     */
    public GrupoADDA114RR2TitComplexType getGrupoADDA114RR2Tit() {
        return grupoADDA114RR2Tit;
    }

    /**
     * Define o atributo grupoADDA114R2TitActo
     */
    public void setGrupoADDA114RR2Tit(GrupoADDA114RR2TitComplexType grupoADDA114R2TitActo) {
        this.grupoADDA114RR2Tit = grupoADDA114R2TitActo;
    }

    /**
     * @return o atributo grupoADDA118RETTitActo
     */
    public GrupoADDA118RETTitActoComplexType getGrupoADDA118RETTitActo() {
        return grupoADDA118RETTitActo;
    }

    /**
     * Define o atributo grupoADDA118RETTitActo
     */
    public void setGrupoADDA118RETTitActo(GrupoADDA118RETTitActoComplexType grupoADDA118RETTitActo) {
        this.grupoADDA118RETTitActo = grupoADDA118RETTitActo;
    }

    /**
     * @return o atributo grupoADDA118R2TitActo
     */
    public GrupoADDA118RR2TitComplexType getGrupoADDA118RR2Tit() {
        return grupoADDA118RR2Tit;
    }

    /**
     * Define o atributo grupoADDA118R2TitActo
     */
    public void setGrupoADDA118RR2Tit(GrupoADDA118RR2TitComplexType grupoADDA118R2TitActo) {
        this.grupoADDA118RR2Tit = grupoADDA118R2TitActo;
    }

    /**
     * @return o atributo grupoADDA120RETTitActo
     */
    public GrupoADDA120TitComplexType getGrupoADDA120Tit() {
        return grupoADDA120Tit;
    }

    /**
     * Define o atributo grupoADDA120RETTitActo
     */
    public void setGrupoADDA120Tit(GrupoADDA120TitComplexType grupoADDA120RETTitActo) {
        this.grupoADDA120Tit = grupoADDA120RETTitActo;
    }

    /**
     * @return the grupoADDA504Benfcrioo
     */
    public GrupoADDA504BenfcrioComplexType getGrupoADDA504Benfcrio() {
        return grupoADDA504Benfcrio;
    }

    /**
     * @param grupoADDA504Benfcrioo the grupoADDA504Benfcrioo to set
     */
    public void setGrupoADDA504Benfcrio(GrupoADDA504BenfcrioComplexType grupoADDA504Benfcrioo) {
        this.grupoADDA504Benfcrio = grupoADDA504Benfcrioo;
    }

    /**
     * @return the grupoADDA115RR2Tit
     */
    public GrupoADDA115RR2TitComplexType getGrupoADDA115RR2Tit() {
        return grupoADDA115RR2Tit;
    }

    /**
     * @param grupoADDA115RR2Tit the grupoADDA115RR2Tit to set
     */
    public void setGrupoADDA115RR2Tit(GrupoADDA115RR2TitComplexType grupoADDA115RR2Tit) {
        this.grupoADDA115RR2Tit = grupoADDA115RR2Tit;
    }

    /**
     * @return o atributo grupoADDA121RR3Tit
     */
    public GrupoADDA121RR3TitComplexType getGrupoADDA121RR3Tit() {
        return grupoADDA121RR3Tit;
    }

    /**
     * Define o atributo grupoADDA121RR3Tit
     */
    public void setGrupoADDA121RR3Tit(GrupoADDA121RR3TitComplexType grupoADDA121RR3Tit) {
        this.grupoADDA121RR3Tit = grupoADDA121RR3Tit;
    }

    /**
     * @return o atributo grupoADDA122RETTitActo
     */
    public GrupoADDA122RETTitActoComplexType getGrupoADDA122RETTitActo() {
        return grupoADDA122RETTitActo;
    }

    /**
     * Define o atributo grupoADDA122RETTitActo
     */
    public void setGrupoADDA122RETTitActo(GrupoADDA122RETTitActoComplexType grupoADDA122RETTitActo) {
        this.grupoADDA122RETTitActo = grupoADDA122RETTitActo;
    }

    /**
     * @return o atributo grupoADDA122RR2Tit
     */
    public GrupoADDA122RR2TitComplexType getGrupoADDA122RR2Tit() {
        return grupoADDA122RR2Tit;
    }

    /**
     * Define o atributo grupoADDA122RR2Tit
     */
    public void setGrupoADDA122RR2Tit(GrupoADDA122RR2TitComplexType grupoADDA122RR2Tit) {
        this.grupoADDA122RR2Tit = grupoADDA122RR2Tit;
    }

    /**
     * @return o atributo grupoADDA121RR2Tit
     */
    public GrupoADDA121RR2TitComplexType getGrupoADDA121RR2Tit() {
        return grupoADDA121RR2Tit;
    }

    /**
     * Define o atributo grupoADDA121RR2Tit
     */
    public void setGrupoADDA121RR2Tit(GrupoADDA121RR2TitComplexType grupoADDA121RR2Tit) {
        this.grupoADDA121RR2Tit = grupoADDA121RR2Tit;
    }

    /**
     * @return o atributo grupoADDA117Tit
     */
    public GrupoADDA117TitComplexType getGrupoADDA117Tit() {
        return grupoADDA117Tit;
    }

    /**
     * Define o atributo grupoADDA117Tit
     */
    public void setGrupoADDA117Tit(GrupoADDA117TitComplexType grupoADDA117Tit) {
        this.grupoADDA117Tit = grupoADDA117Tit;
    }

    /**
     * @return o atributo grupoADDA121RETTitActo
     */
    public GrupoADDA121RETTitActoComplexType getGrupoADDA121RETTitActo() {
        return grupoADDA121RETTitActo;
    }

    /**
     * Define o atributo grupoADDA121RETTitActo
     */
    public void setGrupoADDA121RETTitActo(GrupoADDA121RETTitActoComplexType grupoADDA121RETTitActo) {
        this.grupoADDA121RETTitActo = grupoADDA121RETTitActo;
    }

    /**
     * @return the grupoADDA110RETTitActo
     */
    public GrupoADDA110RETTitActoComplexType getGrupoADDA110RETTitActo() {
        return grupoADDA110RETTitActo;
    }

    /**
     * @param grupoADDA110RETTitActo the grupoADDA110RETTitActo to set
     */
    public void setGrupoADDA110RETTitActo(GrupoADDA110RETTitActoComplexType grupoADDA110RETTitActo) {
        this.grupoADDA110RETTitActo = grupoADDA110RETTitActo;
    }

    /**
     * @return the grupoADDA127Tit
     */
    public GrupoADDA127TitComplexType getGrupoADDA127Tit() {
        return GrupoADDA127Tit;
    }

    /**
     * @param grupoADDA127Tit the grupoADDA127Tit to set
     */
    public void setGrupoADDA127Tit(GrupoADDA127TitComplexType grupoADDA127Tit) {
        GrupoADDA127Tit = grupoADDA127Tit;
    }

    /**
     * @return the grupoADDA200Dmstr
     */
    public GrupoADDA200DmstrComplexType getGrupoADDA200Dmstr() {
        return grupoADDA200Dmstr;
    }

    /**
     * @param grupoADDA200Dmstr the grupoADDA200Dmstr to set
     */
    public void setGrupoADDA200Dmstr(GrupoADDA200DmstrComplexType grupoADDA200Dmstr) {
        this.grupoADDA200Dmstr = grupoADDA200Dmstr;
    }

}
