/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         MonitoracaoDDAServicoEJB.java
 * Data Criação:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.HABILITAR_CONTINGENCIA_MANUAL;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_ARQ_REMESSA_NIVEL_CRITICO_ENVIAR;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_ARQ_REMESSA_NIVEL_CRITICO_PROCESSADO_ERRO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_ARQ_REMESSA_NIVEL_CRITICO_SEM_PROTOCOLO_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_ARQ_REMESSA_NIVEL_CRITICO_SEM_RETORNO_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_ARQ_VARREDURA_NIVEL_CRITICO_ARQ_SEM_GEN0015;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_ARQ_VARREDURA_NIVEL_CRITICO_DISPONIVEL;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_ARQ_VARREDURA_NIVEL_CRITICO_EM_PROCESSAMENTO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_ARQ_VARREDURA_NIVEL_CRITICO_GEN0015_SEM_ARQ;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_DEMAIS_MSG_NIVEL_CRITICO_ENVIAR;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_DEMAIS_MSG_NIVEL_CRITICO_RETORNO_ERRO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_DEMAIS_MSG_NIVEL_CRITICO_SEM_RETORNO_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_DEMAIS_MSG_NIVEL_CRITICO_SEM_RETORNO_SSPB;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_PERCENTUAL_ACEITAVEL_SLA_DDA0110;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_TEMPO_ALERTA_BLOQUEIO_MOTOR_ENVIO_ARQ;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_TEMPO_ALERTA_BLOQUEIO_MOTOR_ENVIO_MSG;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_TEMPO_ALERTA_GEN0015_SEM_ARQ;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_TEMPO_ATUALIZACAO_ARQ_REMESSA;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_TEMPO_ATUALIZACAO_ARQ_VARREDURA;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MON_TEMPO_ATUALIZACAO_MQ_DEMAIS_MSG;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_ENVIO_ARQUIVO_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.MOTOR_ENVIO_MSGS_EM_EXECUCAO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.QTDE_MAXIMA_TENTATIVAS_ACEITAVEIS_HABILITAR_CONTINGENCIA_MANUAL;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.QTDE_TENTATIVAS_CONTINGENCIA_AUTOMATICA;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.SICOOBDDA_CONSULTA_BOLETO_ATIVO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.ID_BANCOOB;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqRemessaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqVarreduraDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDDA0110Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDemaisMensagensDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoContingencia;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ContingenciaDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.ContingenciaServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.MonitoracaoDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ContingenciaDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * MonitoracaoDDAServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MonitoracaoDDAServicoLocal.class })
