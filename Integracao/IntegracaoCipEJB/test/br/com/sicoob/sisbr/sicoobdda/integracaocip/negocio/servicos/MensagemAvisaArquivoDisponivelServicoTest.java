package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemAvisaArquivoDisponivelServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0015.GEN0015ComplexType;

/**
 * MensagemAvisaArquivoDisponivelServicoTest é responsável por Testar o AvisaArquivoDisponivelServicoEJB
 * 
 * @author Francisco.Marcio
 */
@RunWith(MockitoJUnitRunner.class)
public class MensagemAvisaArquivoDisponivelServicoTest extends Mockito {

    @InjectMocks
    private MensagemAvisaArquivoDisponivelServicoEJB ejb;

    @Mock
    private MensagemDDADao dao;

    @Mock
    private ArquivoCipDao arquivoCipDao;

    @Mock
    private EntityManager em;

    /**
     * Método responsável por testar a inclusão quando sucesso
     * 
     * @throws BancoobException
     * 
     * @throws OperacionalNoResultException
     * 
     */
    public void processarRetornoMensagemDDAcomSucesso() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirLogRecebimentoArquivo(new GEN0015ComplexType()));
    }

    /**
     * Método responsável por testar a inclusão quando sucesso
     * 
     * @throws BancoobException
     * 
     * @throws OperacionalNoResultException
     * 
     */
    public void processarRetornoMensagemDDAcomSucessoComSufixoERR() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirLogRecebimentoArquivo(criarLogGEN0015ComplexTypeComSufixoERR()));
    }

    @Test
    public void processarRetornoMensagemDDAcomSucessoComSufixoPRO() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirLogRecebimentoArquivo(criarLogGEN0015ComplexTypeComSufixoPRO()));
    }

    public void processarRetornoMensagemDDAcomSucessoComSufixoRET() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirLogRecebimentoArquivo(criarLogGEN0015ComplexTypeComSufixoRET()));
    }

    public void processarRetornoMensagemDDAcomSucessoComNomeArqNull() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirLogRecebimentoArquivo(criarLogGEN0015ComplexTypeComNomeArqNull()));
    }

    private String incluirLogRecebimentoArquivo(GEN0015ComplexType type) {
        try {
            TipoMensagemDDA tipo = new TipoMensagemDDA();
            tipo.setCodTipoMensagem("");
            when(em.find(TipoMensagemDDA.class, TipoMensagemDDA.ADDA101RR2)).thenReturn(tipo);

            when(em.find(TipoMensagemDDA.class, TipoMensagemDDA.ADDA101RET)).thenReturn(tipo);

            when(arquivoCipDao.obterLogEnvioArquivoPorNome(type.getNomArq())).thenReturn(new LogEnvioArquivoDDA(Constantes.LONG_DOIS));
            ejb.processarRetornoMensagemDDA(type);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;

    }

    private GEN0015ComplexType criarLogGEN0015ComplexTypeComSufixoPRO() {
        GEN0015ComplexType conteudoMsg = new GEN0015ComplexType();
        conteudoMsg.setNomArq("ADDA101_02038232_20170814_00021_RR2");
        return conteudoMsg;

    }

    private GEN0015ComplexType criarLogGEN0015ComplexTypeComSufixoRET() {
        GEN0015ComplexType conteudoMsg = new GEN0015ComplexType();
        conteudoMsg.setNomArq("ADDA101_02038232_20170814_00021_RET");
        return conteudoMsg;

    }

    private GEN0015ComplexType criarLogGEN0015ComplexTypeComSufixoERR() {
        GEN0015ComplexType conteudoMsg = new GEN0015ComplexType();
        conteudoMsg.setNomArq("GEN0015ERR");
        return conteudoMsg;

    }

    private GEN0015ComplexType criarLogGEN0015ComplexTypeComNomeArqNull() {
        GEN0015ComplexType conteudoMsg = new GEN0015ComplexType();
        conteudoMsg.setNomArq(null);
        return conteudoMsg;

    }

}
