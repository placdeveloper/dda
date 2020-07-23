package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

import br.com.bancoob.srtb.dto.TipoParametro;

/**
 * Par�metros de Sa�da para as transa��es
 * 
 * @author Paulo.Stoppa
 */
public final class ParametroEsperadoSaida extends ParametroEsperado implements ValorDefault {

    private final Object valorPadrao;
    private final int tipoJdbc;

    /**
     * Construtor padr�o que recebe chave do r�tulo
     * 
     * @param chaveRotulo
     */
    public ParametroEsperadoSaida(String chaveRotulo, Object valorPadrao, int tipoJdbc) {
        super(chaveRotulo, TipoParametro.SAIDA);
        this.valorPadrao = valorPadrao;
        this.tipoJdbc = tipoJdbc;
    }

    public Object getValorPadrao() {
        return valorPadrao;
    }

    public int getTipoJdbc() {
        return tipoJdbc;
    }

}
