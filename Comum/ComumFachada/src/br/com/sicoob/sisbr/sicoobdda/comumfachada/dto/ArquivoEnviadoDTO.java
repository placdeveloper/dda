package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogEnvioArquivoDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * ArquivoRecebidoDTO
 * 
 * @author samuell.ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoEnviadoDto")
public class ArquivoEnviadoDTO extends BancoobDTO {
	
    private Long idLogEnvioArquivodda;
    private String descNomeArquivoEnviado;
    private String codTipoMensagem;

    private DateTime dataHoraArquivo;
    private Date dataMovimento;
    private DateTime dataHoraEnvio;
    
    private DateTime dataArquivoInicio;
    private DateTime dataArquivoFim;
    
    private DateTime dataEnvioInicio;
    private DateTime dataEnvioFim;
    
    private LogEnvioArquivoDDAVO logEnvioArquivoDDA;
    private List<TipoMensagemDDA> listaTipoMensagem;

	/**
	 * @return o atributo idLogEnvioArquivodda
	 */
	public Long getIdLogEnvioArquivodda() {
		return idLogEnvioArquivodda;
	}

	/**
	 * Define o atributo idLogEnvioArquivodda
	 */
	public void setIdLogEnvioArquivodda(Long idLogEnvioArquivodda) {
		this.idLogEnvioArquivodda = idLogEnvioArquivodda;
	}

	/**
	 * @return o atributo descNomeArquivoEnviado
	 */
	public String getDescNomeArquivoEnviado() {
		return descNomeArquivoEnviado;
	}

	/**
	 * Define o atributo descNomeArquivoEnviado
	 */
	public void setDescNomeArquivoEnviado(String descNomeArquivoEnviado) {
		this.descNomeArquivoEnviado = descNomeArquivoEnviado;
	}

	/**
	 * @return o atributo codTipoMensagem
	 */
	public String getCodTipoMensagem() {
		return codTipoMensagem;
	}

	/**
	 * Define o atributo codTipoMensagem
	 */
	public void setCodTipoMensagem(String codTipoMensagem) {
		this.codTipoMensagem = codTipoMensagem;
	}

	/**
	 * @return o atributo dataHoraArquivo
	 */
	public DateTime getDataHoraArquivo() {
		return dataHoraArquivo;
	}

	/**
	 * Define o atributo dataHoraArquivo
	 */
	public void setDataHoraArquivo(DateTime dataHoraArquivo) {
		this.dataHoraArquivo = dataHoraArquivo;
	}

	/**
	 * @return o atributo dataMovimento
	 */
	public Date getDataMovimento() {
		return dataMovimento;
	}

	/**
	 * Define o atributo dataMovimento
	 */
	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	/**
	 * @return o atributo dataHoraEnvio
	 */
	public DateTime getDataHoraEnvio() {
		return dataHoraEnvio;
	}

	/**
	 * Define o atributo dataHoraEnvio
	 */
	public void setDataHoraEnvio(DateTime dataHoraEnvio) {
		this.dataHoraEnvio = dataHoraEnvio;
	}

	/**
	 * @return o atributo dataArquivoInicio
	 */
	public DateTime getDataArquivoInicio() {
		return dataArquivoInicio;
	}

	/**
	 * Define o atributo dataArquivoInicio
	 */
	public void setDataArquivoInicio(DateTime dataArquivoInicio) {
		this.dataArquivoInicio = dataArquivoInicio;
	}

	/**
	 * @return o atributo dataArquivoFim
	 */
	public DateTime getDataArquivoFim() {
		return dataArquivoFim;
	}

	/**
	 * Define o atributo dataArquivoFim
	 */
	public void setDataArquivoFim(DateTime dataArquivoFim) {
		this.dataArquivoFim = dataArquivoFim;
	}

	/**
	 * @return o atributo listaTipoMensagem
	 */
	public List<TipoMensagemDDA> getListaTipoMensagem() {
		return listaTipoMensagem;
	}

	/**
	 * Define o atributo listaTipoMensagem
	 */
	public void setListaTipoMensagem(List<TipoMensagemDDA> listaTipoMensagem) {
		this.listaTipoMensagem = listaTipoMensagem;
	}

	/**
	 * @return o atributo logEnvioArquivoDDA
	 */
	public LogEnvioArquivoDDAVO getLogEnvioArquivoDDA() {
		return logEnvioArquivoDDA;
	}

	/**
	 * Define o atributo logEnvioArquivoDDA
	 */
	public void setLogEnvioArquivoDDA(LogEnvioArquivoDDAVO logEnvioArquivoDDA) {
		this.logEnvioArquivoDDA = logEnvioArquivoDDA;
	}

	/**
	 * @return o atributo dataEnvioInicio
	 */
	public DateTime getDataEnvioInicio() {
		return dataEnvioInicio;
	}

	/**
	 * Define o atributo dataEnvioInicio
	 */
	public void setDataEnvioInicio(DateTime dataEnvioInicio) {
		this.dataEnvioInicio = dataEnvioInicio;
	}

	/**
	 * @return o atributo dataEnvioFim
	 */
	public DateTime getDataEnvioFim() {
		return dataEnvioFim;
	}

	/**
	 * Define o atributo dataEnvioFim
	 */
	public void setDataEnvioFim(DateTime dataEnvioFim) {
		this.dataEnvioFim = dataEnvioFim;
	}
    
}
