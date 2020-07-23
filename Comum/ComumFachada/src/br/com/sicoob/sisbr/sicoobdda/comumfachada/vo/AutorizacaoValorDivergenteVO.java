package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente")
public class AutorizacaoValorDivergenteVO extends BancoobVO {

    private String codAutorizacaoValorDivergente;
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
