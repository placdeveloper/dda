package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.excecao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;

/**
 * Excecao padrao para o sistema Processamento.
 * 
 * @author Sicoob
 */
public class ProcessamentoException extends ComumException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @param chave
	 */
	private ProcessamentoException(String chave) {
		super(chave);
	}

	/**
	 * @param chave
	 * @param parametros
	 * @param excecao
	 */
	private ProcessamentoException(String chave, Object[] parametros, Throwable excecao) {
		super(chave, parametros, excecao);
	}

	/**
	 * @param chave
	 * @param excecao
	 */
	private ProcessamentoException(String chave, Throwable excecao) {
		super(chave, excecao);
	}

	/**
	 * @param excecao
	 */
	private ProcessamentoException(Throwable excecao) {
		super(excecao);
	}
}