package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Tayrone.Oliveira
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EspecieDocumentoDeParaDto")
public class EspecieDocumentoDeParaDTO extends BancoobDTO {

    private String siglaEspecieDocumento;
    private Integer idEspecieDocumentoCip;
    private String descEspecieDocumento;

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
