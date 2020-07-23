/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         BeneficiarioDto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * AlteraSituacaoBeneficiarioDto
 * 
 * @author Rafael.Silva
 */
public class AlteraSituacaoBeneficiarioDto extends BancoobDto {

    private static final long serialVersionUID = 4173379238806176176L;

    private TipoPessoaEnum tipoPessoaBeneficiario;

    private String cnpjCpfBeneficiario;

    private SituacaoBeneficiarioEnum situacaoBeneficiario;

    private Date dataSituacaoBeneficiario;

    /**
     * 
     */
    public AlteraSituacaoBeneficiarioDto() {
        super();
    }

    /**
     * @param cnpjCpfBeneficiario
     */
    public AlteraSituacaoBeneficiarioDto(String cnpjCpfBeneficiario) {
        super();
        this.cnpjCpfBeneficiario = cnpjCpfBeneficiario;
        if (!ObjectUtil.isEmpty(cnpjCpfBeneficiario)) {
            if (cnpjCpfBeneficiario.length() == Constantes.TAMANHO_CPF) {
                this.tipoPessoaBeneficiario = TipoPessoaEnum.PF;
            } else if (cnpjCpfBeneficiario.length() == Constantes.TAMANHO_CNPJ) {
                this.tipoPessoaBeneficiario = TipoPessoaEnum.PJ;
            }
        }
    }

    /**
     * @return the tipoPessoaBeneficiario
     */
    public TipoPessoaEnum getTipoPessoaBeneficiario() {
        return tipoPessoaBeneficiario;
    }

    /**
     * @param tipoPessoaBeneficiario the tipoPessoaBeneficiario to set
     */
    public void setTipoPessoaBeneficiario(TipoPessoaEnum tipoPessoaBeneficiario) {
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
    public SituacaoBeneficiarioEnum getSituacaoBeneficiario() {
        return situacaoBeneficiario;
    }

    /**
     * @param situacaoBeneficiario the situacaoBeneficiario to set
     */
    public void setSituacaoBeneficiario(SituacaoBeneficiarioEnum situacaoBeneficiario) {
        this.situacaoBeneficiario = situacaoBeneficiario;
    }

    /**
     * @return the dataHoraSituacaoBeneficiario
     */
    public Date getDataSituacaoBeneficiario() {
        return dataSituacaoBeneficiario;
    }

    /**
     * @param dataHoraSituacaoBeneficiario the dataHoraSituacaoBeneficiario to set
     */
    public void setDataSituacaoBeneficiario(Date dataHoraSituacaoBeneficiario) {
        this.dataSituacaoBeneficiario = dataHoraSituacaoBeneficiario;
    }

}
