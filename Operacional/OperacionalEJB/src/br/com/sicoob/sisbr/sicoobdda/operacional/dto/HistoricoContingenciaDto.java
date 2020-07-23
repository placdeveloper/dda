/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         HistoricoContingenciaDto.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * HistoricoContingenciaDto
 * 
 * @author Danilo.Barros
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.HistoricoContingenciaDTO")
public class HistoricoContingenciaDto extends BancoobDto {
    private static final long serialVersionUID = 1L;
    private DateTimeDB dataSituacao;
    private String situacaoContingencia;
    private String tipoContingencia;
    private String login;

    /**
     * @param dataSituacao
     * @param situacaoContingencia
     * @param tipoContingencia
     * @param login
     */
    public HistoricoContingenciaDto(DateTimeDB dataSituacao, String situacaoContingencia, String tipoContingencia, String login) {
        super();
        this.dataSituacao = dataSituacao;
        this.situacaoContingencia = situacaoContingencia;
        this.tipoContingencia = tipoContingencia;
        this.login = login;
    }

    /**
     * @return o atributo dataSituacao
     */
    public Date getDataSituacao() {
        return dataSituacao;
    }

    /**
     * Define o atributo dataSituacao
     */
    public void setDataSituacao(DateTimeDB dataSituacao) {
        this.dataSituacao = dataSituacao;
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

    /**
     * @return o atributo tipoContingencia
     */
    public String getTipoContingencia() {
        return tipoContingencia;
    }

    /**
     * Define o atributo tipoContingencia
     */
    public void setTipoContingencia(String tipoContingencia) {
        this.tipoContingencia = tipoContingencia;
    }

    /**
     * @return o atributo login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o atributo login
     */
    public void setLogin(String login) {
        this.login = login;
    }

}
