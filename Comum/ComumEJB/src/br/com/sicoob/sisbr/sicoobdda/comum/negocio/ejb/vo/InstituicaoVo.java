/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo
 * Arquivo:         InstituicaoVO.java
 * Data Criação:    Oct 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * InstituicaoVO é responsável por
 * 
 * @author Samuell.Ramos
 */
public class InstituicaoVo extends BancoobVo {
    /**
     * Serial UID
     */
    private static final long serialVersionUID = 1L;

    /** O atributo id instituicao. */
    private Integer idInstituicao;

    /** O atributo numero. */
    private Integer numero;

    /** O atributo nome. */
    private String nome;

    /** O atributo sigla instituicao. */
    private String siglaInstituicao;

    /** O atributo codigo tipo instituicao. */
    private Integer codigoTipoInstituicao;

    /** O atributo codigo situacao inst. */
    private Integer codigoSituacaoInst;

    /** O atributo cnpj. */
    private String cnpj;

    /** O atributo descricao do grau cooperativo. */
    private String descTipoGrauCoop;

    /** O atributo codigo do grau cooperativo. */
    private Integer codTipoGrauCoop;

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
     * @return o atributo numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Define o atributo numero
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return o atributo nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o atributo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return o atributo siglaInstituicao
     */
    public String getSiglaInstituicao() {
        return siglaInstituicao;
    }

    /**
     * Define o atributo siglaInstituicao
     */
    public void setSiglaInstituicao(String siglaInstituicao) {
        this.siglaInstituicao = siglaInstituicao;
    }

    /**
     * @return o atributo codigoTipoInstituicao
     */
    public Integer getCodigoTipoInstituicao() {
        return codigoTipoInstituicao;
    }

    /**
     * Define o atributo codigoTipoInstituicao
     */
    public void setCodigoTipoInstituicao(Integer codigoTipoInstituicao) {
        this.codigoTipoInstituicao = codigoTipoInstituicao;
    }

    /**
     * @return o atributo codigoSituacaoInst
     */
    public Integer getCodigoSituacaoInst() {
        return codigoSituacaoInst;
    }

    /**
     * Define o atributo codigoSituacaoInst
     */
    public void setCodigoSituacaoInst(Integer codigoSituacaoInst) {
        this.codigoSituacaoInst = codigoSituacaoInst;
    }

    /**
     * @return o atributo cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o atributo cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return o atributo descTipoGrauCoop
     */
    public String getDescTipoGrauCoop() {
        return descTipoGrauCoop;
    }

    /**
     * Define o atributo descTipoGrauCoop
     */
    public void setDescTipoGrauCoop(String descTipoGrauCoop) {
        this.descTipoGrauCoop = descTipoGrauCoop;
    }

    /**
     * @return o atributo codTipoGrauCoop
     */
    public Integer getCodTipoGrauCoop() {
        return codTipoGrauCoop;
    }

    /**
     * Define o atributo codTipoGrauCoop
     */
    public void setCodTipoGrauCoop(Integer codTipoGrauCoop) {
        this.codTipoGrauCoop = codTipoGrauCoop;
    }
}
