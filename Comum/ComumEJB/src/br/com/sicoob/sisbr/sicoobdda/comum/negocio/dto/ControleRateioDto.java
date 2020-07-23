package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.EventoTarifavelDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;

/**
 * ControleRateioDto é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ControleRateioDTO")
public class ControleRateioDto extends BancoobDto{

    private static final long serialVersionUID = 5355398262270265213L;

    private Long idRateio;
    private DateTimeDB dataInclusao;
    private String descSituacao;
    private Long codSituacaoRateio;

    private BigDecimal desvioPadrao;

    private List<EventoTarifavelDDA> listaEventoTarifavelDDA;

    private List<EventoRateioDto> listaEventoRateio;

    /**
     * @return o atributo idRateio
     */
    public Long getIdRateio() {
        return idRateio;
    }

    /**
     * Define o atributo idRateio
     */
    public void setIdRateio(Long idRateio) {
        this.idRateio = idRateio;
    }

    /**
     * @return o atributo dataInclusao
     */
    public DateTimeDB getDataInclusao() {
        return dataInclusao;
    }

    /**
     * Define o atributo dataInclusao
     */
    public void setDataInclusao(DateTimeDB dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    /**
     * @return o atributo descSituacao
     */
    public String getDescSituacao() {
        return descSituacao;
    }

    /**
     * Define o atributo descSituacao
     */
    public void setDescSituacao(String descSituacao) {
        this.descSituacao = descSituacao;
    }

    /**
     * @return o atributo desvioPadrao
     */
    public BigDecimal getDesvioPadrao() {
        return desvioPadrao;
    }

    /**
     * Define o atributo desvioPadrao
     */
    public void setDesvioPadrao(BigDecimal desvioPadrao) {
        this.desvioPadrao = desvioPadrao;
    }

    /**
     * @return o atributo listaEventoTarifavelDDA
     */
    public List<EventoTarifavelDDA> getListaEventoTarifavelDDA() {
        return listaEventoTarifavelDDA;
    }

    /**
     * Define o atributo listaEventoTarifavelDDA
     */
    public void setListaEventoTarifavelDDA(List<EventoTarifavelDDA> listaEventoTarifavelDDA) {
        this.listaEventoTarifavelDDA = listaEventoTarifavelDDA;
    }

    /**
     * @return o atributo listaEventoRateio
     */
    public List<EventoRateioDto> getListaEventoRateio() {
        return listaEventoRateio;
    }

    /**
     * Define o atributo listaEventoRateio
     */
    public void setListaEventoRateio(List<EventoRateioDto> listaEventoRateio) {
        this.listaEventoRateio = listaEventoRateio;
    }

    /**
     * @return o atributo codSituacaoRateio
     */
    public Long getCodSituacaoRateio() {
        return codSituacaoRateio;
    }

    /**
     * Define o atributo codSituacaoRateio
     */
    public void setCodSituacaoRateio(Long codSituacaoRateio) {
        this.codSituacaoRateio = codSituacaoRateio;
    }

    /**
     * Método responsável por formatar a diferença da quantidade e valor total
     * 
     * @return
     */
    public String getDifQtdValorTotal() {
        int diferenca = 0;
        BigDecimal valor = BigDecimal.ZERO;

        if (!ObjectUtil.isEmpty(listaEventoRateio)) {
            for (EventoRateioDto dto : listaEventoRateio) {
                diferenca += dto.getDiferenca();
                valor = valor.add(dto.getValorApuradoSicoob().subtract(dto.getValorCIP()));
            }
        }

        return FormatadorUtil.formatarValor(diferenca, 0) + " / " + (FormatadorUtil.formatarValorReal(valor.doubleValue()));
    }

}
