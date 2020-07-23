package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * 
 * @author Lucas.Moura
 * 
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.UnidadeInstituicaoDto")
public class UnidadeInstituicaoFachadaDTO extends BancoobDTO {

    private String nomeUnidade;

    private Integer idUnidadeInst;

    private Integer idInstituicao;

    private String nomeInstituicao;

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

    public Boolean getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(Boolean selecionado) {
        this.selecionado = selecionado;
    }

    public String getDescricaoCompletaUnidadeInst() {
        return getIdUnidadeInst() + "-" + getNomeUnidade();
    }

}
