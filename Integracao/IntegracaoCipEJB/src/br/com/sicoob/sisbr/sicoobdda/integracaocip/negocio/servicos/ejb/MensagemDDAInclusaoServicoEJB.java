package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.annotations.Dao;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.CanalEnum;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemDDAInclusaoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;

/**
 * MensagemDDASInclusaoervicoEJB
 * 
 * @author Francisco.Marcio
 */
@Stateless
@Local({ MensagemDDAInclusaoServicoLocal.class })
public class MensagemDDAInclusaoServicoEJB extends IntegracaoCipServicoEJB implements MensagemDDAInclusaoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao mensagemDdaDao;

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
     * @return o atributo mensagemDdaDao
     */
    public MensagemDDADao getMensagemDDADao() {
        return mensagemDdaDao;
    }

    /**
     * Método responsável por persistir MensagemDDA depois de ter ocorrido algum erro no processamento do xml
     * 
     * @param TipoMensagemDDA tipoMsg
     * @param String xmlRecebido
     * @return MensagemDDA
     * 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void incluirMensagemDDA(String xmlRecebido) throws BancoobException {
        TipoMensagemDDA tipoMsg = getEm().find(TipoMensagemDDA.class, TipoMensagemDDA.ERRO001);
        getMensagemDDADao().incluir(criarMensagemDDA(xmlRecebido, tipoMsg));
    }

    /**
     * Método responsável por criar MensagemDDA para ser persistido.
     * 
     * @param TipoMensagemDDA tipoMsg
     * @param String xmlRecebido
     * @return MensagemDDA
     * 
     */
    private MensagemDDA criarMensagemDDA(String xmlRecebido, TipoMensagemDDA tipoMsg) {
        MensagemDDA msgDDA = new MensagemDDA();
        msgDDA.setTipoMensagemDDA(tipoMsg);
        msgDDA.setDataMovimento(new DateTimeDB());
        msgDDA.setBolOrigemSicoob(Boolean.TRUE);
        msgDDA.setDataHoraCadastro(new DateTimeDB());
        msgDDA.setNumPrioridadeEnvio(tipoMsg.getNumPrioridadeEnvio());
        msgDDA.setXmlMensagem(xmlRecebido);
        msgDDA.setIdUsuarioSolicitante(getUsuarioLogado());
        msgDDA.setIdInstituicaoSolicitante(getIdInstituicaoLogado());
        msgDDA.setIdCanal(CanalEnum.RETAGUARDA.getId());
        return msgDDA;
    }

}
