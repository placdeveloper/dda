package br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos;

import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.excecao.IntegracaoInternaException;

/**
 * SSPBServico � respons�vel por
 * 
 * @author George.Santos
 */
public interface SSPBServico extends IntegracaoInternaServico {

    /**
     * M�todo respons�vel por
     * 
     * @param bytesXmlEnvio
     * @return
     * @throws IntegracaoInternaException byte[]
     * 
     */
    byte[] criptografar(byte[] bytesXmlEnvio) throws IntegracaoInternaException;

    /**
     * M�todo respons�vel por
     * 
     * @param bytesXml
     * @return
     * @throws IntegracaoInternaException byte[]
     * 
     */
    byte[] decriptar(byte[] bytesXml) throws IntegracaoInternaException;

}
