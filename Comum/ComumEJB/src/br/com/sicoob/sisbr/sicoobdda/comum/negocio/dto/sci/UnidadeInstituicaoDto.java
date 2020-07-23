package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * 
 * @author George.Santos
 */
@Conversor(DTO = "br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.UnidadeInstituicaoFachadaDTO")
public class UnidadeInstituicaoDto extends BancoobDto {

    private static final long serialVersionUID = -8225223758719183805L;

    public static final String[] CAMPOS = new String[] { "nomeUnidade", "idInstituicao", "idUnidadeInst", "nomeInstituicao", "numCNPJ" };

    private String nomeUnidade;

    private Integer idUnidadeInst;

    private Integer idInstituicao;

    private String nomeInstituicao;

    private String numCNPJ;

    private Boolean selecionado;

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public Integer getIdUnidadeInst() {
        return idUnidadeInst;
    }

    public void setIdUnidadeInst(Integer idUnidadeInst) {
        this.idUnidadeInst = idUnidadeInst;
    }

    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getNumCNPJ() {
        return numCNPJ;
    }

    public void setNumCNPJ(String numCNPJ) {
        this.numCNPJ = numCNPJ;
    }

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

}
