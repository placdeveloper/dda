/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ErrosAgrupadosDto.java
 * Data Criação:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ErroAgrupadoDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ErroAgrupadoDTO")
public class ErroAgrupadoDto extends BancoobDto{

    private static final long serialVersionUID = 5146650659010545894L;

    private String erroPrincipal;
    private String erroDependente;
    private Integer qtdErro;

    /**
     * 
     */
    public ErroAgrupadoDto() {
        super();
    }

    /**
     * @param erroPrincipal
     * @param erroDependente
     * @param qtdErro
     */
    public ErroAgrupadoDto(String erroPrincipal, String erroDependente, Integer qtdErro) {
        super();
        this.erroPrincipal = erroPrincipal;
        this.erroDependente = erroDependente;
        this.qtdErro = qtdErro;
    }

    /**
     * @return the erroPrincipal
     */
    public String getErroPrincipal() {
        return erroPrincipal;
    }

    /**
     * @param erroPrincipal the erroPrincipal to set
     */
    public void setErroPrincipal(String erroPrincipal) {
        this.erroPrincipal = erroPrincipal;
    }

    /**
     * @return the erroDependente
     */
    public String getErroDependente() {
        return erroDependente;
    }

    /**
     * @param erroDependente the erroDependente to set
     */
    public void setErroDependente(String erroDependente) {
        this.erroDependente = erroDependente;
    }

    /**
     * @return the qtdErro
     */
    public Integer getQtdErro() {
        return qtdErro;
    }

    /**
     * @param qtdErro the qtdErro to set
     */
    public void setQtdErro(Integer qtdErro) {
        this.qtdErro = qtdErro;
    }

}
