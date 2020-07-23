/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemConsultaEventoTarifavelServicoTest.java
 * Data Cria��o:    Oct 21, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemConsultaEventoTarifavelServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0200.DDA0200R1ComplexType;

/**
 * MensagemConsultaEventoTarifavelServicoTest � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class MensagemConsultaEventoTarifavelServicoTest extends Mockito {

    @InjectMocks
    private MensagemConsultaEventoTarifavelServicoEJB ejb;

    @Mock
    private IntegracaoCipDao dao;

    /**
     * M�todo respons�vel por testar
     * 
     * @throws ComumException
     */
    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem(true));
    }

    /**
     * M�todo respons�vel por testar
     * 
     * @throws ComumException
     */
    @Test
    public void processarMensagemFalhou() throws ComumException {
        Assert.assertEquals("integracaocip.erro.xml.mensagem.invalido", processarMensagem(false));
    }

    /**
     * M�todo respons�vel por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemDDAPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA());
    }

    /**
     * M�todo respons�vel por
     * 
     * @return String
     * @throws ComumException
     */
    private String processarMensagem(boolean preencherXML) throws ComumException {
        when(dao.obterMensagemDDA(Constantes.LONG_UM)).thenReturn(getMensagemDDA(preencherXML));

        try {
            ejb.processarMensagem(Constantes.LONG_UM);
        } catch (Exception e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * M�todo respons�vel por
     * 
     * @param preencherXML
     * @return
     */
    private MensagemDDA getMensagemDDA(boolean preencherXML) {
        MensagemDDA mensagemDDA = new MensagemDDA();
        mensagemDDA.setId(Constantes.LONG_UM);
        mensagemDDA.setDataMovimento(Constantes.DATE_TIME_DB_AUX);
        if (preencherXML) {
            mensagemDDA.setXmlMensagem("xmlMensagem");
        }
        return mensagemDDA;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemDDA() throws ComumException {
        ejb.processarRetornoMensagemDDA(geraDDA0200R1());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return DDA0200R1ComplexType
     * 
     */
    private DDA0200R1ComplexType geraDDA0200R1() {
        return new DDA0200R1ComplexType();
    }

}
