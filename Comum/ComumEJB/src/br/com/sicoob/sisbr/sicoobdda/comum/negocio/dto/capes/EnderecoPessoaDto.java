package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes;

import java.util.Date;

import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;

public class EnderecoPessoaDto {

    // ENDERECO PESSOA
    private Integer idEndereco;

    private Date dataHoraInicio;

    private String descricao;

    private String numero;

    private String complemento;

    private String bairro;

    private String cep;

    // TIPO ENDERECO
    private Short codigoTipoEndereco;

    private String descricaoTipoEndereco;

    // TIPO LOGRADOURO
    private Short idTipoLogradouro;

    // LOCALIDADE
    private Integer idLocalidade;

    public EnderecoPessoaDto() {

    }

    public EnderecoPessoaDto(Integer idEndereco, Date dataHoraInicio, String descricao, String numero, String complemento, String bairro, String cep, Short codigoTipoEndereco,
            String descricaoTipoEndereco, Short idTipoLogradouro, Integer idLocalidade) {
        this.idEndereco = idEndereco;
        this.dataHoraInicio = dataHoraInicio;
        this.descricao = descricao;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.codigoTipoEndereco = codigoTipoEndereco;
        this.descricaoTipoEndereco = descricaoTipoEndereco;
        this.idTipoLogradouro = idTipoLogradouro;
        this.idLocalidade = idLocalidade;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return o atributo cep formatado com máscara
     */
    public String getCepFormatado() {
        return FormatadorUtil.formatar(getCep(), FormatadorUtil.MASCARA_CEP);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Short getCodigoTipoEndereco() {
        return codigoTipoEndereco;
    }

    public void setCodigoTipoEndereco(Short codigoTipoEndereco) {
        this.codigoTipoEndereco = codigoTipoEndereco;
    }

    public String getDescricaoTipoEndereco() {
        return descricaoTipoEndereco;
    }

    public void setDescricaoTipoEndereco(String descricaoTipoEndereco) {
        this.descricaoTipoEndereco = descricaoTipoEndereco;
    }

    public Short getIdTipoLogradouro() {
        return idTipoLogradouro;
    }

    public void setIdTipoLogradouro(Short idTipoLogradouro) {
        this.idTipoLogradouro = idTipoLogradouro;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public String getDescEnderecoCompleto() {
        StringBuffer sb = new StringBuffer();

        sb.append(getDescricao());

        if (getNumero() != null && getNumero().trim().length() > 0) {
            sb.append(", ");
            sb.append(getNumero());
        }

        if (getComplemento() != null && getComplemento().trim().length() > 0) {
            sb.append(", ");
            sb.append(getComplemento());
        }

        if (getBairro() != null && getBairro().trim().length() > 0) {
            sb.append(", ");
            sb.append(getBairro());
        }

        return sb.toString();
    }
}
