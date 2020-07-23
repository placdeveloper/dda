package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import java.math.BigDecimal;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author rodrigo.neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.Cliente")
public class ClienteVO extends BancoobVO {

    private Long id;
    private Boolean autorizaConsulta;
    private Boolean cobrarCPMF;
    private Boolean cobrarIOF;
    private Boolean cobrarIR;
    private Long codNivelRiscoPerfil;
    private Long codUtilPadraoCNAB;
    private DateTime dataCadastramentoCliente;
    private DateTime dataCadastramentoSFN;
    private DateTime dataConglomerado;
    private DateTime dataRisco;
    private DateTime dataUltimaAtualizacao;
    private String descParecer;
    private Boolean emiteAvisoLancto;
    private Long idConglomerado;
    private Character idNivelRisco;
    private Long idPerfilTarifario;
    private Long idPerfilTarifarioCob;
    private Long idTipoMovCPMFIsencao;
    private String motivoRisco;
    private Long numCooperativa;
    private Long numCooperativaCliente;
    private Long numNucleo;
    private Long numPac;
    private Long numPacCliente;
    private Long numPessoaFuncGer;
    private Long numSequencialCNAB;
    private Long qtdDiasFloatPadrao;
    private Long qtdDiasProtestoPadrao;
    private Long senhaGeral;
    private Long ultimoNossoNumero;
    private Long ultimoNumeroContrato;
    private Long ultimoNumPedido;
    private BigDecimal valorMaxDescontoCheque;
    private BigDecimal valorMaxDescontoTitulo;
    private PessoaVO pessoa;

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
     * @return o atributo autorizaConsulta
     */
    public Boolean getAutorizaConsulta() {
        return autorizaConsulta;
    }

    /**
     * Define o atributo autorizaConsulta
     */
    public void setAutorizaConsulta(Boolean autorizaConsulta) {
        this.autorizaConsulta = autorizaConsulta;
    }

    /**
     * @return o atributo cobrarCPMF
     */
    public Boolean getCobrarCPMF() {
        return cobrarCPMF;
    }

    /**
     * Define o atributo cobrarCPMF
     */
    public void setCobrarCPMF(Boolean cobrarCPMF) {
        this.cobrarCPMF = cobrarCPMF;
    }

    /**
     * @return o atributo cobrarIOF
     */
    public Boolean getCobrarIOF() {
        return cobrarIOF;
    }

    /**
     * Define o atributo cobrarIOF
     */
    public void setCobrarIOF(Boolean cobrarIOF) {
        this.cobrarIOF = cobrarIOF;
    }

    /**
     * @return o atributo cobrarIR
     */
    public Boolean getCobrarIR() {
        return cobrarIR;
    }

    /**
     * Define o atributo cobrarIR
     */
    public void setCobrarIR(Boolean cobrarIR) {
        this.cobrarIR = cobrarIR;
    }

    /**
     * @return o atributo codNivelRiscoPerfil
     */
    public Long getCodNivelRiscoPerfil() {
        return codNivelRiscoPerfil;
    }

    /**
     * Define o atributo codNivelRiscoPerfil
     */
    public void setCodNivelRiscoPerfil(Long codNivelRiscoPerfil) {
        this.codNivelRiscoPerfil = codNivelRiscoPerfil;
    }

    /**
     * @return o atributo codUtilPadraoCNAB
     */
    public Long getCodUtilPadraoCNAB() {
        return codUtilPadraoCNAB;
    }

    /**
     * Define o atributo codUtilPadraoCNAB
     */
    public void setCodUtilPadraoCNAB(Long codUtilPadraoCNAB) {
        this.codUtilPadraoCNAB = codUtilPadraoCNAB;
    }

    /**
     * @return o atributo dataCadastramentoCliente
     */
    public DateTime getDataCadastramentoCliente() {
        return dataCadastramentoCliente;
    }

    /**
     * Define o atributo dataCadastramentoCliente
     */
    public void setDataCadastramentoCliente(DateTime dataCadastramentoCliente) {
        this.dataCadastramentoCliente = dataCadastramentoCliente;
    }

    /**
     * @return o atributo dataCadastramentoSFN
     */
    public DateTime getDataCadastramentoSFN() {
        return dataCadastramentoSFN;
    }

    /**
     * Define o atributo dataCadastramentoSFN
     */
    public void setDataCadastramentoSFN(DateTime dataCadastramentoSFN) {
        this.dataCadastramentoSFN = dataCadastramentoSFN;
    }

