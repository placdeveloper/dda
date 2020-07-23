package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.TipoPessoaAPIEnum;

/**
 * PagadorEletronicoDDAAPIServico é responsável por fornecer os serviços de pagador eletrônico.
 * 
 * @author Rodrigo.Neri
 */
public interface PagadorEletronicoDDAAPIServico extends SicoobDDAServico {

    /**
     * Método responsável por verificar se o CPF/CNPJ informado é de um pagador eletrônico.
     * 
     * @param tipoPessoa F - Pessoa Física; J - Pessoa Jurídica
     * @param cpfCnpj cpf ou cnpj
     * @param isPagadorSicoob - Caso True busca os Pagadores somente do Sicoob (os que tem conta), Caso False busca todos os pagadores
     * @return <code>true<code> se for um pagador eletrônico
     * @throws ComumException
     * @throws ComumNegocioException
     */
    boolean isCpfCnpjPagadorEletronico(TipoPessoaAPIEnum tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws SicoobDDAException, SicoobDDANegocioException;

}
