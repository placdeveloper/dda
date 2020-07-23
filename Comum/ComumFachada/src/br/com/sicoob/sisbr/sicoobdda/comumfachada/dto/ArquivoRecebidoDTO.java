package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogDetRecebimentoArquivoDDAVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoProcessamentoArquivoVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoArquivoVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMensagemDDAVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * ArquivoRecebidoDTO
 * 
 * @author samuell.ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto")
public class ArquivoRecebidoDTO extends BancoobDTO {
	
	private Long idLogRecebimentoArqDDA;
	private Long idLogDetRecebimentoArqDDA;
	private String codTipoArquivo;
	private DateTime dataHoraArquivo;
	private String descNomeArquivoRecebido;
	private String descTipoArquivo;
	private String codTipoErroCip;
	private Long idLogEnvioArqDDA;
	private DateTime dataMovimento;
	private Integer qtdRegistros;
	private Short codSituacaoProcessamentoArquivo;

	private String codTipoMensagem;
	private String codTipoGradeHoraria;
	private String descSituacaoProcessamentoArquivo;

	private DateTime dataArquivoInicio;
	private DateTime dataArquivoFim;
	
	private Boolean bolProcessado;
	private Boolean bolTodos;
	
	private List<TipoArquivoVO> listaTipoArquivo;
	private List<TipoMensagemDDAVO> listaTipoMensagem;
	private List<SituacaoProcessamentoArquivoVO> listaSituacaoArquivo;
	private List<LogDetRecebimentoArquivoDDAVO> listaLogDetRecebimentoArquivoDDA;
	
