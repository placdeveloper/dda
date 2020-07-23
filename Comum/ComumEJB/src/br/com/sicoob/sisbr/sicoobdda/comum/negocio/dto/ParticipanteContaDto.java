/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  integracao-interna-privada
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.integracao.interna.privada.dto.cco
 * Arquivo:         ContaCorrenteDTO.java
 * Data Criação:    28/02/2013
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO para informações da entidade de instituição do sistema CCO.
 * 
 * @author George.santos
 */
public class ParticipanteContaDto extends BancoobDto {

    private static final long serialVersionUID = 9006617444453907711L;

    public static final String[] CAMPOS = new String[] { "idParticipanteConta", "numOrdemTitularidade", "nomeEmbossamento", "bolAtivo", "idContaCorrente", "idPessoa",
            "idInstituicao", "numContaCorrente", "codTipoPessoa" };

    private Long idParticipanteConta;
    private Integer numOrdemTitularidade;
    private String nomeEmbossamento;
    private Integer bolAtivo;
    private Long idContaCorrente;
    private Long idPessoa;
    private Integer idInstituicao;
    private Long numContaCorrente;

    private Short codTipoPessoa;

    /**
     * @return o atributo idParticipanteConta
     */
    public Long getIdParticipanteConta() {
        return idParticipanteConta;
    }

    /**
     * Define o atributo idParticipanteConta
     */
    public void setIdParticipanteConta(Long idParticipanteConta) {
        this.idParticipanteConta = idParticipanteConta;
    }

    /**
     * @return o atributo numOrdemTitularidade
     */
    public Integer getNumOrdemTitularidade() {
        return numOrdemTitularidade;
    }

    /**
     * Define o atributo numOrdemTitularidade
     */
    public void setNumOrdemTitularidade(Integer numOrdemTitularidade) {
        this.numOrdemTitularidade = numOrdemTitularidade;
    }

    /**
     * @return o atributo nomeEmbossamento
     */
    public String getNomeEmbossamento() {
        return nomeEmbossamento;
    }

    /**
     * Define o atributo nomeEmbossamento
     */
    public void setNomeEmbossamento(String nomeEmbossamento) {
        this.nomeEmbossamento = nomeEmbossamento;
    }

    /**
     * @return o atributo bolAtivo
     */
    public Integer getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Integer bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    /**
     * @return o atributo idContaCorrente
     */
    public Long getIdContaCorrente() {
        return idContaCorrente;
    }

    /**
     * Define o atributo idContaCorrente
     */
    public void setIdContaCorrente(Long idContaCorrente) {
        this.idContaCorrente = idContaCorrente;
    }

    /**
     * @return o atributo idPessoa
     */
    public Long getIdPessoa() {
        return idPessoa;
    }

    /**
     * Define o atributo idPessoa
     */
    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return o atributo numContaCorrente
     */
    public Long getNumContaCorrente() {
        return numContaCorrente;
    }

    /**
     * Define o atributo numContaCorrente
     */
    public void setNumContaCorrente(Long numContaCorrente) {
        this.numContaCorrente = numContaCorrente;
    }

    /**
     * @return o atributo codTipoPessoa
     */
    public Short getCodTipoPessoa() {
        return codTipoPessoa;
    }

    /**
     * Define o atributo codTipoPessoa
     */
    public void setCodTipoPessoa(Short codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

}
