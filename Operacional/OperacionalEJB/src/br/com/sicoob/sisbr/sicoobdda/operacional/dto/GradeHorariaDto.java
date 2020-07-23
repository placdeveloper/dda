package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * GradeHorariaDTO
 * 
 * @author samuell.ramos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.GradeHorariaDTO")
public class GradeHorariaDto extends BancoobDto {

    private static final long serialVersionUID = 1L;

    private Long idGradeHoraria;
    private Date dataReferencia;

    private DateTimeDB dataHoraAbertura;
    private DateTimeDB dataAbertura;
    private String horaAbertura;
    private String minutoAbertura;

    private DateTimeDB dataHoraFechamento;
    private DateTimeDB dataFechamento;
    private String horaFechamento;
    private String minutoFechamento;

    private TipoGradeHorariaDto tipoGradeHorariaDto;
    // Atributos do Filtro
    private String codTipoGradeHoraria;
    private Boolean gradesEmUso;

    private List<TipoGradeHorariaDto> listaTipoGradeHoraria;
    private List<GradeHorariaDto> listaGradeHoraria;

    private Date dataHoraAberturaTemp;
    private Date dataHoraFechamentoTemp;
    
    //Necessario no Flex
    private Long orderByDateTime;

    public GradeHorariaDto() {
        super();
    }

    public GradeHorariaDto(Long id, Date dataReferencia, Date dataHoraAbertura, Date dataHoraFechamento, String codTipoGradeHoraria, String descTipoGradeHoraria) {
        this.idGradeHoraria = id;
        this.dataReferencia = DateUtil.getDateTimeDB(dataReferencia);
        this.dataHoraAbertura = DateUtil.getDateTimeDB(dataHoraAbertura);
        this.dataHoraFechamento = DateUtil.getDateTimeDB(dataHoraFechamento);
        this.tipoGradeHorariaDto = new TipoGradeHorariaDto();
        this.tipoGradeHorariaDto.setCodTipoGradeHoraria(codTipoGradeHoraria);
        this.tipoGradeHorariaDto.setDescTipoGradeHoraria(descTipoGradeHoraria);
    }

    public GradeHorariaDto(List<TipoGradeHorariaDto> listaTipoGradeHorariaDto, List<GradeHorariaDto> listaGradeHorariaDto, Date dataReferencia) {
        this.listaGradeHoraria = listaGradeHorariaDto;
        this.listaTipoGradeHoraria = listaTipoGradeHorariaDto;
        this.dataReferencia = DateUtil.getDateTimeDB(dataReferencia);
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
     * @return o atributo dataHoraAbertura
     */
    public DateTimeDB getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    /**
     * Define o atributo dataHoraAbertura
     */
    public void setDataHoraAbertura(DateTimeDB dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    /**
     * @return o atributo dataAbertura
     */
    public DateTimeDB getDataAbertura() {
        return dataAbertura;
    }

    /**
     * Define o atributo dataAbertura
     */
    public void setDataAbertura(DateTimeDB dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * @return o atributo dataHoraFechamento
     */
    public DateTimeDB getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    /**
     * Define o atributo dataHoraFechamento
     */
    public void setDataHoraFechamento(DateTimeDB dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
    }

    /**
     * @return o atributo dataFechamento
     */
    public DateTimeDB getDataFechamento() {
        return dataFechamento;
    }

    /**
     * Define o atributo dataFechamento
     */
    public void setDataFechamento(DateTimeDB dataFechamento) {
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
     * @return o atributo tipoGradeHorariaDto
     */
    public TipoGradeHorariaDto getTipoGradeHorariaDto() {
        return tipoGradeHorariaDto;
    }

    /**
     * Define o atributo tipoGradeHorariaDto
     */
    public void setTipoGradeHorariaDto(TipoGradeHorariaDto tipoGradeHorariaDto) {
        this.tipoGradeHorariaDto = tipoGradeHorariaDto;
    }

    /**
     * @return o atributo listaTipoGradeHoraria
     */
    public List<TipoGradeHorariaDto> getListaTipoGradeHoraria() {
        return listaTipoGradeHoraria;
    }

    /**
     * Define o atributo listaTipoGradeHoraria
     */
    public void setListaTipoGradeHoraria(List<TipoGradeHorariaDto> listaTipoGradeHoraria) {
        this.listaTipoGradeHoraria = listaTipoGradeHoraria;
    }

    /**
     * @return o atributo listaGradeHoraria
     */
    public List<GradeHorariaDto> getListaGradeHoraria() {
        return listaGradeHoraria;
    }

    /**
     * Define o atributo listaGradeHoraria
     */
    public void setListaGradeHoraria(List<GradeHorariaDto> listaGradeHoraria) {
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
