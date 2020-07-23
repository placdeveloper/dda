/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         HistoricoPagadorEletronicoDTO.java
 * Data Criação:    Mar 03, 2017
 */

package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * HistoricoPagadorEletronicoDTO
 * 
 * @author samuell.ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto")
public class HistoricoPagadorEletronicoDTO extends BancoobDTO {

	private Short codTipoTermoPagador;
	private String descTipoTermoPagador;
	private DateTimeDB dataHoraTermoDDA;
	private Short idCanal;
	private String numCpfCnpjPagador;
	private String nomePagador;
	private String numCpfCnpjAgregado;
	private String nomeAgregado;
	private Boolean selecionado;
	/**
	 * @return the codTipoTermoPagador
	 */
	public Short getCodTipoTermoPagador() {
		return codTipoTermoPagador;
	}
	/**
	 * @param codTipoTermoPagador the codTipoTermoPagador to set
	 */
	public void setCodTipoTermoPagador(Short codTipoTermoPagador) {
		this.codTipoTermoPagador = codTipoTermoPagador;
	}
	/**
	 * @return the descTipoTermoPagador
	 */
	public String getDescTipoTermoPagador() {
		return descTipoTermoPagador;
	}
	/**
	 * @param descTipoTermoPagador the descTipoTermoPagador to set
	 */
	public void setDescTipoTermoPagador(String descTipoTermoPagador) {
		this.descTipoTermoPagador = descTipoTermoPagador;
	}
	/**
	 * @return the dataHoraTermoDDA
	 */
	public DateTimeDB getDataHoraTermoDDA() {
		return dataHoraTermoDDA;
	}
	/**
	 * @param dataHoraTermoDDA the dataHoraTermoDDA to set
	 */
	public void setDataHoraTermoDDA(DateTimeDB dataHoraTermoDDA) {
		this.dataHoraTermoDDA = dataHoraTermoDDA;
	}
	/**
	 * @return the idCanal
	 */
	public Short getIdCanal() {
		return idCanal;
	}
	/**
	 * @param idCanal the idCanal to set
	 */
	public void setIdCanal(Short idCanal) {
		this.idCanal = idCanal;
	}
	/**
	 * @return the numCpfCnpjPagador
	 */
	public String getNumCpfCnpjPagador() {
		return numCpfCnpjPagador;
	}
	/**
	 * @param numCpfCnpjPagador the numCpfCnpjPagador to set
	 */
	public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
		this.numCpfCnpjPagador = numCpfCnpjPagador;
	}
	/**
	 * @return the nomePagador
	 */
	public String getNomePagador() {
		return nomePagador;
	}
	/**
	 * @param nomePagador the nomePagador to set
	 */
	public void setNomePagador(String nomePagador) {
		this.nomePagador = nomePagador;
	}
	/**
	 * @return the numCpfCnpjAgregado
	 */
	public String getNumCpfCnpjAgregado() {
		return numCpfCnpjAgregado;
	}
	/**
	 * @param numCpfCnpjAgregado the numCpfCnpjAgregado to set
	 */
	public void setNumCpfCnpjAgregado(String numCpfCnpjAgregado) {
		this.numCpfCnpjAgregado = numCpfCnpjAgregado;
	}
	/**
	 * @return the nomeAgregado
	 */
	public String getNomeAgregado() {
		return nomeAgregado;
	}
	/**
	 * @param nomeAgregado the nomeAgregado to set
	 */
	public void setNomeAgregado(String nomeAgregado) {
		this.nomeAgregado = nomeAgregado;
	}
	/**
	 * @return the selecionado
	 */
	public Boolean getSelecionado() {
		return selecionado;
	}
	/**
	 * @param selecionado the selecionado to set
	 */
	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}
}
