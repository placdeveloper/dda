/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         PagadorEletronicoDDAServicoTest.java
 * Data Criação:    Nov 14, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendentePagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParticipanteContaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.sci.InstituicaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoTermoPagadorEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoTermoPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTermoPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.util.ServicosTestUtil;

/**
 * PagadorEletronicoDDAServicoTest
 * 
 * @author Samuell.Ramos
 */
@RunWith(PowerMockRunner.class)
public class PagadorEletronicoDDAServicoTest extends ServicosTestUtil {

    @InjectMocks
    private PagadorEletronicoDDAServicoEJB ejb;

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#indicadorAceiteDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testIndicadorAceiteDDA() throws ComumException {
        whenBoletoCipDao(Boolean.TRUE, Boolean.TRUE);
        PagadorEletronicoDDADto dto = montarPagadorEletronicoDDADto();
        assertEquals(Constantes.TESTE_SUCESSO, indicadorAceiteDDA(dto, Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#indicadorAceiteDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testIndicadorAceiteDDABoleto() throws ComumException {
        when(boletoCipDao.obterBoletoDDA(anyLong())).thenReturn(montarBoletoDDA());
        PagadorEletronicoDDADto dto = montarPagadorEletronicoDDADto();
        assertEquals(Constantes.TESTE_SUCESSO, indicadorAceiteDDA(dto, Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#indicadorAceiteDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)}
     * .
     * 
     * @throws ComumException
     */
    private String indicadorAceiteDDA(PagadorEletronicoDDADto dto, Boolean exception) throws ComumException {
        try {
            whenParametroDao(Boolean.TRUE, Boolean.TRUE);
            whenParametroDao(Boolean.TRUE, Boolean.TRUE);
            whenADMDelegate(Boolean.TRUE);
            when(sciDelegate.obterInstituicaoPorCooperativaCache(dto.getNumCooperativa())).thenReturn(montaInstituicao());
            ejb.indicadorAceiteDDA(dto);
            return Constantes.TESTE_SUCESSO;

        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)}
     * .
     */
    @Test
    public final void testManterDDATerceiroValidarTericeiroException() {
        whenBoletoCipDao(Boolean.TRUE, Boolean.TRUE);
        whenParametroDao(Boolean.TRUE, Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, manterDDATerceiro(montarDDATerceiroDto(), Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)}
     * .
     */
    @Test
    public final void testManterDDATerceiroValidarTericeiroNaoPagadorEletronicoException() {
        whenBoletoCipDao(Boolean.TRUE, Boolean.FALSE);
        whenParametroDao(Boolean.TRUE, Boolean.FALSE);
        DDATerceiroDto dto = montarDDATerceiroDto();
        dto.setIndTerceiro('I');
        assertEquals(Constantes.TESTE_FALHA, manterDDATerceiro(dto, Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)}
     * .
     */
    @Test
    public final void testManterDDATerceiroIncluiMensagem() throws ComumNegocioException, ComumException {
        DDATerceiroDto dto = montarDDATerceiroDto();
        whenBoletoCipDao(Boolean.TRUE, Boolean.FALSE);
        whenParametroDao(Boolean.TRUE, Boolean.FALSE);
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(dto.getTipoPessoaTerc(), dto.getNumCpfCnpj(), Boolean.FALSE)).thenReturn(Boolean.TRUE);
        dto.setIndTerceiro('I');
        when(boletoCipDao.obterCpfCnpjPagadorEletronico(anyLong())).thenReturn(Constantes.NOME_TESTE);
        when(boletoCipDao.existeTerceiroAutorizadoAtivo(anyString(), anyLong())).thenReturn(Boolean.FALSE);
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(montaInstituicao());
        assertEquals(Constantes.TESTE_SUCESSO, manterDDATerceiro(dto, Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)}
     * .
     */
    @Test
    public final void testManterDDATerceiroIncluiMensagemNumCpfCnpjPagadorException() throws ComumNegocioException, ComumException {
        DDATerceiroDto dto = montarDDATerceiroDto();
        whenBoletoCipDao(Boolean.TRUE, Boolean.FALSE);
        whenParametroDao(Boolean.TRUE, Boolean.FALSE);
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(dto.getTipoPessoaTerc(), dto.getNumCpfCnpj(), Boolean.FALSE)).thenReturn(Boolean.TRUE);
        dto.setIndTerceiro('I');
        when(boletoCipDao.obterCpfCnpjPagadorEletronico(anyLong())).thenReturn(null);
        assertEquals(Constantes.TESTE_FALHA, manterDDATerceiro(dto, Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)}
     * .
     */
    @Test
    public final void testManterDDATerceiroIncluiMensagemException() throws ComumNegocioException, ComumException {
        DDATerceiroDto dto = montarDDATerceiroDto();
        whenBoletoCipDao(Boolean.TRUE, Boolean.FALSE);
        whenParametroDao(Boolean.TRUE, Boolean.FALSE);
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(dto.getTipoPessoaTerc(), dto.getNumCpfCnpj(), Boolean.FALSE)).thenReturn(Boolean.TRUE);
        dto.setIndTerceiro('I');
        when(boletoCipDao.obterCpfCnpjPagadorEletronico(anyLong())).thenReturn(Constantes.NOME_TESTE);
        when(boletoCipDao.existeTerceiroAutorizadoAtivo(anyString(), anyLong())).thenReturn(Boolean.TRUE);
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(montaInstituicao());
        assertEquals(Constantes.TESTE_FALHA, manterDDATerceiro(dto, Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)}
     * .
     */
    @Test
    public final void testManterDDATerceiroExcluirTerceiro() throws ComumNegocioException, ComumException {
        DDATerceiroDto dto = montarDDATerceiroDto();
        whenBoletoCipDao(Boolean.TRUE, Boolean.FALSE);
        whenParametroDao(Boolean.TRUE, Boolean.FALSE);
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(dto.getTipoPessoaTerc(), dto.getNumCpfCnpj(), Boolean.FALSE)).thenReturn(Boolean.TRUE);
        dto.setIndTerceiro('E');
        when(boletoCipDao.obterCpfCnpjPagadorEletronico(anyLong())).thenReturn(Constantes.NOME_TESTE);
        when(boletoCipDao.existeTerceiroAutorizadoAtivo(anyString(), anyLong())).thenReturn(Boolean.TRUE);
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(montaInstituicao());
        assertEquals(Constantes.TESTE_SUCESSO, manterDDATerceiro(dto, Boolean.FALSE));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)}
     * .
     */
    @Test
    public final void testManterDDATerceiroExcluirTerceiroException() throws ComumNegocioException, ComumException {
        DDATerceiroDto dto = montarDDATerceiroDto();
        whenBoletoCipDao(Boolean.TRUE, Boolean.FALSE);
        whenParametroDao(Boolean.TRUE, Boolean.FALSE);
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(dto.getTipoPessoaTerc(), dto.getNumCpfCnpj(), Boolean.FALSE)).thenReturn(Boolean.TRUE);
        dto.setIndTerceiro('E');
        when(boletoCipDao.obterCpfCnpjPagadorEletronico(anyLong())).thenReturn(Constantes.NOME_TESTE);
        when(boletoCipDao.existeTerceiroAutorizadoAtivo(anyString(), anyLong())).thenReturn(Boolean.FALSE);
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(montaInstituicao());
        assertEquals(Constantes.TESTE_FALHA, manterDDATerceiro(dto, Boolean.FALSE));
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @param false1
     * @return String
     * 
     */
    private String manterDDATerceiro(DDATerceiroDto dto, Boolean false1) {
        try {
            ejb.manterDDATerceiro(dto);
            return Constantes.TESTE_SUCESSO;
        } catch (ComumNegocioException e) {
            return Constantes.TESTE_FALHA;
        } catch (ComumException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#obterTermoAdesaoPeloTipo(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testObterTermoAdesaoPeloTipo() throws ComumNegocioException, ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, obterTermoAdesaoPeloTipo());
    }

    private String obterTermoAdesaoPeloTipo() throws ComumNegocioException, ComumException {
        InstituicaoDto inst = montaInstituicao();
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(inst);

        when(
                pagadorEletronicoDDADao.obterTermoPagadorEletronico(new DateTimeDB(), null,
                        new TipoTermoPagador(TipoTermoPagadorEnum.ADESAO_DDA.getValor())
                        .getCodTipoTermoPagador(), Boolean.FALSE, Constantes.INTEGER_UM)).thenReturn(new TermoPagadorDto());

        ejb.obterTermoAdesaoPeloTipo(TipoTermoPagadorEnum.ADESAO_DDA.getValor(), Constantes.INTEGER_UM, Constantes.INTEGER_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#incluirAdesaoDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)}
     * .
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public final void testIncluirAdesaoDDA() throws ComumNegocioException, ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, incluirAdesaoDDA());
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumNegocioException String
     * @throws ComumException
     * 
     */
    private String incluirAdesaoDDA() throws ComumNegocioException, ComumException {
        List<ParticipanteContaDto> listaParticipanteContaDto = new ArrayList<ParticipanteContaDto>();
        ParticipanteContaDto participanteContaDto = new ParticipanteContaDto();
        participanteContaDto.setIdPessoa(Constantes.LONG_UM);
        listaParticipanteContaDto.add(participanteContaDto);
        when(contaCorrenteDelegate.listarParticipanteConta(anyInt(), anyLong())).thenReturn(listaParticipanteContaDto);
        InstituicaoDto inst = montaInstituicao();
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(inst);
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setCpfCnpj(Constantes.CPF_AUX);
        when(capesDelegate.obterPessoa(participanteContaDto.getIdPessoa(), inst.getIdInstituicao())).thenReturn(pessoaDto);
        ejb.incluirAdesaoDDA(new PagadorEletronicoDDADto(Constantes.INTEGER_UM, Constantes.LONG_UM, Constantes.INTEGER_UM, Constantes.SHORT_UM));
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#obterTermoAdesaoPeloSacado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testObterTermoAdesaoPeloSacado() throws ComumNegocioException, ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, obterTermoAdesaoPeloSacado());
    }

    private String obterTermoAdesaoPeloSacado() throws ComumNegocioException, ComumException {
        InstituicaoDto inst = montaInstituicao();
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(inst);

        ParticipanteContaDto participanteContaDto = new ParticipanteContaDto();
        participanteContaDto.setIdPessoa(Constantes.LONG_UM);
        List<ParticipanteContaDto> listaParticipanteContaDto = new ArrayList<ParticipanteContaDto>();
        listaParticipanteContaDto.add(participanteContaDto);
        when(contaCorrenteDelegate.listarParticipanteConta(inst.getIdInstituicao(), Constantes.LONG_UM)).thenReturn(listaParticipanteContaDto);

        String numCpfCnpj = Constantes.CPF_AUX;
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setCpfCnpj(numCpfCnpj);
        when(capesDelegate.obterPessoa(participanteContaDto.getIdPessoa(), inst.getIdInstituicao())).thenReturn(pessoaDto);

        when(pagadorEletronicoDDADao.obterHistoricoPagadorEletronicoPeloCpfCnpj(Constantes.INTEGER_UM, pessoaDto.getCpfCnpj(), inst.getIdInstituicao())).thenReturn(
                new HistoricoTermoPagador(new DateTimeDB(), new TipoTermoPagador(TipoTermoPagadorEnum.ADESAO_DDA.getValor())));

        ejb.obterTermoAdesaoPeloSacado(Constantes.INTEGER_UM, Constantes.LONG_UM, Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.CPF_AUX);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#cancelarAdesaoDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)}
     * .
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public final void testCancelarAdesaoDDA() throws ComumNegocioException, ComumException {
        List<ParticipanteContaDto> listaParticipanteContaDto = new ArrayList<ParticipanteContaDto>();
        ParticipanteContaDto participanteContaDto = new ParticipanteContaDto();
        participanteContaDto.setIdPessoa(Constantes.LONG_UM);
        listaParticipanteContaDto.add(participanteContaDto);
        when(contaCorrenteDelegate.listarParticipanteConta(anyInt(), anyLong())).thenReturn(listaParticipanteContaDto);
        InstituicaoDto inst = montaInstituicao();
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(inst);
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setCpfCnpj(Constantes.CPF_AUX);
        when(capesDelegate.obterPessoa(participanteContaDto.getIdPessoa(), inst.getIdInstituicao())).thenReturn(pessoaDto);

        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, cancelarAdesaoDDA());
    }

    private String cancelarAdesaoDDA() throws ComumNegocioException, ComumException {
        ejb.cancelarAdesaoDDA(new PagadorEletronicoDDADto(Constantes.INTEGER_UM, Constantes.LONG_UM, Constantes.INTEGER_UM, Constantes.SHORT_UM));
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#comprovanteAdesaoDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testComprovanteAdesaoDDA() throws ComumNegocioException, ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, comprovanteAdesaoDDA());
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException
     * @throws ComumNegocioException
     * 
     */
    private String comprovanteAdesaoDDA() throws ComumNegocioException, ComumException {
        InstituicaoDto inst = montaInstituicao();
        when(sciDelegate.obterInstituicaoPorCooperativaCache(anyInt())).thenReturn(inst);

        ParticipanteContaDto participanteContaDto = new ParticipanteContaDto();
        participanteContaDto.setIdPessoa(Constantes.LONG_UM);
        List<ParticipanteContaDto> listaParticipanteContaDto = new ArrayList<ParticipanteContaDto>();
        listaParticipanteContaDto.add(participanteContaDto);
        when(contaCorrenteDelegate.listarParticipanteConta(inst.getIdInstituicao(), Constantes.LONG_UM)).thenReturn(listaParticipanteContaDto);

        String numCpfCnpj = Constantes.CPF_AUX;
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setCpfCnpj(numCpfCnpj);
        when(capesDelegate.obterPessoa(participanteContaDto.getIdPessoa(), inst.getIdInstituicao())).thenReturn(pessoaDto);

        ejb.listarComprovanteAdesaoDDA(Constantes.LONG_UM, Constantes.INTEGER_UM, Constantes.INTEGER_UM, Constantes.CPF_AUX);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#isCpfCnpjPagadorEletronico(java.lang.Character, java.lang.String, boolean)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testIsCpfCnpjPagadorEletronico() throws ComumNegocioException, ComumException {
        whenParametroDao(Boolean.TRUE, Boolean.TRUE);
        assertEquals(Constantes.TESTE_SUCESSO, isCpfCnpjPagadorEletronico(Constantes.TIPO_PESSOA_FISICA, Constantes.CPF_AUX));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#isCpfCnpjPagadorEletronico(java.lang.Character, java.lang.String, boolean)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testIsCpfCnpjPagadorEletronicoTipoPessoaNull() throws ComumNegocioException, ComumException {
        whenParametroDao(Boolean.TRUE, Boolean.TRUE);
        assertEquals(Constantes.TESTE_FALHA, isCpfCnpjPagadorEletronico(' ', Constantes.CPF_AUX));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#isCpfCnpjPagadorEletronico(java.lang.Character, java.lang.String, boolean)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testIsCpfCnpjPagadorEletronicoTipoCpfCnpjNull() throws ComumNegocioException, ComumException {
        whenParametroDao(Boolean.TRUE, Boolean.TRUE);
        assertEquals(Constantes.TESTE_FALHA, isCpfCnpjPagadorEletronico(Constantes.TIPO_PESSOA_FISICA, null));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#isCpfCnpjPagadorEletronico(java.lang.Character, java.lang.String, boolean)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testIsCpfCnpjPagadorEletronicoTipoCpfCnpjIvalido() throws ComumNegocioException, ComumException {
        whenParametroDao(Boolean.TRUE, Boolean.TRUE);
        assertEquals(Constantes.TESTE_FALHA, isCpfCnpjPagadorEletronico(Constantes.TIPO_PESSOA_FISICA, Constantes.CPF_AUX + 9999));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#isCpfCnpjPagadorEletronico(java.lang.Character, java.lang.String, boolean)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testIsCpfCnpjPagadorEletronicoTipoPessoaDiferente() throws ComumNegocioException, ComumException {
        whenParametroDao(Boolean.TRUE, Boolean.TRUE);
        assertEquals(Constantes.TESTE_FALHA, isCpfCnpjPagadorEletronico('P', Constantes.CPF_AUX));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#isCpfCnpjPagadorEletronico(java.lang.Character, java.lang.String, boolean)}
     * .
     * 
     * @throws ComumException
     * @throws ComumNegocioException
     */
    @Test
    public final void testIsCpfCnpjPagadorEletronicoParametroDDAInativo() throws ComumNegocioException, ComumException {
        when(parametroDao.obterValorBoolean(anyLong(), anyInt())).thenReturn(Boolean.FALSE);
        assertEquals(Constantes.TESTE_SUCESSO, isCpfCnpjPagadorEletronico(Constantes.TIPO_PESSOA_FISICA, Constantes.CPF_AUX));
    }

    /**
     * Método responsável por
     * 
     * @param tipoPessoaFisica
     * @param cpfAux
     * @return
     * @throws ComumNegocioException
     * @throws ComumException String
     * 
     */
    private String isCpfCnpjPagadorEletronico(char tipoPessoaFisica, String cpfAux) throws ComumNegocioException, ComumException {
        try {
            ejb.isCpfCnpjPagadorEletronico(tipoPessoaFisica, cpfAux, Boolean.TRUE);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }

    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#atualizarSacadoEletronico(java.lang.Integer)}.
     * 
     * @throws ComumException
     */
    @Test
    public final void testAtualizarSacadoEletronico() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, atualizarSacadoEletronico());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#atualizarSacadoEletronico(java.lang.Integer)}.
     * 
     * @throws ComumException
     */
    private String atualizarSacadoEletronico() throws ComumException {
        ejb.atualizarSacadoEletronico(Constantes.NUM_COOP_0001);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#obterDadosPagador(java.lang.String, java.lang.Integer)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testObterDadosPagadorStringInteger() throws ComumException {
        assertEquals(Constantes.TESTE_SUCESSO, obterDadosPagadorStringInteger());
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException String
     * 
     */
    private String obterDadosPagadorStringInteger() throws ComumException {
        ejb.obterDadosPagador(Constantes.CPF_AUX);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarAdesao() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.FALSE);
        when(contaCorrenteDelegate.listarNumContaCorrenteAtiva(Constantes.INTEGER_UM, Constantes.CPF_AUX)).thenReturn(new ArrayList<String>());
        when(pagadorEletronicoDDADao.obterPagadorAgregadoDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<PagadorAgregadoDto>());
        when(pagadorEletronicoDDADao.obterMensagemPendentePagadorDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<MensagemPendentePagadorDto>());
        when(pagadorEletronicoDDADao.listarHistoricoPagadorEletronico(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(new ArrayList<HistoricoPagadorEletronicoDto>());
        PagadorDto dto = montarPagadorDto();
        dto.setListaPagadorAgregado(null);
        assertEquals(Constantes.TESTE_SUCESSO, solicitarAdesao(dto));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarAdesaoExceptionPossuiAdesao() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.existeSolicitacaoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.TRUE);
        PagadorDto dto = montarPagadorDto();
        dto.setListaPagadorAgregado(null);
        assertEquals(Constantes.TESTE_FALHA, solicitarAdesao(dto));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarNaoPagadorEletronico() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.TRUE);
        PagadorDto dto = montarPagadorDto();
        dto.setListaPagadorAgregado(null);
        assertEquals(Constantes.TESTE_FALHA, solicitarAdesao(dto));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarAdesaoExceptionPossuiAgregados() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.existeSolicitacaoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        PagadorDto dto = montarPagadorDto();
        assertEquals(Constantes.TESTE_FALHA, solicitarAdesao(dto));
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String solicitarAdesao(PagadorDto dto) {
        try {
            ejb.solicitarAdesao(dto, Constantes.SHORT_UM, Boolean.TRUE);
        } catch (ComumNegocioException e) {
            return Constantes.TESTE_FALHA;
        } catch (ComumException e) {
            return Constantes.TESTE_FALHA;
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarCancelamentoAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarCancelamentoAdesao() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.TRUE);
        when(pagadorEletronicoDDADao.existeSolicitacaoCancelamentoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(contaCorrenteDelegate.listarNumContaCorrenteAtiva(Constantes.INTEGER_UM, Constantes.CPF_AUX)).thenReturn(new ArrayList<String>());
        when(pagadorEletronicoDDADao.obterPagadorAgregadoDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<PagadorAgregadoDto>());
        when(pagadorEletronicoDDADao.obterMensagemPendentePagadorDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<MensagemPendentePagadorDto>());
        when(pagadorEletronicoDDADao.listarHistoricoPagadorEletronico(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(new ArrayList<HistoricoPagadorEletronicoDto>());
        PagadorDto dto = montarPagadorDto();
        assertEquals(Constantes.TESTE_SUCESSO, solicitarCancelamentoAdesao(dto));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarCancelamentoAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarCancelamentoAdesaoIsCpfCnpjPagadorEletronicoException() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.FALSE);
        PagadorDto dto = montarPagadorDto();
        assertEquals(Constantes.TESTE_FALHA, solicitarCancelamentoAdesao(dto));
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarCancelamentoAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarCancelamentoAdesaoExisteSolicitacaoCancelamentoAdesaoException() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.TRUE);
        when(pagadorEletronicoDDADao.existeSolicitacaoCancelamentoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.TRUE);
        PagadorDto dto = montarPagadorDto();
        assertEquals(Constantes.TESTE_FALHA, solicitarCancelamentoAdesao(dto));
    }

    /**
     * Método responsável por
     * 
     * @param dto
     * @return String
     * 
     */
    private String solicitarCancelamentoAdesao(PagadorDto dto) {
        try {
            ejb.solicitarCancelamentoAdesao(dto, Constantes.SHORT_UM, Boolean.TRUE);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarInclusaoPagadorAgregado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto, java.lang.String, java.lang.Integer, java.lang.Short)}
     * .
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public final void testSolicitarInclusaoPagadorAgregado() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.TRUE);
        when(pagadorEletronicoDDADao.existeSolicitacaoCancelamentoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.existeSolicitacaoAgregado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.agregadoJaCadastrado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);

        when(contaCorrenteDelegate.listarNumContaCorrenteAtiva(Constantes.INTEGER_UM, Constantes.CPF_AUX)).thenReturn(new ArrayList<String>());
        when(pagadorEletronicoDDADao.obterPagadorAgregadoDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<PagadorAgregadoDto>());
        when(pagadorEletronicoDDADao.obterMensagemPendentePagadorDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<MensagemPendentePagadorDto>());
        when(pagadorEletronicoDDADao.listarHistoricoPagadorEletronico(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(new ArrayList<HistoricoPagadorEletronicoDto>());
        assertEquals(Constantes.TESTE_SUCESSO, solicitarInclusaoPagadorAgregado());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarInclusaoPagadorAgregado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto, java.lang.String, java.lang.Integer, java.lang.Short)}
     * .
     * 
     * @throws ComumNegocioException
     * @throws ComumException
     */
    @Test
    public final void testSolicitarInclusaoPagadorAgregadoExisteException() throws ComumException {
        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.TRUE);
        when(pagadorEletronicoDDADao.existeSolicitacaoCancelamentoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.existeSolicitacaoAgregado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.agregadoJaCadastrado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.TRUE);

        when(contaCorrenteDelegate.listarNumContaCorrenteAtiva(Constantes.INTEGER_UM, Constantes.CPF_AUX)).thenReturn(new ArrayList<String>());
        when(pagadorEletronicoDDADao.obterPagadorAgregadoDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<PagadorAgregadoDto>());
        when(pagadorEletronicoDDADao.obterMensagemPendentePagadorDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<MensagemPendentePagadorDto>());
        when(pagadorEletronicoDDADao.listarHistoricoPagadorEletronico(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(new ArrayList<HistoricoPagadorEletronicoDto>());
        assertEquals(Constantes.TESTE_FALHA, solicitarInclusaoPagadorAgregado());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String solicitarInclusaoPagadorAgregado() {
        try {
            ejb.solicitarInclusaoPagadorAgregado(montarAgregadoDto(), Constantes.CPF_AUX, Constantes.INTEGER_UM, Constantes.SHORT_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (ComumException e) {
            return Constantes.TESTE_FALHA;
        } catch (ComumNegocioException e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarExclusaoPagadorAgregado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto, java.lang.String, java.lang.Integer, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarExclusaoPagadorAgregado() throws ComumException {
        PagadorAgregadoDto dto = montarAgregadoDto();
        when(pagadorEletronicoDDADao.existeSolicitacaoCancelamentoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.existeSolicitacaoAgregado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.agregadoJaCadastrado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);

        when(pagadorEletronicoDDADao.isCpfCnpjPagadorEletronico(Constantes.CPF_AUX, Boolean.TRUE)).thenReturn(Boolean.TRUE);
        when(contaCorrenteDelegate.listarNumContaCorrenteAtiva(Constantes.INTEGER_UM, Constantes.CPF_AUX)).thenReturn(new ArrayList<String>());
        when(pagadorEletronicoDDADao.obterPagadorAgregadoDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<PagadorAgregadoDto>());
        when(pagadorEletronicoDDADao.obterMensagemPendentePagadorDDA(Constantes.CPF_AUX)).thenReturn(new ArrayList<MensagemPendentePagadorDto>());
        when(pagadorEletronicoDDADao.listarHistoricoPagadorEletronico(Constantes.CPF_AUX, Constantes.INTEGER_UM)).thenReturn(new ArrayList<HistoricoPagadorEletronicoDto>());

        assertEquals(Constantes.TESTE_SUCESSO, solicitarExclusaoPagadorAgregado());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarExclusaoPagadorAgregado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto, java.lang.String, java.lang.Integer, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarExclusaoPagadorAgregadoExisteCancelamento() throws ComumException {
        when(pagadorEletronicoDDADao.existeSolicitacaoCancelamentoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.TRUE);
        when(pagadorEletronicoDDADao.existeSolicitacaoAgregado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.agregadoJaCadastrado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, solicitarExclusaoPagadorAgregado());
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#solicitarExclusaoPagadorAgregado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto, java.lang.String, java.lang.Integer, java.lang.Short)}
     * .
     * 
     * @throws ComumException
     */
    @Test
    public final void testSolicitarExclusaoPagadorAgregadoExisteSolicitacao() throws ComumException {
        when(pagadorEletronicoDDADao.existeSolicitacaoCancelamentoAdesao(Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        when(pagadorEletronicoDDADao.existeSolicitacaoAgregado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.TRUE);
        when(pagadorEletronicoDDADao.agregadoJaCadastrado(Constantes.CPF_AUX, Constantes.CPF_AUX)).thenReturn(Boolean.FALSE);
        assertEquals(Constantes.TESTE_FALHA, solicitarExclusaoPagadorAgregado());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String solicitarExclusaoPagadorAgregado() {
        try {
            ejb.solicitarExclusaoPagadorAgregado(montarAgregadoDto(), Constantes.CPF_AUX, Constantes.INTEGER_UM, Constantes.SHORT_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#listarHistoricoPagadorEletronico(java.lang.String, java.lang.Integer)}
     * .
     */
    @Test
    public final void testListarHistoricoPagadorEletronico() {
        assertEquals(Constantes.TESTE_SUCESSO, listarHistoricoPagadorEletronico());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String listarHistoricoPagadorEletronico() {
        try {
            ejb.listarHistoricoPagadorEletronico(Constantes.CPF_AUX, Constantes.INTEGER_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#obterTermoPagadorEletronico(br.com.bancoob.persistencia.types.DateTimeDB, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short, java.lang.Boolean)}
     * .
     */
    @Test
    public final void testObterTermoPagadorEletronico() {
        assertEquals(Constantes.TESTE_SUCESSO, obterTermoPagadorEletronico());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String obterTermoPagadorEletronico() {
        try {
            ejb.obterTermoPagadorEletronico(Constantes.DATE_TIME_DB_AUX, Constantes.DATE_TIME_DB_AUX, Constantes.SHORT_UM, Boolean.TRUE, Constantes.INTEGER_UM);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#obterDadosPagador(java.lang.String)}
     * .
     * 
     * @throws OperacionalException
     */
    @Test
    public final void testObterDadosPagadorString() throws OperacionalException {
        List<PagadorDto> listaPagador = new ArrayList<PagadorDto>();
        listaPagador.add(montarPagadorDto());
        when(pagadorEletronicoDDADao.listarPagadorEletronico(Constantes.INTEGER_UM)).thenReturn(listaPagador);
        assertEquals(Constantes.TESTE_SUCESSO, obterDadosPagadorString());
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String obterDadosPagadorString() {
        try {
            ejb.obterDadosPagador(Constantes.CPF_AUX);
            return Constantes.TESTE_SUCESSO;
        } catch (Exception e) {
            return Constantes.TESTE_FALHA;
        }
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#relatorioDetalhadoPagadorEletronico(java.lang.String)}
     * .
     */
    @Ignore
    public final void testRelatorioDetalhadoPagadorEletronico() {
    }

    /**
     * Test method for
     * {@link br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.PagadorEletronicoDDAServicoEJB#relatorioListarPagadorAgregado(java.lang.String, java.lang.Integer)}
     * .
     */
    @Ignore
    public final void testRelatorioListarPagadorAgregado() {
    }

    /**
     * Método responsável por
     * 
     * @return PagadorEletronicoDDADto
     * 
     */
    private PagadorEletronicoDDADto montarPagadorEletronicoDDADto() {
        PagadorEletronicoDDADto dto = new PagadorEletronicoDDADto();
        dto.setNumIdentDDA(Constantes.CEM);
        dto.setIndAceite(Constantes.STRING_NUMERO_10);
        dto.setNumCooperativa(Constantes.INTEGER_UM);
        dto.setCodCanal(Constantes.INTEGER_DEZ);
        dto.setNumCoopCartao(Constantes.INTEGER_DEZ);
        dto.setNumConta(Constantes.CEM);
        dto.setNumPac(Constantes.INTEGER_DEZ);
        dto.setDescTerminal(Constantes.NOME_TESTE);
        return dto;
    }

    /**
     * Método responsável por
     * 
     * @return BoletoDDA
     * 
     */
    private BoletoDDA montarBoletoDDA() {
        BoletoDDA boleto = new BoletoDDA();
        boleto.setNumRefAtualCadAceite(Constantes.LONG_UM);
        boleto.setNumSeqAtualAceite(Constantes.LONG_UM);
        return boleto;
    }

    /**
     * Método responsável por
     * 
     * @return InstituicaoDto
     * 
     */
    private InstituicaoDto montaInstituicao() {
        InstituicaoDto instDto = new InstituicaoDto();
        instDto.setIdInstituicao(Constantes.INTEGER_UM);
        return instDto;
    }

    /**
     * Método responsável por
     * 
     * @return DDATerceiroDto
     * 
     */
    private DDATerceiroDto montarDDATerceiroDto() {
        /*
         * EXCLUIR_TERCEIRO = 'E'; ALTERAR_TERCEIRO = 'A'; INCLUIR_TERCEIRO = 'I';
         */

        DDATerceiroDto dto = new DDATerceiroDto();
        dto.setNumCpfCnpj(Constantes.CPF_AUX);
        dto.setNumIdentDDA(Constantes.LONG_UM);
        dto.setNumCoopCartao(Constantes.INTEGER_UM);
        dto.setNumCooperativa(Constantes.INTEGER_DEZ);
        dto.setIndTerceiro('A');
        dto.setTipoPessoaTerc('A');
        return dto;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorDto
     * 
     */
    private PagadorDto montarPagadorDto() {
        PagadorDto dto = new PagadorDto();
        dto.setNumCpfCnpj(Constantes.CPF_AUX);
        List<PagadorAgregadoDto> lista = new ArrayList<PagadorAgregadoDto>();
        PagadorAgregadoDto agregadoDto = new PagadorAgregadoDto();
        agregadoDto.setIdPagadorDDA(Constantes.LONG_UM);
        lista.add(agregadoDto);
        dto.setListaPagadorAgregado(lista);
        dto.setIdInstituicao(Constantes.INTEGER_UM);
        return dto;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorAgregadoDto
     * 
     */
    private PagadorAgregadoDto montarAgregadoDto() {
        PagadorAgregadoDto dto = new PagadorAgregadoDto();
        dto.setNumCpfCnpj(Constantes.CPF_AUX);
        return dto;
    }

}
