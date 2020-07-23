/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos
 * Arquivo:         ArquivoRecebidoServico.java
 * Data Criação:    Jan 27, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto;

/**
 * ArquivoRecebidoServico
 * 
 * @author Samuell.Ramos
 */
public interface ArquivoRecebidoServico extends OperacionalCrudServico<LogRecebimentoArquivoDDA> {

    /**
     * @param idLogRecebimentoArquivoDDA
     * @param codSituacao
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    void alterarSituacaoArquivoRecebido(long idLogRecebimentoArquivoDDA, short codSituacao) throws ComumNegocioException, ComumException;

    /**
     * @param idLogRecebimentoArquivoDDA
     * @return ArquivoRecebidoDto
     * @throws ComumException ArquivoRecebidoDto
     * 
     */
    ArquivoRecebidoDto carregarListasArquivoRecebido() throws ComumException;

    /**
     * @return LogDetRecebimentoArquivoDDA
     * @throws ComumException ArquivoRecebidoDto
     */
    LogDetRecebimentoArquivoDDA obterLogDetRecebimentoArquivoDDA(Long idLogRecebimentoArquivoDDA) throws ComumException;

    /**
     * @param arquivoRecebido
     * @return LogRecebimentoArquivoDDA
     * @throws ComumException LogRecebimentoArquivoDDA
     */
    ArquivoRecebidoDto obterArquivoRecebido(Long idLogRecebimentoArquivoDDA) throws ComumException;

    /**
     * @param arquivoDto
     * @throws BancoobException void
     * 
     */
    ArquivoRecebidoDto descriptografarArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param arquivoDto
     * @return
     * @throws BancoobException ArquivoRecebidoDto
     * 
     */
    ArquivoRecebidoDto gravarDetalheArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException;

    /**
     * Método responsável por
     * 
     * @param arquivoDto
     * @return
     * @throws BancoobException ArquivoRecebidoDto
     * 
     */
    ArquivoRecebidoDto processarArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException;
    
    /**
     * Método responsável por 
     * @param logDetRecebimentoArquivoDDA
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    void alterarSituacaoRegistro(LogDetRecebimentoArquivoDDA logDetRecebimentoArquivoDDA) throws ComumNegocioException, ComumException;
}
