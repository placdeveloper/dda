package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CanalPagamentoDDA é responsável por
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "CanalPagamentoDDA", schema = "DDA")
public class CanalPagamentoDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = -4409384617380385567L;

    public static final short AGENCIAS_POSTOS_TRADICIONAIS = 1;
    public static final short TERMINAL_AUTO_ATENDIMENTO = 2;
    public static final short INTERNET = 3;
    public static final short CORRESPONDENTE_BANCARIO = 5;
    public static final short CENTRAL_ATENDIMENTO = 6;
    public static final short ARQUIVO_ELETRONICO = 7;
    public static final short DDA = 8;

    @Id
    @Column(unique = true, nullable = false)
    private Short codCanalPagamento;

    private String descCanalPagamento;

    /**
     * @return o atributo codCanalPagamento
     */
    public Short getCodCanalPagamento() {
        return codCanalPagamento;
    }

    /**
     * Define o atributo codCanalPagamento
     */
    public void setCodCanalPagamento(Short codCanalPagamento) {
        this.codCanalPagamento = codCanalPagamento;
    }

    /**
     * @return o atributo descCanalPagamento
     */
    public String getDescCanalPagamento() {
        return descCanalPagamento;
    }

    /**
     * Define o atributo descCanalPagamento
     */
    public void setDescCanalPagamento(String descCanalPagamento) {
        this.descCanalPagamento = descCanalPagamento;
    }

}
