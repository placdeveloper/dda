/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ParametroCadastroCargaDto.java
 * Data Criação:    Sep 29, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ParametroCadastroCargaDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ParametroCadastroCargaDTO")
public class ParametroCadastroCargaDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -1970615015439097361L;

    private Boolean cargaEmExecucao;
    private Boolean processaADDA506;
    private Boolean requisitaArquivoCarga;

    /**
     * @return the cargaEmExecucao
     */
    public Boolean getCargaEmExecucao() {
        return cargaEmExecucao;
    }

    /**
     * @param cargaEmExecucao the cargaEmExecucao to set
     */
    public void setCargaEmExecucao(Boolean cargaEmExecucao) {
        this.cargaEmExecucao = cargaEmExecucao;
    }

    /**
     * @return the processaADDA506
     */
    public Boolean getProcessaADDA506() {
        return processaADDA506;
    }

    /**
     * @param processaADDA506 the processaADDA506 to set
     */
    public void setProcessaADDA506(Boolean processaADDA506) {
        this.processaADDA506 = processaADDA506;
    }

    /**
     * @return the requisitaArquivoCarga
     */
    public Boolean getRequisitaArquivoCarga() {
        return requisitaArquivoCarga;
    }

    /**
     * @param requisitaArquivoCarga the requisitaArquivoCarga to set
     */
    public void setRequisitaArquivoCarga(Boolean requisitaArquivoCarga) {
        this.requisitaArquivoCarga = requisitaArquivoCarga;
    }

}
