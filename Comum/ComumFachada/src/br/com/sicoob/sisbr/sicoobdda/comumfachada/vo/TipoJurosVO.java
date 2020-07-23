package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros")
public class TipoJurosVO extends BancoobVO {

    private Short id;
    private String descTipoJuros;

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
     * @return o atributo descTipoJuros
     */
    public String getDescTipoJuros() {
        return descTipoJuros;
    }

    /**
     * Define o atributo descTipoJuros
     */
    public void setDescTipoJuros(String descTipoJuros) {
        this.descTipoJuros = descTipoJuros;
    }

}
