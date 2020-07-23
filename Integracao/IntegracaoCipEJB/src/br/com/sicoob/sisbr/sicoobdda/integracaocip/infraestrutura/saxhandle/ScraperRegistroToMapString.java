/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.saxhandle
 * Arquivo:         ScraperRegistroToMapString.java
 * Data Criação:    Jan 20, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.saxhandle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.LeituraADDARR2756Exception;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.LeitorXmlUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;

/**
 * ScraperRegistroToMapString é responsável por
 * 
 * @author Adriano.Pinheiro
 */
public class ScraperRegistroToMapString extends DefaultHandler {

    // private static final String TIPO_MSG_ADDA121 = "ADDA121";
    // private static final String TAG_DE_DISTINCAO = "Grupo_ADDA121RR2_Tit";

    private static HashMap<String, String[]> tokensRetorno = new HashMap<String, String[]>();
    private static HashMap<String, String[]> tokensDistribuicao = new HashMap<String, String[]>();

    static final String MASCARA_TAG = "<CodMsg>%1$s</CodMsg>";
    static final String MASCARA_ATRIBUTO = " %1$s=\"%2$s\"";

    /*
     * ADDA101RET, ADDA101ERR, ADDA101PRO ADDA102RET, ADDA102ERR, ADDA102PRO ADDA114RET, ADDA114ERR, ADDA114PRO
     * 
     * ADDA002 OK ADDA003 OK ADDA101RR2 OK ADDA102RR2 ADDA104RR2 ADDA106 ADDA108RR2 ADDA114RR2 ADDA115RR2 ADDA117 ADDA118RR2 ADDA120 ADDA121RR2 ADDA121RR3 ADDA122RR2 ADDA127 ADDA200 ADDA504
     */
    static {
        tokensDistribuicao.put("ADDA002", new String[] { "Grupo_ADDA002_Pagdr", "ADDA002" });
        tokensDistribuicao.put("ADDA003", new String[] { "Grupo_ADDA003_PagdrDDA", "ADDA003" });
        tokensDistribuicao.put("ADDA101RR2", new String[] { "Grupo_ADDA101RR2_Tit", "ADDA101RR2" });
        tokensDistribuicao.put("ADDA102RR2", new String[] { "Grupo_ADDA102RR2_Tit", "ADDA102RR2" });
        tokensDistribuicao.put("ADDA104RR2", new String[] { "Grupo_ADDA104RR2_Tit", "ADDA104RR2" });
        tokensDistribuicao.put("ADDA106", new String[] { "Grupo_ADDA106_Tit", "ADDA106" });
        tokensDistribuicao.put("ADDA108RR2", new String[] { "Grupo_ADDA108RR2_Tit", "ADDA108RR2" });
        tokensDistribuicao.put("ADDA114RR2", new String[] { "Grupo_ADDA114RR2_Tit", "ADDA114RR2" });
        tokensDistribuicao.put("ADDA115RR2", new String[] { "Grupo_ADDA115RR2_Tit", "ADDA115RR2" });
        tokensDistribuicao.put("ADDA117", new String[] { "Grupo_ADDA117_Tit", "ADDA117" });
        tokensDistribuicao.put("ADDA118RR2", new String[] { "Grupo_ADDA118RR2_Tit", "ADDA118RR2" });
        tokensDistribuicao.put("ADDA120", new String[] { "Grupo_ADDA120_Tit", "ADDA120" });
        tokensDistribuicao.put("ADDA121RR2", new String[] { "Grupo_ADDA121RR2_Tit", "ADDA121RR2" });
        tokensDistribuicao.put("ADDA121RR3", new String[] { "Grupo_ADDA121RR3_Tit", "ADDA121RR3" });
        tokensDistribuicao.put("ADDA122RR2", new String[] { "Grupo_ADDA122RR2_Tit", "ADDA122RR2" });
        tokensDistribuicao.put("ADDA127", new String[] { "Grupo_ADDA127_Tit", "ADDA127" });
        tokensDistribuicao.put("ADDA200", new String[] { "Grupo_ADDA200_Dmstr", "ADDA200" });
        tokensDistribuicao.put("ADDA504", new String[] { "Grupo_ADDA504_Benfcrio", "ADDA504" });

        tokensRetorno.put("ADDA001RET", new String[] { "Grupo_ADDA001RET_PagdrActo", "Grupo_ADDA001RET_PagdrRecsd", "ADDA001RET" });
        tokensRetorno.put("ADDA005RET", new String[] { "Grupo_ADDA005RET_PagdrActo", "Grupo_ADDA005RET_PagdrRecsd", "ADDA005RET" });
        tokensRetorno.put("ADDA006RET", new String[] { "Grupo_ADDA006RET_PagdrActo", "Grupo_ADDA006RET_PagdrRecsd", "ADDA006RET" });
        tokensRetorno.put("ADDA101RET", new String[] { "Grupo_ADDA101RET_TitActo", "Grupo_ADDA101RET_TitRecsd", "ADDA101RET" });
        tokensRetorno.put("ADDA102RET", new String[] { "Grupo_ADDA102RET_TitActo", "Grupo_ADDA102RET_TitRecsd", "ADDA102RET" });
        tokensRetorno.put("ADDA108RET", new String[] { "Grupo_ADDA108RET_TitActo", "Grupo_ADDA108RET_TitRecsd", "ADDA108RET" });
        tokensRetorno.put("ADDA110RET", new String[] { "Grupo_ADDA110RET_TitActo", "Grupo_ADDA110RET_TitRecsd", "ADDA110RET" });
        tokensRetorno.put("ADDA114RET", new String[] { "Grupo_ADDA114RET_TitActo", "Grupo_ADDA114RET_TitRecsd", "ADDA114RET" });
        tokensRetorno.put("ADDA118RET", new String[] { "Grupo_ADDA118RET_TitActo", "Grupo_ADDA118RET_TitRecsd", "ADDA118RET" });
        tokensRetorno.put("ADDA122RET", new String[] { "Grupo_ADDA122RET_TitActo", "Grupo_ADDA122RET_TitRecsd", "ADDA122RET" });
    }

