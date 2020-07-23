package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates;

import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.PagadorEletronicoDDAAPIServico;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.TipoPessoaAPIEnum;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator.SicoobDDAAPIServiceLocator;

public class PagadorEletronicoDDAAPIDelegate extends SicoobDDADelegate<PagadorEletronicoDDAAPIServico> implements PagadorEletronicoDDAAPIServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected PagadorEletronicoDDAAPIServico localizarServico() {
        return SicoobDDAAPIServiceLocator.getInstance().getPagadorEletronicoDDAAPIServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.PagadorEletronicoDDAAPIServico#isCpfCnpjPagadorEletronico(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.TipoPessoaAPIEnum,
     *      java.lang.String)
     */
    public boolean isCpfCnpjPagadorEletronico(TipoPessoaAPIEnum tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws SicoobDDAException, SicoobDDANegocioException {
        return localizarServico().isCpfCnpjPagadorEletronico(tipoPessoa, cpfCnpj, isPagadorSicoob);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

}
