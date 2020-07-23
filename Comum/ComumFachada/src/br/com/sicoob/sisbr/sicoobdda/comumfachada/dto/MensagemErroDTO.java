package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemErroDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemErroDto")
public class MensagemErroDTO extends BancoobDTO {

    private DateTime dataMovimento;
    private Long idMensagem;
    private DateTime dataHoraCadastro;
    private DateTime dataHoraMensagem;
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
     * @return the dataMovimento
     */
    public DateTime getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(DateTime dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    /**
     * @return o atributo dataHoraMensagem
     */
    public DateTime getDataHoraMensagem() {
        return dataHoraMensagem;
    }

    /**
     * Define o atributo dataHoraMensagem
     */
    public void setDataHoraMensagem(DateTime dataHoraMensagem) {
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
    public DateTime getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    /**
     * Define o atributo dataHoraCadastro
     */
    public void setDataHoraCadastro(DateTime dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }
}
