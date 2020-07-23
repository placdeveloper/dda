package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;
import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.EventoConsolidadoDDA")
public class EventoConsolidadoDDAVO extends BancoobVO {

    private Long id;
    private EventoTarifavelDDAVO eventoTarifavelDDA;
    private DateTime dataMovimento;
    private DateTime dataHoraConsolidacao;
    private Integer qtdApuradoSicoob;
    private Integer qtdApuradoCIP;
    private BigDecimal valorTarifaCIP;
    private BigDecimal valorTarifaBancoob;
    private RateioDDAVO rateioDDA;
    private String idUsuarioInclusaoRateio;
    private Integer idInstituicaoUsuarioInclusaoRateio;
    private DateTime dataHoraInclusaoRateio;
    private List<EventoConsolidadoDDADetalheVO> listaEventoConsolidadoDDADetalhe;

    /**
     * @return o atributo id
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo eventoTarifavelDDA
     */
    public EventoTarifavelDDAVO getEventoTarifavelDDA() {
        return eventoTarifavelDDA;
    }

    /**
     * Define o atributo eventoTarifavelDDA
     */
    public void setEventoTarifavelDDA(EventoTarifavelDDAVO eventoTarifavelDDA) {
        this.eventoTarifavelDDA = eventoTarifavelDDA;
    }

    /**
	 * @return the dataMovimento
	 */
	public DateTime getDataMovimento() {
		return dataMovimento;
	}

	/**
	 * @param dataMovimento the dataMovimento to set
	 */
	public void setDataMovimento(DateTime dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	/**
     * @return o atributo qtdApuradoSicoob
     */
    public Integer getQtdApuradoSicoob() {
        return qtdApuradoSicoob;
    }

    /**
     * Define o atributo qtdApuradoSicoob
     */
    public void setQtdApuradoSicoob(Integer qtdApuradoSicoob) {
        this.qtdApuradoSicoob = qtdApuradoSicoob;
    }

    /**
     * @return o atributo qtdApuradoCIP
     */
    public Integer getQtdApuradoCIP() {
        return qtdApuradoCIP;
    }

    /**
     * Define o atributo qtdApuradoCIP
     */
    public void setQtdApuradoCIP(Integer qtdApuradoCIP) {
        this.qtdApuradoCIP = qtdApuradoCIP;
    }

    /**
     * @return o atributo valorTarifaCIP
     */
    public BigDecimal getValorTarifaCIP() {
        return valorTarifaCIP;
    }

    /**
     * Define o atributo valorTarifaCIP
     */
    public void setValorTarifaCIP(BigDecimal valorTarifaCIP) {
        this.valorTarifaCIP = valorTarifaCIP;
    }

    /**
     * @return o atributo valorTarifaBancoob
     */
    public BigDecimal getValorTarifaBancoob() {
        return valorTarifaBancoob;
    }

    /**
     * Define o atributo valorTarifaBancoob
     */
    public void setValorTarifaBancoob(BigDecimal valorTarifaBancoob) {
        this.valorTarifaBancoob = valorTarifaBancoob;
    }

    /**
     * @return o atributo rateioDDA
     */
    public RateioDDAVO getRateioDDA() {
        return rateioDDA;
    }

    /**
     * Define o atributo rateioDDA
     */
    public void setRateioDDA(RateioDDAVO rateioDDA) {
        this.rateioDDA = rateioDDA;
    }

    /**
     * @return o atributo idUsuarioInclusaoRateio
     */
    public String getIdUsuarioInclusaoRateio() {
        return idUsuarioInclusaoRateio;
    }

    /**
     * Define o atributo idUsuarioInclusaoRateio
     */
    public void setIdUsuarioInclusaoRateio(String idUsuarioInclusaoRateio) {
        this.idUsuarioInclusaoRateio = idUsuarioInclusaoRateio;
    }

    /**
     * @return o atributo idInstituicaoUsuarioInclusaoRateio
     */
    public Integer getIdInstituicaoUsuarioInclusaoRateio() {
        return idInstituicaoUsuarioInclusaoRateio;
    }

    /**
     * Define o atributo idInstituicaoUsuarioInclusaoRateio
     */
    public void setIdInstituicaoUsuarioInclusaoRateio(Integer idInstituicaoUsuarioInclusaoRateio) {
        this.idInstituicaoUsuarioInclusaoRateio = idInstituicaoUsuarioInclusaoRateio;
    }

    /**
     * @return o atributo dataHoraInclusaoRateio
     */
    public DateTime getDataHoraInclusaoRateio() {
        return dataHoraInclusaoRateio;
    }

    /**
     * Define o atributo dataHoraInclusaoRateio
     */
    public void setDataHoraInclusaoRateio(DateTime dataHoraInclusaoRateio) {
        this.dataHoraInclusaoRateio = dataHoraInclusaoRateio;
    }

    /**
     * @return o atributo listaEventoConsolidadoDDADetalhe
     */
    public List<EventoConsolidadoDDADetalheVO> getListaEventoConsolidadoDDADetalhe() {
        return listaEventoConsolidadoDDADetalhe;
    }

    /**
     * Define o atributo listaEventoConsolidadoDDADetalhe
     */
    public void setListaEventoConsolidadoDDADetalhe(List<EventoConsolidadoDDADetalheVO> listaEventoConsolidadoDDADetalhe) {
        this.listaEventoConsolidadoDDADetalhe = listaEventoConsolidadoDDADetalhe;
    }

	/**
	 * @return the dataHoraConsolidacao
	 */
	public DateTime getDataHoraConsolidacao() {
		return dataHoraConsolidacao;
	}

	/**
	 * @param dataHoraConsolidacao the dataHoraConsolidacao to set
	 */
	public void setDataHoraConsolidacao(DateTime dataHoraConsolidacao) {
		this.dataHoraConsolidacao = dataHoraConsolidacao;
	}

}
