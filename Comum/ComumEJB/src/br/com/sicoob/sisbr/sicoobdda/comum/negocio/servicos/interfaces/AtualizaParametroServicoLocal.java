package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizaParametroServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;

public interface AtualizaParametroServicoLocal extends AtualizaParametroServico {

    /**
     * M�todo respons�vel por alterar o par�metro numa transa��o separada.
     * 
     * @param parametro
     * @throws ComumException
     */
    void alterarParametroNovaTransacao(ParametroDDA parametro) throws ComumException;

    /**
     * M�todo respons�vel por incluir o par�metro numa transa��o separada.
     * 
     * @param parametro
     * @return
     * @throws ComumException
     */
    ParametroDDA incluirParametroNovaTransacao(ParametroDDA parametro) throws ComumException;

}
