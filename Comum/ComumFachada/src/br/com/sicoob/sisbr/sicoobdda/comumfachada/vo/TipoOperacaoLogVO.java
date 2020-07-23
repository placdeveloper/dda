package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * Classe responsável por representar a entidade.
 * 
 * @author Rodrigo.Neri
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.TipoOperacaoLog")
public class TipoOperacaoLogVO extends BancoobVO {

    private String codTipoOperacaoLog;
    private String descTipoOperacaoLog;

    /**
     * @return o atributo codTipoOperacaoLog
     */
    public String getCodTipoOperacaoLog() {
        return codTipoOperacaoLog;
    }

    /**
     * Define o atributo codTipoOperacaoLog
     */
    public void setCodTipoOperacaoLog(String codTipoOperacaoLog) {
        this.codTipoOperacaoLog = codTipoOperacaoLog;
    }

    /**
     * @return o atributo descTipoOperacaoLog
     */
    public String getDescTipoOperacaoLog() {
        return descTipoOperacaoLog;
    }

    /**
     * Define o atributo descTipoOperacaoLog
     */
    public void setDescTipoOperacaoLog(String descTipoOperacaoLog) {
        this.descTipoOperacaoLog = descTipoOperacaoLog;
    }

}
