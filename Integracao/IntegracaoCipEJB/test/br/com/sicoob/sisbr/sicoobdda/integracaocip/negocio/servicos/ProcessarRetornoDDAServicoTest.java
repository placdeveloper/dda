/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ProcessarRetornoDDAServicoTest.java
 * Data Criação:    May 24, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import javax.ejb.SessionContext;
import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDAInclusaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemExcluirRelacionamentoBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.ProcessarRetornoDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.XMLLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;

/**
 * ProcessarRetornoDDAServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoCipFabricaDelegates.class, InformacoesUsuario.class })
public class ProcessarRetornoDDAServicoTest extends Mockito {

    @InjectMocks
    private ProcessarRetornoDDAServicoEJB ejb;

    @SuppressWarnings("unused")
    @Mock
    private EntityManager em;

    @Mock
    private MensagemDDADao dao;

    @Mock
    private ComumDao comumDao;

    @Mock
    private SessionContext session;

    @Mock
    private MensagemDDAInclusaoDelegate mensagemDDAInclusaoDelegate;

    @Mock
    private MensagemExcluirRelacionamentoBeneficiarioDelegate mensagemExcluirRelacionamentoBeneficiarioDelegate;

    @Mock
    private IntegracaoCipFabricaDelegates fabricaCip;

    private InformacoesUsuario infoUsuario = new InformacoesUsuario(geraUsuarioBancoob());

    /**
     * Método responsável por void
     * 
     * @throws MensagemDesconhecidaException
     * 
     */
    @Before
    public void setUp() throws MensagemDesconhecidaException {
        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(fabricaCip);
        when(fabricaCip.getMensagemDDAInclusaoDelegate()).thenReturn(mensagemDDAInclusaoDelegate);
        when(fabricaCip.getMensagemDelegate(TipoMensagemDDA.DDA0503R1)).thenReturn(mensagemExcluirRelacionamentoBeneficiarioDelegate);

        PowerMockito.mockStatic(InformacoesUsuario.class);
        when(InformacoesUsuario.getInstance()).thenReturn(infoUsuario);
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarMensagemRecebidaMQRecebimentoGEN004() throws BancoobException {
        processarMensagemMQRecebimento(XMLLoader.getMQRecebimentoGEN0004());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarMensagemRecebidaMQRecebimentoDDA0503R1() throws BancoobException {
        processarMensagemMQRecebimento(XMLLoader.getMQRecebimentoDDA0503R1());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarMensagemRecebidaMQRespostaEnvio() throws BancoobException {
        processarMensagemMQRespostaEnvio(XMLLoader.getMQRespostaEnvio());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarMensagemRecebidaMQRespostaEnvioErro() throws BancoobException {
        processarMensagemMQRespostaEnvio(XMLLoader.getMQRespostaEnvioErro());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarMensagemRecebidaInvalida() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagemRecebida(Constantes.STRING_NUMERO_1));
        verify(mensagemDDAInclusaoDelegate, times(1)).incluirMensagemDDA(Constantes.STRING_NUMERO_1);
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void processarMensagemRecebidaContingenciaInvalida() throws BancoobException {
        Assert.assertEquals("integracaocip.erro.processar.mensagem.contingencia", processarMensagemRecebidaContingencia(MensagemDDALoader.geraMensagemDDA()));
        verify(mensagemDDAInclusaoDelegate, times(0)).incluirMensagemDDA(Constantes.STRING_NUMERO_0);
    }

    /**
     * Método responsável por
     * 
     * @param xmlRecebido
     * @throws BancoobException void
     * 
     */
    private void processarMensagemMQRecebimento(String xmlRecebido) throws BancoobException {
        when(dao.incluir(any(MensagemDDA.class))).thenReturn(MensagemDDALoader.geraMensagemDDA());
        when(dao.obterMensagemPorNumOperacao(anyString())).thenReturn(MensagemDDALoader.geraMensagemDDA());
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagemRecebida(xmlRecebido));
        verifySessionContext();
    }

    /**
     * Método responsável por
     * 
     * @param xmlRecebido
     * @throws BancoobException void
     * 
     */
    private void processarMensagemMQRespostaEnvio(String xmlRecebido) throws BancoobException {
        when(dao.obter(anyLong())).thenReturn(MensagemDDALoader.geraMensagemDDA());
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagemRecebida(xmlRecebido));
        verifySessionContext();
    }

    /**
     * Método responsável por
     * 
     * @param xmlRecebido
     * @return
     * @throws BancoobException String
     * 
     */
    private String processarMensagemRecebida(String xmlRecebido) throws BancoobException {
        when(comumDao.obterDataHoraDataBase()).thenReturn(new DateTimeDB());
        ejb.processarMensagemRecebida(xmlRecebido);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @return String
     * 
     */
    private String processarMensagemRecebidaContingencia(MensagemDDA mensagem) {
        try {
            ejb.processarMensagemRecebidaContingencia(mensagem);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por void
     * 
     */
    private void verifySessionContext() {
        verify(session, times(0)).setRollbackOnly();
    }

    /**
     * Método responsável por
     * 
     * @return UsuarioBancoob
     * 
     */
    private UsuarioBancoob geraUsuarioBancoob() {
        UsuarioBancoob usuario = new UsuarioBancoob();
        usuario.setCooperativa(Constantes.STRING_NUMERO_0);
        usuario.setDominio(Constantes.STRING_NUMERO_0);
        usuario.setIdInstituicao(Constantes.STRING_NUMERO_0);
        usuario.setIdUnidadeInstituicao(Constantes.STRING_NUMERO_0);
        usuario.setLogin(Constantes.STRING_NUMERO_0);
        usuario.setPac(Constantes.STRING_NUMERO_0);
        return usuario;
    }

}
