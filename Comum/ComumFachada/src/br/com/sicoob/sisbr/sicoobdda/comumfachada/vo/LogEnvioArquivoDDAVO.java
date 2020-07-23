package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * LogEnvioArquivoDDAVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA")
public class LogEnvioArquivoDDAVO extends BancoobVO {

    private Long id;
    private String descNomeArquivoEnviado;
    private DateTime dataHoraArquivo;
    private DateTime dataMovimento;
    private DateTime dataHoraEnvio;
    private TipoMensagemDDAVO codTipoMensagem;
    private TipoMensagemDDAVO tipoMensagem;
    private List<LogDetalheEnvioArquivoDDAVO> listaLogDetalheEnvioArquivoDDA;
    
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
     * @return the descNomeArquivoEnviado
     */
    public String getDescNomeArquivoEnviado() {
        return descNomeArquivoEnviado;
    }
    /**
     * @param descNomeArquivoEnviado the descNomeArquivoEnviado to set
     */
    public void setDescNomeArquivoEnviado(String descNomeArquivoEnviado) {
        this.descNomeArquivoEnviado = descNomeArquivoEnviado;
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
     * @return the dataHoraEnvio
     */
    public DateTime getDataHoraEnvio() {
        return dataHoraEnvio;
    }
    /**
     * @param dataHoraEnvio the dataHoraEnvio to set
     */
    public void setDataHoraEnvio(DateTime dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }
    /**
     * @return the codTipoMensagem
     */
    public TipoMensagemDDAVO getCodTipoMensagem() {
        return codTipoMensagem;
    }
    /**
     * @param codTipoMensagem the codTipoMensagem to set
     */
    public void setCodTipoMensagem(TipoMensagemDDAVO codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }
	/**
	 * @return o atributo listaLogDetalheEnvioArquivoDDA
	 */
	public List<LogDetalheEnvioArquivoDDAVO> getListaLogDetalheEnvioArquivoDDA() {
		return listaLogDetalheEnvioArquivoDDA;
	}
	/**
	 * Define o atributo listaLogDetalheEnvioArquivoDDA
	 */
	public void setListaLogDetalheEnvioArquivoDDA(
			List<LogDetalheEnvioArquivoDDAVO> listaLogDetalheEnvioArquivoDDA) {
		this.listaLogDetalheEnvioArquivoDDA = listaLogDetalheEnvioArquivoDDA;
	}
	/**
	 * @return o atributo tipoMensagem
	 */
	public TipoMensagemDDAVO getTipoMensagem() {
		return tipoMensagem;
	}
	/**
	 * Define o atributo tipoMensagem
	 */
	public void setTipoMensagem(TipoMensagemDDAVO tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}
}
