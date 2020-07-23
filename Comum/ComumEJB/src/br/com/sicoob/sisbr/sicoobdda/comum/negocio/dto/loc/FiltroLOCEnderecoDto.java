package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.loc;

import java.util.ArrayList;
import java.util.List;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

public class FiltroLOCEnderecoDto extends BancoobDto {

    private static final long serialVersionUID = 4008658998843315108L;

    public static final String[] CAMPOS = new String[] { "idUF", "idLocalidade", "idLogradouro", "siglaUF", "nomeLocalidade", "nomeLogradouro", "nomeBairro", "numCep", };

    public static final String[] CAMPOS_2 = new String[] { "idUF", "idLocalidadePai", "numCep", "numCepLogradouro", "siglaUF", "nomeLocalidade", "nomeLogradouro", "nomeBairro",
            "codIBGE" };

    private Integer idUF;
    private Integer idLocalidade;
    private Integer idLocalidadePai;
    private Integer idLogradouro;
    private String siglaUF;
    private String nomeLocalidade;
    private String nomeLogradouro;
    private String nomeBairro;
    private String codIBGE;

    private String numCep;
    private String numCepLogradouro;

    private Integer idTipoLocalidade;

    private List<Integer> listaIdsLocalidade;

    public Integer getIdUF() {
        return idUF;
    }

    public void setIdUF(Integer idUF) {
        this.idUF = idUF;
    }

    public Integer getIdLocalidadePai() {
        return idLocalidadePai;
    }

    public void setIdLocalidadePai(Integer idLocalidadePai) {
        this.idLocalidadePai = idLocalidadePai;
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

    public Integer getIdLocalidade() {
        return idLocalidade;
    }

    public void setIdLocalidade(Integer idLocalidade) {
        this.idLocalidade = idLocalidade;
    }

    // lista preenchida com idLocalidade para ser utilizada no objeto LocApiFiltroLocalidade,
    // que não contem idLocalidade
    public List<Integer> getListaIdsLocalidade() {
        if (ObjectUtil.isNull(listaIdsLocalidade)) {
            listaIdsLocalidade = new ArrayList<Integer>();
        }
        if (this.idLocalidade != null && this.idLocalidade > 0) {
            listaIdsLocalidade.add(this.idLocalidade);
        }
        return listaIdsLocalidade;
    }

    public String getNumCepLogradouro() {
        return numCepLogradouro;
    }

    public void setNumCepLogradouro(String numCepLogradouro) {
        this.numCepLogradouro = numCepLogradouro;
    }

}
