package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.gerador;

import org.xml.sax.Attributes;

/**
 * XMLHandlerXSDCip
 * 
 * @author Rafael.Silva
 */
public class HandlerXSDArquivoCip extends HandlerXSD {

    public HandlerXSDArquivoCip(String pathArquivoSdda, String tipoArquivo) {
        this.urlArquivo = pathArquivoSdda;
        this.tipoArquivo = tipoArquivo;

        listaTags.add(" name='ADDADOC' type='ADDADOCComplexType'");
        listaTags.add(" name='ADDADOCComplexType'");
        listaTags.add(" name='" + tipoArquivo + "RET' type='" + tipoArquivo + "RETComplexType'");
        listaTags.add(" name='" + tipoArquivo + "RETComplexType'");
        listaTags.add(" name='CodErro' type='CodErro' use='optional'");
    }

    /** inicia uma tag */
    public void startElement(String uri, String localName, String tag, Attributes atributos) {
        galhoAtual.append("/" + tag);
        String strAtributosTag = getAtributosTag(atributos);
        // System.out.println(galhoAtual);
        if (tagExcluir == null && isTagExcluir(strAtributosTag)) {
            galhoAtual.append(tagExcluir);
        } else if (!temTagExcluirGalhoAtual()) {
            arquivoFinal.append(getTagCompleta(tag, atributos));
        }
        valorAtual.delete(0, valorAtual.length());
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
