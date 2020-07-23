package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.sced;

import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.MontagemConteudoRetornoGrupos;

/**
 * Montagem para conteudos do Canal 9 - SCED - CEDENTE
 * 
 * @author Paulo.Stoppa
 */
public final class MontagemConteudoRetornoGruposSCED extends MontagemConteudoRetornoGrupos {

    /** Delimitador de coluna padrão. */
    private static final String DELIMITADOR_LINHA_CED = ";";

    /**
     * Contrutor padrão que irá definir:<br>
     * - delimitadorLinha como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_LINHA_PADRAO},<br>
     * - delimitadorColuna como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_COLUNA_PADRAO},<br>
     * - adicionarContadorGrupos como {@link Boolean#FALSE} - indiceInicialGrupos como <code>0</code>
     */
    public MontagemConteudoRetornoGruposSCED() {
        super(DELIMITADOR_COLUNA_PADRAO, DELIMITADOR_LINHA_CED, false, 0);
    }

}
