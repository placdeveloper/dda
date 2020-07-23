/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         Contingencia.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ContingenciaDTO
 * 
 * @author Danilo.Barros
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeContingenciaDto")
public class GradeContingenciaDTO extends BancoobDTO {
    private String descTipoContingencia;
    private DateTimeDB dataHoraSituacaoInicial;
    private String idUsuarioHabilitacao;
    private DateTimeDB dataHoraSituacaoFinal;
    private String idUsuarioDesabilitacao;
    private String situacaoContingencia;

    /**
     * @param
     */
    public GradeContingenciaDTO() {
        super();
    }

    /**
     * @param descTipoContingencia
     * @param dataHoraSituacaoInicial
     * @param idUsuarioHabilitacao
     * @param dataHoraSituacaoFinal
     * @param idUsuarioDesabilitacao
     */
    public GradeContingenciaDTO(String descTipoContingencia, DateTimeDB dataHoraSituacaoInicial, String idUsuarioHabilitacao, DateTimeDB dataHoraSituacaoFinal,
            String idUsuarioDesabilitacao,
            String situacaoContingencia) {
        super();
        this.descTipoContingencia = descTipoContingencia;
        this.dataHoraSituacaoInicial = dataHoraSituacaoInicial;
        this.idUsuarioHabilitacao = idUsuarioHabilitacao;
        this.dataHoraSituacaoFinal = dataHoraSituacaoFinal;
        this.idUsuarioDesabilitacao = idUsuarioDesabilitacao;
        this.setSituacaoContingencia(getSituacaoContingencia());
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
    public DateTimeDB getDataHoraSituacaoInicial() {
        return dataHoraSituacaoInicial;
    }

    /**
     * Define o atributo dataHoraSituacaoInicial
     */
    public void setDataHoraSituacaoInicial(DateTimeDB dataHoraSituacaoInicial) {
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
    public DateTimeDB getDataHoraSituacaoFinal() {
        return dataHoraSituacaoFinal;
    }

    /**
     * Define o atributo dataHoraSituacaoFinal
     */
    public void setDataHoraSituacaoFinal(DateTimeDB dataHoraSituacaoFinal) {
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
