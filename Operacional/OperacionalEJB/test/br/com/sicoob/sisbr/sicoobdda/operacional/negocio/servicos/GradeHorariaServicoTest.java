/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         TipoGradeHorariaServicoTest.java
 * Data Criação:    Out 03, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm.ProdutoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.SubTipoGrade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoGradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDA0401Delegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.GradeHorariaOrigemDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.TipoGradeHorariaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.GradeHorariaServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.GradeHorariaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoGradeHorariaDao;

/**
 * GradeHorariaServicoTest
 * 
 * @author samuell.ramos
 */
@RunWith(MockitoJUnitRunner.class)
public class GradeHorariaServicoTest extends Mockito {

    @InjectMocks
    private GradeHorariaServicoEJB ejb;

    @Mock
    private GradeHorariaDao dao;

    @Mock
    private TipoGradeHorariaDao tipoGradeHorariaDao;

    @Mock
    private ADMDelegate admDelegate;

    @Mock
    private EntityManager em;

    @SuppressWarnings("unused")
    @Mock
    private MensagemDDA0401Delegate mensagemDelegate;

    private static final int VALIDACAO_DATA_REFERENCIA = 1;
    private static final int VALIDACAO_COD_TIPO_GRADE_HORARIA = 2;
    private static final int VALIDACAO_COD_NAO_CADASTRADO = 3;
    private static final int VALIDACAO_DATA_HORA_ABERTURA = 4;
    private static final int VALIDACAO_DATA_HORA_FECHAMENTO = 5;
    private static final int VALIDACAO_HORA_ABERTURA = 6;
    private static final int VALIDACAO_HORA_FECHAMENTO = 7;
    private static final int VALIDACAO_MINUTO_ABERTURA = 8;
    private static final int VALIDACAO_MINUTO_FECHAMENTO = 9;
    private static final int VALIDACAO_DATA_INVALIDA = 10;
    private static final int VALIDACAO_INCLUSAO_DOIS_REGISTROS = 11;

