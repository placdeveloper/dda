/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         ReplicarBeneficiarioLegadoDaoImpl.java
 * Data Criação:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDABeneficiario;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAOperacao;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.EntidadesLegadoDatasource;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.negocio.excecao.IntegracaoCipException;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarBeneficiarioLegadoDao;

/**
 * ReplicarBeneficiarioLegadoDaoImpl
 * 
 * @author felipe.rosa
 */
public class ReplicarBeneficiarioLegadoDaoImpl extends IntegracaoCipCrudDao<DDABeneficiario> implements ReplicarBeneficiarioLegadoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_integracaocip.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-integracaocip";
    public static final String COMANDOS = "";

    private static final Integer INTEGRACAO_SICOOB_COOPERATIVA = null;
    private static final String ERRO_OBTER_OPERACAO_BENEFICIARIO = "integracaocip.erro.obter.operacao.beneficiario";

    /**
     * @param datasource
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public ReplicarBeneficiarioLegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, DDABeneficiario.class, COMANDOS);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.percistencia.dao.impl.sicoobdda.integracaocip.persistencia.dao.OperacaoBeneficiarioDDADao#obtemOperacoesInapto()
     */
    @SuppressWarnings("unchecked")
    public List<DDAOperacao> obtemOperacoesInapto() throws IntegracaoCipException {
        Comando comando = null;
        Connection conn = null;
        Statement stm = null;
        try {
            EntidadesLegadoDatasource.definirNumeroCooperativa(INTEGRACAO_SICOOB_COOPERATIVA);
            comando = getComando("OBTER_OPERACOES");
            comando.configurar();
            Query query = comando.criarQuery(getEntityManager());
            getLogger().info("Executando OBTER_OPERACOES " + comando.getSqlEmUso());
            return (List<DDAOperacao>) query.getResultList();
        } catch (PersistenceException e) {
            getLogger().erro(e, ERRO_OBTER_OPERACAO_BENEFICIARIO);
            throw new IntegracaoCipException(e);
        } finally {
            fecharComando(comando);
            fecharConexao(conn);
            fecharStatement(stm);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#obter(java.io.Serializable)
     */
    @Override
    public DDABeneficiario obter(Serializable chave) throws ComumException {
        EntidadesLegadoDatasource.definirNumeroCooperativa(INTEGRACAO_SICOOB_COOPERATIVA);
        try {
            return super.obter(chave);
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#incluir(br.com.bancoob.negocio.entidades.BancoobEntidade)
     */
    @Override
    public DDABeneficiario incluir(DDABeneficiario objeto) throws ComumException {
        EntidadesLegadoDatasource.definirNumeroCooperativa(INTEGRACAO_SICOOB_COOPERATIVA);
        try {
            return super.incluir(objeto);
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#alterar(br.com.bancoob.negocio.entidades.BancoobEntidade)
     */
    @Override
    public void alterar(DDABeneficiario objeto) throws ComumException {
        EntidadesLegadoDatasource.definirNumeroCooperativa(INTEGRACAO_SICOOB_COOPERATIVA);
        try {
            super.alterar(objeto);
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.bancoob.persistencia.dao.BancoobCrudDao#excluir(java.io.Serializable)
     */
    @Override
    public void excluir(Serializable chave) throws ComumException {
        EntidadesLegadoDatasource.definirNumeroCooperativa(INTEGRACAO_SICOOB_COOPERATIVA);
        try {
            super.excluir(chave);
        } catch (BancoobException e) {
            throw new ComumException(e);
        }
    }
}
