/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         FiltroRelatorioEventoTarifavelDto.java
 * Data Cria��o:    24 de out de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * FiltroRelatorioEventoTarifavelDto � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRelatorioEventoTarifavelDto")
public class FiltroRelatorioEventoTarifavelDTO extends BancoobDTO {

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
