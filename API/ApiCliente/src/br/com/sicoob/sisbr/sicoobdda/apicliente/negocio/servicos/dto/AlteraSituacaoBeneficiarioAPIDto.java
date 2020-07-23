/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-api-cliente
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.api.cliente.negocio.servicos.dto
 * Arquivo:         AlterarSituacaoBeneficiarioAPIDto.java
 * Data Criação:    Aug 24, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.SituacaoBeneficiarioAPIEnum;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.TipoPessoaAPIEnum;

/**
 * AlterarSituacaoBeneficiarioAPIDto é responsável por
 * 
 * @author felipe.rosa
 */
public class AlteraSituacaoBeneficiarioAPIDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -2863975436483458410L;

    public static final String[] CAMPOS = new String[] { "cnpjCpfBeneficiario", "dataSituacaoBeneficiario" };

    private TipoPessoaAPIEnum tipoPessoaBeneficiario;

    private String cnpjCpfBeneficiario;

    private SituacaoBeneficiarioAPIEnum situacaoBeneficiario;

    private Date dataSituacaoBeneficiario;

    /**
     * @return the tipoPessoaBeneficiario
     */
    public TipoPessoaAPIEnum getTipoPessoaBeneficiario() {
        return tipoPessoaBeneficiario;
    }

    /**
     * @param tipoPessoaBeneficiario the tipoPessoaBeneficiario to set
     */
    public void setTipoPessoaBeneficiario(TipoPessoaAPIEnum tipoPessoaBeneficiario) {
        this.tipoPessoaBeneficiario = tipoPessoaBeneficiario;
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
     * @return the situacaoBeneficiario
     */
    public SituacaoBeneficiarioAPIEnum getSituacaoBeneficiario() {
        return situacaoBeneficiario;
    }

    /**
     * @param situacaoBeneficiario the situacaoBeneficiario to set
     */
    public void setSituacaoBeneficiario(SituacaoBeneficiarioAPIEnum situacaoBeneficiario) {
        this.situacaoBeneficiario = situacaoBeneficiario;
    }

    /**
     * @return the dataSituacaoBeneficiario
     */
    public Date getDataSituacaoBeneficiario() {
        return dataSituacaoBeneficiario;
    }

    /**
     * @param dataSituacaoBeneficiario the dataSituacaoBeneficiario to set
     */
    public void setDataSituacaoBeneficiario(Date dataSituacaoBeneficiario) {
        this.dataSituacaoBeneficiario = dataSituacaoBeneficiario;
    }

}
