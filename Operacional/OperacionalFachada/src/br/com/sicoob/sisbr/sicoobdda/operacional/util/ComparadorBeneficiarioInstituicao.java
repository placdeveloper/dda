/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-operacional-fachada
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.util
 * Arquivo:         ComparadorBeneficiarioInstituicao.java
 * Data Criação:    Mar 21, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.util;

import java.util.Comparator;

import br.com.sicoob.sisbr.sicoobdda.comumfachada.dto.BeneficiarioAlertaDTO;

/**
 * ComparadorBeneficiarioInstituicao
 * 
 * @author Danilo.Barros
 */
public class ComparadorBeneficiarioInstituicao implements Comparator<BeneficiarioAlertaDTO> {

    /**
     * {@inheritDoc}
     * 
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    public int compare(BeneficiarioAlertaDTO o1, BeneficiarioAlertaDTO o2) {
        return o1.getIdBeneficiarioDDA().compareTo(o2.getIdBeneficiarioDDA());
    }

}
