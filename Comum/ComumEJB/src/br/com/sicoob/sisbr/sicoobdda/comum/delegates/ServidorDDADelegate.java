/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         ServidorDDADelegate.java
 * Data Criação:    17 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA;

/**
 * ServidorDDADelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class ServidorDDADelegate extends ComumCrudDelegate<ServidorDDA, ServidorDDAServico> implements ServidorDDAServico {

    /**
     * Método utilizado pelo CacheEnum
     * 
     * @return ParametroDelegate
     * 
     */
    public static ServidorDDADelegate getInstance() {
        return ComumFabricaDelegates.getInstance().getServidorDDADelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ServidorDDAServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarServidorDDAServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    @Override
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#obterServidor()
     */
    @Override
    public ServidorDDA obterServidor() throws ComumException {
        return localizarServico().obterServidor();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#listarAtivo()
     */
    @Override
    public List<ServidorDDA> listarAtivo() throws ComumException {
        return localizarServico().listarAtivo();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#listarServidorDDA()
     */
    @Override
    public List<ServidorDDA> listarServidorDDA() throws ComumException {
        return localizarServico().listarServidorDDA();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#pesquisarServidorDDA(br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA)
     */
    @Override
    public List<ServidorDDA> pesquisarServidorDDA(ServidorDDA filtro) throws ComumException, ComumNegocioException {
        return localizarServico().pesquisarServidorDDA(filtro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ServidorDDAServico#alterarServidorDDA(br.com.sicoob.sisbr.sicoobdda.entidades.ServidorDDA)
     */
    @Override
    public void alterarServidorDDA(ServidorDDA servidorDDA) throws ComumException, ComumNegocioException {
        localizarServico().alterarServidorDDA(servidorDDA);
    }

}
