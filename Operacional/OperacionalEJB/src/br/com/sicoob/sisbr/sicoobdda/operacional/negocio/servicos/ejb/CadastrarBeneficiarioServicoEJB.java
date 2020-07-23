/**
 * Projeto:         Cobranca Bancaria
 * Camada Projeto:  cob-integracao-cip
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.negocio.servicos.ejb
 * Arquivo:         CadastrarBeneficiarioServicoEJB.java
 * Data Criacao:    May 8, 2015
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
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.CadastroBeneficiarioDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConvenioCadastroDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RepresentanteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoCedenteEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoConvenioBeneficiarioEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoManutencaoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.BeneficiarioInstituicao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioConvenio;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABeneficiarioRepresentante;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BeneficiarioCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.AlterarCadastroBeneficiarioDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.CadastrarBeneficiarioServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.BeneficiarioLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * CadastrarBeneficiarioServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ CadastrarBeneficiarioServicoLocal.class })
public class CadastrarBeneficiarioServicoEJB extends OperacionalServicoEJB implements CadastrarBeneficiarioServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BeneficiarioCipDao dao;

    @SuppressWarnings("unused")
    @PersistenceContext(unitName = "emSicoobDDA_sqlServer")
    private EntityManager emSQL;

    @Dao(entityManager = "emSQL", fabrica = OperacionalDaoFactory.class)
    private BeneficiarioLegadoDao beneficiarioDao;

    private AlterarCadastroBeneficiarioDelegate alterarCadastroBeneficiarioDelegate;

    private SCIDelegate sciDelegate;

    private MensagemDDADelegate mensagemDDADelegate;

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CadastrarBeneficiarioServico#processarCadastroBeneficiario(java.lang.String,
     * java.util.List)
     */
    public void processarReenvioMensagemCadastroBeneficiario(String numCpfCnpj, List<Integer> listaNumCooperativa, Short idCanal) throws ComumException, ComumNegocioException {
        CadastroBeneficiarioDDADto cadastroBeneficiarioDto = null;
        Date dataInicioRelacionamentoParticipante = new Date();
        List<ConvenioCadastroDDADto> listaConvenioParticipante = new ArrayList<ConvenioCadastroDDADto>();
        for (Integer numCooperativa : listaNumCooperativa) {
            cadastroBeneficiarioDto = getBeneficiarioDao().obterCadastroBeneficiarioDDADto(numCpfCnpj, numCooperativa);
            listaConvenioParticipante.addAll(cadastroBeneficiarioDto.getListaConvenioCadastroDto());
            if (cadastroBeneficiarioDto.getDataInicioRelacionamentoParticipante().before(dataInicioRelacionamentoParticipante)) {
                dataInicioRelacionamentoParticipante = cadastroBeneficiarioDto.getDataInicioRelacionamentoParticipante();
            }
        }
        cadastroBeneficiarioDto.setDataInicioRelacionamentoParticipante(dataInicioRelacionamentoParticipante);
        cadastroBeneficiarioDto.setListaConvenioCadastroDto(listaConvenioParticipante);
        cadastroBeneficiarioDto.setSituacaoBeneficiario(br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.SituacaoBeneficiarioEnum.APTO);
        if (cadastroBeneficiarioDto.getListaRepresentanteBeneficiarioDto() == null) {
            cadastroBeneficiarioDto.setListaRepresentanteBeneficiarioDto(new ArrayList<RepresentanteBeneficiarioDto>());
        }
        gravarMensagemDDABeneficiario(cadastroBeneficiarioDto, new DateTimeDB(), idCanal);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CadastrarBeneficiarioServico#processarCadastroBeneficiario(java.lang.Long,
     * java.lang.String, java.lang.Integer)
     */
    public void processarCadastroBeneficiario(Long numCliente, String numCpfCnpj, Integer numCooperativa, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException,
            ComumNegocioException {
        BeneficiarioDDA beneficiarioExistente = getDao().obterBeneficiario(numCpfCnpj);
        if (!ObjectUtil.isNull(beneficiarioExistente)
                && beneficiarioExistente.getBolOrigemSicoob()
                && (!ObjectUtil.isNull(beneficiarioExistente.getSituacaoBeneficiarioDDA()) && !beneficiarioExistente.getSituacaoBeneficiarioDDA().getCodSituacaoBeneficiario()
                        .equals(SituacaoBeneficiarioDDA.SEM_CADASTRO))) {
            getAlterarCadastroBeneficiarioDelegate().alterarCadastroBeneficiario(numCliente, numCooperativa, SituacaoCedenteEnum.ATIVO.getCodigo(), beneficiarioExistente,
                    dataAtualMovimento, idCanal);
        } else {
            CadastroBeneficiarioDDADto cadastroDto = obterCadastroBeneficiarioDDADto(numCpfCnpj, numCooperativa);
            cadastroDto.setSituacaoBeneficiario(SituacaoBeneficiarioEnum.APTO);
            gravarMensagemDDABeneficiario(cadastroDto, dataAtualMovimento, idCanal);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CadastrarBeneficiarioServico#processarCadastroBeneficiario(java.lang.Long)
     */
    public void processarCadastroBeneficiario(Long idBeneficiario, Short idCanal) throws ComumException, ComumNegocioException {
        BeneficiarioDDA beneficiario = getEm().find(BeneficiarioDDA.class, idBeneficiario);
        List<Integer> listaNumCooperativa = new ArrayList<Integer>();
        for (BeneficiarioInstituicao beneficiarioInstituicao : beneficiario.getListaBeneficiarioInstituicao()) {
            Integer numCooperativa = getSciDelegate().obterInstituicaoCache(beneficiarioInstituicao.getIdInstituicao()).getNumCooperativa();
            if (!listaNumCooperativa.contains(numCooperativa)) {
                listaNumCooperativa.add(numCooperativa);
            }
        }
        this.processarCadastroBeneficiario(beneficiario, listaNumCooperativa, idCanal);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.CadastrarBeneficiarioServico#processarCadastroBeneficiario(br.com.sicoob.sisbr.sicoobdda.
     * entidades.entidades.BeneficiarioDDA, java.util.List)
     */
    private void processarCadastroBeneficiario(BeneficiarioDDA beneficiario, List<Integer> listaNumCooperativa, Short idCanal) throws ComumException, ComumNegocioException {
        CadastroBeneficiarioDDADto cadastroBeneficiario = null;
        Date dataInicioRelacionamentoParticipante = new Date();
        List<ConvenioCadastroDDADto> listaConvenioParticipante = new ArrayList<ConvenioCadastroDDADto>();
        for (Integer numCooperativa : listaNumCooperativa) {
            cadastroBeneficiario = getBeneficiarioDao().obterCadastroBeneficiarioDDADto(beneficiario.getNumCpfCnpj(), numCooperativa);
            listaConvenioParticipante.addAll(cadastroBeneficiario.getListaConvenioCadastroDto());
            if (cadastroBeneficiario.getDataInicioRelacionamentoParticipante().before(dataInicioRelacionamentoParticipante)) {
                dataInicioRelacionamentoParticipante = cadastroBeneficiario.getDataInicioRelacionamentoParticipante();
            }
        }
        cadastroBeneficiario.setDataInicioRelacionamentoParticipante(dataInicioRelacionamentoParticipante);
        cadastroBeneficiario.setListaConvenioCadastroDto(listaConvenioParticipante);
        cadastroBeneficiario.setSituacaoBeneficiario(SituacaoBeneficiarioEnum.APTO);
        if (cadastroBeneficiario.getListaRepresentanteBeneficiarioDto() == null) {
            cadastroBeneficiario.setListaRepresentanteBeneficiarioDto(new ArrayList<RepresentanteBeneficiarioDto>());
        }

        gravarMensagemDDABeneficiario(cadastroBeneficiario, new DateTimeDB(), idCanal);
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numCooperativa
     * @return
     * @throws ComumException CadastroBeneficiarioDDADto
     * 
     */
    private CadastroBeneficiarioDDADto obterCadastroBeneficiarioDDADto(String numCpfCnpj, Integer numCooperativa) throws ComumException {
        return getBeneficiarioDao().obterCadastroBeneficiarioDDADto(numCpfCnpj, numCooperativa);
    }

    /**
     * Método responsável por Criar o Beneficario e suas respectivas mensagens, DDA.MensagemDDA - DDA.MensagemDDABeneficiario -
     * DDA.MensagemDDABeneficiarioConvenio - DDA.MensagemDDABeneficiarioRepresentante
     * 
     * @param cadastroBeneficiarioDto
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    private void gravarMensagemDDABeneficiario(CadastroBeneficiarioDDADto cadastroBeneficiarioDto, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumException,
            ComumNegocioException {
        getLogger().debug("###### inicio metodo gravarMensagemDDABeneficiario ######");
        validarMensagemDDAEnvioPendente(cadastroBeneficiarioDto.getCnpjCpfBeneficiario());
        validaDadosBeneficiario(cadastroBeneficiarioDto);
        validaBeneficiarioJaCadastrado(cadastroBeneficiarioDto.getCnpjCpfBeneficiario());

        criarMensagemDDABeneficiario(cadastroBeneficiarioDto, dataAtualMovimento, idCanal);

        getLogger().debug("###### fim metodo gravarMensagemDDABeneficiario com sucesso######");
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
    private void criarMensagemDDABeneficiario(CadastroBeneficiarioDDADto cadastroBeneficiarioDto, DateTimeDB dataAtualMovimento, Short idCanal) throws ComumNegocioException,
            ComumException {
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

        mensagemDDABeneficiario.setCodSituacaoBeneficiario(SituacaoConvenioBeneficiarioEnum.ATIVO.getCodDominio());
        mensagemDDABeneficiario.setDataHoraSituacao(new DateTimeDB());
        mensagemDDABeneficiario.setCodSituacaoRelacionamentoBeneficiario(cadastroBeneficiarioDto.getSituacaoRelacionamentoParticipante().getCodDominio());
        mensagemDDABeneficiario.setDataInicioRelacionamento(new DateTimeDB(cadastroBeneficiarioDto.getDataInicioRelacionamentoParticipante().getTime()));

        List<MensagemDDABeneficiarioConvenio> listaMensagemDDABeneficiarioConvenio = new ArrayList<MensagemDDABeneficiarioConvenio>();
        List<MensagemDDABeneficiarioRepresentante> listaMensagemDDABeneficiarioRepresentante = new ArrayList<MensagemDDABeneficiarioRepresentante>();

        if (!ObjectUtil.isEmpty(cadastroBeneficiarioDto.getListaConvenioCadastroDto())) {
            for (ConvenioCadastroDDADto convenio : cadastroBeneficiarioDto.getListaConvenioCadastroDto()) {
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

        getMensagemDDADelegate().incluir(mensagemDDABeneficiario, TipoMensagemDDA.DDA0501, dataAtualMovimento, getUsuarioLogado(), getIdInstituicaoLogado(), idCanal);

        getLogger().debug("###### fim metodo criarMensagemDDABeneficiario ######");
    }

    private MensagemDDABeneficiarioConvenio criarMensagemDDABeneficiarioConvenio(MensagemDDABeneficiario mensagemDDABeneficiario, ConvenioCadastroDDADto convenio) {
        MensagemDDABeneficiarioConvenio mensagemDDABeneficiarioConvenio = new MensagemDDABeneficiarioConvenio();
        mensagemDDABeneficiarioConvenio.setNumAgencia(convenio.getNumAgencia());
        mensagemDDABeneficiarioConvenio.setNumConta(ObjectUtil.isEmpty(convenio.getNumConta()) ? null : convenio.getNumConta().longValue());
        mensagemDDABeneficiarioConvenio.setNumClienteOuConvenio(ObjectUtil.isEmpty(convenio.getCodClienteConvenio()) ? null : convenio.getCodClienteConvenio());
        mensagemDDABeneficiarioConvenio.setCodTipoProdutoDDA(convenio.getTipoProdutoConvenio().getCodDominio());
        mensagemDDABeneficiarioConvenio.setCodSituacaoConvenioDDA(convenio.getSitucaoConvenio().getCodDominio());
        mensagemDDABeneficiarioConvenio.setDataInicioRelacionamento(new DateTimeDB(convenio.getDataInicioConvenio().getTime()));
        mensagemDDABeneficiarioConvenio.setCodISPBParticipanteIncorporado(ObjectUtil.isNull(convenio.getIspbParticipanteIncorporado()) ? null : convenio
                .getIspbParticipanteIncorporado());
        mensagemDDABeneficiarioConvenio.setCodTipoOperacao(TipoManutencaoEnum.INCLUSAO.getCodIndicador());
        mensagemDDABeneficiarioConvenio.setMensagemDDABeneficiario(mensagemDDABeneficiario);

        return mensagemDDABeneficiarioConvenio;
    }

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
     * @param dto
     * @throws ComumNegocioException
     * @throws ComumException
     * 
     */
    private void validaDadosBeneficiario(CadastroBeneficiarioDDADto dto) throws ComumNegocioException {
        if (ObjectUtil.isNull(dto.getTipoPessoaBeneficiario())) {
            throw new ComumNegocioException(ERRO_TIPO_PESSOA_INVALIDO);
        } else if (ObjectUtil.isEmpty(dto.getCnpjCpfBeneficiario())) {
            throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
        } else if (dto.getTipoPessoaBeneficiario().equals(TipoPessoaEnum.PF)) {
            if (!new ValidacaoCpf(dto.getCnpjCpfBeneficiario(), null).validar()) {
                throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
            }
        } else if (dto.getTipoPessoaBeneficiario().equals(TipoPessoaEnum.PJ)) {
            if (!new ValidacaoCnpj(dto.getCnpjCpfBeneficiario(), null).validar()) {
                throw new ComumNegocioException(ERRO_CNPJ_CPF_BENEFICIARIO_INVALIDO);
            }
        }

        if (ObjectUtil.isEmpty(dto.getNomeRazaoSocialBeneficiario())) {
            throw new ComumNegocioException(ERRO_NOME_RAZAO_SOCIAL_BENEFICIARIO_INVALIDO);
        } else if (ObjectUtil.isNull(dto.getSituacaoBeneficiario())) {
            /*
             * FIXME: samuell.ramos - rafael.silva Avaliar remover validação pois no metodo processarReenvioMensagemCadastroBeneficiario o valor e imputado na
             * mão nunca será nulo
             */
            throw new ComumNegocioException(ERRO_SITUACAO_BENEFICIARIO_INVALIDA);
        } else if (ObjectUtil.isNull(dto.getDataHoraSituacaoBeneficiario())) {
            throw new ComumNegocioException(ERRO_DATA_HORA_SITUACAO_BENEFICIARIO_INVALIDA);
        } else if (ObjectUtil.isNull(dto.getSituacaoRelacionamentoParticipante())) {
            throw new ComumNegocioException(ERRO_SITUACAO_RELACIONAMENTO_PARTICIPANTE_INVALIDA);
        } else if (ObjectUtil.isNull(dto.getDataInicioRelacionamentoParticipante())) {
            /*
             * FIXME: samuell.ramos - rafael.silva Avaliar remover validação pois no metodo processarReenvioMensagemCadastroBeneficiario o valor e imputado na
             * mão nunca será nulo
             */
            throw new ComumNegocioException(ERRO_DATA_INICIO_RELACIONAMENTO_PARTICIPANTE_INVALIDA);
        } else if (ObjectUtil.isEmpty(dto.getListaConvenioCadastroDto())) {
            throw new ComumNegocioException(ERRO_GRUPO_CONVENIO_INVALIDO);
        } else {
            for (ConvenioCadastroDDADto convDto : dto.getListaConvenioCadastroDto()) {
                validarDadosConvenioCargaDDADto(convDto);
            }

            if (!ObjectUtil.isEmpty(dto.getListaRepresentanteBeneficiarioDto())) {
                for (RepresentanteBeneficiarioDto repDto : dto.getListaRepresentanteBeneficiarioDto()) {
                    validarDadosRepresentanteBeneficiarioDto(repDto);
                }
            }
        }
    }

    /**
     * Método responsável por verificar se o beneficiário ja foi cadastrado na base do DDA
     * 
     * @param cnpjCpfBeneficiario
     * @throws ComumException void
     * @throws ComumNegocioException
     * 
     */
    private void validaBeneficiarioJaCadastrado(String cnpjCpfBeneficiario) throws ComumNegocioException, ComumException {
        BeneficiarioDDA beneficiarioDDA = getDao().obterBeneficiarioDiferenteDeSemCadastro(cnpjCpfBeneficiario);
        if (!ObjectUtil.isNull(beneficiarioDDA) && !ObjectUtil.isEmpty(beneficiarioDDA.getId()) && beneficiarioDDA.getBolOrigemSicoob()) {
            throw new ComumNegocioException(ERRO_BENEFICARIO_JA_CADASTRADO);
        }
    }

    /**
     * Método responsável por validar os dados do Convenio
     * 
     * @param convDto
     * @throws ComumNegocioException void
     * 
     */
    private void validarDadosConvenioCargaDDADto(ConvenioCadastroDDADto convDto) throws ComumNegocioException {
        if (ObjectUtil.isNull(convDto.getDataInicioConvenio())) {
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
    private void validarDadosRepresentanteBeneficiarioDto(RepresentanteBeneficiarioDto repDto) throws ComumNegocioException {
        if (ObjectUtil.isNull(repDto.getTipoPessoaRepresentanteBeneficiario())) {
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
     * @return o atributo sciDelegate
     */
    public SCIDelegate getSciDelegate() {
        if (sciDelegate == null) {
            sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();
        }
        return sciDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return AlterarCadastroBeneficiarioDelegate
     * 
     */
    public AlterarCadastroBeneficiarioDelegate getAlterarCadastroBeneficiarioDelegate() {
        if (alterarCadastroBeneficiarioDelegate == null) {
            alterarCadastroBeneficiarioDelegate = OperacionalFabricaDelegates.getInstance().getAlterarCadastroBeneficiarioDelegate();
        }
        return alterarCadastroBeneficiarioDelegate;
    }

    public void setAlterarCadastroBeneficiarioDelegate(AlterarCadastroBeneficiarioDelegate alterarCadastroBeneficiarioDelegate) {
        this.alterarCadastroBeneficiarioDelegate = alterarCadastroBeneficiarioDelegate;
    }

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
    public BeneficiarioCipDao getDao() {
        return dao;
    }

    /**
     * Método responsável por
     * 
     * @return BeneficiarioLegadoDao
     * 
     */
    public BeneficiarioLegadoDao getBeneficiarioDao() {
        return beneficiarioDao;
    }

    /**
     * @return o atributo delegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (mensagemDDADelegate == null) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }

        return mensagemDDADelegate;
    }
}
