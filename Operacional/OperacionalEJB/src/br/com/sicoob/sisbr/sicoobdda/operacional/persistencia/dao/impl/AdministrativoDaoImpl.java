/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         AdministrativoDaoImpl.java
 * Data Criação:    Jul 17, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AdministrativoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * AdministrativoDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class AdministrativoDaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements AdministrativoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.administrativo.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-administrativo";

    /**
     */
    public AdministrativoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.AdministrativoDao#atualizarSituacaoBoletos()
     */
    public void atualizarSituacaoBoletos() throws ComumException {
        executarUpdate("ATUALIZAR_SITUACAO_BOLETO_ADM");
    }

}
