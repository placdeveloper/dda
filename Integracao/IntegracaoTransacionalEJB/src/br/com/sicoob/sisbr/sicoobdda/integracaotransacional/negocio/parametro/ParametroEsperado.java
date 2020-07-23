package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

import br.com.bancoob.srtb.dto.TipoParametro;

/**
 * Parâmetros para as transações
 * 
 * @author Paulo.Stoppa
 */
public abstract class ParametroEsperado {

    /**
     * Rótulo do parâmetro
     */
    private final String chaveRotulo;

    /**
     * Tipo de parâmetro
     */
    private final TipoParametro tipoParametro;

    /**
     * Construtor que recebe a chave do rótulo e o tipo de parâmetro
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
