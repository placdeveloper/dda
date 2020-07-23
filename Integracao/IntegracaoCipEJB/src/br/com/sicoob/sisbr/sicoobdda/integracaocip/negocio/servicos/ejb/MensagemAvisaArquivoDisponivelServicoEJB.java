package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoProcessamentoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoArquivo;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ArquivoRecebidoCIPProcessadorServicoDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemAvisaArquivoDisponivelServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0015.GEN0015ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemAvisaArquivoDisponivelServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemAvisaArquivoDisponivelServicoLocal.class })
public class MensagemAvisaArquivoDisponivelServicoEJB extends IntegracaoCipServicoEJB implements MensagemAvisaArquivoDisponivelServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Override
    protected EntityManager getEm() {
        return em;
    }

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoCipDao arquivoCipDao;

    private ArquivoRecebidoCIPProcessadorServicoDelegate arquivoRecebido;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagem) throws ComumException {
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(java.lang.Long,
     *      br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        GEN0015ComplexType gen0015ComplexType = (GEN0015ComplexType) conteudoMsg;

        String tipoMensagem = getTipoMensagemPorNomeArquivo(gen0015ComplexType.getNomArq());
        TipoMensagemDDA tipoMensagemDDA = getEm().find(TipoMensagemDDA.class, tipoMensagem);

        LogRecebimentoArquivoDDA logRecebimentoArquivoDDA = gravarLogRecebimentoArquivoDDA(gen0015ComplexType, tipoMensagem, tipoMensagemDDA);

        if (isArquivoRespostaProErro(gen0015ComplexType.getNomArq())) {
            atualizarMensagemProtocoloErro(gen0015ComplexType, getIdLogEnvioArquivoDDA(logRecebimentoArquivoDDA));
            arquivoCipDao.atualizarDataHoraProcLogRecebimentoArquivo(logRecebimentoArquivoDDA.getId());
        }
    }

    /**
     * Método responsável por
     * 
     * @param logRecebimentoArquivoDDA
     * @return Long
     * 
     */
    private Long getIdLogEnvioArquivoDDA(LogRecebimentoArquivoDDA logRecebimentoArquivoDDA) {
        return !ObjectUtil.isNull(logRecebimentoArquivoDDA.getLogEnvioArquivoDDA()) ? logRecebimentoArquivoDDA.getLogEnvioArquivoDDA().getId() : null;
    }

    /**
     * Método responsável por grarvar o logRecebimento do Arquivo
     * 
     * @param gen0015ComplexType
     * @param tipoMensagem
     * @param tipoMensagemDDA
     * @throws ComumException void
     * 
     */
    private LogRecebimentoArquivoDDA gravarLogRecebimentoArquivoDDA(GEN0015ComplexType gen0015ComplexType, String tipoMensagem, TipoMensagemDDA tipoMensagemDDA)
            throws ComumException {
        LogRecebimentoArquivoDDA logRecebimento = null;
        if (!ObjectUtil.isNull(tipoMensagemDDA)) {
            logRecebimento = criarLogRecebimentoArquivo(gen0015ComplexType, tipoMensagemDDA);
            getEm().persist(logRecebimento);
            return logRecebimento;
        } else {
            getLogger().alerta("Arquivo ignorado pois não é tratado pelo SicoobDDA: " + tipoMensagem);
        }
        return null;
    }

    /**
     * Método responsável por Atualizar a DATAHORAPROTOCOLO e o NUMOPERACAO da tabela DDA.MENSAGEMDDA quando o arquivo for PROTOCOLO ou ERRO
     * 
     * @param gen0015ComplexType
     * @throws ComumException void
     * 
     */
    private void atualizarMensagemProtocoloErro(GEN0015ComplexType gen0015ComplexType, Long idLogEnvioArquivoDDA) throws ComumException {
        if (!ObjectUtil.isNull(idLogEnvioArquivoDDA)) {
            getArquivoRecebido().atualizarMensagemProtocoloErro(gen0015ComplexType.getNomArq(), idLogEnvioArquivoDDA, gen0015ComplexType.getNomArq().endsWith(TipoArquivo.PROTOCOLO) ? TipoArquivoRetornoEnum.PRO
                    : TipoArquivoRetornoEnum.ERR);
        } else {
            debug("Não existe o logEnvioArquivoDDA do arquivo PRO e ERR");
        }
    }

    /**
     * Método responsável por
     * 
     * @param gen0015ComplexType
     * @param tipoMensagemDDA
     * @return
     * @throws ComumException LogRecebimentoArquivoDDA
     */
    private LogRecebimentoArquivoDDA criarLogRecebimentoArquivo(GEN0015ComplexType gen0015ComplexType, TipoMensagemDDA tipoMensagemDDA) throws ComumException {
        LogRecebimentoArquivoDDA logRecebimentoArquivoDDA = new LogRecebimentoArquivoDDA();
        logRecebimentoArquivoDDA.setDescNomeArquivoRecebido(gen0015ComplexType.getNomArq());
        logRecebimentoArquivoDDA.setDataHoraArquivo(new DateTimeDB());
        try {
            logRecebimentoArquivoDDA.setDataMovimento(this.extraiDataMovimento(gen0015ComplexType.getNomArq()));
        } catch (ParseException e) {
            throw new ComumException("integracaocip.erro.registrar.logrecebimento.por.gen0015", new Object[] { gen0015ComplexType.getNomArq(), e.getMessage() }, e);
        }

        logRecebimentoArquivoDDA.setSituacaoProcessamentoArquivo(this.determinarSituacaoArquivo(tipoMensagemDDA, gen0015ComplexType.getNomArq()));
        logRecebimentoArquivoDDA.setTipoMensagemDDA(tipoMensagemDDA);
        logRecebimentoArquivoDDA.setTipoArquivo(new TipoArquivo(obterTipoArquivo(gen0015ComplexType.getNomArq())));
        LogEnvioArquivoDDA logEnvioArquivoDDA = arquivoCipDao.obterLogEnvioArquivoPorNome(retirarSufixoNomeArquivo(gen0015ComplexType.getNomArq()));
        logRecebimentoArquivoDDA.setLogEnvioArquivoDDA(logEnvioArquivoDDA);

        return logRecebimentoArquivoDDA;
    }

    /**
     * Método responsável por
     * 
     * @param tipoDaMensagem
     * @param nomeArquivo
     * @return SituacaoProcessamentoArquivo
     */
    private SituacaoProcessamentoArquivo determinarSituacaoArquivo(TipoMensagemDDA tipoDaMensagem, String nomeArquivo) {
        if (tipoDaMensagem.getCodTipoMensagem().equals(TipoMensagemDDA.ADDA104RR2) || isArquivoRespostaProErro(nomeArquivo)) {
            return new SituacaoProcessamentoArquivo(SituacaoProcessamentoArquivo.ARQUIVO_PROCESSADO);
        }
        return new SituacaoProcessamentoArquivo(SituacaoProcessamentoArquivo.ARQUIVO_DISPONIVEL);
    }

    /**
     * Método responsável por
     * 
     * @param prNomeArquivo
     * @return
     * @throws ParseException DateTimeDB
     */
    private DateTimeDB extraiDataMovimento(String prNomeArquivo) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(sdf.parse(prNomeArquivo.substring(17, 25)));
        return new DateTimeDB(gc.getTimeInMillis());
    }

    /**
     * Método responsável por
     * 
     * @param nomeArquivo
     * @return String
     */
    private String getTipoMensagemPorNomeArquivo(String nomeArquivo) {
        if (!ObjectUtil.isNull(nomeArquivo)) {
            StringBuilder str = new StringBuilder();
            str.append(nomeArquivo.substring(0, 7));
            // caso o nome seja maior que 31 ele tem ERR, PRO, RET, RR2 ou RR3
            if (nomeArquivo.length() > 31) {
                str.append(nomeArquivo.substring(nomeArquivo.length() - 3, nomeArquivo.length()));
            }
            return str.toString();
        }
        return nomeArquivo;
    }

    /**
     * Método responsável por
     * 
     * @param nomeArquivo
     * @return String
     */
    private String retirarSufixoNomeArquivo(String nomeArquivo) {
        if (isArquivoResposta(nomeArquivo)) {
            return nomeArquivo.substring(0, nomeArquivo.length() - 4);
        }
        return nomeArquivo;
    }

    /**
     * Método responsável por
     * 
     * @param nomeArquivo
     * @return String
     */
    private String obterTipoArquivo(String nomeArquivo) {
        if (isArquivoResposta(nomeArquivo)) {
            return nomeArquivo.substring(nomeArquivo.length() - 3, nomeArquivo.length());
        }
        return TipoArquivo.DISTRIBUICAO;

    }

    /**
     * Método responsável por
     * 
     * @param nomeArquivo
     * @return boolean
     */
    private boolean isArquivoResposta(String nomeArquivo) {
        return nomeArquivo != null && (nomeArquivo.endsWith(TipoArquivo.PROTOCOLO) || nomeArquivo.endsWith(TipoArquivo.RETORNO) || nomeArquivo.endsWith(TipoArquivo.ERRO));
    }

    /**
     * Método responsável por
     * 
     * @param nomeArquivo
     * @return boolean
     */
    private boolean isArquivoRespostaProErro(String nomeArquivo) {
        return nomeArquivo != null && (nomeArquivo.endsWith(TipoArquivo.PROTOCOLO) || nomeArquivo.endsWith(TipoArquivo.ERRO));
    }

    /**
     * @return o atributo arquivoRecebido
     */
    public ArquivoRecebidoCIPProcessadorServicoDelegate getArquivoRecebido() {
        if (ObjectUtil.isNull(arquivoRecebido)) {
            arquivoRecebido = IntegracaoCipFabricaDelegates.getInstance().getArquivoRecebidoCIPProcessadorServicoDelegate();
        }
        return arquivoRecebido;
    }
}
