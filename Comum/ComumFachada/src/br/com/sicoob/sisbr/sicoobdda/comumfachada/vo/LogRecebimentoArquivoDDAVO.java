package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * LogRecebimentoArquivoDDAVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA")
public class LogRecebimentoArquivoDDAVO extends BancoobVO {

    private Long id;
    private TipoArquivoVO tipoArquivo;
    private String descNomeArquivoRecebido;
    private DateTime dataHoraArquivo;
    private DateTime dataMovimento;
    private TipoErroCipVO tipoErroCip;
    private LogEnvioArquivoDDAVO logEnvioArquivoDDA;
    private SituacaoProcessamentoArquivoVO situacaoProcessamentoArquivo;
    private TipoMensagemDDAVO tipoMensagemDDA;
    private List<LogDetRecebimentoArquivoDDAVO> listaLogDetRecebimentoArquivoDDA;
    private Integer qtdRegistroArquivo;
    
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
     * @return the tipoArquivo
     */
    public TipoArquivoVO getTipoArquivo() {
        return tipoArquivo;
    }

    /**
     * @param tipoArquivo the tipoArquivo to set
     */
    public void setTipoArquivo(TipoArquivoVO tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    /**
     * @return the descNomeArquivoRecebido
     */
    public String getDescNomeArquivoRecebido() {
        return descNomeArquivoRecebido;
    }

    /**
     * @param descNomeArquivoRecebido the descNomeArquivoRecebido to set
     */
    public void setDescNomeArquivoRecebido(String descNomeArquivoRecebido) {
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
    }

    /**
     * @return the dataHoraArquivo
     */
    public DateTime getDataHoraArquivo() {
        return dataHoraArquivo;
    }

    /**
     * @param dataHoraArquivo the dataHoraArquivo to set
     */
    public void setDataHoraArquivo(DateTime dataHoraArquivo) {
        this.dataHoraArquivo = dataHoraArquivo;
    }

    /**
     * @return the dataMovimento
     */
    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the tipoErroCip
     */
    public TipoErroCipVO getTipoErroCip() {
        return tipoErroCip;
    }

    /**
     * @param tipoErroCip the tipoErroCip to set
     */
    public void setTipoErroCip(TipoErroCipVO tipoErroCip) {
        this.tipoErroCip = tipoErroCip;
    }

    /**
     * @return the logEnvioArquivoDDA
     */
    public LogEnvioArquivoDDAVO getLogEnvioArquivoDDA() {
        return logEnvioArquivoDDA;
    }

    /**
     * @param logEnvioArquivoDDA the logEnvioArquivoDDA to set
     */
    public void setLogEnvioArquivoDDA(LogEnvioArquivoDDAVO logEnvioArquivoDDA) {
        this.logEnvioArquivoDDA = logEnvioArquivoDDA;
    }

	/**
	 * @return o atributo situacaoProcessamentoArquivo
	 */
	public SituacaoProcessamentoArquivoVO getSituacaoProcessamentoArquivo() {
		return situacaoProcessamentoArquivo;
	}

	/**
	 * Define o atributo situacaoProcessamentoArquivo
	 */
	public void setSituacaoProcessamentoArquivo(
			SituacaoProcessamentoArquivoVO situacaoProcessamentoArquivo) {
		this.situacaoProcessamentoArquivo = situacaoProcessamentoArquivo;
	}

	/**
	 * @return o atributo tipoMensagemDDA
	 */
	public TipoMensagemDDAVO getTipoMensagemDDA() {
		return tipoMensagemDDA;
	}

	/**
	 * Define o atributo tipoMensagemDDA
	 */
	public void setTipoMensagemDDA(TipoMensagemDDAVO tipoMensagemDDA) {
		this.tipoMensagemDDA = tipoMensagemDDA;
	}

	/**
	 * @return o atributo listaLogDetRecebimentoArquivoDDA
	 */
	public List<LogDetRecebimentoArquivoDDAVO> getListaLogDetRecebimentoArquivoDDA() {
		return listaLogDetRecebimentoArquivoDDA;
	}

	/**
	 * Define o atributo listaLogDetRecebimentoArquivoDDA
	 */
	public void setListaLogDetRecebimentoArquivoDDA(
			List<LogDetRecebimentoArquivoDDAVO> listaLogDetRecebimentoArquivoDDA) {
		this.listaLogDetRecebimentoArquivoDDA = listaLogDetRecebimentoArquivoDDA;
	}

	/**
	 * @return o atributo qtdRegistroArquivo
	 */
	public Integer getQtdRegistroArquivo() {
		return qtdRegistroArquivo;
	}

	/**
	 * Define o atributo qtdRegistroArquivo
	 */
	public void setQtdRegistroArquivo(Integer qtdRegistroArquivo) {
		this.qtdRegistroArquivo = qtdRegistroArquivo;
	}


}
