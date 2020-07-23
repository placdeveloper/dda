package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * BoletoDDAMultaVO é responsável por
 * 
 * @author george.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut")
public class BoletoDDATerceiroAutVO extends BancoobVO {

    private Long id;

    private BoletoDDAVO boletoDDA;

    private String codTipoPessoaAutorizador;

    private String numCpfCnpjAutorizador;

    private String codTipoPessoaTerceiro;

    private String numCpfCnpjTerceiro;

    private Long numIdentificadorTerceiro;

    private Long numRefAtualTerceiro;

    private Boolean bolAtivo;

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
     * @return o atributo boletoDDA
     */
    public BoletoDDAVO getBoletoDDA() {
        return boletoDDA;
    }

    /**
     * Define o atributo boletoDDA
     */
    public void setBoletoDDA(BoletoDDAVO boletoDDA) {
        this.boletoDDA = boletoDDA;
    }

    /**
     * @return o atributo codTipoPessoaAutorizador
     */
    public String getCodTipoPessoaAutorizador() {
        return codTipoPessoaAutorizador;
    }

    /**
     * Define o atributo codTipoPessoaAutorizador
     */
    public void setCodTipoPessoaAutorizador(String codTipoPessoaAutorizador) {
        this.codTipoPessoaAutorizador = codTipoPessoaAutorizador;
    }

    /**
     * @return o atributo numCpfCnpjAutorizador
     */
    public String getNumCpfCnpjAutorizador() {
        return numCpfCnpjAutorizador;
    }

    /**
     * Define o atributo numCpfCnpjAutorizador
     */
    public void setNumCpfCnpjAutorizador(String numCpfCnpjAutorizador) {
        this.numCpfCnpjAutorizador = numCpfCnpjAutorizador;
    }

    /**
     * @return o atributo codTipoPessoaTerceiro
     */
    public String getCodTipoPessoaTerceiro() {
        return codTipoPessoaTerceiro;
    }

    /**
     * Define o atributo codTipoPessoaTerceiro
     */
    public void setCodTipoPessoaTerceiro(String codTipoPessoaTerceiro) {
        this.codTipoPessoaTerceiro = codTipoPessoaTerceiro;
    }

    /**
     * @return o atributo numCpfCnpjTerceiro
     */
    public String getNumCpfCnpjTerceiro() {
        return numCpfCnpjTerceiro;
    }

    /**
     * Define o atributo numCpfCnpjTerceiro
     */
    public void setNumCpfCnpjTerceiro(String numCpfCnpjTerceiro) {
        this.numCpfCnpjTerceiro = numCpfCnpjTerceiro;
    }

    /**
     * @return o atributo numIdentificadorTerceiro
     */
    public Long getNumIdentificadorTerceiro() {
        return numIdentificadorTerceiro;
    }

    /**
     * Define o atributo numIdentificadorTerceiro
     */
    public void setNumIdentificadorTerceiro(Long numIdentificadorTerceiro) {
        this.numIdentificadorTerceiro = numIdentificadorTerceiro;
    }

    /**
     * @return o atributo numRefAtualTerceiro
     */
    public Long getNumRefAtualTerceiro() {
        return numRefAtualTerceiro;
    }

    /**
     * Define o atributo numRefAtualTerceiro
     */
    public void setNumRefAtualTerceiro(Long numRefAtualTerceiro) {
        this.numRefAtualTerceiro = numRefAtualTerceiro;
    }

    /**
     * @return o atributo bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }
}
