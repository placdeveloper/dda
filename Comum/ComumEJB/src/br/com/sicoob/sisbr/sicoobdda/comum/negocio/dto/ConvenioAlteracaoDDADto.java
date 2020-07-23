/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ConvenioDto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;

/**
 * CadastroConvenioDDADto
 * 
 * @author Rafael.Silva
 */
public class ConvenioAlteracaoDDADto extends ConvenioCadastroDDADto {

    private static final long serialVersionUID = -7155314034534095935L;

    private TipoManutencaoEnum tipoManutencaoConvenio;

    private Date dataFimConvenio;

    /**
     * 
     */
    public ConvenioAlteracaoDDADto() {
        super();
    }

    /**
     * @param tipoManutencaoConvenio
     * @param numCooperativa
     */
    public ConvenioAlteracaoDDADto(TipoManutencaoEnum tipoManutencaoConvenio, Integer numCooperativa) {
        super(numCooperativa);
        this.tipoManutencaoConvenio = tipoManutencaoConvenio;
    }

    /**
     * @return the tipoManutencaoConvenio
     */
    public TipoManutencaoEnum getTipoManutencaoConvenio() {
        return tipoManutencaoConvenio;
    }

    /**
     * @param tipoManutencaoConvenio the tipoManutencaoConvenio to set
     */
    public void setTipoManutencaoConvenio(TipoManutencaoEnum tipoManutencaoConvenio) {
        this.tipoManutencaoConvenio = tipoManutencaoConvenio;
    }

    /**
     * @return the dataFimConvenio
     */
    public Date getDataFimConvenio() {
        return dataFimConvenio;
    }

    /**
     * @param dataFimConvenio the dataFimConvenio to set
     */
    public void setDataFimConvenio(Date dataFimConvenio) {
        this.dataFimConvenio = dataFimConvenio;
    }

}
