/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.dto
 * Arquivo:         BeneficiarioAlertaDTO.java
 * Data Criação:    Feb 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * BeneficiarioAlertaDTO
 * 
 * @author Danilo.Barros
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto")
public class BeneficiarioAlertaDTO extends BancoobDTO implements Cloneable, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idBeneficiarioDDA;
    private Integer idInstituicao;
    private String descTipoPessoa;
    private String cPF_CNPJ;
    private String descStatusAlerta;
    private String numCodISPB;
    private String nomeBancoConveniado;
    private Date dataHoraAlerta;
    private boolean bolSicoob;
    private String nomeCooperativa;
    private String numeroCooperativa;
    private String postoAvancCooperativa;

    /**
     * @param idBeneficiarioDDA
     */
    public BeneficiarioAlertaDTO(Long idBeneficiarioDDA) {
        this.idBeneficiarioDDA = idBeneficiarioDDA;
    }

    /**
     * @return BeneficiarioAlertaDTO
     */
    @Override
    public BeneficiarioAlertaDTO clone() throws CloneNotSupportedException {
        return (BeneficiarioAlertaDTO) super.clone();
    }

    /**
     * 
     */
    public BeneficiarioAlertaDTO() {
    }

    /**
     * @return o atributo idBeneficiarioDDA
     */
    public Long getIdBeneficiarioDDA() {
        return idBeneficiarioDDA;
    }

    /**
     * Define o atributo idBeneficiarioDDA
     */
    public void setIdBeneficiarioDDA(Long idBeneficiarioDDA) {
        this.idBeneficiarioDDA = idBeneficiarioDDA;
    }

    /**
     * @return o atributo idInstituicao
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * Define o atributo idInstituicao
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * @return o atributo descTipoPessoa
     */
    public String getDescTipoPessoa() {
        return descTipoPessoa;
    }

    /**
     * Define o atributo descTipoPessoa
     */
    public void setDescTipoPessoa(String descTipoPessoa) {
        this.descTipoPessoa = descTipoPessoa;
    }

    /**
     * @return o atributo cPF_CNPJ
     */
    public String getcPF_CNPJ() {
        return cPF_CNPJ;
    }

    /**
     * Define o atributo cPF_CNPJ
     */
    public void setcPF_CNPJ(String cPF_CNPJ) {
        this.cPF_CNPJ = cPF_CNPJ;
    }

    /**
     * @return o atributo descStatusAlerta
     */
    public String getDescStatusAlerta() {
        return descStatusAlerta;
    }

    /**
     * Define o atributo descStatusAlerta
     */
    public void setDescStatusAlerta(String descStatusAlerta) {
        this.descStatusAlerta = descStatusAlerta;
    }

    /**
     * @return o atributo numCodISPB
     */
    public String getNumCodISPB() {
        return numCodISPB;
    }

    /**
     * Define o atributo numCodISPB
     */
    public void setNumCodISPB(String numCodISPB) {
        this.numCodISPB = numCodISPB;
    }

    /**
     * @return o atributo nomeBancoConveniado
     */
    public String getNomeBancoConveniado() {
        return nomeBancoConveniado;
    }

    /**
     * Define o atributo nomeBancoConveniado
     */
    public void setNomeBancoConveniado(String nomeBancoConveniado) {
        this.nomeBancoConveniado = nomeBancoConveniado;
    }

    /**
     * @return o atributo dataHoraAlerta
     */
    public Date getDataHoraAlerta() {
        return dataHoraAlerta;
    }

    /**
     * Define o atributo dataHoraAlerta
     */
    public void setDataHoraAlerta(Date dataHoraAlerta) {
        this.dataHoraAlerta = dataHoraAlerta;
    }

    /**
     * @return o atributo bolSicoob
     */
    public boolean isBolSicoob() {
        return bolSicoob;
    }

    /**
     * Define o atributo bolSicoob
     */
    public void setBolSicoob(boolean bolSicoob) {
        this.bolSicoob = bolSicoob;
    }

    /**
     * @return o atributo nomeCooperativa
     */
    public String getNomeCooperativa() {
        return nomeCooperativa;
    }

    /**
     * Define o atributo nomeCooperativa
     */
    public void setNomeCooperativa(String nomeCooperativa) {
        this.nomeCooperativa = nomeCooperativa;
    }

    /**
     * @return o atributo numeroCooperativa
     */
    public String getNumeroCooperativa() {
        return numeroCooperativa;
    }

    /**
     * Define o atributo numeroCooperativa
     */
    public void setNumeroCooperativa(String numeroCooperativa) {
        this.numeroCooperativa = numeroCooperativa;
    }

    /**
     * @return o atributo postoAvancCooperativa
     */
    public String getPostoAvancCooperativa() {
        return postoAvancCooperativa;
    }

    /**
     * Define o atributo postoAvancCooperativa
     */
    public void setPostoAvancCooperativa(String postoAvancCooperativa) {
        this.postoAvancCooperativa = postoAvancCooperativa;
    }

}
