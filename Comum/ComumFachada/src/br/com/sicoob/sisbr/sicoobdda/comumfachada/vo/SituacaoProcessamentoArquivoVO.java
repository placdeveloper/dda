/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  cob-comum-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;
 * Arquivo:         SituacaoArquivoDDAVO.java
 * Data Criação:    Fev 1, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comumfachada.vo;

import br.com.bancoob.sisbrweb.vo.BancoobVO;
import br.com.sicoob.sisbr.sicoobdda.entidades.anotacao.Conversor;

/**
 * @author samuell.ramos
 */
@Conversor(entidade = "br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo")
public class SituacaoProcessamentoArquivoVO extends BancoobVO {


    private short codSituacaoProcessamentoArquivo;
    private String descSituacaoProcessamentoArquivo;
    
	/**
	 * @return o atributo codSituacaoProcessamentoArquivo
	 */
	public short getCodSituacaoProcessamentoArquivo() {
		return codSituacaoProcessamentoArquivo;
	}
	/**
	 * Define o atributo codSituacaoProcessamentoArquivo
	 */
	public void setCodSituacaoProcessamentoArquivo(
			short codSituacaoProcessamentoArquivo) {
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
	public void setDescSituacaoProcessamentoArquivo(
			String descSituacaoProcessamentoArquivo) {
		this.descSituacaoProcessamentoArquivo = descSituacaoProcessamentoArquivo;
	}

}
