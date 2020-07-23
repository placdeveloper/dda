/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         CooperativaDto.java
 * Data Criação:    Sep 15, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CooperativaDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CooperativaDTO")
public class CooperativaDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 2251158515487216480L;

    private Integer numCooperativa;
    private String nomeCooperativa;

    /**
     * @param numCooperativa
     * @param nomeCooperativa
     */
    public CooperativaDto(Integer numCooperativa, String nomeCooperativa) {
        super();
        this.numCooperativa = numCooperativa;
        this.nomeCooperativa = nomeCooperativa;
    }

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
