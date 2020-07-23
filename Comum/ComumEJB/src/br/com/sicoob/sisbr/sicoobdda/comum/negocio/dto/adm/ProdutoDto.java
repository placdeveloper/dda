/**
 * Projeto:         sdda-integracao-interna-ejb
 * Camada Projeto:  sdda-integracao-interna-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto
 * Arquivo:         ProdutoDto.java
 * Data Criação:    Sep 26, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * ProdutoDto é responsável por
 * 
 * @author jesliel.rocha
 */
public class ProdutoDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -2017561103178922205L;

    private Short idProduto;
    private Integer idInstituicao;
    private Integer idUnidadeInstituicao;
    private Date dataUltimoMovimento;
    private Date dataAtualMovimento;
    private Date dataProximoMovimento;

    /**
     * 
     */
    public ProdutoDto() {
    }

    /**
     * @param idProduto
     * @param idInstituicao
     * @param idUnidadeInstituicao
     * @param dataUltimoMovimento
     * @param dataAtualMovimento
     * @param dataProximoMovimento
     */
    public ProdutoDto(Short idProduto, Integer idInstituicao, Integer idUnidadeInstituicao, Date dataUltimoMovimento, Date dataAtualMovimento, Date dataProximoMovimento) {
        super();
        this.idProduto = idProduto;
        this.idInstituicao = idInstituicao;
        this.idUnidadeInstituicao = idUnidadeInstituicao;
        this.dataUltimoMovimento = dataUltimoMovimento;
        this.dataAtualMovimento = dataAtualMovimento;
        this.dataProximoMovimento = dataProximoMovimento;
    }

    /**
     * @return o atributo idProduto
     */
    public Short getIdProduto() {
        return idProduto;
    }

    /**
     * Define o atributo idProduto
     */
    public void setIdProduto(Short idProduto) {
        this.idProduto = idProduto;
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
     * @return o atributo idUnidadeInstituicao
     */
    public Integer getIdUnidadeInstituicao() {
        return idUnidadeInstituicao;
    }

    /**
     * Define o atributo idUnidadeInstituicao
     */
    public void setIdUnidadeInstituicao(Integer idUnidadeInstituicao) {
        this.idUnidadeInstituicao = idUnidadeInstituicao;
    }

    /**
     * @return o atributo dataUltimoMovimento
     */
    public Date getDataUltimoMovimento() {
        return dataUltimoMovimento;
    }

    /**
     * Define o atributo dataUltimoMovimento
     */
    public void setDataUltimoMovimento(Date dataUltimoMovimento) {
        this.dataUltimoMovimento = dataUltimoMovimento;
    }

    /**
     * @return o atributo dataAtualMovimento
     */
    public Date getDataAtualMovimento() {
        return dataAtualMovimento;
    }

    /**
     * Define o atributo dataAtualMovimento
     */
    public void setDataAtualMovimento(Date dataAtualMovimento) {
        this.dataAtualMovimento = dataAtualMovimento;
    }

    /**
     * @return o atributo dataProximoMovimento
     */
    public Date getDataProximoMovimento() {
        return dataProximoMovimento;
    }

    /**
     * Define o atributo dataProximoMovimento
     */
    public void setDataProximoMovimento(Date dataProximoMovimento) {
        this.dataProximoMovimento = dataProximoMovimento;
    }

}
