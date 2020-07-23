package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.loc;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

public class LOCEnderecoDto extends BancoobDto {

    public static final String[] CAMPOS = new String[] { "idUF", "idLocalidade", "idLogradouro", "siglaUF", "nomeLocalidade", "nomeLogradouro", "nomeBairro", "codIBGE",
            "numCepLogradouro", "numCepLocalidade" };

    public static final String[] CAMPOS_2 = new String[] { "idUF", "idLocalidade", "idLogradouro", "siglaUF", "nomeLocalidade", "nomeLogradouro", "nomeBairro", "codIBGE",
            "numCepLocalidade", "idTipoLocalidade", "numCepLogradouro" };

    private static final long serialVersionUID = 452390446945368159L;

    private Integer idUF;
    private Integer idLocalidade;
    private Integer idLogradouro;
    private String siglaUF;
    private String nomeLocalidade;
    private String nomeLogAbreviado;
    private String nomeLogradouro;
    private String nomeBairro;
    private String codIBGE;

    private String numCep;
    private String numCepLocalidade;
    private String numCepLogradouro;

    private Integer idTipoLocalidade;

    public Integer getIdUF() {
        return idUF;
    }

    public void setIdUF(Integer idUF) {
        this.idUF = idUF;
    }

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    public Integer getIdLogradouro() {
        return idLogradouro;
    }

    public void setIdLogradouro(Integer idLogradouro) {
        this.idLogradouro = idLogradouro;
    }

    public String getSiglaUF() {
        return siglaUF;
    }

    public void setSiglaUF(String siglaUF) {
        this.siglaUF = siglaUF;
    }

    public String getNomeLocalidade() {
        return nomeLocalidade;
    }

    public void setNomeLocalidade(String nomeLocalidade) {
        this.nomeLocalidade = nomeLocalidade;
    }

    public String getNomeLogAbreviado() {
        return nomeLogAbreviado;
    }

    public void setNomeLogAbreviado(String nomeLogAbreviado) {
        this.nomeLogAbreviado = nomeLogAbreviado;
    }

    public String getNomeLogradouro() {
        return nomeLogradouro;
    }

    public void setNomeLogradouro(String nomeLogradouro) {
        this.nomeLogradouro = nomeLogradouro;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public String getCodIBGE() {
        return codIBGE;
    }

    public void setCodIBGE(String codIBGE) {
        this.codIBGE = codIBGE;
    }

    public String getNumCep() {
        return numCep;
    }

    public void setNumCep(String numCep) {
        this.numCep = numCep;
    }

    public String getNumCepLocalidade() {
        return numCepLocalidade;
    }

    public void setNumCepLocalidade(String numCepLocalidade) {
        this.numCepLocalidade = numCepLocalidade;
    }

    public String getNumCepLogradouro() {
        return numCepLogradouro;
    }

    public void setNumCepLogradouro(String numCepLogradouro) {
        this.numCepLogradouro = numCepLogradouro;
    }

    public Integer getIdTipoLocalidade() {
        return idTipoLocalidade;
    }

    public void setIdTipoLocalidade(Integer idTipoLocalidade) {
        this.idTipoLocalidade = idTipoLocalidade;
    }

    /**
     * Método responsável por verificar qual objeto deve ser retornado, pois em alguns casos, após o retorno do serviço, o 'numCepLogradouro' é preenchido, em
     * outros, o 'numCepLocalidade'.
     * 
     * @return
     */
    public String getCep() {
        return ObjectUtil.isNull(numCepLogradouro) ? numCepLocalidade : numCepLogradouro;
    }

}
