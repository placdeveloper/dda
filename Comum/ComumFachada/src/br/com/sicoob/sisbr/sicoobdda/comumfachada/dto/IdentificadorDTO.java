package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * IdentificadorDTO é responsável por
 * 
 * @author George.Santos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.IdentificadorDto")
public class IdentificadorDTO extends BancoobDTO {
    private Long identificadorGeral;

    /**
     * 
     */
    public IdentificadorDTO() {
    }

    /**
     * @return o atributo identificadorGeral
     */
    public Long getIdentificadorGeral() {
        return identificadorGeral;
    }

    /**
     * Define o atributo identificadorGeral
     */
    public void setIdentificadorGeral(Long identificadorGeral) {
        this.identificadorGeral = identificadorGeral;
    }
}
