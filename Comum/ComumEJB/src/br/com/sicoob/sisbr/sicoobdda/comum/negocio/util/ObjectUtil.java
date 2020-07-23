package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.beanutils.PropertyUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.tipos.DateTime;

/**
 * ObjectUtil é responsável por
 * 
 * @author Rodrigo.Neri
 */
public final class ObjectUtil {

    private static final double ZERO = 0;

    private static final SicoobLoggerPadrao LOG = SicoobLoggerPadrao.getInstance(ObjectUtil.class);

    private ObjectUtil() {
        // not called
    }

    /**
     * Método responsável por fazer uma cópia completa do objeto.
     * 
     * @param object
     * @return
     * @throws IOException
     * @throws ClassNotFoundException Object
     */
    @SuppressWarnings("unchecked")
    public static <T extends SicoobDDAEntidade> T copy(T object) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();

            ByteArrayInputStream bin = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bin);

            return (T) ois.readObject();
        } finally {
            if (!isNull(oos)) {
                oos.close();
            }

            if (!isNull(ois)) {
                ois.close();
            }
        }
    }

    /**
     * Copia as propriedades do objeto.
     * 
     * @param source Object Objeto fonte do dado.
     * @param target Object Objeto para onde sera copiado as informações.
     * @param properties String[] Lista com as propriedades.
     */
    public static void copy(Object source, Object target, String[] properties) {
        for (String property : properties) {
            try {
                PropertyUtils.setProperty(target, property, PropertyUtils.getProperty(source, property));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.info(e.getMessage());
                // Ignore
            }
        }
    }

    /**
     * Método responsável por copiar todos os atributos da classe de origem para a classe de destino. <br>
     * <br>
     * <b>OBS:</b>
     * <li>- Os atributos devem possuir o mesmo nome</li>
     * <li>- Copia listas, objetos...</li> <br>
     * <br>
     * 
     * @param origem
     * @param destino
     */
    public static void copy(Object origem, Object destino) {
        Field[] fieldsOrigem = origem.getClass().getDeclaredFields();
        Class<? extends Object> classeDestino = destino.getClass();

        for (Field fieldOrigem : fieldsOrigem) {
            if (Modifier.isFinal(fieldOrigem.getModifiers()) || Modifier.isStatic(fieldOrigem.getModifiers()) || Modifier.isTransient(fieldOrigem.getModifiers())) {
                continue;
            }

            try {
                Field fieldDestino = classeDestino.getDeclaredField(fieldOrigem.getName());

                fieldOrigem.setAccessible(true);
                Object objetoOrigem = fieldOrigem.get(origem);

                if (fieldDestino != null && objetoOrigem != null) {
                    fieldDestino.setAccessible(true);

                    Class<?> typeDestino = fieldDestino.getType();

                    if (typeDestino.isAssignableFrom(String.class) || typeDestino.isAssignableFrom(Character.class) || Number.class.isAssignableFrom(typeDestino)
                            || Boolean.class.isAssignableFrom(typeDestino) || typeDestino.isAssignableFrom(Date.class) || typeDestino.isAssignableFrom(DateTime.class)
                            || typeDestino.isAssignableFrom(DateTimeDB.class) || typeDestino.isAssignableFrom(XMLGregorianCalendar.class)) {
                        fieldDestino.set(destino, objetoOrigem);
                    } else if (List.class.isAssignableFrom(typeDestino)) {
                        List<?> listaOrigem = (List<?>) objetoOrigem;
                        List<Object> listaDestino = new ArrayList<Object>(listaOrigem.size());

                        for (Object obj : listaOrigem) {
                            Type genericType = fieldDestino.getGenericType();

                            if (genericType instanceof ParameterizedType) {
                                ParameterizedType parameterizedType = (ParameterizedType) genericType;

                                if (parameterizedType.getActualTypeArguments().length > 0) {
                                    // Obtém o tipo da lista
                                    Class<?> typeLista = (Class<?>) parameterizedType.getActualTypeArguments()[0];

                                    // Instancia um novo objeto
                                    Object objetoDestino = typeLista.newInstance();
                                    // Copia os atributos
                                    copy(obj, objetoDestino);
                                    // Adiciona na lista
                                    listaDestino.add(objetoDestino);
                                }
                            }
                        }

                        // Define a lista no campo
                        fieldDestino.set(destino, listaDestino);
                    } else {
                        // Faz a instância de um novo objeto
                        Object objetoDestino = typeDestino.newInstance();
                        // Copia os atributos
                        copy(objetoOrigem, objetoDestino);
                        // Define no campo
                        fieldDestino.set(destino, objetoDestino);
                    }
                }
            } catch (SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
                LOG.alerta(e, e.getMessage());
            } catch (NoSuchFieldException e) {
                LOG.alerta("Campo não encontrado ao copiar objeto: " + fieldOrigem.getName());
            }
        }
    }

    /**
     * Método responsável por validar se a String informada é nula ou vazia.
     * 
     * @param str
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || str.trim().length() == 0;
    }

    /**
     * Método responsável por validar se o número informado é nulo ou igual a zero.
     * 
     * @param number
     * @return boolean
     */
    public static boolean isEmpty(Number number) {
        return isNull(number) || number.doubleValue() == ZERO;
    }

    /**
     * Método responsável por validar se a lista é nula ou está vazia.
     * 
     * @param lista
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> lista) {
        return isNull(lista) || lista.isEmpty();
    }

    /**
     * Método responsável por validar se o mapa é nulo ou está vazio.
     * 
     * @param mapa
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> mapa) {
        return isNull(mapa) || mapa.isEmpty();
    }

    /**
     * Método responsável por validar se a String contem apenas zeros.
     * 
     * @param mapa
     * @return boolean
     */
    public static boolean isZero(String str) {
        if (isEmpty(str)) {
            return Boolean.FALSE;
        } else {
            int i = 0;
            while (i < str.length()) {
                if (str.charAt(i) != '0') {
                    return Boolean.FALSE;
                }
                i++;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Método responsável por validar se o objeto é nulo.
     * 
     * @param obj
     * @return boolean
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * Método responsável por
     * 
     * @param blob
     * @return
     * @throws SQLException byte[]
     * 
     */
    public static byte[] obterBytes(Blob blob) throws SQLException {
        if (isNull(blob)) {
            return null;
        }

        return blob.getBytes(1, (int) blob.length());
    }

    /**
     * Método responsável por
     * 
     * @param xml
     * @return
     * @throws ComumException String
     * 
     */
    public static String identXmlString(String xml) throws ComumException {
        if (!isEmpty(xml)) {
            try {
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

                XPath xPath = XPathFactory.newInstance().newXPath();
                NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']", document, XPathConstants.NODESET);

                for (int i = 0; i < nodeList.getLength(); ++i) {
                    Node node = nodeList.item(i);
                    node.getParentNode().removeChild(node);
                }

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                StringWriter stringWriter = new StringWriter();
                StreamResult streamResult = new StreamResult(stringWriter);

                transformer.transform(new DOMSource(document), streamResult);
                return stringWriter.toString();
            } catch (Exception e) { // NOSONAR
                LOG.erro(e, e.getMessage());
                throw new ComumException(e);
            }
        }
        return null;
    }

    /**
     * Método responsável por converter o Character em String
     * 
     * @param valor
     * @return String
     */
    public static String converterValor(Character valor) {
        return ObjectUtil.isNull(valor) ? null : valor.toString();
    }

    /**
     * Método responsável por converter a String em Character
     * 
     * @param valor
     * @return Character
     */
    public static Character converterValor(String valor) {
        return ObjectUtil.isEmpty(valor) ? null : valor.charAt(0);
    }

    /**
     * Método responsável por
     * 
     * @param list
     * @param L
     * @return List<List<T>>
     * 
     */
    public static <T> List<List<T>> chopped(List<T> list, final int L) {
        List<List<T>> parts = new ArrayList<List<T>>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
        }
        return parts;
    }

    /**
     * Método responsável por fazer o log das informações do objeto.
     * 
     * @param obj
     */
    public static void log(Object obj) {
        LOG.debug(Constantes.STR_SEPARACAO);
        LOG.debug("LOG DO OBJETO: " + obj);
        LOG.debug(Constantes.STR_SEPARACAO);

        if (isNull(obj)) {
            return;
        }

        Field[] campos = obj.getClass().getDeclaredFields();

        for (Field field : campos) {
            if (Modifier.isFinal(field.getModifiers()) || Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                continue;
            }

            try {
                field.setAccessible(true);

                String nome = field.getName();
                Object valor = field.get(obj);

                LOG.debug(nome + ": " + valor);
            } catch (IllegalAccessException e) {
                LOG.alerta("Erro: " + e.getMessage());
            } catch (SecurityException e) {
                LOG.alerta("Erro: " + e.getMessage());
            }
        }
    }

    /**
     * Método responsável por remover caracteres invisíveis
     * 
     * @param texto
     * @return
     */
    public static String removerCaracteresInvalidos(String texto) {
        LOG.debug("### Removendo caracteres inválidos...");
        LOG.debug("Parâmetro - texto: " + texto);

        if (isEmpty(texto)) {
            return texto;
        }

        String resultado = texto.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "").replaceAll("\\p{C}", "");
        LOG.debug("Resultado: " + resultado);

        return resultado;
    }

}
