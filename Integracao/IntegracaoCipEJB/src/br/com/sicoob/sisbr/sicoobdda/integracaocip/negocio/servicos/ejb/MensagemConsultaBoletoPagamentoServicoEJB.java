/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MensagemConsultaBoletoPagamentoServicoEJB.java
 * Data Criação:    Sep 30, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ComumFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.DominioDDADelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.InstituicaoDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.delegates.ParametroDelegate;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ErroCIPNegocioException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.XMLCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ComumDao;
import br.com.sicoob.sisbr.sicoobdda.comum.persistencia.dao.ParametroDao;
import br.com.sicoob.sisbr.sicoobdda.entidades.BoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.ErroMensagemRetornoCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.GradeHoraria;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAConsultaBoleto;
import br.com.sicoob.sisbr.sicoobdda.entidades.ParametroDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.ConsultaBoletoPagamentoCIPDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.ConsultaBoletoCIPTimedOutException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultaBoletoPagamentoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.LeitorXmlUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.BoletoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.conversor.ConversorBoletoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.DDA0110ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.DDA0110R1ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.MQRecebimento;

/**
 * MensagemConsultaBoletoPagamentoServicoEJB
 * 
 * @author Rafael.Silva
 */
@Stateless
@Local({ MensagemConsultaBoletoPagamentoServicoLocal.class })
public class MensagemConsultaBoletoPagamentoServicoEJB extends IntegracaoCipServicoEJB implements MensagemConsultaBoletoPagamentoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private BoletoCipDao dao;

    @Deprecated
    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ComumDao comumDao;

    @Deprecated
    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private ParametroDao parametroDao;

    private static final String FILTRO_REGISTRO_NUMSEQ = "numseq";

    @EJB
    private MensagemConsultaBoletoPagamentoServicoLocal mensagemConsultaSevico;

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
     * Método responsável por
     * 
     * @return ConsultaBoletoPagamentoCIPDelegate
     * 
     */
    private ConsultaBoletoPagamentoCIPDelegate getConsultaBoletoPagamentoCIPDelegate() {
        return IntegracaoCipFabricaDelegates.getInstance().getConsultaBoletoPagamentoCIPDelegate();
    }

    /**
     * @return o atributo sciDelegate
     */
    private InstituicaoDelegate getInstituicaoDelegate() {
        return ComumFabricaDelegates.getInstance().getInstituicaoDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return ParametroDelegate
     * 
     */
    private ParametroDelegate getParametroDelegate() {
        return ComumFabricaDelegates.getInstance().getParametroDelegate();
    }

    /**
     * Método responsável por
     * 
     * @return DominioDDADelegate
     * 
     */
    private DominioDDADelegate getDominioDDADelegate() {
        return ComumFabricaDelegates.getInstance().getDominioDDADelegate();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoPagamentoServico#obterBoletoDDA(java.lang.String,
     *      java.lang.Integer, java.lang.String, java.lang.Short)
     */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public BoletoDDA obterBoletoDDA(String numCodBarras, Integer idInstituicao, String idUsuario, Short idCanal)
            throws ComumException, ComumNegocioException, ErroCIPNegocioException {
        validaNumeroCodigoBarrasSicoob(numCodBarras);

        MensagemDDA msgDDA = mensagemConsultaSevico.enviarConsultaBoleto(numCodBarras, idInstituicao, idUsuario, idCanal);

        return receberRetornoCip(msgDDA, idInstituicao, idUsuario, idCanal);
    }

    /**
     * Método responsável por
     * 
     * @param numCodBarras
     * @throws ComumNegocioException void
     * 
     */
    private void validaNumeroCodigoBarrasSicoob(String numCodBarras) throws ComumNegocioException {
        debug("########### validando código de barras sicoob");
        if (LinhaDigitavelCodigoBarrasUtil.isCodigoBarrasSicoob(numCodBarras)) {
            throw new ComumNegocioException("integracaocip.erro.consulta.DDA0110.nao.permitida.sicoob");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultaBoletoPagamentoServicoLocal#enviarConsultaBoleto(java.lang.String,
     *      java.lang.Integer, java.lang.String, java.lang.Short)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MensagemDDA enviarConsultaBoleto(String numCodBarras, Integer idInstituicao, String idUsuario, Short idCanal) throws ComumException {
        MensagemDDA msgDDA = criarMensagemDDAConsultaBoleto(numCodBarras, idInstituicao, idUsuario, idCanal);

        debug("###### Postando mensagem na fila. Id/NumSeq da Mensagem enviada: " + msgDDA.getId());
        getConsultaBoletoPagamentoCIPDelegate().enviarMensagemCip(msgDDA);
        debug("###### Mensagem enviada. Id/NumSeq da Mensagem enviada: " + msgDDA.getId());
        return msgDDA;
    }

    /**
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @param timeOutParametrizado
     * @return
     * @throws ComumNegocioException
     * @throws ComumException
     * @throws ErroCIPNegocioException BoletoDDA
     * 
     */
    private BoletoDDA receberRetornoCip(MensagemDDA msg, Integer idInstituicao, String idUsuario, Short idCanal)
            throws ComumException, ComumNegocioException, ErroCIPNegocioException {
        debug("########### Inicio receberRetornoCip(MensagemDDA msg) para DDA0110");

        int timeOutParametrizado = obterTimeOut();

        String xmlRecebido = consumirMensagemRetorno(msg, timeOutParametrizado);
        BoletoDDA boleto;

        boleto = mensagemConsultaSevico.processarMensagemRetorno(xmlRecebido, msg, idInstituicao, idUsuario, idCanal);

        debug("########### Fim receberRetornoCip(MensagemDDA msg) para DDA0110");
        return boleto;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException int
     * 
     */
    private int obterTimeOut() throws ComumException {
        int timeOutParametrizado;
        if (isCacheHabilitado()) {
            timeOutParametrizado = getParametroDelegate().obterValorInteger(ParametroDDA.TEMPO_MAX_RETORNO_CONSULTA_DDA0110);
        } else {
            timeOutParametrizado = parametroDao.obterValorInteger(ParametroDDA.TEMPO_MAX_RETORNO_CONSULTA_DDA0110, Constantes.ID_SICOOB);
        }
        return timeOutParametrizado;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException boolean
     * 
     */
    private boolean isCacheHabilitado() throws ComumException {
        return parametroDao.obterValorBoolean(ParametroDDA.CACHE_HABILITADO, Constantes.ID_SICOOB);
    }

    /**
     * @param codigoBarras
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @return
     * @throws ComumException MensagemDDA
     * 
     */
    private MensagemDDA criarMensagemDDAConsultaBoleto(String codigoBarras, Integer idInstituicao, String idUsuario, Short idCanal) throws ComumException {
        debug("########### Inicio criarMensagemDDAConsultaBoleto(String codigoBarras, Integer idInstituicao, String idUsuario, Short idCanal) para DDA0110");
        MensagemDDAConsultaBoleto msgConsultaBoleto = new MensagemDDAConsultaBoleto(criarMensagemEnvioDDA(idInstituicao, idUsuario, idCanal), codigoBarras);
        em.persist(msgConsultaBoleto);
        debug("###### Mensagem inserida ID-" + msgConsultaBoleto.getId());

        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(getDDA0110ComplexType(msgConsultaBoleto), msgConsultaBoleto.getId());
        msgConsultaBoleto.getMensagemDDA().setXmlMensagem(xmlEnvio);
        debug("###### XmlEnvio DDA0110 de envio gerado >>> " + xmlEnvio);
        debug("########### Fim criarMensagemDDAConsultaBoleto(String codigoBarras, Integer idInstituicao, String idUsuario, Short idCanal) para DDA0110");
        return msgConsultaBoleto.getMensagemDDA();
    }

    /**
     * @param m
     * @return
     * @throws ComumException DDA0110ComplexType
     * 
     */
    private DDA0110ComplexType getDDA0110ComplexType(MensagemDDAConsultaBoleto m) throws ComumException {
        debug("*******INICIO obterXmlEnvio*******");
        DDA0110ComplexType dda0110 = new DDA0110ComplexType();
        dda0110.setCodMsg(TipoMensagemDDA.DDA0110);
        dda0110.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(m.getMensagemDDA().getDataMovimento()));
        dda0110.setISPBPartRecbdrAdmtd(Constantes.ISPB_BANCOOB);
        dda0110.setISPBPartRecbdrPrincipal(Constantes.ISPB_BANCOOB);
        dda0110.setNumCodBarras(m.getNumCodigoBarra());
        dda0110.setNumCtrlPart(m.getId().toString());
        return dda0110;
    }

    /**
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @return MensagemDDA
     * @throws ComumException
     * 
     */
    private MensagemDDA criarMensagemEnvioDDA(Integer idInstituicao, String idUsuario, Short idCanal) throws ComumException {
        MensagemDDA msgDDA = new MensagemDDA();
        msgDDA.setTipoMensagemDDA(new TipoMensagemDDA(TipoMensagemDDA.DDA0110));
        msgDDA.setDataMovimento(obterDataMovimentoGradeHoraria());
        msgDDA.setBolOrigemSicoob(Boolean.TRUE);
        msgDDA.setDataHoraCadastro(new DateTimeDB());
        msgDDA.setDataHoraMensagem(new DateTimeDB());
        msgDDA.setNumPrioridadeEnvio(Constantes.INTEGER_CEM);
        msgDDA.setIdInstituicaoSolicitante(idInstituicao);
        msgDDA.setIdUsuarioSolicitante(idUsuario);
        msgDDA.setIdCanal(idCanal);
        msgDDA.setBolExcedeuSLA(Boolean.FALSE);

        return msgDDA;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException DateTimeDB
     * 
     */
    private DateTimeDB obterDataMovimentoGradeHoraria() throws ComumException {
        DateTimeDB dataMovimento = null;
        if (isCacheHabilitado()) {
            dataMovimento = obterDataMovimentoGradeHorariaCache();
        } else {
            dataMovimento = comumDao.obterDataReferenciaGradeAberta(TipoMensagemDDA.DDA0110);

        }
        debug("########### Data referencia obtida.");

        if (ObjectUtil.isNull(dataMovimento)) {
            throw new ComumException("integracaocip.erro.sem.grade.DDA0110");
        }

        return dataMovimento;
    }

    /**
     * Método responsável por
     * 
     * @return
     * @throws ComumException DateTimeDB
     * 
     */
    private DateTimeDB obterDataMovimentoGradeHorariaCache() throws ComumException {
        debug("########### Obter data referência cache");
        GradeHoraria gradeHoraria = getDominioDDADelegate().obterGradeHorariaReferenciaDDA0110();

        if (!ObjectUtil.isNull(gradeHoraria)) {
            return gradeHoraria.getDataReferencia();
        }
        return null;
    }

    /**
     * Método responsável por consumir fila de recebimento para recuperar o xml de resposta da consulta DDA0110.
     * 
     * @param msg
     * @param timeOutParametrizado
     * @return
     * @throws ComumException
     * @throws ComumNegocioException String
     * 
     */
    private String consumirMensagemRetorno(MensagemDDA msg, int timeOutParametrizado) throws ComumException, ConsultaBoletoCIPTimedOutException {
        String xmlRecebido = getConsultaBoletoPagamentoCIPDelegate().aguardarRepostaCIP(msg, timeOutParametrizado);

        if (ObjectUtil.isEmpty(xmlRecebido)) {
            mensagemConsultaSevico.atualizarBolExcedeuSLA(msg);

            debug("###### O timeout da consulta DDA0110 de " + timeOutParametrizado + " segundos expirou. " + FILTRO_REGISTRO_NUMSEQ + " = '" + msg.getId() + "'");
            throw new ConsultaBoletoCIPTimedOutException("integracaocip.timeout.consulta.DDA0110");
        }
        debug("###### XML de retorno recebido da consulta DDA0110: " + xmlRecebido);

        return xmlRecebido;
    }

    /**
     * Método responsável por atualizar o bolExcedeuSLA da mensagemDDA
     * 
     * @param msg
     * @throws ComumException
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void atualizarBolExcedeuSLA(MensagemDDA msg) throws ComumException {
        debug("###### Atualizando BOLEXCEDEUSLA");
        msg.setBolExcedeuSLA(Boolean.TRUE);
        dao.atualizarBolExcedeuSLA(msg.getId(), Boolean.TRUE);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultaBoletoPagamentoCIPServico#gravarMensagemRetornoSucessoDDA(java.lang.String,
     *      java.lang.String, br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Short)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void gravarMensagemRetornoSucessoDDA(String numOpMsg, String xmlRecebido, MensagemDDA msgOrigem, String numControleDDA, Integer idInstituicao, String idUsuario,
            Short idCanal) throws ComumException {

        MensagemDDA mensagem = criarMensagemRetornoDDA(numOpMsg, xmlRecebido, TipoMensagemDDA.DDA0110R1, msgOrigem, idInstituicao, idUsuario, idCanal);
        mensagem.setNumControleDDA(numControleDDA);

        em.persist(mensagem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ConsultaBoletoPagamentoCIPServico#gravarMensagemRetornoErroDDA(java.lang.String,
     *      java.lang.String, br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA, java.lang.Integer, java.lang.String, java.lang.Short)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String gravarMensagemRetornoErroDDA(String numOpMsg, String xmlRecebido, MensagemDDA msgOrigem, Integer idInstituicao, String idUsuario, Short idCanal)
            throws ComumException {
        MensagemDDA mensagem = criarMensagemRetornoDDA(numOpMsg, xmlRecebido, TipoMensagemDDA.DDA0110E, msgOrigem, idInstituicao, idUsuario, idCanal);
        String msgErro = setErroMensagemRetornoCip(mensagem, xmlRecebido);

        em.persist(mensagem);
        return msgErro;
    }

    /**
     * @param numOpMsg
     * @param xmlRecebido
     * @param codTipoMensagem
     * @param msgOrigem
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @return MensagemDDA
     * 
     */
    private MensagemDDA criarMensagemRetornoDDA(String numOpMsg, String xmlRecebido, String codTipoMensagem, MensagemDDA msgOrigem, Integer idInstituicao, String idUsuario,
            Short idCanal) {
        MensagemDDA mensagem = new MensagemDDA();
        mensagem.setBolOrigemSicoob(Boolean.FALSE);
        mensagem.setDataHoraCadastro(new DateTimeDB());
        mensagem.setDataHoraMensagem(new DateTimeDB());
        mensagem.setDataMovimento(new DateTimeDB());
        mensagem.setDataHoraProtocolo(null);
        mensagem.setDescErroProtocolo(null);
        mensagem.setNumOperacao(numOpMsg);
        mensagem.setQtdTotalRegistros(1);
        mensagem.setXmlMensagem(xmlRecebido);
        mensagem.setTipoMensagemDDA(new TipoMensagemDDA(codTipoMensagem));
        mensagem.setNumPrioridadeEnvio(Constantes.INTEGER_CEM);
        mensagem.setIdInstituicaoSolicitante(idInstituicao);
        mensagem.setIdUsuarioSolicitante(idUsuario);
        mensagem.setIdCanal(idCanal);
        if (!ObjectUtil.isNull(msgOrigem)) {
            mensagem.setMensagemOrigem(msgOrigem);
        }
        return mensagem;
    }

    /**
     * @param mensagem
     * @param xmlRecebido
     * @return
     * @throws ComumException String
     * 
     */
    private String setErroMensagemRetornoCip(MensagemDDA mensagem, String xmlRecebido) throws ComumException {
        StringBuilder msgErroCip = new StringBuilder();
        XMLCipDto xmlCipDto = LeitorXmlUtil.obterDadosXMLErroCip(xmlRecebido);
        mensagem.setListaErroMensagemRetornoCip(new ArrayList<ErroMensagemRetornoCip>());
        for (String codTipoErro : xmlCipDto.getListaCodErro()) {
            ErroMensagemRetornoCip erroRetorno = new ErroMensagemRetornoCip();
            erroRetorno.setMensagemDDA(mensagem);
            erroRetorno.setTipoErroCip(em.find(TipoErroCip.class, codTipoErro));
            erroRetorno.setDataHoraAtualizacao(new DateTimeDB());

            mensagem.getListaErroMensagemRetornoCip().add(erroRetorno);
            msgErroCip.append(erroRetorno.getTipoErroCip().getDescTipoErro());
            msgErroCip.append(" (");
            msgErroCip.append(erroRetorno.getTipoErroCip().getCodTipoErro());
            msgErroCip.append("). ");
        }
        return msgErroCip.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultaBoletoPagamentoServicoLocal#processarMensagemRetorno(java.lang.String,
     *      br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA, java.lang.Integer, java.lang.String, java.lang.Short)
     */
    public BoletoDDA processarMensagemRetorno(String xmlRecebido, MensagemDDA msgOrigem, Integer idInstituicao, String idUsuario, Short idCanal)
            throws ComumException, ErroCIPNegocioException {
        debug("Processando o retorno do novo catálogo da 110R1");

        MQRecebimento recebimento = (MQRecebimento) LeitorXmlUtil.desempacotarArquivo(MQRecebimento.class, xmlRecebido);
        String numOpMsg = recebimento.getHeader().getNuOp();

        DDA0110R1ComplexType dda0110R1 = recebimento.getMsgSPB().getDda0110R1();

        if (!ObjectUtil.isNull(dda0110R1)) {
            mensagemConsultaSevico.gravarMensagemRetornoSucessoDDA(numOpMsg, xmlRecebido, msgOrigem, dda0110R1.getNumCtrlDDA(), idInstituicao, idUsuario, idCanal);
        } else {
            tratarMensagemRetornoErroDDA(numOpMsg, xmlRecebido, msgOrigem, idInstituicao, idUsuario, idCanal);
        }

        return processarBoletoRetorno(dda0110R1);
    }

    /**
     * Método responsável por gravar ou atualizar o boleto recebido da CIP.
     * 
     * @param boletoR1
     * @return
     * @throws ComumException
     */
    private BoletoDDA processarBoletoRetorno(DDA0110R1ComplexType boletoR1) throws ComumException {
        debug("########### Inicio processarBoletoRetorno(DDA0110R1ComplexType boletoR1) para DDA0110");
        BoletoDDA boleto = dao.obterBoletoDDA(boletoR1.getNumIdent());
        if (ObjectUtil.isNull(boleto) || ObjectUtil.isEmpty(boleto.getId())) {
            return incluirBoletoDDA(boletoR1);
        } else {
            return alterarBoletoDDA(boleto, boletoR1);
        }
    }

    /**
     * Método responsável por incluir um novo boleto.
     * 
     * @param boletoR1
     * @return BoletoDDA
     * 
     */
    private BoletoDDA incluirBoletoDDA(DDA0110R1ComplexType boletoR1) {
        debug("########### Inicio da conversão do novo Objeto DDA0110R1ComplexType do BoletoDDA");
        BoletoDDA boletoDDA = ConversorBoletoDDA.converter(boletoR1);
        em.persist(boletoDDA);
        debug("########### Objeto DDA0110R1ComplexType Convertido com Sucesso para BoletoDDA");
        return boletoDDA;
    }

    /**
     * Método responsável por alterar o boletoDDA com as informações recebidas da CIP.
     * 
     * @param boletoDDA
     * @param boletoR1
     * @return
     * @throws ComumException BoletoDDA
     * 
     */
    private BoletoDDA alterarBoletoDDA(BoletoDDA boletoDDA, DDA0110R1ComplexType boletoR1) throws ComumException {
        debug("########### Processando alteração do BoletoDDA para o novo DDA0110R1ComplexType - " + boletoDDA.getId());
        removerRelacionamentoBoletoDDA(boletoDDA);
        BoletoDDA boletoDDAAlterado = ConversorBoletoDDA.merge(boletoR1, boletoDDA);
        debug("########### Boleto convertido.");
        em.merge(boletoDDAAlterado);
        debug("########### Objeto DDA0110R1ComplexType Convertido e Atualizado com Sucesso no BD");

        return boletoDDAAlterado;
    }

    /**
     * Método responsável por limpar os registros de tabelas relacionadas do BoletoDDA.
     * 
     * @param boletoDDA
     * @throws ComumException void
     * 
     */
    private void removerRelacionamentoBoletoDDA(BoletoDDA boletoDDA) throws ComumException {
        dao.removerBaixaEfetiva(boletoDDA.getId());
        dao.removerBaixaOperacional(boletoDDA.getId());
        dao.removerGrupoCalculo(boletoDDA.getId());
        dao.removerTerceiroAutorizado(boletoDDA.getId());
    }

    /**
     * @param numOpMsg
     * @param xmlRecebido
     * @param msgOrigem
     * @param idInstituicao
     * @param idUsuario
     * @param idCanal
     * @throws ComumException
     * @throws ErroCIPNegocioException void
     * 
     */
    private void tratarMensagemRetornoErroDDA(String numOpMsg, String xmlRecebido, MensagemDDA msgOrigem, Integer idInstituicao, String idUsuario, Short idCanal)
            throws ComumException, ErroCIPNegocioException {
        String msgErro = mensagemConsultaSevico.gravarMensagemRetornoErroDDA(numOpMsg, xmlRecebido, msgOrigem, idInstituicao, idUsuario, idCanal);

        throw new ErroCIPNegocioException("integracaocip.erro", msgErro);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemConsultaBoletoPagamentoServico#incluirMensagemDDAConsultaBoleto(java.util.List)
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void incluirMensagemDDAConsultaBoleto(List<String> listaNumCodBarras, Integer numCooperativa) throws ComumException {
        debug("### Incluíndo a mensagem DDA de consulta de boleto...");
        debug("Parâmetro - listaNumCodBarras: " + !ObjectUtil.isNull(listaNumCodBarras));

        if (ObjectUtil.isEmpty(listaNumCodBarras)) {
            debug("A lista está vazia");
            return;
        }

        Integer idInstituicao = null;
        if (!ObjectUtil.isNull(numCooperativa)) {
            idInstituicao = getInstituicaoDelegate().obterInstituicaoPorCooperativa(numCooperativa).getIdInstituicao();
        }

        dao.incluirMensagemDDAConsultaBoletoEmLote(TipoMensagemDDA.ADDA110, new DateTimeDB(), Boolean.TRUE, new DateTimeDB(), Constantes.INTEGER_CEM, listaNumCodBarras,
                idInstituicao);

    }

}
