package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.io.File;
import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipArquivoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarEnvioArquivoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.NomeArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;

/**
 * ProcessarEnvioArquivoServicoEJB
 * 
 * @author George.santos
 */
@Stateless
@Local({ ProcessarEnvioArquivoServicoLocal.class })
public class ProcessarEnvioArquivoServicoEJB extends IntegracaoCipServicoEJB implements ProcessarEnvioArquivoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
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
     * @return the integracaoCipDao
     */
    public IntegracaoCipDao getDao() {
        return dao;
    }

    /**
     * @return ParametroDao
     */
    public ParametroDao getParametroDao() {
        return parametroDao;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico#registrarArquivo(java.util.List, java.lang.String)
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public void registrarArquivo() throws ComumException {
        getLogger().info("[SDDA] - REGISTRAR_ARQUIVO_DDA - Inicio da gravação no DDA.LOGENVIOARQUIVODDA ");
        Long qtdMaxRegistrosArquivos = Long.valueOf(parametroDao.obterValor(ParametroDDA.QTD_MAX_REGISTROS_ARQUIVOS_ENVIO, Constantes.ID_SICOOB));
        Long qtdMaximaArquivosProcessamento = Long.valueOf(parametroDao.obterValor(ParametroDDA.QTDE_MAXIMA_ARQUIVOS_PROCESSAMENTO_SWS_ENVIO_ARQUIVO, Constantes.ID_SICOOB));
        Long qtdMaximaArquivosProcessadosPorInteracao = Long.valueOf(parametroDao.obterValor(ParametroDDA.QTDE_MAXIMA_ARQUIVOS_PROCESSADOS_POR_INTERACAO, Constantes.ID_SICOOB));


        dao.incluirLogEnvioArquivoDDA(qtdMaxRegistrosArquivos, qtdMaxRegistrosArquivos * qtdMaximaArquivosProcessamento, qtdMaximaArquivosProcessadosPorInteracao);
        getLogger().info("[SDDA] - REGISTRAR_ARQUIVO_DDA  - Gravação feita com sucesso!");
    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumNegocioException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico#gerarArquivo(br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA)
     */
    public void gerarArquivo(Long idLogEnvioArquivoDDA) throws ComumException, ComumNegocioException {
        getLogger().info("Inicio da pesquisa ObterLogEnvioArquivoDDA");
        LogEnvioArquivoDDA logEnvioArquivoDDA = getDao().obterLogEnvioArquivoDDA(idLogEnvioArquivoDDA);
        getLogger().info("Fim da pesquisa ObterLogEnvioArquivoDDA - LogEnvioArquivoDDA - " + logEnvioArquivoDDA.toString());

        getLogger().info("Obtendo o SisArq");
        Object sisArq = getDelegate(logEnvioArquivoDDA.getTipoMensagem().getCodTipoMensagem()).obterSISARQ(logEnvioArquivoDDA.getId());
        getLogger().info("pesquisa feita com sucesso!");

        getLogger().info("Inicio da gravacao do arquivo na pasta PRE-Enviar");
        gravarArquivoAbertoDiretorio(sisArq, ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP, logEnvioArquivoDDA);
        getLogger().info("atualizacao feita com sucesso na pasta PRE-Enviar");

        getLogger().info("Inicio da atualizacao da DataHoraArquivo");
        getDao().atualizarDataHoraArquivo(logEnvioArquivoDDA.getId());
        getLogger().info("atualizacao feita com sucesso! - atualizarDataHoraArquivo");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico#postarArquivo(java.lang.Long)
     */
    public void postarArquivo(Long idLogEnvioArquivoDDA) throws ComumException {
        getLogger().info("Inicio da pesquisa ObterLogEnvioArquivoDDA");
        LogEnvioArquivoDDA logEnvioArquivoDDA = getDao().obterLogEnvioArquivoDDA(idLogEnvioArquivoDDA);
        getLogger().info("Fim da pesquisa ObterLogEnvioArquivoDDA - LogEnvioArquivoDDA - " + logEnvioArquivoDDA.toString());

        String nomeArquivoPastaAntesEnvio = getDiretorio(ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP) + "/" + logEnvioArquivoDDA.getDescNomeArquivoEnviado();
        getLogger().info("nome do Arquivo que esta na pasta PRE-Enviar - " + nomeArquivoPastaAntesEnvio);

        getLogger().info("Inicio da gravacao do arquivo na pasta Enviar");
        String enconding = getParametroDao().obterValor(ParametroDDA.ENCONDING_ENVIO_ARQUIVO, Constantes.ID_SICOOB);
        postarArquivoDiretorio(ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_CIP, logEnvioArquivoDDA, nomeArquivoPastaAntesEnvio, enconding);
        getLogger().info("Gravacao feita com sucesso!");

        getLogger().info("Inicio da atualizacao da DataHoraEnvio");
        getDao().atualizarDataHoraEnvio(logEnvioArquivoDDA.getId());
        getLogger().info("Atualizacao feita com sucesso! - atualizarDataHoraEnvio");

        getLogger().info("Inicio da atualizacao da DataHoraMensagem");
        getDao().atualizarDataHoraMensagem(logEnvioArquivoDDA.getId(), logEnvioArquivoDDA.getTipoMensagem().getCodTipoMensagem());
        getLogger().info("Atualizacao feita com sucesso! - atualizarDataHoraMensagem");

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarEnvioArquivoServico#tratarArquivosComErro(java.lang.Long, java.util.Date)
     */
    public void tratarArquivosComErro(Long idLogEnvioArquivoDDA, Date dataMovimento) throws ComumException {
        LogEnvioArquivoDDA logEnvioArquivoDDA = getDao().obterLogEnvioArquivoDDA(idLogEnvioArquivoDDA);

        if (DateUtil.maiorQue(dataMovimento, logEnvioArquivoDDA.getDataMovimento())) {
            String nomeArquivoEnvio = logEnvioArquivoDDA.getDescNomeArquivoEnviado();
            logEnvioArquivoDDA.setDataMovimento(new DateTimeDB(dataMovimento.getTime()));
            logEnvioArquivoDDA.setDescNomeArquivoEnviado(NomeArquivoUtil.getNomeArquivoEnvio(logEnvioArquivoDDA.getTipoMensagem().getCodTipoMensagem(),
                    getNumSequencial(logEnvioArquivoDDA.getTipoMensagem().getCodTipoMensagem(), dataMovimento), DateUtil.getDateTimeDB(dataMovimento)));
            getDao().atualizarLogEnvioArquivoDDA(logEnvioArquivoDDA);

            excluirArquivoPastaPreEnviar(nomeArquivoEnvio);
        }
    }

    /**
     * Método responsável por
     * 
     * @param logEnvioArquivoDDA void
     * @param nomeArquivoEnvio
     * 
     */
    private void excluirArquivoPastaPreEnviar(String nomeArquivoEnvio) {
        String nomeArquivoPastaAntesEnvio = getDiretorio(ParametroDDA.DIRETORIO_ENVIO_ARQUIVOS_ANTES_DA_CIP) + "/" + nomeArquivoEnvio;
        File fileOrigem = new File(nomeArquivoPastaAntesEnvio);

        if (fileOrigem.isFile()) {
            getLogger().info("Arquivo que sera excluido - " + nomeArquivoPastaAntesEnvio);
            fileOrigem.delete();
            getLogger().info("Arquivo excluido com sucesso!");
        }
    }

    /**
     * Método responsável por
     * 
     * @param logEnvioArquivoDDA
     * @return Integer
     * @throws ComumException
     * 
     */
    private Integer getNumSequencial(String codTipoMensagem, Date dataMovimento) throws ComumException {
        return getDao().obterUltimoSequencialArquivo(codTipoMensagem, dataMovimento) == null ? 1 : getDao().obterUltimoSequencialArquivo(codTipoMensagem, dataMovimento) + 1;

    }

    /**
     * Método responsável por recuperar o Delegate (polimorficamente) do arquivo a ser processada.
     * 
     * @param codTipoMensagem
     * @return IntegracaoCipArquivoDelegate<IntegracaoCipServico>
     * @throws ComumException
     * 
     */
    private IntegracaoCipArquivoDelegate<IntegracaoCipServico> getDelegate(String codTipoMensagem) throws ComumException {
        return IntegracaoCipFabricaDelegates.getInstance().getArquivoDelegate(codTipoMensagem);
    }
}
