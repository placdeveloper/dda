/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         LogErroCargaDTO.java
 * Data Criação:    Sep 15, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * LogErroCargaDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroCargaDto")
public class LogErroCargaDTO extends BancoobDTO {

    private Long idLogErro;
    private String numCPFCNPJ;
    private Date dataInicio;
    private Date dataFim;
    private Date dataHoraCadastro;

    private String codTipoErro;
    private String descTipoErro;
    private Integer idInstituicao;

    private List<LogErroCargaDTO> listaErroCarga;
    private List<CooperativaDTO> listaCooperativa;

    /**
     * @return the idLogErro
     */
    public Long getIdLogErro() {
        return idLogErro;
    }

    /**
     * @param idLogErro the idLogErro to set
     */
    public void setIdLogErro(Long idLogErro) {
        this.idLogErro = idLogErro;
    }

    /**
     * @return the numCPFCNPJ
     */
    public String getNumCPFCNPJ() {
        return numCPFCNPJ;
    }

    /**
     * @param numCPFCNPJ the numCPFCNPJ to set
     */
    public void setNumCPFCNPJ(String numCPFCNPJ) {
        this.numCPFCNPJ = numCPFCNPJ;
    }

    /**
     * @return the dataInicio
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * @param dataInicio the dataInicio to set
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * @return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return the dataHoraCadastro
     */
    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    /**
     * @param dataHoraCadastro the dataHoraCadastro to set
     */
    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    /**
     * @return the listaErroBeneficiarioInstituicao
     */
    public List<LogErroCargaDTO> getListaErroCarga() {
        return listaErroCarga;
    }

    /**
     * @param listaErroBeneficiarioInstituicao the listaErroBeneficiarioInstituicao to set
     */
    public void setListaErroCarga(List<LogErroCargaDTO> listaErroCarga) {
        this.listaErroCarga = listaErroCarga;
    }

    /**
     * @return the listaCooperativa
     */
    public List<CooperativaDTO> getListaCooperativa() {
        return listaCooperativa;
    }

    /**
     * @param listaCooperativa the listaCooperativa to set
     */
    public void setListaCooperativa(List<CooperativaDTO> listaCooperativa) {
        this.listaCooperativa = listaCooperativa;
    }

    /**
     * @return the codTipoErro
     */
    public String getCodTipoErro() {
        return codTipoErro;
    }

    /**
     * @param codTipoErro the codTipoErro to set
     */
    public void setCodTipoErro(String codTipoErro) {
        this.codTipoErro = codTipoErro;
    }

    /**
     * @return the descTipoErro
     */
    public String getDescTipoErro() {
        return descTipoErro;
    }

    /**
     * @param descTipoErro the descTipoErro to set
     */
    public void setDescTipoErro(String descTipoErro) {
        this.descTipoErro = descTipoErro;
    }

    /**
     * @return the idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * @param idInstituicao the idInstituicao to set
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

}
