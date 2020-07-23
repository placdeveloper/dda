package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ConsultaTipoErroCipDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaTipoErroCipDto")
public class ConsultaTipoErroCipDTO extends BancoobDTO {

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
