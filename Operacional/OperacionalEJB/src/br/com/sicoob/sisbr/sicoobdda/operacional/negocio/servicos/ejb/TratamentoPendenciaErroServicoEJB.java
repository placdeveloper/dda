/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         TratamentoPendenciaErroServicoEJB.java
 * Data Criação:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.EMAIL_ASSUNTO_ERRO_MSG_CONTINGENCIA;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.EMAIL_DDA_HABILITADO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.EMAIL_DESTINATARIO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.EMAIL_REMETENTE;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DadosTratamentoMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroAgrupadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroProcessamentoCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemErroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PendenciaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoPendenciaErroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoTratamentoErroCipEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.EmailSicoobDDA;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarContingenciaMensagemDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNegocionException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.TratamentoPendenciaErroServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao;
import br.com.sicoob.tipos.DateTime;

/**
 * TratamentoPendenciaErroServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ TratamentoPendenciaErroServicoLocal.class })
public class TratamentoPendenciaErroServicoEJB extends ComumCrudServicoEJB<SicoobDDAEntidade> implements TratamentoPendenciaErroServicoLocal {

    private static final int TAMANHO_MAX_LISTA = 500;
    private static final int PRIORIZAR_MENSAGEM = 1;
    private static final int DESPRIORIZAR_MENSAGEM = 100;

    @SuppressWarnings("unused")
    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private TratamentoPendenciaErroDao dao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private ParametroDao parametroDao;

    private ADMDelegate admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
    private ProcessarContingenciaMensagemDelegate processarMensagensDelegate = IntegracaoCipFabricaDelegates.getInstance().getProcessarContingenciaMensagemDelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected TratamentoPendenciaErroDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#listarTratamentoPendenciaErro()
     */
    public TratamentoPendenciaErroDto listarTratamentoPendenciaErro() throws OperacionalException {
        return new TratamentoPendenciaErroDto(listarPendencia(), listarErroAgrupado(), listarErroProcessamento());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#obterDadosTratamentoMensagemPendencia(br.com.sicoob.tipos.DateTime, java.lang.Short)
     */
    public DadosTratamentoMensagemDto obterDadosTratamentoMensagemPendencia(DateTime dataMovimento, Short codSituacaoMensagemDDA, String codTipoMensagemDDA, int maxResult) throws OperacionalException {
        return new DadosTratamentoMensagemDto(listarTratamentoMensagemErro(dataMovimento, codSituacaoMensagemDDA, codTipoMensagemDDA, maxResult), listarTipoTratamentoErroCip(codSituacaoMensagemDDA));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#obterDadosTratamentoMensagemErroAgrupado(java.lang.String, int)
     */
    public DadosTratamentoMensagemDto obterDadosTratamentoMensagemErroAgrupado(String codTipoErroCIP, int maxResult) throws OperacionalException {
        return new DadosTratamentoMensagemDto(listarTratamentoMensagemErroAgrupado(codTipoErroCIP, maxResult), listarTipoTratamentoSitMensagemRetornoComErro());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#obterDadosTratamentoMensagemErroRetornoCIP(br.com.sicoob.tipos.DateTime, java.lang.Boolean)
     */
    public DadosTratamentoMensagemDto obterDadosTratamentoArquivoErroRetornoCIP(DateTime dataMovimento, int maxResult) throws OperacionalException {
        return new DadosTratamentoMensagemDto(getDAO().obterListaTratamentoMensagemArquivo(dataMovimento, maxResult), listarTipoTratamentoErroCip(SituacaoMensagemDDA.ARQUIVO_ENVIO_COM_ERRO));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#listarTipoTratamentoMensagemContingencia()
     */
    public List<TipoTratamentoErroCip> listarTipoTratamentoMensagemContingencia() {
        return listarTipoTratamentoErroCip(SituacaoMensagemDDA.RETORNO_COM_ERRO_CONTIGENCIA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#executarTratamentoMensagem(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto)
     */
    public void executarTratamentoMensagem(TratamentoMesagemDto tratamento) throws BancoobException {
        switch (TipoTratamentoErroCipEnum.getBy(tratamento.getCodTipoTratamento())) {
            case ATUALIZAR_DATA_MOVIMENTO :
                tratarAtualizarDataMovimento(tratamento);
                break;
            case REENVIAR_SSPB :
                tratarReenviarSSPB(tratamento);
                break;
            case REENVIAR_CIP :
                tratarReenviarCIP(tratamento);
                break;
            case FINALIZAR_SEM_ACAO :
                tratarFinalizarSemAcao(tratamento);
                break;
            case FINALIZAR_REENVIAR_NOVA_MSG :
                tratarFinalizarReeviarMensagem(tratamento);
                break;
            case FINALIZAR_REENVIAR_ATUALIZACAO_DADOS :
                throw new OperacionalNegocionException("integracaocip.erro.tratamento.nao.liberado");
            case REPROCESSAR_MENSAGEM_CONTINGENCIA :
                tratarReprocessarMensagemContingencia(tratamento);
                break;
            case PREPARAR_REENVIO_ARQUIVO_CIP :
                tratarPrepararReenvioArquivo(tratamento);
                break;
            case ATUALIZAR_DATA_PROXIMO_MOVIMENTO :
                tratarAtualizarDataProximoMovimento(tratamento);
                break;
            case PRIORIZAR_MENSAGEM :
                tratarPriorizarMensagem(tratamento);
                break;
            case DESPRIORIZAR_MENSAGEM :
                tratarDespriorizarMensagem(tratamento);
                break;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#executarTratamentoAutomatizadoMensagem()
     */
    public void executarTratamentoAutomatizadoMensagem() throws BancoobException {
        List<TratamentoMesagemDto> listaTratamento = getDAO().obterListaTratamentoAutomatizado();
        if (ObjectUtil.isEmpty(listaTratamento)) {
            throw new OperacionalNegocionException("integracaocip.erro.tratamento.automatizado.nenhuma.ocorrencia");
        }
        for (TratamentoMesagemDto tratamento : listaTratamento) {
            executarTratamentoMensagem(tratamento);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#executarTratamentoMensagemContingenciaBatch(br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Long, java.lang.Long, java.lang.String)
     */
    public void executarTratamentoMensagemContingenciaBatch(DateTimeDB dataMovimento, Long idMensagemInicial, Long idMensagemFinal, String codTipoMensagem) throws BancoobException {
        List<Long> listaIdMensagem = getDAO().obterListaTratamentoMensagemContingenciaBatch(dataMovimento, idMensagemInicial, idMensagemFinal, codTipoMensagem);
        String nomeArquivoErro = processarMensagensDelegate.processarMensagemContingenciaBatch(listaIdMensagem, dataMovimento, codTipoMensagem);
        if (!ObjectUtil.isEmpty(nomeArquivoErro) && emailHabilitado()) {
            enviarEmail(nomeArquivoErro);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#obterDetalheErroMensagem(java.lang.Long)
     */
    public MensagemDDA obterDetalheMensagemErro(Long idMensagemDDA) throws OperacionalNegocionException {
        if (ObjectUtil.isEmpty(idMensagemDDA)) {
            throw new OperacionalNegocionException("integracaocip.erro.mensagem.nao.informada");
        }
        return getDAO().obterDetalheMensagemErro(idMensagemDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#listarTipoTratamentoSitMensagemRetornoComErro()
     */
    public List<TipoTratamentoErroCip> listarTipoTratamentoSitMensagemRetornoComErro() {
        return listarTipoTratamentoErroCip(SituacaoMensagemDDA.RETORNO_COM_MENSAGEM_DE_ERRO);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#excluirMensagemErro(java.lang.Long)
     */
    public void excluirMensagemErro(Long idMensagemDDA) throws BancoobException {
        if (ObjectUtil.isEmpty(idMensagemDDA)) {
            throw new OperacionalNegocionException("integracaocip.erro.id.mensagem.nao.informado");
        }
        getDAO().excluirMensagem(idMensagemDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.TratamentoPendenciaErroServico#excluirListaMensagemErro(java.util.List)
     */
    public void excluirListaMensagemErro(List<MensagemDDA> listaMensagemDDA) throws ComumException {
        for (MensagemDDA mensagemDDA : listaMensagemDDA) {
            if (TipoMensagemDDA.isBeneficiario(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
                removerMensagemBeneficiario(mensagemDDA);
            } else if (TipoMensagemDDA.isPagador(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
                removerMensagemPagador(mensagemDDA);
            } else if (TipoMensagemDDA.isBoleto(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
                removerMensagemBoleto(mensagemDDA);
            } else if (TipoMensagemDDA.isTerceiroAutorizado(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
                removerMensagemTerceiroAutorizado(mensagemDDA);
            } else if (TipoMensagemDDA.isMsgAlteracaoAceiteBoleto(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
                removerMensagemAceite(mensagemDDA);
            } else if (TipoMensagemDDA.isMsgBxOperacional(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())
                    || TipoMensagemDDA.isMsgCancelamentoBxOperacional(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
                removerMensagemBaixaOperacional(mensagemDDA);
            } else if (TipoMensagemDDA.isMsgBxEfetiva(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
                removerMensagemBaixaEfetiva(mensagemDDA);
            } else if (TipoMensagemDDA.isMsgConsultaBoleto(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())
                    || mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0110) || mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0110R1)
                    || mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.ADDA110)
                    || mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.ADDA110RET)) {
                removerMensagemConsultaBoleto(mensagemDDA);
            } else {
                removerMensagemDDA(mensagemDDA);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param mensagemDDA void
     * @throws ComumException
     * 
     */
    private void removerMensagemDDA(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().excluirMensagem(mensagemDDA.getId());
    }

    /**
     * Método responsável por remover as mensagens da Baixa Efetiva
     * 
     * @param mensagemDDA void
     * @throws ComumException
     * 
     */
    private void removerMensagemBaixaEfetiva(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().removerMensagemBaixaEfetiva(mensagemDDA.getId());
        if (TipoMensagemDDA.isArquivoBxEfetiva(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
            getDAO().removerLogDetalheEnvioArquivoDDA(mensagemDDA.getId());
        }
        removerMensagemDDA(mensagemDDA);
    }

    /**
     * Método responsável por
     * 
     * @param mensagemDDA
     * @throws ComumException void
     * 
     */
    private void removerMensagemConsultaBoleto(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().removerMensagemConsultaBoleto(mensagemDDA.getId());
        removerMensagemDDA(mensagemDDA);
    }

    /**
     * Método responsável por remover as mensagens da Baixa Operacional
     * 
     * @param mensagemDDA void
     * @throws ComumException
     * 
     */
    private void removerMensagemBaixaOperacional(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().removerMensagemBaixaOperacional(mensagemDDA.getId());
        if (TipoMensagemDDA.isArquivoBxOperacional(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
            getDAO().removerLogDetalheEnvioArquivoDDA(mensagemDDA.getId());
        }
        removerMensagemDDA(mensagemDDA);
    }

    /**
     * Método responsável por remover as mensagens do Aceite
     * 
     * @param mensagemDDA void
     * @throws ComumException
     * 
     */
    private void removerMensagemAceite(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().removerMensagemAceite(mensagemDDA.getId());
        removerMensagemDDA(mensagemDDA);
    }

    /**
     * Método responsável por remover as mensagens do Terceiro Autorizado
     * 
     * @param mensagemDDA void
     * @throws ComumException
     * 
     */
    private void removerMensagemTerceiroAutorizado(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().removerMensagemTerceiroAutorizado(mensagemDDA.getId());
        removerMensagemDDA(mensagemDDA);
    }

    /**
     * Método responsável por remover as mensagens do boleto
     * 
     * @param mensagemDDA void
     * @throws ComumException
     * 
     */
    private void removerMensagemBoleto(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().removerMensagemGrupoCalculo(mensagemDDA.getId());
        getDAO().removerMensagemNotaFiscal(mensagemDDA.getId());
        getDAO().removerMensagemTextoInfo(mensagemDDA.getId());
        getDAO().removerMensagemBoleto(mensagemDDA.getId());
        if (TipoMensagemDDA.isArquivoBoleto(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
            getDAO().removerLogDetalheEnvioArquivoDDA(mensagemDDA.getId());
        }
        removerMensagemDDA(mensagemDDA);
    }

    /**
     * Método responsável por remover as mensagens do beneficiario
     * 
     * @param mensagemDDA void
     * @throws ComumException
     * 
     */
    private void removerMensagemBeneficiario(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().removerMensagemBeneficiarioRepresentante(mensagemDDA.getId());
        getDAO().removerMensagemBeneficiarioConvenio(mensagemDDA.getId());
        getDAO().removerMensagemBeneficiario(mensagemDDA.getId());
        removerMensagemDDA(mensagemDDA);
    }

    /**
     * Método responsável por remover as mensagens do pagador
     * 
     * @param mensagemDDA void
     * @throws ComumException
     * 
     */
    private void removerMensagemPagador(MensagemDDA mensagemDDA) throws ComumException {
        getDAO().removerMensagemPagadorAgregado(mensagemDDA.getId());
        getDAO().removerMensagemPagadorConta(mensagemDDA.getId());
        getDAO().removerMensagemPagador(mensagemDDA.getId());
        if (TipoMensagemDDA.isArquivoPagador(mensagemDDA.getTipoMensagemDDA().getCodTipoMensagem())) {
            getDAO().removerLogDetalheEnvioArquivoDDA(mensagemDDA.getId());
        }
        removerMensagemDDA(mensagemDDA);
    }

    /**
     * Método responsável por
     * 
     * @return List<PendenciaDto>
     * @throws OperacionalException
     * 
     */
    private List<PendenciaDto> listarPendencia() throws OperacionalException {
        return getDAO().listarPendencia();
    }

    /**
     * Método responsável por
     * 
     * @return List<ErroAgrupadoDto>
     * @throws OperacionalException
     * 
     */
    private List<ErroAgrupadoDto> listarErroAgrupado() throws OperacionalException {
        return getDAO().listarErroAgrupado();
    }

    /**
     * Método responsável por
     * 
     * @return List<ErroProcessamentoRetornoCipDto>
     * @throws OperacionalException
     * 
     */
    private List<ErroProcessamentoCipDto> listarErroProcessamento() throws OperacionalException {
        return getDAO().listarErroProcessamento();
    }

    /**
     * Método responsável por
     * 
     * @param dataMovimento
     * @param codSituacaoMensagemDDA
     * @param codTipoMensagemDDA
     * @param maxResult
     * @return
     * @throws OperacionalException List<MensagemErroDto>
     * 
     */
    private List<MensagemErroDto> listarTratamentoMensagemErro(DateTime dataMovimento, Short codSituacaoMensagemDDA, String codTipoMensagemDDA, int maxResult) throws OperacionalException {
        if (codSituacaoMensagemDDA.equals(SituacaoMensagemDDA.RETORNO_COM_MENSAGEM_DE_ERRO)) {
            return getDAO().obterListaTratamentoRetornoErro(codTipoMensagemDDA, dataMovimento, maxResult);
        } else {
            return getDAO().obterListaTratamentoErroCIP(dataMovimento, codSituacaoMensagemDDA, codTipoMensagemDDA, maxResult);
        }
    }

    /**
     * Método responsável por
     * 
     * @param codTipoErroCIP
     * @param maxResult
     * @return List<MensagemErroDto>
     * @throws OperacionalException
     * 
     */
    private List<MensagemErroDto> listarTratamentoMensagemErroAgrupado(String codTipoErroCIP, int maxResult) throws OperacionalException {
        return getDAO().obterListaTratamentoErroAgrupado(codTipoErroCIP, maxResult);
    }

    /**
     * Método responsável por
     * 
     * @param codSituacaoMensagemDDA
     * @return List<TipoTratamentoErroCip>
     * 
     */
    private List<TipoTratamentoErroCip> listarTipoTratamentoErroCip(Short codSituacaoMensagemDDA) {
        return getDAO().listarTipoTratamentoErroCip(codSituacaoMensagemDDA);
    }

    /**
     * Método responsável por
     * 
     * @param tratamento
     * @throws ComumException void
     * 
     */
    private void tratarAtualizarDataMovimento(TratamentoMesagemDto tratamento) throws ComumException {
        tratarAtualizarDataMovimento(tratamento, Boolean.FALSE);
    }

    /**
     * Método responsável por
     * 
     * @param tratamento
     * @throws ComumException void
     * 
     */
    private void tratarAtualizarDataProximoMovimento(TratamentoMesagemDto tratamento) throws ComumException {
        tratarAtualizarDataMovimento(tratamento, Boolean.TRUE);
    }

    /**
     * Método responsável por
     * 
     * @param tratamento
     * @param bolDataProximoMovimento
     * @throws ComumException void
     * 
     */
    private void tratarAtualizarDataMovimento(TratamentoMesagemDto tratamento, Boolean bolDataProximoMovimento) throws ComumException {
        Date dataMovimentoProduto = (bolDataProximoMovimento ? admDelegate.obterDataProximoMovimentoBancoob() : admDelegate.obterDataMovimentoBancoob());
        if (ObjectUtil.isEmpty(tratamento.getListaIdMensagem())) {
            getDAO().atualizarDataMovimentoMensagem(dataMovimentoProduto);
        } else {
            for (List<Long> listaIdMensagem : ObjectUtil.chopped(tratamento.getListaIdMensagem(), TAMANHO_MAX_LISTA)) {
                getDAO().atualizarDataMovimentoMensagem(listaIdMensagem, dataMovimentoProduto);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param tratamento
     * @throws ComumException
     * 
     */
    private void tratarReenviarSSPB(TratamentoMesagemDto tratamento) throws ComumException {
        Date dataMovimentoProduto = admDelegate.obterDataMovimentoBancoob();
        for (List<Long> listaIdMensagem : ObjectUtil.chopped(tratamento.getListaIdMensagem(), TAMANHO_MAX_LISTA)) {
            getDAO().reenviarSSPBMensagem(listaIdMensagem, dataMovimentoProduto);
        }
    }

    /**
     * Método responsável por
     * 
     * @param tratamento
     * @throws ComumException
     * @throws
     * @throws ComumException void
     * 
     */
    private void tratarReenviarCIP(TratamentoMesagemDto tratamento) throws ComumException {
        Date dataMovimentoProduto = admDelegate.obterDataMovimentoBancoob();
        for (List<Long> listaIdMensagem : ObjectUtil.chopped(tratamento.getListaIdMensagem(), TAMANHO_MAX_LISTA)) {
            getDAO().reenviarCIPMensagem(listaIdMensagem, dataMovimentoProduto);
        }
    }

    /**
     * Método responsável por
     * 
     * @param tratamento void
     * @throws ComumException
     * 
     */
    private void tratarFinalizarSemAcao(TratamentoMesagemDto tratamento) throws ComumException {
        for (List<Long> listaIdErroMensagem : ObjectUtil.chopped(tratamento.getListaIdMensagem(), TAMANHO_MAX_LISTA)) {
            getDAO().finalizarMensagem(listaIdErroMensagem);
        }
    }

    /**
     * Método responsável por
     * 
     * @param tratamento void
     * @throws ComumException
     * 
     */
    private void tratarFinalizarReeviarMensagem(TratamentoMesagemDto tratamento) throws ComumException {
        Date dataMovimentoProduto = admDelegate.obterDataMovimentoBancoob();
        for (List<Long> listaIdMensagem : ObjectUtil.chopped(tratamento.getListaIdMensagem(), TAMANHO_MAX_LISTA)) {
            getDAO().reenviarMensagemFinalizadaSSPB(listaIdMensagem, dataMovimentoProduto);
            getDAO().excluirLogErroMensagemDDA(listaIdMensagem);
        }
    }

    /**
     * Método responsável por
     * 
     * @param tratamento
     * @throws BancoobException
     * 
     */
    private void tratarReprocessarMensagemContingencia(TratamentoMesagemDto tratamento) throws BancoobException {
        processarMensagensDelegate.processarMensagemContingencia(tratamento.getListaIdMensagem());
    }

    /**
     * Método responsável por
     * 
     * @param tratamento void
     * @throws ComumException
     * 
     */
    private void tratarPrepararReenvioArquivo(TratamentoMesagemDto tratamento) throws ComumException {
        for (List<Long> listaIdLogRecebimento : ObjectUtil.chopped(tratamento.getListaIdLogRecebimento(), TAMANHO_MAX_LISTA)) {
            getDAO().prepararReenvioArquivo(listaIdLogRecebimento);
        }
    }

    /**
     * Método responsável por
     * 
     * @param tratamento void
     * @throws ComumException
     * 
     */
    private void tratarPriorizarMensagem(TratamentoMesagemDto tratamento) throws ComumException {
        atualizarPrioridadeMensagem(tratamento, PRIORIZAR_MENSAGEM);
    }

    /**
     * Método responsável por
     * 
     * @param tratamento void
     * @throws ComumException
     * 
     */
    private void tratarDespriorizarMensagem(TratamentoMesagemDto tratamento) throws ComumException {
        atualizarPrioridadeMensagem(tratamento, DESPRIORIZAR_MENSAGEM);
    }

    /**
     * Método responsável por
     * 
     * @param tratamento
     * @param numPrioridade void
     * @throws ComumException
     * 
     */
    private void atualizarPrioridadeMensagem(TratamentoMesagemDto tratamento, Integer numPrioridade) throws ComumException {
        for (List<Long> listaIdMensagem : ObjectUtil.chopped(tratamento.getListaIdMensagem(), TAMANHO_MAX_LISTA)) {
            getDAO().atualizarPrioridadeMensagem(listaIdMensagem, numPrioridade);
        }
    }

    /**
     * Método responsável por
     * 
     * @param nomeArquivoErro void
     * 
     */
    private void enviarEmail(String nomeArquivoErro) {
        String remetente = null;
        String destinatario = null;
        String assunto = null;

        List<ParametroDDA> listaParametro = parametroDao.listarParametros(EMAIL_REMETENTE, EMAIL_DESTINATARIO, EMAIL_ASSUNTO_ERRO_MSG_CONTINGENCIA);
        for (ParametroDDA parametro : listaParametro) {
            if (parametro.getId() == EMAIL_REMETENTE) {
                remetente = parametro.getValorParametro();
            } else if (parametro.getId() == EMAIL_DESTINATARIO) {
                destinatario = parametro.getValorParametro();
            } else if (parametro.getId() == EMAIL_ASSUNTO_ERRO_MSG_CONTINGENCIA) {
                assunto = parametro.getValorParametro();
            }
        }

        EmailSicoobDDA.enviar(remetente, destinatario, assunto, montarCorpoMensagem(nomeArquivoErro));
    }

    /**
     * Método responsável por
     * 
     * @param nomeArquivoErro
     * @return String
     * 
     */
    private String montarCorpoMensagem(String nomeArquivoErro) {
        StringBuilder msg = new StringBuilder();
        msg.append("Nome Servidor: ");
        msg.append(obterNomeServidor());
        msg.append("\r\n\r\nErro ao processar a contingência.\r\nArquivo gerado: ");
        msg.append(nomeArquivoErro);
        msg.append("\r\n\r\nAtenciosamente,\r\nSicoobDDA.");
        return msg.toString();
    }

    /**
     * Método responsável por
     * 
     * @return Object
     * 
     */
    private Object obterNomeServidor() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            getLogger().debug("Erro ao obter o HostName");
        }
        return "";
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException Boolean
     * 
     */
    private Boolean emailHabilitado() throws ComumException {
        return parametroDao.obterValorBoolean(EMAIL_DDA_HABILITADO, Constantes.ID_BANCOOB);
    }

}
