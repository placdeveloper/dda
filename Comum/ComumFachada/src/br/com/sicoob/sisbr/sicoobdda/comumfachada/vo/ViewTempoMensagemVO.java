package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ViewTempoMensagemVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.ViewTempoMensagem")
public class ViewTempoMensagemVO extends BancoobVO {

    private DateTimeDB dataHoraMensagem;
    private String dataHoraLabel;
    private Boolean bolMensagemConsulta;
    private Integer qtdMensagem;
    private Integer tempoMedio;
    private Integer tempoMedioSSPB;

    /**
     * @return the dataHoraMensagem
     */
    public DateTimeDB getDataHoraMensagem() {
        return dataHoraMensagem;
    }

    /**
     * @param dataHoraMensagem the dataHoraMensagem to set
     */
    public void setDataHoraMensagem(DateTimeDB dataHoraMensagem) {
        this.dataHoraMensagem = dataHoraMensagem;
    }

    /**
     * @return the dataHoraLabel
     */
    public String getDataHoraLabel() {
        return dataHoraLabel;
    }

    /**
     * @param dataHoraLabel the dataHoraLabel to set
     */
    public void setDataHoraLabel(String dataHoraLabel) {
        this.dataHoraLabel = dataHoraLabel;
    }

    /**
     * @return the bolMensagemConsulta
     */
    public Boolean getBolMensagemConsulta() {
        return bolMensagemConsulta;
    }

    /**
     * @param bolMensagemConsulta the bolMensagemConsulta to set
     */
    public void setBolMensagemConsulta(Boolean bolMensagemConsulta) {
        this.bolMensagemConsulta = bolMensagemConsulta;
    }

    /**
     * @return the qtdMensagem
     */
    public Integer getQtdMensagem() {
        return qtdMensagem;
    }

    /**
     * @param qtdMensagem the qtdMensagem to set
     */
    public void setQtdMensagem(Integer qtdMensagem) {
        this.qtdMensagem = qtdMensagem;
    }

    /**
     * @return the tempoMedio
     */
    public Integer getTempoMedio() {
        return tempoMedio;
    }

    /**
     * @param tempoMedio the tempoMedio to set
     */
    public void setTempoMedio(Integer tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    /**
     * @return o atributo tempoMedioSSPB
     */
    public Integer getTempoMedioSSPB() {
        return tempoMedioSSPB;
    }

    /**
     * Define o atributo tempoMedioSSPB
     */
    public void setTempoMedioSSPB(Integer tempoMedioSSPB) {
        this.tempoMedioSSPB = tempoMedioSSPB;
    }

}
