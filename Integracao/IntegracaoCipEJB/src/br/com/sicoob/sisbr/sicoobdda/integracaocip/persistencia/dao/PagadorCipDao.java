/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         IntegracaoCipDao.java
 * Data Cria��o:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * IntegracaoCipDao
 * 
 * @author Rafael.Silva
 */
public interface PagadorCipDao extends IntegracaoCipCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por obter a mensagem do pagador para compor a mensagem de postagem do motor de envio de mensagens.
     * 
     * @param idMensagem
     * @return MensagemDDAPagador
     * 
     */
    MensagemDDAPagador obterMensagemDDAPagador(Long idMensagem);

    /**
     * M�todo respons�vel por ober o pagador de acordo com o idMensagem
     * 
     * @param idMensagem
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    PagadorDDA obterPagadorDDA(Long idMensagem) throws ComumException;

    /**
     * M�todo respons�vel por obter o pagador de acordo com o cpfCNpj
     * 
     * @param numCpfCnpjPagador
     * @param isLocarTabela - Caso True sera feito a pesquisa locando a tabela
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    PagadorDDA obterPagadorDDA(String numCpfCnpjPagador, Boolean isLocarTabela) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idPagadorDDA
     * @return
     * @throws ComumException List<PagadorDDAAgregado>
     * 
     */
    List<PagadorDDAAgregado> obterPagadorDDAAgregado(Long idPagadorDDA) throws ComumException;

    /**
     * M�todo respons�vel por
     * 
     * @param idPagadorDDA
     * @return
     * @throws ComumException List<PagadorDDAConta>
     * 
     */
    List<PagadorDDAConta> obterPagadorDDAConta(Long idPagadorDDA) throws ComumException;

    /**
     * M�todo respons�vel por buscar a lista de mensagemDDA com JOIN no LogEnvioArquivoDDA
     * 
     * @param id
     * @return List<Long>
     * 
     */
    List<MensagemDDAPagador> listarMensagemDDAPagadorLogEnvioArquivo(Long idLogEnvioArquivoDDA);

    /**
     * M�todo respons�vel por atualizar o numRef e numSeq da MensagemDDAPagador e retorna-la.
     * 
     * @param idMensagem void
     * 
     */
    MensagemDDAPagador obterMensagemDDAPagadorAtualizaReferencias(Long idMensagem) throws ComumException;

    /**
     * @param idPagadorDDA
     * @throws ComumException void
     * 
     */
    void removerAgregado(Long idPagadorDDA) throws ComumException;

    /**
     * @param idPagadorDDA
     * @throws ComumException void
     * 
     */
    void removerConta(Long idPagadorDDA) throws ComumException;

    /**
     * M�todo respons�vel por gravar os dados do XML ADDA002 na estrutura de PagadorDDA.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetFinal void
     * 
     */
    void gravarPagadorDDAXmlADDA002(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * M�todo respons�vel por realizar as modifica��es nos Clientes Pagador Eletr�nico conforme arquivo ADDA003
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @return
     * @throws ComumException
     * 
     */
    void executarManutencaoPagadorEletronico(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException;

    /**
     * M�todo respons�vel por listar os Clientes Pagador Eletr�nico modificados pelo arquivo ADDA003
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetArqFinal
     * @return List<Object[]>
     * @throws ComumException
     * 
     */
    List<Object[]> listarPagadorEletronicoModificados(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException;

}
