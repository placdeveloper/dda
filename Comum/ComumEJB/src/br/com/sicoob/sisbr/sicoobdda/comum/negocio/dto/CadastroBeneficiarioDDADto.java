/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         BeneficiarioDto.java
 * Data Criação:    May 7, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import java.util.Date;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;

/**
 * CadastroBeneficiarioDDADto
 * 
 * @author Rafael.Silva
 */
public class CadastroBeneficiarioDDADto extends BancoobDto {

    private static final long serialVersionUID = 2928581114653723708L;

    private TipoPessoaEnum tipoPessoaBeneficiario;

    private String cnpjCpfBeneficiario;

    private String nomeRazaoSocialBeneficiario;

    private String nomeFantasiaBeneficiario;

    private SituacaoBeneficiarioEnum situacaoBeneficiario;

    private Date dataHoraSituacaoBeneficiario;

    private SituacaoRelacionamentoParticipanteEnum situacaoRelacionamentoParticipante;

    private Date dataInicioRelacionamentoParticipante;

    private List<ConvenioCadastroDDADto> listaConvenioCadastroDto;

    private List<RepresentanteBeneficiarioDto> listaRepresentanteBeneficiarioDto;

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
     * @return the dataInicioRelacionamentoParticipante
     */
    public Date getDataInicioRelacionamentoParticipante() {
        return dataInicioRelacionamentoParticipante;
    }

    /**
     * @param dataInicioRelacionamentoParticipante the dataInicioRelacionamentoParticipante to set
     */
    public void setDataInicioRelacionamentoParticipante(Date dataInicioRelacionamentoParticipante) {
        this.dataInicioRelacionamentoParticipante = dataInicioRelacionamentoParticipante;
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
    public Date getDataHoraSituacaoBeneficiario() {
        return dataHoraSituacaoBeneficiario;
    }

    /**
     * @param dataHoraSituacaoBeneficiario the dataHoraSituacaoBeneficiario to set
     */
    public void setDataHoraSituacaoBeneficiario(Date dataHoraSituacaoBeneficiario) {
        this.dataHoraSituacaoBeneficiario = dataHoraSituacaoBeneficiario;
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
     * @return the listaCadastroConvenioDto
     */
    public List<ConvenioCadastroDDADto> getListaConvenioCadastroDto() {
        return listaConvenioCadastroDto;
    }

    /**
     * @param listaCadastroConvenioDto the listaCadastroConvenioDto to set
     */
    public void setListaConvenioCadastroDto(List<ConvenioCadastroDDADto> listaCadastroConvenioDto) {
        this.listaConvenioCadastroDto = listaCadastroConvenioDto;
    }

    /**
     * @return the listaRepresentanteBeneficiarioDto
     */
    public List<RepresentanteBeneficiarioDto> getListaRepresentanteBeneficiarioDto() {
        return listaRepresentanteBeneficiarioDto;
    }

    /**
     * @param listaRepresentanteBeneficiarioDto the listaRepresentanteBeneficiarioDto to set
     */
    public void setListaRepresentanteBeneficiarioDto(List<RepresentanteBeneficiarioDto> listaRepresentanteBeneficiarioDto) {
        this.listaRepresentanteBeneficiarioDto = listaRepresentanteBeneficiarioDto;
    }

}
