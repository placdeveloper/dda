/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         DetMonitoracaoDto.java
 * Data Criação:    Dec 13, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.sql.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * DetMonitoracaoDto é responsável por
 * 
 * @author felipe.rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DetMonitoracaoDTO")
public class DetMonitoracaoDto extends BancoobDto {

    private static final long serialVersionUID = -289382496866182035L;

    private DateTimeDB dataMovimento;
    private String codTipoMensagem;

    private Long qtdEnviar;
    private Long qtdSemProtocolo;
    private Long qtdSemRetorno;
    private Long qtdComErro;

    /**
     * 
     */
    public DetMonitoracaoDto() {
        super();
    }

    /**
     * @param dataMovimento
     * @param codTipoMensagem
     * @param qtdEnviar
     * @param qtdSemProtocolo
     * @param qtdSemRetorno
     * @param qtdComErro
     */
    public DetMonitoracaoDto(Date dataMovimento, String codTipoMensagem, Long qtdEnviar, Long qtdSemProtocolo, Long qtdSemRetorno, Long qtdComErro) {
        super();
        this.dataMovimento = DateUtil.getDateTimeDB(dataMovimento);
        this.codTipoMensagem = codTipoMensagem;
        this.qtdEnviar = qtdEnviar;
        this.qtdSemProtocolo = qtdSemProtocolo;
        this.qtdSemRetorno = qtdSemRetorno;
        this.qtdComErro = qtdComErro;
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
     * @return o atributo qtdEnviar
     */
    public Long getQtdEnviar() {
        return qtdEnviar;
    }

    /**
     * Define o atributo qtdEnviar
     */
    public void setQtdEnviar(Long qtdEnviar) {
        this.qtdEnviar = qtdEnviar;
    }

    /**
     * @return o atributo qtdSemProtocolo
     */
    public Long getQtdSemProtocolo() {
        return qtdSemProtocolo;
    }

    /**
     * Define o atributo qtdSemProtocolo
     */
    public void setQtdSemProtocolo(Long qtdSemProtocolo) {
        this.qtdSemProtocolo = qtdSemProtocolo;
    }

    /**
     * @return o atributo qtdSemRetorno
     */
    public Long getQtdSemRetorno() {
        return qtdSemRetorno;
    }

    /**
     * Define o atributo qtdSemRetorno
     */
    public void setQtdSemRetorno(Long qtdSemRetorno) {
        this.qtdSemRetorno = qtdSemRetorno;
    }

    /**
     * @return o atributo qtdComErro
     */
    public Long getQtdComErro() {
        return qtdComErro;
    }

    /**
     * Define o atributo qtdComErro
     */
    public void setQtdComErro(Long qtdComErro) {
        this.qtdComErro = qtdComErro;
    }

}
