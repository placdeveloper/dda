package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoRateioLancamento é responsável por
 * 
 * @author rodrigo.neri
 */
@Entity
@Table(name = "SituacaoRateioLancamento", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoRateioLancamentoVO")
public class SituacaoRateioLancamento extends SicoobDDAEntidade {

    private static final long serialVersionUID = -735634083455805881L;

    public static final long AGUARDANDO_EFETIVACAO = 1;
    public static final long EFETIVADO = 2;
    public static final long ERRO_EFETIVACAO = 3;
    public static final long EFETIVADO_MANUALMENTE = 4;
    public static final long EFETIVADO_COM_AJUSTE = 5;
    public static final long ISENTO = 6;

    @Id
    @Column(name = "CODSITUACAORATEIOLANCAMENTO", unique = true, nullable = false)
    private Long codSituacaoRateioLancamento;

    @Column(length = 30)
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