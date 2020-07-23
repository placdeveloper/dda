/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         CadastroSerie0200Dto.java
 * Data Criação:    Jan 13, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.math.BigInteger;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * CadastroSerie0200Dto é responsável por 
 * 
 * @author felipe.rosa
 */
public class CadastroSerie0200Dto extends BancoobDto {

    private static final long serialVersionUID = -8831275505592313857L;

    private DateTimeDB dataInicioApuracao;
    private DateTimeDB dataFimApuracao;
    private BigInteger numIdentLancamento;
    private String codTipoTransacao;
    private String codTipoConsulta;
    private String codTipoRetorno;

    private DateTimeDB dataHoraRef;
    private String tipoMensagemArquivo;
    private String codTipoMensagem;

    private String numOpMensagem;
    private String identArquivoc;

    /**
     * @return the dataInicioApuracao
     */
    public DateTimeDB getDataInicioApuracao() {
        return dataInicioApuracao;
    }

    /**
     * @param dataInicioApuracao the dataInicioApuracao to set
     */
    public void setDataInicioApuracao(DateTimeDB dataInicioApuracao) {
        this.dataInicioApuracao = dataInicioApuracao;
    }

    /**
     * @return the dataFimApuracao
     */
    public DateTimeDB getDataFimApuracao() {
        return dataFimApuracao;
    }

    /**
     * @param dataFimApuracao the dataFimApuracao to set
     */
    public void setDataFimApuracao(DateTimeDB dataFimApuracao) {
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
    public DateTimeDB getDataHoraRef() {
        return dataHoraRef;
    }

    /**
     * @param dataHoraRef the dataHoraRef to set
     */
    public void setDataHoraRef(DateTimeDB dataHoraRef) {
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
