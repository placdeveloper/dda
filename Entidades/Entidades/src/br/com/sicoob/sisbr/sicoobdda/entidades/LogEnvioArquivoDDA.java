package br.com.sicoob.sisbr.sicoobdda.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * DDALOGENVIOARQUIVODDA
 * 
 * @author george.santos
 */
@Entity
@Table(name = "LOGENVIOARQUIVODDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.LogEnvioArquivoDDAVO")
public class LogEnvioArquivoDDA extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = -2482988932242853010L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLOGENVIOARQUIVODDA", unique = true, nullable = false)
    private Long id;

    private String descNomeArquivoEnviado;

    private DateTimeDB dataHoraArquivo;

    private DateTimeDB dataMovimento;

    private DateTimeDB dataHoraEnvio;

    @ManyToOne
    @JoinColumn(name = "CODTIPOMENSAGEM", nullable = false)
    private TipoMensagemDDA tipoMensagem;

    @OneToMany(mappedBy = "logEnvioArquivoDDA", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LogDetalheEnvioArquivoDDA> listaLogDetalheEnvioArquivoDDA;

    public LogEnvioArquivoDDA() {
    	super();
    }

    public LogEnvioArquivoDDA(Long id) {
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
     * @return o atributo descNomeArquivoEnviado
     */
    public String getDescNomeArquivoEnviado() {
        return descNomeArquivoEnviado;
    }

    /**
     * Define o atributo descNomeArquivoEnviado
     */
    public void setDescNomeArquivoEnviado(String descNomeArquivoEnviado) {
        this.descNomeArquivoEnviado = descNomeArquivoEnviado;
    }

    /**
     * @return o atributo dataHoraArquivo
     */
    public DateTimeDB getDataHoraArquivo() {
        return dataHoraArquivo;
    }

    /**
     * Define o atributo dataHoraArquivo
     */
    public void setDataHoraArquivo(DateTimeDB dataHoraArquivo) {
        this.dataHoraArquivo = dataHoraArquivo;
    }

    /**
     * @return o atributo dataMovimento
     */
    public DateTimeDB getDataMovimento() {
        return dataMovimento;
    }

    /**
     * Define o atributo dataMovimento
     */
    public void setDataMovimento(DateTimeDB dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo dataHoraEnvio
     */
    public DateTimeDB getDataHoraEnvio() {
        return dataHoraEnvio;
    }

    /**
     * Define o atributo dataHoraEnvio
     */
    public void setDataHoraEnvio(DateTimeDB dataHoraEnvio) {
        this.dataHoraEnvio = dataHoraEnvio;
    }

    /**
     * @return o atributo tipoMensagem
     */
    public TipoMensagemDDA getTipoMensagem() {
        return tipoMensagem;
    }

    /**
     * Define o atributo tipoMensagem
     */
    public void setTipoMensagem(TipoMensagemDDA tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }

    /**
     * @return o atributo listaLogDetalheEnvioArquivoDDA
     */
    public List<LogDetalheEnvioArquivoDDA> getListaLogDetalheEnvioArquivoDDA() {
        return listaLogDetalheEnvioArquivoDDA;
    }

    /**
     * Define o atributo listaLogDetalheEnvioArquivoDDA
     */
    public void setListaLogDetalheEnvioArquivoDDA(List<LogDetalheEnvioArquivoDDA> listaLogDetalheEnvioArquivoDDA) {
        this.listaLogDetalheEnvioArquivoDDA = listaLogDetalheEnvioArquivoDDA;
    }

}
