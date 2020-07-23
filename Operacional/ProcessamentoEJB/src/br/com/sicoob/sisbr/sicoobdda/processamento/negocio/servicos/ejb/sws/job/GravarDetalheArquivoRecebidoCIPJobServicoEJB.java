package br.com.sicoob.sisbr.sicoobdda.processamento.negocio.servicos.ejb.sws.job;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.LOCAL_ARQUIVO_TEMPORARIOS;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.QTD_MAX_REGISTROS_POR_STEP;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.LeituraADDARR2756Exception;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.saxhandle.ScraperRegistroToMapString;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sws.api.contexto.ContextoExecucao;
import br.com.sicoob.sws.api.dominio.TipoParametro;
import br.com.sicoob.sws.api.execucao.Step;
import br.com.sicoob.sws.api.execucao.VerificacaoDependencias;
import br.com.sicoob.sws.api.parametro.Parametro;
import br.com.sicoob.sws.api.servico.JobServico;

/**
 * GravarDetalheArquivoRecebidoCIPJobServicoEJB
 */
@Stateless
@Remote(JobServico.class)
public class GravarDetalheArquivoRecebidoCIPJobServicoEJB extends MotorArquivoUtilServico {

    private static final Integer TIME_OUT = 180;
    private static final String JNDI_STEP_EJB = "sicoobdda_processamento/GravarDetalheArquivoRecebidoCIPStepServicoRemote";
    private static final String NOME_JOB = "GravarDetalhe";

    private String pathOrigemArquivo = null;
    private int qtdPorStep;
    private String pathDestinoArquivosTmp;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sws.api.servico.JobServico#verificarDependencias(br.com.sicoob.sws.api.contexto.ContextoExecucao)
     */
    public VerificacaoDependencias verificarDependencias(ContextoExecucao contexto) {
        String parametro = contexto.getParametroDinamico().getValor();
        return verificarDependenciasJobMotor(parametro, SituacaoProcessamentoArquivo.ARQUIVO_ABERTO, NOME_JOB, getLogger());
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

        List<ArquivoProcessamentoVO> listaArquivo = listarArquivoParaProcessamento(instanciaSWS, SituacaoProcessamentoArquivo.ARQUIVO_ABERTO, NOME_JOB, getLogger());
        selecionarParametrosParaSteps();
        for (ArquivoProcessamentoVO arquivoProcessamento : listaArquivo) {

            List<String> listaArquivosGerados;
            try {
                listaArquivosGerados = extrairRegistros(pathOrigemArquivo, pathDestinoArquivosTmp, arquivoProcessamento, qtdPorStep);
            } catch (ComumException e) {
                throw new BancoobRuntimeException(e.getMessage(), e);
            }

            if (!ObjectUtil.isEmpty(listaArquivosGerados)) {
                atualizarQtdTotalRegistrosArquivoRecebido(arquivoProcessamento, listaArquivosGerados);

                for (String arquivoDerivado : listaArquivosGerados) {
                    Parametro prArquivoRec = new Parametro("arquivo", arquivoProcessamento, TipoParametro.OBJETO);
                    Parametro prLocalDoArquivo = new Parametro("nomeArquivo", arquivoDerivado, TipoParametro.TEXTO);
                    Parametro prQtdReg = new Parametro("qtdRegistro", qtdPorStep, TipoParametro.INTEIRO);
                    listaProcessadores.add(ejb(JNDI_STEP_EJB).comTimeout(TIME_OUT).comParametros(prArquivoRec, prLocalDoArquivo, prQtdReg));
                }
            }

        }
        return listaProcessadores;
    }

