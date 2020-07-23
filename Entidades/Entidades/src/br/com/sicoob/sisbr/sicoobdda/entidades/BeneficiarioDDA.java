/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         BeneficiarioDDA.java
 * Data Criação:    Jul 24, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.SDDAStringUtil;

/**
 * DDABeneficiario
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "BENEFICIARIODDA", schema = "DDA")
public class BeneficiarioDDA extends SicoobDDAEntidade {
   
	private static final long serialVersionUID = -8370502617587589718L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDBENEFICIARIODDA", unique = true, nullable = false)
    private Long id;

    @Column(name = "NUMIDENTIFICABENEFICIARIO")
    private Long numIdentBeneficiario;

    @Column(name = "NUMCTRLDDA")
    private String numCtrlDDA;

    @Column(nullable = false)
    private Boolean bolOrigemSicoob;

    @Column(name = "NUMCPFCNPJ", nullable = false)
    private String numCpfCnpj;

    @Column(name = "CODTIPOPESSOA", nullable = false)
    private String codTipoPessoa;

    @ManyToOne
    @JoinColumn(name = "CODSITUACAOBENEFICIARIO", nullable = false)
    private SituacaoBeneficiarioDDA situacaoBeneficiarioDDA;

    @Column(name = "DATAINICIORELACIONAMENTO")
    private DateTimeDB dataInicioRelacionamento;

    @Column(name = "NUMREFATUALCADBENEFICIARIO ")
    private Long numRefAtualCadBeneficiario;

    @Column(name = "NUMSEQATUALCADBENEFICIARIO ")
    private Long numSeqAtualCadBeneficiario;

    @Column(name = "IDPRODUTO", nullable = false)
    private Integer idProduto;

    @OneToMany(mappedBy = "beneficiarioDDA", cascade = CascadeType.ALL)
    private List<BeneficiarioInstituicao> listaBeneficiarioInstituicao;

    @OneToMany(mappedBy = "beneficiarioDDA", cascade = CascadeType.ALL)
    private List<HistoricoStatusBeneficiarioDDA> listaHistoricoStatusBeneficiarioDDA;

    @OneToMany(mappedBy = "beneficiarioDDA", cascade = CascadeType.ALL)
    private List<IFBeneficiarioAlerta> listaIFBeneficiarioAlerta;

    @Column(insertable = false)
    private DateTimeDB dataHoraUltimaAtualizacao;

    @PreUpdate
    private void onUpdate() {
        this.dataHoraUltimaAtualizacao = new DateTimeDB();
    }

    /**
     * 
     */
    public BeneficiarioDDA() {
    }

    /**
     * @param id
     */
    public BeneficiarioDDA(Long id) {
        super();
        this.id = id;
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
     * @return the numIdentBeneficiario
     */
    public Long getNumIdentBeneficiario() {
        return numIdentBeneficiario;
    }

    /**
     * @param numIdentBeneficiario the numIdentBeneficiario to set
     */
    public void setNumIdentBeneficiario(Long numIdentBeneficiario) {
        this.numIdentBeneficiario = numIdentBeneficiario;
    }

    /**
     * @return the numCtrlDDA
     */
    public String getNumCtrlDDA() {
        return numCtrlDDA;
    }

    /**
     * @param numCtrlDDA the numCtrlDDA to set
     */
    public void setNumCtrlDDA(String numCtrlDDA) {
        this.numCtrlDDA = numCtrlDDA;
    }

    /**
     * @return the bolOrigemSicoob
     */
    public Boolean getBolOrigemSicoob() {
        return bolOrigemSicoob;
    }

    /**
     * @param bolOrigemSicoob the bolOrigemSicoob to set
     */
    public void setBolOrigemSicoob(Boolean bolOrigemSicoob) {
        this.bolOrigemSicoob = bolOrigemSicoob;
    }

    /**
     * @return the numCpfCnpj
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
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param codTipoPessoa void
     * 
     */
    public void setNumCpfCnpjCodTipoPessoa(String numCpfCnpj, String codTipoPessoa) {
        setCodTipoPessoa(codTipoPessoa);
        setNumCpfCnpj(numCpfCnpj, TipoPessoaEnum.getBy(codTipoPessoa));
    }

    /**
     * @return the codTipoPessoaBeneficiario
     */
    public String getCodTipoPessoa() {
        return codTipoPessoa;
    }

    /**
     * @param codTipoPessoaBeneficiario the codTipoPessoaBeneficiario to set
     */
    public void setCodTipoPessoa(String codTipoPessoa) {
        this.codTipoPessoa = codTipoPessoa;
    }

    /**
     * @return the situacaoBeneficiarioDDA
     */
    public SituacaoBeneficiarioDDA getSituacaoBeneficiarioDDA() {
        return situacaoBeneficiarioDDA;
    }

    /**
     * @param situacaoBeneficiarioDDA the situacaoBeneficiarioDDA to set
     */
    public void setSituacaoBeneficiarioDDA(SituacaoBeneficiarioDDA situacaoBeneficiarioDDA) {
        this.situacaoBeneficiarioDDA = situacaoBeneficiarioDDA;
    }

    /**
     * @return o atributo dataInicioRelacionamento
     */
    public DateTimeDB getDataInicioRelacionamento() {
        return dataInicioRelacionamento;
    }

    /**
     * Define o atributo dataInicioRelacionamento
     */
    public void setDataInicioRelacionamento(DateTimeDB dataInicioRelacionamento) {
        this.dataInicioRelacionamento = dataInicioRelacionamento;
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
     * @return the idProduto
     */
    public Integer getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return o atributo numRefAtualCadBeneficiario
     */
    public Long getNumRefAtualCadBeneficiario() {
        return numRefAtualCadBeneficiario;
    }

    /**
     * Define o atributo numRefAtualCadBeneficiario
     */
    public void setNumRefAtualCadBeneficiario(Long numRefAtualCadBeneficiario) {
        this.numRefAtualCadBeneficiario = numRefAtualCadBeneficiario;
    }

    /**
     * @return o atributo numSeqAtualCadBeneficiario
     */
    public Long getNumSeqAtualCadBeneficiario() {
        return numSeqAtualCadBeneficiario;
    }

    /**
     * Define o atributo numSeqAtualCadBeneficiario
     */
    public void setNumSeqAtualCadBeneficiario(Long numSeqAtualCadBeneficiario) {
        this.numSeqAtualCadBeneficiario = numSeqAtualCadBeneficiario;
    }

    /**
     * @return the listaBeneficiarioInstituicao
     */
    public List<BeneficiarioInstituicao> getListaBeneficiarioInstituicao() {
        if (listaBeneficiarioInstituicao == null) {
            listaBeneficiarioInstituicao = new ArrayList<BeneficiarioInstituicao>();
        }
        return listaBeneficiarioInstituicao;
    }

    /**
     * @param listaBeneficiarioInstituicao the listaBeneficiarioInstituicao to set
     */
    public void setListaBeneficiarioInstituicao(List<BeneficiarioInstituicao> listaBeneficiarioInstituicao) {
        this.listaBeneficiarioInstituicao = listaBeneficiarioInstituicao;
    }

    /**
     * @return the listaHistoricoStatusBeneficiarioDDA
     */
    public List<HistoricoStatusBeneficiarioDDA> getListaHistoricoStatusBeneficiarioDDA() {
        if (listaHistoricoStatusBeneficiarioDDA == null) {
            listaHistoricoStatusBeneficiarioDDA = new ArrayList<HistoricoStatusBeneficiarioDDA>();
        }
        return listaHistoricoStatusBeneficiarioDDA;
    }

    /**
     * @param listaHistoricoStatusBeneficiarioDDA the listaHistoricoStatusBeneficiarioDDA to set
     */
    public void setListaHistoricoStatusBeneficiarioDDA(List<HistoricoStatusBeneficiarioDDA> listaHistoricoStatusBeneficiarioDDA) {
        this.listaHistoricoStatusBeneficiarioDDA = listaHistoricoStatusBeneficiarioDDA;
    }

    /**
     * @return the listaIFBeneficiarioAlerta
     */
    public List<IFBeneficiarioAlerta> getListaIFBeneficiarioAlerta() {
        if (listaIFBeneficiarioAlerta == null) {
            listaIFBeneficiarioAlerta = new ArrayList<IFBeneficiarioAlerta>();
        }
        return listaIFBeneficiarioAlerta;
    }

    /**
     * @param listaIFBeneficiarioAlerta the listaIFBeneficiarioAlerta to set
     */
    public void setListaIFBeneficiarioAlerta(List<IFBeneficiarioAlerta> listaIFBeneficiarioAlerta) {
        this.listaIFBeneficiarioAlerta = listaIFBeneficiarioAlerta;
    }
}