    @Test
    public void verificarTipoGradePersonalizada() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, verificarTipoGradePersonalizadaEJBFluxoPadrao());
        Assert.assertEquals(Constantes.TESTE_SUCESSO, verificarTipoGradePersonalizadaEJBListasNull());
        Assert.assertEquals(Constantes.TESTE_SUCESSO, erroAoExcluirMsgAntigas());
    }

    private String verificarTipoGradePersonalizadaEJBFluxoPadrao() throws BancoobException {
        when(admDelegate.obterProdutoCobrancaBancoob()).thenReturn(gerarProdutoDto());
        when(tipoGradeHorariaDao.listarTipoGradeHorariaPersonalizadaSemGradeHoraria(any(DateTimeDB.class))).thenReturn(geraListaTipoGradeHorariaDto());
        when(dao.listarGradeHorariaPersonalizadaExtrapolada(any(DateTimeDB.class))).thenReturn(geraListaGradeHorariaOrigem());

        cadastrarGradeHorariaSicoobEJB();

        return Constantes.TESTE_SUCESSO;
    }

    private String verificarTipoGradePersonalizadaEJBListasNull() throws BancoobException {
        when(admDelegate.obterProdutoCobrancaBancoob()).thenReturn(gerarProdutoDto());
        when(tipoGradeHorariaDao.listarTipoGradeHorariaPersonalizadaSemGradeHoraria(any(DateTimeDB.class))).thenReturn(null);
        when(dao.listarGradeHorariaPersonalizadaExtrapolada(any(DateTimeDB.class))).thenReturn(null);

        cadastrarGradeHorariaSicoobEJB();

        return Constantes.TESTE_SUCESSO;
    }

    private String erroAoExcluirMsgAntigas() throws BancoobException {
        doThrow(Exception.class).when(dao).excluirGradesHorariasAntigas();
        cadastrarGradeHorariaSicoobEJB();

        return Constantes.TESTE_SUCESSO;
    }

    private String cadastrarGradeHorariaSicoobEJB() throws BancoobException {
        ejb.cadastrarGradeHoraria();

        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void listarTipoGradeHoraria() throws OperacionalException, OperacionalNoResultException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarTipoGradeHorariaEJB());
    }

    private String listarTipoGradeHorariaEJB() throws OperacionalException {
        List<TipoGradeHorariaDto> listaTipoGradeHorarias = new ArrayList<TipoGradeHorariaDto>();
        listaTipoGradeHorarias.add(geraTipoGradeHorariaDto());
        when(tipoGradeHorariaDao.listarTipoGradeHorariaPorCodigoSubtipo((short) 0)).thenReturn(listaTipoGradeHorarias);
        ejb.listarTipoGradeHoraria();
        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void incluirGradeHoraria() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, incluirGradeHorariaEJB(geraObjetoInclusao(), 0));
        Assert.assertEquals("integracaocip.campo.obrigatorio", incluirGradeHorariaEJB(geraObjetoInclusao(), 1));
        Assert.assertEquals("integracaocip.campo.obrigatorio", incluirGradeHorariaEJB(geraObjetoInclusao(), 2));
        Assert.assertEquals("Cód. Tipo Grade não cadastrado", incluirGradeHorariaEJB(geraObjetoInclusao(), 3));
        Assert.assertEquals("integracaocip.campo.obrigatorio", incluirGradeHorariaEJB(geraObjetoInclusao(), 4));
        Assert.assertEquals("integracaocip.campo.obrigatorio", incluirGradeHorariaEJB(geraObjetoInclusao(), 5));
        Assert.assertEquals("integracaocip.campo.obrigatorio", incluirGradeHorariaEJB(geraObjetoInclusao(), 6));
        Assert.assertEquals("integracaocip.campo.obrigatorio", incluirGradeHorariaEJB(geraObjetoInclusao(), 7));
        Assert.assertEquals("integracaocip.campo.obrigatorio", incluirGradeHorariaEJB(geraObjetoInclusao(), 8));
        Assert.assertEquals("integracaocip.campo.obrigatorio", incluirGradeHorariaEJB(geraObjetoInclusao(), 9));
        Assert.assertEquals("integracaocip.gradehoraria.invalida", incluirGradeHorariaEJB(geraObjetoInclusao(), 10));
        Assert.assertEquals("integracaocip.gradehoraria.invalida", incluirGradeHorariaEJB(geraObjetoInclusao(), 11));
        verifyIncluirGradeHoraria(1);
    }

    private String incluirGradeHorariaEJB(GradeHorariaDto gradeHorariaDto, int tipoValidacao) throws ComumException {
        when(em.find(GradeHoraria.class, geraGradeHoraria().getId())).thenReturn(geraGradeHoraria());
        when(tipoGradeHorariaDao.obterCountTipoGradeHoraria(geraTipoGradeHorariaDto().getCodTipoGradeHoraria())).thenReturn(1L);
        try {
            validacoesInclusao(tipoValidacao, gradeHorariaDto);
            ejb.incluirGradeHoraria(gradeHorariaDto);
        } catch (ComumNegocioException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    public void validacoesInclusao(int tipoValidacao, GradeHorariaDto gradeHorariaDto) throws ComumNegocioException, ComumException {
        switch (tipoValidacao) {
        case VALIDACAO_DATA_REFERENCIA:
            gradeHorariaDto.setDataReferencia(null);
            break;
        case VALIDACAO_COD_TIPO_GRADE_HORARIA:
            gradeHorariaDto.setCodTipoGradeHoraria(null);
            break;
        case VALIDACAO_COD_NAO_CADASTRADO:
            // Não existe o código cadastrado - -teste conncorrencia
            when(tipoGradeHorariaDao.obterCountTipoGradeHoraria(geraTipoGradeHorariaDto().getCodTipoGradeHoraria())).thenReturn(0L);
            break;
        case VALIDACAO_DATA_HORA_ABERTURA:
            gradeHorariaDto.getListaGradeHoraria().get(0).setDataHoraAbertura(null);
            break;
        case VALIDACAO_DATA_HORA_FECHAMENTO:
            gradeHorariaDto.getListaGradeHoraria().get(0).setDataHoraFechamento(null);
            break;
        case VALIDACAO_HORA_ABERTURA:
            gradeHorariaDto.getListaGradeHoraria().get(0).setHoraAbertura(null);
            break;
        case VALIDACAO_HORA_FECHAMENTO:
            gradeHorariaDto.getListaGradeHoraria().get(0).setHoraFechamento(null);
            break;
        case VALIDACAO_MINUTO_ABERTURA:
            gradeHorariaDto.getListaGradeHoraria().get(0).setMinutoAbertura(null);
            break;
        case VALIDACAO_MINUTO_FECHAMENTO:
            gradeHorariaDto.getListaGradeHoraria().get(0).setMinutoFechamento(null);
            break;
        case VALIDACAO_DATA_INVALIDA:
            Date data1Temp = DataUtil.converterStringToDate("28/11/2016 24:00:00", "dd/MM/yyyy kk:mm:ss");
            Date data2Temp = DataUtil.converterStringToDate("28/11/2016 24:30:00", "dd/MM/yyyy kk:mm:ss");

            DateTimeDB data1DB = DateUtil.converterStringToDateTimeDB("28/11/2016 24:00:00", "dd/MM/yyyy kk:mm:ss");
            DateTimeDB data2DB = (DateTimeDB) DateUtil.converterStringToDateTimeDB("28/11/2016 24:30:00", "dd/MM/yyyy kk:mm:ss");

            gradeHorariaDto.getListaGradeHoraria().get(0).setDataHoraAbertura(data1DB);
            gradeHorariaDto.getListaGradeHoraria().get(0).setDataHoraFechamento(data2DB);
            gradeHorariaDto.getListaGradeHoraria().get(0).setDataHoraAberturaTemp(data1Temp);
            gradeHorariaDto.getListaGradeHoraria().get(0).setDataHoraFechamentoTemp(data2Temp);

            break;
        case VALIDACAO_INCLUSAO_DOIS_REGISTROS:
            gradeHorariaDto.getListaGradeHoraria().add(geraGradeHorariaDto());
            DateTimeDB data1Abertura = DateUtil.converterStringToDateTimeDB("28/11/2016 24:00:00", "dd/MM/yyyy kk:mm:ss");
            DateTimeDB data1Fechamento = (DateTimeDB) DateUtil.converterStringToDateTimeDB("28/11/2016 24:30:00", "dd/MM/yyyy kk:mm:ss");

            DateTimeDB data2Abertura = DateUtil.converterStringToDateTimeDB("28/11/2016 24:00:00", "dd/MM/yyyy kk:mm:ss");
            DateTimeDB data2Fechamento = (DateTimeDB) DateUtil.converterStringToDateTimeDB("28/11/2016 24:30:00", "dd/MM/yyyy kk:mm:ss");

            gradeHorariaDto.getListaGradeHoraria().get(0).setDataHoraAbertura(data1Abertura);
            gradeHorariaDto.getListaGradeHoraria().get(0).setDataHoraFechamento(data1Fechamento);

            gradeHorariaDto.getListaGradeHoraria().get(1).setDataHoraAbertura(data2Abertura);
            gradeHorariaDto.getListaGradeHoraria().get(1).setDataHoraFechamento(data2Fechamento);

            break;
        }
    }

    @Test
    public void obterGradeHoraria() throws ComumException, OperacionalNoResultException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterGradeHorariaEJB());
    }

    private String obterGradeHorariaEJB() throws ComumException {
        List<TipoGradeHorariaDto> listaTipoGradeHorarias = new ArrayList<TipoGradeHorariaDto>();
        listaTipoGradeHorarias.add(geraTipoGradeHorariaDto());

        when(dao.listarGrades(Constantes.NOME_TESTE, Constantes.DATE_AUX)).thenReturn(geraListaGradeHorariaDto());
        when(tipoGradeHorariaDao.listarTipoGradeHorariaPorCodigoSubtipo((short) 0)).thenReturn(listaTipoGradeHorarias);
        ejb.obterGradeHoraria(geraGradeHorariaDto());
        return Constantes.TESTE_SUCESSO;
    }

    @Test
    public void removerGradeHoraria() throws ComumException, OperacionalNegocionException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, removerGradeHorariaEJB());
    }

    private String removerGradeHorariaEJB() throws ComumException, OperacionalNegocionException {
        when(em.find(GradeHoraria.class, geraGradeHoraria().getId())).thenReturn(geraGradeHoraria());
        ejb.removerGradeHoraria(geraGradeHoraria());
        return Constantes.TESTE_SUCESSO;
    }

    private TipoGradeHoraria gerarTipoGradeHoraria() {
        TipoGradeHoraria tipoGradeHoraria = new TipoGradeHoraria();
        tipoGradeHoraria.setCodTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHoraria.setDescTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHoraria.setSubTipoGrade(gerarSubtipoGrade());

        return tipoGradeHoraria;
    }

    private SubTipoGrade gerarSubtipoGrade() {
        SubTipoGrade subTipoGrade = new SubTipoGrade();
        subTipoGrade.setCodSubTipoGrade(Constantes.SHORT_UM);
        subTipoGrade.setDescSubTipoGrade(Constantes.NOME_TESTE);

        return subTipoGrade;
    }

    private TipoGradeHorariaDto geraTipoGradeHorariaDto() {
        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        tipoGradeHorariaDto.setCodTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHorariaDto.setDescTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHorariaDto.setCodSubTipoGrade(Constantes.SHORT_UM);
        tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(Constantes.NOME_TESTE);

        return tipoGradeHorariaDto;
    }

    private GradeHorariaDto geraGradeHorariaDto() {
        Date data1Temp = DataUtil.converterStringToDate("27/11/2016 22:00:00", "dd/MM/yyyy kk:mm:ss");
        Date data2Temp = DataUtil.converterStringToDate("28/11/2016 23:00:00", "dd/MM/yyyy kk:mm:ss");

        DateTimeDB data1DB = DateUtil.converterStringToDateTimeDB("27/11/2016 22:00:00", "dd/MM/yyyy kk:mm:ss");
        DateTimeDB data2DB = (DateTimeDB) DateUtil.converterStringToDateTimeDB("28/11/2016 23:00:00", "dd/MM/yyyy kk:mm:ss");

        GradeHorariaDto gradeHorariaDto = new GradeHorariaDto();
        gradeHorariaDto.setTipoGradeHorariaDto(geraTipoGradeHorariaDto());
        gradeHorariaDto.setCodTipoGradeHoraria(geraTipoGradeHorariaDto().getCodTipoGradeHoraria());
        gradeHorariaDto.setDataReferencia(Constantes.DATE_AUX);
        gradeHorariaDto.setDataHoraAbertura(data1DB);
        gradeHorariaDto.setDataHoraFechamento(data2DB);
        gradeHorariaDto.setDataHoraAberturaTemp(data1Temp);
        gradeHorariaDto.setDataHoraFechamentoTemp(data2Temp);
        gradeHorariaDto.setHoraAbertura(Constantes.HORA_ZERO);
        gradeHorariaDto.setMinutoAbertura(Constantes.HORA_ZERO);
        gradeHorariaDto.setHoraFechamento(Constantes.HORA_ZERO);
        gradeHorariaDto.setMinutoFechamento(Constantes.HORA_ZERO);

        return gradeHorariaDto;
    }

    private GradeHorariaDto geraGradeHorariaDto24h() {
        Date data1Temp = DataUtil.converterStringToDate("27/11/2016 24:00:00", "dd/MM/yyyy kk:mm:ss");
        Date data2Temp = DataUtil.converterStringToDate("28/11/2016 24:30:00", "dd/MM/yyyy kk:mm:ss");

        DateTimeDB data1DB = DateUtil.converterStringToDateTimeDB("27/11/2016 24:00:00", "dd/MM/yyyy kk:mm:ss");
        DateTimeDB data2DB = (DateTimeDB) DateUtil.converterStringToDateTimeDB("28/11/2016 24:30:00", "dd/MM/yyyy kk:mm:ss");

        GradeHorariaDto gradeHorariaDto = new GradeHorariaDto();
        gradeHorariaDto.setTipoGradeHorariaDto(geraTipoGradeHorariaDto());
        gradeHorariaDto.setCodTipoGradeHoraria(geraTipoGradeHorariaDto().getCodTipoGradeHoraria());
        gradeHorariaDto.setDataReferencia(Constantes.DATE_AUX);
        gradeHorariaDto.setDataHoraAbertura(data1DB);
        gradeHorariaDto.setDataHoraFechamento(data2DB);
        gradeHorariaDto.setDataHoraAberturaTemp(data1Temp);
        gradeHorariaDto.setDataHoraFechamentoTemp(data2Temp);
        gradeHorariaDto.setHoraAbertura(Constantes.HORA_ZERO);
        gradeHorariaDto.setMinutoAbertura(Constantes.HORA_ZERO);
        gradeHorariaDto.setHoraFechamento(Constantes.HORA_ZERO);
        gradeHorariaDto.setMinutoFechamento(Constantes.HORA_ZERO);

        return gradeHorariaDto;
    }

    private List<GradeHorariaDto> geraListaGradeHorariaDto() {
        List<GradeHorariaDto> lista = new ArrayList<GradeHorariaDto>();
        lista.add(geraGradeHorariaDto());
        lista.add(geraGradeHorariaDto24h());
        return lista;
    }

    private GradeHoraria geraGradeHoraria() {
        GradeHoraria gradeHoraria = new GradeHoraria();
        gradeHoraria.setId(Constantes.LONG_UM);
        gradeHoraria.setDataHoraAbertura(Constantes.DATE_TIME_DB_AUX);
        gradeHoraria.setDataHoraFechamento(Constantes.DATE_TIME_DB_AUX);
        gradeHoraria.setDataReferencia(Constantes.DATE_TIME_DB_AUX);
        gradeHoraria.setTipoGradeHoraria(gerarTipoGradeHoraria());
        return gradeHoraria;
    }

    private GradeHorariaDto geraObjetoInclusao() {
        GradeHorariaDto dto = geraGradeHorariaDto();
        List<GradeHorariaDto> listaGradeHorariaDto = new ArrayList<GradeHorariaDto>();
        listaGradeHorariaDto.add(geraGradeHorariaDto());
        dto.setListaGradeHoraria(listaGradeHorariaDto);
        return dto;
    }

    private ProdutoDto gerarProdutoDto() {
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setDataAtualMovimento(new Date());
        produtoDto.setDataProximoMovimento(new Date());

        return produtoDto;
    }

    private List<TipoGradeHorariaDto> geraListaTipoGradeHorariaDto() {
        List<TipoGradeHorariaDto> listaTipoGradeHorariaDto = new ArrayList<TipoGradeHorariaDto>();

        TipoGradeHorariaDto tipoGradeHorariaDto = new TipoGradeHorariaDto();
        tipoGradeHorariaDto.setCodTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHorariaDto.setDescTipoGradeHoraria(Constantes.NOME_TESTE);
        tipoGradeHorariaDto.setCodSubTipoGrade(Constantes.SHORT_UM);
        tipoGradeHorariaDto.setCodTipoGradeHorariaOrigem(Constantes.NOME_TESTE);
        listaTipoGradeHorariaDto.add(tipoGradeHorariaDto);

        return listaTipoGradeHorariaDto;
    }

    private List<GradeHorariaOrigemDto> geraListaGradeHorariaOrigem() {
        List<GradeHorariaOrigemDto> listaGradeHorariaOrigemDto = new ArrayList<GradeHorariaOrigemDto>();
        GradeHorariaOrigemDto gradeHorariaOrigemDto = new GradeHorariaOrigemDto();

        gradeHorariaOrigemDto.setCodTipoGradeHorariaOrigem(Constantes.NOME_TESTE);
        gradeHorariaOrigemDto.setDataUltimaGrade(new DateTimeDB());
        gradeHorariaOrigemDto.setDataHoraAberturaOrigem(new DateTimeDB());
        gradeHorariaOrigemDto.setDataHoraFechamentoOrigem(new DateTimeDB());

        listaGradeHorariaOrigemDto.add(gradeHorariaOrigemDto);

        return listaGradeHorariaOrigemDto;
    }

    private void verifyIncluirGradeHoraria(int vezes) throws OperacionalNoResultException, ComumException {
        verify(dao, times(vezes)).removerGrades(geraGradeHorariaDto().getCodTipoGradeHoraria(), geraGradeHorariaDto().getDataReferencia());
    }
}
