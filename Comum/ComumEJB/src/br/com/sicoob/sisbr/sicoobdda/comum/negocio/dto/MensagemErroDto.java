/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MensagemErroDto.java
 * Data Criação:    Sep 14, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.sql.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * MensagemErroDto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemErroDTO")
public class MensagemErroDto extends BancoobDto {

    private static final long serialVersionUID = -5283702414679530308L;

    private DateTimeDB dataMovimento;
    private Long idMensagem;
    private DateTimeDB dataHoraCadastro;
    private DateTimeDB dataHoraMensagem;
    private String codTipoMensagemDDA;
    private String tipoDoc;
    private String identificador;
    private String codTipoErroCIP;
    private String descTipoErroCIP;
    private Integer idErroMensagemRetornoCIP;
    private String descSituacaoMensagemDDA;

    private String descNomeArquivoRecebido;
    private Long idLogRecebimentoArquivoDDA;
    private Long idLogDetRecebimentoArquivoDDA;

    private String descErroProtocolo;

    private Boolean selecionado;

    /**
     * 
     */
    public MensagemErroDto() {
        super();
    }

    /**
     * Construtor QUERY - OBTER_LISTA_TRATAMENTO_ERRO_CIP - OBTER_LISTA_TRATAMENTO_RETORNO_ERRO
     * 
     * @param dataMovimento
     * @param idMensagem
     * @param codTipoMensagemDDA
     * @param tipoDoc
     * @param identificador
     * @param codTipoErroCIP
     * @param descTipoErroCIP
     * @param idErroMensagemRetornoCIP
     * @param descSituacaoMensagemDDA
     * @param dataHoraCadastro
     * @param dataHoraMensagem
     */
    public MensagemErroDto(Date dataMovimento, Long idMensagem, String codTipoMensagemDDA, String tipoDoc, String identificador, String codTipoErroCIP, String descTipoErroCIP,
            Integer idErroMensagemRetornoCIP, String descSituacaoMensagemDDA, Date dataHoraCadastro, Date dataHoraMensagem) {
        super();
        this.dataMovimento = new DateTimeDB(dataMovimento.getTime());
        this.idMensagem = idMensagem;
        this.codTipoMensagemDDA = codTipoMensagemDDA;
        this.tipoDoc = tipoDoc;
        this.identificador = identificador;
        this.codTipoErroCIP = codTipoErroCIP;
        this.descTipoErroCIP = descTipoErroCIP;
        this.idErroMensagemRetornoCIP = idErroMensagemRetornoCIP;
        this.descSituacaoMensagemDDA = descSituacaoMensagemDDA;
        this.dataHoraCadastro = new DateTimeDB(dataHoraCadastro.getTime());
        if (dataHoraMensagem != null) {
            this.dataHoraMensagem = new DateTimeDB(dataHoraMensagem.getTime());
        }
    }

    /**
     * Construtor Erros Agrupados
     * 
     * @param dataHoraCadastro
     * @param dataMovimento
     * @param codTipoMensagemDDA
     * @param tipoDoc
     * @param identificador
     * @param codTipoErroCIP
     * @param descTipoErroCIP
     * @param idErroMensagemRetornoCip
     * @param descSituacaoMensagemDDA
     * @param dataHoraMensagem
     */
    public MensagemErroDto(Long idMensagem, Date dataHoraCadastro, Date dataMovimento, String codTipoMensagemDDA, String tipoDoc, String identificador, String codTipoErroCIP, String descTipoErroCIP,
            Integer idErroMensagemRetornoCip, String descSituacaoMensagemDDA, Date dataHoraMensagem) {
        super();
        this.dataMovimento = new DateTimeDB(dataMovimento.getTime());
        this.idMensagem = idMensagem;
        this.codTipoMensagemDDA = codTipoMensagemDDA;
        this.tipoDoc = tipoDoc;
        this.identificador = identificador;
        this.codTipoErroCIP = codTipoErroCIP;
        this.descTipoErroCIP = descTipoErroCIP;
        this.dataHoraCadastro = new DateTimeDB(dataHoraCadastro.getTime());
        this.idErroMensagemRetornoCIP = idErroMensagemRetornoCip;
        this.descSituacaoMensagemDDA = descSituacaoMensagemDDA;
        this.selecionado = false;
        if (dataHoraMensagem != null) {
            this.dataHoraMensagem = new DateTimeDB(dataHoraMensagem.getTime());
        }
    }

    /**
     * Construtor QUERY - OBTER_LISTA_TRATAMENTO_MENSAGEM_ARQUIVO
     * 
     * @param dataMovimento
     * @param codTipoErroCIP
     * @param descTipoErroCIP
     * @param codTipoMensagem
     * @param descNomeArquivoRecebido
     * @param idLogRecebimentoArquivoDDA
     */
    public MensagemErroDto(Date dataMovimento, String codTipoErroCIP, String descTipoErroCIP, String codTipoMensagem, String descNomeArquivoRecebido, Long idLogRecebimentoArquivoDDA) {
        super();
        this.dataMovimento = new DateTimeDB(dataMovimento.getTime());
        this.codTipoErroCIP = codTipoErroCIP;
        this.descTipoErroCIP = descTipoErroCIP;
        this.codTipoMensagemDDA = codTipoMensagem;
        this.descNomeArquivoRecebido = descNomeArquivoRecebido;
        this.idLogRecebimentoArquivoDDA = idLogRecebimentoArquivoDDA;
    }

