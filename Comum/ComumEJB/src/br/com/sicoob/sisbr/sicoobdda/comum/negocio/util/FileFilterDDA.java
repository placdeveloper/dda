/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.util
 * Arquivo:         FileFilterDDA.java
 * Data Criação:    Nov 8, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.util;

import java.io.File;
import java.io.FileFilter;

/**
 * FileFilterDDA é responsável por
 * 
 * @author Felipe.Rosa
 */
public class FileFilterDDA implements FileFilter {

    private String contains;

    /**
     * @param contains
     */
    public FileFilterDDA(String contains) {
        this.contains = contains;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.io.FileFilter#accept(java.io.File)
     */
    public boolean accept(File file) {
        return (file.getName().contains(contains));
    }

}
