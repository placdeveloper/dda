package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.InstituicaoDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDALancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateio;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateioLancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoCCODto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoRetDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.EventoTarifavelDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioDDALancamentoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ServicosTestUtil;
import junit.framework.Assert;

/**
 * RateioTarifaCipServicoTest é responsável por
 * 
 * @author rodrigo.neri
 */
@RunWith(PowerMockRunner.class)
public class RateioTarifaCipServicoTest extends ServicosTestUtil {

    @InjectMocks
    private RateioTarifasCipServicoEJB ejb;

    @Mock
    private RateioTarifasCipDao dao;

    @Mock
    private InstituicaoDao instituicaoDao;

    @Mock
    private EventoTarifavelDDADao eventoTarifavelDDADao;

    @Mock
    private RateioDDALancamentoDao lancamentoDao;

    /**
     * Método responsável por testar
     */
    @Test
    public void testarObterDadosControleRateioPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterDadosControleRateio());
    }

    /**
     * Método responsável por testar
     * 
     * @return
     */
    private String obterDadosControleRateio() {
        SituacaoRateio situacao = new SituacaoRateio();
        situacao.setCodSituacaoRateio(SituacaoRateio.AGUARDANDO_APROVACAO);

        RateioDDA rateio = new RateioDDA();
        rateio.setId(Constantes.LONG_UM);
        rateio.setSituacaoRateio(situacao);

        try {
            when(dao.obterRateioAtual()).thenReturn(rateio);

            ejb.obterDadosControleRateio();

            verify(dao, times(1)).obterRateioAtual();
            verify(dao, times(1)).listarEventosRateio(rateio.getId());

            verify(eventoTarifavelDDADao, times(1)).listarEventoTarifavelDDAParcial();

            verify(parametroDao, times(1)).obterValorDouble(ParametroDDA.DESVIO_PADRAO_RATEIO_TARIFAS, Constantes.ID_SICOOB);
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarPesquisarEventosDisponiveisParametroNull() {
        Assert.assertEquals("integracaocip.parametro.nao.informado", pesquisarEventosDisponiveis(true));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarPesquisarEventosDisponiveisPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, pesquisarEventosDisponiveis(false));
    }

    /**
     * Método responsável por testar
     * 
     * @return
     */
    private String pesquisarEventosDisponiveis(boolean parametroNulo) {
        ConsultaEventoRateioDto dto = null;

        if (!parametroNulo) {
            dto = new ConsultaEventoRateioDto();
        }

        try {
            if (!parametroNulo) {
                when(dao.pesquisarEventosDisponiveis(dto)).thenReturn(null);
            }

            ejb.pesquisarEventosDisponiveis(dto);

            if (!parametroNulo) {
                verify(dao, times(1)).pesquisarEventosDisponiveis(dto);
            }
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarIncluirListaEventoConsolidadoRateioParametroNull() {
        Assert.assertEquals("integracaocip.parametro.nao.informado", incluirListaEventoConsolidadoRateioParametroNull());
    }

    /**
     * Método responsável por testar
     * 
     * @return
     */
    private String incluirListaEventoConsolidadoRateioParametroNull() {
        List<EventoRateioDto> lista = null;

        try {
            ejb.incluirListaEventoConsolidadoRateio(lista);
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarIncluirListaEventoConsolidadoRateioAprovadoFalhou() {
        Assert.assertEquals("operacional.rateio.nao.possivel.incluir.evento", incluirListaEventoConsolidadoRateio(true, false));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarIncluirListaEventoConsolidadoRateioCriarRateioPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirListaEventoConsolidadoRateio(false, true));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarIncluirListaEventoConsolidadoRateioPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirListaEventoConsolidadoRateio(false, false));
    }

    /**
     * Método responsável por testar
     * 
     * @param parametroNulo
     * @param rateioAprovado
     * @return
     */
    @SuppressWarnings("unchecked")
    private String incluirListaEventoConsolidadoRateio(boolean rateioAprovado, boolean incluirRateio) {
        List<EventoRateioDto> lista = null;
        RateioDDA rateio = null;
        SituacaoRateio situacao = null;

        lista = new ArrayList<EventoRateioDto>(1);
        lista.add(new EventoRateioDto());

        situacao = new SituacaoRateio();

        if (rateioAprovado) {
            situacao.setCodSituacaoRateio(SituacaoRateio.APROVADO_PARA_EFETIVACAO);
        } else {
            situacao.setCodSituacaoRateio(SituacaoRateio.AGUARDANDO_APROVACAO);
        }

        if (!incluirRateio) {
            rateio = new RateioDDA();
            rateio.setSituacaoRateio(situacao);
        }

        try {
            when(dao.obterRateioAtual()).thenReturn(rateio);

            if (incluirRateio) {
                when(em.find(SituacaoRateio.class, SituacaoRateio.AGUARDANDO_APROVACAO)).thenReturn(situacao);
                when(dao.incluir(any(RateioDDA.class))).thenReturn(null);
            }

            ejb.incluirListaEventoConsolidadoRateio(lista);

            verify(dao, times(1)).obterRateioAtual();
            verify(dao, times(1)).incluirListaEventoConsolidadoRateio(anyList(), anyLong(), anyInt(), anyString());

            if (incluirRateio) {
                verify(em, times(1)).find(SituacaoRateio.class, SituacaoRateio.AGUARDANDO_APROVACAO);
                verify(dao, times(1)).incluir(any(RateioDDA.class));
            }
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarRemoverListaEventoConsolidadoRateioParametroNull() {
        Assert.assertEquals("integracaocip.parametro.nao.informado", removerListaEventoConsolidadoRateio(true));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarRemoverListaEventoConsolidadoRateioPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, removerListaEventoConsolidadoRateio(false));
    }

    /**
     * Método responsável por testar
     * 
     * @param parametroNulo
     * @return
     */
    private String removerListaEventoConsolidadoRateio(boolean parametroNulo) {
        List<EventoRateioDto> lista = null;

        if (!parametroNulo) {
            lista = new ArrayList<EventoRateioDto>(1);
            lista.add(new EventoRateioDto());
        }

        try {
            ejb.removerListaEventoConsolidadoRateio(lista);

            if (!parametroNulo) {
                verify(dao, times(1)).removerListaEventoConsolidadoRateio(lista);
            }
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAtualizarEventoConsolidadoDesvioNull() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, atualizarEventoConsolidado(null, true));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAtualizarEventoConsolidadoDesvioZero() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, atualizarEventoConsolidado(BigDecimal.ZERO, true));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAtualizarEventoConsolidadoListaNull() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, atualizarEventoConsolidado(BigDecimal.TEN, true));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAtualizarEventoConsolidadoPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, atualizarEventoConsolidado(BigDecimal.TEN, false));
    }

    /**
     * Método responsável por testar
     * 
     * @param listaNula
     * @return
     */
    private String atualizarEventoConsolidado(BigDecimal desvioPadrao, boolean listaNula) {
        List<EventoRateioDto> lista = null;

        if (!listaNula) {
            lista = new ArrayList<EventoRateioDto>(1);
            lista.add(new EventoRateioDto());
        }

        try {
            ejb.atualizarEventoConsolidado(desvioPadrao, lista);

            if (ObjectUtil.isEmpty(desvioPadrao)) {
                verify(parametroDao, times(1)).atualizarValorParametro(ParametroDDA.DESVIO_PADRAO_RATEIO_TARIFAS, Constantes.ID_SICOOB, Constantes.STRING_NUMERO_0);
            } else {
                verify(parametroDao, times(1)).atualizarValorParametro(ParametroDDA.DESVIO_PADRAO_RATEIO_TARIFAS, Constantes.ID_SICOOB, desvioPadrao.toString());
            }

            if (!listaNula) {
                verify(dao, times(1)).atualizarEventoConsolidado(lista);
            }
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAprovarRateioNullFalha() {
        Assert.assertEquals("operacional.nao.existe.rateio.aguardando.aprovacao", aprovarRateio(null));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAprovarRateioAprovadoEfetivacaoFalha() {
        Assert.assertEquals("operacional.nao.existe.rateio.aguardando.aprovacao", aprovarRateio(SituacaoRateio.APROVADO_PARA_EFETIVACAO));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAprovarRateioExisteRateioProcessandoFalha() {
        Assert.assertEquals("operacional.rateio.em.processamento", aprovarRateio(SituacaoRateio.AGUARDANDO_APROVACAO, true, false));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAprovarRateioListaEventoNull() {
        Assert.assertEquals("operacional.rateio.nao.possui.evento.consolidado", aprovarRateio(SituacaoRateio.AGUARDANDO_APROVACAO, false, true));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarAprovarRateioPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, aprovarRateio(SituacaoRateio.AGUARDANDO_APROVACAO, false, false));
    }

    /**
     * Método responsável por testar
     * 
     * @param codSituacaoRateio
     * @return
     */
    private String aprovarRateio(Long codSituacaoRateio) {
        return aprovarRateio(codSituacaoRateio, false, false);
    }

    /**
     * Método responsável por testar
     * 
     * @param codSituacaoRateio
     * @param existeRateioProcessando
     * @param listaEventoNula
     * @return
     */
    private String aprovarRateio(Long codSituacaoRateio, boolean existeRateioProcessando, boolean listaEventoNula) {
        RateioDDA rateio = null;
        SituacaoRateio situacao = null;

        if (!ObjectUtil.isNull(codSituacaoRateio)) {
            situacao = new SituacaoRateio();
            situacao.setCodSituacaoRateio(codSituacaoRateio);

            rateio = new RateioDDA();
            rateio.setId(Constantes.LONG_UM);
            rateio.setSituacaoRateio(situacao);
        }

        try {
            when(dao.obterRateioAtual()).thenReturn(rateio);

            when(em.find(SituacaoRateio.class, SituacaoRateio.AGUARDANDO_APROVACAO)).thenReturn(situacao);

            if (!ObjectUtil.isNull(codSituacaoRateio)) {
                when(dao.existeRateioProcessando()).thenReturn(existeRateioProcessando);
                when(dao.possuiListaEventoConsolidado(rateio.getId())).thenReturn(!listaEventoNula);
            }

            ejb.aprovarRateio(Constantes.STRING_LETRA_S, Constantes.INTEGER_UM);

            verify(dao, times(1)).obterRateioAtual();
            verify(dao, times(1)).existeRateioProcessando();
            verify(dao, times(1)).possuiListaEventoConsolidado(rateio.getId());
            verify(em, times(1)).find(SituacaoRateio.class, SituacaoRateio.APROVADO_PARA_EFETIVACAO);
            verify(dao, times(1)).alterar(rateio);
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarCancelarAprovacaoRateioNull() {
        Assert.assertEquals("operacional.nao.existe.rateio.aprovado.efetivacao", cancelarAprovacaoRateio(null));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarCancelarAprovacaoRateioAguardandoAprovacaoFalha() {
        Assert.assertEquals("operacional.nao.existe.rateio.aprovado.efetivacao", cancelarAprovacaoRateio(SituacaoRateio.AGUARDANDO_APROVACAO));
    }

    /**
     * Método responsável por testar
     */
    @Test
    public void testarCancelarAprovacaoRateioPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, cancelarAprovacaoRateio(SituacaoRateio.APROVADO_PARA_EFETIVACAO));
    }

    /**
     * Método responsável por testar
     * 
     * @return
     */
    private String cancelarAprovacaoRateio(Long codSituacaoRateio) {
        RateioDDA rateio = null;

        if (!ObjectUtil.isNull(codSituacaoRateio)) {
            SituacaoRateio situacao = new SituacaoRateio();
            situacao.setCodSituacaoRateio(codSituacaoRateio);

            rateio = new RateioDDA();
            rateio.setSituacaoRateio(situacao);
        }

        try {
            when(dao.obterRateioAtual()).thenReturn(rateio);

            ejb.cancelarAprovacaoRateio();

            verify(dao, times(1)).obterRateioAtual();
            verify(em, times(1)).find(SituacaoRateio.class, SituacaoRateio.AGUARDANDO_APROVACAO);
            verify(dao, times(1)).alterar(rateio);
        } catch (BancoobException e) {
            return e.getMessage();
        }

        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void testarConsolidarEventosTarifaveisPassou() {
        try {
            doNothing().when(dao).consolidarEventosTarifaveis();
            ejb.consolidarEventosTarifaveis();
            verify(dao, times(Constantes.INTEGER_UM)).consolidarEventosTarifaveis();
        } catch (ComumException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testarConsolidarLancamentosCCOPassou() {
        try {
            doNothing().when(dao).consolidarLancamentosCCO();
            ejb.consolidarLancamentosCCO();
            verify(dao, times(Constantes.INTEGER_UM)).consolidarLancamentosCCO();
        } catch (ComumException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testarEfetivarLancamentosRateioCCOValidarCamposObrigatorios() {
        try {
            ejb.efetivarLancamentosRateioCCO(new RateioCreditoLancamentoCCODto(Constantes.LONG_UM, Constantes.INTEGER_UM, String.valueOf(Constantes.INTEGER_UM), new Date(), Constantes.LONG_MIL));
            verify(dao, times(1)).listarLancamentosRateioCCO(anyLong(), anyInt(), anyLong());
        } catch (BancoobException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(expected = ComumNegocioException.class)
    public void testarEfetivarLancamentosRateioCCODtoNull() throws ComumNegocioException {
        try {
            ejb.efetivarLancamentosRateioCCO(null);
        } catch (ComumException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testarEfetivarLancamentosRateioCCOPassou() {
        try {
            RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto = new RateioCreditoLancamentoCCODto(Constantes.LONG_UM, Constantes.INTEGER_UM, Constantes.STRING_NUMERO_1,
                    new Date(), Constantes.LONG_MIL);
            List<LancamentoIntegracaoDto> listaLancamentosIntegracaoDto = new ArrayList<LancamentoIntegracaoDto>();

            LancamentoIntegracaoDto lancamentoIntegracaoDto = new LancamentoIntegracaoDto();
            lancamentoIntegracaoDto.setIdInstituicao(Constantes.INTEGER_UM);
            listaLancamentosIntegracaoDto.add(lancamentoIntegracaoDto);

            LancamentoIntegracaoRetDto lancamentoIntegracaoRetDto = new LancamentoIntegracaoRetDto();
            lancamentoIntegracaoRetDto.setCodRetorno(Constantes.INTEGER_UM);

            List<LancamentoIntegracaoCCODto> listaLancamentosIntegracaoCCODto = new ArrayList<LancamentoIntegracaoCCODto>();
            LancamentoIntegracaoCCODto lancamentoIntegracaoCCODto = new LancamentoIntegracaoCCODto();
            lancamentoIntegracaoCCODto.setLancamentoIntegracaoDto(lancamentoIntegracaoDto);
            listaLancamentosIntegracaoCCODto.add(lancamentoIntegracaoCCODto);
            rateioCreditoLancamentoCCODto.setCodSituacaoRateioLancamento(Constantes.LONG_ZERO);
            when(
                    dao.listarLancamentosRateioCCO(rateioCreditoLancamentoCCODto.getIdRateioDDA(), rateioCreditoLancamentoCCODto.getIdInstituicao(),
                            rateioCreditoLancamentoCCODto.getIdRateioDDALancamento())).thenReturn(listaLancamentosIntegracaoDto);
            when(contaCorrenteDelegate.gravarLancamentoIntegracao(lancamentoIntegracaoDto)).thenReturn(lancamentoIntegracaoRetDto);
            doNothing().when(dao).atualizarLancamentosRateioCCO(rateioCreditoLancamentoCCODto, listaLancamentosIntegracaoCCODto);
            when(instituicaoDao.obterIdInstituicaoPai(anyInt())).thenReturn(new InstituicaoDto());
            ejb.efetivarLancamentosRateioCCO(rateioCreditoLancamentoCCODto);

            verify(dao, times(1)).listarLancamentosRateioCCO(anyLong(), anyInt(), anyLong());
            verify(dao, times(1)).atualizarLancamentosRateioCCO(any(RateioCreditoLancamentoCCODto.class), anyListOf(LancamentoIntegracaoCCODto.class));

        } catch (BancoobException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(expected = ComumNegocioException.class)
    public void testarAtualizarSituacaoRateioCreditoCCONull() throws ComumNegocioException {
        try {
            ejb.atualizarSituacaoRateioCreditoCCO(null, null);
        } catch (ComumException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testarAtualizarSituacaoRateioCreditoCCOPassou() {
        try {
            doNothing().when(dao).atualizarSituacaoRateioCreditoCCO(anyLong(), anyLong());
            ejb.atualizarSituacaoRateioCreditoCCO(Constantes.LONG_UM, Constantes.LONG_UM);
            verify(dao, times(Constantes.INTEGER_UM)).atualizarSituacaoRateioCreditoCCO(anyLong(), anyLong());
        } catch (BancoobException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB#pesquisarDadosRateio(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testPesquisarDadosRateio() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testPesquisarDadosRateioEJB());
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    public String testPesquisarDadosRateioEJB() throws ComumException {
        try {
            when(dao.listarDadosRateio(new VisualizaRateioTarifaDto())).thenReturn(new ArrayList<VisualizaRateioTarifaDto>());
            when(dao.obterTotalDadosRateio(new VisualizaRateioTarifaDto())).thenReturn(new ArrayList<VisualizaRateioTarifaDto>());
            ejb.pesquisarDadosRateio(new VisualizaRateioTarifaDto());
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB#pesquisarLancamento(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)}
     * .
     */
    @Test
    public final void testPesquisarLancamento() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testPesquisarLancamentoEJB());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testPesquisarLancamentoEJB() {
        try {
            when(dao.listarDadosLancamento(new VisualizaRateioTarifaDto())).thenReturn(new ArrayList<VisualizaRateioTarifaDto>());
            ejb.pesquisarLancamento(new VisualizaRateioTarifaDto());
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB#obterDadosLancamentoRateioDDA(java.lang.Long)}.
     */
    @Test
    public final void testObterDadosLancamentoRateioDDA() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testObterDadosLancamentoRateioDDAEJB());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testObterDadosLancamentoRateioDDAEJB() {
        try {
            InstituicaoDto instituicaoDto = new InstituicaoDto();
            instituicaoDto.setNumCooperativa(Constantes.NUM_COOP_0001);
            when(dao.obterDadosLancamentoRateioDDA(Constantes.LONG_UM)).thenReturn(new VisualizaRateioTarifaDto());
            when(instituicaoDao.obterIdInstituicaoPai(anyInt())).thenReturn(instituicaoDto);
            ejb.obterDadosLancamentoRateioDDA(Constantes.LONG_UM);
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB#detalharRateioTarifa(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)}
     * .
     */
    @Test
    public final void testDetalharRateioTarifa() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testDetalharRateioTarifaEJB());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testDetalharRateioTarifaEJB() {
        try {
            when(dao.listarDadosRateio(new VisualizaRateioTarifaDto())).thenReturn(new ArrayList<VisualizaRateioTarifaDto>());
            when(dao.obterTotalDadosRateio(new VisualizaRateioTarifaDto())).thenReturn(new ArrayList<VisualizaRateioTarifaDto>());
            when(dao.listarDadosLancamento(new VisualizaRateioTarifaDto())).thenReturn(new ArrayList<VisualizaRateioTarifaDto>());
            when(lancamentoDao.listarSituacaoRateioLancamento()).thenReturn(new ArrayList<SituacaoRateioLancamento>());

            ejb.detalharRateioTarifa(new VisualizaRateioTarifaDto());
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB#pesquisarRateioTarifaDDAPaginado(br.com.bancoob.negocio.dto.ConsultaDto)}
     * .
     */
    @Test
    public final void testPesquisarRateioTarifaDDAPaginado() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testPesquisarRateioTarifaDDAPaginadoEJB());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testPesquisarRateioTarifaDDAPaginadoEJB() {
        try {
            when(dao.countRateioTarifaDDA(new VisualizaRateioTarifaDto())).thenReturn(Constantes.INTEGER_UM);
            when(dao.pesquisarRateioTarifaDDAPaginado(new VisualizaRateioTarifaDto())).thenReturn(new ArrayList<VisualizaRateioTarifaDto>());
            ConsultaDto consultaDto = new ConsultaDto();
            consultaDto.setPagina(Constantes.INTEGER_UM);
            consultaDto.setTamanhoPagina(Constantes.INTEGER_UM);
            consultaDto.setFiltro(new VisualizaRateioTarifaDto());
            ejb.pesquisarRateioTarifaDDAPaginado(consultaDto);
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB#listaSituacaoRateio()}.
     */
    @Test
    public final void testListaSituacaoRateio() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testDetalharRateioTarifaEJB());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testListaSituacaoRateioEJB() {
        try {
            when(dao.listaSituacaoRateio()).thenReturn(new ArrayList<SituacaoRateio>());
            ejb.listaSituacaoRateio();
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB#efetivarLancamentosRateioCCO(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto)}
     * .
     */
    @Test
    public final void testEfetivarLancamentosRateioCCO() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testEfetivarLancamentosRateioCCOEJB());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testEfetivarLancamentosRateioCCOEJB() {
        try {
            when(dao.listaSituacaoRateio()).thenReturn(new ArrayList<SituacaoRateio>());
            ejb.listaSituacaoRateio();
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.RateioTarifasCipServicoEJB#efetivacaoManualLancamento(java.lang.Long)}.
     */
    @Test
    public final void testEfetivacaoManualLancamento() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, testEfetivarLancamentosRateioCCOEJB());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    public String testEfetivacaoManualLancamentoEJB() {
        try {
            when(lancamentoDao.obterLancamento(Constantes.LONG_UM)).thenReturn(new RateioDDALancamento());
            ejb.efetivacaoManualLancamento(Constantes.LONG_UM);
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }
}
