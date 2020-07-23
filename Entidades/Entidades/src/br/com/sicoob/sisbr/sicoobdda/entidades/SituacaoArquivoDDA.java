/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-entidades
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.entidades.dda
 * Arquivo:         DDASituacaoArquivo.java
 * Data Criacao:    Jul 27, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoArquivoDDA
 * 
 * @author rafael.silva
 */
@Entity
@Table(name = "SITUACAOARQUIVO", schema = "DDA")
@Conversor(vo = "br.com.sicoob.sisbr.sicoobdda.comumfachada.vo.SituacaoArquivoDDAVO")
public class SituacaoArquivoDDA extends SicoobDDAEntidade {

    private static final long serialVersionUID = -1321733300905397879L;

    public static final int REQUISICAO_REGISTRADA = 1;
    public static final int REQUISICAO_ATENDIDA = 2;
    public static final int REQUISICAO_SEM_DADOS_QUE_ATENDAM_AO_FILTRO = 3;
    public static final int INICIO_PROCESSAMENTO = 4;
    public static final int COMPLETAMENTE_ATENDIDA_VIA_ARQUIVO = 6;
    public static final int ERRO_NO_ARQUIVO = 99;

    @Id
    @Column(name = "CODSITUACAOARQUIVO", unique = true, nullable = false)
    private Integer codSituacaoArquivo;

    @Column(nullable = false)
    private String descSituacaoArquivo;

    /**
     * @return o atributo codSituacaoArquivo
     */
    public Integer getCodSituacaoArquivo() {
        return codSituacaoArquivo;
    }

    /**
     * Define o atributo codSituacaoArquivo
     */
    public void setCodSituacaoArquivo(Integer codSituacaoArquivo) {
        this.codSituacaoArquivo = codSituacaoArquivo;
    }

    /**
     * @return o atributo descSituacaoArquivo
     */
    public String getDescSituacaoArquivo() {
        return descSituacaoArquivo;
    }

    /**
     * Define o atributo descSituacaoArquivo
     */
    public void setDescSituacaoArquivo(String descSituacaoArquivo) {
        this.descSituacaoArquivo = descSituacaoArquivo;
    }

}
