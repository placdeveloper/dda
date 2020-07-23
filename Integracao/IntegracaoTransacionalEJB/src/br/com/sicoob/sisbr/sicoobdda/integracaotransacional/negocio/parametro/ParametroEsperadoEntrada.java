package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

import br.com.bancoob.srtb.dto.TipoParametro;

/**
 * Parâmetros de Entrada para as transações
 * 
 * @author Paulo.Stoppa
 */
public class ParametroEsperadoEntrada extends ParametroEsperado {

    /**
     * Construtor padrão que recebe chave do rótulo
     * 
     * @param chaveRotulo
     */
    public ParametroEsperadoEntrada(String chaveRotulo) {
        super(chaveRotulo, TipoParametro.ENTRADA);
    }

}
