/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         TipoErroCipDelegate.java
 * Data Criação:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TipoErroCipServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * TipoErroCipDelegate é responsável por 
 * 
 * @author Felipe.Rosa
 */
@SuppressWarnings("rawtypes")
public class TipoErroCipDelegate extends OperacionalCrudDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected TipoErroCipServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarTipoErroCipServico();
    }

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @return
     * @throws OperacionalNegocionException TipoErroCip
     * 
     */
    public TipoErroCip obterTipoErro(String codTipoErro) throws OperacionalNegocionException {
        return localizarServico().obterTipoErro(codTipoErro);
    }

    /**
     * Método responsável por
     * 
     * @param tipoErro
     * @return
     * @throws BancoobException TipoErroCip
     * 
     */
    public TipoErroCip incluirTipoErro(TipoErroCip tipoErro) throws BancoobException {
        return localizarServico().incluirTipoErro(tipoErro);
    }

    /**
     * Método responsável por
     * 
     * @param tipoErro
     * @return
     * @throws BancoobException TipoErroCip
     * 
     */
    public TipoErroCip alterarTipoErro(TipoErroCip tipoErro) throws BancoobException {
        return localizarServico().alterarTipoErro(tipoErro);
    }

    /**
     * Método responsável por
     * 
     * @param codTipoErro
     * @throws BancoobException void
     * 
     */
    public void excluirTipoErro(String codTipoErro) throws BancoobException {
        localizarServico().excluirTipoErro(codTipoErro);
    }

}
