/**
 * Projeto:         CobranÃ§a BancÃ¡ria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data CriaÃ§Ã£o:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.bancoob.validacao.ValidacaoCnpj;
import br.com.bancoob.validacao.ValidacaoCpf;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ExcluirRelacionamentoBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.ExcluirRelacionamentoBeneficiarioServicoLocal;

/**
 * CadastrarBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ ExcluirRelacionamentoBeneficiarioServicoLocal.class })
public class ExcluirRelacionamentoBeneficiarioServicoEJB extends OperacionalServicoEJB implements ExcluirRelacionamentoBeneficiarioServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoCipDao dao;

    private MensagemDDADelegate mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return the dao
     */
    public ArquivoCipDao getDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ExcluirRelacionamentoBeneficiarioServico#excluirRelacionamentoBeneficiario(java.lang.String,
     *      java.lang.Long, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short)
     */
    public void excluirRelacionamentoBeneficiario(String numCpfCnpj, Long numIdentBeneficiario, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException,
            ComumNegocioException {
        validarBeneficiarioCadastrado(numIdentBeneficiario);
        excluirRelacionamentoBeneficiario(numCpfCnpj, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ExcluirRelacionamentoBeneficiarioServico#excluirRelacionamentoBeneficiario(java.lang.String)
     */
    public void excluirRelacionamentoBeneficiario(String numCpfCnpj, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumNegocioException, ComumException {
        getLogger().debug("###### inicio metodo excluirRelacionamentoBeneficiario ######");
        validarMensagemDDAEnvioPendente(numCpfCnpj);
        ExcluirRelacionamentoBeneficiarioDto excluirRelBeneficiarioDto = new ExcluirRelacionamentoBeneficiarioDto(numCpfCnpj);

        validaDadosBeneficiario(excluirRelBeneficiarioDto);

        criarMensagemDDABeneficiario(excluirRelBeneficiarioDto, dataAtualMovimento, idCanal);

        getLogger().debug("###### fim metodo excluirRelacionamentoBeneficiario ######");
    }

    /**
     * Método responsável por Setar e Salvar o objeto MensagemDDABeneficiario
     * 
     * @param cadastroBeneficiarioDto
     * @param dataAtualMovimento
     * @return MensagemDDABeneficiario
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void criarMensagemDDABeneficiario(ExcluirRelacionamentoBeneficiarioDto cadastroBeneficiarioDto, DateTimeDB dataAtualMovimento, Short idCanal)
            throws ComumNegocioException, ComumException {
        getLogger().debug("###### inicio metodo criarMensagemDDABeneficiario ######");
        MensagemDDABeneficiario mensagemDDABeneficiario = new MensagemDDABeneficiario();
        mensagemDDABeneficiario.setCodTipoPessoaBeneficiario(cadastroBeneficiarioDto.getTipoPessoaBeneficiario().getCodDominioCip());
        mensagemDDABeneficiario.setNumCpfCnpjBeneficiario(cadastroBeneficiarioDto.getCnpjCpfBeneficiario());
        mensagemDDABeneficiario.setDataFimRelacionamento(new DateTimeDB());

        mensagemDDADelegate.incluir(mensagemDDABeneficiario, TipoMensagemDDA.DDA0503, dataAtualMovimento, getUsuarioLogado(), getIdInstituicaoLogado(), idCanal);
        getLogger().debug("###### fim metodo criarMensagemDDABeneficiario ######");
    }

    /**
     * Método responsável por validar informações recebidas antes do envio para a CIP.
     * 
     * @param beneficiarioDto
     * @throws ComumNegocioException
     * @throws ComumException
     * 
     */
    private void validaDadosBeneficiario(ExcluirRelacionamentoBeneficiarioDto beneficiarioDto) throws ComumNegocioException {
        if (ObjectUtil.isNull(beneficiarioDto.getTipoPessoaBeneficiario())) {
            throw new ComumNegocioException(ERRO_TIPO_PESSOA_INVALIDO);
        } else if (ObjectUtil.isEmpty(beneficiarioDto.getCnpjCpfBeneficiario())) {
            throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
        } else if (beneficiarioDto.getTipoPessoaBeneficiario().equals(TipoPessoaEnum.PF)) {
            if (!new ValidacaoCpf(beneficiarioDto.getCnpjCpfBeneficiario(), null).validar()) {
                throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
            }
        } else if (beneficiarioDto.getTipoPessoaBeneficiario().equals(TipoPessoaEnum.PJ)) {
            if (!new ValidacaoCnpj(beneficiarioDto.getCnpjCpfBeneficiario(), null).validar()) {
                throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
            }
        }
    }

    /**
     * Método responsável por verificar se o beneficiário é válido para alteração.
     * 
     * @param beneficiario
     * @throws ComumNegocioException void
     * 
     */
    private void validarBeneficiarioCadastrado(Long numIdentBeneficiario) throws ComumNegocioException {
        if (ObjectUtil.isEmpty(numIdentBeneficiario)) {
            throw new ComumNegocioException(ERRO_BENEFICARIO_NAO_CADASTRADO_CIP);
        }
    }

}
