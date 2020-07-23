package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoBoletoConsultado")
public class TipoBoletoConsultadoVO extends BancoobVO {

    private String codTipoBoletoConsultado;
    private String descTipoBoletoConsultado;

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
