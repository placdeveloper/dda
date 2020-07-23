/**
 * Projeto:         SicoobDDA
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         IntegracaoCipDaoImpl.java
 * Data Criação:    May 25, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDAPagadorConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao;

/**
 * IntegracaoCipDaoImpl
 * 
 * @author Rafael.Silva
 */
public class PagadorCipDaoImpl extends IntegracaoCipCrudDaoDB2<SicoobDDAEntidade> implements PagadorCipDao {

	/**
     * 
     */
	private static final String LOG_GRAVAR_PAGADOR_ADDA002 = "Gravar Pagador ADDA002";
	private static final String LOG_MANUTENCAO_PAGADOR_ADDA003 = "Manutencao Pagador ADDA003";
	public static final String ARQUIVO_QUERIES = "sicoobdda_pagadorcip.queries.xml";
	public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-pagadorcip";

	private static final String ID_PAGADOR_DDA = "idPagadorDDA";

	/**
	 * IntegracaoCipDaoImpl
	 */
	public PagadorCipDaoImpl() {
		super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, "", "");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipDao#obterMensagemDDAPagador(java.lang.Long)
	 */
	public MensagemDDAPagador obterMensagemDDAPagador(Long idMensagem) {
		getLogger().debug("*******INICIO obterMensagemDDAPagador()*******");
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		MensagemDDAPagador mensagemDDAPagador = null;

		try {
			comando = getComando("OBTER_MENSAGEMDDA_PAGADOR");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			if (rs.next()) {
				mensagemDDAPagador = getMensagemDDAPagador(rs);
			}

			comando = getComando("LISTAR_MENSAGEMDDA_PAGADOR_AGREGADO");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				mensagemDDAPagador.getListaMensagemDDAPagadorAgregado().add(setMensagemDDAPagadorAgregado(rs));
			}

			comando = getComando("LISTAR_MENSAGEMDDA_PAGADOR_CONTA");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				mensagemDDAPagador.getListaMensagemDDAPagadorConta().add(setMensagemDDAPagadorConta(rs));
			}

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}

		return mensagemDDAPagador;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#obterPagadorDDA(java.lang.Long)
	 */
	public PagadorDDA obterPagadorDDA(Long idMensagem) throws ComumException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		PagadorDDA pagadorDDA = new PagadorDDA();
		try {
			comando = getComando("OBTER_PAGADOR_POR_MENSAGEM_DDA");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			if (rs.next()) {
				pagadorDDA = setPagadorDDA(rs);
				pagadorDDA.setListaPagadorDDAAgregado(obterPagadorDDAAgregado(pagadorDDA.getId()));
				pagadorDDA.setListaPagadorDDAConta(obterPagadorDDAConta(pagadorDDA.getId()));
			}
		} catch (SQLException e) {
			throw new ComumException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}
		return pagadorDDA;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#obterPagadorDDA(java.lang.String)
	 */
	public PagadorDDA obterPagadorDDA(String numCpfCnpjPagador, Boolean isLocarTabela) throws ComumException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		PagadorDDA pagadorDDA = new PagadorDDA();
		try {
			comando = getComando("OBTER_PAGADOR_DDA");
			comando.adicionarVariavel("numCpfCnpjPagador", numCpfCnpjPagador);
			comando.adicionarVariavel("isLocarTabela", isLocarTabela);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			if (rs.next()) {
				pagadorDDA = setPagadorDDA(rs);
				pagadorDDA.setListaPagadorDDAAgregado(obterPagadorDDAAgregado(pagadorDDA.getId()));
				pagadorDDA.setListaPagadorDDAConta(obterPagadorDDAConta(pagadorDDA.getId()));
			}
		} catch (SQLException e) {
			throw new ComumException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}
		return pagadorDDA;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#obterPagadorDDAAgregado(java.lang.Long)
	 */
	public List<PagadorDDAAgregado> obterPagadorDDAAgregado(Long idPagadorDDA) throws ComumException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<PagadorDDAAgregado> listaPagadorAgregado = new ArrayList<PagadorDDAAgregado>();
		try {
			comando = getComando("OBTER_PAGADOR_DDA_AGREGADO");
			comando.adicionarVariavel(ID_PAGADOR_DDA, idPagadorDDA);
			comando.configurar();
			conn = estabelecerConexao();
			getLogger().debug(comando.getSqlEmUso());
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				listaPagadorAgregado.add(setPagadorDDAAgregado(rs));
			}
		} catch (SQLException e) {
			throw new ComumException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}
		return listaPagadorAgregado;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#obterPagadorDDAConta(java.lang.Long)
	 */
	public List<PagadorDDAConta> obterPagadorDDAConta(Long idPagadorDDA) throws ComumException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<PagadorDDAConta> listaPagadorConta = new ArrayList<PagadorDDAConta>();
		try {
			comando = getComando("OBTER_PAGADOR_DDA_CONTA");
			comando.adicionarVariavel(ID_PAGADOR_DDA, idPagadorDDA);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				listaPagadorConta.add(setPagadorDDAConta(rs));
			}
		} catch (SQLException e) {
			throw new ComumException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}
		return listaPagadorConta;
	}

	/**
	 * Método responsável por setar os dados do DDA.PagadorDDA
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException PagadorDDA
	 * 
	 */
	private PagadorDDA setPagadorDDA(ResultSet rs) throws SQLException {
		PagadorDDA pagadorDDA = new PagadorDDA();
		pagadorDDA.setId(rs.getLong(1));
		pagadorDDA.setNumCpfCnpjCodTipoPessoa(rs.getString(2), rs.getString(3));
		pagadorDDA.setNumIdentificaPagadorCip(rs.getLong(4));
		pagadorDDA.setNumRefAtualCadPagador(rs.getLong(5));
		pagadorDDA.setQtdAdesaoDDA(rs.getInt(6));
		pagadorDDA.setBolSacadoEletronico(pagadorDDA.getQtdAdesaoDDA());
		pagadorDDA.setNumSeqAtualCadPagador(rs.getLong(8));
		pagadorDDA.setDataHoraUltimaAtualizacao(rs.getDate(9) != null ? new DateTimeDB(rs.getDate(9).getTime()) : null);
		return pagadorDDA;
	}

	/**
	 * Método responsável por setar os dados do DDA.PagadorDDAConta
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException PagadorDDAConta
	 * 
	 */
	private PagadorDDAConta setPagadorDDAConta(ResultSet rs) throws SQLException {
		PagadorDDAConta pagadorDDAConta = new PagadorDDAConta();
		pagadorDDAConta.setId(rs.getLong(1));
		pagadorDDAConta.setPagadorDDA(new PagadorDDA(rs.getLong(2)));
		pagadorDDAConta.setNumAgencia(rs.getInt(3));
		pagadorDDAConta.setNumContaCorrente(rs.getBigDecimal(4));
		pagadorDDAConta.setDataHoraAdesao(rs.getDate(5) != null ? new DateTimeDB(rs.getDate(5).getTime()) : null);

		return pagadorDDAConta;
	}

	/**
	 * Método responsável por setar os dados do DDA.PagadorDDAAgregado
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException PagadorDDAAgregado
	 * 
	 */
	private PagadorDDAAgregado setPagadorDDAAgregado(ResultSet rs) throws SQLException {
		PagadorDDAAgregado pagadorDDAAgregado = new PagadorDDAAgregado();
		pagadorDDAAgregado.setId(rs.getLong(1));
		pagadorDDAAgregado.setPagadorDDA(new PagadorDDA(rs.getLong(2)));
		pagadorDDAAgregado.setNumCpfCnpjCodTipoPessoaAgregado(rs.getString(3), rs.getString(4));
		return pagadorDDAAgregado;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#listarMensagemDDAPagadorLogEnvioArquivo(java.lang.Long)
	 */
	public List<MensagemDDAPagador> listarMensagemDDAPagadorLogEnvioArquivo(Long idLogEnvioArquivoDDA) {
		getLogger().debug("*******INICIO listarMensagemDDALogEnvioArquivo()*******");
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<MensagemDDAPagador> listaMensagemDDAPagador = new ArrayList<MensagemDDAPagador>();
		List<MensagemDDAPagador> listaRetornoMensagemDDAPagador = null;
		try {
			comando = getComando("LISTAR_MENSAGEM_DDA");
			comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
			comando.configurar();
			getLogger().debug("idLogEnvioArquivoDDA = " + idLogEnvioArquivoDDA);
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				listaMensagemDDAPagador.add(getMensagemDDAPagadorReferencias(rs));
			}
			listaRetornoMensagemDDAPagador = new ArrayList<MensagemDDAPagador>();
			for (MensagemDDAPagador mensagemDDAPagador : listaMensagemDDAPagador) {
				getLogger().debug("idMensagemDDA => " + mensagemDDAPagador.getId());
				mensagemDDAPagador.setListaMensagemDDAPagadorConta(getListarMensagemDDAPagadorConta(mensagemDDAPagador.getId()));

				mensagemDDAPagador.setListaMensagemDDAPagadorAgregado(getListarMensagemDDAPagadorAgregado(mensagemDDAPagador.getId()));

				listaRetornoMensagemDDAPagador.add(mensagemDDAPagador);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}
		return listaRetornoMensagemDDAPagador;
	}

	/**
	 * Método responsável por ListarMensagemDDAPagadorConta
	 * 
	 * @param idMensagemDDAPagador
	 * @return List<MensagemDDAPagadorConta>
	 * 
	 */
	private List<MensagemDDAPagadorConta> getListarMensagemDDAPagadorConta(Long idMensagemDDAPagador) {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<MensagemDDAPagadorConta> listaMensagemDDAPagadorConta = null;
		try {

			comando = getComando("LISTAR_MENSAGEMDDA_PAGADOR_CONTA");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagemDDAPagador);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);

			listaMensagemDDAPagadorConta = new ArrayList<MensagemDDAPagadorConta>();
			while (rs.next()) {
				listaMensagemDDAPagadorConta.add(setMensagemDDAPagadorConta(rs));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}

		return listaMensagemDDAPagadorConta;
	}

	/**
	 * Método responsável por Listar a MensagemDDAPagadorAgregado
	 * 
	 * @param idMensagemDDAPagador
	 * @return List<MensagemDDAPagadorAgregado>
	 * 
	 */
	private List<MensagemDDAPagadorAgregado> getListarMensagemDDAPagadorAgregado(Long idMensagemDDAPagador) {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<MensagemDDAPagadorAgregado> listaMensagemDDAPagadorAgregado = null;
		try {
			comando = getComando("LISTAR_MENSAGEMDDA_PAGADOR_AGREGADO");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagemDDAPagador);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			listaMensagemDDAPagadorAgregado = new ArrayList<MensagemDDAPagadorAgregado>();
			while (rs.next()) {
				listaMensagemDDAPagadorAgregado.add(setMensagemDDAPagadorAgregado(rs));
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}

		return listaMensagemDDAPagadorAgregado;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#obterMensagemDDAPagadorAtualizaReferencias(java.lang.Long)
	 */
	public MensagemDDAPagador obterMensagemDDAPagadorAtualizaReferencias(Long idMensagem) throws ComumException {
		getLogger().debug("Inicio Classe: PagadorCipDaoImpl Metodo: obterMensagemDDAPagadorAtualizaReferencias IdMensagemDDA = " + idMensagem);
		MensagemDDAPagador mensagem = obterMensagemDDAPagadorReferencia(idMensagem);
		getLogger().debug("Busca feita com Sucesso - obterMensagemDDAPagadorReferencia");
		if (!ObjectUtil.isNull(mensagem) && !ObjectUtil.isEmpty(mensagem.getNumRefAtualCadPagador()) && !ObjectUtil.isEmpty(mensagem.getNumSeqAtualCadPagador())) {
			atualizaMensagemDDAPagador(mensagem.getId(), mensagem.getNumRefAtualCadPagador(), mensagem.getNumSeqAtualCadPagador());
		}
		getLogger().debug("FIM Classe: PagadorCipDaoImpl Metodo: obterMensagemDDAPagadorAtualizaReferencias");
		return mensagem;

	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#removerAgregado(java.lang.Long)
	 */
	public void removerAgregado(Long idPagadorDDA) throws ComumException {
		remover(idPagadorDDA, "REMOVER_PAGADOR_AGREGADO");
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#removerConta(java.lang.Long)
	 */
	public void removerConta(Long idPagadorDDA) throws ComumException {
		remover(idPagadorDDA, "REMOVER_PAGADOR_CONTA");
	}

	/**
	 * @param idPagadorDDA
	 * @param nomeComando
	 * @throws ComumException void
	 * 
	 */
	private void remover(Long idPagadorDDA, String nomeComando) throws ComumException {
		executarUpdate(nomeComando, setParametroIdPagador(idPagadorDDA));
	}

	/**
	 * Método responsável por
	 * 
	 * @param idPagadorDDA
	 * @return Map<String,Object>
	 * 
	 */
	private Map<String, Object> setParametroIdPagador(Long idPagadorDDA) {
		return setParametroId(idPagadorDDA, "idPagador");
	}

	/**
	 * Método responsável por Obter a MensagemDDAPagador com o numero de referencia e numero sequencial, para isto e feito um JOIN com a tabela PAGADORDDA
	 * 
	 * @param idMensagem
	 * @return
	 * @throws ComumException MensagemDDAPagador
	 * 
	 */
	private MensagemDDAPagador obterMensagemDDAPagadorReferencia(Long idMensagem) throws ComumException {
		getLogger().debug("*******INICIO obterMensagemDDAPagadorReferencia()*******");
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		MensagemDDAPagador mensagemDDAPagador = null;

		try {
			comando = getComando("OBTER_MENSAGEMDDA_PAGADOR_REFERENCIAS");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			if (rs.next()) {
				mensagemDDAPagador = getMensagemDDAPagadorReferencias(rs);
			}

			comando = getComando("LISTAR_MENSAGEMDDA_PAGADOR_AGREGADO");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				mensagemDDAPagador.getListaMensagemDDAPagadorAgregado().add(setMensagemDDAPagadorAgregado(rs));
			}

			comando = getComando("LISTAR_MENSAGEMDDA_PAGADOR_CONTA");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
			comando.configurar();
			getLogger().debug(comando.getSqlEmUso());
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				mensagemDDAPagador.getListaMensagemDDAPagadorConta().add(setMensagemDDAPagadorConta(rs));
			}

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}

		return mensagemDDAPagador;
	}

	/**
	 * Método responsável por Atualizar as referencias da MensagemDDAPagador - numRefAtualCadPagador e numSeqAtualCadPagador
	 * 
	 * @param idMensagemDDAPagador
	 * @param numRefAtualCadPagador
	 * @param numSeqAtualCadPagador
	 * @throws ComumException void
	 * 
	 */
	private void atualizaMensagemDDAPagador(Long idMensagemDDAPagador, Long numRefAtualCadPagador, Long numSeqAtualCadPagador) throws ComumException {
		getLogger().debug("Inicio Classe: PagadorCipDaoImpl Metodo: atualizaMensagemDDAPagador");
		Map<String, Object> parametros = getMapaParametro(idMensagemDDAPagador, "idMensagemDDAPagador");
		parametros.put("numRefAtualCadPagador", numRefAtualCadPagador);
		parametros.put("numSeqAtualCadPagador", numSeqAtualCadPagador);
		executarUpdate("ATUALIZAR_MENSAGEMDDA_PAGADOR", parametros);
		getLogger().debug("FIM Classe: PagadorCipDaoImpl Metodo: atualizaMensagemDDAPagador");

	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException MensagemDDAPagador
	 * 
	 */
	private MensagemDDAPagador getMensagemDDAPagadorReferencias(ResultSet rs) throws SQLException {
		MensagemDDAPagador mensagemDDAPagador = getMensagemDDAPagador(rs);
		mensagemDDAPagador.setNumIdentificaPagadorCip(ObjectUtil.isNull(rs.getLong("numIdentificaPagadorCip")) ? null : rs.getLong("numIdentificaPagadorCip"));
		return mensagemDDAPagador;
	}

	/**
	 * @param rs
	 * @return
	 * @throws SQLException MensagemDDAPagador
	 * 
	 */
	private MensagemDDAPagador getMensagemDDAPagador(ResultSet rs) throws SQLException {
		MensagemDDAPagador mensagemDDAPagador = new MensagemDDAPagador();
		mensagemDDAPagador.setId(rs.getLong("idMensagemDDA"));
		mensagemDDAPagador.setNumCpfCnpjPagador(rs.getString("numCpfCnpjPagador"));
		mensagemDDAPagador.setCodTipoPessoaPagador(rs.getString("codTipoPessoaPagador"));
		mensagemDDAPagador.setBolPagadorEletronico(rs.getBoolean("bolPagadorEletronico"));
		mensagemDDAPagador.setNumRefAtualCadPagador(getNullableLong("numRefAtualCadPagador", rs));
		mensagemDDAPagador.setNumSeqAtualCadPagador(getNullableLong("numSeqAtualCadPagador", rs));

		if (ObjectUtil.isNull(mensagemDDAPagador.getMensagemDDA())) {
			mensagemDDAPagador.setMensagemDDA(obterMensagemDDA(mensagemDDAPagador.getId()));
		}

		return mensagemDDAPagador;
	}

	/**
	 * Método responsável por
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException MensagemDDAPagadorAgregado
	 * 
	 */
	private MensagemDDAPagadorAgregado setMensagemDDAPagadorAgregado(ResultSet rs) throws SQLException {
		return new MensagemDDAPagadorAgregado(rs.getLong("idMensagemDDAPagadorAgregado"), rs.getLong("idMensagemDDA"), rs.getString("numCpfCnpjAgregado"), rs.getString("codTipoPessoaAgregado"),
				ObjectUtil.isEmpty(rs.getString("codTipoOperacao")) ? null : rs.getString("codTipoOperacao"));
	}

	/**
	 * Método responsável por
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException MensagemDDAPagadorConta
	 * 
	 */
	private MensagemDDAPagadorConta setMensagemDDAPagadorConta(ResultSet rs) throws SQLException {
		return new MensagemDDAPagadorConta(rs.getLong("idMensagemDDAPagadorConta"), rs.getLong("idMensagemDDA"), rs.getInt("numAgencia"), rs.getBigDecimal("numContaCorrente"), rs.getString("codTipoOperacao"),
				rs.getDate("dataHoraAdesao"));
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#gravarPagadorDDAXmlADDA002(long, java.lang.String, java.lang.String)
	 */
	public void gravarPagadorDDAXmlADDA002(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
		executarScriptEmXML("PROCESSAR_PAGADORDDA_XML_ADDA002", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_GRAVAR_PAGADOR_ADDA002);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#executarManutencaoPagadorEletronico(long, long, long)
	 */
	public void executarManutencaoPagadorEletronico(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
		Connection connection = null;
		try {
			connection = estabelecerConexao();
			executarAtualizacaoArquivoDDA(connection, "MANUTENCAO_PAGADORDDA_ADDA003", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_MANUTENCAO_PAGADOR_ADDA003);
		} finally {
			fecharConexao(connection);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.PagadorCipDao#listarPagadorEletronicoModificados(long, long, long)
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> listarPagadorEletronicoModificados(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) throws ComumException {
		Connection connection = null;
		List<Object[]> parametros = null;
		Comando comando = null;
		Query query = null;
		try {
			connection = estabelecerConexao();

			comando = getComando("LISTAR_PAGADORDDA_MODIFICADOS_ADDA003");
			comando.adicionarVariavel("idLogArquivoRecebido", idLogArquivoRecebido);
			comando.adicionarVariavel("idLogDetArqInicial", idLogDetArqInicial);
			comando.adicionarVariavel("idLogDetArqFinal", idLogDetArqFinal);
			comando.configurar();

			getLogger().debug(comando.getSqlEmUso());
			query = comando.criarNativeQuery(getEntityManager());
			parametros = query.getResultList();
		} finally {
			fecharComando(comando);
			fecharConexao(connection);
		}
		return parametros;
	}

}
