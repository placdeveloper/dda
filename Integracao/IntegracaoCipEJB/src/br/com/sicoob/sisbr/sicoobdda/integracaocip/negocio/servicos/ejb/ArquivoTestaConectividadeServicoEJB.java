/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb
 * Arquivo:         ArquivoTestaConectividadeServicoEJB.java
 * Data Criação:    May 11, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.persistencia.annotations.Dao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.interfaces.ArquivoTestaConectividadeServicoLocal;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDaoFactory;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemDDADao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001.AGEN001ComplexType;

/**
 * ArquivoTestaConectividadeServicoEJB é responsável por
 * 
 * @author Adriano.Pinheiro
 */
@Stateless
@Local({ ArquivoTestaConectividadeServicoLocal.class })
public class ArquivoTestaConectividadeServicoEJB extends IntegracaoCipServicoEJB implements ArquivoTestaConectividadeServicoLocal {

    @PersistenceContext(unitName = "emSicoobDDA_db2")
    private EntityManager em;

    @Dao(entityManager = "em", fabrica = IntegracaoCipDaoFactory.class)
    private MensagemDDADao msgDao;

    // TODO - ingnorar no snv

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoCipServicoArquivo#obterSISARQ(java.lang.Long)
     */
    public Object obterSISARQ(Long idLogEnvioArquivoDDA) throws ComumException {
        // try {
        //
        // SISARQComplexType srq = new SISARQComplexType();
        // MensagemDDA msg = getMsgDao().obterMensagemDeTesteDeConectividadeAGEN001(idLogEnvioArquivoDDA);
        // AGEN001ComplexType msgComplexType = new AGEN001ComplexType();
        // montaObjetoAgen001ComplexType(msgComplexType, msg.getXmlMensagem());
        // srq.setAGEN001(msgComplexType);
        //
        // return srq;
        //
        // } catch (NullPointerException e) {
        // throw new ComumException(e);
        // }
        return null;
    }

    /**
     * Método responsável por void
     * 
     */
    private void montaObjetoAgen001ComplexType(AGEN001ComplexType prObjAGEN001, String prXml) {
        prObjAGEN001.setISPBDestinatario(Constantes.ISPB_CIP_SITRAF);
        prObjAGEN001.setISPBEmissor(Constantes.ISPB_BANCOOB);
        prObjAGEN001.setMsgECO(extraiMsgEco(prXml));
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.negocio.servicos.BancoobServico#verificarDisponibilidade()
     */
    public void verificarDisponibilidade() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.ejb.IntegracaoCipServicoEJB#getEm()
     */
    @Override
    protected EntityManager getEm() {
        return this.em;
    }

    private String extraiMsgEco(String prXml) {
        int posInicio = prXml.indexOf("<MsgECO>");
        int posFinal = prXml.indexOf("</MsgECO>");

        if (posInicio == -1 || posFinal == -1) {
            return null;
        } else {
            return prXml.substring(posInicio + 8, posFinal);
        }
    }
}
