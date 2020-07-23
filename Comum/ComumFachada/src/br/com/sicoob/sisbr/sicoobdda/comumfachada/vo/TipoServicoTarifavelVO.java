package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoServicoTarifavel")
public class TipoServicoTarifavelVO extends BancoobVO {

    private short codTipoServicoTarifavel;
    private String descServicoTarifavel;

    /**
     * @return o atributo codTipoServicoTarifavel
     */
    public short getCodTipoServicoTarifavel() {
        return codTipoServicoTarifavel;
    }

    /**
     * Define o atributo codTipoServicoTarifavel
     */
    public void setCodTipoServicoTarifavel(short codTipoServicoTarifavel) {
        this.codTipoServicoTarifavel = codTipoServicoTarifavel;
    }

    /**
     * @return o atributo descServicoTarifavel
     */
    public String getDescServicoTarifavel() {
        return descServicoTarifavel;
    }

    /**
     * Define o atributo descServicoTarifavel
     */
    public void setDescServicoTarifavel(String descServicoTarifavel) {
        this.descServicoTarifavel = descServicoTarifavel;
    }

}
