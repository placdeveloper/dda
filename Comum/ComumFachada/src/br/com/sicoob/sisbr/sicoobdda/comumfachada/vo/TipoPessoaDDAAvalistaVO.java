package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoPessoaDDAAvalista")
public class TipoPessoaDDAAvalistaVO extends BancoobVO {

    private String codTipoPessoaDDAAvalista;
    private String descTipoPessoaDDAAvalista;

    /**
     * @return o atributo codTipoPessoaDDAAvalista
     */
    public String getCodTipoPessoaDDAAvalista() {
        return codTipoPessoaDDAAvalista;
    }

    /**
     * Define o atributo codTipoPessoaDDAAvalista
     */
    public void setCodTipoPessoaDDAAvalista(String codTipoPessoaDDAAvalista) {
        this.codTipoPessoaDDAAvalista = codTipoPessoaDDAAvalista;
    }

    /**
     * @return o atributo descTipoPessoaDDAAvalista
     */
    public String getDescTipoPessoaDDAAvalista() {
        return descTipoPessoaDDAAvalista;
    }

    /**
     * Define o atributo descTipoPessoaDDAAvalista
     */
    public void setDescTipoPessoaDDAAvalista(String descTipoPessoaDDAAvalista) {
        this.descTipoPessoaDDAAvalista = descTipoPessoaDDAAvalista;
    }

}
