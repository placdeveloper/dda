package br.com.sicoob.sisbr.sicoobdda.processamento.infraestrutura.mensagens;

import java.util.ResourceBundle;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema Processamento
 * 
 * @author Sicoob
 */
public final class ProcessamentoResourceBundle extends ComumResourceBundle {

    /** Nome do arquivo de mensagens do sistema "Processamento". */
    private static final String PROCESSAMENTO_SICOOB_PROPERTIES = "sicoobdda_processamento.bancoob.properties";
    /** Resource bundle a ser usada. */
    private static ProcessamentoResourceBundle bundle;

    /**
     * Retorna o bundle a ser usado.
     * 
     * @return o bundle a ser usado.
     */
    public static ProcessamentoResourceBundle getInstance() {
        if (bundle == null) {
            criarInstancia();
        }
        return bundle;
    }

    /**
     * Método responsável por void
     * 
     */
    private static synchronized void criarInstancia() {
        if (bundle == null) {
            bundle = new ProcessamentoResourceBundle();
        }
    }

    /**
     * Inicia o bundle com as suas propriedades padrao.
     */
    private ProcessamentoResourceBundle() {
        super(PROCESSAMENTO_SICOOB_PROPERTIES);
    }

    /**
     * @param arquivoProperties
     */
    protected ProcessamentoResourceBundle(String arquivoProperties) {
        this(arquivoProperties, ComumResourceBundle.getInstance());
    }

    /**
     * @param arquivoProperties
     * @param resourcePai
     */
    protected ProcessamentoResourceBundle(String arquivoProperties, ResourceBundle resourcePai) {
        super(arquivoProperties, resourcePai);
    }

}