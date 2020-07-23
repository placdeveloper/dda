package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author Samuell.Ramos
 *
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.HistoricoPagadorEletronicoDTO")
public class HistoricoPagadorEletronicoDto extends BancoobDto {

	/**
	 * Serializador
	 */
	private static final long serialVersionUID = 1383764061977072671L;
	
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
	 * Contrutor super classe
	 */
	public HistoricoPagadorEletronicoDto() {
		super();
	}

	/**
	 * @param codTipoTermoPagador
	 * @param descTipoTermoPagador
	 * @param dataHoraTermoDDA
	 * @param idCanal
	 * @param numCpfCnpjPagador
	 * @param nomePagador
	 * @param numCpfCnpjAgregado
	 * @param nomeAgregado
	 * @param listaHistPagadorEletronicoDto
	 */
	public HistoricoPagadorEletronicoDto(Short codTipoTermoPagador,
			String descTipoTermoPagador, Date dataHoraTermoDDA,
			Short idCanal, String numCpfCnpjPagador, String nomePagador,
			String numCpfCnpjAgregado, String nomeAgregado) {
		this.codTipoTermoPagador = codTipoTermoPagador;
		this.descTipoTermoPagador = descTipoTermoPagador;
		this.dataHoraTermoDDA = new DateTimeDB(dataHoraTermoDDA.getTime());
		this.idCanal = idCanal;
		this.numCpfCnpjPagador = numCpfCnpjPagador;
		this.nomePagador = nomePagador;
		this.numCpfCnpjAgregado = numCpfCnpjAgregado;
		this.nomeAgregado = nomeAgregado;
	}


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
