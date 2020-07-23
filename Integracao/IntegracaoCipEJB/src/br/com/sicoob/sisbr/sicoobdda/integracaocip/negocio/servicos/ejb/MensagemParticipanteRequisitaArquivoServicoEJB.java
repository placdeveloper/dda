package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemParticipanteRequisitaArquivoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemParticipanteRequisitaArquivoServicoEJB é responsável por
 * 
 * @author Rodrigo.Neri
 */
@Stateless
@Local({ MensagemParticipanteRequisitaArquivoServicoLocal.class })
public class MensagemParticipanteRequisitaArquivoServicoEJB extends IntegracaoCipServicoEJB implements MensagemParticipanteRequisitaArquivoServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao dao;

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
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagemDDA) throws ComumException {
        debug("### Processando a mensagem do participante de requisição de mensagem...");
        debug("Parâmetro - idMensagemDDA: " + idMensagemDDA);

        MensagemDDA mensagem = dao.obterMensagemDDA(idMensagemDDA);
        debug("MensagemDDA: " + mensagem);

        /*
         * Esse processo já teve a mensagem XML preenchida quando veio da tela, por isso recupera o XML que já foi salvo.
         */
        String xml = mensagem.getXmlMensagem();
        debug("Xml MensagemDDA: " + xml);

        // Se não houver xml
        if (ObjectUtil.isEmpty(xml)) {
            throw new ComumException("integracaocip.erro.xml.mensagem.invalido");
        }

        // Substituição de "&lt" e "&gt" por "<" e ">" gerados no marshal do campo CDATA "critSelec".
        xml = xml.replace("&lt;", "<").replace("&gt;", ">");
        debug("Xml MensagemDDA - após replace: " + xml);

        return xml;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarRetornoMensagemDDA(br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida)
     */
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida retornoDDA) throws ComumException {
    }

}
