package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * PagadorAgregadoDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto")
public class PagadorAgregadoDTO extends BancoobDTO {

    private String numCpfCnpj;
    private String nomePessoa;
    private String codTipoPessoa;
    private Long idPagadorDDA;
    private Boolean selecionado;

    private String numCpfCnpjPagador;
    private String nomePessoaPagador;
    private DateTimeDB dataAdesaoDDAPagador;
    private Integer qtdAdesaoDDAPagador;
    private Long qtdAgregadosPagador;
    private Integer numCooperativaSingular;
    private Integer numCooperativa;
    private Integer idInstituicao;
    private List<Integer> listaContaCorrente;

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
     * @return the selecionado
     */
    public Boolean getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return o atributo codTipoPessoa
     */
    public String getCodTipoPessoa() {
        return codTipoPessoa;
    }

    /**
     * Define o atributo codTipoPessoa
     */
    public void setCodTipoPessoa(String codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    /**
     * @return o atributo idPagadorDDA
     */
    public Long getIdPagadorDDA() {
        return idPagadorDDA;
    }

    /**
     * Define o atributo idPagadorDDA
     */
    public void setIdPagadorDDA(Long idPagadorDDA) {
        this.idPagadorDDA = idPagadorDDA;
    }

    /**
     * @return o atributo numCpfCnpjPagador
     */
    public String getNumCpfCnpjPagador() {
        return numCpfCnpjPagador;
    }

    /**
     * Define o atributo numCpfCnpjPagador
     */
    public void setNumCpfCnpjPagador(String numCpfCnpjPagador) {
        this.numCpfCnpjPagador = numCpfCnpjPagador;
    }

    /**
     * @return o atributo nomePessoaPagador
     */
    public String getNomePessoaPagador() {
        return nomePessoaPagador;
    }

    /**
     * Define o atributo nomePessoaPagador
     */
    public void setNomePessoaPagador(String nomePessoaPagador) {
        this.nomePessoaPagador = nomePessoaPagador;
    }

    /**
     * @return o atributo dataAdesaoDDAPagador
     */
    public DateTimeDB getDataAdesaoDDAPagador() {
        return dataAdesaoDDAPagador;
    }

    /**
     * Define o atributo dataAdesaoDDAPagador
     */
    public void setDataAdesaoDDAPagador(DateTimeDB dataAdesaoDDAPagador) {
        this.dataAdesaoDDAPagador = dataAdesaoDDAPagador;
    }

    /**
     * @return o atributo qtdAdesaoDDAPagador
     */
    public Integer getQtdAdesaoDDAPagador() {
        return qtdAdesaoDDAPagador;
    }

    /**
     * Define o atributo qtdAdesaoDDAPagador
     */
    public void setQtdAdesaoDDAPagador(Integer qtdAdesaoDDAPagador) {
        this.qtdAdesaoDDAPagador = qtdAdesaoDDAPagador;
    }

    /**
     * @return o atributo qtdAgregadosPagador
     */
    public Long getQtdAgregadosPagador() {
        return qtdAgregadosPagador;
    }

    /**
     * Define o atributo qtdAgregadosPagador
     */
    public void setQtdAgregadosPagador(Long qtdAgregadosPagador) {
        this.qtdAgregadosPagador = qtdAgregadosPagador;
    }

    /**
     * @return o atributo listaContaCorrente
     */
    public List<Integer> getListaContaCorrente() {
        return listaContaCorrente;
    }

    /**
     * Define o atributo listaContaCorrente
     */
    public void setListaContaCorrente(List<Integer> listaContaCorrente) {
        this.listaContaCorrente = listaContaCorrente;
    }

    /**
     * @return o atributo numCooperativaSingular
     */
    public Integer getNumCooperativaSingular() {
        return numCooperativaSingular;
    }

    /**
     * Define o atributo numCooperativaSingular
     */
    public void setNumCooperativaSingular(Integer numCooperativaSingular) {
        this.numCooperativaSingular = numCooperativaSingular;
    }

    /**
     * @return o atributo numCooperativa
     */
    public Integer getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(Integer numCooperativa) {
        this.numCooperativa = numCooperativa;
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
