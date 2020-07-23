/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemDDAAGEN001ServicoEJBTest.java
 * Data Criação:    May 17, 2017
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
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemDDAAGEN001ServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;

/**
 * MensagemDDAAGEN001ServicoEJBTest é responsável por
 * 
 * @author adriano.pinheiro
 */

@RunWith(MockitoJUnitRunner.class)
public class MensagemDDAAGEN001ServicoEJBTest extends Mockito {

    @InjectMocks
    private MensagemDDAAGEN001ServicoEJB ejb;

    @Mock
    private MensagemDDADao dao;

    @Mock
    private MensagemDDADelegate mensagemDDADelegate;

    @Before
    public void setup() {

    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    @Ignore
    public void incluirPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluir(Constantes.LONG_ZERO));
        verify(mensagemDDADelegate, times(1)).incluir(any(ComplexType.class), any(DateTimeDB.class), anyInt(), anyString(), anyInt(), anyShort());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void incluirQtdMensagemPendente() {
        Assert.assertEquals("integracaocip.erro.existe.mensagem.pendente", incluir(Constantes.LONG_UM));
    }

    /**
     * Método responsável por
     * 
     * @param qtdMensagemPendente
     * @return String
     * 
     */
    private String incluir(long qtdMensagemPendente) {
        try {
            when(dao.obterQuantidadeMensagemPendente(TipoMensagemDDA.AGEN001)).thenReturn(qtdMensagemPendente);
            ejb.incluir(Constantes.STRING_NUMERO_1);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }
}
