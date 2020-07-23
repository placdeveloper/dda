package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.infraestrutura.mensagens;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;

/**
 * IntegracaoTransacionalResourceBundle
 * 
 * @author george.santos
 */
public class IntegracaoTransacionalResourceBundle extends ComumResourceBundle {

    /** Nome do arquivo de mensagens do sistema "ConsolidacaoFinanceira". */
    private static final String INTEGRACAOTRANSACIONAL_SICOOB_PROPERTIES = "sicoobdda_integracaotransacional.bancoob.properties";
    /** Resource bundle a ser usada. */
    private static IntegracaoTransacionalResourceBundle bundle;

    /**
     * Retorna o bundle a ser usado.
     * 
     * @return o bundle a ser usado.
     */
    public static IntegracaoTransacionalResourceBundle getInstance() {
        if (bundle == null) {
            criarInstancia();
        }
        return bundle;
    }

    private static synchronized void criarInstancia() {
        if (bundle == null) {
            bundle = new IntegracaoTransacionalResourceBundle();
        }
    }

    /**
     * Inicia o bundle com as suas propriedades padrao.
     */
    private IntegracaoTransacionalResourceBundle() {
        super(INTEGRACAOTRANSACIONAL_SICOOB_PROPERTIES);
    }

}
