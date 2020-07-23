package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * AutorizacaoValorDivergente é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "AutorizacaoValorDivergente", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.AutorizacaoValorDivergenteVO")
public class AutorizacaoValorDivergente extends SicoobDDAEntidade {

    private static final long serialVersionUID = 1674201971498576446L;

    public static final String QUALQUER_VALOR = "1";
    public static final String ENTRE_O_MINIMO_E_O_MAXIMO = "2";
    public static final String NAO_ACEITAR_PAGAMENTO_COM_VALOR_DIVERGENTE = "3";
    public static final String SOMENTE_VALOR_MINIMO = "4";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODAUTORIZACAOVALORDIVERGENTE", unique = true, nullable = false)
    private String codAutorizacaoValorDivergente;

    @Column(nullable = false)
    private String descAutorizacaoValorDivergente;

    private Boolean bolPermiteAlterarValor;

    /**
     * @return o atributo codAutorizacaoValorDivergente
     */
    public String getCodAutorizacaoValorDivergente() {
        return codAutorizacaoValorDivergente;
    }

    /**
     * Define o atributo codAutorizacaoValorDivergente
     */
    public void setCodAutorizacaoValorDivergente(String codAutorizacaoValorDivergente) {
        this.codAutorizacaoValorDivergente = codAutorizacaoValorDivergente;
    }

    /**
     * @return o atributo descAutorizacaoValorDivergente
     */
    public String getDescAutorizacaoValorDivergente() {
        return descAutorizacaoValorDivergente;
    }

    /**
     * Define o atributo descAutorizacaoValorDivergente
     */
    public void setDescAutorizacaoValorDivergente(String descAutorizacaoValorDivergente) {
        this.descAutorizacaoValorDivergente = descAutorizacaoValorDivergente;
    }

    /**
     * @return o atributo bolPermiteAlterarValor
     */
    public Boolean getBolPermiteAlterarValor() {
        return bolPermiteAlterarValor;
    }

    /**
     * Define o atributo bolPermiteAlterarValor
     */
    public void setBolPermiteAlterarValor(Boolean bolPermiteAlterarValor) {
        this.bolPermiteAlterarValor = bolPermiteAlterarValor;
    }

}
