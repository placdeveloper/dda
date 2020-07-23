package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.SDDAStringUtil;

/**
 * DDA.PAGADORDDAAGREGADO
 * 
 * @author George.santos
 */
@Entity
@Table(name = "PAGADORDDAAGREGADO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.PagadorDDAAgregadoVO")
public class PagadorDDAAgregado extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPAGADORDDAAGREGADO", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDPAGADORDDA", nullable = false)
    private PagadorDDA pagadorDDA;

    @Column(nullable = false)
    private String numCpfCnpjAgregado;

    @Column(nullable = false)
    private String codTipoPessoaAgregado;

    /**
     * 
     */
    public PagadorDDAAgregado() {
        super();
    }

    /**
     * @param pagadorDDA
     * @param numCpfCnpjAgregado
     * @param codTipoPessoaAgregado
     */
    public PagadorDDAAgregado(PagadorDDA pagadorDDA, String numCpfCnpjAgregado, String codTipoPessoaAgregado) {
        super();
        this.pagadorDDA = pagadorDDA;
        setNumCpfCnpjCodTipoPessoaAgregado(numCpfCnpjAgregado, codTipoPessoaAgregado);
        this.codTipoPessoaAgregado = codTipoPessoaAgregado;
    }

    public PagadorDDAAgregado(String numCpfCnpjAgregado, String codTipoPessoaAgregado) {
        super();
        setNumCpfCnpjCodTipoPessoaAgregado(numCpfCnpjAgregado, codTipoPessoaAgregado);
        this.codTipoPessoaAgregado = codTipoPessoaAgregado;
    }

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
    public PagadorDDA getPagadorDDA() {
        return pagadorDDA;
    }

    /**
     * Define o atributo pagadorDDA
     */
    public void setPagadorDDA(PagadorDDA pagadorDDA) {
        this.pagadorDDA = pagadorDDA;
    }

    /**
     * @return o atributo numCpfCnpjAgregado
     */
    public String getNumCpfCnpjAgregado() {
        return numCpfCnpjAgregado;
    }

    /**
     * @param numCpfCnpj
     * @param tipoPessoaEnum void
     * 
     */
    public void setNumCpfCnpjAgregado(String numCpfCnpj, TipoPessoaEnum tipoPessoaEnum) {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            this.numCpfCnpjAgregado = SDDAStringUtil.incluirZerosCPF(numCpfCnpj);
        } else {
            this.numCpfCnpjAgregado = SDDAStringUtil.incluirZerosCNPJ(numCpfCnpj);
        }
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaAgregado void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoaAgregado(String numCpfCnpj, String codTipoPessoaAgregado) {
        setCodTipoPessoaAgregado(codTipoPessoaAgregado);
        setNumCpfCnpjAgregado(numCpfCnpj, TipoPessoaEnum.getBy(codTipoPessoaAgregado));
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

    public String getNumCpfCnpjAgregadoFormatado() {
        if (getNumCpfCnpjAgregado() != null) {
            if (getNumCpfCnpjAgregado().length() == Constantes.TAMANHO_CPF) {
                return FormatadorUtil.formatar(getNumCpfCnpjAgregado(), FormatadorUtil.MASCARA_CPF);
            } else {
                return FormatadorUtil.formatar(getNumCpfCnpjAgregado(), FormatadorUtil.MASCARA_CNPJ);
            }
        }
        return "";
    }
}
