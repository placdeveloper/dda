/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         TestaConectividadeArquivosServicoEJB.java
 * Data Criação:    May 26, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoTestaConectividadeArquivosServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ArquivoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001.AGEN001ComplexType;

/**
 * TestaConectividadeArquivosServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ ArquivoTestaConectividadeArquivosServicoLocal.class })
public class ArquivoTestaConectividadeArquivosServicoEJB extends IntegracaoCipServicoEJB implements ArquivoTestaConectividadeArquivosServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ArquivoCipDao arquivoCipDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    protected EntityManager getEm() {
        return em;
    }

    // TODO - Rafael - ignorar classe no svn;

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.TestaConectividadeArquivosServico#processarTesteConectividade()
     */
    public void processarTesteConectividade() throws ComumException {
        // try {
        // ADDADOCComplexType docAgen001 = getADDADOC001();
        // List<String> listaUrlsArquivo = new ArrayList<String>();
        // listaUrlsArquivo.add(postarArquivoDiretorioCargaTmp(docAgen001, ParametroDDA.DIRETORIO_CARGATMP_ARQUIVOS_CIP,
        // docAgen001.getBCARQ().getNomArq()));
        // } catch (Exception e) {
        // getLogger().erro(e, "integracaocip.erro.processar.arquivo.teste.conectividade");
        // throw new ComumException("integracaocip.erro.processar.arquivo.teste.conectividade", e);
        // }
    }

    /**
     * Método responsável por recuperar o objeto ADDADOCComplexType
     * 
     * @param numSeqArq
     * @return
     * @throws ComumException ADDADOCComplexType
     * 
     */
    // public ADDADOCComplexType getADDADOC001() throws ComumException {
    // Long qtdArquivosGeradosDia = getDao().obterQtdArquivosGeradosDataAtual();
    // LogArquivoCarga logArquivoCarga = criarLogArquivoCarga(TipoArquivoEnum.AGEN001.getCodTipoArquivo(), qtdArquivosGeradosDia);

    // BCARQComplexType bcArq = new BCARQComplexType();
    // // bcArq.setNomArq(logArquivoCarga.getDescNomeArquivoEnviado());
    // // bcArq.setNumCtrlEmis(logArquivoCarga.getId().toString());
    // bcArq.setISPBEmissor(Constantes.ISPB_BANCOOB);
    // bcArq.setISPBDestinatario(Constantes.ISPB_CIP_SITRAF);
    // bcArq.setDtHrDDA(DataCipUtil.dateTimeParaXMLGregorianCalendar(new DateTime()));
    // bcArq.setIndrFlagFim("N");// Indica o último arquivo do dia. (S ou N)
    // bcArq.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(new DateTime()));
    //
    // SISARQComplexType sisArq = new SISARQComplexType();
    // sisArq.setAGEN001(getAGEN001());
    //
    // ADDADOCComplexType docAgen001 = new ADDADOCComplexType();
    // docAgen001.setBCARQ(bcArq);
    // docAgen001.setSISARQ(sisArq);
    //
    // return docAgen001;
    // }

    /**
     * Método responsável por recuperar o objeto AGEN001ComplexType de composicao do arquivo
     * 
     * @return AGEN001ComplexType
     * 
     */
    private AGEN001ComplexType getAGEN001() {
        AGEN001ComplexType agen001 = new AGEN001ComplexType();
        agen001.setISPBEmissor(Constantes.ISPB_BANCOOB);
        agen001.setISPBDestinatario(Constantes.ISPB_CIP_SITRAF);
        agen001.setMsgECO(Constantes.MENSAGEM_ECO);
        return agen001;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.TestaConectividadeArquivosServico#processarRetornoTesteConectividadePorArquivo(java .lang.String)
     */
    public void processarRetornoCIPTesteConectividadePorArquivo(String nomeArquivoRecebido) throws ComumException {
        if (nomeArquivoRecebido.contains(Constantes.SUFIXO_ARQUIVO_PROTOCOLO)) {
            processarProtocolo(nomeArquivoRecebido);
        } else if (nomeArquivoRecebido.contains(Constantes.SUFIXO_ARQUIVO_ERRO)) {
            processarErro(nomeArquivoRecebido);
        } else if (nomeArquivoRecebido.contains(Constantes.SUFIXO_ARQUIVO_RETORNO)) {
            processarRetorno(nomeArquivoRecebido);
        }
    }

    /**
     * Método responsável por processar o retorno do tipo PRO (protocolo) recebido.
     * 
     * @param nomeArquivoRecebido
     * @throws ComumException void
     * 
     */
    private void processarProtocolo(String nomeArquivoRecebido) throws ComumException {
        // try {
        // br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001PRO.ADDADOCComplexType docAgen001Pro =
        // (br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001PRO.ADDADOCComplexType) obterObjectArquivoRecebido(
        // br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001PRO.ADDADOCComplexType.class,
        // getDiretorio(ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP),
        // nomeArquivoRecebido);

        // Long idLogArquivoCarga = Long.parseLong(docAgen001Pro.getBCARQ().getNumCtrlDestOr());
        // int idSituacaoArquivo = docAgen001Pro.getBCARQ().getSitReqDDA().intValue();

        /*
         * LogArquivoCarga logArquivoCarga = em.find(LogArquivoCarga.class, idLogArquivoCarga); logArquivoCarga.setSituacaoArquivoDDA(em.find(SituacaoArquivoDDA.class, idSituacaoArquivo)); logArquivoCarga.setNumCtrlDestinatarioOriginal(docAgen001Pro.getBCARQ().getNumCtrlDestOr()); logArquivoCarga.setDataHoraProtocolo(new DateTimeDB());
         */
        // } catch (Exception e) {
        // getLogger().erro(e, "integracaocip.erro.processar.arquivo.protocolo");
        // throw new ComumException("integracaocip.erro.processar.arquivo.protocolo", e);
        // }

    }

    /**
     * Método responsável por processar o retorno do tipo ERR (erro) recebido.
     * 
     * @param nomeArquivoRecebido
     * @throws ComumException void
     * 
     */
    private void processarErro(String nomeArquivoRecebido) throws ComumException {
        // try {
        // br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001ERR.ADDADOCComplexType docAgen001ERR =
        // (br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001ERR.ADDADOCComplexType) obterObjectArquivoRecebido(
        // br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001ERR.ADDADOCComplexType.class,
        // getDiretorio(ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP),
        // nomeArquivoRecebido);

        // String nomeArquivoEnviado = docAgen001ERR.getBCARQ().getNomArq().getValue().replace(Constantes.SUFIXO_ARQUIVO_ERRO, "");

        /*
         * LogArquivoCarga logArquivoCarga = getDao().obterLogArquivoCargaPorNome(nomeArquivoEnviado); logArquivoCarga.setSituacaoArquivoDDA(em.find(SituacaoArquivoDDA.class, SituacaoArquivoDDA.ERRO_NO_ARQUIVO)); logArquivoCarga.setTipoErroCip(em.find(TipoErroCip.class, docAgen001ERR.getBCARQ().getNomArq().getCodErro()));
         * logArquivoCarga.setDataHoraProtocolo(new DateTimeDB());
         */
        // } catch (Exception e) {
        // getLogger().erro(e, "integracaocip.erro.processar.arquivo.erro");
        // throw new ComumException("integracaocip.erro.processar.arquivo.erro", e);
        // }

    }

    /**
     * Método responsável por processar o retorno do tipo RET (retorno) recebido.
     * 
     * @param nomeArquivoRecebido
     * @throws ComumException void
     * 
     */
    private void processarRetorno(String nomeArquivoRecebido) throws ComumException {
        // ADDADOCComplexType docAgen001 = (ADDADOCComplexType) obterObjectArquivoRecebido(ADDADOCComplexType.class,
        // getDiretorio(ParametroDDA.DIRETORIO_ARQUIVOS_RECEBIDOS_CIP),
        // nomeArquivoRecebido);

        // Long idLogArquivoCarga = Long.parseLong(docAgen001.getBCARQ().getNumCtrlDestOr());
        // LogArquivoCarga logArquivoCarga = em.find(LogArquivoCarga.class, idLogArquivoCarga);
        /*
         * if (docAgen001.getBCARQ().getSitReqDDA() != null) { int idSituacaoArquivo = docAgen001.getBCARQ().getSitReqDDA().intValue(); logArquivoCarga.setSituacaoArquivoDDA(em.find(SituacaoArquivoDDA.class, idSituacaoArquivo)); } logArquivoCarga.setDescNomeArquivoRecebido(nomeArquivoRecebido); logArquivoCarga.setDataHoraArqRecebido(new
         * DateTimeDB()); logArquivoCarga.setNumCtrlDestinatarioOriginal(docAgen001.getBCARQ().getNumCtrlDestOr());
         */
    }
}
