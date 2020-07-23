/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         TipoContingenciaDto.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoContingenciaDto é responsável por
 * 
 * @author Danilo.Barros
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TipoContingenciaDTO")
public class TipoContingenciaDto extends BancoobDto {
    private static final long serialVersionUID = 1L;
    private Long codTipoContingencia;
    private String descTipoContingencia;

    /**
     * @param codTipoContingencia
     * @param descTipoContingencia
     */
    public TipoContingenciaDto(Long codTipoContingencia, String descTipoContingencia) {
        super();
        this.codTipoContingencia = codTipoContingencia;
        this.descTipoContingencia = descTipoContingencia;
    }

    /**
     * @return o atributo codTipoContingencia
     */
    public Long getCodTipoContingencia() {
        return codTipoContingencia;
    }

    /**
     * Define o atributo codTipoContingencia
     */
    public void setCodTipoContingencia(Long codTipoContingencia) {
        this.codTipoContingencia = codTipoContingencia;
    }

    /**
     * @return o atributo descTipoContingencia
     */
    public String getDescTipoContingencia() {
        return descTipoContingencia;
    }

    /**
     * Define o atributo descTipoContingencia
     */
    public void setDescTipoContingencia(String descTipoContingencia) {
        this.descTipoContingencia = descTipoContingencia;
    }

}
