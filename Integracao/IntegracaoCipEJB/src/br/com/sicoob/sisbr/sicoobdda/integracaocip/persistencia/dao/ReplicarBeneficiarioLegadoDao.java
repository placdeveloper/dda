/**
 * Projeto:         Cobran�a Banc�ria
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao
 * Arquivo:         ReplicarBeneficiarioLegadoDao.java
 * Data Cria��o:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao;

import java.util.List;

import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAOperacao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;

/**
 * ReplicarBeneficiarioLegadoDao � respons�vel por
 * 
 * @author felipe.rosa
 */
public interface ReplicarBeneficiarioLegadoDao extends IntegracaoCipCrudDaoIF<DDABeneficiario> {

    /**
     * M�todo respons�vel por
     * 
     * @return
     * @throws IntegracaoCipException List<DDAOperacao>
     * 
     */
    List<DDAOperacao> obtemOperacoesInapto() throws IntegracaoCipException;

}
