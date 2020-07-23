/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         ComumCrudDelegate.java
 * Data Cria��o:    24/09/2012
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.PesquisaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * CobrancaCrudDelegate � respons�vel por
 * 
 * @author Fabricio.Brandao
 */
public abstract class ComumCrudDelegate<T extends SicoobDDAEntidade, S extends ComumCrudServico<T>> extends BancoobCrudDelegate<T, S> {

    /**
     * M�todo respons�vel por pesquisar
     * 
     * @param classe
     * @param criterios
     * @param pe
     * @return ConsultaDto<E>
     * @throws ComumException
     */
    public <E extends SicoobDDAEntidade> ConsultaDto<E> pesquisar(Class<E> classe, ConsultaDto<E> criterios, PesquisaEnum pe) throws ComumException {
        return pesquisar(classe, criterios, pe, -1, -1);
    }

    /**
     * M�todo respons�vel por realizar a pesquisa, e, se informados os ids do par�metro e da institui��o, verificar� o limite de consulta dispon�vel.
     * 
     * @param classe
     * @param criterios
     * @param pe
     * @param idParametro
     * @param idInstituicao
     * @return
     * @throws ComumException
     */
    public <E extends SicoobDDAEntidade> ConsultaDto<E> pesquisar(Class<E> classe, ConsultaDto<E> criterios, PesquisaEnum pe, long idParametro, int idInstituicao)
            throws ComumException {
        try {
            return getServico().pesquisar(classe, criterios, pe, idParametro, idInstituicao);
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

}
