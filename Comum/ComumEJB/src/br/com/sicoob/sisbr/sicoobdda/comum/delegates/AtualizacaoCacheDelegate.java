/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         AtualizacaoCacheDelegate.java
 * Data Criação:    16 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizacaoCacheServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.entidades.AtualizacaoCache;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * AtualizacaoCacheDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class AtualizacaoCacheDelegate extends ComumCrudDelegate<AtualizacaoCache, AtualizacaoCacheServico> implements AtualizacaoCacheServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected AtualizacaoCacheServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarAtualizacaoCacheServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizacaoCacheServico#processarRequisicao(br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache)
     */
    @Override
    public void processarRequisicao(RequisicaoCache requisicaoCache) throws ComumException, ComumNegocioException {
        localizarServico().processarRequisicao(requisicaoCache);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.AtualizacaoCacheServico#listar(java.lang.Long)
     */
    @Override
    public List<AtualizacaoCache> listar(Long idRequisicaoCache) throws ComumException, ComumNegocioException {
        return localizarServico().listar(idRequisicaoCache);
    }
}
