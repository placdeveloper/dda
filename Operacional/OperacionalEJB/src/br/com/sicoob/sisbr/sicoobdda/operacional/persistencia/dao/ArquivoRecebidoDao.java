/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao
 * Arquivo:         ArquivoRecebidoDao.java
 * Data Criação:    Jan 27, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;

/**
 * ArquivoRecebidoDao
 * 
 * @author Samuell.Ramos
 */
public interface ArquivoRecebidoDao extends OperacionalCrudDaoIF<LogRecebimentoArquivoDDA> {

    /**
     * @return
     * @throws ComumException List<SituacaoProcessamentoArquivo>
     * 
     */
    List<SituacaoProcessamentoArquivo> listarSitucaoArquivo() throws ComumException;

    /**
     * @return
     * @throws ComumException List<TipoArquivo>
     * 
     */
    List<TipoArquivo> listarTipoArquivo() throws ComumException;

    /**
     * @return List<TipoMensagemDDA>
     * @throws OperacionalException List<TipoMensagemDDA>
     */
    List<TipoMensagemDDA> listarTipoMensagemAgenAdda() throws OperacionalException;

    /**
     * @param idLogRecebimentoArquivoDDA
     * @return
     * @throws ComumException ArquivoRecebidoDto
     * 
     */
    ArquivoRecebidoDto obterArquivoRecebido(Long idLogRecebimentoArquivoDDA) throws ComumException;

    /**
     * @param idLogRecebimentoArquivoDDA
     * @param codSituacao void
     * 
     */
    void alterarSituacaoArquivo(long idLogRecebimentoArquivoDDA, short codSituacao) throws ComumException;

    /**
     * Método responsável por setar a quantidade de registros recebidos no arquivo.
     * 
     * @param idArquivoRecebido
     * @param qtdTotalRegistro
     * @throws ComumException void
     * 
     */
    void atualizarQtdeTotalRegistrosArquivoRecebido(long idArquivoRecebido, int qtdTotalRegistro) throws ComumException;

    /**
     * Método responsável por obter os paramêtros necessários para processar o arquivo.
     * 
     * @param idLogRecebimentoArquivoDDA
     * @return
     * @throws ComumException ArquivoParaProc
     * 
     */
    ArquivoProcessamentoVO obterDadosProcessamentoArquivo(Long idLogRecebimentoArquivoDDA) throws ComumException;

    /**
     * Método responsável por 
     * @param logDetRecebimentoArquivoDDA
     * @throws ComumException void
     * 
     */
    void alterarSituacaoRegistro(LogDetRecebimentoArquivoDDA logDetRecebimentoArquivoDDA) throws ComumException;
}
