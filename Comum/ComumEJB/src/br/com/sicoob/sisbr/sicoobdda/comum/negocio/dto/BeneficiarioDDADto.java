/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.dto
 * Arquivo:         BeneficiarioDDADto.java
 * Data Criação:    Jul 3, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCarteiraCobrancaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;

/**
 * BeneficiarioDDADto é responsável por
 * 
 * @author Jesliel.Rocha
 */
public class BeneficiarioDDADto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -655462290589266245L;

    private TipoPessoaEnum tipoPessoaBeneficiario;
    private String cnpjCpfBeneficiario;
    private String nomeRazaoSocialBeneficiario;
    private String nomeFantasiaBeneficiario;
    private SituacaoBeneficiarioEnum situacaoBeneficiario;
    private SituacaoRelacionamentoParticipanteEnum situacaoRelacionamentoParticipante;
    private Date dataHoraInclusaoBeneficiario;
    private Date dataHoraExclusaoBeneficiario;
    private Date dataInicioRelacionamentoParticipante;
    private Date dataFimRelacionamentoParticipante;
    private TipoCarteiraCobrancaEnum tipoCarteira;
    private String banco;
    private String agencia;

    public BeneficiarioDDADto() {

    }

    /**
     * @param tipoPessoaBeneficiario
     * @param cnpjCpfBeneficiario
     * @param nomeRazaoSocialBeneficiario
     * @param nomeFantasiaBeneficiario
     * @param situacaoBeneficiario
     * @param situacaoRelacionamentoParticipante
     * @param dataHoraInclusaoBeneficiario
     * @param dataHoraExclusaoBeneficiario
     * @param dataInicioRelacionamentoParticipante
     * @param dataFimRelacionamentoParticipante
     * @param tipoCarteira
     * @param banco
     * @param agencia
     */
    public BeneficiarioDDADto(String tipoPessoaBeneficiario, String cnpjCpfBeneficiario, String nomeRazaoSocialBeneficiario, String nomeFantasiaBeneficiario,
            String situacaoBeneficiario, String situacaoRelacionamentoParticipante, Date dataHoraInclusaoBeneficiario, Date dataHoraExclusaoBeneficiario,
            Date dataInicioRelacionamentoParticipante, Date dataFimRelacionamentoParticipante, String tipoCarteira, String banco, String agencia) {
        super();
        this.tipoPessoaBeneficiario = TipoPessoaEnum.getBy(tipoPessoaBeneficiario);
        this.cnpjCpfBeneficiario = cnpjCpfBeneficiario.trim();
        this.nomeRazaoSocialBeneficiario = nomeRazaoSocialBeneficiario.trim();
        this.nomeFantasiaBeneficiario = nomeFantasiaBeneficiario != null ? nomeFantasiaBeneficiario.trim() : null;
        // Adaptacao autorizada pela Suest pois o nomeFantasia e obrigatorio para a CIP.
        if (ObjectUtil.isEmpty(nomeFantasiaBeneficiario) && isPessoaJuridica()) {
            this.nomeFantasiaBeneficiario = this.nomeRazaoSocialBeneficiario;
        }
        this.situacaoBeneficiario = SituacaoBeneficiarioEnum.getBy(situacaoBeneficiario);
        this.situacaoRelacionamentoParticipante = SituacaoRelacionamentoParticipanteEnum.getBy(situacaoRelacionamentoParticipante);
        this.dataHoraInclusaoBeneficiario = dataHoraInclusaoBeneficiario;
        this.dataHoraExclusaoBeneficiario = dataHoraExclusaoBeneficiario;
        this.dataInicioRelacionamentoParticipante = dataInicioRelacionamentoParticipante;
        this.dataFimRelacionamentoParticipante = dataFimRelacionamentoParticipante;
        this.tipoCarteira = TipoCarteiraCobrancaEnum.getBy(tipoCarteira);
        this.banco = banco;
        this.agencia = agencia;
    }

    /**
     * @return o atributo tipoPessoaBeneficiario
     */
    public TipoPessoaEnum getTipoPessoaBeneficiario() {
        return tipoPessoaBeneficiario;
    }

    /**
     * Define o atributo tipoPessoaBeneficiario
     */
    public void setTipoPessoaBeneficiario(TipoPessoaEnum tipoPessoaBeneficiario) {
        this.tipoPessoaBeneficiario = tipoPessoaBeneficiario;
    }

    /**
     * @return o atributo cnpjCpfBeneficiario
     */
    public String getCnpjCpfBeneficiario() {
        return cnpjCpfBeneficiario;
    }

    /**
     * Define o atributo cnpjCpfBeneficiario
     */
    public void setCnpjCpfBeneficiario(String cnpjCpfBeneficiario) {
        this.cnpjCpfBeneficiario = cnpjCpfBeneficiario;
    }

    /**
     * @return o atributo nomeRazaoSocialBeneficiario
     */
    public String getNomeRazaoSocialBeneficiario() {
        return nomeRazaoSocialBeneficiario;
    }

    /**
     * Define o atributo nomeRazaoSocialBeneficiario
     */
    public void setNomeRazaoSocialBeneficiario(String nomeRazaoSocialBeneficiario) {
        this.nomeRazaoSocialBeneficiario = nomeRazaoSocialBeneficiario;
    }

    /**
     * @return o atributo nomeFantasiaBeneficiario
     */
    public String getNomeFantasiaBeneficiario() {
        return nomeFantasiaBeneficiario;
    }

    /**
     * Define o atributo nomeFantasiaBeneficiario
     */
    public void setNomeFantasiaBeneficiario(String nomeFantasiaBeneficiario) {
        this.nomeFantasiaBeneficiario = nomeFantasiaBeneficiario;
    }

    /**
     * @return o atributo situacaoBeneficiario
     */
    public SituacaoBeneficiarioEnum getSituacaoBeneficiario() {
        return situacaoBeneficiario;
    }

    /**
     * Define o atributo situacaoBeneficiario
     */
    public void setSituacaoBeneficiario(SituacaoBeneficiarioEnum situacaoBeneficiario) {
        this.situacaoBeneficiario = situacaoBeneficiario;
    }

    /**
     * @return o atributo situacaoRelacionamentoParticipante
     */
    public SituacaoRelacionamentoParticipanteEnum getSituacaoRelacionamentoParticipante() {
        return situacaoRelacionamentoParticipante;
    }

    /**
     * Define o atributo situacaoRelacionamentoParticipante
     */
    public void setSituacaoRelacionamentoParticipante(SituacaoRelacionamentoParticipanteEnum situacaoRelacionamentoParticipante) {
        this.situacaoRelacionamentoParticipante = situacaoRelacionamentoParticipante;
    }

    /**
     * @return o atributo dataHoraInclusaoBeneficiario
     */
    public Date getDataHoraInclusaoBeneficiario() {
        return dataHoraInclusaoBeneficiario;
    }

    /**
     * Define o atributo dataHoraInclusaoBeneficiario
     */
    public void setDataHoraInclusaoBeneficiario(Date dataHoraInclusaoBeneficiario) {
        this.dataHoraInclusaoBeneficiario = dataHoraInclusaoBeneficiario;
    }

    /**
     * @return o atributo dataHoraExclusaoBeneficiario
     */
    public Date getDataHoraExclusaoBeneficiario() {
        return dataHoraExclusaoBeneficiario;
    }

    /**
     * Define o atributo dataHoraExclusaoBeneficiario
     */
    public void setDataHoraExclusaoBeneficiario(Date dataHoraExclusaoBeneficiario) {
        this.dataHoraExclusaoBeneficiario = dataHoraExclusaoBeneficiario;
    }

    /**
     * @return o atributo dataInicioRelacionamentoParticipante
     */
    public Date getDataInicioRelacionamentoParticipante() {
        return dataInicioRelacionamentoParticipante;
    }

    /**
     * Define o atributo dataInicioRelacionamentoParticipante
     */
    public void setDataInicioRelacionamentoParticipante(Date dataInicioRelacionamentoParticipante) {
        this.dataInicioRelacionamentoParticipante = dataInicioRelacionamentoParticipante;
    }

    /**
     * @return o atributo dataFimRelacionamentoParticipante
     */
    public Date getDataFimRelacionamentoParticipante() {
        return dataFimRelacionamentoParticipante;
    }

    /**
     * Define o atributo dataFimRelacionamentoParticipante
     */
    public void setDataFimRelacionamentoParticipante(Date dataFimRelacionamentoParticipante) {
        this.dataFimRelacionamentoParticipante = dataFimRelacionamentoParticipante;
    }

    /**
     * @return o atributo tipoCarteira
     */
    public TipoCarteiraCobrancaEnum getTipoCarteira() {
        return tipoCarteira;
    }

    /**
     * Define o atributo tipoCarteira
     */
    public void setTipoCarteira(TipoCarteiraCobrancaEnum tipoCarteira) {
        this.tipoCarteira = tipoCarteira;
    }

    /**
     * @return o atributo banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * Define o atributo banco
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * @return o atributo agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * Define o atributo agencia
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * 
     * Método responsável por
     * 
     * @return boolean
     * 
     */
    public boolean isPessoaJuridica() {
        if (ObjectUtil.isNull(getTipoPessoaBeneficiario())) {
            return false;
        }
        return getTipoPessoaBeneficiario().equals(TipoPessoaEnum.PJ);
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cnpjCpfBeneficiario == null) ? 0 : cnpjCpfBeneficiario.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BeneficiarioDDADto other = (BeneficiarioDDADto) obj;
        if (cnpjCpfBeneficiario == null) {
            if (other.cnpjCpfBeneficiario != null) {
                return false;
            }
        } else if (!cnpjCpfBeneficiario.equals(other.cnpjCpfBeneficiario)) {
            return false;
        }
        return true;
    }

}
