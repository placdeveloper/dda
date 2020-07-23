package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.TipoOperacaoAjuste")
public class TipoOperacaoAjusteVO extends BancoobVO {

    private Short id;
    private String descTipoOperacaoAjuste;

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
     * @return o atributo descTipoOperacaoAjuste
     */
    public String getDescTipoOperacaoAjuste() {
        return descTipoOperacaoAjuste;
    }

    /**
     * Define o atributo descTipoOperacaoAjuste
     */
    public void setDescTipoOperacaoAjuste(String descTipoOperacaoAjuste) {
        this.descTipoOperacaoAjuste = descTipoOperacaoAjuste;
    }

}
