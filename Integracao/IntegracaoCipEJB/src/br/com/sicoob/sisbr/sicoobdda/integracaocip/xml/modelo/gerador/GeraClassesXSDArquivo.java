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
 * Após a geração executar: 1) Format nas classes geradas; 2) Alterar atributos protected para private; 3) Implementar ConteudoMsgRecebimento para objetos de
 * recebimento; 4) Inserir em MsgSPBRecebimento ou ArqRecebimento; 5) Inserir a tag @XmlRootElement(name = "SISARQ"); 6) Criar construtor no SISARQComplexType
 * para XSD de envio.
 * 
 * @author Rafael.Silva
 */
public final class GeraClassesXSDArquivo {

    private static Logger lg = Logger.getLogger(GeraClassesXSDMensagem.class.getName());

    private GeraClassesXSDArquivo() {
    }

    public static final String DIRETORIO_MODELO_ARQ = "src/br/com/sicoob/sisbr/sicoobdda/integracaocip/xml/modelo/arquivos";
    public static final String PACOTE_MODELO_ARQ = "br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.";
    public static final String DIRETORIO_XSD_CIP = "src/br/com/sicoob/sisbr/sicoobdda/integracaocip/xml/modelo/arquivos/xsd/cip";
    public static final String DIRETORIO_XSD_SDDA = "src/br/com/sicoob/sisbr/sicoobdda/integracaocip/xml/modelo/arquivos/xsd/sdda";
    public static final String CLASSE_PACKAGE_INFO = "package-info.java";
    public static final String ADDATIPOS = "ADDATIPOS.xsd";

    public static void main(String[] args) {
        gerarArquivosXSD(ADDATIPOS);
        gerarClassesBinding(ADDATIPOS);
        gerarArquivosXSD();
        gerarClassesBinding();
    }

    private static void gerarClassesBinding() {
        try {
            File f = new File(DIRETORIO_XSD_SDDA);
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                if (!file.isDirectory()) {
                    if (!file.getName().equals(ADDATIPOS)) {
                        gerarClassesBiding(file.getName());
                    }
                }
            }
        } catch (IOException ex) {
            lg.logp(Level.SEVERE, GeraClassesXSDArquivo.class.getName(), "gerarClassesBinding()", "falha na geração de classes Bind MOTIVO [" + ex.getMessage() + "]", ex);
        }
    }

    private static void gerarClassesBinding(String arquivo) {
        try {
            File file = new File(DIRETORIO_XSD_SDDA + "/" + arquivo);
            gerarClassesBiding(file.getName());
        } catch (IOException ex) {
            lg.logp(Level.SEVERE, GeraClassesXSDArquivo.class.getName(), "gerarClassesBinding(String arquivo)", "falha na geração de classes Bind MOTIVO [" + ex.getMessage() + "]", ex);
        }
    }

    private static void gerarArquivosXSD() {
        File f = new File(DIRETORIO_XSD_CIP);
        File[] files = f.listFiles();
        gerarArquivosXSD(files);
    }

    private static void gerarArquivosXSD(String arquivo) {
        File[] files = new File[1];
        files[0] = new File(DIRETORIO_XSD_CIP + File.separator + arquivo);
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
            lg.logp(Level.SEVERE, GeraClassesXSDArquivo.class.getName(), "gerarArquivosXSD(File[] files)", "falha na geração dos arquivos XSD MOTIVO [" + ex.getMessage() + "]", ex);
        } catch (SAXException ex) {
            lg.logp(Level.SEVERE, GeraClassesXSDArquivo.class.getName(), "gerarArquivosXSD(File[] files)", "falha na geração dos arquivos XSD MOTIVO [" + ex.getMessage() + "]", ex);
        } catch (IOException ex) {
            lg.logp(Level.SEVERE, GeraClassesXSDArquivo.class.getName(), "gerarArquivosXSD(File[] files)", "falha na geração dos arquivos XSD MOTIVO [" + ex.getMessage() + "]", ex);
        }
    }

    private static void executarParseSax(SAXParser parser, File file) throws SAXException, IOException {
        String caminhoRelativo = file.getAbsolutePath();
        if (!file.getName().equals(ADDATIPOS)) {
            parser.parse(caminhoRelativo, new HandlerXSDArquivoCip(getUrlGeracaoXSD(file.getName()), getNomeArqSemExtensao(file.getName())));
        } else {
            parser.parse(caminhoRelativo, new HandlerXSDTipoArquivoCip(getUrlGeracaoXSD(file.getName())));
        }
    }

    private static String getUrlGeracaoXSD(String nomeArq) {
        return DIRETORIO_XSD_SDDA + File.separator + nomeArq;
    }

    private static String getNomeArqSemExtensao(String nomeArqXSD) {
        return nomeArqXSD.replace(".xsd", "").replace(".XSD", "");
    }

    private static void gerarClassesBiding(String nomeArqXSD) throws IOException {
        String urlOrigem = getUrlGeracaoXSD(nomeArqXSD);
        String pacoteDestino = PACOTE_MODELO_ARQ + getNomeArqSemExtensao(nomeArqXSD);

        String comando = "xjc " + urlOrigem + " -d src -p " + pacoteDestino;
        lg.logp(Level.INFO, GeraClassesXSDArquivo.class.getName(), "excluirArquivoPackageInfo(String nomeArqXSD)", "##### Comanco XJC --> " + comando);

        ExecutaComando.executarComando(comando);

        excluirArquivoPackageInfo(nomeArqXSD);
    }

    public static void excluirArquivoPackageInfo(String nomeArqXSD) {
        String urlArquivo = DIRETORIO_MODELO_ARQ + File.separator + getNomeArqSemExtensao(nomeArqXSD) + File.separator + CLASSE_PACKAGE_INFO;
        if (new File(urlArquivo).delete()) {
            lg.logp(Level.INFO, GeraClassesXSDArquivo.class.getName(), "excluirArquivoPackageInfo(String nomeArqXSD)", "arquivo package-info.java excluido --> " + urlArquivo);
        }
    }
}
