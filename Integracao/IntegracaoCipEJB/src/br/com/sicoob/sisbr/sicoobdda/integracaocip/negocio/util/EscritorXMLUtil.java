package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.AGEN001.AGEN001ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0001.DDA0001ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0002.DDA0002ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0005.DDA0005ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0006.DDA0006ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0101.DDA0101ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0102.DDA0102ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0104.DDA0104ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0106.DDA0106ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0108.DDA0108ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0110.DDA0110ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0115.DDA0115ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0118.DDA0118ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0121.DDA0121ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0122.DDA0122ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0200.DDA0200ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0214.DDA0214ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0215.DDA0215ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0401.DDA0401ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0501.DDA0501ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0502.DDA0502ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0503.DDA0503ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0504.DDA0504ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.DDA0505.DDA0505ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.GEN0014.GEN0014ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.envio.HeaderEnvio;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.envio.MQEnvio;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.sspb.envio.MsgSPBEnvio;

/**
 * EscritorXMLUtil é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class EscritorXMLUtil {

    /**
     * 
     */
    private EscritorXMLUtil() {
    }

    /**
     * Método responsável por criar cabeçalho MqEnvio da mensagem de envio ao SSPB - CIP.
     * 
     * @param numSeq
     * @return MQEnvio
     * 
     */
    public static MQEnvio preencherCabecalhoEnvio(Number numSeq) {
        HeaderEnvio header = new HeaderEnvio();
        header.setCooperativa(Constantes.NUM_COOP_0001);
        header.setDataHora(DataCipUtil.obterStringDataAtualBacen());
        header.setNumSeq(numSeq != null ? numSeq.longValue() : null);

        MQEnvio mqEnvio = new MQEnvio();
        mqEnvio.setHeader(header);
        mqEnvio.setMsgSPB(new MsgSPBEnvio());
        return mqEnvio;
    }

    /**
     * Método responsável por gerar o xml de envio ao SSPB - CIP
     * 
     * @param object
     * @param number
     * @param numCooperativa
     * @return
     * @throws ComumException String
     * 
     */
    public static String obterXmlEnvio(Object object, Long numSeq) throws ComumException {
        MQEnvio mqEnvio = preencherCabecalhoEnvio(numSeq);
        setMsgEnvio(object, mqEnvio);

        return gerarXml(mqEnvio);
    }

    /**
     * Método responsável por setar o objeto utilizado no envio.
     * 
     * @param object
     * @param mqEnvio
     * @throws ComumException void
     * 
     */
    public static void setMsgEnvio(Object object, MQEnvio mqEnvio) throws ComumException {
        if (object instanceof DDA0110ComplexType) {
            mqEnvio.getMsgSPB().setDDA0110((DDA0110ComplexType) object);
        } else if (object instanceof DDA0501ComplexType) {
            mqEnvio.getMsgSPB().setDDA0501((DDA0501ComplexType) object);
        } else if (object instanceof DDA0502ComplexType) {
            mqEnvio.getMsgSPB().setDDA0502((DDA0502ComplexType) object);
        } else if (object instanceof DDA0503ComplexType) {
            mqEnvio.getMsgSPB().setDDA0503((DDA0503ComplexType) object);
        } else if (object instanceof DDA0504ComplexType) {
            mqEnvio.getMsgSPB().setDDA0504((DDA0504ComplexType) object);
        } else if (object instanceof DDA0505ComplexType) {
            mqEnvio.getMsgSPB().setDDA0505((DDA0505ComplexType) object);
        } else if (object instanceof GEN0014ComplexType) {
            mqEnvio.getMsgSPB().setGen0014((GEN0014ComplexType) object);
        } else if (object instanceof DDA0001ComplexType) {
            mqEnvio.getMsgSPB().setDDA0001((DDA0001ComplexType) object);
        } else if (object instanceof DDA0002ComplexType) {
            mqEnvio.getMsgSPB().setDDA0002((DDA0002ComplexType) object);
        } else if (object instanceof DDA0005ComplexType) {
            mqEnvio.getMsgSPB().setDDA0005((DDA0005ComplexType) object);
        } else if (object instanceof DDA0006ComplexType) {
            mqEnvio.getMsgSPB().setDDA0006((DDA0006ComplexType) object);
        } else if (object instanceof DDA0101ComplexType) {
            mqEnvio.getMsgSPB().setDDA0101((DDA0101ComplexType) object);
        } else if (object instanceof DDA0102ComplexType) {
            mqEnvio.getMsgSPB().setDDA0102((DDA0102ComplexType) object);
        } else if (object instanceof DDA0104ComplexType) {
            mqEnvio.getMsgSPB().setDDA0104((DDA0104ComplexType) object);
        } else if (object instanceof DDA0106ComplexType) {
            mqEnvio.getMsgSPB().setDDA0106((DDA0106ComplexType) object);
        } else if (object instanceof DDA0108ComplexType) {
            mqEnvio.getMsgSPB().setDDA0108((DDA0108ComplexType) object);
        } else if (object instanceof DDA0115ComplexType) {
            mqEnvio.getMsgSPB().setDDA0115((DDA0115ComplexType) object);
        } else if (object instanceof DDA0118ComplexType) {
            mqEnvio.getMsgSPB().setDDA0118((DDA0118ComplexType) object);
        } else if (object instanceof DDA0121ComplexType) {
            mqEnvio.getMsgSPB().setDDA0121((DDA0121ComplexType) object);
        } else if (object instanceof DDA0122ComplexType) {
            mqEnvio.getMsgSPB().setDDA0122((DDA0122ComplexType) object);
        } else if (object instanceof DDA0401ComplexType) {
            mqEnvio.getMsgSPB().setDDA0401((DDA0401ComplexType) object);
        } else if (object instanceof DDA0200ComplexType) {
            mqEnvio.getMsgSPB().setDDA0200((DDA0200ComplexType) object);
        } else if (object instanceof DDA0214ComplexType) {
            mqEnvio.getMsgSPB().setDDA0214((DDA0214ComplexType) object);
        } else if (object instanceof DDA0215ComplexType) {
            mqEnvio.getMsgSPB().setDDA0215((DDA0215ComplexType) object);
        } else if (object instanceof AGEN001ComplexType) {
            mqEnvio.getMsgSPB().setAgen001((AGEN001ComplexType) object);
        } else {
            throw new ComumException("integracaocip.erro.objeto.invalido.envio.msg", object.getClass());
        }

    }

    /**
     * Método responsável por realizar o marshal do objeto recebido.
     * 
     * @param object
     * @return
     * 
     */
    public static String gerarXml(Object object) throws ComumException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass().getPackage().getName());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Constantes.ENCODING_UTF_8);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(object, outputStream);

        } catch (JAXBException e) {
            throw new ComumException("integracaocip.erro.marshal.objeto", object.getClass(), e);
        }
        return replaceCaracteresEspeciaisCIP(outputStream.toString());
    }

    /**
     * Método responsável por substituir os caracteres especiais (&, ") HTML que sao gerados incorretamente pelo processo de marshall.
     * 
     * @param xmlEnvio
     * @return String
     * 
     */
    private static String replaceCaracteresEspeciaisCIP(String xmlEnvio) {
        return xmlEnvio.replaceAll("&amp;", "E").replaceAll("&quot;", "");
    }

}