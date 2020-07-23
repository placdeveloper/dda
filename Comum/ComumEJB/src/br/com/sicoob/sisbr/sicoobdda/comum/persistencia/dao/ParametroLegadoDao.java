package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ParametroLegadoDao � respons�vel por
 * 
 * @author George.Santos
 */
public interface ParametroLegadoDao extends ComumCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por retornar o valor do par�metro
     * 
     * @param idParametro
     * @param numCooperativa
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    String obterValor(Integer idParametro, Integer numCooperativa) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por retornar o valor do par�metro como boolean
     * 
     * @param idParametro
     * @param numCooperativa
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    Boolean obterValorBoolean(Integer idParametro, Integer numCooperativa) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por retornar o valor do par�metro como double
     * 
     * @param idParametro
     * @param numCooperativa
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    Double obterValorDouble(Integer idParametro, Integer numCooperativa) throws ComumException, ComumNegocioException;

    /**
     * M�todo respons�vel por retornar o segundo valor do par�metro
     * 
     * @param idParametro
     * @param numCooperativa
     * @return
     * @throws ComumException
     * @throws ComumNegocioException String
     * 
     */
    String obterValorSegundo(Long idParametro, Integer numCooperativa) throws ComumException, ComumNegocioException;

}
