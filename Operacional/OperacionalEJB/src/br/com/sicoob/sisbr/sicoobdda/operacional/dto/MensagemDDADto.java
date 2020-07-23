/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
 * Arquivo:         MensagemDDADto.java
 * Data Criação:    Aug 31, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;
import br.com.sicoob.tipos.DateTime;

/**
 * MensagemDDADto é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.MensagemDDADTO")
public class MensagemDDADto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -6930013488350664209L;

    private Long idMensagem;
    private String codTipoMensagem;
    private String numOperacao;
    private Boolean bolOrigemSicoob;
    private Date dataHoraMensagem;
    private Date dataHoraProtocolo;
    private Short statusEnvio;
    private Boolean selecionado;

    private String descTipoTratamentoErroCip;

    private DateTime movimentoDataInicial;
    private DateTime movimentoDataFinal;
    
    

    /**
     * 
     */
    public MensagemDDADto() {
        super();
    }

    /**
     * @param idMensagem
     * @param codTipoMensagem
     * @param numOperacao
     * @param bolOrigemSicoob
     * @param dataHoraMensagem
     * @param dataHoraProtocolo
     * @param descErroProtocolo
     * @param qtdErroBeneficiarioRetornoCip
     * @param codTipoTratamentoErroCip
     * @param descTipoTratamentoErroCip
     */
    public MensagemDDADto(Long idMensagem, String codTipoMensagem, String numOperacao, Boolean bolOrigemSicoob, Date dataHoraMensagem, Date dataHoraProtocolo,
            String descErroProtocolo, Long qtdErroBeneficiarioRetornoCip, Short codTipoTratamentoErroCip, String descTipoTratamentoErroCip) {
        super();
        this.idMensagem = idMensagem;
        this.codTipoMensagem = codTipoMensagem;
        this.numOperacao = numOperacao;
        this.bolOrigemSicoob = bolOrigemSicoob;
        this.dataHoraMensagem = dataHoraMensagem;
        this.dataHoraProtocolo = dataHoraProtocolo;
        this.statusEnvio = getStatusEnvioRetornoQuery(descErroProtocolo, qtdErroBeneficiarioRetornoCip, codTipoTratamentoErroCip);
        this.descTipoTratamentoErroCip = descTipoTratamentoErroCip;
    }

    /**
     * @param idMensagem
     * @param codTipoMensagem
     * @param numOperacao
     * @param bolOrigemSicoob
     * @param dataHoraMensagem
     * @param dataHoraProtocolo
     * @param descErroProtocolo
     * @param qtdErroBeneficiarioRetornoCip
     */
    public MensagemDDADto(Long idMensagem, String codTipoMensagem, String numOperacao, Boolean bolOrigemSicoob, Date dataHoraMensagem, Date dataHoraProtocolo,
            String descErroProtocolo, Long qtdErroBeneficiarioRetornoCip) {
        super();
        this.idMensagem = idMensagem;
        this.codTipoMensagem = codTipoMensagem;
        this.numOperacao = numOperacao;
        this.bolOrigemSicoob = bolOrigemSicoob;
        this.dataHoraMensagem = dataHoraMensagem;
        this.dataHoraProtocolo = dataHoraProtocolo;
        this.descTipoTratamentoErroCip = descErroProtocolo;
        this.statusEnvio = getStatusEnvioRetornoQuery(descErroProtocolo, qtdErroBeneficiarioRetornoCip, null);
    }

    /**
     * Método responsável por popular o Status da tela de Monitoramento da mensagem
     * 
     * Caso retorno 0 - ERRO - Caso retorno 1 - SUCESSO Caso retorno 2 - CORRIGIDO
     * 
     * 
     * @param descErroProtocolo
     * @param qtdErroBeneficiarioRetornoCip
     * @param bolTratado
     * @return Short
     * 
     */
    public Short getStatusEnvioRetornoQuery(String descErroProtocolo, Long qtdErroBeneficiarioRetornoCip, Short codTipoTratamentoErroCip) {
        if (!ObjectUtil.isEmpty(descErroProtocolo) || !ObjectUtil.isEmpty(qtdErroBeneficiarioRetornoCip)) {
            if (!ObjectUtil.isNull(codTipoTratamentoErroCip) && codTipoTratamentoErroCip == TipoTratamentoErroCip.FINALIZAR_REENVIAR_NOVA_MENSAGEM) {
                return (short) 2;
            }
            return (short) 0;
        }
        return (short) 1;
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
     * @return the codMensagem
     */
    public String getCodTipoMensagem() {
        return codTipoMensagem;
    }

    /**
     * @param codMensagem the codMensagem to set
     */
    public void setCodTipoMensagem(String codMensagem) {
        this.codTipoMensagem = codMensagem;
    }

    /**
     * @return the numOperacao
     */
    public String getNumOperacao() {
        return numOperacao;
    }

    /**
     * @param numOperacao the numOperacao to set
     */
    public void setNumOperacao(String numOperacao) {
        this.numOperacao = numOperacao;
    }

    /**
     * @return the bolOrigemSicoob
     */
    public Boolean getBolOrigemSicoob() {
        return bolOrigemSicoob;
    }

    /**
     * @param bolOrigemSicoob the bolOrigemSicoob to set
     */
    public void setBolOrigemSicoob(Boolean bolOrigemSicoob) {
        this.bolOrigemSicoob = bolOrigemSicoob;
    }

    /**
     * @return the dataHoraMensagem
     */
    public Date getDataHoraMensagem() {
        return dataHoraMensagem;
    }

    /**
     * @param dataHoraMensagem the dataHoraMensagem to set
     */
    public void setDataHoraMensagem(Date dataHoraMensagem) {
        this.dataHoraMensagem = dataHoraMensagem;
    }

    /**
     * @return the dataHoraProtocolo
     */
    public Date getDataHoraProtocolo() {
        return dataHoraProtocolo;
    }

    /**
     * @param dataHoraProtocolo the dataHoraProtocolo to set
     */
    public void setDataHoraProtocolo(Date dataHoraProtocolo) {
        this.dataHoraProtocolo = dataHoraProtocolo;
    }

    /**
     * @return the statusEnvio
     */
    public Short getStatusEnvio() {
        return statusEnvio;
    }

    /**
     * @param statusEnvio the statusEnvio to set
     */
    public void setStatusEnvio(Short statusEnvio) {
        this.statusEnvio = statusEnvio;
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
     * @return the movimentoDataInicial
     */
    public DateTime getMovimentoDataInicial() {
        return movimentoDataInicial;
    }

    /**
     * @param movimentoDataInicial the movimentoDataInicial to set
     */
    public void setMovimentoDataInicial(DateTime movimentoDataInicial) {
        this.movimentoDataInicial = movimentoDataInicial;
    }

    /**
     * @return the movimentoDataFinal
     */
    public DateTime getMovimentoDataFinal() {
        return movimentoDataFinal;
    }

    /**
     * @param movimentoDataFinal the movimentoDataFinal to set
     */
    public void setMovimentoDataFinal(DateTime movimentoDataFinal) {
        this.movimentoDataFinal = movimentoDataFinal;
    }

    /**
     * @return o atributo descTipoTratamentoErroCip
     */
    public String getDescTipoTratamentoErroCip() {
        return descTipoTratamentoErroCip;
    }

    /**
     * Define o atributo descTipoTratamentoErroCip
     */
    public void setDescTipoTratamentoErroCip(String descTipoTratamentoErroCip) {
        this.descTipoTratamentoErroCip = descTipoTratamentoErroCip;
    }

}
