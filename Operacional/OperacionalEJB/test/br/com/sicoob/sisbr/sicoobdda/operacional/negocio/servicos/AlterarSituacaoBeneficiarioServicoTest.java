/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         AlterarSituacaoBeneficiarioServicoTest.java
 * Data Criação:    Jun 5, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

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

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlteraSituacaoBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.IFBeneficiarioAlerta;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ReplicarBeneficiarioLegadoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.MensagemDesconhecidaException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AlterarSituacaoBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacaoBeneficiarioDDADao;

/**
 * AlterarSituacaoBeneficiarioServicoTest é responsável por
 * 
 * @author Daniel.Basso
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ InformacoesUsuario.class })
public class AlterarSituacaoBeneficiarioServicoTest extends Mockito {

    @InjectMocks
    private AlterarSituacaoBeneficiarioServicoEJB ejb;

    @Mock
    private EntityManager em;

    @Mock
    private BeneficiarioCipDao dao;

    @Mock
    @SuppressWarnings("unused")
    private OperacaoBeneficiarioDDADao operacionalDao;

    @Mock
    @SuppressWarnings("unused")
    private ReplicarBeneficiarioLegadoDelegate replicarDelegate;

    @Mock
    private MensagemDDADelegate mensagemDDADelegate;

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

    /**
     * Método responsável por validar o teste do processarAlterarSituacaoBeneficiario(String)
     */
    @Test
    public void validarProcessarAlterarSituacaoBeneficiarioPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarAlterarSituacaoBeneficiario());
    }

    /**
     * Método responsável por validar o teste do processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioDto)
     */
    @Test
    public void validarprocessarAlterarSituacaoBeneficiarioEnumPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validarprocessarAlterarSituacaoBeneficiarioEnum());
    }

    /**
     * Método responsável por executar as rotinas do teste do processarAlterarSituacaoBeneficiario(String)
     * 
     * @return
     */
    private String processarAlterarSituacaoBeneficiario() {
        try {
            mockingEm();
            mockingObterBeneficiarioPorCpfCnpj();
            ejb.processarAlterarSituacaoBeneficiario(Constantes.CPF_AUX, CanalEnum.RETAGUARDA.getId());
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por executar as rotinas do teste do processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioDto)
     * 
     * @return
     */
    private String validarprocessarAlterarSituacaoBeneficiarioEnum() {
        try {
            mockingEm();
            mockingObterBeneficiarioPorCpfCnpj();
            ejb.processarAlterarSituacaoBeneficiario(montarDto(), CanalEnum.RETAGUARDA.getId());
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por montar um objeto AlteraSituacaoBeneficiarioDto
     * 
     * @return
     */
    private AlteraSituacaoBeneficiarioDto montarDto() {
        AlteraSituacaoBeneficiarioDto retorno = new AlteraSituacaoBeneficiarioDto();
        retorno.setSituacaoBeneficiario(SituacaoBeneficiarioEnum.APTO);
        retorno.setCnpjCpfBeneficiario(Constantes.CNPJ_AUX);
        retorno.setTipoPessoaBeneficiario(TipoPessoaEnum.PJ);
        retorno.setDataSituacaoBeneficiario(Constantes.DATE_AUX);
        return retorno;
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    private void mockingObterBeneficiarioPorCpfCnpj() throws ComumException {
        when(dao.obterBeneficiarioDiferenteDeSemCadastro(anyString())).thenReturn(geraBeneficiarioDDA());
    }

    /**
     * Método responsável por criar os mocks para o ejb MensagemAlterarSituacaoBeneficiarioDelegate
     * 
     */
    private void mockingEm() {
        when(em.find(SituacaoBeneficiarioDDA.class, SituacaoBeneficiarioDDA.APTO)).thenReturn(geraSituacaoBeneficiarioDDA(SituacaoBeneficiarioDDA.APTO));
        when(em.find(SituacaoBeneficiarioDDA.class, SituacaoBeneficiarioDDA.INAPTO)).thenReturn(geraSituacaoBeneficiarioDDA(SituacaoBeneficiarioDDA.INAPTO));
        when(em.find(ParametroDDA.class, ParametroDDA.REPLICAR_BENEFICIARIO_LEGADO)).thenReturn(geraParametroDDA());
        when(em.find(TipoMensagemDDA.class, TipoMensagemDDA.DDA0505)).thenReturn(geraTipoMensagemDDA());
    }

    /**
     * Método responsável por
     * 
     * @return TipoMensagemDDA
     * 
     */
    private TipoMensagemDDA geraTipoMensagemDDA() {
        TipoMensagemDDA tipoMsg = new TipoMensagemDDA();
        tipoMsg.setNumPrioridadeEnvio(Constantes.INTEGER_CEM);
        return tipoMsg;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioDDA
     * 
     */
    private BeneficiarioDDA geraBeneficiarioDDA() {
        BeneficiarioDDA beneficiarioDDA = new BeneficiarioDDA();
        beneficiarioDDA.setSituacaoBeneficiarioDDA(geraSituacaoBeneficiarioDDA(SituacaoBeneficiarioDDA.APTO));

        IFBeneficiarioAlerta ifBen = new IFBeneficiarioAlerta();
        ifBen.setCodIspbDestinatarioOriginalAlerta(Constantes.ISPB_BANCOOB);
        List<IFBeneficiarioAlerta> listaIF = new ArrayList<IFBeneficiarioAlerta>();
        listaIF.add(ifBen);
        beneficiarioDDA.setListaIFBeneficiarioAlerta(listaIF);

        beneficiarioDDA.setDataHoraUltimaAtualizacao(Constantes.DATE_TIME_DB_AUX);

        beneficiarioDDA.setNumIdentBeneficiario(Constantes.LONG_UM);
        return beneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @return SituacaoBeneficiarioDDA
     * 
     */
    private SituacaoBeneficiarioDDA geraSituacaoBeneficiarioDDA(String situacao) {
        SituacaoBeneficiarioDDA situacaoBeneficiarioDDA = new SituacaoBeneficiarioDDA();
        situacaoBeneficiarioDDA.setCodSituacaoBeneficiario(situacao);
        return situacaoBeneficiarioDDA;
    }

    /**
     * Método responsável por
     * 
     * @return ParametroDDA
     * 
     */
    private ParametroDDA geraParametroDDA() {
        ParametroDDA parametro = new ParametroDDA();
        parametro.setValorParametro(Constantes.STRING_NUMERO_1);
        return parametro;
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
