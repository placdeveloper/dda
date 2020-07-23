/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         TipoErroCipDaoImpl.java
 * Data Criação:    Sep 22, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoErroCipDao;

/**
 * TipoErroCipDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class TipoErroCipDaoImpl extends OperacionalCrudDaoDB2<TipoErroCip> implements TipoErroCipDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.tipoerro.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-tipoerro";

    public static final String PESQUISAR = "PESQUISAR_TIPO_ERRO_CIP_PAGINADO";
    public static final String OBTER_NUMERO_REGISTROS = "OBTER_NUMERO_REGISTROS_TIPO_ERRO_CIP_PAGINADO";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public TipoErroCipDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, TipoErroCip.class, PESQUISAR, OBTER_NUMERO_REGISTROS);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoErroCipDao#obterTipoErro(java.lang.String)
     */
    public TipoErroCip obterTipoErro(String codTipoErro) throws OperacionalNoResultException {
        Comando comando = null;
        TipoErroCip tipoErroCip = null;
        try {
            comando = getComando("OBTER_TIPO_ERRO_CIP");
            comando.adicionarVariavel("codTipoErro", codTipoErro);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            Query query = comando.criarQuery(getEntityManager());
            tipoErroCip = (TipoErroCip) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OperacionalNoResultException("integracaocip.tipo.erro.cip.nao.encontrado", e);
        } finally {
            fecharComando(comando);
        }
        return tipoErroCip;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TipoErroCipDao#existeMensagemVinculoTipoErro(java.lang.String)
     */
    public Boolean existeMensagemVinculoTipoErro(String codTipoErro) {
        Comando comando = null;
        Long countMensagem = null;
        try {
            comando = getComando("OBTER_COUNT_MENSAGEM_TIPO_ERRO_CIP");
            comando.adicionarVariavel("codTipoErro", codTipoErro);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            Query query = comando.criarQuery(getEntityManager());
            countMensagem = (Long) query.getSingleResult();
        } finally {
            fecharComando(comando);
        }
        return countMensagem == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

}
