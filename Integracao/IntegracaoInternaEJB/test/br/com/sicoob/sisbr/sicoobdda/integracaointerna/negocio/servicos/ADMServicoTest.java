package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import br.com.bancoob.infraestrutura.cache.ServicoCache;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiBancoCafDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiFabricaDelegates;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiFeriadoDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.delegates.AdmApiProdutoInstituicaoDelegate;
import br.com.sicoob.sisbr.administrativo.api.negocio.dto.filtro.AdmApiFiltroBancoCaf;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiBancoCaf;
import br.com.sicoob.sisbr.administrativo.api.negocio.entidades.IAdmApiProdutoInstituicao;
import br.com.sicoob.sisbr.administrativo.api.negocio.excecao.AdmApiException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.BancoNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.ISPBNaoEncontradoException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.ADMServicoEJB;

/**
 * ADMServicoTest é responsável por testes Unitários da classe ADMServicoEJB. Logo, todos os métodos citados nos comentários de testes são relativos dessa
 * classe
 * 
 * @author Tayrone.Oliveira
 * @since 29/05/2017
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AdmApiBancoCafDelegate.class, AdmApiProdutoInstituicaoDelegate.class, AdmApiFeriadoDelegate.class, AdmApiFabricaDelegates.class })
public class ADMServicoTest extends Mockito {

    @InjectMocks
    private ADMServicoEJB ejb;

    @Mock
    private ServicoCache servicoCache;

    @Mock
    private AdmApiBancoCafDelegate admApiBancoCafDelegate;

    @Mock
    private AdmApiProdutoInstituicaoDelegate admApiProdutoInstituicaoDelegate;

    @Mock
    private IAdmApiProdutoInstituicao iAdmApiProdutoInst;

    @Mock
    private IAdmApiBancoCaf iAdminApiBancoCaf;

    @Mock
    private AdmApiFabricaDelegates fabrica;

    @Mock
    private AdmApiFeriadoDelegate admApiFeriadoDelegate;

    private final Integer idProduto = Integer.valueOf(Constantes.ID_PRODUTO_COBRANCA);

    private final static Boolean SUCESSO = Boolean.TRUE;

    @Before
    public void init() {
        try {
            PowerMockito.mockStatic(AdmApiFabricaDelegates.class);
            when(AdmApiFabricaDelegates.getInstance()).thenReturn(fabrica);
            when(fabrica.createAdmApiFeriadoDelegate()).thenReturn(admApiFeriadoDelegate);
            when(fabrica.createAdmApiBancoCafDelegate()).thenReturn(admApiBancoCafDelegate);
            when(fabrica.createAdmApiProdutoInstituicaoDelegate()).thenReturn(admApiProdutoInstituicaoDelegate);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    // TESTS =========================================================================
    /**
     * Método responsável por testar obterNomeBancoCache com SUCESSO
     * 
     * @throws ComumException void
     * @throws AdmApiException
     * @throws ComumNegocioException
     */
    @Test
    public void obterNomeBancoCacheSucess() throws ComumException, AdmApiException, ComumNegocioException {
        when(admApiBancoCafDelegate.recuperarNomeBanco(Constantes.NUM_BANCO)).thenReturn(Constantes.NOME_BANCO);
        when(servicoCache.recuperar(Constantes.KEY_CACHE_NUM_BANCO_TESTE)).thenReturn(Constantes.NOME_BANCO);
        Assert.assertEquals(Constantes.NOME_BANCO, ejb.obterNomeBancoCache(Constantes.NUM_BANCO));

    }

    /**
     * Método responsável por testar obterNomeBancoCache com NOME EM BRANCO
     * 
     * @throws AdmApiException
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public void obterNomeBancoCacheEmptyNome() throws AdmApiException, ComumException, ComumNegocioException {
        when(admApiBancoCafDelegate.recuperarNomeBanco(Constantes.NUM_BANCO)).thenReturn(Constantes.NOME_BANCO);
        when(servicoCache.recuperar(Constantes.KEY_CACHE_NUM_BANCO_TESTE)).thenReturn(null);
        Assert.assertEquals(Constantes.NOME_BANCO, ejb.obterNomeBancoCache(Constantes.NUM_BANCO));
    }

    /**
     * Método responsável por testar obterDataMovimento com SUCESSO
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void obterDataMovimentoSucess() throws BancoobException {
        Date atual = Constantes.DATE_TIME_DB_AUX;
        when(iAdmApiProdutoInst.getDataAtualMovimento()).thenReturn(atual);
        when(admApiProdutoInstituicaoDelegate.pesquisarProdutoInstituicao(Constantes.INTEGER_UM, idProduto.shortValue())).thenReturn(getListaProdInstituicao());
        Assert.assertEquals(atual, ejb.obterDataMovimento(Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar obterDataProximoMovimento com SUCESSO
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void obterDataProximoMovimentoSucess() throws BancoobException {
        Date atual = Constantes.DATE_TIME_DB_AUX;

        when(iAdmApiProdutoInst.getDataProximoMovimento()).thenReturn(atual);
        when(admApiProdutoInstituicaoDelegate.pesquisarProdutoInstituicao(Constantes.INTEGER_UM, idProduto.shortValue())).thenReturn(getListaProdInstituicao());
        Assert.assertEquals(atual, ejb.obterDataProximoMovimento(Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar obterDataMovimento com FALHA DE OBJETO VAZIO
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void obterDataMovimentoEmptyFailed() throws BancoobException {
        Date atual = Constantes.DATE_TIME_DB_AUX;

        when(iAdmApiProdutoInst.getDataAtualMovimento()).thenReturn(atual);
        when(admApiProdutoInstituicaoDelegate.pesquisarProdutoInstituicao(Constantes.INTEGER_UM, idProduto.shortValue())).thenReturn(new ArrayList<IAdmApiProdutoInstituicao>());
        Assert.assertEquals(Constantes.TESTE_FALHA, obterDtMovimento());

    }

    /**
     * Método responsável por testar obterDataMovimento com FALHA DE OBJETO NULO
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void obterDataMovimentoNullFailed() throws BancoobException {
        when(iAdmApiProdutoInst.getDataAtualMovimento()).thenReturn(null);
        when(admApiProdutoInstituicaoDelegate.pesquisarProdutoInstituicao(Constantes.INTEGER_UM, idProduto.shortValue())).thenReturn(getListaProdInstituicao());
        Assert.assertEquals(Constantes.TESTE_FALHA, obterDtMovimento());
    }

    /**
     * Método responsável por testar obterDataMovimento com FALHA EXCEPTION
     * 
     * @throws BancoobException void PRECISO FAZER ESSE TESTE PASSAR
     * 
     */
    @Test
    public void obterDataMovimentoExceptionFailed() throws BancoobException {
        when(admApiProdutoInstituicaoDelegate.pesquisarProdutoInstituicao(Constantes.INTEGER_UM, idProduto.shortValue())).thenThrow(new BancoobException(Constantes.TESTE_FALHA));
        Assert.assertEquals(Constantes.TESTE_FALHA, obterDtMovimento());
    }

    /**
     * Método responsável por testar obterDataMovimentoBancoob com SUCESSO
     * 
     */
    @Test
    public void obterDataMovimentoBancoobSucess() {
        dataMovimentoPorEvento(Boolean.FALSE);
        whenListaFull(Constantes.INTEGER_UM);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterDataMovimentoBancoob());
    }

    /**
     * Método responsável por testar obterDataProximoMovimentooBancoob com SUCESSO
     * 
     */
    @Test
    public void obterDataProximoMovimentooBancoobSucess() {
        dataMovimentoPorEvento(Boolean.TRUE);
        whenListaFull(Constantes.INTEGER_UM);
        Assert.assertEquals(Constantes.TESTE_SUCESSO, oterDataProximoMovimentoBancoob());
    }

    /**
     * Método responsável por testar obterProdutoCobrancaBancoob com SUCESSO
     * 
     */
    @Test
    public void obterProdutoCobrancaBancoobSucess() {
        obterProdutoDTO();
        whenListaFull(Constantes.ID_BANCOOB);

        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterProdutoCobrancaBancoob());
    }

    /**
     * Método responsável por testar obterProdutoCobrancaBancoob com FALHA EXCEPTION
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void obterProdutoCobrancaBancoobExceptionFailed() throws BancoobException {
        whenListaException(Constantes.ID_BANCOOB);
        Assert.assertEquals(Constantes.TESTE_FALHA, obterProdutoCobrancaBancoob());
    }

    /**
     * Método responsável por testar obterProdutoCobrancaBancoob com FALHA DE OBJECT EMPTY
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void obterProdutoCobrancaBancoobEmptyFailed() throws BancoobException {
        whenListaEmpty(Constantes.ID_BANCOOB);
        Assert.assertEquals(Constantes.TESTE_FALHA, obterProdutoCobrancaBancoob());
    }

    /**
     * Método responsável por testar verificarDiaUtil com SUCESSO
     * 
     */
    @Test
    public void verificarDiaUtilSucess() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, verificarDiaUtil(SUCESSO));
        Assert.assertEquals(Constantes.TESTE_FALHA, verificarDiaUtil(!SUCESSO));
    }

    /**
     * Método responsável por testar obterNomeBanco com FALHAS | OBJECT NULL, e EXCEPTIONS
     * 
     */
    @Test
    public void obterNomeBanco() {
        // Object.isNull TRUE - BancoNaoEncontradoException
        Assert.assertEquals(Constantes.TESTE_FALHA, obterNomeBanco(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        // FAILED - BancoNaoEncontradoException
        Assert.assertEquals(Constantes.TESTE_FALHA, obterNomeBanco(Boolean.FALSE, Boolean.TRUE, Boolean.FALSE));
        // FAILED - AdminApiException
        Assert.assertEquals(Constantes.TESTE_FALHA, obterNomeBanco(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE));
    }

    /**
     * Método responsável por testar obterNomeInstituicaoFinanceiraCache com SUCESSO
     * 
     * @throws ComumException
     * @throws AdmApiException void
     * 
     */
    @Test
    public void obterNomeInstFinanCacheSucess() throws ComumException, AdmApiException {
        whenServicoCache(SUCESSO);
        Assert.assertEquals(Constantes.NOME_BANCO, ejb.obterNomeInstituicaoFinanceiraCache(Constantes.KEY_CACHE_NUM_BANCO_TESTE.toString()));
    }

    /**
     * Método responsável por testar obterNomeInstituicaoFinanceiraCache com FALHA
     * 
     * @throws ComumException
     * @throws AdmApiException void
     * 
     */
    @Test
    public void obterNomeInstFinanCacheFailed() throws ComumException, AdmApiException {
        whenServicoCache(!SUCESSO);
        Assert.assertEquals(Constantes.NOME_BANCO, ejb.obterNomeInstituicaoFinanceiraCache(Constantes.KEY_CACHE_NUM_BANCO_TESTE.toString()));
    }

    /**
     * Método responsável por testar obterNomeInstituicaoFinanceira com FALHAS | OBJECT NULL, OBJECT EMPTY, ISPException, ADMException
     * 
     */
    @Test
    public void obterNomeInstituicaoFinanceira() {
        // objetNull
        Assert.assertEquals(Constantes.TESTE_FALHA, obterNomeInstituicaoFinanceira(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        // objectEmpty
        Assert.assertEquals(Constantes.TESTE_FALHA, obterNomeInstituicaoFinanceira(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        // ISPException
        Assert.assertEquals(Constantes.TESTE_FALHA, obterNomeInstituicaoFinanceira(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE));
        // AdmException
        Assert.assertEquals(Constantes.TESTE_FALHA, obterNomeInstituicaoFinanceira(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE));
    }

    /**
     * Método responsável por testar recuperarQtdDiasUteis com SUCESSO e FALHA
     * 
     */
    @Test
    public void recuperarQtdDiasUteis() {
        // : SUCCESS
        Assert.assertEquals(Constantes.TESTE_SUCESSO, recuperarQtdDiasUteis(SUCESSO));
        // : FAILED
        Assert.assertEquals(Constantes.TESTE_FALHA, recuperarQtdDiasUteis(!SUCESSO));
    }

    /**
     * Método responsável por testar listarBancosCafComAgenciasAtivas com SUCESSO e FALHA
     * 
     */
    @Test
    public void listarBancosCafComAgenciasAtivas() {
        // : SUCCESS
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarBancosCafComAgenciasAtivas(SUCESSO));
        // : FAILED
        Assert.assertEquals(Constantes.TESTE_FALHA, listarBancosCafComAgenciasAtivas(!SUCESSO));
    }

    /**
     * Método responsável por testar listarBancosCafDtoComAgenciasAtivas com SUCESSO normal e SUCESSO com OBJETC EMPTY, e FALHA
     * 
     */
    @Test
    public void listarBancosCafDtoComAgenciasAtivas() {
        // : SUCCESS
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarBancosCafDtoComAgenciasAtivas(SUCESSO, false));
        // : SUCCESS - Objetc.isEmpty
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarBancosCafDtoComAgenciasAtivas(SUCESSO, true));
        // : FAILED
        Assert.assertEquals(Constantes.TESTE_FALHA, listarBancosCafDtoComAgenciasAtivas(!SUCESSO, false));
    }

    // PRIVATES METHODS ==============================================================================
    /**
     * Método responsável por
     * 
     * @param success void
     * 
     */
    private void whenAdmApiFeriadoDelegate(Boolean success) {
        try {
            if (success) {
                when(admApiFeriadoDelegate.verificarDiaUtil(Constantes.ID_BANCOOB, Constantes.DATE_TIME_DB_AUX)).thenReturn(SUCESSO);

                when(admApiFeriadoDelegate.recuperarQtdDiasUteis(Constantes.DATE_TIME_DB_AUX, Constantes.DATE_TIME_DB_AUX, Constantes.ID_INST)).thenReturn(Constantes.INTEGER_UM);
            } else {
                when(admApiFeriadoDelegate.verificarDiaUtil(Constantes.ID_BANCOOB, Constantes.DATE_TIME_DB_AUX)).thenThrow(new AdmApiException(Constantes.TESTE_FALHA));

                when(admApiFeriadoDelegate.recuperarQtdDiasUteis(Constantes.DATE_TIME_DB_AUX, Constantes.DATE_TIME_DB_AUX, Constantes.ID_INST)).thenThrow(
                        new AdmApiException(Constantes.TESTE_FALHA));
            }
        } catch (Exception e) {

        }

    }

    /**
     * Método responsável por
     * 
     * @return List<IAdmApiProdutoInstituicao>
     * 
     */
    private List<IAdmApiProdutoInstituicao> getListaProdInstituicao() {
        List<IAdmApiProdutoInstituicao> list = new ArrayList<IAdmApiProdutoInstituicao>();
        list.add(iAdmApiProdutoInst);

        return list;
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @param objectEmpty
     * @return String
     * 
     */
    private String listarBancosCafDtoComAgenciasAtivas(Boolean success, Boolean objectEmpty) {
        List<IAdmApiBancoCaf> list = new ArrayList<IAdmApiBancoCaf>();
        list.add(iAdminApiBancoCaf);
        try {
            if (success) {
                if (objectEmpty) {
                    when(iAdminApiBancoCaf.getDescBanco()).thenReturn("");
                } else {
                    when(iAdminApiBancoCaf.getDescBanco()).thenReturn(Constantes.NOME_BANCO);
                }
                when(iAdminApiBancoCaf.getNumBanco()).thenReturn(Constantes.SHORT_UM);
                when(iAdminApiBancoCaf.getNumCodISPB()).thenReturn(Constantes.NOME_BANCO);
                when(admApiBancoCafDelegate.listarBancosCafComAgenciasAtivas()).thenReturn(list);
            } else {
                when(admApiBancoCafDelegate.listarBancosCafComAgenciasAtivas()).thenThrow(new AdmApiException("falhou"));
            }
            ejb.listarBancosCafDtoComAgenciasAtivas(success);
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return String
     * 
     */
    private String listarBancosCafComAgenciasAtivas(Boolean success) {
        List<IAdmApiBancoCaf> list = new ArrayList<IAdmApiBancoCaf>();
        list.add(iAdminApiBancoCaf);
        try {
            if (success) {
                when(admApiBancoCafDelegate.listarBancosCafComAgenciasAtivas()).thenReturn(list);
            } else {
                when(admApiBancoCafDelegate.listarBancosCafComAgenciasAtivas()).thenThrow(new AdmApiException("falhou"));
            }
            ejb.listarBancosCafComAgenciasAtivas();
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return String
     * 
     */
    private String recuperarQtdDiasUteis(Boolean success) {
        Date dataInicial = Constantes.DATE_TIME_DB_AUX;
        Date dataFinal = Constantes.DATE_TIME_DB_AUX;
        try {
            if (success) {
                when(admApiFeriadoDelegate.recuperarQtdDiasUteis(dataInicial, dataFinal, Constantes.NUM_INSTITUICAO_FINANCEIRA)).thenReturn(3);
            } else {
                when(admApiFeriadoDelegate.recuperarQtdDiasUteis(dataInicial, dataFinal, Constantes.NUM_INSTITUICAO_FINANCEIRA)).thenThrow(new AdmApiException("falhou"));
            }
            ejb.recuperarQtdDiasUteis(dataInicial, dataFinal, Constantes.NUM_INSTITUICAO_FINANCEIRA);
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param objectNull
     * @param objectEmpty
     * @param ispException
     * @param admException
     * @return String
     * 
     */
    private String obterNomeInstituicaoFinanceira(Boolean objectNull, Boolean objectEmpty, Boolean ispException, Boolean admException) {
        try {
            when(servicoCache.recuperar("COBRANCA_CACHE_INSTITUICAO_ISPB_" + Constantes.KEY_CACHE_NUM_BANCO_TESTE)).thenReturn(null);
            if (objectNull) {
                when(iAdminApiBancoCaf.getDescBanco()).thenReturn(null);
            }
            if (objectEmpty) {
                List<IAdmApiBancoCaf> list = new ArrayList<IAdmApiBancoCaf>();
                list.add(iAdminApiBancoCaf);
                when(admApiBancoCafDelegate.obterBancoPorFiltroBancoCaf(any(AdmApiFiltroBancoCaf.class))).thenReturn(list);
                when(iAdminApiBancoCaf.getDescBanco()).thenReturn(null);
            }
            if (ispException) {
                when(admApiBancoCafDelegate.obterBancoPorFiltroBancoCaf(any(AdmApiFiltroBancoCaf.class))).thenThrow(new ISPBNaoEncontradoException(Constantes.BANCOOB));
            }
            if (admException) {
                when(admApiBancoCafDelegate.obterBancoPorFiltroBancoCaf(any(AdmApiFiltroBancoCaf.class))).thenThrow(new AdmApiException("falhou"));
            }
            ejb.obterNomeInstituicaoFinanceiraCache(Constantes.KEY_CACHE_NUM_BANCO_TESTE.toString());
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @throws AdmApiException void
     * 
     */
    private void whenServicoCache(Boolean success) throws AdmApiException {
        if (success) {
            when(servicoCache.recuperar("COBRANCA_CACHE_INSTITUICAO_ISPB_" + Constantes.KEY_CACHE_NUM_BANCO_TESTE)).thenReturn(Constantes.NOME_BANCO);
        } else {
            List<IAdmApiBancoCaf> list = new ArrayList<IAdmApiBancoCaf>();
            list.add(iAdminApiBancoCaf);
            when(servicoCache.recuperar("COBRANCA_CACHE_INSTITUICAO_ISPB_" + Constantes.KEY_CACHE_NUM_BANCO_TESTE)).thenReturn(null);
            when(admApiBancoCafDelegate.obterBancoPorFiltroBancoCaf(any(AdmApiFiltroBancoCaf.class))).thenReturn(list);
            when(iAdminApiBancoCaf.getDescBanco()).thenReturn(Constantes.NOME_BANCO);
        }
    }

    /**
     * Método responsável por
     * 
     * @param success
     * @return String
     * 
     */
    private String verificarDiaUtil(Boolean success) {
        try {
            whenAdmApiFeriadoDelegate(success);
            ejb.verificarDiaUtil(Constantes.DATE_TIME_DB_AUX);
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param exceptionNull
     * @param exceptionBancoNaoEncontrado
     * @param admApiException
     * @return String
     * 
     */
    private String obterNomeBanco(Boolean exceptionNull, Boolean exceptionBancoNaoEncontrado, Boolean admApiException) {
        try {
            if (exceptionNull) {
                when(admApiBancoCafDelegate.recuperarNomeBanco(Constantes.NUM_BANCO)).thenReturn(null);
            }
            if (exceptionBancoNaoEncontrado) {
                when(admApiBancoCafDelegate.recuperarNomeBanco(Constantes.NUM_BANCO)).thenThrow(new BancoNaoEncontradoException(Constantes.NUM_BANCO));
            }
            if (admApiException) {
                when(admApiBancoCafDelegate.recuperarNomeBanco(Constantes.NUM_BANCO)).thenThrow(new AdmApiException("falhou"));
            }
            ejb.obterNomeBancoCache(Constantes.NUM_BANCO);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String obterProdutoCobrancaBancoob() {
        try {
            ejb.obterProdutoCobrancaBancoob();
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String oterDataProximoMovimentoBancoob() {
        try {
            ejb.obterDataProximoMovimentoBancoob();
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String obterDataMovimentoBancoob() {
        try {
            ejb.obterDataMovimentoBancoob();
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Método responsável por
     * 
     * @param param void
     * 
     */
    private void whenListaFull(int param) {
        try {
            when(admApiProdutoInstituicaoDelegate.pesquisarProdutoInstituicao(param, idProduto.shortValue())).thenReturn(getListaProdInstituicao());
        } catch (BancoobException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Método responsável por
     * 
     * @param param
     * @throws BancoobException void
     * 
     */
    private void whenListaEmpty(int param) throws BancoobException {
        when(admApiProdutoInstituicaoDelegate.pesquisarProdutoInstituicao(param, idProduto.shortValue())).thenReturn(new ArrayList<IAdmApiProdutoInstituicao>());
    }

    /**
     * Método responsável por
     * 
     * @param param
     * @throws BancoobException void
     * 
     */
    private void whenListaException(int param) throws BancoobException {
        when(admApiProdutoInstituicaoDelegate.pesquisarProdutoInstituicao(param, idProduto.shortValue())).thenThrow(new BancoobException("teste"));

    }

    /**
     * Método responsável por void
     * 
     */
    private void obterProdutoDTO() {
        when(iAdmApiProdutoInst.getIdUnidadeInstituicao()).thenReturn(Constantes.INTEGER_UM);
        when(iAdmApiProdutoInst.getDataUltimoMovimento()).thenReturn(Constantes.DATE_TIME_DB_AUX);
        when(iAdmApiProdutoInst.getDataAtualMovimento()).thenReturn(Constantes.DATE_TIME_DB_AUX);
        when(iAdmApiProdutoInst.getDataProximoMovimento()).thenReturn(Constantes.DATE_TIME_DB_AUX);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String obterDtMovimento() {
        try {
            ejb.obterDataMovimento(Constantes.INTEGER_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (IntegracaoInternaException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Método responsável por
     * 
     * @param param void
     * 
     */
    private void dataMovimentoPorEvento(Boolean param) {
        if (param) {
            when(iAdmApiProdutoInst.getDataProximoMovimento()).thenReturn(Constantes.DATE_TIME_DB_AUX);
        } else {
            when(iAdmApiProdutoInst.getDataAtualMovimento()).thenReturn(Constantes.DATE_TIME_DB_AUX);
        }
    }
}
