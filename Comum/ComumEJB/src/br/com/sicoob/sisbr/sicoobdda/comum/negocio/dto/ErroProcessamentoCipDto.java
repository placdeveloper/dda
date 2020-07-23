/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ErroProcessamentoRetornoCipDto.java
 * Data Criação:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.sql.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * ErroProcessamentoCipDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.ErroProcessamentoCipDTO")
public class ErroProcessamentoCipDto extends BancoobDto {

    private static final long serialVersionUID = 4375798219497552092L;

    private String tipoErro;
    private DateTime dataMovimento;
    private Integer qtdErroProcessamento;

    /**
     * 
     */
    public ErroProcessamentoCipDto() {
        super();
    }

    /**
     * Construtor Query - OBTER_LISTA_ERRO_PROCESSAMENTO_CIP
     * 
     * @param tipoErro
     * @param dataMovimento
     * @param qtdErroProcessamento
     */
    public ErroProcessamentoCipDto(String tipoErro, Date dataMovimento, Integer qtdErroProcessamento) {
        super();
        this.tipoErro = tipoErro;
        this.dataMovimento = new DateTimeDB(dataMovimento.getTime());
        this.qtdErroProcessamento = qtdErroProcessamento;
    }

    /**
     * @return the tipoErro
     */
    public String getTipoErro() {
        return tipoErro;
    }

    /**
     * @param tipoErro the tipoErro to set
     */
    public void setTipoErro(String tipoErro) {
        this.tipoErro = tipoErro;
    }

    /**
     * @return the dataMovimento
     */
    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the qtdErroProcessamento
     */
    public Integer getQtdErroProcessamento() {
        return qtdErroProcessamento;
    }

    /**
     * @param qtdErroProcessamento the qtdErroProcessamento to set
     */
    public void setQtdErroProcessamento(Integer qtdErroProcessamento) {
        this.qtdErroProcessamento = qtdErroProcessamento;
    }

}
