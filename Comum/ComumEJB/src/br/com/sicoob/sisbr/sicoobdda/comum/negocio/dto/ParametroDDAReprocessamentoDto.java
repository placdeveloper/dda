/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ErroBenficiarioCipDto.java
 * Data Cria��o:    Sep 11, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ParametroDDaReprocessamentoDto � respons�vel por
 * 
 * @author Daniel.Basso
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ParametroDDAReprocessamentoDTO")
public class ParametroDDAReprocessamentoDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7978916278120143356L;

    private Boolean emExecucao;
    private Long qtdMaximaErros;
    private Long qtdTotalErrosExecucao;
    private String qtdErrosEnviados;

    /**
     * @return o atributo emExecucao
     */
    public Boolean getEmExecucao() {
        return emExecucao;
    }

    /**
     * Define o atributo emExecucao
     */
    public void setEmExecucao(Boolean emExecucao) {
        this.emExecucao = emExecucao;
    }

    /**
     * @return o atributo qtdMaximaErros
     */
    public Long getQtdMaximaErros() {
        return qtdMaximaErros;
    }

    /**
     * Define o atributo qtdMaximaErros
     */
    public void setQtdMaximaErros(Long qtdMaximaErros) {
        this.qtdMaximaErros = qtdMaximaErros;
    }

    /**
     * @return o atributo qtdTotalErrosExecucao
     */
    public Long getQtdTotalErrosExecucao() {
        return qtdTotalErrosExecucao;
    }

    /**
     * Define o atributo qtdTotalErrosExecucao
     */
    public void setQtdTotalErrosExecucao(Long qtdTotalErrosExecucao) {
        this.qtdTotalErrosExecucao = qtdTotalErrosExecucao;
    }

    /**
     * @return o atributo qtdErrosEnviados
     */
    public String getQtdErrosEnviados() {
        return qtdErrosEnviados;
    }

    /**
     * Define o atributo qtdErrosEnviados
     */
    public void setQtdErrosEnviados(String qtdErrosEnviados) {
        this.qtdErrosEnviados = qtdErrosEnviados;
    }

}
