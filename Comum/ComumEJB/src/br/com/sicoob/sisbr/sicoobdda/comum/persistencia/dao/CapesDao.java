/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         InstituicaoDao.java
 * Data Cria��o:    30/09/2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * {@link CapesDao} � respons�vel por incluir e recuperar cooperativas.
 * 
 * @author george.santos
 */
public interface CapesDao extends ComumCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por buscar o cliente pelo CPF/CNPJ na Viw do Capes.
     *
     * @param cpfCnpj the cpf cnpj
     * @param idInstituicao the id instituicao
     * @return PessoaDto
     * @throws ComumException the comum exception
     */
    PessoaDto obterPessoa(String cpfCnpj, Integer idInstituicao) throws ComumException;

    /**
     * M�todo respons�vel por buscar o cliente pelo idPessoa na Viw do Capes.
     * 
     * @param idPessoa
     * @param idInstituicao
     * @return
     * @throws ComumException PessoaDto
     */
    PessoaDto obterPessoa(Long idPessoa, Integer idInstituicao) throws ComumException;
}
