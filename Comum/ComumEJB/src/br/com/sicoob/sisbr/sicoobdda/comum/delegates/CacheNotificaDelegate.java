/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         CacheNotificaDelegate.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CacheNotificaServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * CacheNotificaDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class CacheNotificaDelegate extends ComumDelegate<CacheNotificaServico> implements CacheNotificaServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected CacheNotificaServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarCacheNotificaServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CacheNotificaServico#notificarCache(br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache)
     */
    @Override
    public void notificarCache(RequisicaoCache requisicao) throws ComumException {
        localizarServico().notificarCache(requisicao);
    }

}
