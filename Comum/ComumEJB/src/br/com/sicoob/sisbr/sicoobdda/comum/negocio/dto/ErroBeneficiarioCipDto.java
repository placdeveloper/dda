/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ErroBenficiarioCipDto.java
 * Data Criação:    Sep 11, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ErroBeneficiarioCipDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ErroBeneficiarioCipDTO")
public class ErroBeneficiarioCipDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7978916278120143356L;

    private Long idBeneficiario;
    private String numCpfCnpj;
    private String codTipoErroCip;
    private String descTipoErroCip;
    private Date dataHoraAtualizacao;
    private Long idErroBeneficiario;
    private Long idArquivo;
    private Long qtdTotalErrosEnviar;

    /**
     * 
     */
    public ErroBeneficiarioCipDto() {
        super();
    }

    /**
     * @param idBeneficiario
     * @param numCpfCnpj
     * @param codTipoErroCip
     * @param descTipoErroCip
     * @param dataHoraAtualizacao
     * @param idErroBeneficiario
     * @param idArquivo
     */
    public ErroBeneficiarioCipDto(Long idBeneficiario, String numCpfCnpj, String codTipoErroCip, String descTipoErroCip, Date dataHoraAtualizacao, Long idErroBeneficiario,
            Long idArquivo) {
        super();
        this.idBeneficiario = idBeneficiario;
        this.numCpfCnpj = numCpfCnpj;
        this.codTipoErroCip = codTipoErroCip;
        this.descTipoErroCip = descTipoErroCip;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.idErroBeneficiario = idErroBeneficiario;
        this.idArquivo = idArquivo;
    }

    /**
     * @return the idBeneficiario
     */
    public Long getIdBeneficiario() {
        return idBeneficiario;
    }

    /**
     * @param idBeneficiario the idBeneficiario to set
     */
    public void setIdBeneficiario(Long idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
    }

    /**
     * @return the numCpfCnpj
     */
    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    /**
     * @param numCpfCnpj the numCpfCnpj to set
     */
    public void setNumCpfCnpj(String numCpfCnpj) {
        this.numCpfCnpj = numCpfCnpj;
    }

    /**
     * @return the codTipoErroCip
     */
    public String getCodTipoErroCip() {
        return codTipoErroCip;
    }

    /**
     * @param codTipoErroCip the codTipoErroCip to set
     */
    public void setCodTipoErroCip(String codTipoErroCip) {
        this.codTipoErroCip = codTipoErroCip;
    }

    /**
     * @return the descTipoErroCip
     */
    public String getDescTipoErroCip() {
        return descTipoErroCip;
    }

    /**
     * @param descTipoErroCip the descTipoErroCip to set
     */
    public void setDescTipoErroCip(String descTipoErroCip) {
        this.descTipoErroCip = descTipoErroCip;
    }

    /**
     * @return the dataHoraAtualizacao
     */
    public Date getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * @param dataHoraAtualizacao the dataHoraAtualizacao to set
     */
    public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    /**
     * @return the idErroBeneficiario
     */
    public Long getIdErroBeneficiario() {
        return idErroBeneficiario;
    }

    /**
     * @param idErroBeneficiario the idErroBeneficiario to set
     */
    public void setIdErroBeneficiario(Long idErroBeneficiario) {
        this.idErroBeneficiario = idErroBeneficiario;
    }

    /**
     * @return the idArquivo
     */
    public Long getIdArquivo() {
        return idArquivo;
    }

    /**
     * @param idArquivo the idArquivo to set
     */
    public void setIdArquivo(Long idArquivo) {
        this.idArquivo = idArquivo;
    }

    /**
     * @return the qtdTotalErrosEnviar
     */
    public Long getQtdTotalErrosEnviar() {
        return qtdTotalErrosEnviar;
    }

    /**
     * @param qtdTotalErrosEnviar the qtdTotalErrosEnviar to set
     */
    public void setQtdTotalErrosEnviar(Long qtdTotalErrosEnviar) {
        this.qtdTotalErrosEnviar = qtdTotalErrosEnviar;
    }

}
