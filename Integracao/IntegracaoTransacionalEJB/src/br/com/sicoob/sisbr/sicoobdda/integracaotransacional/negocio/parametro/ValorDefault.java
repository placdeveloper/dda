package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

/**
 * interface, que define contrato para valores default
 * 
 * @author Paulo.Stoppa
 */
public interface ValorDefault {

    /**
     * Retorna o valor padr�o para o par�metro
     * 
     * @return
     */
    Object getValorPadrao();

    /**
     * Retorna o tipo JDBC do par�metro
     * 
     * @return
     */
    int getTipoJdbc();

}