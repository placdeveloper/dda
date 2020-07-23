/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         FiltroRelatorioEventoTarifavelDto.java
 * Data Criação:    24 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * FiltroRelatorioEventoTarifavelDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.FiltroRelatorioEventoTarifavelDTO")
public class FiltroRelatorioEventoTarifavelDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 7824036585502978175L;

    private Integer codEventoTarifavel;
    private Integer codStatus;
    private String descEventoTarifavel;

    /**
     * @return o atributo codEventoTarifavel
     */
    public Integer getCodEventoTarifavel() {
        return codEventoTarifavel;
    }

    /**
     * Define o atributo codEventoTarifavel
     */
    public void setCodEventoTarifavel(Integer codEventoTarifavel) {
        this.codEventoTarifavel = codEventoTarifavel;
    }

    /**
     * @return o atributo codStatus
     */
    public Integer getCodStatus() {
        return codStatus;
    }

    /**
     * Define o atributo codStatus
     */
    public void setCodStatus(Integer codStatus) {
        this.codStatus = codStatus;
    }

    /**
     * @return o atributo descEventoTarifavel
     */
    public String getDescEventoTarifavel() {
        return descEventoTarifavel;
    }

    /**
     * Define o atributo descEventoTarifavel
     */
    public void setDescEventoTarifavel(String descEventoTarifavel) {
        this.descEventoTarifavel = descEventoTarifavel;
    }

}
