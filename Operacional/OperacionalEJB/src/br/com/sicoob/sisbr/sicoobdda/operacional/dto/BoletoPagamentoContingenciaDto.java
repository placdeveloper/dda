/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         BoletoPagamentoContingenciaDto.java
 * Data Criação:    Feb 1, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * BoletoPagamentoContingenciaDto é responsável por
 * 
 * @author Danilo.Barros
 */
public class BoletoPagamentoContingenciaDto extends BancoobDto {
    private static final long serialVersionUID = 1L;
    private Date dataMovimento;
    private String numCodigoBarra;

    /**
     * @param dataMovimento
     * @param numCodigoBarra
     */
    public BoletoPagamentoContingenciaDto(Date dataMovimento, String numCodigoBarra) {
        super();
        this.dataMovimento = dataMovimento;
        this.numCodigoBarra = numCodigoBarra;
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

}
