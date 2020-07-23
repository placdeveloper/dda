/**
 * Projeto:         S
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
 * Arquivo:         TipoMensagemDto.java
 * Data Criação:    Set 23, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.CategoriaMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author samuell.ramos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoMensagemDTO")
public class TipoMensagemDto extends BancoobDto {

    private static final long serialVersionUID = 1L;

    private String codTipoMensagem;
    private String descTipoMensagem;
    private Integer numPrioridadeEnvio;
    private Boolean bolExigeMensagemRetorno;
    private Short exigeMensagemRetorno;

    private List<TipoGradeHoraria> listaTipoGradeHoraria;
    private List<CategoriaMensagemDDA> listaCategoriaMensagem;
    private List<TipoMensagemDDA> listaArquivoCorrespondente;

    private String codCategoriaMensagemDda;
    private String codTipoGradeHoraria;

    private String codTipoArquivoCorrespondente;

    /**
     * @return o atributo codTipoMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * Define o atributo codTipoMensagem
     */
    public void setCodTipoMensagem(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }

    /**
     * @return o atributo descTipoMensagem
     */
    public String getDescTipoMensagem() {
        return descTipoMensagem;
    }

    /**
     * Define o atributo descTipoMensagem
     */
    public void setDescTipoMensagem(String descTipoMensagem) {
        this.descTipoMensagem = descTipoMensagem;
    }

    /**
     * @return o atributo numPrioridadeEnvio
     */
    public Integer getNumPrioridadeEnvio() {
        return numPrioridadeEnvio;
    }

    /**
     * Define o atributo numPrioridadeEnvio
     */
    public void setNumPrioridadeEnvio(Integer numPrioridadeEnvio) {
        this.numPrioridadeEnvio = numPrioridadeEnvio;
    }

    /**
     * @return o atributo bolExigeMensagemRetorno
     */
    public Boolean getBolExigeMensagemRetorno() {
        return bolExigeMensagemRetorno;
    }

    /**
     * Define o atributo bolExigeMensagemRetorno
     */
    public void setBolExigeMensagemRetorno(Boolean bolExigeMensagemRetorno) {
        this.bolExigeMensagemRetorno = bolExigeMensagemRetorno;
    }

    /**
     * @return o atributo listaTipoGradeHoraria
     */
    public List<TipoGradeHoraria> getListaTipoGradeHoraria() {
        return listaTipoGradeHoraria;
    }

    /**
     * Define o atributo listaTipoGradeHoraria
     */
    public void setListaTipoGradeHoraria(List<TipoGradeHoraria> listaTipoGradeHoraria) {
        this.listaTipoGradeHoraria = listaTipoGradeHoraria;
    }

    /**
     * @return o atributo listaCategoriaMensagem
     */
    public List<CategoriaMensagemDDA> getListaCategoriaMensagem() {
        return listaCategoriaMensagem;
    }

    /**
     * Define o atributo listaCategoriaMensagem
     */
    public void setListaCategoriaMensagem(List<CategoriaMensagemDDA> listaCategoriaMensagem) {
        this.listaCategoriaMensagem = listaCategoriaMensagem;
    }

    /**
     * @return o atributo listaArquivoCorrespondente
     */
    public List<TipoMensagemDDA> getListaArquivoCorrespondente() {
        return listaArquivoCorrespondente;
    }

    /**
     * Define o atributo listaArquivoCorrespondente
     */
    public void setListaArquivoCorrespondente(List<TipoMensagemDDA> listaArquivoCorrespondente) {
        this.listaArquivoCorrespondente = listaArquivoCorrespondente;
    }

    /**
     * @return o atributo serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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
     * @return o atributo codCategoriaMensagemDda
     */
    public String getCodCategoriaMensagemDda() {
        return codCategoriaMensagemDda;
    }

    /**
     * Define o atributo codCategoriaMensagemDda
     */
    public void setCodCategoriaMensagemDda(String codCategoriaMensagemDda) {
        this.codCategoriaMensagemDda = codCategoriaMensagemDda;
    }

    /**
     * @return o atributo exigeMensagemRetorno
     */
    public Short getExigeMensagemRetorno() {
        return exigeMensagemRetorno;
    }

    /**
     * Define o atributo exigeMensagemRetorno
     */
    public void setExigeMensagemRetorno(Short exigeMensagemRetorno) {
        this.exigeMensagemRetorno = exigeMensagemRetorno;
    }

	/**
	 * @return the codTipoArquivoCorrespondente
	 */
	public String getCodTipoArquivoCorrespondente() {
		return codTipoArquivoCorrespondente;
	}

	/**
	 * @param codTipoArquivoCorrespondente the codTipoArquivoCorrespondente to set
	 */
	public void setCodTipoArquivoCorrespondente(String codTipoArquivoCorrespondente) {
		this.codTipoArquivoCorrespondente = codTipoArquivoCorrespondente;
	}


}
