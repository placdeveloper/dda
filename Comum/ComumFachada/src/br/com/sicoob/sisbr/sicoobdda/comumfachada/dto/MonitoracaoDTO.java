package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDto")
public class MonitoracaoDTO extends BancoobDTO {

    private MonitoracaoDDA0110DTO monitoracaoDDA0110;
    private MonitoracaoDemaisMensagensDTO monitoracaoDemaisMensagens;
    private MonitoracaoArqRemessaDTO monitoracaoArqRemessa;
    private MonitoracaoArqVarreduraDTO monitoracaoArqVarredura;

    /**
     * @return o atributo monitoracaoDDA0110
     */
    public MonitoracaoDDA0110DTO getMonitoracaoDDA0110() {
        return monitoracaoDDA0110;
    }

    /**
     * Define o atributo monitoracaoDDA0110
     */
    public void setMonitoracaoDDA0110(MonitoracaoDDA0110DTO monitoracaoDDA0110) {
        this.monitoracaoDDA0110 = monitoracaoDDA0110;
    }

    /**
     * @return o atributo monitoracaoDemaisMensagens
     */
    public MonitoracaoDemaisMensagensDTO getMonitoracaoDemaisMensagens() {
        return monitoracaoDemaisMensagens;
    }

    /**
     * Define o atributo monitoracaoDemaisMensagens
     */
    public void setMonitoracaoDemaisMensagens(MonitoracaoDemaisMensagensDTO monitoracaoDemaisMensagens) {
        this.monitoracaoDemaisMensagens = monitoracaoDemaisMensagens;
    }

    /**
     * @return o atributo monitoracaoArqRemessa
     */
    public MonitoracaoArqRemessaDTO getMonitoracaoArqRemessa() {
        return monitoracaoArqRemessa;
    }

    /**
     * Define o atributo monitoracaoArqRemessa
     */
    public void setMonitoracaoArqRemessa(MonitoracaoArqRemessaDTO monitoracaoArqRemessa) {
        this.monitoracaoArqRemessa = monitoracaoArqRemessa;
    }

    /**
     * @return o atributo monitoracaoArqVarredura
     */
    public MonitoracaoArqVarreduraDTO getMonitoracaoArqVarredura() {
        return monitoracaoArqVarredura;
    }

    /**
     * Define o atributo monitoracaoArqVarredura
     */
    public void setMonitoracaoArqVarredura(MonitoracaoArqVarreduraDTO monitoracaoArqVarredura) {
        this.monitoracaoArqVarredura = monitoracaoArqVarredura;
    }

}
