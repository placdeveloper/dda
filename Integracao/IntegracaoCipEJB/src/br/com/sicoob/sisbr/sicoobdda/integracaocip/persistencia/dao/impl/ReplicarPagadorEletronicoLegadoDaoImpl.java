/**
 * Projeto:         SDDA
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         ReplicarBeneficiarioLegadoDaoImpl.java
 * Data Criação:    Aug 25, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAPagadorEletronico;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDao;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao;

/**
 * ReplicarPagadorEletronicoLegadoDaoImpl
 * 
 * @author george.santos
 */
public class ReplicarPagadorEletronicoLegadoDaoImpl extends IntegracaoCipCrudDao<DDAPagadorEletronico> implements ReplicarPagadorEletronicoLegadoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_integracaocip.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-integracaocip";
    public static final String COMANDOS = "";
    // private static final Integer INTEGRACAO_SICOOB_COOPERATIVA = 0;

    /**
     * @param datasource
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     */
    public ReplicarPagadorEletronicoLegadoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, DDAPagadorEletronico.class, COMANDOS);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao#obterDDAPagadorEletronico(java.lang.String)
     */
    public DDAPagadorEletronico obterDDAPagadorEletronico(String cPFCNPJ) throws ComumException {
        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Obtendo o Pagador Eletrônico...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DDAPagadorEletronico ddaPagadorEletronico = null;

        try {
            getLogger().debug(Constantes.STR_SEPARACAO_TRACO + "Estabelecendo conexão...");
            connection = estabelecerConexao();

            final String SQL = "SELECT NUMCPFCNPJ, BOLSACADOELETRONICO, NUMCOOPERATIVA FROM DBO.DDAPAGADORELETRONICO WHERE NUMCPFCNPJ = CAST(? AS VARCHAR(14))";
            getLogger().debug(Constantes.STR_SEPARACAO_TRACO + "SQL: " + SQL);

            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, cPFCNPJ);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ddaPagadorEletronico = new DDAPagadorEletronico(resultSet.getString(1), resultSet.getBoolean(2), resultSet.getInt(3));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } finally {
            fecharStatement(preparedStatement);
            fecharResultSet(resultSet);
            fecharConexao(connection);
        }

        getLogger().debug(String.format(Constantes.STR_SEPARACAO_2 + "Pagador Eletrônico %s", ddaPagadorEletronico != null ? "obtido." : "não encontrado."));

        return ddaPagadorEletronico;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao#alterarDDAPagadorEletronico(br.com.sicoob.sisbr.sicoobdda.entidadeslegado.DDAPagadorEletronico)
     */
    public void alterarDDAPagadorEletronico(DDAPagadorEletronico ddaPagadorEletronico) throws ComumException {
        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Alterando Pagador Eletrônico...");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowCount = 0;

        try {
            if (!ObjectUtil.isNull(ddaPagadorEletronico)) {
                getLogger().debug(Constantes.STR_SEPARACAO_TRACO + "Estabelecendo conexão...");
                connection = estabelecerConexao();

                final String SQL = "UPDATE DBO.DDAPAGADORELETRONICO SET BOLSACADOELETRONICO = ?, NUMCOOPERATIVA = ? WHERE NUMCPFCNPJ = CAST(? AS VARCHAR(14))";
                getLogger().debug(Constantes.STR_SEPARACAO_TRACO + "SQL: " + SQL);

                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setBoolean(1, ddaPagadorEletronico.getBolSacadoEletronico());

                if (ObjectUtil.isNull(ddaPagadorEletronico.getNumCooperativa())) {
                    preparedStatement.setNull(2, Types.INTEGER);
                } else {
                    preparedStatement.setInt(2, ddaPagadorEletronico.getNumCooperativa());
                }

                preparedStatement.setString(3, ddaPagadorEletronico.getNumCPFCNPJ());
                rowCount = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } finally {
            fecharStatement(preparedStatement);
            fecharConexao(connection);
        }

        getLogger().debug(String.format(Constantes.STR_SEPARACAO_2 + "Pagador Eletrônico alterado {%s}", rowCount));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao#replicarPagadorEletronico(java.util.List)
     */
    public void replicarPagadorEletronico(List<Object[]> parametros) throws ComumException {
        try {
            executarUpdateEmLote("REPLICAR_PAGADOR_ELETRONICO", parametros);
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.ReplicarPagadorEletronicoLegadoDao#incluirDDAPagadorEletronico(java.lang.String,
     *      java.lang.Boolean, java.lang.Integer)
     */
    public void incluirDDAPagadorEletronico(String numCpfCnpj, Boolean bolSacadoEletronico, Integer numCooperativa) throws ComumException {
        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Inicio do Metodo=incluirDDAPagadorEletronico - utilizado para incluir na DBO.BDSICOOBINTEGRACAO");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            getLogger().debug(Constantes.STR_SEPARACAO_TRACO + "Estabelecendo conexão...");
            connection = estabelecerConexao();

            final String SQL = "INSERT INTO DBO.DDAPAGADORELETRONICO ( NUMCPFCNPJ, NUMCOOPERATIVA, BOLSACADOELETRONICO) VALUES (CAST(? AS VARCHAR(14)), ?, ?)";
            getLogger().debug(Constantes.STR_SEPARACAO_TRACO + "SQL: " + SQL);

            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, numCpfCnpj);

            if (ObjectUtil.isNull(numCooperativa)) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, numCooperativa);
            }
            preparedStatement.setBoolean(3, bolSacadoEletronico);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } finally {
            fecharStatement(preparedStatement);
            fecharConexao(connection);
        }

        getLogger().debug(String.format(Constantes.STR_SEPARACAO_2 + "Inclusão do Pagador Eletrônico feita com sucesso! ", numCpfCnpj));
    }

}