    /**
     * Método responsável por
     * 
     * @param arquivoProcessamento
     * @param listaArquivosGerados void
     * 
     */
    private void atualizarQtdTotalRegistrosArquivoRecebido(ArquivoProcessamentoVO arquivoProcessamento, List<String> listaArquivosGerados) {
        try {
            getProcessamentoSWSDao().atualizarQtdeTotalRegistrosArquivoRecebido(arquivoProcessamento.getIdArquivoRecebido(), getQtdeTotalRegistros(listaArquivosGerados));
        } catch (ComumException e) {
            getLogger().erro(e, e.getMessage());
            throw new BancoobRuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Método responsável por pegar a qtde de registros do arquivos que foi inserida como último item da lista de arquivos gerados.
     * 
     * @param listaArquivosGerados
     * @return int
     * 
     */
    private int getQtdeTotalRegistros(List<String> listaArquivosGerados) {
        int quantidade = Integer.parseInt(listaArquivosGerados.get(listaArquivosGerados.size() - 1));
        listaArquivosGerados.remove(listaArquivosGerados.size() - 1);
        return quantidade;
    }

    /**
     * Método responsável por dividir um arquivo baseado na quantidade de registros por transação
     * 
     * @param pathOrigem
     * @param pathDestino
     * @param arqProcecessamento
     * @param listaArquivosGerados
     * @param qtdRegistrosPorArq
     * @param dividir
     * @return List<String>
     * @throws ComumException
     * 
     */
    private List<String> extrairRegistros(String pathOrigem, String pathDestino, ArquivoProcessamentoVO arqProcecessamento, int qtdRegistrosPorArq) throws ComumException {
        List<String> listaArquivosGerados = new ArrayList<String>();
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(Boolean.TRUE);
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new ScraperRegistroToMapString(pathDestino, arqProcecessamento, qtdRegistrosPorArq, listaArquivosGerados, Boolean.TRUE));
            xmlReader.parse(pathOrigem + File.separator + arqProcecessamento.getNmArquivoRecebido());
            return listaArquivosGerados;
        } catch (ParserConfigurationException e) {
            throw new ComumException("erro.job.gravardetalhe.extrair.registros.configurar.parse", e.getMessage(), e);
        } catch (LeituraADDARR2756Exception e) {

            IntegracaoCipFabricaDelegates.getInstance().getArquivoRecebidoCIPProcessadorServicoDelegate().atualizarSituacaoProcessamentoArquivoRecebido(arqProcecessamento.getIdArquivoRecebido(), SituacaoProcessamentoArquivo.ARQUIVO_ADDARR2_REJEITADO_756);
            return null;
        } catch (IOException e) {
            throw new ComumException("erro.job.gravardetalhe.extrair.registros.acessar.arquivo", new Object[] { arqProcecessamento.getNmArquivoRecebido(), e.getMessage() }, e);
        } catch (SAXParseException e) {
            throw new ComumException("erro.job.gravardetalhe.extrair.registros.executar.parse", new Object[] { arqProcecessamento.getNmArquivoRecebido(), e.getMessage() }, e);
        } catch (SAXException e) {
            throw new ComumException("erro.job.gravardetalhe.extrair.registros.parse", new Object[] { e.getMessage() }, e);
        }
    }

    /**
     * Método responsável por setar os parametros utilizados no JOB
     */
    private void selecionarParametrosParaSteps() {

        getLogger().debug("Recuperando parâmetros:" + DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO + ", " + QTD_MAX_REGISTROS_POR_STEP + ", " + LOCAL_ARQUIVO_TEMPORARIOS);
        List<ParametroDDA> listaParametrosStep = getParametroDAO().listarParametros(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO, QTD_MAX_REGISTROS_POR_STEP, LOCAL_ARQUIVO_TEMPORARIOS);

        if (!ObjectUtil.isEmpty(listaParametrosStep)) {
            for (ParametroDDA valorpar : listaParametrosStep) {
                if (valorpar.getId() == DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO) {
                    this.pathOrigemArquivo = valorpar.getValorParametro();
                } else if (valorpar.getId() == QTD_MAX_REGISTROS_POR_STEP) {
                    this.qtdPorStep = Integer.valueOf(valorpar.getValorParametro());
                } else if (valorpar.getId() == LOCAL_ARQUIVO_TEMPORARIOS) {
                    this.pathDestinoArquivosTmp = valorpar.getValorParametro();
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
        return SicoobLoggerPadrao.getInstance(GravarDetalheArquivoRecebidoCIPJobServicoEJB.class);
    }

}
