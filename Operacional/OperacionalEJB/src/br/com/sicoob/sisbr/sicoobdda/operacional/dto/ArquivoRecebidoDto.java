package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ArquivoRecebidoDto
 * 
 * @author samuell.ramos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ArquivoRecebidoDTO")
public class ArquivoRecebidoDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -750138598399199766L;
    

    private Long idLogRecebimentoArqDDA;
    private Long idLogDetRecebimentoArqDDA;
    private String codTipoArquivo;
    private Date dataHoraArquivo;
    private String descTipoArquivo;
    private String descNomeArquivoRecebido;
    private String descSituacaoProcessamentoArquivo;
    private String codTipoErroCip;
    private Long idLogEnvioArqDDA;
    private Date dataMovimento;
    private Integer qtdRegistros;

    private String codTipoMensagem;
    private String codTipoGradeHoraria;
    private Short codSituacaoProcessamentoArquivo;

    private DateTimeDB dataArquivoInicio;
    private DateTimeDB dataArquivoFim;
    
    private Boolean bolProcessado;
    private Boolean bolTodos;

    private List<TipoArquivo> listaTipoArquivo;
    private List<TipoMensagemDDA> listaTipoMensagem;
    private List<SituacaoProcessamentoArquivo> listaSituacaoArquivo;
    private List<LogDetRecebimentoArquivoDDA> listaLogDetRecebimentoArquivoDDA;

    /**
	 * 
	 */
    public ArquivoRecebidoDto() {
    }

    /**
     * @param idLogRecebimentoArqDDA
     * @param descNomeArquivoRecebido
     * @param codSituacaoProcessamentoArquivo
     */
    public ArquivoRecebidoDto(Long idLogRecebimentoArqDDA, String descNomeArquivoRecebido, Short codSituacaoProcessamentoArquivo) {
        super();
        this.idLogRecebimentoArqDDA = idLogRecebimentoArqDDA;
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
        this.codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
    }

    /**
     * @param id
     * @param descTipoArquivo
     * @param descNomeArquivoRecebido
     * @param dataHoraArquivo
     * @param codTipoErroCip
     * @param idLogEnvioArqDDA
     * @param codTipoMensagem
     * @param descSituacaoProcessamentoArquivo
     * @param dataMovimento
     * @param qtdRegistros
     */
    public ArquivoRecebidoDto(Long id, String descTipoArquivo, String descNomeArquivoRecebido, Date dataHoraArquivo, String codTipoErroCip, Long idLogEnvioArqDDA, String codTipoMensagem,
            String descSituacaoProcessamentoArquivo, Date dataMovimento, Integer qtdRegistros) {
        this.idLogRecebimentoArqDDA = id;
        this.dataHoraArquivo = dataHoraArquivo;
        this.descTipoArquivo = descTipoArquivo;
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
        this.descSituacaoProcessamentoArquivo = descSituacaoProcessamentoArquivo;
        this.codTipoErroCip = codTipoErroCip;
        this.idLogEnvioArqDDA = idLogEnvioArqDDA;
        this.dataMovimento = dataMovimento;
        this.qtdRegistros = qtdRegistros;
        this.codTipoMensagem = codTipoMensagem;
    }

    /**
     * @param idLogRecebimentoArqDDA
     * @param codTipoArquivo
     * @param dataHoraArquivo
     * @param descTipoArquivo
     * @param descNomeArquivoRecebido
     * @param descSituacaoProcessamentoArquivo
     * @param codTipoErroCip
     * @param idLogEnvioArqDDA
     * @param dataMovimento
     * @param qtdRegistros
     * @param codTipoMensagem
     * @param codSituacaoProcessamentoArquivo
     */
    public ArquivoRecebidoDto(Long idLogRecebimentoArqDDA, String codTipoArquivo, Date dataHoraArquivo, String descTipoArquivo, String descNomeArquivoRecebido,
            String descSituacaoProcessamentoArquivo, String codTipoErroCip, Long idLogEnvioArqDDA, Date dataMovimento, Integer qtdRegistros, String codTipoMensagem,
            Short codSituacaoProcessamentoArquivo) {
        super();
        this.idLogRecebimentoArqDDA = idLogRecebimentoArqDDA;
        this.codTipoArquivo = codTipoArquivo;
        this.dataHoraArquivo = dataHoraArquivo;
        this.descTipoArquivo = descTipoArquivo;
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
        this.descSituacaoProcessamentoArquivo = descSituacaoProcessamentoArquivo;
        this.codTipoErroCip = codTipoErroCip;
        this.idLogEnvioArqDDA = idLogEnvioArqDDA;
        this.dataMovimento = dataMovimento;
        this.qtdRegistros = qtdRegistros;
        this.codTipoMensagem = codTipoMensagem;
        this.codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
    }

    /**
     * @param listaTipoArquivo
     * @param listaTipoMensagem
     * @param listaSituacaoArquivo
     */
    public ArquivoRecebidoDto(List<TipoArquivo> listaTipoArquivo, List<TipoMensagemDDA> listaTipoMensagem, List<SituacaoProcessamentoArquivo> listaSituacaoArquivo) {
        super();
        this.listaTipoArquivo = listaTipoArquivo;
        this.listaTipoMensagem = listaTipoMensagem;
        this.listaSituacaoArquivo = listaSituacaoArquivo;
    }

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
     * @return o atributo dataHoraArquivo
     */
    public Date getDataHoraArquivo() {
        return dataHoraArquivo;
    }

    /**
     * Define o atributo dataHoraArquivo
     */
    public void setDataHoraArquivo(Date dataHoraArquivo) {
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
     * @return o atributo codSituacaoProcessamentoArquivo
     */
    public Short getCodSituacaoProcessamentoArquivo() {
        return codSituacaoProcessamentoArquivo;
    }

    /**
     * Define o atributo codSituacaoProcessamentoArquivo
     */
    public void setCodSituacaoProcessamentoArquivo(Short codSituacaoProcessamentoArquivo) {
        this.codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
    }

    /**
     * @return o atributo dataArquivoInicio
     */
    public DateTimeDB getDataArquivoInicio() {
        return dataArquivoInicio;
    }

    /**
     * Define o atributo dataArquivoInicio
     */
    public void setDataArquivoInicio(DateTimeDB dataArquivoInicio) {
        this.dataArquivoInicio = dataArquivoInicio;
    }

    /**
     * @return o atributo dataArquivoFim
     */
    public DateTimeDB getDataArquivoFim() {
        return dataArquivoFim;
    }

    /**
     * Define o atributo dataArquivoFim
     */
    public void setDataArquivoFim(DateTimeDB dataArquivoFim) {
        this.dataArquivoFim = dataArquivoFim;
    }

    /**
     * @return o atributo listaTipoArquivo
     */
    public List<TipoArquivo> getListaTipoArquivo() {
        return listaTipoArquivo;
    }

    /**
     * Define o atributo listaTipoArquivo
     */
    public void setListaTipoArquivo(List<TipoArquivo> listaTipoArquivo) {
        this.listaTipoArquivo = listaTipoArquivo;
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
     * @return o atributo listaSituacaoArquivo
     */
    public List<SituacaoProcessamentoArquivo> getListaSituacaoArquivo() {
        return listaSituacaoArquivo;
    }

    /**
     * Define o atributo listaSituacaoArquivo
     */
    public void setListaSituacaoArquivo(List<SituacaoProcessamentoArquivo> listaSituacaoArquivo) {
        this.listaSituacaoArquivo = listaSituacaoArquivo;
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
    public void setDescSituacaoProcessamentoArquivo(String descSituacaoProcessamentoArquivo) {
        this.descSituacaoProcessamentoArquivo = descSituacaoProcessamentoArquivo;
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
     * @return o atributo listaLogDetRecebimentoArquivoDDA
     */
    public List<LogDetRecebimentoArquivoDDA> getListaLogDetRecebimentoArquivoDDA() {
        return listaLogDetRecebimentoArquivoDDA;
    }

    /**
     * Define o atributo listaLogDetRecebimentoArquivoDDA
     */
    public void setListaLogDetRecebimentoArquivoDDA(List<LogDetRecebimentoArquivoDDA> listaLogDetRecebimentoArquivoDDA) {
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
     * @return o atributo idLogDetRecebimentoArqDDA
     */
    public Long getIdLogDetRecebimentoArqDDA() {
        return idLogDetRecebimentoArqDDA;
    }

    /**
     * Define o atributo idLogDetRecebimentoArqDDA
     */
    public void setIdLogDetRecebimentoArqDDA(Long idLogDetRecebimentoArqDDA) {
        this.idLogDetRecebimentoArqDDA = idLogDetRecebimentoArqDDA;
    }
    
}
