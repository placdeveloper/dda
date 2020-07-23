package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MeioPagamento")
public class MeioPagamentoVO extends BancoobVO {

    private Short codMeioPagamento;
    private String descMeioPagamento;

    /**
     * @return o atributo codMeioPagamento
     */
    public Short getCodMeioPagamento() {
        return codMeioPagamento;
    }

    /**
     * Define o atributo codMeioPagamento
     */
    public void setCodMeioPagamento(Short codMeioPagamento) {
        this.codMeioPagamento = codMeioPagamento;
    }

    /**
     * @return o atributo descMeioPagamento
     */
    public String getDescMeioPagamento() {
        return descMeioPagamento;
    }

    /**
     * Define o atributo descMeioPagamento
     */
    public void setDescMeioPagamento(String descMeioPagamento) {
        this.descMeioPagamento = descMeioPagamento;
    }

}
