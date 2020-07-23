package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;

/**
 * SSPBServico é responsável por
 * 
 * @author George.Santos
 */
public interface SSPBServico extends IntegracaoInternaServico {

    /**
     * Método responsável por
     * 
     * @param bytesXmlEnvio
     * @return
     * @throws IntegracaoInternaException byte[]
     * 
     */
    byte[] criptografar(byte[] bytesXmlEnvio) throws IntegracaoInternaException;

    /**
     * Método responsável por
     * 
     * @param bytesXml
     * @return
     * @throws IntegracaoInternaException byte[]
     * 
     */
    byte[] decriptar(byte[] bytesXml) throws IntegracaoInternaException;

}
