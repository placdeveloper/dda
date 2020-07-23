/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         CadastrarBeneficiarioServicoTest.java
 * Data Criação:    Jan 27, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.infraestrutura.seguranca.UsuarioBancoob;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioCadastroDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendenteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RepresentanteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoRelacionamentoParticipanteEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoProdutoConvenioEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.AlterarCadastroBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CadastrarBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiarioLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacaoBeneficiarioDDADao;
import junit.framework.Assert;

/**
 * @author Samuell.Ramos
 * 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ IntegracaoInternaFabricaDelegates.class, IntegracaoCipFabricaDelegates.class, OperacionalFabricaDelegates.class, InformacoesUsuario.class })
public class CadastrarBeneficiarioServicoTest extends Mockito {

    @InjectMocks
    private CadastrarBeneficiarioServicoEJB ejb;

    @Mock
    private EntityManager em;

    @Mock
    private BeneficiarioCipDao dao;

    @Mock
    private BeneficiarioLegadoDao beneficiarioDao;

    @Mock
    private OperacaoBeneficiarioDDADao operacaoBeneficiarioDDADao;

    @Mock
    private SCIDelegate sciDelegate;

    @Mock
    private MensagemDDADelegate mensagemDDADelegate;

    @Mock
    private AlterarCadastroBeneficiarioDelegate alterarCadastroBeneficiarioDelegate;

    @Mock
    private IntegracaoInternaFabricaDelegates integracaoInternaFabricaDelegates;

    @Mock
    private IntegracaoCipFabricaDelegates integracaoCipFabricaDelegates;

    @Mock
    private OperacionalFabricaDelegates operacionalFabricaDelegates;

    private InformacoesUsuario infoUsuario = new InformacoesUsuario(geraUsuarioBancoob());

    @Before
    public void setUp() {
        PowerMockito.mockStatic(IntegracaoInternaFabricaDelegates.class);
        PowerMockito.mockStatic(IntegracaoCipFabricaDelegates.class);
        PowerMockito.mockStatic(OperacionalFabricaDelegates.class);

        when(IntegracaoInternaFabricaDelegates.getInstance()).thenReturn(integracaoInternaFabricaDelegates);
        when(IntegracaoCipFabricaDelegates.getInstance()).thenReturn(integracaoCipFabricaDelegates);
        when(OperacionalFabricaDelegates.getInstance()).thenReturn(operacionalFabricaDelegates);

        when(integracaoInternaFabricaDelegates.getSCIDelegate()).thenReturn(sciDelegate);
        when(integracaoCipFabricaDelegates.getMensagemDDADelegate()).thenReturn(mensagemDDADelegate);
        when(operacionalFabricaDelegates.getAlterarCadastroBeneficiarioDelegate()).thenReturn(alterarCadastroBeneficiarioDelegate);

        PowerMockito.mockStatic(InformacoesUsuario.class);
        when(InformacoesUsuario.getInstance()).thenReturn(infoUsuario);
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CadastrarBeneficiarioServicoEJB#processarReenvioMensagemCadastroBeneficiario(java.lang.String, java.util.List)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testProcessarReenvioMensagemCadastroBeneficiario() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarReenvioMensagemCadastroBeneficiario());
    }

    @Test
    public final void testProcessarReenvioMensagemCadastroBeneficiarioJacCadastrado() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.beneficario.ja.cadastrado", processarReenvioMensagemCadastroBeneficiarioJaCadastrado());
    }

    @Test
    public final void testMsgDDAProcessarEnvioPendente() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, msgDDAProcessarEnvioPendente());
    }

    @Test
    public final void testMsgDDAErroProcessarEnvioPendenteEJB() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, msgDDAErroProcessarEnvioPendenteEJB());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CadastrarBeneficiarioServicoEJB#processarCadastroBeneficiario(java.lang.Long, java.lang.String, java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testProcessarCadastroBeneficiarioLongStringIntegerDateTimeDB() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarCadastroBeneficiarioLongStringIntegerDateTimeDB(1));
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarCadastroBeneficiarioLongStringIntegerDateTimeDB(2));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.CadastrarBeneficiarioServicoEJB#processarCadastroBeneficiario(java.lang.Long)}.
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testProcessarCadastroBeneficiarioLong() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, processarCadastroBeneficiarioLong());

    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioTipoPessoaNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.tipo.pessoa.invalido", validaDadosBeneficiarioTipoPessoaNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioCpfBeneficiarioNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.cnpj.cpf.beneficiario.invalido", validaDadosBeneficiarioCpfBeneficiarioNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioCpfBeneficiarioInvalido() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.cnpj.cpf.beneficiario.invalido", validaDadosBeneficiarioCpfBeneficiarioInvalido());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioCnpjBeneficiarioPassou() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validaDadosBeneficiarioCnpjBeneficiarioPassou());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioCnpjBeneficiarioInvalido() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.cnpj.cpf.beneficiario.invalido", validaDadosBeneficiarioCnpjBeneficiarioInvalido());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioTipoPessoaBeneficiarioNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.tipo.pessoa.invalido", validaDadosBeneficiarioTipoPessoaBeneficiarioNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioTipoRazaoNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.nome.razao.social.beneficiario.invalido", validaDadosBeneficiarioTipoRazaoNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioSituacaoBenefNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validaDadosBeneficiarioSituacaoBenefNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioDtHrSituacaoBenfNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.data.hora.situacao.beneficiario.invalida", validaDadosBeneficiarioDtHrSituacaoBenfNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioSituacaoRelParticipante() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.situacao.relacionamento.participante.invalida", validaDadosBeneficiarioSituacaoRelParticipante());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    // FIXME:SAMUELL.RAMOS VERIFICAR VALIDACAO COM NULL
    public void testValidaDadosBeneficiarioDtInicioRelParticipante() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validaDadosBeneficiarioDtInicioRelParticipante());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosBeneficiarioListaCadastroConveioNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.grupo.convenio.invalido", validaDadosBeneficiarioListaCadastroConveioNull());
    }

    /*
     * ============================================ Validações Dados Convenio Carga DDA ===========================================
     */

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidaDadosConvenioCargaDtoDtInicioNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.data.inicio.relacionamento.convenio.invalida", validaDadosConvenioCargaDtoDtInicioNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioCargTpAgenciaNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.tp.agencia.destino.convenio.invalida", validarDadosConvenioCargTpAgenciaNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioCargaDtoNumAgenciaNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.agencia.destino.convenio.invalida", validarDadosConvenioCargaDtoNumAgenciaNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioCargaDtoTpProdConvenioNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.tp.produto.convenio.invalida", validarDadosConvenioCargaDtoTpProdConvenioNull());
    }

    /*
     * ============================================ Validações de Beneficiario Representante ===========================================
     */
    /**
     * 
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioListaRepresentBeneficiarioNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.tipo.pessoa.representante.invalido", validarDadosConvenioListaRepresentBeneficiarioNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioListaRepresentBeneficiarioCpfCnpjNull() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.cnpj.cpf.representante.invalido", validarDadosConvenioListaRepresentBeneficiarioCpfCnpjNull());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioListaRepresentBeneficiarioPF() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validarDadosConvenioListaRepresentBeneficiarioPF());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioListaRepresentBeneficiarioPJ() throws ComumNegocioException, ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, validarDadosConvenioListaRepresentBeneficiarioPJ());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioListaRepresentBeneficiarioCpfInvalido() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.cnpj.cpf.representante.invalido", validarDadosConvenioListaRepresentBeneficiarioCpfInvalido());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    @Test
    public void testValidarDadosConvenioListaRepresentBeneficiarioCnpjInvalido() throws ComumNegocioException, ComumException {
        Assert.assertEquals("integracaocip.erro.cnpj.cpf.representante.invalido", validarDadosConvenioListaRepresentBeneficiarioCnpjInvalido());
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioTipoPessoaNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_FALHA;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setTipoPessoaBeneficiario(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioCpfBeneficiarioNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_FALHA;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setCnpjCpfBeneficiario(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioCpfBeneficiarioInvalido() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_FALHA;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CPF_INVALIDO_AUX);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioCnpjBeneficiarioPassou() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setTipoPessoaBeneficiario(TipoPessoaEnum.PJ);
            cadastroBeneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CNPJ_AUX);
            cadastroBeneficiarioDDADto.setNomeFantasiaBeneficiario(Constantes.NOME_TESTE);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioFantasiaNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setTipoPessoaBeneficiario(TipoPessoaEnum.PJ);
            cadastroBeneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CNPJ_AUX);
            cadastroBeneficiarioDDADto.setNomeFantasiaBeneficiario(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioCnpjBeneficiarioInvalido() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setTipoPessoaBeneficiario(TipoPessoaEnum.PJ);
            cadastroBeneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CNPJ_INVALIDO_AUX);
            cadastroBeneficiarioDDADto.setNomeFantasiaBeneficiario(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioTipoPessoaBeneficiarioNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setTipoPessoaBeneficiario(null);
            cadastroBeneficiarioDDADto.setCnpjCpfBeneficiario(null);
            cadastroBeneficiarioDDADto.setNomeFantasiaBeneficiario(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioTipoRazaoNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setNomeRazaoSocialBeneficiario(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioSituacaoBenefNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setSituacaoBeneficiario(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioDtHrSituacaoBenfNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setDataHoraSituacaoBeneficiario(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioSituacaoRelParticipante() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setSituacaoRelacionamentoParticipante(null);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    // Erro no processarReenvioMensagemCadastroBeneficiario nullpointer
    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioDtInicioRelParticipante() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setDataInicioRelacionamentoParticipante(new DateTimeDB());
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosBeneficiarioListaCadastroConveioNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<ConvenioCadastroDDADto> listaCadastroDDADtos = new ArrayList<ConvenioCadastroDDADto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            cadastroBeneficiarioDDADto.setListaConvenioCadastroDto(listaCadastroDDADtos);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /*
     * ============================================ Validações Dados Convenio Carga DDA ===========================================
     */
    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validaDadosConvenioCargaDtoDtInicioNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            List<ConvenioCadastroDDADto> listaCadastroDDADtos = new ArrayList<ConvenioCadastroDDADto>();
            listaCadastroDDADtos.add(geraDadosConvenioCargaDtoDtInicioNull());
            cadastroBeneficiarioDDADto.setListaConvenioCadastroDto(listaCadastroDDADtos);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioCargTpAgenciaNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<ConvenioCadastroDDADto> listaCadastroDDADtos = new ArrayList<ConvenioCadastroDDADto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaCadastroDDADtos.add(geraDadosConvenioCargTpAgenciaNull());
            cadastroBeneficiarioDDADto.setListaConvenioCadastroDto(listaCadastroDDADtos);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioCargaDtoNumAgenciaNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<ConvenioCadastroDDADto> listaCadastroDDADtos = new ArrayList<ConvenioCadastroDDADto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaCadastroDDADtos.add(geraDadosConvenioCargaDtoNumAgenciaNull());
            cadastroBeneficiarioDDADto.setListaConvenioCadastroDto(listaCadastroDDADtos);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioCargaDtoTpProdConvenioNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<ConvenioCadastroDDADto> listaCadastroDDADtos = new ArrayList<ConvenioCadastroDDADto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaCadastroDDADtos.add(geraDadosConvenioCargaDtoTpProdConvenioNull());
            cadastroBeneficiarioDDADto.setListaConvenioCadastroDto(listaCadastroDDADtos);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioListaRepresentBeneficiarioNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<RepresentanteBeneficiarioDto> listaRepresentante = new ArrayList<RepresentanteBeneficiarioDto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaRepresentante.add(geraRepresentanteBeneficiarioDtoPessoaNull());
            cadastroBeneficiarioDDADto.setListaRepresentanteBeneficiarioDto(listaRepresentante);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioListaRepresentBeneficiarioCpfCnpjNull() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<RepresentanteBeneficiarioDto> listaRepresentante = new ArrayList<RepresentanteBeneficiarioDto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaRepresentante.add(geraRepresentanteBeneficiarioDtoCpfCnpjNull());
            cadastroBeneficiarioDDADto.setListaRepresentanteBeneficiarioDto(listaRepresentante);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioListaRepresentBeneficiarioPF() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<RepresentanteBeneficiarioDto> listaRepresentante = new ArrayList<RepresentanteBeneficiarioDto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaRepresentante.add(geraRepresentanteBeneficiarioDtoPF());
            cadastroBeneficiarioDDADto.setListaRepresentanteBeneficiarioDto(listaRepresentante);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioListaRepresentBeneficiarioPJ() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<RepresentanteBeneficiarioDto> listaRepresentante = new ArrayList<RepresentanteBeneficiarioDto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaRepresentante.add(geraRepresentanteBeneficiarioCnpjPJ());
            cadastroBeneficiarioDDADto.setListaRepresentanteBeneficiarioDto(listaRepresentante);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioListaRepresentBeneficiarioCpfInvalido() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<RepresentanteBeneficiarioDto> listaRepresentante = new ArrayList<RepresentanteBeneficiarioDto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaRepresentante.add(geraRepresentanteBeneficiarioCpfPFInvalido());
            cadastroBeneficiarioDDADto.setListaRepresentanteBeneficiarioDto(listaRepresentante);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String validarDadosConvenioListaRepresentBeneficiarioCnpjInvalido() throws ComumNegocioException, ComumException {
        String retorno = Constantes.TESTE_SUCESSO;
        try {
            List<RepresentanteBeneficiarioDto> listaRepresentante = new ArrayList<RepresentanteBeneficiarioDto>();
            CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = geraCadastroBeneficiarioDDADto();
            listaRepresentante.add(geraRepresentanteBeneficiarioCnpjPJInvalido());
            cadastroBeneficiarioDDADto.setListaRepresentanteBeneficiarioDto(listaRepresentante);
            retornoValidaDadosBeneficiario(cadastroBeneficiarioDDADto);
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            retorno = e.getMessage();
        }
        return retorno;
    }

    /**
     * @param cadBeneficiarioDto
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    private void retornoValidaDadosBeneficiario(CadastroBeneficiarioDDADto cadBeneficiarioDto) throws ComumNegocioException, ComumException {
        when(beneficiarioDao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(cadBeneficiarioDto);
        List<MensagemPendenteBeneficiarioDto> listaMensagemPendenteBeneficiarioDto = new ArrayList<MensagemPendenteBeneficiarioDto>();
        when(operacaoBeneficiarioDDADao.listarMensagemPendenteBeneficiario(any(String.class))).thenReturn(listaMensagemPendenteBeneficiarioDto);
        when(dao.obterBeneficiario(Constantes.CPF_AUX)).thenReturn(null);
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String processarReenvioMensagemCadastroBeneficiario() throws ComumNegocioException, ComumException {
        when(beneficiarioDao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(geraCadastroBeneficiarioDDADto());
        List<MensagemPendenteBeneficiarioDto> listaMensagemPendenteBeneficiarioDto = new ArrayList<MensagemPendenteBeneficiarioDto>();
        when(operacaoBeneficiarioDDADao.listarMensagemPendenteBeneficiario(any(String.class))).thenReturn(listaMensagemPendenteBeneficiarioDto);
        when(dao.obterBeneficiario(Constantes.CPF_AUX)).thenReturn(null);
        callProcessarReenvioMsgCadastroBenfcrio();

        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String processarReenvioMensagemCadastroBeneficiarioJaCadastrado() throws ComumNegocioException, ComumException {
        when(beneficiarioDao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(geraCadastroBeneficiarioDDADto());
        List<MensagemPendenteBeneficiarioDto> listaMensagemPendenteBeneficiarioDto = new ArrayList<MensagemPendenteBeneficiarioDto>();
        when(operacaoBeneficiarioDDADao.listarMensagemPendenteBeneficiario(any(String.class))).thenReturn(listaMensagemPendenteBeneficiarioDto);
        when(dao.obterBeneficiarioDiferenteDeSemCadastro(anyString())).thenReturn(geraBeneficiarioDDA());
        try {
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (Exception e) {
            return e.getMessage();
        }
        return Constantes.TESTE_FALHA;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String msgDDAProcessarEnvioPendente() throws ComumNegocioException, ComumException {
        when(beneficiarioDao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(geraCadastroBeneficiarioDDADto());
        when(operacaoBeneficiarioDDADao.listarMensagemPendenteBeneficiario(any(String.class))).thenReturn(geraListaMensagemPendenteBeneficiarioDto());
        when(dao.obterBeneficiario(Constantes.CPF_AUX)).thenReturn(null);
        try {
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (ComumNegocioException e) {
            return Constantes.TESTE_SUCESSO;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String msgDDAErroProcessarEnvioPendenteEJB() throws ComumNegocioException, ComumException {
        String statusTest = Constantes.TESTE_FALHA;
        List<MensagemPendenteBeneficiarioDto> listaMensagemPendenteBeneficiarioDto = geraListaMensagemPendenteBeneficiarioDto();
        listaMensagemPendenteBeneficiarioDto.get(0).setCodTipoErroCIP(null);
        listaMensagemPendenteBeneficiarioDto.get(0).setDescTipoErroCIP(null);

        when(beneficiarioDao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(geraCadastroBeneficiarioDDADto());
        when(operacaoBeneficiarioDDADao.listarMensagemPendenteBeneficiario(any(String.class))).thenReturn(listaMensagemPendenteBeneficiarioDto);
        when(dao.obterBeneficiario(Constantes.CPF_AUX)).thenReturn(null);

        try {
            callProcessarReenvioMsgCadastroBenfcrio();
        } catch (ComumNegocioException e) {
            statusTest = Constantes.TESTE_SUCESSO;
        }
        return statusTest;
    }

    /**
     * @param operacao
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String processarCadastroBeneficiarioLongStringIntegerDateTimeDB(int operacao) throws ComumNegocioException, ComumException {
        switch (operacao) {
        case 1:
            when(dao.obterBeneficiario(Constantes.CPF_AUX)).thenReturn(geraBeneficiarioDDA());
            break;
        case 2:
            when(dao.obterBeneficiario(Constantes.CPF_AUX)).thenReturn(null);
            when(beneficiarioDao.obterCadastroBeneficiarioDDADto(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(geraCadastroBeneficiarioDDADto());
            break;
        }
        ejb.processarCadastroBeneficiario(Constantes.LONG_UM, Constantes.CPF_AUX, Constantes.INTEGER_UM, new DateTimeDB(), CanalEnum.RETAGUARDA.getId());
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @return
     * @throws ComumException
     * @throws ComumNegocioException String
     * 
     */
    private String processarCadastroBeneficiarioLong() throws ComumException, ComumNegocioException {
        BeneficiarioDDA beneficiarioDDA = geraBeneficiarioDDA();
        beneficiarioDDA.setListaBeneficiarioInstituicao(geraListaBeneficiarioInstituicao());
        when(em.find((Class<BeneficiarioDDA>) any(), anyLong())).thenReturn(beneficiarioDDA);
        when(sciDelegate.obterInstituicaoCache(Constantes.INTEGER_UM)).thenReturn(geraInstituicaoDto());
        when(beneficiarioDao.obterCadastroBeneficiarioDDADto(anyString(), anyInt())).thenReturn(geraCadastroBeneficiarioDDADto());
        callProcessarCadastroBeneficiarioPorIdBeneficirio();
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    private void callProcessarReenvioMsgCadastroBenfcrio() throws ComumNegocioException, ComumException {
        ejb.processarReenvioMensagemCadastroBeneficiario(Constantes.CPF_AUX, geraListaCooperativas(), CanalEnum.RETAGUARDA.getId());
    }

    /**
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    private void callProcessarCadastroBeneficiarioPorIdBeneficirio() throws ComumNegocioException, ComumException {
        ejb.processarCadastroBeneficiario(Constantes.LONG_UM, CanalEnum.RETAGUARDA.getId());
    }

    /**
     * @return List<Integer>
     * 
     */
    private List<Integer> geraListaCooperativas() {
        List<Integer> listaCooperativas = new ArrayList<Integer>();
        listaCooperativas.add(Constantes.INTEGER_UM);
        listaCooperativas.add(Constantes.INTEGER_UM);
        return listaCooperativas;
    }

    /**
     * @return CadastroBeneficiarioDDADto
     * 
     */
    private CadastroBeneficiarioDDADto geraCadastroBeneficiarioDDADto() {
        CadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = new CadastroBeneficiarioDDADto();
        cadastroBeneficiarioDDADto.setDataInicioRelacionamentoParticipante(Constantes.DATE_AUX);
        cadastroBeneficiarioDDADto.setTipoPessoaBeneficiario(TipoPessoaEnum.PF);
        cadastroBeneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CPF_AUX);
        cadastroBeneficiarioDDADto.setNomeRazaoSocialBeneficiario(Constantes.STRING_LETRA_S);
        cadastroBeneficiarioDDADto.setSituacaoBeneficiario(SituacaoBeneficiarioEnum.APTO);
        cadastroBeneficiarioDDADto.setDataHoraSituacaoBeneficiario(Constantes.DATE_AUX);
        cadastroBeneficiarioDDADto.setSituacaoRelacionamentoParticipante(SituacaoRelacionamentoParticipanteEnum.ATIVO);
        cadastroBeneficiarioDDADto.setDataInicioRelacionamentoParticipante(Constantes.DATE_AUX);
        cadastroBeneficiarioDDADto.setListaConvenioCadastroDto(geraListaConvenioCadastroDDADto());
        return cadastroBeneficiarioDDADto;
    }

    /**
     * @return ConvenioCadastroDDADto
     * 
     */
    private ConvenioCadastroDDADto geraConvenioCadastroDDADto() {
        ConvenioCadastroDDADto convenio = new ConvenioCadastroDDADto();
        convenio.setDataInicioConvenio(Constantes.DATE_AUX);
        convenio.setTipoAgencia(TipoAgenciaEnum.FISICA);
        convenio.setNumAgencia(Constantes.INTEGER_UM);
        convenio.setTipoProdutoConvenio(TipoProdutoConvenioEnum.BOLETO_DE_COBRANCA);
        convenio.setSitucaoConvenio(SituacaoConvenioBeneficiarioEnum.ATIVO);
        convenio.setIspbParticipanteIncorporado(Constantes.NOME_TESTE);
        convenio.setNumConta(Constantes.INTEGER_UM);
        convenio.setCodClienteConvenio(Constantes.NOME_TESTE);
        return convenio;
    }

    /**
     * @return List<ConvenioCadastroDDADto>
     * 
     */
    private List<ConvenioCadastroDDADto> geraListaConvenioCadastroDDADto() {
        List<ConvenioCadastroDDADto> listaConvenio = new ArrayList<ConvenioCadastroDDADto>();
        ConvenioCadastroDDADto convenioCadastroDDADto1 = geraConvenioCadastroDDADto();
        ConvenioCadastroDDADto convenioCadastroDDADto2 = geraConvenioCadastroDDADto();
        convenioCadastroDDADto2.setNumConta(null);
        convenioCadastroDDADto2.setCodClienteConvenio(null);
        convenioCadastroDDADto2.setIspbParticipanteIncorporado(null);
        listaConvenio.add(convenioCadastroDDADto1);
        listaConvenio.add(convenioCadastroDDADto2);
        return listaConvenio;
    }

    /**
     * @return List<MensagemPendenteBeneficiarioDto>
     * 
     */
    private List<MensagemPendenteBeneficiarioDto> geraListaMensagemPendenteBeneficiarioDto() {
        List<MensagemPendenteBeneficiarioDto> listaMensagemPendenteBeneficiarioDto = new ArrayList<MensagemPendenteBeneficiarioDto>();
        listaMensagemPendenteBeneficiarioDto.add(geraMensagemPendenteBeneficiarioDto());
        return listaMensagemPendenteBeneficiarioDto;
    }

    /**
     * @return MensagemPendenteBeneficiarioDto
     * 
     */
    private MensagemPendenteBeneficiarioDto geraMensagemPendenteBeneficiarioDto() {
        return new MensagemPendenteBeneficiarioDto(Constantes.LONG_UM, Constantes.NOME_TESTE, Constantes.CPF_AUX, Constantes.NOME_TESTE, Constantes.NOME_TESTE);
    }

    /**
     * @return BeneficiarioDDA
     * 
     */
    private BeneficiarioDDA geraBeneficiarioDDA() {
        BeneficiarioDDA beneficiarioDDA = new BeneficiarioDDA();
        beneficiarioDDA.setId(Constantes.LONG_UM);
        beneficiarioDDA.setBolOrigemSicoob(Boolean.TRUE);
        beneficiarioDDA.setSituacaoBeneficiarioDDA(new SituacaoBeneficiarioDDA("A"));
        return beneficiarioDDA;
    }

    /**
     * @return BeneficiarioInstituicao
     * 
     */
    private BeneficiarioInstituicao geraBeneficiarioInstituicao() {
        BeneficiarioInstituicao beneficiarioInstituicao = new BeneficiarioInstituicao();

        BeneficiarioDDA beneficiarioDDA = geraBeneficiarioDDA();
        beneficiarioInstituicao.setBeneficiarioDDA(beneficiarioDDA);
        beneficiarioInstituicao.setDataInicioRelacionamento(new DateTimeDB());
        beneficiarioInstituicao.setId(Constantes.LONG_UM);
        beneficiarioInstituicao.setIdInstituicao(Constantes.INTEGER_UM);

        return beneficiarioInstituicao;
    }

    /**
     * @return List<BeneficiarioInstituicao>
     * 
     */
    private List<BeneficiarioInstituicao> geraListaBeneficiarioInstituicao() {

        List<BeneficiarioInstituicao> listaBeneficiarioInstituicao = new ArrayList<BeneficiarioInstituicao>();
        listaBeneficiarioInstituicao.add(geraBeneficiarioInstituicao());
        listaBeneficiarioInstituicao.add(geraBeneficiarioInstituicao());

        return listaBeneficiarioInstituicao;
    }

    private InstituicaoDto geraInstituicaoDto() {
        InstituicaoDto instituicaoDto = new InstituicaoDto();
        instituicaoDto.setNumCooperativa(Constantes.INTEGER_UM);
        return instituicaoDto;
    }

    /**
     * @return ConvenioCadastroDDADto
     * 
     */
    private ConvenioCadastroDDADto geraDadosConvenioCargaDtoDtInicioNull() {
        ConvenioCadastroDDADto convenio = geraConvenioCadastroDDADto();
        convenio.setDataInicioConvenio(null);
        return convenio;
    }

    /**
     * @return ConvenioCadastroDDADto
     * 
     */
    private ConvenioCadastroDDADto geraDadosConvenioCargTpAgenciaNull() {
        ConvenioCadastroDDADto convenio = geraConvenioCadastroDDADto();
        convenio.setTipoAgencia(null);
        return convenio;
    }

    /**
     * @return ConvenioCadastroDDADto
     * 
     */
    private ConvenioCadastroDDADto geraDadosConvenioCargaDtoNumAgenciaNull() {
        ConvenioCadastroDDADto convenio = geraConvenioCadastroDDADto();
        convenio.setNumAgencia(null);
        return convenio;
    }

    /**
     * @return ConvenioCadastroDDADto
     * 
     */
    private ConvenioCadastroDDADto geraDadosConvenioCargaDtoTpProdConvenioNull() {
        ConvenioCadastroDDADto convenio = geraConvenioCadastroDDADto();
        convenio.setTipoProdutoConvenio(null);
        return convenio;
    }

    /**
     * @return RepresentanteBeneficiarioDto
     * 
     */
    private RepresentanteBeneficiarioDto geraRepresentanteBeneficiarioDtoPessoaNull() {
        RepresentanteBeneficiarioDto representanteDto = new RepresentanteBeneficiarioDto();
        representanteDto.setCnpjCpfRepresentanteBeneficiario(Constantes.CPF_AUX);
        representanteDto.setTipoPessoaRepresentanteBeneficiario(null);
        return representanteDto;
    }

    /**
     * @return RepresentanteBeneficiarioDto
     * 
     */
    private RepresentanteBeneficiarioDto geraRepresentanteBeneficiarioDtoCpfCnpjNull() {
        RepresentanteBeneficiarioDto representanteDto = new RepresentanteBeneficiarioDto();
        representanteDto.setCnpjCpfRepresentanteBeneficiario(null);
        representanteDto.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PF);
        return representanteDto;
    }

    /**
     * @return RepresentanteBeneficiarioDto
     * 
     */
    private RepresentanteBeneficiarioDto geraRepresentanteBeneficiarioDtoPF() {
        RepresentanteBeneficiarioDto representanteDto = new RepresentanteBeneficiarioDto();
        representanteDto.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PF);
        representanteDto.setCnpjCpfRepresentanteBeneficiario(Constantes.CPF_AUX);
        return representanteDto;
    }

    /**
     * @return RepresentanteBeneficiarioDto
     * 
     */
    private RepresentanteBeneficiarioDto geraRepresentanteBeneficiarioCpfPFInvalido() {
        RepresentanteBeneficiarioDto representanteDto = new RepresentanteBeneficiarioDto();
        representanteDto.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PF);
        representanteDto.setCnpjCpfRepresentanteBeneficiario(Constantes.CPF_INVALIDO_AUX);
        return representanteDto;
    }

    /**
     * @return RepresentanteBeneficiarioDto
     * 
     */
    private RepresentanteBeneficiarioDto geraRepresentanteBeneficiarioCnpjPJ() {
        RepresentanteBeneficiarioDto representanteDto = new RepresentanteBeneficiarioDto();
        representanteDto.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PJ);
        representanteDto.setCnpjCpfRepresentanteBeneficiario(Constantes.CNPJ_AUX);
        return representanteDto;
    }

    /**
     * @return RepresentanteBeneficiarioDto
     * 
     */
    private RepresentanteBeneficiarioDto geraRepresentanteBeneficiarioCnpjPJInvalido() {
        RepresentanteBeneficiarioDto representanteDto = new RepresentanteBeneficiarioDto();
        representanteDto.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PJ);
        representanteDto.setCnpjCpfRepresentanteBeneficiario(Constantes.CNPJ_INVALIDO_AUX);
        return representanteDto;
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
