package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.CapesDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ParticipanteContaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.capes.PessoaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.RelatorioSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoOperacaoSicoobDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoTermoPagadorEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfiguraRelatorio;
import br.com.sicoob.sisbr.sicoobdda.comum.relatorio.ConfigurarRelatorioJasper;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoTermoPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAAceite;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDATerceiroAut;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTermoPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.TipoPessoaEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.FormatadorUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ContaCorrenteDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.MensagemDDAPagadorDelegate;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.delegates.OperacionalFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.PagadorEletronicoDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDALegadoDao;

/**
 * PagadorEletronicoDDAServicoEJB é responsável por fornecer serviços para os canais
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Local(PagadorEletronicoDDAServicoLocal.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PagadorEletronicoDDAServicoEJB extends OperacionalCrudServicoEJB<SicoobDDAEntidade> implements PagadorEletronicoDDAServicoLocal {

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private PagadorEletronicoDDALegadoDao daoSql;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private PagadorEletronicoDDADao dao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao boletoCipDao;

    private MensagemDDADelegate mensagemDDADelegate;

    private MensagemDDAPagadorDelegate mensagemDDAPagadorDelegate;

    private ADMDelegate admDelegate;

    private ContaCorrenteDelegate ccoDelegate;

    private SCIDelegate sciDelegate;

    private ContaCorrenteDelegate contaCorrenteDelegate;

    private CapesDelegate capesDelegate;

    private ConfigurarRelatorioJasper configurarRelatorio = null;

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    private static final char EXCLUIR_TERCEIRO = 'E';
    private static final char ALTERAR_TERCEIRO = 'A';
    private static final char INCLUIR_TERCEIRO = 'I';

    public static final int COD_TRANSACAO_TITULOS = 23;
    public static final int DETALHE_TITULOS = 1;
    public static final BigDecimal VALOR_LANCAMENTO = BigDecimal.ZERO;

    /*
     * 
     * 1 - DESCTERMOREDUZIDO;
     * 
     * 2 - DESCTERMOTXT;
     * 
     * 3 - DESCTERMOHTML;
     * 
     * Utilizado pelas transações dos canais. (SELECT DESCTERMOREDUZIDO, DESCTERMOTXT, DESCTERMOHTML FROM dbo.MODELOTERMODDA)
     */
    private static final Integer DESC_TERMO_HTML = 3;

    private static final String ERRO_CPF_CNPJ_DIFERENTE_TRANSACAO_RETORNO_CONTA_CORRENTE = "CPF/CNPJ do participante diferente do informado.";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#isCpfCnpjPagadorEletronico(java.lang.Short, java.lang.String)
     */
    public boolean isCpfCnpjPagadorEletronico(Character tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws ComumException, ComumNegocioException {
        if (ObjectUtil.isNull(tipoPessoa)) {
            throw new OperacionalException("integracaocip.parametro.obrigatorio", "tipo de pessoa");
        } else if (!tipoPessoa.equals(Constantes.TIPO_PESSOA_FISICA) && !tipoPessoa.equals(Constantes.TIPO_PESSOA_JURIDICA)) {
            throw new OperacionalException("integracaocip.tipo.pessoa.invalido");
        } else {
            validaCpfCnpj(cpfCnpj);
        }
        return getDao().isCpfCnpjPagadorEletronico(tipoPessoa, cpfCnpj, isPagadorSicoob);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#incluirAdesaoDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)
     */
    public TermoPagadorDto incluirAdesaoDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumException, ComumNegocioException {
        debug(" Inicio da Adesao do Pagador");
        debug("Número da Cooperativa: " + pagadorEletronicoDDADto.getNumCooperativa());
        debug("Número da Conta: " + pagadorEletronicoDDADto.getNumConta());
        debug("codigo Tipo Retorno: " + pagadorEletronicoDDADto.getTipoRetorno());
        debug("id do Canal: " + pagadorEletronicoDDADto.getCodCanal());
        debug("id Responsavel Titular: " + pagadorEletronicoDDADto.getIdRespTitular());
        debug("Número do CPF/CNPJ vindo do canal: " + pagadorEletronicoDDADto.getNumCpfCnpj());

        PagadorDto pagadorDTO = obterCpfCnpjPeloNumeroConta(pagadorEletronicoDDADto.getNumCooperativa(), pagadorEletronicoDDADto.getNumConta().longValue(),
                pagadorEletronicoDDADto.getIdRespTitular(), pagadorEletronicoDDADto.getNumCpfCnpj());

        pagadorDTO = solicitarAdesao(pagadorDTO, pagadorEletronicoDDADto.getCodCanal().shortValue(), Boolean.TRUE);

        TermoPagadorDto termoPagadorDto = null;

        termoPagadorDto = obterTermoPagador(pagadorEletronicoDDADto.getTipoRetorno(), pagadorDTO, termoPagadorDto, pagadorEletronicoDDADto.getNumCooperativa());

        return termoPagadorDto;

    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#cancelarAdesaoDDa(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)
     */
    public TermoPagadorDto cancelarAdesaoDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumException, ComumNegocioException {
        debug(" Inicio do cancelamento do Pagador");
        debug("Número da Cooperativa: " + pagadorEletronicoDDADto.getNumCooperativa());
        debug("Número da Conta: " + pagadorEletronicoDDADto.getNumConta());
        debug("codigo Tipo Retorno: " + pagadorEletronicoDDADto.getTipoRetorno());
        debug("id do Canal: " + pagadorEletronicoDDADto.getCodCanal());
        debug("id Responsavel Titular: " + pagadorEletronicoDDADto.getIdRespTitular());
        debug("Número do CPF/CNPJ vindo do canal: " + pagadorEletronicoDDADto.getNumCpfCnpj());

        PagadorDto pagadorDTO = obterCpfCnpjPeloNumeroConta(pagadorEletronicoDDADto.getNumCooperativa(), pagadorEletronicoDDADto.getNumConta().longValue(),
                pagadorEletronicoDDADto.getIdRespTitular(), pagadorEletronicoDDADto.getNumCpfCnpj());

        pagadorDTO = solicitarCancelamentoAdesao(pagadorDTO, pagadorEletronicoDDADto.getCodCanal().shortValue(), Boolean.TRUE);

        TermoPagadorDto termoPagadorDto = null;

        termoPagadorDto = obterTermoPagador(pagadorEletronicoDDADto.getTipoRetorno(), pagadorDTO, termoPagadorDto, pagadorEletronicoDDADto.getNumCooperativa());

        return termoPagadorDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#obterTermoAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)
     */
    public TermoPagadorDto obterTermoAdesaoPeloTipo(Short codTipoTermoPagador, Integer numCooperativa, Integer codTipoRetorno) throws ComumException, ComumNegocioException {
        return obterTermoPagadorEletronico(new DateTimeDB(), null, codTipoTermoPagador, codTipoRetorno.equals(DESC_TERMO_HTML), numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#obterTermoAdesaoPeloSacado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)
     */
    public TermoPagadorDto obterTermoAdesaoPeloSacado(Integer idHistoricoTermoPagador, Long numContaCorrente, Integer numCooperativa, Integer idResponsavelTitular,
            String numCpfCnpj)
            throws ComumException, ComumNegocioException {
        debug(" Inicio da transação do Termo de Adesao pelo pagador");
        debug("idHistoricoTermoPagador: " + idHistoricoTermoPagador);
        debug("Número da Conta: " + numContaCorrente);
        debug("numCooperativa: " + numCooperativa);
        debug("id Responsavel Titular: " + idResponsavelTitular);
        debug("Número do CPF/CNPJ vindo do canal: " + numCpfCnpj);

        String numCpfCnpjPagador = obterNumCpfCnpjPagador(numContaCorrente, obterIdInstituicao(numCooperativa), idResponsavelTitular);

        if (ObjectUtil.isNull(idResponsavelTitular) || !ObjectUtil.isNull(numCpfCnpj) && numCpfCnpjPagador.equals(numCpfCnpj)) {
            HistoricoTermoPagador historicoTermoPagador = getDao().obterHistoricoPagadorEletronicoPeloCpfCnpj(idHistoricoTermoPagador, numCpfCnpjPagador,
                    obterIdInstituicao(numCooperativa));

            return obterTermoPagadorEletronico(historicoTermoPagador.getDataHoraTermoDDA(), null, historicoTermoPagador.getTipoTermoPagador().getCodTipoTermoPagador(),
                    Boolean.FALSE, numCooperativa);
        } else {
            debug("Número do CPF/CNPJ vindo do CANAL = " + numCpfCnpj + " diferente do retorno do CAPES - " + numCpfCnpjPagador);
            throw new ComumNegocioException(ERRO_CPF_CNPJ_DIFERENTE_TRANSACAO_RETORNO_CONTA_CORRENTE);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#comprovanteAdesaoDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)
     */
    public List<PagadorEletronicoDDADto> listarComprovanteAdesaoDDA(Long numContaCorrente, Integer numCooperativa, Integer idResponsavelTitular, String numCpfCnpj)
            throws ComumException, ComumNegocioException {
        debug(" Inicio da transação Listar Comprovantes do Pagador");
        debug("Número da Conta: " + numContaCorrente);
        debug("numCooperativa: " + numCooperativa);
        debug("id Responsavel Titular: " + idResponsavelTitular);
        debug("Número do CPF/CNPJ vindo do canal: " + numCpfCnpj);

        String numCpfCnpjPagador = obterNumCpfCnpjPagador(numContaCorrente, obterIdInstituicao(numCooperativa), idResponsavelTitular);

        if (ObjectUtil.isEmpty(idResponsavelTitular) || !ObjectUtil.isNull(numCpfCnpj) && numCpfCnpjPagador.equals(numCpfCnpj)) {
            return getDao().listarComprovanteAdesaoDDA(numCpfCnpjPagador);
        } else {
            debug("Número do CPF/CNPJ vindo do CANAL = " + numCpfCnpj + " diferente do retorno do CAPES - " + numCpfCnpjPagador);
            throw new ComumNegocioException(ERRO_CPF_CNPJ_DIFERENTE_TRANSACAO_RETORNO_CONTA_CORRENTE);
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#indicadorAceiteDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto)
     */
    public Boolean indicadorAceiteDDA(PagadorEletronicoDDADto pagadorEletronicoDDADto) throws ComumException {
        MensagemDDAAceite mensagemAceite = new MensagemDDAAceite();
        // NumIdentDDA e a mesma coisa do numero de indentificacao do titulo
        mensagemAceite.setNumIdentificadorBoletoCip(pagadorEletronicoDDADto.getNumIdentDDA().longValue());
        mensagemAceite.setBolAceite(pagadorEletronicoDDADto.getIndAceite().equalsIgnoreCase("S"));
        BoletoDDA boletoDDA = boletoCipDao.obterBoletoDDA(pagadorEletronicoDDADto.getNumIdentDDA().longValue());
        if (!ObjectUtil.isNull(boletoDDA) && !ObjectUtil.isNull(boletoDDA.getNumRefAtualCadAceite()) && !ObjectUtil.isNull(boletoDDA.getNumSeqAtualAceite())) {
            mensagemAceite.setNumRefAtualAceite(boletoDDA.getNumRefAtualCadAceite());
            mensagemAceite.setNumSeqAtualAceite(boletoDDA.getNumSeqAtualAceite());
        }

        Date dataMovimento = getAdmDelegate().obterDataMovimentoBancoob();
        getMensagemDDADelegate().incluir(mensagemAceite, TipoMensagemDDA.DDA0104, DateUtil.getDateTimeDB(dataMovimento), getUsuarioLogado(),
                obterIdInstituicao(pagadorEletronicoDDADto.getNumCooperativa()),
                ObjectUtil.isNull(pagadorEletronicoDDADto.getCodCanal()) ? null : pagadorEletronicoDDADto.getCodCanal().shortValue());

        return Boolean.TRUE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#manterDDATerceiro(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DDATerceiroDto)
     */
    public boolean manterDDATerceiro(DDATerceiroDto dto) throws ComumException, ComumNegocioException {
        debug("### Manter DDA terceiro...");
        ObjectUtil.log(dto);

        validarManterDDATerceiro(dto);

        incluirMensagemDDATerceiroAutorizado(dto);
        return Boolean.TRUE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#atualizarSacadoEletronico(java.lang.Integer)
     */
    public void atualizarSacadoEletronico(Integer numCooperativa) throws ComumException {
        getDaoSql().atualizarSacadoEletronico(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#obterDadosPagador(java.lang.String)
     */
    public PagadorDto obterDadosPagador(String numCpfCnpj, Integer idInstituicao) throws ComumNegocioException, ComumException {
        validaCpfCnpj(numCpfCnpj);
        return new PagadorDto(dao.isCpfCnpjPagadorEletronico(numCpfCnpj, Boolean.TRUE), getCCODelegate().listarNumContaCorrenteAtiva(idInstituicao, numCpfCnpj),
                dao.obterPagadorAgregadoDDA(numCpfCnpj), dao.obterMensagemPendentePagadorDDA(numCpfCnpj), dao.listarHistoricoPagadorEletronico(numCpfCnpj, idInstituicao));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#solicitarAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto)
     */
    public PagadorDto solicitarAdesao(PagadorDto pagadorDto, Short idCanal, Boolean isRetornoTermo) throws ComumNegocioException, ComumException {
        debug("### Solicitando adesão...");

        if (dao.isCpfCnpjPagadorEletronico(pagadorDto.getNumCpfCnpj(), Boolean.TRUE)) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.possui.adesao");
        } else if (dao.existeSolicitacaoAdesao(pagadorDto.getNumCpfCnpj())) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.solicitacao.existente", "Adesão");
        } else if (!ObjectUtil.isEmpty(pagadorDto.getListaPagadorAgregado())) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.possui.agregado");
        }
        return gravarSolicitacaoPagador(pagadorDto, TipoMensagemDDA.DDA0001, Boolean.TRUE, TipoTermoPagadorEnum.ADESAO_DDA.getValor(), idCanal, isRetornoTermo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#solicitarCancelamentoAdesao(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto)
     */
    public PagadorDto solicitarCancelamentoAdesao(PagadorDto pagadorDto, Short idCanal, Boolean isRetornoTermo) throws ComumNegocioException, ComumException {
        debug("### Solicitando cancelamento da adesão...");

        if (!dao.isCpfCnpjPagadorEletronico(pagadorDto.getNumCpfCnpj(), Boolean.TRUE)) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.nao.possui.adesao");
        } else if (dao.existeSolicitacaoCancelamentoAdesao(pagadorDto.getNumCpfCnpj())) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.solicitacao.existente", "Cancelar Adesão");
        }
        return gravarSolicitacaoPagador(pagadorDto, TipoMensagemDDA.DDA0006, Boolean.FALSE, TipoTermoPagadorEnum.CANCELAR_ADESAO_DDA.getValor(), idCanal, isRetornoTermo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#solicitarInclusaoPagadorAgregado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto,
     *      java.lang.String, java.lang.Integer)
     */
    public PagadorDto solicitarInclusaoPagadorAgregado(PagadorAgregadoDto pagadorAgregadoDto, String numCpfCnpjPagador, Integer idInstituicao, Short idCanal)
            throws ComumException, ComumNegocioException {
        debug("### Solicitando inclusão do pagador...");

        validaSolicitacaoAgregado(numCpfCnpjPagador, pagadorAgregadoDto.getNumCpfCnpj(), TipoOperacaoSicoobDDAEnum.INCLUSAO);
        List<PagadorAgregadoDto> listaPagadorAgregado = new ArrayList<PagadorAgregadoDto>();
        listaPagadorAgregado.add(pagadorAgregadoDto);

        return gravarSolicitacaoAgregado(pagadorAgregadoDto, numCpfCnpjPagador, idInstituicao, TipoOperacaoSicoobDDAEnum.INCLUSAO, TipoTermoPagadorEnum.INCLUIR_AGREGADO.getValor(),
                idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#solicitarExclusaoPagadorAgregado(java.util.List,
     *      java.lang.String, java.lang.Integer)
     */
    public PagadorDto solicitarExclusaoPagadorAgregado(PagadorAgregadoDto pagadorAgregadoDto, String numCpfCnpjPagador, Integer idInstituicao, Short idCanal)
            throws ComumException, ComumNegocioException {
        debug("### Solicitando exclusão do pagador...");

        validaSolicitacaoAgregado(numCpfCnpjPagador, pagadorAgregadoDto.getNumCpfCnpj(), TipoOperacaoSicoobDDAEnum.EXCLUSAO);

        return gravarSolicitacaoAgregado(pagadorAgregadoDto, numCpfCnpjPagador, idInstituicao, TipoOperacaoSicoobDDAEnum.EXCLUSAO, TipoTermoPagadorEnum.EXCLUIR_AGREGADO.getValor(),
                idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#listarHistoricoPagadorEletronico(java.lang.String,
     *      java.lang.Integer)
     */
    public List<HistoricoPagadorEletronicoDto> listarHistoricoPagadorEletronico(String numCpfCnpjPagador, Integer idInstituicao) throws OperacionalException {
        return dao.listarHistoricoPagadorEletronico(numCpfCnpjPagador, idInstituicao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#obterTermoPagadorEletronico(br.com.bancoob.persistencia.types.DateTimeDB,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short, java.lang.Boolean)
     */
    public TermoPagadorDto obterTermoPagadorEletronico(DateTimeDB dataInicioVigencia, DateTimeDB dataFimVigencia, Short codTipoTermoPagador, Boolean bolFormatoHtml,
            Integer numCooperativa) throws OperacionalException {
        return dao.obterTermoPagadorEletronico(dataInicioVigencia, dataFimVigencia, codTipoTermoPagador, bolFormatoHtml, numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#obterDadosPagador(java.lang.String)
     */
    public PagadorDto obterDadosPagador(String numCpfCnpj) throws ComumException {
        PagadorDto pagadorDto = getDao().obterDadosPagador(numCpfCnpj);
        if (!ObjectUtil.isNull(pagadorDto)) {
            List<PagadorAgregadoDto> listaPagadorAgregado = getDao().listarAgregadosPorIdPagador(pagadorDto.getIdPagador());
            pagadorDto.setListaAgencia(getDao().listarAgencia(pagadorDto.getIdPagador()));
            pagadorDto.setListaPagadorAgregado(listaPagadorAgregado);
        }
        return pagadorDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#relatorioListarPagadorAgregado(java.lang.String,
     *      java.lang.Integer, java.lang.Integer)
     */
    public void relatorioListarPagadorAgregado(String numCpfCnpj, Integer numAgencia, Integer codCentral) throws ComumException {
        List<PagadorAgregadoDto> listaAgregadosDto = getDao().listarPagadorAgregado(numCpfCnpj, numAgencia);
        ConfiguraRelatorio configRelatorio = new ConfiguraRelatorio(RelatorioSicoobDDAEnum.SDDA509, listaAgregadosDto);
        configRelatorio.setParametro("codCentral", codCentral);
        configRelatorio.setParametro("codSingular", numAgencia);
        configRelatorio.setParametro("numCpfCnpj", numCpfCnpj);
        configRelatorio.gerarRelatorioSinc();
    }

    /**
     * Método responsável por obter o Numero do CPF ou CNPJ do pagador de acordo com o numero da conta corrente e a cooperativa
     * 
     * @param numContaCorrente
     * @param idResponsavelTitular
     * @param numCooperativa
     * @return
     * @throws ComumException String
     * 
     */
    private String obterNumCpfCnpjPagador(Long numContaCorrente, Integer idInstituicao, Integer idResponsavelTitular) throws ComumException {
        List<ParticipanteContaDto> listaParticipanteContaDto = getContaCorrenteDelegate().listarParticipanteConta(idInstituicao, numContaCorrente);
        String numCpfCnpjPagador = null;
        for (ParticipanteContaDto participanteContaDto : listaParticipanteContaDto) {
            if (!ObjectUtil.isNull(idResponsavelTitular) && idResponsavelTitular == participanteContaDto.getNumOrdemTitularidade()) {
                PessoaDto pessoaDto = getCapesDelegate().obterPessoa(participanteContaDto.getIdPessoa(), idInstituicao);

                numCpfCnpjPagador = pessoaDto.getCpfCnpj();
                debug("CPF/CNPJ recuperado do CAPES: " + numCpfCnpjPagador);
                break;
            }

            PessoaDto pessoaDto = getCapesDelegate().obterPessoa(participanteContaDto.getIdPessoa(), idInstituicao);

            numCpfCnpjPagador = pessoaDto.getCpfCnpj();
            debug("CPF/CNPJ recuperado do CAPES: " + numCpfCnpjPagador);
        }
        return numCpfCnpjPagador;
    }

    /**
     * Método responsável por validar as informações
     * 
     * @param dto
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void validarManterDDATerceiro(DDATerceiroDto dto) throws ComumException, ComumNegocioException {
        if (boletoCipDao.existeSolicitacaoTerceiroAutorizadoEmAndamento(dto.getNumCpfCnpj(), dto.getNumIdentDDA())) {
            throw new ComumNegocioException("integracaocip.terceiro.autorizado.existe.mensagem.cpf.cnpj", formatarCpfCnpj(dto.getNumCpfCnpj()));
        } else if (dto.getIndTerceiro().equals(INCLUIR_TERCEIRO)) {
            // Se não for um pagador eletrônico
            if (!getDao().isCpfCnpjPagadorEletronico(dto.getTipoPessoaTerc(), dto.getNumCpfCnpj(), Boolean.FALSE)) {
                throw new ComumNegocioException("integracaocip.cpf.cnpj.nao.pagador.eletronico", formatarCpfCnpj(dto.getNumCpfCnpj()));
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param numConta
     * @param numCpfCNPJ
     * @param idResponsavelTitular
     * @return
     * @throws ComumException PagadorDto
     * 
     */
    private PagadorDto obterCpfCnpjPeloNumeroConta(Integer numCooperativa, Long numConta, Integer idResponsavelTitular, String numCpfCnpj)
            throws ComumException, ComumNegocioException {
        Integer idInstituicao = obterIdInstituicao(numCooperativa);
        String numCpfCnpjPagador = obterNumCpfCnpjPagador(numConta, idInstituicao, idResponsavelTitular);

        if (ObjectUtil.isNull(idResponsavelTitular) || !ObjectUtil.isNull(numCpfCnpj) && numCpfCnpjPagador.equals(numCpfCnpj)) {
            PagadorDto pagadorDTO = new PagadorDto();
            pagadorDTO.setNumCpfCnpj(numCpfCnpjPagador);
            pagadorDTO.setIdInstituicao(idInstituicao);

            pagadorDTO.setListaNumCCO(new ArrayList<String>());
            pagadorDTO.getListaNumCCO().add(numConta.toString());
            return pagadorDTO;
        } else {
            debug("Número do CPF/CNPJ vindo do CANAL = " + numCpfCnpj + " diferente do retorno do CAPES - " + numCpfCnpjPagador);
            throw new ComumNegocioException(ERRO_CPF_CNPJ_DIFERENTE_TRANSACAO_RETORNO_CONTA_CORRENTE);
        }

    }

    /**
     * Método responsável por incluir a mensagem
     * 
     * 
     * @param dto
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void incluirMensagemDDATerceiroAutorizado(DDATerceiroDto dto) throws ComumException, ComumNegocioException {
        debug("### Incluíndo mensagemDDA terceiro autorizado...");

        MensagemDDATerceiroAut mensagemTerceiro = new MensagemDDATerceiroAut();
        mensagemTerceiro.setNumIdentificadorBoletoCip(dto.getNumIdentDDA());

        mensagemTerceiro.setCodTipoPessoaTerceiro(dto.getTipoPessoaTerc().toString());
        mensagemTerceiro.setNumCpfCnpjTerceiro(dto.getNumCpfCnpj());

        String cpfCnpjPagadorEletronico = boletoCipDao.obterCpfCnpjPagadorEletronico(mensagemTerceiro.getNumIdentificadorBoletoCip());
        debug("CPF/CNPJ do Pagador Eletrônico: " + cpfCnpjPagadorEletronico);

        if (ObjectUtil.isEmpty(cpfCnpjPagadorEletronico)) {
            throw new ComumNegocioException("identificador.boleto.dda.nao.encontrado", mensagemTerceiro.getNumIdentificadorBoletoCip());
        }

        mensagemTerceiro.setCodTipoPessoaAutorizador(
                cpfCnpjPagadorEletronico.length() == Constantes.TAMANHO_CNPJ ? TipoPessoaEnum.PJ.getCodDominioCip() : TipoPessoaEnum.PF.getCodDominioCip());
        mensagemTerceiro.setNumCpfCnpjAutorizador(cpfCnpjPagadorEletronico);

        Date dataMovimento = getAdmDelegate().obterDataMovimentoBancoob();
        debug("Data de movimento: " + dataMovimento);

        debug("IndTerceiro: " + dto.getIndTerceiro());

        if (dto.getIndTerceiro().equals(INCLUIR_TERCEIRO) || dto.getIndTerceiro().equals(ALTERAR_TERCEIRO)) {
            validaTerceiroAutoExitente(dto.getNumCpfCnpj(), dto.getNumIdentDDA());
            getMensagemDDADelegate().incluir(mensagemTerceiro, TipoMensagemDDA.DDA0121, DateUtil.getDateTimeDB(dataMovimento), getUsuarioLogado(),
                    obterIdInstituicao(dto.getNumCooperativa()), ObjectUtil.isNull(dto.getCodCanal()) ? null : dto.getCodCanal().shortValue());
        } else if (dto.getIndTerceiro().equals(EXCLUIR_TERCEIRO)) {
            validaTerceiroAutoNaoExitente(dto.getNumCpfCnpj(), dto.getNumIdentDDA());
            getMensagemDDADelegate().incluir(mensagemTerceiro, TipoMensagemDDA.DDA0122, DateUtil.getDateTimeDB(dataMovimento), getUsuarioLogado(),
                    obterIdInstituicao(dto.getNumCooperativa()), ObjectUtil.isNull(dto.getCodCanal()) ? null : dto.getCodCanal().shortValue());
        }
    }

    /**
     * @param numCpfCnpjTerceiro
     * @param numIdentTitulo
     * @throws OperacionalNegocionException
     * @throws ComumException void
     * 
     */
    private void validaTerceiroAutoExitente(String numCpfCnpjTerceiro, Long numIdentTitulo) throws OperacionalNegocionException, ComumException {
        if (boletoCipDao.existeTerceiroAutorizadoAtivo(numCpfCnpjTerceiro, numIdentTitulo)) {
            throw new OperacionalNegocionException("integracaocip.terceiro.autorizado.existente.boleto", formatarCpfCnpj(numCpfCnpjTerceiro));
        }
    }

    /**
     * @param numCpfCnpjTerceiro
     * @param numIdentTitulo
     * @throws OperacionalNegocionException
     * @throws ComumException void
     * 
     */
    private void validaTerceiroAutoNaoExitente(String numCpfCnpjTerceiro, Long numIdentTitulo) throws OperacionalNegocionException, ComumException {
        if (!boletoCipDao.existeTerceiroAutorizadoAtivo(numCpfCnpjTerceiro, numIdentTitulo)) {
            throw new OperacionalNegocionException("integracaocip.terceiro.autorizado.nao.existente.boleto", formatarCpfCnpj(numCpfCnpjTerceiro));
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @return
     * @throws ComumException Integer
     * 
     */
    private Integer obterIdInstituicao(Integer numCooperativa) throws ComumException {
        return getSciDelegate().obterInstituicaoPorCooperativaCache(numCooperativa).getIdInstituicao();
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDto
     * @param codTipoMensagemDDA
     * @param bolPagadorEletronico
     * @return
     * @throws ComumException
     * @throws ComumNegocioException PagadorDto
     * 
     */
    private PagadorDto gravarSolicitacaoPagador(PagadorDto pagadorDto, String codTipoMensagemDDA, Boolean bolPagadorEletronico, Short codTipoTermo, Short idCanal,
            Boolean isRetornoTermo) throws ComumException, ComumNegocioException {
        getMensagemDDAPagadorDelegate().incluirMensagemPagador(pagadorDto.getNumCpfCnpj(), pagadorDto.getIdInstituicao(), pagadorDto.getListaNumCCO(), bolPagadorEletronico,
                codTipoMensagemDDA, idCanal);
        debug("### Gravando Solicitacao do pagador na Historico Termo Pagador...");
        em.persist(montarHistoricoTermoPagador(null, pagadorDto.getNumCpfCnpj(), codTipoTermo, idCanal));

        if (isRetornoTermo) {
            pagadorDto.setListaHistPagadorEletronico(dao.listarHistoricoPagadorEletronico(pagadorDto.getNumCpfCnpj(), pagadorDto.getIdInstituicao()));
            return pagadorDto;
        }
        return obterDadosPagador(pagadorDto.getNumCpfCnpj(), pagadorDto.getIdInstituicao());
    }

    /**
     * Método responsável por
     * 
     * @param listaPagadorAgregado
     * @param numCpfCnpjPagador
     * @param idInstituicao
     * @param tipoOperacao
     * @return
     * @throws ComumNegocioException
     * @throws ComumException PagadorDto
     * 
     */
    private PagadorDto gravarSolicitacaoAgregado(PagadorAgregadoDto pagadorAgregadoDto, String numCpfCnpjPagador, Integer idInstituicao, TipoOperacaoSicoobDDAEnum tipoOperacao,
            Short codTipoTermo, Short idCanal) throws ComumNegocioException, ComumException {
        List<PagadorAgregadoDto> listaPagadorAgregado = new ArrayList<PagadorAgregadoDto>();
        listaPagadorAgregado.add(pagadorAgregadoDto);
        getMensagemDDAPagadorDelegate().incluirMensagemPagadorAgregado(listaPagadorAgregado, numCpfCnpjPagador, idInstituicao, tipoOperacao, idCanal);
        debug("### Gravando Solicitacao do pagador na Historico Termo Pagador...");
        em.persist(montarHistoricoTermoPagador(pagadorAgregadoDto.getNumCpfCnpj(), numCpfCnpjPagador, codTipoTermo, CanalEnum.RETAGUARDA.getId()));
        return obterDadosPagador(numCpfCnpjPagador, idInstituicao);
    }

    /**
     * @param numCpfCnpjAgregado
     * @param numCpfCnpjPagador
     */
    private HistoricoTermoPagador montarHistoricoTermoPagador(String numCpfCnpjAgregado, String numCpfCnpjPagador, Short codTipoTermoPagador, Short idCanal) {
        HistoricoTermoPagador histTermoPagador = new HistoricoTermoPagador(new DateTimeDB(), ObjectUtil.isNull(idCanal) ? CanalEnum.RETAGUARDA.getId() : idCanal,
                numCpfCnpjAgregado, numCpfCnpjPagador);
        histTermoPagador.setTipoTermoPagador(new TipoTermoPagador(codTipoTermoPagador));
        return histTermoPagador;
    }

    /**
     * Método responsável por
     * 
     * @param cpfCnpj
     * @throws ComumException
     * @throws ComumNegocioException void
     * 
     */
    private void validaCpfCnpj(String cpfCnpj) throws ComumException, ComumNegocioException {
        if (ObjectUtil.isEmpty(cpfCnpj)) {
            throw new ComumException("integracaocip.parametro.obrigatorio", "CPF/CNPJ");
        } else if (cpfCnpj.length() != Constantes.TAMANHO_CPF && cpfCnpj.length() != Constantes.TAMANHO_CNPJ) {
            throw new ComumNegocioException("integracaocip.cpf.cnpj.tamanho.invalido");
        }
    }

    /**
     * Método responsável por validar o cpfCnpj
     * 
     * @param numCpfCnpjPagador
     * @param numCpfCnpjAgregado
     * @throws ComumException
     * @throws ComumNegocioException
     */
    private void validaSolicitacaoAgregado(String numCpfCnpjPagador, String numCpfCnpjAgregado, TipoOperacaoSicoobDDAEnum tipoOperacao)
            throws ComumException, ComumNegocioException {
        if (dao.existeSolicitacaoCancelamentoAdesao(numCpfCnpjPagador)) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.agregado.solicitacao.existente", formatarCpfCnpj(numCpfCnpjPagador));
        } else if (dao.existeSolicitacaoAgregado(numCpfCnpjPagador, numCpfCnpjAgregado)) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.agregado.solicitacao.existente", formatarCpfCnpj(numCpfCnpjAgregado));
        } else if (tipoOperacao.equals(TipoOperacaoSicoobDDAEnum.INCLUSAO) && dao.agregadoJaCadastrado(numCpfCnpjPagador, numCpfCnpjAgregado)) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.agregado.existente");
        }
    }

    /**
     * Método responsável por
     * 
     * @param codTipoRetorno
     * @param pagadorDTO
     * @param termoPagadorDto
     * @return
     * @throws OperacionalException TermoPagadorDto
     * 
     */
    private TermoPagadorDto obterTermoPagador(Integer codTipoRetorno, PagadorDto pagadorDTO, TermoPagadorDto termoPagadorDto, Integer numCooperativa) throws OperacionalException {
        if (!ObjectUtil.isEmpty(pagadorDTO.getListaHistPagadorEletronico())) {
            for (HistoricoPagadorEletronicoDto historicoPagadorEletronicoDto : pagadorDTO.getListaHistPagadorEletronico()) {
                termoPagadorDto = obterTermoPagadorEletronico(historicoPagadorEletronicoDto.getDataHoraTermoDDA(), null, historicoPagadorEletronicoDto.getCodTipoTermoPagador(),
                        codTipoRetorno.equals(DESC_TERMO_HTML), numCooperativa);
                validaPagadorAgregado(pagadorDTO, termoPagadorDto, historicoPagadorEletronicoDto);
            }
        }
        return termoPagadorDto;
    }

    /**
     * Método responsável por
     * 
     * @param pagadorDTO
     * @param termoPagadorDto
     * @param historicoPagadorEletronicoDto void
     * 
     */
    private void validaPagadorAgregado(PagadorDto pagadorDTO, TermoPagadorDto termoPagadorDto, HistoricoPagadorEletronicoDto historicoPagadorEletronicoDto) {
        if (historicoPagadorEletronicoDto.getCodTipoTermoPagador().equals(TipoTermoPagadorEnum.INCLUIR_AGREGADO.getValor())
                || historicoPagadorEletronicoDto.getCodTipoTermoPagador().equals(TipoTermoPagadorEnum.EXCLUIR_AGREGADO.getValor())) {
            termoPagadorDto.getDescConteudoTermoPagador().replaceAll("@ASSOCIADO_AGREGADO", pagadorDTO.getNomePessoaAgregado());
            termoPagadorDto.getDescConteudoTermoPagador().replaceAll("@CPF_CNPJ_AGREGADO", pagadorDTO.getNumCpfCnpjAgregado());
        }
    }

    /**
     * Método responsável por formatar o CPF/CNPJ
     * 
     * @param cpfCnpj
     * @return
     */
    private String formatarCpfCnpj(String cpfCnpj) {
        return ObjectUtil.isEmpty(cpfCnpj) ? cpfCnpj
                : (FormatadorUtil.formatar(cpfCnpj, cpfCnpj.length() == Constantes.TAMANHO_CPF ? FormatadorUtil.MASCARA_CPF : FormatadorUtil.MASCARA_CNPJ));
    }

    /**
     * @return o atributo mensagemDDADelegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (ObjectUtil.isNull(mensagemDDADelegate)) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }

    /**
     * @return o atributo admDelegate
     */
    private ADMDelegate getAdmDelegate() {
        if (ObjectUtil.isNull(admDelegate)) {
            admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
        }
        return admDelegate;
    }

    /**
     * Método responsável por setEm para testes
     * 
     * @param em
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /*   *//**
            * Método responsável por
            * 
            * @return PagadorEletronicoDDALegadoDao
            */

    public PagadorEletronicoDDALegadoDao getDaoSql() {
        return daoSql;
    }

    /**
     * Método responsável por
     * 
     * @param daoSql
     */
    public void setDaoSql(PagadorEletronicoDDALegadoDao daoSql) {
        this.daoSql = daoSql;
    }

    /**
     * Método responsável por
     * 
     * @return PagadorEletronicoDDADao
     */
    public PagadorEletronicoDDADao getDao() {
        return dao;
    }

    /**
     * Método responsável por
     * 
     * @param dao
     */
    public void setDao(PagadorEletronicoDDADao dao) {
        this.dao = dao;
    }

    /**
     * Método responsável por
     * 
     * @return ParametroDao
     */
    public ParametroDao getParametroDAO() {
        return parametroDAO;
    }

    /**
     * Método responsável por
     * 
     * @param parametroDAO
     */
    public void setParametroDAO(ParametroDao parametroDAO) {
        this.parametroDAO = parametroDAO;
    }

    /**
     * Método responsável por
     * 
     * @return ContaCorrenteDelegate
     * 
     */
    private ContaCorrenteDelegate getCCODelegate() {
        if (ccoDelegate == null) {
            ccoDelegate = IntegracaoInternaFabricaDelegates.getInstance().getContaCorrenteDelegate();
        }
        return ccoDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return MensagemDDAPagadorDelegate
     * 
     */
    private MensagemDDAPagadorDelegate getMensagemDDAPagadorDelegate() {
        if (mensagemDDAPagadorDelegate == null) {
            mensagemDDAPagadorDelegate = OperacionalFabricaDelegates.getInstance().getMensagemDDAPagadorDelegate();
        }
        return mensagemDDAPagadorDelegate;
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
     * @return o atributo capesDelegate
     */
    public CapesDelegate getCapesDelegate() {
        if (ObjectUtil.isNull(capesDelegate)) {
            capesDelegate = IntegracaoInternaFabricaDelegates.getInstance().getCapesDelegate();
        }
        return capesDelegate;
    }

    /**
     * Método responsável por
     * 
     * @return ContaCorrenteDelegate
     * 
     */
    public ContaCorrenteDelegate getContaCorrenteDelegate() {
        if (ObjectUtil.isNull(contaCorrenteDelegate)) {
            contaCorrenteDelegate = IntegracaoInternaFabricaDelegates.getInstance().getContaCorrenteDelegate();
        }

        return contaCorrenteDelegate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected PagadorEletronicoDDADao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#configurarRelatorioPagadorAgregado(java.lang.String,
     *      java.lang.Integer, java.lang.Integer, br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioPagadorAgregado(String numCpfCnpj, Integer numCooperativa, Integer codCentral, UsuarioBancoobDTO usuario)
            throws BancoobException {

        List<PagadorAgregadoDto> listaAgregadosDto = getDao().listarPagadorAgregado(numCpfCnpj, numCooperativa);

        if (ObjectUtil.isEmpty(listaAgregadosDto)) {
            throw new NegocioException("operacional.relatorio.erro.dados.nulos");
        }

        configurarRelatorio = new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA509, listaAgregadosDto, usuario, null);
        configurarRelatorio.setParametro("codCentral", codCentral);
        configurarRelatorio.setParametro("codSingular", numCooperativa);
        configurarRelatorio.setParametro("numCpfCnpj", numCpfCnpj);

        return configurarRelatorio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#configurarRelatorioDetalhePagadorEletronico(java.lang.String,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioDetalhePagadorEletronico(String numCpfCnpj, UsuarioBancoobDTO usuario) throws BancoobException {

        PagadorDto pagadorDto = obterDadosPagador(numCpfCnpj);

        if (ObjectUtil.isNull(pagadorDto)) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.nao.encontrado");
        }

        configurarRelatorio = new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA508, pagadorDto.getListaPagadorAgregado(), usuario, null);
        configurarRelatorio.setParametro("pagadorDto", pagadorDto);

        return configurarRelatorio;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#configurarRelatorioListaPagadorEletronico(java.lang.Integer,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioListaPagadorEletronico(Integer numCooperativa, UsuarioBancoobDTO usuario) throws BancoobException {
        List<PagadorDto> listaPagadorDto = getDao().listarPagadorEletronico(numCooperativa);

        if (ObjectUtil.isEmpty(listaPagadorDto)) {
            throw new ComumNegocioException("integracaocip.pagador.eletronico.nao.encontrado");
        }

        return new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA507, listaPagadorDto, usuario, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.PagadorEletronicoDDAServico#configurarRelatorioTermoPagadorEletronico(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto,
     *      br.com.sicoob.relatorio.api.dto.UsuarioBancoobDTO)
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public ConfigurarRelatorioJasper configurarRelatorioTermoPagadorEletronico(HistoricoPagadorEletronicoDto historicoPagdorEletroDto, UsuarioBancoobDTO usuario)
            throws BancoobException {

        TermoPagadorDto termoPagadorDto = obterTermoPagadorEletronico(new DateTimeDB(), new DateTimeDB(), historicoPagdorEletroDto.getCodTipoTermoPagador(), Boolean.TRUE,
                Integer.parseInt(usuario.getCooperativa()));

        configurarRelatorio = new ConfigurarRelatorioJasper(RelatorioSicoobDDAEnum.SDDA505, usuario, null);
        configurarRelatorio.setParametro("termoPagadorDto", termoPagadorDto);
        configurarRelatorio.setParametro("hpeDto", historicoPagdorEletroDto);

        return configurarRelatorio;
    }

}
