package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

import br.com.bancoob.srtb.dto.TipoParametro;

/**
 * Par�metros para as transa��es
 * 
 * @author Paulo.Stoppa
 */
public abstract class ParametroEsperado {

    /**
     * R�tulo do par�metro
     */
    private final String chaveRotulo;

    /**
     * Tipo de par�metro
     */
    private final TipoParametro tipoParametro;

    /**
     * Construtor que recebe a chave do r�tulo e o tipo de par�metro
     * 
     * @param chaveRotulo
     * @param tipoParametro
     */
    public ParametroEsperado(String chaveRotulo, TipoParametro tipoParametro) {
        this.tipoParametro = tipoParametro;
        this.chaveRotulo = chaveRotulo;
    }

    public String getChaveRotulo() {
        return chaveRotulo;
    }

    public TipoParametro getTipoParametro() {
        return tipoParametro;
    }

}
