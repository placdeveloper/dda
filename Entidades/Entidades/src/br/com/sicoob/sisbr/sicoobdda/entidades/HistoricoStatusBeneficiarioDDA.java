/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         DDAHistoricoStatus.java
 * Data Criação:    Jul 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

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
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * HistoricoStatusBeneficiarioDDA
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "HISTORICOSTATUSBENEFICIARIO", schema = "DDA")
public class HistoricoStatusBeneficiarioDDA extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -1485026039612449912L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHISTORICOSTATUSBENEFICIARIO", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDBeneficiarioDDA", nullable = false)
    private BeneficiarioDDA beneficiarioDDA;

    @ManyToOne
    @JoinColumn(name = "CODSITUACAOBENEFICIARIO", nullable = false)
    private SituacaoBeneficiarioDDA situacaoBeneficiarioDDA;

    @Column(nullable = false)
    private DateTimeDB dataHoraCadastro;

    @OneToMany(mappedBy = "historicoStatusBeneficiarioDDA", cascade = CascadeType.ALL)
    private List<HistoricoIfStatusBeneficiario> listaHistoricoIfStatusBeneficiario;

    /**
     * 
     */
    public HistoricoStatusBeneficiarioDDA() {
        super();
    }

    /**
     * @param beneficiarioDDA
     * @param situacaoBeneficiarioDDA
     * @param dataHoraCadastro
     * @param listaHistoricoIfStatusBeneficiario
     */
    public HistoricoStatusBeneficiarioDDA(BeneficiarioDDA beneficiarioDDA, SituacaoBeneficiarioDDA situacaoBeneficiarioDDA, DateTimeDB dataHoraCadastro,
            List<HistoricoIfStatusBeneficiario> listaHistoricoIfStatusBeneficiario) {
        super();
        this.beneficiarioDDA = beneficiarioDDA;
        this.situacaoBeneficiarioDDA = situacaoBeneficiarioDDA;
        this.dataHoraCadastro = dataHoraCadastro;
        this.listaHistoricoIfStatusBeneficiario = listaHistoricoIfStatusBeneficiario;
    }

    /**
     * @param beneficiarioDDA
     * @param situacaoBeneficiarioDDA
     * @param dataHoraCadastro
     */
    public HistoricoStatusBeneficiarioDDA(BeneficiarioDDA beneficiarioDDA, SituacaoBeneficiarioDDA situacaoBeneficiarioDDA, DateTimeDB dataHoraCadastro) {
        super();
        this.beneficiarioDDA = beneficiarioDDA;
        this.situacaoBeneficiarioDDA = situacaoBeneficiarioDDA;
        this.dataHoraCadastro = dataHoraCadastro;
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
     * @return o atributo beneficiarioDDA
     */
    public BeneficiarioDDA getBeneficiarioDDA() {
        return beneficiarioDDA;
    }

    /**
     * Define o atributo beneficiarioDDA
     */
    public void setBeneficiarioDDA(BeneficiarioDDA beneficiarioDDA) {
        this.beneficiarioDDA = beneficiarioDDA;
    }

    /**
     * @return o atributo situacaoBeneficiarioDDA
     */
    public SituacaoBeneficiarioDDA getSituacaoBeneficiarioDDA() {
        return situacaoBeneficiarioDDA;
    }

    /**
     * Define o atributo situacaoBeneficiarioDDA
     */
    public void setSituacaoBeneficiarioDDA(SituacaoBeneficiarioDDA situacaoBeneficiarioDDA) {
        this.situacaoBeneficiarioDDA = situacaoBeneficiarioDDA;
    }

    /**
     * @return o atributo dataHoraCadastro
     */
    public DateTimeDB getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    /**
     * Define o atributo dataHoraCadastro
     */
    public void setDataHoraCadastro(DateTimeDB dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    /**
     * @return o atributo listaHistoricoIfStatusBeneficiario
     */
    public List<HistoricoIfStatusBeneficiario> getListaHistoricoIfStatusBeneficiario() {
        return listaHistoricoIfStatusBeneficiario;
    }

    /**
     * Define o atributo listaHistoricoIfStatusBeneficiario
     */
    public void setListaHistoricoIfStatusBeneficiario(List<HistoricoIfStatusBeneficiario> listaHistoricoIfStatusBeneficiario) {
        this.listaHistoricoIfStatusBeneficiario = listaHistoricoIfStatusBeneficiario;
    }

}
