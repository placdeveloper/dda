/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  cob-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         OperacaoBeneficiarioDDADaoImpl.java
 * Data Criação:    Sep 1, 2015
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendenteBeneficiarioDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoBeneficiarioDDA;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacaoBeneficiarioDDADao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * OperacaoBeneficiarioDDADaoImpl
 * 
 * @author Daniel.Basso
 */
public class OperacaoBeneficiarioDDADaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements OperacaoBeneficiarioDDADao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional";

    /**
     * 
     */
    public OperacaoBeneficiarioDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, "", "");
    }

    /**
     * {@inheritDoc}
     * 
     * @throws BancoobException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.obterSituacaoBeneficiario#obterSituacaoBeneficiario(java.lang.String)
     */
    public SituacaoBeneficiarioDDA obterSituacaoBeneficiario(String numCpfCnpj) throws ComumException {
        return obterDados("OBTER_SITUCAO_BENEFICIARIO_DDA", getMapaParametro(numCpfCnpj, "numCpfCnpj"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacaoBeneficiarioDDADao#listarMensagemPendenteBeneficiario(java.lang.String)
     */
    public List<MensagemPendenteBeneficiarioDto> listarMensagemPendenteBeneficiario(String numCpfCnpjBeneficiario) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<MensagemPendenteBeneficiarioDto> listaViewMensagemDDaPendente = new ArrayList<>();
        getLogger().debug("*******LISTAR_MENSAGEM_DDA_ENVIO_PENDENTE*******");
        try {
            comando = getComando("LISTAR_MENSAGEM_DDA_ENVIO_PENDENTE");
            comando.adicionarVariavel("numCpfCnpjBeneficiario", numCpfCnpjBeneficiario);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaViewMensagemDDaPendente.add(new MensagemPendenteBeneficiarioDto(rs.getLong("IDMENSAGEMDDA"), rs.getString("CODTIPOMENSAGEMDDA"),
                        rs.getString("NUMCPFCNPJBENEFICIARIO"), rs.getString("CODTIPOERROCIP"), rs.getString("DESCTIPOERROCIP")));
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaViewMensagemDDaPendente;

    }

    /**
     * {@inheritDoc}
     * 
     * @throws ComumException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacaoBeneficiarioDDADao#obterBeneficiarioDDA(java.lang.String)
     */
    public Boolean beneficiarioEstaNaCip(String numCPFCNPJ) throws ComumException {
        return existeRegistro("BENEFICIARIO_ESTA_NA_CIP", getMapaParametro(numCPFCNPJ, "numCpfCnpj"));
    }
}
