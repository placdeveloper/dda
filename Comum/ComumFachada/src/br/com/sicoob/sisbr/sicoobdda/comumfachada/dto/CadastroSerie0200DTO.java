package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.math.BigInteger;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * CadastroSerie0200DTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto")
public class CadastroSerie0200DTO extends BancoobDTO {

    private DateTime dataInicioApuracao;
    private DateTime dataFimApuracao;
    private BigInteger numIdentLancamento;
    private String codTipoTransacao;
    private String codTipoConsulta;
    private String codTipoRetorno;
    private DateTime dataHoraRef;
    private String tipoMensagemArquivo;
    private String codTipoMensagem;
    private String numOpMensagem;
    private String identArquivoc;

    /**
     * @return the dataInicioApuracao
     */
    public DateTime getDataInicioApuracao() {
        return dataInicioApuracao;
    }

    /**
     * @param dataInicioApuracao the dataInicioApuracao to set
     */
    public void setDataInicioApuracao(DateTime dataInicioApuracao) {
        this.dataInicioApuracao = dataInicioApuracao;
    }

    /**
     * @return the dataFimApuracao
     */
    public DateTime getDataFimApuracao() {
        return dataFimApuracao;
    }

    /**
     * @param dataFimApuracao the dataFimApuracao to set
     */
    public void setDataFimApuracao(DateTime dataFimApuracao) {
        this.dataFimApuracao = dataFimApuracao;
    }

    /**
     * @return the numIdentLancamento
     */
    public BigInteger getNumIdentLancamento() {
        return numIdentLancamento;
    }

    /**
     * @param numIdentLancamento the numIdentLancamento to set
     */
    public void setNumIdentLancamento(BigInteger numIdentLancamento) {
        this.numIdentLancamento = numIdentLancamento;
    }

    /**
     * @return the codTipoTransacao
     */
    public String getCodTipoTransacao() {
        return codTipoTransacao;
    }

    /**
     * @param codTipoTransacao the codTipoTransacao to set
     */
    public void setCodTipoTransacao(String codTipoTransacao) {
        this.codTipoTransacao = codTipoTransacao;
    }

    /**
     * @return the codTipoConsulta
     */
    public String getCodTipoConsulta() {
        return codTipoConsulta;
    }

    /**
     * @param codTipoConsulta the codTipoConsulta to set
     */
    public void setCodTipoConsulta(String codTipoConsulta) {
        this.codTipoConsulta = codTipoConsulta;
    }

    /**
     * @return the codTipoRetorno
     */
    public String getCodTipoRetorno() {
        return codTipoRetorno;
    }

    /**
     * @param codTipoRetorno the codTipoRetorno to set
     */
    public void setCodTipoRetorno(String codTipoRetorno) {
        this.codTipoRetorno = codTipoRetorno;
    }

    /**
     * @return the dataHoraRef
     */
    public DateTime getDataHoraRef() {
        return dataHoraRef;
    }

    /**
     * @param dataHoraRef the dataHoraRef to set
     */
    public void setDataHoraRef(DateTime dataHoraRef) {
        this.dataHoraRef = dataHoraRef;
    }

    /**
     * @return the tipoMensagemArquivo
     */
    public String getTipoMensagemArquivo() {
        return tipoMensagemArquivo;
    }

    /**
     * @param tipoMensagemArquivo the tipoMensagemArquivo to set
     */
    public void setTipoMensagemArquivo(String tipoMensagemArquivo) {
        this.tipoMensagemArquivo = tipoMensagemArquivo;
    }

    /**
     * @return the codTipoMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * @param codTipoMensagem the codTipoMensagem to set
     */
    public void setCodTipoMensagem(String codTipoMensagem) {
        this.codTipoMensagem = codTipoMensagem;
    }

    /**
     * @return the numOpMensagem
     */
    public String getNumOpMensagem() {
        return numOpMensagem;
    }

    /**
     * @param numOpMensagem the numOpMensagem to set
     */
    public void setNumOpMensagem(String numOpMensagem) {
        this.numOpMensagem = numOpMensagem;
    }

    /**
     * @return the identArquivoc
     */
    public String getIdentArquivoc() {
        return identArquivoc;
    }

    /**
     * @param identArquivoc the identArquivoc to set
     */
    public void setIdentArquivoc(String identArquivoc) {
        this.identArquivoc = identArquivoc;
    }

}
