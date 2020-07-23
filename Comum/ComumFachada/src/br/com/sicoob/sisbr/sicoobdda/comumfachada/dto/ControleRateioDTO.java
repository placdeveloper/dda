package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;

import java.io.Serializable;
import java.math.BigDecimal;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoTarifavelDDAVO;
import java.util.List;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ControleRateioDto")
public class ControleRateioDTO extends BancoobDTO implements Serializable{
	private static final long serialVersionUID = -8890564497007556518L;
	private Long idRateio;
    private DateTime dataInclusao;
    private String descSituacao;
    private Long codSituacaoRateio;
    private BigDecimal desvioPadrao;
    private List<EventoTarifavelDDAVO> listaEventoTarifavelDDA;
    private List<EventoRateioDTO> listaEventoRateio;

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
    public DateTime getDataInclusao() {
        return dataInclusao;
    }

    /**
     * Define o atributo dataInclusao
     */
    public void setDataInclusao(DateTime dataInclusao) {
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
    public List<EventoTarifavelDDAVO> getListaEventoTarifavelDDA() {
        return listaEventoTarifavelDDA;
    }

    /**
     * Define o atributo listaEventoTarifavelDDA
     */
    public void setListaEventoTarifavelDDA(List<EventoTarifavelDDAVO> listaEventoTarifavelDDA) {
        this.listaEventoTarifavelDDA = listaEventoTarifavelDDA;
    }

    /**
     * @return o atributo listaEventoRateio
     */
    public List<EventoRateioDTO> getListaEventoRateio() {
        return listaEventoRateio;
    }

    /**
     * Define o atributo listaEventoRateio
     */
    public void setListaEventoRateio(List<EventoRateioDTO> listaEventoRateio) {
        this.listaEventoRateio = listaEventoRateio;
    }

}
