package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * AgendamentoDto é responsável por
 * 
 * @author rodrigo.neri
 */
public class AgendamentoDto extends BancoobDto {

    private static final long serialVersionUID = 1446927207664440121L;

    private Long numIdentificadorBoletoCip;
    private Double valorPagamento;
    private DateTimeDB dataPagamento;

    /**
     * @return o atributo numIdentificadorBoletoCip
     */
    public Long getNumIdentificadorBoletoCip() {
        return numIdentificadorBoletoCip;
    }

    /**
     * Define o atributo numIdentificadorBoletoCip
     */
    public void setNumIdentificadorBoletoCip(Long numIdentificadorBoletoCip) {
        this.numIdentificadorBoletoCip = numIdentificadorBoletoCip;
    }

    /**
     * @return o atributo valorPagamento
     */
    public Double getValorPagamento() {
        return valorPagamento;
    }

    /**
     * Define o atributo valorPagamento
     */
    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    /**
     * @return o atributo dataPagamento
     */
    public DateTimeDB getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Define o atributo dataPagamento
     */
    public void setDataPagamento(DateTimeDB dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}