package br.com.sicoob.sisbr.sicoobdda.comum.negocio.timer;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumServico;

/**
 * DisparadorMonitorCacheServico é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface DisparadorMonitorCacheServico extends ComumServico {
	
	/**
	 * Prepara a execução dos serviços. Uma dessas preparações é o próximo horário de execução que é
	 * feito com base no parâmetro. Se o valor deste
	 * parâmetro for vazio, é utilizado o parâmetro padrão,
	 * Para esses parâmetros são
	 * esperados minutos que serão adicionados ao horário atual do qual indicará a próximo execução
	 * dos serviços de sincronização do cache.
	 */
	void executarServico();

}
