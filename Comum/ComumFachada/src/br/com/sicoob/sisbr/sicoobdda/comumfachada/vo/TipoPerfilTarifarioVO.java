package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.TipoPerfilTarifario")
public class TipoPerfilTarifarioVO extends BancoobVO {

    private Short id;
    private String descTipoPerfilTarifario;

    /**
     * @return o atributo id
     */
    public Short getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Short id) {
        this.id = id;
    }

    /**
     * @return o atributo descTipoPerfilTarifario
     */
    public String getDescTipoPerfilTarifario() {
        return descTipoPerfilTarifario;
    }

    /**
     * Define o atributo descTipoPerfilTarifario
     */
    public void setDescTipoPerfilTarifario(String descTipoPerfilTarifario) {
        this.descTipoPerfilTarifario = descTipoPerfilTarifario;
    }

}
