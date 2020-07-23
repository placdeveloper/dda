package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoRegistroModalidade")
public class TipoRegistroModalidadeVO extends BancoobVO {

    private Short id;
    private String descTipoRegistroModalidade;

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
     * @return o atributo descTipoRegistroModalidade
     */
    public String getDescTipoRegistroModalidade() {
        return descTipoRegistroModalidade;
    }

    /**
     * Define o atributo descTipoRegistroModalidade
     */
    public void setDescTipoRegistroModalidade(String descTipoRegistroModalidade) {
        this.descTipoRegistroModalidade = descTipoRegistroModalidade;
    }

}
