package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TIPOPESSOADDAAVALISTA
 * 
 * @author George.santos
 */
@Entity
@Table(name = "TIPOPESSOADDAAVALISTA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoPessoaDDAAvalistaVO")
public class TipoPessoaDDAAvalista extends SicoobDDAEntidade {

    private static final long serialVersionUID = 4990210398862186865L;

    public static final String ISENTO_NAO_INFORMADO = "0";
    public static final String CPF = "1";
    public static final String CNPJ = "2";
    public static final String PIS_PASEP = "3";
    public static final String OUTROS = "9";

    @Id
    @Column(name = "CODTIPOPESSOADDAAVALISTA", insertable = false, unique = true, nullable = false)
    private String codTipoPessoaDDAAvalista;

    @Column(name = "DESCTIPOPESSOADDAAVALISTA", nullable = false, length = 500)
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