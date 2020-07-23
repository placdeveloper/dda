package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * AbrirArquivoRecebidoCIPJobServicoEJB é responsável por descriptorgrar os arquivos recebidos na pasta do ConnectDirect e salvar na pasta
 * Sistemas/SicoobDDA/ABERTOS
 */
@Stateless
@Remote(JobServico.class)
public class AbrirArquivoRecebidoCIPJobServicoEJB extends MotorArquivoUtilServico {

    static final Integer TIME_OUT = 180;
    static final String JNDI_STEP_EJB = "sicoobdda_processamento/AbrirArquivoRecebidoCIPStepServicoRemote";
    private static final String NOME_JOB = "AbrirArquivo";

    private String pathOrigemArquivo = null;
    private String pathDestinoArquivo = null;
    private int tempoMaxUltimaAlteracaoNoArquivo = 0;

    /**
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        String parametro = contexto.getParametroDinamico().getValor();
        return verificarDependenciasJobMotor(parametro, SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL, NOME_JOB, getLogger());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#obterSteps(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public List<Step> obterSteps(ContextoExecucao contexto) {
        List<Step> listaProcessadores = new ArrayList<Step>();
        String parametro = (String) contexto.getParametroDinamico().getValor();
        TipoInstanciaProcessamentoEnum instanciaSWS = TipoInstanciaProcessamentoEnum.valueOf(parametro);

        List<ArquivoProcessamentoVO> listaArquivo = listarArquivoParaProcessamento(instanciaSWS, SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL, NOME_JOB, getLogger());

        selecionarParametrosParaSteps();
        for (ArquivoProcessamentoVO arquivo : listaArquivo) {

            Parametro prIdArquivoRecebido = new Parametro("idArquivoREC", arquivo.getIdArquivoRecebido(), TipoParametro.LONGO);
            Parametro prIdArquivoEnviado = new Parametro("idArquivoENV", arquivo.getIdArquivoEnviado(), TipoParametro.LONGO);
            Parametro prNomeDoArquivo = new Parametro("nmArquivo", pathOrigemArquivo + File.separator + arquivo.getNmArquivoRecebido(), TipoParametro.TEXTO);
            Parametro prLocalDeSalva = new Parametro("pathSalvaArq", pathDestinoArquivo, TipoParametro.TEXTO);
            Parametro prTmpMaxUltAtual = new Parametro("tmpMAxUltAtual", tempoMaxUltimaAlteracaoNoArquivo, TipoParametro.INTEIRO);

            listaProcessadores.add(ejb(JNDI_STEP_EJB).comTimeout(TIME_OUT)
                    .comParametros(prIdArquivoRecebido, prIdArquivoEnviado, prNomeDoArquivo, prLocalDeSalva, prTmpMaxUltAtual));
        }
        return listaProcessadores;
    }

    /**
     * Método responsável por void
     * 
     */
    private void selecionarParametrosParaSteps() {
        getLogger().debug(
                "Recuperando parâmetros:" + DIRETORIO_ARQUIVOS_RECEBIDOS_CIP + " " + DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO + " "
                        + TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO);
        List<ParametroDDA> listaParametrosStep = getParametroDAO().listarParametros(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP, DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO,
                TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO);
        if (!ObjectUtil.isEmpty(listaParametrosStep)) {
            for (ParametroDDA valorpar : listaParametrosStep) {
                if (valorpar.getId() == DIRETORIO_ARQUIVOS_RECEBIDOS_CIP) {
                    pathOrigemArquivo = valorpar.getValorParametro();
                } else if (valorpar.getId() == DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO) {
                    pathDestinoArquivo = valorpar.getValorParametro();
                } else if (valorpar.getId() == TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO) {
                    tempoMaxUltimaAlteracaoNoArquivo = Integer.valueOf(valorpar.getValorParametro());
                }
            }
        }
    }

    /**
     * Método responsável por obter o DAO
     * 
     * @return
     */
    private ParametroDao getParametroDAO() {
        return ComumDaoFactory.getInstance().criarParametroDao();
    }

    /**
     * Método responsável por obter o logger
     * 
     * @return
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(AbrirArquivoRecebidoCIPJobServicoEJB.class);
    }

}
