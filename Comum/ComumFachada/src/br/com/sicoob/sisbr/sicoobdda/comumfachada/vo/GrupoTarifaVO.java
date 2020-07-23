package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.GrupoTarifa")
public class GrupoTarifaVO extends BancoobVO {

    private Short id;
    private String descGrupoTarifa;

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
     * @return o atributo descGrupoTarifa
     */
    public String getDescGrupoTarifa() {
        return descGrupoTarifa;
    }

    /**
     * Define o atributo descGrupoTarifa
     */
    public void setDescGrupoTarifa(String descGrupoTarifa) {
        this.descGrupoTarifa = descGrupoTarifa;
    }

}
