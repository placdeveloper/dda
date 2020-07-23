/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         CarteiraDeParaDto.java
 * Data Criação:    Aug 15, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CarteiraDeParaDto é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.CarteiraDeParaDTO")
public class CarteiraDeParaDto extends BancoobDto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String descricaoCarteira;
    private Integer idCarteiraCip;

    /**
     * @param descricaoCarteira
     * @param idCarteiraCip
     */
    public CarteiraDeParaDto(String descricaoCarteira, Integer idCarteiraCip) {
        super();
        this.descricaoCarteira = descricaoCarteira;
        this.idCarteiraCip = idCarteiraCip;
    }

    /**
     * @return o atributo descricaoCarteira
     */
    public String getDescricaoCarteira() {
        return descricaoCarteira;
    }

    /**
     * Define o atributo descricaoCarteira
     */
    public void setDescricaoCarteira(String descricaoCarteira) {
        this.descricaoCarteira = descricaoCarteira;
    }

    /**
     * @return o atributo idCarteiraCip
     */
    public Integer getIdCarteiraCip() {
        return idCarteiraCip;
    }

    /**
     * Define o atributo idCarteiraCip
     */
    public void setIdCarteiraCip(Integer idCarteiraCip) {
        this.idCarteiraCip = idCarteiraCip;
    }

}
