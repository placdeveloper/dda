package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.util.List;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * BoletoDDABaixaOperVO é responsável por
 * 
 * @author george.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA")
public class PagadorDDAVO extends BancoobVO {

    private Long id;

    private String numCpfCnpj;

    private String codTipoPessoaCip;

    private Long numIdentificaPagadorCip;

    private Long numRefAtualCadPagador;

    private Long numSeqAtualCadPagador;

    private Integer qtdAdesaoDDA;

    private Boolean bolSacadoEletronico;

    private DateTime dataHoraUltimaAtualizacao;

    private List<PagadorDDAAgregadoVO> listaPagadorDDAAgregado;

    private List<PagadorDDAContaVO> listaPagadorDDAConta;

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
     * @return o atributo numCpfCnpj
     */
    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    /**
     * Define o atributo numCpfCnpj
     */
    public void setNumCpfCnpj(String numCpfCnpj) {
        this.numCpfCnpj = numCpfCnpj;
    }

    /**
     * @return o atributo codTipoPessoaCip
     */
    public String getCodTipoPessoaCip() {
        return codTipoPessoaCip;
    }

    /**
     * Define o atributo codTipoPessoaCip
     */
    public void setCodTipoPessoaCip(String codTipoPessoaCip) {
        this.codTipoPessoaCip = codTipoPessoaCip;
    }

    /**
     * @return o atributo numIdentificaPagadorCip
     */
    public Long getNumIdentificaPagadorCip() {
        return numIdentificaPagadorCip;
    }

    /**
     * Define o atributo numIdentificaPagadorCip
     */
    public void setNumIdentificaPagadorCip(Long numIdentificaPagadorCip) {
        this.numIdentificaPagadorCip = numIdentificaPagadorCip;
    }

    /**
     * @return o atributo numRefAtualCadPagador
     */
    public Long getNumRefAtualCadPagador() {
        return numRefAtualCadPagador;
    }

    /**
     * Define o atributo numRefAtualCadPagador
     */
    public void setNumRefAtualCadPagador(Long numRefAtualCadPagador) {
        this.numRefAtualCadPagador = numRefAtualCadPagador;
    }

    /**
     * @return o atributo numSeqAtualCadPagador
     */
    public Long getNumSeqAtualCadPagador() {
        return numSeqAtualCadPagador;
    }

    /**
     * Define o atributo numSeqAtualCadPagador
     */
    public void setNumSeqAtualCadPagador(Long numSeqAtualCadPagador) {
        this.numSeqAtualCadPagador = numSeqAtualCadPagador;
    }

    /**
     * @return o atributo qtdAdesaoDDA
     */
    public Integer getQtdAdesaoDDA() {
        return qtdAdesaoDDA;
    }

    /**
     * Define o atributo qtdAdesaoDDA
     */
    public void setQtdAdesaoDDA(Integer qtdAdesaoDDA) {
        this.qtdAdesaoDDA = qtdAdesaoDDA;
    }

    /**
     * @return o atributo bolSacadoEletronico
     */
    public Boolean getBolSacadoEletronico() {
        return bolSacadoEletronico;
    }

    /**
     * Define o atributo bolSacadoEletronico
     */
    public void setBolSacadoEletronico(Boolean bolSacadoEletronico) {
        this.bolSacadoEletronico = bolSacadoEletronico;
    }

    /**
     * @return o atributo dataHoraUltimaAtualizacao
     */
    public DateTime getDataHoraUltimaAtualizacao() {
        return dataHoraUltimaAtualizacao;
    }

    /**
     * Define o atributo dataHoraUltimaAtualizacao
     */
    public void setDataHoraUltimaAtualizacao(DateTime dataHoraUltimaAtualizacao) {
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    /**
     * @return o atributo listaPagadorDDAAgregado
     */
    public List<PagadorDDAAgregadoVO> getListaPagadorDDAAgregado() {
        return listaPagadorDDAAgregado;
    }

    /**
     * Define o atributo listaPagadorDDAAgregado
     */
    public void setListaPagadorDDAAgregado(List<PagadorDDAAgregadoVO> listaPagadorDDAAgregado) {
        this.listaPagadorDDAAgregado = listaPagadorDDAAgregado;
    }

    /**
     * @return o atributo listaPagadorDDAConta
     */
    public List<PagadorDDAContaVO> getListaPagadorDDAConta() {
        return listaPagadorDDAConta;
    }

    /**
     * Define o atributo listaPagadorDDAConta
     */
    public void setListaPagadorDDAConta(List<PagadorDDAContaVO> listaPagadorDDAConta) {
        this.listaPagadorDDAConta = listaPagadorDDAConta;
    }
}
