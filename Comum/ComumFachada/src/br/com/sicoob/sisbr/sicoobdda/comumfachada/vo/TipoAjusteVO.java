package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.TipoAjuste")
public class TipoAjusteVO extends BancoobVO {

    private Short id;
    private String descTipoAjuste;

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
     * @return o atributo descTipoAjuste
     */
    public String getDescTipoAjuste() {
        return descTipoAjuste;
    }

    /**
     * Define o atributo descTipoAjuste
     */
    public void setDescTipoAjuste(String descTipoAjuste) {
        this.descTipoAjuste = descTipoAjuste;
    }

}
