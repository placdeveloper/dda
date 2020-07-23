package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.CriterioCobranca")
public class CriterioCobrancaVO extends BancoobVO {

    private Short id;
    private String descCriterioCobranca;

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
     * @return o atributo descCriterioCobranca
     */
    public String getDescCriterioCobranca() {
        return descCriterioCobranca;
    }

    /**
     * Define o atributo descCriterioCobranca
     */
    public void setDescCriterioCobranca(String descCriterioCobranca) {
        this.descCriterioCobranca = descCriterioCobranca;
    }

}
