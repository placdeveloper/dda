package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoEventoTarifavel")
public class TipoEventoTarifavelVO extends BancoobVO {

    private short codTipoEventoTarifavel;
    private String descTipoEventoTarifavel;

    /**
     * @return o atributo codTipoEventoTarifavel
     */
    public short getCodTipoEventoTarifavel() {
        return codTipoEventoTarifavel;
    }

    /**
     * Define o atributo codTipoEventoTarifavel
     */
    public void setCodTipoEventoTarifavel(short codTipoEventoTarifavel) {
        this.codTipoEventoTarifavel = codTipoEventoTarifavel;
    }

    /**
     * @return o atributo descTipoEventoTarifavel
     */
    public String getDescTipoEventoTarifavel() {
        return descTipoEventoTarifavel;
    }

    /**
     * Define o atributo descTipoEventoTarifavel
     */
    public void setDescTipoEventoTarifavel(String descTipoEventoTarifavel) {
        this.descTipoEventoTarifavel = descTipoEventoTarifavel;
    }

}
