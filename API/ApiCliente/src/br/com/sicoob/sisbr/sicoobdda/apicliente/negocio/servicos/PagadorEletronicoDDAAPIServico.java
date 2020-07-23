package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.TipoPessoaAPIEnum;

/**
 * PagadorEletronicoDDAAPIServico � respons�vel por fornecer os servi�os de pagador eletr�nico.
 * 
 * @author Rodrigo.Neri
 */
public interface PagadorEletronicoDDAAPIServico extends SicoobDDAServico {

    /**
     * M�todo respons�vel por verificar se o CPF/CNPJ informado � de um pagador eletr�nico.
     * 
     * @param tipoPessoa F - Pessoa F�sica; J - Pessoa Jur�dica
     * @param cpfCnpj cpf ou cnpj
     * @param isPagadorSicoob - Caso True busca os Pagadores somente do Sicoob (os que tem conta), Caso False busca todos os pagadores
     * @return <code>true<code> se for um pagador eletr�nico
     * @throws ComumException
     * @throws ComumNegocioException
     */
    boolean isCpfCnpjPagadorEletronico(TipoPessoaAPIEnum tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws SicoobDDAException, SicoobDDANegocioException;

}
