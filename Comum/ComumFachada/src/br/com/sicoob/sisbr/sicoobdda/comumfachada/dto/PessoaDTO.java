package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.Date;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto")
public class PessoaDTO extends BancoobDTO {

    private Integer idPessoa;
    private String cpfCnpj;
    private Short codTipoPessoa;
    private Short codigoCompartilhamentoCadastro;
    private String nomePessoa;
    private String nomeCompleto;
    private String nomeApelido;
    private String descricaoObservacaoPessoa;
    private Short codigoAtividadeEconomica;
    private String codigoCnaeFiscal;
    private Date dataInclusaoSistema;
    private Boolean autorizaConsultaBacen;
    private Integer idPessoaLegado;
    private Integer idInstituicao;
    private Date dataRenovacaoCadastral;

    /**
     * @return o atributo idPessoa
     */
    public Integer getIdPessoa() {
        return idPessoa;
    }

    /**
     * Define o atributo idPessoa
     */
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return o atributo cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * Define o atributo cpfCnpj
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * @return o atributo codTipoPessoa
     */
    public Short getCodTipoPessoa() {
        return codTipoPessoa;
    }

    /**
     * Define o atributo codTipoPessoa
     */
    public void setCodTipoPessoa(Short codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    /**
     * @return o atributo codigoCompartilhamentoCadastro
     */
    public Short getCodigoCompartilhamentoCadastro() {
        return codigoCompartilhamentoCadastro;
    }

    /**
     * Define o atributo codigoCompartilhamentoCadastro
     */
    public void setCodigoCompartilhamentoCadastro(Short codigoCompartilhamentoCadastro) {
        this.codigoCompartilhamentoCadastro = codigoCompartilhamentoCadastro;
    }

    /**
     * @return o atributo nomePessoa
     */
    public String getNomePessoa() {
        return nomePessoa;
    }

    /**
     * Define o atributo nomePessoa
     */
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    /**
     * @return o atributo nomeCompleto
     */
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    /**
     * Define o atributo nomeCompleto
     */
    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    /**
     * @return o atributo nomeApelido
     */
    public String getNomeApelido() {
        return nomeApelido;
    }

    /**
     * Define o atributo nomeApelido
     */
    public void setNomeApelido(String nomeApelido) {
        this.nomeApelido = nomeApelido;
    }

    /**
     * @return o atributo descricaoObservacaoPessoa
     */
    public String getDescricaoObservacaoPessoa() {
        return descricaoObservacaoPessoa;
    }

    /**
     * Define o atributo descricaoObservacaoPessoa
     */
    public void setDescricaoObservacaoPessoa(String descricaoObservacaoPessoa) {
        this.descricaoObservacaoPessoa = descricaoObservacaoPessoa;
    }

    /**
     * @return o atributo codigoAtividadeEconomica
     */
    public Short getCodigoAtividadeEconomica() {
        return codigoAtividadeEconomica;
    }

    /**
     * Define o atributo codigoAtividadeEconomica
     */
    public void setCodigoAtividadeEconomica(Short codigoAtividadeEconomica) {
        this.codigoAtividadeEconomica = codigoAtividadeEconomica;
    }

    /**
     * @return o atributo codigoCnaeFiscal
     */
    public String getCodigoCnaeFiscal() {
        return codigoCnaeFiscal;
    }

    /**
     * Define o atributo codigoCnaeFiscal
     */
    public void setCodigoCnaeFiscal(String codigoCnaeFiscal) {
        this.codigoCnaeFiscal = codigoCnaeFiscal;
    }

    /**
     * @return o atributo dataInclusaoSistema
     */
    public Date getDataInclusaoSistema() {
        return dataInclusaoSistema;
    }

    /**
     * Define o atributo dataInclusaoSistema
     */
    public void setDataInclusaoSistema(Date dataInclusaoSistema) {
        this.dataInclusaoSistema = dataInclusaoSistema;
    }

    /**
     * @return o atributo autorizaConsultaBacen
     */
    public Boolean getAutorizaConsultaBacen() {
        return autorizaConsultaBacen;
    }

    /**
     * Define o atributo autorizaConsultaBacen
     */
    public void setAutorizaConsultaBacen(Boolean autorizaConsultaBacen) {
        this.autorizaConsultaBacen = autorizaConsultaBacen;
    }

    /**
     * @return o atributo idPessoaLegado
     */
    public Integer getIdPessoaLegado() {
        return idPessoaLegado;
    }

    /**
     * Define o atributo idPessoaLegado
     */
    public void setIdPessoaLegado(Integer idPessoaLegado) {
        this.idPessoaLegado = idPessoaLegado;
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

    /**
     * @return o atributo dataRenovacaoCadastral
     */
    public Date getDataRenovacaoCadastral() {
        return dataRenovacaoCadastral;
    }

    /**
     * Define o atributo dataRenovacaoCadastral
     */
    public void setDataRenovacaoCadastral(Date dataRenovacaoCadastral) {
        this.dataRenovacaoCadastral = dataRenovacaoCadastral;
    }

}
