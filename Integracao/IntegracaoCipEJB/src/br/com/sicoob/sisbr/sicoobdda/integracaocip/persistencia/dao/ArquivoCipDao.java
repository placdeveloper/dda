package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * ArquivoCipDao é responsável por
 * 
 * @author Francisco.Marcio
 */
public interface ArquivoCipDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por obter a contagem dos arquivos gerados para a dataAtual.
     * 
     * @return
     * @throws ComumException Long
     * 
     */
    Long obterQtdArquivosGeradosDataAtual() throws ComumException;

    /**
     * @param idArquivo - identificador do arquivo a ser alterado na tabela LOGRECEBIMENTOOARQUIVODDA
     * @param codSituacaoProcessamentoArquivo - identificador da situação de processamento do arquivo da CIP na tabela SITUACAOPROCESSAMENTOARQUIVO
     * @throws ComumException - será gerada caso o processo de alteração não seja concluído com sucesso
     */
    void alterarSituacaoProcessamento(long idArquivo, long codSituacaoProcessamentoArquivo) throws ComumException;

    /**
     * Método responsável por obter LogEnvioArquivoDDA.
     * 
     * @param nomeArquivo String
     * @throws ComumException
     * 
     */
    LogEnvioArquivoDDA obterLogEnvioArquivoPorNome(String nomeArquivo) throws ComumException;

    /**
     * Método responsável por excluir os detalhes em um determinado range
     * 
     * @param prArquivoRecebido identificador do arquivo recebido
     * @param ordemInicial o inicio do range de exclusão
     * @param ordemFinal o final do range de exclusão
     * @return boolean true se ocorrer tudo certo e false se ocorrer algum problema
     * 
     */
    void excluirRegistrosDetalheRecebidos(long prArquivoRecebido, int ordemInicial, int ordemFinal) throws ComumException;

    /**
     * Método responsável por recuperar os detalhes do arquivo a ser processado.
     * 
     * @param idArquivo identificador do arquivo
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @return List<String> lista de string com os dados dados do detalhe separados por "::"
     * 
     */
    List<String> listarDetalhesArquivoRecebido(long idArquivo, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * Método responsável por atualizar o status de processamento do detalhe
     * 
     * @param prBolProcessado status true para processado, false para não processado
     * @return boolean true se a atualização ocorrer sem problemas false se ocorrer algo de errado
     * @throws ComumException
     * 
     */
    void atualizarStatusDetalheArquivoRecebido(Long idLogDetalheArquivo) throws ComumException;

    /**
     * Método responsável por gravar um conjunto de registros em uma só execução
     * 
     * @param parametros uma lista contendo o conjunto de parâmetros para cada registro a ser incluido
     * @throws ComumException exeção que será gerada caso ocorra problema na execução da gravação
     * 
     */
    void gravaDetalheEmLote(List<Object[]> parametros) throws ComumException;

    /**
     * Método responsável por efetivar a tualização das mensagens enviadas com os dados do arquivos de protocolo
     * 
     * @param prParametros a lista de parâmetros referentes aos dados a serem aplicados nas mensgens
     * @return boolean true se todas as atualizações ocorrerem sem problemas e false se alguma atualização não funcionar corretamente
     * 
     */
    void atualizarMensagensComProtocolo(List<Object[]> prParametros) throws ComumException;

    /**
     * Método responsável por eftivar a atualização das mensagens enviadas com os dados do arquivos de erro quando for este o caso
     * 
     * @param prParametros a lista de parametros referentes aos dados a serem aplicados às mensgens
     * @return boolean true se todas as atualizações ocorrerem sem problemas e false se alguma atualização não funcionar
     * @exception ComumException
     * @see ComumException
     */
    void atualizarMensagensComErroProtocolo(List<Object[]> prParametros) throws ComumException;

    /**
     * Método responsável por marcar os detalhes do arquivo processado como PROCESSADO.
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
     * Método responsável por 
     * @param idLongRecebimento
     * @throws ComumException void
     * 
     */
    void atualizarDataHoraProcLogRecebimentoArquivo(long idLongRecebimento) throws ComumException;
}
