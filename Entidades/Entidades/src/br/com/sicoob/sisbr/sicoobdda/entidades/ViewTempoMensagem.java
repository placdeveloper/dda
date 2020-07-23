/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         ViewTempoMensagem.java
 * Data Criação:    Sep 27, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ViewTempoMensagem é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "VIW_TEMPOMENSAGEM", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ViewTempoMensagemVO")
public class ViewTempoMensagem extends SicoobDDAEntidade implements Comparable<ViewTempoMensagem> {

    private static final long serialVersionUID = -582202819539500025L;

    @Id
    private DateTimeDB dataHoraMensagem;

    private String dataHoraLabel;

    private Boolean bolMensagemConsulta;

    @Column(name = "QTD_MSG")
    private Integer qtdMensagem;

    @Column(name = "TEMPO_MEDIO")
    private Integer tempoMedio;

    @Column(name = "TEMPO_MEDIO_SPB")
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

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataHoraMensagem == null) ? 0 : dataHoraMensagem.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ViewTempoMensagem other = (ViewTempoMensagem) obj;
        if (dataHoraMensagem == null) {
            if (other.dataHoraMensagem != null) {
                return false;
            }
        } else if (!dataHoraMensagem.equals(other.dataHoraMensagem)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(ViewTempoMensagem viewTempoMensagem) {
        return this.dataHoraMensagem.compareTo(viewTempoMensagem.dataHoraMensagem);
    }
}
