/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         DDAHistoricoIf.java
 * Data Criação:    Jul 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * HistoricoIfStatusBeneficiario
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "HISTORICOIFSTATUSBENEFICIARIO", schema = "DDA")
public class HistoricoIfStatusBeneficiario extends SicoobDDAEntidade {

    private static final long serialVersionUID = -7568650518843298613L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHISTORICOIFSTATUSBENEFICIARIO", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDHISTORICOSTATUSBENEFICIARIO", nullable = false)
    private HistoricoStatusBeneficiarioDDA historicoStatusBeneficiarioDDA;

    @Column(name = "CODISPBPARTDESTINATARIOORIGDRALERT", nullable = false)
    private String codISPBPartDestinatarioOrigAlerta;

    @Column(nullable = false)
    private DateTimeDB dataHoraCadastro;

    /**
     * 
     */
    public HistoricoIfStatusBeneficiario() {
        super();
    }

    /**
     * @param historicoStatusBeneficiarioDDA
     * @param codISPBPartDestinatarioOrigAlerta
     * @param dataHoraCadastro
     */
    public HistoricoIfStatusBeneficiario(HistoricoStatusBeneficiarioDDA historicoStatusBeneficiarioDDA, String codISPBPartDestinatarioOrigAlerta, DateTimeDB dataHoraCadastro) {
        super();
        this.historicoStatusBeneficiarioDDA = historicoStatusBeneficiarioDDA;
        this.codISPBPartDestinatarioOrigAlerta = codISPBPartDestinatarioOrigAlerta;
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
     * @return o atributo historicoStatusBeneficiarioDDA
     */
    public HistoricoStatusBeneficiarioDDA getHistoricoStatusBeneficiarioDDA() {
        return historicoStatusBeneficiarioDDA;
    }

    /**
     * Define o atributo historicoStatusBeneficiarioDDA
     */
    public void setHistoricoStatusBeneficiarioDDA(HistoricoStatusBeneficiarioDDA historicoStatusBeneficiarioDDA) {
        this.historicoStatusBeneficiarioDDA = historicoStatusBeneficiarioDDA;
    }

    /**
     * @return o atributo codISPBPartDestinatarioOrigAlerta
     */
    public String getCodISPBPartDestinatarioOrigAlerta() {
        return codISPBPartDestinatarioOrigAlerta;
    }

    /**
     * Define o atributo codISPBPartDestinatarioOrigAlerta
     */
    public void setCodISPBPartDestinatarioOrigAlerta(String codISPBPartDestinatarioOrigAlerta) {
        this.codISPBPartDestinatarioOrigAlerta = codISPBPartDestinatarioOrigAlerta;
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

}
