package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigInteger;

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
 * DDA.BOLETODDANOTAFISCAL
 * 
 * @author George.santos
 */
@Entity
@Table(name = "BOLETODDATERCEIROAUT", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.BoletoDDATerceiroAutVO")
public class BoletoDDATerceiroAut extends SicoobDDAEntidade {

    private static final long serialVersionUID = -8601117274953893345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBOLETODDATERCEIROAUT", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBOLETODDA", nullable = false)
    private BoletoDDA boletoDDA;

    @Column(nullable = false)
    private String codTipoPessoaAutorizador;

    @Column(nullable = false)
    private String numCpfCnpjAutorizador;

    @Column(nullable = false)
    private String codTipoPessoaTerceiro;

    @Column(nullable = false)
    private String numCpfCnpjTerceiro;

    private Long numIdentificadorTerceiro;

    private Long numRefAtualTerceiro;

    private Boolean bolAtivo;

    /**
     * @return o atributo bolAtivo
     */
    public Boolean getBolAtivo() {
        return bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(Boolean bolAtivo) {
        this.bolAtivo = bolAtivo;
    }

    /**
     * Define o atributo bolAtivo
     */
    public void setBolAtivo(String sitTerceiro) {
        this.bolAtivo = sitTerceiro.equals("1");
    }

    /**
     * 
     */
    public BoletoDDATerceiroAut() {
        super();
    }

    /**
     * @param boletoDDA
     * @param codTipoPessoaAutorizador
     * @param numCpfCnpjAutorizador
     * @param codTipoPessoaTerceiro
     * @param numCpfCnpjTerceiro
     * @param numIdentificadorTerceiro
     * @param numRefAtualTerceiro
     * @param sitTerceiro
     */
    public BoletoDDATerceiroAut(BoletoDDA boletoDDA, String codTipoPessoaAutorizador, BigInteger numCpfCnpjAutorizador, String codTipoPessoaTerceiro,
            BigInteger numCpfCnpjTerceiro, BigInteger numIdentificadorTerceiro, BigInteger numRefAtualTerceiro, String sitTerceiro) {
        super();
        this.boletoDDA = boletoDDA;
        setNumCpfCnpjCodTipoPessoaAutorizador(numCpfCnpjAutorizador != null ? numCpfCnpjAutorizador.toString() : null, codTipoPessoaAutorizador);
        setNumCpfCnpjCodTipoPessoaTerceiro(numCpfCnpjTerceiro != null ? numCpfCnpjTerceiro.toString() : null, codTipoPessoaTerceiro);
        if (numIdentificadorTerceiro != null) {
            this.numIdentificadorTerceiro = numIdentificadorTerceiro.longValue();
        }
        if (numRefAtualTerceiro != null) {
            this.numRefAtualTerceiro = numRefAtualTerceiro.longValue();
        }
        if (sitTerceiro != null) {
            setBolAtivo(sitTerceiro);
        }
    }

    /**
     * @param boletoDDA
     * @param codTipoPessoaAutorizador
     * @param numCpfCnpjAutorizador
     * @param codTipoPessoaTerceiro
     * @param numCpfCnpjTerceiro
     * @param numIdentificadorTerceiro
     * @param numRefAtualTerceiro
     */
    public BoletoDDATerceiroAut(BoletoDDA boletoDDA, String codTipoPessoaAutorizador, String numCpfCnpjAutorizador, String codTipoPessoaTerceiro, String numCpfCnpjTerceiro,
            BigInteger numIdentificadorTerceiro, BigInteger numRefAtualTerceiro) {
        super();
        this.boletoDDA = boletoDDA;
        setNumCpfCnpjCodTipoPessoaAutorizador(numCpfCnpjAutorizador, codTipoPessoaAutorizador);
        setNumCpfCnpjCodTipoPessoaTerceiro(numCpfCnpjTerceiro, codTipoPessoaTerceiro);
        if (numIdentificadorTerceiro != null) {
            this.numIdentificadorTerceiro = numIdentificadorTerceiro.longValue();
        }
        if (numRefAtualTerceiro != null) {
            this.numRefAtualTerceiro = numRefAtualTerceiro.longValue();
        }
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
     * @return o atributo boletoDDA
     */
    public BoletoDDA getBoletoDDA() {
        return boletoDDA;
    }

    /**
     * Define o atributo boletoDDA
     */
    public void setBoletoDDA(BoletoDDA boletoDDA) {
        this.boletoDDA = boletoDDA;
    }

    /**
     * @return o atributo codTipoPessoaAutorizador
     */
    public String getCodTipoPessoaAutorizador() {
        return codTipoPessoaAutorizador;
    }

    /**
     * Define o atributo codTipoPessoaAutorizador
     */
    public void setCodTipoPessoaAutorizador(String codTipoPessoaAutorizador) {
        this.codTipoPessoaAutorizador = codTipoPessoaAutorizador;
    }

    /**
     * @return o atributo numCpfCnpjAutorizador
     */
    public String getNumCpfCnpjAutorizador() {
        return numCpfCnpjAutorizador;
    }

    /**
     * Define o atributo numCpfCnpjAutorizador
     */
    public void setNumCpfCnpjAutorizador(String numCpfCnpjAutorizador) {
        this.numCpfCnpjAutorizador = numCpfCnpjAutorizador;
    }

    /**
     * @return o atributo codTipoPessoaTerceiro
     */
    public String getCodTipoPessoaTerceiro() {
        return codTipoPessoaTerceiro;
    }

    /**
     * Define o atributo codTipoPessoaTerceiro
     */
    public void setCodTipoPessoaTerceiro(String codTipoPessoaTerceiro) {
        this.codTipoPessoaTerceiro = codTipoPessoaTerceiro;
    }

    /**
     * @return o atributo numCpfCnpjTerceiro
     */
    public String getNumCpfCnpjTerceiro() {
        return numCpfCnpjTerceiro;
    }

    /**
     * Define o atributo numCpfCnpjTerceiro
     */
    public void setNumCpfCnpjTerceiro(String numCpfCnpjTerceiro) {
        this.numCpfCnpjTerceiro = numCpfCnpjTerceiro;
    }

    /**
     * @return o atributo numIdentificadorTerceiro
     */
    public Long getNumIdentificadorTerceiro() {
        return numIdentificadorTerceiro;
    }

    /**
     * Define o atributo numIdentificadorTerceiro
     */
    public void setNumIdentificadorTerceiro(Long numIdentificadorTerceiro) {
        this.numIdentificadorTerceiro = numIdentificadorTerceiro;
    }

    /**
     * @return o atributo numRefAtualTerceiro
     */
    public Long getNumRefAtualTerceiro() {
        return numRefAtualTerceiro;
    }

    /**
     * Define o atributo numRefAtualTerceiro
     */
    public void setNumRefAtualTerceiro(Long numRefAtualTerceiro) {
        this.numRefAtualTerceiro = numRefAtualTerceiro;
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpjAutorizador
     * @param tipoPessoaEnum void
     * 
     */
    public void setNumCpfCnpjAutorizador(String numCpfCnpjAutorizador, TipoPessoaEnum tipoPessoaEnum) {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            this.numCpfCnpjAutorizador = SDDAStringUtil.incluirZerosCPF(numCpfCnpjAutorizador);
        } else {
            this.numCpfCnpjAutorizador = SDDAStringUtil.incluirZerosCNPJ(numCpfCnpjAutorizador);
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpjAutorizador
     * @param codTipoPessoaAutorizador void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoaAutorizador(String numCpfCnpjAutorizador, String codTipoPessoaAutorizador) {
        if (codTipoPessoaAutorizador != null) {
            setCodTipoPessoaAutorizador(codTipoPessoaAutorizador);
            setNumCpfCnpjAutorizador(numCpfCnpjAutorizador, TipoPessoaEnum.getBy(codTipoPessoaAutorizador));
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpjTerceiro
     * @param tipoPessoaEnum void
     * 
     */
    public void setNumCpfCnpjTerceiro(String numCpfCnpjTerceiro, TipoPessoaEnum tipoPessoaEnum) {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            this.numCpfCnpjTerceiro = SDDAStringUtil.incluirZerosCPF(numCpfCnpjTerceiro);
        } else {
            this.numCpfCnpjTerceiro = SDDAStringUtil.incluirZerosCNPJ(numCpfCnpjTerceiro);
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpjAutorizador
     * @param codTipoPessoaAutorizador void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoaTerceiro(String numCpfCnpjTerceiro, String codTipoPessoaTerceiro) {
        if (codTipoPessoaTerceiro != null) {
            setCodTipoPessoaTerceiro(codTipoPessoaTerceiro);
            setNumCpfCnpjTerceiro(numCpfCnpjTerceiro, TipoPessoaEnum.getBy(codTipoPessoaTerceiro));
        }
    }

    public String getCpfCnpjPagadorTerceiroFormatado() {
        if (getNumCpfCnpjTerceiro() != null) {
            if (getNumCpfCnpjTerceiro().length() == Constantes.TAMANHO_CPF) {
                return FormatadorUtil.formatar(getNumCpfCnpjTerceiro(), FormatadorUtil.MASCARA_CPF);
            } else {
                return FormatadorUtil.formatar(getNumCpfCnpjTerceiro(), FormatadorUtil.MASCARA_CNPJ);
            }
        }
        return "";
    }

    public String getCpfCnpjPagadorAutorizadoFormatado() {
        if (getNumCpfCnpjAutorizador() != null) {
            if (getNumCpfCnpjAutorizador().length() == Constantes.TAMANHO_CPF) {
                return FormatadorUtil.formatar(getNumCpfCnpjAutorizador(), FormatadorUtil.MASCARA_CPF);
            } else {
                return FormatadorUtil.formatar(getNumCpfCnpjAutorizador(), FormatadorUtil.MASCARA_CNPJ);
            }
        }
        return "";
    }

}
