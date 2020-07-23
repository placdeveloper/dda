package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.SDDAStringUtil;

/**
 * DDA.PAGADORDDA
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "PAGADORDDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.PagadorDDAVO")
public class PagadorDDA extends SicoobDDAEntidade {

	private static final long serialVersionUID = 778982980869486603L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPAGADORDDA", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String numCpfCnpj;

    @Column(nullable = false)
    private String codTipoPessoaCip;

    private Long numIdentificaPagadorCip;

    private Long numRefAtualCadPagador;

    private Long numSeqAtualCadPagador;

    @Column(nullable = false)
    private Integer qtdAdesaoDDA;

    @Column(nullable = false)
    private Boolean bolSacadoEletronico;

    @Column(insertable = false)
    private DateTimeDB dataHoraUltimaAtualizacao;

    @PreUpdate
    void onUpdate() {
        dataHoraUltimaAtualizacao = new DateTimeDB();
    }

    @OneToMany(mappedBy = "pagadorDDA", cascade = CascadeType.ALL)
    private List<PagadorDDAAgregado> listaPagadorDDAAgregado;

    @OneToMany(mappedBy = "pagadorDDA", cascade = CascadeType.ALL)
    private List<PagadorDDAConta> listaPagadorDDAConta;

    /**
     * 
     */
    public PagadorDDA() {
    }

    /**
     * @param idPagador
     */
    public PagadorDDA(Long idPagador) {
        this.id = idPagador;
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaCip
     * @param qtdAdesaoDDA
     * @param indicadorAdesao
     * @param dataHoraUltimaAtualizacao
     */
    public PagadorDDA(String numCpfCnpj, String codTipoPessoaCip, BigInteger qtdAdesaoDDA) {
        super();
        setNumCpfCnpjCodTipoPessoa(numCpfCnpj, codTipoPessoaCip);
        setQtdAdesaoDDA(qtdAdesaoDDA);
        setBolSacadoEletronico(getQtdAdesaoDDA());
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaCip
     * @param qtdAdesaoDDA
     * @param bolSacadoEletronico
     * @param dataHoraUltimaAtualizacao
     */
    public PagadorDDA(String numCpfCnpj, String codTipoPessoaCip, BigInteger qtdAdesaoDDA, DateTimeDB dataHoraUltimaAtualizacao) {
        super();
        setNumCpfCnpjCodTipoPessoa(numCpfCnpj, codTipoPessoaCip);
        setQtdAdesaoDDA(qtdAdesaoDDA);
        setBolSacadoEletronico(getQtdAdesaoDDA());
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaCip
     * @param numIdentificaPagadorCip
     * @param numRefAtualCadPagador
     * @param qtdAdesaoDDA
     * @param bolSacadoEletronico
     * @param numSeqAtualCadPagador
     * @param dataHoraUltimaAtualizacao
     */
    public PagadorDDA(String numCpfCnpj, String codTipoPessoaCip, BigInteger numIdentificaPagadorCip, BigInteger numRefAtualCadPagador, BigInteger qtdAdesaoDDA,
            BigInteger numSeqAtualCadPagador) {
        super();
        setNumCpfCnpjCodTipoPessoa(numCpfCnpj, codTipoPessoaCip);
        if (numIdentificaPagadorCip != null) {
            this.numIdentificaPagadorCip = numIdentificaPagadorCip.longValue();
        }
        if (numRefAtualCadPagador != null) {
            this.numRefAtualCadPagador = numRefAtualCadPagador.longValue();
        }
        if (numSeqAtualCadPagador != null) {
            this.numSeqAtualCadPagador = numSeqAtualCadPagador.longValue();
        }
        setQtdAdesaoDDA(qtdAdesaoDDA);
        setBolSacadoEletronico(getQtdAdesaoDDA());
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
     * @return o atributo numCPfCnpj
     */
    public String getNumCpfCnpj() {
        return numCpfCnpj;
    }

    /**
     * @param numCpfCnpj
     * @param tipoPessoaEnum void
     * 
     */
    public void setNumCpfCnpj(String numCpfCnpj, TipoPessoaEnum tipoPessoaEnum) {
        if (tipoPessoaEnum.equals(TipoPessoaEnum.PF)) {
            this.numCpfCnpj = SDDAStringUtil.incluirZerosCPF(numCpfCnpj);
        } else {
            this.numCpfCnpj = SDDAStringUtil.incluirZerosCNPJ(numCpfCnpj);
        }
    }

    /**
     * @param numCpfCnpj
     * @param codTipoPessoaCip void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoa(String numCpfCnpj, String codTipoPessoaCip) {
        setCodTipoPessoaCip(codTipoPessoaCip);
        setNumCpfCnpj(numCpfCnpj, TipoPessoaEnum.getBy(codTipoPessoaCip));
    }

    /**
     * @return o atributo codTipoPessoaCip
     */
    public String getCodTipoPessoaCip() {
        return codTipoPessoaCip;
    }

    /**
     * Define o atributo codTipoPessoaCip
     */
    public void setCodTipoPessoaCip(String codTipoPessoaCip) {
        this.codTipoPessoaCip = codTipoPessoaCip;
    }

    /**
     * @return o atributo numIdentificaPagadorCip
     */
    public Long getNumIdentificaPagadorCip() {
        return numIdentificaPagadorCip;
    }

    /**
     * Define o atributo numIdentificaPagadorCip
     */
    public void setNumIdentificaPagadorCip(Long numIdentificaPagadorCip) {
        this.numIdentificaPagadorCip = numIdentificaPagadorCip;
    }

    /**
     * @return o atributo numRefAtualCadPagador
     */
    public Long getNumRefAtualCadPagador() {
        return numRefAtualCadPagador;
    }

    /**
     * Define o atributo numRefAtualCadPagador
     */
    public void setNumRefAtualCadPagador(Long numRefAtualCadPagador) {
        this.numRefAtualCadPagador = numRefAtualCadPagador;
    }

    /**
     * @return o atributo qtdAdesaoDDA
     */
    public Integer getQtdAdesaoDDA() {
        return qtdAdesaoDDA;
    }

    /**
     * Define o atributo qtdAdesaoDDA
     */
    public void setQtdAdesaoDDA(Integer qtdAdesaoDDA) {
        if (qtdAdesaoDDA != null) {
            this.qtdAdesaoDDA = qtdAdesaoDDA;
        } else {
            this.qtdAdesaoDDA = 0;
        }
    }

    /**
     * @param qtdAdesaoDDA void
     * 
     */
    public void setQtdAdesaoDDA(BigInteger qtdAdesaoDDA) {
        if (qtdAdesaoDDA != null) {
            this.qtdAdesaoDDA = qtdAdesaoDDA.intValue();
        } else {
            this.qtdAdesaoDDA = 0;
        }

    }

    /**
     * @return o atributo bolSacadoEletronico
     */
    public Boolean getBolSacadoEletronico() {
        return bolSacadoEletronico;
    }

    /**
     * Método responsável por inferir o valor do bolSacadoEletronico através da quantidade de adesao, se for maior que 0 e um sacado eletronico
     * 
     * @param indicadorAdesao void
     * 
     */
    public void setBolSacadoEletronico(Integer qtdAdesao) {
        this.bolSacadoEletronico = qtdAdesao > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * Define o atributo bolSacadoEletronico
     */
    public void setBolSacadoEletronico(Boolean bolSacadoEletronico) {
        this.bolSacadoEletronico = bolSacadoEletronico;
    }

    /**
     * @return o atributo numSeqAtualCadPagador
     */
    public Long getNumSeqAtualCadPagador() {
        return numSeqAtualCadPagador;
    }

    /**
     * Define o atributo numSeqAtualCadPagador
     */
    public void setNumSeqAtualCadPagador(Long numSeqAtualCadpagador) {
        this.numSeqAtualCadPagador = numSeqAtualCadpagador;
    }

    /**
     * @return o atributo dataHoraUltimaAtualizacao
     */
    public DateTimeDB getDataHoraUltimaAtualizacao() {
        return dataHoraUltimaAtualizacao;
    }

    /**
     * Define o atributo dataHoraUltimaAtualizacao
     */
    public void setDataHoraUltimaAtualizacao(DateTimeDB dataHoraUltimaAtualizacao) {
        this.dataHoraUltimaAtualizacao = dataHoraUltimaAtualizacao;
    }

    /**
     * @return o atributo listaPagadorDDAAgregado
     */
    public List<PagadorDDAAgregado> getListaPagadorDDAAgregado() {
        if (listaPagadorDDAAgregado == null) {
            listaPagadorDDAAgregado = new ArrayList<PagadorDDAAgregado>();
        }
        return listaPagadorDDAAgregado;
    }

    /**
     * Define o atributo listaPagadorDDAAgregado
     */
    public void setListaPagadorDDAAgregado(List<PagadorDDAAgregado> listaPagadorDDAAgregado) {
        this.listaPagadorDDAAgregado = listaPagadorDDAAgregado;
    }

    /**
     * @return o atributo listaPagadorDDAConta
     */
    public List<PagadorDDAConta> getListaPagadorDDAConta() {
        if (listaPagadorDDAConta == null) {
            listaPagadorDDAConta = new ArrayList<PagadorDDAConta>();
        }
        return listaPagadorDDAConta;
    }

    /**
     * Define o atributo listaPagadorDDAConta
     */
    public void setListaPagadorDDAConta(List<PagadorDDAConta> listaPagadorDDAConta) {
        this.listaPagadorDDAConta = listaPagadorDDAConta;
    }

}
