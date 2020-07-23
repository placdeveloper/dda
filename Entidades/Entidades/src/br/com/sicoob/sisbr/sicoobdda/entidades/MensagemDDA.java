/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         DDAMensagem.java
 * Data Criação:    Jul 27, 2015
 */
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
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemDDA
 * 
 * @author rafael.silva
 */
/**
 * MensagemDDA é responsável por
 * 
 * @author George.Santos
 */
@Entity
@Table(name = "MENSAGEMDDA", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.MensagemDDAVO")
public class MensagemDDA extends SicoobDDAEntidade {

    /** * */
    private static final long serialVersionUID = 6157356586108177224L;

    // NUMCTRLPART
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMENSAGEMDDA", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMENSAGEMDDAORIGEM")
    private MensagemDDA mensagemOrigem;

    @OneToMany(mappedBy = "mensagemOrigem")
    private List<MensagemDDA> listaMensagemDDA;

    @ManyToOne
    @JoinColumn(name = "CODTIPOMENSAGEMDDA", nullable = false)
    private TipoMensagemDDA tipoMensagemDDA;

    @Column(nullable = false)
    private DateTimeDB dataMovimento;

    private DateTimeDB dataHoraMensagem;

    @Column(name = "QTDTOTREG")
    private Integer qtdTotalRegistros;

    private DateTimeDB dataHoraProtocolo;

    private String numOperacao;

    private String descErroProtocolo;

    private String xmlMensagem;

    @Column(nullable = false)
    private Boolean bolOrigemSicoob;

    @Column(nullable = false)
    private DateTimeDB dataHoraCadastro;

    @Column(nullable = false)
    private Integer numPrioridadeEnvio;

    private String numControleDDA;

    private String idUsuarioSolicitante;

    private Integer idInstituicaoSolicitante;

    private Short idCanal;

    @Column(nullable = false)
    private Boolean bolExcedeuSLA;

    @OneToMany(mappedBy = "mensagemDDA", cascade = CascadeType.ALL)
    private List<ErroMensagemRetornoCip> listaErroMensagemRetornoCip;

    /**
     * 
     */
    public MensagemDDA() {
    }

    /**
     * @param id
     */
    public MensagemDDA(Long id) {
        super();
        this.id = id;
    }

    /**
     * @param tipoMensagemDDA
     * @param dataMovimento
     * @param dataHoraCadastro
     * @param numPrioridadeEnvio
     */
    public MensagemDDA(String codTipoMensagemDDA, DateTimeDB dataMovimento, DateTimeDB dataHoraCadastro, Integer numPrioridadeEnvio, Boolean bolOrigemSicoob) {
        super();
        this.tipoMensagemDDA = new TipoMensagemDDA(codTipoMensagemDDA);
        this.dataMovimento = dataMovimento;
        this.dataHoraCadastro = dataHoraCadastro;
        this.numPrioridadeEnvio = numPrioridadeEnvio;
        this.bolOrigemSicoob = bolOrigemSicoob;
    }

    /**
     * @param id
     * @param idMensamgeDDAOrigem
     * @param codTipoMensagem
     * @param dataMovimento
     * @param dataHoraMensagem
     * @param dataHoraProtocolo
     * @param numOperacao
     * @param xmlMensagem
     * @param bolOrigemSicoob
     * @param dataHoraCadastro
     * @param numPrioridadeEnvio
     * @param numControleDDA
     */
    public MensagemDDA(Long id, Long idMensamgeDDAOrigem, String codTipoMensagem, DateTimeDB dataMovimento, DateTimeDB dataHoraMensagem, DateTimeDB dataHoraProtocolo,
            String numOperacao, String xmlMensagem, Boolean bolOrigemSicoob, DateTimeDB dataHoraCadastro, Integer numPrioridadeEnvio, String numControleDDA) {
        super();
        this.id = id;
        this.mensagemOrigem = new MensagemDDA(idMensamgeDDAOrigem);
        this.tipoMensagemDDA = new TipoMensagemDDA(codTipoMensagem);
        this.dataMovimento = dataMovimento;
        this.dataHoraMensagem = dataHoraMensagem;
        this.dataHoraProtocolo = dataHoraProtocolo;
        this.numOperacao = numOperacao;
        this.xmlMensagem = xmlMensagem;
        this.bolOrigemSicoob = bolOrigemSicoob;
        this.dataHoraCadastro = dataHoraCadastro;
        this.numPrioridadeEnvio = numPrioridadeEnvio;
        this.numControleDDA = numControleDDA;
    }

    /**
     * @param id
     * @param idMensamgeDDAOrigem
     * @param codTipoMensagem
     * @param dataMovimento
     * @param dataHoraMensagem
     * @param dataHoraProtocolo
     * @param numOperacao
     * @param xmlMensagem
     * @param bolOrigemSicoob
     * @param dataHoraCadastro
     * @param numPrioridadeEnvio
     * @param numControleDDA
     * @param bolExcedeuSLA
     * @param idCanal
     * @param idInstituicaoSolicitante
     * @param idUsuarioSolicitante
     */
    public MensagemDDA(Long id, Long idMensamgeDDAOrigem, String codTipoMensagem, DateTimeDB dataMovimento, DateTimeDB dataHoraMensagem, DateTimeDB dataHoraProtocolo,
            String numOperacao, String xmlMensagem, Boolean bolOrigemSicoob, DateTimeDB dataHoraCadastro, Integer numPrioridadeEnvio, String numControleDDA, Boolean bolExcedeuSLA,
            Short idCanal, Integer idInstituicaoSolicitante, String idUsuarioSolicitante) {
        this(id, idMensamgeDDAOrigem, codTipoMensagem, dataMovimento, dataHoraMensagem, dataHoraProtocolo, numOperacao, xmlMensagem, bolOrigemSicoob, dataHoraCadastro,
                numPrioridadeEnvio, numControleDDA);
        this.bolExcedeuSLA = bolExcedeuSLA;
        this.idCanal = idCanal;
        this.idInstituicaoSolicitante = idInstituicaoSolicitante;
        this.idUsuarioSolicitante = idUsuarioSolicitante;
    }

    /**
     * Construtor QUERY - OBTER_MENSAGEM_ERRO
     * 
     * @param id
     * @param descErroProtocolo
     * @param xmlMensagem
     * @param codTipoMensagem
     */
    public MensagemDDA(Long id, String xmlMensagem, String descErroProtocolo, String codTipoMensagem) {
        super();
        this.id = id;
        this.descErroProtocolo = descErroProtocolo;
        this.xmlMensagem = xmlMensagem;
        this.tipoMensagemDDA = new TipoMensagemDDA(codTipoMensagem);
    }

    /**
     * Construtor QUERY - OBTER_MENSAGEM_ERRO_LOCK_REGISTRO
     * 
     * @param id
     * @param xmlMensagem
     */
    public MensagemDDA(Long id, String xmlMensagem) {
        super();
        this.id = id;
        this.xmlMensagem = xmlMensagem;
    }

    /**
     * Método responsável por definir o valor default para os campos antes da inclusão
     */
    @PrePersist
    public void prePersist() {
        if (bolExcedeuSLA == null) {
            bolExcedeuSLA = Boolean.FALSE;
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
     * @return o atributo mensagemOrigem
     */
    public MensagemDDA getMensagemOrigem() {
        return mensagemOrigem;
    }

    /**
     * Define o atributo mensagemOrigem
     */
    public void setMensagemOrigem(MensagemDDA mensagemOrigem) {
        this.mensagemOrigem = mensagemOrigem;
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
     * @return o atributo dataHoraMensagem
     */
    public DateTimeDB getDataHoraMensagem() {
        return dataHoraMensagem;
    }

    /**
     * Define o atributo dataHoraMensagem
     */
    public void setDataHoraMensagem(DateTimeDB dataHoraMensagem) {
        this.dataHoraMensagem = dataHoraMensagem;
    }

    /**
     * @return o atributo qtdTotalRegistros
     */
    public Integer getQtdTotalRegistros() {
        return qtdTotalRegistros;
    }

    /**
     * Define o atributo qtdTotalRegistros
     */
    public void setQtdTotalRegistros(Integer qtdTotalRegistros) {
        this.qtdTotalRegistros = qtdTotalRegistros;
    }

    /**
     * @return o atributo dataHoraProtocolo
     */
    public DateTimeDB getDataHoraProtocolo() {
        return dataHoraProtocolo;
    }

    /**
     * Define o atributo dataHoraProtocolo
     */
    public void setDataHoraProtocolo(DateTimeDB dataHoraProtocolo) {
        this.dataHoraProtocolo = dataHoraProtocolo;
    }

    /**
     * @return o atributo numOperacao
     */
    public String getNumOperacao() {
        return numOperacao;
    }

    /**
     * Define o atributo numOperacao
     */
    public void setNumOperacao(String numOperacao) {
        this.numOperacao = numOperacao;
    }

    /**
     * @return o atributo descErroProtocolo
     */
    public String getDescErroProtocolo() {
        return descErroProtocolo;
    }

    /**
     * Define o atributo descErroProtocolo
     */
    public void setDescErroProtocolo(String descErroProtocolo) {
        this.descErroProtocolo = descErroProtocolo;
    }

    /**
     * @return o atributo xmlMensagem
     */
    public String getXmlMensagem() {
        return xmlMensagem;
    }

    /**
     * Define o atributo xmlMensagem
     */
    public void setXmlMensagem(String xmlMensagem) {
        this.xmlMensagem = xmlMensagem;
    }

    /**
     * @return o atributo bolOrigemSicoob
     */
    public Boolean getBolOrigemSicoob() {
        return bolOrigemSicoob;
    }

    /**
     * Define o atributo bolOrigemSicoob
     */
    public void setBolOrigemSicoob(Boolean bolOrigemSicoob) {
        this.bolOrigemSicoob = bolOrigemSicoob;
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
     * @return o atributo numPrioridadeEnvio
     */
    public Integer getNumPrioridadeEnvio() {
        return numPrioridadeEnvio;
    }

    /**
     * Define o atributo numPrioridadeEnvio
     */
    public void setNumPrioridadeEnvio(Integer numPrioridadeEnvio) {
        this.numPrioridadeEnvio = numPrioridadeEnvio;
    }

    /**
     * @return o atributo numControleDDA
     */
    public String getNumControleDDA() {
        return numControleDDA;
    }

    /**
     * Define o atributo numControleDDA
     */
    public void setNumControleDDA(String numControleDDA) {
        this.numControleDDA = numControleDDA;
    }

    /**
     * @return o atributo idUsuarioSolicitante
     */
    public String getIdUsuarioSolicitante() {
        return idUsuarioSolicitante;
    }

    /**
     * Define o atributo idUsuarioSolicitante
     */
    public void setIdUsuarioSolicitante(String idUsuarioSolicitante) {
        this.idUsuarioSolicitante = idUsuarioSolicitante;
    }

    /**
     * @return o atributo idInstituicaoSolicitante
     */
    public Integer getIdInstituicaoSolicitante() {
        return idInstituicaoSolicitante;
    }

    /**
     * Define o atributo idInstituicaoSolicitante
     */
    public void setIdInstituicaoSolicitante(Integer idInstituicaoSolicitante) {
        this.idInstituicaoSolicitante = idInstituicaoSolicitante;
    }

    /**
     * @return o atributo idCanal
     */
    public Short getIdCanal() {
        return idCanal;
    }

    /**
     * Define o atributo idCanal
     */
    public void setIdCanal(Short idCanal) {
        this.idCanal = idCanal;
    }

    /**
     * @return o atributo bolExcedeuSLA
     */
    public Boolean getBolExcedeuSLA() {
        return bolExcedeuSLA;
    }

    /**
     * Define o atributo bolExcedeuSLA
     */
    public void setBolExcedeuSLA(Boolean bolExcedeuSLA) {
        this.bolExcedeuSLA = bolExcedeuSLA;
    }

    /**
     * @return o atributo listaErroMensagemRetornoCip
     */
    public List<ErroMensagemRetornoCip> getListaErroMensagemRetornoCip() {
        return listaErroMensagemRetornoCip;
    }

    /**
     * Define o atributo listaErroMensagemRetornoCip
     */
    public void setListaErroMensagemRetornoCip(List<ErroMensagemRetornoCip> listaErroMensagemRetornoCip) {
        this.listaErroMensagemRetornoCip = listaErroMensagemRetornoCip;
    }

    /**
     * @return o atributo listaMensagemDDA
     */
    public List<MensagemDDA> getListaMensagemDDA() {
        return listaMensagemDDA;
    }

    /**
     * Define o atributo listaMensagemDDA
     */
    public void setListaMensagemDDA(List<MensagemDDA> listaMensagemDDA) {
        this.listaMensagemDDA = listaMensagemDDA;
    }

}
