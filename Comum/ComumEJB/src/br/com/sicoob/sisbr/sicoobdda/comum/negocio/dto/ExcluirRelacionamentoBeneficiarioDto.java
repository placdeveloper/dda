/**
 * Projeto:         IntegracaoCIP
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.dto
 * Arquivo:         BeneficiarioDto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * BeneficiarioDto
 * 
 * @author Rafael.Silva
 */
public class ExcluirRelacionamentoBeneficiarioDto extends BancoobDto {

    private static final long serialVersionUID = 6280895349575995358L;

    private TipoPessoaEnum tipoPessoaBeneficiario;

    private String cnpjCpfBeneficiario;

    /**
     * 
     */
    public ExcluirRelacionamentoBeneficiarioDto() {
        super();
    }

    /**
     * Metodo j� seta o TipoPesso pelo cnpjCpfBeneficiario
     * 
     * @param cnpjCpfBeneficiario
     */
    public ExcluirRelacionamentoBeneficiarioDto(String cnpjCpfBeneficiario) {
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
     * @return the tipoPessoaBeneficiarioEnum
     */
    public TipoPessoaEnum getTipoPessoaBeneficiario() {
        return tipoPessoaBeneficiario;
    }

    /**
     * @param tipoPessoaBeneficiarioEnum the tipoPessoaBeneficiarioEnum to set
     */
    public void setTipoPessoaBeneficiario(TipoPessoaEnum tipoPessoaBeneficiarioEnum) {
        this.tipoPessoaBeneficiario = tipoPessoaBeneficiarioEnum;
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

}
