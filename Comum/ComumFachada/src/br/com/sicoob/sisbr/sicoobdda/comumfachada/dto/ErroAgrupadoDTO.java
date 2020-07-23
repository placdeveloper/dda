package br.com.sicoob.sisbr.sicoobdda.comumfachada.dto;

import br.com.bancoob.sisbrweb.dto.BancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * ErroAgrupadoDTO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(dto = "br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroAgrupadoDto")
public class ErroAgrupadoDTO extends BancoobDTO {

    private String erroPrincipal;
    private String erroDependente;
    private Integer qtdErro;

    /**
     * @return the erroPrincipal
     */
    public String getErroPrincipal() {
        return erroPrincipal;
    }

    /**
     * @param erroPrincipal the erroPrincipal to set
     */
    public void setErroPrincipal(String erroPrincipal) {
        this.erroPrincipal = erroPrincipal;
    }

    /**
     * @return the erroDependente
     */
    public String getErroDependente() {
        return erroDependente;
    }

    /**
     * @param erroDependente the erroDependente to set
     */
    public void setErroDependente(String erroDependente) {
        this.erroDependente = erroDependente;
    }

    /**
     * @return the qtdErro
     */
    public Integer getQtdErro() {
        return qtdErro;
    }

    /**
     * @param qtdErro the qtdErro to set
     */
    public void setQtdErro(Integer qtdErro) {
        this.qtdErro = qtdErro;
    }


}
