/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.65-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto
 * Arquivo:         MensagemPendenteBeneficiarioDto.java
 * Data Criação:    27 de nov de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * MensagemPendenteBeneficiarioDto é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemPendenteBeneficiarioDto extends BancoobDto {

    /**
     * 
     */
    private static final long serialVersionUID = -1973266142786811798L;

    private Long idMensagemDDA;
    private String codTipoMensagemDDA;
    private String numCpfCnpjBeneficiario;
    private String codTipoErroCIP;
    private String descTipoErroCIP;

    /**
     * @param idMensagemDDA
     * @param codTipoMensagemDDA
     * @param numCpfCnpjBeneficiario
     * @param codTipoErroCIP
     * @param descTipoErroCIP
     */
    public MensagemPendenteBeneficiarioDto(Long idMensagemDDA, String codTipoMensagemDDA, String numCpfCnpjBeneficiario, String codTipoErroCIP, String descTipoErroCIP) {
        super();
        this.idMensagemDDA = idMensagemDDA;
        this.codTipoMensagemDDA = codTipoMensagemDDA;
        this.numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
        this.codTipoErroCIP = codTipoErroCIP;
        this.descTipoErroCIP = descTipoErroCIP;
    }

    /**
     * @return o atributo idMensagemDDA
     */
    public Long getIdMensagemDDA() {
        return idMensagemDDA;
    }

    /**
     * Define o atributo idMensagemDDA
     */
    public void setIdMensagemDDA(Long idMensagemDDA) {
        this.idMensagemDDA = idMensagemDDA;
    }

    /**
     * @return o atributo codTipoMensagemDDA
     */
    public String getCodTipoMensagemDDA() {
        return codTipoMensagemDDA;
    }

    /**
     * Define o atributo codTipoMensagemDDA
     */
    public void setCodTipoMensagemDDA(String codTipoMensagemDDA) {
        this.codTipoMensagemDDA = codTipoMensagemDDA;
    }

    /**
     * @return o atributo numCpfCnpjBeneficiario
     */
    public String getNumCpfCnpjBeneficiario() {
        return numCpfCnpjBeneficiario;
    }

    /**
     * Define o atributo numCpfCnpjBeneficiario
     */
    public void setNumCpfCnpjBeneficiario(String numCpfCnpjBeneficiario) {
        this.numCpfCnpjBeneficiario = numCpfCnpjBeneficiario;
    }

    /**
     * @return o atributo codTipoErroCIP
     */
    public String getCodTipoErroCIP() {
        return codTipoErroCIP;
    }

    /**
     * Define o atributo codTipoErroCIP
     */
    public void setCodTipoErroCIP(String codTipoErroCIP) {
        this.codTipoErroCIP = codTipoErroCIP;
    }

    /**
     * @return o atributo descTipoErroCIP
     */
    public String getDescTipoErroCIP() {
        return descTipoErroCIP;
    }

    /**
     * Define o atributo descTipoErroCIP
     */
    public void setDescTipoErroCIP(String descTipoErroCIP) {
        this.descTipoErroCIP = descTipoErroCIP;
    }

}
