package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * PesquisaBoletoDDADto é responsável por
 * 
 * @author george.santos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.PesquisaBoletoDDADTO")
public class PesquisaBoletoDDADto extends BancoobDto {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String numBanco;

    private String numCpfCnpjBeneficiario;
    private String numCpfCnpjPagador;
    private String numCpfCnpjPagadorAgregado;
    private String numCpfCnpjTerceiro;

    private String numCodigoBarra;
    private String numCodigoBarraCampoLivre;
    private String numLinhaDigitavel;

    private DateTimeDB dataVencimentoInicial;
    private DateTimeDB dataVencimentoFinal;

    private DateTimeDB dataRegistroDDAInicial;
    private DateTimeDB dataRegistroDDAFinal;

    private String numSeuNumero;

    private String codSituacaoBoleto;

    private DateTimeDB dataProcessamento;
    private DateTimeDB dataHoraEntrada;
    private DateTimeDB dataVencimento;
    private BigDecimal valorBoleto;

    private String descSituacaoBoletoCIP;
    private String descSituacaoBoletoSicoob;
    private String numCooperativa;

    private String descTipoPagador;

    public PesquisaBoletoDDADto() {
    }

    public PesquisaBoletoDDADto(String numBanco, Date dataProcessamento, String idTipoSituacaoBoleto, String numCodigoBarra, Date dataHoraEntrada, Date dataVencimento,
            BigDecimal valorBoleto) throws ComumNegocioException {
        this.numBanco = numBanco;
        this.dataProcessamento = new DateTimeDB(dataProcessamento.getTime());
        this.descSituacaoBoletoSicoob = getDescTipoSituacaoBoleto(idTipoSituacaoBoleto);
        this.numCodigoBarra = numCodigoBarra;
        if (numCodigoBarra.startsWith(Constantes.BANCOOB)) {
            this.numCooperativa = String.valueOf(LinhaDigitavelCodigoBarrasUtil.obterNumeroCooperativaPorCodigoBarras(numCodigoBarra));
        }
        this.dataHoraEntrada = new DateTimeDB(dataHoraEntrada.getTime());
        this.dataVencimento = new DateTimeDB(dataVencimento.getTime());
        this.valorBoleto = valorBoleto;
    }

    /**
     * @return o atributo numBanco
     */
    public String getNumBanco() {
        return numBanco;
    }

    /**
     * Define o atributo numBanco
     */
    public void setNumBanco(String numBanco) {
        this.numBanco = numBanco;
    }

    /**
     * @return o atributo numCpfCnpjBeneficiario
     */
    public String getNumCpfCnpjBeneficiario() {
        return numCpfCnpjBeneficiario;
    }

