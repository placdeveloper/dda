/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb-2.3.3.9-SNAPSHOT
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb
 * Arquivo:         CacheServicoEJB.java
 * Data Criação:    8 de set de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.cache.CacheLoad;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.interfaces.CacheServicoLocal;

/**
 * CacheServicoEJB é responsável por 
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local(CacheServicoLocal.class)
public class CacheServicoEJB extends ComumServicoEJB implements CacheServicoLocal {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.CacheServico#carregar()
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void carregar() throws ComumException {
        CacheLoad.getInstance().sondarCache();
    }

}
