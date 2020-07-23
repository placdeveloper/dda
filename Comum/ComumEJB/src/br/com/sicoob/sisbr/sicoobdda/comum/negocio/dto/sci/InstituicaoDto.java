/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  integracao-interna-privada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.dto.sci
 * Arquivo:         InstituicaoDTO.java
 * Data Criação:    02/08/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * Data transfer object para informações da entidade de instituição do sistema SCI.
 * 
 * @author Kaio.Rocha
 */
public class InstituicaoDto extends BancoobDto {

    private static final long serialVersionUID = 1L;

    private Integer idInstituicao;
    private Integer idInstituicaoPai;
    private String nomeInstituicao;
    private String siglaInstituicao;
    private String descInstituicao;// NomeInst
    private String numero;
    private String descTipoGrauCoop;
    private Integer codTipoGrauCoop;
    private Boolean todos;
    private Integer numCooperativa;
    private Integer numCooperativaPai;
    private String numCNPJ;
    private Long numContaConvenio;

    /**
     * 
     */
    public InstituicaoDto() {
    }

    public InstituicaoDto(Integer idInstituicao, Integer numCoop, Integer idInstituicaoPai, Integer numCoopPai, String descNomeCoop, String descSiglaCoop, Integer codTipoGrauCoop,
            String descTipoGrauCoop, String numCnpj, Long numContaConvenio) {
        this.idInstituicao = idInstituicao;
        this.idInstituicaoPai = idInstituicaoPai;
        this.numCooperativa = numCoop;
        this.numCooperativaPai = numCoopPai;
        this.nomeInstituicao = descNomeCoop;
        this.siglaInstituicao = descSiglaCoop;
        this.codTipoGrauCoop = codTipoGrauCoop;
        this.descTipoGrauCoop = descTipoGrauCoop;
        this.numCNPJ = numCnpj;

        this.descInstituicao = descNomeCoop;
        this.numero = numCoop != null ? numCoop.toString() : null;
        this.numContaConvenio = numContaConvenio;

    }

    /**
     * @param idInstituicao
     */
    public InstituicaoDto(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return the idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * @param idInstituicao the idInstituicao to set
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return the nomeInstituicao
     */
    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    /**
     * @param nomeInstituicao the nomeInstituicao to set
     */
    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    /**
     * @return the siglaInstituicao
     */
    public String getSiglaInstituicao() {
        return siglaInstituicao;
    }

    /**
     * @param siglaInstituicao the siglaInstituicao to set
     */
    public void setSiglaInstituicao(String siglaInstituicao) {
        this.siglaInstituicao = siglaInstituicao;
    }

    /**
     * @return the descInstituicao
     */
    public String getDescInstituicao() {
        return this.descInstituicao;
    }

    /**
     * @param descInstituicao the descInstituicao to set
     */
    public void setDescInstituicao(String descInstituicao) {
        this.descInstituicao = descInstituicao;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescTipoGrauCoop() {
        return descTipoGrauCoop;
    }

    public void setDescTipoGrauCoop(String descTipoGrauCoop) {
        this.descTipoGrauCoop = descTipoGrauCoop;
    }

    public Integer getCodTipoGrauCoop() {
        return codTipoGrauCoop;
    }

    public void setCodTipoGrauCoop(Integer codTipoGrauCoop) {
        this.codTipoGrauCoop = codTipoGrauCoop;
    }

    /**
     * @return the todos
     */
    public Boolean getTodos() {
        return todos;
    }

    /**
     * @param todos the todos to set
     */
    public void setTodos(Boolean todos) {
        this.todos = todos;
    }

    /**
     * @return o atributo numCooperativa
     */
    public Integer getNumCooperativa() {
        return numCooperativa;
    }

    /**
     * Define o atributo numCooperativa
     */
    public void setNumCooperativa(Integer numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    /**
     * @return the idInstituicaoPai
     */
    public Integer getIdInstituicaoPai() {
        return idInstituicaoPai;
    }

    /**
     * @param idInstituicaoPai the idInstituicaoPai to set
     */
    public void setIdInstituicaoPai(Integer idInstituicaoPai) {
        this.idInstituicaoPai = idInstituicaoPai;
    }

    /**
     * @return the numCooperativaPai
     */
    public Integer getNumCooperativaPai() {
        return numCooperativaPai;
    }

    /**
     * @param numCooperativaPai the numCooperativaPai to set
     */
    public void setNumCooperativaPai(Integer numCooperativaPai) {
        this.numCooperativaPai = numCooperativaPai;
    }

    /**
     * @return the numCNPJ
     */
    public String getNumCNPJ() {
        return numCNPJ;
    }

    /**
     * @param numCNPJ the numCNPJ to set
     */
    public void setNumCNPJ(String numCNPJ) {
        this.numCNPJ = numCNPJ;
    }

    /**
     * @return o atributo numContaConvenio
     */
    public Long getNumContaConvenio() {
        return numContaConvenio;
    }

    /**
     * Define o atributo numContaConvenio
     */
    public void setNumContaConvenio(Long numContaConvenio) {
        this.numContaConvenio = numContaConvenio;
    }

}