	/**
	 * @return o atributo idLogRecebimentoArqDDA
	 */
	public Long getIdLogRecebimentoArqDDA() {
		return idLogRecebimentoArqDDA;
	}
	/**
	 * Define o atributo idLogRecebimentoArqDDA
	 */
	public void setIdLogRecebimentoArqDDA(Long idLogRecebimentoArqDDA) {
		this.idLogRecebimentoArqDDA = idLogRecebimentoArqDDA;
	}
	/**
	 * @return o atributo codTipoArquivo
	 */
	public String getCodTipoArquivo() {
		return codTipoArquivo;
	}
	/**
	 * Define o atributo codTipoArquivo
	 */
	public void setCodTipoArquivo(String codTipoArquivo) {
		this.codTipoArquivo = codTipoArquivo;
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
	 * @return o atributo descNomeArquivoRecebido
	 */
	public String getDescNomeArquivoRecebido() {
		return descNomeArquivoRecebido;
	}
	/**
	 * Define o atributo descNomeArquivoRecebido
	 */
	public void setDescNomeArquivoRecebido(String descNomeArquivoRecebido) {
		this.descNomeArquivoRecebido = descNomeArquivoRecebido;
	}
	/**
	 * @return o atributo descTipoArquivo
	 */
	public String getDescTipoArquivo() {
		return descTipoArquivo;
	}
	/**
	 * Define o atributo descTipoArquivo
	 */
	public void setDescTipoArquivo(String descTipoArquivo) {
		this.descTipoArquivo = descTipoArquivo;
	}
	/**
	 * @return o atributo codTipoErroCip
	 */
	public String getCodTipoErroCip() {
		return codTipoErroCip;
	}
	/**
	 * Define o atributo codTipoErroCip
	 */
	public void setCodTipoErroCip(String codTipoErroCip) {
		this.codTipoErroCip = codTipoErroCip;
	}
	/**
	 * @return o atributo idLogEnvioArqDDA
	 */
	public Long getIdLogEnvioArqDDA() {
		return idLogEnvioArqDDA;
	}
	/**
	 * Define o atributo idLogEnvioArqDDA
	 */
	public void setIdLogEnvioArqDDA(Long idLogEnvioArqDDA) {
		this.idLogEnvioArqDDA = idLogEnvioArqDDA;
	}
	/**
	 * @return o atributo dataMovimento
	 */
	public DateTime getDataMovimento() {
		return dataMovimento;
	}
	/**
	 * Define o atributo dataMovimento
	 */
	public void setDataMovimento(DateTime dataMovimento) {
		this.dataMovimento = dataMovimento;
	}
	/**
	 * @return o atributo qtdRegistros
	 */
	public Integer getQtdRegistros() {
		return qtdRegistros;
	}
	/**
	 * Define o atributo qtdRegistros
	 */
	public void setQtdRegistros(Integer qtdRegistros) {
		this.qtdRegistros = qtdRegistros;
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
	 * @return o atributo codTipoGradeHoraria
	 */
	public String getCodTipoGradeHoraria() {
		return codTipoGradeHoraria;
	}
	/**
	 * Define o atributo codTipoGradeHoraria
	 */
	public void setCodTipoGradeHoraria(String codTipoGradeHoraria) {
		this.codTipoGradeHoraria = codTipoGradeHoraria;
	}
	/**
	 * @return o atributo descSituacaoProcessamentoArquivo
	 */
	public String getDescSituacaoProcessamentoArquivo() {
		return descSituacaoProcessamentoArquivo;
	}
	/**
	 * Define o atributo descSituacaoProcessamentoArquivo
	 */
	public void setDescSituacaoProcessamentoArquivo(
			String descSituacaoProcessamentoArquivo) {
		this.descSituacaoProcessamentoArquivo = descSituacaoProcessamentoArquivo;
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
	 * @return o atributo listaTipoArquivo
	 */
	public List<TipoArquivoVO> getListaTipoArquivo() {
		return listaTipoArquivo;
	}
	/**
	 * Define o atributo listaTipoArquivo
	 */
	public void setListaTipoArquivo(List<TipoArquivoVO> listaTipoArquivo) {
		this.listaTipoArquivo = listaTipoArquivo;
	}
	/**
	 * @return o atributo listaTipoMensagem
	 */
	public List<TipoMensagemDDAVO> getListaTipoMensagem() {
		return listaTipoMensagem;
	}
	/**
	 * Define o atributo listaTipoMensagem
	 */
	public void setListaTipoMensagem(List<TipoMensagemDDAVO> listaTipoMensagem) {
		this.listaTipoMensagem = listaTipoMensagem;
	}
	/**
	 * @return o atributo listaSituacaoArquivo
	 */
	public List<SituacaoProcessamentoArquivoVO> getListaSituacaoArquivo() {
		return listaSituacaoArquivo;
	}
	/**
	 * Define o atributo listaSituacaoArquivo
	 */
	public void setListaSituacaoArquivo(
			List<SituacaoProcessamentoArquivoVO> listaSituacaoArquivo) {
		this.listaSituacaoArquivo = listaSituacaoArquivo;
	}
	/**
	 * @return o atributo codSituacaoProcessamentoArquivo
	 */
	public Short getCodSituacaoProcessamentoArquivo() {
		return codSituacaoProcessamentoArquivo;
	}
	/**
	 * Define o atributo codSituacaoProcessamentoArquivo
	 */
	public void setCodSituacaoProcessamentoArquivo(
			Short codSituacaoProcessamentoArquivo) {
		this.codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
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
     * @return o atributo bolProcessado
     */
    public Boolean getBolProcessado() {
        return bolProcessado;
    }
    /**
     * Define o atributo bolProcessado
     */
    public void setBolProcessado(Boolean bolProcessado) {
        this.bolProcessado = bolProcessado;
    }
    /**
     * @return o atributo bolTodos
     */
    public Boolean getBolTodos() {
        return bolTodos;
    }
    /**
     * Define o atributo bolTodos
     */
    public void setBolTodos(Boolean bolTodos) {
        this.bolTodos = bolTodos;
    }
	/**
	 * @return the idLogDetRecebimentoArqDDA
	 */
	public Long getIdLogDetRecebimentoArqDDA() {
		return idLogDetRecebimentoArqDDA;
	}
	/**
	 * @param idLogDetRecebimentoArqDDA the idLogDetRecebimentoArqDDA to set
	 */
	public void setIdLogDetRecebimentoArqDDA(Long idLogDetRecebimentoArqDDA) {
		this.idLogDetRecebimentoArqDDA = idLogDetRecebimentoArqDDA;
	}
	
}
