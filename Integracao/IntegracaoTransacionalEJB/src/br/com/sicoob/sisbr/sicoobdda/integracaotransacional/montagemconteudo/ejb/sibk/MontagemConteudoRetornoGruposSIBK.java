package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.sibk;

import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.MontagemConteudoRetornoGrupos;

/**
 * Montagem para conteudos do Canal 4 - SIBK - SICOOBNET PESSOAL
 * 
 * @author Paulo.Stoppa
 */
public final class MontagemConteudoRetornoGruposSIBK extends MontagemConteudoRetornoGrupos {

    /**
     * Contrutor padrão que irá definir:<br>
     * - delimitadorLinha como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_LINHA_PADRAO},<br>
     * - delimitadorColuna como {@link MontagemConteudoRetornoGrupos#DELIMITADOR_COLUNA_PADRAO},<br>
     * - adicionarContadorGrupos como {@link Boolean#FALSE} - indiceInicialGrupos como <code>0</code>
     */
    public MontagemConteudoRetornoGruposSIBK() {
        super(DELIMITADOR_COLUNA_PADRAO, DELIMITADOR_LINHA_PADRAO, false, 0);
    }

}
