/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
 * Arquivo:         MensagemFilaDto.java
 * Data Criação:    Aug 2, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SubTipoGradeVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoGradeHorariaVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author samuell.ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto")
public class TipoGradeHorariaDTO extends BancoobDTO {
	
	private String codTipoGradeHoraria;
	 
	private String descTipoGradeHoraria;
	
    private String descSubTipoGrade;
    
    private String codTipoGradeHorariaOrigem;
    
    private Short codSubTipoGrade;
    
    private List<SubTipoGradeVO> listaSubTipoGrade;
    
    private List<TipoGradeHorariaVO> listaCodigoTipoGradeHoraria;  

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

	public List<SubTipoGradeVO> getListaSubTipoGrade() {
		return listaSubTipoGrade;
	}

	public void setListaSubTipoGrade(List<SubTipoGradeVO> listaSubTipoGrade) {
		this.listaSubTipoGrade = listaSubTipoGrade;
	}

	public List<TipoGradeHorariaVO> getListaCodigoTipoGradeHoraria() {
		return listaCodigoTipoGradeHoraria;
	}

	public void setListaCodigoTipoGradeHoraria(
			List<TipoGradeHorariaVO> listaCodigoTipoGradeHoraria) {
		this.listaCodigoTipoGradeHoraria = listaCodigoTipoGradeHoraria;
	}

	public Short getCodSubTipoGrade() {
		return codSubTipoGrade;
	}

	public void setCodSubTipoGrade(Short codSubTipoGrade) {
		this.codSubTipoGrade = codSubTipoGrade;
	}
}
