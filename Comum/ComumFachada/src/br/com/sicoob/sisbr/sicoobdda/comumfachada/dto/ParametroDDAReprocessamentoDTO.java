/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         ErroBenficiarioCipDTO.java
 * Data Criação:    Sep 11, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ParametroDDAReprocessamentoDTO é responsável por
 * 
 * @author Daniel.Basso
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParametroDDAReprocessamentoDto")
public class ParametroDDAReprocessamentoDTO extends BancoobDTO {

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
