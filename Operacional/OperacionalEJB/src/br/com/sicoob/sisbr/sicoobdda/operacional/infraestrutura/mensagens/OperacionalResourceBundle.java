package br.com.sicoob.sisbr.sicoobdda.operacional.infraestrutura.mensagens;

import br.com.sicoob.sisbr.sicoobdda.comum.infraestrutura.mensagens.ComumResourceBundle;

/**
 * Classe responsavel pela carga das mensagens do sistema Operacional
 * 
 * @author Sicoob
 */
public class OperacionalResourceBundle extends ComumResourceBundle {

    /** Nome do arquivo de mensagens do sistema "Operacional". */
    private static final String OPERACIONAL_SICOOB_PROPERTIES = "sicoobdda_operacional.bancoob.properties";
    /** Resource bundle a ser usada. */
    private static OperacionalResourceBundle bundle;

    /**
     * Retorna o bundle a ser usado.
     * 
     * @return o bundle a ser usado.
     */
    public static OperacionalResourceBundle getInstance() {
        if (bundle == null) {
            criarInstancia();
        }
        return bundle;
    }

    private static synchronized void criarInstancia() {
        if (bundle == null) {
            bundle = new OperacionalResourceBundle();
        }
    }

    /**
     * Inicia o bundle com as suas propriedades padrao.
     */
    private OperacionalResourceBundle() {
        super(OPERACIONAL_SICOOB_PROPERTIES);
    }
}