/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         TipoMensagemDDAVO.java
 * Data Criação:    Aug 13, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author Samuell.Ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA")
public class TipoMensagemDDAVO extends BancoobVO {

    private String codTipoMensagem;

    private String descTipoMensagem;

    private Integer numPrioridadeEnvio;

    private CategoriaMensagemDDAVO categoriaMensagemDDA;

    private Boolean bolExigeMensagemRetorno;

    private TipoGradeHorariaVO tipoGradeHoraria;

    private String codTipoArquivoCorrespondente;

    /**
     * @return the codTipoMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * @param codTipoMensagem the codTipoMensagem to set
     */
    public void setCodTipoMensagem(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }

    /**
     * @return the descTipoMensagem
     */
    public String getDescTipoMensagem() {
        return descTipoMensagem;
    }

    /**
     * @param descTipoMensagem the descTipoMensagem to set
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
     * @return o atributo tipoGradeHoraria
     */
    public TipoGradeHorariaVO getTipoGradeHoraria() {
        return tipoGradeHoraria;
    }

    /**
     * Define o atributo tipoGradeHoraria
     */
    public void setTipoGradeHoraria(TipoGradeHorariaVO tipoGradeHoraria) {
        this.tipoGradeHoraria = tipoGradeHoraria;
    }

    /**
     * @return o atributo categoriaMensagemDDA
     */
    public CategoriaMensagemDDAVO getCategoriaMensagemDDA() {
        return categoriaMensagemDDA;
    }

    /**
     * Define o atributo categoriaMensagemDDA
     */
    public void setCategoriaMensagemDDA(CategoriaMensagemDDAVO categoriaMensagemDDA) {
        this.categoriaMensagemDDA = categoriaMensagemDDA;
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
