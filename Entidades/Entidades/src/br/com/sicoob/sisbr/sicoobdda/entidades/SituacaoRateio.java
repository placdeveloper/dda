package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoRateio é responsável por
 * 
 * @author rodrigo.neri
 */
@Entity
@Table(name = "SituacaoRateio", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoRateioVO")
public class SituacaoRateio extends SicoobDDAEntidade {

    private static final long serialVersionUID = -735634083455805881L;

    public static final long AGUARDANDO_APROVACAO = 1;
    public static final long APROVADO_PARA_EFETIVACAO = 2;
    public static final long PROCESSANDO_EFETIVACAO = 3;
    public static final long EFETIVADO = 4;
    public static final long ERRO_EFETIVACAO = 5;

    @Id
    @Column(name = "CODSITUACAORATEIO", unique = true, nullable = false)
    private Long codSituacaoRateio;

    @Column(length = 30)
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