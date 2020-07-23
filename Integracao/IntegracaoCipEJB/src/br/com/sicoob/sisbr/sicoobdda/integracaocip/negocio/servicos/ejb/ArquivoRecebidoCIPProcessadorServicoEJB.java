package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.SUFIXO_NOME_ARQUIVO_ABERTO_FLEX;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.AbrirArquivoErroCIPNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.AbrirArquivoRetornoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.saxhandle.ScraperRegistros;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ProcessarRetornoDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoRecebidoCIPProcessadorServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.ZipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;

/**
 * ArquivoRecebidoCIPProcessadorServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ArquivoRecebidoCIPProcessadorServicoLocal.class })
public class ArquivoRecebidoCIPProcessadorServicoEJB extends IntegracaoCipServicoEJB implements ArquivoRecebidoCIPProcessadorServicoLocal {

    /**
     * 
     */
    private static final String ERRO_INCLUSAO_REGISTROS_ARQUIVO_RECEBIDO = "integracaocip.erro.inclusao.registros.arquivo.recebido";

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDDADao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoCipDao arquivoCipDao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDao;

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
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#abrirArquivoParaProcessamento(long,
     *      java.lang.String, java.lang.String, int)
     */
    public TipoSituacaoArquivo abrirArquivoParaProcessamento(long idArquivoRecebido, String nmArquivo, String localDeSalva, int tmpAposAtualizacao) throws ComumException {
        try {
            /**********************************************************************************************************************
             * tratamento para adequação ao novo nome do arquivo a ser renomeado pela rotina de baixa do arquivo Adriano 29/12/2016
             **********************************************************************************************************************/
            debug("***** RECUPERANDO O ARQUIVO [ " + nmArquivo + " ] DO FILESYSTEM");

            File arquivoDeEntrada = new File(nmArquivo);
            TipoSituacaoArquivo situacaoArquivo = ArquivoUtil.verificaSituacaoDoArquivo(arquivoDeEntrada, tmpAposAtualizacao);

            if (situacaoArquivo == TipoSituacaoArquivo.ARQUIVO_OK) {
                salvarArquivoAberto(localDeSalva, arquivoDeEntrada, Boolean.FALSE);
                arquivoCipDao.alterarSituacaoProcessamento(idArquivoRecebido, SituacaoProcessamentoArquivo.ARQUIVO_ABERTO);
            } else {
                debug("***** NÃO FOI POSSÍVEL ACESSAR O ARQUIVO [ " + nmArquivo + " ] MOTIVO[" + situacaoArquivo.getDescricao() + "]");
            }
            return situacaoArquivo;
        } catch (IOException e) {
            throw new ComumException("integracaocip.erro.IO.abrir.arquivo.recebido.processamento", e);
        } catch (IntegracaoInternaException e) {
            throw new ComumException("integracaocip.erro.integracao.abrir.arquivo.recebido.processamento", e);
        }
    }

    /**
     * Método responsável por descriptar, descompactar e salvar o arquivo.
     * 
     * @param localArquivo
     * @param arquivoDeEntrada
     * @throws IntegracaoInternaException
     * @throws ComumException
     * @throws IOException void
     * 
     */
    private void salvarArquivoAberto(String localArquivo, File arquivoDeEntrada, boolean chamadaFlex) throws IntegracaoInternaException, ComumException, IOException {
        byte[] dadosDeRetornoDecriptografado = getSspbDelegate().decriptar(ArquivoUtil.getBytesFromFileSemLimite(arquivoDeEntrada));

        if (ObjectUtil.isNull(dadosDeRetornoDecriptografado)) {
            throw new ComumException("integracaocip.erro.descriptografar.xml.recebido.sspb");
        }

        if (chamadaFlex) {
            ZipUtil.descompactarArquivoCIP(dadosDeRetornoDecriptografado, localArquivo.concat(File.separator).concat(arquivoDeEntrada.getName().concat(SUFIXO_NOME_ARQUIVO_ABERTO_FLEX)));
        } else {
            ZipUtil.descompactarArquivoCIP(dadosDeRetornoDecriptografado, localArquivo.concat(File.separator).concat(arquivoDeEntrada.getName()));
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#abrirArquivoCIP(java.util.List)
     */
    public AbrirArquivoRetornoDto abrirArquivoCIP(List<String> listaArquivos) throws BancoobException {
        // TODO - Seu Adriano - Refatorar
        AbrirArquivoRetornoDto abrirArquivoRetornoDto = new AbrirArquivoRetornoDto();

        try {
            for (String prNmArquivo : listaArquivos) {
                abrirArquivoRetornoDto = new AbrirArquivoRetornoDto(prNmArquivo);
                abrirArquivoRetornoDto.setRetorno(abrirArquivoCIP(prNmArquivo));
            }
        } catch (ComumException e) {
            abrirArquivoRetornoDto.setRetorno(Boolean.FALSE);
            throw new AbrirArquivoErroCIPNegocioException("integracaocip.erro.abrir.arquivo.recebido.processamento", new Object[] { e.getCause(), abrirArquivoRetornoDto }, e);
        } catch (IOException e) {
            abrirArquivoRetornoDto.setRetorno(Boolean.FALSE);
            throw new AbrirArquivoErroCIPNegocioException("integracaocip.erro.abrir.arquivo.recebido.processamento", new Object[] { e.getCause(), abrirArquivoRetornoDto }, e);
        }

        return abrirArquivoRetornoDto;
    }

    /**
     * @param prNmArquivo
     * @return
     * @throws BancoobException
     * @throws IOException boolean
     * 
     */
    private boolean abrirArquivoCIP(String prNmArquivo) throws BancoobException, IOException {
        File arquivoDeEntrada = null;
        List<ParametroDDA> parametros = null;
        String pathAbertura = null;
        String pathSalva = null;

        /**********************************************************************************************************************
         * tratamento para adequação ao novo nome do arquivo a ser renomeado pela rotina de baixa do arquivo Adriano 29/12/2016
         **********************************************************************************************************************/

        debug("***** RECUPERANDO O ARQUIVO [ " + prNmArquivo + " ] DO FILESYSTEM");

        // recuperar o parâmetro com o local de recebimento dos arquivos

        parametros = parametroDao.listarParametros(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP, DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO);

        for (ParametroDDA prmt : parametros) {

            if (prmt.getId() == DIRETORIO_ARQUIVOS_RECEBIDOS_CIP) {
                pathAbertura = prmt.getValorParametro();
            } else if (prmt.getId() == DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO) {
                pathSalva = prmt.getValorParametro();
            }
        }

        arquivoDeEntrada = new File(pathAbertura.concat(File.separator).concat(prNmArquivo));

        salvarArquivoAberto(pathSalva, arquivoDeEntrada, Boolean.TRUE);

        return Boolean.TRUE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#gravarDetalhes(br.com.sicoob.sisbr.sicoobdda.integracaocip
     *      .negocio.vo.ArquivoProcessamentoVO, java.lang.String, int)
     */
    public void gravarDetalhes(ArquivoProcessamentoVO arquivoProc, String localDoArquivo, int qtdeRegistrosPorArquivo) throws BancoobException {
        // qual o tipo do arquivo [PRO|ERR|RET|DIS|ADD];
        switch (arquivoProc.getTipoArquivo()) {
        case RET:
            debug("**** ARQUIVO TIPO: RET ");
            trataArquivoRetornoDistribuicao(arquivoProc, localDoArquivo, qtdeRegistrosPorArquivo);
            break;
        case DIS:
            debug("**** ARQUIVO TIPO: DIS ");
            trataArquivoRetornoDistribuicao(arquivoProc, localDoArquivo, qtdeRegistrosPorArquivo);
            break;
        default:
            throw new ComumNegocioException("TipoArquivoRetornoEnum não identificado.");
        }
    }

    /**
     * {@inheritDoc}
     * 
     */
    private void trataArquivoRetornoDistribuicao(ArquivoProcessamentoVO arquivoProc, String nomeArquivo, int qtdeRegistrosPorArquivo) throws ComumException {
        int ordemInicial = Integer.valueOf(nomeArquivo.substring(nomeArquivo.indexOf(".xml") - 6, nomeArquivo.length() - 4));
        int ordemFinal = ordemInicial + qtdeRegistrosPorArquivo - 1;

        try {
            arquivoCipDao.excluirRegistrosDetalheRecebidos(arquivoProc.getIdArquivoRecebido(), ordemInicial, ordemFinal);
        } catch (ComumException e) {
            throw new ComumException("integracaocip.erro.exclusao.registros.arquivo.recebido", arquivoProc.getIdArquivoRecebido(), arquivoProc.getNmArquivoRecebido(), e.getMessage(), e);
        }

        List<LogDetRecebimentoArquivoDDA> listaLogDetRecebimentoArquivoDDA;
        try {
            listaLogDetRecebimentoArquivoDDA = extrairRegistrosDetalheArquivo(arquivoProc, nomeArquivo, ordemInicial);
            if (!ObjectUtil.isEmpty(listaLogDetRecebimentoArquivoDDA)) {
                gravarLogDetRecebimentoArquivoDDAEmLote(arquivoProc, listaLogDetRecebimentoArquivoDDA, ordemInicial);
            }
        } catch (ParserConfigurationException e) {
            throw new ComumException(ERRO_INCLUSAO_REGISTROS_ARQUIVO_RECEBIDO, arquivoProc.getIdArquivoRecebido(), arquivoProc.getNmArquivoRecebido(), e);
        } catch (SAXException e) {
            throw new ComumException(ERRO_INCLUSAO_REGISTROS_ARQUIVO_RECEBIDO, arquivoProc.getIdArquivoRecebido(), arquivoProc.getNmArquivoRecebido(), e);
        } catch (IOException e) {
            throw new ComumException(ERRO_INCLUSAO_REGISTROS_ARQUIVO_RECEBIDO, arquivoProc.getIdArquivoRecebido(), arquivoProc.getNmArquivoRecebido(), e);
        } catch (ComumException e) {
            throw new ComumException(ERRO_INCLUSAO_REGISTROS_ARQUIVO_RECEBIDO, arquivoProc.getIdArquivoRecebido(), arquivoProc.getNmArquivoRecebido(), e);
        }

    }

    /**
     * @param prArquivoProc
     * @param prNomeArquivo
     * @param ordemInicial
     * @return
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException List<LogDetRecebimentoArquivoDDA>
     * 
     */
    private List<LogDetRecebimentoArquivoDDA> extrairRegistrosDetalheArquivo(ArquivoProcessamentoVO prArquivoProc, String prNomeArquivo, int ordemInicial) throws ParserConfigurationException, SAXException,
            IOException {
        debug("**** INICIANDO EXTRAÇÃO DE REGISTROS DO ARQUIVO: " + prNomeArquivo + " ORDEMINICIAL: " + ordemInicial + "****");
        List<LogDetRecebimentoArquivoDDA> listaLogDetRecebimentoArquivoDDA = new ArrayList<LogDetRecebimentoArquivoDDA>();
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(Boolean.TRUE);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(new ScraperRegistros(prArquivoProc, listaLogDetRecebimentoArquivoDDA, ordemInicial));
        xmlReader.parse(prNomeArquivo);

        debug("**** EXTRAÇÃO CONCLUÍDA DO ARQUIVO: " + prNomeArquivo + " ORDEMINICIAL: " + ordemInicial + "****");
        return listaLogDetRecebimentoArquivoDDA;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#atualizarSituacaoProcessamentoArquivoRecebido(long)
     */
    public void atualizarSituacaoProcessamentoArquivoRecebido(long idArquivoRecebido, short codSituacaoProcessamentoArquivo) throws ComumException {
        try {
            arquivoCipDao.alterarSituacaoProcessamento(idArquivoRecebido, codSituacaoProcessamentoArquivo);
        } catch (ComumException e) {
            throw new ComumException("integracaocip.erro.atualizar.situacao.arquivo.cip", e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @throws BancoobException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#processarDetalhes(br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum,
     *      long, java.lang.String, java.lang.String, java.lang.String, int)
     */
    public void processarDetalhes(TipoInstanciaProcessamentoEnum tipoInstancia, long idLogRecebArq, long idLogDetRecebimentoInicial, long idLogDetRecebimentoFinal, String codTipoMensagem, int qtdeRegsPorTrans)
            throws BancoobException {

        if (codTipoMensagem.equals(TipoMensagemDDA.ADDA002)) {
            IntegracaoCipFabricaDelegates.getInstance().getMensagemConsultarPagadorDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA003)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoManutencaoPagadorDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
            arquivoCipDao.atualizarSituacaoDetalhesArquivoProcessados(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA106)) {
            IntegracaoCipFabricaDelegates.getInstance().getMensagemConsultaBoletoDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
            arquivoCipDao.atualizarSituacaoDetalhesArquivoProcessados(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA101RR2)) {
            IntegracaoCipFabricaDelegates.getInstance().getMensagemInclusaoBoletoDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA108RR2)) {
            IntegracaoCipFabricaDelegates.getInstance().getMensagemBaixaOperacionalBoletoDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA118RR2)) {
            IntegracaoCipFabricaDelegates.getInstance().getMensagemBaixaEfetivaBoletoDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA102RR2)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoAlteracaoBoletoDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA101RET)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoInclusaoBoletoDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA102RET)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoAlteracaoBoletoDelegate().processarRetornoAlteracaoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial,
                    idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA117)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoDecursoPrazoBaixaOperacionalDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA110RET)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoConsultaBoletoPagamentoDelegate().processarArquivoRetornoConsultaBoletoDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA118RET)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoBaixaEfetivaBoletoDelegate().processarArquivoRetornoBaixaEfetivaDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA108RET)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoBaixaOperacionalDelegate().processarArquivoRetornoBaixaOperacionalDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA127)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoBoletoPagamentoAbertoDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);
        } else if (codTipoMensagem.equals(TipoMensagemDDA.ADDA114RET)) {
            IntegracaoCipFabricaDelegates.getInstance().getArquivoBaixaOperacionalContingenciaDelegate().processarRetornoMensagemDDA(idLogRecebArq, idLogDetRecebimentoInicial,
                    idLogDetRecebimentoFinal);
        } else {
            List<String> listaDetalhesArquivo = arquivoCipDao.listarDetalhesArquivoRecebido(idLogRecebArq, idLogDetRecebimentoInicial, idLogDetRecebimentoFinal);

            if (ObjectUtil.isEmpty(listaDetalhesArquivo)) {
                debug("Nenhum LogRecebimentoArquivo encontrado para processamento PARÂMETROS[ID ARQUIVO: " + idLogRecebArq + " PR_INICIAL: " + idLogDetRecebimentoInicial + " PR_FINAL: "
                        + idLogDetRecebimentoFinal + " INSTANCIA: " + tipoInstancia.getDescricao() + " CODMENSAGEM : " + codTipoMensagem + "]");
            } else {
                ProcessarRetornoDDADelegate processarRetornoDelegate = IntegracaoCipFabricaDelegates.getInstance().getProcessarRetornoDDADelegate();
                try {
                    int indexInicio = 0;
                    int indexFim = getIndexFim(qtdeRegsPorTrans, listaDetalhesArquivo, indexInicio);

                    while (listaDetalhesArquivo.size() >= indexFim) {
                        processarRetornoDelegate.processarMensagemRecebida(listaDetalhesArquivo.subList(indexInicio, indexFim));
                        indexInicio = indexFim;

                        if (indexInicio >= listaDetalhesArquivo.size()) {
                            break;
                        }

                        indexFim = getIndexFim(qtdeRegsPorTrans, listaDetalhesArquivo, indexInicio);
                    }
                } catch (BancoobException e) {
                    getLogger().erro(e, "***** FALHA NO PROCESSAMENTO DOS DETALHES DO ARQUIVO:[" + idLogRecebArq + "] MOTIVO[" + e.getMessage() + "]");
                    throw new ComumException("integracaocip.erro.processamento.grava.efetiva.detalhe.arquiv.cip", e.getMessage(), e);
                }
            }
        }
    }

    /**
     * @param qtdeRegsPorTrans
     * @param listaDetalhesArquivo
     * @param indexInicio
     * @return int
     * 
     */
    private int getIndexFim(int qtdeRegsPorTrans, List<String> listaDetalhesArquivo, int indexInicio) {
        return (indexInicio + qtdeRegsPorTrans) < listaDetalhesArquivo.size() ? indexInicio + qtdeRegsPorTrans : listaDetalhesArquivo.size();
    }

    /**
     * Método responsável por preparar a lista de LogDetRecebimentoArquivoDDA para gravar em lote.
     * 
     * @param prArquivoProc
     * @param listaLogDetRecebimentoArquivoDDA
     * @param seqInicial
     * @throws ComumException void
     * 
     */
    private void gravarLogDetRecebimentoArquivoDDAEmLote(ArquivoProcessamentoVO prArquivoProc, List<LogDetRecebimentoArquivoDDA> listaLogDetRecebimentoArquivoDDA, int ordemInicial) throws ComumException {
        debug("**** MONTANDO LOTE DE REGISTROS PARA PERSISTENCIA. ARQUIVO: " + prArquivoProc.getNmArquivoRecebido() + " OrdemInicial: " + ordemInicial + "****");
        List<Object[]> listaLoteParametros = new ArrayList<Object[]>();
        Object[] parametros = null;

        for (LogDetRecebimentoArquivoDDA logDetalhe : listaLogDetRecebimentoArquivoDDA) {
            parametros = new Object[4];

            parametros[0] = prArquivoProc.getIdArquivoRecebido();
            parametros[1] = logDetalhe.getDescXMLMensagemRecebida();
            parametros[2] = logDetalhe.getBolProcessado();
            parametros[3] = logDetalhe.getNumOrdemProcessamento();

            listaLoteParametros.add(parametros);
            parametros = null;
        }
        arquivoCipDao.gravaDetalheEmLote(listaLoteParametros);
        debug("**** LOTE DE REGISTROS GRAVADOS. ARQUIVO: " + prArquivoProc.getNmArquivoRecebido() + " OrdemInicial: " + ordemInicial + "****");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#conciliarDetalhesGravados(long)
     */
    public void conciliarDetalhesGravados(long idArquivoRecebido) throws BancoobException {
        int qtdRegistrosArquivo = arquivoCipDao.obterQtdRegistroArquivo(idArquivoRecebido);

        if (!ObjectUtil.isEmpty(qtdRegistrosArquivo) && qtdRegistrosArquivo == arquivoCipDao.obterCountDetGravados(idArquivoRecebido)) {
            atualizarSituacaoProcessamentoArquivoRecebido(idArquivoRecebido, SituacaoProcessamentoArquivo.REGISTROS_DETALHADOS);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ArquivoRecebidoCIPProcessadorServico#salvarMensagemProtocoloErro(java.lang.String,
     *      long, br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum)
     */
    public void atualizarMensagemProtocoloErro(String nmArquivo, long idArquivoEnvio, TipoArquivoRetornoEnum tipoArquivoEnum) throws ComumException {
        List<Long> listaMensagensIds = mensagemDDADao.listarIdsMensagensEnviadas(idArquivoEnvio);
        if (ObjectUtil.isEmpty(listaMensagensIds)) {
            // TODO - George - Refatorar mensagem de exceção
            throw new ComumException("Integracao.erro.nenhuma.menagem.recuperada", idArquivoEnvio, "Deve retornar pelo menos uma menasgem.");
        }

        List<Object[]> listaParametrosDasMensagens = new ArrayList<Object[]>();
        for (Long idMensagemDDA : listaMensagensIds) {
            listaParametrosDasMensagens.add(new Object[] { nmArquivo, idMensagemDDA });
        }

        switch (tipoArquivoEnum) {
        case PRO:
            arquivoCipDao.atualizarMensagensComProtocolo(listaParametrosDasMensagens);
            break;
        case ERR:
            arquivoCipDao.atualizarMensagensComErroProtocolo(listaParametrosDasMensagens);
            break;
        default:
            break;
        }
    }
}
