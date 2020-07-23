package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * TipoOperacaoLog
 * 
 * @author Samuell.Ramos
 */
@Entity
@Table(name = "TIPOOPERACAOLOG", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.TipoOperacaoLogVO")
public class TipoOperacaoLog extends SicoobDDAEntidade {

    private static final long serialVersionUID = -2855303526898260419L;
    public static final String INCLUSAO = "I";
    public static final String ALTERACAO = "A";
    public static final String EXCLUSAO = "E";

    @Id
    @Column(unique = true, nullable = false)
    private String codTipoOperacaoLog;

    @Column(length = 10)
    private String descTipoOperacaoLog;

    public TipoOperacaoLog() {
    }

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