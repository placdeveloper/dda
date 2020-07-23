/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         PagadorDto.java
 * Data Criação:    Dec 21, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * PagadorDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PagadorDTO")
public class PagadorDto extends BancoobDto {

    /**
     * Dto parametrizado no relatorio de Pagador Eletronico
     */
    private static final long serialVersionUID = -1650383331912387257L;

    private Boolean bolPagadorEletronico;
    private List<String> listaNumCCO;
    private List<PagadorAgregadoDto> listaPagadorAgregado;
    private List<MensagemPendentePagadorDto> listaMensagemPendente;
    private List<HistoricoPagadorEletronicoDto> listaHistPagadorEletronico;

    private String numCpfCnpj;
    private String nomePessoa;
    private Integer idInstituicao;

    private Long idPagador;
    private String cooperativa;
    private String descSituacaoCIP;
    private Integer qtdAdesaoDDA;
    private DateTimeDB dataAdesaoDDA;
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
     * 
     */
    public PagadorDto() {
        super();
    }

    public PagadorDto(Long idPagador, Integer numAgencia, String numCpfCnpj, String nomePagador, Integer qtdAdesaoDDA, Boolean bolPagadorEletronico,
            Boolean bolPagadorEletronicoSicoob, Number qtdAgregado, Date dataAdesaoDDA) {
        this.idPagador = idPagador;
        this.numAgencia = numAgencia;
        this.numCpfCnpj = numCpfCnpj;
        this.nomePessoa = nomePagador;
        this.qtdAdesaoDDA = qtdAdesaoDDA;
        this.bolPagadorEletronico = bolPagadorEletronico;
        this.bolPagadorEletronicoSicoob = bolPagadorEletronicoSicoob;
        this.qtdAgregado = qtdAgregado.intValue();
        this.dataAdesaoDDA = ObjectUtil.isNull(dataAdesaoDDA) ? null : new DateTimeDB(dataAdesaoDDA.getTime());

    }

    public PagadorDto(Long idPagadorDDA, Integer numAgencia, String numCpfCnpj, String nomePessoa, String numDDDResidencial, String numTelefoneResidencial, String numDDDCelular,
            String numTelefoneCelular, String descLogradouro, String descComplemento, String descNumero, String nomeBairro, String nomeLocalidade, String siglaUF, String codCep,
            Integer qtdAdesaoDDA, Boolean bolPagadorEletronico, Boolean bolPagadorEletronicoSicoob, Integer qtdAgregado, Date dataAdesaoDDA) {
        this.idPagador = idPagadorDDA;
        this.numAgencia = numAgencia;
        this.numCpfCnpj = numCpfCnpj;
        this.nomePessoa = nomePessoa;
        this.numDDDResidencial = numDDDResidencial;
        this.numTelefoneResidencial = numTelefoneResidencial;
        this.numDDDCelular = numDDDCelular;
        this.numTelefoneCelular = numTelefoneCelular;
        this.descLogradouro = descLogradouro;
        this.descComplemento = descComplemento;
        this.descNumero = descNumero;
        this.nomeBairro = nomeBairro;
        this.nomeLocalidade = nomeLocalidade;
        this.siglaUF = siglaUF;
        this.codCep = codCep;
        this.qtdAdesaoDDA = qtdAdesaoDDA;
        this.bolPagadorEletronico = bolPagadorEletronico;
        this.bolPagadorEletronicoSicoob = bolPagadorEletronicoSicoob;
        this.qtdAgregado = qtdAgregado;
        this.dataAdesaoDDA = ObjectUtil.isNull(dataAdesaoDDA) ? null : new DateTimeDB(dataAdesaoDDA.getTime());

    }

    public PagadorDto(Long idPagador, String numCpfCnpj, String nomePagador, Integer qtdAdesaoDDA, Boolean bolPagadorEletronico, Boolean bolPagadorEletronicoSicoob,
            Number qtdAgregado, Date dataAdesaoDDA) {
        this.idPagador = idPagador;
        this.numCpfCnpj = numCpfCnpj;
        this.nomePessoa = nomePagador;
        this.qtdAdesaoDDA = qtdAdesaoDDA;
        this.bolPagadorEletronico = bolPagadorEletronico;
        this.bolPagadorEletronicoSicoob = bolPagadorEletronicoSicoob;
        this.qtdAgregado = qtdAgregado.intValue();
        this.dataAdesaoDDA = ObjectUtil.isNull(dataAdesaoDDA) ? null : new DateTimeDB(dataAdesaoDDA.getTime());

    }

    /**
     * @param bolPagadorEletronico
     * @param listaNumCCO
     * @param listaPagadorAgregado
     * @param listaMensagemPendente
     * @param listaHistPagadorEletronico
     */
    public PagadorDto(Boolean bolPagadorEletronico, List<String> listaNumCCO, List<PagadorAgregadoDto> listaPagadorAgregado,
            List<MensagemPendentePagadorDto> listaMensagemPendente, List<HistoricoPagadorEletronicoDto> listaHistPagadorEletronico) {
        this.bolPagadorEletronico = bolPagadorEletronico;
        this.listaNumCCO = listaNumCCO;
        this.listaPagadorAgregado = listaPagadorAgregado;
        this.listaMensagemPendente = listaMensagemPendente;
        this.listaHistPagadorEletronico = listaHistPagadorEletronico;
    }

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
    public List<PagadorAgregadoDto> getListaPagadorAgregado() {
        return listaPagadorAgregado;
    }

