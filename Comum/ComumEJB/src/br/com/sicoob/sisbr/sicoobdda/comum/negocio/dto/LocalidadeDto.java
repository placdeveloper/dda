package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

public class LocalidadeDto extends BancoobDto {

    private static final long serialVersionUID = 1L;

    public static final String[] CAMPOS = new String[] { "idLocalidade", "nomeLocalidade", "siglaUF" };

    private Integer idLocalidade;
    private String nomeLocalidade;
    private String nomeLocalidadeAnterior;
    private String nomeLimpoLocalidade;
    private String codIBGE;
    private String codBACEN;
    private String codRF;
    private String codECT;
    private String codCAF;
    private Integer idUF;
    private Integer idLocalidadePai;
    private String codDDD;
    private Integer bolCapital;
    private Integer idSituacaoLocalidadeIbge;
    private Integer idTipoLocalidade;
    private String numCepLocalidade;
    private Integer bolFronteira;
    private String nomeLocAbreviado;
    private String descLongitude;
    private String descLatitude;

    private String nomeUF;
    private String siglaUF;

    private Integer idLogradouro;
    private Integer idBairro;
    private String nomeBairro;
    private String nomeBairroAbreviado;
    private String nomeLogradouro;
    private String nomeLogAbreviado;
    private String descComplementoLogradouro;
    private String numCepLogradouro;

    /**
     * 
     */
    public LocalidadeDto() {
    }

    public LocalidadeDto(Integer idLocalidade, String nomeLocalidade, String nomeLocalidadeAnterior, String nomeLimpoLocalidade, String codIBGE, String codBACEN, String codRF,
            String codECT, String codCAF, Integer idUF, Integer idLocalidadePai, String codDDD, Integer bolCapital, String numCepLocalidade, Integer bolFronteira,
            String nomeLocAbreviado, String descLongitude, String descLatitude, String nomeUF, String siglaUF) {
        this.idLocalidade = idLocalidade;
        this.nomeLocalidade = nomeLocalidade;
        this.nomeLocalidadeAnterior = nomeLocalidadeAnterior;
        this.nomeLimpoLocalidade = nomeLimpoLocalidade;
        this.codIBGE = codIBGE;
        this.codBACEN = codBACEN;
        this.codRF = codRF;
        this.codECT = codECT;
        this.codCAF = codCAF;
        this.idUF = idUF;
        this.idLocalidadePai = idLocalidadePai;
        this.codDDD = codDDD;
        this.bolCapital = bolCapital;
        this.numCepLocalidade = numCepLocalidade;
        this.bolFronteira = bolFronteira;
        this.nomeLocAbreviado = nomeLocAbreviado;
        this.descLongitude = descLongitude;
        this.descLatitude = descLatitude;
        this.nomeUF = nomeUF;
        this.siglaUF = siglaUF;
    }

