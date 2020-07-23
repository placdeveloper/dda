/**
 * Projeto:         Sicoob DDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates
 * Arquivo:         ArquivoRecebidoDelegate.java
 * Data Criação:    Jan 30, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * ArquivoRecebidoDelegate
 * 
 * @author samuell.ramos
 */
/**
 * ArquivoRecebidoDelegate é responsável por
 * 
 * @author Felipe.Rosa
 */
@SuppressWarnings("rawtypes")
public class ArquivoRecebidoDelegate extends OperacionalCrudDelegate {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected ArquivoRecebidoServico localizarServico() {
        return OperacionalServiceLocator.getInstance().localizarArquivoRecebidoServico();
    }

    /**
     * @return
     * @throws ComumException ArquivoRecebidoDto
     * 
     */
    public ArquivoRecebidoDto carregarListasArquivoRecebido() throws ComumException {
        return localizarServico().carregarListasArquivoRecebido();
    }

    /**
     * @param arquivoRecebido
     * @return LogRecebimentoArquivoDDA
     * @throws ComumException LogRecebimentoArquivoDDA
     */
    public ArquivoRecebidoDto obterArquivoRecebido(Long idLogRecebimentoArquivoDDA) throws ComumException {
        return localizarServico().obterArquivoRecebido(idLogRecebimentoArquivoDDA);

    }

    /**
     * @return LogDetRecebimentoArquivoDDA
     * @throws ComumException ArquivoRecebidoDto
     */
    public LogDetRecebimentoArquivoDDA obterDetLogRecebimentoArquivoDDA(Long idLogRecebimentoArquivoDDA) throws ComumException {
        return localizarServico().obterLogDetRecebimentoArquivoDDA(idLogRecebimentoArquivoDDA);
    }

    /**
     * @param idLogRecebimentoArquivoDDA
     * @param codSituacao
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    public void alterarSituacaoArquivoRecebido(long idLogRecebimentoArquivoDDA, short codSituacao) throws ComumException, ComumNegocioException {
        localizarServico().alterarSituacaoArquivoRecebido(idLogRecebimentoArquivoDDA, codSituacao);
    }
    
    /**
     * Método responsável por 
     * @param logDetRecebimentoArquivoDDA
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    public void alterarSituacaoRegistro(LogDetRecebimentoArquivoDDA logDetRecebimentoArquivoDDA) throws ComumException, ComumNegocioException {
        localizarServico().alterarSituacaoRegistro(logDetRecebimentoArquivoDDA);
    }

    /**
     * @param arquivoDto
     * @return
     * @throws BancoobException ArquivoRecebidoDto
     * 
     */
    public ArquivoRecebidoDto descriptografarArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException {
        return localizarServico().descriptografarArquivo(arquivoDto);
    }

    /**
     * @param arquivoDto
     * @return
     * @throws BancoobException ArquivoRecebidoDto
     * 
     */
    public ArquivoRecebidoDto gravarDetalheArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException {
        return localizarServico().gravarDetalheArquivo(arquivoDto);
    }

    /**
     * @param arquivoDto
     * @return
     * @throws BancoobException ArquivoRecebidoDto
     * 
     */
    public ArquivoRecebidoDto processarArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException {
        return localizarServico().processarArquivo(arquivoDto);
    }
}
