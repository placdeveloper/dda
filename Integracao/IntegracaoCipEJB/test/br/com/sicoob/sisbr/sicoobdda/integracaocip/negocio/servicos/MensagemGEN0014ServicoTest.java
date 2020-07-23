/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         ArquivoRequisitarArquivoServicoTest.java
 * Data Criação:    May 24, 2016
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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroGen0014Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSolicitacaoGEN0014Enum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemGEN0014ServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.CadastroGen0014DtoLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;

/**
 * ArquivoRequisitarArquivoServicoTest é responsável por
 * 
 * @author Daniel.Basso
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class })
public class MensagemGEN0014ServicoTest extends Mockito {

    @InjectMocks
    private MensagemGEN0014ServicoEJB ejb;

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
    public void incluirBeneficiario() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluir(CadastroGen0014DtoLoader.geraCadastroBeneficiario(), TipoSolicitacaoGEN0014Enum.BENEFICIARIO));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    @Ignore
    public void incluirPagador() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluir(CadastroGen0014DtoLoader.geraCadastroPagador(), TipoSolicitacaoGEN0014Enum.PAGADOR));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    @Ignore
    public void incluirBoleto() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluir(CadastroGen0014DtoLoader.geraCadastroTitulo(), TipoSolicitacaoGEN0014Enum.TITULO));
    }

    /**
     * Método responsável por
     * 
     * @param cadastroDto
     * @param tipoSolicitacao
     * @return
     * @throws BancoobException String
     * 
     */
    private String incluir(CadastroGen0014Dto cadastroDto, TipoSolicitacaoGEN0014Enum tipoSolicitacao) throws BancoobException {
        ejb.incluir(cadastroDto, tipoSolicitacao);
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
