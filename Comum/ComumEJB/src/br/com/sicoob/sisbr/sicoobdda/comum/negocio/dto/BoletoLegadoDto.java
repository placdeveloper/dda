package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * BoletoLegadoDto é responsável por
 * 
 * @author George.Santos
 */
public class BoletoLegadoDto extends BancoobDto {

    private static final long serialVersionUID = -2925719561383465415L;

    private Integer numCooperativa;
    private Integer numCodigoCanal;
    private Integer terminal;
    private Integer cooperativaCartao;
    private Integer numeroPac;
    private Integer numeroCliente;
    private Integer numeroTerminal;
    private Integer idProduto;
    private Integer idModalidade;
    private Integer numeroTitulo;
    private DateTimeDB dataCartaRemessa;
    private String descSeuNumero;
    private DateTimeDB dataEmissaoDocumento;
    private Integer codTipoVencimento;
    private DateTimeDB dataVencimentoTitulo;
    private BigDecimal qtdMonetaria;
    private BigDecimal valor;
    private BigDecimal valorAbatimento;
    private BigDecimal valorIoc;
    private DateTimeDB dataPrimDesconto;
    private BigDecimal valorPrimDesconto;
    private DateTimeDB dataSegDesconto;
    private BigDecimal valorSegDesconto;
    private DateTimeDB dataTerDesconto;
    private BigDecimal valorTerDesconto;
    private Boolean bolUtilizaPadraCh;
    private String descUsoBanco;
    private Boolean bolAceite;
    private Integer qtdDiasFloat;
    private Integer qtdDiasProtesto;

    private Integer codTipoBloqueto;
    private DateTimeDB dataLiquidacao;
    private String descInstCed1;
    private String descInstCed2;
    private String descInstCed3;
    private String descInstCed4;
    private String descInstCed5;
    private BigDecimal percTaxaMulta;
    private BigDecimal percTaxaMora;
    private Integer numBancoDep;
    private Integer numAgenciaDep;
    private Integer numeroBordero;
    private Integer codSituacao;
    private DateTimeDB dataEnvioProtesto;
    private BigDecimal numeroContaCorrente;
    private Integer codSituacaoFloat;
    private Integer codEmissaoBloq;
    private Integer idIndice;
    private Integer numParcela;
    private DateTimeDB dataPrevisaoCredito;
    private Integer numContratoOperCredito;
    private String identCnab;
    private Integer codCritProtesto;
    private DateTimeDB dataEmissaoAvSacado;
    private Boolean bolEmiteAviso;
    private Integer idAvisoVencimento;
    private Integer idCodInstrucao;
    private Integer idTipoHistorico;
    private DateTimeDB dataBordero;
    private Integer numPessoaFuncGer;
    private Integer numAgenciaDepAnt;
    private Integer origemImportacao;
    private Integer codTipoPraca;
    private Integer codTipoPracaAnterior;
    private Integer idNumBordero;
    private String idCnab;
    private Integer codLocalConvenio;
    private Integer idSeqConvenio;
    private String descNomeSacador;
    private String numCgcCpfSacador;
    private BigDecimal nossoNumero;
    private String nossoNumeroString;
    private String nossoNumeroStringRetornoSP;
    private String descNomeSacado;
    private String numCpfCnpjPagador;
    private String numDdd;
    private String numTelefone;
    private String numRamal;
    private DateTimeDB dataNascimento;
    private Boolean bolRecebeBoletoEletronico;
    private String descEndereco;
    private String descNomeBairro;
    private String descNomeCidade;
    private String descCep;
    private Integer codigoMunicipio;
    private String descEmail;
    private String chaveAcessoWeb;

    // Beneficiario
    private String numCpfCnpjCedente;
    private String descNomePessoa;
    private String descEnderecoCedente;
    private String descNumeroCedente;
    private String descComplementoCedente;
    private String descBairroCedente;
    private String descUFCedente;
    private String descCidadeCedente;
    private Integer numCEPCedente;

    private String codEspDocumento;

    private String numCodigoBarras;
    private String numLinhaDigitavel;
    private String numLinhaDigitavelFormatada;

    private String numCodMoeda;

    // SicobNetEmpresarial
    private Integer numBanco;
    private Integer numAgencia;
    private Integer codOperacao;
    private Integer idModalidadeAnterior;
    private Integer numCartaRemessa;
    private Integer numAvisoSacado;
    private String descNumero;
    private String descComplemente;
    private Integer origemMunicipio;
    private Boolean bolEnderecoAtivo;
    private Integer idPerfilTrarifirioCob;
    private Integer codTipoStatus;
    private Integer codGrupoSacado;
    private Integer codInstrucao;
    private Integer codTipoMora;
    private Integer codTipoMulta;
    private Integer codTipoDesconto;
    private String xmlParcelas;

    private String codCedente;
    private String dataMovEntrada;
    private String descSiglaModalidade;
    private String descAvalista;
    private String numCpfCnpjAvalista;
    private String numDDDCedente;
    private String numTelefoneCedente;
    private String valorConv;

    private String localPagamento;
    private String siglaCoop;
    private String descEmailSacado;
    private String numContaCorrente;

    private Boolean imprimeVerso;
    private Integer codigoRetorno;
    private Boolean bolSacadoEletronico;

    private Boolean bolEnviaCIP;

    private DateTimeDB dataLimitePagamento;

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
     * @return o atributo numCodigoCanal
     */
    public Integer getNumCodigoCanal() {
        return numCodigoCanal;
    }

    /**
     * Define o atributo numCodigoCanal
     */
    public void setNumCodigoCanal(Integer numCodigoCanal) {
        this.numCodigoCanal = numCodigoCanal;
    }

    /**
     * @return o atributo terminal
     */
    public Integer getTerminal() {
        return terminal;
    }

