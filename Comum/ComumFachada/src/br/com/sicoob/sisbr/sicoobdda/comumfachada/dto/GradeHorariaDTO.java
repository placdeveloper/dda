package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * GradeHorariaDTO
 * 
 * @author samuell.ramos
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto")
public class GradeHorariaDTO extends BancoobDTO {

    private Long idGradeHoraria;
    private Date dataReferencia;

    private DateTime dataHoraAbertura;
    private DateTime dataAbertura;
    private String horaAbertura;
    private String minutoAbertura;

    private DateTime dataHoraFechamento;
    private DateTime dataFechamento;
    private String horaFechamento;
    private String minutoFechamento;

    private TipoGradeHorariaDTO tipoGradeHorariaDto;
    // Atributos do Filtro
    private Boolean gradesEmUso;
    private String codTipoGradeHoraria;

    private List<TipoGradeHorariaDTO> listaTipoGradeHoraria;
    private List<GradeHorariaDTO> listaGradeHoraria;

    private Date dataHoraAberturaTemp;
    private Date dataHoraFechamentoTemp;
    
    //Necessario no Flex
    private Long orderByDateTime;

    /**
     * @return o atributo dataHoraAbertura
     */
    public DateTime getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    /**
     * Define o atributo dataHoraAbertura
     */
    public void setDataHoraAbertura(DateTime dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    /**
     * @return o atributo dataAbertura
     */
    public DateTime getDataAbertura() {
        return dataAbertura;
    }

    /**
     * Define o atributo dataAbertura
     */
    public void setDataAbertura(DateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * @return o atributo dataHoraFechamento
     */
    public DateTime getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    /**
     * Define o atributo dataHoraFechamento
     */
    public void setDataHoraFechamento(DateTime dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
    }

    /**
     * @return o atributo dataFechamento
     */
    public DateTime getDataFechamento() {
        return dataFechamento;
    }

    /**
     * Define o atributo dataFechamento
     */
    public void setDataFechamento(DateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    /**
     * @return o atributo gradesEmUso
     */
    public Boolean getGradesEmUso() {
        return gradesEmUso;
    }

    /**
     * Define o atributo gradesEmUso
     */
    public void setGradesEmUso(Boolean gradesEmUso) {
        this.gradesEmUso = gradesEmUso;
    }

    /**
     * @return o atributo idGradeHoraria
     */
    public Long getIdGradeHoraria() {
        return idGradeHoraria;
    }

    /**
     * Define o atributo idGradeHoraria
     */
    public void setIdGradeHoraria(Long idGradeHoraria) {
        this.idGradeHoraria = idGradeHoraria;
    }

    /**
     * @return o atributo dataReferencia
     */
    public Date getDataReferencia() {
        return dataReferencia;
    }

    /**
     * Define o atributo dataReferencia
     */
    public void setDataReferencia(Date dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    /**
     * @return o atributo tipoGradeHorariaDto
     */
    public TipoGradeHorariaDTO getTipoGradeHorariaDto() {
        return tipoGradeHorariaDto;
    }

    /**
     * Define o atributo tipoGradeHorariaDto
     */
    public void setTipoGradeHorariaDto(TipoGradeHorariaDTO tipoGradeHorariaDto) {
        this.tipoGradeHorariaDto = tipoGradeHorariaDto;
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
     * @return o atributo listaTipoGradeHoraria
     */
    public List<TipoGradeHorariaDTO> getListaTipoGradeHoraria() {
        return listaTipoGradeHoraria;
    }

    /**
     * Define o atributo listaTipoGradeHoraria
     */
    public void setListaTipoGradeHoraria(List<TipoGradeHorariaDTO> listaTipoGradeHoraria) {
        this.listaTipoGradeHoraria = listaTipoGradeHoraria;
    }

    /**
     * @return o atributo listaGradeHoraria
     */
    public List<GradeHorariaDTO> getListaGradeHoraria() {
        return listaGradeHoraria;
    }

    /**
     * Define o atributo listaGradeHoraria
     */
    public void setListaGradeHoraria(List<GradeHorariaDTO> listaGradeHoraria) {
        this.listaGradeHoraria = listaGradeHoraria;
    }

    /**
     * @return o atributo horaAbertura
     */
    public String getHoraAbertura() {
        return horaAbertura;
    }

    /**
     * Define o atributo horaAbertura
     */
    public void setHoraAbertura(String horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    /**
     * @return o atributo minutoAbertura
     */
    public String getMinutoAbertura() {
        return minutoAbertura;
    }

    /**
     * Define o atributo minutoAbertura
     */
    public void setMinutoAbertura(String minutoAbertura) {
        this.minutoAbertura = minutoAbertura;
    }

    /**
     * @return o atributo horaFechamento
     */
    public String getHoraFechamento() {
        return horaFechamento;
    }

    /**
     * Define o atributo horaFechamento
     */
    public void setHoraFechamento(String horaFechamento) {
        this.horaFechamento = horaFechamento;
    }

    /**
     * @return o atributo minutoFechamento
     */
    public String getMinutoFechamento() {
        return minutoFechamento;
    }

    /**
     * Define o atributo minutoFechamento
     */
    public void setMinutoFechamento(String minutoFechamento) {
        this.minutoFechamento = minutoFechamento;
    }

    /**
     * @return o atributo dataHoraAberturaTemp
     */
    public Date getDataHoraAberturaTemp() {
        return dataHoraAberturaTemp;
    }

    /**
     * Define o atributo dataHoraAberturaTemp
     */
    public void setDataHoraAberturaTemp(Date dataHoraAberturaTemp) {
        this.dataHoraAberturaTemp = dataHoraAberturaTemp;
    }

    /**
     * @return o atributo dataHoraFechamentoTemp
     */
    public Date getDataHoraFechamentoTemp() {
        return dataHoraFechamentoTemp;
    }

    /**
     * Define o atributo dataHoraFechamentoTemp
     */
    public void setDataHoraFechamentoTemp(Date dataHoraFechamentoTemp) {
        this.dataHoraFechamentoTemp = dataHoraFechamentoTemp;
    }

	/**
	 * @return the orderByDateTime
	 */
	public Long getOrderByDateTime() {
		return orderByDateTime;
	}

	/**
	 * @param orderByDateTime the orderByDateTime to set
	 */
	public void setOrderByDateTime(Long orderByDateTime) {
		this.orderByDateTime = orderByDateTime;
	}

}
