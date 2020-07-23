package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.util.List;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * PagadorDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto")
public class PagadorDTO extends BancoobDTO {

    private Boolean bolPagadorEletronico;
    private List<String> listaNumCCO;
    private List<PagadorAgregadoDTO> listaPagadorAgregado;
    private List<MensagemPendentePagadorDTO> listaMensagemPendente;
    private List<HistoricoPagadorEletronicoDTO> listaHistPagadorEletronico;

    private String numCpfCnpj;
    private String nomePessoa;
    private Integer idInstituicao;

    private Long idPagador;
    private String cooperativa;
    private String descSituacaoCIP;
    private Integer qtdAdesaoDDA;
    private DateTime dataAdesaoDDA;
    private Boolean bolPagadorEletronicoSicoob;
    private Integer qtdAgregado;
    private Integer numAgencia;
    private List<Integer> listaAgencia;
    private Integer idSingular;

    private String numDDDResidencial;
    private String numTelefoneResidencial;
    private String numDDDCelular;
    private String numTelefoneCelular;
    private String descLogradouro;
    private String descComplemento;
    private String descNumero;
    private String nomeBairro;
    private String nomeLocalidade;
    private String siglaUF;
    private String codCep;
    private String numCpfCnpjAgregado;
    private String nomePessoaAgregado;

    /**
     * @return the bolPagadorEletronico
     */
    public Boolean getBolPagadorEletronico() {
        return bolPagadorEletronico;
    }
    /**
     * @param bolPagadorEletronico the bolPagadorEletronico to set
     */
    public void setBolPagadorEletronico(Boolean bolPagadorEletronico) {
        this.bolPagadorEletronico = bolPagadorEletronico;
    }
    /**
     * @return the listaNumCCO
     */
    public List<String> getListaNumCCO() {
        return listaNumCCO;
    }

