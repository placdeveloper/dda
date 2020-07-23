package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.BoletoLegadoDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * PagadorEletronicoDDALegadoDao é responsável por
 * 
 * @author Rodrigo.Neri
 */
public interface BoletoLegadoDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por obter a carteira de acordo com a modalidade
     * 
     * @param numCooperativa
     * @param idProduto
     * @param idModalidade
     * @return Boolean
     * 
     */
    Integer obterCodCarteiraPelaModalidade(Integer numCooperativa, Integer idProduto, Integer idModalidade) throws ComumException, ComumNegocioException;

    /**
     * Método responsável por incluir o registro na tabela TituloDDA se o boleto estiver na onda
     * 
     * @param boletoLegadoDto
     * @return
     */
    boolean incluirTituloDDA(BoletoLegadoDto boletoLegadoDto) throws ComumException;

}
