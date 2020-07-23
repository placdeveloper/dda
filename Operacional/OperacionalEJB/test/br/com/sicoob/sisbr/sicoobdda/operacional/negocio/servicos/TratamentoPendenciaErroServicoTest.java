/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional
 * Arquivo:         TratamentoPendenciaErroServicoTest.java
 * Data Criação:    Sep 20, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoTratamentoErroCipEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarContingenciaMensagemDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.TratamentoPendenciaErroServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao;
import br.com.sicoob.tipos.DateTime;
import junit.framework.Assert;

/**
 * TratamentoPendenciaErroServicoTest é responsável por
 * 
 * @author Felipe.Rosa
 */
@RunWith(MockitoJUnitRunner.class)
public class TratamentoPendenciaErroServicoTest extends Mockito {

    @InjectMocks
    private TratamentoPendenciaErroServicoEJB ejb;

    @Mock
    private TratamentoPendenciaErroDao dao;

    @Mock
    private ADMDelegate admDelegate;

    @Mock
    @SuppressWarnings("unused")
    private ProcessarContingenciaMensagemDelegate processarContingenciaDelegate;

    /**
     * Método responsável por void
     * 
     * @throws OperacionalException
     * 
     */
    @Test
    public void listarTratamentoPendenciaErroPassou() throws OperacionalException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarTratamentoPendenciaErro());
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalException
     * 
     */
    @Test
    public void obterDadosTratamentoMensagemPendenciaRetornoErroPassou() throws OperacionalException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterDadosTratamentoMensagemPendencia(SituacaoMensagemDDA.RETORNO_COM_MENSAGEM_DE_ERRO));
        verify(dao, times(0)).obterListaTratamentoErroCIP(any(DateTime.class), anyShort(), anyString(), anyInt());
        verify(dao, times(1)).obterListaTratamentoRetornoErro(anyString(), any(DateTime.class), anyInt());
        verifyListaTipoTratamentoErroCip(SituacaoMensagemDDA.RETORNO_COM_MENSAGEM_DE_ERRO);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalException
     * 
     */
    @Test
    public void obterDadosTratamentoMensagemPendenciaErroCIPPassou() throws OperacionalException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterDadosTratamentoMensagemPendencia(SituacaoMensagemDDA.RETORNO_COM_ERRO_CONTIGENCIA));
        verify(dao, times(1)).obterListaTratamentoErroCIP(any(DateTime.class), anyShort(), anyString(), anyInt());
        verify(dao, times(0)).obterListaTratamentoRetornoErro(anyString(), any(DateTime.class), anyInt());
        verifyListaTipoTratamentoErroCip(SituacaoMensagemDDA.RETORNO_COM_ERRO_CONTIGENCIA);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalException
     * 
     */
    @Test
    public void obterDadosTratamentoMensagemErroAgrupadoPassou() throws OperacionalException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterDadosTratamentoMensagemErroAgrupado());
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalException
     * 
     */
    @Test
    public void obterDadosTratamentoArqvuioErroRetornoCIPPassou() throws OperacionalException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterDadosTratamentoArquivoErroRetornoCIP());
        verify(dao, times(1)).obterListaTratamentoMensagemArquivo(any(DateTime.class), anyInt());
        verifyListaTipoTratamentoErroCip(SituacaoMensagemDDA.ARQUIVO_ENVIO_COM_ERRO);
    }

    /**
     * Método responsável por void
     * 
     * @throws OperacionalException
     * 
     */
    @Test
    public void listarTipoTratamentoMensagemContingenciaPassou() throws OperacionalException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, listarTipoTratamentoMensagemContingencia());
        verifyListaTipoTratamentoErroCip(SituacaoMensagemDDA.RETORNO_COM_ERRO_CONTIGENCIA);
    }

    /**
     * Método responsável por
     * 
     * @throws ComumException void
     * 
     */
    @Test
    public void executarTratamentoMensagemAtualizarDataMovimentoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.ATUALIZAR_DATA_MOVIMENTO));
        verify(admDelegate, times(1)).obterDataMovimentoBancoob();
        verify(admDelegate, times(0)).obterDataProximoMovimentoBancoob();
        verify(dao, times(0)).atualizarDataMovimentoMensagem(any(Date.class));
        verify(dao, times(1)).atualizarDataMovimentoMensagem(anyListOf(Long.class), any(Date.class));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void executarTratamentoMensagemAtualizarDataMovimentoListaIdNullPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.ATUALIZAR_DATA_MOVIMENTO, Boolean.TRUE));
        verify(dao, times(1)).atualizarDataMovimentoMensagem(any(Date.class));
        verify(dao, times(0)).atualizarDataMovimentoMensagem(anyListOf(Long.class), any(Date.class));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void executarTratamentoMensagemReenviarSSPBPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.REENVIAR_SSPB));
        verifyReenviarSSPBMensagem();
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void executarTratamentoMensagemReenviarCIPPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.REENVIAR_CIP));
        verify(dao, times(1)).reenviarCIPMensagem(anyListOf(Long.class), any(Date.class));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void executarTratamentoMensagemFinalizarSemAcaoPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.FINALIZAR_SEM_ACAO));
        verifyFinalizarMensagem();
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    // @Test
    // public void executarTratamentoMensagemFinalizarReenviarPassou() throws ComumException {
    // Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.FINALIZAR_REENVIAR_NOVA_MSG));
    // verifyReenviarMensagemFinalizadaSSPB();
    // }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void executarTratamentoMensagemFinalizarReenviarAtualizadoPassou() {
        Assert.assertEquals("integracaocip.erro.tratamento.nao.liberado", executarTratamentoMensagem(TipoTratamentoErroCipEnum.FINALIZAR_REENVIAR_ATUALIZACAO_DADOS));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void executarTratamentoMensagemReprocessarArquivoPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.REPROCESSAR_MENSAGEM_CONTINGENCIA));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void executarTratamentoMensagemPrepararReenvioPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.PREPARAR_REENVIO_ARQUIVO_CIP));
        verify(dao, times(1)).prepararReenvioArquivo(anyListOf(Long.class));
    }

    /**
     * Método responsável por
     * 
     * @throws IntegracaoInternaException void
     * 
     */
    @Test
    public void executarTratamentoMensagemAtualizarDataProximoMovimentoPassou() throws IntegracaoInternaException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.ATUALIZAR_DATA_PROXIMO_MOVIMENTO));
        verify(admDelegate, times(0)).obterDataMovimentoBancoob();
        verify(admDelegate, times(1)).obterDataProximoMovimentoBancoob();
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void executarTratamentoAutomatizadoMensagemPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoAutomatizadoMensagem(geraListaTratamento()));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void executarTratamentoAutomatizadoMensagemListaNull() {
        Assert.assertEquals("integracaocip.erro.tratamento.automatizado.nenhuma.ocorrencia", executarTratamentoAutomatizadoMensagem(null));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void executarTratamentoAutomatizadoMensagemListaVazia() {
        Assert.assertEquals("integracaocip.erro.tratamento.automatizado.nenhuma.ocorrencia", executarTratamentoAutomatizadoMensagem(geraListaTratamentoVazia()));
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException
     * 
     */
    @Test
    public void executarTratamentoMensagemContingenciaBatchPassou() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagemContingenciaBatch());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void executarTratamentoMensagemPriorizar() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.PRIORIZAR_MENSAGEM));
        verify(dao, times(1)).atualizarPrioridadeMensagem(anyListOf(Long.class), anyInt());
    }

    /**
     * Método responsável por
     * 
     * @throws BancoobException void
     * 
     */
    @Test
    public void executarTratamentoMensagemDespriorizar() throws BancoobException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, executarTratamentoMensagem(TipoTratamentoErroCipEnum.DESPRIORIZAR_MENSAGEM));
        verify(dao, times(1)).atualizarPrioridadeMensagem(anyListOf(Long.class), anyInt());
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void obterDetalheMensagemMensagemPassou() {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, obterDetalheErroMensagem(Constantes.LONG_UM));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void obterDetalheMensagemMensagemIdInvalido() {
        Assert.assertEquals("integracaocip.erro.mensagem.nao.informada", obterDetalheErroMensagem(Constantes.LONG_ZERO));
    }

    /**
     * Método responsável por void
     * 
     */
    @Test
    public void obterDetalheMensagemMensagemIdNull() {
        Assert.assertEquals("integracaocip.erro.mensagem.nao.informada", obterDetalheErroMensagem(null));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void excluirMensagemErroPassou() throws ComumException {
        Assert.assertEquals(Constantes.TESTE_SUCESSO, excluirMensagemErro(Constantes.LONG_UM));
        verifyExcluirMensagem(1);
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void excluirMensagemErroIdNull() throws ComumException {
        Assert.assertEquals("integracaocip.erro.id.mensagem.nao.informado", excluirMensagemErro(null));
        verifyExcluirMensagem(0);
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    @Test
    public void excluirMensagemErroIdInvalido() throws ComumException {
        Assert.assertEquals("integracaocip.erro.id.mensagem.nao.informado", excluirMensagemErro(Constantes.LONG_ZERO));
        verifyExcluirMensagem(0);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws OperacionalException
     * 
     */
    private String listarTratamentoPendenciaErro() throws OperacionalException {
        ejb.listarTratamentoPendenciaErro();
        verify(dao, times(1)).listarPendencia();
        verify(dao, times(1)).listarErroAgrupado();
        verify(dao, times(1)).listarErroProcessamento();
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws OperacionalException
     * 
     */
    private String obterDadosTratamentoMensagemPendencia(Short situacaoMensagemDDA) throws OperacionalException {
        ejb.obterDadosTratamentoMensagemPendencia(new DateTime(), situacaoMensagemDDA, Constantes.NOME_TESTE, Constantes.INTEGER_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws OperacionalException
     * 
     */
    private String obterDadosTratamentoMensagemErroAgrupado() throws OperacionalException {
        ejb.obterDadosTratamentoMensagemErroAgrupado(Constantes.NOME_TESTE, Constantes.INTEGER_UM);
        verify(dao, times(1)).obterListaTratamentoErroAgrupado(Constantes.NOME_TESTE, Constantes.INTEGER_UM);
        verifyListaTipoTratamentoErroCip(SituacaoMensagemDDA.RETORNO_COM_MENSAGEM_DE_ERRO);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * @throws OperacionalException
     * 
     */
    private String obterDadosTratamentoArquivoErroRetornoCIP() throws OperacionalException {
        ejb.obterDadosTratamentoArquivoErroRetornoCIP(new DateTime(), Constantes.INTEGER_UM);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String listarTipoTratamentoMensagemContingencia() {
        ejb.listarTipoTratamentoMensagemContingencia();
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param tipoTratamento
     * @return String
     * 
     */
    private String executarTratamentoMensagem(TipoTratamentoErroCipEnum tipoTratamento) {
        return executarTratamentoMensagem(tipoTratamento, Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    private String executarTratamentoMensagem(TipoTratamentoErroCipEnum tipoTratamento, Boolean bolListaVazia) {
        try {
            when(admDelegate.obterDataMovimentoBancoob()).thenReturn(new Date());
            when(admDelegate.obterDataProximoMovimentoBancoob()).thenReturn(new Date());
            ejb.executarTratamentoMensagem(geraTratamentoMensagemDto(tipoTratamento, bolListaVazia));
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param listaTratamento
     * @return String
     * 
     */
    private String executarTratamentoAutomatizadoMensagem(List<TratamentoMesagemDto> listaTratamento) {
        try {
            when(dao.obterListaTratamentoAutomatizado()).thenReturn(listaTratamento);
            ejb.executarTratamentoAutomatizadoMensagem();
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws BancoobException
     * 
     */
    private String executarTratamentoMensagemContingenciaBatch() throws BancoobException {
        when(dao.obterListaTratamentoMensagemContingenciaBatch(Constantes.DATE_TIME_DB_AUX, Constantes.LONG_UM, Constantes.LONG_UM, Constantes.STRING_NUMERO_1)).thenReturn(geraListaIdLong());
        ejb.executarTratamentoMensagemContingenciaBatch(Constantes.DATE_TIME_DB_AUX, Constantes.LONG_UM, Constantes.LONG_UM, Constantes.STRING_NUMERO_1);
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param idMensagemDDA
     * @return String
     * 
     */
    private String obterDetalheErroMensagem(Long idMensagemDDA) {
        try {
            ejb.obterDetalheMensagemErro(idMensagemDDA);
            verify(dao, times(1)).obterDetalheMensagemErro(idMensagemDDA);
        } catch (OperacionalNegocionException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param longUm
     * @return String
     * 
     */
    private String excluirMensagemErro(Long idMensagemDDA) {
        try {
            ejb.excluirMensagemErro(idMensagemDDA);
        } catch (BancoobException e) {
            return e.getMessage();
        }
        return Constantes.TESTE_SUCESSO;
    }

    /**
     * Método responsável por
     * 
     * @param vezes void
     * @throws ComumException
     * 
     */
    private void verifyExcluirMensagem(int vezes) throws ComumException {
        verify(dao, times(vezes)).excluirMensagem(anyLong());
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoMensagemDDA void
     * 
     */
    private void verifyListaTipoTratamentoErroCip(Short codSituacaoMensagemDDA) {
        verify(dao, times(1)).listarTipoTratamentoErroCip(codSituacaoMensagemDDA);
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    private void verifyReenviarSSPBMensagem() throws ComumException {
        verify(dao, times(1)).reenviarSSPBMensagem(anyListOf(Long.class), any(Date.class));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    private void verifyReenviarMensagemFinalizadaSSPB() throws ComumException {
        verify(dao, times(1)).reenviarMensagemFinalizadaSSPB(anyListOf(Long.class), any(Date.class));
    }

    /**
     * Método responsável por void
     * 
     * @throws ComumException
     * 
     */
    private void verifyFinalizarMensagem() throws ComumException {
        verify(dao, times(1)).finalizarMensagem(anyListOf(Long.class));
    }

    /**
     * Método responsável por
     * 
     * @return List<TratamentoMesagemDto>
     * 
     */
    private List<TratamentoMesagemDto> geraListaTratamentoVazia() {
        return geraListaTratamento(null);
    }

    /**
     * Método responsável por
     * 
     * @return List<TratamentoMesagemDto>
     * 
     */
    private List<TratamentoMesagemDto> geraListaTratamento() {
        return geraListaTratamento(geraTratamentoMensagemDto(TipoTratamentoErroCipEnum.FINALIZAR_SEM_ACAO));
    }

    /**
     * Método responsável por
     * 
     * @param tratamentoMensagemDto
     * @return List<TratamentoMesagemDto>
     * 
     */
    private List<TratamentoMesagemDto> geraListaTratamento(TratamentoMesagemDto tratamentoMensagemDto) {
        List<TratamentoMesagemDto> lista = new ArrayList<TratamentoMesagemDto>();
        if (!ObjectUtil.isNull(tratamentoMensagemDto)) {
            lista.add(tratamentoMensagemDto);
        }
        return lista;
    }

    /**
     * Método responsável por
     * 
     * @param tipoTratamento
     * @return TratamentoMesagemDto
     * 
     */
    private TratamentoMesagemDto geraTratamentoMensagemDto(TipoTratamentoErroCipEnum tipoTratamento) {
        return geraTratamentoMensagemDto(tipoTratamento, Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param tipoTratamento
     * @param bolListaVazia
     * @return TratamentoMesagemDto
     * 
     */
    private TratamentoMesagemDto geraTratamentoMensagemDto(TipoTratamentoErroCipEnum tipoTratamento, Boolean bolListaVazia) {
        TratamentoMesagemDto tratamento;
        if (!bolListaVazia) {
            tratamento = new TratamentoMesagemDto(Constantes.LONG_UM, Constantes.LONG_UM, tipoTratamento.getCodTipoTratamento());
            tratamento.setListaIdLogRecebimento(geraListaIdLong());
        } else {
            tratamento = new TratamentoMesagemDto(tipoTratamento.getCodTipoTratamento());
        }
        return tratamento;
    }

    /**
     * Método responsável por
     * 
     * @return List<Long>
     * 
     */
    private List<Long> geraListaIdLong() {
        List<Long> lista = new ArrayList<Long>();
        lista.add(Constantes.LONG_UM);
        return lista;
    }

}
