package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public abstract class IntegracaoCipArquivoDelegate<T extends IntegracaoCipServico> extends ComumDelegate<T> {

    /**
     * 
     */
    public IntegracaoCipArquivoDelegate() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico#enviarMensagem()
     */
    public abstract Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumNegocioException, ComumException;

}