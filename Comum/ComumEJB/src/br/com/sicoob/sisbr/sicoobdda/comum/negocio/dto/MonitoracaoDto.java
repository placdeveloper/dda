/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MonitoracaoDto.java
 * Data Criação:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MonitoracaoDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MonitoracaoDTO")
public class MonitoracaoDto extends BancoobDto {

    private static final long serialVersionUID = -1580994678637715922L;

    private MonitoracaoDDA0110Dto monitoracaoDDA0110;
    private MonitoracaoDemaisMensagensDto monitoracaoDemaisMensagens;
    private MonitoracaoArqRemessaDto monitoracaoArqRemessa;
    private MonitoracaoArqVarreduraDto monitoracaoArqVarredura;

    /**
     * 
     */
    public MonitoracaoDto() {
        super();
    }

    /**
     * @param monitoracaoDDA0110
     * @param monitoracaoDemaisMensagens
     * @param monitoracaoArqRemessa
     * @param monitoracaoArqVarredura
     */
    public MonitoracaoDto(MonitoracaoDDA0110Dto monitoracaoDDA0110, MonitoracaoDemaisMensagensDto monitoracaoDemaisMensagens, MonitoracaoArqRemessaDto monitoracaoArqRemessa,
            MonitoracaoArqVarreduraDto monitoracaoArqVarredura) {
        super();
        this.monitoracaoDDA0110 = monitoracaoDDA0110;
        this.monitoracaoDemaisMensagens = monitoracaoDemaisMensagens;
        this.monitoracaoArqRemessa = monitoracaoArqRemessa;
        this.monitoracaoArqVarredura = monitoracaoArqVarredura;
    }

    /**
     * @return o atributo monitoracaoDDA0110
     */
    public MonitoracaoDDA0110Dto getMonitoracaoDDA0110() {
        return monitoracaoDDA0110;
    }

    /**
     * Define o atributo monitoracaoDDA0110
     */
    public void setMonitoracaoDDA0110(MonitoracaoDDA0110Dto monitoracaoDDA0110) {
        this.monitoracaoDDA0110 = monitoracaoDDA0110;
    }

    /**
     * @return o atributo monitoracaoDemaisMensagens
     */
    public MonitoracaoDemaisMensagensDto getMonitoracaoDemaisMensagens() {
        return monitoracaoDemaisMensagens;
    }

    /**
     * Define o atributo monitoracaoDemaisMensagens
     */
    public void setMonitoracaoDemaisMensagens(MonitoracaoDemaisMensagensDto monitoracaoDemaisMensagens) {
        this.monitoracaoDemaisMensagens = monitoracaoDemaisMensagens;
    }

    /**
     * @return o atributo monitoracaoArqRemessa
     */
    public MonitoracaoArqRemessaDto getMonitoracaoArqRemessa() {
        return monitoracaoArqRemessa;
    }

    /**
     * Define o atributo monitoracaoArqRemessa
     */
    public void setMonitoracaoArqRemessa(MonitoracaoArqRemessaDto monitoracaoArqRemessa) {
        this.monitoracaoArqRemessa = monitoracaoArqRemessa;
    }

    /**
     * @return o atributo monitoracaoArqVarredura
     */
    public MonitoracaoArqVarreduraDto getMonitoracaoArqVarredura() {
        return monitoracaoArqVarredura;
    }

    /**
     * Define o atributo monitoracaoArqVarredura
     */
    public void setMonitoracaoArqVarredura(MonitoracaoArqVarreduraDto monitoracaoArqVarredura) {
        this.monitoracaoArqVarredura = monitoracaoArqVarredura;
    }

}
