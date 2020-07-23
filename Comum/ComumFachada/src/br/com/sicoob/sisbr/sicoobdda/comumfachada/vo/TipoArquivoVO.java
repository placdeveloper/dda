package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoArquivoVO é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoArquivo")
public class TipoArquivoVO extends BancoobVO {

    private String codTipoArquivo;
    private String descTipoArquivo;
    /**
     * @return the codTipoArquivo
     */
    public String getCodTipoArquivo() {
        return codTipoArquivo;
    }
    /**
     * @param codTipoArquivo the codTipoArquivo to set
     */
    public void setCodTipoArquivo(String codTipoArquivo) {
        this.codTipoArquivo = codTipoArquivo;
    }
    /**
     * @return the descTipoArquivo
     */
    public String getDescTipoArquivo() {
        return descTipoArquivo;
    }
    /**
     * @param descTipoArquivo the descTipoArquivo to set
     */
    public void setDescTipoArquivo(String descTipoArquivo) {
        this.descTipoArquivo = descTipoArquivo;
    }



}
