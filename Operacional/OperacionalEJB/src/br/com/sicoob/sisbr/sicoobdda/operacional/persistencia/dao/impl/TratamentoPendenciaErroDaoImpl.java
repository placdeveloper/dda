/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         TratamentoPendenciaErroDaoImpl.java
 * Data Criação:    Sep 13, 2016
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.bancoob.persistencia.excecao.PersistenciaException;
import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroAgrupadoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.ErroProcessamentoCipDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MensagemErroDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.PendenciaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TratamentoMesagemDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.MensagemDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.TipoTratamentoErroCip;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalException;
import br.com.sicoob.sisbr.sicoobdda.operacional.negocio.excecao.OperacionalNoResultException;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao;
import br.com.sicoob.tipos.DateTime;

/**
 * TratamentoPendenciaErroDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class TratamentoPendenciaErroDaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements TratamentoPendenciaErroDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.tratamento.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-tratamento";

    private static final String COD_CATEGORIA = "codCategoria";
    private static final String DATA_MOVIMENTO = "dataMovimento";
    private static final String ID_MENSAGEM_DDA = "IDMENSAGEMDDA";
    private static final String DATA_MOVIMENTO_RS = "DATAMOVIMENTO";
    private static final String COD_TIPO_ERRO_CIP = "CODTIPOERROCIP";
    private static final String LISTA_ID_MENSAGEM = "listaIdMensagem";
    private static final String DESC_TIPO_ERRO_CIP = "DESCTIPOERROCIP";
    private static final String COD_TIPO_MENSAGEM_DDA = "CODTIPOMENSAGEMDDA";
    private static final String COD_SITUACAO_MENSAGEM_DDA = "codSituacaoMensagemDDA";
    private static final String ID_ERRO_MENSAGEM_RETORNO_CIP = "IDERROMENSAGEMRETORNOCIP";
    private static final String DESC_SITUACAO_MENSAGEM_DDA = "DESCSITUACAOMENSAGEMDDA";

    /**
     * @param arquivoQueries
     * @param nomeColecaoComandos
     * @param clazz
     * @param nomeComandoPesquisar
     * @param nomeComandoPesquisaNumeroRegistros
     */
    public TratamentoPendenciaErroDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#listarPendencia()
     */
    public List<PendenciaDto> listarPendencia() throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<PendenciaDto> listaPendenciaDto = new ArrayList<PendenciaDto>();
        try {
            comando = getComando("OBTER_LISTA_PENDENCIA");
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaPendenciaDto.add(montaPendenciaDto(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "OBTER_LISTA_PENDENCIA");
            throw new OperacionalException("integracaocip.erro.obter.lista.pendecia", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaPendenciaDto;

    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#listarErroAgrupado()
     */
    public List<ErroAgrupadoDto> listarErroAgrupado() throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<ErroAgrupadoDto> listaErroAgrupadoDto = new ArrayList<ErroAgrupadoDto>();
        try {
            comando = getComando("OBTER_LISTA_ERRO_AGRUPADO");
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaErroAgrupadoDto.add(montaErroAgrupadoDto(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "OBTER_LISTA_ERRO_AGRUPADO");
            throw new OperacionalException("integracaocip.erro.obter.lista.erro.agrupado", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaErroAgrupadoDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws OperacionalException
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#listarErroProcessamento()
     */
    public List<ErroProcessamentoCipDto> listarErroProcessamento() throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<ErroProcessamentoCipDto> listaErroProcessamentoCipDto = new ArrayList<ErroProcessamentoCipDto>();
        try {
            comando = getComando("OBTER_LISTA_ERRO_PROCESSAMENTO_CIP");
            comando.adicionarVariavel("inicioCODMSG", "<CodMsg>");
            comando.adicionarVariavel("finalCODMSG", "</CodMsg>");
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaErroProcessamentoCipDto.add(montaErroProcessamentoCipDto(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "OBTER_LISTA_ERRO_PROCESSAMENTO_CIP");
            throw new OperacionalException("integracaocip.erro.obter.lista.erro.processamento.cip", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaErroProcessamentoCipDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#listarTipoTratamentoErroCip(java.lang.Short)
     */
    @SuppressWarnings("unchecked")
    public List<TipoTratamentoErroCip> listarTipoTratamentoErroCip(Short codSituacaoMensagemDDA) {
        Comando comando = null;
        List<TipoTratamentoErroCip> listaTipoTratamentoErroCip = null;
        try {
            comando = getComando("LISTA_TIPO_TRATAMENTO_ERRO_CIP");
            comando.adicionarVariavel(COD_SITUACAO_MENSAGEM_DDA, codSituacaoMensagemDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            Query query = comando.criarQuery(getEntityManager());
            listaTipoTratamentoErroCip = (List<TipoTratamentoErroCip>) query.getResultList();
        } finally {
            fecharComando(comando);
        }
        return listaTipoTratamentoErroCip;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#obterListaTratamentoErroCIP(br.com.sicoob.tipos.DateTime,
     *      java.lang.Short, java.lang.String, int)
     */
    public List<MensagemErroDto> obterListaTratamentoErroCIP(DateTime dataMovimento, Short codSituacaoMensagemDDA, String codTipoMensagemDDA, int maxResult)
            throws OperacionalException {
        return obterListaTramentoErro(criarComandoListaTratamentoErro("OBTER_LISTA_TRATAMENTO_ERRO_CIP", dataMovimento, codSituacaoMensagemDDA, null, codTipoMensagemDDA, maxResult));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#obterListaTratamentoRetornoErro(java.lang.String,
     *      br.com.sicoob.tipos.DateTime, int)
     */
    public List<MensagemErroDto> obterListaTratamentoRetornoErro(String codTipoMensagemDDA, DateTime dataMovimento, int maxResult) throws OperacionalException {
        return obterListaTratamentoRetornoErro(dataMovimento, null, codTipoMensagemDDA, maxResult);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#obterListaTratamentoErroAgrupado(java.lang.String, int)
     */
    public List<MensagemErroDto> obterListaTratamentoErroAgrupado(String codTipoErroCIP, int maxResult) throws OperacionalException {
        return listaTratamentoErroAgrupado(codTipoErroCIP, maxResult);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#obterListaTratamentoMensagemArquivo(br.com.sicoob.tipos.DateTime,
     *      int)
     */
    public List<MensagemErroDto> obterListaTratamentoMensagemArquivo(DateTime dataMovimento, int maxResult) throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<MensagemErroDto> listaTratamentoErro = new ArrayList<MensagemErroDto>();
        try {
            comando = getComando("OBTER_LISTA_TRATAMENTO_MENSAGEM_ARQUIVO");
            comando.adicionarVariavel(DATA_MOVIMENTO, dataMovimento);
            comando.adicionarVariavel("maxResult", maxResult);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaTratamentoErro.add(montaMensagemErroArquivoDto(rs));
            }
        } catch (SQLException e) {
            throw new OperacionalException("integracaocip.erro.obter.lista.tratamento.mensagem.arquivo", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaTratamentoErro;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#obterListaTratamentoMensagemContingenciaBatch(br.com.bancoob.persistencia.types.DateTimeDB,
     *      java.lang.Long, java.lang.Long, java.lang.String)
     */
    public List<Long> obterListaTratamentoMensagemContingenciaBatch(DateTimeDB dataMovimento, Long idMensagemInicial, Long idMensagemFinal, String codTipoMensagem)
            throws ComumException {
        Map<String, Object> parametros = getMapaParametro(dataMovimento, DATA_MOVIMENTO);
        parametros.put("codTipoMensagem", codTipoMensagem);
        parametros.put("idMensagemInicial", idMensagemInicial);
        parametros.put("idMensagemFinal", idMensagemFinal);
        return listar("OBTER_LISTA_TRATAMENTO_MENSAGEM_CONTINGENCIA_BATCH", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#atualizarDataMovimentoMensagem(java.util.Date)
     */
    public void atualizarDataMovimentoMensagem(Date dataMovimento) throws ComumException {
        atualizarDataMovimentoMensagem(null, dataMovimento);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#atualizarDataMovimentoMensagem(java.util.List)
     */
    public void atualizarDataMovimentoMensagem(List<Long> listaIdMensagem, Date dataMovimento) throws ComumException {
        executarUpdate("ATUALIZAR_DATA_MOVIMENTO_MENSAGEM", criarComandoUpdateTratarMensagem(listaIdMensagem, dataMovimento));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#reenviarSSPBMensagem(java.util.List, java.util.Date)
     */
    public void reenviarSSPBMensagem(List<Long> listaIdMensagem, Date dataMovimento) throws ComumException {
        executarUpdate("ATUALIZAR_REENVIAR_SSPB", criarComandoUpdateTratarMensagem(listaIdMensagem, dataMovimento));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#reenviarCIPMensagem(java.util.List, java.util.Date)
     */
    public void reenviarCIPMensagem(List<Long> listaIdMensagem, Date dataMovimento) throws ComumException {
        executarUpdate("ATUALIZAR_REENVIAR_CIP", criarComandoUpdateTratarMensagem(listaIdMensagem, dataMovimento));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#finalizarMensagem(java.util.List, java.lang.Short)
     */
    public void finalizarMensagem(List<Long> listaIdMensagem) throws ComumException {
        executarUpdate("ATUALIZAR_FINALIZAR_MENSAGEM", criarComandoUpdateTratarMensagem(listaIdMensagem));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#reenviarMensagemFinalizadaSSPB(java.util.List, java.util.Date)
     */
    public void reenviarMensagemFinalizadaSSPB(List<Long> listaIdMensagem, Date dataMovimentoProduto) throws ComumException {
        executarUpdate("ATUALIZAR_REENVIAR_FINALIZADA_SSPB", criarComandoUpdateTratarMensagem(listaIdMensagem, dataMovimentoProduto));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#reprocessarArquivoRecebido(java.util.List)
     */
    public void reprocessarArquivoRecebido(List<Long> listaIdLogDetRecebimento) throws ComumException {
        executarUpdate("ATUALIZAR_REPROCESSAR_ARQUIVO", criaComandoUpdateReprocessarArquivo(listaIdLogDetRecebimento));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#prepararReenvioArquivo(java.util.List)
     */
    public void prepararReenvioArquivo(List<Long> listaIdLogRecebimento) throws ComumException {
        executarUpdate("ATUALIZAR_PREPARAR_REEVIO_ARQUIVO", criarComandoUpdateTratarMensagem(listaIdLogRecebimento));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#obterDetalheMensagemErro(java.lang.Long)
     */
    public MensagemDDA obterDetalheMensagemErro(Long idMensagemDDA) throws OperacionalNoResultException {
        Comando comando = null;
        try {
            comando = getComando("OBTER_MENSAGEM_ERRO");
            comando.adicionarVariavel("idMensagemDDA", idMensagemDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());
            Query query = comando.criarQuery(getEntityManager());
            return (MensagemDDA) query.getSingleResult();
        } catch (NoResultException e) {
            throw new OperacionalNoResultException("integracaocip.erro.obter.detalhe.mensagem.erro", e);
        } finally {
            fecharComando(comando);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#obterListaMensagemErroLock(java.util.List)
     */
    public List<MensagemDDA> obterListaMensagemErroLock(List<Long> listaIdMensagem) throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<MensagemDDA> listaMensagem = new ArrayList<MensagemDDA>();
        try {
            comando = getComando("OBTER_MENSAGEM_ERRO_LOCK_REGISTRO");
            comando.adicionarVariavel(LISTA_ID_MENSAGEM, listaIdMensagem);
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaMensagem.add(montaMensagemDDA(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "OBTER_MENSAGEM_ERRO_LOCK_REGISTRO");
            throw new OperacionalException("integracaocip.erro.obter.lista.mensagem.lock.registro", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaMensagem;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#obterListaTratamentoAutomatizado()
     */
    public List<TratamentoMesagemDto> obterListaTratamentoAutomatizado() throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        Comando comando = null;
        List<TratamentoMesagemDto> listaTratamentoDto = new ArrayList<TratamentoMesagemDto>();
        try {
            comando = getComando("OBTER_LISTA_TRATAMENTO_AUTOMATIZADO");
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaTratamentoDto.add(montaTramatentoDto(rs));
            }
        } catch (SQLException e) {
            getLogger().erro(e, "OBTER_LISTA_TRATAMENTO_AUTOMATIZADO");
            throw new OperacionalException("integracaocip.erro.obter.lista.tratamento.automatizado", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaTratamentoDto;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#excluirMensagem(java.lang.Long)
     */
    public void excluirMensagem(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEMDDA", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemBeneficiarioRepresentante(java.lang.Long)
     */
    public void removerMensagemBeneficiarioRepresentante(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BENEFICIARIO_REPRESENTANTE", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemBeneficiarioConvenio(java.lang.Long)
     */
    public void removerMensagemBeneficiarioConvenio(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BENEFICIARIO_CONVENIO", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemBeneficiario(java.lang.Long)
     */
    public void removerMensagemBeneficiario(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BENEFICIARIO", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemPagadorAgregado(java.lang.Long)
     */
    public void removerMensagemPagadorAgregado(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_PAGADOR_AGREGADO", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemPagadorConta(java.lang.Long)
     */
    public void removerMensagemPagadorConta(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_PAGADOR_CONTA", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemPagador(java.lang.Long)
     */
    public void removerMensagemPagador(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_PAGADOR", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemGrupoCalculo(java.lang.Long)
     */
    public void removerMensagemGrupoCalculo(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BOLETO_GRUPO_CALCULO", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemNotaFiscal(java.lang.Long)
     */
    public void removerMensagemNotaFiscal(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BOLETO_NOTA_FISCAL", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemTextoInfo(java.lang.Long)
     */
    public void removerMensagemTextoInfo(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BOLETO_TEXTO_INFO", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemBoleto(java.lang.Long)
     */
    public void removerMensagemBoleto(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BOLETO", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemBaixaEfetiva(java.lang.Long)
     */
    public void removerMensagemBaixaEfetiva(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BAIXA_EFETIVA", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemConsultaBoleto(java.lang.Long)
     */
    public void removerMensagemConsultaBoleto(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_CONSULTA_BOLETO", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemBaixaOperacional(java.lang.Long)
     */
    public void removerMensagemBaixaOperacional(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_BAIXA_OPERACIONAL", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemAceite(java.lang.Long)
     */
    public void removerMensagemAceite(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_ACEITE", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerMensagemTerceiroAutorizado(java.lang.Long)
     */
    public void removerMensagemTerceiroAutorizado(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_MENSAGEM_TERCEIRO_AUTORIZADO", getMapaParametro(idMensagemDDA, "id"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#removerLogDetalheEnvioArquivoDDA(java.lang.Long)
     */
    public void removerLogDetalheEnvioArquivoDDA(Long idMensagemDDA) throws ComumException {
        executarUpdate("EXCLUIR_LOG_DETALHE_ENVIO_ARQUIVO", getMapaParametro(idMensagemDDA, "id"));

    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#atualizarPrioridadeMensagem(java.util.List, java.lang.Integer)
     */
    public void atualizarPrioridadeMensagem(List<Long> listaIdMensagem, Integer numPrioridade) throws ComumException {
        Map<String, Object> parametros = getMapaParametro(listaIdMensagem, "listaId");
        parametros.put("numPrioridade", numPrioridade);
        executarUpdate("ALTERAR_PRIORIDADE_MENSAGEM", parametros);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.TratamentoPendenciaErroDao#excluirLogErroMensagemDDA()
     */
    public void excluirLogErroMensagemDDA(List<Long> listaIdMensagem) throws ComumException {
        Connection conn = null;
        try {
            conn = estabelecerConexao();
            Map<String, Object> parametros = getMapaParametro(listaIdMensagem, "listaId");
            executarUpdate("EXCLUIR_LOGDETALHEENVIOARQUIVODDA_MENSAGEM_REENVIADA", parametros);
            executarUpdate("EXCLUIR_ERROMENSAGEMRETORNOCIP_MENSAGEM_REENVIADA", parametros);
            executarUpdate("EXCLUIR_MENSAGEM_REENVIADA", parametros);
        } finally {
            fecharConexao(conn);
        }

    }

    /**
     * @param codTipoErroCIP
     * @param maxResult
     * @return
     * @throws OperacionalException List<MensagemErroDto>
     * 
     */
    private List<MensagemErroDto> listaTratamentoErroAgrupado(String codTipoErroCIP, int maxResult) throws OperacionalException {
        return obterListaTramentoErroAgrupado(criarComandoObterTratamentoErroAgrupado("LISTA_TRATAMENTO_ERRO_AGRUPADO", codTipoErroCIP, maxResult));
    }

    /**
     * Método responsável por
     * 
     * @param dataMovimento
     * @param codTipoErroCIP
     * @param codTipoMensagemDDA
     * @param maxResult
     * @return
     * @throws OperacionalException List<MensagemErroDto>
     * 
     */
    private List<MensagemErroDto> obterListaTratamentoRetornoErro(DateTime dataMovimento, String codTipoErroCIP, String codTipoMensagemDDA, int maxResult)
            throws OperacionalException {
        return obterListaTramentoErro(criarComandoListaTratamentoErro("OBTER_LISTA_TRATAMENTO_RETORNO_ERRO", dataMovimento, null,
                codTipoErroCIP, codTipoMensagemDDA, maxResult));
    }

    /**
     * Método responsável por
     * 
     * @param comando
     * @return
     * @throws OperacionalException List<MensagemErroDto>
     * 
     */
    private List<MensagemErroDto> obterListaTramentoErro(Comando comando) throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        List<MensagemErroDto> listaTratamentoErro = new ArrayList<MensagemErroDto>();
        try {
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();
            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaTratamentoErro.add(montaMensagemErroDto(rs));
            }
        } catch (SQLException e) {
            throw new OperacionalException("integracaocip.erro.obter.lista.tratamento.erro.cip", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaTratamentoErro;
    }

    /**
     * Método responsável por
     * 
     * @param comando
     * @return List<MensagemErroDto>
     * @throws OperacionalException List<MensagemErroDto>
     * 
     */
    private List<MensagemErroDto> obterListaTramentoErroAgrupado(Comando comando) throws OperacionalException {
        ResultSet rs = null;
        Connection conn = null;
        List<MensagemErroDto> listaTratamentoErro = new ArrayList<MensagemErroDto>();
        try {
            comando.configurar();
            getLogger().info(comando.getSqlEmUso());
            conn = estabelecerConexao();

            rs = comando.executarConsulta(conn);
            while (rs.next()) {
                listaTratamentoErro.add(montaMensagemErroAgrupadoDto(rs));
            }
        } catch (SQLException e) {
            throw new OperacionalException("integracaocip.erro.obter.lista.tratamento.erro.cip", e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }
        return listaTratamentoErro;
    }

    /**
     * Método responsável por
     * 
     * @param nomeComando
     * @param dataMovimento
     * @param codSituacaoMensagemDDA
     * @param codTipoErroCIP
     * @param maxResult
     * @param bolErroArquivo
     * @return Comando
     * 
     */
    private Comando criarComandoListaTratamentoErro(String nomeComando, DateTime dataMovimento, Short codSituacaoMensagemDDA, String codTipoErroCIP, String codTipoMensagemDDA,
            int maxResult) {
        Comando comando = getComando(nomeComando);
        comando.adicionarVariavel("dataMovimento", dataMovimento);
        comando.adicionarVariavel("codTipoErroCIP", codTipoErroCIP);
        comando.adicionarVariavel(COD_SITUACAO_MENSAGEM_DDA, codSituacaoMensagemDDA);
        comando.adicionarVariavel("codTipoMensagemDDA", codTipoMensagemDDA);
        comando.adicionarVariavel("maxResult", maxResult);
        return comando;
    }

    /**
     * @param nomeComando
     * @param codTipoErroCIP
     * @param maxResult
     * @return Comando
     */
    private Comando criarComandoObterTratamentoErroAgrupado(String nomeComando, String codTipoErroCIP, int maxResult) {
        Comando comando = getComando(nomeComando);
        comando.adicionarVariavel("codTipoErroCIP", codTipoErroCIP);
        comando.adicionarVariavel("maxResult", maxResult);
        return comando;
    }

    /**
     * Método responsável por
     * 
     * @param listaIdMensagem
     * @param dataMovimento
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> criarComandoUpdateTratarMensagem(List<Long> listaIdMensagem, Date dataMovimento) {
        return criarComandoUpdateTratarMensagem(listaIdMensagem, dataMovimento, null);
    }

    /**
     * Método responsável por
     * 
     * @param listaIdMensagem
     * @param codTipoTratamento
     * @param situacaoMensagem
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> criarComandoUpdateTratarMensagem(List<Long> listaIdMensagem) {
        return criarComandoUpdateTratarMensagem(listaIdMensagem, null, null);
    }


    /**
     * Método responsável por
     * 
     * @param listaId
     * @param dataMovimento
     * @param codTipoTratamento
     * @param situacaoMensagem
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> criarComandoUpdateTratarMensagem(List<Long> listaId, Date dataMovimento, Short codTipoTratamento) {
        Map<String, Object> parametros = setParametroListaId(listaId);
        parametros.put("dataMovimento", dataMovimento);
        parametros.put("codTipoTratamento", codTipoTratamento);
        return parametros;
    }

    /**
     * Método responsável por
     * 
     * @param listaIdLogDetRecebimento
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> criaComandoUpdateReprocessarArquivo(List<Long> listaIdLogDetRecebimento) {
        return setParametroListaId(listaIdLogDetRecebimento);
    }

    /**
     * Método responsável por
     * 
     * @param listaId
     * @return Map<String,Object>
     * 
     */
    private Map<String, Object> setParametroListaId(List<Long> listaId) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("listaId", listaId);
        return parametros;
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException PendenciaDto
     * 
     */
    private PendenciaDto montaPendenciaDto(ResultSet rs) throws SQLException {
        return new PendenciaDto(rs.getDate(DATA_MOVIMENTO_RS), rs.getString("CODTIPOMENSAGEMDDA"), rs.getInt("A_ENVIAR"), rs.getInt("SEM_RETORNO_SSPB"),
                rs.getInt("SEM_RETORNO_CIP"), rs.getInt("RETORNO_COM_ERRO"));
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException ErroAgrupadoDto
     * 
     */
    private ErroAgrupadoDto montaErroAgrupadoDto(ResultSet rs) throws SQLException {
        return new ErroAgrupadoDto(rs.getString("ERRO_PRINCIPAL"), rs.getString("ERRO_DEPENDENTE"), rs.getInt("QTD_ERRO"));
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException ErroProcessamentoCipDto
     * 
     */
    private ErroProcessamentoCipDto montaErroProcessamentoCipDto(ResultSet rs) throws SQLException {
        return new ErroProcessamentoCipDto(rs.getString("CODTIPOMENSAGEMDDA"), rs.getDate(DATA_MOVIMENTO_RS), rs.getInt("QTD"));
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemErroDto
     * 
     */
    private MensagemErroDto montaMensagemErroDto(ResultSet rs) throws SQLException {
        return new MensagemErroDto(rs.getDate(DATA_MOVIMENTO_RS), rs.getLong(ID_MENSAGEM_DDA), rs.getString(COD_TIPO_MENSAGEM_DDA), rs.getString("TIPODOC"),
                rs.getString("IDENTIFICADOR"), rs.getString(COD_TIPO_ERRO_CIP), rs.getString(DESC_TIPO_ERRO_CIP), rs.getInt(ID_ERRO_MENSAGEM_RETORNO_CIP),
                rs.getString("DESCSITUACAOMENSAGEMDDA"), rs.getDate("DATAHORACADASTRO"), rs.getDate("DATAHORAMENSAGEM"));
    }

    /**
     * @param rs
     * @return MensagemErroDto
     * @throws SQLException
     */
    private MensagemErroDto montaMensagemErroAgrupadoDto(ResultSet rs) throws SQLException {
        return new MensagemErroDto(rs.getLong(ID_MENSAGEM_DDA), rs.getDate("DATAHORACADASTRO"), rs.getDate(DATA_MOVIMENTO_RS), rs.getString(COD_TIPO_MENSAGEM_DDA),
                rs.getString("TIPODOC"), rs.getString("IDENTIFICADOR"), rs.getString(COD_TIPO_ERRO_CIP), rs.getString(DESC_TIPO_ERRO_CIP), rs.getInt(ID_ERRO_MENSAGEM_RETORNO_CIP),
                rs.getString(DESC_SITUACAO_MENSAGEM_DDA), rs.getDate("DATAHORAMENSAGEM"));
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemErroDto
     * 
     */
    private MensagemErroDto montaMensagemErroArquivoDto(ResultSet rs) throws SQLException {
        return new MensagemErroDto(rs.getDate(DATA_MOVIMENTO_RS), rs.getString(COD_TIPO_ERRO_CIP), rs.getString(DESC_TIPO_ERRO_CIP), rs.getString("CODTIPOMENSAGEM"),
                rs.getString("DESCNOMEARQUIVORECEBIDO"), rs.getLong("IDLOGRECEBIMENTOOARQUIVODDA"));
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return TratamentoMesagemDto
     * @throws SQLException
     * 
     */
    private TratamentoMesagemDto montaTramatentoDto(ResultSet rs) throws SQLException {
        return new TratamentoMesagemDto(rs.getLong(ID_MENSAGEM_DDA), rs.getLong(ID_ERRO_MENSAGEM_RETORNO_CIP), rs.getShort("CODTIPOTRATAMENTOAUTOMATIZADO"));
    }

    /**
     * Método responsável por
     * 
     * @param rs
     * @return
     * @throws SQLException MensagemDDA
     * 
     */
    private MensagemDDA montaMensagemDDA(ResultSet rs) throws SQLException {
        return new MensagemDDA(rs.getLong(ID_MENSAGEM_DDA), rs.getString("XMLMENSAGEM"));
    }

    /**
     * Método responsável por
     * 
     * @param conn
     * @param nomeComando
     * @throws ComumException void
     * 
     */
    private void executarUpdate(Connection conn, String nomeComando) throws ComumException {
        Comando comando = null;
        try {
            comando = getComando(nomeComando);
            getLogger().debug(comando.getSqlEmUso());
            comando.executarAtualizacao(conn);
        } catch (PersistenciaException e) {
            throw new ComumException(e);
        } finally {
            fecharComando(comando);
        }
    }
}
