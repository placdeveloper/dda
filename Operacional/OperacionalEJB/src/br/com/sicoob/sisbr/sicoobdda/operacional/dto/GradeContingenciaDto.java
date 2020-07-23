/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         ContingenciaDto.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * ContingenciaDto
 * 
 * @author Danilo.Barros
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.GradeContingenciaDTO")
public class GradeContingenciaDto extends BancoobDto {
    private static final long serialVersionUID = 1L;
    private String descTipoContingencia;
    private DateTime dataHoraSituacaoInicial;
    private String idUsuarioHabilitacao;
    private DateTime dataHoraSituacaoFinal;
    private String idUsuarioDesabilitacao;
    private String situacaoContingencia;

    /**
     * @param
     */
    public GradeContingenciaDto() {
        super();
    }

    /**
     * @param descTipoContingencia
     * @param dataHoraSituacaoInicial
     * @param idUsuarioHabilitacao
     * @param dataHoraSituacaoFinal
     * @param idUsuarioDesabilitacao
     * @param idUsuarioDesabilitacao
     */
    public GradeContingenciaDto(String descTipoContingencia, DateTime dataHoraSituacaoInicial, String idUsuarioHabilitacao, DateTime dataHoraSituacaoFinal,
            String idUsuarioDesabilitacao,
            String situacaoContingencia) {
        super();
        this.descTipoContingencia = descTipoContingencia;
        this.dataHoraSituacaoInicial = dataHoraSituacaoInicial;
        this.idUsuarioHabilitacao = idUsuarioHabilitacao;
        this.dataHoraSituacaoFinal = dataHoraSituacaoFinal;
        this.idUsuarioDesabilitacao = idUsuarioDesabilitacao;
        this.setSituacaoContingencia(situacaoContingencia);
    }

    /**
     * @return o atributo descTipoContingencia
     */
    public String getDescTipoContingencia() {
        return descTipoContingencia;
    }

    /**
     * Define o atributo descTipoContingencia
     */
    public void setDescTipoContingencia(String descTipoContingencia) {
        this.descTipoContingencia = descTipoContingencia;
    }

    /**
     * @return o atributo dataHoraSituacaoInicial
     */
    public DateTime getDataHoraSituacaoInicial() {
        return dataHoraSituacaoInicial;
    }

    /**
     * Define o atributo dataHoraSituacaoInicial
     */
    public void setDataHoraSituacaoInicial(DateTime dataHoraSituacaoInicial) {
        this.dataHoraSituacaoInicial = dataHoraSituacaoInicial;
    }

    /**
     * @return o atributo idUsuarioHabilitacao
     */
    public String getIdUsuarioHabilitacao() {
        return idUsuarioHabilitacao;
    }

    /**
     * Define o atributo idUsuarioHabilitacao
     */
    public void setIdUsuarioHabilitacao(String idUsuarioHabilitacao) {
        this.idUsuarioHabilitacao = idUsuarioHabilitacao;
    }

    /**
     * @return o atributo dataHoraSituacaoFinal
     */
    public DateTime getDataHoraSituacaoFinal() {
        return dataHoraSituacaoFinal;
    }

    /**
     * Define o atributo dataHoraSituacaoFinal
     */
    public void setDataHoraSituacaoFinal(DateTime dataHoraSituacaoFinal) {
        this.dataHoraSituacaoFinal = dataHoraSituacaoFinal;
    }

    /**
     * @return o atributo idUsuarioDesabilitacao
     */
    public String getIdUsuarioDesabilitacao() {
        return idUsuarioDesabilitacao;
    }

    /**
     * Define o atributo idUsuarioDesabilitacao
     */
    public void setIdUsuarioDesabilitacao(String idUsuarioDesabilitacao) {
        this.idUsuarioDesabilitacao = idUsuarioDesabilitacao;
    }

    /**
     * @return o atributo situacaoContingencia
     */
    public String getSituacaoContingencia() {
        return situacaoContingencia;
    }

    /**
     * Define o atributo situacaoContingencia
     */
    public void setSituacaoContingencia(String situacaoContingencia) {
        this.situacaoContingencia = situacaoContingencia;
    }

}
