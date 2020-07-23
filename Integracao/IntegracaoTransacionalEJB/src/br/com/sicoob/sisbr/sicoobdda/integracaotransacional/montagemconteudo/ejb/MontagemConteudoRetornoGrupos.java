package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.montagemconteudo.ejb;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;

/**
 * Classe de montagem de retorno para o SRTB padrão para o CapRemRenovacao
 * 
 * @author Paulo.Stoppa
 */
public abstract class MontagemConteudoRetornoGrupos {

    /** Valor padrão para os campos nulos. */
    private static final String VALOR_PADRAO_CAMPOS_NULOS = "";

    /** Delimitador de coluna padrão. */
    protected static final String DELIMITADOR_COLUNA_PADRAO = "\\t";

    /** Delimitador de linha padrão. */
    protected static final String DELIMITADOR_LINHA_PADRAO = "\\n";

    /** Caracter delimitador de coluna. */
    private final String delimitadorColuna;

    /** Caracter delimitador de coluna. */
    private final String delimitadorLinha;

    /** Indica se deve adicionar contador de grupos. */
    private final boolean adicionarContadorGrupos;

    /** Indice inicial grupos. */
    private final int indiceInicialContadorGrupos;

    /**
     * Contrutor que irá definir o delimitador de linha, delimitador de coluna de coluna, indicar se irá adicionar contador de grupo e o indice Inicial do
     * contador de grupos
     * 
     * @param delimitadorColuna
     * @param delimitadorLinha
     * @param adicionarContadorGrupos
     * @param indiceInicialContadorGrupos
     */
    protected MontagemConteudoRetornoGrupos(String delimitadorColuna, String delimitadorLinha, boolean adicionarContadorGrupos, int indiceInicialContadorGrupos) {
        this.delimitadorColuna = delimitadorColuna;
        this.delimitadorLinha = delimitadorLinha;
        this.adicionarContadorGrupos = adicionarContadorGrupos;
        this.indiceInicialContadorGrupos = indiceInicialContadorGrupos;
    }

    /**
     * Método que recebe os grupos e retorna a String formatada para o canal
     * 
     * @param grupos
     * @return
     */
    public final String montarConteudoRetorno(final Grupos grupos) {
        StringBuilder builder = new StringBuilder();
        int contadorGrupos = indiceInicialContadorGrupos;
        for (Grupo grupo : grupos) {
            for (Linha linha : grupo.getLinhas()) {
                if (adicionarContadorGrupos) {
                    builder.append(contadorGrupos);
                    builder.append(delimitadorColuna);
                }
                for (String valor : linha.getValores()) {
                    if (!ObjectUtil.isEmpty(valor)) {
                        builder.append(valor.replaceAll(delimitadorColuna, "").replaceAll(delimitadorLinha, ""));
                    } else {
                        builder.append(VALOR_PADRAO_CAMPOS_NULOS);
                    }

                    builder.append(delimitadorColuna);
                }
                builder.append(delimitadorLinha);
            }
            contadorGrupos++;
        }
        return builder.toString();
    }

}
