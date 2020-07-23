/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates
 * Arquivo:         ProcessarEnvioMensagensDelegate.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates;

import java.util.Date;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.locator.IntegracaoCipServiceLocator;

/**
 * ProcessarEnvioMensagensDelegate
 * 
 * @author Rafael.Silva
 */
public class ProcessarEnvioArquivoDelegate extends IntegracaoCipDelegate<IntegracaoCipServico> implements ProcessarEnvioArquivoServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
        localizarServico().verificarDisponibilidade();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ProcessarEnvioArquivoServico localizarServico() {
        return IntegracaoCipServiceLocator.getInstance().localizarProcessarEnvioArquivoServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico#registrarArquivo(java.util.List, java.lang.Long)
     */
    public void registrarArquivo() throws ComumException {
        localizarServico().registrarArquivo();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico#gerarArquivo(java.lang.Long)
     */
    public void gerarArquivo(Long idLogEnvioArquivoDDA) throws ComumException, ComumNegocioException {
        localizarServico().gerarArquivo(idLogEnvioArquivoDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico#postarArquivo(java.lang.Long)
     */
    public void postarArquivo(Long idLogEnvioArquivoDDA) throws ComumException {
        localizarServico().postarArquivo(idLogEnvioArquivoDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico#tratarArquivosComErro(java.lang.Long, java.util.Date)
     */
    public void tratarArquivosComErro(Long idLogEnvioArquivoDDA, Date dataMovimento) throws ComumException {
        localizarServico().tratarArquivosComErro(idLogEnvioArquivoDDA, dataMovimento);
    }

}
