/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         OperacionalDelegate.java
 * Data Criação:    Mai 10, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacionalServico;

/**
 * IntegracaoInternaPrivadaDelegate
 * 
 * @author Rafael.Silva
 */
public abstract class OperacionalDelegate<T extends OperacionalServico> extends ComumDelegate<T> {

    /**
     * Construtor
     */
    public OperacionalDelegate() {
    }

}
