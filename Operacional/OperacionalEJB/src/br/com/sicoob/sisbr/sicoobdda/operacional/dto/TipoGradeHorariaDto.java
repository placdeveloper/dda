/**
 * Projeto:         S
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
 * Arquivo:         TipoGradeHorariaDto.java
 * Data Criação:    Aug 23, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author samuell.ramos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoGradeHorariaDTO")
public class TipoGradeHorariaDto extends BancoobDto {

    private static final long serialVersionUID = 1L;

    private String codTipoGradeHoraria;

    private String descTipoGradeHoraria;

    private String descSubTipoGrade;

    private String codTipoGradeHorariaOrigem;

    private Short codSubTipoGrade;

    private Date dataUltimaGrade;

    private List<SubTipoGrade> listaSubTipoGrade;

    private List<TipoGradeHoraria> listaCodigoTipoGradeHoraria;

    /**
     * @param codTipoGradeHoraria
     * @param descTipoGradeHoraria
     * @param descSubTipoGrade
     * @param codTipoGradeHorigem
     */
    public TipoGradeHorariaDto(String codTipoGradeHoraria, String descTipoGradeHoraria, String descSubTipoGrade, String codTipoGradeHorariaOrigem) {
        this.codTipoGradeHoraria = codTipoGradeHoraria;
        this.descTipoGradeHoraria = descTipoGradeHoraria;
        this.descSubTipoGrade = descSubTipoGrade;
        this.codTipoGradeHorariaOrigem = codTipoGradeHorariaOrigem;
    }

    public TipoGradeHorariaDto() {
        super();
    }

    public String getCodTipoGradeHoraria() {
        return codTipoGradeHoraria;
    }

    public void setCodTipoGradeHoraria(String codTipoGradeHoraria) {
        this.codTipoGradeHoraria = codTipoGradeHoraria;
    }

    public String getDescTipoGradeHoraria() {
        return descTipoGradeHoraria;
    }

    public void setDescTipoGradeHoraria(String descTipoGradeHoraria) {
        this.descTipoGradeHoraria = descTipoGradeHoraria;
    }

    public String getDescSubTipoGrade() {
        return descSubTipoGrade;
    }

    public void setDescSubTipoGrade(String descSubTipoGrade) {
        this.descSubTipoGrade = descSubTipoGrade;
    }

    public String getCodTipoGradeHorariaOrigem() {
        return codTipoGradeHorariaOrigem;
    }

    public void setCodTipoGradeHorariaOrigem(String codTipoGradeHorariaOrigem) {
        this.codTipoGradeHorariaOrigem = codTipoGradeHorariaOrigem;
    }

    public List<SubTipoGrade> getListaSubTipoGrade() {
        return listaSubTipoGrade;
    }

    public void setListaSubTipoGrade(List<SubTipoGrade> listaSubTipoGrade) {
        this.listaSubTipoGrade = listaSubTipoGrade;
    }

    public List<TipoGradeHoraria> getListaCodigoTipoGradeHoraria() {
        return listaCodigoTipoGradeHoraria;
    }

    public void setListaCodigoTipoGradeHoraria(List<TipoGradeHoraria> listaCodigoTipoGradeHoraria) {
        this.listaCodigoTipoGradeHoraria = listaCodigoTipoGradeHoraria;
    }

    public Short getCodSubTipoGrade() {
        return codSubTipoGrade;
    }

    public void setCodSubTipoGrade(Short codSubTipoGrade) {
        this.codSubTipoGrade = codSubTipoGrade;
    }

    /**
     * @return o atributo dataUltimaGrade
     */
    public Date getDataUltimaGrade() {
        return dataUltimaGrade;
    }

    /**
     * Define o atributo dataUltimaGrade
     */
    public void setDataUltimaGrade(Date dataUltimaGrade) {
        this.dataUltimaGrade = dataUltimaGrade;
    }

}
