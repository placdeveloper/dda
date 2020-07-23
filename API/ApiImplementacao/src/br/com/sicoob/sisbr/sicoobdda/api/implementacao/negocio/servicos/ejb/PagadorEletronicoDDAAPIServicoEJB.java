package br.com.sicoob.sisbr.sicoobdda.api.implementacao.negocio.servicos.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.TipoPessoaAPIEnum;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.interfaces.PagadorEletronicoDDAAPIServicoRemote;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;

/**
 * PagadorEletronicoDDAAPIServicoEJB é responsável por prover os serviços de pagador eletrônico.
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Remote(PagadorEletronicoDDAAPIServicoRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PagadorEletronicoDDAAPIServicoEJB extends SicoobDDAServicoEJB implements PagadorEletronicoDDAAPIServicoRemote {

    /**
     * Método responsável por recuperar o serviço
     * 
     * @return
     */
    protected PagadorEletronicoDDAServico getPagadorEletronicoDDAServico() {
        return OperacionalServiceLocator.getInstance().localizarPagadorEletronicoDDAServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.PagadorEletronicoDDAAPIServico#isCpfCnpjPagadorEletronico(java.lang.Character,
     *      java.lang.String)
     */
    public boolean isCpfCnpjPagadorEletronico(TipoPessoaAPIEnum tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws SicoobDDAException, SicoobDDANegocioException {
        try {
            Character tipoPessoaChar = ObjectUtil.isNull(tipoPessoa) ? null : tipoPessoa.getCodDominio().charAt(0);

            return getPagadorEletronicoDDAServico().isCpfCnpjPagadorEletronico(tipoPessoaChar, cpfCnpj, isPagadorSicoob);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {
    }

}