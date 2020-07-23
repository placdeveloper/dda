package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * DataMovimentoNaoEncontradaException é responsável por
 * 
 * @author Rodrigo.Neri
 */
@ApplicationException(rollback = true)
public class DataMovimentoNaoEncontradaException extends IntegracaoInternaException {

    private static final long serialVersionUID = -5419893345658354409L;

    /**
     * @param numBanco
     */
    public DataMovimentoNaoEncontradaException(Integer idInstituicao, Integer idProduto) {
        super("adaptadorintegracaoservico.data.movimento.nao.encontrada.instituicao", idInstituicao, idProduto);
    }

}
