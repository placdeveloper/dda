/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         TipoContingenciaDTO.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoContingenciaDTO é responsável por
 * 
 * @author Danilo.Barros
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoContingenciaDto")
public class TipoContingenciaDTO extends BancoobDTO {
    private Long codTipoContingencia;
    private String descTipoContingencia;

    /**
     * @param codTipoContingencia
     * @param descTipoContingencia
     */
    public TipoContingenciaDTO(Long codTipoContingencia, String descTipoContingencia) {
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
