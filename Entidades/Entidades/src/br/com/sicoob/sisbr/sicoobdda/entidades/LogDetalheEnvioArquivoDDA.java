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

/**
 * DDALOGDETALHEENVIOARQUIVODDA
 * 
 * @author george.santos
 */
@Entity
@Table(name = "LOGDETALHEENVIOARQUIVODDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogDetalheEnvioArquivoDDAVO")
public class LogDetalheEnvioArquivoDDA extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -2482988932242853010L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLOGDETALHEENVIOARQUIVODDA", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "IDLOGENVIOARQUIVODDA", nullable = false)
    private LogEnvioArquivoDDA logEnvioArquivoDDA;

    @ManyToOne
    @JoinColumn(name = "IDMENSAGEMDDA", nullable = false)
    private MensagemDDA mensagemDDA;

    /**
     * 
     */
    public LogDetalheEnvioArquivoDDA() {
        super();
    }

    /**
     * Construtor para QUERY: LISTAR_DET_ENVIO_ARQUIVO_MENSAGEM_DDA
     * 
     * @param id
     */
    public LogDetalheEnvioArquivoDDA(Long idMensagemDDA) {
        super();
        this.mensagemDDA = new MensagemDDA(idMensagemDDA);
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
     * @return o atributo logEnvioArquivoDDA
     */
    public LogEnvioArquivoDDA getLogEnvioArquivoDDA() {
        return logEnvioArquivoDDA;
    }

    /**
     * Define o atributo logEnvioArquivoDDA
     */
    public void setLogEnvioArquivoDDA(LogEnvioArquivoDDA logEnvioArquivoDDA) {
        this.logEnvioArquivoDDA = logEnvioArquivoDDA;
    }

    /**
     * @return o atributo mensagemDDA
     */
    public MensagemDDA getMensagemDDA() {
        return mensagemDDA;
    }

    /**
     * Define o atributo mensagemDDA
     */
    public void setMensagemDDA(MensagemDDA mensagemDDA) {
        this.mensagemDDA = mensagemDDA;
    }
}
