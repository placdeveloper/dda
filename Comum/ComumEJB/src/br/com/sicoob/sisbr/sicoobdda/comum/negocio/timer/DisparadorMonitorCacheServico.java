package br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumServico;

/**
 * DisparadorMonitorCacheServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface DisparadorMonitorCacheServico extends ComumServico {
	
	/**
	 * Prepara a execu��o dos servi�os. Uma dessas prepara��es � o pr�ximo hor�rio de execu��o que �
	 * feito com base no par�metro. Se o valor deste
	 * par�metro for vazio, � utilizado o par�metro padr�o,
	 * Para esses par�metros s�o
	 * esperados minutos que ser�o adicionados ao hor�rio atual do qual indicar� a pr�ximo execu��o
	 * dos servi�os de sincroniza��o do cache.
	 */
	void executarServico();

}
