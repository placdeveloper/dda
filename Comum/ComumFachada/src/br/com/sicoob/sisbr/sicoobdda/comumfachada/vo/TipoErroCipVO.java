/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.fachada.vo
 * Arquivo:         TipoErroCipVO.java
 * Data Criação:    Aug 4, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoErroCip é responsável por
 * 
 * @author Samuell.Ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip")
public class TipoErroCipVO extends BancoobVO {

    private String codTipoErro;
    private String descTipoErro;
    private TipoTratamentoErroCipVO tipoTratamentoErroCip;

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
     * @return the tipoTratamentoErroCip
     */
    public TipoTratamentoErroCipVO getTipoTratamentoErroCip() {
        return tipoTratamentoErroCip;
    }

    /**
     * @param tipoTratamentoErroCip the tipoTratamentoErroCip to set
     */
    public void setTipoTratamentoErroCip(TipoTratamentoErroCipVO tipoTratamentoErroCip) {
        this.tipoTratamentoErroCip = tipoTratamentoErroCip;
    }

}
