/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.util
 * Arquivo:         ZipUtil.java
 * Data Criação:    May 26, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ArquivoUtil;

/**
 * ZipUtil é responsável por compactar de descompactar os objetos recebidos e enviados a cip.
 * 
 * @author Rafael.Silva
 */
public class ZipUtil {

    static int TAMANHO_BUFFER = 1024;

    /**
     * Método responsável por
     * 
     * @return SicoobLoggerPadrao
     * 
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(ZipUtil.class);
    }

    /**
     * Compacta objetos
     * 
     * @param aObjs
     * @return
     * @throws IOException
     */
    public static byte[] compactarArquivoNovo(byte[] dados, String charset) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(dados);

        ByteArrayOutputStream byteArrayOutPutStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutPutStream = new GZIPOutputStream(byteArrayOutPutStream);

        byte[] buffer = new byte[TAMANHO_BUFFER];
        int len;
        while ((len = inputStream.read(buffer, 0, buffer.length)) > 0) {
            gzipOutPutStream.write(new String(buffer).getBytes(charset), 0, len);
        }
        gzipOutPutStream.close();
        inputStream.close();

        return byteArrayOutPutStream.toByteArray();
    }

    /**
     * Método responsável por
     * 
     * @param dados
     * @param charset
     * @return
     * @throws IOException byte[]
     * 
     */
    public static byte[] compactarArquivo(byte[] dados, String charset) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream zipOut = new GZIPOutputStream(baos);
        zipOut.write(new String(dados).getBytes(charset));
        zipOut.close();
        return baos.toByteArray();
    }

    /**
     * Descompacta Objetos
     * 
     * @param aByte
     * @param aObjs
     * @return
     * @throws ComumException
     */
    public static Object extrairObj(byte[] aByte) throws ComumException {
        Object objDescompactado = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(aByte);
        GZIPInputStream gzipIn;
        try {
            gzipIn = new GZIPInputStream(bais);
            ObjectInputStream objectIn = new ObjectInputStream(gzipIn);
            objDescompactado = objectIn.readObject();
            objectIn.close();
        } catch (IOException e) {
            throw new ComumException(e);
        } catch (ClassNotFoundException e) {
            throw new ComumException(e);
        }
        return objDescompactado;
    }

    /**
     * Método responsável por
     * 
     * @param dados
     * @return
     * @throws IOException String
     * 
     */
    public static String extrairArquivo(byte[] dados) throws IOException {
        GZIPInputStream zipInput = new GZIPInputStream(new ByteArrayInputStream(dados));
        DataInputStream input = new DataInputStream(zipInput);

        StringBuilder builder = new StringBuilder();
        while (input.available() != 0) {
            try {
                builder.append(input.readChar());
            } catch (EOFException ex1) {
                getLogger().alerta("EOFException lancada indicando fim do arquivo. Erro apresenta pois GZIPInputStream nao aceita o caracter EOF -1");
                break;
            }
        }
        input.close();

        return builder.toString();
    }

    /**
     * Método responsável por descompacatar o array de bytes e gravar o arquivo no diretorio informado.
     * 
     * @param bytesCompactados - Array de bytes compactados.
     * @param pathArquivo - Diretório onde será salvo o arquivo descompactado.
     * @throws IOException void
     * 
     */
    public static void descompactarArquivoCIP(byte[] bytesCompactados, String pathArquivo) throws IOException {
        GZIPInputStream zipIs = new GZIPInputStream(new ByteArrayInputStream(bytesCompactados));
        ArquivoUtil.escreverArquivo(zipIs, pathArquivo);
    }

    /**
     * Método responsável por descompactar um arquivo no , o método utiliza a implementação interna do java para compactação/descompactação java.util.zip
     * 
     * @param baytesCompactados um array de bytes contendo os dados compactados
     * @param arquivoDestinoDescompactado um FileInputStream que aponta para um arquivo onde serão salvos os dados descompactados
     * @return boolean caso a descompactação ocorra sem problemas será retornado TRUE caso não seja possível descompactar será retornado FALSE
     * 
     * @see java.util.zip
     */
    public static boolean descompactarArquivoCIP(byte[] baytesCompactados, FileOutputStream arquivoDestinoDescompactado) {
        ByteArrayInputStream dadosDeEntrada = new ByteArrayInputStream(baytesCompactados);
        GZIPInputStream gzipDescompacta = null;
        DataOutputStream ou = null;
        DataInputStream din = null;
        char dado;
        try {
            gzipDescompacta = new GZIPInputStream(dadosDeEntrada);
            din = new DataInputStream(gzipDescompacta);
            ou = new DataOutputStream(arquivoDestinoDescompactado);

            while (din.available() > 0) {
                dado = din.readChar();
                ou.write(String.valueOf(dado).getBytes());
            }

            gzipDescompacta.close();
            return Boolean.TRUE;
        } catch (EOFException eo) {
            return fecharArquivo(ou, din);
        } catch (IOException e) {
            getLogger().debug("Falha na descompactação do Arquivo MOTIVO[" + e.getMessage() + "]");
            return Boolean.FALSE;
        } finally {
            fecharArquivo(ou, din);
        }
    }

    /**
     * Método responsável por
     * 
     * @param ou
     * @param din
     * @return boolean
     * 
     */
    private static boolean fecharArquivo(DataOutputStream ou, DataInputStream din) {
        try {
            if (ou != null) {
                ou.flush();
                ou.close();
            }
            if (din != null) {
                din.close();
            }
            return Boolean.TRUE;
        } catch (IOException e) {
            getLogger().debug("Falha na descompactação do Arquivo MOTIVO[" + e.getMessage() + "]");
            return Boolean.FALSE;
        }
    }

}
