package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * MensagemDDADao � respons�vel por
 * 
 * @author Rodrigo.Neri
 */
public interface MensagemDDADao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * M�todo respons�vel por obter o canal DDA de acordo com o canal CTR
     * 
     * @param canalCTR
     * @return
     * @throws ComumException
     */
    Short obterCanalDDA(short canalCTR) throws ComumException;

}
