package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         TermoPagadorDto.java
 * Data Criação:    Mar 03, 2017
 */
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author Samuell.Ramos
 * 
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.TermoPagadorDTO")
public class TermoPagadorDto extends BancoobDto {

    /**
     * Serializador
     */
    private static final long serialVersionUID = 7982322525859400179L;

    private Short idTermoPagador;
    private Short codTipoTermoPagador;
    private DateTimeDB dataInicioVigencia;
    private DateTimeDB dataFimVigencia;
    private Boolean bolFormatoHtml;
    private String descConteudoTermoPagador;
    private String descTipoTermoPagador;

    private String descOuvidoria;
    private String descTerminal;

    /**
	 * 
	 */
    public TermoPagadorDto() {
        super();
    }

    /**
     * @param idTermoPagador
     * @param codTipoTermoPagador
     * @param dataInicioVigencia
     * @param dataFimVigencia
     * @param bolFormatoHtml
     * @param descConteudoTermoPagador
     * @param descTipoTermoPagador
     */
    public TermoPagadorDto(Short idTermoPagador, Short codTipoTermoPagador, Date dataInicioVigencia, Date dataFimVigencia, Boolean bolFormatoHtml, String descConteudoTermoPagador,
            String descTipoTermoPagador, String descOuvidoria) {
        this.idTermoPagador = idTermoPagador;
        this.codTipoTermoPagador = codTipoTermoPagador;
        this.dataInicioVigencia = new DateTimeDB(dataInicioVigencia.getTime());
        this.dataFimVigencia = dataFimVigencia != null ? new DateTimeDB(dataFimVigencia.getTime()) : null;
        this.bolFormatoHtml = bolFormatoHtml;
        this.descConteudoTermoPagador = descConteudoTermoPagador;
        this.descTipoTermoPagador = descTipoTermoPagador;
        this.descOuvidoria = descOuvidoria;
    }

    /**
     * @return the idTermoPagador
     */
    public Short getIdTermoPagador() {
        return idTermoPagador;
    }

    /**
     * @param idTermoPagador the idTermoPagador to set
     */
    public void setIdTermoPagador(Short idTermoPagador) {
        this.idTermoPagador = idTermoPagador;
    }

    /**
     * @return the codTipoTermoPagador
     */
    public Short getCodTipoTermoPagador() {
        return codTipoTermoPagador;
    }

    /**
     * @param codTipoTermoPagador the codTipoTermoPagador to set
     */
    public void setCodTipoTermoPagador(Short codTipoTermoPagador) {
        this.codTipoTermoPagador = codTipoTermoPagador;
    }

    /**
     * @return the dataInicioVigencia
     */
    public DateTimeDB getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    /**
     * @param dataInicioVigencia the dataInicioVigencia to set
     */
    public void setDataInicioVigencia(DateTimeDB dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    /**
     * @return the dataFimVigencia
     */
    public DateTimeDB getDataFimVigencia() {
        return dataFimVigencia;
    }

    /**
     * @param dataFimVigencia the dataFimVigencia to set
     */
    public void setDataFimVigencia(DateTimeDB dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    /**
     * @return the bolFormatoHtml
     */
    public Boolean getBolFormatoHtml() {
        return bolFormatoHtml;
    }

    /**
     * @param bolFormatoHtml the bolFormatoHtml to set
     */
    public void setBolFormatoHtml(Boolean bolFormatoHtml) {
        this.bolFormatoHtml = bolFormatoHtml;
    }

    /**
     * @return the descConteudoTermoPagador
     */
    public String getDescConteudoTermoPagador() {
        return descConteudoTermoPagador;
    }

    /**
     * @param descConteudoTermoPagador the descConteudoTermoPagador to set
     */
    public void setDescConteudoTermoPagador(String descConteudoTermoPagador) {
        this.descConteudoTermoPagador = descConteudoTermoPagador;
    }

    /**
     * @return the descTipoTermoPagador
     */
    public String getDescTipoTermoPagador() {
        return descTipoTermoPagador;
    }

    /**
     * @param descTipoTermoPagador the descTipoTermoPagador to set
     */
    public void setDescTipoTermoPagador(String descTipoTermoPagador) {
        this.descTipoTermoPagador = descTipoTermoPagador;
    }

    /**
     * @return o atributo descOuvidoria
     */
    public String getDescOuvidoria() {
        return descOuvidoria;
    }

    /**
     * Define o atributo descOuvidoria
     */
    public void setDescOuvidoria(String descOuvidoria) {
        this.descOuvidoria = descOuvidoria;
    }

    /**
     * @return o atributo descTerminal
     */
    public String getDescTerminal() {
        return descTerminal;
    }

    /**
     * Define o atributo descTerminal
     */
    public void setDescTerminal(String descTerminal) {
        this.descTerminal = descTerminal;
    }
}
