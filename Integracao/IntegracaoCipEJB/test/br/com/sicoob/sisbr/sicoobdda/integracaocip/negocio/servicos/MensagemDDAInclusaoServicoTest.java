/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         MensagemDDAInclusaoServicoTest.java
 * Data Criação:    Outubro 06, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import javax.persistence.EntityManager;

import org.junit.Assert;
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
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemDDAInclusaoServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;

/**
 * MensagemDDAInclusaoServicoTest é responsável por Testar o MensagemDDAInclusaoServicoEJB
 * 
 * @author Francisco.Marcio
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class })
public class MensagemDDAInclusaoServicoTest extends Mockito {

    @InjectMocks
    private MensagemDDAInclusaoServicoEJB ejb;

    @Mock
    private MensagemDDADao dao;

    @Mock
    private EntityManager em;

    private InformacoesUsuario infoUsuario = new InformacoesUsuario(geraUsuarioBancoob());

    @Before
    public void setUp() throws MensagemDesconhecidaException {
        PowerMockito.mockStatic(InformacoesUsuario.class);
        when(InformacoesUsuario.getInstance()).thenReturn(infoUsuario);
    }

    /**
     * Método responsável por testar a inclusão quando sucesso
     * 
     * @throws BancoobException
     * 
     * @throws OperacionalNoResultException
     * 
     */
    @Test
    public void incluirMensagemDDAcomSucesso() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirMensagemDDA());
    }

    /**
     * Método responsável por testar a inclusão quando lançado uma execption
     * 
     * @throws BancoobException
     */
    @Test
    public void obterErroAoIncluirMensagemDDAException() throws BancoobException {
        when(dao.incluir(any(MensagemDDA.class))).thenThrow(new BancoobException(Constantes.TESTE_FALHA));
        Assert.assertEquals(Constantes.TESTE_FALHA, incluirMensagemDDA());
    }

    private String incluirMensagemDDA() {
        try {
            when(em.find(TipoMensagemDDA.class, TipoMensagemDDA.ERRO001)).thenReturn(new TipoMensagemDDA());
            ejb.incluirMensagemDDA("xmlRecebido");
            verify(em, times(1)).find(TipoMensagemDDA.class, TipoMensagemDDA.ERRO001);
        } catch (BancoobException e) {
            return e.getMessage();
        }
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
