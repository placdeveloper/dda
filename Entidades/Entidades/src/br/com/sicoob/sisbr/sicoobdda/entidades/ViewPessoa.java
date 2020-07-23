package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ViewPessoa é responsável por
 * 
 * @author george.santos
 */
@Entity
@Table(name = "VIW_PESSOA", schema = "CLI")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ViewPessoaVO")
public class ViewPessoa extends SicoobDDAEntidade {

    private static final long serialVersionUID = 6078528236212338000L;

    @Id
    @Column(name = "CODCOMPARTILHAMENTOCADASTRO")
    private Integer id;

    private Integer codTipoPessoa;

    private Long idPessoa;

    private String nomePessoa;

    private String numCpfCnpj;

    private String nomeCompleto;

    private String nomeApelido;

    private Integer idInstituicao;

    private Integer idUnidadeInst;

    private Integer idPessoaLegado;

    private Integer idInstituicaoResponsavel;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.entidades.negocio.entidades.CobrancaBancariaEntidade#getId()
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the codTipoPessoa
     */
    public Integer getCodTipoPessoa() {
        return codTipoPessoa;
    }

    /**
     * @param codTipoPessoa the codTipoPessoa to set
     */
    public void setCodTipoPessoa(Integer codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    /**
     * @return the idPessoa
     */
    public Long getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return the nomePessoa
     */
    public String getNomePessoa() {
        return nomePessoa;
    }

    /**
     * @param nomePessoa the nomePessoa to set
     */
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    /**
     * @return the numCpfCnpj
     */
    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    /**
     * @param numCpfCnpj the numCpfCnpj to set
     */
    public void setNumCpfCnpj(String numCpfCnpj) {
        this.numCpfCnpj = numCpfCnpj;
    }

    /**
     * @return the nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * @param nomeCompleto the nomeCompleto to set
     */
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    /**
     * @return the nomeApelido
     */
    public String getNomeApelido() {
        return nomeApelido;
    }

    /**
     * @param nomeApelido the nomeApelido to set
     */
    public void setNomeApelido(String nomeApelido) {
        this.nomeApelido = nomeApelido;
    }

    /**
     * @return the idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * @param idInstituicao the idInstituicao to set
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return the idUnidadeInst
     */
    public Integer getIdUnidadeInst() {
        return idUnidadeInst;
    }

    /**
     * @param idUnidadeInst the idUnidadeInst to set
     */
    public void setIdUnidadeInst(Integer idUnidadeInst) {
        this.idUnidadeInst = idUnidadeInst;
    }

    /**
     * @return the idPessoaLegado
     */
    public Integer getIdPessoaLegado() {
        return idPessoaLegado;
    }

    /**
     * @param idPessoaLegado the idPessoaLegado to set
     */
    public void setIdPessoaLegado(Integer idPessoaLegado) {
        this.idPessoaLegado = idPessoaLegado;
    }

    /**
     * @return the idInstituicaoResponsavel
     */
    public Integer getIdInstituicaoResponsavel() {
        return idInstituicaoResponsavel;
    }

    /**
     * @param idInstituicaoResponsavel the idInstituicaoResponsavel to set
     */
    public void setIdInstituicaoResponsavel(Integer idInstituicaoResponsavel) {
        this.idInstituicaoResponsavel = idInstituicaoResponsavel;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
