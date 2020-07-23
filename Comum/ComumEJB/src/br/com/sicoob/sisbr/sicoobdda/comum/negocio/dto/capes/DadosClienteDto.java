package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author Lucas.Borges
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.DadosClienteFachadaDTO")
public class DadosClienteDto extends BancoobDto {

    private static final long serialVersionUID = 3427996523937888891L;

    // PESSOA INSTITUIÇÃO
    private Integer idPessoaInstituicao;

    private Date dataHoraInicio;

    private Integer idUnidadeInst;

    private Integer idFuncionarioResponsavel;

    private String parecerGerencia;

    private Boolean emiteAvisoLancamento;

    // NUCLEO
    private Integer idNucleo;

    private String descricaoNucleo;

    private Integer numNucleo;

    // PERFIL TARIFARIO
    private Integer codigoPerfilTarifario;

    private String siglaPerfilTarifario;

    private String descricaoPerfilTarifario;

    private BigDecimal valorPercentualPerfilTarifario;

    private Short codTipoPercentualPerfilTarifario;

    private Short codTipoPerfilContaPerfilTarifario;

    private Short codigoTipoPerfilPerfilTarifario;

    private Boolean perfilTarifarioIsento;

    public DadosClienteDto() {

    }

    public DadosClienteDto(Integer idPessoaInstituicao, Date dataHoraInicio, Integer idUnidadeInst, Integer idFuncionarioResponsavel, String parecerGerencia,
            Boolean emiteAvisoLancamento, Integer idNucleo, String descricaoNucleo, Integer numNucleo, Integer codigoPerfilTarifario, String siglaPerfilTarifario,
            String descricaoPerfilTarifario, BigDecimal valorPercentualPerfilTarifario, Short codTipoPercentualPerfilTarifario, Short codTipoPerfilContaPerfilTarifario,
            Short codigoTipoPerfilPerfilTarifario, Boolean perfilTarifarioIsento) {
        this.idPessoaInstituicao = idPessoaInstituicao;
        this.dataHoraInicio = dataHoraInicio;
        this.idUnidadeInst = idUnidadeInst;
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
        this.parecerGerencia = parecerGerencia;
        this.emiteAvisoLancamento = emiteAvisoLancamento;
        this.idNucleo = idNucleo;
        this.descricaoNucleo = descricaoNucleo;
        this.numNucleo = numNucleo;
        this.codigoPerfilTarifario = codigoPerfilTarifario;
        this.siglaPerfilTarifario = siglaPerfilTarifario;
        this.descricaoPerfilTarifario = descricaoPerfilTarifario;
        this.valorPercentualPerfilTarifario = valorPercentualPerfilTarifario;
        this.codTipoPercentualPerfilTarifario = codTipoPercentualPerfilTarifario;
        this.codTipoPerfilContaPerfilTarifario = codTipoPerfilContaPerfilTarifario;
        this.codigoTipoPerfilPerfilTarifario = codigoTipoPerfilPerfilTarifario;
        this.perfilTarifarioIsento = perfilTarifarioIsento;
    }

    public Integer getIdPessoaInstituicao() {
        return idPessoaInstituicao;
    }

    public void setIdPessoaInstituicao(Integer idPessoaInstituicao) {
        this.idPessoaInstituicao = idPessoaInstituicao;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Integer getIdUnidadeInst() {
        return idUnidadeInst;
    }

    public void setIdUnidadeInst(Integer idUnidadeInst) {
        this.idUnidadeInst = idUnidadeInst;
    }

    public Integer getIdFuncionarioResponsavel() {
        return idFuncionarioResponsavel;
    }

    public void setIdFuncionarioResponsavel(Integer idFuncionarioResponsavel) {
        this.idFuncionarioResponsavel = idFuncionarioResponsavel;
    }

    public String getParecerGerencia() {
        return parecerGerencia;
    }

    public void setParecerGerencia(String parecerGerencia) {
        this.parecerGerencia = parecerGerencia;
    }

    public Boolean getEmiteAvisoLancamento() {
        return emiteAvisoLancamento;
    }

    public void setEmiteAvisoLancamento(Boolean emiteAvisoLancamento) {
        this.emiteAvisoLancamento = emiteAvisoLancamento;
    }

    public Integer getIdNucleo() {
        return idNucleo;
    }

    public void setIdNucleo(Integer idNucleo) {
        this.idNucleo = idNucleo;
    }

    public String getDescricaoNucleo() {
        return descricaoNucleo;
    }

    public void setDescricaoNucleo(String descricaoNucleo) {
        this.descricaoNucleo = descricaoNucleo;
    }

    public Integer getNumNucleo() {
        return numNucleo;
    }

    public void setNumNucleo(Integer numNucleo) {
        this.numNucleo = numNucleo;
    }

    public Integer getCodigoPerfilTarifario() {
        return codigoPerfilTarifario;
    }

    public void setCodigoPerfilTarifario(Integer codigoPerfilTarifario) {
        this.codigoPerfilTarifario = codigoPerfilTarifario;
    }

    public String getSiglaPerfilTarifario() {
        return siglaPerfilTarifario;
    }

    public void setSiglaPerfilTarifario(String siglaPerfilTarifario) {
        this.siglaPerfilTarifario = siglaPerfilTarifario;
    }

    public String getDescricaoPerfilTarifario() {
        return descricaoPerfilTarifario;
    }

    public void setDescricaoPerfilTarifario(String descricaoPerfilTarifario) {
        this.descricaoPerfilTarifario = descricaoPerfilTarifario;
    }

    public BigDecimal getValorPercentualPerfilTarifario() {
        return valorPercentualPerfilTarifario;
    }

    public void setValorPercentualPerfilTarifario(BigDecimal valorPercentualPerfilTarifario) {
        this.valorPercentualPerfilTarifario = valorPercentualPerfilTarifario;
    }

    public Short getCodTipoPercentualPerfilTarifario() {
        return codTipoPercentualPerfilTarifario;
    }

    public void setCodTipoPercentualPerfilTarifario(Short codTipoPercentualPerfilTarifario) {
        this.codTipoPercentualPerfilTarifario = codTipoPercentualPerfilTarifario;
    }

    public Short getCodTipoPerfilContaPerfilTarifario() {
        return codTipoPerfilContaPerfilTarifario;
    }

    public void setCodTipoPerfilContaPerfilTarifario(Short codTipoPerfilContaPerfilTarifario) {
        this.codTipoPerfilContaPerfilTarifario = codTipoPerfilContaPerfilTarifario;
    }

    public Short getCodigoTipoPerfilPerfilTarifario() {
        return codigoTipoPerfilPerfilTarifario;
    }

    public void setCodigoTipoPerfilPerfilTarifario(Short codigoTipoPerfilPerfilTarifario) {
        this.codigoTipoPerfilPerfilTarifario = codigoTipoPerfilPerfilTarifario;
    }

    public Boolean getPerfilTarifarioIsento() {
        return perfilTarifarioIsento;
    }

    public void setPerfilTarifarioIsento(Boolean perfilTarifarioIsento) {
        this.perfilTarifarioIsento = perfilTarifarioIsento;
    }
}
