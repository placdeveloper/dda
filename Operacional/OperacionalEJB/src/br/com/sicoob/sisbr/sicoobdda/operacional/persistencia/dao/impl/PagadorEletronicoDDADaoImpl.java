package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.HistoricoPagadorEletronicoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemPendentePagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorAgregadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PagadorEletronicoDDADto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TermoPagadorDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.HistoricoTermoPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAAgregado;
import br.com.sicoob.sisbr.sicoobdda.entidades.PagadorDDAConta;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoMensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTermoPagador;
import br.com.sicoob.sisbr.sicoobdda.entidades.util.Constantes;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao;

/**
 * PagadorEletronicoDDADaoImpl é responsável por
 * 
 * @author Rodrigo.Neri
 */
public class PagadorEletronicoDDADaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements PagadorEletronicoDDADao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.pagador.eletronico.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-pagador-eletronico";
    public static final int PESQUISAR_PAGADOR_ELETRONICO = 1;
    public static final int OBTER_PAGADOR_ELETRONICO = 2;
    public static final int OBTER_LISTA_AGENCIA_PAGADOR = 3;

    public PagadorEletronicoDDADaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#isCpfCnpjPagadorEletronico(java.lang.String)
     */
    public boolean isCpfCnpjPagadorEletronico(String cpfCnpj, boolean isPagadoresSicoob) throws ComumException {
        return isCpfCnpjPagadorEletronico(cpfCnpj.length() <= Constantes.TAMANHO_CPF ? Constantes.TIPO_PESSOA_FISICA : Constantes.TIPO_PESSOA_JURIDICA, cpfCnpj, isPagadoresSicoob);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#isCpfCnpjPagadorEletronico(java.lang.Character, java.lang.String)
     */
    public boolean isCpfCnpjPagadorEletronico(Character tipoPessoa, String cpfCnpj, boolean isPagadorSicoob) throws ComumException {
        debug("### Verificando se o CPF/CNPJ é de um pagador eletrônico");
        debug("Parâmetro - tipoPessoa: " + tipoPessoa);
        debug("Parâmetro - cpfCnpj: " + cpfCnpj);
        debug("Parâmetro - isPagadorSicoob: " + isPagadorSicoob);

        boolean sucesso = false;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            debug("Estabelecendo conexão no DB2");
            conn = estabelecerConexao();

            if (isPagadorSicoob) {
                ps = conn.prepareStatement(
                        "select DISTINCT 1 from dda.PAGADORDDA as pagador inner join dda.PAGADORDDACONTA as pagadorConta on pagadorCOnta.IDPAGADORDDA = pagador.IDPAGADORDDA where pagador.codTipoPessoaCip = ? AND pagador.numCPfCnpj = ? AND pagador.bolSacadoEletronico = 1");
            } else {
                ps = conn.prepareStatement(
                        "select DISTINCT 1 from dda.PAGADORDDA as pagador where pagador.codTipoPessoaCip = ? AND pagador.numCPfCnpj = ? AND pagador.bolSacadoEletronico = 1");
            }

            ps.setString(1, tipoPessoa.toString());
            ps.setString(2, cpfCnpj);

            boolean executado = ps.execute();

            if (executado) {
                debug("Consulta executada");
                rs = ps.getResultSet();

                if (rs != null && rs.next()) {
                    debug("O CPF/CNPJ é de um pagador eletrônico");
                    sucesso = true;
                }
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharConexao(conn);
            fecharStatement(ps);
        }

        return sucesso;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#obterPagadorDDAConta(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<PagadorDDAConta> obterPagadorDDAConta(String numCpfCnpj) throws ComumException {
        Comando comando = null;
        List<PagadorDDAConta> lista = null;

        try {
            comando = getComando("OBTER_PAGADOR_DDA_CONTA", getMapaNumCpfCnpj(numCpfCnpj));

            Query query = comando.criarQuery(getEntityManager());

            lista = (List<PagadorDDAConta>) query.getResultList();
        } catch (IllegalStateException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
        }

        return lista;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#obterPagadorAgregadoDDA(java.lang.String)
     */
    public List<PagadorAgregadoDto> obterPagadorAgregadoDDA(String numCpfCnpjPagador) throws ComumException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<PagadorAgregadoDto> listaPagadorAgregadoDto = new ArrayList<PagadorAgregadoDto>();
        try {
            comando = getComando("OBTER_PAGADOR_AGREGADO");
            comando.adicionarVariavel("numCpfCnpj", numCpfCnpjPagador);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaPagadorAgregadoDto.add(montaPagadorAgregadoDto(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "OBTER_PAGADOR_AGREGADO");
            throw new OperacionalException("integracaocip.erro.obter.pagador.agregado", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaPagadorAgregadoDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#obterPagadorAgregadoDDA(java.lang.String)
     */
    public List<PagadorDDAAgregado> listarPagadorAgregadoDDA(String numCpfCnpjPagador) throws ComumException {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numCpfCnpj", numCpfCnpjPagador);

        return listar("LISTAR_PAGADORDDA_AGREGADO", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#obterMensagemPendentePagadorDDA(java.lang.String)
     */
    public List<MensagemPendentePagadorDto> obterMensagemPendentePagadorDDA(String numCpfCnpjPagador) throws ComumException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<MensagemPendentePagadorDto> listaMensagemPendentePagadorDto = new ArrayList<MensagemPendentePagadorDto>();
        try {
            comando = getComando("OBTER_MENSAGEM_PENDENTE_PAGADOR");
            comando.adicionarVariavel("numCpfCnpj", numCpfCnpjPagador);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaMensagemPendentePagadorDto.add(montaMensagemPendentePagadorDto(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "OBTER_MENSAGEM_PENDENTE_PAGADOR");
            throw new OperacionalException("integracaocip.erro.obter.mensagem.pendente.pagador", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaMensagemPendentePagadorDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#existeSolicitacaoAdesao(java.lang.String)
     */
    public Boolean existeSolicitacaoAdesao(String numCpfCnpj) throws OperacionalException {
        return existeSolicitacao(numCpfCnpj, TipoMensagemDDA.DDA0001);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#existeSolicitacaoCancelamentoAdesao(java.lang.String)
     */
    public Boolean existeSolicitacaoCancelamentoAdesao(String numCpfCnpj) throws OperacionalException {
        return existeSolicitacao(numCpfCnpj, TipoMensagemDDA.DDA0006);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#existeSolicitacaoAgregado(java.lang.String, java.lang.String)
     */
    public Boolean existeSolicitacaoAgregado(String numCpfCnpj, String numCpfCnpjAgregado) throws OperacionalException {
        return existeSolicitacao(numCpfCnpj, numCpfCnpjAgregado, TipoMensagemDDA.DDA0005, "EXISTE_SOLICITACAO_AGREGADO");
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param codTipoMensagemDDA
     * @return
     * @throws OperacionalException Boolean
     * 
     */
    private Boolean existeSolicitacao(String numCpfCnpj, String codTipoMensagemDDA) throws OperacionalException {
        return existeSolicitacao(numCpfCnpj, null, codTipoMensagemDDA, "EXISTE_SOLICITACAO_ADESAO");
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param codTipoMensagemDDA
     * @param nomeComando
     * @return
     * @throws OperacionalException Boolean
     * 
     */
    private Boolean existeSolicitacao(String numCpfCnpj, String numCpfCnpjAgregado, String codTipoMensagemDDA, String nomeComando) throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        try {
            comando = getComando(nomeComando);
            comando.adicionarVariavel("numCpfCnpj", numCpfCnpj);
            comando.adicionarVariavel("numCpfCnpjAgregado", numCpfCnpjAgregado);
            comando.adicionarVariavel("codTipoMensagemDDA", codTipoMensagemDDA);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            return rs.next();
        } catch (SQLException e) {
            getLogger().erro(e, nomeComando);
            throw new OperacionalException("integracaocip.erro.obter.solicitacao", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#agregadoJaCadastrado(java.lang.String, java.lang.String)
     */
    public Boolean agregadoJaCadastrado(String numCpfCnpjPagador, String numCpfCnpjAgregado) throws ComumException {
        Comando comando = null;
        try {
            comando = getComando("EXISTE_PAGADOR_AGREGADO");
            comando.adicionarVariavel("numCpfCnpj", numCpfCnpjPagador);
            comando.adicionarVariavel("numCpfCnpjAgregado", numCpfCnpjAgregado);
            comando.configurar();
            Query query = comando.criarQuery(getEntityManager());
            query.getSingleResult();
        } catch (NoResultException e) {
            return Boolean.FALSE;
        } finally {
            fecharComando(comando);
        }
        return Boolean.TRUE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#listarHistoricoPagadorEletronico(java.lang.String)
     */
    public List<HistoricoPagadorEletronicoDto> listarHistoricoPagadorEletronico(String numCpfCnpjPagador, Integer idInstituicao) throws OperacionalException {
        debug("### LISTA TERMO PAGADOR ELETRONICO DDA");
        debug("Parâmetro - numCpfCnpjPagador: " + numCpfCnpjPagador);
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        return listarHistoricoPagadorEletronico(criaComandoListarHistoricoPagadorEletronicoCpfCnpj("LISTA_TERMO_PAGADOR_ELETRONICO_DDA", numCpfCnpjPagador, idInstituicao));
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#listarHistoricoPagadorEletronico(java.lang.String)
     */
    public List<HistoricoPagadorEletronicoDto> listarHistoricoPagadorEletronico(Short codTipoTermoPagador, Integer idInstituicao) throws OperacionalException {
        debug("### LISTA TERMO PAGADOR ELETRONICO DDA pelo TERMO ");
        debug("Parâmetro - codTipoTermoPagador: " + codTipoTermoPagador);
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        return listarHistoricoPagadorEletronico(
                criaComandoListarHistoricoPagadorEletronicoTipoTermo("LISTA_TERMO_PAGADOR_ELETRONICO_DDA_TIPO_TERMO", codTipoTermoPagador, idInstituicao));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#obterHistoricoPagadorEletronicoPeloCpfCnpj(java.lang.String,
     *      java.lang.Integer)
     */
    public HistoricoTermoPagador obterHistoricoPagadorEletronicoPeloCpfCnpj(Integer idHistoricoTermoPagador, String numCpfCnpjPagador, Integer idInstituicao)
            throws ComumException {
        debug("### OBTER O HISTORICO TERMO PAGADOR ELETRONICO DDA pelo CPF/CNPJ ");
        debug("Parâmetro - idHistoricoTermoPagador: " + idHistoricoTermoPagador);
        debug("Parâmetro - numCpfCnpjPagador: " + numCpfCnpjPagador);
        debug("Parâmetro - idInstituicao: " + idInstituicao);
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        HistoricoTermoPagador historicoTermoPagador = new HistoricoTermoPagador();
        try {
            comando = getComando("OBTER_HIST_TERMO_PAGADOR_ELETRONICO_DDA_CPF_CNPJ");
            comando.adicionarVariavel("numCpfCnpjPagador", numCpfCnpjPagador);
            comando.adicionarVariavel("idHistoricoTermoPagador", idHistoricoTermoPagador);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                getHistoricoTermoPagador(rs, historicoTermoPagador);
            }
        } catch (SQLException e) {
            getLogger().erro(e, "OBTER_HIST_TERMO_PAGADOR_ELETRONICO_DDA_CPF_CNPJ");
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return historicoTermoPagador;
    }

    /**
     * Método responsável por Popular o Historico Termo Pagador
     * 
     * @param rs
     * @param historicoTermoPagador
     * @throws SQLException void
     * 
     */
    private void getHistoricoTermoPagador(ResultSet rs, HistoricoTermoPagador historicoTermoPagador) throws SQLException {
        historicoTermoPagador.setNumCpfCnpjPagador(rs.getString("numCpfCnpjPagador"));
        historicoTermoPagador.setNumCpfCnpjAgregado(rs.getString("numCpfCnpjAgregado"));
        historicoTermoPagador.setIdCanal(rs.getShort("idCanal"));
        historicoTermoPagador.setDataHoraTermoDDA(new DateTimeDB(rs.getDate("dataHoraTermoDDA").getTime()));
        TipoTermoPagador tipoTermoPagador = new TipoTermoPagador(rs.getShort("codTipoTermoPagador"));
        tipoTermoPagador.setDescTipoTermoPagador(rs.getString("descTipoTermoPagador"));
        historicoTermoPagador.setTipoTermoPagador(tipoTermoPagador);
    }

    /*
     * (non-Javadoc)
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#obterTermoPagadorEletronico(br.com.bancoob.persistencia.types.
     * DateTimeDB , br.com.bancoob.persistencia.types.DateTimeDB, java.lang.Short, java.lang.Boolean)
     */
    public TermoPagadorDto obterTermoPagadorEletronico(DateTimeDB dataInicioVigencia, DateTimeDB dataFimVigencia, Short codTipoTermoPagador, Boolean bolFormatoHtml,
            Integer numCooperativa) throws OperacionalException {
        return obterTermoPagadorEletronico(criaComandoObterTermoPagadorEletronico("OBTER_TERMO_PAGADOR_ELETRONICO_DDA", dataInicioVigencia, dataFimVigencia, codTipoTermoPagador,
                bolFormatoHtml, numCooperativa));
    }

    /**
     * @param nomeComando
     * @param numCpfCnpjPagador
     * @return Comando
     */
    private Comando criaComandoListarHistoricoPagadorEletronicoCpfCnpj(String nomeComando, String numCpfCnpjPagador, Integer idInstituicao) {
        Comando comando = getComando(nomeComando);
        comando.adicionarVariavel("numCpfCnpjPagador", numCpfCnpjPagador);
        comando.adicionarVariavel("idInstituicao", idInstituicao);
        return comando;
    }

    /**
     * @param nomeComando
     * @param numCpfCnpjPagador
     * @return Comando
     */
    private Comando criaComandoListarHistoricoPagadorEletronicoTipoTermo(String nomeComando, Short codTipoTermoPagador, Integer idInstituicao) {
        Comando comando = getComando(nomeComando);
        comando.adicionarVariavel("codTipoTermoPagador", codTipoTermoPagador);
        comando.adicionarVariavel("idInstituicao", idInstituicao);
        return comando;
    }

    /**
     * @param nomeComando
     * @param dataInicioVigencia
     * @param dataFimVigencia
     * @param codTipoTermoPagador
     * @param bolFormatoHtml
     * @return Comando
     */
    private Comando criaComandoObterTermoPagadorEletronico(String nomeComando, DateTimeDB dataInicioVigencia, DateTimeDB dataFimVigencia, Short codTipoTermoPagador,
            Boolean bolFormatoHtml, Integer numCooperativa) {
        Comando comando = getComando(nomeComando);
        comando.adicionarVariavel("dataInicioVigencia", dataInicioVigencia);
        comando.adicionarVariavel("dataFimVigencia", dataFimVigencia);
        comando.adicionarVariavel("codTipoTermoPagador", codTipoTermoPagador);
        comando.adicionarVariavel("bolFormatoHtml", bolFormatoHtml);
        comando.adicionarVariavel("numCooperativa", numCooperativa);
        return comando;
    }

    /**
     * @param comando
     * @return
     * @throws OperacionalException
     */
    private List<HistoricoPagadorEletronicoDto> listarHistoricoPagadorEletronico(Comando comando) throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        List<HistoricoPagadorEletronicoDto> listaHistPagadorEletronicoDto = new ArrayList<HistoricoPagadorEletronicoDto>();
        try {
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaHistPagadorEletronicoDto.add(montaListaHistoricoPagadorEletronico(rs));
            }
        } catch (SQLException e) {
            throw new OperacionalException("integracaocip.erro.obter.lista.tratamento.erro.cip", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaHistPagadorEletronicoDto;
    }

    /**
     * @param comando
     * @return
     * @throws OperacionalException
     */
    private TermoPagadorDto obterTermoPagadorEletronico(Comando comando) throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        TermoPagadorDto termoPagadorDto = new TermoPagadorDto();
        try {
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            if (rs.next()) {
                termoPagadorDto = montaTermoPagadorEletronico(rs);
            }
        } catch (SQLException e) {
            throw new OperacionalException("integracaocip.erro.obter.lista.tratamento.erro.cip", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return termoPagadorDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#listarComprovanteAdesaoDDA(java.lang.String)
     */
    public List<PagadorEletronicoDDADto> listarComprovanteAdesaoDDA(String numCpfCnpjPagador) {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<PagadorEletronicoDDADto> listaPagadorEletronicoDDADto = new ArrayList<PagadorEletronicoDDADto>();
        try {
            comando = getComando("LISTAR_COMPROVANTE_ADESAO_DDA");
            comando.adicionarVariavel("numCpfCnpjPagador", numCpfCnpjPagador);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                PagadorEletronicoDDADto pagadorEletronicoDDADto = new PagadorEletronicoDDADto();
                pagadorEletronicoDDADto.setIdEventoDDA(rs.getInt("IDHISTORICOTERMOPAGADOR"));
                pagadorEletronicoDDADto.setDataEvento(new DateTimeDB(rs.getDate("DATAHORATERMODDA").getTime()));
                pagadorEletronicoDDADto.setDescOperacao(rs.getString("DESCCOMPROVANTE"));
                listaPagadorEletronicoDDADto.add(pagadorEletronicoDDADto);
            }
        } catch (SQLException e) {
            getLogger().erro(e, "LISTAR_COMPROVANTE_ADESAO_DDA");
            throw new PersistenceException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaPagadorEletronicoDDADto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#listarAgencia(java.lang.Long)
     */
    public List<Integer> listarAgencia(Long idPagadorDDA) throws ComumException {
        debug("### Carregando Lista Pagador Conta ###");
        debug("Parâmetro - idPagadorDDA: " + idPagadorDDA);
        return listar("OBTER_LISTA_AGENCIA_PAGADOR", getPagarametroIdPagador(idPagadorDDA));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#listarPagadorEletronico(java.lang.Integer)
     */
    public List<PagadorDto> listarPagadorEletronico(Integer numAgencia) throws OperacionalException {
        debug("### Carregando Lista de Pagadores Eletrônicos ###");
        debug("Parâmetro - Agência: " + numAgencia);

        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<PagadorDto> listaPagadoresEletronicos = new ArrayList<PagadorDto>();
        try {
            comando = getComando("PESQUISAR_PAGADOR_ELETRONICO");
            comando.adicionarVariavel("numAgencia", numAgencia);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs != null && rs.next()) {
                listaPagadoresEletronicos.add(montaPagadorEletronico(rs));
            }
        } catch (SQLException e) {
            throw new OperacionalException("Erro ao obter lista de pagadores", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaPagadoresEletronicos;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#obterDadosPagador(java.lang.String)
     */
    public PagadorDto obterDadosPagador(String numCpfCnpj) throws ComumException {
        debug("### Obtendo dados Pagador  ###");
        debug("Parâmetro - CPF/CNPJ: " + numCpfCnpj);
        return obterDados("OBTER_PAGADOR_ELETRONICO", getPagarametroNumCpfCnpjPagador(numCpfCnpj), 0, 1);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#listarAgregadosPorIdPagador(java.lang.Long)
     */
    public List<PagadorAgregadoDto> listarAgregadosPorIdPagador(Long idPagadorDDA) throws ComumException {
        debug("### Carregando lista de agregados por ID  ###");
        debug("Parâmetro - idPagadorDDA: " + idPagadorDDA);
        return listar("LISTAR_AGREGADOS_POR_PAGADOR", getPagarametroIdPagador(idPagadorDDA));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#listarPagadorAgregado(java.lang.String, java.lang.Integer)
     */
    public List<PagadorAgregadoDto> listarPagadorAgregado(String numCpfCnpj, Integer numAgencia) throws ComumException {
        debug("### Carregando lista de Pagador/Agregado ###");
        debug("Parâmetro - CPF: " + numCpfCnpj);
        debug("Parâmetro - AGENCIA: " + numAgencia);
        return listar("PESQUISAR_AGREGADO", getPagarametroListarPagadorAgregado(numCpfCnpj, numAgencia));
    }

    /**
     * Método responsável por
     * 
     * @param idPagadorDDA
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getPagarametroIdPagador(Long idPagadorDDA) {
        return getMapaParametro(idPagadorDDA, "idPagadorDDA");
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getPagarametroNumCpfCnpjPagador(String numCpfCnpj) {
        return getMapaParametro(numCpfCnpj, "numCpfCnpj");
    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @param numAgencia
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getPagarametroListarPagadorAgregado(String numCpfCnpj, Integer numAgencia) {
        Map<String, Object> parametro = new HashMap<String, Object>();
        parametro.put("criterios.filtro.numCpfCnpj", numCpfCnpj);
        parametro.put("criterios.filtro.numCooperativaSingular", numAgencia);
        return parametro;
    }

    /**
     * @param rs
     * @return HistoricoPagadorEletronicoDto
     * @throws SQLException
     */
    private HistoricoPagadorEletronicoDto montaListaHistoricoPagadorEletronico(ResultSet rs) throws SQLException {
        return new HistoricoPagadorEletronicoDto(rs.getShort("CODTIPOTERMOPAGADOR"), rs.getString("DESCTIPOTERMOPAGADOR"), rs.getDate("DATAHORATERMODDA"), rs.getShort("IDCANAL"),
                rs.getString("NUMCPFCNPJPAGADOR"), rs.getString("NOMEPESSOAPAGADOR"), rs.getString("NUMCPFCNPJAGREGADO"), rs.getString("NOMEPESSOAAGREGADO"));
    }

    /**
     * @param rs
     * @return TermoPagadorDto
     * @throws SQLException
     */
    private TermoPagadorDto montaTermoPagadorEletronico(ResultSet rs) throws SQLException {
        return new TermoPagadorDto(rs.getShort("IDTERMOPAGADOR"), rs.getShort("CODTIPOTERMOPAGADOR"), rs.getDate("DATAINICIOVIGENCIA"), rs.getDate("DATAFIMVIGENCIA"),
                rs.getBoolean("BOLFORMATOHTML"), rs.getString("DESCCONTEUDOTERMOPAGADOR"), rs.getString("DESCTIPOTERMOPAGADOR"), rs.getString("DESCOUVIDORIA"));

    }

    /**
     * Método responsável por
     * 
     * @param numCpfCnpj
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> getMapaNumCpfCnpj(String numCpfCnpj) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("numCpfCnpj", numCpfCnpj);
        return parametros;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException PagadorAgregadoDto
     * 
     */
    private PagadorAgregadoDto montaPagadorAgregadoDto(ResultSet rs) throws SQLException {
        return new PagadorAgregadoDto(rs.getString("NUMCPFCNPJ"), rs.getString("NOMEPESSOA"));
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException PagadorDto
     * 
     */
    private PagadorDto montaPagadorEletronico(ResultSet rs) throws SQLException {
        return new PagadorDto(rs.getLong("IDPAGADORDDA"), rs.getInt("NUMAGENCIA"), rs.getString("NUMCPFCNPJ"), rs.getString("NOMEPESSOAPAGADOR"), rs.getString("NUMDDDRESIDENCIAL"),
                rs.getString("NUMTELEFONERESIDENCIAL"), rs.getString("NUMDDDCELULAR"), rs.getString("NUMTELEFONECELULAR"), rs.getString("DESCLOGRADOURO"),
                rs.getString("DESCCOMPLEMENTO"), rs.getString("DESCNUMERO"), rs.getString("NOMEBAIRRO"), rs.getString("NOMELOCALIDADE"), rs.getString("SIGLAUF"),
                rs.getString("CODCEP"), rs.getInt("QTDADESAODDA"), rs.getBoolean("BOLSACADOELETRONICO"), rs.getBoolean("BOLPAGADORSICCOB"), rs.getInt("QTDAGREGADOS"),
                rs.getDate("DATAHORAADESAO"));
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemPendentePagadorDto
     * 
     */
    private MensagemPendentePagadorDto montaMensagemPendentePagadorDto(ResultSet rs) throws SQLException {
        return new MensagemPendentePagadorDto(rs.getDate("DATAHORACADASTRO"), rs.getString("DESCTIPOMENSAGEMDDA"), rs.getString("DESCSITUACAOMENSAGEMDDA"),
                rs.getString("NUMCPFCNPJ"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.PagadorEletronicoDDADao#isCpfCnpjAgregado(java.lang.Character, java.lang.String)
     */
    public boolean isPagadorEletronicoSicoob(Character codTipoPessoa, String numCpfCnpj) throws ComumException {
        debug("### Verificando se o CPF/CNPJ é pagador eletrônico Sicoob");
        debug("Parâmetro - codTipoPessoaPagador: " + codTipoPessoa);
        debug("Parâmetro - numCpfCnpjPagador: " + numCpfCnpj);

        Connection conn = null;
        Comando comando = null;
        ResultSet rs = null;

        try {
            comando = getComando("VERIFICA_PAGADOR_ELETRONICO_SICOOB");
            comando.adicionarVariavel("numCpfCnpj", numCpfCnpj);
            comando.adicionarVariavel("codTipoPessoa", codTipoPessoa.toString());
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);

            return rs.next();
        } catch (SQLException e) {
            throw new ComumException("operacional.pagadordda.erro.verificar.pagador.eletronico", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
    }

}
