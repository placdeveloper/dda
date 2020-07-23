/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-transacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util
 * Arquivo:         RetornarObjetosUtil.java
 * Data Criação:    Dec 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.TipoParametro;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoAdesaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * RetornarObjetosUtil é responsável por
 * 
 * @author tayrone.oliveira
 */
public class RetornoObjetosUtil extends Mockito {

    // OBJECTS ===================================================

    /**
     * Método responsável por
     * 
     * @return Parametro
     * 
     */
    protected Parametro retornarParametro() {
        Parametro ret = new Parametro();
        ret.setNome(Constantes.NOME_BANCO);
        ret.setTipoJdbc(Constantes.INTEGER_UM);
        ret.setTipoParametro(TipoParametro.ENTRADA);
        ret.setValor(Constantes.STRING_NUMERO_1);
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return TermoAdesaoDDADto
     * 
     */
    protected TermoAdesaoDDADto retornarTermoAdesaoDDADto() {
        TermoAdesaoDDADto ret = new TermoAdesaoDDADto();
        return ret;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorEletronicoDDADto
     * 
     */
    protected PagadorEletronicoDDADto retornarPagadorEletronicoDDADto() {
        PagadorEletronicoDDADto ret = new PagadorEletronicoDDADto();
        return ret;
    }

    // LISTS =====================================================
    /**
     * Método responsável por
     * 
     * @return List<PagadorEletronicoDDADto>
     * 
     */
    protected List<PagadorEletronicoDDADto> listarPagadorEletronicoDDADto() {
        List<PagadorEletronicoDDADto> ret = new ArrayList<PagadorEletronicoDDADto>();
        ret.add(retornarPagadorEletronicoDDADto());
        return ret;
    }
}
