/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.cobrancabancaria.comum.delegates
 * Arquivo:         EmailDelegate.java
 * Data Criação:    Jun 15, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.comum.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.EmailServico;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.locator.ComumServiceLocator;

/**
 * EmailDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
public class EmailDelegate extends ComumDelegate<EmailServico> implements EmailServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected EmailServico localizarServico() {
        return ComumServiceLocator.getInstance().localizarEmailServico();
    }

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
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.negocio.servicos.EmailServico#enviar(java.lang.Long, java.lang.Long, java.lang.String)
     */
    public void enviar(Long idParametroEmailHabilitado, Long idParametroAssunto, String mensagem) throws ComumException {
        localizarServico().enviar(idParametroEmailHabilitado, idParametroAssunto, mensagem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.comum.negocio.servicos.EmailServico#enviar(java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    public void enviar(String remetente, String destinatario, String assunto, String mensagem) throws ComumException {
        localizarServico().enviar(remetente, destinatario, assunto, mensagem);
    }
}