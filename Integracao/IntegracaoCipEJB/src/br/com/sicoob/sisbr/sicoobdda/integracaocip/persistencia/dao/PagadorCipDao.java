/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         IntegracaoCipDao.java
 * Data Criação:    May 25, 2016
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
     * Método responsável por obter a mensagem do pagador para compor a mensagem de postagem do motor de envio de mensagens.
     * 
     * @param idMensagem
     * @return MensagemDDAPagador
     * 
     */
    MensagemDDAPagador obterMensagemDDAPagador(Long idMensagem);

    /**
     * Método responsável por ober o pagador de acordo com o idMensagem
     * 
     * @param idMensagem
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    PagadorDDA obterPagadorDDA(Long idMensagem) throws ComumException;

    /**
     * Método responsável por obter o pagador de acordo com o cpfCNpj
     * 
     * @param numCpfCnpjPagador
     * @param isLocarTabela - Caso True sera feito a pesquisa locando a tabela
     * @return
     * @throws ComumException PagadorDDA
     * 
     */
    PagadorDDA obterPagadorDDA(String numCpfCnpjPagador, Boolean isLocarTabela) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idPagadorDDA
     * @return
     * @throws ComumException List<PagadorDDAAgregado>
     * 
     */
    List<PagadorDDAAgregado> obterPagadorDDAAgregado(Long idPagadorDDA) throws ComumException;

    /**
     * Método responsável por
     * 
     * @param idPagadorDDA
     * @return
     * @throws ComumException List<PagadorDDAConta>
     * 
     */
    List<PagadorDDAConta> obterPagadorDDAConta(Long idPagadorDDA) throws ComumException;

    /**
     * Método responsável por buscar a lista de mensagemDDA com JOIN no LogEnvioArquivoDDA
     * 
     * @param id
     * @return List<Long>
     * 
     */
    List<MensagemDDAPagador> listarMensagemDDAPagadorLogEnvioArquivo(Long idLogEnvioArquivoDDA);

    /**
     * Método responsável por atualizar o numRef e numSeq da MensagemDDAPagador e retorna-la.
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
     * Método responsável por gravar os dados do XML ADDA002 na estrutura de PagadorDDA.
     * 
     * @param idLogArquivoRecebido
     * @param idLogDetArqInicial
     * @param idLogDetFinal void
     * 
     */
    void gravarPagadorDDAXmlADDA002(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal);

    /**
     * Método responsável por realizar as modificações nos Clientes Pagador Eletrônico conforme arquivo ADDA003
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
     * Método responsável por listar os Clientes Pagador Eletrônico modificados pelo arquivo ADDA003
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
