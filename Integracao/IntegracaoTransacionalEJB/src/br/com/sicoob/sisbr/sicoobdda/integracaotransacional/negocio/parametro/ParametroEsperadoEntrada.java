package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

import br.com.bancoob.srtb.dto.TipoParametro;

/**
 * Par�metros de Entrada para as transa��es
 * 
 * @author Paulo.Stoppa
 */
public class ParametroEsperadoEntrada extends ParametroEsperado {

    /**
     * Construtor padr�o que recebe chave do r�tulo
     * 
     * @param chaveRotulo
     */
    public ParametroEsperadoEntrada(String chaveRotulo) {
        super(chaveRotulo, TipoParametro.ENTRADA);
    }

}
