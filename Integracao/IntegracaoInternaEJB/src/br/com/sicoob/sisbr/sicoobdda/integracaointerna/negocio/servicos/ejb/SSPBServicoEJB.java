package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.interfaces.SSPBServicoLocal;
import br.com.sicoob.sisbr.sspb.api.negocio.delegates.ExposicaoCriptografiaDelegate;
import br.com.sicoob.sisbr.sspb.api.negocio.delegates.SSPBAPIFabricaDelegates;
import br.com.sicoob.sisbr.sspb.api.negocio.descriptors.EnumCriptCamara;
import br.com.sicoob.sisbr.sspb.api.negocio.descriptors.EnumCriptIndicativo;

/**
 * SSPBServicoEJB é responsável por
 * 
 * @author George.Santos
 */
@Stateless
@Local({ SSPBServicoLocal.class })
public class SSPBServicoEJB extends IntegracaoInternaServicoEJB implements SSPBServicoLocal {

    private static final String ERRO_DESCRIPTOGRAFAR_XML_RECEBIDO_SSPB = "integracaocip.erro.descriptografar.xml.recebido.sspb";
    private static final String ERRO_CRIPTOGRAFAR_XML_SSPB = "integracaocip.erro.criptografar.xml.sspb";

    /**
     * {@inheritDoc})
     * 
     * @throws IntegracaoInternaException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoServico#criptografar(byte[])
     */
    public byte[] criptografar(byte[] bytesXmlEnvio) throws IntegracaoInternaException {
        try {
            ExposicaoCriptografiaDelegate exposicaoCriptografiaDelegate = SSPBAPIFabricaDelegates.getInstance().criarExposicaoCriptografiaDelegate();
            return exposicaoCriptografiaDelegate.criptografar(bytesXmlEnvio, EnumCriptCamara.SPB02_CIP_NPC, EnumCriptIndicativo.I8);
        } catch (BancoobException e) {
            throw new IntegracaoInternaException(ERRO_CRIPTOGRAFAR_XML_SSPB, e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IntegracaoInternaException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.servicos.IntegracaoServico#decriptar(byte[])
     */
    public byte[] decriptar(byte[] bytesXml) throws IntegracaoInternaException {
        try {
            ExposicaoCriptografiaDelegate exposicaoCriptografiaDelegate = SSPBAPIFabricaDelegates.getInstance().criarExposicaoCriptografiaDelegate();
            return exposicaoCriptografiaDelegate.decriptar(bytesXml, EnumCriptCamara.SPB02_CIP_NPC);
        } catch (BancoobException e) {
            throw new IntegracaoInternaException(ERRO_DESCRIPTOGRAFAR_XML_RECEBIDO_SSPB, e);
        }
    }

}
