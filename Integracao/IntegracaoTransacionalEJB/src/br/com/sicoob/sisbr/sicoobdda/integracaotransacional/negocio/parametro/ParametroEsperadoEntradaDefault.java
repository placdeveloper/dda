package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

/**
 * @see ParametroEsperadoEntrada Valor default dos par�metros de entrada
 * 
 * @author Paulo.Stoppa
 */
public final class ParametroEsperadoEntradaDefault extends ParametroEsperadoEntrada implements ValorDefault {

    private final Object valorPadrao;
    private final int tipoJdbc;

    /**
     * Construtor padr�o que recebe a chave do r�tulo e o valor padr�o para o par�metro
     * 
     * @param chaveRotulo
     * @param valorPadrao
     * @param tipoJdbc
     */
    public ParametroEsperadoEntradaDefault(String chaveRotulo, Object valorPadrao, int tipoJdbc) {
        super(chaveRotulo);
        this.valorPadrao = valorPadrao;
        this.tipoJdbc = tipoJdbc;
    }

    /**
     * @see ValorDefault#getValorPadrao()
     */
    public Object getValorPadrao() {
        return valorPadrao;
    }

    /**
     * @see ValorDefault#getTipoJdbc()
     */
    public int getTipoJdbc() {
        return tipoJdbc;
    }

}