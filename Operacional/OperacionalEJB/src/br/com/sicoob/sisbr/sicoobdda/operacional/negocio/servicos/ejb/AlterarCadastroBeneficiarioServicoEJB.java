/**
alterar * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criação:    May 8, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AlterarCadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioAlteracaoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RepresentanteBeneficiarioAlteracaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RepresentanteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioRepresentante;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.CadastrarBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.ExcluirRelacionamentoBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.AlterarCadastroBeneficiarioServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiarioLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * AlterarCadastroBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ AlterarCadastroBeneficiarioServicoLocal.class })
public class AlterarCadastroBeneficiarioServicoEJB extends OperacionalServicoEJB implements AlterarCadastroBeneficiarioServicoLocal {

    private static final String ERRO_BENEFICIARIO_SEM_CONVENIO = "integracaocip.erro.beneficiario.sem.convenio";
    protected static final String ERRO_TP_MANUTENCAO_CONVENIO_INVALIDA = "integracaocip.erro.tp.manutencao.convenio.invalida";
    protected static final String ERRO_TP_MANUTENCAO_REPRESENTANTE_INVALIDA = "integracaocip.erro.tp.manutencao.representante.invalida";

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BeneficiarioCipDao dao;

    @SuppressWarnings("unused")
    @PersistenceContext(unitName = "emSicoobDDA_sqlServer")
    private EntityManager emSQL;

    @Dao(entityManager = "emSQL", fabrica = OperacionalDaoFactory.class)
    private BeneficiarioLegadoDao beneficiarioDao;

    private ExcluirRelacionamentoBeneficiarioDelegate excluirRelacionamentoBeneficiarioDelegate = OperacionalFabricaDelegates.getInstance()
            .getExcluirRelacionamentoBeneficiarioDelegate();

    private CadastrarBeneficiarioDelegate cadastrarBeneficiarioDelegate = OperacionalFabricaDelegates.getInstance().getCadastrarBeneficiarioDelegate();

    private SCIDelegate sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();

    private MensagemDDADelegate mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarCadastroBeneficiarioServico#excluirConvenioBeneficiario(java.lang.Long,
     *      java.lang.Integer, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short)
     */
    public void excluirConvenioBeneficiario(Long numCliente, Integer numCooperativa, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException, ComumNegocioException {
        AlterarCadastroBeneficiarioDDADto alterarCadastro = beneficiarioDao.obterAlterarCadastroBeneficiarioDDADto(numCliente, numCooperativa);

        BeneficiarioDDA beneficiario = getDao().obterBeneficiarioDiferenteDeSemCadastro(alterarCadastro.getCnpjCpfBeneficiario());

        if (ObjectUtil.isNull(beneficiario)) {
            // Beneficiario ja excluido. Ex.: Se a situacao for ContratoCancelado e o mesmo estiver apenas em uma cooperativa a exclusao ja foi enviada para cip
            // e ja foi removido da base do DDA.
            validarMensagemDDAEnvioPendente(alterarCadastro.getCnpjCpfBeneficiario());
        } else if (beneficiario.getListaBeneficiarioInstituicao().size() > Constantes.INTEGER_UM) {
            alterarCadastro.setListaConvenioAlteracaoDto(new ArrayList<ConvenioAlteracaoDDADto>());
            alterarCadastro.getListaConvenioAlteracaoDto()
                    .add(this.obterConvenioAlteracaoDDADto(alterarCadastro.getCnpjCpfBeneficiario(), numCooperativa, TipoManutencaoEnum.EXCLUSAO));
            alterarMensagemDDABeneficiario(alterarCadastro, beneficiario.getNumIdentBeneficiario(), dataAtualMovimento, idCanal);
        } else {
            excluirRelacionamentoBeneficiarioDelegate.excluirRelacionamentoBeneficiario(alterarCadastro.getCnpjCpfBeneficiario(), dataAtualMovimento, idCanal);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarCadastroBeneficiarioServico#processarReenvioAlterarCadastroBeneficiario(br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA,
     *      java.util.List, java.lang.Short)
     */
    public void processarReenvioAlterarCadastroBeneficiario(BeneficiarioDDA beneficiario, List<ConvenioAlteracaoDDADto> listaConvenio, Short idCanal)
            throws ComumException, ComumNegocioException {
        AlterarCadastroBeneficiarioDDADto alterarCadastro = beneficiarioDao.obterAlterarCadastroBeneficiarioDDADto(beneficiario.getNumCpfCnpj(),
                listaConvenio.get(0).getNumAgencia());
        if (!ObjectUtil.isEmpty(alterarCadastro.getListaConvenioAlteracaoDto())) {
            alterarCadastro.getListaConvenioAlteracaoDto().clear();
        }
        for (ConvenioAlteracaoDDADto convenio : listaConvenio) {
            alterarCadastro.getListaConvenioAlteracaoDto()
                    .add(this.obterConvenioAlteracaoDDADto(alterarCadastro.getCnpjCpfBeneficiario(), convenio.getNumAgencia(), convenio.getTipoManutencaoConvenio()));
        }

        alterarMensagemDDABeneficiario(alterarCadastro, beneficiario.getNumIdentBeneficiario(), new DateTimeDB(), idCanal);
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numCooperativa
     * @param tipoManutencao
     * @return
     * @throws ComumException ConvenioAlteracaoDDADto
     * 
     */
    private ConvenioAlteracaoDDADto obterConvenioAlteracaoDDADto(String numCpfCnpj, Integer numCooperativa, TipoManutencaoEnum tipoManutencao) throws ComumException {
        return this.obterConvenioAlteracaoDDADto(numCpfCnpj, numCooperativa, null, null, tipoManutencao);
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numCooperativa
     * @param codSituacaoCedente
     * @param dataCancelamento
     * @param tipoManutencao
     * @return
     * @throws ComumException ConvenioAlteracaoDDADto
     * 
     */
    private ConvenioAlteracaoDDADto obterConvenioAlteracaoDDADto(String numCpfCnpj, Integer numCooperativa, Long codSituacaoCedente, Date dataCancelamento,
            TipoManutencaoEnum tipoManutencao) throws ComumException {
        ConvenioAlteracaoDDADto convenio = beneficiarioDao.obterConvenioAlteracaoDDADto(numCpfCnpj, numCooperativa);
        convenio.setTipoManutencaoConvenio(tipoManutencao);

        if (tipoManutencao.equals(TipoManutencaoEnum.EXCLUSAO)) {
            convenio.setSitucaoConvenio(null);
            convenio.setDataFimConvenio(ObjectUtil.isNull(dataCancelamento) ? new Date() : dataCancelamento);
        } else {
            if (!ObjectUtil.isNull(codSituacaoCedente)) {
                convenio.setSitucaoConvenio(SituacaoConvenioBeneficiarioEnum.getBy(codSituacaoCedente.shortValue()));
            }
            if (!ObjectUtil.isNull(dataCancelamento)) {
                convenio.setDataFimConvenio(dataCancelamento);
            }
        }

        return convenio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarCadastroBeneficiarioServico#alterarCadastroBeneficiario(java.lang.Long,
     *      java.lang.Integer, java.lang.Long, java.util.Date, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short)
     */
    public void alterarCadastroBeneficiario(Long numCliente, Integer idInstituicao, Long codSituacaoCedente, Date dataCancelamento, DateTimeDB dataAtualMovimento, Short idCanal)
            throws ComumException, ComumNegocioException {
        Integer numCooperativa = sciDelegate.obterInstituicaoCache(idInstituicao).getNumCooperativa();
        this.alterarCadastro(numCliente, numCooperativa, codSituacaoCedente, dataCancelamento, null, dataAtualMovimento, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.AlterarCadastroBeneficiarioServico#alterarCadastroBeneficiario(java.lang.Long,
     *      java.lang.Integer, java.lang.Long, br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA, br.com.bancoob.persistencia.types.DateTimeDB,
     *      java.lang.Short)
     */
    public void alterarCadastroBeneficiario(Long numCliente, Integer numCooperativa, Long codSituacaoCedente, BeneficiarioDDA beneficiario, DateTimeDB dataAtualMovimento,
            Short idCanal) throws ComumException, ComumNegocioException {
        this.alterarCadastro(numCliente, numCooperativa, codSituacaoCedente, null, beneficiario, dataAtualMovimento, idCanal);
    }

    /**
     * @param numCliente
     * @param numCooperativa
     * @param codSituacaoCedente
     * @param dataCancelamento
     * @throws ComumException void
     * @throws ComumNegocioException
     * 
     */
    private void alterarCadastro(Long numCliente, Integer numCooperativa, Long codSituacaoCedente, Date dataCancelamento, BeneficiarioDDA beneficiario,
            DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException, ComumNegocioException {
        AlterarCadastroBeneficiarioDDADto alterarCadastroDto = beneficiarioDao.obterAlterarCadastroBeneficiarioDDADto(numCliente, numCooperativa);

        BeneficiarioDDA beneficiarioCadastrado = setBeneficiarioDDA(beneficiario, alterarCadastroDto.getCnpjCpfBeneficiario());

        if (codSituacaoCedente.shortValue() == SituacaoConvenioBeneficiarioEnum.EXCLUIDO.getCodSituacao()) {
            criarMensagemExcluirBeneficiarioDDA(numCooperativa, codSituacaoCedente, dataCancelamento, alterarCadastroDto, beneficiarioCadastrado, dataAtualMovimento, idCanal);
        } else {
            if (ObjectUtil.isNull(beneficiarioCadastrado)) {
                cadastrarBeneficiarioDelegate.processarCadastroBeneficiario(numCliente, alterarCadastroDto.getCnpjCpfBeneficiario(), numCooperativa, dataAtualMovimento, idCanal);
            } else {
                alterarCadastroDto.setListaConvenioAlteracaoDto(new ArrayList<ConvenioAlteracaoDDADto>());
                alterarInclusaoConvenio(numCooperativa, codSituacaoCedente, dataCancelamento, alterarCadastroDto, beneficiarioCadastrado.getNumIdentBeneficiario(),
                        dataAtualMovimento, idCanal);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param codSituacaoCedente
     * @param dataCancelamento
     * @param alterarCadastroDto
     * @param beneficiarioCadastrado
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    private void criarMensagemExcluirBeneficiarioDDA(Integer numCooperativa, Long codSituacaoCedente, Date dataCancelamento, AlterarCadastroBeneficiarioDDADto alterarCadastroDto,
            BeneficiarioDDA beneficiarioCadastrado, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumNegocioException, ComumException {
        if (ObjectUtil.isNull(beneficiarioCadastrado)) {
            excluirRelacionamentoBeneficiarioDelegate.excluirRelacionamentoBeneficiario(alterarCadastroDto.getCnpjCpfBeneficiario(), dataAtualMovimento, idCanal);
        } else {
            alterarCadastroDto.setListaConvenioAlteracaoDto(new ArrayList<ConvenioAlteracaoDDADto>());
            alterarExcluirConvenio(numCooperativa, codSituacaoCedente, dataCancelamento, alterarCadastroDto, beneficiarioCadastrado, dataAtualMovimento, idCanal);
        }
    }

    /**
     * Método responsável por setar o BeneficiarioDDA
     * 
     * @param beneficiario
     * @param numCpfCnpjBeneficiario
     * @return
     * @throws ComumException BeneficiarioDDA
     * 
     */
    private BeneficiarioDDA setBeneficiarioDDA(BeneficiarioDDA beneficiario, String numCpfCnpjBeneficiario) throws ComumException {
        BeneficiarioDDA beneficiarioCadastrado;
        if (ObjectUtil.isNull(beneficiario) || ObjectUtil.isNull(beneficiario.getId())) {
            beneficiarioCadastrado = getDao().obterBeneficiarioDiferenteDeSemCadastro(numCpfCnpjBeneficiario);
        } else {
            beneficiarioCadastrado = beneficiario;
        }
        return beneficiarioCadastrado;
    }

    /**
     * @param numCooperativa
     * @param codSituacaoCedente
     * @param dataCancelamento
     * @param alterarCadastro
     * @throws ComumException void
     * @throws ComumNegocioException
     * 
     */
    private void alterarInclusaoConvenio(Integer numCooperativa, Long codSituacaoCedente, Date dataCancelamento, AlterarCadastroBeneficiarioDDADto alterarCadastro,
            Long numIdentificacaoBeneficiario, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException, ComumNegocioException {
        alterarCadastro.getListaConvenioAlteracaoDto().add(
                this.obterConvenioAlteracaoDDADto(alterarCadastro.getCnpjCpfBeneficiario(), numCooperativa, codSituacaoCedente, dataCancelamento, TipoManutencaoEnum.INCLUSAO));
        alterarMensagemDDABeneficiario(alterarCadastro, numIdentificacaoBeneficiario, dataAtualMovimento, idCanal);
    }

    /**
     * Método responsável por
     * 
     * @param beneficiarioDto
     * @param numIdentificacaoBeneficiario
     * @param dataAtualMovimento
     * @param idCanal
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    private void alterarMensagemDDABeneficiario(AlterarCadastroBeneficiarioDDADto beneficiarioDto, Long numIdentificacaoBeneficiario, DateTimeDB dataAtualMovimento, Short idCanal)
            throws ComumNegocioException, ComumException {
        getLogger().debug("###### inicio metodo alterarMensagemDDABeneficiario ######");
        validarMensagemDDAEnvioPendente(beneficiarioDto.getCnpjCpfBeneficiario());
        validaDadosBeneficiario(beneficiarioDto);
        validarBeneficiarioCadastrado(numIdentificacaoBeneficiario);

        criarMensagemDDABeneficiario(beneficiarioDto, dataAtualMovimento, idCanal);

        getLogger().debug("###### fim metodo alterarMensagemDDABeneficiario ######");
    }

    /**
     * Método responsável por Setar e Salvar o objeto MensagemDDABeneficiario
     * 
     * @param cadastroBeneficiarioDto
     * @param beneficiario
     * @param dataAtualMovimento
     * @return MensagemDDABeneficiario
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void criarMensagemDDABeneficiario(AlterarCadastroBeneficiarioDDADto cadastroBeneficiarioDto, DateTimeDB dataAtualMovimento, Short idCanal)
            throws ComumNegocioException, ComumException {
        getLogger().debug("###### inicio metodo criarMensagemDDABeneficiario ######");
        MensagemDDABeneficiario mensagemDDABeneficiario = new MensagemDDABeneficiario();
        mensagemDDABeneficiario.setNumCpfCnpjBeneficiario(cadastroBeneficiarioDto.getCnpjCpfBeneficiario());
        mensagemDDABeneficiario.setCodTipoPessoaBeneficiario(cadastroBeneficiarioDto.getTipoPessoaBeneficiario().getCodDominioCip());
        mensagemDDABeneficiario.setNomeBeneficiario(cadastroBeneficiarioDto.getNomeRazaoSocialBeneficiario());

        // Se for PJ e não tiver nome fantasia define a razão social
        if (TipoPessoaEnum.isPessoaJuridica(cadastroBeneficiarioDto.getTipoPessoaBeneficiario().getCodDominioCip())
                && ObjectUtil.isEmpty(cadastroBeneficiarioDto.getNomeFantasiaBeneficiario())) {
            mensagemDDABeneficiario.setNomeFantasiaBeneficiario(cadastroBeneficiarioDto.getNomeRazaoSocialBeneficiario());
        } else {
            mensagemDDABeneficiario.setNomeFantasiaBeneficiario(cadastroBeneficiarioDto.getNomeFantasiaBeneficiario());
        }

        mensagemDDABeneficiario.setDataInicioRelacionamento(new DateTimeDB(cadastroBeneficiarioDto.getDataInicioRelacionamento().getTime()));

        List<MensagemDDABeneficiarioConvenio> listaMensagemDDABeneficiarioConvenio = new ArrayList<MensagemDDABeneficiarioConvenio>();
        List<MensagemDDABeneficiarioRepresentante> listaMensagemDDABeneficiarioRepresentante = new ArrayList<MensagemDDABeneficiarioRepresentante>();

        if (!ObjectUtil.isEmpty(cadastroBeneficiarioDto.getListaConvenioAlteracaoDto())) {
            for (ConvenioAlteracaoDDADto convenio : cadastroBeneficiarioDto.getListaConvenioAlteracaoDto()) {
                listaMensagemDDABeneficiarioConvenio.add(criarMensagemDDABeneficiarioConvenio(mensagemDDABeneficiario, convenio));
            }
        }
        if (!ObjectUtil.isEmpty(cadastroBeneficiarioDto.getListaRepresentanteBeneficiarioDto())) {
            for (RepresentanteBeneficiarioDto representanteBeneficiarioDto : cadastroBeneficiarioDto.getListaRepresentanteBeneficiarioDto()) {
                listaMensagemDDABeneficiarioRepresentante.add(criarMensagemDDABeneficiarioRepresentante(mensagemDDABeneficiario, representanteBeneficiarioDto));
            }
        }

        mensagemDDABeneficiario.setListaMensagemDDABeneficiarioConvenio(listaMensagemDDABeneficiarioConvenio);
        mensagemDDABeneficiario.setListaMensagemDDABeneficiarioRepresentante(listaMensagemDDABeneficiarioRepresentante);

        mensagemDDADelegate.incluir(mensagemDDABeneficiario, TipoMensagemDDA.DDA0502, dataAtualMovimento, getUsuarioLogado(), getIdInstituicaoLogado(), idCanal);

        getLogger().debug("###### fim metodo criarMensagemDDABeneficiario ######");
    }

    /**
     * Método responsável por criar a MensagemDDABeneficiarioConvenio
     * 
     * @param mensagemDDABeneficiario
     * @param convenio
     * @return MensagemDDABeneficiarioConvenio
     * 
     */
    private MensagemDDABeneficiarioConvenio criarMensagemDDABeneficiarioConvenio(MensagemDDABeneficiario mensagemDDABeneficiario, ConvenioAlteracaoDDADto convenio) {
        MensagemDDABeneficiarioConvenio mensagemDDABeneficiarioConvenio = new MensagemDDABeneficiarioConvenio();
        mensagemDDABeneficiarioConvenio.setNumAgencia(convenio.getNumAgencia());
        mensagemDDABeneficiarioConvenio.setNumConta(ObjectUtil.isEmpty(convenio.getNumConta()) ? null : convenio.getNumConta().longValue());
        mensagemDDABeneficiarioConvenio.setNumClienteOuConvenio(ObjectUtil.isEmpty(convenio.getCodClienteConvenio()) ? null : convenio.getCodClienteConvenio());
        mensagemDDABeneficiarioConvenio.setCodTipoProdutoDDA(convenio.getTipoProdutoConvenio().getCodDominio());
        mensagemDDABeneficiarioConvenio.setCodSituacaoConvenioDDA(ObjectUtil.isNull(convenio.getSitucaoConvenio()) ? null : convenio.getSitucaoConvenio().getCodDominio());
        mensagemDDABeneficiarioConvenio.setDataInicioRelacionamento(new DateTimeDB(convenio.getDataInicioConvenio().getTime()));
        mensagemDDABeneficiarioConvenio
                .setCodISPBParticipanteIncorporado(ObjectUtil.isNull(convenio.getIspbParticipanteIncorporado()) ? null : convenio.getIspbParticipanteIncorporado());
        mensagemDDABeneficiarioConvenio.setDataFimRelacionamento(ObjectUtil.isNull(convenio.getDataFimConvenio()) ? null : new DateTimeDB(convenio.getDataFimConvenio().getTime()));

        mensagemDDABeneficiarioConvenio.setCodTipoOperacao(convenio.getTipoManutencaoConvenio().getCodIndicador());

        mensagemDDABeneficiarioConvenio.setMensagemDDABeneficiario(mensagemDDABeneficiario);

        return mensagemDDABeneficiarioConvenio;
    }

    /**
     * Método responsável por
     * 
     * @param mensagemDDABeneficiario
     * @param representanteBeneficiarioDto
     * @return MensagemDDABeneficiarioRepresentante
     * 
     */
    private MensagemDDABeneficiarioRepresentante criarMensagemDDABeneficiarioRepresentante(MensagemDDABeneficiario mensagemDDABeneficiario,
            RepresentanteBeneficiarioDto representanteBeneficiarioDto) {
        MensagemDDABeneficiarioRepresentante mensagemDDABeneficiarioRepresetante = new MensagemDDABeneficiarioRepresentante();
        mensagemDDABeneficiarioRepresetante.setCodTipoPessoaRepresentante(representanteBeneficiarioDto.getTipoPessoaRepresentanteBeneficiario().getCodDominioCip());
        mensagemDDABeneficiarioRepresetante.setNumCpfCnpjRepresentante(representanteBeneficiarioDto.getCnpjCpfRepresentanteBeneficiario());
        mensagemDDABeneficiarioRepresetante.setMensagemDDABeneficiario(mensagemDDABeneficiario);

        return mensagemDDABeneficiarioRepresetante;
    }

    /**
     * Método responsável por validar informações recebidas antes do envio para a CIP.
     * 
     * @param beneficiarioDto
     * @throws ComumNegocioException
     * @throws ComumException
     * 
     */
    private void validaDadosBeneficiario(AlterarCadastroBeneficiarioDDADto beneficiarioDto) throws ComumNegocioException {
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

        if (ObjectUtil.isEmpty(beneficiarioDto.getNomeRazaoSocialBeneficiario())) {
            throw new ComumNegocioException(ERRO_NOME_RAZAO_SOCIAL_BENEFICIARIO_INVALIDO);
        } else {
            for (ConvenioAlteracaoDDADto convDto : beneficiarioDto.getListaConvenioAlteracaoDto()) {
                validarDadosConvenioCargaDDADto(convDto);
            }

            if (!ObjectUtil.isEmpty(beneficiarioDto.getListaRepresentanteBeneficiarioDto())) {
                for (RepresentanteBeneficiarioAlteracaoDto repDto : beneficiarioDto.getListaRepresentanteBeneficiarioDto()) {
                    validarDadosRepresentanteBeneficiarioDto(repDto);
                }
            }
        }
    }

    /**
     * Método responsável por validar os dados do Convenio
     * 
     * @param convDto
     * @throws ComumNegocioException void
     * 
     */
    private void validarDadosConvenioCargaDDADto(ConvenioAlteracaoDDADto convDto) throws ComumNegocioException {
        if (ObjectUtil.isNull(convDto.getTipoManutencaoConvenio())) {
            throw new ComumNegocioException(ERRO_TP_MANUTENCAO_CONVENIO_INVALIDA);
        } else if (ObjectUtil.isNull(convDto.getDataInicioConvenio())) {
            throw new ComumNegocioException(ERRO_DATA_INICIO_RELACIONAMENTO_CONVENIO_INVALIDA);
        } else if (ObjectUtil.isNull(convDto.getTipoAgencia())) {
            throw new ComumNegocioException(ERRO_TP_AGENCIA_DESTINO_CONVENIO_INVALIDA);
        } else if (ObjectUtil.isEmpty(convDto.getNumAgencia())) {
            throw new ComumNegocioException(ERRO_AGENCIA_DESTINO_CONVENIO_INVALIDA);
        } else if (ObjectUtil.isNull(convDto.getTipoProdutoConvenio())) {
            throw new ComumNegocioException(ERRO_TP_PRODUTO_CONVENIO_INVALIDA);
        }
    }

    /**
     * Método responsável por validar os dados dos representantes
     * 
     * @param repDto void
     * @throws ComumNegocioException
     * 
     */
    private void validarDadosRepresentanteBeneficiarioDto(RepresentanteBeneficiarioAlteracaoDto repDto) throws ComumNegocioException {
        if (ObjectUtil.isNull(repDto.getTipoManutencaoRepresentante())) {
            throw new ComumNegocioException(ERRO_TP_MANUTENCAO_REPRESENTANTE_INVALIDA);
        } else if (ObjectUtil.isNull(repDto.getTipoPessoaRepresentanteBeneficiario())) {
            throw new ComumNegocioException(ERRO_TIPO_PESSOA_REPRESENTANTE_INVALIDO);
        } else if (ObjectUtil.isEmpty(repDto.getCnpjCpfRepresentanteBeneficiario())) {
            throw new ComumNegocioException(ERRO_CNPJ_CPF_REPRESENTANTE_INVALIDO);
        } else if (repDto.getTipoPessoaRepresentanteBeneficiario().equals(TipoPessoaEnum.PF)) {
            if (!new ValidacaoCpf(repDto.getCnpjCpfRepresentanteBeneficiario(), null).validar()) {
                throw new ComumNegocioException(ERRO_CNPJ_CPF_REPRESENTANTE_INVALIDO);
            }
        } else if (repDto.getTipoPessoaRepresentanteBeneficiario().equals(TipoPessoaEnum.PJ)) {
            if (!new ValidacaoCnpj(repDto.getCnpjCpfRepresentanteBeneficiario(), null).validar()) {
                throw new ComumNegocioException(ERRO_CNPJ_CPF_REPRESENTANTE_INVALIDO);
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
    private void validarBeneficiarioCadastrado(Long numIdentificacaoBeneficiario) throws ComumNegocioException {
        if (ObjectUtil.isEmpty(numIdentificacaoBeneficiario)) {
            throw new ComumNegocioException(ERRO_BENEFICARIO_NAO_CADASTRADO_CIP);
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param codSituacaoCedente
     * @param dataCancelamento
     * @param alterarCadastro
     * @param beneficiario
     * @throws ComumException void
     * @throws ComumNegocioException
     * 
     */
    private void alterarExcluirConvenio(Integer numCooperativa, Long codSituacaoCedente, Date dataCancelamento, AlterarCadastroBeneficiarioDDADto alterarCadastro,
            BeneficiarioDDA beneficiario, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException, ComumNegocioException {
        switch (beneficiario.getListaBeneficiarioInstituicao().size()) {
        case 0:
            throw new ComumException(ERRO_BENEFICIARIO_SEM_CONVENIO);
        case 1:
            excluirRelacionamentoBeneficiarioDelegate.excluirRelacionamentoBeneficiario(alterarCadastro.getCnpjCpfBeneficiario(), beneficiario.getNumIdentBeneficiario(),
                    dataAtualMovimento, idCanal);
            break;
        default:
            alterarCadastro.getListaConvenioAlteracaoDto().add(
                    this.obterConvenioAlteracaoDDADto(alterarCadastro.getCnpjCpfBeneficiario(), numCooperativa, codSituacaoCedente, dataCancelamento, TipoManutencaoEnum.EXCLUSAO));
            alterarMensagemDDABeneficiario(alterarCadastro, getDao().obterBeneficiarioDiferenteDeSemCadastro(alterarCadastro.getCnpjCpfBeneficiario()).getNumIdentBeneficiario(),
                    dataAtualMovimento, idCanal);
        }
    }

    /**
     * @return the dao
     */
    public BeneficiarioCipDao getDao() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacionalServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }
}
