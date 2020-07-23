/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades-legado
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.dda
 * Arquivo:         DDABeneficiarioOperacao.java
 * Data Criação:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidadeslegado;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * DDABeneficiarioOperacao é responsável por
 * 
 * @author felipe.rosa
 */
@Entity
@Table(schema = "dbo", name = "DDABENEFICIARIOOPERACAO")
public class DDABeneficiarioOperacao extends SicoobDDAEntidadeLegado<DDABeneficiarioOperacaoPK> {

    /**
     * 
     */
    private static final long serialVersionUID = -6017784519092321206L;

    @EmbeddedId
    private DDABeneficiarioOperacaoPK pk;

    @ManyToOne
    @JoinColumn(name = "IDBENEFICIARIO", nullable = false, updatable = false, insertable = false)
    private DDABeneficiario beneficiario;

    @ManyToOne
    @JoinColumn(name = "CODOPERACAO", nullable = false, updatable = false, insertable = false)
    private DDAOperacao operacao;

    @Column(name = "DATAHORAATUALIZACAO")
    private DateTimeDB dataHoraAtualizacao;

    /**
     * 
     */
    public DDABeneficiarioOperacao() {

    }

    /**
     * @param beneficiario
     * @param operacao
     * @param dataHoraAtualizacao
     */
    public DDABeneficiarioOperacao(DDABeneficiario beneficiario, DDAOperacao operacao, DateTimeDB dataHoraAtualizacao) {
        super();
        this.beneficiario = beneficiario;
        this.operacao = operacao;
        this.dataHoraAtualizacao = dataHoraAtualizacao;
        this.pk = new DDABeneficiarioOperacaoPK(beneficiario == null ? null : beneficiario.getId(), operacao == null ? null : operacao.getCodOperacao());
    }

    /**
     * @return the beneficiario
     */
    public DDABeneficiario getBeneficiario() {
        return beneficiario;
    }

    /**
     * @param beneficiario the beneficiario to set
     */
    public void setBeneficiario(DDABeneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    /**
     * @return the operacao
     */
    public DDAOperacao getOperacao() {
        return operacao;
    }

    /**
     * @param operacao the operacao to set
     */
    public void setOperacao(DDAOperacao operacao) {
        this.operacao = operacao;
    }

    /**
     * @return the dataHoraAtualizacao
     */
    public DateTimeDB getDataHoraAtualizacao() {
        return dataHoraAtualizacao;
    }

    /**
     * @param dataHoraAtualizacao the dataHoraAtualizacao to set
     */
    public void setDataHoraAtualizacao(DateTimeDB dataHoraAtualizacao) {
        this.dataHoraAtualizacao = dataHoraAtualizacao;
    }

    /**
     * @return the pk
     */
    public DDABeneficiarioOperacaoPK getPk() {
        return pk;
    }

    /**
     * @param pk the pk to set
     */
    public void setPk(DDABeneficiarioOperacaoPK pk) {
        this.pk = pk;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.entidadeslegado.negocio.entidades.SicoobDDAEntidadeLegado#getIdSQL()
     */
    @Override
    public DDABeneficiarioOperacaoPK getIdSQL() {
        return pk;
    }

    /**
     * Método responsável por
     * 
     * @param pk void
     * 
     */
    public void setIdSQL(DDABeneficiarioOperacaoPK pk) {
        this.pk = pk;
    }
}
