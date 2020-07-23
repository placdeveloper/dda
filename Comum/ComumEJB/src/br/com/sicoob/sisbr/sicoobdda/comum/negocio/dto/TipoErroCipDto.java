/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ErroBenficiarioCipDto.java
 * Data Criação:    Sep 11, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoErroCipDto é responsável por
 * 
 * @author Daniel.Basso
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoErroCipDTO")
public class TipoErroCipDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7978916278120143356L;

    private String codTipoErro;
    private String descTipoErro;

    private Short codTipoTratamentoErroCip;
    private String descTipoTratamentoErroCip;

    private Integer qtdErros;

    /**
     * 
     */
    public TipoErroCipDto() {
        super();
    }

    /**
     * @param codTipoErro
     * @param descTipoErro
     */
    public TipoErroCipDto(String codTipoErro, String descTipoErro) {
        super();
        this.codTipoErro = codTipoErro;
        this.descTipoErro = descTipoErro;
    }

    /**
     * Construtor QUERY - PESQUISAR_TIPO_ERRO_CIP_PAGINADO
     * 
     * @param codTipoErro
     * @param descTipoErro
     * @param codTipoTratamentoErroCip
     * @param descTipoTratamentoErroCip
     */
    public TipoErroCipDto(String codTipoErro, String descTipoErro, Short codTipoTratamentoErroCip, String descTipoTratamentoErroCip) {
        super();
        this.codTipoErro = codTipoErro;
        this.descTipoErro = descTipoErro;
        this.codTipoTratamentoErroCip = codTipoTratamentoErroCip;
        this.descTipoTratamentoErroCip = descTipoTratamentoErroCip;
    }

    /**
     * @param codTipoErro
     * @param descTipoErro
     * @param qtdErros
     */
    public TipoErroCipDto(String codTipoErro, String descTipoErro, Integer qtdErros) {
        this(codTipoErro, descTipoErro);
        this.qtdErros = qtdErros;
    }

    /**
     * @return the codTipoTratamentoErroCip
     */
    public Short getCodTipoTratamentoErroCip() {
        return codTipoTratamentoErroCip;
    }

    /**
     * @param codTipoTratamentoErroCip the codTipoTratamentoErroCip to set
     */
    public void setCodTipoTratamentoErroCip(Short codTipoTratamentoErroCip) {
        this.codTipoTratamentoErroCip = codTipoTratamentoErroCip;
    }

    /**
     * @return the descTipoTratamentoErroCip
     */
    public String getDescTipoTratamentoErroCip() {
        return descTipoTratamentoErroCip;
    }

    /**
     * @param descTipoTratamentoErroCip the descTipoTratamentoErroCip to set
     */
    public void setDescTipoTratamentoErroCip(String descTipoTratamentoErroCip) {
        this.descTipoTratamentoErroCip = descTipoTratamentoErroCip;
    }

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
