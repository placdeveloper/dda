package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;
import java.util.List;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.EventoConsolidadoDDAVO;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDA")
public class RateioDDAVO extends BancoobVO {

    private Long id;
    private ExtratoDDAVO extrato;
    private String descRateio;
    private SituacaoRateioVO situacaoRateio;
    private DateTime dataHoraInclusao;
    private DateTime dataHoraAprovacao;
    private String idUsuarioAprovacao;
    private Integer idInstituicaoUsuarioAprovacao;
    private List<EventoConsolidadoDDAVO> listaEventoConsolidadoDDA;

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
     * @return o atributo extrato
     */
    public ExtratoDDAVO getExtrato() {
        return extrato;
    }

    /**
     * Define o atributo extrato
     */
    public void setExtrato(ExtratoDDAVO extrato) {
        this.extrato = extrato;
    }

    /**
     * @return o atributo descRateio
     */
    public String getDescRateio() {
        return descRateio;
    }

    /**
     * Define o atributo descRateio
     */
    public void setDescRateio(String descRateio) {
        this.descRateio = descRateio;
    }

    /**
     * @return o atributo situacaoRateio
     */
    public SituacaoRateioVO getSituacaoRateio() {
        return situacaoRateio;
    }

    /**
     * Define o atributo situacaoRateio
     */
    public void setSituacaoRateio(SituacaoRateioVO situacaoRateio) {
        this.situacaoRateio = situacaoRateio;
    }

    /**
     * @return o atributo dataHoraInclusao
     */
    public DateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    /**
     * Define o atributo dataHoraInclusao
     */
    public void setDataHoraInclusao(DateTime dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

    /**
     * @return o atributo dataHoraAprovacao
     */
    public DateTime getDataHoraAprovacao() {
        return dataHoraAprovacao;
    }

    /**
     * Define o atributo dataHoraAprovacao
     */
    public void setDataHoraAprovacao(DateTime dataHoraAprovacao) {
        this.dataHoraAprovacao = dataHoraAprovacao;
    }

    /**
     * @return o atributo idUsuarioAprovacao
     */
    public String getIdUsuarioAprovacao() {
        return idUsuarioAprovacao;
    }

    /**
     * Define o atributo idUsuarioAprovacao
     */
    public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
        this.idUsuarioAprovacao = idUsuarioAprovacao;
    }

    /**
     * @return o atributo idInstituicaoUsuarioAprovacao
     */
    public Integer getIdInstituicaoUsuarioAprovacao() {
        return idInstituicaoUsuarioAprovacao;
    }

    /**
     * Define o atributo idInstituicaoUsuarioAprovacao
     */
    public void setIdInstituicaoUsuarioAprovacao(Integer idInstituicaoUsuarioAprovacao) {
        this.idInstituicaoUsuarioAprovacao = idInstituicaoUsuarioAprovacao;
    }

    /**
     * @return o atributo listaEventoConsolidadoDDA
     */
    public List<EventoConsolidadoDDAVO> getListaEventoConsolidadoDDA() {
        return listaEventoConsolidadoDDA;
    }

    /**
     * Define o atributo listaEventoConsolidadoDDA
     */
    public void setListaEventoConsolidadoDDA(List<EventoConsolidadoDDAVO> listaEventoConsolidadoDDA) {
        this.listaEventoConsolidadoDDA = listaEventoConsolidadoDDA;
    }

}
