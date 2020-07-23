package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateio")
public class SituacaoRateioVO extends BancoobVO {

    private Long codSituacaoRateio;
    private String descSituacaoRateio;

    /**
     * @return o atributo codSituacaoRateio
     */
    public Long getCodSituacaoRateio() {
        return codSituacaoRateio;
    }

    /**
     * Define o atributo codSituacaoRateio
     */
    public void setCodSituacaoRateio(Long codSituacaoRateio) {
        this.codSituacaoRateio = codSituacaoRateio;
    }

    /**
     * @return o atributo descSituacaoRateio
     */
    public String getDescSituacaoRateio() {
        return descSituacaoRateio;
    }

    /**
     * Define o atributo descSituacaoRateio
     */
    public void setDescSituacaoRateio(String descSituacaoRateio) {
        this.descSituacaoRateio = descSituacaoRateio;
    }

}
