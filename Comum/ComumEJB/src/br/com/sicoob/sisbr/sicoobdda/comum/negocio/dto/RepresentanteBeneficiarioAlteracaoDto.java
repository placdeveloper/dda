/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.dto
 * Arquivo:         RepresentanteClienteBeneficiarioDto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;

/**
 * RepresentanteBeneficiarioAlteracaoDto
 * 
 * @author Rafael.Silva
 */
public class RepresentanteBeneficiarioAlteracaoDto extends RepresentanteBeneficiarioDto {

    private static final long serialVersionUID = 1L;

    private TipoManutencaoEnum tipoManutencaoRepresentante;

    /**
     * @return the tipoManutencaoRepresentante
     */
    public TipoManutencaoEnum getTipoManutencaoRepresentante() {
        return tipoManutencaoRepresentante;
    }

    /**
     * @param tipoManutencaoRepresentante the tipoManutencaoRepresentante to set
     */
    public void setTipoManutencaoRepresentante(TipoManutencaoEnum tipoManutencaoRepresentante) {
        this.tipoManutencaoRepresentante = tipoManutencaoRepresentante;
    }

}