    /**
     * Define o atributo numCpfCnpjBeneficiario
     */
    public void setNumCpfCnpjBeneficiario(String numCpfCnpjBeneficiario) {
        this.numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
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
     * @return o atributo numCpfCnpjPagadorAgregado
     */
    public String getNumCpfCnpjPagadorAgregado() {
        return numCpfCnpjPagadorAgregado;
    }

    /**
     * Define o atributo numCpfCnpjPagadorAgregado
     */
    public void setNumCpfCnpjPagadorAgregado(String numCpfCnpjPagadorAgregado) {
        this.numCpfCnpjPagadorAgregado = numCpfCnpjPagadorAgregado;
    }

    /**
     * @return o atributo numCpfCnpjTerceiro
     */
    public String getNumCpfCnpjTerceiro() {
        return numCpfCnpjTerceiro;
    }

    /**
     * Define o atributo numCpfCnpjTerceiro
     */
    public void setNumCpfCnpjTerceiro(String numCpfCnpjTerceiro) {
        this.numCpfCnpjTerceiro = numCpfCnpjTerceiro;
    }

    /**
     * @return o atributo numCodigoBarra
     */
    public String getNumCodigoBarra() {
        return numCodigoBarra;
    }

    /**
     * Define o atributo numCodigoBarra
     */
    public void setNumCodigoBarra(String numCodigoBarra) {
        this.numCodigoBarra = numCodigoBarra;
    }

    /**
     * @return numCodigoBarraCampoLivre
     */
    public String getNumCodigoBarraCampoLivre() {
        return numCodigoBarraCampoLivre;
    }

    /**
     * @param numCodigoBarraCampoLivre
     */
    public void setNumCodigoBarraCampoLivre(String numCodigoBarraCampoLivre) {
        this.numCodigoBarraCampoLivre = numCodigoBarraCampoLivre;
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
     * @return o atributo dataVencimentoInicial
     */
    public DateTimeDB getDataVencimentoInicial() {
        return dataVencimentoInicial;
    }

    /**
     * Define o atributo dataVencimentoInicial
     */
    public void setDataVencimentoInicial(DateTimeDB dataVencimentoInicial) {
        this.dataVencimentoInicial = dataVencimentoInicial;
    }

    /**
     * @return o atributo dataVencimentoFinal
     */
    public DateTimeDB getDataVencimentoFinal() {
        return dataVencimentoFinal;
    }

    /**
     * Define o atributo dataVencimentoFinal
     */
    public void setDataVencimentoFinal(DateTimeDB dataVencimentoFinal) {
        this.dataVencimentoFinal = dataVencimentoFinal;
    }

    /**
     * @return o atributo dataRegistroDDAInicial
     */
    public DateTimeDB getDataRegistroDDAInicial() {
        return dataRegistroDDAInicial;
    }

    /**
     * Define o atributo dataRegistroDDAInicial
     */
    public void setDataRegistroDDAInicial(DateTimeDB dataRegistroDDAInicial) {
        this.dataRegistroDDAInicial = dataRegistroDDAInicial;
    }

    /**
     * @return o atributo dataRegistroDDAFinal
     */
    public DateTimeDB getDataRegistroDDAFinal() {
        return dataRegistroDDAFinal;
    }

    /**
     * Define o atributo dataRegistroDDAFinal
     */
    public void setDataRegistroDDAFinal(DateTimeDB dataRegistroDDAFinal) {
        this.dataRegistroDDAFinal = dataRegistroDDAFinal;
    }

    /**
     * @return o atributo numSeuNumero
     */
    public String getNumSeuNumero() {
        return numSeuNumero;
    }

    /**
     * Define o atributo numSeuNumero
     */
    public void setNumSeuNumero(String numSeuNumero) {
        this.numSeuNumero = numSeuNumero;
    }

    /**
     * @return o atributo codSituacaoBoleto
     */
    public String getCodSituacaoBoleto() {
        return codSituacaoBoleto;
    }

    /**
     * Define o atributo codSituacaoBoleto
     */
    public void setCodSituacaoBoleto(String codSituacaoBoleto) {
        this.codSituacaoBoleto = codSituacaoBoleto;
    }

    /**
     * @return o atributo dataProcessamento
     */
    public DateTimeDB getDataProcessamento() {
        return dataProcessamento;
    }

    /**
     * Define o atributo dataProcessamento
     */
    public void setDataProcessamento(DateTimeDB dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }

    /**
     * @return o atributo dataHoraEntrada
     */
    public DateTimeDB getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    /**
     * Define o atributo dataHoraEntrada
     */
    public void setDataHoraEntrada(DateTimeDB dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    /**
     * @return o atributo dataVencimento
     */
    public DateTimeDB getDataVencimento() {
        return dataVencimento;
    }

    /**
     * Define o atributo dataVencimento
     */
    public void setDataVencimento(DateTimeDB dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    /**
     * @return o atributo valorBoleto
     */
    public BigDecimal getValorBoleto() {
        return valorBoleto;
    }

    /**
     * Define o atributo valorBoleto
     */
    public void setValorBoleto(BigDecimal valorBoleto) {
        this.valorBoleto = valorBoleto;
    }

    /**
     * @return o atributo descSituacaoBoletoCIP
     */
    public String getDescSituacaoBoletoCIP() {
        return descSituacaoBoletoCIP;
    }

    /**
     * Define o atributo descSituacaoBoletoCIP
     */
    public void setDescSituacaoBoletoCIP(String descSituacaoBoletoCIP) {
        this.descSituacaoBoletoCIP = descSituacaoBoletoCIP;
    }

    /**
     * @return o atributo descSituacaoBoletoSicoob
     */
    public String getDescSituacaoBoletoSicoob() {
        return descSituacaoBoletoSicoob;
    }

    /**
     * Define o atributo descSituacaoBoletoSicoob
     */
    public void setDescSituacaoBoletoSicoob(String descSituacaoBoletoSicoob) {
        this.descSituacaoBoletoSicoob = descSituacaoBoletoSicoob;
    }

    /**
     * @return o atributo numCooperativa
     */
    public String getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(String numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return o atributo descTipoPagador
     */
    public String getDescTipoPagador() {
        return descTipoPagador;
    }

    /**
     * Define o atributo descTipoPagador
     */
    public void setDescTipoPagador(String descTipoPagador) {
        this.descTipoPagador = descTipoPagador;
    }

    private String getDescTipoSituacaoBoleto(String idTipoSituacaoBoleto) {
        if (Integer.valueOf(idTipoSituacaoBoleto) == Constantes.SITUACAO_BOLETO_EM_ABERTO) {
            return SituacaoBoleto.EM_ABERTO;
        } else if (Integer.valueOf(idTipoSituacaoBoleto) == Constantes.SITUACAO_BOLETO_BAIXADO) {
            return SituacaoBoleto.BAIXADO;
        } else if (Integer.valueOf(idTipoSituacaoBoleto) == Constantes.SITUACAO_BOLETO_LIQUIDADO) {
            return SituacaoBoleto.LIQUIDADO;
        }
        return "";
    }
}
