package br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.util;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;

/**
 * 
 * MontadorNomeclaturaArquivo
 * 
 */
public final class NomeArquivoUtil {

    /**
     * Monta o nome do arquivo de envio (ADDAxxx1_62313268_20100510_00039)
     * 
     * @param nomeServico
     * @return nome do arquivo formatado.
     */
    public static String getNomeArquivoEnvio(String nomeServico, Integer numSequencial, DateTimeDB dataMovimento) {
        return String.format("%1$s_%2$s_%3$s_%4$05d", nomeServico, Constantes.ISPB_BANCOOB, DataCipUtil.formatadorDataArquivo(dataMovimento), numSequencial);
    }
}