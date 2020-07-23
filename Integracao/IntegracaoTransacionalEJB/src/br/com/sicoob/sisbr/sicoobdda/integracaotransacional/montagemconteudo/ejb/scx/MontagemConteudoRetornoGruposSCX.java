package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.scx;

import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb.MontagemConteudoRetornoGrupos;

/**
 * Montagem para conteudos do Canal 2 - SCX - SICOOB CAIXA
 * 
 * @author Rodrigo.Neri
 */
public final class MontagemConteudoRetornoGruposSCX extends MontagemConteudoRetornoGrupos {

    /**
     * Contrutor padrão que irá definir:<br>
     * - delimitadorLinha como {@link MontagemConteudoRetornoGruposSCX#DELIMITADOR_COLUNA_PADRAO},<br>
     * - delimitadorColuna como {@link MontagemConteudoRetornoGruposSCX#DELIMITADOR_LINHA_PADRAO},<br>
     * - adicionarContadorGrupos como {@link Boolean#TRUE} - indiceInicialGrupos como <code>1</code>
     */
    public MontagemConteudoRetornoGruposSCX() {
        super(DELIMITADOR_COLUNA_PADRAO, DELIMITADOR_LINHA_PADRAO, true, 1);
    }

}
