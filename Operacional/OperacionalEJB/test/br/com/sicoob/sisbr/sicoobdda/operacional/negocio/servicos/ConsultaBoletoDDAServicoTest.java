package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaBoletoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoModeloCalculo;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ConsultaBoletoDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AgendamentoBoletoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BoletoDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao;

/**
 * ConsultaBoletoDDAServicoTest é responsável por
 * 
 * @author Rodrigo.Neri
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsultaBoletoDDAServicoTest extends Mockito {

    private final String VALIDADE_CONSULTA_CIP = "D0";

    private final String CODIGO_BARRAS_756 = "75698206700000200001100402000001900000033001";
    private final String CODIGO_BARRAS_237 = "23799000000050000001434212123456712345678001";
    private final String LINHA_DIGITAVEL_756 = "75691100470200000190500000330019820670000020000";
    private final String LINHA_DIGITAVEL_988 = "98891100460200000190500000330019920670000020000";

    private final String CODIGO_BARRAS_DV_INVALIDO = "75694206700000200001100402000001900000033001";
    private final String LINHA_DIGITAVEL_DV_1_INVALIDO = "75691100460200000190500000330019820670000020000";
    private final String LINHA_DIGITAVEL_DV_2_INVALIDO = "75691100470200000190200000330019820670000020000";
    private final String LINHA_DIGITAVEL_DV_3_INVALIDO = "75691100470200000190500000330012820670000020000";
    private final String LINHA_DIGITAVEL_DV_4_INVALIDO = "75691100470200000190500000330019320670000020000";

    @InjectMocks
    private ConsultaBoletoDDAServicoEJB ejb;

    @Mock
    private AgendamentoBoletoDao dao;

    @Mock
    private BoletoDDADao boletoDDADao;

    @Mock
    private ParametroDao parametroDAO;

    @Mock
    private ADMDelegate admDelegate;

    @Mock
    private SCIDelegate sciDelegate;

    @Mock
    private PagadorEletronicoDDADao pagadorEletronicoDDADao;

    /**
     * Método responsável por testar a consulta de um boleto sem informar a linha digitável/cód barras.
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public void obterBoletoLinhaDigitavelCodBarrasNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.linha.digitavel.codigo.barras.nao.informado", obterBoleto(null, null, null, null, null));
    }

    /**
     * Método responsável por testar a consulta de um boleto sem informar o canal.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    // FIXME rodrigo.neri: arrumar
    @Ignore
    @Test
    public void obterBoletoIdCanalNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.parametro.nao.informado", obterBoleto(CODIGO_BARRAS_756, Constantes.DATE_AUX, null, null, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto sem informar o num PA.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoNumPANull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.parametro.nao.informado", obterBoleto(CODIGO_BARRAS_756, Constantes.DATE_AUX, CanalEnum.CAIXA.getId(), null, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto sem informar a cooperativa.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoNumCooperativaNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.parametro.nao.informado", obterBoleto(CODIGO_BARRAS_756, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, null));
    }

    /**
     * Método responsável por testar a consulta de um boleto com a cooperativa zerada.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoNumCooperativaZerada() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.parametro.nao.informado",
                obterBoleto(CODIGO_BARRAS_756, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_ZERO));
    }

    /**
     * Método responsável por testar a consulta de um boleto com a data de pagamento anterior a data atual.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoDataPagamentoAnteriorDataAtual() throws ComumNegocioException, ComumException {
        Calendar dataPagamentoOntem = Calendar.getInstance();
        dataPagamentoOntem.setTime(Constantes.DATE_AUX);
        dataPagamentoOntem.add(Calendar.DATE, -1);

        Assert.assertEquals("integracaocip.data.vencimento.anterior.data.atual",
                obterBoleto(CODIGO_BARRAS_756, dataPagamentoOntem.getTime(), Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto com a data de pagamento para dia não útil.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoDataPagamentoDiaNaoUtil() throws ComumNegocioException, ComumException {
        when(dao.isDataUtil(anyInt(), any(Date.class))).thenReturn(false);

        Assert.assertEquals("integracaocip.data.vencimento.dia.util",
                obterBoleto(CODIGO_BARRAS_756, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto com codigo de barras/linha digitável com tamanho invalido.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoCodBarrasTamanhoInvalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();

        Assert.assertEquals("integracaocip.linha.digitavel.codigo.barras.tamanho.invalido",
                obterBoleto("1111111111111111111111111111111111", Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto com código de barras/linha digitável invalido.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoCodigoBarrasInvalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();

        Assert.assertEquals("integracaocip.linha.digitavel.codigo.barras.invalido",
                obterBoleto("A@#11111111111@@111111111 1111111S1111111111", Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto com DV do código de barras inválido.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoCodigoBarrasDVInvalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();

        Assert.assertEquals("O DV do código de barras é inválido.",
                obterBoleto(CODIGO_BARRAS_DV_INVALIDO, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto com DV 1 da linha digitável inválido.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoLinhaDigitavelDV1Invalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();

        Assert.assertEquals("O DV 1 da linha digitável é inválido.",
                obterBoleto(LINHA_DIGITAVEL_DV_1_INVALIDO, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto com DV 2 da linha digitável inválido.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoLinhaDigitavelDV2Invalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();

        Assert.assertEquals("O DV 2 da linha digitável é inválido.",
                obterBoleto(LINHA_DIGITAVEL_DV_2_INVALIDO, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto com DV 3 da linha digitável inválido.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoLinhaDigitavelDV3Invalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();

        Assert.assertEquals("O DV 3 da linha digitável é inválido.",
                obterBoleto(LINHA_DIGITAVEL_DV_3_INVALIDO, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto com DV 4 da linha digitável inválido.
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoLinhaDigitavelDV4Invalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();

        Assert.assertEquals("O DV 4 da linha digitável é inválido.",
                obterBoleto(LINHA_DIGITAVEL_DV_4_INVALIDO, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto da tabela Título (DDA inativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    @Ignore
    public void obterBoletoLegadoVencido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockParametroDDA(false);

        Date dataVencimento = LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(LINHA_DIGITAVEL_756);
        mockObterDataUtil(dataVencimento, dataVencimento);

        Assert.assertEquals("integracaocip.boleto.vencido",
                obterBoleto(LINHA_DIGITAVEL_756, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto da JDDDA (DDA inativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoLegadoJDDDASucesso() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterBoletoLegadoJDDDA());
    }

    /**
     * Método responsável por testar a consulta de um boleto da tabela Título (DDA inativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoLegadoSucesso() throws ComumNegocioException, ComumException {
        when(dao.obterBoletoPorCodBarras(Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.INTEGER_UM, CODIGO_BARRAS_237, DateUtil.obterDataSemHora(Constantes.DATE_AUX)))
                .thenReturn(getConsultaBoletoDDADto());

        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterBoletoLegado(LINHA_DIGITAVEL_756));
    }

    /**
     * Método responsável por testar a consulta de um boleto lendo o código de barras (DDA inativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoLegadoLeituraCodBarrasSucesso() throws ComumNegocioException, ComumException {
        when(dao.obterBoletoPorCodBarras(Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.INTEGER_UM, CODIGO_BARRAS_237, DateUtil.obterDataSemHora(Constantes.DATE_AUX)))
                .thenReturn(null);

        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterBoletoLegado(LINHA_DIGITAVEL_756));
    }

    /**
     * Método responsável por testar a consulta de um boleto lendo o código de barras de outro banco (DDA inativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoLegadoLeituraCodBarrasOutrosBancosSucesso() throws ComumNegocioException, ComumException {
        when(dao.obterBoletoPorCodBarras(Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.INTEGER_UM, CODIGO_BARRAS_237, DateUtil.obterDataSemHora(Constantes.DATE_AUX)))
                .thenReturn(null);

        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterBoletoLegado(CODIGO_BARRAS_237));
    }

    /**
     * Método responsável por testar a consulta de um boleto com IF 988 (DDA inativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    @Ignore
    public void obterBoletoLegadoIF988() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockParametroDDA(false);

        Date dataVencimento = LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(LINHA_DIGITAVEL_756);
        mockObterDataUtil(dataVencimento, Constantes.DATE_AUX);

        Assert.assertEquals("integracaocip.consulta.nao.permitida.instituicao.financeira.invalida",
                obterBoleto(LINHA_DIGITAVEL_988, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por simular obter o boleto da JDDDA
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private String obterBoletoLegadoJDDDA() throws ComumException, ComumNegocioException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockParametroDDA(false);

        Date dataVencimento = LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(LINHA_DIGITAVEL_756);
        mockObterDataUtil(dataVencimento, Constantes.DATE_AUX);

        obterBoleto(LINHA_DIGITAVEL_756, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM);

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por simular obter o boleto da tabela Título
     * 
     * @param linhaDigitavel
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private String obterBoletoLegado(String linhaDigitavel) throws ComumException, ComumNegocioException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockParametroDDA(false);

        Date dataVencimento = LinhaDigitavelCodigoBarrasUtil.obterDataVencimentoPorLinhaDigitavelCodigoBarras(LINHA_DIGITAVEL_756);
        mockObterDataUtil(dataVencimento, Constantes.DATE_AUX);

        obterBoleto(linhaDigitavel, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM);

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar a consulta de um boleto DDA com o parâmetro de validade da consulta com tamanho inválido (DDA ativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    @Ignore
    public void obterBoletoDDAParametroValidadeConsultaCIPTamanhoInvalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockPagadorEletronico();
        mockParametroDDA(true);

        when(boletoDDADao.obterBoletoDDA(CODIGO_BARRAS_237, null, Boolean.TRUE)).thenReturn(getBoletoDDA());
        when(parametroDAO.obterValor(ParametroDDA.VALIDADE_CONSULTA_CIP, Constantes.ID_SICOOB)).thenReturn("2");

        Assert.assertEquals("integracaocip.erro.parametro.nulo.vazio.invalido",
                obterBoleto(CODIGO_BARRAS_237, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto DDA com o parâmetro de validade da consulta nulo (DDA ativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    @Ignore
    public void obterBoletoDDAParametroValidadeConsultaCIPNull() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockPagadorEletronico();
        mockParametroDDA(true);

        when(boletoDDADao.obterBoletoDDA(CODIGO_BARRAS_237, null, Boolean.TRUE)).thenReturn(getBoletoDDA());
        when(parametroDAO.obterValor(ParametroDDA.VALIDADE_CONSULTA_CIP, Constantes.ID_SICOOB)).thenReturn(null);

        Assert.assertEquals("integracaocip.erro.parametro.nulo.vazio.invalido",
                obterBoleto(CODIGO_BARRAS_237, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto DDA com o parâmetro de validade da consulta inválido (DDA ativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    @Ignore
    public void obterBoletoDDAParametroValidadeConsultaCIPInvalido() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockPagadorEletronico();
        mockParametroDDA(true);

        BoletoDDA boletoDDA = getBoletoDDA();

        when(boletoDDADao.obterBoletoDDA(CODIGO_BARRAS_237, null, Boolean.TRUE)).thenReturn(boletoDDA);
        when(parametroDAO.obterValor(ParametroDDA.VALIDADE_CONSULTA_CIP, Constantes.ID_SICOOB)).thenReturn("A2");

        Assert.assertEquals("integracaocip.erro.tipo.validade.desconhecido",
                obterBoleto(CODIGO_BARRAS_237, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto DDA com o tipo de modelo de cálculo nulo (DDA ativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    @Ignore
    public void obterBoletoDDATipoModeloCalculoNull() throws ComumNegocioException, ComumException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockParametroDDA(true);

        BoletoDDA boletoDDA = getBoletoDDA();
        boletoDDA.setCodTipoModeloCalculo(null);

        when(boletoDDADao.obterBoletoDDA(CODIGO_BARRAS_237, null, Boolean.TRUE)).thenReturn(boletoDDA);
        when(parametroDAO.obterValor(ParametroDDA.VALIDADE_CONSULTA_CIP, Constantes.ID_SICOOB)).thenReturn(VALIDADE_CONSULTA_CIP);

        Assert.assertEquals("integracaocip.tipo.modelo.calculo.nulo",
                obterBoleto(CODIGO_BARRAS_237, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM));
    }

    /**
     * Método responsável por testar a consulta de um boleto DDA de outro banco quando for pagador eletrônico (DDA ativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoDDAPagadorEletronico() throws ComumNegocioException, ComumException {
        Calendar dataOntem = Calendar.getInstance();
        dataOntem.add(Calendar.DATE, -1);

        BoletoDDA boletoDDA = getBoletoDDA();
        boletoDDA.setDataHoraUltimaAtualizacao(new DateTimeDB(dataOntem.getTimeInMillis()));

        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(ObjectUtil.converterValor(boletoDDA.getCodTipoPessoaPagador()), boletoDDA.getNumCpfCnpjPagador(), Boolean.TRUE))
                .thenReturn(true);

        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterBoleto(CODIGO_BARRAS_237, boletoDDA));
    }

    /**
     * Método responsável por testar a consulta de um boleto DDA de outros bancos que esteja no período de validade (DDA ativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoDDAOutrosBancosValido() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterBoleto(CODIGO_BARRAS_237, getBoletoDDA()));
    }

    /**
     * Método responsável por testar a consulta de um boleto DDA 756 (DDA ativo)
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public void obterBoletoDDA756() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterBoleto(CODIGO_BARRAS_756, getBoletoDDA()));
    }

    /**
     * Método responsável por simular obter o boleto da tabela Título
     * 
     * @param codigoBarras
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private String obterBoleto(String codigoBarras, BoletoDDA boletoDDA) throws ComumException, ComumNegocioException {
        mockValidarDataUtil();
        mockObterInstituicao();
        mockParametroDDA(true);

        mockObterDataUtil(boletoDDA.getDataVencimento(), boletoDDA.getDataVencimento(), false);

        when(boletoDDADao.obterBoletoDDA(codigoBarras, null, Boolean.TRUE)).thenReturn(boletoDDA);
        when(parametroDAO.obterValor(ParametroDDA.VALIDADE_CONSULTA_CIP, Constantes.ID_SICOOB)).thenReturn(VALIDADE_CONSULTA_CIP);
        when(boletoDDADao.permiteAlterarValor(boletoDDA.getCodAutorizacaoValorDivergente())).thenReturn(false);
        when(admDelegate.obterNomeInstituicaoFinanceiraCache(boletoDDA.getCodIspbPartDestinatario())).thenReturn(Constantes.TESTE_SUCESSO);

        obterBoleto(codigoBarras, Constantes.DATE_AUX, Constantes.SHORT_UM, Constantes.INTEGER_ZERO, Constantes.INTEGER_UM);

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar obter o boleto.
     * 
     * @param linhaDigitavelCodigoBarras
     * @param dataPagamento
     * @param idCanal
     * @param numPA
     * @param numCooperativa
     * @param numContaCorrente
     * @return
     */
    private String obterBoleto(String linhaDigitavelCodigoBarras, Date dataPagamento, Short idCanal, Integer numPA, Integer numCooperativa) {
        try {
            mockObterInstituicao();
            ejb.obterBoleto(linhaDigitavelCodigoBarras, dataPagamento, idCanal, numPA, numCooperativa, null, null);
        } catch (Exception e) {
            return e.getMessage();
        }
        return Constantes.TESTE_FALHA;
    }

    /**
     * Método responsável por fazer o mock de obter a data útil
     * 
     * @param dataParametro
     * @param dataRetorno
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void mockObterDataUtil(Date dataParametro, Date dataRetorno) throws ComumException, ComumNegocioException {
        mockObterDataUtil(dataParametro, dataRetorno, true);
    }

    /**
     * Método responsável por fazer o mock da data util
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void mockValidarDataUtil() throws ComumException, ComumNegocioException {
        when(dao.isDataUtil(anyInt(), any(Date.class))).thenReturn(true);
    }

    /**
     * Método responsável por fazer o mock de obter a data útil
     * 
     * @param dataParametro
     * @param dataRetorno
     * @param arredondar
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void mockObterDataUtil(Date dataParametro, Date dataRetorno, boolean arredondar) throws ComumException, ComumNegocioException {
        Date dataSemHora = arredondar ? DateUtil.obterDataSemHora(dataParametro) : dataParametro;
        Date dataRetornoSemHora = arredondar ? DateUtil.obterDataSemHora(dataRetorno) : dataRetorno;

        when(dao.obterDataUtil(Constantes.INTEGER_UM, Constantes.INTEGER_ZERO, dataSemHora)).thenReturn(dataRetornoSemHora);
    }

    /**
     * Método responsável por fazer o mock do parametro DDA
     * 
     * @param retorno
     * @throws ComumException
     */
    private void mockParametroDDA(boolean retorno) throws ComumException {
        when(parametroDAO.obterValorBoolean(ParametroDDA.SICOOBDDA_ATIVADO, Constantes.INTEGER_UM)).thenReturn(retorno);
    }

    /**
     * Método responsável por fazer o mock do obter instituicao
     * 
     * @throws ComumException
     */
    private void mockObterInstituicao() throws ComumException {
        when(sciDelegate.obterInstituicaoPorCooperativaCache(Constantes.INTEGER_UM)).thenReturn(getInstituicaoDto());
    }

    /**
     * Método responsável por fazer o mock do pagador eletrônico
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    private void mockPagadorEletronico() throws ComumNegocioException, ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(any(Character.class), any(String.class), any(Boolean.class))).thenReturn(Boolean.FALSE);
    }

    /**
     * Método responsável por criar o dto
     * 
     * @return
     */
    private InstituicaoDto getInstituicaoDto() {
        return new InstituicaoDto(Constantes.INTEGER_UM);
    }

    /**
     * Método responsável por criar o dto
     * 
     * @return
     */
    private ConsultaBoletoDDADto getConsultaBoletoDDADto() {
        return new ConsultaBoletoDDADto();
    }

    /**
     * Método responsável por criar o dto
     * 
     * @return
     */
    private BoletoDDA getBoletoDDA() {
        BoletoDDA boletoDDA = new BoletoDDA();
        boletoDDA.setId(Constantes.LONG_UM);
        boletoDDA.setDataHoraUltimaAtualizacao(Constantes.DATE_TIME_DB_AUX);
        boletoDDA.setDataVencimento(Constantes.DATE_TIME_DB_AUX);
        boletoDDA.setDataLimitePagamento(Constantes.DATE_TIME_DB_AUX);
        boletoDDA.setValorBoleto(Constantes.CEM);
        boletoDDA.setBolPagamentoParcial(false);
        boletoDDA.setCodTipoModeloCalculo(TipoModeloCalculo.INSTITUICAO_DESTINATARIA_CALCULA_TITULOS_A_VENCER_E_VENCIDOS);
        boletoDDA.setBolBloqueioPagamento(false);
        return boletoDDA;
    }

}
