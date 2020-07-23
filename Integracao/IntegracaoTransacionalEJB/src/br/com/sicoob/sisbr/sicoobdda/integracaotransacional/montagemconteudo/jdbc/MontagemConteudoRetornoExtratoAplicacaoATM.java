package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.jdbc;

import br.com.bancoob.srtb.montagemconteudo.jdbc.MontagemConteudoRetornoStoredProcedureDelimitadoATM;

/**
 * Montagem para a trasação de Extrato de Aplicação para o canal SATM. Aonde somente os delimitadores serão alterados, mantendo o comportamento da classe pai.
 * 
 * @author Paulo.Stoppa
 */
public final class MontagemConteudoRetornoExtratoAplicacaoATM extends MontagemConteudoRetornoStoredProcedureDelimitadoATM {

    private static final long serialVersionUID = -2499750626318274787L;

    /**
     * Alteração do delimitar de coluna
     */
    public String getDelimitadorColuna() {
        return DELIMITADOR_COLUNA_PADRAO;
    }

    /**
     * Alteração no delimitador de Linha
     */
    public String getDelimitadorLinha() {
        return DELIMITADOR_LINHA_PADRAO;
    }

}