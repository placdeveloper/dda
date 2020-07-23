package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.AutorizacaoValorDivergenteVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MeioPagamentoVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoDescontoVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoJurosVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoModeloCalculoVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoMultaVO;
import br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoPessoaDDAAvalistaVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemDDABoletoFiltroDto")
public class MensagemDDABoletoFiltroDTO extends BancoobDTO {

    private List<String> comboTipoMensagem;
    private String tipoMensagem;
    private Date dataMovimento;
    private String numCodigoDeBarras;
    private List<TipoJurosVO> listaTipoJuros;
    private List<TipoModeloCalculoVO> listaTipoModeloCalculo;
    private List<TipoDescontoVO> listaTipoDesconto;
    private List<TipoMultaVO> listaTipoMulta;
    private List<MeioPagamentoVO> listaTipoModeloPagamento;
    private List<EspecieDocumentoDeParaDTO> listaEspecieDocumento;
    private List<AutorizacaoValorDivergenteVO> listaAutorizacaoValorDivergente;
    private List<CarteiraDeParaDTO> listaIdCarteira;
    private List<TipoPessoaDDAAvalistaVO> listaTipoPessoaDDAAvalista;
    private Integer situacaoMensagem;
    private Integer paginaAtual;

    /**
     * @return o atributo comboTipoMensagem
     */
    public List<String> getComboTipoMensagem() {
        return comboTipoMensagem;
    }

    /**
     * Define o atributo comboTipoMensagem
     */
    public void setComboTipoMensagem(List<String> comboTipoMensagem) {
        this.comboTipoMensagem = comboTipoMensagem;
    }

    /**
     * @return o atributo tipoMensagem
     */
    public String getTipoMensagem() {
        return tipoMensagem;
    }

    /**
     * Define o atributo tipoMensagem
     */
    public void setTipoMensagem(String tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    /**
     * @return o atributo dataMovimento
     */
    public Date getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Define o atributo dataMovimento
     */
    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo numCodigoDeBarras
     */
    public String getNumCodigoDeBarras() {
        return numCodigoDeBarras;
    }

    /**
     * Define o atributo numCodigoDeBarras
     */
    public void setNumCodigoDeBarras(String numCodigoDeBarras) {
        this.numCodigoDeBarras = numCodigoDeBarras;
    }

    /**
     * @return o atributo listaTipoJuros
     */
    public List<TipoJurosVO> getListaTipoJuros() {
        return listaTipoJuros;
    }

    /**
     * Define o atributo listaTipoJuros
     */
    public void setListaTipoJuros(List<TipoJurosVO> listaTipoJuros) {
        this.listaTipoJuros = listaTipoJuros;
    }

    /**
     * @return o atributo listaTipoModeloCalculo
     */
    public List<TipoModeloCalculoVO> getListaTipoModeloCalculo() {
        return listaTipoModeloCalculo;
    }

    /**
     * Define o atributo listaTipoModeloCalculo
     */
    public void setListaTipoModeloCalculo(List<TipoModeloCalculoVO> listaTipoModeloCalculo) {
        this.listaTipoModeloCalculo = listaTipoModeloCalculo;
    }

    /**
     * @return o atributo listaTipoDesconto
     */
    public List<TipoDescontoVO> getListaTipoDesconto() {
        return listaTipoDesconto;
    }

    /**
     * Define o atributo listaTipoDesconto
     */
    public void setListaTipoDesconto(List<TipoDescontoVO> listaTipoDesconto) {
        this.listaTipoDesconto = listaTipoDesconto;
    }

    /**
     * @return o atributo listaTipoMulta
     */
    public List<TipoMultaVO> getListaTipoMulta() {
        return listaTipoMulta;
    }

    /**
     * Define o atributo listaTipoMulta
     */
    public void setListaTipoMulta(List<TipoMultaVO> listaTipoMulta) {
        this.listaTipoMulta = listaTipoMulta;
    }

    /**
     * @return o atributo listaTipoModeloPagamento
     */
    public List<MeioPagamentoVO> getListaTipoModeloPagamento() {
        return listaTipoModeloPagamento;
    }

    /**
     * Define o atributo listaTipoModeloPagamento
     */
    public void setListaTipoModeloPagamento(List<MeioPagamentoVO> listaTipoModeloPagamento) {
        this.listaTipoModeloPagamento = listaTipoModeloPagamento;
    }

    /**
     * @return o atributo listaEspecieDocumento
     */
    public List<EspecieDocumentoDeParaDTO> getListaEspecieDocumento() {
        return listaEspecieDocumento;
    }

    /**
     * Define o atributo listaEspecieDocumento
     */
    public void setListaEspecieDocumento(List<EspecieDocumentoDeParaDTO> listaEspecieDocumento) {
        this.listaEspecieDocumento = listaEspecieDocumento;
    }

    /**
     * @return o atributo listaAutorizacaoValorDivergente
     */
    public List<AutorizacaoValorDivergenteVO> getListaAutorizacaoValorDivergente() {
        return listaAutorizacaoValorDivergente;
    }

    /**
     * Define o atributo listaAutorizacaoValorDivergente
     */
    public void setListaAutorizacaoValorDivergente(List<AutorizacaoValorDivergenteVO> listaAutorizacaoValorDivergente) {
        this.listaAutorizacaoValorDivergente = listaAutorizacaoValorDivergente;
    }

    /**
     * @return o atributo listaIdCarteira
     */
    public List<CarteiraDeParaDTO> getListaIdCarteira() {
        return listaIdCarteira;
    }

    /**
     * Define o atributo listaIdCarteira
     */
    public void setListaIdCarteira(List<CarteiraDeParaDTO> listaIdCarteira) {
        this.listaIdCarteira = listaIdCarteira;
    }

    /**
     * @return o atributo listaTipoPessoaDDAAvalista
     */
    public List<TipoPessoaDDAAvalistaVO> getListaTipoPessoaDDAAvalista() {
        return listaTipoPessoaDDAAvalista;
    }

    /**
     * Define o atributo listaTipoPessoaDDAAvalista
     */
    public void setListaTipoPessoaDDAAvalista(List<TipoPessoaDDAAvalistaVO> listaTipoPessoaDDAAvalista) {
        this.listaTipoPessoaDDAAvalista = listaTipoPessoaDDAAvalista;
    }

    /**
     * @return o atributo situacaoMensagem
     */
    public Integer getSituacaoMensagem() {
        return situacaoMensagem;
    }

    /**
     * Define o atributo situacaoMensagem
     */
    public void setSituacaoMensagem(Integer situacaoMensagem) {
        this.situacaoMensagem = situacaoMensagem;
    }

    /**
     * @return o atributo paginaAtual
     */
    public Integer getPaginaAtual() {
        return paginaAtual;
    }

    /**
     * Define o atributo paginaAtual
     */
    public void setPaginaAtual(Integer paginaAtual) {
        this.paginaAtual = paginaAtual;
    }
}
