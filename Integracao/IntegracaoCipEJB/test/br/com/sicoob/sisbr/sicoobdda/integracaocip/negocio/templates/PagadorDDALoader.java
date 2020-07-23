/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates
 * Arquivo:         PagadorDDALoader.java
 * Data Criação:    Oct 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates;

import java.math.BigInteger;

import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * PagadorDDALoader é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class PagadorDDALoader {

    /**
     * 
     */
    private PagadorDDALoader() {

    }

    /**
     * Método responsável por
     * 
     * @return PagadorDDA
     * 
     */
    public static PagadorDDA geraPagadorDDA() {
        PagadorDDA pagador = new PagadorDDA(Constantes.CNPJ_AUX, TipoPessoaEnum.PJ.getCodDominioCip(), Constantes.BIG_INTEGER_1_AUX, Constantes.BIG_INTEGER_1_AUX,
                Constantes.BIG_INTEGER_1_AUX, BigInteger.ONE);
        pagador.setId(Constantes.LONG_UM);
        return pagador;
    }

}
