/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         MensagemRequisicaoArquivoMensagemServicoEJB.java
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
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.MensagemRequisicaoArquivoMensagemServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.DataCipUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.DDA0215ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.GrupoDDA0215DettComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.recebimento.ConteudoMsgRecebida;

/**
 * MensagemRequisicaoArquivoMensagemServicoEJB é responsável por
 * 
 * @author Felipe.Rosa
 */
@Stateless
@Local({ MensagemRequisicaoArquivoMensagemServicoLocal.class })
public class MensagemRequisicaoArquivoMensagemServicoEJB extends IntegracaoCipServicoEJB implements MensagemRequisicaoArquivoMensagemServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return em;
    }

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private IntegracaoCipDao dao;

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoMensagem#processarMensagem(java.lang.Long)
     */
    public String processarMensagem(Long idMensagemDDA) throws ComumException {
        getLogger().debug("########### Inicio processarMensagem(Long idMensagem) para DDA0215 - Participante requisita regeracao de arquivo ou mensagem");
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
     * @throws ComumException DDA0215ComplexType
     * 
     */
    public DDA0215ComplexType getDDA0215ComplexType(MensagemDDA mensagem) throws ComumException {
        DDA0215ComplexType dda = new DDA0215ComplexType();
        dda.setCodMsg(TipoMensagemDDA.DDA0215);
        dda.setNumCtrlPart(mensagem.getId().toString());
        dda.setISPBPartPrincipal(Constantes.ISPB_BANCOOB);

        // dda.setTpMsgArq(value);
        dda.setCodMsgArq(mensagem.getTipoMensagemDDA().getCodTipoMensagem());

        GrupoDDA0215DettComplexType grupo = new GrupoDDA0215DettComplexType();
        grupo.setNUOpOr(mensagem.getNumOperacao());
        // grupo.setIdentdArqOr(value);
        dda.getGrupoDDA0215Dett().add(grupo);

        dda.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(mensagem.getDataMovimento()));

        return dda;
    }

}
