/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-transacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util
 * Arquivo:         ServicosTestUtil.java
 * Data Criação:    Dec 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util;

import org.junit.Before;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.reflect.Whitebox;

import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util.RetornoObjetosUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.PagadorEletronicoDDADelegate;
import junit.framework.Assert;

/**
 * ServicosTestUtil é responsável por
 * 
 * @author tayrone.oliveira
 */
@PrepareForTest({ OperacionalFabricaDelegates.class })
public class ServicosTestUtil extends RetornoObjetosUtil {

    // PRIVATES
    @Mock
    private OperacionalFabricaDelegates operacionalFabricaDelegates;

    // PROTECTEDS
    @Mock
    protected PagadorEletronicoDDADelegate pagadorEletronicoDDADelegate;
    @Mock
    protected Mensagem mensagem;

    // FINALS
    protected static final String TESTE_EM = "getEm";
    protected static final String TESTE_DAO = "getDAO";

    /**
     * Método responsável por void
     * 
     */
    @Before
    public void init() {
        PowerMockito.mockStatic(OperacionalFabricaDelegates.class);
        PowerMockito.when(OperacionalFabricaDelegates.getInstance()).thenReturn(operacionalFabricaDelegates);
        PowerMockito.when(operacionalFabricaDelegates.getPagadorEletronicoDDADelegate()).thenReturn(pagadorEletronicoDDADelegate);
    }

    // AUXILIARS
    /**
     * Método responsável por
     * 
     * @param expected
     * @param actual void
     * 
     */
    protected void assertEquals(Object expected, Object actual) {
        Assert.assertEquals(expected, actual);
    }

    /**
     * Método responsável por
     * 
     * @param method
     * @return String
     * 
     */
    protected String retornarString(Object method) {
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param ejb
     * @param method
     * @return
     * @throws Exception String
     * 
     */
    protected String testaEmAndDao(Object ejb, String method) throws Exception {
        return retornarString(Whitebox.invokeMethod(ejb, method));
    }

    /**
     * Método responsável por
     * 
     * @param retorno
     * @return String
     * 
     */
    protected String retornarSucessoOuFalha(String retorno) {
        return ObjectUtil.isEmpty(retorno) ? Constantes.TESTE_SUCESSO : retorno;
    }

    // WHENS
    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    protected void whenMensagem(Boolean success) {
        Parametro parametro = retornarParametro();
        parametro.setValor(Boolean.TRUE);
        when(mensagem.recuperarParametro(anyString())).thenReturn(retornarParametro());
        when(mensagem.recuperarParametro(RotulosSicoobDDA.BOL_OUVIDORIA)).thenReturn(parametro);
        when(mensagem.recuperarParametro(RotulosSicoobDDA.BOL_CONTROLA_TRANS)).thenReturn(parametro);
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws ComumNegocioException void
     * @throws ComumException
     * 
     */
    protected void whenPagadorEletronicoDDADelegate(Boolean success) throws ComumNegocioException, ComumException {
        when(pagadorEletronicoDDADelegate.indicadorAceiteDDA(any(PagadorEletronicoDDADto.class))).thenReturn(Boolean.TRUE);
    }

}
