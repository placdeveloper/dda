/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao
 * Arquivo:         InstituicaoDao.java
 * Data Criação:    30/09/2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * {@link CapesDao} é responsável por incluir e recuperar cooperativas.
 * 
 * @author george.santos
 */
public interface CapesDao extends ComumCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por buscar o cliente pelo CPF/CNPJ na Viw do Capes.
     *
     * @param cpfCnpj the cpf cnpj
     * @param idInstituicao the id instituicao
     * @return PessoaDto
     * @throws ComumException the comum exception
     */
    PessoaDto obterPessoa(String cpfCnpj, Integer idInstituicao) throws ComumException;

    /**
     * Método responsável por buscar o cliente pelo idPessoa na Viw do Capes.
     * 
     * @param idPessoa
     * @param idInstituicao
     * @return
     * @throws ComumException PessoaDto
     */
    PessoaDto obterPessoa(Long idPessoa, Integer idInstituicao) throws ComumException;
}
