package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.swap;

import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.MontagemConteudoRetornoGrupos;

/**
 * Montagem para conteudos do Canal 6 - SWAP - SICOOBNET CELULAR
 * 
 * @author Paulo.Stoppa
 */
public final class MontagemConteudoRetornoGruposSWAP extends MontagemConteudoRetornoGrupos {

    /**
     * Contrutor padrão que irá definir:<br>
     * - delimitadorLinha como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_LINHA_PADRAO},<br>
     * - delimitadorColuna como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_COLUNA_PADRAO},<br>
     * - adicionarContadorGrupos como {@link Boolean#FALSE} - indiceInicialGrupos como <code>0</code>
     */
    public MontagemConteudoRetornoGruposSWAP() {
        super(DELIMITADOR_COLUNA_PADRAO, DELIMITADOR_LINHA_PADRAO, false, 0);
    }

}
