/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.util
 * Arquivo:         ArquivoUtil.java
 * Data Criação:    Nov 17, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import static br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes.BRANCO;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.IOUtils;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.util.MensagemUtil;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.enums.TipoSituacaoArquivo;

/**
 * ArquivoUtil
 * 
 * @author Rafael.Silva
 */
public final class ArquivoUtil {

    static int TAMANHO_BUFFER = 1024;

    /**
     * 
     */
    private ArquivoUtil() {
    }

    /**
     * Método responsável por
     * 
     * @return SicoobLoggerPadrao
     * 
     */
    private static SicoobLoggerPadrao getLogger() {
        return SicoobLoggerPadrao.getInstance(ArquivoUtil.class);
    }

    /**
     * Método responsável por fechar um FileInputStream
     * 
     * @param fis void
     * 
     */
    public static void fecharFileInputStream(FileInputStream fis) {
        try {
            if (fis != null) {
                fis.close();
            }
        } catch (IOException e) {
            getLogger().erro(e, "Erro ao fechar o stream");
        }
    }

    /**
     * Método responsável por fechar um InputStream
     * 
     * @param is void
     * 
     */
    public static void fecharInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            getLogger().erro(e, "Erro ao fechar o stream");
        }
    }

    /**
     * Método responsável por fechar um FileOutputStream
     * 
     * @param fos void
     * 
     */
    public static void fecharFileOutputStream(FileOutputStream fos) {
        try {
            if (fos != null) {
                fos.flush();
                fos.close();
            }
        } catch (IOException e) {
            getLogger().erro(e, "Erro ao fechar o stream");
        }
    }

    /**
     * Método responsável por fechar um FileWriter
     * 
     * @param fw void
     * 
     */
    public static void fecharFileWriter(FileWriter fw) {
        try {
            if (fw != null) {
                fw.flush();
                fw.close();
            }
        } catch (IOException e) {
            getLogger().erro(e, "Erro ao fechar o arquivo");
        }
    }

    /**
     * Método responsável por verificar se a ultima atualização de um determinado arquivo já tem mais de um determinado tempo
     * 
     * @param arquivoDeEntrada o arquivo a ser verificado
     * @return boolean retorna tru se o tempo da ultima atualização é maior que o tempo esperado ou False se o tempo da ultima atualização não form maior que o tempo esperado
     * 
     */
    public static TipoSituacaoArquivo verificaSituacaoDoArquivo(File arquivoDeEntrada, int prMinutosAposUltimaAtualizacao) {

        if (!arquivoDeEntrada.exists()) {
            return TipoSituacaoArquivo.ARQUIVO_INEXISTENTE;
        }

        if (!arquivoDeEntrada.canRead()) {
            return TipoSituacaoArquivo.ARQUIVO_ILEGIVEL;
        }

        if (!arquivoDeEntrada.canWrite()) {
            return TipoSituacaoArquivo.ARQUIVO_INALTERAVEL;
        }

        if (!arquivoDeEntrada.isFile()) {
            return TipoSituacaoArquivo.ARQUIVO_NAO_E_ARQUIVO;
        }

        GregorianCalendar dataAtual = new GregorianCalendar();
        GregorianCalendar dataUltimaAtualizacaoDoArquivo = new GregorianCalendar();

        dataAtual.add(Calendar.MINUTE, (prMinutosAposUltimaAtualizacao * -1));

        dataUltimaAtualizacaoDoArquivo.setTimeInMillis(arquivoDeEntrada.lastModified());

        if (!dataAtual.after(dataUltimaAtualizacaoDoArquivo)) {

            return TipoSituacaoArquivo.ARQUIVO_EM_TRANSFERENCIA;
        }

        return TipoSituacaoArquivo.ARQUIVO_OK;

    }

    /**
     * Método responsável por escrever arquivo apartir de um InputStream
     * 
     * @param is - Dados do arquivo.
     * @param pathArquivo - Local onde o arquivo será salvo.
     * @throws IOException void
     * 
     */
    public static void escreverArquivo(InputStream is, String pathArquivo) throws IOException {
        long ms = System.currentTimeMillis();
        FileOutputStream fos = new FileOutputStream(new File(pathArquivo));
        try {
            byte[] buffer = new byte[TAMANHO_BUFFER]; // Buffer size, Usually 1024-4096
            int len;
            while ((len = is.read(buffer, 0, buffer.length)) > 0) {
                fos.write(buffer, 0, len);
            }
        } catch (EOFException ex1) {
            getLogger().alerta("EOFException lancada indicando fim do arquivo. Erro apresenta pois GZIPInputStream nao aceita o caracter EOF -1");
        } finally {
            fecharInputStream(is);
            fecharFileOutputStream(fos);
        }
        getLogger().debug("###### Arquivo salvo em " + pathArquivo + "em " + (System.currentTimeMillis() - ms) + " ms.");
    }

    /**
     * Método responsável por recuperar bytes de um arquivo.
     * 
     * @param aFile
     * @return
     * @throws ComumException byte[]
     * 
     */
    public static byte[] getBytesFromFileSemLimite(File aFile) throws ComumException {
        byte[] bytes = null;
        InputStream is = null;

        try {
            is = new FileInputStream(aFile);
            long length = aFile.length();
            bytes = new byte[(int) length];

            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length) {
                numRead = is.read(bytes, offset, bytes.length - offset);
                if (numRead >= 0) {
                    offset += numRead;
                } else {
                    break;
                }
            }
            if (offset < bytes.length) {
                throw new IOException("Não foi possível ler o arquivo " + aFile.getName());
            }
        } catch (IOException e) {
            throw new ComumException(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new ComumException(e);
            }
        }
        return bytes;
    }

    /**
     * Método que realiza a gravação do arquivo no diretorio de destino.
     * 
     * @param dados
     * @param destinoArquivo
     * @throws IOException
     * @throws Exception
     */
    public static void gravarArquivo(byte[] dados, String destinoArquivo) throws ComumException, IOException {
        criaDiretorioInexistente(destinoArquivo.substring(0, destinoArquivo.lastIndexOf(File.separator)));
        ByteArrayOutputStream baos = null;
        FileOutputStream fost = null;
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
            excluirArquivo(urlOrigem);
        } catch (IOException e) {
            throw new ComumException(MensagemUtil.getString("Erro ao mover o arquivo " + urlOrigem), e);
        } finally {
            fecharFIS(origem);
            fecharFOS(destino);
        }
    }

    /**
     * Método responsável por
     * 
     * @param diretorioArquivo
     * @param nomeArquivo
     * @throws ComumException void
     * 
     */
    public static void excluirArquivo(String diretorioArquivo, String nomeArquivo) throws ComumException {
        excluirArquivo(concatenarCaminho(diretorioArquivo, nomeArquivo));
    }

    /**
     * Método responsável por
     * 
     * @param urlArquivo
     * @throws ComumException void
     * 
     */
    public static void excluirArquivo(String urlArquivo) throws ComumException {
        if (!new File(urlArquivo).delete()) {
            getLogger().alerta(MensagemUtil.getString("Erro ao excluir o arquivo " + urlArquivo));
            throw new ComumException(MensagemUtil.getString("Erro ao excluir o arquivo " + urlArquivo));
        }
    }

    /**
     * Método responsável por
     * 
     * @param urlArquivo
     * @throws ComumException void
     * 
     */
    public static void excluirArquivoSemExcecao(String urlArquivo) throws ComumException {
        if (!new File(urlArquivo).delete()) {
            getLogger().alerta(MensagemUtil.getString("Erro ao excluir o arquivo " + urlArquivo));
        }
    }

    /**
     * Método responsável por
     * 
     * @param caminho
     * @return File[]
     * 
     */
    public static File[] listarArquivos(String caminho) {
        return new File(caminho).listFiles();
    }

    /**
     * Método responsável por
     * 
     * @param caminho
     * @param contains
     * @return File[]
     * 
     */
    public static File[] listarArquivos(String caminho, String contains) {
        return new File(caminho).listFiles(new FileFilterDDA(contains));
    }

    /**
     * Método responsável por
     * 
     * @param diretorioArquivo
     * @param nomeArquivo
     * @throws ComumException void
     * 
     */
    public static void criarDiretorio(String diretorioArquivo, String nomeArquivo) throws ComumException {
        criarDiretorio(concatenarCaminho(diretorioArquivo, nomeArquivo));
    }

    /**
     * Método responsável por
     * 
     * @param urlDiretorio
     * @throws ComumException void
     * 
     */
    public static void criarDiretorio(String urlDiretorio) throws ComumException {
        if (!new File(urlDiretorio).mkdirs()) {
            throw new ComumException(MensagemUtil.getString("Erro ao criar diretório " + urlDiretorio));
        }
    }

    /**
     * Método responsável por
     * 
     * @param arquivo
     * @return
     * @throws IOException Boolean
     * 
     */
    public static Boolean exists(String arquivo) throws IOException {
        File file = new File(arquivo);
        return file.exists();
    }

    /**
     * Método responsável por
     * 
     * @param diretorioArquivo
     * @param nomeArquivo
     * @return
     * @throws ComumException InputStream
     * 
     */
    public static InputStream obterStreamArquivo(String diretorioArquivo, String nomeArquivo) throws ComumException {
        return obterStreamArquivo(concatenarCaminho(diretorioArquivo, nomeArquivo));
    }

    /**
     * Método responsável por
     * 
     * @param urlArquivo
     * @return
     * @throws ComumException InputStream
     * 
     */
    public static InputStream obterStreamArquivo(String urlArquivo) throws ComumException {
        try {
            return new BufferedInputStream(new FileInputStream(urlArquivo));
        } catch (FileNotFoundException e) {
            throw new ComumException(MensagemUtil.getString("Erro ao obter stream do arquivo " + urlArquivo), e);
        }
    }

    /**
     * Método responsável por
     * 
     * @param diretorioArquivo
     * @param nomeArquivo
     * @param dadosArquivos
     * @throws ComumException void
     * 
     */
    public static void gravarArquivo(String diretorioArquivo, String nomeArquivo, InputStream dadosArquivos) throws ComumException {
        gravarArquivo(concatenarCaminho(diretorioArquivo, nomeArquivo), dadosArquivos);
    }

    /**
     * Método responsável por
     * 
     * @param urlArquivo
     * @param dadosArquivos
     * @throws ComumException void
     * 
     */
    public static void gravarArquivo(String urlArquivo, InputStream dadosArquivos) throws ComumException {
        criaDiretorioInexistente(urlArquivo.substring(0, urlArquivo.lastIndexOf(File.separator)));
        try {
            FileOutputStream destino = new FileOutputStream(urlArquivo);

            IOUtils.copy(dadosArquivos, destino);
            dadosArquivos.close();
            destino.close();
        } catch (IOException excecao) {
            throw new ComumException(excecao);
        }
    }

    /**
     * Método responsável por
     * 
     * @param diretorio
     * @throws ComumException void
     * 
     */
    public static void criaDiretorioInexistente(String diretorio) throws ComumException {
        File tempFile = new File(diretorio);
        if (!tempFile.exists()) {
            if (!tempFile.mkdirs()) {
                throw new ComumException("Não foi possível criar o diretório " + diretorio);
            }
        }
    }

    /**
     * Método responsável por
     * 
     * @param caminhoArquivo
     * @param nomeArquivo
     * @return
     * @throws ComumException FileWriter
     * 
     */
    public static FileWriter criarArquivo(String caminhoArquivo, String nomeArquivo) throws ComumException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(caminhoArquivo, nomeArquivo));
        } catch (IOException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException("Não foi possível criar o arquivo " + nomeArquivo, e);
        }
        return fileWriter;
    }

    /**
     * Método responsável por
     * 
     * @param objects
     * @throws ComumException void
     * 
     */
    public static void fecharRecursos(Object... objects) throws ComumException {
        Object object = null;
        try {
            if (!ObjectUtil.isNull(objects)) {
                for (int i = 0; i < objects.length; i++) {
                    object = objects[i];
                    if (!ObjectUtil.isNull(object) && object instanceof Closeable) {
                        ((Closeable) object).close();
                    }
                }
            }
        } catch (IOException e) {
            throw new ComumException("Erro ao fechar o recurso " + (!ObjectUtil.isNull(object) ? object.getClass().getSimpleName() : BRANCO), e);
        }
    }

    /**
     * Método responsável por
     * 
     * @param fis void
     * 
     */
    private static void fecharFIS(FileInputStream fis) {
        try {
            if (fis != null) {
                fis.close();
            }
        } catch (IOException e) {
            getLogger().erro(e, "Erro ao fechar o stream");
        }
    }

    /**
     * Método responsável por
     * 
     * @param fos void
     * 
     */
    private static void fecharFOS(FileOutputStream fos) {
        try {
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            getLogger().erro(e, "Erro ao fechar o stream");
        }
    }

    /**
     * Método responsável por
     * 
     * @param diretorioArquivo
     * @param nomeArquivo
     * @return String
     * 
     */
    public static String concatenarCaminho(String diretorioArquivo, String nomeArquivo) {
        if (!nomeArquivo.startsWith(File.separator)) {
            return diretorioArquivo + File.separator + nomeArquivo;
        } else {
            return diretorioArquivo + nomeArquivo;
        }
    }
}
