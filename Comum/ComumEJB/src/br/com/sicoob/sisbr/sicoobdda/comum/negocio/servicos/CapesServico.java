/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-atendimento-processamento-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.atendimentoprocessamento.negocio.servicos
 * Arquivo:         InstituicaoServico.java
 * Data Criação:    Oct 01, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;

/**
 * InstituicaoServico é responsável por declarar os serviços auxiliares de domínio do Cobrança Bancária Sicoob que poderão ser consumidos pelos demais Produtos
 * sistêmicos do Sicoob;.
 *
 * @author george.santos
 */
public interface CapesServico extends ComumServico {

    /**
     * Método responsável por buscar o cliente pelo CPF/CNPJ na Viw do Capes.
     *
     * @param cpfCnpj the cpf cnpj
     * @param idInstituicao the id instituicao
     * @return the pessoa dto
     * @throws ComumException PessoaDto
     */
    PessoaDto obterPessoa(String cpfCnpj, Integer idInstituicao) throws ComumException;

    /**
     * étodo responsável por buscar o cliente pelo idPessoa na Viw do Capes.
     * 
     * @param idPessoa
     * @param idInstituicao
     * @return
     * @throws ComumException PessoaDto
     */
    PessoaDto obterPessoa(Long idPessoa, Integer idInstituicao) throws ComumException;
}
