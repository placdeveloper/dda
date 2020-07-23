/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         SubTipoGradeVO.java
 * Data Criação:    Aug 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author Samuell.Ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade")
public class SubTipoGradeVO extends BancoobVO {

	private Short codSubTipoGrade;

	private String descSubTipoGrade;
	
    private List<TipoGradeHoraria> listaTipoGradeHoraria;

	public Short getCodSubTipoGrade() {
		return codSubTipoGrade;
	}

	public void setCodSubTipoGrade(Short codSubTipoGrade) {
		this.codSubTipoGrade = codSubTipoGrade;
	}

	public String getDescSubTipoGrade() {
		return descSubTipoGrade;
	}

	public void setDescSubTipoGrade(String descSubTipoGrade) {
		this.descSubTipoGrade = descSubTipoGrade;
	}

	public List<TipoGradeHoraria> getListaTipoGradeHoraria() {
		return listaTipoGradeHoraria;
	}

	public void setListaTipoGradeHoraria(
			List<TipoGradeHoraria> listaTipoGradeHoraria) {
		this.listaTipoGradeHoraria = listaTipoGradeHoraria;
	}

}
