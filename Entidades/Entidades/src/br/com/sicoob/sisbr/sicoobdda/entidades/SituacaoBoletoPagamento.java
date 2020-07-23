package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SituacaoBoletoPagamento é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "SITUACAOBOLETOPAGAMENTO", schema = "DDA")
public class SituacaoBoletoPagamento extends SicoobDDAEntidade {

    private static final long serialVersionUID = -6179524537341519977L;

    public static final String BOLETO_JA_BAIXADO = "01";
    public static final String BOLETO_BLOQUEADO_PARA_PAGAMENTO = "02";
    public static final String BOLETO_ENCONTRADO_E_CLIENTE_BENEFICIARIO_INAPTO_NA_INSTITUICAO_EMISSORA_DO_TITULO = "03";
    public static final String BOLETO_ENCONTRADO_NA_BASE_E_CLIENTE_BENEFICIARIO_SEM_CADASTRO = "04";
    public static final String BOLETO_ENCONTRADO_E_CLIENTE_BENEFICIARIO_EM_ANALISE_NA_INSTITUICAO_EMISSORA_DO_TITULO = "05";
    public static final String BOLETO_EXCEDEU_O_LIMITE_DE_PAGAMENTOS_PARCIAIS = "06";
    public static final String BAIXA_OPERACIONAL_EM_DUPLICIDADE_PARA_TITULO_QUE_NAO_PERMITE_PAGAMENTO_PARCIAL = "07";
    public static final String BAIXA_OPERACIONAL_JA_REGISTRADA_PARA_TITULO_QUE_NAO_PERMITE_PAGAMENTO_PARCIAL = "08";
    public static final String BOLETO_EXCEDEU_VALOR_DE_SALDO_PARA_PAGAMENTO_PARCIAL_PARA_TIPO_DE_MODELO_DE_CALCULO_04 = "09";
    public static final String BOLETO_ENCONTRADO_E_CLIENTE_BENEFICIARIO_INAPTO_EM_INSTITUICAO_DIFERENTE_DA_EMISSORA = "10";
    public static final String BOLETO_ENCONTRADO_E_CLIENTE_BENEFICIARIO_EM_ANALISE_EM_INSTITUICAO_DIFERENTE_DA_EMISSORA = "11";
    public static final String BOLETO_ENCONTRADO_E_CLIENTE_BENEFICIARIO_APTO = "12";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private String codSituacaoBoletoPagamento;

    @Column(nullable = false)
    private String descSituacaoBoletoPagamento;

    @Column(nullable = false)
    private Boolean bolLiberadoRecebimento;

    private String descMensagemErroPagamento;

    /**
     * @return o atributo codSituacaoBoletoPagamento
     */
    public String getCodSituacaoBoletoPagamento() {
        return codSituacaoBoletoPagamento;
    }

    /**
     * Define o atributo codSituacaoBoletoPagamento
     */
    public void setCodSituacaoBoletoPagamento(String codSituacaoBoletoPagamento) {
        this.codSituacaoBoletoPagamento = codSituacaoBoletoPagamento;
    }

    /**
     * @return o atributo descSituacaoBoletoPagamento
     */
    public String getDescSituacaoBoletoPagamento() {
        return descSituacaoBoletoPagamento;
    }

    /**
     * Define o atributo descSituacaoBoletoPagamento
     */
    public void setDescSituacaoBoletoPagamento(String descSituacaoBoletoPagamento) {
        this.descSituacaoBoletoPagamento = descSituacaoBoletoPagamento;
    }

    /**
     * @return o atributo bolLiberadoRecebimento
     */
    public Boolean getBolLiberadoRecebimento() {
        return bolLiberadoRecebimento;
    }

    /**
     * Define o atributo bolLiberadoRecebimento
     */
    public void setBolLiberadoRecebimento(Boolean bolLiberadoRecebimento) {
        this.bolLiberadoRecebimento = bolLiberadoRecebimento;
    }

    /**
     * @return o atributo descMensagemErroPagamento
     */
    public String getDescMensagemErroPagamento() {
        return descMensagemErroPagamento;
    }

    /**
     * Define o atributo descMensagemErroPagamento
     */
    public void setDescMensagemErroPagamento(String descMensagemErroPagamento) {
        this.descMensagemErroPagamento = descMensagemErroPagamento;
    }

}
