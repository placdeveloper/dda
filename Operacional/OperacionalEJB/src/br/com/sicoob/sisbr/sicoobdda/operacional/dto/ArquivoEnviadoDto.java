package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * ArquivoEnviadoDto
 * 
 * @author samuell.ramos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ArquivoEnviadoDTO")
public class ArquivoEnviadoDto extends BancoobDto {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long idLogEnvioArquivodda;
    private String descNomeArquivoEnviado;
    private String codTipoMensagem;

    private Date dataHoraArquivo;
    private Date dataMovimento;
    private Date dataHoraEnvio;
    
    private DateTimeDB dataArquivoInicio;
    private DateTimeDB dataArquivoFim;
    
    private DateTime dataEnvioInicio;
    private DateTime dataEnvioFim;
    
    private LogEnvioArquivoDDA logEnvioArquivoDDA;
    
    private List<TipoMensagemDDA> listaTipoMensagem;
    
    /**
     * CONSTRUTOR
     */
    public ArquivoEnviadoDto() {
        super();
    }
    /**
     * @param idLogEnvioArquivodda
     * @param descNomeArquivoenviado
     * @param codTipomensagem
     * @param dataHoraArquivo
     * @param dataMovimento
     * @param dataHoraEnvio
     */
    public ArquivoEnviadoDto(Long idLogEnvioArquivodda, String descNomeArquivoEnviado, String codTipoMensagem, Date dataHoraArquivo, Date dataMovimento, Date dataHoraEnvio) {
        this.idLogEnvioArquivodda = idLogEnvioArquivodda;
        this.descNomeArquivoEnviado = descNomeArquivoEnviado;
        this.codTipoMensagem = codTipoMensagem;
        this.dataHoraArquivo = dataHoraArquivo;
        this.dataMovimento = dataMovimento;
        this.dataHoraEnvio = dataHoraEnvio;
    }
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
     * @return o atributo dataHoraEnvio
     */
    public Date getDataHoraEnvio() {
        return dataHoraEnvio;
    }
    /**
     * Define o atributo dataHoraEnvio
     */
    public void setDataHoraEnvio(Date dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
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
    public LogEnvioArquivoDDA getLogEnvioArquivoDDA() {
        return logEnvioArquivoDDA;
    }
    /**
     * Define o atributo logEnvioArquivoDDA
     */
    public void setLogEnvioArquivoDDA(LogEnvioArquivoDDA logEnvioArquivoDDA) {
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
