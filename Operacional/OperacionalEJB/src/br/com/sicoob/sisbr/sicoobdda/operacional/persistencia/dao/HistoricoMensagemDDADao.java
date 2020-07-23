package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;

/**
 * 
 * @author George.santos
 * 
 */
public interface HistoricoMensagemDDADao extends OperacionalCrudDaoIF<SicoobDDAEntidade> {

    /**
     * Método responsável por fazer todo o Processo de Arquivamento
     * 
     * @param qtdDiasLimiteExpurgo
     * @param qtdRegistroBlocoExpurgo
     * 
     * @throws ComumException void
     * 
     */
    void arquivarMensagensDDA(Long qtdRegistroBlocoExpurgo, Long qtdDiasLimiteExpurgo) throws ComumException;

}
