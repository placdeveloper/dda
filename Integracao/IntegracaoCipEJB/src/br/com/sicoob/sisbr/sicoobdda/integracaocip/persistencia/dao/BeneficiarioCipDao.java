/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         BeneficiarioCipDao.java
 * Data Cria��o:    Nov 1, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.math.BigInteger;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;

/**
 * BeneficiarioCipDao � respons�vel por
 * 
 * @author Felipe.Rosa
 */
public interface BeneficiarioCipDao extends IntegracaoCipCrudDaoIF<BeneficiarioDDA> {

    /**
     * M�todo respons�vel por recuperar um beneficiario por numCpfCnpj.
     * 
     * @param numCpfCnpj
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    BeneficiarioDDA obterBeneficiario(String numCpfCnpj) throws ComumException;

    BeneficiarioDDA obterBeneficiarioDiferenteDeSemCadastro(String numCpfCnpj) throws ComumException;

    /**
     * M�todo respons�vel por um beneficiario por numCpfCnpj.
     * 
     * @param numCpfCnpj
     * @param isLocarTabela - Caso True sera feito a pesquisa locando a tabela
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    BeneficiarioDDA obterBeneficiario(String numCpfCnpj, Boolean isLocarTabela) throws ComumException;

    /**
     * M�todo respons�vel por obter BeneficiarioDDA por numCpfCnpj formatando o cpfCnpj com zeros a esquerda.
     * 
     * @param numCpfCnpj
     * @param tipoPessoaEnum
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    BeneficiarioDDA obterBeneficiario(BigInteger numCpfCnpj, TipoPessoaEnum tipoPessoaEnum) throws ComumException;

    /**
     * M�todo respons�vel por recuperar um beneficiario por numCpfCnpj.
     * 
     * @param numCpfCnpj
     * @return
     * @throws IntegracaoCipException BeneficiarioDDA
     * @throws ComumException
     * 
     */
    BeneficiarioDDA obterBeneficiario(Long numIdentBeneficiario) throws ComumException;

    /**
     * M�todo respons�vel por remover o vinculo do beneficiario com o sicoob, mantendo o registro na base pois o beneficiario possui alerta em outras IFs.
     * 
     * @param id void
     * @throws ComumException
     * 
     */
    void removerViculoSicoobBeneficiario(Long idBeneficiarioDDA) throws ComumException;

    /**
     * @param idBeneficiarioDDA
     * @throws ComumException void
     * 
     */
    void removerBeneficiarioInstituicao(Long idBeneficiarioDDA) throws ComumException;

    /**
     * @param idBeneficiarioDDA
     * @throws ComumException void
     * 
     */
    void removerIFBeneficiarioAlerta(Long idBeneficiarioDDA) throws ComumException;

    /**
     * M�todo respons�vel por obter a mensagem do benefici�rio para compor a mensagem de postagem do motor de envio de mensagens.
     * 
     * @param idMensagem
     * @return
     * @throws ComumException MensagemDDABeneficiario
     * 
     */
    MensagemDDABeneficiario obterMensagemDDABeneficiario(Long idMensagem) throws ComumException;

    /**
     * M�todo respons�vel por atualizar o numRef e numSeq da MensagemDDABeneficiario e retorna-la.
     * 
     * @param idMensagem
     * @return
     * @throws ComumException MensagemDDABeneficiario
     * 
     */
    MensagemDDABeneficiario obterMensagemDDABeneficiarioAtualizaReferencias(Long idMensagem) throws ComumException;

}
