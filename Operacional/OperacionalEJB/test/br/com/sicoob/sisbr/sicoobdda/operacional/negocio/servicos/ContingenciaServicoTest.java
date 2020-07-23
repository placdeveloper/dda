/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servico
 * Arquivo:         ContingenciaServicoTest.java
 * Data Criação:    Feb 7, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.BRANCO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.INTEGER_UM;
import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.LONG_UM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

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

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoContingenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BoletoPagamentoContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ContingenciaServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao;

/**
 * ContingenciaServicoTest é responsável por
 * 
 * @author Danilo.Barros
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoCipFabricaDelegates.class, InformacoesUsuario.class })
public class ContingenciaServicoTest extends Mockito {

    @Mock
    private EntityManager entityManager;

    @Mock
    private ContingenciaDao contingenciaDao;

    @Mock
    private ParametroDao parametroDao;

    @Mock
    private IntegracaoCipFabricaDelegates cipFabricaDelegates;

    @Mock
    private MensagemDDADelegate mensagemDDADelegate;

    @InjectMocks
    private ContingenciaServicoEJB contingenciaServicoEJB;

    private ContingenciaDto contingenciaDto = new ContingenciaDto(new Date(), Long.valueOf(TipoContingenciaEnum.CIP.getCodContingencia()), BRANCO, LONG_UM, Boolean.FALSE);

    private InformacoesUsuario infoUsuario = new InformacoesUsuario(geraUsuarioBancoob());

    /**
     * Método responsável por
     * 
     * @throws java.lang.Exception void
     * 
     */
    @Before
    public void setUp() throws Exception {
        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(cipFabricaDelegates);
        when(cipFabricaDelegates.getMensagemDDADelegate()).thenReturn(mensagemDDADelegate);
        doNothing().when(mensagemDDADelegate).incluir(any(IMensagemDDA.class), anyString(), any(DateTimeDB.class), any(String.class), any(Integer.class), any(Short.class));

        PowerMockito.mockStatic(InformacoesUsuario.class);
        when(InformacoesUsuario.getInstance()).thenReturn(infoUsuario);
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ContingenciaServicoEJB#incluirContingencia(br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto)}
     * .
     * 
     * @throws BancoobException
     */
    @Ignore
    @Test
    public final void testIncluirContingenciaHabilitacao() throws BancoobException {
        doNothing().when(contingenciaDao).incluirContingencia(any(HistoricoContingencia.class));
        doNothing().when(parametroDao).atualizarValorParametro(anyLong(), anyInt(), anyString());

        contingenciaDto.setContingenciaHabilitada(Boolean.TRUE);
        contingenciaServicoEJB.incluirContingencia(contingenciaDto, CanalEnum.RETAGUARDA.getId());

        verify(contingenciaDao, atLeastOnce()).incluirContingencia(any(HistoricoContingencia.class));
        verify(parametroDao, atLeastOnce()).atualizarValorParametro(anyLong(), anyInt(), anyString());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ContingenciaServicoEJB#incluirContingencia(br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto)}
     * .
     * 
     * @throws BancoobException
     */
    @Ignore
    @Test
    public final void testIncluirContingenciaDesabilitacao() throws BancoobException {
        List<BoletoPagamentoContingenciaDto> listaBoletoPagamentoContingenciaDtos = new ArrayList<BoletoPagamentoContingenciaDto>();

        when(entityManager.find(HistoricoContingencia.class, LONG_UM)).thenReturn(
                new HistoricoContingencia(new DateTimeDB(), new TipoContingencia(Long.valueOf(TipoContingenciaEnum.CIP.getCodContingencia())), BRANCO, null, Boolean.TRUE));
        when(contingenciaDao.obterIdUltimaHabilitacaoContingencia()).thenReturn(LONG_UM);
        when(contingenciaDao.listarBoletosPagamentoContingencia()).thenReturn(listaBoletoPagamentoContingenciaDtos);
        doNothing().when(contingenciaDao).atualizarMensagensBaixaOperacional();
        doNothing().when(contingenciaDao).incluirContingencia(any(HistoricoContingencia.class));
        doNothing().when(parametroDao).atualizarValorParametro(anyLong(), anyInt(), anyString());

        listaBoletoPagamentoContingenciaDtos.add(new BoletoPagamentoContingenciaDto(new Date(), BRANCO));
        contingenciaDto.setContingenciaHabilitada(Boolean.FALSE);
        contingenciaServicoEJB.incluirContingencia(contingenciaDto, CanalEnum.RETAGUARDA.getId());

        verify(entityManager, atLeastOnce()).find(HistoricoContingencia.class, LONG_UM);
        verify(contingenciaDao, atLeastOnce()).obterIdUltimaHabilitacaoContingencia();
        verify(contingenciaDao, atLeastOnce()).listarBoletosPagamentoContingencia();
        verify(contingenciaDao, atLeastOnce()).atualizarMensagensBaixaOperacional();
        verify(contingenciaDao, atLeastOnce()).incluirContingencia(any(HistoricoContingencia.class));
        verify(parametroDao, atLeastOnce()).atualizarValorParametro(anyLong(), anyInt(), anyString());
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ContingenciaServicoEJB#listarHistoricoContingencias()}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testListarHistoricoContingencias() throws ComumException {
        List<GradeContingenciaDto> listaGradeContingenciaDtos = new ArrayList<GradeContingenciaDto>();

        when(contingenciaDao.listarHistoricoContingencias()).thenReturn(listaGradeContingenciaDtos);

        contingenciaServicoEJB.listarHistoricoContingencias();

        verify(contingenciaDao, atLeastOnce()).listarHistoricoContingencias();
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ContingenciaServicoEJB#obterValorInteger(java.lang.Long, java.lang.Integer)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterValorInteger() throws ComumException {
        when(parametroDao.obterValorInteger(anyLong(), anyInt())).thenReturn(INTEGER_UM);

        contingenciaServicoEJB.obterValorInteger(LONG_UM, INTEGER_UM);

        verify(parametroDao, atLeastOnce()).obterValorInteger(anyLong(), anyInt());
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.ContingenciaServicoEJB#gerarRelatorioHistoricoContingencia()}.
     */
    @Test
    public final void testGerarRelatorioHistoricoContingencia() {
        Assert.assertTrue(Boolean.TRUE);
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
