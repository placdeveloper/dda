/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         BaixaOperacionalServicoTest.java
 * Data Criação:    Jan 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.math.BigDecimal;

import org.junit.Assert;
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

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaOperacional;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.BaixaOperacionalServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MensagemDDADao;

/**
 * BaixaOperacionalServicoTest
 * 
 * @author samuell.ramos
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class })
public class BaixaOperacionalServicoTest extends Mockito {

    @InjectMocks
    private BaixaOperacionalServicoEJB ejb;

    @Mock
    private MensagemDDADelegate delegate;

    @Mock
    private MensagemDDADao dao;

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

    @Ignore
    @Test
    public void incluir() throws ComumException, ComumNegocioException {
        // Teste fluxo
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirEJB(1));

        // Teste excecao canalDDA
        Assert.assertEquals("integracaocip.canal.ctr.nao.correspondente", incluirEJB(2));
        // Teste excecao nulo mensagemDDABP
        Assert.assertEquals("integracaocip.parametro.nao.informado", incluirEJB(3));
        // Teste excecao vazio numBanco
        Assert.assertEquals("integracaocip.parametro.nao.informado", incluirEJB(4));
        // Teste excecao nulo dataMovimento
        Assert.assertEquals("integracaocip.parametro.nao.informado", incluirEJB(5));
        // Teste excecao nulo bolOperacaoContig
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirEJB(6));
        // Teste excecao vazio valorBaixa
        Assert.assertEquals("integracaocip.parametro.nao.informado", incluirEJB(7));
        // NumCodigo Baixa
        Assert.assertEquals("integracaocip.parametro.nao.informado", incluirEJB(8));
        // Definindo meio de pagamento
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirEJB(10));
        /*
         * Testes abaixo responsáveis por obter o tipo de baixa operacional correspondente ao banco e tipo de pagamento
         */
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirEJB(10));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirEJB(11));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirEJB(12));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirEJB(13));
    }

    public String incluirEJB(int operacao) throws ComumException, ComumNegocioException {
        MensagemDDABaixaOperacional mensagem = getMensagemDDABP();
        Short numBanco = 756;
        Short canal = Constantes.SHORT_UM;
        Boolean aceitaPagamentoParcial = Boolean.TRUE;
        DateTimeDB dataMovimento = new DateTimeDB();
        int idInstituicao = 0;

        when(dao.obterCanalDDA(Constantes.SHORT_UM)).thenReturn(Constantes.SHORT_UM);

        switch (operacao) {
        // Teste excecao canalDDA
        case 2:
            when(dao.obterCanalDDA(Constantes.SHORT_UM)).thenReturn(null);
            break;
        // Teste excecao nulo mensagemDDABP
        case 3:
            mensagem = null;
            break;
        // Teste excecao vazio numBanco
        case 4:
            numBanco = 0;
            break;
        // Teste excecao nulo dataMovimento
        case 5:
            dataMovimento = null;
            break;
        // Teste excecao nulo bolOperacaoContig
        case 6:
            mensagem.setBolOperacaoContingencia(Boolean.TRUE);
            break;
        // Teste excecao vazio valorBaixa
        case 7:
            mensagem.setValorBaixa(null);
            break;
        // NumCodigo Baixa
        case 8:
            mensagem.setNumCodigoBarra(null);
            break;
        case 10:
            // Definindo meio de pagamento
            canal = 2;
            break;
        /*
         * Testes abaixo responsáveis por obter o tipo de baixa operacional correspondente ao banco e tipo de pagamento
         */
        case 11:
            numBanco = 755;
            break;
        case 12:
            aceitaPagamentoParcial = Boolean.FALSE;
            numBanco = 1;
            break;
        case 13:
            aceitaPagamentoParcial = Boolean.FALSE;
            numBanco = 755;
            break;
        }

        try {
            ejb.incluir(mensagem, numBanco, canal, aceitaPagamentoParcial, dataMovimento, idInstituicao);
        } catch (ComumException e) {
            return e.getMessage();
        }

        verify(delegate, times(1)).incluir(mensagem, TipoMensagemDDA.DDA0108, dataMovimento, Constantes.STRING_NUMERO_0, idInstituicao, canal);

        return Constantes.TESTE_SUCESSO;
    }

    public MensagemDDABaixaOperacional getMensagemDDABP() {
        MensagemDDABaixaOperacional mensagemDDABP = new MensagemDDABaixaOperacional();
        mensagemDDABP.setBolOperacaoContingencia(Boolean.TRUE);
        mensagemDDABP.setValorBaixa(BigDecimal.ONE);
        mensagemDDABP.setNumCodigoBarra(Constantes.STRING_NUMERO_1);
        mensagemDDABP.setNumCodBarrasCampoLivre(Constantes.STRING_NUMERO_1);
        mensagemDDABP.setNumRefAtualCadBoleto(Constantes.LONG_UM);
        return mensagemDDABP;
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
