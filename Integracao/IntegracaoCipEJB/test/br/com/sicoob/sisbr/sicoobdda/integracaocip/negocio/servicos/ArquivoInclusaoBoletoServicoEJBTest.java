package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoInclusaoBoletoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.util.ServicosTestUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.GrupoADDA101TitComplexType;

/**
 * ArquivoInclusaoBoletoServicoEJBTest é responsável por 
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class ArquivoInclusaoBoletoServicoEJBTest extends ServicosTestUtil {

    @InjectMocks
    private ArquivoInclusaoBoletoServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoInclusaoBoletoServicoEJB#getEm()}.
     */
    @Test
    public final void testGetEm() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getEm"));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoInclusaoBoletoServicoEJB#obterSISARQ(java.lang.Long)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterSISARQ() throws ComumException {
        whenBoletoCipDao(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterSISARQ", Constantes.LONG_UM));
        when(boletoCipDao.listarMensagemDDABoletoLogEnvioArquivo(anyLong())).thenReturn(gerarListaMensagemDDABoletoTeste());
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "obterSISARQ", Constantes.LONG_UM));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ArquivoInclusaoBoletoServicoEJB#processarRetornoMensagemDDA(long, long, long)}.
     */
    @Test
    public final void testProcessarRetornoMensagemDDA() {
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "processarRetornoMensagemDDA", Constantes.LONG_UM, Constantes.LONG_UM, Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetMensagemDDABoletoJuros() {
        MensagemDDABoleto mensagem = retornarMensagemDDABoleto();
        mensagem.setCodTipoJuros(Constantes.INTEGER_UM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getMensagemDDABoletoJuros", mensagem, new GrupoADDA101TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetMensagagemDDABoletoMulta() {
        MensagemDDABoleto mensagem = retornarMensagemDDABoleto();
        mensagem.setCodTipoMulta(Constantes.INTEGER_UM);
        mensagem.setDataMulta(Constantes.DATE_TIME_DB_AUX);
        mensagem.setValorPercentualMulta(Constantes.CEM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getMensagagemDDABoletoMulta", mensagem, new GrupoADDA101TitComplexType()));
    }

    /**
     * Método responsável por void
     */
    @Test
    public final void testGetMensagemDDABoletoDesconto() {
        MensagemDDABoleto mensagem = retornarMensagemDDABoleto();
        mensagem.setCodTipoDesconto1(Constantes.STRING_NUMERO_1);
        mensagem.setDataDesconto1(Constantes.DATE_TIME_DB_AUX);
        mensagem.setValorPercentualDesconto1(Constantes.CEM);
        mensagem.setCodTipoDesconto2(Constantes.STRING_NUMERO_1);
        mensagem.setDataDesconto2(Constantes.DATE_TIME_DB_AUX);
        mensagem.setValorPercentualDesconto2(Constantes.CEM);
        mensagem.setDataDesconto3(Constantes.DATE_TIME_DB_AUX);
        mensagem.setValorPercentualDesconto3(Constantes.CEM);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getMensagemDDABoletoDesconto", mensagem, new GrupoADDA101TitComplexType()));
        mensagem.setCodTipoDesconto3(Constantes.STRING_NUMERO_1);
        assertEquals(Constantes.TESTE_SUCESSO, genericTests(ejb, "getMensagemDDABoletoDesconto", mensagem, new GrupoADDA101TitComplexType()));
    }

    /**
     * Método responsável por
     * 
     * @return List<MensagemDDABoleto>
     */
    private List<MensagemDDABoleto> gerarListaMensagemDDABoletoTeste() {
        List<MensagemDDABoleto> ret = new ArrayList<MensagemDDABoleto>();
        while (ret.size() <= Constantes.QTD_ARQUIVOS_INSERIDOS_BOLETO_POR_VEZ) {
            ret.add(retornarMensagemDDABoleto());
        }
        return ret;
    }

}
