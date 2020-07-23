package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CarteiraDeParaDto")
public class CarteiraDeParaDTO extends BancoobDTO {

    private String descricaoCarteira;
    private Integer idCarteiraCip;

    /**
     * @return o atributo descricaoCarteira
     */
    public String getDescricaoCarteira() {
        return descricaoCarteira;
    }

    /**
     * Define o atributo descricaoCarteira
     */
    public void setDescricaoCarteira(String descricaoCarteira) {
        this.descricaoCarteira = descricaoCarteira;
    }

    /**
     * @return o atributo idCarteiraCip
     */
    public Integer getIdCarteiraCip() {
        return idCarteiraCip;
    }

    /**
     * Define o atributo idCarteiraCip
     */
    public void setIdCarteiraCip(Integer idCarteiraCip) {
        this.idCarteiraCip = idCarteiraCip;
    }

}
