/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MensagemContingenciaDto.java
 * Data Criação:    Aug 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.sql.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * MensagemContingenciaDto é responsável por
 * 
 * @author felipe.rosa
 */
public class MensagemContingenciaDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -2839415150857726867L;

    private DateTimeDB dataMovimento;
    private String codTipoMensagem;
    private Long idMensagemInicial;
    private Long idMensagemFinal;
    private Integer qtdRegistros;

    /**
     * @param dataMovimento
     * @param codTipoMensagem
     * @param idMensagemInicial
     * @param idMensagemFinal
     * @param qtdRegistros
     */
    public MensagemContingenciaDto(Date dataMovimento, String codTipoMensagem, Long idMensagemInicial, Long idMensagemFinal, Integer qtdRegistros) {
        super();
        if (!ObjectUtil.isNull(dataMovimento)) {
            this.dataMovimento = new DateTimeDB(dataMovimento.getTime());
        }
        this.codTipoMensagem = codTipoMensagem;
        this.idMensagemInicial = idMensagemInicial;
        this.idMensagemFinal = idMensagemFinal;
        this.qtdRegistros = qtdRegistros;
    }

    /**
     * @return o atributo dataMovimento
     */
    public DateTimeDB getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Define o atributo dataMovimento
     */
    public void setDataMovimento(DateTimeDB dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo codTipoMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * Define o atributo codTipoMensagem
     */
    public void setCodTipoMensagem(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }

    /**
     * @return o atributo idMensagemInicial
     */
    public Long getIdMensagemInicial() {
        return idMensagemInicial;
    }

    /**
     * Define o atributo idMensagemInicial
     */
    public void setIdMensagemInicial(Long idMensagemInicial) {
        this.idMensagemInicial = idMensagemInicial;
    }

    /**
     * @return o atributo idMensagemFinal
     */
    public Long getIdMensagemFinal() {
        return idMensagemFinal;
    }

    /**
     * Define o atributo idMensagemFinal
     */
    public void setIdMensagemFinal(Long idMensagemFinal) {
        this.idMensagemFinal = idMensagemFinal;
    }

    /**
     * @return o atributo qtdRegistros
     */
    public Integer getQtdRegistros() {
        return qtdRegistros;
    }

    /**
     * Define o atributo qtdRegistros
     */
    public void setQtdRegistros(Integer qtdRegistros) {
        this.qtdRegistros = qtdRegistros;
    }

}
