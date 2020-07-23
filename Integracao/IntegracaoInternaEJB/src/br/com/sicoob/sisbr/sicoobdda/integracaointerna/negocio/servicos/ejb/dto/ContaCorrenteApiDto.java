/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-interna-privada-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.negocio.servicos.ejb.dto
 * Arquivo:         ContaCorrenteApiDto.java
 * Data Criação:    Nov 19, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.enums.AplicativoEnum;

/**
 * ContaCorrenteApiDto é responsável por
 * 
 * @author samuell.ramos
 */
public abstract class ContaCorrenteApiDto extends BancoobDto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private AplicativoEnum aplicativoEnum;
    private Integer idAplicativo;

    public AplicativoEnum getAplicativoEnum() {
        return aplicativoEnum;
    }

    /**
     * Utilize o idAplicativo
     * 
     * @see idAplicativo
     */
    public void setAplicativoEnum(AplicativoEnum aplicativoEnum) {
        this.aplicativoEnum = aplicativoEnum;
    }

    public Integer getIdAplicativo() {
        return idAplicativo;
    }

    public void setIdAplicativo(Integer idAplicativo) {
        this.idAplicativo = idAplicativo;
    }
}
