/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         FiltroRequisicaoCacheDTO.java
 * Data Criação:    1 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.Date;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * FiltroRequisicaoCacheDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto")
public class FiltroRequisicaoCacheDTO extends BancoobDTO {

    private String codServidorDestino;
    private String descCache;
    private String descPerfilDestino;
    private Date dataRequisicao;

    /**
     * @return o atributo codServidorDestino
     */
    public String getCodServidorDestino() {
        return codServidorDestino;
    }

    /**
     * Define o atributo codServidorDestino
     */
    public void setCodServidorDestino(String codServidorDestino) {
        this.codServidorDestino = codServidorDestino;
    }

    /**
     * @return o atributo descCache
     */
    public String getDescCache() {
        return descCache;
    }

    /**
     * Define o atributo descCache
     */
    public void setDescCache(String descCache) {
        this.descCache = descCache;
    }

    /**
     * @return o atributo descPerfilDestino
     */
    public String getDescPerfilDestino() {
        return descPerfilDestino;
    }

    /**
     * Define o atributo descPerfilDestino
     */
    public void setDescPerfilDestino(String descPerfilDestino) {
        this.descPerfilDestino = descPerfilDestino;
    }

    /**
     * @return o atributo dataRequisicao
     */
    public Date getDataRequisicao() {
        return dataRequisicao;
    }

    /**
     * Define o atributo dataRequisicao
     */
    public void setDataRequisicao(Date dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

}
