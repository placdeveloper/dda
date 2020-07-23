/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb
 * Arquivo:         ArquivoRecebidoServicoEJB.java
 * Data Criação:    Jan 27, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ejb;

import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.LOCAL_ARQUIVO_TEMPORARIOS;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.QTD_MAX_REGISTROS_POR_STEP;
import static br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA.TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO;

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
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.LeituraADDARR2756Exception;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoInstanciaProcessamentoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.saxhandle.ScraperRegistroToMapString;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.interfaces.ArquivoRecebidoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.ArquivoRecebidoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalDaoFactory;

/**
 * ArquivoRecebidoServicoEJB
 * 
 * @author Samuell.Ramos
 */
@Stateless
@Local(ArquivoRecebidoServicoLocal.class)
public class ArquivoRecebidoServicoEJB extends OperacionalCrudServicoEJB<LogRecebimentoArquivoDDA> implements ArquivoRecebidoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = OperacionalDaoFactory.class)
    private ArquivoRecebidoDao dao;

    @Dao(entityManager = "em", fabrica = ComumDaoFactory.class)
    private ParametroDao parametroDao;

    private ArquivoRecebidoCIPProcessadorServicoDelegate arquivoDelegate = IntegracaoCipFabricaDelegates.getInstance().getArquivoRecebidoCIPProcessadorServicoDelegate();

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.comum.negocio.servicos.ejb.ComumCrudServicoEJB#getDAO()
     */
    @Override
    protected ArquivoRecebidoDao getDAO() {
        return dao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico#carregarListasArquivoRecebido()
     */
    public ArquivoRecebidoDto carregarListasArquivoRecebido() throws ComumException {
        return new ArquivoRecebidoDto(getDAO().listarTipoArquivo(), getDAO().listarTipoMensagemAgenAdda(), getDAO().listarSitucaoArquivo());
    }

    /**
     * @param idLogRecebimentoArquivoDDA
     * @return LogRecebimentoArquivoDDA
     * @throws ComumException LogRecebimentoArquivoDDA
     * 
     */
    public ArquivoRecebidoDto obterArquivoRecebido(Long idLogRecebimentoArquivoDDA) throws ComumException {
        return getDAO().obterArquivoRecebido(idLogRecebimentoArquivoDDA);
    }

    /**
     * @param idDetLogRecebimentoArquivoDDA
     * @return LogDetRecebimentoArquivoDDA
     * @throws ComumException LogDetRecebimentoArquivoDDA
     */
    public LogDetRecebimentoArquivoDDA obterLogDetRecebimentoArquivoDDA(Long idDetLogRecebimentoArquivoDDA) throws ComumException {
        LogDetRecebimentoArquivoDDA logDetRecArquivoDDA = em.find(LogDetRecebimentoArquivoDDA.class, idDetLogRecebimentoArquivoDDA);
        return logDetRecArquivoDDA;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico#alterarSituacaoArquivoRecebido(java.lang.Long, long)
     */
    public void alterarSituacaoArquivoRecebido(long idLogRecebimentoArquivoDDA, short codSituacao) throws ComumNegocioException, ComumException {
        validarAlterarSituacaoArquivo(idLogRecebimentoArquivoDDA, codSituacao);
        getDAO().alterarSituacaoArquivo(idLogRecebimentoArquivoDDA, codSituacao);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico#alterarSituacaoRegistro(br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA)
     */
    public void alterarSituacaoRegistro(LogDetRecebimentoArquivoDDA logDetRecebimentoArquivoDDA) throws ComumNegocioException, ComumException {
        getDAO().alterarSituacaoRegistro(logDetRecebimentoArquivoDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico#descriptografarArquivo(br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto)
     */
    public ArquivoRecebidoDto descriptografarArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException {
        validarProcessamentoArquivo(arquivoDto, SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);

        String pathOrigemArquivo = null;
        String pathDestinoArquivo = null;
        Integer tempoMaxUltimaAlteracaoNoArquivo = null;

        for (ParametroDDA valorpar : parametroDao.listarParametros(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP, DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO,
                TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO)) {
            if (valorpar.getId() == DIRETORIO_ARQUIVOS_RECEBIDOS_CIP) {
                pathOrigemArquivo = valorpar.getValorParametro() + File.separator + arquivoDto.getDescNomeArquivoRecebido();
            } else if (valorpar.getId() == DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO) {
                pathDestinoArquivo = valorpar.getValorParametro();
            } else if (valorpar.getId() == TEMPO_MAXIMO_APOS_ULTIMA_ALTERACAO_NO_ARQUIVO) {
                tempoMaxUltimaAlteracaoNoArquivo = Integer.valueOf(valorpar.getValorParametro());
            }
        }

        TipoSituacaoArquivo situacaoArquivo = arquivoDelegate.abrirArquivoParaProcessamento(arquivoDto.getIdLogRecebimentoArqDDA(), pathOrigemArquivo, pathDestinoArquivo,
                tempoMaxUltimaAlteracaoNoArquivo);
        validaSituacaoArquivo(situacaoArquivo);
        return obterArquivoRecebido(arquivoDto.getIdLogRecebimentoArqDDA());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico#gravarDetalheArquivo(br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto)
     */
    public ArquivoRecebidoDto gravarDetalheArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException {
        validarProcessamentoArquivo(arquivoDto, SituacaoProcessamentoArquivo.ARQUIVO_ABERTO);

        String pathOrigemArquivo = null;
        String pathDestinoArquivosTmp = null;
        Integer qtdPorArquivo = null;

        for (ParametroDDA valorpar : parametroDao.listarParametros(DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO, QTD_MAX_REGISTROS_POR_STEP, LOCAL_ARQUIVO_TEMPORARIOS)) {
            if (valorpar.getId() == DIRETORIO_ARQUIVOS_RECEBIDOS_CIP_POS_ABERTO) {
                pathOrigemArquivo = valorpar.getValorParametro();
            } else if (valorpar.getId() == QTD_MAX_REGISTROS_POR_STEP) {
                qtdPorArquivo = Integer.valueOf(valorpar.getValorParametro());
            } else if (valorpar.getId() == LOCAL_ARQUIVO_TEMPORARIOS) {
                pathDestinoArquivosTmp = valorpar.getValorParametro();
            }
        }

        ArquivoProcessamentoVO arquivoParaProc = prepraraArquivoParaProc(arquivoDto);

        List<String> listaArquivosTemp = extrairRegistros(pathOrigemArquivo, pathDestinoArquivosTmp, arquivoParaProc, qtdPorArquivo);

        getDAO().atualizarQtdeTotalRegistrosArquivoRecebido(arquivoDto.getIdLogRecebimentoArqDDA(), getQtdeTotalRegistros(listaArquivosTemp));

        for (String arquivoTemp : listaArquivosTemp) {
            arquivoDelegate.gravarDetalhes(arquivoParaProc, arquivoTemp, qtdPorArquivo);
        }

        getDAO().alterarSituacaoArquivo(arquivoDto.getIdLogRecebimentoArqDDA(), SituacaoProcessamentoArquivo.REGISTROS_DETALHADOS);

        return obterArquivoRecebido(arquivoDto.getIdLogRecebimentoArqDDA());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.negocio.servicos.ArquivoRecebidoServico#processarArquivo(br.com.sicoob.sisbr.sicoobdda.operacional.dto.ArquivoRecebidoDto)
     */
    public ArquivoRecebidoDto processarArquivo(ArquivoRecebidoDto arquivoDto) throws BancoobException {
        validarProcessamentoArquivo(arquivoDto, SituacaoProcessamentoArquivo.REGISTROS_DETALHADOS);

        arquivoDelegate.atualizarSituacaoProcessamentoArquivoRecebido(arquivoDto.getIdLogRecebimentoArqDDA(), SituacaoProcessamentoArquivo.ARQUIVO_EM_PROCESSAMENTO);

        ArquivoProcessamentoVO arquivoParaProc = getDAO().obterDadosProcessamentoArquivo(arquivoDto.getIdLogRecebimentoArqDDA());
        arquivoDelegate.processarDetalhes(TipoInstanciaProcessamentoEnum.getTipoInstancia(arquivoDto.getCodTipoMensagem()), arquivoDto.getIdLogRecebimentoArqDDA(), arquivoParaProc
                .getIdDetLogRecebimentoIni(), arquivoParaProc.getIdDetLogRecebimentoFin(), arquivoDto.getCodTipoMensagem(), arquivoParaProc.getQtdRegistros().intValue());

        getDAO().alterarSituacaoArquivo(arquivoDto.getIdLogRecebimentoArqDDA(), SituacaoProcessamentoArquivo.ARQUIVO_PROCESSADO);

        return obterArquivoRecebido(arquivoDto.getIdLogRecebimentoArqDDA());
    }

    /**
     * Método responsável por dividir um arquivo baseado na quantidade de registros por transação
     * 
     * @param pathOrigem
     * @param pathDestino
     * @param arqProc
     * @param qtdRegsPorArq
     * @return
     * @throws ComumException
     * @throws ComumNegocioException List<String>
     * 
     */
    private List<String> extrairRegistros(String pathOrigem, String pathDestino, ArquivoProcessamentoVO arqProc, int qtdRegsPorArq) throws ComumException, ComumNegocioException {
        List<String> listaArquivosGerados = new ArrayList<String>();
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(Boolean.TRUE);
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new ScraperRegistroToMapString(pathDestino, arqProc, qtdRegsPorArq, listaArquivosGerados, Boolean.TRUE));
            xmlReader.parse(pathOrigem + File.separator + arqProc.getNmArquivoRecebido());
        } catch (LeituraADDARR2756Exception e) {
            tratarExcecaoLeituraDDARR2756(arqProc, e);
        } catch (SAXException e) {
            throw new ComumException("integracaocip.erro.extrair.registros.saxparser", e);
        } catch (ParserConfigurationException e) {
            throw new ComumException("integracaocip.erro.extrair.registros.saxparser", e);
        } catch (IOException e) {
            throw new ComumException("integracaocip.erro.extrair.registros.saxparser", e);
        }
        return listaArquivosGerados;
    }

    /**
     * Método responsável por
     * 
     * @param arqProc
     * @param e
     * @throws ComumNegocioException
     * @throws ComumException void
     * 
     */
    private void tratarExcecaoLeituraDDARR2756(ArquivoProcessamentoVO arqProc, LeituraADDARR2756Exception e) throws ComumNegocioException, ComumException {
        try {
            arquivoDelegate.atualizarSituacaoProcessamentoArquivoRecebido(arqProc.getIdArquivoRecebido(), SituacaoProcessamentoArquivo.ARQUIVO_ADDARR2_REJEITADO_756);
            throw new ComumNegocioException("integracaocip.erro.gravardetalhe.arquivo.bancoob", new Object[] { arqProc.getTipoDaMensagem() }, e);
        } catch (ComumException e1) {
            throw new ComumException("integracaocip.erro.gravardetalhe.extrair.registros.atualizar.status.arquivo", e1);
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
        if (listaArquivosGerados.isEmpty()) {
            return 0;
        }
        int quantidade = Integer.parseInt(listaArquivosGerados.get(listaArquivosGerados.size() - 1));
        listaArquivosGerados.remove(listaArquivosGerados.size() - 1);
        return quantidade;
    }

    /**
     * 
     * @param arquivoDto
     * @return ArquivoParaProc
     * 
     */
    private ArquivoProcessamentoVO prepraraArquivoParaProc(ArquivoRecebidoDto arquivoDto) {
        ArquivoProcessamentoVO arquivoProc = new ArquivoProcessamentoVO();
        arquivoProc.setIdArquivoRecebido(arquivoDto.getIdLogRecebimentoArqDDA());
        arquivoProc.setTipoArquivo(TipoArquivoRetornoEnum.valueOf(arquivoDto.getCodTipoArquivo()));
        arquivoProc.setNmArquivoRecebido(arquivoDto.getDescNomeArquivoRecebido());
        arquivoProc.setIdArquivoEnviado(arquivoDto.getIdLogEnvioArqDDA());
        arquivoProc.setTipoDaMensagem(arquivoDto.getCodTipoMensagem());
        arquivoProc.setCodSituacaoProcessamentoArquivo(arquivoDto.getCodSituacaoProcessamentoArquivo());
        return arquivoProc;
    }

    /**
     * Método responsável por
     * 
     * @param arquivoDto
     * @throws ComumNegocioException void
     * 
     */
    private void validarProcessamentoArquivo(ArquivoRecebidoDto arquivoDto, short codSituacaoProcessamento) throws ComumNegocioException {
        if (ObjectUtil.isNull(arquivoDto) || ObjectUtil.isEmpty(arquivoDto.getIdLogRecebimentoArqDDA()) || ObjectUtil.isEmpty(arquivoDto.getDescNomeArquivoRecebido())) {
            throw new ComumNegocioException("integracaocip.erro.arquivo.nao.informado");
        } else if (arquivoDto.getCodSituacaoProcessamentoArquivo() != codSituacaoProcessamento) {
            throw new ComumNegocioException("integracaocip.erro.situacao.arquivo.invalida");
        }
    }

    /**
     * Método responsável por
     * 
     * @param idLogRecebimentoArquivoDDA
     * @param codSituacao
     * @throws ComumNegocioException void
     * 
     */
    private void validarAlterarSituacaoArquivo(long idLogRecebimentoArquivoDDA, short codSituacao) throws ComumNegocioException {
        if (ObjectUtil.isEmpty(idLogRecebimentoArquivoDDA)) {
            throw new ComumNegocioException("integracaocip.erro.arquivo.nao.informado");
        } else if (ObjectUtil.isEmpty(codSituacao)) {
            throw new ComumNegocioException("integracaocip.erro.situacao.arquivo.nao.informada");
        }
    }

    /**
     * Método responsável por
     * 
     * @param situacaoArquivo void
     * 
     */
    private void validaSituacaoArquivo(TipoSituacaoArquivo situacaoArquivo) throws ComumNegocioException {
        if (!situacaoArquivo.equals(TipoSituacaoArquivo.ARQUIVO_OK)) {
            throw new ComumNegocioException(situacaoArquivo.getDescricao());
        }
    }
}
