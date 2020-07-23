package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.IOUtils;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogEnvioArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.BCARQComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.ADDA101ComplexType;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.xml.modelo.arquivos.ADDA101.SISARQComplexType;
import br.com.sicoob.tipos.DateTime;

/**
 * EscritorXMLArquivoUtil é responsável por
 * 
 * @author Felipe.Rosa
 */
public final class EscritorXMLArquivoUtil {

    /**
     * 
     */
    private EscritorXMLArquivoUtil() {
    }

    /**
     * Método responsável por
     * 
     * @return SicoobLoggerPadrao
     * 
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(EscritorXMLArquivoUtil.class);
    }

    /**
     * Método responsável por
     * 
     * @param object
     * @return
     * @throws ComumException String
     * 
     */
    public static String gerarXml(Object object) throws ComumException {
        return gerarByteArrayOutputStream(object).toString();
    }

    /**
     * Método responsável por realizar marshal do objeto recebido.
     * 
     * @param object
     * @return
     * @throws ComumException String
     * 
     */
    public static ByteArrayOutputStream gerarByteArrayOutputStream(Object object) throws ComumException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass().getPackage().getName());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, Constantes.ENCODING_UTF_8);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            marshaller.marshal(object, outputStream);

        } catch (JAXBException e) {
            throw new ComumException("Erro ao realizar marshal do objeto" + object.getClass(), e);
        }
        return outputStream;
    }

    /**
     * Método que realiza a gravação do arquivo no diretorio de destino.
     * 
     * @param dados
     * @param destinoArquivo
     * @throws IOException
     * @throws Exception
     */
    public static void salvarArquivo(byte[] dados, String destinoArquivo) throws ComumException, IOException {
        ByteArrayOutputStream baos = null;
        FileOutputStream fost = null;
        // TODO - VALIDAR THROW RUNTIME
        try {
            baos = new ByteArrayOutputStream();
            fost = new FileOutputStream(destinoArquivo);
            baos.write(dados);

            baos.writeTo(fost);

        } catch (IOException e) {
            throw new BancoobRuntimeException("Erro ao salvar o arquivo em: " + destinoArquivo, e);
        } finally {
            if (fost != null) {
                fost.close();
            }
        }
    }

    /**
     * Método que realiza a gravação do arquivo no diretorio de destino.
     * 
     * @param dados
     * @param destinoArquivo
     * @throws IOException
     * @throws Exception
     */
    public static void salvarArquivoAberto(Object sisARQComplexType, LogEnvioArquivoDDA logEnvioArquivoDDA, String destinoArquivo) throws ComumException, IOException {
        String strXmlEnvioEstarq = "<ESTARQ>ESTARQ</ESTARQ>";

        FileWriter fileWriter = new FileWriter(destinoArquivo);
        try {
            // TODO - VALIDAR THROW RUNTIME
            getLogger().debug("Iniciando a criacao do arquivo - Cabecalho");
            fileWriter.write(getCabecalhoArquivoADDADOC(logEnvioArquivoDDA.getTipoMensagem().getCodTipoMensagem()));
            getLogger().debug("Criando o BCARQ");
            fileWriter.write(EscritorXMLArquivoUtil.gerarXml(getBCARQComplexType(logEnvioArquivoDDA)));
            getLogger().debug("Criando o SisArq");
            if (sisARQComplexType instanceof List<?>) {
                populaArquivoBoleto(sisARQComplexType, fileWriter);
            } else {
                fileWriter.write(EscritorXMLArquivoUtil.gerarXml(sisARQComplexType));
            }
            getLogger().debug("Criando o Estarq");
            fileWriter.write(strXmlEnvioEstarq);
            fileWriter.write("</ADDADOC>");
            fileWriter.flush();

        } catch (IOException e) {
            throw new BancoobRuntimeException("Erro ao salvar o arquivo em: " + destinoArquivo, e);
        } finally {
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }

    /**
     * Método responsável por popular o arquivo do boleto
     * 
     * @param sisARQComplexType
     * @param fileWriter
     * @throws IOException
     * @throws ComumException void
     * 
     */
    private static void populaArquivoBoleto(Object sisARQComplexType, FileWriter fileWriter) throws IOException, ComumException {
        @SuppressWarnings("unchecked")
        List<ADDA101ComplexType> listaAdda101 = (List<ADDA101ComplexType>) sisARQComplexType;

        if (listaAdda101 != null && !listaAdda101.isEmpty()) {
            fileWriter.write("<SISARQ>");
            fileWriter.write("<ADDA101>");
            for (ADDA101ComplexType aDDA101 : listaAdda101) {
                fileWriter.write(EscritorXMLArquivoUtil.gerarXml(new SISARQComplexType(aDDA101)).replaceAll("<SISARQ>", "").replaceAll("</SISARQ>", "").replaceAll("<ADDA101>", "")
                        .replaceAll("</ADDA101>", ""));
            }
            fileWriter.write("</ADDA101>");
            fileWriter.write("</SISARQ>");
        }

    }

    /**
     * Método responsável por criar o cabecalho do arquivo ADDADOC
     * 
     * @param codTipoMensagem
     * @param str void
     * @return
     * 
     */
    private static String getCabecalhoArquivoADDADOC(String codTipoMensagem) {
        return Constantes.CABECALHO_ADDA_DOC.replace("CODTIPOMENSAGEM", codTipoMensagem);
    }

    /**
     * Método responsável por criar o BCARComplexType
     * 
     * @param nomeArquivo
     * @param idLogEnvioArquivoDDA
     * @param logEnvioArquivoDDA
     * @return
     * @throws ComumException BCARQComplexType
     * 
     */
    private static BCARQComplexType getBCARQComplexType(LogEnvioArquivoDDA logEnvioArquivoDDA) throws ComumException {
        BCARQComplexType bcArq = new BCARQComplexType();
        bcArq.setNomArq(logEnvioArquivoDDA.getDescNomeArquivoEnviado());
        bcArq.setNumCtrlEmis(logEnvioArquivoDDA.getId() != null ? logEnvioArquivoDDA.getId().toString() : null);
        bcArq.setISPBEmissor(Constantes.ISPB_BANCOOB);
        bcArq.setISPBDestinatario(Constantes.ISPB_CIP_SITRAF);
        bcArq.setDtHrDDA(DataCipUtil.dateTimeHoraParaXMLGregorianCalendar(new DateTime()));
        bcArq.setIndrFlagFim("N");// Indica o último arquivo do dia. (S ou N)
        bcArq.setDtMovto(DataCipUtil.dateTimeParaXMLGregorianCalendar(logEnvioArquivoDDA.getDataMovimento()));
        return bcArq;
    }

    /**
     * Método responsável por mover arquivos
     * 
     * @param urlOrigem
     * @param urlDestino
     * @throws ComumException void
     * 
     */
    public static void moverArquivo(String urlOrigem, String urlDestino) throws ComumException {
        FileInputStream origem = null;
        FileOutputStream destino = null;
        try {
            origem = new FileInputStream(urlOrigem);
            destino = new FileOutputStream(urlDestino);
            IOUtils.copy(origem, destino);

            if (!new File(urlOrigem).delete()) {
                getLogger().alerta(MensagemUtil.getString("integracaocip.erro.excluir.arquivo", urlOrigem));
            }
        } catch (IOException e) {
            getLogger().erro(e, MensagemUtil.getString("integracaocip.erro.mover.arquivo", urlOrigem));
            throw new ComumException(MensagemUtil.getString("integracaocip.erro.mover.arquivo", urlOrigem), e);
        } finally {
            ArquivoUtil.fecharFileInputStream(origem);
            ArquivoUtil.fecharFileOutputStream(destino);
        }
    }

}