package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.parametro;

import br.com.bancoob.srtb.dto.TipoParametro;

/**
 * Parâmetros de Saída para as transações
 * 
 * @author Paulo.Stoppa
 */
public final class ParametroEsperadoSaida extends ParametroEsperado implements ValorDefault {

    private final Object valorPadrao;
    private final int tipoJdbc;

    /**
     * Construtor padrão que recebe chave do rótulo
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
