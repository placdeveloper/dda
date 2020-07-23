package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoManutencaoPagadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA003.GrupoADDA003PagdrDDAComplexType;

/**
 * ArquivoManutencaoPagadorServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ArquivoManutencaoPagadorServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoManutencaoPagadorServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoManutencaoPagadorServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoManutencaoPagadorServicoEJB#processarMensagem(java.lang.Long)}.
     */
    @Test
    public final void testProcessarMensagem() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarMensagem", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoManutencaoPagadorServicoEJB#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDAConteudoMsgRecebida() throws ComumException {
        GrupoADDA003PagdrDDAComplexType grupo = retornarGrupoADDA003PagdrDDAComplexType();
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", grupo));
        whenPagadorCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", grupo));
        grupo.setIndrManutPagdr(TipoManutencaoEnum.EXCLUSAO.getCodIndicador());
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", grupo));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoManutencaoPagadorServicoEJB#processarRetornoMensagemDDA(long, long, long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testProcessarRetornoMensagemDDALongLongLong() throws ComumException {
        whenPagadorCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
        whenPagadorCipDao(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, genericTests(ejb, "processarRetornoMensagemDDA", Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
    }

}
