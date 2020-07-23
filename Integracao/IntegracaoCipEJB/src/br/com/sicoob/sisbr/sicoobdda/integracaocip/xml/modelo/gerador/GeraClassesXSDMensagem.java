/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  teste-ejb
 * Pacote:          main.java.br.com.teste.ejb
 * Arquivo:         CriarClassesBindingXSD.java
 * Data Criação:    Oct 20, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.gerador;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * CriarClassesBindingXSD
 * 
 * Após a geração executar: 1) Format nas classes geradas; 2) Alterar atributos protected para private; 3)Implementar ConteudoMsgRecebimento para objetos de
 * recebimento; 4) Inserir em MsgSPBRecebimento ou ArqRecebimento.
 * 
 * @author Rafael.Silva
 */
public final class GeraClassesXSDMensagem {

    private static Logger lg = Logger.getLogger(GeraClassesXSDMensagem.class.getName());

    private GeraClassesXSDMensagem() {
    }

    public static final String DIRETORIO_MODELO_MSGS = "src/br/com/sicoob/sisbr/sicoobdda/integracaocip/xml/modelo/mensagens";
    public static final String PACOTE_MODELO_MSGS = "br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.mensagens.";
    public static final String DIRETORIO_XSD_CIP = "src/br/com/sicoob/sisbr/sicoobdda/integracaocip/xml/modelo/mensagens/xsd/cip";
    public static final String DIRETORIO_XSD_SDDA = "src/br/com/sicoob/sisbr/sicoobdda/integracaocip/xml/modelo/mensagens/xsd/sdda";
    public static final String CLASSE_PACKAGE_INFO = "package-info.java";

    public static void main(String[] args) throws IOException {
        gerarArquivosXSD("DDA0401.XSD");
        gerarClassesBinding("DDA0401.XSD");
        gerarArquivosXSD();
        gerarClassesBinding();
    }

    private static void gerarClassesBinding() throws IOException {
        File f = new File(DIRETORIO_XSD_SDDA);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (!file.isDirectory()) {
                gerarClassesBinding(file.getName());
            }
        }
    }

    private static void gerarArquivosXSD() {
        File f = new File(DIRETORIO_XSD_CIP);
        File[] files = f.listFiles();
        gerarArquivosXSD(files);
    }

    private static void gerarArquivosXSD(String nomeArq) {
        File[] files = new File[1];
        files[0] = new File(DIRETORIO_XSD_CIP + File.separator + nomeArq);
        gerarArquivosXSD(files);
    }

    private static void gerarArquivosXSD(File[] files) {
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (!file.isDirectory()) {
                    executarParseSax(parser, file);
                }
            }
        } catch (ParserConfigurationException ex) {
            lg.logp(Level.SEVERE, GeraClassesXSDMensagem.class.getName(), "gerarArquivosXSD(File[] files)", "falha na geração de arquivos XSD MOTIVO [" + ex.getMessage() + "]", ex);
        } catch (SAXException ex) {
            lg.logp(Level.SEVERE, GeraClassesXSDMensagem.class.getName(), "gerarArquivosXSD(File[] files)", "falha na geração de arquivos XSD MOTIVO [" + ex.getMessage() + "]", ex);
        } catch (IOException ex) {
            lg.logp(Level.SEVERE, GeraClassesXSDMensagem.class.getName(), "gerarArquivosXSD(File[] files)", "falha na geração de arquivos XSD MOTIVO [" + ex.getMessage() + "]", ex);

        }
    }

    private static void executarParseSax(SAXParser parser, File file) throws SAXException, IOException {
        String caminhoRelativo = file.getAbsolutePath();
        parser.parse(caminhoRelativo, new HandlerXSDMensagemCip(getUrlGeracaoXSD(file.getName()), getNomeArqSemExtensao(file.getName())));
    }

    private static String getUrlGeracaoXSD(String nomeArq) {
        return DIRETORIO_XSD_SDDA + File.separator + nomeArq;
    }

    private static String getNomeArqSemExtensao(String nomeArqXSD) {
        return nomeArqXSD.replace(".xsd", "").replace(".XSD", "");
    }

    private static void gerarClassesBinding(String nomeArqXSD) throws IOException {
        String urlOrigem = getUrlGeracaoXSD(nomeArqXSD);
        String pacoteDestino = PACOTE_MODELO_MSGS + getNomeArqSemExtensao(nomeArqXSD);

        String comando = "xjc " + urlOrigem + " -d src -p " + pacoteDestino;
        lg.logp(Level.INFO, GeraClassesXSDMensagem.class.getName(), "gerarClassesBinding(String nomeArqXSD)", "##### Comanco XJC --> " + comando);
        ExecutaComando.executarComando(comando);
        excluirArquivoPackageInfo(nomeArqXSD);
    }

    public static void excluirArquivoPackageInfo(String nomeArqXSD) {
        String urlArquivo = DIRETORIO_MODELO_MSGS + File.separator + getNomeArqSemExtensao(nomeArqXSD) + File.separator + CLASSE_PACKAGE_INFO;
        if (new File(urlArquivo).delete()) {

            lg.logp(Level.INFO, GeraClassesXSDMensagem.class.getName(), "excluirArquivoPackageInfo(String nomeArqXSD)", "arquivo package-info.java excluido --> " + urlArquivo);
        }
    }
}
