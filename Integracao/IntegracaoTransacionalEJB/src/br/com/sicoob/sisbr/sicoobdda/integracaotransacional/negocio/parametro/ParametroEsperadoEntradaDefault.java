package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

/**
 * @see ParametroEsperadoEntrada Valor default dos parâmetros de entrada
 * 
 * @author Paulo.Stoppa
 */
public final class ParametroEsperadoEntradaDefault extends ParametroEsperadoEntrada implements ValorDefault {

    private final Object valorPadrao;
    private final int tipoJdbc;

    /**
     * Construtor padrão que recebe a chave do rótulo e o valor padrão para o parâmetro
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