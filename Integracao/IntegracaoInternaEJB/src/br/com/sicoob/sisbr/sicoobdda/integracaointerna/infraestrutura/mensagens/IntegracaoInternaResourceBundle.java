package br.com.sicoob.sisbr.sicoobdda.integracaointerna.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;
import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema IntegracaoInternaPrivada
 * 
 * @author Samuell.Ramos
 */
public class IntegracaoInternaResourceBundle extends ComumResourceBundle {

    /** Nome do arquivo de mensagens do sistema "IntegracaoInternaPrivada". */
    private static final String INTEGRACAOINTERNAPRIVADA_SICOOB_PROPERTIES = "sicoobdda_integracaointerna.bancoob.properties";
    /** Resource bundle a ser usada. */
    private static IntegracaoInternaResourceBundle bundle;

    /**
     * Retorna o bundle a ser usado.
     * 
     * @return o bundle a ser usado.
     */
    public static IntegracaoInternaResourceBundle getInstance() {
        if (bundle == null) {
            criarInstancia();
        }
        return bundle;
    }

    private static synchronized void criarInstancia() {
        if (bundle == null) {
            bundle = new IntegracaoInternaResourceBundle();
        }
    }

    /**
     * Inicia o bundle com as suas propriedades padrao.
     */
    private IntegracaoInternaResourceBundle() {
        this(INTEGRACAOINTERNAPRIVADA_SICOOB_PROPERTIES);
    }

    protected IntegracaoInternaResourceBundle(String arquivoProperties) {
        this(arquivoProperties, CorporativoResourceBundle.getInstance());
    }

    protected IntegracaoInternaResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
        super(arquivoProperties, resourcePai);
    }

}