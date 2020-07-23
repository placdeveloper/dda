/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.dto
 * Arquivo:         ConsultaBeneficiarioDto.java
 * Data Criacao:    May 14, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;

/**
 * ConsultaBeneficiarioDto
 * 
 * @author Rafael.Silva
 */
public class ConsultaBeneficiarioDto extends BancoobDto {

    private static final long serialVersionUID = -5280830836311261951L;

    private TipoPessoaEnum tipoPessoaBeneficiarioEnum;

    private String cnpjCpfBeneficiario;

    private SituacaoBeneficiarioEnum situacaoBeneficiarioEnum;

    private Date dataInicioUltAlteracaoSituacao;

    private Date dataFimUltAlteracaoSituacao;

    private SituacaoRelacionamentoParticipanteEnum situacaoRelacionamentoParticipante;

    private Date dataMovimento;

    /**
     * @return the tipoPessoaBeneficiarioEnum
     */
    public TipoPessoaEnum getTipoPessoaBeneficiarioEnum() {
        return tipoPessoaBeneficiarioEnum;
    }

    /**
     * @param tipoPessoaBeneficiarioEnum the tipoPessoaBeneficiarioEnum to set
     */
    public void setTipoPessoaBeneficiarioEnum(TipoPessoaEnum tipoPessoaBeneficiarioEnum) {
        this.tipoPessoaBeneficiarioEnum = tipoPessoaBeneficiarioEnum;
    }

    /**
     * @return the cnpjCpfBeneficiario
     */
    public String getCnpjCpfBeneficiario() {
        return cnpjCpfBeneficiario;
    }

    /**
     * @param cnpjCpfBeneficiario the cnpjCpfBeneficiario to set
     */
    public void setCnpjCpfBeneficiario(String cnpjCpfBeneficiario) {
        this.cnpjCpfBeneficiario = cnpjCpfBeneficiario;
    }

    /**
     * @return the situacaoBeneficiarioEnum
     */
    public SituacaoBeneficiarioEnum getSituacaoBeneficiarioEnum() {
        return situacaoBeneficiarioEnum;
    }

    /**
     * @param situacaoBeneficiarioEnum the situacaoBeneficiarioEnum to set
     */
    public void setSituacaoBeneficiarioEnum(SituacaoBeneficiarioEnum situacaoBeneficiarioEnum) {
        this.situacaoBeneficiarioEnum = situacaoBeneficiarioEnum;
    }

    /**
     * @return the dataInicioUltAlteracaoSituacao
     */
    public Date getDataInicioUltAlteracaoSituacao() {
        return dataInicioUltAlteracaoSituacao;
    }

    /**
     * @param dataInicioUltAlteracaoSituacao the dataInicioUltAlteracaoSituacao to set
     */
    public void setDataInicioUltAlteracaoSituacao(Date dataInicioUltAlteracaoSituacao) {
        this.dataInicioUltAlteracaoSituacao = dataInicioUltAlteracaoSituacao;
    }

    /**
     * @return the dataFimUltAlteracaoSituacao
     */
    public Date getDataFimUltAlteracaoSituacao() {
        return dataFimUltAlteracaoSituacao;
    }

    /**
     * @param dataFimUltAlteracaoSituacao the dataFimUltAlteracaoSituacao to set
     */
    public void setDataFimUltAlteracaoSituacao(Date dataFimUltAlteracaoSituacao) {
        this.dataFimUltAlteracaoSituacao = dataFimUltAlteracaoSituacao;
    }

    /**
     * @return the situacaoRelacionamentoParticipante
     */
    public SituacaoRelacionamentoParticipanteEnum getSituacaoRelacionamentoParticipante() {
        return situacaoRelacionamentoParticipante;
    }

    /**
     * @param situacaoRelacionamentoParticipante the situacaoRelacionamentoParticipante to set
     */
    public void setSituacaoRelacionamentoParticipante(SituacaoRelacionamentoParticipanteEnum situacaoRelacionamentoParticipante) {
        this.situacaoRelacionamentoParticipante = situacaoRelacionamentoParticipante;
    }

    /**
     * @return the dataMovimento
     */
    public Date getDataMovimento() {
        return dataMovimento;
    }

    /**
     * @param dataMovimento the dataMovimento to set
     */
    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

}
