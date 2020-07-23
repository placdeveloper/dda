package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo")
public class TipoModeloCalculoVO extends BancoobVO {

    private String codTipoModeloCalculo;
    private String descTipoModeloCalculo;

    /**
     * @return o atributo codTipoModeloCalculo
     */
    public String getCodTipoModeloCalculo() {
        return codTipoModeloCalculo;
    }

    /**
     * Define o atributo codTipoModeloCalculo
     */
    public void setCodTipoModeloCalculo(String codTipoModeloCalculo) {
        this.codTipoModeloCalculo = codTipoModeloCalculo;
    }

    /**
     * @return o atributo descTipoModeloCalculo
     */
    public String getDescTipoModeloCalculo() {
        return descTipoModeloCalculo;
    }

    /**
     * Define o atributo descTipoModeloCalculo
     */
    public void setDescTipoModeloCalculo(String descTipoModeloCalculo) {
        this.descTipoModeloCalculo = descTipoModeloCalculo;
    }

}
