/**
 * Projeto:         SDDA
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         RateioTarifasCipDaoImplx.java
 * Data Criação:    Jan 19, 2018
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.EventoRateioDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.SituacaoRateioCreditoCCODto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.util.ObjectUtil;
import br.com.sicoob.sisbr.sicoobdda.entidades.RateioDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateio;
import br.com.sicoob.sisbr.sicoobdda.entidades.SituacaoRateioLancamento;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoCCODto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoDto;
import br.com.sicoob.sisbr.sicoobdda.integracaointerna.negocio.servicos.ejb.dto.LancamentoIntegracaoRetDto;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao;

/**
 * RateioTarifasCipDaoImpl
 * 
 * @author Danilo.Barros
 */
public class RateioTarifasCipDaoImpl extends OperacionalCrudDaoDB2<RateioDDA> implements RateioTarifasCipDao {

    private static final String ARQUIVO_QUERIES = "sicoobdda_operacional.rateiotarifascip.queries.xml";
    private static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-rateio-tarifas-cip";

    /**
     * 
     */
    public RateioTarifasCipDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, RateioDDA.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#obterDadosLancamentoRateioDDA(java.lang.Long)
     */
    public VisualizaRateioTarifaDto obterDadosLancamentoRateioDDA(Long idRateioLancamento) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idRateioLancamento", idRateioLancamento);
        return obterDados("OBTER_LANCAMENTO_RATEIODDA", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#listaSituacaoRateio()
     */
    public List<SituacaoRateio> listaSituacaoRateio() throws ComumException {
        return listar("LISTA_SITUACAO_RATEIO");
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#listarDadosRateio(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)
     */
    public List<VisualizaRateioTarifaDto> listarDadosRateio(VisualizaRateioTarifaDto dto) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("dto", dto);
        return listar("LISTAR_DADOS_RATEIO_TARIFAS", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#obterTotalDadosRateio(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)
     */
    public List<VisualizaRateioTarifaDto> obterTotalDadosRateio(VisualizaRateioTarifaDto dto) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("dto", dto);
        return listar("OBTER_TOTALIZADORES_RATEIO_TARIFAS", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#listarDadosLancamento(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)
     */
    public List<VisualizaRateioTarifaDto> listarDadosLancamento(VisualizaRateioTarifaDto dto) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("dto", dto);
        return listar("LISTAR_LANCAMENTOS_TARIFAS", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#consolidarEventosTarifaveis()
     */
    public void consolidarEventosTarifaveis() throws ComumException {
        Connection connection = null;
        try {
            connection = estabelecerConexao();
            executarAtualizacao(connection, "CRIAR_TABELA_TEMPORARIA_CONSOLIDACAO");
            executarAtualizacao(connection, "GRAVAR_TABELA_TEMPORARIA_CONSOLIDACAO");
            executarAtualizacao(connection, "CONFIGURAR_TABELA_TEMPORARIA_CONSOLIDACAO");
            executarAtualizacao(connection, "GRAVAR_CONSOLIDACAO");
            executarAtualizacao(connection, "GRAVAR_CONSOLIDACAO_DETALHE");
        } finally {
            fecharConexao(connection);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#obterRateioAtual()
     */
    public RateioDDA obterRateioAtual() throws ComumException {
        List<Long> listaSituacaoRateio = new ArrayList<Long>();
        listaSituacaoRateio.add(SituacaoRateio.AGUARDANDO_APROVACAO);
        listaSituacaoRateio.add(SituacaoRateio.APROVADO_PARA_EFETIVACAO);

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("listaSituacaoRateio", listaSituacaoRateio);

        return obterDados("OBTER_RATEIO_ATUAL", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#pesquisarEventosDisponiveis(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ConsultaEventoRateioDto)
     */
    public List<EventoRateioDto> pesquisarEventosDisponiveis(ConsultaEventoRateioDto dto) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("dto", dto);

        return listar("PESQUISAR_EVENTOS_DISPONIVEIS", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#listarEventosRateio(java.lang.Long)
     */
    public List<EventoRateioDto> listarEventosRateio(Long idRateio) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idRateio", idRateio);

        return listar("LISTAR_EVENTOS_RATEIO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#consolidarLancamentosCCO()
     */
    public void consolidarLancamentosCCO() throws ComumException {
        executarConsulta("CONSOLIDAR_LANCAMENTOS_CCO");
    }

    /**
     * Método responsável por executar a DML do Comando
     * 
     * @param commando
     * @return
     * @throws ComumException
     */
    private void executarConsulta(String commando) throws ComumException {
        Comando comando = null;
        Connection connection = null;
        try {
            connection = estabelecerConexao();
            comando = getComando(commando);
            getLogger().debug(comando.getSql());
            comando.executarConsulta(connection);
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
            fecharConexao(connection);
        }
    }

    /**
     * Método genérico que executa o comando de atualização informada
     * 
     * @param connection
     * @param nomeComando
     * @return
     * @throws ComumException
     * 
     */
    private void executarAtualizacao(Connection connection, String nomeComando) throws ComumException {
        Comando comando = null;
        try {
            comando = getComando(nomeComando);
            comando.configurar();
            getLogger().debug(comando.getSql());
            comando.executarAtualizacao(connection);
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#incluirListaEventoConsolidadoRateio(java.util.List, java.lang.Long,
     *      java.lang.Integer, java.lang.String)
     */
    public void incluirListaEventoConsolidadoRateio(List<EventoRateioDto> lista, Long idRateio, Integer idInstituicaoUsuarioInclusaoRateio, String idUsuarioInclusaoRateio)
            throws ComumException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            debug("Estabelecendo a conexão...");
            conn = estabelecerConexao();

            final String sql = "UPDATE dda.EventoConsolidadoDDA SET idRateioDDA = ?, dataHoraInclusaoRateio = ?, idInstituicaoUsuarioInclusaoRateio = ?, "
                    + "idUsuarioInclusaoRateio = ?, valorTarifaCIP = ?, valorTarifaBancoob = ? WHERE idEventoConsolidadoDDA = ?";
            debug("SQL da Mensagem DDA: " + sql);

            ps = conn.prepareStatement(sql);

            Date dataHoraInclusao = new Date(new java.util.Date().getTime());

            for (EventoRateioDto dto : lista) {
                ps.setLong(1, idRateio);
                ps.setDate(2, dataHoraInclusao);
                ps.setInt(3, idInstituicaoUsuarioInclusaoRateio);
                ps.setString(4, idUsuarioInclusaoRateio);
                ps.setBigDecimal(5, dto.getValorUnitarioCIP());
                ps.setBigDecimal(6, dto.getValorUnitarioBancoob());

                ps.setLong(7, dto.getIdEventoConsolidado());

                ps.addBatch();
            }

            debug("Atualizando os eventos consolidados...");
            ps.executeBatch();
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharStatement(ps);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#removerListaEventoConsolidadoRateio(java.util.List)
     */
    public void removerListaEventoConsolidadoRateio(List<EventoRateioDto> lista) throws ComumException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            debug("Estabelecendo a conexão...");
            conn = estabelecerConexao();

            final String sql = "UPDATE dda.EventoConsolidadoDDA SET idRateioDDA = null, dataHoraInclusaoRateio = null, idInstituicaoUsuarioInclusaoRateio = null, "
                    + "idUsuarioInclusaoRateio = null, valorTarifaCIP = null, valorTarifaBancoob = null WHERE idEventoConsolidadoDDA = ?";
            debug("SQL da Mensagem DDA: " + sql);

            ps = conn.prepareStatement(sql);

            for (EventoRateioDto dto : lista) {
                ps.setLong(1, dto.getIdEventoConsolidado());

                ps.addBatch();
            }

            debug("Atualizando os eventos consolidados...");
            ps.executeBatch();
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharStatement(ps);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#atualizarEventoConsolidado(java.util.List)
     */
    public void atualizarEventoConsolidado(List<EventoRateioDto> lista) throws ComumException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            debug("Estabelecendo a conexão...");
            conn = estabelecerConexao();

            final String sql = "UPDATE dda.EventoConsolidadoDDA SET qtdApuradoCIP = ? WHERE idEventoConsolidadoDDA = ?";
            debug("SQL: " + sql);

            ps = conn.prepareStatement(sql);

            for (EventoRateioDto dto : lista) {
                ps.setInt(1, dto.getQtdCIP());
                ps.setLong(2, dto.getIdEventoConsolidado());

                ps.addBatch();
            }

            debug("Atualizando os eventos consolidados...");
            ps.executeBatch();
        } catch (SQLException e) {
            getLogger().erro(e, e.getMessage());
            throw new ComumException(e);
        } finally {
            fecharStatement(ps);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#listarLancamentosRateioCCO(java.lang.Long, java.lang.Integer,
     *      java.lang.Long)
     */
    public List<LancamentoIntegracaoDto> listarLancamentosRateioCCO(Long idRateioDDA, Integer idInstituicao, Long idRateioDDALancamento) throws ComumException {
        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Obtendo os lançamentos de rateio para efetivação no CCO...");

        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idRateioDDA", idRateioDDA);
        parametros.put("idInstituicao", idInstituicao);
        parametros.put("idRateioDDALancamento", idRateioDDALancamento);

        return listar("LISTAR_LANCAMENTOS_RATEIO_CCO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#atualizarLancamentosRateioCCO(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.RateioCreditoLancamentoCCODto,
     *      java.util.List)
     */
    public void atualizarLancamentosRateioCCO(RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto, List<LancamentoIntegracaoCCODto> listaLancamentosIntegracaoCCODto)
            throws ComumException {
        Comando comando = null;
        Connection connection = null;

        if (!ObjectUtil.isEmpty(listaLancamentosIntegracaoCCODto)) {
            try {
                connection = estabelecerConexao();
                comando = getComando("ATUALIZAR_LANCAMENTOS_RATEIO_CCO");
                getLogger().debug(comando.getSql());
                List<Object[]> listaUpdates = new ArrayList<Object[]>(listaLancamentosIntegracaoCCODto.size());

                for (LancamentoIntegracaoCCODto lancamentoIntegracaoCCODto : listaLancamentosIntegracaoCCODto) {
                    LancamentoIntegracaoDto lancamentoIntegracaoDto = lancamentoIntegracaoCCODto.getLancamentoIntegracaoDto();
                    LancamentoIntegracaoRetDto lancamentoIntegracaoRetDto = lancamentoIntegracaoCCODto.getLancamentoIntegracaoRetDto();

                    /* @formatter:off */
                    if (!ObjectUtil.isNull(lancamentoIntegracaoRetDto)) {
                        listaUpdates.add(new Object[] { 
                                lancamentoIntegracaoDto.getDataLote(), 
                                new java.util.Date(), 
                                lancamentoIntegracaoRetDto.getNumSeqLanc(),
                                tratarRetLancamentoIntegracao(lancamentoIntegracaoRetDto.getCodErroRetorno()), 
                                tratarRetLancamentoIntegracao(lancamentoIntegracaoRetDto.getMensagem()), 
                                lancamentoIntegracaoRetDto.getCodRetorno(),
                                tratarRetLancamentoIntegracao(lancamentoIntegracaoRetDto.getCampoErro()),
                                getSituacaoRateioLancamento(lancamentoIntegracaoRetDto.getCodRetorno(), rateioCreditoLancamentoCCODto.getCodSituacaoRateioLancamento()),
                                lancamentoIntegracaoDto.getNumContaCorrente(),
                                lancamentoIntegracaoDto.getIdInstituicaoTransferenciaDebito(),
                                rateioCreditoLancamentoCCODto.getIdRateioDDA(), 
                                rateioCreditoLancamentoCCODto.getIdInstituicao(),
                                getCodSituacaoRateioLancamento(rateioCreditoLancamentoCCODto),
                                lancamentoIntegracaoDto.getNumSeqLanc() });
                    } else if(Arrays.asList(Constantes.ID_BANCOOB, Constantes.ID_SICOOB).contains(rateioCreditoLancamentoCCODto.getIdInstituicao())) {
                        listaUpdates.add(new Object[] { 
                                lancamentoIntegracaoDto.getDataLote(), 
                                new java.util.Date(), 
                                null,
                                null, 
                                null, 
                                null,
                                null,
                                SituacaoRateioLancamento.ISENTO,
                                lancamentoIntegracaoDto.getNumContaCorrente(),
                                lancamentoIntegracaoDto.getIdInstituicaoTransferenciaDebito(),
                                rateioCreditoLancamentoCCODto.getIdRateioDDA(), 
                                rateioCreditoLancamentoCCODto.getIdInstituicao(),
                                getCodSituacaoRateioLancamento(rateioCreditoLancamentoCCODto),
                                lancamentoIntegracaoDto.getNumSeqLanc() });
                    }
                    /* @formatter:on */
                }

                comando.executarAtualizacaoEmLote(listaUpdates, connection);
            } catch (PersistenciaException e) {
                throw new ComumException(e);
            } finally {
                fecharComando(comando);
                fecharConexao(connection);
            }
        }
    }

    /**
     * Método responsável por verificar o retorno do CCO
     * 
     * @param retorno
     * @return String
     */
    private String tratarRetLancamentoIntegracao(String retorno) {
        if (!ObjectUtil.isEmpty(retorno)) {
            return retorno.trim();
        }
        return null;
    }

    /**
     * Método responsável por retornar a situação do lançamento para compor os parâmetros de atualização
     * 
     * @param rateioCreditoLancamentoCCODto
     * @return Long
     * @throws
     */
    private Long getCodSituacaoRateioLancamento(RateioCreditoLancamentoCCODto rateioCreditoLancamentoCCODto) {
        if (Arrays.asList(SituacaoRateioLancamento.EFETIVADO_COM_AJUSTE, SituacaoRateioLancamento.EFETIVADO_MANUALMENTE).contains(
                rateioCreditoLancamentoCCODto.getCodSituacaoRateioLancamento())) {
            return SituacaoRateioLancamento.ERRO_EFETIVACAO;
        } else {
            return rateioCreditoLancamentoCCODto.getCodSituacaoRateioLancamento();
        }
    }

    /**
     * Método responsável por definir a situação do lançamento com base no retorno do CCO e da situação atual
     * 
     * @param codRetorno
     * @return Long
     * @throws
     */
    private Long getSituacaoRateioLancamento(Integer codRetorno, Long codSituacaoRateioLancamento) {
        if (Constantes.INTEGER_ZERO == codRetorno) {
            return SituacaoRateioLancamento.ERRO_EFETIVACAO;
        } else if (SituacaoRateioLancamento.AGUARDANDO_EFETIVACAO == codSituacaoRateioLancamento) {
            return SituacaoRateioLancamento.EFETIVADO;
        } else {
            return codSituacaoRateioLancamento;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#existeRateioProcessando()
     */
    public boolean existeRateioProcessando() throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>(1);
        parametros.put("codSituacaoRateio", SituacaoRateio.PROCESSANDO_EFETIVACAO);

        return existeRegistro("EXISTE_RATEIO_PROCESSAMENTO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#possuiListaEventoConsolidado(java.lang.Long)
     */
    public boolean possuiListaEventoConsolidado(Long idRateio) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idRateio", idRateio);

        return existeRegistro("TOTAL_EVENTOS_RATEIO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#atualizarSituacaoRateioCreditoCCO(java.lang.Long, java.lang.Long)
     */
    public void atualizarSituacaoRateioCreditoCCO(Long idRateioDDA, Long codSituacaoRateio) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("idRateioDDA", idRateioDDA);
        parametros.put("codSituacaoRateio", codSituacaoRateio);

        executarUpdate("ATUALIZAR_SITUACAO_RATEIO_CCO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#pesquisarRateioTarifaDDAPaginado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)
     */
    public List<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(VisualizaRateioTarifaDto visualizaRateioTarifaDto) throws BancoobException {
        return pesquisarRateioTarifaDDAPaginado(visualizaRateioTarifaDto, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#pesquisarRateioTarifaDDAPaginado(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto,
     *      java.lang.Integer, java.lang.Integer)
     */
    public List<VisualizaRateioTarifaDto> pesquisarRateioTarifaDDAPaginado(VisualizaRateioTarifaDto visualizaRateioTarifaDto, Integer pagina, Integer tamanhoPagina)
            throws BancoobException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("consultaDto", visualizaRateioTarifaDto);
        parametros.put("pagina", pagina);
        parametros.put("tamanhoPagina", tamanhoPagina);

        Comando comando = null;
        ResultSet rs = null;
        Connection conn = null;

        List<VisualizaRateioTarifaDto> lista = null;

        try {
            conn = estabelecerConexao();
            comando = getComando("PESQUISA_RATEIO_PAGINADA", parametros);
            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                lista = new ArrayList<VisualizaRateioTarifaDto>();

                do {
                    VisualizaRateioTarifaDto dto = new VisualizaRateioTarifaDto(rs.getLong("IDRATEIODDA"), rs.getDate("DATAHORAINCLUSAO"), rs.getString("DESCSITUACAORATEIO"),
                            rs.getDate("DATAHORAAPROVACAO"), rs.getLong("QTDTOTAL"), rs.getBigDecimal("VALORTOTAL"), rs.getDate("DATAINICIOEVENTO"), rs.getDate("DATAFIMEVENTO"));
                    lista.add(dto);
                } while (rs.next());
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#countRateioTarifaDDA(br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.VisualizaRateioTarifaDto)
     */
    public Integer countRateioTarifaDDA(VisualizaRateioTarifaDto visualizaRateioTarifaDto) throws BancoobException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("consultaDto", visualizaRateioTarifaDto);

        Comando comando = null;
        ResultSet rs = null;
        Connection conn = null;

        Integer total = 0;

        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_COUNT_RATEIO_PAGINADA", parametros);

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } catch (PersistenceException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return total;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.RateioTarifasCipDao#listarRateiosCreditoAtualizacaoCCO()
     */
    public SituacaoRateioCreditoCCODto listarRateiosCreditoAtualizacaoCCO(Long idRateio) throws ComumException {
        getLogger().debug(Constantes.STR_SEPARACAO_2 + RateioTarifasCipDaoImpl.class.getName());
        getLogger().debug(Constantes.STR_SEPARACAO_2 + "Obtendo os rateios de crétido em processamento para atualização");

        Map<String, Object> parametros = new HashMap<String, Object>();
        Comando comando = null;
        Connection connection = null;
        ResultSet resultSet = null;
        SituacaoRateioCreditoCCODto rateiosCreditoCCO = new SituacaoRateioCreditoCCODto();

        try {
            parametros.put("idRateio", idRateio);
            comando = getComando("LISTAR_RATEIOS_CREDITO_ATUALIZACAO_CCO", parametros);
            getLogger().debug(comando.getSqlEmUso());
            connection = estabelecerConexao();
            resultSet = comando.executarConsulta(connection);
            while (resultSet.next()) {
                rateiosCreditoCCO = new SituacaoRateioCreditoCCODto(resultSet.getLong(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5),
                        resultSet.getInt(6));
            }
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(resultSet);
            fecharComando(comando);
            fecharConexao(connection);
        }

        return rateiosCreditoCCO;
    }
}
