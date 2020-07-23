package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.scos;

import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.MontagemConteudoRetornoGrupos;

/**
 * Montagem para conteudos do Canal 12 - SCOS - CORRESPONDENTE SICOOB
 * 
 * @author Rodrigo.Neri
 */
public final class MontagemConteudoRetornoGruposSCOS extends MontagemConteudoRetornoGrupos {

    /**
     * Contrutor padrão que irá definir:<br>
     * - delimitadorLinha como {@link MontagemConteudoRetornoGruposSCOS#DELIMITADOR_LINHA_PADRAO},<br>
     * - delimitadorColuna como {@link MontagemConteudoRetornoGruposSCOS#DELIMITADOR_COLUNA_PADRAO},<br>
     * - adicionarContadorGrupos como {@link Boolean#FALSE} - indiceInicialGrupos como <code>0</code>
     */
    public MontagemConteudoRetornoGruposSCOS() {
        super(DELIMITADOR_COLUNA_PADRAO, DELIMITADOR_LINHA_PADRAO, true, 1);
    }

}