    private String codMensagem;
    private TipoArquivoRetornoEnum tipoArq;
    private String tagAceito;
    private String tagRecusado;
    private StringBuilder sb;
    private String dado;

    private String tagEntrada;
    private String tagSaida;

    private int qtdPArquivo;
    private int serialArquivo;
    private int contadorRegGeral;
    private int contadorRegTrans;

    private boolean gerarArqDerivados;
    private ArquivoProcessamentoVO arqproc;

    private OutputStreamWriter bufferSaida;
    private List<String> listaArquivosGerados;
    private String pathDestino;
    private boolean isADDA101RR2;
    private boolean isADDA118RR2;

    /**
     * @param prPathDestino
     * @param prArqproc
     * @param prqtdPorArq
     * @param listaArquivosGerados
     * @param prGerarArqDerivados
     */
    public ScraperRegistroToMapString(final String prPathDestino, final ArquivoProcessamentoVO prArqproc, int prqtdPorArq, final List<String> listaArquivosGerados, boolean prGerarArqDerivados) {
        super();

        this.sb = new StringBuilder();
        this.tipoArq = prArqproc.getTipoArquivo();
        this.codMensagem = prArqproc.getTipoDaMensagem();

        switch (tipoArq) {
            case RET :
                this.tagAceito = tokensRetorno.get(this.codMensagem)[0];
                this.tagRecusado = tokensRetorno.get(this.codMensagem)[1];
                this.codMensagem = tokensRetorno.get(this.codMensagem)[2];
                break;

            case DIS :
                this.tagAceito = tokensDistribuicao.get(this.codMensagem)[0];
                this.codMensagem = tokensDistribuicao.get(this.codMensagem)[1];

                this.tagRecusado = null;
                break;
        }

        this.qtdPArquivo = prqtdPorArq;
        this.serialArquivo = 0;
        this.contadorRegGeral = 0;
        this.contadorRegTrans = 1;
        this.gerarArqDerivados = prGerarArqDerivados;
        this.arqproc = prArqproc;
        this.listaArquivosGerados = listaArquivosGerados;
        this.pathDestino = prPathDestino;
        this.pathDestino = prPathDestino;
        this.isADDA101RR2 = Boolean.FALSE;
        this.isADDA118RR2 = Boolean.FALSE;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startDocument()
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        try {
            if (!this.gerarArqDerivados || this.tipoArq == TipoArquivoRetornoEnum.ERR || this.tipoArq == TipoArquivoRetornoEnum.PRO) {
                String nome = String.format("%1$s_%2$05d_%3$06d.xml", this.pathDestino + File.separator + this.arqproc.getNmArquivoRecebido(), this.serialArquivo, this.contadorRegGeral);

                this.bufferSaida = new OutputStreamWriter(new FileOutputStream(new File(nome)), "UTF-8");

                this.listaArquivosGerados.add(nome);
            }
        } catch (IOException e) {
            throw new SAXException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.tagEntrada = localName;

        if (localName.equals(TipoMensagemDDA.ADDA101RR2)) {
            this.isADDA101RR2 = Boolean.TRUE;
        } else if (localName.equals(TipoMensagemDDA.ADDA118RR2)) {
            this.isADDA118RR2 = Boolean.TRUE;
        }

        if (!this.tagEntrada.equals(this.tagSaida)) {
            this.dado = "";
        }
        if (localName.equals(this.tagAceito) || localName.equals(this.tagRecusado)) {
            this.contadorRegGeral++;
            try {
                if (this.contadorRegTrans == 1) {
                    if (this.gerarArqDerivados) {

                        this.serialArquivo++;
                        String nome = String.format("%1$s_%2$05d_%3$06d.xml", this.pathDestino + File.separator + this.arqproc.getNmArquivoRecebido(), this.serialArquivo, this.contadorRegGeral);

                        this.bufferSaida = new OutputStreamWriter(new FileOutputStream(new File(nome)), "UTF-8");
                        this.listaArquivosGerados.add(nome);
                    }

                    this.bufferSaida.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
                    this.bufferSaida.write("<ADDA>");
                }
            } catch (IOException e) {
                throw new SAXException(e.getMessage(), e);
            }
            this.sb = new StringBuilder();
        }
        if (sb != null) {
            sb.append("<" + localName);
        }
        for (int idx = 0; idx < attributes.getLength(); idx++) {
            sb.append(String.format(MASCARA_ATRIBUTO, attributes.getLocalName(idx), attributes.getValue(idx)));
        }
        if (sb != null) {
            sb.append(">");
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.tagSaida = localName;

        if (isADDA101RR2 && localName.equals(Constantes.TAGCODPARTDESTINATARIO) && dado.contains(Constantes.BANCOOB)) {
            throw new LeituraADDARR2756Exception(TipoMensagemDDA.ADDA102RR2);
        } else if (isADDA118RR2 && localName.equals(Constantes.TAGNUMCODBARRASBAIXAEFT) && dado.startsWith(Constantes.BANCOOB)) {
            throw new LeituraADDARR2756Exception(TipoMensagemDDA.ADDA118RR2);
        }

        if (localName.equals(this.tagAceito) || localName.equals(this.tagRecusado)) {

            sb.append(String.format("</%1s>", localName));
            try {

                // this.bff.write(String.format("%1$s", sb.toString()));
                this.bufferSaida.write(String.format("%1$s", sb.toString()));

                if ((this.contadorRegTrans % this.qtdPArquivo == 0) && this.gerarArqDerivados) {

                    /*
                     * this.bff.write("</ADDA>"); this.bff.flush(); this.bff.close(); this.bff = null;
                     */

                    this.bufferSaida.write("</ADDA>");
                    this.bufferSaida.flush();
                    this.bufferSaida.close();
                    this.bufferSaida = null;

                    this.contadorRegTrans = 1;

                } else {
                    // this.bff.write(String.format("%1$06d|%2$s", this.contadorRegGeral, sb.toString()));
                    this.contadorRegTrans++;
                }
            } catch (IOException e) {
                throw new SAXException(e.getMessage(), e);
            }
            sb.delete(0, sb.length() - 1);
            sb.setLength(0);
        }

        if (sb != null && dado.length() > 0) {
            dado = LeitorXmlUtil.tratarDados(dado);
            sb.append(dado + "</" + localName + ">");
            dado = "";
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endDocument()
     */
    @Override
    public void endDocument() throws SAXException {
        try {
            if (this.bufferSaida != null) {
                this.bufferSaida.write("</ADDA>");
                this.bufferSaida.flush();
                this.bufferSaida.close();
                this.bufferSaida = null;
            }

            this.listaArquivosGerados.add(String.valueOf(this.contadorRegGeral));
        } catch (IOException e) {
            throw new SAXException(e.getMessage(), e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        char[] dadoOriginal = ch.clone();
        dado += new String(dadoOriginal, start, length);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
     */
    @Override
    public void warning(SAXParseException e) throws SAXException {
        super.warning(e);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
     */
    @Override
    public void error(SAXParseException e) throws SAXException {
        super.error(e);
    }

}