public class MonitoracaoDDAServicoEJB extends OperacionalServicoEJB implements MonitoracaoDDAServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private MonitoracaoDao dao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private ParametroDao parametroDao;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private ContingenciaDao contingenciaDao;

    private ParametroDelegate parametroDelegate;

    @EJB
    private ContingenciaServicoLocal contingenciaEJB;

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(Constantes.PATTERN_DATA_HORA_BD);

    private static final String USUARIO_CONTINGENCIA = "contingência_automatica";

    private Date dataUltimaValidacao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb.OperacionalServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * Método responsável por obter o delegate
     * 
     * @return ParametroDelegate
     */
    private ParametroDelegate getParametroDelegate() {
        if (ObjectUtil.isNull(parametroDelegate)) {
            parametroDelegate = ComumFabricaDelegates.getInstance().getParametroDelegate();
        }
        return parametroDelegate;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracao()
     */
    public MonitoracaoDto obterDadosMonitoracao() throws ComumException {
        MonitoracaoDDA0110Dto monitoracaoDDA0110 = obterDadosMonitoracaoDDA0110();
        MonitoracaoDemaisMensagensDto monitoracaoDemaisMensagens = obterDadosMonitoracaoDemaisMensagens();
        MonitoracaoArqRemessaDto monitoracaoArqRemessa = obterDadosMonitoracaoArquivoRemessa();
        MonitoracaoArqVarreduraDto monitoracaoArqVarredura = obterDadosMonitoracaoArquivoVarredura();
        return new MonitoracaoDto(monitoracaoDDA0110, monitoracaoDemaisMensagens, monitoracaoArqRemessa, monitoracaoArqVarredura);
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException MonitoracaoDDA0110Dto
     * 
     */
    private MonitoracaoDDA0110Dto obterDadosMonitoracaoDDA0110() throws ComumException {
        return obterDadosMonitoracaoDDA0110(null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracaoDDA0110(java.lang.String)
     */
    public MonitoracaoDDA0110Dto obterDadosMonitoracaoDDA0110(String dataHoraUltimaAfericao) throws ComumException {
        MonitoracaoDDA0110Dto monitoracaoDDA0110 = dao.obterDadosMonitoracaoDDA0110(dataHoraUltimaAfericao);
        monitoracaoDDA0110.setPercentualAceitavelSLA(parametroDao.obterValorDouble(MON_PERCENTUAL_ACEITAVEL_SLA_DDA0110, ID_BANCOOB));
        monitoracaoDDA0110.setBolConsultaCipHabilitada(parametroDao.obterValorBoolean(SICOOBDDA_CONSULTA_BOLETO_ATIVO, ID_BANCOOB));
        monitoracaoDDA0110.setBolContingenciaHabilitadaAutomatica(parametroDao.obterValorBoolean(CONTINGENCIA_HABILITADA_CONSULTA_CIP, ID_BANCOOB));
        monitoracaoDDA0110.setBolContingenciaHabilitadaManual(parametroDao.obterValorBoolean(HABILITAR_CONTINGENCIA_MANUAL, ID_BANCOOB));

        Integer qtdTentativasContingenciaAutomatica = parametroDao.obterValorInteger(QTDE_TENTATIVAS_CONTINGENCIA_AUTOMATICA, ID_BANCOOB);
       
        if (monitoracaoDDA0110.getBolContingenciaHabilitadaAutomatica() && qtdTentativasContingenciaAutomatica != null
                && qtdTentativasContingenciaAutomatica.equals(Integer.valueOf(0))) {
            monitoracaoDDA0110.setBolContingenciaHabilitadaAutomatica(Boolean.FALSE);
            monitoracaoDDA0110.setBolContingenciaHabilitadaManual(Boolean.TRUE);
        }
        return monitoracaoDDA0110;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracaoDemaisMensagens()
     */
    public MonitoracaoDemaisMensagensDto obterDadosMonitoracaoDemaisMensagens() throws ComumException {
        MonitoracaoDemaisMensagensDto monitoracaoDemaisMensagens = dao.obterDadosMonitoracaoDemaisMensagens();
        monitoracaoDemaisMensagens.setAlertaEnviar(parametroDao.obterValorInteger(MON_DEMAIS_MSG_NIVEL_CRITICO_ENVIAR, ID_BANCOOB));
        monitoracaoDemaisMensagens.setAlertaSemRetornoSSPB(parametroDao.obterValorInteger(MON_DEMAIS_MSG_NIVEL_CRITICO_SEM_RETORNO_SSPB, ID_BANCOOB));
        monitoracaoDemaisMensagens.setAlertaSemRetornoCIP(parametroDao.obterValorInteger(MON_DEMAIS_MSG_NIVEL_CRITICO_SEM_RETORNO_CIP, ID_BANCOOB));
        monitoracaoDemaisMensagens.setAlertaRetornoErro(parametroDao.obterValorInteger(MON_DEMAIS_MSG_NIVEL_CRITICO_RETORNO_ERRO, ID_BANCOOB));
        monitoracaoDemaisMensagens.setParametroTempoAtualizacao(parametroDao.obterValorInteger(MON_TEMPO_ATUALIZACAO_MQ_DEMAIS_MSG, ID_BANCOOB));
        monitoracaoDemaisMensagens.setBolAlertaBloqueioMotorEnvio(geraAlertaBloqueioMotorEnvio(MOTOR_ENVIO_MSGS_EM_EXECUCAO, MON_TEMPO_ALERTA_BLOQUEIO_MOTOR_ENVIO_MSG));
        return monitoracaoDemaisMensagens;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracaoArquivoRemessa()
     */
    public MonitoracaoArqRemessaDto obterDadosMonitoracaoArquivoRemessa() throws ComumException {
        MonitoracaoArqRemessaDto monitoracaoArqRemessa = dao.obterDadosMonitoracaoArquivoRemessa();
        monitoracaoArqRemessa.setAlertaEnviar(parametroDao.obterValorInteger(MON_ARQ_REMESSA_NIVEL_CRITICO_ENVIAR, ID_BANCOOB));
        monitoracaoArqRemessa.setAlertaSemProtocoloCIP(parametroDao.obterValorInteger(MON_ARQ_REMESSA_NIVEL_CRITICO_SEM_PROTOCOLO_CIP, ID_BANCOOB));
        monitoracaoArqRemessa.setAlertaSemRetornoCIP(parametroDao.obterValorInteger(MON_ARQ_REMESSA_NIVEL_CRITICO_SEM_RETORNO_CIP, ID_BANCOOB));
        monitoracaoArqRemessa.setAlertaProcessadoErro(parametroDao.obterValorInteger(MON_ARQ_REMESSA_NIVEL_CRITICO_PROCESSADO_ERRO, ID_BANCOOB));
        monitoracaoArqRemessa.setParametroTempoAtualizacao(parametroDao.obterValorInteger(MON_TEMPO_ATUALIZACAO_ARQ_REMESSA, ID_BANCOOB));
        monitoracaoArqRemessa.setBolAlertaBloqueioMotorEnvio(geraAlertaBloqueioMotorEnvio(MOTOR_ENVIO_ARQUIVO_EM_EXECUCAO, MON_TEMPO_ALERTA_BLOQUEIO_MOTOR_ENVIO_ARQ));
        return monitoracaoArqRemessa;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDadosMonitoracaoArquivoVarredura()
     */
    public MonitoracaoArqVarreduraDto obterDadosMonitoracaoArquivoVarredura() throws ComumException {
        String diretorio = parametroDao.obterValor(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP, Constantes.ID_BANCOOB);

        Date dataMovimento = DateUtil.somarDias(new Date(), -1);
        List<LogRecebimentoArquivoDDA> listaGEN0015 = listarGEN0015(dataMovimento);

        List<File> listaArquivo = listaArquivoRecebido(diretorio, dataMovimento);

        List<LogRecebimentoArquivoDDA> listaArqEmProcessamento = new ArrayList<LogRecebimentoArquivoDDA>();
        List<LogRecebimentoArquivoDDA> listaArqDisponivel = new ArrayList<LogRecebimentoArquivoDDA>();
        List<LogRecebimentoArquivoDDA> listaGEN0015SemArq = new ArrayList<LogRecebimentoArquivoDDA>();
        List<LogRecebimentoArquivoDDA> listaGEN0015Disponivel = new ArrayList<LogRecebimentoArquivoDDA>();

        Boolean bolAlertaGEN0015SemArq = montarListaArquivos(listaGEN0015, listaArquivo, listaGEN0015SemArq, listaArqDisponivel, listaArqEmProcessamento, listaGEN0015Disponivel);

        List<LogRecebimentoArquivoDDA> listaArqSemGEN0015 = montarArqSemGEN0015(listaArquivo, listaGEN0015Disponivel);

        return obterDadosMonitoracaoArquivoVarredura(listaArqDisponivel, listaGEN0015SemArq, listaArqSemGEN0015, listaArqEmProcessamento, bolAlertaGEN0015SemArq);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDetalheMonitoracaoDemaisMensagens()
     */
    public MonitoracaoDemaisMensagensDto obterDetalheMonitoracaoDemaisMensagens() throws ComumException {
        MonitoracaoDemaisMensagensDto monitoracao = obterDadosMonitoracaoDemaisMensagens();
        monitoracao.setListaDetalhamento(dao.obterDetalheMonitoracaoDemaisMensagens());
        monitoracao.setListaErro(dao.obterDadosMonitoracaoErroDemaisMensagens());
        return monitoracao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#obterDetalheMonitoracaoArquivoRemessa()
     */
    public MonitoracaoArqRemessaDto obterDetalheMonitoracaoArquivoRemessa() throws ComumException {
        MonitoracaoArqRemessaDto monitoracao = obterDadosMonitoracaoArquivoRemessa();
        monitoracao.setListaDetalhamento(dao.obterDetalheMonitoracaoArquivoRemessa());
        monitoracao.setListaErro(dao.obterDadosMonitoracaoErroArquivoRemessa());
        return monitoracao;
    }

    /**
     * Método responsável por
     * 
     * @param diretorio
     * @param dataMovimento
     * @return List<File>
     * 
     */
    private List<File> listaArquivoRecebido(String diretorio, Date dataMovimento) {
        List<File> listaArquivo = new ArrayList<File>();
        File[] arquivoVarredura = ArquivoUtil.listarArquivos(diretorio, DateUtil.formatarDataArquivo(dataMovimento));
        listaArquivo.addAll(Arrays.asList(arquivoVarredura));

        arquivoVarredura = ArquivoUtil.listarArquivos(diretorio, DateUtil.formatarDataArquivo(new Date()));

        listaArquivo.addAll(Arrays.asList(arquivoVarredura));
        return listaArquivo;
    }

    /**
     * @param listaArquivo
     * @param listaGEN0015
     * @return List<LogRecebimentoArquivoDDA>
     * 
     */
    private List<LogRecebimentoArquivoDDA> montarArqSemGEN0015(List<File> listaArquivo, List<LogRecebimentoArquivoDDA> listaGEN0015) {
        List<LogRecebimentoArquivoDDA> listaArqSemGEN0015 = new ArrayList<LogRecebimentoArquivoDDA>();
        for (File file : listaArquivo) {
            if (!containsGen0015(file.getName(), listaGEN0015)) {
                listaArqSemGEN0015.add(new LogRecebimentoArquivoDDA(file.getName(), new DateTimeDB(file.lastModified())));
            }
        }
        return listaArqSemGEN0015;
    }

    /**
     * @param listaGEN0015
     * @param listaArquivo
     * @param listaGEN0015SemArq
     * @param listaArqDisponivel
     * @param listaArqEmProcessamento
     * @param listaGEN0015Disponivel
     * @return
     * @throws ComumException Boolean
     * 
     */
    private Boolean montarListaArquivos(List<LogRecebimentoArquivoDDA> listaGEN0015, List<File> listaArquivo, List<LogRecebimentoArquivoDDA> listaGEN0015SemArq,
            List<LogRecebimentoArquivoDDA> listaArqDisponivel, List<LogRecebimentoArquivoDDA> listaArqEmProcessamento, List<LogRecebimentoArquivoDDA> listaGEN0015Disponivel)
            throws ComumException {
        Boolean bolAlertaGEN0015SemArq = Boolean.FALSE;
        Integer parametroTempoAlerta = parametroDao.obterValorInteger(MON_TEMPO_ALERTA_GEN0015_SEM_ARQ, ID_BANCOOB);
        DateTimeDB dataLocal = new DateTimeDB();

        for (LogRecebimentoArquivoDDA gen0015 : listaGEN0015) {
            switch (gen0015.getSituacaoProcessamentoArquivo().getCodSituacaoProcessamentoArquivo()) {
            case SituacaoProcessamentoArquivo.ARQUIVO_EM_PROCESSAMENTO:
                listaArqEmProcessamento.add(gen0015);
                removerDaListaArquivo(listaArquivo, gen0015.getDescNomeArquivoRecebido());
                break;
            case SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL:
            case SituacaoProcessamentoArquivo.ARQUIVO_ABERTO:
            case SituacaoProcessamentoArquivo.REGISTROS_DETALHADOS:
                listaGEN0015Disponivel.add(gen0015);
                if (containsGen0015(listaArquivo, gen0015.getDescNomeArquivoRecebido())) {
                    listaArqDisponivel.add(gen0015);
                } else {
                    listaGEN0015SemArq.add(gen0015);

                    if (!bolAlertaGEN0015SemArq) {
                        bolAlertaGEN0015SemArq = validaTempoAlerta(parametroTempoAlerta, dataLocal, gen0015.getDataHoraArquivo());
                    }
                }
                break;
            default:
                removerDaListaArquivo(listaArquivo, gen0015.getDescNomeArquivoRecebido());
                break;
            }

        }

        return bolAlertaGEN0015SemArq;
    }

    /**
     * Método responsável por
     * 
     * @param listaArquivo
     * @param descNomeArquivoRecebido void
     * @return
     * 
     */
    private void removerDaListaArquivo(List<File> listaArquivo, String descNomeArquivoRecebido) {
        List<File> listaAux = new ArrayList<File>(listaArquivo);
        for (File arquivo : listaAux) {
            if (arquivo.getName().equals(descNomeArquivoRecebido)) {
                listaArquivo.remove(arquivo);
                break;
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param parametroTempoAlerta
     * @param dataLocal
     * @param dataMonitoracao
     * @return boolean
     * 
     */
    private boolean validaTempoAlerta(Integer parametroTempoAlerta, DateTimeDB dataLocal, DateTimeDB dataMonitoracao) {
        Date dataParametro = DateUtil.decrementarData(dataLocal, Calendar.SECOND, parametroTempoAlerta);
        return DateUtil.maiorQue(dataParametro, dataMonitoracao);
    }

    /**
     * @param fileName
     * @param listaGEN0015
     * @return boolean
     * 
     */
    private boolean containsGen0015(String fileName, List<LogRecebimentoArquivoDDA> listaGEN0015) {
        for (LogRecebimentoArquivoDDA gen0015 : listaGEN0015) {
            if (gen0015.getDescNomeArquivoRecebido().equals(fileName)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * @param listaArquivo
     * @param descNomeArquivoRecebido
     * @return boolean
     * 
     */
    private boolean containsGen0015(List<File> listaArquivo, String descNomeArquivoRecebido) {
        for (File file : listaArquivo) {
            if (file.getName().equals(descNomeArquivoRecebido)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Método responsável por
     * 
     * @param dataMovimento
     * @param codSituacaoProcessamentoArquivo
     * @return
     * @throws ComumException List<LogRecebimentoArquivoDDA>
     * 
     */
    private List<LogRecebimentoArquivoDDA> listarGEN0015(Date dataMovimento) throws ComumException {
        List<LogRecebimentoArquivoDDA> listaGEN0015 = dao.listarGEN0015(dataMovimento);
        return ObjectUtil.isNull(listaGEN0015) ? new ArrayList<LogRecebimentoArquivoDDA>() : listaGEN0015;
    }

    /**
     * @param listaGEN0015
     * @param listaArqDisponivel
     * @param listaArqSemGEN0015
     * @param bolAlertaGEN0015SemArq
     * @return
     * @throws ComumException MonitoracaoArqVarreduraDto
     * 
     */
    private MonitoracaoArqVarreduraDto obterDadosMonitoracaoArquivoVarredura(List<LogRecebimentoArquivoDDA> listaArqDisponivel, List<LogRecebimentoArquivoDDA> listaGEN0015SemArq,
            List<LogRecebimentoArquivoDDA> listaArqSemGEN0015, List<LogRecebimentoArquivoDDA> listaArqEmProcessamento, Boolean bolAlertaGEN0015SemArq) throws ComumException {
        MonitoracaoArqVarreduraDto monitoracaoArqVarredura = new MonitoracaoArqVarreduraDto();
        monitoracaoArqVarredura.setAlertaArqDisponivel(parametroDao.obterValorInteger(MON_ARQ_VARREDURA_NIVEL_CRITICO_DISPONIVEL, ID_BANCOOB));
        monitoracaoArqVarredura.setAlertaGEN0015SemArq(parametroDao.obterValorInteger(MON_ARQ_VARREDURA_NIVEL_CRITICO_GEN0015_SEM_ARQ, ID_BANCOOB));
        monitoracaoArqVarredura.setAlertaArqSemGEN0015(parametroDao.obterValorInteger(MON_ARQ_VARREDURA_NIVEL_CRITICO_ARQ_SEM_GEN0015, ID_BANCOOB));
        monitoracaoArqVarredura.setAlertaArqEmProcessamento(parametroDao.obterValorInteger(MON_ARQ_VARREDURA_NIVEL_CRITICO_EM_PROCESSAMENTO, ID_BANCOOB));
        monitoracaoArqVarredura.setParametroTempoAtualizacao(parametroDao.obterValorInteger(MON_TEMPO_ATUALIZACAO_ARQ_VARREDURA, ID_BANCOOB));
        monitoracaoArqVarredura.setListaArqDisponivel(listaArqDisponivel);
        monitoracaoArqVarredura.setListaGEN0015SemArq(listaGEN0015SemArq);
        monitoracaoArqVarredura.setListaArqSemGEN0015(listaArqSemGEN0015);
        monitoracaoArqVarredura.setBolAlertaGEN0015SemArq(bolAlertaGEN0015SemArq);
        monitoracaoArqVarredura.setListaArqEmProcessamento(listaArqEmProcessamento);
        return monitoracaoArqVarredura;
    }

    /**
     * @return
     * @throws ComumException Boolean
     * 
     */
    private Boolean geraAlertaBloqueioMotorEnvio(long idParametroMotorEnvio, long idParametroAlertaBloqueio) throws ComumException {
        ParametroDDA parametroBloqueioMotor = parametroDao.obterParametro(idParametroMotorEnvio, ID_BANCOOB);
        if (parametroBloqueioMotor.getValorParametro().equals(Constantes.STRING_NUMERO_0)) {
            return Boolean.FALSE;
        }

        Integer tempoAlertaBloqueioMotorEnvio = parametroDao.obterValorInteger(idParametroAlertaBloqueio, ID_BANCOOB);
        return validaTempoAlerta(tempoAlertaBloqueioMotorEnvio, new DateTimeDB(), parametroBloqueioMotor.getDataHoraUltimaAtualizacao());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.MonitoracaoDDAServico#avaliarHabilitarContingencia()
     */
    @Override
    public void avaliarHabilitarContingencia() throws ComumException {
        getLogger().info("### Avaliar habilitar contingência...");

        final boolean timerHabilitado = getParametroDelegate().obterValorBoolean(ParametroDDA.HABILITAR_TIMER);
        debug("Timer habilitado: " + timerHabilitado);

        final boolean contingenciaManualHabilitada = getParametroDelegate().obterValorBoolean(ParametroDDA.HABILITAR_CONTINGENCIA_MANUAL);
        debug("Contingência manual habilitada: " + contingenciaManualHabilitada);

        // Se o timer estiver desabilitado ou a contingência manual estiver habilitada
        if (!timerHabilitado || contingenciaManualHabilitada) {
            debug(timerHabilitado ? "Contingência manual habilitada" : "Timer desabilitado");
            return;
        }

        final String nomeServidor = obterNomeServidor();
        final String nomeServidorParam = getParametroDelegate().obter(ParametroDDA.SERVIDOR_EXECUCAO_TIMER).getValorParametro();

        // Se não for o mesmo servidor do parâmetro
        if (ObjectUtil.isEmpty(nomeServidor) || !nomeServidor.equals(nomeServidorParam)) {
            debug("Servidor (" + nomeServidor + ") diferente do especificado no parâmetro " + nomeServidorParam);
            return;
        }

        final boolean contingenciaAutomaticaHabilitada = getParametroDelegate().obterValorBoolean(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP);

        if (contingenciaAutomaticaHabilitada) {
            tratarContingenciaHabilitada();
        } else {
            tratarContingenciaDesabilitada();
        }
    }

    /**
     * Método responsável por tratar a contingência
     * 
     * @throws ComumException
     */
    private void tratarContingenciaDesabilitada() throws ComumException {
        debug("### Tratando contingência desabilitada, DataUltimaValidacao: " + dataUltimaValidacao);

        final int tempoAfericao = getParametroDelegate().obterValorInteger(ParametroDDA.TEMPO_AFERICAO_MENSAGENS_DDA0110_PELO_TIMER);

        long diferenca = obterSegundosDiferenca(dataUltimaValidacao);

        // Se já passou o tempo para avaliar os dados
        if (diferenca >= tempoAfericao) {
            realizarAfericaoDDA0110();
        }
    }

    /**
     * Método responsável por realizar a validação se deve entrar em contingência
     * 
     * @throws ComumException
     */
    private void realizarAfericaoDDA0110() throws ComumException {
        debug("### Realizando aferição DDA0110...");

        final int porcentagemAceitavelSLA = getParametroDelegate().obterValorInteger(ParametroDDA.PERCENTUAL_ACEITAVEL_DDA0110_QUE_EXTRAPOLARAM_SLA);
        debug("Porcentagem aceitável SLA: " + porcentagemAceitavelSLA);

        MonitoracaoDDA0110Dto monitoracaoDDA0110 = dao.obterDadosMonitoracaoDDA0110Contingencia();

        long sucesso = monitoracaoDDA0110.getDda0110Sucesso();
        debug("Sucesso: " + sucesso);

        long rejeitada = monitoracaoDDA0110.getDda0110Rejeitada();
        debug("Rejeitada: " + rejeitada);

        long total = sucesso + rejeitada;
        debug("Total: " + total);

        // Se total for igual a ZERO define +1 para evitar erro de divisão por ZERO
        if (total == 0) {
            total++;
        }

        int qtdMinimaMensagem = getParametroDelegate().obterValorInteger(ParametroDDA.QTDE_MINIMA_MENSAGENS_VALIDACAO_CONTINGENCIA);
        debug("Quantidade mínima de mensagem para análise: " + qtdMinimaMensagem);

        int numTentativas = getParametroDelegate().obterValorInteger(ParametroDDA.QTDE_TENTATIVAS_CONTINGENCIA_AUTOMATICA);

        if (total < qtdMinimaMensagem) {
            getLogger().info("Total de mensagens menor que o mínimo para análise");
            zerarParametro(numTentativas);
        } else {
            debug("Resultado: " + ((rejeitada * 100) / total));

            if (((rejeitada * 100) / total) > porcentagemAceitavelSLA) {
                final int numMaxTentativas = getParametroDelegate().obterValorInteger(ParametroDDA.QTDE_MAXIMA_TENTATIVAS_ACEITAVEIS_HABILITAR_CONTINGENCIA_MANUAL);

                // Se extrapolou o limite de tentativas
                if (numTentativas >= numMaxTentativas) {
                    habilitarContingenciaManual();
                } else {
                    habilitarContingenciaAutomatica(++numTentativas);
                }
            } else {
                debug("Não será necessário habilitar a contingência...");
                zerarParametro(numTentativas);
            }
        }

        dataUltimaValidacao = new Date();
    }

    /**
     * Método responsável por zerar o parâmetro com a qtd de tentativas
     * 
     * @param numTentativas
     * @throws ComumException
     */
    private void zerarParametro(int numTentativas) throws ComumException {
        // Se for maior que zero atualiza para 0, pois não há mais ocorrência de rejeição
        if (numTentativas > 0) {
            debug("### Limpando número de tentativas");
            atualizarParametro(ParametroDDA.QTDE_TENTATIVAS_CONTINGENCIA_AUTOMATICA, Constantes.STRING_NUMERO_0);
        }
    }

    /**
     * Método responsável por tratar a contingência
     * 
     * @param numTentativas
     * @throws ComumException
     */
    private void tratarContingenciaHabilitada() throws ComumException {
        debug("### Tratando contingência habilitada, DataUltimaValidacao: " + dataUltimaValidacao);

        final int tempoContingencia = getParametroDelegate().obterValorInteger(ParametroDDA.TEMPO_DESABILITAR_CONTINGENCIA_AUTOMATICA);
        Date dataUltimaContingencia = obterDataHoraHabilitacaoContingencia();

        long diferenca = obterSegundosDiferenca(dataUltimaContingencia);

        // Se já passou o tempo para reavaliar a contingência
        if (diferenca >= tempoContingencia) {
            desabilitarContingencia();
        }
    }

    /**
     * Método responsável por obter a data/hora de habilitação da contingência
     * 
     * @return
     * @throws ComumException
     */
    private Date obterDataHoraHabilitacaoContingencia() throws ComumException {
        String strDataHoraHabilitacao = getParametroDelegate().obter(ParametroDDA.DATA_HORA_HABILITACAO_CONTINGENCIA_AUTOMATICA).getValorParametro();

        if (ObjectUtil.isEmpty(strDataHoraHabilitacao) || strDataHoraHabilitacao.equals("-")) {
            return null;
        }

        try {
            return FORMATTER.parse(strDataHoraHabilitacao);
        } catch (ParseException e) {
            throw new ComumException(
                    "Ocorreu erro ao converter o parâmetro " + ParametroDDA.DATA_HORA_HABILITACAO_CONTINGENCIA_AUTOMATICA + " para data. Valor: " + strDataHoraHabilitacao);
        }
    }

    /**
     * Método responsável por obter a diferença de tempo em segundos entre duas datas
     * 
     * @param dataInicial
     * @return long
     */
    private long obterSegundosDiferenca(Date dataInicial) {
        // Se a data for nula retorna o valor máximo do long
        if (ObjectUtil.isNull(dataInicial)) {
            return Long.MAX_VALUE;
        }

        Date dataAtual = new Date();

        long timeInicio = dataInicial.getTime();
        long timeAtual = dataAtual.getTime();

        long diferenca = (timeAtual - timeInicio) / 1000;
        debug("Diferença (segundos): " + diferenca);

        return diferenca;
    }

    /**
     * Método responsável por habilitar a contingência manual.
     * 
     * @throws ComumException
     */
    private void habilitarContingenciaManual() throws ComumException {
        getLogger().info("### Habilitando a contingência manual...");

        // Atualiza a contingência automática
        atualizarParametro(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP, Constantes.STRING_NUMERO_1);

        // Atualiza a contingência manual
        atualizarParametro(ParametroDDA.HABILITAR_CONTINGENCIA_MANUAL, Constantes.STRING_NUMERO_1);

        // Atualiza o número de tentativas
        atualizarParametro(ParametroDDA.QTDE_TENTATIVAS_CONTINGENCIA_AUTOMATICA, Constantes.STRING_NUMERO_0);

        incluirHistoricoContingencia(true);
    }

    /**
     * Método responsável por desbloquear a contingência
     * 
     * @param numTentativas
     * @throws ComumException
     */
    private void desabilitarContingencia() throws ComumException {
        getLogger().info("### Desbloqueando a contingência...");

        desabilitarParametroContingencia();

        contingenciaEJB.alterarMensagensBaixaOperacionalContingencia();
    }

    /**
     * Método responsável por desabilitar o parâmetro da contingência
     * 
     * @throws ComumException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void desabilitarParametroContingencia() throws ComumException {
        // Atualiza a contingência
        atualizarParametro(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP, Constantes.STRING_NUMERO_0);

        incluirHistoricoContingencia(false);
    }

    /**
     * Método responsável por incluir o histórico de contingência
     * 
     * @throws ComumException
     */
    private void incluirHistoricoContingencia(boolean paramHabilitado) throws ComumException {
        ContingenciaDto contingenciaDto = new ContingenciaDto(new Date(), TipoContingencia.BANCOOB, USUARIO_CONTINGENCIA, null, paramHabilitado);

        try {
            contingenciaEJB.incluirHistoricoContingencia(contingenciaDto);
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    /**
     * Método responsável por habilitar a contingência automática
     * 
     * @param numTentativas
     * @throws ComumException
     */
    private void habilitarContingenciaAutomatica(int numTentativas) throws ComumException {
        getLogger().info("### Habilitando a contingência automática. Tentativa: " + numTentativas);

        // Atualiza a contingência
        atualizarParametro(ParametroDDA.CONTINGENCIA_HABILITADA_CONSULTA_CIP, Constantes.STRING_NUMERO_1);

        // Atualiza o número de tentativas
        atualizarParametro(ParametroDDA.QTDE_TENTATIVAS_CONTINGENCIA_AUTOMATICA, String.valueOf(numTentativas));

        // Atualiza a data do início da contingência
        atualizarParametro(ParametroDDA.DATA_HORA_HABILITACAO_CONTINGENCIA_AUTOMATICA, obterDataHoraFormatada());

        incluirHistoricoContingencia(true);
    }

    /**
     * Método responsável por obter a data atual formatada
     * 
     * @return String
     */
    private String obterDataHoraFormatada() {
        return FORMATTER.format(new Date());
    }

    /**
     * Método responsável por obter o nome do servidor
     * 
     * @return
     * @throws ComumException
     */
    private String obterNomeServidor() throws ComumException {
        debug("### Obtendo o nome do servidor...");

        try {
            InetAddress ip = InetAddress.getLocalHost();

            String hostName = ip.getHostName();
            debug("HostName: " + hostName);

            return hostName;
        } catch (UnknownHostException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException("Ocorreu erro ao obter o LocalHost.");
        }
    }

    /**
     * Método responsável por atualizar o parâmetro
     * 
     * @param idParametro
     * @param valor
     * @throws ComumException
     */
    private void atualizarParametro(long idParametro, String valor) throws ComumException {
        debug("### Atualizando parâmetro: " + idParametro + " - valor: " + valor);

        ParametroDDA parametroDDA = getParametroDelegate().obter(idParametro);
        parametroDDA.setValorParametro(valor);
        parametroDDA.setDataHoraUltimaAtualizacao(new DateTimeDB());

        getParametroDelegate().alterarParametro(parametroDDA);
    }

}
