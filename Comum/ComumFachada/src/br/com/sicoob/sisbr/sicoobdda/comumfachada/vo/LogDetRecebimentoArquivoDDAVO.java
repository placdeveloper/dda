package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * LogDetRecebimentoArquivoDDAVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA")
public class LogDetRecebimentoArquivoDDAVO extends BancoobVO {

    private Long id;
    private LogRecebimentoArquivoDDAVO logRecebimentoArquivoDDA;
    private String descXMLMensagemRecebida;
    private Boolean bolProcessado;
    private String descErro;
    private Long numOrdemProcessamento;

    /**
     * @return the id
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
    public LogRecebimentoArquivoDDAVO getLogRecebimentoArquivoDDA() {
        return logRecebimentoArquivoDDA;
    }

    /**
     * @param logRecebimentoArquivoDDA the logRecebimentoArquivoDDA to set
     */
    public void setLogRecebimentoArquivoDDA(LogRecebimentoArquivoDDAVO logRecebimentoArquivoDDA) {
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
	 * @return o atributo numOrdemProcessamento
	 */
	public Long getNumOrdemProcessamento() {
		return numOrdemProcessamento;
	}

	/**
	 * Define o atributo numOrdemProcessamento
	 */
	public void setNumOrdemProcessamento(Long numOrdemProcessamento) {
		this.numOrdemProcessamento = numOrdemProcessamento;
	}

}
