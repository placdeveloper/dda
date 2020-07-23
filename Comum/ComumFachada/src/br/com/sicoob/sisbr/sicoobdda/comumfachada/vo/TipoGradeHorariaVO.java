/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         TipoGradeHorariaVO.java
 * Data Criação:    Aug 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoMensagemDDAVO é responsável por
 * 
 * @author Samuell.Ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria")
public class TipoGradeHorariaVO extends BancoobVO {

    private String codTipoGradeHoraria;

    private String descTipoGradeHoraria;

    private List<GradeHoraria> listaGradeHoraria;

    private SubTipoGradeVO subTipoGrade;

    private TipoGradeHorariaVO tipoGradeHorariaOrigem;

    /**
     * @return o atributo codTipoGradeHoraria
     */
    public String getCodTipoGradeHoraria() {
        return codTipoGradeHoraria;
    }

    /**
     * Define o atributo codTipoGradeHoraria
     */
    public void setCodTipoGradeHoraria(String codTipoGradeHoraria) {
        this.codTipoGradeHoraria = codTipoGradeHoraria;
    }

    /**
     * @return o atributo descTipoGradeHoraria
     */
    public String getDescTipoGradeHoraria() {
        return descTipoGradeHoraria;
    }

    /**
     * Define o atributo descTipoGradeHoraria
     */
    public void setDescTipoGradeHoraria(String descTipoGradeHoraria) {
        this.descTipoGradeHoraria = descTipoGradeHoraria;
    }

    /**
     * @return o atributo listaGradeHoraria
     */
    public List<GradeHoraria> getListaGradeHoraria() {
        return listaGradeHoraria;
    }

    /**
     * Define o atributo listaGradeHoraria
     */
    public void setListaGradeHoraria(List<GradeHoraria> listaGradeHoraria) {
        this.listaGradeHoraria = listaGradeHoraria;
    }

    /**
     * @return o atributo subTipoGrade
     */
    public SubTipoGradeVO getSubTipoGrade() {
        return subTipoGrade;
    }

    /**
     * Define o atributo subTipoGrade
     */
    public void setSubTipoGrade(SubTipoGradeVO subTipoGrade) {
        this.subTipoGrade = subTipoGrade;
    }

    /**
     * @return o atributo tipoGradeHorariaOrigem
     */
    public TipoGradeHorariaVO getTipoGradeHorariaOrigem() {
        return tipoGradeHorariaOrigem;
    }

    /**
     * Define o atributo tipoGradeHorariaOrigem
     */
    public void setTipoGradeHorariaOrigem(TipoGradeHorariaVO tipoGradeHorariaOrigem) {
        this.tipoGradeHorariaOrigem = tipoGradeHorariaOrigem;
    }

}
