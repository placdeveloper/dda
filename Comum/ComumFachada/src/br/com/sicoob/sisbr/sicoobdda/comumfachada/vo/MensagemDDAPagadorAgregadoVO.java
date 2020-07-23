package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemDDAPagadorAgregadoVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado")
public class MensagemDDAPagadorAgregadoVO extends BancoobVO {

    private Long id;
    private MensagemDDAPagadorVO mensagemDDAPagador;
    private String numCpfCnpjAgregado;
    private String codTipoPessoaAgregado;
    private String codTipoOperacao;

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
     * @return the numCpfCnpjAgregado
     */
    public String getNumCpfCnpjAgregado() {
        return numCpfCnpjAgregado;
    }

    /**
     * @param numCpfCnpjAgregado the numCpfCnpjAgregado to set
     */
    public void setNumCpfCnpjAgregado(String numCpfCnpjAgregado) {
        this.numCpfCnpjAgregado = numCpfCnpjAgregado;
    }

    /**
     * @return the codTipoPessoaAgregado
     */
    public String getCodTipoPessoaAgregado() {
        return codTipoPessoaAgregado;
    }

    /**
     * @param codTipoPessoaAgregado the codTipoPessoaAgregado to set
     */
    public void setCodTipoPessoaAgregado(String codTipoPessoaAgregado) {
        this.codTipoPessoaAgregado = codTipoPessoaAgregado;
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


}
