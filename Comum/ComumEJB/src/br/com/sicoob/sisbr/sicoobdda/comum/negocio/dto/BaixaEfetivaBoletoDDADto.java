package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * BaixaEfetivaBoletoDDADto é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class BaixaEfetivaBoletoDDADto extends BancoobDto {

    private static final long serialVersionUID = -6574218372214920056L;

    private Long numBaixaEfetiva;

    private Long numRefAtualBaixaEfetiva;

    private Long numSeqAtualizacaoBaixaEfetiva;

    private DateTimeDB dataProcessamentoBaixaEfetiva;

    private DateTimeDB dataHoraProcessamentoBaixaEfetiva;

    private Double valorBaixaEfetivaBoleto;

    private String numCodigoBarrasBaixaEfetiva;

    private Short canalPagamento;

    private Short meioPagamento;

    private DateTimeDB dataHoraSituacaoBaixaEfetiva;

    /**
     * @return o atributo numBaixaEfetiva
     */
    public Long getNumBaixaEfetiva() {
        return numBaixaEfetiva;
    }

    /**
     * Define o atributo numBaixaEfetiva
     */
    public void setNumBaixaEfetiva(Long numBaixaEfetiva) {
        this.numBaixaEfetiva = numBaixaEfetiva;
    }

    /**
     * @return o atributo numRefAtualBaixaEfetiva
     */
    public Long getNumRefAtualBaixaEfetiva() {
        return numRefAtualBaixaEfetiva;
    }

    /**
     * Define o atributo numRefAtualBaixaEfetiva
     */
    public void setNumRefAtualBaixaEfetiva(Long numRefAtualBaixaEfetiva) {
        this.numRefAtualBaixaEfetiva = numRefAtualBaixaEfetiva;
    }

    /**
     * @return o atributo numSeqAtualizacaoBaixaEfetiva
     */
    public Long getNumSeqAtualizacaoBaixaEfetiva() {
        return numSeqAtualizacaoBaixaEfetiva;
    }

    /**
     * Define o atributo numSeqAtualizacaoBaixaEfetiva
     */
    public void setNumSeqAtualizacaoBaixaEfetiva(Long numSeqAtualizacaoBaixaEfetiva) {
        this.numSeqAtualizacaoBaixaEfetiva = numSeqAtualizacaoBaixaEfetiva;
    }

    /**
     * @return o atributo dataProcessamentoBaixaEfetiva
     */
    public DateTimeDB getDataProcessamentoBaixaEfetiva() {
        return dataProcessamentoBaixaEfetiva;
    }

    /**
     * Define o atributo dataProcessamentoBaixaEfetiva
     */
    public void setDataProcessamentoBaixaEfetiva(DateTimeDB dataProcessamentoBaixaEfetiva) {
        this.dataProcessamentoBaixaEfetiva = dataProcessamentoBaixaEfetiva;
    }

    /**
     * @return o atributo dataHoraProcessamentoBaixaEfetiva
     */
    public DateTimeDB getDataHoraProcessamentoBaixaEfetiva() {
        return dataHoraProcessamentoBaixaEfetiva;
    }

    /**
     * Define o atributo dataHoraProcessamentoBaixaEfetiva
     */
    public void setDataHoraProcessamentoBaixaEfetiva(DateTimeDB dataHoraProcessamentoBaixaEfetiva) {
        this.dataHoraProcessamentoBaixaEfetiva = dataHoraProcessamentoBaixaEfetiva;
    }

    /**
     * @return o atributo valorBaixaEfetivaBoleto
     */
    public Double getValorBaixaEfetivaBoleto() {
        return valorBaixaEfetivaBoleto;
    }

    /**
     * Define o atributo valorBaixaEfetivaBoleto
     */
    public void setValorBaixaEfetivaBoleto(Double valorBaixaEfetivaBoleto) {
        this.valorBaixaEfetivaBoleto = valorBaixaEfetivaBoleto;
    }

    /**
     * @return o atributo numCodigoBarrasBaixaEfetiva
     */
    public String getNumCodigoBarrasBaixaEfetiva() {
        return numCodigoBarrasBaixaEfetiva;
    }

    /**
     * Define o atributo numCodigoBarrasBaixaEfetiva
     */
    public void setNumCodigoBarrasBaixaEfetiva(String numCodigoBarrasBaixaEfetiva) {
        this.numCodigoBarrasBaixaEfetiva = numCodigoBarrasBaixaEfetiva;
    }

    /**
     * @return o atributo canalPagamento
     */
    public Short getCanalPagamento() {
        return canalPagamento;
    }

    /**
     * Define o atributo canalPagamento
     */
    public void setCanalPagamento(Short canalPagamento) {
        this.canalPagamento = canalPagamento;
    }

    /**
     * @return o atributo meioPagamento
     */
    public Short getMeioPagamento() {
        return meioPagamento;
    }

    /**
     * Define o atributo meioPagamento
     */
    public void setMeioPagamento(Short meioPagamento) {
        this.meioPagamento = meioPagamento;
    }

    /**
     * @return o atributo dataHoraSituacaoBaixaEfetiva
     */
    public DateTimeDB getDataHoraSituacaoBaixaEfetiva() {
        return dataHoraSituacaoBaixaEfetiva;
    }

    /**
     * Define o atributo dataHoraSituacaoBaixaEfetiva
     */
    public void setDataHoraSituacaoBaixaEfetiva(DateTimeDB dataHoraSituacaoBaixaEfetiva) {
        this.dataHoraSituacaoBaixaEfetiva = dataHoraSituacaoBaixaEfetiva;
    }

}
