/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         CooperativaDTO.java
 * Data Criação:    Sep 15, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CooperativaDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CooperativaDto")
public class CooperativaDTO extends BancoobDTO {

    private Integer numCooperativa;
    private String nomeCooperativa;

    /**
     * @return the numCooperativa
     */
    public Integer getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * @param numCooperativa the numCooperativa to set
     */
    public void setNumCooperativa(Integer numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return the nomeCooperativa
     */
    public String getNomeCooperativa() {
        return nomeCooperativa;
    }

    /**
     * @param nomeCooperativa the nomeCooperativa to set
     */
    public void setNomeCooperativa(String nomeCooperativa) {
        this.nomeCooperativa = nomeCooperativa;
    }
}
