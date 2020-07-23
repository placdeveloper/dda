/**
 * Projeto:         SicoobDDa
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         AlterarCadastroBeneficiarioDDADto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;

/**
 * AlterarCadastroBeneficiarioDDADto
 * 
 * @author Rafael.Silva
 */
public class AlterarCadastroBeneficiarioDDADto extends BancoobDto {

    private static final long serialVersionUID = 2928581114653723708L;

    private TipoPessoaEnum tipoPessoaBeneficiario;

    private String cnpjCpfBeneficiario;

    private String nomeRazaoSocialBeneficiario;

    private String nomeFantasiaBeneficiario;

    private Date dataInicioRelacionamento;

    private List<ConvenioAlteracaoDDADto> listaConvenioAlteracaoDto;

    private List<RepresentanteBeneficiarioAlteracaoDto> listaRepresentanteBeneficiarioDto;

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
     * @return the nomeRazaoSocialBeneficiario
     */
    public String getNomeRazaoSocialBeneficiario() {
        return nomeRazaoSocialBeneficiario;
    }

    /**
     * @param nomeRazaoSocialBeneficiario the nomeRazaoSocialBeneficiario to set
     */
    public void setNomeRazaoSocialBeneficiario(String nomeRazaoSocialBeneficiario) {
        this.nomeRazaoSocialBeneficiario = nomeRazaoSocialBeneficiario;
    }

    /**
     * @return the nomeFantasiaBeneficiario
     */
    public String getNomeFantasiaBeneficiario() {
        return nomeFantasiaBeneficiario;
    }

    /**
     * @param nomeFantasiaBeneficiario the nomeFantasiaBeneficiario to set
     */
    public void setNomeFantasiaBeneficiario(String nomeFantasiaBeneficiario) {
        this.nomeFantasiaBeneficiario = nomeFantasiaBeneficiario;
    }

    /**
     * @return the dataInicioRelacionamento
     */
    public Date getDataInicioRelacionamento() {
        return dataInicioRelacionamento;
    }

    /**
     * @param dataInicioRelacionamento the dataInicioRelacionamento to set
     */
    public void setDataInicioRelacionamento(Date dataInicioRelacionamento) {
        this.dataInicioRelacionamento = dataInicioRelacionamento;
    }

    /**
     * @return the listaConvenioAlteracaoDto
     */
    public List<ConvenioAlteracaoDDADto> getListaConvenioAlteracaoDto() {
        if (ObjectUtil.isEmpty(listaConvenioAlteracaoDto)) {
            listaConvenioAlteracaoDto = new ArrayList<ConvenioAlteracaoDDADto>();
        }
        return listaConvenioAlteracaoDto;
    }

    /**
     * @param listaConvenioAlteracaoDto the listaConvenioAlteracaoDto to set
     */
    public void setListaConvenioAlteracaoDto(List<ConvenioAlteracaoDDADto> listaConvenioAlteracaoDto) {
        this.listaConvenioAlteracaoDto = listaConvenioAlteracaoDto;
    }

    /**
     * @return the listaRepresentanteBeneficiarioDto
     */
    public List<RepresentanteBeneficiarioAlteracaoDto> getListaRepresentanteBeneficiarioDto() {
        if (ObjectUtil.isEmpty(listaRepresentanteBeneficiarioDto)) {
            listaRepresentanteBeneficiarioDto = new ArrayList<RepresentanteBeneficiarioAlteracaoDto>();
        }
        return listaRepresentanteBeneficiarioDto;
    }

    /**
     * @param listaRepresentanteBeneficiarioDto the listaRepresentanteBeneficiarioDto to set
     */
    public void setListaRepresentanteBeneficiarioDto(List<RepresentanteBeneficiarioAlteracaoDto> listaRepresentanteBeneficiarioDto) {
        this.listaRepresentanteBeneficiarioDto = listaRepresentanteBeneficiarioDto;
    }
}
