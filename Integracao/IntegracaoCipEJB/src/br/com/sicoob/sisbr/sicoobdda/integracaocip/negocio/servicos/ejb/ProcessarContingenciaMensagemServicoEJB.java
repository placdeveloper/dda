/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ProcessarContingenciaMensagemServicoEJB.java
 * Data Criação:    Jan 20, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.io.FileWriter;
import java.io.IOException;
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
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.LogErroSWSDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.DateUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarContingenciaMensagemServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ProcessarRetornoDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;

/**
 * ProcessarContingenciaMensagemServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ ProcessarContingenciaMensagemServicoLocal.class })
public class ProcessarContingenciaMensagemServicoEJB extends IntegracaoCipServicoEJB implements ProcessarContingenciaMensagemServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ParametroDao parametroDao;

    @EJB
    private ProcessarRetornoDDAServicoLocal processarRetorno;

    @EJB
    private ProcessarContingenciaMensagemServicoLocal processarContingencia;

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarContingenciaMensagemServico#processarMensagemContingencia(java.util.List)
     */
    public void processarMensagemContingencia(List<Long> listaIdMensagem) throws BancoobException {
        LogErroSWSDto log = new LogErroSWSDto(listaIdMensagem.size(), "integracaocip.erro.processar.envio.msgs");
        for (Long idMensagem : listaIdMensagem) {
            try {
                processarContingencia.processarMensagemContingencia(idMensagem);
            } catch (BancoobException e) {
                getLogger().erro(e, log.getLogErroComChave(e.getMessage(), idMensagem.toString()));
                log.incluirErro(e, idMensagem.toString());
            }
        }
        log.validarPossuiErro();
    }

    /**
     * Método responsável por
     * 
     * @param log
     * @param mensagemDDA
     * @throws ComumException void
     * 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processarMensagemContingencia(Long idMensagem) throws BancoobException {
        MensagemDDA mensagemDDA = dao.obterMensagemErroLock(idMensagem);
        if (!ObjectUtil.isNull(mensagemDDA)) {
            processarRetorno.processarMensagemRecebidaContingencia(mensagemDDA);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ProcessarContingenciaMensagemServico#processarMensagemContingenciaBatch(java.util.List)
     */
    public String processarMensagemContingenciaBatch(List<Long> listaIdMensagem, DateTimeDB dataMovimento, String codTipoMensagem) throws BancoobException {
        FileWriter arquivoErro = null;
        String nomeArquivo = null;
        try {
            for (Long idMensagem : listaIdMensagem) {
                try {
                    processarContingencia.processarMensagemContingencia(idMensagem);
                } catch (BancoobException e) {
                    nomeArquivo = gerarNomeArquivoErrroContingencia(dataMovimento, codTipoMensagem);
                    getLogger().erro(e, "CONTINGÊNCIA BATCH: Erro ao postar mensagem.\n Id:" + idMensagem);
                    arquivoErro = inserirErroArquivo(dataMovimento, codTipoMensagem, arquivoErro, idMensagem, nomeArquivo, e.getMessage());
                }
            }
        } catch (IOException e) {
            getLogger().erro(e, "CONTINGÊNCIA BATCH: Erro no arquivo.");
        } finally {
            ArquivoUtil.fecharFileWriter(arquivoErro);
        }
        return nomeArquivo;
    }

    /**
     * @param dataMovimento
     * @param codTipoMensagem
     * @param arquivoErro
     * @param idMensagem
     * @param nomeArquivo
     * @param descErro
     * @return
     * @throws ComumException
     * @throws IOException FileWriter
     * 
     */
    private FileWriter inserirErroArquivo(DateTimeDB dataMovimento, String codTipoMensagem, FileWriter arquivoErro, Long idMensagem, String nomeArquivo, String descErro) throws ComumException,
            IOException {
        if (ObjectUtil.isNull(arquivoErro)) {
            String urlArquivo = parametroDao.obterValor(ParametroDDA.DIRETORIO_ARQUIVOS_ERRO_CONTINGENCIA_MENSAGEM, Constantes.ID_BANCOOB);
            arquivoErro = ArquivoUtil.criarArquivo(urlArquivo, nomeArquivo);
            arquivoErro.write("MENSAGENS COM ERRO NA CONTINGÊNCIA BATCH - " + codTipoMensagem + "-" + DateUtil.formatarDataLocalePtBr(new DateTimeDB()) + Constantes.STRING_QUEBRA_LINHA_TXT);
        }
        arquivoErro.write(idMensagem.toString());
        arquivoErro.write(": " + descErro + Constantes.STRING_QUEBRA_LINHA_TXT);
        return arquivoErro;
    }

    /**
     * Método responsável por
     * 
     * @param dataMovimento
     * @param codTipoMensagem
     * @return String
     * 
     */
    private String gerarNomeArquivoErrroContingencia(DateTimeDB dataMovimento, String codTipoMensagem) {
        return DateUtil.formatarDataArquivo(dataMovimento) + Constantes.STRING_SUBLINHADO + codTipoMensagem + Constantes.STRING_SUBLINHADO + DateUtil.formatarDataHoraArquivo(new Date()) + ".txt";
    }
}
