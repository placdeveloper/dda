package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;

/**
 * ConsultaMovimentoSicoobDDADto é responsável por
 * 
 * @author rodrigo.neri
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ConsultaMovimentoSicoobDDADTO")
public class ConsultaMovimentoSicoobDDADto extends BancoobDto {

    private static final long serialVersionUID = 2259334823395425249L;

    private String numCodBarras;
    private String nomeBeneficiario;
    private String cpfCnpjBeneficiario;
    private DateTimeDB dataEntrada;
    private DateTimeDB dataVencimento;
    private BigDecimal valor;
    private DateTimeDB dataEvento;
    private Long idEventoConsolidadoDDA;
    private Long idEventoConsolidadoDDADetalhe;
    private String numCooperativa;

    // Utilizado no relatório
    private String numLinhaDigitavel;

    public ConsultaMovimentoSicoobDDADto() {
    }

    public ConsultaMovimentoSicoobDDADto(String numCodBarras, String nomeBeneficiario, String cpfCnpjBeneficiario, Date dataEntrada, Date dataVencimento, BigDecimal valor) {
        this.numCodBarras = numCodBarras;
        this.nomeBeneficiario = nomeBeneficiario;
        this.cpfCnpjBeneficiario = cpfCnpjBeneficiario;
        this.dataEntrada = dataEntrada != null ? new DateTimeDB(dataEntrada.getTime()) : null;
        this.dataVencimento = dataVencimento != null ? new DateTimeDB(dataVencimento.getTime()) : null;
    }

    /**
     * @return o atributo numCodBarras
     */
    public String getNumCodBarras() {
        return numCodBarras;
    }

    /**
     * Define o atributo numCodBarras
     */
    public void setNumCodBarras(String numCodBarras) {
        this.numCodBarras = numCodBarras;
    }

    /**
     * @return o atributo nomeBeneficiario
     */
    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    /**
     * Define o atributo nomeBeneficiario
     */
    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    /**
     * @return o atributo cpfCnpjBeneficiario
     */
    public String getCpfCnpjBeneficiario() {
        return cpfCnpjBeneficiario;
    }

    /**
     * Define o atributo cpfCnpjBeneficiario
     */
    public void setCpfCnpjBeneficiario(String cpfCnpjBeneficiario) {
        this.cpfCnpjBeneficiario = cpfCnpjBeneficiario;
    }

    /**
     * @return o atributo dataEntrada
     */
    public DateTimeDB getDataEntrada() {
        return dataEntrada;
    }

    /**
     * Define o atributo dataEntrada
     */
    public void setDataEntrada(DateTimeDB dataEntrada) {
        this.dataEntrada = dataEntrada;
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
     * @return o atributo dataEvento
     */
    public DateTimeDB getDataEvento() {
        return dataEvento;
    }

    /**
     * Define o atributo dataEvento
     */
    public void setDataEvento(DateTimeDB dataEvento) {
        this.dataEvento = dataEvento;
    }

    /**
     * @return o atributo idEventoConsolidadoDDA
     */
    public Long getIdEventoConsolidadoDDA() {
        return idEventoConsolidadoDDA;
    }

    /**
     * Define o atributo idEventoConsolidadoDDA
     */
    public void setIdEventoConsolidadoDDA(Long idEventoConsolidadoDDA) {
        this.idEventoConsolidadoDDA = idEventoConsolidadoDDA;
    }

    /**
     * @return o atributo idEventoConsolidadoDDADetalhe
     */
    public Long getIdEventoConsolidadoDDADetalhe() {
        return idEventoConsolidadoDDADetalhe;
    }

    /**
     * Define o atributo idEventoConsolidadoDDADetalhe
     */
    public void setIdEventoConsolidadoDDADetalhe(Long idEventoConsolidadoDDADetalhe) {
        this.idEventoConsolidadoDDADetalhe = idEventoConsolidadoDDADetalhe;
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
     * Método responsável por formatar o CPF/CNPJ
     * 
     * @return
     */
    public String getCpfCnpjBeneficiarioFormatado() {
        if (ObjectUtil.isEmpty(cpfCnpjBeneficiario)) {
            return "";
        }

        return FormatadorUtil.formatar(cpfCnpjBeneficiario, cpfCnpjBeneficiario.length() == Constantes.TAMANHO_CPF ? FormatadorUtil.MASCARA_CPF : FormatadorUtil.MASCARA_CNPJ);
    }

    /**
     * Método responsável por formatar o código de barras/linha digitável
     * 
     * @return
     */
    public String getNumCodBarrasLinhaDigitavelFormatado() {
        if (ObjectUtil.isEmpty(numLinhaDigitavel)) {
            return numCodBarras;
        } else {
            return FormatadorUtil.formatar(numLinhaDigitavel, FormatadorUtil.MASCARA_LINHA_DIGITAVEL);
        }
    }

    /**
     * Método responsável por ver se a linha digitável não foi informada
     * 
     * @return
     */
    public Boolean isNumCodBarras() {
        return ObjectUtil.isEmpty(numLinhaDigitavel);
    }

}
