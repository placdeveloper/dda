/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades
 * Arquivo:         LogRecebimentoArquivoDDA.java
 * Data Criação:    Sep 19, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * LogRecebimentoArquivoDDA é responsável por
 * 
 * @author Felipe.Rosa
 */
@Entity
@Table(name = "LOGRECEBIMENTOOARQUIVODDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogRecebimentoArquivoDDAVO")
public class LogRecebimentoArquivoDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = -3505914275170795231L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLOGRECEBIMENTOOARQUIVODDA", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "CODTIPOARQUIVO", nullable = false)
    private TipoArquivo tipoArquivo;

    @Column(nullable = false)
    private String descNomeArquivoRecebido;

    @Column(nullable = false)
    private DateTimeDB dataHoraArquivo;

    @Column(nullable = false)
    private DateTimeDB dataMovimento;

    @OneToOne
    @JoinColumn(name = "CODTIPOERROCIP", nullable = true)
    private TipoErroCip tipoErroCip;

    @OneToOne
    @JoinColumn(name = "IDLOGENVIOARQUIVODDA", nullable = true)
    private LogEnvioArquivoDDA logEnvioArquivoDDA;

    @ManyToOne
    @JoinColumn(name = "CODSITUACAOPROCESSAMENTOARQUIVO", nullable = false)
    private SituacaoProcessamentoArquivo situacaoProcessamentoArquivo;

    @ManyToOne
    @JoinColumn(name = "CODTIPOMENSAGEM", nullable = false)
    private TipoMensagemDDA tipoMensagemDDA;

    @OneToMany(mappedBy = "logRecebimentoArquivoDDA", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LogDetRecebimentoArquivoDDA> listaLogDetRecebimentoArquivoDDA;

    private Integer qtdRegistroArquivo;

    /**
     * 
     */
    public LogRecebimentoArquivoDDA() {
        super();
    }

    /**
     * @param descNomeArquivoRecebido
     */
    public LogRecebimentoArquivoDDA(String descNomeArquivoRecebido, DateTimeDB dataHoraArquivo) {
        super();
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
        this.dataHoraArquivo = dataHoraArquivo;
    }

    /**
     * @param id
     * @param descNomeArquivoRecebido
     * @param dataHoraArquivo
     * @param dataMovimento
     * @param codTipoMensagem
     * @param codTipoArquivo
     * @param codSituacaoProcessamentoArquivo
     */
    public LogRecebimentoArquivoDDA(Long id, String descNomeArquivoRecebido, Date dataHoraArquivo, Date dataMovimento, String codTipoMensagem, String codTipoArquivo,
            short codSituacaoProcessamentoArquivo) {
        this(descNomeArquivoRecebido, new DateTimeDB(dataHoraArquivo.getTime()));
        this.id = id;
        this.dataMovimento = new DateTimeDB(dataMovimento.getTime());
        this.tipoMensagemDDA = new TipoMensagemDDA(codTipoMensagem);
        this.tipoArquivo = new TipoArquivo(codTipoArquivo);
        this.situacaoProcessamentoArquivo = new SituacaoProcessamentoArquivo(codSituacaoProcessamentoArquivo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade#getId()
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the tipoArquivo
     */
    public TipoArquivo getTipoArquivo() {
        return tipoArquivo;
    }

    /**
     * @param tipoArquivo the tipoArquivo to set
     */
    public void setTipoArquivo(TipoArquivo tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    /**
     * @return the descNomeArquivoRecebido
     */
    public String getDescNomeArquivoRecebido() {
        return descNomeArquivoRecebido;
    }

    /**
     * @param descNomeArquivoRecebido the descNomeArquivoRecebido to set
     */
    public void setDescNomeArquivoRecebido(String descNomeArquivoRecebido) {
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
    }

    /**
     * @return the dataHoraArquivo
     */
    public DateTimeDB getDataHoraArquivo() {
        return dataHoraArquivo;
    }

    /**
     * @param dataHoraArquivo the dataHoraArquivo to set
     */
    public void setDataHoraArquivo(DateTimeDB dataHoraArquivo) {
        this.dataHoraArquivo = dataHoraArquivo;
    }

    /**
     * @return the dataMovimento
     */
    public DateTimeDB getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(DateTimeDB dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return the tipoErroCip
     */
    public TipoErroCip getTipoErroCip() {
        return tipoErroCip;
    }

    /**
     * @param tipoErroCip the tipoErroCip to set
     */
    public void setTipoErroCip(TipoErroCip tipoErroCip) {
        this.tipoErroCip = tipoErroCip;
    }

    /**
     * @return the logEnvioArquivoDDA
     */
    public LogEnvioArquivoDDA getLogEnvioArquivoDDA() {
        return logEnvioArquivoDDA;
    }

    /**
     * @param logEnvioArquivoDDA the logEnvioArquivoDDA to set
     */
    public void setLogEnvioArquivoDDA(LogEnvioArquivoDDA logEnvioArquivoDDA) {
        this.logEnvioArquivoDDA = logEnvioArquivoDDA;
    }

    /**
     * @return o atributo situacaoProcessamentoArquivo
     */
    public SituacaoProcessamentoArquivo getSituacaoProcessamentoArquivo() {
        return situacaoProcessamentoArquivo;
    }

    /**
     * Define o atributo situacaoProcessamentoArquivo
     */
    public void setSituacaoProcessamentoArquivo(SituacaoProcessamentoArquivo situacaoProcessamentoArquivo) {
        this.situacaoProcessamentoArquivo = situacaoProcessamentoArquivo;
    }

    /**
     * @return o atributo tipoMensagemDDA
     */
    public TipoMensagemDDA getTipoMensagemDDA() {
        return tipoMensagemDDA;
    }

    /**
     * Define o atributo tipoMensagemDDA
     */
    public void setTipoMensagemDDA(TipoMensagemDDA tipoMensagemDDA) {
        this.tipoMensagemDDA = tipoMensagemDDA;
    }

    /**
     * @return the listaLogDetRecebimentoArquivoDDA
     */
    public List<LogDetRecebimentoArquivoDDA> getListaLogDetRecebimentoArquivoDDA() {
        return listaLogDetRecebimentoArquivoDDA;
    }

    /**
     * @param listaLogDetRecebimentoArquivoDDA the listaLogDetRecebimentoArquivoDDA to set
     */
    public void setListaLogDetRecebimentoArquivoDDA(List<LogDetRecebimentoArquivoDDA> listaLogDetRecebimentoArquivoDDA) {
        this.listaLogDetRecebimentoArquivoDDA = listaLogDetRecebimentoArquivoDDA;
    }

    /**
     * @return o atributo qtdRegistroArquivo
     */
    public Integer getQtdRegistroArquivo() {
        return qtdRegistroArquivo;
    }

    /**
     * Define o atributo qtdRegistroArquivo
     */
    public void setQtdRegistroArquivo(Integer qtdRegistroArquivo) {
        this.qtdRegistroArquivo = qtdRegistroArquivo;
    }
}
