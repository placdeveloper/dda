/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         LogErroCargaDto.java
 * Data Criação:    Sep 15, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * LogErroCargaDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.LogErroCargaDTO")
public class LogErroCargaDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 969343958959894165L;
    private Long idLogErro;
    private String numCPFCNPJ;
    private Date dataInicio;
    private Date dataFim;
    private Date dataHoraCadastro;

    private String codTipoErro;
    private String descTipoErro;
    private Integer idInstituicao;

    private List<LogErroCargaDto> listaErroCarga;
    private List<CooperativaDto> listaCooperativa;

    /**
     * 
     */
    public LogErroCargaDto() {
        super();
    }

    /**
     * @param idLogErro
     * @param numCPFCNPJ
     * @param dataHoraCadastro
     */
    public LogErroCargaDto(Long idLogErro, String numCPFCNPJ, Date dataHoraCadastro) {
        super();
        this.idLogErro = idLogErro;
        this.numCPFCNPJ = numCPFCNPJ;
        this.dataHoraCadastro = dataHoraCadastro;
    }

    /**
     * @param codTipoErro
     * @param descTipoErro
     * @param idInstituicao
     */
    public LogErroCargaDto(String codTipoErro, String descTipoErro, Integer idInstituicao) {
        super();
        this.codTipoErro = codTipoErro;
        this.descTipoErro = descTipoErro;
        this.idInstituicao = idInstituicao;
    }

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
     * @return the listaCooperativa
     */
    public List<CooperativaDto> getListaCooperativa() {
        return listaCooperativa;
    }

    /**
     * @param listaCooperativa the listaCooperativa to set
     */
    public void setListaCooperativa(List<CooperativaDto> listaCooperativa) {
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

    /**
     * @return the ListaErroCarga
     */
    public List<LogErroCargaDto> getListaErroCarga() {
        return listaErroCarga;
    }

    /**
     * @param listaErroCarga the listaErroCarga to set
     */
    public void setListaErroCarga(List<LogErroCargaDto> listaErroCarga) {
        this.listaErroCarga = listaErroCarga;
    }

}
