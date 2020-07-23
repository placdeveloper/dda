/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ProcessarCargaMensagensServicoEJB.java
 * Data Criação:    Aug 16, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Calendar;
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
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroSWSDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDABaixaOper;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.MensagemDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarCargaMensagensServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipLegadoDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.SCIDelegate;

/**
 * ProcessarCargaMensagensServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ ProcessarCargaMensagensServicoLocal.class })
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProcessarCargaMensagensServicoEJB extends IntegracaoCipServicoEJB implements ProcessarCargaMensagensServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipLegadoDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDDADao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDAO;

    private SCIDelegate sciDelegate = IntegracaoInternaFabricaDelegates.getInstance().getSCIDelegate();

    private MensagemDDADelegate mensagemDDADelegate;

    private static String NOME_USUARIO_BAIXA_EFETIVA = "SWS_CARGA_BAIXA_EFETIVA";
    private static String NOME_USUARIO_BOLETO = "SWS_CARGA_BOLETO";
    private static String NOME_USUARIO_PAGADOR = "SWS_CARGA_PAGADOR";

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#gerarCargaDadosLegado(java.lang.Integer)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void gerarCargaDadosLegadoInclusao(Integer numCooperativa) throws ComumException {

        dao.gerarCargaDadosLegadoInclusao(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#gerarCargaDadosLegado(java.lang.Integer)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void gerarCargaDadosLegadoAlteracao(Integer numCooperativa) throws ComumException {

        dao.gerarCargaDadosLegadoAlteracao(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#gerarCargaDadosLegado(java.lang.Integer)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void gerarCargaDadosLegadoBaixa(Integer numCooperativa) throws ComumException {

        dao.gerarCargaDadosLegadoBaixa(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#processarCadastroMensagensPagador(java.lang.Integer)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarCadastroMensagensPagador(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException {
        debug("SWS carga de mensagem - inicio do cadastro de mensagens pagador Instancia - TODOS");
        LogErroSWSDto log = new LogErroSWSDto("integracaocip.erro.processar.carga.pagadores");
        try {
            Integer idInstituicao = sciDelegate.obterInstituicaoPorCooperativaCache(numCooperativa).getIdInstituicao();
            Integer qtdeMaximaRegistros = parametroDAO.obterValorInteger(ParametroDDA.QTDE_REGISTROS_AGRUPAMENTO_SWS_CARGA_TODOS, Constantes.ID_SICOOB);
            Date dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0001);

            for (int agrupamento = 0; agrupamento < qtdAgrupamentoCooperativa; agrupamento++) {
                incluirMensagemPagador(numCooperativa, log, idInstituicao, dataReferencia, agrupamento);
            }

            // while (Boolean.TRUE) {
            // getSessionContext().getUserTransaction().begin();
            // getLogger().debug("########### listarMensagensPagadorDDA(" + numCooperativa + ").");
            //
            // Date dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0001);
            //
            // listaMsgPagador = dao.listarMensagensPagadorDDA(numCooperativa, dataReferencia);
            // contador = contador + (ObjectUtil.isEmpty(listaMsgPagador) ? 0 : listaMsgPagador.size());
            // if (!ObjectUtil.isEmpty(listaMsgPagador)) {
            // Long idEventoErro = processarCadastroListaMensagem(listaMsgPagador, idInstituicao, NOME_USUARIO_PAGADOR, log);
            // validaIdEventoErro(numCooperativa, idEventoErro);
            //
            // if (contador >= qtdeMaximaRegistros) {
            // getSessionContext().getUserTransaction().commit();
            // break;
            // }
            // }
            // getSessionContext().getUserTransaction().commit();
            // if (ObjectUtil.isEmpty(listaMsgPagador)) {
            // getLogger().debug("SPU_DDA_ENVIO_PAGADORDDA sem resultado!");
            // break;
            // }
            // }
        } catch (Exception e) {
            rollback();
            throw new ComumException(e);
        }
        log.validarPossuiErro();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#processarCadastroMensagensBoleto(java.lang.Integer)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarCadastroMensagensBoleto(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException {
        debug("SWS carga de mensagem - inicio do cadastro de mensagens boleto Instancia - TODOS");
        LogErroSWSDto log = new LogErroSWSDto("integracaocip.erro.processar.carga.boletos");
        try {
            Integer idInstituicao = sciDelegate.obterInstituicaoPorCooperativaCache(numCooperativa).getIdInstituicao();
            Boolean bolHabilitarModeloCalculo01 = parametroDAO.obterValorBoolean(ParametroDDA.HABILITAR_MODELO_CALCULO_01, Constantes.ID_SICOOB);
            Date dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0101);

            for (int agrupamento = 0; agrupamento < qtdAgrupamentoCooperativa; agrupamento++) {
                incluirMensagemBoleto(numCooperativa, log, idInstituicao, bolHabilitarModeloCalculo01, dataReferencia, agrupamento);
            }
        } catch (Exception e) {
            rollback();
            throw new ComumException(e);
        }
        log.validarPossuiErro();
    }


    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#processarCadastroMensagensBaixaEfetiva(java.lang.Integer)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarCadastroMensagensBaixaEfetiva(Integer numCooperativa, Integer qtdAgrupamentoCooperativa) throws ComumException {
        debug("SWS carga de mensagem - inicio do cadastro de mensagens Baixa Efetiva Instancia - TODOS");
        LogErroSWSDto log = new LogErroSWSDto("integracaocip.erro.processar.carga.baixas");
        try {
            Integer idInstituicao = sciDelegate.obterInstituicaoPorCooperativaCache(numCooperativa).getIdInstituicao();
            Date dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0118);

            for (int agrupamento = 0; agrupamento < qtdAgrupamentoCooperativa; agrupamento++) {
                incluirMensagemBaixa(numCooperativa, log, idInstituicao, dataReferencia, agrupamento);
            }
        } catch (Exception e) {
            rollback();
            throw new ComumException(e);
        }
        log.validarPossuiErro();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#processarCadastroMensagens(java.lang.Integer,
     *      java.lang.Integer, java.lang.String)
     * 
     *      Cod Tipo Operacao - I - Boleto B - Baixa P - Pagador
     * 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarCadastroMensagens(Integer numCooperativa, Integer nrAgrupamento, String codTipoOperacao) throws ComumException {
        debug("SWS carga de mensagem - inicio do cadastro de mensagens");
        Integer idInstituicao = sciDelegate.obterInstituicaoPorCooperativaCache(numCooperativa).getIdInstituicao();
        Date dataReferencia;
        LogErroSWSDto log;
        switch (codTipoOperacao) {
        case "I":
            debug("SWS carga de mensagem - inicio do cadastro de mensagens Boleto Instancia - Especificas");
            dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0101);
            log = new LogErroSWSDto("integracaocip.erro.processar.carga.boletos");
            Boolean bolHabilitarModeloCalculo01 = parametroDAO.obterValorBoolean(ParametroDDA.HABILITAR_MODELO_CALCULO_01, Constantes.ID_SICOOB);

            try {
                incluirMensagemBoleto(numCooperativa, log, idInstituicao, bolHabilitarModeloCalculo01, dataReferencia, nrAgrupamento);
            } catch (Exception e) {
                rollback();
                throw new ComumException(e);
            }
            break;

        case "B":
            debug("SWS carga de mensagem - inicio do cadastro de mensagens Baixa Efetiva Instancia - Especificas");
            dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0118);
            log = new LogErroSWSDto("integracaocip.erro.processar.carga.baixas");
            try {
                incluirMensagemBaixa(numCooperativa, log, idInstituicao, dataReferencia, nrAgrupamento);
            } catch (Exception e) {
                rollback();
                throw new ComumException(e);
            }
            break;
        case "P":
            debug("SWS carga de mensagem - inicio do cadastro de mensagens Pagador Instancia - Especificas");
            dataReferencia = getMensagemDDADao().obterDataReferencia(TipoMensagemDDA.DDA0001);
            log = new LogErroSWSDto("integracaocip.erro.processar.carga.pagadores");
            try {
                incluirMensagemPagador(numCooperativa, log, idInstituicao, dataReferencia, nrAgrupamento);
            } catch (Exception e) {
                rollback();
                throw new ComumException(e);
            }
            break;
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#obterQtdAgrupamentoCooperativa(java.lang.Integer)
     */
    public Integer obterQtdAgrupamentoCooperativa(Integer numCooperativa) throws ComumException {
        return dao.obterQtdAgrupamentoCooperativa(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#obterQtdAgrupCooperativaEspecificaBoleto(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaBoleto(Integer numCooperativa) {
        return dao.listaAgrupaCooperativaEspecificaBoleto(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#obterQtdAgrupCooperativaEspecificaBaixa(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaBaixa(Integer numCooperativa) {
        return dao.listaAgrupaCooperativaEspecificaBaixa(numCooperativa);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarCargaMensagensServico#obterQtdAgrupCooperativaEspecificaPagador(java.lang.Integer)
     */
    public List<Integer> listaAgrupaCooperativaEspecificaPagador(Integer numCooperativa) {
        return dao.listaAgrupaCooperativaEspecificaPagador(numCooperativa);
    }

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param log
     * @param idInstituicao
     * @param qtdeMaximaRegistros
     * @param contador
     * @param dataReferencia
     * @param agrupamento
     * @return
     * @throws SystemException
     * @throws NotSupportedException
     * @throws ComumException
     * @throws HeuristicRollbackException
     * @throws HeuristicMixedException
     * @throws RollbackException
     */
    private void incluirMensagemPagador(Integer numCooperativa, LogErroSWSDto log, Integer idInstituicao, Date dataReferencia,
            int agrupamento) throws NotSupportedException, SystemException, ComumException, RollbackException, HeuristicMixedException,
            HeuristicRollbackException {
        getSessionContext().getUserTransaction().begin();
        List<MensagemDDAPagador> listaMsgPagador = dao.listarMensagensPagadorDDA(numCooperativa, dataReferencia, agrupamento);

        if (!ObjectUtil.isEmpty(listaMsgPagador)) {
            mensagemDDADao.incluirListaMsgPagadorBatch(listaMsgPagador, idInstituicao, NOME_USUARIO_PAGADOR, log);
        }
        dao.atualizarTituloDDAAgrupador(numCooperativa, agrupamento);
        getSessionContext().getUserTransaction().commit();
    }

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param log
     * @param idInstituicao
     * @param qtdeMaximaRegistros
     * @param contador
     * @param bolHabilitarModeloCalculo01
     * @param dataReferencia
     * @param agrupamento
     * @return
     * @throws SystemException
     * @throws NotSupportedException
     * @throws ComumException
     * @throws HeuristicRollbackException
     * @throws HeuristicMixedException
     * @throws RollbackException
     */
    private void incluirMensagemBoleto(Integer numCooperativa, LogErroSWSDto log, Integer idInstituicao,
            Boolean bolHabilitarModeloCalculo01, Date dataReferencia, int agrupamento)
            throws NotSupportedException, SystemException, ComumException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
        getSessionContext().getUserTransaction().begin();
        List<MensagemDDABoleto> listaMensagemDDABoleto = dao.listarMensagensBoleto(numCooperativa, dataReferencia, bolHabilitarModeloCalculo01, agrupamento);

        // Dependendo da quantidade de dias restantes para o vencimento do boleto sera enviado via MQ.
        incluirMensagemBoletoOnline(idInstituicao, dataReferencia, listaMensagemDDABoleto);

        if (!ObjectUtil.isEmpty(listaMensagemDDABoleto)) {
            mensagemDDADao.incluirListaMsgBoletoBatch(listaMensagemDDABoleto, idInstituicao, NOME_USUARIO_BOLETO, log);
        }
        dao.atualizarTituloDDAAgrupador(numCooperativa, agrupamento);
        getSessionContext().getUserTransaction().commit();
    }

    /**
     * Método responsável por
     * 
     * @param idInstituicao
     * @param dataReferencia
     * @param listaMensagemDDABoleto
     * @throws ComumException void
     */
    private void incluirMensagemBoletoOnline(Integer idInstituicao, Date dataReferencia, List<MensagemDDABoleto> listaMensagemDDABoleto) throws ComumException {
        debug("SWS carga de mensagem - inicio da Inclusão/Alteração boleto online");

        Integer qtdDiasVencimentoBoleto = parametroDAO.obterValorInteger(ParametroDDA.QTDE_DIAS_VENCIMENTO_BOLETO_ENVIAR_ONLINNE, Constantes.ID_SICOOB);

        Date dataAtual = new Date();
        Date dataAtualMaisDiasVencimento = DateUtil.incrementarData(dataAtual, Calendar.DATE, qtdDiasVencimentoBoleto);

        List<MensagemDDABoleto> listaMensagemDDABoletoOnlinne = new ArrayList<>();

        for (MensagemDDABoleto mensagemDDABoleto : listaMensagemDDABoleto) {
            if (DateUtil.estaNoIntervalo(dataAtual, dataAtualMaisDiasVencimento, mensagemDDABoleto.getDataVencimento())) {
                listaMensagemDDABoletoOnlinne.add(mensagemDDABoleto);
            }
        }

        if (!ObjectUtil.isEmpty(listaMensagemDDABoletoOnlinne)) {
            debug("SWS carga de mensagem - Inclusão/Alteração boleto online - QTD: " + listaMensagemDDABoletoOnlinne.size());

            listaMensagemDDABoleto.removeAll(listaMensagemDDABoletoOnlinne);

            for (MensagemDDABoleto msgDDABoleto : listaMensagemDDABoletoOnlinne) {
                String tipoMensagem = msgDDABoleto.getMensagemDDA().getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.ADDA101)
                        || msgDDABoleto.getMensagemDDA().getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0101) ? TipoMensagemDDA.DDA0101
                                : TipoMensagemDDA.DDA0102;

                String tipoArquivo = msgDDABoleto.getMensagemDDA().getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.ADDA101)
                        || msgDDABoleto.getMensagemDDA().getTipoMensagemDDA().getCodTipoMensagem().equals(TipoMensagemDDA.DDA0101) ? TipoMensagemDDA.ADDA101
                                : TipoMensagemDDA.ADDA102;

                Boolean bolEnviaOnlineMensagemBoleto = parametroDAO.obterValorBoolean(ParametroDDA.HABILITAR_ENVIO_MENSAGEM_GERA_BOLETO_ONLINE, Constantes.ID_SICOOB);

                int prioridade = bolEnviaOnlineMensagemBoleto ? 0 : 10;
                debug("Tipo de mensagem: " + tipoMensagem + ", prioridade: " + prioridade);

                if (!ObjectUtil.isNull(dataReferencia)) {
                    debug("Parâmetro - dataReferencia: " + dataReferencia);
                    debug("Parâmetro - data Atual (new Date): " + DateUtil.obterDataSemHora(new Date()));
                    if (DateUtil.igualA(dataReferencia, DateUtil.obterDataSemHora(new Date()))) {
                        getMensagemDDADelegate().incluir(msgDDABoleto, tipoMensagem, new DateTimeDB(dataReferencia.getTime()), prioridade, getUsuarioLogado(), idInstituicao,
                                CanalEnum.RETAGUARDA.getId());
                    } else {
                        getMensagemDDADelegate().incluir(msgDDABoleto, tipoArquivo, new DateTimeDB(dataReferencia.getTime()), 10, getUsuarioLogado(), idInstituicao,
                                CanalEnum.RETAGUARDA.getId());
                    }
                } else {
                    // nao tem data de referencia, sera enviado por arquivos (ADDA101) com prioridade 10 e new Date da data de movimento
                    getMensagemDDADelegate().incluir(msgDDABoleto, tipoArquivo, new DateTimeDB(), 10, getUsuarioLogado(), idInstituicao, CanalEnum.RETAGUARDA.getId());
                }
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param numCooperativa
     * @param log
     * @param idInstituicao
     * @param qtdeMaximaRegistros
     * @param contador
     * @param dataReferencia
     * @param agrupamento
     * @return
     * @throws ComumException
     * @throws HeuristicRollbackException
     * @throws HeuristicMixedException
     * @throws RollbackException
     * @throws NotSupportedException
     */
    private void incluirMensagemBaixa(Integer numCooperativa, LogErroSWSDto log, Integer idInstituicao, Date dataReferencia,
            int agrupamento) throws ComumException, RollbackException, HeuristicMixedException, HeuristicRollbackException,
            SystemException, NotSupportedException {
        getSessionContext().getUserTransaction().begin();
        List<MensagemDDABaixaEfetiva> listaMsgBaixas = dao.listarMensagensBaixaEfetivaDDA(numCooperativa, dataReferencia, agrupamento);

        if (!ObjectUtil.isEmpty(listaMsgBaixas)) {
            List<MensagemDDABaixaEfetiva> listaMensagemBaixaEfetiva = getMensagemDDABaixaEfetiva(listaMsgBaixas);

            mensagemDDADao.incluirListaMsgBaixaEfetivaBatch(listaMensagemBaixaEfetiva, idInstituicao, NOME_USUARIO_BAIXA_EFETIVA, log);
        }
        dao.atualizarTituloDDAAgrupador(numCooperativa, agrupamento);
        getSessionContext().getUserTransaction().commit();
    }

    /**
     * Método responsável por
     * 
     * @param listaMsgBaixas
     * @return List<MensagemDDABaixaEfetiva>
     * @throws ComumException
     */
    private List<MensagemDDABaixaEfetiva> getMensagemDDABaixaEfetiva(List<MensagemDDABaixaEfetiva> listaMsgBaixas) throws ComumException {
        List<MensagemDDABaixaEfetiva> listaMensagemBaixaEfetiva = new ArrayList<>();
        for (MensagemDDABaixaEfetiva msgBxEfetiva : listaMsgBaixas) {
            if (msgBxEfetiva.getCodTipoBaixaEfetiva().equals(Integer.valueOf(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_SOLICITACAO_CEDENTE))
                    || msgBxEfetiva.getCodTipoBaixaEfetiva().equals(Integer.valueOf(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_ENVIO_PROTESTO))
                    || msgBxEfetiva.getCodTipoBaixaEfetiva().equals(Integer.valueOf(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_DECURSO_PRAZO))) {
                msgBxEfetiva.setCodCanalPagamento(null);
                msgBxEfetiva.setCodMeioPagamento(null);
                msgBxEfetiva.setNumIdentificadorBaixaOper(null);
                msgBxEfetiva.setCodPartRecbdrBaixaOper(null);
                msgBxEfetiva.setCodISPBPartRecbdrBaixaOper(null);
            } else {
                BoletoDDABaixaOper boletoDDaBaixaOper = getMensagemDDADao().obterBoletoBaixaOperacional(msgBxEfetiva.getValorBaixa(), msgBxEfetiva.getNumCodigoBarra());
                msgBxEfetiva.setCodCanalPagamento(boletoDDaBaixaOper.getCodCanalPagamento() != null ? boletoDDaBaixaOper.getCodCanalPagamento().intValue() : null);
                msgBxEfetiva.setCodMeioPagamento(boletoDDaBaixaOper.getCodMeioPagamento() != null ? boletoDDaBaixaOper.getCodMeioPagamento().intValue() : null);
                msgBxEfetiva.setNumIdentificadorBaixaOper(boletoDDaBaixaOper.getNumIdentificadorBaixaOper());
                msgBxEfetiva.setCodPartRecbdrBaixaOper(boletoDDaBaixaOper.getCodPartRecebedorBaixaOperacional());
                msgBxEfetiva.setCodISPBPartRecbdrBaixaOper(boletoDDaBaixaOper.getCodIspbPartRecebedorBaixaOperacional());

                /**
                 * TODO george.santos incluir no momento da carga os campos da baixa operacional
                 * 
                 * SELECT BBO.NUMIDENTIFICADORBAIXAOPER AS NUMIDENTIFICADORBAIXAOPER, BBO.CODCANALPAGAMENTO AS CODCANALPAGAMENTO, BBO.CODMEIOPAGAMENTO AS
                 * CODMEIOPAGAMENTO, BBO.CODPARTRECEBEDORBAIXAOPERACIONAL AS CODPARTRECBDRBAIXAOPER, BBO.CODISPBPARTRECEBEDORBAIXAOPERACIONAL AS
                 * CODISPBPARTRECBDRBAIXAOPER FROM DDA.BOLETODDABAIXAOPER BBO INNER JOIN dda.BOLETODDA B ON b.IDBOLETODDA = BBO.IDBOLETODDA WHERE
                 * BBO.CODSITUACAOBAIXAOPERACIONAL != 'C' AND BBO.VALORBAIXAOPER = '' AND BBO.NUMCODIGOBARRA = '2019013001881366529'
                 */
            }
            listaMensagemBaixaEfetiva.add(msgBxEfetiva);
        }
        return listaMensagemBaixaEfetiva;
    }

    /**
     * @return o atributo mensagemDDADao
     */
    public MensagemDDADao getMensagemDDADao() {
        return mensagemDDADao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return o atributo mensagemDDADelegate
     */
    public MensagemDDADelegate getMensagemDDADelegate() {
        if (mensagemDDADelegate == null) {
            mensagemDDADelegate = IntegracaoCipFabricaDelegates.getInstance().getMensagemDDADelegate();
        }
        return mensagemDDADelegate;
    }
}
