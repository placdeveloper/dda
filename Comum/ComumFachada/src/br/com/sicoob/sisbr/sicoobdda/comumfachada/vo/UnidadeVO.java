package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * UnidadeVO � respons�vel por
 * 
 * @author Samuell.Ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.UnidadeVo")
public class UnidadeVO extends BancoobVo {
    private static final long serialVersionUID = -3639753618358025920L;

    private Integer codigo;
    private String descricao;
    private String nomeUnidade;
    private Integer idUnidadeInst;
    private String nomeInstituicao;

    /**
     * M�todo respons�vel por pegar o codigo da unidade
     * 
     * @return
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * M�todo respons�vel por colocar o codigo da unidade
     * 
     * @param codigo
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    /**
     * M�todo respons�vel por pegar o descricao da unidade
     * 
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * M�todo respons�vel por colocar a descricao da unidade
     * 
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return o atributo nomeUnidade
     */
    public String getNomeUnidade() {
        return nomeUnidade;
    }

    /**
     * Define o atributo nomeUnidade
     */
    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    /**
     * @return o atributo idUnidadeInst
     */
    public Integer getIdUnidadeInst() {
        return idUnidadeInst;
    }

    /**
     * Define o atributo idUnidadeInst
     */
    public void setIdUnidadeInst(Integer idUnidadeInst) {
        this.idUnidadeInst = idUnidadeInst;
    }

    /**
     * @return o atributo nomeInstituicao
     */
    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    /**
     * Define o atributo nomeInstituicao
     */
    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

}