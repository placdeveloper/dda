/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         TratamentoPendenciaErroDao.java
 * Data Criação:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroAgrupadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroProcessamentoCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemErroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PendenciaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.tipos.DateTime;

/**
 * TratamentoPendenciaErroDao é responsável por
 * 
 * @author Felipe.Rosa
 */
public interface TratamentoPendenciaErroDao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por
     * 
     * @return
     * @throws OperacionalException List<PendenciaDto>
     * 
     */
    List<PendenciaDto> listarPendencia() throws OperacionalException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws OperacionalException List<ErroAgrupadoDto>
     * 
     */
    List<ErroAgrupadoDto> listarErroAgrupado() throws OperacionalException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws OperacionalException List<ErroProcessamentoCipDto>
     * 
     */
    List<ErroProcessamentoCipDto> listarErroProcessamento() throws OperacionalException;

    /**
     * Método responsável por
     * 
     * @param codSituacaoMensagemDDA
     * @return List<TipoTratamentoErroCip>
     * 
     */
    List<TipoTratamentoErroCip> listarTipoTratamentoErroCip(Short codSituacaoMensagemDDA);

    /**
     * Método responsável por
     * 
     * @param dataMovimento
     * @param codSituacaoMensagemDDA
     * @param codTipoMensagemDDA
     * @param maxResult
     * @return
     * @throws OperacionalException List<MensagemErroDto>
     * 
     */
    List<MensagemErroDto> obterListaTratamentoErroCIP(DateTime dataMovimento, Short codSituacaoMensagemDDA, String codTipoMensagemDDA, int maxResult) throws OperacionalException;

    /**
     * Método responsável por
     * 
     * @param dataMovimento
     * @param codTipoMensagemDDA
     * @param maxResult
     * @return
     * @throws OperacionalException List<MensagemErroDto>
     * 
     */
    List<MensagemErroDto> obterListaTratamentoRetornoErro(String codTipoMensagemDDA, DateTime dataMovimento, int maxResult) throws OperacionalException;

    /**
     * @param codTipoErroCIP
     * @param maxResult
     * @return List<MensagemErroDto>
     * @throws OperacionalException
     */
    List<MensagemErroDto> obterListaTratamentoErroAgrupado(String codTipoErroCIP, int maxResult) throws OperacionalException;

    /**
     * Método responsável por
     * 
     * @param dataMovimento
     * @param maxResult
     * @return
     * @throws OperacionalException List<MensagemErroDto>
     * 
     */
    List<MensagemErroDto> obterListaTratamentoMensagemArquivo(DateTime dataMovimento, int maxResult) throws OperacionalException;

    /**
     * @param dataMovimento
     * @param idMensagemInicial
     * @param idMensagemFinal
     * @param codTipoMensagem
     * @return
     * @throws ComumException List<Long>
     * 
     */
    List<Long> obterListaTratamentoMensagemContingenciaBatch(DateTimeDB dataMovimento, Long idMensagemInicial, Long idMensagemFinal, String codTipoMensagem) throws ComumException;

    /**
     * @param dataMovimento
     * @throws ComumException void
     * 
     */
    void atualizarDataMovimentoMensagem(Date dataMovimento) throws ComumException;

    /**
     * @param listaIdMensagem
     * @param dataMovimento
     * @throws ComumException void
     * 
     */
    void atualizarDataMovimentoMensagem(List<Long> listaIdMensagem, Date dataMovimento) throws ComumException;

    /**
     * @param listaIdMensagem
     * @param dataMovimento
     * @throws ComumException void
     * 
     */
    void reenviarSSPBMensagem(List<Long> listaIdMensagem, Date dataMovimento) throws ComumException;

    /**
     * @param listaIdMensagem
     * @param dataMovimentoProduto
     * @throws ComumException void
     * 
     */
    void reenviarCIPMensagem(List<Long> listaIdMensagem, Date dataMovimentoProduto) throws ComumException;

    /**
     * Método responsável por
     * 
     * @return
     * @throws OperacionalException List<TratamentoMesagemDto>
     * 
     */
    List<TratamentoMesagemDto> obterListaTratamentoAutomatizado() throws OperacionalException;

    /**
     * @param listaIdErroMensagem
     * @param codTipoTratamento
     * @throws ComumException void
     * 
     */
    void finalizarMensagem(List<Long> listaIdErroMensagem) throws ComumException;

    /**
     * @param listaIdMensagem
     * @param dataMovimentoProduto
     * @throws ComumException void
     * 
     */
    void reenviarMensagemFinalizadaSSPB(List<Long> listaIdMensagem, Date dataMovimentoProduto) throws ComumException;

    /**
     * @param listaIdLogDetRecebimento
     * @throws ComumException void
     * 
     */
    void reprocessarArquivoRecebido(List<Long> listaIdLogDetRecebimento) throws ComumException;

    /**
     * @param listaIdLogRecebimento
     * @throws ComumException void
     * 
     */
    void prepararReenvioArquivo(List<Long> listaIdLogRecebimento) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idMesangemDDA
     * @return
     * @throws OperacionalNoResultException MensagemDDA
     * 
     */
    MensagemDDA obterDetalheMensagemErro(Long idMesangemDDA) throws OperacionalNoResultException;

    /**
     * Método responsável por
     * 
     * @param listaIdMensagem
     * @return
     * @throws OperacionalException List<MensagemDDA>
     * 
     */
    List<MensagemDDA> obterListaMensagemErroLock(List<Long> listaIdMensagem) throws OperacionalException;

    /**
     * @param idMensagemDDA
     * @throws ComumException void
     * 
     */
    void excluirMensagem(Long idMensagemDDA) throws ComumException;

    /**
     * Método responsável por remover a mensagem do beneficiario representante de acordo com o idMensagemDDA
     * 
     * @param idMensagemDDA
     * @throws ComumException void
     * 
     */
    void removerMensagemBeneficiarioRepresentante(Long idMensagemDDA) throws ComumException;

    /**
     * Método responsável por remover a mensagem do beneficiario convenio de acordo com o idMensagemDDA
     * 
     * @param idMensagemDDA
     * @throws ComumException void
     * 
     */
    void removerMensagemBeneficiarioConvenio(Long idMensagemDDA) throws ComumException;

    /**
     * Método responsável por remover a mensagem do beneficiario de acordo com o idMensagemDDA
     * 
     * @param idMensagemDDA
     * @throws ComumException void
     * 
     */
    void removerMensagemBeneficiario(Long idMensagemDDA) throws ComumException;

    /**
     * Método responsável por remover a mensagem do Pagador agregado de acordo com o idMensagemDDA
     * 
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemPagadorAgregado(Long id) throws ComumException;

    /**
     * Método responsável por remover a mensagem do Pagador conta de acordo com o idMensagemDDA
     * 
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemPagadorConta(Long id) throws ComumException;

    /**
     * Método responsável por remover a mensagem do Pagador de acordo com o idMensagemDDA
     * 
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemPagador(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemGrupoCalculo(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemNotaFiscal(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemTextoInfo(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemBoleto(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemBaixaEfetiva(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemBaixaOperacional(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemAceite(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemTerceiroAutorizado(Long id) throws ComumException;

    /**
     * @param listaIdMensagem
     * @param numPrioridade
     * @throws ComumException void
     * 
     */
    void atualizarPrioridadeMensagem(List<Long> listaIdMensagem, Integer numPrioridade) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerMensagemConsultaBoleto(Long id) throws ComumException;

    /**
     * @param id
     * @throws ComumException void
     * 
     */
    void removerLogDetalheEnvioArquivoDDA(Long id) throws ComumException;

    /**
     * Método responsável por void
     * 
     * @param tratamento
     * 
     */
    void excluirLogErroMensagemDDA(List<Long> listaIdMensagem) throws ComumException;
}
