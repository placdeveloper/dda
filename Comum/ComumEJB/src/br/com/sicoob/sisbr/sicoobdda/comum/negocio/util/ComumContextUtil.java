/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.util
 * Arquivo:         ComumContextUtil.java
 * Data Criação:    Jun 30, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import br.com.bancoob.negocio.excecao.RelatorioException;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.RelatorioSicoobDDA;

/**
 * @author samuell.ramos
 * 
 */
public class ComumContextUtil {

    private ContextoHttp contextoHttp;

    /**
     * @return ContextoHttp
     */
    private ContextoHttp getContextoHttp() {
        if (this.contextoHttp == null) {
            this.contextoHttp = ContextoHttp.getInstance();
        }
        return contextoHttp;
    }
    
    /**
     * @param relatorioSicoobDDA
     * @throws ComumException
     */
    public void gerarRelatorioSincronamente(RelatorioSicoobDDA relatorioSicoobDDA) throws ComumException {
        Object conteudo;
        try {
            conteudo = relatorioSicoobDDA.gerarSincronamente();
        } catch (RelatorioException e) {
            throw new ComumException(e);
        }
        getContextoHttp().adicionarContexto(relatorioSicoobDDA.getContextoFlex(), conteudo);
    }

}