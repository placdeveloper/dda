package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.AutorizacaoValorDivergente;
import br.com.sicoob.sisbr.sicoobdda.entidades.MeioPagamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoDesconto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoJuros;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMulta;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoPessoaDDAAvalista;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemDDABoletoFiltroDto é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDABoletoFiltroDTO")
public class MensagemDDABoletoFiltroDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 6613584431643215636L;
    private List<String> comboTipoMensagem;
    private String tipoMensagem;
    private Date dataMovimento;
    private String numCodigoDeBarras;
    private List<TipoJuros> listaTipoJuros;
    private List<TipoModeloCalculo> listaTipoModeloCalculo;
    private List<TipoDesconto> listaTipoDesconto;
    private List<TipoMulta> listaTipoMulta;
    private List<MeioPagamento> listaTipoModeloPagamento;
    private List<EspecieDocumentoDeParaDto> listaEspecieDocumento;
    private List<AutorizacaoValorDivergente> listaAutorizacaoValorDivergente;
    private List<CarteiraDeParaDto> listaIdCarteira;
    private List<TipoPessoaDDAAvalista> listaTipoPessoaDDAAvalista;
    private Integer situacaoMensagem;
    private Integer paginaAtual;

    // SETTERS AND GETTERS ============================
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
     * @return o atributo listaTipoJuros
     */
    public List<TipoJuros> getListaTipoJuros() {
        if (ObjectUtil.isNull(listaTipoJuros)) {
            listaTipoJuros = new ArrayList<TipoJuros>();
        }
        return listaTipoJuros;
    }

    /**
     * Define o atributo listaTipoJuros
     */
    public void setListaTipoJuros(List<TipoJuros> listaTipoJuros) {
        this.listaTipoJuros = listaTipoJuros;
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
     * @return o atributo listaTipoModeloCalculo
     */
    public List<TipoModeloCalculo> getListaTipoModeloCalculo() {
        if (ObjectUtil.isNull(listaTipoModeloCalculo)) {
            listaTipoModeloCalculo = new ArrayList<TipoModeloCalculo>();
        }
        return listaTipoModeloCalculo;
    }

    /**
     * Define o atributo listaTipoModeloCalculo
     */
    public void setListaTipoModeloCalculo(List<TipoModeloCalculo> listaTipoModeloCalculo) {
        this.listaTipoModeloCalculo = listaTipoModeloCalculo;
    }

    /**
     * @return o atributo listaTipoDesconto
     */
    public List<TipoDesconto> getListaTipoDesconto() {
        if (ObjectUtil.isNull(listaTipoDesconto)) {
            listaTipoDesconto = new ArrayList<TipoDesconto>();
        }
        return listaTipoDesconto;
    }

    /**
     * Define o atributo listaTipoDesconto
     */
    public void setListaTipoDesconto(List<TipoDesconto> listaTipoDesconto) {
        this.listaTipoDesconto = listaTipoDesconto;
    }

    /**
     * @return o atributo listaTipoMulta
     */
    public List<TipoMulta> getListaTipoMulta() {
        if (ObjectUtil.isNull(listaTipoMulta)) {
            listaTipoMulta = new ArrayList<TipoMulta>();
        }
        return listaTipoMulta;
    }

    /**
     * Define o atributo listaTipoMulta
     */
    public void setListaTipoMulta(List<TipoMulta> listaTipoMulta) {
        this.listaTipoMulta = listaTipoMulta;
    }

    /**
     * @return o atributo listaTipoModeloPagamento
     */
    public List<MeioPagamento> getListaTipoModeloPagamento() {
        if (ObjectUtil.isNull(listaTipoModeloPagamento)) {
            listaTipoModeloPagamento = new ArrayList<MeioPagamento>();
        }
        return listaTipoModeloPagamento;
    }

    /**
     * Define o atributo listaTipoModeloPagamento
     */
    public void setListaTipoModeloPagamento(List<MeioPagamento> listaTipoModeloPagamento) {
        this.listaTipoModeloPagamento = listaTipoModeloPagamento;
    }

    /**
     * @return o atributo listaEspecieDocumento
     */
    public List<EspecieDocumentoDeParaDto> getListaEspecieDocumento() {
        if (ObjectUtil.isNull(listaEspecieDocumento)) {
            listaEspecieDocumento = new ArrayList<EspecieDocumentoDeParaDto>();
        }
        return listaEspecieDocumento;
    }

    /**
     * Define o atributo listaEspecieDocumento
     */
    public void setListaEspecieDocumento(List<EspecieDocumentoDeParaDto> listaEspecieDocumento) {
        this.listaEspecieDocumento = listaEspecieDocumento;
    }

    /**
     * @return o atributo listaAutorizacaoValorDivergente
     */
    public List<AutorizacaoValorDivergente> getListaAutorizacaoValorDivergente() {
        if (ObjectUtil.isNull(listaAutorizacaoValorDivergente)) {
            listaAutorizacaoValorDivergente = new ArrayList<AutorizacaoValorDivergente>();
        }
        return listaAutorizacaoValorDivergente;
    }

    /**
     * Define o atributo listaAutorizacaoValorDivergente
     */
    public void setListaAutorizacaoValorDivergente(List<AutorizacaoValorDivergente> listaAutorizacaoValorDivergente) {
        this.listaAutorizacaoValorDivergente = listaAutorizacaoValorDivergente;
    }

    /**
     * @return o atributo listaIdCarteira
     */
    public List<CarteiraDeParaDto> getListaIdCarteira() {
        if (ObjectUtil.isNull(listaIdCarteira)) {
            listaIdCarteira = new ArrayList<CarteiraDeParaDto>();
        }
        return listaIdCarteira;
    }

    /**
     * Define o atributo listaIdCarteira
     */
    public void setListaIdCarteira(List<CarteiraDeParaDto> listaIdCarteira) {
        this.listaIdCarteira = listaIdCarteira;
    }

    /**
     * @return o atributo listaTipoPessoaDDAAvalista
     */
    public List<TipoPessoaDDAAvalista> getListaTipoPessoaDDAAvalista() {
        if (ObjectUtil.isNull(listaTipoPessoaDDAAvalista)) {
            listaTipoPessoaDDAAvalista = new ArrayList<TipoPessoaDDAAvalista>();
        }
        return listaTipoPessoaDDAAvalista;
    }

    /**
     * Define o atributo listaTipoPessoaDDAAvalista
     */
    public void setListaTipoPessoaDDAAvalista(List<TipoPessoaDDAAvalista> listaTipoPessoaDDAAvalista) {
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
