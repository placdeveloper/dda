package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto")
public class ConsultaEventoRateioDTO extends BancoobDTO {

    private Integer codEventoTarifavel;
    private String descEventoTarifavel;
    private DateTime dataInicial;
    private DateTime dataFinal;

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
    public DateTime getDataInicial() {
        return dataInicial;
    }

    /**
     * Define o atributo dataInicial
     */
    public void setDataInicial(DateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return o atributo dataFinal
     */
    public DateTime getDataFinal() {
        return dataFinal;
    }

    /**
     * Define o atributo dataFinal
     */
    public void setDataFinal(DateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

}
