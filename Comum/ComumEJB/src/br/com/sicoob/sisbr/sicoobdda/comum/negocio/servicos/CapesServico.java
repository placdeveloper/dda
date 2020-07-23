/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-atendimento-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.atendimentoprocessamento.negocio.servicos
 * Arquivo:         InstituicaoServico.java
 * Data Cria��o:    Oct 01, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;

/**
 * InstituicaoServico � respons�vel por declarar os servi�os auxiliares de dom�nio do Cobran�a Banc�ria Sicoob que poder�o ser consumidos pelos demais Produtos
 * sist�micos do Sicoob;.
 *
 * @author george.santos
 */
public interface CapesServico extends ComumServico {

    /**
     * M�todo respons�vel por buscar o cliente pelo CPF/CNPJ na Viw do Capes.
     *
     * @param cpfCnpj the cpf cnpj
     * @param idInstituicao the id instituicao
     * @return the pessoa dto
     * @throws ComumException PessoaDto
     */
    PessoaDto obterPessoa(String cpfCnpj, Integer idInstituicao) throws ComumException;

    /**
     * �todo respons�vel por buscar o cliente pelo idPessoa na Viw do Capes.
     * 
     * @param idPessoa
     * @param idInstituicao
     * @return
     * @throws ComumException PessoaDto
     */
    PessoaDto obterPessoa(Long idPessoa, Integer idInstituicao) throws ComumException;
}
