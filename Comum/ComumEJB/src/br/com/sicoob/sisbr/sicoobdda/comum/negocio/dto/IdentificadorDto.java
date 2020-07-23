/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         AbrirArquivoRetornoDto.java
 * Data Criação:    Jul 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * IdentificadorDto é responsável por
 * 
 * @author George.Santos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.IdentificadorDTO")
public class IdentificadorDto extends BancoobDto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long identificadorGeral;

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
