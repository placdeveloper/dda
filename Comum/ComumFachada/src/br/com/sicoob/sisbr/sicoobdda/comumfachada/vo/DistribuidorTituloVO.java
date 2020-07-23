package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.DistribuidorTitulo")
public class DistribuidorTituloVO extends BancoobVO {

    private Short id;
    private String descDistribuidorTitulo;

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
     * @return o atributo descDistribuidorTitulo
     */
    public String getDescDistribuidorTitulo() {
        return descDistribuidorTitulo;
    }

    /**
     * Define o atributo descDistribuidorTitulo
     */
    public void setDescDistribuidorTitulo(String descDistribuidorTitulo) {
        this.descDistribuidorTitulo = descDistribuidorTitulo;
    }

}
