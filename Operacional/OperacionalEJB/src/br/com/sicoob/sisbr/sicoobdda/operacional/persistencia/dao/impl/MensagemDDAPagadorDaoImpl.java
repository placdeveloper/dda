/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         MensagemDDAPagadorDaoImpl.java
 * Data Criação:    Dec 28, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MensagemDDAPagadorDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * MensagemDDAPagadorDaoImpl é responsável por 
 * 
 * @author Felipe.Rosa
 */
public class MensagemDDAPagadorDaoImpl extends OperacionalCrudDaoDB2<MensagemDDAPagador> implements MensagemDDAPagadorDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.pagador.eletronico.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-pagador-eletronico";

    /**
     * 
     */
    public MensagemDDAPagadorDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, MensagemDDAPagador.class, null, null);
    }

}
