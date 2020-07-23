/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  teste-ejb
 * Pacote:          main.java.br.com.teste.ejb.xmlcip
 * Arquivo:         HandlerXSD.java
 * Data Criação:    Oct 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.gerador;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * HandlerXSD é responsável por
 * 
 * @author Rafael.Silva
 */
public class HandlerXSD extends DefaultHandler {

    /** o galho atual */
    protected StringBuffer galhoAtual = new StringBuffer(200);
    /** o valor da tag atual */
    protected StringBuffer valorAtual = new StringBuffer(100);

    protected String urlArquivo;
    protected String tipoArquivo;
    protected String codErroRemoverTag = "_CodErro";

    protected String tagExcluir = null;
    protected StringBuilder arquivoFinal = new StringBuilder();
    protected List<String> listaTags = new ArrayList<String>();

    private static Logger lg = Logger.getLogger(HandlerXSD.class.getName());

    /**
     * Método responsável por
     * 
     * @return Boolean
     * 
     */
    protected Boolean temTagExcluirGalhoAtual() {
        return !ObjectUtil.isNull(tagExcluir);
    }

    /**
     * Método responsável por
     * 
     * @param tag
     * @param atributos
     * @return String
     * 
     */
    protected String getTagCompleta(String tag, Attributes atributos) {
        StringBuilder strTag = new StringBuilder();
        strTag.append("<");
        strTag.append(tag);
        strTag.append(getAtributosTag(atributos).replace(codErroRemoverTag, ""));
        strTag.append(">");
        return strTag.toString();
    }

    /**
     * Método responsável por
     * 
     * @param atributos
     * @return String
     * 
     */
    protected String getAtributosTag(Attributes atributos) {
        StringBuilder strTag = new StringBuilder();
        for (int i = 0; i < atributos.getLength(); i++) {
            strTag.append(" ");
            strTag.append(atributos.getQName(i));
            strTag.append("='");
            strTag.append(atributos.getValue(i));
            strTag.append("'");
        }
        return strTag.toString();
    }

    /**
     * Método responsável por
     * 
     * @param tag
     * @param valorAtual
     * @return String
     * 
     */
    protected String getValorComTagFechamento(String tag, String valorAtual) {
        StringBuilder strTag = new StringBuilder();
        strTag.append(valorAtual);
        strTag.append("</" + tag + ">");
        return strTag.toString();
    }

    /**
     * Método responsável por
     * 
     * @param strTag
     * @return Boolean
     * 
     */
    protected Boolean isTagExcluir(String strTag) {
        for (String tag : listaTags) {
            if (tag.equals(strTag)) {
                tagExcluir = tag;
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * Método responsável por
     * 
     * @param tag
     * @return Boolean
     * 
     */
    protected Boolean isTagExcluirFechamento(String tag) {
        return tagExcluir != null && galhoAtual.substring(1).endsWith(tag + tagExcluir);
    }

    /**
     * Método responsável por
     * 
     * @return String
     * 
     */
    protected String getTagErroComplexType() {
        StringBuilder str = new StringBuilder();
        str.append("<xs:complexType name='TagErroComplexType'>");
        str.append("<xs:simpleContent>");
        str.append("<xs:extension base='xs:string'>");
        str.append("<xs:attribute name='CodErro' type='CodErro'/>");
        str.append("</xs:extension>");
        str.append("</xs:simpleContent>");
        str.append("</xs:complexType>");

        return str.toString();
    }

    /**
     * @param dados
     * @param destinoArquivo
     * @throws IOException void
     * 
     */
    protected void gravarArquivo(byte[] dados, String destinoArquivo) throws IOException {
        ByteArrayOutputStream baos = null;
        FileOutputStream fost = null;
        try {
            baos = new ByteArrayOutputStream();
            fost = new FileOutputStream(destinoArquivo);
            baos.write(dados);
            baos.writeTo(fost);

        } finally {
            if (fost != null) {
                fost.close();
            }
        }
        lg.logp(Level.INFO, HandlerXSD.class.getName(), "gravarArquivo(byte[] dados, String destinoArquivo)", "##### Arquivo gerado --> " + destinoArquivo);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        try {
            gravarArquivo(arquivoFinal.toString().getBytes(Constantes.ENCODING_UTF_8), urlArquivo);
        } catch (UnsupportedEncodingException e) {
            lg.logp(Level.INFO, HandlerXSD.class.getName(), "endDocument()", e.getMessage(), e);
        } catch (IOException e) {
            lg.logp(Level.INFO, HandlerXSD.class.getName(), "endDocument()", e.getMessage(), e);
        }
    }

}
