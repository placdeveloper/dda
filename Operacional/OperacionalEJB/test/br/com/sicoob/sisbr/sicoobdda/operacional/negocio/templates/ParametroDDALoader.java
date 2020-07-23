/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.templates
 * Arquivo:         ParametroDDALoader.java
 * Data Cria��o:    Jul 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.templates;

import java.util.ArrayList;
import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * ParametroDDALoader � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public final class ParametroDDALoader {

    /**
     * 
     */
    private ParametroDDALoader() {
    }

    /**
     * M�todo respons�vel por
     * 
     * @param idParametro
     * @return ParametroDDA
     * 
     */
    public static ParametroDDA gerar(Long idParametro) {
        return new ParametroDDA(idParametro, Boolean.TRUE, Constantes.STRING_NUMERO_1);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param idsParametros
     * @return List<ParametroDDA>
     * 
     */
    public static List<ParametroDDA> gerarLista(long... idsParametros) {
        List<ParametroDDA> listaParametro = new ArrayList<ParametroDDA>();
        for (int i = 0; i < idsParametros.length; i++) {
            listaParametro.add(new ParametroDDA(idsParametros[i], Boolean.TRUE, Constantes.STRING_NUMERO_1));
        }
        return listaParametro;
    }

}
