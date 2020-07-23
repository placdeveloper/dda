/**
 * Projeto:         sdda-operacional-ejb
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         GradeHorariaOrigemDto.java
 * Data Criação:    Sep 28, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * GradeHorariaOrigemDto é responsável por
 * 
 * @author jesliel.rocha
 */
public class GradeHorariaOrigemDto extends GradeHorariaDto {

    /**
     * 
     */
    private static final long serialVersionUID = -546526409144442575L;

    private String codTipoGradeHorariaOrigem;
    private DateTimeDB dataUltimaGrade;
    private DateTimeDB dataHoraAberturaOrigem;
    private DateTimeDB dataHoraFechamentoOrigem;

    /**
     * 
     */
    public GradeHorariaOrigemDto() {

    }

    /**
     * 
     * @param codTipoGradeHoraria
     * @param dataHoraAbertura
     * @param dataHoraFechamento
     * @param codTipoGradeHorariaOrigem
     * @param dataHoraAberturaOrigem
     * @param dataHoraFechamentoOrigem
     */
    public GradeHorariaOrigemDto(Date dataUltimaGrade, Date dataReferencia, String codTipoGradeHoraria, Date dataHoraAbertura, Date dataHoraFechamento,
            String codTipoGradeHorariaOrigem, Date dataHoraAberturaOrigem, Date dataHoraFechamentoOrigem) {
        super();
        this.codTipoGradeHorariaOrigem = codTipoGradeHorariaOrigem;
        setCodTipoGradeHoraria(codTipoGradeHoraria);
        setDataReferencia(dataReferencia);
        if (dataHoraAbertura != null) {
            setDataHoraAbertura(new DateTimeDB(dataHoraAbertura.getTime()));
        }
        if (dataHoraFechamento != null) {
            setDataHoraFechamento(new DateTimeDB(dataHoraFechamento.getTime()));
        }
        if (dataUltimaGrade != null) {
            this.dataUltimaGrade = new DateTimeDB(dataUltimaGrade.getTime());
        }
        if (dataHoraAberturaOrigem != null) {
            this.dataHoraAberturaOrigem = new DateTimeDB(dataHoraAberturaOrigem.getTime());
        }
        if (dataHoraFechamentoOrigem != null) {
            this.dataHoraFechamentoOrigem = new DateTimeDB(dataHoraFechamentoOrigem.getTime());
        }
    }

    /**
     * @return o atributo codTipoGradeHorariaOrigem
     */
    public String getCodTipoGradeHorariaOrigem() {
        return codTipoGradeHorariaOrigem;
    }

    /**
     * Define o atributo codTipoGradeHorariaOrigem
     */
    public void setCodTipoGradeHorariaOrigem(String codTipoGradeHorariaOrigem) {
        this.codTipoGradeHorariaOrigem = codTipoGradeHorariaOrigem;
    }

    /**
     * @return o atributo dataHoraAberturaOrigem
     */
    public DateTimeDB getDataHoraAberturaOrigem() {
        return dataHoraAberturaOrigem;
    }

    /**
     * Define o atributo dataHoraAberturaOrigem
     */
    public void setDataHoraAberturaOrigem(DateTimeDB dataHoraAberturaOrigem) {
        this.dataHoraAberturaOrigem = dataHoraAberturaOrigem;
    }

    /**
     * @return o atributo dataHoraFechamentoOrigem
     */
    public DateTimeDB getDataHoraFechamentoOrigem() {
        return dataHoraFechamentoOrigem;
    }

    /**
     * Define o atributo dataHoraFechamentoOrigem
     */
    public void setDataHoraFechamentoOrigem(DateTimeDB dataHoraFechamentoOrigem) {
        this.dataHoraFechamentoOrigem = dataHoraFechamentoOrigem;
    }

    /**
     * @return o atributo dataUltimaGrade
     */
    public DateTimeDB getDataUltimaGrade() {
        return dataUltimaGrade;
    }

    /**
     * Define o atributo dataUltimaGrade
     */
    public void setDataUltimaGrade(DateTimeDB dataUltimaGrade) {
        this.dataUltimaGrade = dataUltimaGrade;
    }

}
