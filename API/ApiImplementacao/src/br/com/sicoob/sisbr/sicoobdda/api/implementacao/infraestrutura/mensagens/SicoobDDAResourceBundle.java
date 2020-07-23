/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-implementacao
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.api.implementacao.infraestrutura.mensagens
 * Arquivo:         SicoobDDAResourceBundle.java
 * Data Criação:    13/05/2016
 */
package br.com.sicoob.sisbr.sicoobdda.api.implementacao.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.bancoob.infraestrutura.mensagens.BancoobResourceBundle;
import br.com.bancoob.infraestrutura.mensagens.CorporativoResourceBundle;

/**
 * SicoobDDAResourceBundle é responsável por criar o Bundle do arquivo de mensagens.
 * 
 */
public class SicoobDDAResourceBundle extends BancoobResourceBundle {

    private static final String COMUM_SICOOB_PROPERTIES = "sicoobdda_apicliente.bancoob.properties";
    private static SicoobDDAResourceBundle bundle = null;

    public static SicoobDDAResourceBundle getInstance() {
        if (bundle == null) {
            criarInstancia();
        }
        return bundle;
    }

    private static synchronized void criarInstancia() {
        if (bundle == null) {
            bundle = new SicoobDDAResourceBundle();
        }
    }

    protected SicoobDDAResourceBundle() {
        this(COMUM_SICOOB_PROPERTIES);
    }

    protected SicoobDDAResourceBundle(String arquivoProperties) {
        this(arquivoProperties, CorporativoResourceBundle.getInstance());
    }

    protected SicoobDDAResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
        super(arquivoProperties, resourcePai);
    }
}
