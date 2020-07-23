/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MonitoracaoParametroDto.java
 * Data Criação:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * MonitoracaoParametroDto é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MonitoracaoParametroDto extends BancoobDto {

    private static final long serialVersionUID = 1808397906444913123L;

    private Integer parametroMQ;
    private Integer parametroArqRemessa;
    private Integer parametroVarredura;

    /**
     * 
     */
    public MonitoracaoParametroDto() {
        super();
    }

    /**
     * @param parametroMQ
     * @param parametroArqRemessa
     * @param parametroVarredura
     */
    public MonitoracaoParametroDto(Integer parametroMQ, Integer parametroArqRemessa, Integer parametroVarredura) {
        super();
        this.parametroMQ = parametroMQ;
        this.parametroArqRemessa = parametroArqRemessa;
        this.parametroVarredura = parametroVarredura;
    }

    /**
     * @return o atributo parametroMQ
     */
    public Integer getParametroMQ() {
        return parametroMQ;
    }

    /**
     * Define o atributo parametroMQ
     */
    public void setParametroMQ(Integer parametroMQ) {
        this.parametroMQ = parametroMQ;
    }

    /**
     * @return o atributo parametroArqRemessa
     */
    public Integer getParametroArqRemessa() {
        return parametroArqRemessa;
    }

    /**
     * Define o atributo parametroArqRemessa
     */
    public void setParametroArqRemessa(Integer parametroArqRemessa) {
        this.parametroArqRemessa = parametroArqRemessa;
    }

    /**
     * @return o atributo parametroVarredura
     */
    public Integer getParametroVarredura() {
        return parametroVarredura;
    }

    /**
     * Define o atributo parametroVarredura
     */
    public void setParametroVarredura(Integer parametroVarredura) {
        this.parametroVarredura = parametroVarredura;
    }

}
