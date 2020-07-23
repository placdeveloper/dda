package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.jdbc;

import br.com.bancoob.srtb.montagemconteudo.jdbc.MontagemConteudoRetornoStoredProcedureDelimitadoATM;

/**
 * Montagem para a trasa��o de Extrato de Aplica��o para o canal SATM. Aonde somente os delimitadores ser�o alterados, mantendo o comportamento da classe pai.
 * 
 * @author Paulo.Stoppa
 */
public final class MontagemConteudoRetornoExtratoAplicacaoATM extends MontagemConteudoRetornoStoredProcedureDelimitadoATM {

    private static final long serialVersionUID = -2499750626318274787L;

    /**
     * Altera��o do delimitar de coluna
     */
    public String getDelimitadorColuna() {
        return DELIMITADOR_COLUNA_PADRAO;
    }

    /**
     * Altera��o no delimitador de Linha
     */
    public String getDelimitadorLinha() {
        return DELIMITADOR_LINHA_PADRAO;
    }

}