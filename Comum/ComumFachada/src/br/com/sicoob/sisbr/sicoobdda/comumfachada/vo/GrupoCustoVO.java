package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.GrupoCusto")
public class GrupoCustoVO extends BancoobVO {

    private Short id;
    private String descGrupoCusto;

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
     * @return o atributo descGrupoCusto
     */
    public String getDescGrupoCusto() {
        return descGrupoCusto;
    }

    /**
     * Define o atributo descGrupoCusto
     */
    public void setDescGrupoCusto(String descGrupoCusto) {
        this.descGrupoCusto = descGrupoCusto;
    }

}
