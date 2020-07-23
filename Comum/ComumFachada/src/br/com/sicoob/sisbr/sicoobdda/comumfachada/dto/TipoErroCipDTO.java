package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoErroCipDTO é responsável por
 * 
 * @author felipe.rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TipoErroCipDto")
public class TipoErroCipDTO extends BancoobDTO {

    private String codTipoErro;
    private String descTipoErro;
    private Short codTipoTratamentoErroCip;
    private String descTipoTratamentoErroCip;

    private Integer qtdErros;

    /**
     * @return o atributo codTipoErro
     */
    public String getCodTipoErro() {
        return codTipoErro;
    }

    /**
     * Define o atributo codTipoErro
     */
    public void setCodTipoErro(String codTipoErro) {
        this.codTipoErro = codTipoErro;
    }

    /**
     * @return o atributo descTipoErro
     */
    public String getDescTipoErro() {
        return descTipoErro;
    }

    /**
     * Define o atributo descTipoErro
     */
    public void setDescTipoErro(String descTipoErro) {
        this.descTipoErro = descTipoErro;
    }

    /**
     * @return o atributo codTipoTratamentoErroCip
     */
    public Short getCodTipoTratamentoErroCip() {
        return codTipoTratamentoErroCip;
    }

    /**
     * Define o atributo codTipoTratamentoErroCip
     */
    public void setCodTipoTratamentoErroCip(Short codTipoTratamentoErroCip) {
        this.codTipoTratamentoErroCip = codTipoTratamentoErroCip;
    }

    /**
     * @return o atributo descTipoTratamentoErroCip
     */
    public String getDescTipoTratamentoErroCip() {
        return descTipoTratamentoErroCip;
    }

    /**
     * Define o atributo descTipoTratamentoErroCip
     */
    public void setDescTipoTratamentoErroCip(String descTipoTratamentoErroCip) {
        this.descTipoTratamentoErroCip = descTipoTratamentoErroCip;
    }

    /**
     * @return o atributo qtdErros
     */
    public Integer getQtdErros() {
        return qtdErros;
    }

    /**
     * Define o atributo qtdErros
     */
    public void setQtdErros(Integer qtdErros) {
        this.qtdErros = qtdErros;
    }

}