    /**
     * @param listaPagadorAgregado the listaPagadorAgregado to set
     */
    public void setListaPagadorAgregado(List<PagadorAgregadoDto> listaPagadorAgregado) {
        this.listaPagadorAgregado = listaPagadorAgregado;
    }

    /**
     * @return the listaMensagemPendente
     */
    public List<MensagemPendentePagadorDto> getListaMensagemPendente() {
        return listaMensagemPendente;
    }

    /**
     * @param listaMensagemPendente the listaMensagemPendente to set
     */
    public void setListaMensagemPendente(List<MensagemPendentePagadorDto> listaMensagemPendente) {
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
    public List<HistoricoPagadorEletronicoDto> getListaHistPagadorEletronico() {
        return listaHistPagadorEletronico;
    }

    /**
     * @param listaHistPagadorEletronico the listaHistPagadorEletronico to set
     */
    public void setListaHistPagadorEletronico(List<HistoricoPagadorEletronicoDto> listaHistPagadorEletronico) {
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
    public DateTimeDB getDataAdesaoDDA() {
        return dataAdesaoDDA;
    }

    /**
     * Define o atributo dataAdesaoDDA
     */
    public void setDataAdesaoDDA(DateTimeDB dataAdesaoDDA) {
        this.dataAdesaoDDA = dataAdesaoDDA;
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
     * @return o atributo numDDDResidencial
     */
    public String getNumDDDResidencial() {
        return numDDDResidencial;
    }

    /**
     * Define o atributo numDDDResidencial
     */
    public void setNumDDDResidencial(String numDDDResidencial) {
        this.numDDDResidencial = numDDDResidencial;
    }

    /**
     * @return o atributo numTelefoneResidencial
     */
    public String getNumTelefoneResidencial() {
        return numTelefoneResidencial;
    }

    /**
     * Define o atributo numTelefoneResidencial
     */
    public void setNumTelefoneResidencial(String numTelefoneResidencial) {
        this.numTelefoneResidencial = numTelefoneResidencial;
    }

    /**
     * @return o atributo numDDDCelular
     */
    public String getNumDDDCelular() {
        return numDDDCelular;
    }

    /**
     * Define o atributo numDDDCelular
     */
    public void setNumDDDCelular(String numDDDCelular) {
        this.numDDDCelular = numDDDCelular;
    }

    /**
     * @return o atributo numTelefoneCelular
     */
    public String getNumTelefoneCelular() {
        return numTelefoneCelular;
    }

    /**
     * Define o atributo numTelefoneCelular
     */
    public void setNumTelefoneCelular(String numTelefoneCelular) {
        this.numTelefoneCelular = numTelefoneCelular;
    }

    /**
     * @return o atributo descLogradouro
     */
    public String getDescLogradouro() {
        return descLogradouro;
    }

    /**
     * Define o atributo descLogradouro
     */
    public void setDescLogradouro(String descLogradouro) {
        this.descLogradouro = descLogradouro;
    }

    /**
     * @return o atributo descComplemento
     */
    public String getDescComplemento() {
        return descComplemento;
    }

    /**
     * Define o atributo descComplemento
     */
    public void setDescComplemento(String descComplemento) {
        this.descComplemento = descComplemento;
    }

    /**
     * @return o atributo descNumero
     */
    public String getDescNumero() {
        return descNumero;
    }

    /**
     * Define o atributo descNumero
     */
    public void setDescNumero(String descNumero) {
        this.descNumero = descNumero;
    }

    /**
     * @return o atributo nomeBairro
     */
    public String getNomeBairro() {
        return nomeBairro;
    }

    /**
     * Define o atributo nomeBairro
     */
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    /**
     * @return o atributo nomeLocalidade
     */
    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    /**
     * Define o atributo nomeLocalidade
     */
    public void setNomeLocalidade(String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

    /**
     * @return o atributo siglaUF
     */
    public String getSiglaUF() {
        return siglaUF;
    }

    /**
     * Define o atributo siglaUF
     */
    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    /**
     * @return o atributo codCep
     */
    public String getCodCep() {
        return codCep;
    }

    /**
     * Define o atributo codCep
     */
    public void setCodCep(String codCep) {
        this.codCep = codCep;
    }

    /**
     * @return o atributo numCpfCnpjAgregado
     */
    public String getNumCpfCnpjAgregado() {
        return numCpfCnpjAgregado;
    }

    /**
     * Define o atributo numCpfCnpjAgregado
     */
    public void setNumCpfCnpjAgregado(String numCpfCnpjAgregado) {
        this.numCpfCnpjAgregado = numCpfCnpjAgregado;
    }

    /**
     * @return o atributo nomePessoaAgregado
     */
    public String getNomePessoaAgregado() {
        return nomePessoaAgregado;
    }

    /**
     * Define o atributo nomePessoaAgregado
     */
    public void setNomePessoaAgregado(String nomePessoaAgregado) {
        this.nomePessoaAgregado = nomePessoaAgregado;
    }

}
