package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * PagadorDDAAgregadoVO é responsável por
 * 
 * @author george.santos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAAgregado")
public class PagadorDDAAgregadoVO extends BancoobVO {

    private Long id;

    private PagadorDDAVO pagadorDDA;

    private String numCpfCnpjAgregado;

    private String codTipoPessoaAgregado;

    /**
     * @return o atributo id
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o atributo id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return o atributo pagadorDDA
     */
    public PagadorDDAVO getPagadorDDA() {
        return pagadorDDA;
    }

    /**
     * Define o atributo pagadorDDA
     */
    public void setPagadorDDA(PagadorDDAVO pagadorDDA) {
        this.pagadorDDA = pagadorDDA;
    }

    /**
     * @return o atributo numCpfCnpjAgregado
     */
    public String getNumCpfCnpjAgregado() {
        return numCpfCnpjAgregado;
    }

    /**
     * Define o atributo numCpfCnpjAgregado
     */
    public void setNumCpfCnpjAgregado(String numCpfCnpjAgregado) {
        this.numCpfCnpjAgregado = numCpfCnpjAgregado;
    }

    /**
     * @return o atributo codTipoPessoaAgregado
     */
    public String getCodTipoPessoaAgregado() {
        return codTipoPessoaAgregado;
    }

    /**
     * Define o atributo codTipoPessoaAgregado
     */
    public void setCodTipoPessoaAgregado(String codTipoPessoaAgregado) {
        this.codTipoPessoaAgregado = codTipoPessoaAgregado;
    }
}
