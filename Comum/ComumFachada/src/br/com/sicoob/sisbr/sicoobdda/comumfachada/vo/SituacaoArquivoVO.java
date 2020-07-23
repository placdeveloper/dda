package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoArquivoVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoArquivo")
public class SituacaoArquivoVO extends BancoobVO {

    private String codSituacaoArquivo;
    private String descSituacaoArquivo;

    /**
     * @return the codSituacaoArquivo
     */
    public String getCodSituacaoArquivo() {
        return codSituacaoArquivo;
    }

    /**
     * @param codSituacaoArquivo the codSituacaoArquivo to set
     */
    public void setCodSituacaoArquivo(String codSituacaoArquivo) {
        this.codSituacaoArquivo = codSituacaoArquivo;
    }

    /**
     * @return the descSituacaoArquivo
     */
    public String getDescSituacaoArquivo() {
        return descSituacaoArquivo;
    }

    /**
     * @param descSituacaoArquivo the descSituacaoArquivo to set
     */
    public void setDescSituacaoArquivo(String descSituacaoArquivo) {
        this.descSituacaoArquivo = descSituacaoArquivo;
    }


}