    /**
     * Construtor QUERY - PESQUISAR_MENSAGEM_ERRO_CONTINGENCIA
     * 
     * @param dataMovimento
     * @param idMensagem
     * @param codTipoMensagemDDA
     * @param descErroProtocolo
     */
    public MensagemErroDto(java.util.Date dataMovimento, Long idMensagem, String codTipoMensagemDDA, String descErroProtocolo, java.util.Date dataHoraCadastro) {
        super();
        this.dataMovimento = new DateTimeDB(dataMovimento.getTime());
        this.idMensagem = idMensagem;
        this.codTipoMensagemDDA = codTipoMensagemDDA;
        this.descErroProtocolo = descErroProtocolo;
        this.dataHoraCadastro = new DateTimeDB(dataHoraCadastro.getTime());
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
     * @return the idMensagem
     */
    public Long getIdMensagem() {
        return idMensagem;
    }

    /**
     * @param idMensagem the idMensagem to set
     */
    public void setIdMensagem(Long idMensagem) {
        this.idMensagem = idMensagem;
    }

    /**
     * @return the codTipoMensagemDDA
     */
    public String getCodTipoMensagemDDA() {
        return codTipoMensagemDDA;
    }

    /**
     * @param codTipoMensagemDDA the codTipoMensagemDDA to set
     */
    public void setCodTipoMensagemDDA(String codTipoMensagemDDA) {
        this.codTipoMensagemDDA = codTipoMensagemDDA;
    }

    /**
     * @return the tipoDoc
     */
    public String getTipoDoc() {
        return tipoDoc;
    }

    /**
     * @param tipoDoc the tipoDoc to set
     */
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the codTipoErroCIP
     */
    public String getCodTipoErroCIP() {
        return codTipoErroCIP;
    }

    /**
     * @param codTipoErroCIP the codTipoErroCIP to set
     */
    public void setCodTipoErroCIP(String codTipoErroCIP) {
        this.codTipoErroCIP = codTipoErroCIP;
    }

    /**
     * @return the descTipoErroCIP
     */
    public String getDescTipoErroCIP() {
        return descTipoErroCIP;
    }

    /**
     * @param descTipoErroCIP the descTipoErroCIP to set
     */
    public void setDescTipoErroCIP(String descTipoErroCIP) {
        this.descTipoErroCIP = descTipoErroCIP;
    }

    /**
     * @return the idErroMensagemRetornoCIP
     */
    public Integer getIdErroMensagemRetornoCIP() {
        return idErroMensagemRetornoCIP;
    }

    /**
     * @param idErroMensagemRetornoCIP the idErroMensagemRetornoCIP to set
     */
    public void setIdErroMensagemRetornoCIP(Integer idErroMensagemRetornoCIP) {
        this.idErroMensagemRetornoCIP = idErroMensagemRetornoCIP;
    }

    /**
     * @return the selecionado
     */
    public Boolean getSelecionado() {
        return selecionado;
    }

    /**
     * @param selecionado the selecionado to set
     */
    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    /**
     * @return the descSituacaoMensagemDDA
     */
    public String getDescSituacaoMensagemDDA() {
        return descSituacaoMensagemDDA;
    }

    /**
     * @param descSituacaoMensagemDDA the descSituacaoMensagemDDA to set
     */
    public void setDescSituacaoMensagemDDA(String descSituacaoMensagemDDA) {
        this.descSituacaoMensagemDDA = descSituacaoMensagemDDA;
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
     * @return the idLogRecebimentoArquivoDDA
     */
    public Long getIdLogRecebimentoArquivoDDA() {
        return idLogRecebimentoArquivoDDA;
    }

    /**
     * @param idLogRecebimentoArquivoDDA the idLogRecebimentoArquivoDDA to set
     */
    public void setIdLogRecebimentoArquivoDDA(Long idLogRecebimentoArquivoDDA) {
        this.idLogRecebimentoArquivoDDA = idLogRecebimentoArquivoDDA;
    }

    /**
     * @return the idLogDetRecebimentoArquivoDDA
     */
    public Long getIdLogDetRecebimentoArquivoDDA() {
        return idLogDetRecebimentoArquivoDDA;
    }

    /**
     * @param idLogDetRecebimentoArquivoDDA the idLogDetRecebimentoArquivoDDA to set
     */
    public void setIdLogDetRecebimentoArquivoDDA(Long idLogDetRecebimentoArquivoDDA) {
        this.idLogDetRecebimentoArquivoDDA = idLogDetRecebimentoArquivoDDA;
    }

    /**
     * @return the descErroProtocolo
     */
    public String getDescErroProtocolo() {
        return descErroProtocolo;
    }

    /**
     * @param descErroProtocolo the descErroProtocolo to set
     */
    public void setDescErroProtocolo(String descErroProtocolo) {
        this.descErroProtocolo = descErroProtocolo;
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
