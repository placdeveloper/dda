/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.util
 * Arquivo:         ComparadorBeneficiarioInstituicao.java
 * Data Cria��o:    Mar 21, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.util;

import java.util.Comparator;

import br.com.sicoob.sisbr.sicoobdda.operacional.dto.BeneficiarioAlertaDto;

/**
 * ComparadorBeneficiarioInstituicao
 * 
 * @author Danilo.Barros
 */
public class ComparadorBeneficiarioAlerta implements Comparator<BeneficiarioAlertaDto> {

    /**
     * {@inheritDoc}
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(BeneficiarioAlertaDto o1, BeneficiarioAlertaDto o2) {
        return o1.getIdBeneficiarioDDA().compareTo(o2.getIdBeneficiarioDDA());
    }

}
