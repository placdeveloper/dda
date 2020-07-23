package br.com.sicoob.sisbr.sicoobdda.integracaocip.infraestrutura.saxhandle;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.CAMPO_NOME_ARQUIVO;
import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.TAGRECEBIMENTOARQ;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogDetRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.enums.TipoArquivoRetornoEnum;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util.LeitorXmlUtil;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.vo.ArquivoProcessamentoVO;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.gerador.HandlerXSD;

public class ScraperRegistros extends DefaultHandler {

    // private static final String TIPO_MSG_ADDA121 = "ADDA121";
    // private static final String TAG_DE_DISTINCAO = "Grupo_ADDA121RR2_Tit";

    private static HashMap<String, String[]> tokensRetorno = new HashMap<String, String[]>();
    private static HashMap<String, String[]> tokensDistribuicao = new HashMap<String, String[]>();
    private Logger lg;

    static final String MASCARA_TAG = "<CodMsg>%1$s</CodMsg>";
    static final String MASCARA_ATRIBUTO = " %1$s=\"%2$s\"";

    /*
     * ADDA101RET, ADDA101ERR, ADDA101PRO ADDA102RET, ADDA102ERR, ADDA102PRO ADDA114RET, ADDA114ERR, ADDA114PRO
     * 
     * ADDA002 OK ADDA003 OK ADDA101RR2 OK ADDA102RR2 ADDA104RR2 ADDA106 ADDA108RR2 ADDA114RR2 ADDA115RR2 ADDA117 ADDA118RR2 ADDA120 ADDA121RR2 ADDA121RR3
     * ADDA122RR2 ADDA127 ADDA200 ADDA504
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
    private List<LogDetRecebimentoArquivoDDA> registros;
    private LogDetRecebimentoArquivoDDA registro;
    private StringBuilder sb;
    private String dado;
    private String tagEntrada;
    private String tagSaida;
    private int ordem;
    private String nomeArquivoCip;

    public ScraperRegistros(final ArquivoProcessamentoVO prArqproc, final List<LogDetRecebimentoArquivoDDA> prRegistros, int prOrdemInicial) {
        super();

        this.registros = prRegistros;
        this.sb = new StringBuilder();
        this.tipoArq = prArqproc.getTipoArquivo();
        this.codMensagem = prArqproc.getTipoDaMensagem();
        this.nomeArquivoCip = prArqproc.getNmArquivoRecebido();

        switch (tipoArq) {
        case RET:
            this.tagAceito = tokensRetorno.get(this.codMensagem)[0];
            this.tagRecusado = tokensRetorno.get(this.codMensagem)[1];
            this.codMensagem = tokensRetorno.get(this.codMensagem)[2];
            break;

        case DIS:
            this.tagAceito = tokensDistribuicao.get(this.codMensagem)[0];
            this.codMensagem = tokensDistribuicao.get(this.codMensagem)[1];

            this.tagRecusado = null;
            break;
        }

        this.ordem = prOrdemInicial;
        lg = Logger.getLogger(ScraperRegistros.class.getName());

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        String tag = null;

        this.tagEntrada = localName;

        if (!this.tagEntrada.equals(this.tagSaida)) {
            this.dado = "";
        }

        if (localName.equals(this.tagAceito) || localName.equals(this.tagRecusado)) {

            this.registro = new LogDetRecebimentoArquivoDDA();
            this.sb = new StringBuilder();

            this.sb.append(String.format("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <%1$s> <%2$s>%3$s</%2$s>", TAGRECEBIMENTOARQ, CAMPO_NOME_ARQUIVO, this.nomeArquivoCip));

            if (this.tipoArq == TipoArquivoRetornoEnum.RET) {

                tag = localName.equals(this.tagAceito) ? String.format(MASCARA_TAG, this.codMensagem)
                        : String.format(MASCARA_TAG, this.codMensagem.substring(0, this.codMensagem.length() - 3) + "ERR");

            } else if (this.tipoArq == TipoArquivoRetornoEnum.DIS) {

                tag = String.format(MASCARA_TAG, this.codMensagem);

            }

            sb.append(tag);

        }

        if (this.registro != null && sb != null) {
            sb.append("<" + localName);
        }

        for (int idx = 0; idx < attributes.getLength(); idx++) {

            sb.append(String.format(MASCARA_ATRIBUTO, attributes.getLocalName(idx), attributes.getValue(idx)));
        }

        if (this.registro != null && sb != null) {
            sb.append(">");
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        this.tagSaida = localName;

        if (localName.equals(this.tagAceito) || localName.equals(this.tagRecusado)) {

            sb.append(String.format("</%1s></%2s>", localName, TAGRECEBIMENTOARQ));

            this.registro.setDescXMLMensagemRecebida(sb.toString());
            this.registro.setBolProcessado(false);
            this.registro.setNumOrdemProcessamento(this.ordem);
            this.registros.add(this.registro);
            this.registro = null;
            this.ordem++;
            sb.setLength(0);
        }

        if (this.registro != null && sb != null && dado.length() > 0) {

            dado = LeitorXmlUtil.tratarDados(dado);

            sb.append(dado + "</" + localName + ">");
            dado = "";
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        char[] dadoOriginal = ch.clone();

        dado += new String(dadoOriginal, start, length);

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#warning(org.xml.sax.SAXParseException)
     */
    @Override
    public void warning(SAXParseException e) throws SAXException {

        super.warning(e);

        lg.logp(Level.INFO, HandlerXSD.class.getName(), "warning(SAXParseException e)", MensagemUtil.getString("integracaocip.alerta.processamento.scraperxml", e.getMessage()));

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#error(org.xml.sax.SAXParseException)
     */
    @Override
    public void error(SAXParseException e) throws SAXException {

        super.error(e);

        throw new SAXException(MensagemUtil.getString("integracaocip.erro.processamento.scraperxml", this.tagEntrada, e.getMessage()));

    }

}
