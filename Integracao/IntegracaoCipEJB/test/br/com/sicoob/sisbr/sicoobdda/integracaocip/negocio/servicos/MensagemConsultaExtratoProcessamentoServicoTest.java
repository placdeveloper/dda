/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemConsultaExtratoProcessamentoServicoTest.java
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
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemConsultaExtratoProcessamentoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0214.DDA0214R1ComplexType;

/**
 * MensagemConsultaExtratoProcessamentoServicoTest � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class MensagemConsultaExtratoProcessamentoServicoTest extends Mockito {

    @InjectMocks
    private MensagemConsultaExtratoProcessamentoServicoEJB ejb;

    @Mock
    private IntegracaoCipDao dao;

    /**
     * M�todo respons�vel por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem());
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
     * 
     */
    private String processarMensagem() throws ComumException {
        when(dao.obterMensagemDDA(Constantes.LONG_UM)).thenReturn(getMensagemDDA());
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    private MensagemDDA getMensagemDDA() {
        MensagemDDA mensagemDDA = new MensagemDDA();
        mensagemDDA.setXmlMensagem(Constantes.STRING_LETRA_S);
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
        ejb.processarRetornoMensagemDDA(geraDDA0214R1());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * M�todo respons�vel por
     * 
     * @return DDA0215R1ComplexType
     * 
     */
    private DDA0214R1ComplexType geraDDA0214R1() {
        return new DDA0214R1ComplexType();
    }

}
