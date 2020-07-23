/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         ViewContingencia.java
 * Data Criação:    08/08/2017
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;


/**
 * ViewContingencia
 * 
 * @author samuell.ramos
 */
@Entity
@Table(name="VIW_CONTINGENCIA", schema = "DDA")
public class ViewContingencia extends SicoobDDAEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5141523710173201851L;

	@Id
	@Column(name = "idMensagemDDA")
	private Long id;

	private String codTipoMensagemDDA;

	private DateTimeDB dataHoraCadastro;

	private DateTimeDB dataMovimento;

	private String descErroProtocolo;


	public ViewContingencia() {
	}

	/**
	 * @return o atributo codTipoMensagemDDA
	 */
	public String getCodTipoMensagemDDA() {
		return codTipoMensagemDDA;
	}

	/**
	 * Define o atributo codTipoMensagemDDA
	 */
	public void setCodTipoMensagemDDA(String codTipoMensagemDDA) {
		this.codTipoMensagemDDA = codTipoMensagemDDA;
	}

	/**
	 * @return o atributo dataHoraCadastro
	 */
	public DateTimeDB getDataHoraCadastro() {
		return dataHoraCadastro;
	}

	/**
	 * Define o atributo dataHoraCadastro
	 */
	public void setDataHoraCadastro(DateTimeDB dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	/**
	 * @return o atributo dataMovimento
	 */
	public DateTimeDB getDataMovimento() {
		return dataMovimento;
	}

	/**
	 * Define o atributo dataMovimento
	 */
	public void setDataMovimento(DateTimeDB dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	/**
	 * @return o atributo descErroProtocolo
	 */
	public String getDescErroProtocolo() {
		return descErroProtocolo;
	}

	/**
	 * Define o atributo descErroProtocolo
	 */
	public void setDescErroProtocolo(String descErroProtocolo) {
		this.descErroProtocolo = descErroProtocolo;
	}

	/**
	 * @return o atributo id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Define o atributo id
	 */
	public void setId(Long id) {
		this.id = id;
	}

}