    /**
     * Define o atributo terminal
     */
    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    /**
     * @return o atributo cooperativaCartao
     */
    public Integer getCooperativaCartao() {
        return cooperativaCartao;
    }

    /**
     * Define o atributo cooperativaCartao
     */
    public void setCooperativaCartao(Integer cooperativaCartao) {
        this.cooperativaCartao = cooperativaCartao;
    }

    /**
     * @return o atributo numeroPac
     */
    public Integer getNumeroPac() {
        return numeroPac;
    }

    /**
     * Define o atributo numeroPac
     */
    public void setNumeroPac(Integer numeroPac) {
        this.numeroPac = numeroPac;
    }

    /**
     * @return o atributo numeroCliente
     */
    public Integer getNumeroCliente() {
        return numeroCliente;
    }

    /**
     * Define o atributo numeroCliente
     */
    public void setNumeroCliente(Integer numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    /**
     * @return o atributo numeroTerminal
     */
    public Integer getNumeroTerminal() {
        return numeroTerminal;
    }

    /**
     * Define o atributo numeroTerminal
     */
    public void setNumeroTerminal(Integer numeroTerminal) {
        this.numeroTerminal = numeroTerminal;
    }

    /**
     * @return o atributo idProduto
     */
    public Integer getIdProduto() {
        return idProduto;
    }

    /**
     * Define o atributo idProduto
     */
    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return o atributo idModalidade
     */
    public Integer getIdModalidade() {
        return idModalidade;
    }

    /**
     * Define o atributo idModalidade
     */
    public void setIdModalidade(Integer idModalidade) {
        this.idModalidade = idModalidade;
    }

    /**
     * @return o atributo numeroTitulo
     */
    public Integer getNumeroTitulo() {
        return numeroTitulo;
    }

    /**
     * Define o atributo numeroTitulo
     */
    public void setNumeroTitulo(Integer numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    /**
     * @return o atributo dataCartaRemessa
     */
    public DateTimeDB getDataCartaRemessa() {
        return dataCartaRemessa;
    }

    /**
     * Define o atributo dataCartaRemessa
     */
    public void setDataCartaRemessa(DateTimeDB dataCartaRemessa) {
        this.dataCartaRemessa = dataCartaRemessa;
    }

    /**
     * @return o atributo descSeuNumero
     */
    public String getDescSeuNumero() {
        return descSeuNumero;
    }

    /**
     * Define o atributo descSeuNumero
     */
    public void setDescSeuNumero(String descSeuNumero) {
        this.descSeuNumero = descSeuNumero;
    }

    /**
     * @return o atributo dataEmissaoDocumento
     */
    public DateTimeDB getDataEmissaoDocumento() {
        return dataEmissaoDocumento;
    }

    /**
     * Define o atributo dataEmissaoDocumento
     */
    public void setDataEmissaoDocumento(DateTimeDB dataEmissaoDocumento) {
        this.dataEmissaoDocumento = dataEmissaoDocumento;
    }

    /**
     * @return o atributo codTipoVencimento
     */
    public Integer getCodTipoVencimento() {
        return codTipoVencimento;
    }

    /**
     * Define o atributo codTipoVencimento
     */
    public void setCodTipoVencimento(Integer codTipoVencimento) {
        this.codTipoVencimento = codTipoVencimento;
    }

    /**
     * @return o atributo dataVencimentoTitulo
     */
    public DateTimeDB getDataVencimentoTitulo() {
        return dataVencimentoTitulo;
    }

    /**
     * Define o atributo dataVencimentoTitulo
     */
    public void setDataVencimentoTitulo(DateTimeDB dataVencimentoTitulo) {
        this.dataVencimentoTitulo = dataVencimentoTitulo;
    }

    /**
     * @return o atributo qtdMonetaria
     */
    public BigDecimal getQtdMonetaria() {
        return qtdMonetaria;
    }

    /**
     * Define o atributo qtdMonetaria
     */
    public void setQtdMonetaria(BigDecimal qtdMonetaria) {
        this.qtdMonetaria = qtdMonetaria;
    }

    /**
     * @return o atributo valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * Define o atributo valor
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    /**
     * @return o atributo valorAbatimento
     */
    public BigDecimal getValorAbatimento() {
        return valorAbatimento;
    }

    /**
     * Define o atributo valorAbatimento
     */
    public void setValorAbatimento(BigDecimal valorAbatimento) {
        this.valorAbatimento = valorAbatimento;
    }

    /**
     * @return o atributo valorIoc
     */
    public BigDecimal getValorIoc() {
        return valorIoc;
    }

    /**
     * Define o atributo valorIoc
     */
    public void setValorIoc(BigDecimal valorIoc) {
        this.valorIoc = valorIoc;
    }

    /**
     * @return o atributo dataPrimDesconto
     */
    public DateTimeDB getDataPrimDesconto() {
        return dataPrimDesconto;
    }

    /**
     * Define o atributo dataPrimDesconto
     */
    public void setDataPrimDesconto(DateTimeDB dataPrimDesconto) {
        this.dataPrimDesconto = dataPrimDesconto;
    }

    /**
     * @return o atributo valorPrimDesconto
     */
    public BigDecimal getValorPrimDesconto() {
        return valorPrimDesconto;
    }

    /**
     * Define o atributo valorPrimDesconto
     */
    public void setValorPrimDesconto(BigDecimal valorPrimDesconto) {
        this.valorPrimDesconto = valorPrimDesconto;
    }

    /**
     * @return o atributo dataSegDesconto
     */
    public DateTimeDB getDataSegDesconto() {
        return dataSegDesconto;
    }

    /**
     * Define o atributo dataSegDesconto
     */
    public void setDataSegDesconto(DateTimeDB dataSegDesconto) {
        this.dataSegDesconto = dataSegDesconto;
    }

    /**
     * @return o atributo valorSegDesconto
     */
    public BigDecimal getValorSegDesconto() {
        return valorSegDesconto;
    }

    /**
     * Define o atributo valorSegDesconto
     */
    public void setValorSegDesconto(BigDecimal valorSegDesconto) {
        this.valorSegDesconto = valorSegDesconto;
    }

    /**
     * @return o atributo dataTerDesconto
     */
    public DateTimeDB getDataTerDesconto() {
        return dataTerDesconto;
    }

    /**
     * Define o atributo dataTerDesconto
     */
    public void setDataTerDesconto(DateTimeDB dataTerDesconto) {
        this.dataTerDesconto = dataTerDesconto;
    }

    /**
     * @return o atributo valorTerDesconto
     */
    public BigDecimal getValorTerDesconto() {
        return valorTerDesconto;
    }

    /**
     * Define o atributo valorTerDesconto
     */
    public void setValorTerDesconto(BigDecimal valorTerDesconto) {
        this.valorTerDesconto = valorTerDesconto;
    }

    /**
     * @return o atributo bolUtilizaPadraCh
     */
    public Boolean getBolUtilizaPadraCh() {
        return bolUtilizaPadraCh;
    }

    /**
     * Define o atributo bolUtilizaPadraCh
     */
    public void setBolUtilizaPadraCh(Boolean bolUtilizaPadraCh) {
        this.bolUtilizaPadraCh = bolUtilizaPadraCh;
    }

    /**
     * @return o atributo descUsoBanco
     */
    public String getDescUsoBanco() {
        return descUsoBanco;
    }

    /**
     * Define o atributo descUsoBanco
     */
    public void setDescUsoBanco(String descUsoBanco) {
        this.descUsoBanco = descUsoBanco;
    }

    /**
     * @return o atributo bolAceite
     */
    public Boolean getBolAceite() {
        return bolAceite;
    }

    /**
     * Define o atributo bolAceite
     */
    public void setBolAceite(Boolean bolAceite) {
        this.bolAceite = bolAceite;
    }

    /**
     * @return o atributo qtdDiasFloat
     */
    public Integer getQtdDiasFloat() {
        return qtdDiasFloat;
    }

    /**
     * Define o atributo qtdDiasFloat
     */
    public void setQtdDiasFloat(Integer qtdDiasFloat) {
        this.qtdDiasFloat = qtdDiasFloat;
    }

    /**
     * @return o atributo qtdDiasProtesto
     */
    public Integer getQtdDiasProtesto() {
        return qtdDiasProtesto;
    }

    /**
     * Define o atributo qtdDiasProtesto
     */
    public void setQtdDiasProtesto(Integer qtdDiasProtesto) {
        this.qtdDiasProtesto = qtdDiasProtesto;
    }

    /**
     * @return o atributo codTipoBloqueto
     */
    public Integer getCodTipoBloqueto() {
        return codTipoBloqueto;
    }

    /**
     * Define o atributo codTipoBloqueto
     */
    public void setCodTipoBloqueto(Integer codTipoBloqueto) {
        this.codTipoBloqueto = codTipoBloqueto;
    }

    /**
     * @return o atributo dataLiquidacao
     */
    public DateTimeDB getDataLiquidacao() {
        return dataLiquidacao;
    }

    /**
     * Define o atributo dataLiquidacao
     */
    public void setDataLiquidacao(DateTimeDB dataLiquidacao) {
        this.dataLiquidacao = dataLiquidacao;
    }

    /**
     * @return o atributo descInstCed1
     */
    public String getDescInstCed1() {
        return descInstCed1;
    }

    /**
     * Define o atributo descInstCed1
     */
    public void setDescInstCed1(String descInstCed1) {
        this.descInstCed1 = descInstCed1;
    }

    /**
     * @return o atributo descInstCed2
     */
    public String getDescInstCed2() {
        return descInstCed2;
    }

    /**
     * Define o atributo descInstCed2
     */
    public void setDescInstCed2(String descInstCed2) {
        this.descInstCed2 = descInstCed2;
    }

    /**
     * @return o atributo descInstCed3
     */
    public String getDescInstCed3() {
        return descInstCed3;
    }

    /**
     * Define o atributo descInstCed3
     */
    public void setDescInstCed3(String descInstCed3) {
        this.descInstCed3 = descInstCed3;
    }

    /**
     * @return o atributo descInstCed4
     */
    public String getDescInstCed4() {
        return descInstCed4;
    }

    /**
     * Define o atributo descInstCed4
     */
    public void setDescInstCed4(String descInstCed4) {
        this.descInstCed4 = descInstCed4;
    }

    /**
     * @return o atributo descInstCed5
     */
    public String getDescInstCed5() {
        return descInstCed5;
    }

    /**
     * Define o atributo descInstCed5
     */
    public void setDescInstCed5(String descInstCed5) {
        this.descInstCed5 = descInstCed5;
    }

    /**
     * @return o atributo percTaxaMulta
     */
    public BigDecimal getPercTaxaMulta() {
        return percTaxaMulta;
    }

    /**
     * Define o atributo percTaxaMulta
     */
    public void setPercTaxaMulta(BigDecimal percTaxaMulta) {
        this.percTaxaMulta = percTaxaMulta;
    }

    /**
     * @return o atributo percTaxaMora
     */
    public BigDecimal getPercTaxaMora() {
        return percTaxaMora;
    }

    /**
     * Define o atributo percTaxaMora
     */
    public void setPercTaxaMora(BigDecimal percTaxaMora) {
        this.percTaxaMora = percTaxaMora;
    }

    /**
     * @return o atributo numBancoDep
     */
    public Integer getNumBancoDep() {
        return numBancoDep;
    }

    /**
     * Define o atributo numBancoDep
     */
    public void setNumBancoDep(Integer numBancoDep) {
        this.numBancoDep = numBancoDep;
    }

    /**
     * @return o atributo numAgenciaDep
     */
    public Integer getNumAgenciaDep() {
        return numAgenciaDep;
    }

    /**
     * Define o atributo numAgenciaDep
     */
    public void setNumAgenciaDep(Integer numAgenciaDep) {
        this.numAgenciaDep = numAgenciaDep;
    }

    /**
     * @return o atributo numeroBordero
     */
    public Integer getNumeroBordero() {
        return numeroBordero;
    }

    /**
     * Define o atributo numeroBordero
     */
    public void setNumeroBordero(Integer numeroBordero) {
        this.numeroBordero = numeroBordero;
    }

    /**
     * @return o atributo codSituacao
     */
    public Integer getCodSituacao() {
        return codSituacao;
    }

    /**
     * Define o atributo codSituacao
     */
    public void setCodSituacao(Integer codSituacao) {
        this.codSituacao = codSituacao;
    }

    /**
     * @return o atributo dataEnvioProtesto
     */
    public DateTimeDB getDataEnvioProtesto() {
        return dataEnvioProtesto;
    }

    /**
     * Define o atributo dataEnvioProtesto
     */
    public void setDataEnvioProtesto(DateTimeDB dataEnvioProtesto) {
        this.dataEnvioProtesto = dataEnvioProtesto;
    }

    /**
     * @return o atributo numeroContaCorrente
     */
    public BigDecimal getNumeroContaCorrente() {
        return numeroContaCorrente;
    }

    /**
     * Define o atributo numeroContaCorrente
     */
    public void setNumeroContaCorrente(BigDecimal numeroContaCorrente) {
        this.numeroContaCorrente = numeroContaCorrente;
    }

    /**
     * @return o atributo codSituacaoFloat
     */
    public Integer getCodSituacaoFloat() {
        return codSituacaoFloat;
    }

    /**
     * Define o atributo codSituacaoFloat
     */
    public void setCodSituacaoFloat(Integer codSituacaoFloat) {
        this.codSituacaoFloat = codSituacaoFloat;
    }

    /**
     * @return o atributo codEmissaoBloq
     */
    public Integer getCodEmissaoBloq() {
        return codEmissaoBloq;
    }

    /**
     * Define o atributo codEmissaoBloq
     */
    public void setCodEmissaoBloq(Integer codEmissaoBloq) {
        this.codEmissaoBloq = codEmissaoBloq;
    }

    /**
     * @return o atributo idIndice
     */
    public Integer getIdIndice() {
        return idIndice;
    }

    /**
     * Define o atributo idIndice
     */
    public void setIdIndice(Integer idIndice) {
        this.idIndice = idIndice;
    }

    /**
     * @return o atributo numParcela
     */
    public Integer getNumParcela() {
        return numParcela;
    }

    /**
     * Define o atributo numParcela
     */
    public void setNumParcela(Integer numParcela) {
        this.numParcela = numParcela;
    }

    /**
     * @return o atributo dataPrevisaoCredito
     */
    public DateTimeDB getDataPrevisaoCredito() {
        return dataPrevisaoCredito;
    }

    /**
     * Define o atributo dataPrevisaoCredito
     */
    public void setDataPrevisaoCredito(DateTimeDB dataPrevisaoCredito) {
        this.dataPrevisaoCredito = dataPrevisaoCredito;
    }

    /**
     * @return o atributo numContratoOperCredito
     */
    public Integer getNumContratoOperCredito() {
        return numContratoOperCredito;
    }

    /**
     * Define o atributo numContratoOperCredito
     */
    public void setNumContratoOperCredito(Integer numContratoOperCredito) {
        this.numContratoOperCredito = numContratoOperCredito;
    }

    /**
     * @return o atributo identCnab
     */
    public String getIdentCnab() {
        return identCnab;
    }

    /**
     * Define o atributo identCnab
     */
    public void setIdentCnab(String identCnab) {
        this.identCnab = identCnab;
    }

    /**
     * @return o atributo codCritProtesto
     */
    public Integer getCodCritProtesto() {
        return codCritProtesto;
    }

    /**
     * Define o atributo codCritProtesto
     */
    public void setCodCritProtesto(Integer codCritProtesto) {
        this.codCritProtesto = codCritProtesto;
    }

    /**
     * @return o atributo dataEmissaoAvSacado
     */
    public DateTimeDB getDataEmissaoAvSacado() {
        return dataEmissaoAvSacado;
    }

    /**
     * Define o atributo dataEmissaoAvSacado
     */
    public void setDataEmissaoAvSacado(DateTimeDB dataEmissaoAvSacado) {
        this.dataEmissaoAvSacado = dataEmissaoAvSacado;
    }

    /**
     * @return o atributo bolEmiteAviso
     */
    public Boolean getBolEmiteAviso() {
        return bolEmiteAviso;
    }

    /**
     * Define o atributo bolEmiteAviso
     */
    public void setBolEmiteAviso(Boolean bolEmiteAviso) {
        this.bolEmiteAviso = bolEmiteAviso;
    }

    /**
     * @return o atributo idAvisoVencimento
     */
    public Integer getIdAvisoVencimento() {
        return idAvisoVencimento;
    }

    /**
     * Define o atributo idAvisoVencimento
     */
    public void setIdAvisoVencimento(Integer idAvisoVencimento) {
        this.idAvisoVencimento = idAvisoVencimento;
    }

    /**
     * @return o atributo idCodInstrucao
     */
    public Integer getIdCodInstrucao() {
        return idCodInstrucao;
    }

    /**
     * Define o atributo idCodInstrucao
     */
    public void setIdCodInstrucao(Integer idCodInstrucao) {
        this.idCodInstrucao = idCodInstrucao;
    }

    /**
     * @return o atributo idTipoHistorico
     */
    public Integer getIdTipoHistorico() {
        return idTipoHistorico;
    }

    /**
     * Define o atributo idTipoHistorico
     */
    public void setIdTipoHistorico(Integer idTipoHistorico) {
        this.idTipoHistorico = idTipoHistorico;
    }

    /**
     * @return o atributo dataBordero
     */
    public DateTimeDB getDataBordero() {
        return dataBordero;
    }

    /**
     * Define o atributo dataBordero
     */
    public void setDataBordero(DateTimeDB dataBordero) {
        this.dataBordero = dataBordero;
    }

    /**
     * @return o atributo numPessoaFuncGer
     */
    public Integer getNumPessoaFuncGer() {
        return numPessoaFuncGer;
    }

    /**
     * Define o atributo numPessoaFuncGer
     */
    public void setNumPessoaFuncGer(Integer numPessoaFuncGer) {
        this.numPessoaFuncGer = numPessoaFuncGer;
    }

    /**
     * @return o atributo numAgenciaDepAnt
     */
    public Integer getNumAgenciaDepAnt() {
        return numAgenciaDepAnt;
    }

    /**
     * Define o atributo numAgenciaDepAnt
     */
    public void setNumAgenciaDepAnt(Integer numAgenciaDepAnt) {
        this.numAgenciaDepAnt = numAgenciaDepAnt;
    }

    /**
     * @return o atributo origemImportacao
     */
    public Integer getOrigemImportacao() {
        return origemImportacao;
    }

    /**
     * Define o atributo origemImportacao
     */
    public void setOrigemImportacao(Integer origemImportacao) {
        this.origemImportacao = origemImportacao;
    }

    /**
     * @return o atributo codTipoPraca
     */
    public Integer getCodTipoPraca() {
        return codTipoPraca;
    }

    /**
     * Define o atributo codTipoPraca
     */
    public void setCodTipoPraca(Integer codTipoPraca) {
        this.codTipoPraca = codTipoPraca;
    }

    /**
     * @return o atributo codTipoPracaAnterior
     */
    public Integer getCodTipoPracaAnterior() {
        return codTipoPracaAnterior;
    }

    /**
     * Define o atributo codTipoPracaAnterior
     */
    public void setCodTipoPracaAnterior(Integer codTipoPracaAnterior) {
        this.codTipoPracaAnterior = codTipoPracaAnterior;
    }

    /**
     * @return o atributo idNumBordero
     */
    public Integer getIdNumBordero() {
        return idNumBordero;
    }

    /**
     * Define o atributo idNumBordero
     */
    public void setIdNumBordero(Integer idNumBordero) {
        this.idNumBordero = idNumBordero;
    }

    /**
     * @return o atributo idCnab
     */
    public String getIdCnab() {
        return idCnab;
    }

    /**
     * Define o atributo idCnab
     */
    public void setIdCnab(String idCnab) {
        this.idCnab = idCnab;
    }

    /**
     * @return o atributo codLocalConvenio
     */
    public Integer getCodLocalConvenio() {
        return codLocalConvenio;
    }

    /**
     * Define o atributo codLocalConvenio
     */
    public void setCodLocalConvenio(Integer codLocalConvenio) {
        this.codLocalConvenio = codLocalConvenio;
    }

    /**
     * @return o atributo idSeqConvenio
     */
    public Integer getIdSeqConvenio() {
        return idSeqConvenio;
    }

    /**
     * Define o atributo idSeqConvenio
     */
    public void setIdSeqConvenio(Integer idSeqConvenio) {
        this.idSeqConvenio = idSeqConvenio;
    }

    /**
     * @return o atributo descNomeSacador
     */
    public String getDescNomeSacador() {
        return descNomeSacador;
    }

    /**
     * Define o atributo descNomeSacador
     */
    public void setDescNomeSacador(String descNomeSacador) {
        this.descNomeSacador = descNomeSacador;
    }

    /**
     * @return o atributo numCgcCpfSacador
     */
    public String getNumCgcCpfSacador() {
        return numCgcCpfSacador;
    }

    /**
     * Define o atributo numCgcCpfSacador
     */
    public void setNumCgcCpfSacador(String numCgcCpfSacador) {
        this.numCgcCpfSacador = numCgcCpfSacador;
    }

    /**
     * @return o atributo nossoNumero
     */
    public BigDecimal getNossoNumero() {
        return nossoNumero;
    }

    /**
     * Define o atributo nossoNumero
     */
    public void setNossoNumero(BigDecimal nossoNumero) {
        this.nossoNumero = nossoNumero;
    }

    /**
     * @return o atributo nossoNumeroString
     */
    public String getNossoNumeroString() {
        return nossoNumeroString;
    }

    /**
     * Define o atributo nossoNumeroString
     */
    public void setNossoNumeroString(String nossoNumeroString) {
        this.nossoNumeroString = nossoNumeroString;
    }

    /**
     * @return o atributo nossoNumeroStringRetornoSP
     */
    public String getNossoNumeroStringRetornoSP() {
        return nossoNumeroStringRetornoSP;
    }

    /**
     * Define o atributo nossoNumeroStringRetornoSP
     */
    public void setNossoNumeroStringRetornoSP(String nossoNumeroStringRetornoSP) {
        this.nossoNumeroStringRetornoSP = nossoNumeroStringRetornoSP;
    }

    /**
     * @return o atributo descNomeSacado
     */
    public String getDescNomeSacado() {
        return descNomeSacado;
    }

    /**
     * Define o atributo descNomeSacado
     */
    public void setDescNomeSacado(String descNomeSacado) {
        this.descNomeSacado = descNomeSacado;
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
     * @return o atributo numDdd
     */
    public String getNumDdd() {
        return numDdd;
    }

    /**
     * Define o atributo numDdd
     */
    public void setNumDdd(String numDdd) {
        this.numDdd = numDdd;
    }

    /**
     * @return o atributo numTelefone
     */
    public String getNumTelefone() {
        return numTelefone;
    }

    /**
     * Define o atributo numTelefone
     */
    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    /**
     * @return o atributo numRamal
     */
    public String getNumRamal() {
        return numRamal;
    }

    /**
     * Define o atributo numRamal
     */
    public void setNumRamal(String numRamal) {
        this.numRamal = numRamal;
    }

    /**
     * @return o atributo dataNascimento
     */
    public DateTimeDB getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define o atributo dataNascimento
     */
    public void setDataNascimento(DateTimeDB dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return o atributo bolRecebeBoletoEletronico
     */
    public Boolean getBolRecebeBoletoEletronico() {
        return bolRecebeBoletoEletronico;
    }

    /**
     * Define o atributo bolRecebeBoletoEletronico
     */
    public void setBolRecebeBoletoEletronico(Boolean bolRecebeBoletoEletronico) {
        this.bolRecebeBoletoEletronico = bolRecebeBoletoEletronico;
    }

    /**
     * @return o atributo descEndereco
     */
    public String getDescEndereco() {
        return descEndereco;
    }

    /**
     * Define o atributo descEndereco
     */
    public void setDescEndereco(String descEndereco) {
        this.descEndereco = descEndereco;
    }

    /**
     * @return o atributo descNomeBairro
     */
    public String getDescNomeBairro() {
        return descNomeBairro;
    }

    /**
     * Define o atributo descNomeBairro
     */
    public void setDescNomeBairro(String descNomeBairro) {
        this.descNomeBairro = descNomeBairro;
    }

    /**
     * @return o atributo descNomeCidade
     */
    public String getDescNomeCidade() {
        return descNomeCidade;
    }

    /**
     * Define o atributo descNomeCidade
     */
    public void setDescNomeCidade(String descNomeCidade) {
        this.descNomeCidade = descNomeCidade;
    }

    /**
     * @return o atributo descCep
     */
    public String getDescCep() {
        return descCep;
    }

    /**
     * Define o atributo descCep
     */
    public void setDescCep(String descCep) {
        this.descCep = descCep;
    }

    /**
     * @return o atributo codigoMunicipio
     */
    public Integer getCodigoMunicipio() {
        return codigoMunicipio;
    }

    /**
     * Define o atributo codigoMunicipio
     */
    public void setCodigoMunicipio(Integer codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    /**
     * @return o atributo descEmail
     */
    public String getDescEmail() {
        return descEmail;
    }

    /**
     * Define o atributo descEmail
     */
    public void setDescEmail(String descEmail) {
        this.descEmail = descEmail;
    }

    /**
     * @return o atributo chaveAcessoWeb
     */
    public String getChaveAcessoWeb() {
        return chaveAcessoWeb;
    }

    /**
     * Define o atributo chaveAcessoWeb
     */
    public void setChaveAcessoWeb(String chaveAcessoWeb) {
        this.chaveAcessoWeb = chaveAcessoWeb;
    }

    /**
     * @return o atributo numCpfCnpjCedente
     */
    public String getNumCpfCnpjCedente() {
        return numCpfCnpjCedente;
    }

    /**
     * Define o atributo numCpfCnpjCedente
     */
    public void setNumCpfCnpjCedente(String numCpfCnpjCedente) {
        this.numCpfCnpjCedente = numCpfCnpjCedente;
    }

    /**
     * @return o atributo descNomePessoa
     */
    public String getDescNomePessoa() {
        return descNomePessoa;
    }

    /**
     * Define o atributo descNomePessoa
     */
    public void setDescNomePessoa(String descNomePessoa) {
        this.descNomePessoa = descNomePessoa;
    }

    /**
     * @return o atributo descEnderecoCedente
     */
    public String getDescEnderecoCedente() {
        return descEnderecoCedente;
    }

    /**
     * Define o atributo descEnderecoCedente
     */
    public void setDescEnderecoCedente(String descEnderecoCedente) {
        this.descEnderecoCedente = descEnderecoCedente;
    }

    /**
     * @return o atributo descNumeroCedente
     */
    public String getDescNumeroCedente() {
        return descNumeroCedente;
    }

    /**
     * Define o atributo descNumeroCedente
     */
    public void setDescNumeroCedente(String descNumeroCedente) {
        this.descNumeroCedente = descNumeroCedente;
    }

    /**
     * @return o atributo descComplementoCedente
     */
    public String getDescComplementoCedente() {
        return descComplementoCedente;
    }

    /**
     * Define o atributo descComplementoCedente
     */
    public void setDescComplementoCedente(String descComplementoCedente) {
        this.descComplementoCedente = descComplementoCedente;
    }

    /**
     * @return o atributo descBairroCedente
     */
    public String getDescBairroCedente() {
        return descBairroCedente;
    }

    /**
     * Define o atributo descBairroCedente
     */
    public void setDescBairroCedente(String descBairroCedente) {
        this.descBairroCedente = descBairroCedente;
    }

    /**
     * @return o atributo descUFCedente
     */
    public String getDescUFCedente() {
        return descUFCedente;
    }

    /**
     * Define o atributo descUFCedente
     */
    public void setDescUFCedente(String descUFCedente) {
        this.descUFCedente = descUFCedente;
    }

    /**
     * @return o atributo descCidadeCedente
     */
    public String getDescCidadeCedente() {
        return descCidadeCedente;
    }

    /**
     * Define o atributo descCidadeCedente
     */
    public void setDescCidadeCedente(String descCidadeCedente) {
        this.descCidadeCedente = descCidadeCedente;
    }

    /**
     * @return o atributo numCEPCedente
     */
    public Integer getNumCEPCedente() {
        return numCEPCedente;
    }

    /**
     * Define o atributo numCEPCedente
     */
    public void setNumCEPCedente(Integer numCEPCedente) {
        this.numCEPCedente = numCEPCedente;
    }

    /**
     * @return o atributo codEspDocumento
     */
    public String getCodEspDocumento() {
        return codEspDocumento;
    }

    /**
     * Define o atributo codEspDocumento
     */
    public void setCodEspDocumento(String codEspDocumento) {
        this.codEspDocumento = codEspDocumento;
    }

    /**
     * @return o atributo numCodigoBarras
     */
    public String getNumCodigoBarras() {
        return numCodigoBarras;
    }

    /**
     * Define o atributo numCodigoBarras
     */
    public void setNumCodigoBarras(String numCodigoBarras) {
        this.numCodigoBarras = numCodigoBarras;
    }

    /**
     * @return o atributo numLinhaDigitavel
     */
    public String getNumLinhaDigitavel() {
        return numLinhaDigitavel;
    }

    /**
     * Define o atributo numLinhaDigitavel
     */
    public void setNumLinhaDigitavel(String numLinhaDigitavel) {
        this.numLinhaDigitavel = numLinhaDigitavel;
    }

    /**
     * @return o atributo numLinhaDigitavelFormatada
     */
    public String getNumLinhaDigitavelFormatada() {
        return numLinhaDigitavelFormatada;
    }

    /**
     * Define o atributo numLinhaDigitavelFormatada
     */
    public void setNumLinhaDigitavelFormatada(String numLinhaDigitavelFormatada) {
        this.numLinhaDigitavelFormatada = numLinhaDigitavelFormatada;
    }

    /**
     * @return o atributo numCodMoeda
     */
    public String getNumCodMoeda() {
        return numCodMoeda;
    }

    /**
     * Define o atributo numCodMoeda
     */
    public void setNumCodMoeda(String numCodMoeda) {
        this.numCodMoeda = numCodMoeda;
    }

    /**
     * @return o atributo numBanco
     */
    public Integer getNumBanco() {
        return numBanco;
    }

    /**
     * Define o atributo numBanco
     */
    public void setNumBanco(Integer numBanco) {
        this.numBanco = numBanco;
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
     * @return o atributo codOperacao
     */
    public Integer getCodOperacao() {
        return codOperacao;
    }

    /**
     * Define o atributo codOperacao
     */
    public void setCodOperacao(Integer codOperacao) {
        this.codOperacao = codOperacao;
    }

    /**
     * @return o atributo idModalidadeAnterior
     */
    public Integer getIdModalidadeAnterior() {
        return idModalidadeAnterior;
    }

    /**
     * Define o atributo idModalidadeAnterior
     */
    public void setIdModalidadeAnterior(Integer idModalidadeAnterior) {
        this.idModalidadeAnterior = idModalidadeAnterior;
    }

    /**
     * @return o atributo numCartaRemessa
     */
    public Integer getNumCartaRemessa() {
        return numCartaRemessa;
    }

    /**
     * Define o atributo numCartaRemessa
     */
    public void setNumCartaRemessa(Integer numCartaRemessa) {
        this.numCartaRemessa = numCartaRemessa;
    }

    /**
     * @return o atributo numAvisoSacado
     */
    public Integer getNumAvisoSacado() {
        return numAvisoSacado;
    }

    /**
     * Define o atributo numAvisoSacado
     */
    public void setNumAvisoSacado(Integer numAvisoSacado) {
        this.numAvisoSacado = numAvisoSacado;
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
     * @return o atributo descComplemente
     */
    public String getDescComplemente() {
        return descComplemente;
    }

    /**
     * Define o atributo descComplemente
     */
    public void setDescComplemente(String descComplemente) {
        this.descComplemente = descComplemente;
    }

    /**
     * @return o atributo origemMunicipio
     */
    public Integer getOrigemMunicipio() {
        return origemMunicipio;
    }

    /**
     * Define o atributo origemMunicipio
     */
    public void setOrigemMunicipio(Integer origemMunicipio) {
        this.origemMunicipio = origemMunicipio;
    }

    /**
     * @return o atributo bolEnderecoAtivo
     */
    public Boolean getBolEnderecoAtivo() {
        return bolEnderecoAtivo;
    }

    /**
     * Define o atributo bolEnderecoAtivo
     */
    public void setBolEnderecoAtivo(Boolean bolEnderecoAtivo) {
        this.bolEnderecoAtivo = bolEnderecoAtivo;
    }

    /**
     * @return o atributo idPerfilTrarifirioCob
     */
    public Integer getIdPerfilTrarifirioCob() {
        return idPerfilTrarifirioCob;
    }

    /**
     * Define o atributo idPerfilTrarifirioCob
     */
    public void setIdPerfilTrarifirioCob(Integer idPerfilTrarifirioCob) {
        this.idPerfilTrarifirioCob = idPerfilTrarifirioCob;
    }

    /**
     * @return o atributo codTipoStatus
     */
    public Integer getCodTipoStatus() {
        return codTipoStatus;
    }

    /**
     * Define o atributo codTipoStatus
     */
    public void setCodTipoStatus(Integer codTipoStatus) {
        this.codTipoStatus = codTipoStatus;
    }

    /**
     * @return o atributo codGrupoSacado
     */
    public Integer getCodGrupoSacado() {
        return codGrupoSacado;
    }

    /**
     * Define o atributo codGrupoSacado
     */
    public void setCodGrupoSacado(Integer codGrupoSacado) {
        this.codGrupoSacado = codGrupoSacado;
    }

    /**
     * @return o atributo codInstrucao
     */
    public Integer getCodInstrucao() {
        return codInstrucao;
    }

    /**
     * Define o atributo codInstrucao
     */
    public void setCodInstrucao(Integer codInstrucao) {
        this.codInstrucao = codInstrucao;
    }

    /**
     * @return o atributo codTipoMora
     */
    public Integer getCodTipoMora() {
        return codTipoMora;
    }

    /**
     * Define o atributo codTipoMora
     */
    public void setCodTipoMora(Integer codTipoMora) {
        this.codTipoMora = codTipoMora;
    }

    /**
     * @return o atributo codTipoMulta
     */
    public Integer getCodTipoMulta() {
        return codTipoMulta;
    }

    /**
     * Define o atributo codTipoMulta
     */
    public void setCodTipoMulta(Integer codTipoMulta) {
        this.codTipoMulta = codTipoMulta;
    }

    /**
     * @return o atributo codTipoDesconto
     */
    public Integer getCodTipoDesconto() {
        return codTipoDesconto;
    }

    /**
     * Define o atributo codTipoDesconto
     */
    public void setCodTipoDesconto(Integer codTipoDesconto) {
        this.codTipoDesconto = codTipoDesconto;
    }

    /**
     * @return o atributo xmlParcelas
     */
    public String getXmlParcelas() {
        return xmlParcelas;
    }

    /**
     * Define o atributo xmlParcelas
     */
    public void setXmlParcelas(String xmlParcelas) {
        this.xmlParcelas = xmlParcelas;
    }

    /**
     * @return o atributo codCedente
     */
    public String getCodCedente() {
        return codCedente;
    }

    /**
     * Define o atributo codCedente
     */
    public void setCodCedente(String codCedente) {
        this.codCedente = codCedente;
    }

    /**
     * @return o atributo dataMovEntrada
     */
    public String getDataMovEntrada() {
        return dataMovEntrada;
    }

    /**
     * Define o atributo dataMovEntrada
     */
    public void setDataMovEntrada(String dataMovEntrada) {
        this.dataMovEntrada = dataMovEntrada;
    }

    /**
     * @return o atributo descSiglaModalidade
     */
    public String getDescSiglaModalidade() {
        return descSiglaModalidade;
    }

    /**
     * Define o atributo descSiglaModalidade
     */
    public void setDescSiglaModalidade(String descSiglaModalidade) {
        this.descSiglaModalidade = descSiglaModalidade;
    }

    /**
     * @return o atributo descAvalista
     */
    public String getDescAvalista() {
        return descAvalista;
    }

    /**
     * Define o atributo descAvalista
     */
    public void setDescAvalista(String descAvalista) {
        this.descAvalista = descAvalista;
    }

    /**
     * @return o atributo numCpfCnpjAvalista
     */
    public String getNumCpfCnpjAvalista() {
        return numCpfCnpjAvalista;
    }

    /**
     * Define o atributo numCpfCnpjAvalista
     */
    public void setNumCpfCnpjAvalista(String numCpfCnpjAvalista) {
        this.numCpfCnpjAvalista = numCpfCnpjAvalista;
    }

    /**
     * @return o atributo numDDDCedente
     */
    public String getNumDDDCedente() {
        return numDDDCedente;
    }

    /**
     * Define o atributo numDDDCedente
     */
    public void setNumDDDCedente(String numDDDCedente) {
        this.numDDDCedente = numDDDCedente;
    }

    /**
     * @return o atributo numTelefoneCedente
     */
    public String getNumTelefoneCedente() {
        return numTelefoneCedente;
    }

    /**
     * Define o atributo numTelefoneCedente
     */
    public void setNumTelefoneCedente(String numTelefoneCedente) {
        this.numTelefoneCedente = numTelefoneCedente;
    }

    /**
     * @return o atributo valorConv
     */
    public String getValorConv() {
        return valorConv;
    }

    /**
     * Define o atributo valorConv
     */
    public void setValorConv(String valorConv) {
        this.valorConv = valorConv;
    }

    /**
     * @return o atributo localPagamento
     */
    public String getLocalPagamento() {
        return localPagamento;
    }

    /**
     * Define o atributo localPagamento
     */
    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    /**
     * @return o atributo siglaCoop
     */
    public String getSiglaCoop() {
        return siglaCoop;
    }

    /**
     * Define o atributo siglaCoop
     */
    public void setSiglaCoop(String siglaCoop) {
        this.siglaCoop = siglaCoop;
    }

    /**
     * @return o atributo descEmailSacado
     */
    public String getDescEmailSacado() {
        return descEmailSacado;
    }

    /**
     * Define o atributo descEmailSacado
     */
    public void setDescEmailSacado(String descEmailSacado) {
        this.descEmailSacado = descEmailSacado;
    }

    /**
     * @return o atributo numContaCorrente
     */
    public String getNumContaCorrente() {
        return numContaCorrente;
    }

    /**
     * Define o atributo numContaCorrente
     */
    public void setNumContaCorrente(String numContaCorrente) {
        this.numContaCorrente = numContaCorrente;
    }

    /**
     * @return o atributo imprimeVerso
     */
    public Boolean getImprimeVerso() {
        return imprimeVerso;
    }

    /**
     * Define o atributo imprimeVerso
     */
    public void setImprimeVerso(Boolean imprimeVerso) {
        this.imprimeVerso = imprimeVerso;
    }

    /**
     * @return o atributo codigoRetorno
     */
    public Integer getCodigoRetorno() {
        return codigoRetorno;
    }

    /**
     * Define o atributo codigoRetorno
     */
    public void setCodigoRetorno(Integer codigoRetorno) {
        this.codigoRetorno = codigoRetorno;
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
     * @return o atributo bolEnviaCIP
     */
    public Boolean getBolEnviaCIP() {
        return bolEnviaCIP;
    }

    /**
     * Define o atributo bolEnviaCIP
     */
    public void setBolEnviaCIP(Boolean bolEnviaCIP) {
        this.bolEnviaCIP = bolEnviaCIP;
    }

    /**
     * @return o atributo dataLimitePagamento
     */
    public DateTimeDB getDataLimitePagamento() {
        return dataLimitePagamento;
    }

    /**
     * Define o atributo dataLimitePagamento
     */
    public void setDataLimitePagamento(DateTimeDB dataLimitePagamento) {
        this.dataLimitePagamento = dataLimitePagamento;
    }

}
