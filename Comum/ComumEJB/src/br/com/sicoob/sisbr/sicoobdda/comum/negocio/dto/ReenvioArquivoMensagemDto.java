/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         ErroBenficiarioCipDto.java
 * Data Criação:    Sep 11, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * ReenvioArquivoMensagemDto é responsável por
 * 
 * @author George.Santos
 */
public class ReenvioArquivoMensagemDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -7978916278120143356L;

    private String codTipoMensagemArquivo;
    private String codMensagemArquivo;
    private List<String> listaNumeroOperacao;
    private DateTimeDB dataMovimento;

    /**
     * 
     */
    public ReenvioArquivoMensagemDto() {
        super();
    }

    /**
     * @param codTipoMensagemArquivo
     * @param codMensagemArquivo
     * @param listaNumeroOperacao
     * @param dataMovimento
     */
    public ReenvioArquivoMensagemDto(String codTipoMensagemArquivo, String codMensagemArquivo, List<String> listaNumeroOperacao, DateTimeDB dataMovimento) {
        super();
        this.codTipoMensagemArquivo = codTipoMensagemArquivo;
        this.codMensagemArquivo = codMensagemArquivo;
        this.listaNumeroOperacao = listaNumeroOperacao;
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo codTipoMensagemArquivo
     */
    public String getCodTipoMensagemArquivo() {
        return codTipoMensagemArquivo;
    }

    /**
     * Define o atributo codTipoMensagemArquivo
     */
    public void setCodTipoMensagemArquivo(String codTipoMensagemArquivo) {
        this.codTipoMensagemArquivo = codTipoMensagemArquivo;
    }

    /**
     * @return o atributo codMensagemArquivo
     */
    public String getCodMensagemArquivo() {
        return codMensagemArquivo;
    }

    /**
     * Define o atributo codMensagemArquivo
     */
    public void setCodMensagemArquivo(String codMensagemArquivo) {
        this.codMensagemArquivo = codMensagemArquivo;
    }

    /**
     * @return o atributo listaNumeroOperacao
     */
    public List<String> getListaNumeroOperacao() {
        return listaNumeroOperacao;
    }

    /**
     * Define o atributo listaNumeroOperacao
     */
    public void setListaNumeroOperacao(List<String> listaNumeroOperacao) {
        this.listaNumeroOperacao = listaNumeroOperacao;
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

}
