/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ConsultaTipoErroCipDto.java
 * Data Criação:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;


/**
 * ConsultaTipoErroCipDto é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaTipoErroCipDTO")
public class ConsultaTipoErroCipDto extends BancoobDto {

    private static final long serialVersionUID = 4705060154297606803L;

    private String codTipoErro;
    private String descTipoErro;
    private Boolean bolErroTratamentoAutomatizado;

    /**
     * @return the codTipoErro
     */
    public String getCodTipoErro() {
        return codTipoErro;
    }

    /**
     * @param codTipoErro the codTipoErro to set
     */
    public void setCodTipoErro(String codTipoErro) {
        this.codTipoErro = codTipoErro;
    }

    /**
     * @return the descTipoErro
     */
    public String getDescTipoErro() {
        return descTipoErro;
    }

    /**
     * @param descTipoErro the descTipoErro to set
     */
    public void setDescTipoErro(String descTipoErro) {
        this.descTipoErro = descTipoErro;
    }

    /**
     * @return the bolErroTratamentoAutomatizado
     */
    public Boolean getBolErroTratamentoAutomatizado() {
        return bolErroTratamentoAutomatizado;
    }

    /**
     * @param bolErroTratamentoAutomatizado the bolErroTratamentoAutomatizado to set
     */
    public void setBolErroTratamentoAutomatizado(Boolean bolErroTratamentoAutomatizado) {
        this.bolErroTratamentoAutomatizado = bolErroTratamentoAutomatizado;
    }


}
