/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         EspecieDocumentoDeParaDto.java
 * Data Criação:    Aug 15, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * EspecieDocumentoDeParaDto é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.EspecieDocumentoDeParaDTO")
public class EspecieDocumentoDeParaDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String siglaEspecieDocumento;
    private Integer idEspecieDocumentoCip;
    private String descEspecieDocumento;

    /**
     * @param siglaEspecieDocumento
     * @param idEspecieDocumentoCip
     * @param desEspecieDocumento
     */
    public EspecieDocumentoDeParaDto(String siglaEspecieDocumento, Integer idEspecieDocumentoCip, String desEspecieDocumento) {
        super();
        this.siglaEspecieDocumento = siglaEspecieDocumento;
        this.idEspecieDocumentoCip = idEspecieDocumentoCip;
        this.descEspecieDocumento = desEspecieDocumento;
    }

    /**
     * @return o atributo siglaEspecieDocumento
     */
    public String getSiglaEspecieDocumento() {
        return siglaEspecieDocumento;
    }

    /**
     * Define o atributo siglaEspecieDocumento
     */
    public void setSiglaEspecieDocumento(String siglaEspecieDocumento) {
        this.siglaEspecieDocumento = siglaEspecieDocumento;
    }

    /**
     * @return o atributo idEspecieDocumentoCip
     */
    public Integer getIdEspecieDocumentoCip() {
        return idEspecieDocumentoCip;
    }

    /**
     * Define o atributo idEspecieDocumentoCip
     */
    public void setIdEspecieDocumentoCip(Integer idEspecieDocumentoCip) {
        this.idEspecieDocumentoCip = idEspecieDocumentoCip;
    }

    /**
     * @return o atributo descEspecieDocumento
     */
    public String getDescEspecieDocumento() {
        return descEspecieDocumento;
    }

    /**
     * Define o atributo descEspecieDocumento
     */
    public void setDescEspecieDocumento(String descEspecieDocumento) {
        this.descEspecieDocumento = descEspecieDocumento;
    }

}
