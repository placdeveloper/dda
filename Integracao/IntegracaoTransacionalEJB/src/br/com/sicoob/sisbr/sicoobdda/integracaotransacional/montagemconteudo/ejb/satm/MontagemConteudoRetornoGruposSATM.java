package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.satm;

import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.MontagemConteudoRetornoGrupos;

/**
 * Montagem para conteudos do Canal 3 - SATM - SICOOB ATM
 * 
 * @author Paulo.Stoppa
 */
public final class MontagemConteudoRetornoGruposSATM extends MontagemConteudoRetornoGrupos {

    /** Delimitador de coluna padrão. */
    private static final String DELIMITADOR_COLUNA_ATM = ";";

    /** Delimitador de linha padrão. */
    private static final String DELIMITADOR_LINHA_ATM = "\n";

    /**
     * Contrutor padrão que irá definir:<br>
     * - delimitadorColuna como {@link MontagemConteudoRetornoGruposSATM#DELIMITADOR_COLUNA_ATM},<br>
     * - delimitadorLinha como {@link MontagemConteudoRetornoGruposSATM#DELIMITADOR_LINHA_ATM},<br>
     * - adicionarContadorGrupos como {@link Boolean#FALSE} - indiceInicialGrupos como <code>0</code>
     */
    public MontagemConteudoRetornoGruposSATM() {
        super(DELIMITADOR_COLUNA_ATM, DELIMITADOR_LINHA_ATM, false, 0);
    }

}
