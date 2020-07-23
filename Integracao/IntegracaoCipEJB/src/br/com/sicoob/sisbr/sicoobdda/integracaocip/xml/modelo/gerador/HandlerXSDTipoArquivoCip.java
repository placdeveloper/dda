package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.gerador;

import org.xml.sax.Attributes;

/**
 * XMLHandlerXSDCip
 * 
 * @author Rafael.Silva
 */
public class HandlerXSDTipoArquivoCip extends HandlerXSD {

    public HandlerXSDTipoArquivoCip(String pathArquivoSdda) {
        this.urlArquivo = pathArquivoSdda;

        listaTags.add(" name='ADDADOCPROComplexType'");
        listaTags.add(" name='ADDADOCERRComplexType'");
        listaTags.add(" name='BCARQERRComplexType'");
        listaTags.add(" name='BCARQComplexType'");
        listaTags.add(" name='Grupo_SeqComplexType'");
    }

    /** inicia uma tag */
    public void startElement(String uri, String localName, String tag, Attributes atributos) {
        galhoAtual.append("/" + tag);
        String strAtributosTag = getAtributosTag(atributos);
        if (tagExcluir == null && isTagExcluir(strAtributosTag)) {
            galhoAtual.append(tagExcluir);
        } else if (!temTagExcluirGalhoAtual()) {
            arquivoFinal.append(getTagCompleta(tag, atributos));
        }
        valorAtual.delete(0, valorAtual.length());
    }

    /*
     * (non-Javadoc)
     * 
     * @see main.java.br.com.teste.ejb.xmlcip.HandlerXSD#isTagExcluir(java.lang.String)
     */
    @Override
    public Boolean isTagExcluir(String strTag) {
        if (super.isTagExcluir(strTag)) {
            return Boolean.TRUE;
        } else if (strTag.contains(codErroRemoverTag)) {
            tagExcluir = "COD_ERRO";
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /** recebe os dados de uma tag */
    public void characters(char[] ch, int start, int length) {
        // adiciona ao valor atual
        valorAtual.append(ch, start, length);
    }

    /** termina uma tag */
    public void endElement(String uri, String localName, String tag) {
        if (isTagExcluirFechamento(tag)) {
            tag = tag + tagExcluir;
            tagExcluir = null;
        } else if (!temTagExcluirGalhoAtual()) {
            arquivoFinal.append(getValorComTagFechamento(tag, valorAtual.toString().trim()));
        }
        valorAtual.delete(0, valorAtual.length());
        galhoAtual.delete(galhoAtual.length() - tag.length() - 1, galhoAtual.length());
    }
}
