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
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ExtratoDDA é responsável por
 * 
 * @author rodrigo.neri
 */
@Entity
@Table(name = "ExtratoDDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.ExtratoDDAVO")
public class ExtratoDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = -1029741537939402740L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEXTRATODDA", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDLOGRECEBIMENTOOARQUIVODDA")
    private LogRecebimentoArquivoDDA logRecebimentoArquivoDDA;

    @Column(nullable = false)
    private DateTimeDB dataInicioApuracao;

    @Column(nullable = false)
    private DateTimeDB dataFimApuracao;

    private Integer numControleDDA;

    @Column(nullable = false)
    private DateTimeDB dataHoraApuracao;

    @Column(nullable = false)
    private DateTimeDB dataHoraInclusao;

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
     * @return o atributo logRecebimentoArquivoDDA
     */
    public LogRecebimentoArquivoDDA getLogRecebimentoArquivoDDA() {
        return logRecebimentoArquivoDDA;
    }

    /**
     * Define o atributo logRecebimentoArquivoDDA
     */
    public void setLogRecebimentoArquivoDDA(LogRecebimentoArquivoDDA logRecebimentoArquivoDDA) {
        this.logRecebimentoArquivoDDA = logRecebimentoArquivoDDA;
    }

    /**
     * @return o atributo dataInicioApuracao
     */
    public DateTimeDB getDataInicioApuracao() {
        return dataInicioApuracao;
    }

    /**
     * Define o atributo dataInicioApuracao
     */
    public void setDataInicioApuracao(DateTimeDB dataInicioApuracao) {
        this.dataInicioApuracao = dataInicioApuracao;
    }

    /**
     * @return o atributo dataFimApuracao
     */
    public DateTimeDB getDataFimApuracao() {
        return dataFimApuracao;
    }

    /**
     * Define o atributo dataFimApuracao
     */
    public void setDataFimApuracao(DateTimeDB dataFimApuracao) {
        this.dataFimApuracao = dataFimApuracao;
    }

    /**
     * @return o atributo numControleDDA
     */
    public Integer getNumControleDDA() {
        return numControleDDA;
    }

    /**
     * Define o atributo numControleDDA
     */
    public void setNumControleDDA(Integer numControleDDA) {
        this.numControleDDA = numControleDDA;
    }

    /**
     * @return o atributo dataHoraApuracao
     */
    public DateTimeDB getDataHoraApuracao() {
        return dataHoraApuracao;
    }

    /**
     * Define o atributo dataHoraApuracao
     */
    public void setDataHoraApuracao(DateTimeDB dataHoraApuracao) {
        this.dataHoraApuracao = dataHoraApuracao;
    }

    /**
     * @return o atributo dataHoraInclusao
     */
    public DateTimeDB getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    /**
     * Define o atributo dataHoraInclusao
     */
    public void setDataHoraInclusao(DateTimeDB dataHoraInclusao) {
        this.dataHoraInclusao = dataHoraInclusao;
    }

}