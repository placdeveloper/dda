/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         ComumCrudDaoIF.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.persistencia.dao.BancoobCrudDaoIF;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ComumCrudDaoIF é responsável por
 * 
 * @author Fabricio.Brandao
 */
public interface ComumCrudDaoIF<T extends SicoobDDAEntidade> extends BancoobCrudDaoIF<T> {

    /**
     * Método responsável por realizar a pesquisa, e, se informados os ids do parâmetro e da instituição, verificará o limite de consulta disponível.
     * 
     * @param classe
     * @param criterios
     * @param pe
     * @param idParametro
     * @param idInstituicao
     * 
     * @return ConsultaDto<E>
     */
    <E extends SicoobDDAEntidade> ConsultaDto<E> pesquisar(Class<E> classe, ConsultaDto<E> criterios, PesquisaEnum pe, long idParametro, int idInstituicao)
            throws ComumNegocioException, ComumException;

}
