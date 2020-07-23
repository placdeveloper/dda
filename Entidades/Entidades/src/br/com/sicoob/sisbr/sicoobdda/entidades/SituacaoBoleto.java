package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoBoleto é responsável por
 * 
 * @author George.santos
 */
@Entity
@Table(name = "SITUACAOBOLETO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoBoletoVO")
public class SituacaoBoleto extends SicoobDDAEntidade {

    private static final long serialVersionUID = -6179524537341519977L;

    public static final int ABERTO = 1;
    public static final int BAIXA_EFETIVA_LIQUIDACAO_INTRABANCARIA = 3;
    public static final int BAIXA_EFETIVA_POR_LIQUIDACAO_INTERBANCARIA = 4;
    public static final int BAIXA_EFETIVA_POR_SOLICITACAO_DO_CEDENTE = 2;
    public static final int BAIXA_EFETIVA_POR_DECURSO_DE_PRAZO = 5;
    public static final int BAIXA_EFETIVA_POR_ENVIO_PARA_PROTESTO = 6;
    public static final int BAIXA_EFETIVA_POR_SOLICITACAO_DA_INSTITUICAO_DESTINATARIA = 7;

    public static final String EM_ABERTO = "Em Aberto";
    public static final String LIQUIDADO = "Liquidado";
    public static final String BAIXADO = "Baixado";

    @Id
    @Column(unique = true, nullable = false)
    private String codSituacaoBoleto;

    @Column(nullable = false)
    private String descSituacaoBoleto;

    // Essa tipoSituacaoBoleto vem do COB.TipoSituacaoBoleto
    @Column(nullable = false)
    private String idTipoSituacaoBoleto;

    /**
     * @return o atributo codSituacaoBoleto
     */
    public String getCodSituacaoBoleto() {
        return codSituacaoBoleto;
    }

    /**
     * Define o atributo codSituacaoBoleto
     */
    public void setCodSituacaoBoleto(String codSituacaoBoleto) {
        this.codSituacaoBoleto = codSituacaoBoleto;
    }

    /**
     * @return o atributo descSituacaoBoleto
     */
    public String getDescSituacaoBoleto() {
        return descSituacaoBoleto;
    }

    /**
     * Define o atributo descSituacaoBoleto
     */
    public void setDescSituacaoBoleto(String descSituacaoBoleto) {
        this.descSituacaoBoleto = descSituacaoBoleto;
    }

    /**
     * @return o atributo idTipoSituacaoBoleto
     */
    public String getIdTipoSituacaoBoleto() {
        return idTipoSituacaoBoleto;
    }

    /**
     * Define o atributo idTipoSituacaoBoleto
     */
    public void setIdTipoSituacaoBoleto(String idTipoSituacaoBoleto) {
        this.idTipoSituacaoBoleto = idTipoSituacaoBoleto;
    }
}
