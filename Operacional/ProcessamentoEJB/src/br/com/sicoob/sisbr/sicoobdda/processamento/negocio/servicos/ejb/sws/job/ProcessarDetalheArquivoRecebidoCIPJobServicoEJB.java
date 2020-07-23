package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * ProcessarDetalheArquivoRecebidoCIPJobServicoEJB
 */
@Stateless
@Remote(JobServico.class)
public class ProcessarDetalheArquivoRecebidoCIPJobServicoEJB extends MotorArquivoUtilServico {

    static final Integer TIME_OUT = 180;
    static final String JNDI_STEP_EJB = "sicoobdda_processamento/ProcessarDetalheArquivoRecebidoCIPStepServicoRemote";
    private static final String NOME_JOB = "ProcessarRegistros";
    private TipoInstanciaProcessamentoEnum instanciaSWS;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        String parametro = contexto.getParametroDinamico().getValor();
        return verificarDependenciasJobMotor(parametro, SituacaoProcessamentoArquivo.ARQUIVO_EM_PROCESSAMENTO, NOME_JOB, getLogger());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        List<Step> listaProcessadores = new ArrayList<Step>();
        instanciaSWS = TipoInstanciaProcessamentoEnum.valueOf((String) contexto.getParametroDinamico().getValor());

        List<Long> listaIdArquivo = listarIdArquivoParaProcessamento(instanciaSWS, SituacaoProcessamentoArquivo.ARQUIVO_EM_PROCESSAMENTO, NOME_JOB, getLogger());

        if (!ObjectUtil.isEmpty(listaIdArquivo)) {
            getLogger().debug("Recuperando dados para execução dos steps!");
            int qtdRegTrans = obterQtdeRegistroPorTransacao();
            Map<Long, List<ArquivoProcessamentoVO>> mapaParametroArquivo = getProcessamentoSWSDao().listarParametrosStepsProcessamentoArq(listaIdArquivo, qtdRegTrans);

            for (Long idArquivo : listaIdArquivo) {
                if (mapaParametroArquivo.containsKey(idArquivo)) {
                    montarListaStep(listaProcessadores, instanciaSWS, qtdRegTrans, mapaParametroArquivo.get(idArquivo));
                }
            }
        }

        return listaProcessadores;
    }

    /**
     * Método responsável por
     * 
     * @param listaProcessadores
     * @param instanciaSWS
     * @param qtdRegTrans
     * @param listaParametroArquivo void
     * 
     */
    private void montarListaStep(List<Step> listaProcessadores, TipoInstanciaProcessamentoEnum instanciaSWS, int qtdRegTrans, List<ArquivoProcessamentoVO> listaParametroArquivo) {
        int totalIteracao = listaParametroArquivo.size();
        int numeroIteracao = 1;
        for (ArquivoProcessamentoVO arquivo : listaParametroArquivo) {
            Parametro idArquivoRecebido = new Parametro("idArquivo", arquivo.getIdArquivoRecebido(), TipoParametro.LONGO);
            Parametro idDetInicial = new Parametro("idDetInicial", arquivo.getIdDetLogRecebimentoIni(), TipoParametro.LONGO);
            Parametro idDetFinal = new Parametro("idDetFinal", arquivo.getIdDetLogRecebimentoFin(), TipoParametro.LONGO);
            Parametro idTipoInstancia = new Parametro("idTipoInstancia", instanciaSWS.toString(), TipoParametro.TEXTO);
            Parametro codTipoMensagem = new Parametro("codTipoMensagem", arquivo.getTipoDaMensagem(), TipoParametro.TEXTO);
            Parametro qtdRegistroTrans = new Parametro("qtdRegistroTrans", qtdRegTrans, TipoParametro.INTEIRO);
            Parametro particao = new Parametro("particao", montarInformacaoParticao(numeroIteracao, totalIteracao), TipoParametro.TEXTO);

            listaProcessadores.add(ejb(JNDI_STEP_EJB).comTimeout(TIME_OUT).comParametros(idArquivoRecebido, idDetInicial, idDetFinal, idTipoInstancia, codTipoMensagem,
                    qtdRegistroTrans, particao));
            numeroIteracao++;
        }
    }

    /**
     * @param numeroIteracao
     * @param totalIteracao
     * @return String
     * 
     */
    private String montarInformacaoParticao(int numeroIteracao, int totalIteracao) {
        return numeroIteracao + "/" + totalIteracao;
    }

    /**
     * @return int
     * 
     */
    private int obterQtdeRegistroPorTransacao() {
        try {
            return getParametroDAO().obterValorInteger(instanciaSWS.getIdParamRegistrosPorStep(), Constantes.ID_BANCOOB);
        } catch (ComumException e) {
            throw new BancoobRuntimeException(e);
        }
    }

    /**
     * Método responsável por obter o logger
     * 
     * @return
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(ProcessarDetalheArquivoRecebidoCIPJobServicoEJB.class);
    }

    /**
     * Método responsável por obter o DAO
     * 
     * @return
     */
    private ParametroDao getParametroDAO() {
        return ComumDaoFactory.getInstance().criarParametroDao();
    }
}
