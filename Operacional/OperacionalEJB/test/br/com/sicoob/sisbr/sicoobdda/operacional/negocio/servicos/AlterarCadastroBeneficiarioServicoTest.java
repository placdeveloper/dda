/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         AlterarCadastroBeneficiarioServicoTest.java
 * Data Criação:    Nov 30, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlterarCadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioAlteracaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RepresentanteBeneficiarioAlteracaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoAgenciaEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AlterarCadastroBeneficiarioServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ServicosTestUtil;


/**
 * AlterarCadastroBeneficiarioServicoTest é responsável por
 * 
 * @author Tayrone.Oliveira
 */
@RunWith(PowerMockRunner.class)
public class AlterarCadastroBeneficiarioServicoTest extends ServicosTestUtil {

    @InjectMocks
    private AlterarCadastroBeneficiarioServicoEJB ejb;

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AlterarCadastroBeneficiarioServicoEJB#getEm()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetEm() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getEm")));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AlterarCadastroBeneficiarioServicoEJB#excluirConvenioBeneficiario(java.lang.Long, java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testExcluirConvenioBeneficiario() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, excluirConvenioBeneficiario(null));
        BeneficiarioDDA dda = recuperarBeneciarioDDA();
        assertEquals(Constantes.TESTE_SUCESSO, excluirConvenioBeneficiario(dda));
        dda.getListaBeneficiarioInstituicao().clear();
        assertEquals(Constantes.TESTE_SUCESSO, excluirConvenioBeneficiario(dda));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AlterarCadastroBeneficiarioServicoEJB#processarReenvioAlterarCadastroBeneficiario(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA, java.util.List, java.lang.Short)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testProcessarReenvioAlterarCadastroBeneficiario() throws Exception {
        whenBeneficiarioLegadoDao(Boolean.TRUE, Boolean.FALSE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "processarReenvioAlterarCadastroBeneficiario", recuperarBeneciarioDDA(),
                listarConvenioAlteracaoDDADto(), Constantes.SHORT_UM)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AlterarCadastroBeneficiarioServicoEJB#alterarCadastroBeneficiario(java.lang.Long, java.lang.Integer, java.lang.Long, java.util.Date, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testAlterarCadastroBeneficiarioLongIntegerLongDateDateTimeDBShort() throws Exception {
        whenSciDelegate(Boolean.TRUE);
        whenBeneficiarioLegadoDao(Boolean.TRUE, Boolean.FALSE);
        assertEquals(
                Constantes.TESTE_SUCESSO,
                retornarString(Whitebox.invokeMethod(ejb, "alterarCadastroBeneficiario", Constantes.LONG_UM, Constantes.INTEGER_UM,
                        Long.valueOf(SituacaoConvenioBeneficiarioEnum.EXCLUIDO.getCodSituacao()), Constantes.DATE_AUX, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM)));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AlterarCadastroBeneficiarioServicoEJB#alterarCadastroBeneficiario(java.lang.Long, java.lang.Integer, java.lang.Long, br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short)}
     * .
     * 
     * @throws Exception
     */
    @Test
    public final void testAlterarCadastroBeneficiarioLongIntegerLongBeneficiarioDDADateTimeDBShort() throws Exception {
        whenSciDelegate(Boolean.TRUE);
        whenBeneficiarioLegadoDao(Boolean.TRUE, Boolean.FALSE);
        assertEquals(
                Constantes.TESTE_SUCESSO,
                retornarString(Whitebox.invokeMethod(ejb, "alterarCadastroBeneficiario", Constantes.LONG_UM, Constantes.INTEGER_UM,
                        Long.valueOf(SituacaoConvenioBeneficiarioEnum.ATIVO.getCodSituacao()), recuperarBeneciarioDDA(), Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM)));
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.AlterarCadastroBeneficiarioServicoEJB#getDao()}.
     * 
     * @throws Exception
     */
    @Test
    public final void testGetDao() throws Exception {
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "getDao")));
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testObterConvenioAlteracaoDDADto() throws Exception {
        whenBeneficiarioLegadoDao(Boolean.TRUE, Boolean.FALSE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "obterConvenioAlteracaoDDADto", Constantes.CPF_AUX, Constantes.INTEGER_UM,
                Constantes.LONG_UM, Constantes.DATE_AUX, TipoManutencaoEnum.ALTERAR)));
    }

    // PRIVATE METHODS ===================================
    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testAlterarCadastro() throws Exception {
        when(beneficiarioDao.obterAlterarCadastroBeneficiarioDDADto(anyLong(), anyInt())).thenReturn(recuperarAlterarCadastroBeneficiarioDDADto());
        assertEquals(Constantes.TESTE_SUCESSO,
                retornarString(Whitebox.invokeMethod(ejb, "alterarCadastro", Constantes.LONG_UM, Constantes.INTEGER_UM,
                        Long.valueOf(SituacaoConvenioBeneficiarioEnum.ATIVO.getCodSituacao()), Constantes.DATE_AUX, new BeneficiarioDDA(), Constantes.DATE_TIME_DB_AUX,
                        Constantes.SHORT_UM)));
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testCriarMensagemExcluirBeneficiarioDDA() throws Exception {
        whenBeneficiarioLegadoDao(Boolean.TRUE, Boolean.FALSE);
        whenBeneficiarioCIPDao(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
        assertEquals(Constantes.TESTE_SUCESSO, retornarString(Whitebox.invokeMethod(ejb, "criarMensagemExcluirBeneficiarioDDA", Constantes.INTEGER_UM, Constantes.LONG_UM,
                Constantes.DATE_AUX, recuperarAlterarCadastroBeneficiarioDDADto(), recuperarBeneciarioDDA(), Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM)));
    }

    /**
     * Método responsável por
     * 
     * @throws Exception void
     * 
     */
    @Test
    public final void testCriarMensagemDDABeneficiario() throws Exception {
        AlterarCadastroBeneficiarioDDADto cadastroBeneficiarioDDADto = recuperarAlterarCadastroBeneficiarioDDADto();
        cadastroBeneficiarioDDADto.setTipoPessoaBeneficiario(TipoPessoaEnum.PJ);
        cadastroBeneficiarioDDADto.setNomeFantasiaBeneficiario(null);
        cadastroBeneficiarioDDADto.setListaRepresentanteBeneficiarioDto(listarRepresentanteBeneficiarioAlteracaoDto());
        assertEquals(Constantes.TESTE_SUCESSO,
                retornarString(Whitebox.invokeMethod(ejb, "criarMensagemDDABeneficiario", cadastroBeneficiarioDDADto, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM)));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testValidaDadosBeneficiario() {
        AlterarCadastroBeneficiarioDDADto beneficiarioDDADto = new AlterarCadastroBeneficiarioDDADto();
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(beneficiarioDDADto));
        beneficiarioDDADto.setTipoPessoaBeneficiario(TipoPessoaEnum.PF);
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(beneficiarioDDADto));
        beneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CPF_INVALIDO_AUX);
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(beneficiarioDDADto));
        beneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CPF_AUX);
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(beneficiarioDDADto));
        beneficiarioDDADto.setTipoPessoaBeneficiario(TipoPessoaEnum.PJ);
        beneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CNPJ_INVALIDO_AUX);
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(beneficiarioDDADto));
        beneficiarioDDADto.setCnpjCpfBeneficiario(Constantes.CNPJ_AUX);
        beneficiarioDDADto.setNomeFantasiaBeneficiario(Constantes.NOME_BANCO);
        assertEquals(Constantes.TESTE_FALHA, validaDadosBeneficiario(beneficiarioDDADto));
        beneficiarioDDADto.setListaRepresentanteBeneficiarioDto(listarRepresentanteBeneficiarioAlteracaoDto());
        beneficiarioDDADto.setNomeRazaoSocialBeneficiario(Constantes.NOME_BANCO);
        assertEquals(Constantes.TESTE_SUCESSO, validaDadosBeneficiario(beneficiarioDDADto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testValidarDadosConvenioCargaDDADto() {
        ConvenioAlteracaoDDADto dto = new ConvenioAlteracaoDDADto();
        assertEquals(Constantes.TESTE_FALHA, validarDadosConvenioCargaDDADto(dto));
        dto.setTipoManutencaoConvenio(TipoManutencaoEnum.MANTER);
        assertEquals(Constantes.TESTE_FALHA, validarDadosConvenioCargaDDADto(dto));
        dto.setDataInicioConvenio(Constantes.DATE_AUX);
        assertEquals(Constantes.TESTE_FALHA, validarDadosConvenioCargaDDADto(dto));
        dto.setTipoAgencia(TipoAgenciaEnum.FISICA);
        assertEquals(Constantes.TESTE_FALHA, validarDadosConvenioCargaDDADto(dto));
        dto.setNumAgencia(Constantes.INTEGER_UM);
        assertEquals(Constantes.TESTE_FALHA, validarDadosConvenioCargaDDADto(dto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testValidarDadosRepresentanteBeneficiarioDto() {
        RepresentanteBeneficiarioAlteracaoDto dto = new RepresentanteBeneficiarioAlteracaoDto();
        assertEquals(Constantes.TESTE_FALHA, validarDadosRepresentanteBeneficiarioDto(dto));
        dto.setTipoManutencaoRepresentante(TipoManutencaoEnum.ALTERAR);
        assertEquals(Constantes.TESTE_FALHA, validarDadosRepresentanteBeneficiarioDto(dto));
        dto.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PF);
        assertEquals(Constantes.TESTE_FALHA, validarDadosRepresentanteBeneficiarioDto(dto));
        dto.setCnpjCpfRepresentanteBeneficiario(Constantes.CPF_INVALIDO_AUX);
        assertEquals(Constantes.TESTE_FALHA, validarDadosRepresentanteBeneficiarioDto(dto));
        dto.setTipoPessoaRepresentanteBeneficiario(TipoPessoaEnum.PJ);
        dto.setCnpjCpfRepresentanteBeneficiario(Constantes.CNPJ_INVALIDO_AUX);
        assertEquals(Constantes.TESTE_FALHA, validarDadosRepresentanteBeneficiarioDto(dto));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testValidarBeneficiarioCadastrado() {
        assertEquals(Constantes.TESTE_FALHA, validarBeneficiarioCadastrado());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public final void testAlterarExcluirConvenio() {
        assertEquals(Constantes.TESTE_FALHA, alterarExcluirConvenio(0));
        assertEquals(Constantes.TESTE_SUCESSO, alterarExcluirConvenio(1));
    }

    // AUXILIARS =========================================
    /**
     * Método responsável por
     * 
     * @param dda
     * @return
     * @throws Exception String
     * 
     */
    private String excluirConvenioBeneficiario(BeneficiarioDDA dda) throws Exception {
        when(beneficiarioCipDao.obterBeneficiarioDiferenteDeSemCadastro(anyString())).thenReturn(dda);
        whenBeneficiarioLegadoDao(Boolean.TRUE, Boolean.FALSE);
        return retornarString(Whitebox
                .invokeMethod(ejb, "excluirConvenioBeneficiario", Constantes.LONG_UM, Constantes.INTEGER_UM, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM));
    }

    /**
     * Método responsável por
     * 
     * @param beneficiarioDDADto
     * @return String
     * 
     */
    private String validaDadosBeneficiario(AlterarCadastroBeneficiarioDDADto beneficiarioDDADto) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "validaDadosBeneficiario", beneficiarioDDADto);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosConvenioCargaDDADto(ConvenioAlteracaoDDADto dto) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "validarDadosConvenioCargaDDADto", dto);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String validarDadosRepresentanteBeneficiarioDto(RepresentanteBeneficiarioAlteracaoDto dto) {
        String retorno = null;
        try {
            Whitebox.invokeMethod(ejb, "validarDadosRepresentanteBeneficiarioDto", dto);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String validarBeneficiarioCadastrado() {
        String retorno = null;
        try {
            Long num = null; // o WhiteBox increspa se colocar 'null' direto nele.
            Whitebox.invokeMethod(ejb, "validarBeneficiarioCadastrado", num);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }

    /**
     * Método responsável por
     * 
     * @param iteracao
     * @return String
     * 
     */
    private String alterarExcluirConvenio(int iteracao) {
        String retorno = null;
        BeneficiarioDDA beneficiarioDDA = new BeneficiarioDDA();
        for (int i = 0; i < iteracao; i++) {
            beneficiarioDDA.getListaBeneficiarioInstituicao().add(recuperarBeneficiarioInsituicao());
        }
        try {
            Whitebox.invokeMethod(ejb, "alterarExcluirConvenio", Constantes.INTEGER_UM, Constantes.LONG_UM, Constantes.DATE_AUX, recuperarAlterarCadastroBeneficiarioDDADto(),
                    beneficiarioDDA, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM);
        } catch (Exception e) {
            retorno = Constantes.TESTE_FALHA;
        }
        return retornaSucessFailed(retorno);
    }
}
