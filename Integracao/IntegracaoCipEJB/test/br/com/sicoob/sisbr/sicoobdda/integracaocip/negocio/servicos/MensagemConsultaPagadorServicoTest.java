/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemConsultarPagadorServicoTest.java
 * Data Criação:    Oct 20, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.math.BigInteger;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarPagadorEletronicoLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemConsultaPagadorServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.PagadorDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002AgrgdDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002CtCliPagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA002.GrupoADDA002PagdrProprioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.DDA0002R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1AgrgdDDAComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1CtCliPagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1PagdrComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.GrupoDDA0002R1PagdrProprioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemConsultarPagadorServicoTest é responsável por
 * 
 * @author felipe.rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoCipFabricaDelegates.class })
public class MensagemConsultaPagadorServicoTest extends Mockito {

    @InjectMocks
    private MensagemConsultaPagadorServicoEJB ejb;

    @Mock
    private PagadorCipDao dao;

    @Mock
    private EntityManager em;

    @Mock
    private ReplicarPagadorEletronicoLegadoDelegate replicarPagadorEletronicoLegadoDelegate;

    @Mock
    private IntegracaoCipFabricaDelegates fabricaCip;

    /**
     * Método responsável por void
     * 
     */
    @Before
    public void setUp() {
        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(fabricaCip);
        when(fabricaCip.getReplicarPagadorEletronicoLegadoDelegate()).thenReturn(replicarPagadorEletronicoLegadoDelegate);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemDDAIncluirPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA0002Incluir());
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA002Incluir());
        verificaEmPersist();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemDDAIncluirPagadorProprioPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA0002IncluirPagadorProprio());
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA002IncluirPagadorProprio());
        verificaEmPersist();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemDDAAlterarPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA0002Alterar());
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA002Alterar());
        verificaEmMerge();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemDDAAlterarPagadorProprioPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemDDA0002AlterarPagadorProprio());
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA002AlterarPagadorProprio());
        verificaEmMerge();
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemDDA0002Incluir() throws ComumException {
        return processarRetornoMensagemDDA(new PagadorDDA(), geraDDA0002R1(null));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemDDA0002IncluirPagadorProprio() throws ComumException {
        return processarRetornoMensagemDDA(new PagadorDDA(), geraDDA0002R1(geraPagadorProprioDDA0002R1()));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemDDA0002Alterar() throws ComumException {
        return processarRetornoMensagemDDA(PagadorDDALoader.geraPagadorDDA(), geraDDA0002R1(null));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemDDA0002AlterarPagadorProprio() throws ComumException {
        return processarRetornoMensagemDDA(PagadorDDALoader.geraPagadorDDA(), geraDDA0002R1(geraPagadorProprioDDA0002R1()));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemADDA002Incluir() throws ComumException {
        return processarRetornoMensagemDDA(new PagadorDDA(), geraGrupoPagadorADDA002(null));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemADDA002IncluirPagadorProprio() throws ComumException {
        return processarRetornoMensagemDDA(new PagadorDDA(), geraGrupoPagadorADDA002(geraGrupoPagadorProprioADDA002()));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemADDA002Alterar() throws ComumException {
        return processarRetornoMensagemDDA(PagadorDDALoader.geraPagadorDDA(), geraGrupoPagadorADDA002(geraGrupoPagadorProprioADDA002()));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemADDA002AlterarPagadorProprio() throws ComumException {
        return processarRetornoMensagemDDA(PagadorDDALoader.geraPagadorDDA(), geraGrupoPagadorADDA002(geraGrupoPagadorProprioADDA002()));
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemDDA(PagadorDDA pagadorExistente, ConteudoMsgRecebida conteudoMsgRecebida) throws ComumException {
        when(dao.obterPagadorDDA(Constantes.CNPJ_AUX, Boolean.TRUE)).thenReturn(pagadorExistente);
        ejb.processarRetornoMensagemDDA(conteudoMsgRecebida);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return DDA0002R1ComplexType
     * @throws ComumException
     * 
     */
    private DDA0002R1ComplexType geraDDA0002R1(GrupoDDA0002R1PagdrProprioComplexType pagadorProprio) throws ComumException {
        DDA0002R1ComplexType dda0002 = new DDA0002R1ComplexType();
        dda0002.getGrupoDDA0002R1Pagdr().add(geraGrupoPagadorDDA0002R1(pagadorProprio));
        dda0002.setCodMsg(TipoMensagemDDA.DDA0002R1);
        return dda0002;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0002R1PagdrComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0002R1PagdrComplexType geraGrupoPagadorDDA0002R1(GrupoDDA0002R1PagdrProprioComplexType pagadorProprio) throws ComumException {
        GrupoDDA0002R1PagdrComplexType grupoPagador = new GrupoDDA0002R1PagdrComplexType();
        grupoPagador.setTpPessoaPagdr(TipoPessoaEnum.PF.getCodDominioCip());
        grupoPagador.setCNPJCPFPagdr(Constantes.CNPJ_AUX_BIGINT);
        grupoPagador.setSitCliPagdrDDA(Constantes.STRING_LETRA_S);
        grupoPagador.setDtHrSitAdesCliPagdrDDA(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_TIME_DB_AUX));
        grupoPagador.setIndrAdesCliPagdrDDA(Constantes.STRING_LETRA_S);
        grupoPagador.setQtdAdesCliPagdrDDA(Constantes.BIG_INTEGER_1_AUX);
        grupoPagador.setGrupoDDA0002R1PagdrProprio(pagadorProprio);
        GrupoDDA0002R1PagdrProprioComplexType grupoDDA0002R1PagdrProprioComplexType = new GrupoDDA0002R1PagdrProprioComplexType();
        grupoDDA0002R1PagdrProprioComplexType.setNumSeqAtlzCadCliPagdr(BigInteger.TEN);
        grupoDDA0002R1PagdrProprioComplexType.setSitCliPagdrPart(Constantes.STRING_NUMERO_1);
        grupoPagador.setGrupoDDA0002R1PagdrProprio(grupoDDA0002R1PagdrProprioComplexType);
        return grupoPagador;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0002R1PagdrProprioComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0002R1PagdrProprioComplexType geraPagadorProprioDDA0002R1() throws ComumException {
        GrupoDDA0002R1PagdrProprioComplexType pagadorProprio = new GrupoDDA0002R1PagdrProprioComplexType();
        pagadorProprio.setNumIdentcPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorProprio.setNumRefAtlCadCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorProprio.setNumSeqAtlzCadCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorProprio.setSitCliPagdrPart(Constantes.STRING_NUMERO_1);
        pagadorProprio.getGrupoDDA0002R1CtCliPagdr().add(geraGrupoPagadorContaDDA0002R1());
        pagadorProprio.getGrupoDDA0002R1AgrgdDDA().add(geraGrupoPagadorAgregadoDDA0002R1());
        return pagadorProprio;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0002R1CtPagdrComplexType
     * @throws ComumException
     * 
     */
    private GrupoDDA0002R1CtCliPagdrComplexType geraGrupoPagadorContaDDA0002R1() throws ComumException {
        GrupoDDA0002R1CtCliPagdrComplexType pagadorConta = new GrupoDDA0002R1CtCliPagdrComplexType();
        pagadorConta.setTpAgCliPagdr(Constantes.NOME_TESTE);
        pagadorConta.setAgCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorConta.setTpAgCliPagdr(Constantes.NOME_TESTE);
        pagadorConta.setCtCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorConta.setDtAdesCliPagdrDDA(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_TIME_DB_AUX));
        return pagadorConta;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoDDA0002R1AgrgdDDAComplexType
     * 
     */
    private GrupoDDA0002R1AgrgdDDAComplexType geraGrupoPagadorAgregadoDDA0002R1() {
        GrupoDDA0002R1AgrgdDDAComplexType pagadorAgregado = new GrupoDDA0002R1AgrgdDDAComplexType();
        pagadorAgregado.setSitAgrgd(Constantes.NOME_TESTE);
        pagadorAgregado.setTpPessoaAgrgd(TipoPessoaEnum.PF.getCodDominioCip());
        pagadorAgregado.setCNPJCPFAgrgd(Constantes.BIG_INTEGER_1_AUX);
        return pagadorAgregado;
    }

    /**
     * Método responsável por
     * 
     * @param pagadorProprio
     * @return
     * @throws ComumException GrupoADDA002PagdrComplexType
     * 
     */
    private GrupoADDA002PagdrComplexType geraGrupoPagadorADDA002(GrupoADDA002PagdrProprioComplexType pagadorProprio) throws ComumException {
        GrupoADDA002PagdrComplexType grupoPagador = new GrupoADDA002PagdrComplexType();
        grupoPagador.setTpPessoaPagdr(TipoPessoaEnum.PF.getCodDominioCip());
        grupoPagador.setCNPJCPFPagdr(Constantes.CNPJ_AUX_BIGINT);
        grupoPagador.setSitCliPagdrDDA(Constantes.STRING_LETRA_S);
        grupoPagador.setDtHrSitAdesCliPagdrDDA(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_TIME_DB_AUX));
        grupoPagador.setIndrAdesCliPagdrDDA(Constantes.STRING_LETRA_S);
        grupoPagador.setQtdAdesCliPagdrDDA(Constantes.BIG_INTEGER_1_AUX);
        grupoPagador.setGrupoADDA002PagdrProprio(pagadorProprio);

        return grupoPagador;
    }

    /**
     * Método responsável por
     * 
     * @param pagadorProprio
     * @return GrupoADDA002PagdrProprioComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA002PagdrProprioComplexType geraGrupoPagadorProprioADDA002() throws ComumException {
        GrupoADDA002PagdrProprioComplexType pagadorProprio = new GrupoADDA002PagdrProprioComplexType();
        pagadorProprio.setNumIdentcPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorProprio.setNumRefAtlCadCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorProprio.setNumSeqAtlzCadCliPagdr(BigInteger.ONE);
        pagadorProprio.setSitCliPagdrPart(Constantes.NOME_TESTE);
        pagadorProprio.getGrupoADDA002CtCliPagdr().add(geraGrupoPagadorContaADDA002());
        pagadorProprio.getGrupoADDA002AgrgdDDA().add(geraGrupoPagadorAgregadoADDA002());
        return pagadorProprio;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA002AgrgdDDAComplexType
     * 
     */
    private GrupoADDA002AgrgdDDAComplexType geraGrupoPagadorAgregadoADDA002() {
        GrupoADDA002AgrgdDDAComplexType pagadorAgregado = new GrupoADDA002AgrgdDDAComplexType();
        pagadorAgregado.setSitAgrgd(Constantes.NOME_TESTE);
        pagadorAgregado.setTpPessoaAgrgd(TipoPessoaEnum.PF.getCodDominioCip());
        pagadorAgregado.setCNPJCPFAgrgd(Constantes.BIG_INTEGER_1_AUX);
        return pagadorAgregado;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA002CtCliPagdrComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA002CtCliPagdrComplexType geraGrupoPagadorContaADDA002() throws ComumException {
        GrupoADDA002CtCliPagdrComplexType pagadorConta = new GrupoADDA002CtCliPagdrComplexType();
        pagadorConta.setTpAgCliPagdr(Constantes.NOME_TESTE);
        pagadorConta.setAgCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorConta.setTpAgCliPagdr(Constantes.NOME_TESTE);
        pagadorConta.setCtCliPagdr(Constantes.BIG_INTEGER_1_AUX);
        pagadorConta.setDtAdesCliPagdrDDA(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_TIME_DB_AUX));
        return pagadorConta;
    }

    /**
     * Método responsável por void
     * 
     */
    private void verificaEmPersist() {
        verify(em, times(2)).persist(any(PagadorDDA.class));
    }

    /**
     * Método responsável por void
     * 
     */
    private void verificaEmMerge() {
        verify(em, times(2)).merge(any(PagadorDDA.class));
    }
}
