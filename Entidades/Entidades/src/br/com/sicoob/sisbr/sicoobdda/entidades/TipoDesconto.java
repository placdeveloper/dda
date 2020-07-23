package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoDesconto é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "TIPODESCONTO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoDescontoVO")
public class TipoDesconto extends SicoobDDAEntidade {

    private static final long serialVersionUID = -8601117274953893345L;

    public static final String ISENTO = "0";
    public static final String VALOR_FIXO_ATE_A_DATA_INFORMADA = "1";
    public static final String PERCENTUAL_ATE_A_DATA_INFORMADA = "2";
    public static final String VALOR_POR_ANTECIPACAO_DIA_CORRIDO = "3";
    public static final String VALOR_POR_ANTECIPACAO_DIA_UTIL = "4";
    public static final String PERCENTUAL_POR_ANTECIPACAO_DIA_CORRIDO = "5";
    public static final String PERCENTUAL_POR_ANTECIPACAO_DIA_UTIL = "6";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODTIPODESCONTO", unique = true, nullable = false)
    private String codTipoDesconto;

    @Column(nullable = false)
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
