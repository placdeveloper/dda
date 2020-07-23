package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.sofc;

import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.MontagemConteudoRetornoGrupos;

/**
 * Montagem para conteudos do Canal 5 - SOFC - SICOOBNET EMPRESARIAL
 * 
 * @author Paulo.Stoppa
 */
public final class MontagemConteudoRetornoGruposSOFC extends MontagemConteudoRetornoGrupos {

    /**
     * Contrutor padrão que irá definir:<br>
     * - delimitadorLinha como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_LINHA_PADRAO},<br>
     * - delimitadorColuna como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_COLUNA_PADRAO},<br>
     * - adicionarContadorGrupos como {@link Boolean#TRUE} - indiceInicialGrupos como <code>1</code>
     */
    public MontagemConteudoRetornoGruposSOFC() {
        super(DELIMITADOR_COLUNA_PADRAO, DELIMITADOR_LINHA_PADRAO, true, 1);
    }

    /**
     * Contrutor que irá definir:<br>
     * - delimitadorLinha como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_LINHA_PADRAO},<br>
     * - delimitadorColuna como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_COLUNA_PADRAO},<br>
     * - adicionarContadorGrupos como {@link Boolean#TRUE}
     * 
     * @param indiceInicialGrupos
     * 
     */
    public MontagemConteudoRetornoGruposSOFC(int indiceInicialGrupos) {
        super(DELIMITADOR_COLUNA_PADRAO, DELIMITADOR_LINHA_PADRAO, true, indiceInicialGrupos);
    }

}
