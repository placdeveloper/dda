/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-api-implementacao
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.apiimplementacao.negocio.servicos.ejb
 * Arquivo:         PagadorServicoEJB.java
 * Data Criação:    May 9, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.api.implementacao.negocio.servicos.ejb;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDAException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.excecao.SicoobDDANegocioException;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.AlteraSituacaoBeneficiarioAPIDto;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.enums.RetornoAlterarSituacaoBeneficiarioAPIEnum;
import br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.interfaces.BeneficiarioAPIServicoRemote;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlteraSituacaoBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RetornoAlterarSituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarCadastroBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarSituacaoBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CadastrarBeneficiarioServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.OperacaoBeneficiarioDDAServico;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.locator.OperacionalServiceLocator;
import br.com.sicoob.tipos.DateTime;

/**
 * PagadorServicoEJB é responsável por
 * 
 * @author Daniel.Basso
 */
@Stateless
@Remote(BeneficiarioAPIServicoRemote.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BeneficiarioAPIServicoEJB extends SicoobDDAServicoEJB implements BeneficiarioAPIServicoRemote {

    /**
     * Método responsável por recuperar o servico de cadastro de beneficiário
     * 
     * @return
     */
    protected CadastrarBeneficiarioServico getCadastrarBeneficiarioServico() {
        return OperacionalServiceLocator.getInstance().localizarCadastrarBeneficiarioServico();
    }

    /**
     * Método responsável por recuperar o serviço de alteração do beneficiário
     * 
     * @return
     */
    protected AlterarCadastroBeneficiarioServico getAlterarCadastroBeneficiarioServico() {
        return OperacionalServiceLocator.getInstance().localizarAlterarCadastroBeneficiarioServico();
    }

    /**
     * Método responsável por recuperar os serviço de altearação da situação do beneficiário
     * 
     * @return
     */
    protected AlterarSituacaoBeneficiarioServico getAlterarSituacaoBeneficiarioServico() {
        return OperacionalServiceLocator.getInstance().localizarAlterarSituacaoBeneficiarioServico();
    }

    /**
     * Método responsável por recuperar o serviço para verificar a situação do beneficiário na CIP.
     * 
     * @return
     */
    protected OperacaoBeneficiarioDDAServico getOperacaoBeneficiarioDDAServico() {
        return OperacionalServiceLocator.getInstance().localizarOperacaoBeneficiarioDDASevico();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    @Override
    public void verificarDisponibilidade() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#processarCadastroBeneficiario(java.lang.Long, java.lang.String, java.lang.Integer,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short)
     */
    public void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTime dataAtualMovimento, Short idCanal) throws SicoobDDAException,
            SicoobDDANegocioException {
        try {
            getCadastrarBeneficiarioServico().processarCadastroBeneficiario(numCliente, numCpfCnpj, numCooperativa, (DateTimeDB)dataAtualMovimento, idCanal);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#processarCadastroBeneficiario(java.lang.Long, java.lang.String, java.lang.Integer,
     *      br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTime dataAtualMovimento) throws SicoobDDAException,
            SicoobDDANegocioException {
        try {
            getCadastrarBeneficiarioServico().processarCadastroBeneficiario(numCliente, numCpfCnpj, numCooperativa, (DateTimeDB)dataAtualMovimento, null);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#alterarCadastroBeneficiario(java.lang.Long, java.lang.Integer, java.lang.Long,
     *      java.util.Date)
     */
    public void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTime dataAtualMovimento, Short idCanal)
            throws SicoobDDAException, SicoobDDANegocioException {
        try {
            getAlterarCadastroBeneficiarioServico().alterarCadastroBeneficiario(numCliente, idInstituicao, codSituacaoCedente, dataCancelamento, (DateTimeDB)dataAtualMovimento, idCanal);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#alterarCadastroBeneficiario(java.lang.Long, java.lang.Integer, java.lang.Long,
     *      java.util.Date, br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTime dataAtualMovimento)
            throws SicoobDDAException, SicoobDDANegocioException {
        try {
            getAlterarCadastroBeneficiarioServico().alterarCadastroBeneficiario(numCliente, idInstituicao, codSituacaoCedente, dataCancelamento, (DateTimeDB)dataAtualMovimento, null);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#excluirConvenioBeneficiario(java.lang.Long, java.lang.Integer)
     */
    public void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTime dataAtualMovimento, Short idCanal) throws SicoobDDAException,
            SicoobDDANegocioException {
        try {
        	
            getAlterarCadastroBeneficiarioServico().excluirConvenioBeneficiario(numCliente, numCooperativa, (DateTimeDB)dataAtualMovimento, idCanal);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#excluirConvenioBeneficiario(java.lang.Long, java.lang.Integer,
     *      br.com.bancoob.persistencia.types.DateTimeDB)
     */
    public void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTime dataAtualMovimento) throws SicoobDDAException, SicoobDDANegocioException {
        try {
            getAlterarCadastroBeneficiarioServico().excluirConvenioBeneficiario(numCliente, numCooperativa, (DateTimeDB)dataAtualMovimento, null);
        } catch (ComumNegocioException e) {
            throw new SicoobDDANegocioException(e.getMessage());
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.cobrancabancaria.api.cliente.negocio.servicos.AlterarSituacaoBeneficiarioAPIServico#processarAlterarSituacaoBeneficiario(br.com.sicoob.sisbr.cobrancabancaria.api.cliente.negocio.servicos.dto.AlterarSituacaoBeneficiarioAPIDto)
     */
    public RetornoAlterarSituacaoBeneficiarioAPIEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioAPIDto alteraSitBeneficiarioAPIDto, Short idCanal)
            throws SicoobDDAException {
        RetornoAlterarSituacaoBeneficiarioAPIEnum retornoAPIEnum = null;
        try {
            AlteraSituacaoBeneficiarioDto alteraSituacaoDto = this.deAlteraSituacaoBeneficiarioAPIDtoParaDto(alteraSitBeneficiarioAPIDto);
            RetornoAlterarSituacaoBeneficiarioEnum retornoEnum = getAlterarSituacaoBeneficiarioServico().processarAlterarSituacaoBeneficiario(alteraSituacaoDto, idCanal);
            retornoAPIEnum = RetornoAlterarSituacaoBeneficiarioAPIEnum.getBy(retornoEnum.getCodDescricao());
        } catch (BancoobException e) {
            getLogger().erro(e, "api.cliente.erro.altera.situacao.beneficiario");
            throw new SicoobDDAException("api.cliente.erro.altera.situacao.beneficiario");
        }
        return retornoAPIEnum;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#processarAlterarSituacaoBeneficiario(br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.dto.AlteraSituacaoBeneficiarioAPIDto)
     */
    public RetornoAlterarSituacaoBeneficiarioAPIEnum processarAlterarSituacaoBeneficiario(AlteraSituacaoBeneficiarioAPIDto alteraSitBeneficiarioAPIDto) throws SicoobDDAException {
        RetornoAlterarSituacaoBeneficiarioAPIEnum retornoAPIEnum = null;
        try {
            AlteraSituacaoBeneficiarioDto alteraSituacaoDto = this.deAlteraSituacaoBeneficiarioAPIDtoParaDto(alteraSitBeneficiarioAPIDto);
            RetornoAlterarSituacaoBeneficiarioEnum retornoEnum = getAlterarSituacaoBeneficiarioServico().processarAlterarSituacaoBeneficiario(alteraSituacaoDto, null);
            retornoAPIEnum = RetornoAlterarSituacaoBeneficiarioAPIEnum.getBy(retornoEnum.getCodDescricao());
        } catch (BancoobException e) {
            getLogger().erro(e, "api.cliente.erro.altera.situacao.beneficiario");
            throw new SicoobDDAException("api.cliente.erro.altera.situacao.beneficiario");
        }
        return retornoAPIEnum;
    }

    /**
     * Método responsável por
     * 
     * @param sourceDto
     * @return AlteraSituacaoBeneficiarioDto
     * 
     */
    private AlteraSituacaoBeneficiarioDto deAlteraSituacaoBeneficiarioAPIDtoParaDto(AlteraSituacaoBeneficiarioAPIDto sourceDto) {
        AlteraSituacaoBeneficiarioDto targetDto = new AlteraSituacaoBeneficiarioDto();
        ObjectUtil.copy(sourceDto, targetDto, AlteraSituacaoBeneficiarioAPIDto.CAMPOS);

        if (sourceDto.getTipoPessoaBeneficiario() != null) {
            targetDto.setTipoPessoaBeneficiario(TipoPessoaEnum.getBy(sourceDto.getTipoPessoaBeneficiario().getCodDominio()));
        }
        if (sourceDto.getSituacaoBeneficiario() != null) {
            targetDto.setSituacaoBeneficiario(SituacaoBeneficiarioEnum.getBy(sourceDto.getSituacaoBeneficiario().getCodDominio()));
        }

        return targetDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#verificarSituacaoBeneficiario(java.lang.String)
     */
    public void validaSituacaoBeneficiarioCIP(String numCpfCnpj) throws SicoobDDAException, SicoobDDANegocioException {
        debug("********* Validando a situação do beneficiário *********");
        debug("Parâmetro - numCpfCnpj: " + numCpfCnpj);
        try {
            getOperacaoBeneficiarioDDAServico().validaSituacaoBeneficiarioCIP(numCpfCnpj);
        } catch (ComumException e) {
            getLogger().erro(e, e.getMessage());
            throw new SicoobDDAException(e.getMessage());
        } catch (ComumNegocioException e) {
            getLogger().erro(e, e.getMessage());
            throw new SicoobDDANegocioException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @throws SicoobDDAException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.apicliente.negocio.servicos.BeneficiarioAPIServico#beneficiarioEstaNaCip(java.lang.String)
     */
    public Boolean beneficiarioEstaNaCip(String numCpfCnpj) throws SicoobDDAException {
        try {
            return getOperacaoBeneficiarioDDAServico().beneficiarioEstaNaCip(numCpfCnpj);
        } catch (ComumException e) {
            throw new SicoobDDAException(e.getMessage());
        }
    }

}
