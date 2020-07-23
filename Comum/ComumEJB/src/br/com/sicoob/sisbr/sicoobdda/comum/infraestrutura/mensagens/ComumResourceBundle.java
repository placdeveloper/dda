package br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * ComumResourceBundle é responsável por
 * 
 * @author Fabricio.Brandao
 */
public class ComumResourceBundle extends BancoobResourceBundle {

    private static final String COMUM_SICOOB_PROPERTIES = "sicoobdda_comum.bancoob.properties";
    private static ComumResourceBundle bundle = null;

    /**
     * Método responsável por obter a instância da classe.
     */
    public static ComumResourceBundle getInstance() {
        if (bundle == null) {
            criarInstancia();
        }
        return bundle;
    }

    private static synchronized void criarInstancia() {
        if (bundle == null) {
            bundle = new ComumResourceBundle();
        }
    }

    /**
     * Construtor
     */
    protected ComumResourceBundle() {
        this(COMUM_SICOOB_PROPERTIES);
    }

    /**
     * Construtor que recebe como parâmetro o nome do arquivo de propriedade.
     */
    protected ComumResourceBundle(String arquivoProperties) {
        this(arquivoProperties, CorporativoResourceBundle.getInstance());
    }

    /**
     * Construtor que recebe como parâmetro o nome do arquivo de propriedade e o resource pai.
     */
    protected ComumResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
        super(arquivoProperties, resourcePai);
    }
}
