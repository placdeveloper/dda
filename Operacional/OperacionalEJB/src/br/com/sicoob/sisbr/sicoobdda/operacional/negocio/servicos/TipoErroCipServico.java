/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         TipoErroServico.java
 * Data Criação:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;

/**
 * TipoErroServico é responsável por 
 * 
 * @author Felipe.Rosa
 */
public interface TipoErroCipServico extends OperacionalCrudServico<TipoErroCip> {

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @return
     * @throws OperacionalNegocionException TipoErroCip
     * 
     */
    TipoErroCip obterTipoErro(String codTipoErro) throws OperacionalNegocionException;

    /**
     * Método responsável por
     * 
     * @param tipoErro
     * @return
     * @throws BancoobException TipoErroCip
     * 
     */
    TipoErroCip incluirTipoErro(TipoErroCip tipoErro) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param tipoErro
     * @return
     * @throws BancoobException TipoErroCip
     * 
     */
    TipoErroCip alterarTipoErro(TipoErroCip tipoErro) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @throws BancoobException void
     * 
     */
    void excluirTipoErro(String codTipoErro) throws BancoobException;

}
