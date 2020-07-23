/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         TratamentoPendenciaErroServico.java
 * Data Cria��o:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DadosTratamentoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoPendenciaErroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ComumCrudServico;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.tipos.DateTime;

/**
 * TratamentoPendenciaErroServico � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface TratamentoPendenciaErroServico extends ComumCrudServico<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws OperacionalException TratamentoPendenciaErroDto
     * 
     */
    TratamentoPendenciaErroDto listarTratamentoPendenciaErro() throws OperacionalException;

    /**
     * @param dataMovimento
     * @param codSituacaoMensagemDDA
     * @param codTipoMensagemDD
     * @param maxResult
     * @return
     * @throws OperacionalException DadosTratamentoMensagemDto
     * 
     */
    DadosTratamentoMensagemDto obterDadosTratamentoMensagemPendencia(DateTime dataMovimento, Short codSituacaoMensagemDDA, String codTipoMensagemDD, int maxResult) throws OperacionalException;

    /**
     * @param codTipoErroCIP
     * @param maxResult
     * @return
     * @throws OperacionalException DadosTratamentoMensagemDto
     * 
     */
    DadosTratamentoMensagemDto obterDadosTratamentoMensagemErroAgrupado(String codTipoErroCIP, int maxResult) throws OperacionalException;

    /**
     * @param dataMovimento
     * @param maxResult
     * @return
     * @throws OperacionalException DadosTratamentoMensagemDto
     * 
     */
    DadosTratamentoMensagemDto obterDadosTratamentoArquivoErroRetornoCIP(DateTime dataMovimento, int maxResult) throws OperacionalException;

    /**
     * @return List<TipoTratamentoErroCip>
     * 
     */
    List<TipoTratamentoErroCip> listarTipoTratamentoMensagemContingencia();

    /**
     * M�todo respons�vel por
     * 
     * @param tratamento
     * @throws BancoobException void
     * 
     */
    void executarTratamentoMensagem(TratamentoMesagemDto tratamento) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @throws BancoobException void
     * 
     */
    void executarTratamentoAutomatizadoMensagem() throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param dataMovimento
     * @param idMensagemInicial
     * @param idMensagemFinal
     * @param codTipoMensagem
     * @throws BancoobException void
     * 
     */
    void executarTratamentoMensagemContingenciaBatch(DateTimeDB dataMovimento, Long idMensagemInicial, Long idMensagemFinal, String codTipoMensagem) throws BancoobException;

    /**
     * M�todo respons�vel por
     * 
     * @param idMensagemDDA
     * @return
     * @throws OperacionalNegocionException MensagemDDA
     * 
     */
    MensagemDDA obterDetalheMensagemErro(Long idMensagemDDA) throws OperacionalNegocionException;

    /**
     * M�todo respons�vel por
     * 
     * @return List<TipoTratamentoErroCip>
     * 
     */
    List<TipoTratamentoErroCip> listarTipoTratamentoSitMensagemRetornoComErro();

    /**
     * M�todo respons�vel por
     * 
     * @param idMensagemDDA
     * @throws OperacionalNegocionException
     * @throws BancoobException void
     * 
     */
    void excluirMensagemErro(Long idMensagemDDA) throws OperacionalNegocionException, BancoobException;

    /**
     * @param listaMensagemDDA
     * @throws ComumException void
     * 
     */
    void excluirListaMensagemErro(List<MensagemDDA> listaMensagemDDA) throws ComumException;

}
