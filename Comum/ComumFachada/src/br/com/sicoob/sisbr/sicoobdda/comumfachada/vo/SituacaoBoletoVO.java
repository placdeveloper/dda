package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoBoletoVO é responsável por
 * 
 * @author George.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto")
public class SituacaoBoletoVO extends BancoobVO {

    private String codSituacaoBoleto;
    private String descSituacaoBoleto;
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
