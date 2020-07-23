/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util
 * Arquivo:         AssignIdToArticleAnswer.java
 * Data Criação:    28 de dez de 2018
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;

/**
 * AssignIdToArticleAnswer é responsável por retornar ID após Persistir alguma entidade
 * 
 * @author tayrone.oliveira
 */
public class AssignIdToArticleAnswer implements Answer<Void> {

    @SuppressWarnings("unused")
    private Long id;
    @SuppressWarnings("unused")
    private Object obj;

    /**
     * @param id
     */
    public AssignIdToArticleAnswer(Long id, Object obj) {
        super();
        this.id = id;
        this.obj = obj;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.mockito.stubbing.Answer#answer(org.mockito.invocation.InvocationOnMock)
     */
    @Override
    public Void answer(InvocationOnMock invocation) throws Throwable {
        if (this.obj instanceof LogRecebimentoArquivoDDA) {
            LogRecebimentoArquivoDDA log = (LogRecebimentoArquivoDDA) invocation.getArguments()[0];
            log.setId(id);
        }
        return null;
    }

}
