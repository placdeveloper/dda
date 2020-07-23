/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.4.0-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.delegates
 * Arquivo:         RequisicaoCacheDelegate.java
 * Data Criação:    19 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.RequisicaoCacheServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;
import br.com.sicoob.sisbr.sicoobdda.entidades.RequisicaoCache;

/**
 * RequisicaoCacheDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class RequisicaoCacheDelegate extends ComumCrudDelegate<RequisicaoCache, RequisicaoCacheServico> implements RequisicaoCacheServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected RequisicaoCacheServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarRequisicaoCacheServico();
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
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.RequisicaoCacheServico#incluir(br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CacheEnum)
     */
    @Override
    public void incluir(CacheEnum cacheEnum) throws ComumException, ComumNegocioException {
        localizarServico().incluir(cacheEnum);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.RequisicaoCacheServico#pesquisarRequisicao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.FiltroRequisicaoCacheDto)
     */
    @Override
    public List<RequisicaoCache> pesquisarRequisicao(FiltroRequisicaoCacheDto filtroDto) throws ComumException, ComumNegocioException {
        return localizarServico().pesquisarRequisicao(filtroDto);
    }

}
