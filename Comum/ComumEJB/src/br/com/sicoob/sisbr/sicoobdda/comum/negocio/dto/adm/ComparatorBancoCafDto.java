/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  sdda-comum-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm
 * Arquivo:         ComparatorBancoCafDto.java
 * Data Cria��o:    Feb 21, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.adm;

import java.util.Comparator;

/**
 * ComparatorBancoCafDto � respons�vel por
 * 
 * @author Danilo.Barros
 */
public class ComparatorBancoCafDto implements Comparator<BancoCafDto> {

    /**
     * {@inheritDoc}
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(BancoCafDto o1, BancoCafDto o2) {
        return o1.getDescBanco().trim().compareToIgnoreCase(o2.getDescBanco().trim());
    }

}
