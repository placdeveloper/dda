package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * PagadorEletronicoDDALegadoDao � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
public interface PagadorEletronicoDDALegadoDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * 
     * M�todo respons�vel por executar SPU_DDA_ATUALIZAR_SACADOELETRONICO
     * 
     * @param numCooperativa
     * @throws ComumException
     */
    void atualizarSacadoEletronico(Integer numCooperativa) throws ComumException;

}
