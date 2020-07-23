/**
 * Projeto:         Cobrança Bancária
 * Camada Projeto:  sdda-operacional-ejb
 * Pacote:          br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl
 * Arquivo:         MonitoracaoDaoImpl.java
 * Data Criação:    Nov 10, 2017
 */
package br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.persistencia.comandos.Comando;
import br.com.sicoob.sisbr.sicoobdda.comum.excecao.ComumException;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.DetMonitoracaoDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoArqRemessaDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDDA0110Dto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.MonitoracaoDemaisMensagensDto;
import br.com.sicoob.sisbr.sicoobdda.comum.negocio.dto.TipoErroCipDto;
import br.com.sicoob.sisbr.sicoobdda.entidades.LogRecebimentoArquivoDDA;
import br.com.sicoob.sisbr.sicoobdda.entidades.SicoobDDAEntidade;
import br.com.sicoob.sisbr.sicoobdda.entidades.enums.CategoriaMensagemDDAEnum;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao;
import br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.OperacionalCrudDaoDB2;

/**
 * MonitoracaoDaoImpl é responsável por
 * 
 * @author Felipe.Rosa
 */
public class MonitoracaoDaoImpl extends OperacionalCrudDaoDB2<SicoobDDAEntidade> implements MonitoracaoDao {

    public static final String ARQUIVO_QUERIES = "sicoobdda_operacional.monitoracao.queries.xml";
    public static final String NOME_COLECAO_COMANDOS = "comandos-sicoobdda-operacional-monitoracao";

    /**
     * 
     */
    public MonitoracaoDaoImpl() {
        super(ARQUIVO_QUERIES, NOME_COLECAO_COMANDOS, SicoobDDAEntidade.class, null, null);
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#obterDadosMonitoracaoDDA0110(java.lang.String)
     */
    public MonitoracaoDDA0110Dto obterDadosMonitoracaoDDA0110(String dataHoraUltimaAfericao) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_MONITORACAO_DDA0110");
            comando.adicionarVariavel("dataHoraUltimaAfericao", dataHoraUltimaAfericao);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                return new MonitoracaoDDA0110Dto(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getLong(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharResultSet(rs);
            fecharComando(comando);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#obterDadosMonitoracaoDemaisMensagens()
     */
    public MonitoracaoDemaisMensagensDto obterDadosMonitoracaoDemaisMensagens() throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_MONITORACAO_DEMAIS_MENSAGENS");
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                return new MonitoracaoDemaisMensagensDto(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getLong(4), rs.getBoolean(5), rs.getBoolean(6));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharResultSet(rs);
            fecharComando(comando);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#obterDadosMonitoracaoArquivoRemessa()
     */
    public MonitoracaoArqRemessaDto obterDadosMonitoracaoArquivoRemessa() throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_MONITORACAO_ARQUIVO_REMESSA");
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                return new MonitoracaoArqRemessaDto(rs.getLong(1), rs.getLong(2), rs.getLong(3), rs.getLong(4), rs.getBoolean(5), rs.getBoolean(6));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharResultSet(rs);
            fecharComando(comando);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#listarGEN0015(java.util.Date, short)
     */
    public List<LogRecebimentoArquivoDDA> listarGEN0015(Date dataMovimento) throws ComumException {
        return listar("LISTAR_GEN0015", getMapaParametro(dataMovimento, "dataMovimento"));
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#obterDetalheMonitoracaoDemaisMensagens()
     */
    public List<DetMonitoracaoDto> obterDetalheMonitoracaoDemaisMensagens() throws ComumException {
        return obterDetalheMonitoracao(CategoriaMensagemDDAEnum.ENVIO_FILA.getCodCategoriaMensagemDDA());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#obterDadosMonitoracaoErroDemaisMensagens()
     */
    public List<TipoErroCipDto> obterDadosMonitoracaoErroDemaisMensagens() throws ComumException {
        return obterDadosMonitoracaoErro(CategoriaMensagemDDAEnum.ENVIO_FILA.getCodCategoriaMensagemDDA());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#obterDetalheMonitoracaoArquivoRemessa()
     */
    public List<DetMonitoracaoDto> obterDetalheMonitoracaoArquivoRemessa() throws ComumException {
        return obterDetalheMonitoracao(CategoriaMensagemDDAEnum.ENVIO_ARQUIVO.getCodCategoriaMensagemDDA());
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#obterDadosMonitoracaoErroArquivoRemessa()
     */
    public List<TipoErroCipDto> obterDadosMonitoracaoErroArquivoRemessa() throws ComumException {
        return obterDadosMonitoracaoErro(CategoriaMensagemDDAEnum.ENVIO_ARQUIVO.getCodCategoriaMensagemDDA());
    }

    /**
     * Método responsável por
     * 
     * @param codCategoriaMensagemDDA
     * @return
     * @throws ComumException List<DetMonitoracaoDto>
     * 
     */
    private List<DetMonitoracaoDto> obterDetalheMonitoracao(String codCategoriaMensagemDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<DetMonitoracaoDto> listaDetalhe = new ArrayList<DetMonitoracaoDto>();
        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_DETALHE_MONITORACAO");
            comando.adicionarVariavel("codCategoriaMensagemDDA", codCategoriaMensagemDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                listaDetalhe.add(new DetMonitoracaoDto(rs.getDate(1), rs.getString(2), rs.getLong(3), rs.getLong(4), rs.getLong(5), rs.getLong(6)));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharResultSet(rs);
            fecharComando(comando);
        }
        return listaDetalhe;
    }

    /**
     * Método responsável por
     * 
     * @param codCategoriaMensagemDDA
     * @return
     * @throws ComumException List<TipoErroCipDto>
     * 
     */
    private List<TipoErroCipDto> obterDadosMonitoracaoErro(String codCategoriaMensagemDDA) throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;
        List<TipoErroCipDto> listaErro = new ArrayList<TipoErroCipDto>();
        try {
            conn = estabelecerConexao();
            comando = getComando("OBTER_DADOS_MONITORACAO_ERRO");
            comando.adicionarVariavel("codCategoriaMensagemDDA", codCategoriaMensagemDDA);
            comando.configurar();
            getLogger().debug(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);

            while (rs.next()) {
                listaErro.add(new TipoErroCipDto(rs.getString(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharConexao(conn);
            fecharResultSet(rs);
            fecharComando(comando);
        }
        return listaErro;
    }

    /**
     * {@inheritDoc}
     * 
     * @see br.com.sicoob.sisbr.sicoobdda.operacional.persistencia.dao.MonitoracaoDao#obterDadosMonitoracaoDDA0110Contingencia()
     */
    public MonitoracaoDDA0110Dto obterDadosMonitoracaoDDA0110Contingencia() throws ComumException {
        Comando comando = null;
        Connection conn = null;
        ResultSet rs = null;

        MonitoracaoDDA0110Dto dto = null;

        try {
            conn = estabelecerConexao();

            comando = getComando("OBTER_MONITORACAO_DDA0110_CONTINGENCIA");
            comando.configurar();

            debug(comando.getSqlEmUso());

            rs = comando.executarConsulta(conn);

            if (rs.next()) {
                dto = new MonitoracaoDDA0110Dto();
                dto.setDda0110Sucesso(rs.getLong(1));
                dto.setDda0110Rejeitada(rs.getLong(2));
            }
        } catch (SQLException e) {
            throw new ComumException(e);
        } finally {
            fecharResultSet(rs);
            fecharComando(comando);
            fecharConexao(conn);
        }

        return dto;
    }

}