    /**
     * @return the idLocalidade
     */
    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    /**
     * @param idLocalidade the idLocalidade to set
     */
    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    /**
     * @return the nomeLocalidade
     */
    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    /**
     * @param nomeLocalidade the nomeLocalidade to set
     */
    public void setNomeLocalidade(String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

    /**
     * @return the nomeLocalidadeAnterior
     */
    public String getNomeLocalidadeAnterior() {
        return nomeLocalidadeAnterior;
    }

    /**
     * @param nomeLocalidadeAnterior the nomeLocalidadeAnterior to set
     */
    public void setNomeLocalidadeAnterior(String nomeLocalidadeAnterior) {
        this.nomeLocalidadeAnterior = nomeLocalidadeAnterior;
    }

    /**
     * @return the nomeLimpoLocalidade
     */
    public String getNomeLimpoLocalidade() {
        return nomeLimpoLocalidade;
    }

    /**
     * @param nomeLimpoLocalidade the nomeLimpoLocalidade to set
     */
    public void setNomeLimpoLocalidade(String nomeLimpoLocalidade) {
        this.nomeLimpoLocalidade = nomeLimpoLocalidade;
    }

    /**
     * @return the codIBGE
     */
    public String getCodIBGE() {
        return codIBGE;
    }

    /**
     * @param codIBGE the codIBGE to set
     */
    public void setCodIBGE(String codIBGE) {
        this.codIBGE = codIBGE;
    }

    /**
     * @return the codBACEN
     */
    public String getCodBACEN() {
        return codBACEN;
    }

    /**
     * @param codBACEN the codBACEN to set
     */
    public void setCodBACEN(String codBACEN) {
        this.codBACEN = codBACEN;
    }

    /**
     * @return the codRF
     */
    public String getCodRF() {
        return codRF;
    }

    /**
     * @param codRF the codRF to set
     */
    public void setCodRF(String codRF) {
        this.codRF = codRF;
    }

    /**
     * @return the codECT
     */
    public String getCodECT() {
        return codECT;
    }

    /**
     * @param codECT the codECT to set
     */
    public void setCodECT(String codECT) {
        this.codECT = codECT;
    }

    /**
     * @return the codCAF
     */
    public String getCodCAF() {
        return codCAF;
    }

    /**
     * @param codCAF the codCAF to set
     */
    public void setCodCAF(String codCAF) {
        this.codCAF = codCAF;
    }

    /**
     * @return the idUF
     */
    public Integer getIdUF() {
        return idUF;
    }

    /**
     * @param idUF the idUF to set
     */
    public void setIdUF(Integer idUF) {
        this.idUF = idUF;
    }

    /**
     * @return the idLocalidadePai
     */
    public Integer getIdLocalidadePai() {
        return idLocalidadePai;
    }

    /**
     * @param idLocalidadePai the idLocalidadePai to set
     */
    public void setIdLocalidadePai(Integer idLocalidadePai) {
        this.idLocalidadePai = idLocalidadePai;
    }

    /**
     * @return the codDDD
     */
    public String getCodDDD() {
        return codDDD;
    }

    /**
     * @param codDDD the codDDD to set
     */
    public void setCodDDD(String codDDD) {
        this.codDDD = codDDD;
    }

    /**
     * @return the bolCapital
     */
    public Integer getBolCapital() {
        return bolCapital;
    }

    /**
     * @param bolCapital the bolCapital to set
     */
    public void setBolCapital(Integer bolCapital) {
        this.bolCapital = bolCapital;
    }

    /**
     * @return the idSituacaoLocalidadeIbge
     */
    public Integer getIdSituacaoLocalidadeIbge() {
        return idSituacaoLocalidadeIbge;
    }

    /**
     * @param idSituacaoLocalidadeIbge the idSituacaoLocalidadeIbge to set
     */
    public void setIdSituacaoLocalidadeIbge(Integer idSituacaoLocalidadeIbge) {
        this.idSituacaoLocalidadeIbge = idSituacaoLocalidadeIbge;
    }

    /**
     * @return the idTipoLocalidade
     */
    public Integer getIdTipoLocalidade() {
        return idTipoLocalidade;
    }

    /**
     * @param idTipoLocalidade the idTipoLocalidade to set
     */
    public void setIdTipoLocalidade(Integer idTipoLocalidade) {
        this.idTipoLocalidade = idTipoLocalidade;
    }

    /**
     * @return the numCepLocalidade
     */
    public String getNumCepLocalidade() {
        return numCepLocalidade;
    }

    /**
     * @param numCepLocalidade the numCepLocalidade to set
     */
    public void setNumCepLocalidade(String numCepLocalidade) {
        this.numCepLocalidade = numCepLocalidade;
    }

    /**
     * @return the bolFronteira
     */
    public Integer getBolFronteira() {
        return bolFronteira;
    }

    /**
     * @param bolFronteira the bolFronteira to set
     */
    public void setBolFronteira(Integer bolFronteira) {
        this.bolFronteira = bolFronteira;
    }

    /**
     * @return the nomeLocAbreviado
     */
    public String getNomeLocAbreviado() {
        return nomeLocAbreviado;
    }

    /**
     * @param nomeLocAbreviado the nomeLocAbreviado to set
     */
    public void setNomeLocAbreviado(String nomeLocAbreviado) {
        this.nomeLocAbreviado = nomeLocAbreviado;
    }

    /**
     * @return the descLongitude
     */
    public String getDescLongitude() {
        return descLongitude;
    }

    /**
     * @param descLongitude the descLongitude to set
     */
    public void setDescLongitude(String descLongitude) {
        this.descLongitude = descLongitude;
    }

    /**
     * @return the descLatitude
     */
    public String getDescLatitude() {
        return descLatitude;
    }

    /**
     * @param descLatitude the descLatitude to set
     */
    public void setDescLatitude(String descLatitude) {
        this.descLatitude = descLatitude;
    }

    /**
     * @return the nomeUF
     */
    public String getNomeUF() {
        return nomeUF;
    }

    /**
     * @param nomeUF the nomeUF to set
     */
    public void setNomeUF(String nomeUF) {
        this.nomeUF = nomeUF;
    }

    /**
     * @return the siglaUF
     */
    public String getSiglaUF() {
        return siglaUF;
    }

    /**
     * @param siglaUF the siglaUF to set
     */
    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    /**
     * @return the idLogradouro
     */
    public Integer getIdLogradouro() {
        return idLogradouro;
    }

    /**
     * @param idLogradouro the idLogradouro to set
     */
    public void setIdLogradouro(Integer idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    /**
     * @return the idBairro
     */
    public Integer getIdBairro() {
        return idBairro;
    }

    /**
     * @param idBairro the idBairro to set
     */
    public void setIdBairro(Integer idBairro) {
        this.idBairro = idBairro;
    }

    /**
     * @return the nomeBairro
     */
    public String getNomeBairro() {
        return nomeBairro;
    }

    /**
     * @param nomeBairro the nomeBairro to set
     */
    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    /**
     * @return the nomeBairroAbreviado
     */
    public String getNomeBairroAbreviado() {
        return nomeBairroAbreviado;
    }

    /**
     * @param nomeBairroAbreviado the nomeBairroAbreviado to set
     */
    public void setNomeBairroAbreviado(String nomeBairroAbreviado) {
        this.nomeBairroAbreviado = nomeBairroAbreviado;
    }

    /**
     * @return the nomeLogradouro
     */
    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    /**
     * @param nomeLogradouro the nomeLogradouro to set
     */
    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    /**
     * @return the nomeLogAbreviado
     */
    public String getNomeLogAbreviado() {
        return nomeLogAbreviado;
    }

    /**
     * @param nomeLogAbreviado the nomeLogAbreviado to set
     */
    public void setNomeLogAbreviado(String nomeLogAbreviado) {
        this.nomeLogAbreviado = nomeLogAbreviado;
    }

    /**
     * @return the descComplementoLogradouro
     */
    public String getDescComplementoLogradouro() {
        return descComplementoLogradouro;
    }

    /**
     * @param descComplementoLogradouro the descComplementoLogradouro to set
     */
    public void setDescComplementoLogradouro(String descComplementoLogradouro) {
        this.descComplementoLogradouro = descComplementoLogradouro;
    }

    /**
     * @return the numCepLogradouro
     */
    public String getNumCepLogradouro() {
        return numCepLogradouro;
    }

    /**
     * @param numCepLogradouro the numCepLogradouro to set
     */
    public void setNumCepLogradouro(String numCepLogradouro) {
        this.numCepLogradouro = numCepLogradouro;
    }

}
