package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.ListaItemLegado")
public class ListaItemLegadoVO extends BancoobVO {

    private String codListaItem;
    private String descListaItem;
    private Long idLista;

    /**
     * @return o atributo codListaItem
     */
    public String getCodListaItem() {
        return codListaItem;
    }

    /**
     * Define o atributo codListaItem
     */
    public void setCodListaItem(String codListaItem) {
        this.codListaItem = codListaItem;
    }

    /**
     * @return o atributo descListaItem
     */
    public String getDescListaItem() {
        return descListaItem;
    }

    /**
     * Define o atributo descListaItem
     */
    public void setDescListaItem(String descListaItem) {
        this.descListaItem = descListaItem;
    }

    /**
     * @return o atributo idLista
     */
    public Long getIdLista() {
        return idLista;
    }

    /**
     * Define o atributo idLista
     */
    public void setIdLista(Long idLista) {
        this.idLista = idLista;
    }

}
