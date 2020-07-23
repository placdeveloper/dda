package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizaParametroServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;

public interface AtualizaParametroServicoLocal extends AtualizaParametroServico {

    /**
     * Método responsável por alterar o parâmetro numa transação separada.
     * 
     * @param parametro
     * @throws ComumException
     */
    void alterarParametroNovaTransacao(ParametroDDA parametro) throws ComumException;

    /**
     * Método responsável por incluir o parâmetro numa transação separada.
     * 
     * @param parametro
     * @return
     * @throws ComumException
     */
    ParametroDDA incluirParametroNovaTransacao(ParametroDDA parametro) throws ComumException;

}
