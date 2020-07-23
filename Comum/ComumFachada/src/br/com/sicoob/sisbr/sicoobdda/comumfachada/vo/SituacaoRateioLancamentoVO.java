package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateioLancamento")
public class SituacaoRateioLancamentoVO extends BancoobVO {

    private Long codSituacaoRateioLancamento;
    private String descSituacaoRateioLancamento;

    /**
     * @return o atributo codSituacaoRateioLancamento
     */
    public Long getCodSituacaoRateioLancamento() {
        return codSituacaoRateioLancamento;
    }

    /**
     * Define o atributo codSituacaoRateioLancamento
     */
    public void setCodSituacaoRateioLancamento(Long codSituacaoRateioLancamento) {
        this.codSituacaoRateioLancamento = codSituacaoRateioLancamento;
    }

    /**
     * @return o atributo descSituacaoRateioLancamento
     */
    public String getDescSituacaoRateioLancamento() {
        return descSituacaoRateioLancamento;
    }

    /**
     * Define o atributo descSituacaoRateioLancamento
     */
    public void setDescSituacaoRateioLancamento(String descSituacaoRateioLancamento) {
        this.descSituacaoRateioLancamento = descSituacaoRateioLancamento;
    }

}
