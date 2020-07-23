/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         TratamentoPendenciaErroDelegate.java
 * Data Cria��o:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumCrudDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DadosTratamentoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoPendenciaErroDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;
import br.com.sicoob.tipos.DateTime;

/**
 * TratamentoPendenciaErroDelegate � respons�vel por
 * 
 * @author Felipe.Rosa
 */
@SuppressWarnings("rawtypes")
public class TratamentoPendenciaErroDelegate extends ComumCrudDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected TratamentoPendenciaErroServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarTratamentoPendenciaErroServico();
    }

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws OperacionalException TratamentoPendenciaErroDto
     * 
     */
    public TratamentoPendenciaErroDto listarTratamentoPendenciaErro() throws OperacionalException {
        return localizarServico().listarTratamentoPendenciaErro();
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dataMovimento
     * @param codSituacaoMensagemDDA
     * @param codTipoMensagemDDA
     * @param maxResult
     * @return
     * @throws OperacionalException DadosTratamentoMensagemDto
     * 
     */
    public DadosTratamentoMensagemDto obterDadosTratamentoMensagemPendencia(DateTime dataMovimento, Short codSituacaoMensagemDDA, String codTipoMensagemDDA, int maxResult) throws OperacionalException {
        return localizarServico().obterDadosTratamentoMensagemPendencia(dataMovimento, codSituacaoMensagemDDA, codTipoMensagemDDA, maxResult);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param codTipoErroCIP
     * @param maxResult
     * @return
     * @throws OperacionalException DadosTratamentoMensagemDto
     * 
     */
    public DadosTratamentoMensagemDto obterDadosTratamentoMensagemErroAgrupado(String codTipoErroCIP, int maxResult) throws OperacionalException {
        return localizarServico().obterDadosTratamentoMensagemErroAgrupado(codTipoErroCIP, maxResult);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param dataMovimento
     * @param maxResult
     * @return
     * @throws OperacionalException DadosTratamentoMensagemDto
     * 
     */
    public DadosTratamentoMensagemDto obterDadosTratamentoArquivoErroRetornoCIP(DateTime dataMovimento, int maxResult) throws OperacionalException {
        return localizarServico().obterDadosTratamentoArquivoErroRetornoCIP(dataMovimento, maxResult);
    }

    /**
     * M�todo respons�vel por
     * 
     * @return List<TipoTratamentoErroCip>
     * 
     */
    public List<TipoTratamentoErroCip> listarTipoTratamentoMensagemContingencia() {
        return localizarServico().listarTipoTratamentoMensagemContingencia();
    }

    /**
     * M�todo respons�vel por
     * 
     * @param tratamento
     * @throws BancoobException
     * 
     */
    public void executarTratamentoMensagem(TratamentoMesagemDto tratamento) throws BancoobException {
        localizarServico().executarTratamentoMensagem(tratamento);
    }

    /**
     * M�todo respons�vel por
     * 
     * @throws BancoobException
     * 
     */
    public void executarTratamentoAutomatizadoMensagem() throws BancoobException {
        localizarServico().executarTratamentoAutomatizadoMensagem();
    }

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
    public void executarTratamentoMensagemContingenciaBatch(DateTimeDB dataMovimento, Long idMensagemInicial, Long idMensagemFinal, String codTipoMensagem) throws BancoobException {
        localizarServico().executarTratamentoMensagemContingenciaBatch(dataMovimento, idMensagemInicial, idMensagemFinal, codTipoMensagem);
    }

    /**
     * M�todo respons�vel por
     * 
     * @param idMensagemDDA
     * @return
     * @throws OperacionalNegocionException MensagemDDA
     * 
     */
    public MensagemDDA obterDetalheMensagemErro(Long idMensagemDDA) throws OperacionalNegocionException {
        return localizarServico().obterDetalheMensagemErro(idMensagemDDA);
    }

    /**
     * M�todo respons�vel por
     * 
     * @return List<TipoTratamentoErroCip>
     * 
     */
    public List<TipoTratamentoErroCip> listarTipoTratamentoSitMensagemRetornoComErro() {
        return localizarServico().listarTipoTratamentoSitMensagemRetornoComErro();
    }

    /**
     * M�todo respons�vel por
     * 
     * @param idMensagemDDA
     * @throws BancoobException void
     * 
     */
    public void excluirMensagemErro(Long idMensagemDDA) throws BancoobException {
        localizarServico().excluirMensagemErro(idMensagemDDA);
    }

    /**
     * M�todo respons�vel por excluir uma lista de mensagemDDA
     * 
     * @param listaIdMensagemDDA void
     * @throws ComumException
     * 
     */
    public void excluirListaMensagemErro(List<MensagemDDA> listaMensagemDDA) throws ComumException {
        localizarServico().excluirListaMensagemErro(listaMensagemDDA);
    }

}
