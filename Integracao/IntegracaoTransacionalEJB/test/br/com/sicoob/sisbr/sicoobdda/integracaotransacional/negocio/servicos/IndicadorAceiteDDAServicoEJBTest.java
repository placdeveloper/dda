/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-transacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos
 * Arquivo:         IndicadorAceiteDDAServicoEJBTest.java
 * Data Criação:    Dec 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.bancoob.conversao.constantes.Rotulos;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IndicadorAceiteDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.util.RotulosSicoobDDA;

/**
 * IndicadorAceiteDDAServicoEJBTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class IndicadorAceiteDDAServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private IndicadorAceiteDDAServicoEJB ejb;

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaotransacional.negocio.servicos.ejb.IndicadorAceiteDDAServicoEJB#executar(br.com.bancoob.srtb.dto.Mensagem)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testExecutar() throws Exception {
        whenMensagem(Boolean.TRUE);
        when(pagadorEletronicoDDADelegate.indicadorAceiteDDA(any(PagadorEletronicoDDADto.class))).thenReturn(null);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "executar", mensagem)));
        whenPagadorEletronicoDDADelegate(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "executar", mensagem)));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testValidacaoIndicadorAceite() {
        assertEquals(Constantes.TESTE_FALHA, validacaoIndicadorAceite(RotulosSicoobDDA.NUMERO_CONTA));
        assertEquals(Constantes.TESTE_FALHA, validacaoIndicadorAceite(Rotulos.CODIGO_CANAL));
        assertEquals(Constantes.TESTE_FALHA, validacaoIndicadorAceite(Rotulos.COOPERATIVA_CARTAO));
        assertEquals(Constantes.TESTE_FALHA, validacaoIndicadorAceite(Rotulos.COOPERATIVA));
        assertEquals(Constantes.TESTE_FALHA, validacaoIndicadorAceite(Rotulos.NUMERO_PAC));
        assertEquals(Constantes.TESTE_FALHA, validacaoIndicadorAceite(RotulosSicoobDDA.NUMERO_IDENTIFICACAO_DDA));
        assertEquals(Constantes.TESTE_FALHA, validacaoIndicadorAceite(RotulosSicoobDDA.BOL_ACEITE));
    }

    // AUXILIARS
    /**
     * Método responsável por
     * 
     * @param valorParametro
     * @return String
     * 
     */
    private String validacaoIndicadorAceite(String valorParametro) {
        String retorno = null;
        try {
            whenMensagem(Boolean.TRUE);
            when(mensagem.recuperarParametro(valorParametro)).thenReturn(null);
            Whitebox.invokeMethod(ejb, "validacaoIndicadorAceite", mensagem);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornarSucessoOuFalha(retorno);
    }
}
