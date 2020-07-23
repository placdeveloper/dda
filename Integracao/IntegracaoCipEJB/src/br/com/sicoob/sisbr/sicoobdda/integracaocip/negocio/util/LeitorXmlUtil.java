/**
 * Projeto:         Cobrança Bancaria
 * Camada Projeto:  cip-integracao-ejb
 * Pacote:          br.com.sicoob.sisbr.centralizacaobeneficiario.cadastrobeneficiario.util
 * Arquivo:         LeitorXmlUtil.java
 * Data CriaÃ§Ã£o:    May 18, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.XMLCipDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.sax.HandlerXMLMensagemErroCIP;

/**
 * LeitorXmlUtil
 * 
 * @author Rafael.Silva
 */
public class LeitorXmlUtil {

    /**
     * 
     */
    private LeitorXmlUtil() {
    }

    /**
     * @return SicoobLoggerPadrao
     * 
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(LeitorXmlUtil.class);
    }

    /**
     * Realiza a conversao (unmarshal) de um arquivo XML em um objeto do seu tipo.
     * 
     * @param clazz classe referente ao objeto a ser criado a partir do XML.
     * @param inputStreamXml a ser convertido em objeto.
     * @return novo objeto.
     * @throws ComumException Object
     * 
     */
    public static Object desempacotarArquivo(Class<?> clazz, InputStream inputStreamXml) throws ComumException {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(inputStreamXml);
        } catch (JAXBException e) {
            getLogger().erro(e, MensagemUtil.getString("integracaocip.erro.unmarshal.objeto", clazz));
            throw new ComumException(e);
        }
    }

    /**
     * Realiza a conversao (unmarshal) de um arquivo XML em um objeto do seu tipo.
     * 
     * @param clazz classe referente ao objeto a ser criado a partir do XML.
     * @param fileXml nome do arquivo XML a ser convertido em objeto.
     * @return novo objeto.
     * @throws FileNotFoundException
     * @throws JAXBException
     * @throws ComumException
     * @throws CAPNegocioException
     */
    public Object desempacotarArquivo(Class<?> clazz, File fileXml) throws FileNotFoundException, JAXBException, ComumException {
        return desempacotarArquivo(clazz, new FileInputStream(fileXml));
    }

    /**
     * Método responsável por realizar o unmarshall do xml.
     * 
     * @param clazz
     * @param strXml
     * @return
     * @throws JAXBException Object
     * @throws ComumException
     * 
     */
    public static Object desempacotarArquivo(Class<?> clazz, String strXml) throws ComumException {
        try {
            return desempacotarArquivo(clazz, new ByteArrayInputStream(strXml.getBytes(Constantes.ENCODING_UTF_8)));
        } catch (UnsupportedEncodingException e) {
            throw new ComumException(e);
        }
    }

    /**
     * 
     * @param caminho
     * @return
     * @throws IOException
     */
    public static byte[] getBytes(String caminho) throws IOException {
        byte[] bytes = new byte[1024];
        FileInputStream input = null;
        try {
            int bytesLidos = 0;
            input = new FileInputStream(caminho);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((bytesLidos = input.read(bytes)) != -1) {
                baos.write(bytes, 0, bytesLidos);
            }
            baos.flush();
            baos.close();
            bytes = baos.toByteArray();

        } finally {
            if (input != null) {
                input.close();
            }
        }
        return bytes;
    }

    /**
     * @param dadoOriginal
     * @return String
     * 
     */
    public static String tratarDados(String dadoOriginal) {
        return dadoOriginal.replaceAll("&", "&amp;").replaceAll("\"", "&quot;").replaceAll("<", "&quot;").replaceAll(">", "&quot;");
    }

    /**
     * Método responsável por varrer o XML utilizando SAX para recuperar o idMensagemOrigem, o codTipoMensagem e os CodErro recebidos.
     * 
     * @param xml
     * @return
     * @throws ComumException XMLCipDto
     * 
     */
    public static XMLCipDto obterDadosXMLErroCip(String xml) throws ComumException {
        XMLCipDto erroXMLCipDto = null;
        try {
            HandlerXMLMensagemErroCIP handle = new HandlerXMLMensagemErroCIP();
            InputSource is = new InputSource(new StringReader(xml));
            SAXParserFactory.newInstance().newSAXParser().parse(is, handle);
            erroXMLCipDto = handle.getErroXMLCipDto();
        } catch (SAXException e) {
            throw new ComumException(e.getMessage(), e);
        } catch (IOException e) {
            throw new ComumException(e.getMessage(), e);
        } catch (ParserConfigurationException e) {
            throw new ComumException(e.getMessage(), e);
        }
        return erroXMLCipDto;
    }

}
