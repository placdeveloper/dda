package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci;

import java.io.Serializable;

public class EnderecoInstituicaoDto implements Serializable {

    private static final long serialVersionUID = -8293073939244114900L;

    private Integer idEnderecoInst;
    private Integer idInstituicao;
    private Integer idUnidadeInst;
    private Integer idLocalidade;
    private Integer idPais;

    private String descComplemento;
    private String descEndereco;
    private String descNumero;
    private String nomeBairro;
    private String nomeCidade;
    private String numCEP;
    private String descLatitude;
    private String descLongitude;
    private String descPontoReferencia;
    private Integer numPac;

    // Pertence ao objeto Localidade
    private String siglaUF;

    /**
     */
    public EnderecoInstituicaoDto(Integer numPAC, Integer idInstituicao, String descEnderecoCoop, String descNumeroCoop, String descComplementoCoop, String descBairroCoop,
            String descCidadeCoop, String siglaUF, String numCepCoop, Integer idLocalidade) {
        this.numPac = numPAC;
        this.idInstituicao = idInstituicao;
        this.descEndereco = descEnderecoCoop;
        this.descNumero = descNumeroCoop;
        this.descComplemento = descComplementoCoop;
        this.nomeBairro = descBairroCoop;
        this.nomeCidade = descCidadeCoop;
        this.siglaUF = siglaUF;
        this.numCEP = numCepCoop;
        this.idLocalidade = idLocalidade;
    }

    public Integer getIdEnderecoInst() {
        return idEnderecoInst;
    }

    public void setIdEnderecoInst(Integer idEnderecoInst) {
        this.idEnderecoInst = idEnderecoInst;
    }

    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public Integer getIdUnidadeInst() {
        return idUnidadeInst;
    }

    public void setIdUnidadeInst(Integer idUnidadeInst) {
        this.idUnidadeInst = idUnidadeInst;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public Integer getIdPais() {
        return idPais;
    }

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }

    public String getDescComplemento() {
        return descComplemento;
    }

    public void setDescComplemento(String descComplemento) {
        this.descComplemento = descComplemento;
    }

    public String getDescEndereco() {
        return descEndereco;
    }

    public void setDescEndereco(String descEndereco) {
        this.descEndereco = descEndereco;
    }

    public String getDescNumero() {
        return descNumero;
    }

    public void setDescNumero(String descNumero) {
        this.descNumero = descNumero;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getNumCEP() {
        return numCEP;
    }

    public void setNumCEP(String numCEP) {
        this.numCEP = numCEP;
    }

    public String getDescLatitude() {
        return descLatitude;
    }

    public void setDescLatitude(String descLatitude) {
        this.descLatitude = descLatitude;
    }

    public String getDescLongitude() {
        return descLongitude;
    }

    public void setDescLongitude(String descLongitude) {
        this.descLongitude = descLongitude;
    }

    public String getDescPontoReferencia() {
        return descPontoReferencia;
    }

    public void setDescPontoReferencia(String descPontoReferencia) {
        this.descPontoReferencia = descPontoReferencia;
    }

    public String getSiglaUF() {
        return siglaUF;
    }

    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    /**
     * @return the numPac
     */
    public Integer getNumPac() {
        return numPac;
    }

    /**
     * @param numPac the numPac to set
     */
    public void setNumPac(Integer numPac) {
        this.numPac = numPac;
    }

    public String getDescricaoEnderecoCompleto() {
        StringBuffer sb = new StringBuffer();
        sb.append(getDescEndereco());

        if (getDescNumero() != null && getDescNumero().trim().length() > 0) {
            sb.append(", ");
            sb.append(getDescNumero());
        }

        if (getDescComplemento() != null && getDescComplemento().trim().length() > 0) {
            sb.append(", ");
            sb.append(getDescComplemento());
        }

        if (getNomeBairro() != null && getNomeBairro().trim().length() > 0) {
            sb.append(", ");
            sb.append(getNomeBairro());
        }

        if (getNomeCidade() != null && getNomeCidade().trim().length() > 0) {
            sb.append(", ");
            sb.append(getNomeCidade());

            if (getSiglaUF() != null && getSiglaUF().trim().length() > 0) {
                sb.append(" - ");
                sb.append(getSiglaUF());
            }
        }
        return sb.toString();
    }
}
