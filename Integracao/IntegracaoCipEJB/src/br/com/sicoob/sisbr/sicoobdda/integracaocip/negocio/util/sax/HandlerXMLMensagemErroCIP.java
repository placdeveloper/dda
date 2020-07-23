package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.sax;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.XMLCipDto;

/**
 * HandlerXMLMensagemErroCIP é reponsável por varrer o xml das mensagens de erro utilizando SAX para retirar o numCtrlPart (IdMensagem) e a lista de erros
 * devolvidos pela CIP.
 * 
 * @author Rafael.Silva
 */
public class HandlerXMLMensagemErroCIP extends DefaultHandler {

    /** o galho atual */
    private StringBuffer galhoAtual = new StringBuffer(200);
    /** o valor da tag atual */
    private StringBuffer valorAtual = new StringBuffer(100);

    private static final String TAG_NUM_CTRL_PART = "NumCtrlPart";
    private static final String TAG_NUM_CTRL_REQ_PART = "NumCtrlReqPart";
    private static final String ATRIB_COD_ERRO = "CodErro";
    private static final String COD_MSG = "CodMsg";

    private XMLCipDto erroXMLCipDto = new XMLCipDto();

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    /** inicia uma tag */
    public void startElement(String uri, String localName, String tag, Attributes atributos) {
        galhoAtual.append("/" + tag);
        setCodErro(atributos);
        valorAtual.delete(0, valorAtual.length());
    }

    protected void setCodErro(Attributes atributos) {
        for (int i = 0; i < atributos.getLength(); i++) {
            if (atributos.getQName(i).equalsIgnoreCase(ATRIB_COD_ERRO)) {
                erroXMLCipDto.getListaCodErro().add(atributos.getValue(i));
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    /** recebe os dados de uma tag */
    public void characters(char[] ch, int start, int length) {
        valorAtual.append(ch, start, length);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    /** termina uma tag */
    public void endElement(String uri, String localName, String tag) {
        if (tag.equalsIgnoreCase(TAG_NUM_CTRL_PART) || tag.equalsIgnoreCase(TAG_NUM_CTRL_REQ_PART)) {
            erroXMLCipDto.setIdMensagem(Long.valueOf(valorAtual.toString().trim()));
        } else if (tag.equalsIgnoreCase(COD_MSG)) {
            erroXMLCipDto.setCodTipoMensagem((valorAtual.toString().trim()));
        }
        valorAtual.delete(0, valorAtual.length());
        galhoAtual.delete(galhoAtual.length() - tag.length() - 1, galhoAtual.length());
    }

    /**
     * @return the erroXMLCipDto
     */
    public XMLCipDto getErroXMLCipDto() {
        return erroXMLCipDto;
    }

    /**
     * @param erroXMLCipDto the erroXMLCipDto to set
     */
    public void setErroXMLCipDto(XMLCipDto erroXMLCipDto) {
        this.erroXMLCipDto = erroXMLCipDto;
    }
}
