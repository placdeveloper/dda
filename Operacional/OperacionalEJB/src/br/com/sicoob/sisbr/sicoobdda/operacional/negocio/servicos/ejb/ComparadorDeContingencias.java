/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         ComparadorDeContingencias.java
 * Data Criação:    Jan 20, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.Comparator;

import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;

/**
 * ComparadorDeContingencias é responsável por
 * 
 * @author Danilo.Barros
 */
public class ComparadorDeContingencias implements Comparator<HistoricoContingencia>, Serializable {
    private static final long serialVersionUID = 1L;

    public int compare(HistoricoContingencia primeira, HistoricoContingencia segunda) {
        return primeira.getId().compareTo(segunda.getId());
    }

}
