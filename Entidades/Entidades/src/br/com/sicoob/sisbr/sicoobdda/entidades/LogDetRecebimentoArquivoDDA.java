/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         LogDetRecebimentoArquivoDDA.java
 * Data Criação:    Sep 19, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * LogDetRecebimentoArquivoDDA é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "LOGDETRECEBIMENTOOARQUIVODDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogDetRecebimentoArquivoDDAVO")
public class LogDetRecebimentoArquivoDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = 4554983453279848360L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLOGDETRECEBIMENTOOARQUIVODDA", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDLOGRECEBIMENTOOARQUIVODDA", nullable = false)
    private LogRecebimentoArquivoDDA logRecebimentoArquivoDDA;

    private String descXMLMensagemRecebida;

    private Boolean bolProcessado;

    private String descErro;

    private Long numOrdemProcessamento;

    /**
     * 
     */
    public LogDetRecebimentoArquivoDDA() {

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the logRecebimentoArquivoDDA
     */
    public LogRecebimentoArquivoDDA getLogRecebimentoArquivoDDA() {
        return logRecebimentoArquivoDDA;
    }

    /**
     * @param logRecebimentoArquivoDDA the logRecebimentoArquivoDDA to set
     */
    public void setLogRecebimentoArquivoDDA(LogRecebimentoArquivoDDA logRecebimentoArquivoDDA) {
        this.logRecebimentoArquivoDDA = logRecebimentoArquivoDDA;
    }

    /**
     * @return the descXMLMensagemRecebida
     */
    public String getDescXMLMensagemRecebida() {
        return descXMLMensagemRecebida;
    }

    /**
     * @param descXMLMensagemRecebida the descXMLMensagemRecebida to set
     */
    public void setDescXMLMensagemRecebida(String descXMLMensagemRecebida) {
        this.descXMLMensagemRecebida = descXMLMensagemRecebida;
    }

    /**
     * @return the bolProcessado
     */
    public Boolean getBolProcessado() {
        return bolProcessado;
    }

    /**
     * @param bolProcessado the bolProcessado to set
     */
    public void setBolProcessado(Boolean bolProcessado) {
        this.bolProcessado = bolProcessado;
    }

    /**
     * @return the descErro
     */
    public String getDescErro() {
        return descErro;
    }

    /**
     * @param descErro the descErro to set
     */
    public void setDescErro(String descErro) {
        this.descErro = descErro;
    }

    /**
     * @return the numOrdemProcessamento
     */
    public Long getNumOrdemProcessamento() {
        return numOrdemProcessamento;
    }

    /**
     * @param numOrdemProcessamento the numOrdemProcessamento to set
     */
    public void setNumOrdemProcessamento(long numOrdemProcessamento) {
        this.numOrdemProcessamento = numOrdemProcessamento;
    }

}
