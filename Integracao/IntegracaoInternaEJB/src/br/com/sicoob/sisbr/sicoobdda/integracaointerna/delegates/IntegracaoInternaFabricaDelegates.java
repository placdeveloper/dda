package br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates;

import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;

/**
 * IntegracaoInternaPrivadaFabrica é responsável por
 * 
 * @author Samuell.Ramos
 */
public final class IntegracaoInternaFabricaDelegates extends ComumFabricaDelegates {

    private static IntegracaoInternaFabricaDelegates fabrica;

    private SCIDelegate sciDelegate;
    private SSPBDelegate sspbDelegate;
    private ADMDelegate admDelegate;
    private ContaCorrenteDelegate contaCorrenteDelegate;

    /**
     * Retorna a fabrica de delegates a ser utilizada.
     * 
     * @return a fabrica de delegates a ser utilizada.
     */
    public static IntegracaoInternaFabricaDelegates getInstance() {
        synchronized (IntegracaoInternaFabricaDelegates.class) {
            if (fabrica == null) {
                fabrica = new IntegracaoInternaFabricaDelegates();
            }
        }
        return fabrica;
    }

    /**
     * Construtor privado no-args da classe
     */
    private IntegracaoInternaFabricaDelegates() {
    }

    /**
     * Método responsável por criar o SCIDelegate.
     */
    public SCIDelegate getSCIDelegate() {
        if (sciDelegate == null) {
            sciDelegate = new SCIDelegate();
        }
        return sciDelegate;
    }

    /**
     * Método responsável por criar o delegate
     * 
     * @return
     */
    public SSPBDelegate getSSPBDelegate() {
        if (sspbDelegate == null) {
            sspbDelegate = new SSPBDelegate();
        }
        return sspbDelegate;
    }

    /**
     * Método responsável por criar o delegate
     * 
     * @return
     */
    public ADMDelegate getADMDelegate() {
        if (admDelegate == null) {
            admDelegate = new ADMDelegate();
        }
        return admDelegate;
    }

    public ContaCorrenteDelegate getContaCorrenteDelegate() {
        if (contaCorrenteDelegate == null) {
            contaCorrenteDelegate = new ContaCorrenteDelegate();
        }
        return contaCorrenteDelegate;
    }

}
