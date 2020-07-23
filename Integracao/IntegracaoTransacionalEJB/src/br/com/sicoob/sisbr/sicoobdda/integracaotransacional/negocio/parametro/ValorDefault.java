package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

/**
 * interface, que define contrato para valores default
 * 
 * @author Paulo.Stoppa
 */
public interface ValorDefault {

    /**
     * Retorna o valor padrão para o parâmetro
     * 
     * @return
     */
    Object getValorPadrao();

    /**
     * Retorna o tipo JDBC do parâmetro
     * 
     * @return
     */
    int getTipoJdbc();

}