package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * CooperativaLegadoDao é responsável por
 * 
 * @author Rodrigo.Neri
 */
public interface CooperativaLegadoDao extends ComumCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por listar as cooperativas
     * 
     * @param numCooperativa
     * @return
     * @throws ComumException
     */
    List<Integer> listarCooperativa(Integer numCooperativa) throws ComumException;

}
