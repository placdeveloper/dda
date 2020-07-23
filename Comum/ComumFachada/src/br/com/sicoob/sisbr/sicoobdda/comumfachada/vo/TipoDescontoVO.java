package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto")
public class TipoDescontoVO extends BancoobVO {

    private String codTipoDesconto;
    private String descTipoDesconto;

    /**
     * @return o atributo codTipoDesconto
     */
    public String getCodTipoDesconto() {
        return codTipoDesconto;
    }

    /**
     * Define o atributo codTipoDesconto
     */
    public void setCodTipoDesconto(String codTipoDesconto) {
        this.codTipoDesconto = codTipoDesconto;
    }

    /**
     * @return o atributo descTipoDesconto
     */
    public String getDescTipoDesconto() {
        return descTipoDesconto;
    }

    /**
     * Define o atributo descTipoDesconto
     */
    public void setDescTipoDesconto(String descTipoDesconto) {
        this.descTipoDesconto = descTipoDesconto;
    }

}
