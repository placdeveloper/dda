package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoProcessamentoArquivo é responsável por
 * 
 * @author Francisco.Marcio
 */
@Entity
@Table(name = "SITUACAOPROCESSAMENTOARQUIVO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoProcessamentoArquivoVO")
public class SituacaoProcessamentoArquivo extends SicoobDDAEntidade {

    private static final long serialVersionUID = 0L;

    public static final short ARQUIVO_DISPONIVEL = 1;
    public static final short ARQUIVO_ABERTO = 2;
    public static final short REGISTROS_DETALHADOS = 3;
    public static final short ARQUIVO_EM_PROCESSAMENTO = 4;
    public static final short ARQUIVO_PROCESSADO = 5;
    public static final short ARQUIVO_REJEITADO = 6;
    public static final short ARQUIVO_ADDARR2_REJEITADO_756 = 7;

    @Id
    @Column(name = "CODSITUACAOPROCESSAMENTOARQUIVO", unique = true, nullable = false)
    private short codSituacaoProcessamentoArquivo;

    @Column(name = "DESCSITUACAOPROCESSAMENTOARQUIVO", nullable = false)
    private String descSituacaoProcessamentoArquivo;

    /**
     * 
     */
    public SituacaoProcessamentoArquivo() {
    }

    /**
     * @param codSituacaoProcessamentoArquivo
     */
    public SituacaoProcessamentoArquivo(short codSituacaoProcessamentoArquivo) {
        this.codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
    }

    /**
     * @return o atributo codSituacaoProcessamentoArquivo
     */
    public short getCodSituacaoProcessamentoArquivo() {
        return codSituacaoProcessamentoArquivo;
    }

    /**
     * Define o atributo codSituacaoProcessamentoArquivo
     */
    public void setCodSituacaoProcessamentoArquivo(short codSituacaoProcessamentoArquivo) {
        this.codSituacaoProcessamentoArquivo = codSituacaoProcessamentoArquivo;
    }

    /**
     * @return o atributo descSituacaoProcessamentoArquivo
     */
    public String getDescSituacaoProcessamentoArquivo() {
        return descSituacaoProcessamentoArquivo;
    }

    /**
     * Define o atributo descSituacaoProcessamentoArquivo
     */
    public void setDescSituacaoProcessamentoArquivo(String descSituacaoProcessamentoArquivo) {
        this.descSituacaoProcessamentoArquivo = descSituacaoProcessamentoArquivo;
    }

}
