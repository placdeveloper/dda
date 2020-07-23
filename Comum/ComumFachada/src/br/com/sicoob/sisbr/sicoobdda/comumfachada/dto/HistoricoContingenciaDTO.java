/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         HistoricoContingenciaDTO.java
 * Data Criação:    Jan 2, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * HistoricoContingenciaDTO
 * 
 * @author Danilo.Barros
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.HistoricoContingenciaDto")
public class HistoricoContingenciaDTO extends BancoobDTO{
    private DateTime dataSituacao;
    private String situacaoContingencia;
    private String tipoContingencia;
    private String login;

    /**
     * @return o atributo dataSituacao
     */
    public DateTime getDataSituacao() {
        return dataSituacao;
    }

    /**
     * Define o atributo dataSituacao
     */
    public void setDataSituacao(DateTime dataSituacao) {
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
