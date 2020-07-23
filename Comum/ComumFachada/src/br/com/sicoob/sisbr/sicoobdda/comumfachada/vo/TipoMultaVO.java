package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta")
public class TipoMultaVO extends BancoobVO {

    private Short id;
    private String descTipoMulta;

    /**
     * @return o atributo id
     */
    public Short getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * @return o atributo descTipoMulta
     */
    public String getDescTipoMulta() {
        return descTipoMulta;
    }

    /**
     * Define o atributo descTipoMulta
     */
    public void setDescTipoMulta(String descTipoMulta) {
        this.descTipoMulta = descTipoMulta;
    }

}
