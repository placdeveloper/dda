/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemSerie0200ServicoTest.java
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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroSerie0200Dto;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemSerie0200ServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.CadastroSerie0200DtoLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;

/**
 * MensagemSerie0200ServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class })
public class MensagemSerie0200ServicoTest extends Mockito {

    @InjectMocks
    private MensagemSerie0200ServicoEJB ejb;

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
     * Método responsável por void
     * 
     */
    @Test
    @Ignore
    public void incluirDDA0200() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluir(CadastroSerie0200DtoLoader.geraCadastroDDA0200(), TipoMensagemDDA.DDA0200));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    @Ignore
    public void incluirDDA0214() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluir(CadastroSerie0200DtoLoader.geraCadastroDDA0214(), TipoMensagemDDA.DDA0214));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    @Ignore
    public void incluirDDA0215() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluir(CadastroSerie0200DtoLoader.geraCadastroDDA0215(), TipoMensagemDDA.DDA0215));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void incluirDDAExecption() {
        Assert.assertEquals("integracaocip.erro.solicitacao.invalida", incluir(CadastroSerie0200DtoLoader.geraCadastroDDA0200(), TipoMensagemDDA.DDA0401));
    }

    /**
     * Método responsável por
     * 
     * @param cadastroDto
     * @param codTipoMensagem
     * @return String
     * 
     */
    private String incluir(CadastroSerie0200Dto cadastroDto, String codTipoMensagem) {
        try {
            ejb.incluir(cadastroDto, codTipoMensagem, 0);
            verify(mensagemDDADelegate, times(1)).incluir(any(ComplexType.class), any(DateTimeDB.class), any(Integer.class), any(String.class), any(Integer.class),
                    any(Short.class));
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
