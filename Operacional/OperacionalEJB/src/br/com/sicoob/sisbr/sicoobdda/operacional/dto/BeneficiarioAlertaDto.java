/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.dto
 * Arquivo:         BeneficiarioAlertaDto.java
 * Data Criação:    Feb 23, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.dto;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * BeneficiarioAlertaDto
 * 
 * @author Danilo.Barros
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BeneficiarioAlertaDTO")
public class BeneficiarioAlertaDto extends BancoobDto {
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
     * @param descTipoPessoa
     * @param cPF_CNPJ
     * @param descStatusAlerta
     * @param numCodISPB
     * @param dataHoraAlerta
     * @param bolSicoob
     */

    public BeneficiarioAlertaDto(Long idBeneficiarioDDA, String descTipoPessoa, String cPF_CNPJ, String descStatusAlerta, String numCodISPB, Date dataHoraAlerta,
            boolean bolSicoob, Integer idInstituicao) {
        this.idBeneficiarioDDA = idBeneficiarioDDA;
        this.descTipoPessoa = descTipoPessoa;
        this.cPF_CNPJ = cPF_CNPJ;
        this.descStatusAlerta = descStatusAlerta;
        this.numCodISPB = numCodISPB;
        this.dataHoraAlerta = dataHoraAlerta;
        this.bolSicoob = bolSicoob;
        this.idInstituicao = idInstituicao;
    }

    /**
     * @param idBeneficiarioDDA
     * @param idInstituicao
     * @param nomeCooperativa
     * @param numeroCooperativa
     * @param postoAvancCooperativa
     */
    public BeneficiarioAlertaDto(Long idBeneficiarioDDA, Integer idInstituicao, String nomeCooperativa, String numeroCooperativa, String postoAvancCooperativa) {
        this.idBeneficiarioDDA = idBeneficiarioDDA;
        this.idInstituicao = idInstituicao;
        this.nomeCooperativa = nomeCooperativa;
        this.numeroCooperativa = numeroCooperativa;
        this.postoAvancCooperativa = postoAvancCooperativa;
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
     * 
     */
    public BeneficiarioAlertaDto() {
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
