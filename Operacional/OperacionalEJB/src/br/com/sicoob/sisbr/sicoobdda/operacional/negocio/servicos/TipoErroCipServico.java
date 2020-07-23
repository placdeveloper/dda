/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         TipoErroServico.java
 * Data Cria��o:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;

/**
 * TipoErroServico � respons�vel por 
 * 
 * @author Felipe.Rosa
 */
public interface TipoErroCipServico extends OperacionalCrudServico<TipoErroCip> {

    /**
     * M�todo respons�vel por
     * 
     * @param codTipoErro
     * @return
     * @throws OperacionalNegocionException TipoErroCip
     * 
     */
    TipoErroCip obterTipoErro(String codTipoErro) throws OperacionalNegocionException;

    /**
     * M�todo respons�vel por
     * 
     * @param tipoErro
     * @return
     * @throws BancoobException TipoErroCip
     * 
     */
    TipoErroCip incluirTipoErro(TipoErroCip tipoErro) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param tipoErro
     * @return
     * @throws BancoobException TipoErroCip
     * 
     */
    TipoErroCip alterarTipoErro(TipoErroCip tipoErro) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param codTipoErro
     * @throws BancoobException void
     * 
     */
    void excluirTipoErro(String codTipoErro) throws BancoobException;

}
