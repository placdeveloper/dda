/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemExcluirTerceiroAutorizadoServicoTest.java
 * Data Criação:    May 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.math.BigInteger;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemExcluirTerceiroAutorizadoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDATerceiroAutLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA122.GrupoADDA122RR2TitComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122R2ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemExcluirTerceiroAutorizadoServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class MensagemExcluirTerceiroAutorizadoServicoTest extends Mockito {

    @InjectMocks
    private MensagemExcluirTerceiroAutorizadoServicoEJB ejb;

    @Mock
    private BoletoCipDao dao;

    @Mock
    private EntityManager em;

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem());
        verify(dao, times(1)).obterMensagemDDATerceiroAutorizadoAtualizaReferencias(Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoDDA0122R1() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetorno(geraDDA0122R1()));
        verify(dao, times(1)).obterBoletoDDATerceiroAutorizado(Constantes.LONG_UM, Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoDDA0122R2() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetorno(geraDDA0122R2()));
        verify(dao, times(1)).obterBoletoDDATerceiroAutorizado(Constantes.LONG_UM, Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoADDA122RR2() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetorno(geraADDA0122RR2()));
        verify(dao, times(1)).obterBoletoDDATerceiroAutorizado(Constantes.LONG_UM, Constantes.LONG_UM);
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String processarMensagem() throws ComumException {
        when(dao.obterMensagemDDATerceiroAutorizadoAtualizaReferencias(Constantes.LONG_UM)).thenReturn(MensagemDDATerceiroAutLoader.gerar());
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param retornoDDA
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetorno(ConteudoMsgRecebida retornoDDA) throws ComumException {
        when(dao.obterBoletoDDATerceiroAutorizado(Constantes.LONG_UM, Constantes.LONG_UM)).thenReturn(new BoletoDDATerceiroAut());
        ejb.processarRetornoMensagemDDA(retornoDDA);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0122R1ComplexType
     * 
     */
    private DDA0122R1ComplexType geraDDA0122R1() {
        DDA0122R1ComplexType msg = new DDA0122R1ComplexType();
        msg.setCodMsg(TipoMensagemDDA.DDA0122R1);
        msg.setNumCtrlPart(Constantes.STRING_NUMERO_1);
        msg.setNumIdentcTerc(BigInteger.ONE);
        msg.setNumIdentcTit(BigInteger.ONE);
        msg.setNumRefAtlCadTerc(BigInteger.ONE);
        return msg;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0122R2ComplexType
     * 
     */
    private DDA0122R2ComplexType geraDDA0122R2() {
        DDA0122R2ComplexType msg = new DDA0122R2ComplexType();
        msg.setCodMsg(TipoMensagemDDA.DDA0122R2);
        msg.setNumIdentcTerc(BigInteger.ONE);
        msg.setNumIdentcTit(BigInteger.ONE);
        msg.setNumRefAtlCadTerc(BigInteger.ONE);
        return msg;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA122RR2TitComplexType
     * 
     */
    private GrupoADDA122RR2TitComplexType geraADDA0122RR2() {
        GrupoADDA122RR2TitComplexType msg = new GrupoADDA122RR2TitComplexType();
        msg.setNumIdentcTerc(BigInteger.ONE);
        msg.setNumIdentcTit(BigInteger.ONE);
        msg.setNumRefAtlCadTerc(BigInteger.ONE);
        return msg;
    }
}
