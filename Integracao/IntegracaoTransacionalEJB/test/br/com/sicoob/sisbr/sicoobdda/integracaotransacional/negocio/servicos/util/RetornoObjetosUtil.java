/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-transacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util
 * Arquivo:         RetornarObjetosUtil.java
 * Data Cria��o:    Dec 8, 2017
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
 * RetornarObjetosUtil � respons�vel por
 * 
 * @author tayrone.oliveira
 */
public class RetornoObjetosUtil extends Mockito {

    // OBJECTS ===================================================

    /**
     * M�todo respons�vel por
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
     * M�todo respons�vel por
     * 
     * @return TermoAdesaoDDADto
     * 
     */
    protected TermoAdesaoDDADto retornarTermoAdesaoDDADto() {
        TermoAdesaoDDADto ret = new TermoAdesaoDDADto();
        return ret;
    }

    /**
     * M�todo respons�vel por
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
     * M�todo respons�vel por
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
