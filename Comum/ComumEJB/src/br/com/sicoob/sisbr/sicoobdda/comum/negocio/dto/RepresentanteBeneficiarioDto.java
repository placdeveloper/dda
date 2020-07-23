/**
 * Projeto:         SicoobDDa
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         RepresentanteClienteBeneficiarioDto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;

/**
 * RepresentanteClienteBeneficiarioDto
 * 
 * @author Rafael.Silva
 */
public class RepresentanteBeneficiarioDto extends BancoobDto {

    private static final long serialVersionUID = 3631262981673248211L;

    private TipoPessoaEnum tipoPessoaRepresentanteBeneficiario;

    private String cnpjCpfRepresentanteBeneficiario;

    /**
     * @return the tipoPessoaRepresentanteBeneficiario
     */
    public TipoPessoaEnum getTipoPessoaRepresentanteBeneficiario() {
        return tipoPessoaRepresentanteBeneficiario;
    }

    /**
     * @param tipoPessoaRepresentanteBeneficiario the tipoPessoaRepresentanteBeneficiario to set
     */
    public void setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum tipoPessoaRepresentanteBeneficiario) {
        this.tipoPessoaRepresentanteBeneficiario = tipoPessoaRepresentanteBeneficiario;
    }

    /**
     * @return the cnpjCpfRepresentanteBeneficiario
     */
    public String getCnpjCpfRepresentanteBeneficiario() {
        return cnpjCpfRepresentanteBeneficiario;
    }

    /**
     * @param cnpjCpfRepresentanteBeneficiario the cnpjCpfRepresentanteBeneficiario to set
     */
    public void setCnpjCpfRepresentanteBeneficiario(String cnpjCpfRepresentanteBeneficiario) {
        this.cnpjCpfRepresentanteBeneficiario = cnpjCpfRepresentanteBeneficiario;
    }

}