    /**
     * @return o atributo dataConglomerado
     */
    public DateTime getDataConglomerado() {
        return dataConglomerado;
    }

    /**
     * Define o atributo dataConglomerado
     */
    public void setDataConglomerado(DateTime dataConglomerado) {
        this.dataConglomerado = dataConglomerado;
    }

    /**
     * @return o atributo dataRisco
     */
    public DateTime getDataRisco() {
        return dataRisco;
    }

    /**
     * Define o atributo dataRisco
     */
    public void setDataRisco(DateTime dataRisco) {
        this.dataRisco = dataRisco;
    }

    /**
     * @return o atributo dataUltimaAtualizacao
     */
    public DateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    /**
     * Define o atributo dataUltimaAtualizacao
     */
    public void setDataUltimaAtualizacao(DateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    /**
     * @return o atributo descParecer
     */
    public String getDescParecer() {
        return descParecer;
    }

    /**
     * Define o atributo descParecer
     */
    public void setDescParecer(String descParecer) {
        this.descParecer = descParecer;
    }

    /**
     * @return o atributo emiteAvisoLancto
     */
    public Boolean getEmiteAvisoLancto() {
        return emiteAvisoLancto;
    }

    /**
     * Define o atributo emiteAvisoLancto
     */
    public void setEmiteAvisoLancto(Boolean emiteAvisoLancto) {
        this.emiteAvisoLancto = emiteAvisoLancto;
    }

    /**
     * @return o atributo idConglomerado
     */
    public Long getIdConglomerado() {
        return idConglomerado;
    }

    /**
     * Define o atributo idConglomerado
     */
    public void setIdConglomerado(Long idConglomerado) {
        this.idConglomerado = idConglomerado;
    }

    /**
     * @return o atributo idNivelRisco
     */
    public Character getIdNivelRisco() {
        return idNivelRisco;
    }

    /**
     * Define o atributo idNivelRisco
     */
    public void setIdNivelRisco(Character idNivelRisco) {
        this.idNivelRisco = idNivelRisco;
    }

    /**
     * @return o atributo idPerfilTarifario
     */
    public Long getIdPerfilTarifario() {
        return idPerfilTarifario;
    }

    /**
     * Define o atributo idPerfilTarifario
     */
    public void setIdPerfilTarifario(Long idPerfilTarifario) {
        this.idPerfilTarifario = idPerfilTarifario;
    }

    /**
     * @return o atributo idPerfilTarifarioCob
     */
    public Long getIdPerfilTarifarioCob() {
        return idPerfilTarifarioCob;
    }

    /**
     * Define o atributo idPerfilTarifarioCob
     */
    public void setIdPerfilTarifarioCob(Long idPerfilTarifarioCob) {
        this.idPerfilTarifarioCob = idPerfilTarifarioCob;
    }

    /**
     * @return o atributo idTipoMovCPMFIsencao
     */
    public Long getIdTipoMovCPMFIsencao() {
        return idTipoMovCPMFIsencao;
    }

    /**
     * Define o atributo idTipoMovCPMFIsencao
     */
    public void setIdTipoMovCPMFIsencao(Long idTipoMovCPMFIsencao) {
        this.idTipoMovCPMFIsencao = idTipoMovCPMFIsencao;
    }

    /**
     * @return o atributo motivoRisco
     */
    public String getMotivoRisco() {
        return motivoRisco;
    }

    /**
     * Define o atributo motivoRisco
     */
    public void setMotivoRisco(String motivoRisco) {
        this.motivoRisco = motivoRisco;
    }

    /**
     * @return o atributo numCooperativa
     */
    public Long getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(Long numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return o atributo numCooperativaCliente
     */
    public Long getNumCooperativaCliente() {
        return numCooperativaCliente;
    }

    /**
     * Define o atributo numCooperativaCliente
     */
    public void setNumCooperativaCliente(Long numCooperativaCliente) {
        this.numCooperativaCliente = numCooperativaCliente;
    }

    /**
     * @return o atributo numNucleo
     */
    public Long getNumNucleo() {
        return numNucleo;
    }

    /**
     * Define o atributo numNucleo
     */
    public void setNumNucleo(Long numNucleo) {
        this.numNucleo = numNucleo;
    }

    /**
     * @return o atributo numPac
     */
    public Long getNumPac() {
        return numPac;
    }

    /**
     * Define o atributo numPac
     */
    public void setNumPac(Long numPac) {
        this.numPac = numPac;
    }

    /**
     * @return o atributo numPacCliente
     */
    public Long getNumPacCliente() {
        return numPacCliente;
    }

    /**
     * Define o atributo numPacCliente
     */
    public void setNumPacCliente(Long numPacCliente) {
        this.numPacCliente = numPacCliente;
    }

    /**
     * @return o atributo numPessoaFuncGer
     */
    public Long getNumPessoaFuncGer() {
        return numPessoaFuncGer;
    }

    /**
     * Define o atributo numPessoaFuncGer
     */
    public void setNumPessoaFuncGer(Long numPessoaFuncGer) {
        this.numPessoaFuncGer = numPessoaFuncGer;
    }

    /**
     * @return o atributo numSequencialCNAB
     */
    public Long getNumSequencialCNAB() {
        return numSequencialCNAB;
    }

    /**
     * Define o atributo numSequencialCNAB
     */
    public void setNumSequencialCNAB(Long numSequencialCNAB) {
        this.numSequencialCNAB = numSequencialCNAB;
    }

    /**
     * @return o atributo qtdDiasFloatPadrao
     */
    public Long getQtdDiasFloatPadrao() {
        return qtdDiasFloatPadrao;
    }

    /**
     * Define o atributo qtdDiasFloatPadrao
     */
    public void setQtdDiasFloatPadrao(Long qtdDiasFloatPadrao) {
        this.qtdDiasFloatPadrao = qtdDiasFloatPadrao;
    }

    /**
     * @return o atributo qtdDiasProtestoPadrao
     */
    public Long getQtdDiasProtestoPadrao() {
        return qtdDiasProtestoPadrao;
    }

    /**
     * Define o atributo qtdDiasProtestoPadrao
     */
    public void setQtdDiasProtestoPadrao(Long qtdDiasProtestoPadrao) {
        this.qtdDiasProtestoPadrao = qtdDiasProtestoPadrao;
    }

    /**
     * @return o atributo senhaGeral
     */
    public Long getSenhaGeral() {
        return senhaGeral;
    }

    /**
     * Define o atributo senhaGeral
     */
    public void setSenhaGeral(Long senhaGeral) {
        this.senhaGeral = senhaGeral;
    }

    /**
     * @return o atributo ultimoNossoNumero
     */
    public Long getUltimoNossoNumero() {
        return ultimoNossoNumero;
    }

    /**
     * Define o atributo ultimoNossoNumero
     */
    public void setUltimoNossoNumero(Long ultimoNossoNumero) {
        this.ultimoNossoNumero = ultimoNossoNumero;
    }

    /**
     * @return o atributo ultimoNumeroContrato
     */
    public Long getUltimoNumeroContrato() {
        return ultimoNumeroContrato;
    }

    /**
     * Define o atributo ultimoNumeroContrato
     */
    public void setUltimoNumeroContrato(Long ultimoNumeroContrato) {
        this.ultimoNumeroContrato = ultimoNumeroContrato;
    }

    /**
     * @return o atributo ultimoNumPedido
     */
    public Long getUltimoNumPedido() {
        return ultimoNumPedido;
    }

    /**
     * Define o atributo ultimoNumPedido
     */
    public void setUltimoNumPedido(Long ultimoNumPedido) {
        this.ultimoNumPedido = ultimoNumPedido;
    }

    /**
     * @return o atributo valorMaxDescontoCheque
     */
    public BigDecimal getValorMaxDescontoCheque() {
        return valorMaxDescontoCheque;
    }

    /**
     * Define o atributo valorMaxDescontoCheque
     */
    public void setValorMaxDescontoCheque(BigDecimal valorMaxDescontoCheque) {
        this.valorMaxDescontoCheque = valorMaxDescontoCheque;
    }

    /**
     * @return o atributo valorMaxDescontoTitulo
     */
    public BigDecimal getValorMaxDescontoTitulo() {
        return valorMaxDescontoTitulo;
    }

    /**
     * Define o atributo valorMaxDescontoTitulo
     */
    public void setValorMaxDescontoTitulo(BigDecimal valorMaxDescontoTitulo) {
        this.valorMaxDescontoTitulo = valorMaxDescontoTitulo;
    }

    /**
     * @return o atributo pessoa
     */
    public PessoaVO getPessoa() {
        return pessoa;
    }

    /**
     * Define o atributo pessoa
     */
    public void setPessoa(PessoaVO pessoa) {
        this.pessoa = pessoa;
    }

}
