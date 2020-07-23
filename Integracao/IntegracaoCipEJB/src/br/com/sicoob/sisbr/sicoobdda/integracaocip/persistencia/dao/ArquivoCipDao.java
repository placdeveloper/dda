package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ArquivoCipDao � respons�vel por
 * 
 * @author Francisco.Marcio
 */
public interface ArquivoCipDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por obter a contagem dos arquivos gerados para a dataAtual.
     * 
     * @return
     * @throws ComumException Long
     * 
     */
    Long obterQtdArquivosGeradosDataAtual() throws ComumException;

    /**
     * @param idArquivo - identificador do arquivo a ser alterado na tabela LOGRECEBIMENTOOARQUIVODDA
     * @param codSituacaoProcessamentoArquivo - identificador da situa��o de processamento do arquivo da CIP na tabela SITUACAOPROCESSAMENTOARQUIVO
     * @throws ComumException - ser� gerada caso o processo de altera��o n�o seja conclu�do com sucesso
     */
    void alterarSituacaoProcessamento(long idArquivo, long codSituacaoProcessamentoArquivo) throws ComumException;

    /**
     * M�todo respons�vel por obter LogEnvioArquivoDDA.
     * 
     * @param nomeArquivo String
     * @throws ComumException
     * 
     */
    LogEnvioArquivoDDA obterLogEnvioArquivoPorNome(String nomeArquivo) throws ComumException;

    /**
     * M�todo respons�vel por excluir os detalhes em um determinado range
     * 
     * @param prArquivoRecebido identificador do arquivo recebido
     * @param ordemInicial o inicio do range de exclus�o
     * @param ordemFinal o final do range de exclus�o
     * @return boolean true se ocorrer tudo certo e false se ocorrer algum problema
     * 
     */
    void excluirRegistrosDetalheRecebidos(long prArquivoRecebido, int ordemInicial, int ordemFinal) throws ComumException;

    /**
     * M�todo respons�vel por recuperar os detalhes do arquivo a ser processado.
     * 
     * @param idArquivo identificador do arquivo
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @return List<String> lista de string com os dados dados do detalhe separados por "::"
     * 
     */
    List<String> listarDetalhesArquivoRecebido(long idArquivo, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * M�todo respons�vel por atualizar o status de processamento do detalhe
     * 
     * @param prBolProcessado status true para processado, false para n�o processado
     * @return boolean true se a atualiza��o ocorrer sem problemas false se ocorrer algo de errado
     * @throws ComumException
     * 
     */
    void atualizarStatusDetalheArquivoRecebido(Long idLogDetalheArquivo) throws ComumException;

    /**
     * M�todo respons�vel por gravar um conjunto de registros em uma s� execu��o
     * 
     * @param parametros uma lista contendo o conjunto de par�metros para cada registro a ser incluido
     * @throws ComumException exe��o que ser� gerada caso ocorra problema na execu��o da grava��o
     * 
     */
    void gravaDetalheEmLote(List<Object[]> parametros) throws ComumException;

    /**
     * M�todo respons�vel por efetivar a tualiza��o das mensagens enviadas com os dados do arquivos de protocolo
     * 
     * @param prParametros a lista de par�metros referentes aos dados a serem aplicados nas mensgens
     * @return boolean true se todas as atualiza��es ocorrerem sem problemas e false se alguma atualiza��o n�o funcionar corretamente
     * 
     */
    void atualizarMensagensComProtocolo(List<Object[]> prParametros) throws ComumException;

    /**
     * M�todo respons�vel por eftivar a atualiza��o das mensagens enviadas com os dados do arquivos de erro quando for este o caso
     * 
     * @param prParametros a lista de parametros referentes aos dados a serem aplicados �s mensgens
     * @return boolean true se todas as atualiza��es ocorrerem sem problemas e false se alguma atualiza��o n�o funcionar
     * @exception ComumException
     * @see ComumException
     */
    void atualizarMensagensComErroProtocolo(List<Object[]> prParametros) throws ComumException;

    /**
     * M�todo respons�vel por marcar os detalhes do arquivo processado como PROCESSADO.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal void
     * 
     */
    void atualizarSituacaoDetalhesArquivoProcessados(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * @param idArquivoRecebido
     * @return Integer
     * 
     */
    Integer obterQtdRegistroArquivo(long idArquivoRecebido) throws ComumException;

    /**
     * @param idArquivoRecebido
     * @return Long
     * 
     */
    Long obterCountDetGravados(long idArquivoRecebido) throws ComumException;

    /**
     * M�todo respons�vel por 
     * @param idLongRecebimento
     * @throws ComumException void
     * 
     */
    void atualizarDataHoraProcLogRecebimentoArquivo(long idLongRecebimento) throws ComumException;
}
