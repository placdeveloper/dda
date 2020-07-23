/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         RangeMensagensStepDto.java
 * Data Criação:    Jun 21, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * RangeMensagensStepDto
 * 
 * @author rafael.silva
 */
public class RangeMensagensStepDto extends BancoobDto {
    private static final long serialVersionUID = -8689153845667693798L;

    private Long idMsgInicial;

    private Long idMsgFinal;

    /**
     * @param idMsgInicial
     * @param idMsgFinal
     */
    public RangeMensagensStepDto(long idMsgInicial, long idMsgFinal) {
        this.idMsgInicial = idMsgInicial;
        this.idMsgFinal = idMsgFinal;
    }

    /**
     * @return the idMsgInicial
     */
    public Long getIdMsgInicial() {
        return idMsgInicial;
    }

    /**
     * @param idMsgInicial the idMsgInicial to set
     */
    public void setIdMsgInicial(Long idMsgInicial) {
        this.idMsgInicial = idMsgInicial;
    }

    /**
     * @return the idMsgFinal
     */
    public Long getIdMsgFinal() {
        return idMsgFinal;
    }

    /**
     * @param idMsgFinal the idMsgFinal to set
     */
    public void setIdMsgFinal(Long idMsgFinal) {
        this.idMsgFinal = idMsgFinal;
    }

}
