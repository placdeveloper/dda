/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MensagemConsultaExtratoProcessamentoServicoEJB.java
 * Data Criação:    Oct 21, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemConsultaExtratoProcessamentoServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0214.DDA0214ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemConsultaExtratoProcessamentoServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemConsultaExtratoProcessamentoServicoLocal.class })
public class MensagemConsultaExtratoProcessamentoServicoEJB extends IntegracaoCipServicoEJB implements MensagemConsultaExtratoProcessamentoServicoLocal {

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
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0214 - Participante consulta extrato de processamento");
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
    public void processarRetornoMensagemDDA(ConteudoMsgRecebida conteudoMsg) throws ComumException {
        // TODO Esperar definição de negócio.

    }

    /**
     * Método responsável por criar o objeto de envio para a CIP.
     * 
     * @param mensagem
     * @return
     * @throws ComumException DDA0214ComplexType
     * 
     */
    public DDA0214ComplexType getDDA0214ComplexType(MensagemDDA mensagem) throws ComumException {
        DDA0214ComplexType dda = new DDA0214ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0214);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPart(Constantes.ISPB_BANCOOB);

        dda.setDtRef(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(mensagem.getDataHoraCadastro()));

        // TODO george.santos verificar com o jorge a respeito desses campos.
        // dda.setDtHrIni(value);
        // dda.setDtHrFim(value);

        // dda.setTpMsgArq(value);

        dda.setCodMsgArq(mensagem.getTipoMensagemDDA().getCodTipoMensagem());

        // M - Mensagem
        // X - Arquivo XML
        dda.setTpRet(TipoRetornoEnum.M.getCodDominio());

        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataMovimento()));

        return dda;
    }
}
