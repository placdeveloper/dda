/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.fachada.vo
 * Arquivo:         SituacaoArquivoDDAVO.java
 * Data Criação:    Aug 4, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * SituacaoArquivoDDAVO é responsável por
 * 
 * @author Felipe.Rosa
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoArquivoDDA")
public class SituacaoArquivoDDAVO extends BancoobVO {

    private Integer codSituacaoArquivo;
    private String descSituacaoArquivo;

    /**
     * @return the codSituacaoArquivo
     */
    public Integer getCodSituacaoArquivo() {
        return codSituacaoArquivo;
    }

    /**
     * @param codSituacaoArquivo the codSituacaoArquivo to set
     */
    public void setCodSituacaoArquivo(Integer codSituacaoArquivo) {
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
