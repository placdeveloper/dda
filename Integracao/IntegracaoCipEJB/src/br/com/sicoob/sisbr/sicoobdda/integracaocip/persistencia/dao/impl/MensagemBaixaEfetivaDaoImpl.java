/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-integracao-cip-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl
 * Arquivo:         MensagemBaixaEfetivaDaoImpl.java
 * Data Criação:    Jul 24, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.LinhaDigitavelCodigoBarrasUtil;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDABaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoBaixaEfetiva;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.IntegracaoCipCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao;

/**
 * MensagemBaixaEfetivaDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MensagemBaixaEfetivaDaoImpl extends IntegracaoCipCrudDaoDB2<MensagemDDABaixaEfetiva> implements MensagemBaixaEfetivaDao {

	public static final String ARQUIVO_QUERIES = "sicoobdda_mensagem.baixaefetiva.queries.xml";
	public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-mensagem-baixaefetiva";

	private static final String LOG_BAIXA_EFETIVA_BOLETO_ADDA118RR2 = "Gravar Baixa Efetiva Boleto ADDA118RR2";

	/**
     * 
     */
	public MensagemBaixaEfetivaDaoImpl() {
		super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, MensagemDDABaixaEfetiva.class, null, null);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao#gravarBaixaADDA118RR2(long,
	 *      long, long)
	 */
	public void gravarBaixaADDA118RR2(long idLogArquivoRecebido, long idLogDetArqInicial, long idLogDetArqFinal) {
		executarScriptEmXML("GRAVAR_BOLETODDA_BAIXA_EFETIVA_XML_ADDA118RR2", idLogArquivoRecebido, idLogDetArqInicial, idLogDetArqFinal, LOG_BAIXA_EFETIVA_BOLETO_ADDA118RR2);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao#obterMensagemDDABaixaEfetivaAtualizaReferencias(java.lang.Long)
	 */
	public MensagemDDABaixaEfetiva obterMensagemDDABaixaEfetivaAtualizaReferencias(Long idMensagem) throws ComumException {
		getLogger().debug("Inicio Classe: BoletoCipDaoImpl Metodo: obterMensagemDDABaixaEfetivaAtualizaReferencias IdMensagemDDA = " + idMensagem);
		MensagemDDABaixaEfetiva mensagem = obterMensagemDDABaixaEfetivaReferencia(idMensagem);
		validaIdentificadorBaixaOperacionalNulo(mensagem);

		getLogger().debug("Busca feita com Sucesso - obterMensagemDDABaixaEfetivaAtualizaReferencias");
		if (!ObjectUtil.isNull(mensagem) && verificaCampoAlterar(mensagem)) {
			atualizaMensagemDDABaixaEfetiva(mensagem.getId(), mensagem.getNumRefAtualBaixaEfetiva(), mensagem.getNumSeqAtualBaixaEfetiva(),
					mensagem.getNumIdentificadorBaixaOper(), mensagem.getCodCanalPagamento(), mensagem.getCodMeioPagamento(), mensagem.getNumRefAtualCadBoleto(), mensagem
							.getCodTipoBaixaEfetiva().toString(), mensagem.getValorBaixa());
		}

		getLogger().debug("FIM Classe: BoletoCipDaoImpl Metodo: obterMensagemDDABaixaEfetivaAtualizaReferencias");
		return mensagem;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see br.com.sicoob.sisbr.sicoobdda.integracaocip.persistencia.dao.MensagemBaixaEfetivaDao#listarMensagemDDABaixaEfetiva(java.lang.Long)
	 */
	public List<MensagemDDABaixaEfetiva> listarMensagemDDABaixaEfetiva(Long idLogEnvioArquivoDDA) throws ComumException {
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		List<MensagemDDABaixaEfetiva> listarMensagemDDABaixaEfetiva = new ArrayList<MensagemDDABaixaEfetiva>();

		try {
			comando = getComando("LISTAR_MENSAGEMDDA_BAIXA_EFETIVA");
			comando.adicionarVariavel("idLogEnvioArquivoDDA", idLogEnvioArquivoDDA);
			comando.configurar();
			getLogger().debug("idLogEnvioArquivoDDA = " + idLogEnvioArquivoDDA);
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			while (rs.next()) {
				listarMensagemDDABaixaEfetiva.add(setMensagemDDABaixaEfetiva(rs));
			}

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}
		return listarMensagemDDABaixaEfetiva;
	}

	/**
	 * Método responsável por
	 * 
	 * @param idMensagem
	 * @return MensagemDDABaixaEfetiva
	 * 
	 */
	private MensagemDDABaixaEfetiva obterMensagemDDABaixaEfetivaReferencia(Long idMensagem) {
		getLogger().debug("*******INICIO obterMensagemDDABaixaEfetivaReferencia()*******");
		Comando comando = null;
		Connection conn = null;
		ResultSet rs = null;
		MensagemDDABaixaEfetiva mensagemDDABaixaEfetiva = null;

		try {
			comando = getComando("OBTER_MENSAGEMDDA_BAIXA_EFETIVA_REFERENCIAS");
			comando.adicionarVariavel(ID_MENSAGEM, idMensagem);
			comando.configurar();
			getLogger().debug("idMensagem = " + idMensagem);
			getLogger().debug(comando.getSqlEmUso());
			conn = estabelecerConexao();
			rs = comando.executarConsulta(conn);
			if (rs.next()) {
				mensagemDDABaixaEfetiva = setMensagemDDABaixaEfetiva(rs);
			}
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			fecharResultSet(rs);
			fecharComando(comando);
			fecharConexao(conn);
		}
		return mensagemDDABaixaEfetiva;
	}

	/**
	 * Método responsável por
	 * 
	 * @param idMensagemDDABaixaEfetiva
	 * @param numRefAtualBaixaEfetiva
	 * @param numSeqAtualBaixaEfetiva
	 * @param numIdentificadorBaixaOper
	 * @param codCanalPagamento
	 * @param codMeioPagamento
	 * @param numRefAtualCadBoleto
	 * @param codTipoBaixaEfetiva
	 * @throws ComumException void
	 * 
	 */
	private void atualizaMensagemDDABaixaEfetiva(Long idMensagemDDABaixaEfetiva, Long numRefAtualBaixaEfetiva, Long numSeqAtualBaixaEfetiva, Long numIdentificadorBaixaOper,
			Integer codCanalPagamento, Integer codMeioPagamento, Long numRefAtualCadBoleto, String codTipoBaixaEfetiva, BigDecimal valorBaixa) throws ComumException {
		getLogger().debug(
				"Inicio Classe: MensagemBaixaEfetivaDaoImpl Metodo: atualizaMensagemDDABaixaEfetiva Parametros - idMensagemDDABaixaEfetiva = " + idMensagemDDABaixaEfetiva);
		Map<String, Object> parametros = getMapaParametrosAtualizarMensagem(idMensagemDDABaixaEfetiva, numRefAtualBaixaEfetiva, numSeqAtualBaixaEfetiva, numIdentificadorBaixaOper,
				codCanalPagamento, codMeioPagamento, numRefAtualCadBoleto, codTipoBaixaEfetiva, valorBaixa);

		executarUpdate("ATUALIZAR_MENSAGEMDDA_BAIXA_EFETIVA", parametros);
		getLogger().debug("FIM Classe: MensagemBaixaEfetivaDaoImpl Metodo: atualizaMensagemDDABaixaEfetiva");
	}

	/**
	 * Método responsável por
	 * 
	 * @param idMensagemDDABaixaEfetiva
	 * @param numRefAtualBaixaEfetiva
	 * @param numSeqAtualBaixaEfetiva
	 * @param numIdentificadorBaixaOper
	 * @param codCanalPagamento
	 * @param codMeioPagamento
	 * @param numRefAtualCadBoleto
	 * @param codTipoBaixaEfetiva
	 * @param valorBaixa
	 * @return Map<String,Object>
	 * 
	 */
	private Map<String, Object> getMapaParametrosAtualizarMensagem(Long idMensagemDDABaixaEfetiva, Long numRefAtualBaixaEfetiva, Long numSeqAtualBaixaEfetiva,
			Long numIdentificadorBaixaOper, Integer codCanalPagamento, Integer codMeioPagamento, Long numRefAtualCadBoleto, String codTipoBaixaEfetiva, BigDecimal valorBaixa) {
		Map<String, Object> parametros = getMapaParametro(idMensagemDDABaixaEfetiva, "idMensagemDDABaixaEfetiva");
		parametros.put("numRefAtualBaixaEfetiva", numRefAtualBaixaEfetiva);
		parametros.put("numSeqAtualBaixaEfetiva", numSeqAtualBaixaEfetiva);
		parametros.put("numIdentificadorBaixaOper", numIdentificadorBaixaOper);

		parametros.put("codCanalPagamento", codCanalPagamento);
		parametros.put("codMeioPagamento", codMeioPagamento);
		parametros.put("valorBaixa", valorBaixa);
		parametros.put("codTipoBaixaEfetiva", codTipoBaixaEfetiva);

		parametros.put("numRefAtualCadBoleto", numRefAtualCadBoleto);
		return parametros;
	}

	/**
	 * Método responsável por fazer a validaçao Baixa Operacional nula
	 * 
	 * @param mensagem void
	 * 
	 */
	private void validaIdentificadorBaixaOperacionalNulo(MensagemDDABaixaEfetiva mensagem) {
		if (ObjectUtil.isNull(mensagem.getNumIdentificadorBaixaOper())
				&& (mensagem.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTERBANCARIA) || mensagem.getCodTipoBaixaEfetiva().toString()
						.equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_INTRABANCARIA))) {
			mensagem.setCodCanalPagamento(null);
			mensagem.setCodMeioPagamento(null);
			mensagem.setValorBaixa(BigDecimal.ZERO);
			mensagem.setCodTipoBaixaEfetiva(Integer.valueOf(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA));
		} else if (ObjectUtil.isNull(mensagem.getNumIdentificadorBaixaOper())
				&& (mensagem.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_SOLICITACAO_CEDENTE)
						|| mensagem.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_ENVIO_PROTESTO)
						|| mensagem.getCodTipoBaixaEfetiva().toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_INTEGRAL_DECURSO_PRAZO) || mensagem.getCodTipoBaixaEfetiva()
						.toString().equals(TipoBaixaEfetiva.BAIXA_EFETIVA_SOLICITACAO_INSTITUICAO_DESTINATARIA))) {
			mensagem.setCodCanalPagamento(null);
			mensagem.setCodMeioPagamento(null);
			mensagem.setValorBaixa(BigDecimal.ZERO);
		}
	}

	/**
	 * Método responsável por
	 * 
	 * @param mensagem
	 * @return boolean
	 * 
	 */
	private boolean verificaCampoAlterar(MensagemDDABaixaEfetiva mensagem) {
		return !ObjectUtil.isEmpty(mensagem.getNumRefAtualBaixaEfetiva()) || !ObjectUtil.isEmpty(mensagem.getNumSeqAtualBaixaEfetiva())
				|| !ObjectUtil.isEmpty(mensagem.getNumIdentificadorBaixaOper()) || !ObjectUtil.isEmpty(mensagem.getCodCanalPagamento())
				|| !ObjectUtil.isEmpty(mensagem.getCodMeioPagamento()) || !ObjectUtil.isEmpty(mensagem.getNumRefAtualCadBoleto())
				|| !ObjectUtil.isEmpty(mensagem.getCodTipoBaixaEfetiva());
	}

	/**
	 * Método responsável por
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException MensagemDDABaixaEfetiva
	 * 
	 */
	private MensagemDDABaixaEfetiva setMensagemDDABaixaEfetiva(ResultSet rs) throws SQLException {
		MensagemDDABaixaEfetiva mensagemDDABaixaEfetiva = new MensagemDDABaixaEfetiva();
		mensagemDDABaixaEfetiva.setId(rs.getLong("idMensagemDDA"));

		mensagemDDABaixaEfetiva.setNumCodigoBarra(rs.getString("numCodigoBarra"));
        mensagemDDABaixaEfetiva
                .setNumCodBarrasCampoLivre(rs.getString("numCodigoBarra") != null ? LinhaDigitavelCodigoBarrasUtil.obterCampoLivre(rs.getString("numCodigoBarra")) : null);
		mensagemDDABaixaEfetiva.setCodTipoBaixaEfetiva(rs.getInt("codTipoBaixaEfetiva"));
		mensagemDDABaixaEfetiva.setValorBaixa(rs.getBigDecimal("valorBaixa"));

		mensagemDDABaixaEfetiva.setNumRefAtualCadBoleto(rs.getLong("numRefAtualCadBoleto"));
		mensagemDDABaixaEfetiva.setNumIdentificadorBoletoCip(rs.getLong("numIdentificadorBoletoCip"));

		mensagemDDABaixaEfetiva.setNumIdentificadorBaixaOper(getNullableLong("numIdentificadorBaixaOper", rs));
		mensagemDDABaixaEfetiva.setCodCanalPagamento(getNullableInt("codCanalPagamento", rs));
		mensagemDDABaixaEfetiva.setCodMeioPagamento(getNullableInt("codMeioPagamento", rs));
        mensagemDDABaixaEfetiva.setCodPartRecbdrBaixaOper(rs.getString("codPartRecbdrBaixaOper") != null ? rs.getString("codPartRecbdrBaixaOper") : null);
        mensagemDDABaixaEfetiva.setCodISPBPartRecbdrBaixaOper(rs.getString("codISPBPartRecbdrBaixaOper") != null ? rs.getString("codISPBPartRecbdrBaixaOper") : null);
		
		mensagemDDABaixaEfetiva.setNumRefAtualBaixaEfetiva(getNullableLong("numRefAtualBaixaEfet", rs));
		mensagemDDABaixaEfetiva.setNumSeqAtualBaixaEfetiva(getNullableLong("numSeqAtualBaixaEfet", rs));

		mensagemDDABaixaEfetiva.setMensagemDDA(obterMensagemDDA(mensagemDDABaixaEfetiva.getId()));

		return mensagemDDABaixaEfetiva;
	}
}
