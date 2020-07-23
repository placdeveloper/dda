/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos
 * Arquivo:         MensagemConsultaBeneficiarioServicoTest.java
 * Data Criação:    Nov 4, 2016
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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoCarteiraCobrancaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoContaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoProdutoConvenioEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarBeneficiarioLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.MensagemConsultaBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.BeneficiarioDDALoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.templates.MensagemDDABeneficiarioLoader;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA504.GrupoADDA504BenfcrioComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA504.GrupoADDA504ConvComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;

/**
 * MensagemConsultaBeneficiarioServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoInternaFabricaDelegates.class, IntegracaoCipFabricaDelegates.class })
public class MensagemConsultaBeneficiarioServicoTest extends Mockito {

    @InjectMocks
    private MensagemConsultaBeneficiarioServicoEJB ejb;

    @Mock
    private BeneficiarioCipDao dao;

    @Mock
    private EntityManager em;

    @Mock
    private IntegracaoCipFabricaDelegates fabricaCip;

    @Mock
    private ReplicarBeneficiarioLegadoDelegate replicarDelegate;

    @Mock
    private IntegracaoInternaFabricaDelegates fabrica;

    @Mock
    private SCIDelegate sciDelegate;

    /**
     * Método responsável por void
     * 
     */
    @Before
    public void setUp() {
        PowerMockito.mockStatic(IntegracaoInternaFabricaDelegates.class);
        when(IntegracaoInternaFabricaDelegates.getInstance()).thenReturn(fabrica);
        when(fabrica.getSCIDelegate()).thenReturn(sciDelegate);

        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(fabricaCip);
        when(fabricaCip.getReplicarBeneficiarioLegadoDelegate()).thenReturn(replicarDelegate);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem(MensagemDDABeneficiarioLoader.geraMensagemDDABeneficiario()));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemCodTipoPessoaNull() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem(MensagemDDABeneficiarioLoader.geraMensagemDDABeneficiarioCodTipoPessoaNull()));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemNumCnpjCpfNull() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem(MensagemDDABeneficiarioLoader.geraMensagemDDABeneficiarioNumCnpjCpfNull()));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarMensagemRelacionamentoNull() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarMensagem(MensagemDDABeneficiarioLoader.geraMensagemDDABeneficiarioRelacionamentoNull()));
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemADDA504IncluirPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA504Incluir());
        verifyEmPersist();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemADDA504AlterarPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA504Alterar(BeneficiarioDDALoader.geraBeneficiarioDDA()));
        verifyEmMerge();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemADDA504AlterarOrigemSicoobFalse() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA504Alterar(BeneficiarioDDALoader.geraBeneficiarioDDAOrigemSicoob(Boolean.FALSE)));
        verifyEmMerge();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemADDA504AlterarListaIFVazia() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA504Alterar(BeneficiarioDDALoader.geraBeneficiarioDDAIFAlerta(Boolean.FALSE)));
        verifyEmMerge();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemADDA504AlterarListaHistoricoStatusVazia() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA504Alterar(BeneficiarioDDALoader.geraBeneficiarioDDAHistoricoStatus(Boolean.FALSE)));
        verifyEmMerge();
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void processarRetornoMensagemADDA504AlterarReplicarLegado() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarRetornoMensagemADDA504AlterarReplicarLegado(geraParametroReplicarLegado(Constantes.STRING_NUMERO_1)));
        verifyEmMerge();
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarMensagem(MensagemDDABeneficiario mensagemBeneficiario) throws ComumException {
        when(dao.obterMensagemDDABeneficiarioAtualizaReferencias(Constantes.LONG_UM)).thenReturn(mensagemBeneficiario);
        ejb.processarMensagem(Constantes.LONG_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemADDA504Incluir() throws ComumException {
        return processarRetornoMensagem(geraGrupoADDA504Beneficiario(), null, geraParametroReplicarLegado(Constantes.STRING_NUMERO_0));
    }

    /**
     * Método responsável por
     * 
     * @param beneficiario
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetornoMensagemADDA504Alterar(BeneficiarioDDA beneficiario) throws ComumException {
        return processarRetornoMensagem(geraGrupoADDA504Beneficiario(), beneficiario, geraParametroReplicarLegado(Constantes.STRING_NUMERO_0));
    }

    /**
     * Método responsável por
     * 
     * @param parametroReplicarLegado
     * @return String
     * @throws ComumException
     * 
     */
    private String processarRetornoMensagemADDA504AlterarReplicarLegado(ParametroDDA parametroReplicarLegado) throws ComumException {
        return processarRetornoMensagem(geraGrupoADDA504Beneficiario(), BeneficiarioDDALoader.geraBeneficiarioDDA(), parametroReplicarLegado);
    }

    /**
     * Método responsável por
     * 
     * @param mensagem
     * @param beneficiario
     * @return
     * @throws ComumException String
     * 
     */
    private String processarRetornoMensagem(ConteudoMsgRecebida mensagem, BeneficiarioDDA beneficiario, ParametroDDA replicarLegado) throws ComumException {
        when(dao.obterBeneficiario(Constantes.CNPJ_AUX, Boolean.TRUE)).thenReturn(beneficiario);
        when(em.find(ParametroDDA.class, ParametroDDA.REPLICAR_BENEFICIARIO_LEGADO)).thenReturn(replicarLegado);
        when(sciDelegate.obterInstituicaoPorCooperativaCache(Constantes.INTEGER_UM)).thenReturn(geraInstituicaoDto());
        ejb.processarRetornoMensagemDDA(mensagem);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return InstituicaoDto
     * 
     */
    private InstituicaoDto geraInstituicaoDto() {
        InstituicaoDto instituicao = new InstituicaoDto();
        instituicao.setIdInstituicao(Constantes.INTEGER_UM);
        return instituicao;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException GrupoADDA504BenfcrioComplexType
     * 
     */
    private GrupoADDA504BenfcrioComplexType geraGrupoADDA504Beneficiario() throws ComumException {
        GrupoADDA504BenfcrioComplexType grupoBeneficiario = new GrupoADDA504BenfcrioComplexType();
        grupoBeneficiario.setNumCtrlReqOr(Constantes.NOME_TESTE);
        grupoBeneficiario.setNumCtrlDDA(Constantes.NOME_TESTE);
        grupoBeneficiario.setISPBPartDestinatarioPrincipal(Constantes.ISPB_BANCOOB);
        grupoBeneficiario.setISPBPartDestinatarioAdmtd(Constantes.TESTE_SUCESSO);
        grupoBeneficiario.setNumIdentcBenfcrio(BigInteger.ONE);
        grupoBeneficiario.setNumRefAtlCadBenfcrio(BigInteger.ONE);
        grupoBeneficiario.setNumSeqAtlzCadBenfcrio(BigInteger.ONE);
        grupoBeneficiario.setTpPessoaBenfcrio(TipoPessoaEnum.PJ.getCodDominioCip());
        grupoBeneficiario.setCNPJCPFBenfcrio(Constantes.CNPJ_AUX_BIGINT);
        grupoBeneficiario.setNomRzSocBenfcrio(Constantes.NOME_TESTE);
        grupoBeneficiario.setNomFantsBenfcrio(Constantes.NOME_TESTE);
        grupoBeneficiario.setSitBenfcrio(SituacaoBeneficiarioEnum.APTO.getCodDominio());
        grupoBeneficiario.setDtHrSitBenfcrio(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBeneficiario.setDtHrInclBenfcrioPart(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBeneficiario.setDtHrUltAltBenfcrioPart(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBeneficiario.setSitRelctPart(SituacaoRelacionamentoParticipanteEnum.ATIVO.getCodDominio());
        grupoBeneficiario.setDtIniRelctPart(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBeneficiario.setDtFimRelctPart(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBeneficiario.setDtIniRelctMaisAntgBenfcrio(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBeneficiario.setDtIniRelctMaisRecteBenfcrio(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoBeneficiario.getISPBPartDestinatarioOrigdrAlert().add(Constantes.ISPB_BANCOOB);
        grupoBeneficiario.getGrupoADDA504Conv().add(geraGrupoConv());
        return grupoBeneficiario;
    }

    /**
     * Método responsável por
     * 
     * @return GrupoADDA504ConvComplexType
     * @throws ComumException
     * 
     */
    private GrupoADDA504ConvComplexType geraGrupoConv() throws ComumException {
        GrupoADDA504ConvComplexType grupoConv = new GrupoADDA504ConvComplexType();
        grupoConv.setISPBPartIncorpd(Constantes.ISPB_BANCOOB);
        grupoConv.setSitConvBenfcrioPart(SituacaoConvenioBeneficiarioEnum.ATIVO.getCodDominio());
        grupoConv.setDtIniRelctConv(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoConv.setDtFimRelctConv(DataCipUtil.dateTimeParaXMLGregorianCalendar(Constantes.DATE_AUX));
        grupoConv.setTpAgDest(TipoAgenciaEnum.FISICA.getCodDominio());
        grupoConv.setAgDest(BigInteger.ONE);
        grupoConv.setTpCtDest(TipoContaEnum.CONTA_CORRENTE.getCodDominio());
        grupoConv.setCtDest(BigInteger.ONE);
        grupoConv.setTpProdtConv(TipoProdutoConvenioEnum.BOLETO_DE_COBRANCA.getCodDominio());
        grupoConv.setTpCartConvCobr(TipoCarteiraCobrancaEnum.COM_REGISTRO.getCodDominio());
        grupoConv.setCodCliConv(Constantes.NOME_TESTE);
        return grupoConv;
    }

    /**
     * Método responsável por
     * 
     * @param integerZero
     * @return ParametroDDA
     * 
     */
    private ParametroDDA geraParametroReplicarLegado(String valorParametro) {
        ParametroDDA parametroDDA = new ParametroDDA();
        parametroDDA.setValorParametro(valorParametro);
        return parametroDDA;
    }

    /**
     * Método responsável por void
     * 
     */
    private void verifyEmPersist() {
        verify(em, times(1)).persist(any(BeneficiarioDDA.class));
    }

    /**
     * Método responsável por void
     * 
     */
    private void verifyEmMerge() {
        verify(em, times(1)).merge(any(BeneficiarioDDA.class));
    }

}
