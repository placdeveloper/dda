package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ConsultaEventoRateioDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaEventoRateioDTO")
public class ConsultaEventoRateioDto extends BancoobDto {

    private static final long serialVersionUID = 802789206109164951L;

    private Integer codEventoTarifavel;
    private String descEventoTarifavel;
    private DateTimeDB dataInicial;
    private DateTimeDB dataFinal;

    /**
     * @return o atributo codEventoTarifavel
     */
    public Integer getCodEventoTarifavel() {
        return codEventoTarifavel;
    }

    /**
     * Define o atributo codEventoTarifavel
     */
    public void setCodEventoTarifavel(Integer codEventoTarifavel) {
        this.codEventoTarifavel = codEventoTarifavel;
    }

    /**
     * @return o atributo descEventoTarifavel
     */
    public String getDescEventoTarifavel() {
        return descEventoTarifavel;
    }

    /**
     * Define o atributo descEventoTarifavel
     */
    public void setDescEventoTarifavel(String descEventoTarifavel) {
        this.descEventoTarifavel = descEventoTarifavel;
    }

    /**
     * @return o atributo dataInicial
     */
    public DateTimeDB getDataInicial() {
        return dataInicial;
    }

    /**
     * Define o atributo dataInicial
     */
    public void setDataInicial(DateTimeDB dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return o atributo dataFinal
     */
    public DateTimeDB getDataFinal() {
        return dataFinal;
    }

    /**
     * Define o atributo dataFinal
     */
    public void setDataFinal(DateTimeDB dataFinal) {
        this.dataFinal = dataFinal;
    }

}
