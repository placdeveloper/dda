/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-transacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos
 * Arquivo:         IncluirAdesaoSacadoDDAServicoEJBTest.java
 * Data Criação:    Dec 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IncluirAdesaoSacadoDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;

/**
 * IncluirAdesaoSacadoDDAServicoEJBTest é responsável por
 * 
 * @author tayrone.oliveira
 */
@RunWith(PowerMockRunner.class)
public class IncluirAdesaoSacadoDDAServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private IncluirAdesaoSacadoDDAServicoEJB ejb;

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IncluirAdesaoSacadoDDAServicoEJB#executar(br.com.bancoob.srtb.dto.Mensagem)}
     * .
     * 
     * @throws Exception
     */
    @Ignore
    @Test
    public final void testExecutar() throws Exception {
        whenMensagem(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "executar", mensagem)));
        whenPagadorEletronicoDDADelegate(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "executar", mensagem)));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testValidacaoAdesaoDDA() {
        assertEquals(Constantes.TESTE_FALHA, validacaoAdesaoDDA(RotulosSicoobDDA.NUMERO_CONTA));
        assertEquals(Constantes.TESTE_FALHA, validacaoAdesaoDDA(Rotulos.CODIGO_CANAL));
        assertEquals(Constantes.TESTE_FALHA, validacaoAdesaoDDA(RotulosSicoobDDA.TIPO_RETORNO));
        assertEquals(Constantes.TESTE_FALHA, validacaoAdesaoDDA(Rotulos.COOPERATIVA_CARTAO));
        assertEquals(Constantes.TESTE_FALHA, validacaoAdesaoDDA(Rotulos.COOPERATIVA));
    }

    // AUXILIARS
    private String validacaoAdesaoDDA(String parametro) {
        String retorno = null;
        try {
            whenMensagem(Boolean.TRUE);
            when(mensagem.recuperarParametro(parametro)).thenReturn(null);
            Whitebox.invokeMethod(ejb, "validacaoAdesaoDDA", mensagem);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoOuFalha(retorno);
    }

}
