package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * CentralSingularVo � respons�vel por carregar Singular e Centrais
 * 
 * @author Samuell.Ramos
 */
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.ejb.vo.CentralSingularVo")
public class CentralSingularVO extends BancoobVO {

    private Integer idInstituicao;
    private Integer numeroCooperativa;

    /**
     * M�todo respons�vel por pegar o id do objeto instituicao
     * 
     * @return
     */
    public Integer getIdInstituicao() {
        return idInstituicao;
    }

    /**
     * M�todo respons�vel por colcoar o id do objeto instituicao
     * 
     * @param idInstituicao
     */
    public void setIdInstituicao(Integer idInstituicao) {
        this.idInstituicao = idInstituicao;
    }

    /**
     * M�todo respons�vel por pegar o numeroCoperativa do objeto instituicao
     * 
     * @return
     */
    public Integer getNumeroCooperativa() {
        return numeroCooperativa;
    }

    /**
     * M�todo respons�vel por colocar o numeroCoperativa do objeto instituicao
     * 
     * @param numeroCooperativa
     */
    public void setNumeroCooperativa(Integer numeroCooperativa) {
        this.numeroCooperativa = numeroCooperativa;
    }

}