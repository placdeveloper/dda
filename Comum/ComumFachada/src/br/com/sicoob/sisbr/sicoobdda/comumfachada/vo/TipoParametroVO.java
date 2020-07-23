package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoParametroDDA")
public class TipoParametroVO extends BancoobVO {

    private Short id;
    private String descTipoParametro;

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
     * @return o atributo descTipoParametro
     */
    public String getDescTipoParametro() {
        return descTipoParametro;
    }

    /**
     * Define o atributo descTipoParametro
     */
    public void setDescTipoParametro(String descTipoParametro) {
        this.descTipoParametro = descTipoParametro;
    }

}
