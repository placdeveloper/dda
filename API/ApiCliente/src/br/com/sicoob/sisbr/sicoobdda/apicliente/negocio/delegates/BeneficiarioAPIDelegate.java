/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-cliente
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates
 * Arquivo:         SicoobDDAApiDelegate.java
 * Data Criação:    May 9, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.delegates;

import java.util.Date;

import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.AlteraSituacaoBeneficiarioAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.RetornoAlterarSituacaoBeneficiarioAPIEnum;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.locator.SicoobDDAAPIServiceLocator;
import br.com.sicoob.tipos.DateTime;

/**
 * PagadorAPIDelegate é responsável por disponibilizar os serviços de Manutenção de Pagador junto a CIP.
 * 
 * @author Daniel.Basso
 */
public class BeneficiarioAPIDelegate extends SicoobDDADelegate<BeneficiarioAPIServico> implements BeneficiarioAPIServico {

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.delegates.BancoobDelegate#localizarServico()
     */
    @Override
    protected BeneficiarioAPIServico localizarServico() {
        return SicoobDDAAPIServiceLocator.getInstance().getBeneficiarioAPIServico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /**
     * {@inheritDoc}
     * 
     * @param dataAtualMovimento
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#processarCadastroBeneficiario(java.lang.Long, java.lang.String,
     *      java.lang.Integer)
     */
    public void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTime dataAtualMovimento, Short idCanal) throws SicoobDDAException, SicoobDDANegocioException {
        localizarServico().processarCadastroBeneficiario(numCliente, numCpfCnpj, numCooperativa, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#processarCadastroBeneficiario(java.lang.Long, java.lang.String,
     *      java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTime dataAtualMovimento) throws SicoobDDAException, SicoobDDANegocioException {
        localizarServico().processarCadastroBeneficiario(numCliente, numCpfCnpj, numCooperativa, dataAtualMovimento);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#alterarCadastroBeneficiario(java.lang.Long, java.lang.Integer,
     *      java.lang.Long, java.util.Date)
     */
    public void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTime dataAtualMovimento, Short idCanal) throws SicoobDDAException,
            SicoobDDANegocioException {
        localizarServico().alterarCadastroBeneficiario(numCliente, idInstituicao, codSituacaoCedente, dataCancelamento, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#alterarCadastroBeneficiario(java.lang.Long, java.lang.Integer,
     *      java.lang.Long, java.util.Date, br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTime dataAtualMovimento) throws SicoobDDAException,
            SicoobDDANegocioException {
        localizarServico().alterarCadastroBeneficiario(numCliente, idInstituicao, codSituacaoCedente, dataCancelamento, dataAtualMovimento);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#excluirConvenioBeneficiario(java.lang.Long, java.lang.Integer)
     */
    public void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTime dataAtualMovimento, Short idCanal) throws SicoobDDAException, SicoobDDANegocioException {
        localizarServico().excluirConvenioBeneficiario(numCliente, numCooperativa, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#excluirConvenioBeneficiario(java.lang.Long, java.lang.Integer,
     *      br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTime dataAtualMovimento) throws SicoobDDAException, SicoobDDANegocioException {
        localizarServico().excluirConvenioBeneficiario(numCliente, numCooperativa, dataAtualMovimento);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.api.cliente.negocio.servicos.BeneficiarioAPIServico#processarAlterarSituacaoBeneficiario(br.com.sicoob.sisbr.cobrancabancaria.api.cliente.negocio.servicos.dto.AlteraSituacaoBeneficiarioAPIDto)
     */
    public RetornoAlterarSituacaoBeneficiarioAPIEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioAPIDto alteraSitBeneficiarioDto, Short idCanal) throws SicoobDDAException {
        return localizarServico().processarAlterarSituacaoBeneficiario(alteraSitBeneficiarioDto, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#processarAlterarSituacaoBeneficiario(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.AlteraSituacaoBeneficiarioAPIDto)
     */
    public RetornoAlterarSituacaoBeneficiarioAPIEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioAPIDto alteraSitBeneficiarioDto) throws SicoobDDAException {
        return localizarServico().processarAlterarSituacaoBeneficiario(alteraSitBeneficiarioDto);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#verificarSituacaoBeneficiario(java.lang.Long, java.lang.Integer)
     */
    public void validaSituacaoBeneficiarioCIP(String numCpfCnpj) throws SicoobDDAException, SicoobDDANegocioException {
        localizarServico().validaSituacaoBeneficiarioCIP(numCpfCnpj);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#beneficiarioEstaNaCip(java.lang.String)
     */
    public Boolean beneficiarioEstaNaCip(String numCpfCnpj) throws SicoobDDAException {
        return localizarServico().beneficiarioEstaNaCip(numCpfCnpj);
    }

}
