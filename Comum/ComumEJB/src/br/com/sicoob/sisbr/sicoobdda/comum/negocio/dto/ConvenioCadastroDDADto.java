/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
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
public class ConvenioCadastroDDADto extends BancoobDto {

    private static final long serialVersionUID = -5300107074833566598L;

    private String ispbParticipanteIncorporado;

    private SituacaoConvenioBeneficiarioEnum situcaoConvenio;

    private Date dataInicioConvenio;

    private TipoAgenciaEnum tipoAgencia;

    private Integer numAgencia;

    private TipoContaEnum tipoConta;

    private Integer numConta;

    private TipoProdutoConvenioEnum tipoProdutoConvenio;

    private TipoCarteiraCobrancaEnum tipoCarteiraConvenio;

    private String codClienteConvenio;

    /**
     * 
     */
    public ConvenioCadastroDDADto() {
        super();
    }

    /**
     * @param numAgencia
     */
    public ConvenioCadastroDDADto(Integer numAgencia) {
        super();
        this.numAgencia = numAgencia;
    }

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
     * @return the situcaoConvenioEnum
     */
    public SituacaoConvenioBeneficiarioEnum getSitucaoConvenio() {
        return situcaoConvenio;
    }

    /**
     * @param situcaoConvenioEnum the situcaoConvenioEnum to set
     */
    public void setSitucaoConvenio(SituacaoConvenioBeneficiarioEnum situcaoConvenioEnum) {
        this.situcaoConvenio = situcaoConvenioEnum;
    }

    /**
     * @return the dataInicioConvenio
     */
    public Date getDataInicioConvenio() {
        return dataInicioConvenio;
    }

    /**
     * @param dataInicioConvenio the dataInicioConvenio to set
     */
    public void setDataInicioConvenio(Date dataInicioConvenio) {
        this.dataInicioConvenio = dataInicioConvenio;
    }

    /**
     * @return the tipoAgencia
     */
    public TipoAgenciaEnum getTipoAgencia() {
        return tipoAgencia;
    }

    /**
     * @param tipoAgencia the tipoAgencia to set
     */
    public void setTipoAgencia(TipoAgenciaEnum tipoAgencia) {
        this.tipoAgencia = tipoAgencia;
    }

    /**
     * @return the numAgencia
     */
    public Integer getNumAgencia() {
        return numAgencia;
    }

    /**
     * @param numAgencia the numAgencia to set
     */
    public void setNumAgencia(Integer numAgencia) {
        this.numAgencia = numAgencia;
    }

    /**
     * @return the tipoConta
     */
    public TipoContaEnum getTipoConta() {
        return tipoConta;
    }

    /**
     * @param tipoConta the tipoConta to set
     */
    public void setTipoConta(TipoContaEnum tipoConta) {
        this.tipoConta = tipoConta;
    }

    /**
     * @return the numConta
     */
    public Integer getNumConta() {
        return numConta;
    }

    /**
     * @param numConta the numConta to set
     */
    public void setNumConta(Integer numConta) {
        this.numConta = numConta;
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
     * @return the tipoCarteiraConvenio
     */
    public TipoCarteiraCobrancaEnum getTipoCarteiraConvenio() {
        return tipoCarteiraConvenio;
    }

    /**
     * @param tipoCarteiraConvenio the tipoCarteiraConvenio to set
     */
    public void setTipoCarteiraConvenio(TipoCarteiraCobrancaEnum tipoCarteiraConvenio) {
        this.tipoCarteiraConvenio = tipoCarteiraConvenio;
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
