/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         TipoBoletoConsultado.java
 * Data Criação:    Sep 15, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoBoletoConsultado é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "TIPOBOLETOCONSULTADO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoBoletoConsultadoVO")
public class TipoBoletoConsultado extends SicoobDDAEntidade {

    private static final long serialVersionUID = -5541577962295365566L;

    public static final String PROPRIO = "P";
    public static final String TERCEIRO = "T";

    @Id
    @Column(unique = true, nullable = false)
    private String codTipoBoletoConsultado;

    private String descTipoBoletoConsultado;

    /**
     * 
     */
    public TipoBoletoConsultado() {
        super();
    }

    /**
     * @param codTipoBoletoConsultado
     */
    public TipoBoletoConsultado(String codTipoBoletoConsultado) {
        super();
        this.codTipoBoletoConsultado = codTipoBoletoConsultado;
    }

    /**
     * @return o atributo codTipoBoletoConsultado
     */
    public String getCodTipoBoletoConsultado() {
        return codTipoBoletoConsultado;
    }

    /**
     * Define o atributo codTipoBoletoConsultado
     */
    public void setCodTipoBoletoConsultado(String codTipoBoletoConsultado) {
        this.codTipoBoletoConsultado = codTipoBoletoConsultado;
    }

    /**
     * @return o atributo descTipoBoletoConsultado
     */
    public String getDescTipoBoletoConsultado() {
        return descTipoBoletoConsultado;
    }

    /**
     * Define o atributo descTipoBoletoConsultado
     */
    public void setDescTipoBoletoConsultado(String descTipoBoletoConsultado) {
        this.descTipoBoletoConsultado = descTipoBoletoConsultado;
    }

}
