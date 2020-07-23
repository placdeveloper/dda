/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servico
 * Arquivo:         MensagemDDAPagadorServicoTest.java
 * Data Criação:    Dec 28, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.SessionContext;
import javax.transaction.UserTransaction;

import org.junit.Before;
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
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.MensagemDDAPagadorServicoEJB;

/**
 * MensagemDDAPagadorServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class })
public class MensagemDDAPagadorServicoTest extends Mockito {

    @InjectMocks
    private MensagemDDAPagadorServicoEJB ejb;

    @Mock
    private ADMDelegate admDelegate;

    @Mock
    private SCIDelegate sciDelegate;

    @Mock
    private MensagemDDADelegate mensagemDDADelegate;

    @Mock
    private SessionContext session;

    @Mock
    private UserTransaction userTransaction;

    private InformacoesUsuario infoUsuario = new InformacoesUsuario(geraUsuarioBancoob());

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
     * Método responsável por void
     * 
     */
    /*
     * @Test public void incluirMensagemPagadorDDA0001() { Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirMensagemPagador(TipoMensagemDDA.DDA0001)); }
     */

    /**
     * Método responsável por void
     * 
     */
    /*
     * @Test public void incluirMensagemPagadorADDA006() { Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirMensagemPagador(TipoMensagemDDA.ADDA006)); }
     */

    /**
     * Método responsável por void
     * 
     */
    /*
     * @Test public void incluirMensagemPagadorAgregadoPassou() { Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirMensagemPagadorAgregado(null)); }
     */

    /**
     * Método responsável por
     * 
     * @param codTipoMensagemDDA
     * @return String
     * 
     */
    private String incluirMensagemPagador(String codTipoMensagemDDA) {
        return incluirMensagemPagador(codTipoMensagemDDA, null);
    }

    /**
     * Método responsável por
     * 
     * @param codTipoMensagemDDA
     * @param comumException
     * @return String
     * 
     */
    private String incluirMensagemPagador(String codTipoMensagemDDA, ComumException comumException) {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            mockingSCIDelegate();
            mockingAdmDelegate();
            mockSessionContext();
            ejb.incluirMensagemPagador(Constantes.CNPJ_AUX, Constantes.INTEGER_UM, geraListaNumCCO(), Boolean.TRUE, codTipoMensagemDDA, CanalEnum.RETAGUARDA.getId());

            verify(mensagemDDADelegate).incluir(any(IMensagemDDA.class), any(String.class), any(DateTimeDB.class), any(Integer.class), any(String.class), any(Integer.class),
                    any(Short.class));
        } catch (BancoobException e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * Método responsável por
     * 
     * @param comumException
     * @return String
     * 
     */
    private String incluirMensagemPagadorAgregado(ComumException comumException) {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            mockSessionContext();
            mockingAdmDelegate();
            mockingSCIDelegate();
            ejb.incluirMensagemPagadorAgregado(geraListaPagadorAgregado(), Constantes.CPF_AUX, Constantes.INTEGER_UM, TipoOperacaoSicoobDDAEnum.INCLUSAO,
                    CanalEnum.RETAGUARDA.getId());

            verify(mensagemDDADelegate).incluir(any(IMensagemDDA.class), any(String.class), any(DateTimeDB.class), any(Integer.class), any(String.class), any(Integer.class),
                    any(Short.class));
        } catch (BancoobException e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * Método responsável por
     * 
     * @throws IntegracaoInternaException void
     * 
     */
    private void mockingAdmDelegate() throws IntegracaoInternaException {
        when(admDelegate.obterDataMovimentoBancoob()).thenReturn(new Date());
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    private void mockingSCIDelegate() throws ComumException {
        when(sciDelegate.obterInstituicaoCache(Constantes.INTEGER_UM)).thenReturn(new InstituicaoDto(Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por void
     * 
     */
    private void mockSessionContext() {
        when(session.getUserTransaction()).thenReturn(userTransaction);
    }

    /**
     * Método responsável por
     * 
     * @return List<String>
     * 
     */
    private List<String> geraListaNumCCO() {
        List<String> lista = new ArrayList<String>();
        lista.add(Constantes.CPF_AUX);
        lista.add(Constantes.CNPJ_AUX);
        return lista;
    }

    /**
     * Método responsável por
     * 
     * @return List<PagadorAgregadoDto>
     * 
     */
    private List<PagadorAgregadoDto> geraListaPagadorAgregado() {
        List<PagadorAgregadoDto> lista = new ArrayList<PagadorAgregadoDto>();
        lista.add(new PagadorAgregadoDto(Constantes.CPF_AUX, Constantes.NOME_TESTE));
        return lista;
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
