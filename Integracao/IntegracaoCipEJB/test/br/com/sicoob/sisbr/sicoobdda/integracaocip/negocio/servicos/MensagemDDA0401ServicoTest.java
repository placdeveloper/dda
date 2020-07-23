/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemDDA0401ServicoTest.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
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
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemDDA0401ServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;

/**
 * MensagemDDA0401ServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class })
public class MensagemDDA0401ServicoTest extends Mockito {

    @InjectMocks
    private MensagemDDA0401ServicoEJB ejb;

    @Mock
    private ParametroDao parametroDAO;

    @Mock
    private MensagemDDADelegate mensagemDDADelegate;

    private final InformacoesUsuario infoUsuario = new InformacoesUsuario(geraUsuarioBancoob());

    /**
     * Método responsável por void
     * 
     * @throws MensagemDesconhecidaException
     * 
     */
    @Before
    public void setUp() throws MensagemDesconhecidaException {
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
    @Ignore
    public void incluirPassou() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluir());
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws BancoobException String
     * 
     */
    private String incluir() throws BancoobException {
        when(parametroDAO.obterValor(Constantes.LONG_UM, null)).thenReturn("1");
        ejb.incluir(Constantes.STRING_NUMERO_1, new DateTimeDB(), 0);
        verify(mensagemDDADelegate, times(1)).incluir(any(ComplexType.class), any(DateTimeDB.class), any(Integer.class), any(String.class), any(Integer.class), any(Short.class));

        return Constantes.TESTE_SUCESSO;
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
