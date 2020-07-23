/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         ContingenciaDto.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ContingenciaDto
 * 
 * @author Danilo.Barros
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ContingenciaDTO")
public class ContingenciaDto extends BancoobDto {

    private static final long serialVersionUID = -4480027132300608757L;

    private Date dataHoraSituacao;
    private Long tipoContingencia;
    private String idUsuario;
    private Long idHabilitacaoContingencia;
    private Boolean contingenciaHabilitada;

    /**
     * @param dataHoraSituacao
     * @param tipoContingenciaDto
     * @param idUsuario
     * @param idHabilitacaoContingencia
     * @param contingenciaHabilitada
     */
    public ContingenciaDto(Date dataHoraSituacao, Long tipoContingencia, String idUsuario, Long idHabilitacaoContingencia, Boolean contingenciaHabilitada) {
        super();
        this.dataHoraSituacao = dataHoraSituacao;
        this.tipoContingencia = tipoContingencia;
        this.idUsuario = idUsuario;
        this.idHabilitacaoContingencia = idHabilitacaoContingencia;
        this.contingenciaHabilitada = contingenciaHabilitada;
    }

    /**
     * @return o atributo dataHoraSituacao
     */
    public Date getDataHoraSituacao() {
        return dataHoraSituacao;
    }

    /**
     * Define o atributo dataHoraSituacao
     */
    public void setDataHoraSituacao(Date dataHoraSituacao) {
        this.dataHoraSituacao = dataHoraSituacao;
    }

    /**
     * @return o atributo tipoContingencia
     */
    public Long getTipoContingencia() {
        return tipoContingencia;
    }

    /**
     * Define o atributo tipoContingencia
     */
    public void setTipoContingenciaDto(Long tipoContingencia) {
        this.tipoContingencia = tipoContingencia;
    }

    /**
     * @return o atributo idUsuario
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * Define o atributo idUsuario
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return o atributo idHabilitacaoContingencia
     */
    public Long getIdHabilitacaoContingencia() {
        return idHabilitacaoContingencia;
    }

    /**
     * Define o atributo idHabilitacaoContingencia
     */
    public void setIdHabilitacaoContingencia(Long idHabilitacaoContingencia) {
        this.idHabilitacaoContingencia = idHabilitacaoContingencia;
    }

    /**
     * @return o atributo contingenciaHabilitada
     */
    public Boolean getContingenciaHabilitada() {
        return contingenciaHabilitada;
    }

    /**
     * Define o atributo contingenciaHabilitada
     */
    public void setContingenciaHabilitada(Boolean contingenciaHabilitada) {
        this.contingenciaHabilitada = contingenciaHabilitada;
    }

}
