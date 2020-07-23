/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  integracao-interna-privada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracao.interna.privada.dto.sci
 * Arquivo:         InstituicaoCooperativaDto.java
 * Data Criação:    18/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.dto;

import java.io.Serializable;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Data transfer object para informações da entidade de instituição cooperativa do sistema SCI.
 * 
 * @author Rafael.Silva
 * 
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.InstituicaoCooperativaDTO")
public class InstituicaoCooperativaDto implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String[] CAMPOS = new String[] { "idInstituicao", "numCooperativa", "nomeCooperativa", "idLocalidade", "nomeCidadeCooperativa", "siglaUfCooperativa",
            "nomeUfCooperativa" };

    private Integer idInstituicao;

    private Integer numCooperativa;

    private String nomeCooperativa;

    private Integer idLocalidade;

    private String nomeCidadeCooperativa;

    private String siglaUfCooperativa;

    private String nomeUfCooperativa;

    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public Integer getNumCooperativa() {
        return numCooperativa;
    }

    public void setNumCooperativa(Integer numCooperativa) {
        this.numCooperativa = numCooperativa;
    }

    public String getNomeCooperativa() {
        return nomeCooperativa;
    }

    public void setNomeCooperativa(String nomeCooperativa) {
        this.nomeCooperativa = nomeCooperativa;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getNomeCidadeCooperativa() {
        return nomeCidadeCooperativa;
    }

    public void setNomeCidadeCooperativa(String nomeCidadeCooperativa) {
        this.nomeCidadeCooperativa = nomeCidadeCooperativa;
    }

    public String getSiglaUfCooperativa() {
        return siglaUfCooperativa;
    }

    public void setSiglaUfCooperativa(String siglaUfCooperativa) {
        this.siglaUfCooperativa = siglaUfCooperativa;
    }

    public String getNomeUfCooperativa() {
        return nomeUfCooperativa;
    }

    public void setNomeUfCooperativa(String nomeUfCooperativa) {
        this.nomeUfCooperativa = nomeUfCooperativa;
    }
}