    /**
     * @param listaNumCCO the listaNumCCO to set
     */
    public void setListaNumCCO(List<String> listaNumCCO) {
        this.listaNumCCO = listaNumCCO;
    }
    /**
     * @return the listaPagadorAgregado
     */
    public List<PagadorAgregadoDTO> getListaPagadorAgregado() {
        return listaPagadorAgregado;
    }
    /**
     * @param listaPagadorAgregado the listaPagadorAgregado to set
     */
    public void setListaPagadorAgregado(List<PagadorAgregadoDTO> listaPagadorAgregado) {
        this.listaPagadorAgregado = listaPagadorAgregado;
    }
    /**
     * @return the listaMensagemPendente
     */
    public List<MensagemPendentePagadorDTO> getListaMensagemPendente() {
        return listaMensagemPendente;
    }
    /**
     * @param listaMensagemPendente the listaMensagemPendente to set
     */
    public void setListaMensagemPendente(List<MensagemPendentePagadorDTO> listaMensagemPendente) {
        this.listaMensagemPendente = listaMensagemPendente;
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
	 * @return the listaHistPagadorEletronico
	 */
	public List<HistoricoPagadorEletronicoDTO> getListaHistPagadorEletronico() {
		return listaHistPagadorEletronico;
	}
	/**
	 * @param listaHistPagadorEletronico the listaHistPagadorEletronico to set
	 */
	public void setListaHistPagadorEletronico(
			List<HistoricoPagadorEletronicoDTO> listaHistPagadorEletronico) {
		this.listaHistPagadorEletronico = listaHistPagadorEletronico;
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
     * @return o atributo cooperativa
     */
    public String getCooperativa() {
        return cooperativa;
    }

    /**
     * Define o atributo cooperativa
     */
    public void setCooperativa(String cooperativa) {
        this.cooperativa = cooperativa;
    }

    /**
     * @return o atributo descSituacaoCIP
     */
    public String getDescSituacaoCIP() {
        return descSituacaoCIP;
    }

    /**
     * Define o atributo descSituacaoCIP
     */
    public void setDescSituacaoCIP(String descSituacaoCIP) {
        this.descSituacaoCIP = descSituacaoCIP;
    }

    /**
     * @return o atributo bolPagadorEletronicoSicoob
     */
    public Boolean getBolPagadorEletronicoSicoob() {
        return bolPagadorEletronicoSicoob;
    }
    
    /**
     * Define o atributo bolPagadorEletronicoSicoob
     */
    public void setBolPagadorEletronicoSicoob(Boolean bolPagadorEletronicoSicoob) {
        this.bolPagadorEletronicoSicoob = bolPagadorEletronicoSicoob;
    }
    /**
     * @return o atributo qtdAgregado
     */
    public Integer getQtdAgregado() {
        return qtdAgregado;
    }

    /**
     * Define o atributo qtdAgregado
     */
    public void setQtdAgregado(Integer qtdAgregado) {
        this.qtdAgregado = qtdAgregado;
    }

    /**
     * @return o atributo numAgencia
     */
    public Integer getNumAgencia() {
        return numAgencia;
    }

    /**
     * Define o atributo numAgencia
     */
    public void setNumAgencia(Integer numAgencia) {
        this.numAgencia = numAgencia;
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
     * @return o atributo dataAdesaoDDA
     */
    public DateTime getDataAdesaoDDA() {
        return dataAdesaoDDA;
    }

    /**
     * Define o atributo dataAdesaoDDA
     */
    public void setDataAdesaoDDA(DateTime dataAdesaoDDA) {
        this.dataAdesaoDDA = dataAdesaoDDA;
    }

    /**
     * @return o atributo idPagador
     */
    public Long getIdPagador() {
        return idPagador;
    }

    /**
     * Define o atributo idPagador
     */
    public void setIdPagador(Long idPagador) {
        this.idPagador = idPagador;
    }

    /**
     * @return o atributo idSingular
     */
    public Integer getIdSingular() {
        return idSingular;
    }

    /**
     * Define o atributo idSingular
     */
    public void setIdSingular(Integer idSingular) {
        this.idSingular = idSingular;
    }
    
    /**
     * @return o atributo listaAgencia
     */
    public List<Integer> getListaAgencia() {
        return listaAgencia;
    }
    
    /**
     * Define o atributo listaAgencia
     */
    public void setListaAgencia(List<Integer> listaAgencia) {
        this.listaAgencia = listaAgencia;
    }

    /**
     * @return numDDDResidencial
     */
    public String getNumDDDResidencial() {
        return numDDDResidencial;
    }

    /**
     * @param numDDDResidencial
     */
    public void setNumDDDResidencial(String numDDDResidencial) {
        this.numDDDResidencial = numDDDResidencial;
    }

    /**
     * @return numTelefoneResidencial
     */
    public String getNumTelefoneResidencial() {
        return numTelefoneResidencial;
    }

    /**
     * @param numTelefoneResidencial
     */
    public void setNumTelefoneResidencial(String numTelefoneResidencial) {
        this.numTelefoneResidencial = numTelefoneResidencial;
    }

    /**
     * @return numDDDCelular
     */
    public String getNumDDDCelular() {
        return numDDDCelular;
    }

    /**
     * @param numDDDCelular
     */
    public void setNumDDDCelular(String numDDDCelular) {
        this.numDDDCelular = numDDDCelular;
    }

    /**
     * @return numTelefoneCelular
     */
    public String getNumTelefoneCelular() {
        return numTelefoneCelular;
    }

    /**
     * @param numTelefoneCelular
     */
    public void setNumTelefoneCelular(String numTelefoneCelular) {
        this.numTelefoneCelular = numTelefoneCelular;
    }

    /**
     * @return descLogradouro
     */
    public String getDescLogradouro() {
        return descLogradouro;
    }

    /**
     * @param descLogradouro
     */
    public void setDescLogradouro(String descLogradouro) {
        this.descLogradouro = descLogradouro;
    }

    /**
     * @return descComplemento
     */
    public String getDescComplemento() {
        return descComplemento;
    }

    /**
     * @param descComplemento
     */
    public void setDescComplemento(String descComplemento) {
        this.descComplemento = descComplemento;
    }

    /**
     * @return descNumero
     */
    public String getDescNumero() {
        return descNumero;
    }

    /**
     * @param descNumero
     */
    public void setDescNumero(String descNumero) {
        this.descNumero = descNumero;
    }

    /**
     * @return nomeBairro
     */
    public String getNomeBairro() {
        return nomeBairro;
    }

    /**
     * @param nomeBairro
     */
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    /**
     * @return nomeLocalidade
     */
    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    /**
     * @param nomeLocalidade
     */
    public void setNomeLocalidade(String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

    /**
     * @return siglaUF
     */
    public String getSiglaUF() {
        return siglaUF;
    }

    /**
     * @param siglaUF
     */
    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    /**
     * @return codCep
     */
    public String getCodCep() {
        return codCep;
    }

    /**
     * @param codCep
     */
    public void setCodCep(String codCep) {
        this.codCep = codCep;
    }

    /**
     * @return numCpfCnpjAgregado
     */
    public String getNumCpfCnpjAgregado() {
        return numCpfCnpjAgregado;
    }

    /**
     * @param numCpfCnpjAgregado
     */
    public void setNumCpfCnpjAgregado(String numCpfCnpjAgregado) {
        this.numCpfCnpjAgregado = numCpfCnpjAgregado;
    }

    /**
     * @return nomePessoaAgregado
     */
    public String getNomePessoaAgregado() {
        return nomePessoaAgregado;
    }

    /**
     * @param nomePessoaAgregado
     */
    public void setNomePessoaAgregado(String nomePessoaAgregado) {
        this.nomePessoaAgregado = nomePessoaAgregado;
    }

}
