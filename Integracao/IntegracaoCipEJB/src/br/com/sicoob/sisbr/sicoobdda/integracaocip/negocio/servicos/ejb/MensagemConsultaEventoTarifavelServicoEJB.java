/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MensagemConsultaEventoTarifavelServicoEJB.java
 * Data Criação:    Oct 21, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultaEventoTarifavelServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemConsultaEventoTarifavelServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemConsultaEventoTarifavelServicoLocal.class })
public class MensagemConsultaEventoTarifavelServicoEJB extends IntegracaoCipServicoEJB implements MensagemConsultaEventoTarifavelServicoLocal {

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

    public String processarMensagem(Long idMensagem) throws ComumException {
        debug("########### Inicio processarMensagem(Long idMensagem) para DDA0200 - Consulta Extrato de Eventos Tarifaveis");

        MensagemDDA mensagem = dao.obterMensagemDDA(idMensagem);
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
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        // TODO Esperar definição de negócio.
    }

}
