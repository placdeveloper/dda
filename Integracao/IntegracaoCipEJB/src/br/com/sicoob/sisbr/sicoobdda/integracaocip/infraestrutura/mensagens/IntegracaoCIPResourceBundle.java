package br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema IntegracaoCip
 * 
 * @author Sicoob
 */
public final class IntegracaoCIPResourceBundle extends ComumResourceBundle {

    /** Nome do arquivo de mensagens do sistema "SicoobDDA". */
    private static final String SICOOB_DDA_PROPERTIES = "sicoobdda_integracaocip.bancoob.properties";
    /** Resource bundle a ser usada. */
    private static IntegracaoCIPResourceBundle bundle;

    /**
     * Retorna o bundle a ser usado.
     * 
     * @return o bundle a ser usado.
     */
    public static IntegracaoCIPResourceBundle getInstance() {
        if (bundle == null) {
            criarInstancia();
        }
        return bundle;
    }

    private static synchronized void criarInstancia() {
        if (bundle == null) {
            bundle = new IntegracaoCIPResourceBundle();
        }
    }

    /**
     * Inicia o bundle com as suas propriedades padrao.
     */
    private IntegracaoCIPResourceBundle() {
        this(SICOOB_DDA_PROPERTIES);
    }

    protected IntegracaoCIPResourceBundle(String arquivoProperties) {
        this(arquivoProperties, ComumResourceBundle.getInstance());
    }

    protected IntegracaoCIPResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
        super(arquivoProperties, resourcePai);
    }

}