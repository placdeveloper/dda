package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MeioPagamento é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Entity
@Table(name = "MeioPagamento", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MeioPagamentoVO")
public class MeioPagamento extends SicoobDDAEntidade {

    private static final long serialVersionUID = -3235107977715685693L;

    public static final short ESPECIE = 1;
    public static final short DEBITO_EM_CONTA = 2;
    public static final short CARTAO_DE_CREDITO = 3;
    public static final short CHEQUE = 4;

    @Id
    @Column(unique = true, nullable = false)
    private Short codMeioPagamento;

    @Column(nullable = false)
    private String descMeioPagamento;

    /**
     * @return o atributo codMeioPagamento
     */
    public Short getCodMeioPagamento() {
        return codMeioPagamento;
    }

    /**
     * Define o atributo codMeioPagamento
     */
    public void setCodMeioPagamento(Short codMeioPagamento) {
        this.codMeioPagamento = codMeioPagamento;
    }

    /**
     * @return o atributo descMeioPagamento
     */
    public String getDescMeioPagamento() {
        return descMeioPagamento;
    }

    /**
     * Define o atributo descMeioPagamento
     */
    public void setDescMeioPagamento(String descMeioPagamento) {
        this.descMeioPagamento = descMeioPagamento;
    }

}
