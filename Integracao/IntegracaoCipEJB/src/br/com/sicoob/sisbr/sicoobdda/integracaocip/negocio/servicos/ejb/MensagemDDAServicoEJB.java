package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DominioCadastroMensagemDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.delegates.IntegracaoCipMensagemDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemDDAServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.EscritorXMLUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.ADMDelegate;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.delegates.IntegracaoInternaFabricaDelegates;
import br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA;

/**
 * MensagemDDAServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Local({ MensagemDDAServicoLocal.class })
public class MensagemDDAServicoEJB extends IntegracaoCipServicoEJB implements MensagemDDAServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    private ADMDelegate admDelegate;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao dao;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao integracaoCipDao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.IntegracaoCipServicoEJB.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    /**
     * @return o atributo dao
     */
    public MensagemDDADao getDao() {
        return dao;
    }

    /**
     * Método responsável por
     * 
     * @return ADMDelegate
     * 
     */
    private ADMDelegate getADMDelegate() {
        if (admDelegate == null) {
            admDelegate = IntegracaoInternaFabricaDelegates.getInstance().getADMDelegate();
        }
        return admDelegate;
    }

    /**
     * Método responsável por criar a MensagemDDA
     * 
     * @param codTipoMensagem
     * @param dataMovimento
     * @return MensagemDDA
     */
    private MensagemDDA criarMensagemDDA(String codTipoMensagem, DateTimeDB dataMovimento, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) {
        debug("### Criando a mensagemDDA...");
        debug("Parâmetro - codTipoMensagem: " + codTipoMensagem);
        debug("Parâmetro - dataMovimento: " + dataMovimento);

        TipoMensagemDDA tipoMsg = getEm().find(TipoMensagemDDA.class, codTipoMensagem);
        debug("TipoMensagemDDA obtido: " + tipoMsg);

        MensagemDDA msgDDA = new MensagemDDA();
        msgDDA.setTipoMensagemDDA(tipoMsg);
        msgDDA.setDataMovimento(dataMovimento);
        msgDDA.setBolOrigemSicoob(Boolean.TRUE);
        msgDDA.setDataHoraCadastro(new DateTimeDB());
        msgDDA.setNumPrioridadeEnvio(tipoMsg.getNumPrioridadeEnvio());
        msgDDA.setIdUsuarioSolicitante(usuarioSolicitante);
        msgDDA.setIdInstituicaoSolicitante(idInstitiucaoSolicitante);
        msgDDA.setIdCanal(idCanal);

        return msgDDA;
    }

    /**
     * Método responsável por incluir a mensagem
     * 
     * @param mensagem
     */
    private void incluir(IMensagemDDA mensagem) {
        em.persist(mensagem);

        // Força a inclusão do objeto filho, pois no persist ele só inclui o objeto pai (MensagemDDA)
        em.flush();
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA,
     *      java.lang.String, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, DateTimeDB dataMovimento, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal)
            throws ComumException {
        incluir(mensagem, tipoMensagemDDA, dataMovimento, null, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA,
     *      java.lang.String, br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, DateTimeDB dataMovimento, Integer numPrioridadeEnvio, String usuarioSolicitante,
            Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException {
        debug("### Incluíndo mensagem...");
        debug("Parâmetro - mensagem: " + mensagem);
        debug("Parâmetro - tipoMensagemDDA: " + tipoMensagemDDA);
        debug("Parâmetro - dataMovimento: " + dataMovimento);
        debug("Parâmetro - numPrioridadeEnvio: " + numPrioridadeEnvio);
        debug("Parâmetro - usuarioSolicitante: " + usuarioSolicitante);
        debug("Parâmetro - idInstitiucaoSolicitante: " + idInstitiucaoSolicitante);
        debug("Parâmetro - idCanal: " + idCanal);

        if (ObjectUtil.isNull(mensagem)) {
            throw new ComumException("integracaocip.parametro.nao.informado", "mensagem");
        } else if (ObjectUtil.isEmpty(tipoMensagemDDA)) {
            throw new ComumException("integracaocip.parametro.nao.informado", "tipoMensagemDDA");
        } else if (ObjectUtil.isNull(dataMovimento)) {
            throw new ComumException("integracaocip.parametro.nao.informado", "dataMovimento");
        }

        MensagemDDA mensagemDDA = criarMensagemDDA(tipoMensagemDDA, dataMovimento, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
        mensagem.setMensagemDDA(mensagemDDA);

        if (!ObjectUtil.isNull(numPrioridadeEnvio) && numPrioridadeEnvio >= 0) {
            mensagemDDA.setNumPrioridadeEnvio(numPrioridadeEnvio);
        }

        incluir(mensagem);

        tratarPostarMensagem(mensagemDDA);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(ComplexType complexType, DateTimeDB dataMovimento, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException {
        incluir(complexType, dataMovimento, null, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.ComplexType,
     *      br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(ComplexType complexType, DateTimeDB dataMovimento, Integer numPrioridadeEnvio, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal)
            throws ComumException {
        debug("### Incluíndo mensagem...");
        debug("Parâmetro - complexType: " + complexType);
        debug("Parâmetro - dataMovimento: " + dataMovimento);
        debug("Parâmetro - numPrioridadeEnvio: " + numPrioridadeEnvio);

        if (ObjectUtil.isNull(complexType)) {
            throw new ComumException("integracaocip.parametro.nao.informado", "complexType");
        } else if (ObjectUtil.isNull(dataMovimento)) {
            throw new ComumException("integracaocip.parametro.nao.informado", "dataMovimento");
        }

        MensagemDDA mensagemDDA = criarMensagemDDA(complexType.getCodMsg(), dataMovimento, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);

        if (!ObjectUtil.isNull(numPrioridadeEnvio)) {
            mensagemDDA.setNumPrioridadeEnvio(numPrioridadeEnvio);
        }

        em.persist(mensagemDDA);

        atualizarXML(complexType, mensagemDDA);

        tratarPostarMensagem(mensagemDDA);
    }

    /**
     * Método responsável por verificar se deve postar a mensagem.
     * 
     * @param mensagemDDA
     * @throws ComumException
     */
    private void tratarPostarMensagem(MensagemDDA mensagemDDA) throws ComumException {
        debug("### Tratar postar mensagem...");
        debug("Prioridade da mensagem: " + mensagemDDA.getNumPrioridadeEnvio());

        /*
         * Para o cadastramento de mensagens on-line, deverá obedecer os seguintes critérios:
         * 
         * 1 - A prioridade da mensagem deve ser zero;
         * 
         * 2 - A mensagem deve estar na VIEW de envio.
         */
        if (mensagemDDA.getNumPrioridadeEnvio() == 0 && getDao().possuiMensagemDDAParaEnvio(mensagemDDA.getId())) {
            postarMensagem(mensagemDDA.getId());
        }
    }

    /**
     * Método responsável por atualizar o XML na mensagem DDA
     * 
     * @param complexType
     * @param mensagemDDA
     * @throws ComumException
     */
    private void atualizarXML(ComplexType complexType, MensagemDDA mensagemDDA) throws ComumException {
        debug("### Atualizando o XML...");
        debug("Id MensagemDDA: " + mensagemDDA.getId());

        // Define o id da MensagemDDA
        complexType.setIdMensagemDDA(mensagemDDA.getId().toString());

        // Gera o XML
        String xmlEnvio = EscritorXMLUtil.obterXmlEnvio(complexType, mensagemDDA.getId());

        // Atualiza o XML na MensagemDDA
        integracaoCipDao.atualizarXMLMensagemDDA(mensagemDDA.getId(), xmlEnvio);
    }

    /**
     * Método responsável por postar a mensagem na fila.
     * 
     * @param idMensagem
     * @throws ComumException
     */
    private void postarMensagem(Long idMensagem) throws ComumException {
        debug("### Postando a mensagemDDA: " + idMensagem);

        String codTipoMensagem = integracaoCipDao.obterTipoMensagemLock(idMensagem);
        debug("Tipo mensagem: " + codTipoMensagem);

        if (!ObjectUtil.isNull(codTipoMensagem)) {
            String xmlMsg = getDelegate(codTipoMensagem).processarMensagem(idMensagem);
            postarMensagemFilaSSPBDDA(xmlMsg);
            integracaoCipDao.atualizarMensagemEnviada(idMensagem, xmlMsg);
        }
    }

    /**
     * Método responsável por recuperar o delegate (polimorficamente) da mensagem a ser processada.
     * 
     * @param codTipoMensagem
     * @return IntegracaoCipMensagemDelegate<IntegracaoCipServico>
     * @throws ComumException
     */
    private IntegracaoCipMensagemDelegate<IntegracaoCipServico> getDelegate(String codTipoMensagem) throws ComumException {
        return IntegracaoCipFabricaDelegates.getInstance().getMensagemDelegate(codTipoMensagem);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA,
     *      java.lang.String, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal) throws ComumException {
        incluir(mensagem, tipoMensagemDDA, -1, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#incluir(br.com.sicoob.sisbr.sicoobdda.interfaces.IMensagemDDA,
     *      java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.Short)
     */
    public void incluir(IMensagemDDA mensagem, String tipoMensagemDDA, Integer numPrioridadeEnvio, String usuarioSolicitante, Integer idInstitiucaoSolicitante, Short idCanal)
            throws ComumException {
        DateTimeDB dataMovimento = new DateTimeDB(getADMDelegate().obterDataMovimentoBancoob().getTime());
        incluir(mensagem, tipoMensagemDDA, dataMovimento, numPrioridadeEnvio, usuarioSolicitante, idInstitiucaoSolicitante, idCanal);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.MensagemDDAServico#listarTipoSituacaoBoleto()
     */
    public DominioCadastroMensagemDto obterDominioCadastroMensagem() throws ComumException {
        return new DominioCadastroMensagemDto(dao.listarTipoSituacaoBoleto(), dao.listarTipoBoletoConsultado());
    }
}
