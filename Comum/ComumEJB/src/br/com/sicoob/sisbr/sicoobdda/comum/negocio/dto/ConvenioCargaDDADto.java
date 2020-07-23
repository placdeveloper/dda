/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.dto
 * Arquivo:         ConvenioDto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCarteiraCobrancaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoContaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoProdutoConvenioEnum;

/**
 * CadastroConvenioDDADto
 * 
 * @author Rafael.Silva
 */
public class ConvenioCargaDDADto extends BancoobDto {

    private static final long serialVersionUID = -5300107074833566598L;

    private String ispbParticipanteIncorporado;

    private SituacaoConvenioBeneficiarioEnum situacaoConvenioBeneficiarioEnum;

    private Date dataInicioRelacionamentoConvenio;

    private Date dataFimRelacionamentoConvenio;

    private TipoAgenciaEnum tipoAgenciaDestino;

    private Integer agenciaDestino;

    private TipoContaEnum tipoContaDestino;

    private Integer contaDestino;

    private TipoProdutoConvenioEnum tipoProdutoConvenio;

    private TipoCarteiraCobrancaEnum tipoCarteiraConvenioCobranca;

    private String codClienteConvenio;

    /**
     * @return the ispbParticipanteIncorporado
     */
    public String getIspbParticipanteIncorporado() {
        return ispbParticipanteIncorporado;
    }

    /**
     * @param ispbParticipanteIncorporado the ispbParticipanteIncorporado to set
     */
    public void setIspbParticipanteIncorporado(String ispbParticipanteIncorporado) {
        this.ispbParticipanteIncorporado = ispbParticipanteIncorporado;
    }

    /**
     * @return the situcaoConvenioBeneficiarioEnum
     */
    public SituacaoConvenioBeneficiarioEnum getSitucaoConvenioBeneficiarioEnum() {
        return situacaoConvenioBeneficiarioEnum;
    }

    /**
     * @param situacaoConvenioBeneficiarioEnum the situcaoConvenioBeneficiarioEnum to set
     */
    public void setSitucaoConvenioBeneficiarioEnum(SituacaoConvenioBeneficiarioEnum situacaoConvenioBeneficiarioEnum) {
        this.situacaoConvenioBeneficiarioEnum = situacaoConvenioBeneficiarioEnum;
    }

    /**
     * @return the dataInicioRelacionamentoConvenio
     */
    public Date getDataInicioRelacionamentoConvenio() {
        return dataInicioRelacionamentoConvenio;
    }

    /**
     * @param dataInicioRelacionamentoConvenio the dataInicioRelacionamentoConvenio to set
     */
    public void setDataInicioRelacionamentoConvenio(Date dataInicioRelacionamentoConvenio) {
        this.dataInicioRelacionamentoConvenio = dataInicioRelacionamentoConvenio;
    }

    /**
     * @return the dataFimRelacionamentoConvenio
     */
    public Date getDataFimRelacionamentoConvenio() {
        return dataFimRelacionamentoConvenio;
    }

    /**
     * @param dataFimRelacionamentoConvenio the dataFimRelacionamentoConvenio to set
     */
    public void setDataFimRelacionamentoConvenio(Date dataFimRelacionamentoConvenio) {
        this.dataFimRelacionamentoConvenio = dataFimRelacionamentoConvenio;
    }

    /**
     * @return the tipoAgenciaDestino
     */
    public TipoAgenciaEnum getTipoAgenciaDestino() {
        return tipoAgenciaDestino;
    }

    /**
     * @param tipoAgenciaDestino the tipoAgenciaDestino to set
     */
    public void setTipoAgenciaDestino(TipoAgenciaEnum tipoAgenciaDestino) {
        this.tipoAgenciaDestino = tipoAgenciaDestino;
    }

    /**
     * @return the agenciaDestino
     */
    public Integer getAgenciaDestino() {
        return agenciaDestino;
    }

    /**
     * @param agenciaDestino the agenciaDestino to set
     */
    public void setAgenciaDestino(Integer agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
    }

    /**
     * @return the tipoContaDestino
     */
    public TipoContaEnum getTipoContaDestino() {
        return tipoContaDestino;
    }

    /**
     * @param tipoContaDestino the tipoContaDestino to set
     */
    public void setTipoContaDestino(TipoContaEnum tipoContaDestino) {
        this.tipoContaDestino = tipoContaDestino;
    }

    /**
     * @return the contaDestino
     */
    public Integer getContaDestino() {
        return contaDestino;
    }

    /**
     * @param contaDestino the contaDestino to set
     */
    public void setContaDestino(Integer contaDestino) {
        this.contaDestino = contaDestino;
    }

    /**
     * @return the tipoProdutoConvenio
     */
    public TipoProdutoConvenioEnum getTipoProdutoConvenio() {
        return tipoProdutoConvenio;
    }

    /**
     * @param tipoProdutoConvenio the tipoProdutoConvenio to set
     */
    public void setTipoProdutoConvenio(TipoProdutoConvenioEnum tipoProdutoConvenio) {
        this.tipoProdutoConvenio = tipoProdutoConvenio;
    }

    /**
     * @return the tipoCarteiraConvenioCobranca
     */
    public TipoCarteiraCobrancaEnum getTipoCarteiraConvenioCobranca() {
        return tipoCarteiraConvenioCobranca;
    }

    /**
     * @param tipoCarteiraConvenioCobranca the tipoCarteiraConvenioCobranca to set
     */
    public void setTipoCarteiraConvenioCobranca(TipoCarteiraCobrancaEnum tipoCarteiraConvenioCobranca) {
        this.tipoCarteiraConvenioCobranca = tipoCarteiraConvenioCobranca;
    }

    /**
     * @return the codClienteConvenio
     */
    public String getCodClienteConvenio() {
        return codClienteConvenio;
    }

    /**
     * @param codClienteConvenio the codClienteConvenio to set
     */
    public void setCodClienteConvenio(String codClienteConvenio) {
        this.codClienteConvenio = codClienteConvenio;
    }

}
