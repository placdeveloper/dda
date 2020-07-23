package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AbrirArquivoRetornoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;

/**
 * ArquivoRecebidoCIPProcessadorServico é responsável por
 * 
 * @author Adriano.Pinheiro
 */
public interface ArquivoRecebidoCIPProcessadorServico extends IntegracaoCipServico {

    /**
     * Mét]odo responsável por abrir o aruivo enviado pela cip descriptografando-o e descompactando-o
     * 
     * @param IdArquivoRec Id do arquivo recebido na base de dados, tabela LogRecebimentoArquivoDDA
     * @param nmArquivo o nome do arquivo que será tratado
     * @param localDeSalva o local onde o arquivo deverá ser salvo após o tratamento
     * @param tmpMaxAposUltimaAtualizacao o tempo em minutos após a última atualização do arquivo para que o mesmo seja considerado apto a processamento
     * @return
     * @throws BancoobException TipoSituacaoArquivo
     * 
     */
    TipoSituacaoArquivo abrirArquivoParaProcessamento(long idArquivoRec, String nmArquivo, String localDeSalva, int tmpMaxAposUltimaAtualizacao) throws BancoobException;

    /**
     * Método responsável por abrir um arquivo recebido da cip executando a descriptografia e a descompactação do mesmo
     * 
     * @param listaArquivos lista dos arquivos a serem abertos
     * @return AbrirArquivoRetornoDto retorna true caso o arquivo tenha sido aberto com sucesso e false caso o arquivo não tenha sido aberto
     * @throws BancoobException caso de algum erro na abertura
     * 
     */
    AbrirArquivoRetornoDto abrirArquivoCIP(List<String> listaArquivos) throws BancoobException;

    /**
     * Método responsável por executar a gravação dos detalhes de um arquivo
     * 
     * @param prArquivo o objeto com as informações do arquivo a ser processado
     * @param localDoArquivo localização do arquivo
     * @param prQuantidade a quantidade de registros a serem persistidos por cada transação
     * @return boolean true se o processamento ocorrer sem problemas e false caso o processamento não seja realizado adequadamente
     * @throws ComumException exceção gerada em caso de erro
     * 
     */
    void gravarDetalhes(ArquivoProcessamentoVO prArquivo, String localDoArquivo, int prQuantidade) throws BancoobException;

    /**
     * Método responsável por conciliar a quantidade de registros no arquivo e quantos detalhes foram gravados.
     * 
     * @param idArquivoRecebido void
     * 
     */
    void conciliarDetalhesGravados(long idArquivoRecebido) throws BancoobException;

    /**
     * Método responsável por executar a gravação dos detalhes de um arquivo considetrando a quantidade de registros por transação
     * 
     * @param tipoInstancia tipo da instancia que está sendo executada pelo STEP
     * @param idLogRecebArq identificador do arquivo recebido
     * @param idLogDetArqInicial parâmetro inicial utilzado para recuperar os dados a serem processados
     * @param idLogDetArqFinal parâmetro final utilizado para recuperar os dados a serem processados
     * @param cdMensagem o código da mensagem no arquivo recebido
     * @param prRegPorTrans quantidade de registros por transação
     * @return boolean True se ocorret tudo ok e False caso ocorra algum problema
     * @throws ComumException
     * @throws BancoobException
     * 
     */
    void processarDetalhes(TipoInstanciaProcessamentoEnum tipoInstancia, long idLogRecebArq, long idLogDetArqInicial, long idLogDetArqFinal, String cdMensagem, int prRegPorTrans)
            throws ComumException, BancoobException;

    /**
     * Método responsável por alterar a situação de processamento do arquivo após todos os registros terem sido importados.
     * 
     * @param idArquivo identificador do arquivo recebido
     * @throws ComumException
     * 
     */
    void atualizarSituacaoProcessamentoArquivoRecebido(long idArquivo, short codSituacaoProcessamentoArquivo) throws ComumException;

    /**
     * Método responsável por Salvar na tabela de mensagem campo numOperacao o nome do arquivo de Protocolo ou o erro.
     * 
     * @param nmArquivo
     * @param idArquivoEnvio
     * @param tipoArquivoENUM
     * @throws ComumException void
     * 
     */
    void atualizarMensagemProtocoloErro(String nmArquivo, long idArquivoEnvio, TipoArquivoRetornoEnum tipoArquivoENUM) throws ComumException;

}
