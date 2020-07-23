package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.bancoob.sisbrweb.vo.BancoobVO;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.EventoConsolidadoDDADetalhe")
public class EventoConsolidadoDDADetalheVO extends BancoobVO {

    private Long id;
    private EventoConsolidadoDDAVO eventoConsolidadoDDA;
    private Integer qtdMensagemApurado;
    private Integer idInstituicao;

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
     * @return o atributo eventoConsolidadoDDA
     */
    public EventoConsolidadoDDAVO getEventoConsolidadoDDA() {
        return eventoConsolidadoDDA;
    }

    /**
     * Define o atributo eventoConsolidadoDDA
     */
    public void setEventoConsolidadoDDA(EventoConsolidadoDDAVO eventoConsolidadoDDA) {
        this.eventoConsolidadoDDA = eventoConsolidadoDDA;
    }

    /**
     * @return o atributo qtdMensagemApurado
     */
    public Integer getQtdMensagemApurado() {
        return qtdMensagemApurado;
    }

    /**
     * Define o atributo qtdMensagemApurado
     */
    public void setQtdMensagemApurado(Integer qtdMensagemApurado) {
        this.qtdMensagemApurado = qtdMensagemApurado;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

}
