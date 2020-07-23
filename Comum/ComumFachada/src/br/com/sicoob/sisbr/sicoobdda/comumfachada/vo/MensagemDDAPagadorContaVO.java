package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemDDAPagadorContaVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta")
public class MensagemDDAPagadorContaVO extends BancoobVO {

    private Long id;
    private MensagemDDAPagadorVO mensagemDDAPagador;
    private Integer numAgencia;
    private BigDecimal numContaCorrente;
    private String codTipoOperacao;
    private DateTime dataHoraAdesao;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the mensagemDDAPagador
     */
    public MensagemDDAPagadorVO getMensagemDDAPagador() {
        return mensagemDDAPagador;
    }

    /**
     * @param mensagemDDAPagador the mensagemDDAPagador to set
     */
    public void setMensagemDDAPagador(MensagemDDAPagadorVO mensagemDDAPagador) {
        this.mensagemDDAPagador = mensagemDDAPagador;
    }

    /**
     * @return the numAgencia
     */
    public Integer getNumAgencia() {
        return numAgencia;
    }

    /**
     * @param numAgencia the numAgencia to set
     */
    public void setNumAgencia(Integer numAgencia) {
        this.numAgencia = numAgencia;
    }

    /**
     * @return the numContaCorrente
     */
    public BigDecimal getNumContaCorrente() {
        return numContaCorrente;
    }

    /**
     * @param numContaCorrente the numContaCorrente to set
     */
    public void setNumContaCorrente(BigDecimal numContaCorrente) {
        this.numContaCorrente = numContaCorrente;
    }

    /**
     * @return the codTipoOperacao
     */
    public String getCodTipoOperacao() {
        return codTipoOperacao;
    }

    /**
     * @param codTipoOperacao the codTipoOperacao to set
     */
    public void setCodTipoOperacao(String codTipoOperacao) {
        this.codTipoOperacao = codTipoOperacao;
    }

    /**
     * @return the dataHoraAdesao
     */
    public DateTime getDataHoraAdesao() {
        return dataHoraAdesao;
    }

    /**
     * @param dataHoraAdesao the dataHoraAdesao to set
     */
    public void setDataHoraAdesao(DateTime dataHoraAdesao) {
        this.dataHoraAdesao = dataHoraAdesao;
    }

}
