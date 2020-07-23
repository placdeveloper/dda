/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos
 * Arquivo:         ComumCrudServico.java
 * Data Criação:    26/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.servicos.BancoobCrudServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ComumCrudServico é responsável por
 * 
 * @author Fabricio.Brandao
 */
public interface ComumCrudServico<T extends SicoobDDAEntidade> extends ComumServico, BancoobCrudServico<T> {

    /**
     * Método responsável por realizar a pesquisa, e, se informados os ids do parâmetro e da instituição, verificará o limite de consulta disponível.
     * 
     * @param classe
     * @param criterios
     * @param pe
     * @param idParametro
     * @param idInstituicao
     * @return ConsultaDto<E>
     */
    <E extends SicoobDDAEntidade> ConsultaDto<E> pesquisar(Class<E> classe, ConsultaDto<E> criterios, PesquisaEnum pe, long idParametro, int idInstituicao) throws BancoobException;
}
